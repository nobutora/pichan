using UnityEngine;
using System.Collections;

[AddComponentMenu("Quad UI/UI Components/Element2D")]
[RequireComponent(typeof(Quad2D))]

/**
	The base of all QuadUI framework UI components. Contains methods accessible from all its inheriters. 
	
	This class does not accept user interaction events, and should be used for visible items which you wish to connect to the framework, but are not interactive. 
*/
public class Element2D : EventDispatcher
{
	/**
		The attached Quad2D.
	*/
	public Quad2D quad;
	
	/**
		Enables/Disables the attached MeshRenderer. This effectively is the same this as "quad.visible" but is here for ease of access.
	*/
	public bool Visible
	{
		set
		{
			quad.visible = value;
		}
		get
		{
			return quad.visible;
		}
	}
	
	/**
		Will change the Tint of the attached material to the assigned Color. This is the same as saying renderer.material.color, but you have to type less.
	*/
	public Color Tint
	{	
		set
		{
			renderer.material.color = value;
		}
		get
		{
			return renderer.material.color;
		}
	}
	
	internal virtual void _PreAwake()
	{
		quad._PreAwake();
	}
}