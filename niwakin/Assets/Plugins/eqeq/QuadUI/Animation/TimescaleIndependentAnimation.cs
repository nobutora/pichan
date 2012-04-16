using UnityEngine;
using System.Collections;

[AddComponentMenu("Quad UI/Animation/Timescale Independent Animation")]
[RequireComponent(typeof(Animation))]
/**
	TimescaleIndependentAnimations are just that, an Animation that ignores Time.timeScale and will play regardless of what Time.timeScale is.
	This is achieved by a custom deltaTime variable, found in QuadUI.deltaTime.
	
	Note: This component invokes RequireComponent, and requires an Animation component attached to its GameObject. It is also important to note that AnimationEvents are not currently available through this method of playback. There are a few hooks provided for you to attach code at certain points of the animation in this class.
*/
public class TimescaleIndependentAnimation : MonoBehaviour 
{	
	internal float _accumulatedTime = 0F;
	public AnimationState _currentState;
	private bool _animating = false;
	
	/**
		Takes the <string> name of the AnimationState.
		Internally updates the normalized time with a Time.timeScale independent delta (QuadUI.deltaTime).
		The provided AnimationState will have a weight of 1, be on layer 1, use AnimationBlendMode.Blend. Calling this function sets the normalizedTime to 0 before playing if the animation is not Paused, otherwise it picks up where it left off.
		
	Note: The provided AnimationState will retain the wrapMode defined in its creation.
		The hook OnAnimationBegin() is called from this function.
	*/
	public void Play(string state)
	{
		if(_currentState == animation[state] && _currentState.normalizedTime > 0 && _accumulatedTime > 0)
		{
			_animating = true;
			return;
		}
		
		_currentState = animation[state];
		if(!_currentState) return;
		
		_currentState.enabled = true;
		_currentState.weight = 1F;
		_currentState.wrapMode = _currentState.clip.wrapMode;//_currentState.clip.wrapMode;
		_currentState.layer = 1;
		_currentState.blendMode = AnimationBlendMode.Blend;
		_currentState.normalizedTime = 0;
		_accumulatedTime = 0F;
		OnAnimationBegin();
		
		_animating = true;
	}
	
	/**
		Stops the current AnimationState from updating and returns the animation to frame 1.
		Note: When calling Stop() all data set when calling Play() is reset, and normalizedTime will be set to 0 resulting in the animation returning to frame 1.
		
		The hook OnAnimationStop() is called from this function.
	*/
	public void Stop()
	{
		if(!_currentState) return;
		
		_currentState.enabled = false;
		_currentState.weight = 0F;
		_currentState.layer = 0;
		_currentState.normalizedTime = 0;
		_accumulatedTime = 0F;
		OnAnimationStop();
		
		_animating = false;
	}
	
	/**
		Stops the current AnimationState from updating, but retains its current frame.
		Note: There is no hook called from Pause.
	*/
	public void Pause()
	{
		_animating = false;
	}
	
	void Update()
	{
		if(_animating) UpdateAnimation();
	}
	
	internal virtual void UpdateAnimation()
	{		
		//accumulate time
		_accumulatedTime += QuadUI.deltaTime;
		//place where the animation should be.
		_currentState.normalizedTime = _accumulatedTime/_currentState.length; 
		//check for completion
		if(_accumulatedTime >= _currentState.length) 
		{
			if(_currentState.wrapMode == WrapMode.Loop)
			{
				OnLoop();
			}
			else
			{
				OnAnimationComplete();
				_currentState.enabled = false;
				_animating = false;
			}
		}
	}
	
	/**
		Takes the <string> name of the AnimationState and the <float> 0.0F-1.0F desired normalizedTime of the animation you wish to move to.
		Allows you to manually set the normalizedTime of the provided AnimationState.
	*/
	public void ManuallySetNormalizedTime(string animState, float _normalizedTime)
	{
		if(_currentState)_currentState.enabled = false;
		_currentState = animation[animState];
		_currentState.enabled = true;
		_currentState.weight = 1F;
		_currentState.wrapMode = WrapMode.Once;
		_currentState.layer = 1;
		_currentState.blendMode = AnimationBlendMode.Blend;
		_currentState.normalizedTime = _normalizedTime;
	}
	
	/**
		This function is virtual and can be overridden when extending this class.
		
		This function is intended to be a hook and is called from Play(string state). You should use this function to execute any code you want to happen when the AnimationState begins playing.
	*/
	protected virtual void OnAnimationBegin()
	{	
	}
	
	/**
		This function is virtual and can be overridden when extending this class.
		
		This function is intended to be a hook and is called from Stop(). You should use this function to execute any code you want to happen when the AnimationState is told to stop playing.
	*/
	protected virtual void OnAnimationStop()
	{	
	}
	
	/**
		This function is virtual and can be overridden when extending this class.
		
		This function is intended to be a hook and is called when the AnimationState successfully reaches a normalizedTime of 1. You should use this function to execute any code you want to happen when the AnimationState finishes playing.
		
		Note: This hook will only be called if the wrapMode of the current AnimationState is set to Default or Once.
	*/
	protected virtual void OnAnimationComplete()
	{
	}
	
	/**
		This function is virtual and can be overridden when extending this class.
		
		This function is intended to be a hook and is called when the AnimationState successfully reaches a normalizedTime of 1, before it loops back to 0 and continues playing. You should use this function to execute any code you want to happen when the AnimationState loops.
		
		Note: This hook will only be called if the wrapMode of the current AnimationState is set to Loop.
	*/
	protected virtual void OnLoop()
	{
	}
	
	/**
		Read only. Returns true if the animation is playing, false if it is not.
	*/
	public bool isPlaying
	{
		get
		{
			return _animating;
		}
	}
	
	/**
		Read only. Returns the current AnimationState of the animation.
	*/
	public AnimationState currentState
	{
		get
		{
			return _currentState;
		}
	}
	
	internal void ManualTrack(bool b)
	{
		_animating = b;
	}
}
