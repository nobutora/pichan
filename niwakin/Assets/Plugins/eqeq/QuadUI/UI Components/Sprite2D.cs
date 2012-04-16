using UnityEngine;
using System.Collections;

[AddComponentMenu("Quad UI/UI Components/Sprite2D")]
/**
	An non-interactive, animated Sprite which ignores Time.timeScale.
	
	Note: This class uses both Awake() and Update(). If you are extending this class, please use Init() instead of Awake() and OnUpdate() instead of Update(), these hooks are called from their respective functions to maintain the execution order.
*/
public class Sprite2D : Element2D
{
	/**
		Array of all the top-left UV coordinates for each frame.
	*/
	public Vector2[] frames;
	
	/**
		Should the sprite animation play on Awake()?
	*/
	public bool playOnAwake = false;
	
	/**
		Should the sprite animation play backwards?
	*/
	public bool reverse = false;
	
	/**
		Should the sprite animation wrap around/loop?
	*/
	public bool wrap = true;
	
	/**
		The fixed fps of your sprite animation. If set to 0 it will play back at the game's frame rate, changing on Update().
	*/
	public int fps = 0;
	
	int _fps;
	float _fCount;	
	protected int _currFrame = 0;
	bool animating = false;
	
	void Awake()
	{		
		if(playOnAwake) PlayAnimation();
		Init();
	}
	
	void Update()
	{
		if(!animating) return;
		
		if(fps <= 0)
		{
			Step();
		}
		else
		{
			_fps = 1000/fps;
			_fCount += QuadUI.deltaTime;
			if(_fCount >= _fps*.001F)
			{
				_fCount = 0;
				Step();
			}
		}

		OnUpdate();
	}
	
	/**
		This class uses Update for updating UV coordinates of your Quad. If you need to use Update(), use OnUpdate() instead as it is called from Update to maintain the execution order. 
		
		Note: If Sprite2D.animating is false, OnUpdate will not be called.
	*/
	protected virtual void OnUpdate(){}
	
	/**
		This class uses Awake for its own initialization. If you need to use Awake, use Init() instead as it is called from Awake to maintain the execution order.
	*/
	protected virtual void Init(){}
	
	/**
		The sprite will update its UV coordinates on a time step to play the sprite's animation.
	*/
	public void PlayAnimation()
	{
		animating = true;
	}
	
	/**
		The sprite will stop updating its UV coordinates on a time step, effectively stopping the sprite's animation.
	*/
	public void StopAnimation()
	{
		animating = false;
	}
	
	/**
		Moves the sprite's UV coordinates to the next frame index, or the previous frame if reverse is true.
		
		Note: If wrap is false it will not move back to frame[0] once the end is reached, or back to frame[frame.Length-1] if in reverse mode.
	*/
	public void Step()
	{
		if(wrap)
		{
			if(reverse)
			{
				_currFrame = (int) Mathf.Repeat(_currFrame-1,frames.Length);
			}
			else
			{
				_currFrame = (int) Mathf.Repeat(_currFrame+1,frames.Length);
			}
		}
		else
		{
			if(reverse)
			{
				if(_currFrame > 0) _currFrame--;
			}
			else
			{
				if(_currFrame < frames.Length-1) _currFrame++;
			}
		}
		
		GoToFrame(_currFrame);
	}
	
	/**
		Manually moves to frame index of the specified int.
	*/
	public void GoToFrame(int index)
	{
		_currFrame = index;
		quad.UV(frames[_currFrame]);//new Point2D((int)frames[_currFrame].x,(int)frames[_currFrame].y));
	}
	
	/**
		Gets the current frame of your animation / Sets the current frame of your animation and updates the UV coordinates as well. Essentially the same as Sprite2D.GoToFrame(int index).
	*/
	public int currentFrame
	{
		get
		{
			return _currFrame;
		}
		set
		{
			_currFrame = value;
			quad.UV(frames[_currFrame]);//new Point2D((int)frames[_currFrame].x,(int)frames[_currFrame].y));
		}
	}
}
