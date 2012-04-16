using UnityEngine;
using System.Collections;

#region QuadUI structs

[System.Serializable]
/**
	This is a 2 dimenstional struct which unlike Vector2, only accepts int as its parameters. This is to provent the passing of fractional pixels.
	Implicitly casts to a Vector2.
	
	Note: These structs are handled in the background of the framework and you will likely never have to use them at high-level.
*/
public struct Point2D
{
	public int x;
	public int y;
	
	public Point2D(int _x, int _y)
	{
		x = _x;
		y = _y;
	}
	
	public static implicit operator Point2D(Vector2 vec2)
	{
		Point2D p2d;
		
		p2d.x = (int) vec2.x;
		p2d.y = (int) vec2.y;
		
		return p2d;
	}
	
	public static implicit operator Vector2(Point2D p2d)
	{
		return new Vector2(p2d.x, p2d.y);
	}
}

[System.Serializable]
/**
	This is a 2 dimenstional struct which is used for describing the width and height of a quad.
	Implicitly casts to a Vector2.
	
	Note: These structs are handled in the background of the framework and you will likely never have to use them at high-level.
*/
public struct Dimension2D
{
	public float width;
	public float height;
	
	public Dimension2D(float w, float h)
	{
		width = w;
		height = h;
	}
	
	public static implicit operator Dimension2D(Vector2 vec2)
	{
		Dimension2D d2d;
		
		d2d.width = (int) vec2.x;
		d2d.height = (int) vec2.y;
		
		return d2d;
	}
	
	public static implicit operator Vector2(Dimension2D d2d)
	{
		return new Vector2(d2d.width, d2d.height);
	}
}

#endregion

[AddComponentMenu("Quad UI/Quad2D")]
[System.Serializable]
[RequireComponent(typeof(MeshFilter))]
[RequireComponent(typeof(MeshRenderer))]
/**
	This class is sealed an cannot be extended.
	
	This class is your communication to the Quad mesh. Though you can still communicate with the Renderer and Collider components, this class has functions for performing common operations.
	This class invokes RequireComponent on MeshFilter and MeshRenderer.
	
	Note: The MeshFilter, MeshRenderer and MeshCollider components are cached internally on Awake(), so communicating with them through this class is faster than using renderer or collider in any other attached script.
*/
sealed public class Quad2D : MonoBehaviour 
{
	bool _visible = true;
	MeshFilter _meshFilter;
	MeshRenderer _renderer;
	Collider _collider;
	Dimension2D _dimensions;
	Transform _transform;
	
	void Awake()
	{
		CacheComponents();
		_dimensions = new Vector2(_meshFilter.mesh.bounds.size.x, _meshFilter.mesh.bounds.size.y);
	}
	
	void CacheComponents()
	{
		_renderer = GetComponent<MeshRenderer>();
		_meshFilter = GetComponent<MeshFilter>();
		_collider = GetComponent<MeshCollider>();
		_transform = transform;
	}
	
	/**
		Read only. Returns the cached Collider or null if there is none. Faster than using MonoBehaviour.collider in an attached script.
	*/
	new public Collider collider
	{
		get
		{
			return _collider;
		}
	}
	
	/**
		Read only. Returns the cached MeshRenderer. Faster than using MonoBehaviour.renderer in an attached script.
	*/
	new public MeshRenderer renderer
	{
		get
		{
			return _renderer as MeshRenderer;
		}
	}
	
	/**
		Enables/Disables attached MeshRenderer. Use this instead of manually toggling MeshRenderer.enabled.
	*/
	public bool visible
	{
		set
		{
			_visible = value;
			if(_renderer) _renderer.enabled = _visible;
		}
		get
		{
			_visible = _renderer.enabled;
			return _visible;
		}
	}
	
	/**
		Read only. Returns the width and height of the Quad as a Vector2.
	*/
	public Vector2 dimensions
	{
		get
		{
			return _dimensions;
		}
	}
	
	/**
		Takes a Point2D, the top-left coordinate of the UV map.
		
		Note: we use Point2D instead of Vector2 because Vectors support floats, but Point2D forces int. This prevents fractional pixels.
	*/
	public void UV(Vector2 topLeftPixel)
	{
		MoveUV(topLeftPixel);
	}
	
	void MoveUV(Vector2 newCoords)
	{
		Vector2[] newUV = new Vector2[4];
		
		#if UNITY_3_0 || UNITY_3_1
		newUV[0] = CalcUV(new Point2D((int) newCoords.x + (int) _dimensions.width, (int) newCoords.y)); //top right
		newUV[1] = CalcUV(new Point2D((int) newCoords.x, (int) newCoords.y)); //top left
		newUV[2] = CalcUV(new Point2D((int) newCoords.x, (int) newCoords.y + (int) _dimensions.height)); //bottom left
		newUV[3] = CalcUV(new Point2D((int) newCoords.x + (int) _dimensions.width, (int) newCoords.y + (int) _dimensions.height)); //bottom right
		#else
		newUV[0] = CalcUV(new Point2D((int) newCoords.x, (int) newCoords.y)); //top left
		newUV[1] = CalcUV(new Point2D((int) newCoords.x + (int) _dimensions.width, (int) newCoords.y)); //top right
		newUV[2] = CalcUV(new Point2D((int) newCoords.x, (int) newCoords.y + (int) _dimensions.height)); //bottom left
		newUV[3] = CalcUV(new Point2D((int) newCoords.x + (int) _dimensions.width, (int) newCoords.y + (int) _dimensions.height)); //bottom right
		#endif
		
		//print(newUV[0] +","+newUV[1]+","+newUV[2]+","+newUV[3]);
		_meshFilter.mesh.uv = newUV;
	}
	
	/** Short-cut to setting Transform.localScale of the quad.*/
	public void Scale(Vector3 scale)
	{
		_transform.localScale = scale;
	}
	
	/** Short-cut to setting Transform.localPosition of the quad.
	 * Has one overload with the option to specify if position should be assigned to Transform.localPosition*/
	public void MovePosition(Vector3 position)
	{
		_transform.position = position;
	}
	public void MovePosition(Vector3 position, bool isLocal)
	{
		if(isLocal) _transform.localPosition = position;
		else MovePosition(position);
	}
	
	/** Short-cut to getting Transform.localScale of the quad.*/
	public Vector3 GetScale()
	{
		return _transform.localScale;
	}
	
	/** 
	 	Short-cut to getting Transform.localPosition of the quad.
		Note: This is localPosition because of the nature of how Quad's are typically placed - nested inside of Game Objects.
	*/
	public Vector3 GetPosition()
	{
		return _transform.localPosition;
	}
	
	/** Short-cut to getting/setting Renderer.material.color of the quad.*/
	public Color tint
	{
		get
		{
			return _renderer.material.color;
		}
		set
		{
			_renderer.material.color = value;
		}
	}
	
	/** Short-cut to setting Renderer.material.color of the quad.*/
	public void Tint(Color color)
	{
		_renderer.material.color = color;
	}
	
	/** Short-cut to setting the alpha value of Renderer.material.color of the quad.*/
	public void SetAlpha(float alpha)
	{
		Color c = _renderer.material.color;
		c = new Color(c.r,c.g,c.b,alpha);
		_renderer.material.color = c;
	}
	
	/** If you have modified the material in any way to make it different from the sharedMaterial, this will set the quad's material back to its sharedMaterial.
	 * This is very useful if you are keeping a tight draw call budget!*/
	public void RejoinBatch()
	{
		_renderer.material = _renderer.sharedMaterial;
	}
	
	#region Experimental
	/*
	internal Material SharedMaterial
	{
		get
		{
			return _renderer.sharedMaterial;
		}
	}
	
	internal Material[] SharedMaterials
	{
		get
		{
			return _renderer.sharedMaterials;
		}
	}
	
	internal Mesh Quad
	{
		get
		{
			return _meshFilter.mesh;
		}
	}
	
	internal void MeshToHD()
	{
		Mesh mesh = _meshFilter.mesh;
		
		mesh.vertices[1] = new Vector3(mesh.vertices[i].x*2,mesh.vertices[i].y,mesh.vertices[i].z);
		mesh.vertices[3] = new Vector3(mesh.vertices[i].x*2,mesh.vertices[i].y*2,mesh.vertices[i].z);
	}
	*/
	#endregion
	
	Vector2 CalcUV(Vector2 xy) //accepts the pixel coordinates of the 
	{
		Texture t = _renderer.sharedMaterial.GetTexture("_MainTex");
		Dimension2D wh = new Dimension2D(t.width,t.height);
		
		return new Vector2(xy.x / wh.width, (wh.height - xy.y) / wh.height);
	}
	
	Vector2 DecodeUV(Vector2 xy) //xy should be the normalized coordinates in UV space, this function then returns the UV coordinates in pixel coordinates
	{
		Texture t = _renderer.sharedMaterial.GetTexture("_MainTex");
		Dimension2D wh = new Dimension2D(t.width,t.height);
		
		return new Vector2(xy.x * wh.width, xy.y * wh.height);
	}
	
	internal void _PreAwake()
	{
		CacheComponents();
		_dimensions = new Vector2(_meshFilter.mesh.bounds.size.x, _meshFilter.mesh.bounds.size.y);
	}
}
