using UnityEngine;
using System.Collections;

[AddComponentMenu("Quad UI/Back End/Event Dispatcher")]
[System.Serializable]
/**
	Base class which ties objects into the event system. Any class can listen for an event, but only classes which inherit from EventDispatcher can communicate events.
*/
public class EventDispatcher : MonoBehaviour 
{
	private Hashtable _eventListeners = new Hashtable();

	/** 
		Adds a delegate function (EQEQEvent.EventHandler handler) which will be called whenever this object dispatches the event (string type).
	*/
	public virtual void AddEventListener(string type, EQEQEvent.EventHandler handler)
	{
		if(!_eventListeners.Contains(type))
		{
			_eventListeners.Add(type, new ArrayList());
			(_eventListeners[type] as ArrayList).Add(handler);
		}
		else
		{
			(_eventListeners[type] as ArrayList).Add(handler);
		}
	}
	
	/** 
		Removes the specified event listener.
	*/
	public virtual void RemoveEventListener(string type, EQEQEvent.EventHandler handler)
	{
		(_eventListeners[type] as ArrayList).Remove(handler);
	}
	
	/** 
		Does this object have a listener for the specified type?
	*/
	public bool HasEventListener(string type)
	{
		if(!_eventListeners.Contains(type)) return false;
		else return ((_eventListeners[type] as ArrayList).Count > 0) ? true : false;
	}
	
	/** 
		Dispatches a new event object. If this object has any recorded listeners, those handler functions will be called.
	*/
	public void DispatchEvent(EQEQEvent e)
	{
		if(_eventListeners[e.type] == null) return;
		for(int i = 0; i < (_eventListeners[e.type] as ArrayList).Count; i++)
		{
			EQEQEvent.EventHandler handler = (EQEQEvent.EventHandler) (_eventListeners[e.type] as ArrayList)[i];
			handler(e);
		}
	}
	
	/**
		Returns how many listeners this object has for a particular event type. 	
	*/
	
	public int GetNumListenersOfType(string type)
	{
		if(_eventListeners[type] == null) return 0;
		return (_eventListeners[type] as ArrayList).Count;
	}
}