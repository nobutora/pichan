using UnityEngine;
using System.Collections;

[AddComponentMenu("Quad UI/UI Components/Button2D")]
[System.Serializable]

/**
	Basic Button with Up, Down and Over states. These states are stored as Vector2's in a Vector2[3], when you generate your quad from the tool. 
	The appropriate UV coordinates are labeled in name-basis getters in this class where "Normal" (Up) is index 0, "Press" (Down) is 1, and "RollOver" is 2.
	
	This class is sealed and cannot be extended.
	
	Note: Under iOS/Android Build Settings, this class will only have Up and Down states, as the Over/Out events are not communicated.
*/
sealed public class Button2D : InteractiveElement2D
{		
	
	#region UV States
	
	/**
		Returns a Vector2 representation of the top-left UV coordinate for the "RollOver" button state. This is the same as frames[2]
	*/
	public Vector2 rollOverUV
	{
		get
		{
			return frames[2];
		}
	}
	
	/**
		Returns a Vector2 representation of the top-left UV coordinate for the "Press" (Down) button state. This is the same as frames[1]
	*/
	public Vector2 pressUV
	{
		get
		{
			return frames[1];
		}
	}
	
	/**
		Returns a Vector2 representation of the top-left UV coordinate for the "Normal" (Up) button state. This is the same as frames[0]
	*/
	public Vector2 normalUV
	{
		get
		{
			return frames[0];
		}
	}
	
	#endregion
	
	#region Interaction Events
	
	sealed override internal void UserPress()
	{
		quad.UV(pressUV);
	}
	
	sealed override internal void UserRelease()
	{
		#if !UNITY_IPHONE && !UNITY_ANDROID
			if(_hasFocus) quad.UV(rollOverUV);
			else quad.UV(normalUV);
		#else
			quad.UV(normalUV);
			//quad.UV(new Point2D((int)normalUV.x,(int)normalUV.y));
		#endif
	}
	
	#if UNITY_IPHONE || UNITY_ANDROID
	
	sealed override internal void UserCanceled()
	{
		//quad.UV(new Point2D((int)normalUV.x,(int)normalUV.y));
		quad.UV(normalUV);
	}
	
	#endif
	
	/* These are useless for mobile as they are never communicated */
	
	#if !UNITY_IPHONE && !UNITY_ANDROID
	
	sealed override internal void UserOver()
	{
		//quad.UV(new Point2D((int)rollOverUV.x,(int)rollOverUV.y));
		quad.UV(rollOverUV);
	}
	
	sealed override internal void UserOut()
	{
		//quad.UV(new Point2D((int)normalUV.x,(int)normalUV.y));
		quad.UV(normalUV);
	}
	
	#endif
	
	#endregion
}
