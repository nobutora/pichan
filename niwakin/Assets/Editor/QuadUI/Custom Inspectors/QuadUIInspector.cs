using UnityEngine;
using UnityEditor;
using System.Collections;

[CustomEditor(typeof(QuadUI))]
public class QuadUIInspector : Editor
{
	internal const string __version = "1.2.8b";
	
	QuadUI _target;
	
	#if UNITY_IPHONE
	
	string[] resolutionLabels = new string[6]
	{
		"iPhone Portrait (320x480)",
		"iPhone Landscape (480x320)",
		"iPad Portrait (768x1024)",
		"iPad Landscape (1024x768)",
		"iPhone 4 Portrait (640x920)",
		"iPhone 4 Landscape (920x640)"
	};
	
	#elif UNITY_ANDROID
	
	string[] resolutionLabels = new string[9]
	{
		"CUSTOM",
		"HTC Legend Tall ()",
		"HTC Legend Wide ()",
		"Nexus One Tall ()",
		"Nexus One Wide ()",
		"Motorola Droid Tall ()",
		"Motorola Droid Wide ()",
		"Tegra Tablet Tall ()",
		"Tegra Tablet Wide ()"
	};
	
	#endif	
	
	override public void OnInspectorGUI()
	{
		_target = (QuadUI) target;
		
		
		#if UNITY_IPHONE
		int __enumLength = 6;
		_target.resolution = (QuadUI.Resolution) ((int) Mathf.Clamp((int) _target.resolution,0,__enumLength - 1));
		#elif UNITY_ANDROID
		int __enumLength = 9;
		_target.resolution = (QuadUI.Resolution) ((int) Mathf.Clamp((int) _target.resolution,0,__enumLength - 1));
		#else 
		_target.resolution = (QuadUI.Resolution) 0;
		#endif
		
		GUILayout.Box("Current Version: QuadUI " + __version + "",new GUILayoutOption[0]{});
		
		EditorGUILayout.BeginVertical();
		
		_target.allowUserInput = EditorGUILayout.Toggle("Allow User Input:",_target.allowUserInput);
		EditorGUILayout.Space();
		_target.cam = (Camera) EditorGUILayout.ObjectField("Camera",_target.cam,typeof(Camera));
		#if !UNITY_IPHONE && !UNITY_ANDROID
			_target.resolution = QuadUI.Resolution.Custom;
			_target.screenDimensions = EditorGUILayout.Vector2Field("Screen Dimensions:", _target.screenDimensions);
			_target.resizable = EditorGUILayout.Toggle("Resizable:",_target.resizable);
		#else
			//_target.resolution = (QuadUI.Resolution) EditorGUILayout.EnumPopup("Resolution:", (QuadUI.Resolution) _target.resolution);
			#if UNITY_IPHONE

			_target.resolution = (QuadUI.Resolution) EditorGUILayout.Popup("Resolution:",(int) _target.resolution, resolutionLabels,new GUILayoutOption[0]{});
			_target.screenDimensions = ResolutionToDimension();
			
			#elif UNITY_ANDROID
			_target.resolution = (QuadUI.Resolution) EditorGUILayout.Popup("Resolution:",(int) _target.resolution, resolutionLabels,new GUILayoutOption[0]{});		
			if(_target.resolution == QuadUI.Resolution.Custom)
				_target.screenDimensions = EditorGUILayout.Vector2Field("Screen Dimensions:", _target.screenDimensions);
			else
				_target.screenDimensions = ResolutionToDimension();
			#endif
			_target.resizable = EditorGUILayout.Toggle("Resizable:",_target.resizable);
			EditorGUILayout.Space();
			//EditorGUILayout.PrefixLabel("Turning off Remote will enable emulation.");
			_target.remote = EditorGUILayout.Toggle("Unity Remote:", _target.remote,new GUILayoutOption[0]{});
			_target.multitouch = EditorGUILayout.Toggle("Multitouch:", _target.multitouch,new GUILayoutOption[0]{});
			if(_target.multitouch) _target.multitouchCap = EditorGUILayout.IntField("Max Touches: ",Mathf.Clamp(_target.multitouchCap,2,5),new GUILayoutOption[0]{});
		#endif
		
		EditorGUILayout.EndVertical();
	}
	
	Vector2 ResolutionToDimension()
	{
		int screenWidth = 480;
		int screenHeight = 320;
		
		switch(_target.resolution)
		{
			#if UNITY_IPHONE
			case QuadUI.Resolution.iPhonePortrait:
				screenWidth = 320;
				screenHeight = 480;
			break;
			case QuadUI.Resolution.iPhoneLandscape:
				screenWidth = 480;
				screenHeight = 320;
			break;
			case QuadUI.Resolution.iPadPortrait:
				screenWidth = 768;
				screenHeight = 1024;
			break;
			case QuadUI.Resolution.iPadLandscape:
				screenWidth = 1024;
				screenHeight = 768;
			break;
			case QuadUI.Resolution.iPhone4Portrait:
				screenWidth = 640;
				screenHeight = 920;
			break;
			case QuadUI.Resolution.iPhone4Landscape:
				screenWidth = 920;
				screenHeight = 640;
			break;
	
			#elif UNITY_ANDROID

			case QuadUI.Resolution.HTCLegendTall:
				screenWidth = 320;
				screenHeight = 480;
			break;
			case QuadUI.Resolution.HTCLegendWide:
				screenWidth = 480;
				screenHeight = 320;
			break;
			case QuadUI.Resolution.NexusOneTall:
				screenWidth = 480;
				screenHeight = 800;
			break;
			case QuadUI.Resolution.NexusOneWide:
				screenWidth = 800;
				screenHeight = 480;
			break;
			case QuadUI.Resolution.MotorolaDroidTall:
				screenWidth = 480;
				screenHeight = 854;
			break;
			case QuadUI.Resolution.MotorolaDroidWide:
				screenWidth =854;
				screenHeight = 480;
			break;
			case QuadUI.Resolution.TegraTabletTall:
				screenWidth = 600;
				screenHeight = 1024;
			break;
			case QuadUI.Resolution.TegraTabletWide:
				screenWidth = 1024;
				screenHeight = 600;
			break;
			#endif
			
			default:
			screenWidth = Screen.currentResolution.width;
			screenHeight = Screen.currentResolution.height;
			break;
		}
	
		return new Vector2(screenWidth,screenHeight);
		
	}
}