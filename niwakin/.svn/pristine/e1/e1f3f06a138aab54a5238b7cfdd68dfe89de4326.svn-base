using UnityEngine;
using System.Collections;

/**
	Base Event object. If you are familiar with Adobe Flash's ActionScript 3 this structure will seem rather familiar to you.
	
	Each Event object has a few constant string members which are used as Event Types. When creating a new Event you simply specify the type of event you are looking for, then a function you wish to be called when the event takes place. The function must accept one parameter: the event object (EQEQEvent). In the handler function you will be able to typecast it to the specific event to get any custom data that one might need.
	
	Note: If extending this object, your constructor must look like the following: public MyEvent(string type, object value, object myParameter1, object myParameter2) : base(type,value) - for more info you should refer to extending constructors in the C# reference.
*/
public class EQEQEvent
{
	
	/** 
		The delegate function called when an event is dispatched. Takes one parameter, the created event.
	*/
	public delegate void EventHandler(EQEQEvent e);
	
	private string _type;
	private object _value;
	
	public const string COMPLETE = "complete";
	public const string CHANGED = "changed";
	
	/** 
		Creates a new event with the type of (string type) and an Object which is most commonly the object which dispatched the event.
	*/
	public EQEQEvent(string type, object value)
	{
		_type = type;
		_value = value;
	}
	
	/** 
		Read only. The event type.
	*/
	public string type
	{
		get
		{
			return _type;
		}
	}
	
	/**
		Read only. An open-ended parameter which stores some info from the event dispatcher. It is most commonly the EventDispatcher object.
	*/
	public object value
	{
		get
		{
			return _value;
		}
	}
}