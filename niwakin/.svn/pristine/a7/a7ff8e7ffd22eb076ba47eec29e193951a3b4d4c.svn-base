using UnityEngine;
using System.Collections;

[AddComponentMenu("Quad UI/Complex Components/Toolbar2D")]
/**
 	This class is used for mutually exclusive selection. (Like Unity GUI's toolbar, or a group of radio buttons!) It consists of several Toggle2D primitives, as many as you want.
 	The first index of your Toggle2D array toggles will be selected at startup. Be sure to keep that in mind!
 	
 	Note: This could easily be adapted to become a selection grid, just manually position your items in multiple rows.
*/
public class Toolbar2D : ComplexComponent2D
{
	int _selected = 0;
	
	public Toggle2D[] toggles;
	
	void Awake()
	{
		for(int i = 0; i < toggles.Length; i++)
		{
			toggles[i]._PreAwake();
			if(i > 0)toggles[i].SetVal(false);
			else toggles[i].SetVal(true);
			
			toggles[i].id = i;
			toggles[i].AddEventListener(UIEvent.PRESS, SelectionChange);
		}
		
		Init();
	}
	
	internal void SelectionChange(EQEQEvent e)
	{
		toggles[_selected].SetVal(false);
		Toggle2D toggle = (Toggle2D) e.value;
		_selected = toggle.id;
		
		DispatchEvent(new EQEQEvent(EQEQEvent.CHANGED, this));
	}
	
	/**
	 * Sets the selection of the toolbar manually, and dispatches an Event EQEQEvent.CHANGED 
	 * */
	public void SetSelected(int id)
	{
		for(int i = 0; i < toggles.Length; i++)
		{
			Toggle2D toggle = toggles[i];
			
			if(toggle.id == id)
			{
				toggle.SetVal(true);
			}
			else
			{
				toggle.SetVal(false);
			}
		}
		
		DispatchEvent(new EQEQEvent(EQEQEvent.CHANGED, this));
	}
	
	/**
	 * Read-only: Current selection's id.
	 * */
	
	public int selection
	{
		get{return _selected;}
	}
}
