using UnityEngine;
using System.Collections;

/** 
	Part of the event system. Use this for listening for user interface events, such as rollovers and pressing/releasing.
*/
public class UIEvent : EQEQEvent 
{
	/** 
		A Press event type.
	*/
	public const string PRESS = "press";
	
	/** 
		An event for holding down the mouse button or maintaining a touch within a rect.
	*/
	
	public const string HOLD = "hold";
	
	/** 
		A Release event type.
	*/
	public const string RELEASE = "release";
	
	/** 
		A RollOver event type.
	
		Note: Not available in iOS/Android
	*/
	public const string ROLLOVER = "roll over";
	
	/** 
		A RollOut event type.
	
		Note: Not available in iOS/Android
	*/
	public const string ROLLOUT = "roll out";
	
	/** 
		A Disable event type.
	*/
	public const string DISABLE = "disable";
	
	/** 
		An Enable event type.
	*/
	public const string ENABLE = "enable";
	
	/** 
		Creates a new UIEvent with the type and whatever value you wish to communicate (Internally, it is most commonly the object which fired the event)
	*/
	public UIEvent(string type, Object value) : base(type,value)
	{
	}
	
}
