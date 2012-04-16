using UnityEngine;
using System.Collections;

[AddComponentMenu("Quad UI/UI Components/Toggle2D")]
/**
	Basic Toggle class. Contains States for On, On-Over, Off, and Off-Over.
	
	Note: Over states are not available under iOS/Android Build Settings.
*/
public class Toggle2D : InteractiveElement2D
{
	/**
		If true, when this element is disabled by calling Disable() (not to be confused will MonoBehavior.enable, please see Interactive2DElement.Enable() for details) it will set the material's color to new Color(1,1,1,.25F) (25% opacity) and by calling Enable() back to Color.white.
	*/	
	public bool dimOnDisable = true;
	
	bool on = false;
	
	sealed override internal void UserPress()
	{
		if(!_enabled) return;
		#if UNITY_IPHONE || UNITY_ANDROID
		
		Toggle();
		
		#endif
	}
	
	sealed override internal void UserRelease()
	{
		if(!_enabled) return;
		#if !UNITY_IPHONE && !UNITY_ANDROID
		
		Toggle();
		
		#endif
	}	
	
	/* These are useless for mobile as they are never communicated */
	
	sealed override internal void UserOver()
	{
		if(!_enabled) return;
		if(on) quad.UV(onOverUV);//new Point2D((int)onOverUV.x,(int)onOverUV.y));
		else quad.UV(offOverUV);//new Point2D((int)offOverUV.x,(int)offOverUV.y));
	}
	
	sealed override internal void UserOut()
	{
		if(!_enabled) return;
		if(on) quad.UV(onUV);
		else quad.UV(offUV);
	}
	
	/**
		Toggles the button On and Off respectively. If the button is to be turned off by this function, the hook Off() will be called, otherwise the hook On() will be called.
		Additionally, the hook OnToggle is called any time this function of called regardless of state.
	*/
	public void Toggle()
	{
		on = !on;
		if(on)
		{
			quad.UV(onUV);
			//quad.UV(new Point2D((int)onUV.x,(int)onUV.y));
			On();
		}
		else
		{ 
			quad.UV(offUV);
			//quad.UV(new Point2D((int)offUV.x,(int)offUV.y));
			Off();
		}
		
		OnToggle();
	}
	
	/**
		Use this hook for any generic initialization.
		
		Note: This is not called automatically by the framework, this is something you will have to call yourself.
	*/
	protected virtual void Init()
	{	
	}
	
	/**
		Hook called when toggle is switched On.
	*/
	protected virtual void On()
	{	
	}
	
	/**
		Hook called when toggle is switched Off.
	*/
	protected virtual void Off()
	{	
	}
	
	/**
		Extended version of InteractiveElement2D.Enable() to integrate a dimmer on enable/disable. See related Toggle2D.dimOnDisable.
	*/
	sealed override public void Enable()
	{
		if(dimOnDisable) Tint = Color.white;
		base.Enable();
	}
	
	/**
		Extended version of InteractiveElement2D.Disable() to integrate a dimmer on enable/disable. See related Toggle2D.dimOnDisable.
	*/
	sealed override public void Disable()
	{
		if(dimOnDisable) Tint = new Color(1,1,1,.25F);
		base.Disable();
	}
	
	/**
		Generic hook called from Toggle(), not state specific like On() and Off().
		
		Note: This is called after On or Off
	*/
	protected virtual void OnToggle()
	{	
	}
	
	/**
		Toggles the button On and Off respectively. If the button is to be turned off by this function, the hook Off() will be called, otherwise the hook On() will be called.
	*/
	public void SetVal(bool val)
	{
		on = val;
		
		if(val)
		{
			quad.UV(new Point2D((int)onUV.x,(int)onUV.y));
		}
		else
		{
			quad.UV(new Point2D((int)offUV.x,(int)offUV.y));
		}
	}
	
	/**
		Is the toggle on?
	*/
	public bool val
	{
		get
		{
			return on;
		}
	}
	
	/**
		Returns a Vector2 representation of the top-left UV coordinate for the "On" state. This is the same as frames[2]
	*/
	public Vector2 onUV
	{
		get
		{
			return frames[2];
		}
	}
	
	/**
		Returns a Vector2 representation of the top-left UV coordinate for the "Off" state. This is the same as frames[0]
	*/
	public Vector2 offUV
	{
		get
		{
			return frames[0];
		}
	}
	
	/**
		Returns a Vector2 representation of the top-left UV coordinate for the "On" state, but with a MouseOver state. This is the same as frames[3]
		
		Note: MouseOver states are not available under iOS/Android Build Settings
	*/
	public Vector2 onOverUV
	{
		get
		{
			return frames[3];
		}
	}
	
	/**
		Returns a Vector2 representation of the top-left UV coordinate for the "Off" state, but with a MouseOver state. This is the same as frames[1]
		
		Note: MouseOver states are not available under iOS/Android Build Settings
	*/
	public Vector2 offOverUV
	{
		get
		{
			return frames[1];
		}
	}
}
