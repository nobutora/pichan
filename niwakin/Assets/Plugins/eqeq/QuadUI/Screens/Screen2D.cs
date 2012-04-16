using UnityEngine;
using System.Collections;

/**
	QuadUI supports 4 different states of screens: Open,Closed,Expanded and Collapsed. If you are transitioning between screens, the state will be InTransition.
*/
public enum ScreenState
{
	Open = 0,
	InTransition = 1,
	Closed = 2,
	Expanded = 3,
	Collapsed = 4
}

[AddComponentMenu("Quad UI/Screens/Screen2D")]
[RequireComponent(typeof(Screen2DTransition))]
/**
	The encapsulating Screen. This should be attached to the GameObject that is the parent Transform of all the UI elements that are contained in this screen.
	
	This class invokes RequireComponent and requires a Screen2DTransition component to be attached. In turn, Screen2DTransition requires an Animation component.
	
	Note: If you are extending this class, do not use Awake() as it is implemented here. Instead, override and use Init() as it is called from within Awake() to maintain execution order.
*/
public class Screen2D : EventDispatcher
{
	/**
		It is imperative to call this method before moving to another Level, especially if the next level contains another instantiation of QuadUI.
	*/
	public Screen2DManager manager;
	
	/**
		It is imperative to call this method before moving to another Level, especially if the next level contains another instantiation of QuadUI.
	*/
	public Screen2DTransition transition;
	
	internal ScreenState state = ScreenState.Closed;
	
	/** 
		Assign an ID to your Screen so that it is distinguishable from other screens by the Screen2DManager.
		
		Note: ID's are NOT automatically generated, this is because they will be used by you when extending Screen2DManager and you need to know what screen is which.
	*/
	public int id;
	
	void Awake()
	{
		if(transition) transition.screen = this;
		Init();
	}
	
	/**
		Use this method instead of Awake to perform initialization. It is called from within Awake to maintain the execution order.
	*/
	protected virtual void Init(){}
	
	/**
		This will tell the attached Screen2DTransition to play the animation associated with ScreenTransitionType.Open and call the hook OnOpen(), effectively opening the screen.
		Dispatches ScreenEvent.TRANSITION_BEGIN
	*/
	public void Open()
	{
		PlayTransition(ScreenTransitionType.Open);
		DispatchEvent(new ScreenEvent(ScreenEvent.TRANSITION_BEGIN, this));
		OnOpen();
	}
	
	/**
		Use this for bypassing Screen2DTransition and making custom transitions or simply making a Screen appear.
	
		This will call the hook CustomOpen() where you can define custom transitions hide/show code
	*/
	public void OpenWithCustomTransition()
	{
		CustomOpen();
	}
	
	/**
		Use this for bypassing Screen2DTransition and making custom transitions or simply making a Screen disappear.
	
		This will call the hook OnClose() where you can define custom transitions hide/show code
	*/
	public void CloseWithCustomTransition()
	{
		CustomClose();
	}
	
	/**
		Use this for bypassing Screen2DTransition and making custom transitions or simply making a Screen appear.
	
		This will call the hook CustomExpand() where you can define custom transitions hide/show code
	*/
	public void ExpandWithCustomTransition()
	{
		CustomExpand();
	}
	
	/**
		Use this for bypassing Screen2DTransition and making custom transitions or simply making a Screen disappear.
	
		This will call the hook CustomCollapse() where you can define custom transitions hide/show code
	*/
	public void CollapseWithCustomTransition()
	{
		CustomCollapse();
	}
	
	/**
		This will tell the attached Screen2DTransition to play the animation associated with ScreenTransitionType.Close and call the hook OnClose(), effectively closing the screen.
	*/
	public void Close()
	{
		PlayTransition(ScreenTransitionType.Close);
		DispatchEvent(new ScreenEvent(ScreenEvent.TRANSITION_BEGIN, this));
		OnClose();
	}
	
	/**
		This will tell the attached Screen2DTransition to play the animation associated with ScreenTransitionType.Collapse and call the hook OnCollapse(), effectively collapsing the screen.
	*/
	public void Collapse()
	{
		PlayTransition(ScreenTransitionType.Collapse);
		DispatchEvent(new ScreenEvent(ScreenEvent.TRANSITION_BEGIN, this));
		OnCollapse();
	}
	
	/**
		This will tell the attached Screen2DTransition to play the animation associated with ScreenTransitionType.Expand and call the hook OnExpand(), effectively expanding the screen.
	*/
	public void Expand()
	{
		PlayTransition(ScreenTransitionType.Expand);
		DispatchEvent(new ScreenEvent(ScreenEvent.TRANSITION_BEGIN, this));
		OnExpand();
	}
	
	/**
		A hook, called from Open()
	*/
	protected virtual void OnOpen(){}
	/**
		A hook, called from Close()
	*/
	protected virtual void OnClose(){}
	/**
		A hook, called from Collapse()
	*/
	protected virtual void OnCollapse(){}
	/**
		A hook, called from Expand()
	*/
	protected virtual void OnExpand(){}
	
	
	/**
		Hook called from CloseWithCustomTransition()
		
		Use this to initiate customized transitions or hide screen content.
	*/
	protected virtual void CustomClose(){}
	/**
		Hook called from OpenWithCustomTransition()
		
		Use this to initiate customized transitions or show screen content.
	*/
	protected virtual void CustomOpen(){}
	/**
		Hook called from CollapseWithCustomTransition()
		
		Use this to initiate customized transitions or hide screen content.
	*/
	protected virtual void CustomCollapse(){}
	/**
		Hook called from ExpandWithCustomTransition()
		
		Use this to initiate customized transitions or show screen content.
	*/
	protected virtual void CustomExpand(){}
	
	
	/**
		Plays the animation associated with the provided ScreenTransitionType. If you are conforming to the framework, this is for communication between the Screen2DManager and the Screen2D and there is no real reason to call this method explicitly.
		
		The hook OnTransitionBegin(ScreenTransitionType type) is called here.
	*/
	public void PlayTransition(ScreenTransitionType type)
	{		
		state = ScreenState.InTransition; //set the screens state to in transition
		
		if(manager) manager.TransitionDidBegin(); //notify manager we are beginning a transition
		
		OnTransitionBegin(type); //handle all screen-relative stuff when a transition begins
		transition.PlayTransition(type); //play our transition
	}
	
	/**
		This is called from the attached Screen2DTransition upon completion of a transition, there really isn't a reason to call it explicitly.
		
		The hook OnTransitionComplete(ScreenTransitionType type) is called here.
	*/
	public void TransitionCompleted(ScreenTransitionType type)
	{		
		//depending on what transition we just finished performing, change our state to the appropriate one.
		switch(type)
		{
			case ScreenTransitionType.Open:
				state = ScreenState.Open;
				if(manager)
				{
					manager.openScreen = this;
					manager.ScreenDidOpen(this);
				}
				DispatchEvent(new ScreenEvent(ScreenEvent.OPEN, this));
			break;
			
			case ScreenTransitionType.Close:
				state = ScreenState.Closed;
				if(manager)
				{
					manager.openScreen = null;
					manager.ScreenDidClose(this);
				}
				DispatchEvent(new ScreenEvent(ScreenEvent.CLOSE, this));
			break;
			
			case ScreenTransitionType.Collapse:
				state = ScreenState.Collapsed;
				if(manager) manager.ScreenDidCollapse(this);
				DispatchEvent(new ScreenEvent(ScreenEvent.COLLAPSE, this));
			break;
			
			case ScreenTransitionType.Expand:
				state = ScreenState.Expanded;
				if(manager) manager.ScreenDidExpand(this);
				DispatchEvent(new ScreenEvent(ScreenEvent.EXPAND, this));
			break;
		}
		
		DispatchEvent(new ScreenEvent(ScreenEvent.TRANSITION_COMPLETE, this));
		if(manager) manager.TransitionDidEnd(); //notify manager we completed transition
		OnTransitionComplete(type); //handle all the stuff relative to the screen when the transition completes
		transition.Clear(); //clear our cached transition data we no longer need
	}
	
	/**
		Uses Component.GetComponentsInChildren to recursively move through the hierarchy of the screen and set Element2D.Visible to the provided boolean value.
	*/
	public void SetVisibleRecursively(bool b)
	{
		Element2D[] children = (Element2D[]) GetComponentsInChildren(typeof(Element2D));
		foreach(Element2D child in children)
		{
			child.Visible = b;
		}
	}
	
	/**
		Uses Component.GetComponentsInChildren to recursively move through the hierarchy of the screen and call InteractiveElement2D.Enable()
	
		Note: InteractiveElement2D.Enable() is not to be confused with MonoBehaviour.enabled for more info see InteractiveElement2D.Enable()
	*/
	public void EnableRecursively()
	{
		InteractiveElement2D[] children = (InteractiveElement2D[]) GetComponentsInChildren(typeof(InteractiveElement2D));
		foreach(InteractiveElement2D child in children)
		{
			child.Enable();
		}
	}
	
	/**
		Uses Component.GetComponentsInChildren to recursively move through the hierarchy of the screen and call InteractiveElement2D.Disable()
	
		Note: InteractiveElement2D.Disable() is not to be confused with MonoBehaviour.enabled for more info see InteractiveElement2D.Disable()
	*/
	public void DisableRecursively()
	{
		InteractiveElement2D[] children = (InteractiveElement2D[]) GetComponentsInChildren(typeof(InteractiveElement2D));
		foreach(InteractiveElement2D child in children)
		{
			child.Disable();
		}
	}
	
	/**
		Hook called from PlayTransition(ScreenTransitionType type). Place code here to execute when a transition begins.
	*/
	protected virtual void OnTransitionBegin(ScreenTransitionType type){}
	/**
		Hook called from TransitionCompleted(ScreenTransitionType type). Place code here to execute when a transition finishes.
	*/
	protected virtual void OnTransitionComplete(ScreenTransitionType type){}
	
	protected virtual void OnResolutionChange(){}
}
