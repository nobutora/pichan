using UnityEngine;
using System.Collections;

/**
	The type of transition to be played. This coincides with the enum ScreenState.
*/
public enum ScreenTransitionType
{
	None = -1,
	Open = 0,
	Close = 1,
	Collapse = 2,
	Expand = 3
}

[AddComponentMenu("Quad UI/Screens/Screen2D Transition")]
/** 
	A Time.timeScale independent animation, used for transitions between screens.
	
	This class is sealed and cannot be extended.
		
	This is *very* important to be noted:

	All AnimationClips for Screen Transitions should be gameObject.name + " Open" or " Close", etc.
	example: If the gameObject.name is "Main Menu" then the animation for Open should be "Main Menu Open"
		
	Since animations are called on a string basis, this is the easiest way I can think of and keeps the project structure clean with naming conventions.	
*/
public sealed class Screen2DTransition : TimescaleIndependentAnimation 
{
	[HideInInspector]
	public Screen2D screen;
	
	internal ScreenTransitionType _type = ScreenTransitionType.None;
	
	AnimationState _open;
	AnimationState _close;
	AnimationState _collapse;
	AnimationState _expand;
	
	void Awake()
	{
		_open = animation[gameObject.name + " Open"];
		_close = animation[gameObject.name + " Close"];
		_collapse = animation[gameObject.name + " Collapse"];
		_expand = animation[gameObject.name + " Expand"];
		
		if(!_open || !_close) return;
		
		_open.weight = 1F;
		_close.weight = 1F;
		
		_open.blendMode = AnimationBlendMode.Blend;
		_close.blendMode = AnimationBlendMode.Blend;
		
		if(!_collapse || !_expand) return;
		
		_collapse.weight = 1F;
		_expand.weight = 1F;
		
		_collapse.blendMode = AnimationBlendMode.Blend;
		_expand.blendMode = AnimationBlendMode.Blend;
	}
	
	/**
		Plays the transition which corresponds with ScreenTransitionType type.	
	*/
	public void PlayTransition(ScreenTransitionType type)
	{
		string suffix = "";
		
		_type = type;
		
		switch(type)
		{
			case ScreenTransitionType.Open:
				suffix = " Open";
				_open.enabled = true;
				_close.enabled = false;
				if(!_collapse || !_expand) break;
				_collapse.enabled = false;
				_expand.enabled = false;
				
			break;
			
			case ScreenTransitionType.Close:
				suffix = " Close";
				_close.enabled = true;
				_open.enabled = false;
				if(!_collapse || !_expand) break;
				_collapse.enabled = false;
				_expand.enabled = false;
			break;
			
			default: return;
		}
				
		Play(gameObject.name + suffix);
	}
	
	/**
		This function is overridden and calls the method Screen2D.TransitionCompleted(ScreenTransitionType _type) of the appropriate Screen2D.	
	*/
	sealed override protected void OnAnimationComplete()
	{
		OnTransitionComplete();
	}
	
	internal void OnTransitionComplete()
	{
		screen.TransitionCompleted(_type);		
	}
	
	public void Clear()
	{
		animation.Stop();
		_accumulatedTime = 0F;
		_type = ScreenTransitionType.None;
	}
}