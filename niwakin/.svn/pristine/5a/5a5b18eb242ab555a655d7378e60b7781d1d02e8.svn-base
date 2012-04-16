using UnityEngine;
using UnityEditor;
using System.Collections;

[CustomEditor(typeof(ResizableBar2D))]
public class ResizableBar2DInspector : Editor
{
	
	float percent = 0; 
	
	public override void OnInspectorGUI ()
	{
		ResizableBar2D bar = (ResizableBar2D) target;
		
		DrawDefaultInspector();
		
		if(!Application.isPlaying) return;
		
		GUILayout.Space(20);
		GUILayout.Label("Percentage Slider:",EditorStyles.boldLabel);
		
		percent = EditorGUILayout.Slider(percent,0,1);
		bar.Scale(percent);
		
	}
}
