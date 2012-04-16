using UnityEngine;
using System.Collections;

/** 
	Part of the event system. This holds Screen2D related events like open/close and transition begin/complete.
*/
public class ScreenEvent : EQEQEvent 
{
	/** 
		An Screen Opened event type.
	*/
	public const string OPEN = "open";
	
	/** 
		A Screen Closed event type.
	*/
	public const string CLOSE = "close";
	
	/** 
		A Screen Expanded event type.
	*/
	public const string EXPAND = "expand";
	
	/** 
		A Screen Collapsed event type.
	*/
	public const string COLLAPSE = "collapse";
	
	/** 
		A Transition Began event type.
	*/
	public const string TRANSITION_BEGIN = "transition begin";
	
	/** 
		A Transition Completed event type.
	*/
	public const string TRANSITION_COMPLETE = "transition complete";
	
	public ScreenEvent(string type, Object value) : base(type,value)
	{
	}
	
}
