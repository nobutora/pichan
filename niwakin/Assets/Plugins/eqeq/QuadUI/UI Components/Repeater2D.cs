using UnityEngine;
using System.Collections;

[AddComponentMenu("Quad UI/UI Components/Repeater2D")]
[System.Serializable]
/**
 *Very similar to a toggle, but only is "on" as you're pressing down. 
 **/
public class Repeater2D : InteractiveElement2D
{	
	
	bool _bool = false;
	
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
	
	internal override void UserPress ()
	{
		quad.UV(pressUV);
		_bool = true;
		//print("press..");
		DispatchEvent(new UIEvent(UIEvent.HOLD,this));
	}
	
	internal override void UserRelease ()
	{
		_bool = false;
		#if !UNITY_IPHONE && !UNITY_ANDROID
			if(_hasFocus) quad.UV(rollOverUV);
			else quad.UV(normalUV);
		#else
			quad.UV(normalUV);
		#endif
	}
	
#if !UNITY_IPHONE && !UNITY_ANDROID
	
	internal override void UserHold()
	{
		//print("press");
		_bool = true;
		DispatchEvent(new UIEvent(UIEvent.HOLD,this));
	}
	
	internal override void UserOver ()
	{
		quad.UV(rollOverUV);
	}
	
	internal override void UserOut ()
	{
		quad.UV(normalUV);
	}
	
#elif UNITY_IPHONE || UNITY_ANDROID
	
	internal override void UserStayed ()
	{
		//print("press..");
		_bool = true;
		DispatchEvent(new UIEvent(UIEvent.HOLD,this));
	}
	
	internal override void UserMoved (Vector2 position, Vector2 delta)
	{
		//print("press..");
		_bool = true;
		DispatchEvent(new UIEvent(UIEvent.HOLD,this));
	}
	
#endif
	
	public bool down
	{
		get{return _bool;}
	}
}
