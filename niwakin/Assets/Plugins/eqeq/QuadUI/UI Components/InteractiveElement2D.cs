using UnityEngine;
using System.Collections;

[AddComponentMenu("Quad UI/UI Components/Interactive Element2D")]
[RequireComponent(typeof(MeshCollider))]

/**
	This is the base class for all interactive 2D elements.
	Use this class to create your own custom UI elements.
	
	This class invokes RequireComponent and must have a MeshCollider attached.
*/
public class InteractiveElement2D : Element2D
{	
	
	public enum InteractionState
	{
		None,
		#if !UNITY_IPHONE && !UNITY_ANDROID
		Out,
		Over,
		#endif		
		Down,
		Up
		
		#if UNITY_IPHONE || UNITY_ANDROID
		,Stationary,
		Moved
		#endif
	}
	
	/**
		Assign an ID to this element to distinguish it from other elements which are part of the screen.
	*/
	public int id = 0;
	
	/**
		Array of all the top-left UV coordinates. Depending on the framework component, they may be labeled in the Inspector as something specific.
	*/
	public Vector2[] frames;
	internal bool _enabled = true;
	
	internal bool _hasFocus = false;
	[HideInInspector]
	public InteractionState state = InteractionState.None;
	
	#region Messages
	
	/**
		Message recieved when the user touches or presses down on a UI element.
		
		Dispatches UIEvent type PRESS
		Calls hook OnPress()
	*/
	public void OnDown(Vector2 pos)
	{
		if(!_enabled) return;
		#if UNITY_IPHONE || UNITY_ANDROID
		_hasFocus = true;
		#endif
		state = InteractiveElement2D.InteractionState.Down;
		UserPress();
		DispatchEvent(new UIEvent(UIEvent.PRESS,this));
		OnPress();
		
	}
	
	internal virtual void UserPress(){}
	
	/**
		Message recieved when the user releases from a UI element.
		
		Dispatches UIEvent type RELEASE
		Calls hook OnRelease()
	*/
	public void OnUp(Vector2 pos)
	{
		if(!_enabled) return;
		#if UNITY_IPHONE || UNITY_ANDROID
		_hasFocus = false;
		#endif
		state = InteractiveElement2D.InteractionState.Up;
		UserRelease();
		DispatchEvent(new UIEvent(UIEvent.RELEASE,this));
		OnRelease();
		
		
	}
	internal virtual void UserRelease(){}
	
	/**
		Message recieved when the user is pressing down and then moves out of the object's bounding box.
	*/
	
	#if UNITY_IPHONE || UNITY_ANDROID
	
	public void OnCanceled()
	{
		if(!_enabled) return;
		if(state == InteractionState.None) return;
		
		UserCanceled();
		_hasFocus = false;
		state = InteractionState.None;
	}
	internal virtual void UserCanceled(){}
	
	/**
		Message recieved when the user has been pressing down in the same place for atleast 2 consecutive frames.
	*/
	
	public void OnStationary()
	{
		if(!_enabled) return;
				
		UserStayed();
		_hasFocus = true;
		state = InteractionState.Stationary;
	}
	internal virtual void UserStayed(){}
	
	/**
		Message recieved when the user has been pressing down on the same element for at least 2 frames, but has moved within that element since the last frame.
	*/
	
	public void OnMoved(Vector2 position, Vector2 delta)
	{
		if(!_enabled) return;
				
		UserMoved(position,delta);
		_hasFocus = true;
		state = InteractionState.Moved;
	}
	internal virtual void UserMoved(Vector2 position, Vector2 delta){}
	#elif !UNITY_IPHONE && !UNITY_ANDROID
	
	public void OnHold(Vector2 pos)
	{
		if(!_enabled) return;
		
		state = InteractiveElement2D.InteractionState.Down;
		UserHold();
		DispatchEvent(new UIEvent(UIEvent.RELEASE,this));
		OnHoldDown();
	}
	internal virtual void UserHold(){}
	
	#endif
	
	
	/**
		Message recieved when the user's mouse enters the bounding box of the UI element.
		Dispatches UIEvent type ROLLOVER
		Calls hook OnRollover()
		
		Note: This message is not recieved under iOS/Android build settings.
	*/
	public void OnOver(Vector2 pos)
	{
		#if !UNITY_IPHONE && !UNITY_ANDROID
			if(!_enabled) return;
		
			if(_hasFocus) return;
			_hasFocus = true;
		
			UserOver();
			DispatchEvent(new UIEvent(UIEvent.ROLLOVER,this));
			OnRollover();
		#endif
	}
	
	internal virtual void UserOver(){}
	
	/**
		Message recieved when the user's mouse leaves the bounding box of the UI element.
		Dispatches UIEvent type ROLLOUT
		Calls hook OnRollout()
		
		Note: This message is not recieved under iOS/Android build settings.
	*/
	public void OnOut()
	{
		#if !UNITY_IPHONE && !UNITY_ANDROID
		print(gameObject.name + " out!");
		
		if(!_enabled) return;
		
		_hasFocus = false;
		
		UserOut();
		DispatchEvent(new UIEvent(UIEvent.ROLLOUT,this));
		OnRollout();
		#endif
	}
	
	internal virtual void UserOut(){}
	
	#endregion
	
	#region Enable / Disable
	
	/**
		Enables the element to accept user input events.
		Dispatches UIEvent type ENABLE
		Calls hook OnBecameEnabled()
		
		Note: This is not to be confused with MonoBehaviour.enabled which is completely different. Calling MonoBehaviour.enabled will enable/disable the script's ability to Update, while this function is intended to toggle whether this element can accept user interaction events.
	*/
	public virtual void Enable()
	{
		_enabled = true;
		DispatchEvent(new UIEvent(UIEvent.ENABLE, this));
		OnBecameEnabled();
	}
	
	/**
		Disables the element to accept user input events.
		Dispatches UIEvent type DISABLE
		Calls hook OnBecameDisabled()
		
		Note: This is not to be confused with MonoBehaviour.enabled which is completely different. Calling MonoBehaviour.enabled will enable/disable the script's ability to Update, while this function is intended to toggle whether this element can accept user interaction events.
	*/
	public virtual void Disable()
	{
		_enabled = false;
		DispatchEvent(new UIEvent(UIEvent.DISABLE, this));
		OnBecameDisabled();
		_hasFocus = false;
	}
	
	/**
		Disables the element to accept user input events.
	
		Note: This is not to be confused with MonoBehaviour.enabled which is completely different. Calling MonoBehaviour.enabled will enable/disable the script's ability to Update, while this is intended to tell you whether this element can accept user interaction events.
	*/	
	public bool isEnabled
	{
		get
		{
			return _enabled;
		}
	}
	
	#endregion
	
	/**
		Does this object have mouse/touch focus?
	*/	
	public bool hasFocus
	{
		get
		{
			return _hasFocus;
		}
	}
	
	/* These are the overridable methods for when you build your own classes around these. */
	#region Overrides
	/**
		Hook called from Enable()
	*/	
	protected virtual void OnBecameEnabled()
	{
		#if UNITY_EDITOR
		//print(gameObject.name + " " + this + " became enabled.");
		#endif
	}
	
	/**
		Hook called from Disable()
	*/	
	protected virtual void OnBecameDisabled()
	{
		#if UNITY_EDITOR
		//print(gameObject.name + " " + this + " became disabled.");
		#endif
	}
	
	/**
		Hook called when the user touches or presses down on a UI element. 
	*/	
	protected virtual void OnPress()
	{
		#if UNITY_EDITOR
		//print(gameObject.name + " " + this + " was pressed.");
		#endif
	}
	
	/**
		Hook called when the user releases from a UI element. 
	*/	
	protected virtual void OnRelease()
	{
		#if UNITY_EDITOR
		//print(gameObject.name + " " + this + " was released.");
		#endif
	}	
	
	#if !UNITY_IPHONE && !UNITY_ANDROID
	protected virtual void OnHoldDown(){}
	
	/**
		Hook called when the user's mouse enters the bounding box of a UI element.
		
		Note: This hook is not available under iOS/Android Build Settings.
	*/	
	protected virtual void OnRollover()
	{
		#if UNITY_EDITOR
		//print(gameObject.name + " " + this + " was rolled over.");
		#endif
	}
	
	/**
		Hook called when the user's mouse leaves the bounding box of a UI element.
		
		Note: This hook is not available under iOS/Android Build Settings.
	*/	
	protected virtual void OnRollout()
	{
		#if UNITY_EDITOR
		//print(gameObject.name + " " + this + " was rolled out of.");
		#endif
	}
	#endif
	
	#endregion
}