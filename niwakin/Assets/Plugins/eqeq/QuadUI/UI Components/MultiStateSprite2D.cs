using UnityEngine;
using System.Collections;

[System.Serializable]
public class SpriteAnimation
{
	public int firstFrame; //0 based index
	public int lastFrame;
	public bool loop;
	public string name;
	
	public SpriteAnimation(string animationName, int first, int last, bool loopPlayback)
	{
		firstFrame = first;
		lastFrame = last;
		loop = loopPlayback;
		name = animationName;
	}
}

[System.Serializable]
[AddComponentMenu("Quad UI/UI Components/MultiStateSprite2D")]
public class MultiStateSprite2D : Sprite2D 
{
	
	public SpriteAnimation[] animations;
	Vector2[] _cachedFrames;
	
	Vector2[][] _sortedAnims;
	int _currAnim = 0;
	void Awake()
	{
		_cachedFrames = frames;
		if(animations.Length == 0) return;
		_sortedAnims = new Vector2[animations.Length][];
		
		for(int i = 0; i < animations.Length; i++)
		{
			
			_sortedAnims[i] = new Vector2[animations[i].lastFrame-animations[i].firstFrame+1];
			int _count = animations[i].firstFrame;
			
			//print(animations[i].name + " has " + _sortedAnims[i].Length + " frames.");
			for(int j = 0; j < _sortedAnims[i].Length; j++)
			{
				//print(animations[i].name + "["+j+"] is frame " + _count + "");
				_sortedAnims[i][j] = _cachedFrames[_count];
				_count++;
			}
		}
		
		//set the default animation as the first
		frames = _sortedAnims[0];
		
		//because we are overriding...
		if(playOnAwake) PlayAnimation();
		Init();
	}
	
	public void PlayAnimation(string animName)
	{
		for(int i = 0; i < animations.Length; i++)
		{
			if(animations[i].name == animName)
			{
				ApplyAnimationToFrames(i);
				break;
			}
		}
		base.PlayAnimation();
	}
	
	public void PlayAnimation(int index)
	{
		ApplyAnimationToFrames(index);
		base.PlayAnimation();
	}
	
	void ApplyAnimationToFrames(int index)
	{
		_currAnim = index;
		currentFrame = 0;
		wrap = animations[index].loop;
		frames = _sortedAnims[index];
	}
	
	public int currentAnimation
	{
		get{return _currAnim;}
	}
	
}
