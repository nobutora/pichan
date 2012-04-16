using UnityEngine;
using System.Collections;


/**
 * The concept behind a ComplexComponent is actually rather simple: 
 * Attach a ComplexComponent to an empty Game Object and then parent the ComplexComponent's required primitive sub-objects as children. 
 * You can communicate with a ComplexComponent as if it were any primitive in QuadUI!
 * */
[AddComponentMenu("Quad UI/Complex Components/ComplexComponent2D")]
public class ComplexComponent2D : EventDispatcher
{
	protected bool _enabled = true;
	protected bool _visible = true;
	
	void Awake()
	{
		//setup
		Init();
	}
	
	#region Enabling and Visibility
	
	public void Enable()
	{
		_enabled = true;
		OnEnable();
	}
	
	public void Disable()
	{
		_enabled = false;
		OnDisable();
	}
	
	public bool visible
	{
		get
		{
			return _visible;
		}
		set
		{
			_visible = value;
			Visible(value);
		}
	}
	
	protected virtual void Visible(bool val)
	{
	}
	
	#endregion
	
	#region Hooks
	protected void Init(){}
	protected void OnEnable(){}
	protected void OnDisable(){}
	#endregion
}
