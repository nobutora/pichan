using UnityEngine;
using UnityEditor;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System;
using EqualsEquals;

/*
 * I want to give many thanks to Keli Hlodversson (http://keli.dk/) for providing the ObjExporter on the Unify Wiki. 
 * I integrated the scripts into this Editor class to provide the functionality to save the generated meshes.  
 * Big time saver, you are an awesome programmer.
*/

public class QuadExporter
{
	public struct QuadUIObjMaterial
	{
		public string name;
		public string textureName;
	}
	
	private string _meshOutputPath;
	private string _name;
	
	private int vertexOffset = 0;
	private int normalOffset = 0;
	private int uvOffset = 0;
	
	public QuadExporter(string name, string outputPath)
	{
		_meshOutputPath = outputPath;
		_name = name;
	}
	
	public string ExportObj(MeshFilter mf)
	{
		if(!EQEQIO.CreateTargetFolder(_meshOutputPath))
			return "";
		
		MeshToFile(mf, _meshOutputPath, mf.gameObject.name);
		
		return _meshOutputPath + "/" + _name + ".obj";	
	}
	
	public void MeshToFile(MeshFilter mf, string folder, string filename) 
	{
		Dictionary<string, QuadUIObjMaterial> materialList = PrepareFileWrite();
	
		using (StreamWriter sw = new StreamWriter(folder +"/" + filename + ".obj")) 
		{
			sw.Write(MeshToString(mf, materialList));
		}
	}
	
	public Dictionary<string, QuadUIObjMaterial> PrepareFileWrite()
	{
	 	Clear();
		
		return new Dictionary<string, QuadUIObjMaterial>();
	}
	
	public void Clear()
	{
		vertexOffset = 0;
		normalOffset = 0;
		uvOffset = 0;
	}
	
	public string MeshToString(MeshFilter mf, Dictionary<string, QuadUIObjMaterial> materialList) 
	{
		Mesh m = mf.sharedMesh;
		//Material[] mats = new Material[0];//mf.renderer.sharedMaterials;
		
		StringBuilder sb = new StringBuilder();
		
		sb.Append("g ").Append(mf.name).Append("\n");
		foreach(Vector3 lv in m.vertices) 
		{
			Vector3 wv = mf.transform.TransformPoint(lv);
		
			//This is sort of ugly - inverting x-component since we're in a different coordinate system than "everyone" is "used to".
			sb.Append(string.Format("v {0} {1} {2}\n",-wv.x,wv.y,wv.z));
		}
		sb.Append("\n");
		
		/*
		//if UNITY_3_0 || UNITY_3_1
		foreach(Vector3 lv in m.normals) 
		{
			Vector3 wv = mf.transform.TransformDirection(lv);
		
			sb.Append(string.Format("vn {0} {1} {2}\n",-wv.x,wv.y,wv.z));
		}
		
		sb.Append("\n");
		//else
		//Unity 3.2 or later
		*/
		
		sb.Append("s 1"); //smooth groups works across the board, so we'll just use this.
		sb.Append("\n");
		
		/*
		//endif
		*/
		
		foreach(Vector3 v in m.uv) 
		{
			sb.Append(string.Format("vt {0} {1}\n",v.x,v.y));
		}
		
		sb.Append("\n");
		
		try
		{
			 QuadUIObjMaterial objMaterial = new QuadUIObjMaterial();
			 objMaterial.textureName = null;
		}
		catch (ArgumentException)
		{
			//Already in the dictionary
		}
		
		int[] triangles = m.GetTriangles(0);
		
		for (int i=0;i<triangles.Length;i+=3) 
		{
			//Because we inverted the x-component, we also needed to alter the triangle winding.
			sb.Append(string.Format("f {1}/{1}/{1} {0}/{0}/{0} {2}/{2}/{2}\n", 
			triangles[i]+1 + vertexOffset, triangles[i+1]+1 + normalOffset, triangles[i+2]+1 + uvOffset));
		}

		vertexOffset += m.vertices.Length;
		normalOffset += m.normals.Length;
		uvOffset += m.uv.Length;
		
		return sb.ToString();
	}
	
}
