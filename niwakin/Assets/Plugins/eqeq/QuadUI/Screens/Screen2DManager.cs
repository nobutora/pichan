using UnityEngine;
using System.Collections;

[AddComponentMenu("Quad UI/Screens/Screen2D Manager")]
/**
	This class is abstract and should be used as a base to manager your Screen2D Game Objects.
	
	Note: This class uses Awake for its own initialization purposes. When extending this class do not use Awake, override and use Init() instead. Init() is called from Awake() to maintain execution order.
*/
public class Screen2DManager : MonoBehaviour
{
	/**
		All the Screen2Ds that make up the User Interface / Menu System.
	*/
	public Screen2D[] screens;
	
	/**
		The Screen2D which will open by default.
	*/
	public Screen2D _default;
	
	private Screen2D _openScreen;
	private Screen2D _queuedScreen;
	
	void Awake()
	{
		//add reference of manager to all attached screens.
		foreach(Screen2D screen in screens)
		{
			screen.manager = this;
		}
		
		//any custom initialization can be done here.
		Init();
	}
	
	/**
		This class uses Awake() for its own initialization purposes. When extending this class do not use Awake(), but use Init() instead. Init() is called from Awake() to maintain execution order.	
	*/ 
	protected virtual void Init(){}
	
	/**
		Hook called when a Screen2D closes.
	*/
	public virtual void ScreenDidClose(Screen2D screen){}
	
	/**
		Hook called when a Screen2D opens.
	*/
	public virtual void ScreenDidOpen(Screen2D screen){}
		
	/**
		Hook called when a Screen2D collapse.
	*/
	public virtual void ScreenDidCollapse(Screen2D screen){}
	
	/**
		Hook called when a Screen2D expands.
	*/
	public virtual void ScreenDidExpand(Screen2D screen){}
		
	/**
		Hook called when a transition begins. Use this for disabling input and such.
	*/
	public virtual void TransitionDidBegin(){}
	
	/**
		Hook called when a transition ends. Use this for enabling input and such.
	*/
	public virtual void TransitionDidEnd(){}
	
	/**
		Launch is where your menu should begin. This effectively opens your menu.
		
		If there is no screen currently deamed open, it assigns the default screen to open and calls Screen2D.Open on it,
	*/	
	public void Launch()
	{
		if(!openScreen && _default) _openScreen = _default;
		else throw new System.Exception("Screen2DManager cannot launch. No open screen, no default.");
		_openScreen.Open();
	}
	
	/**
		This will bypass transitions if you aren't using them. This should be your alternative to Launch(). Sets the provided Screen2D screen as the open screen and calls OpenWithCustomTransition() on it.
	*/
	public void OpenScreenWithCustomTransition(Screen2D screen)
	{
		_openScreen = screen;
		_openScreen.OpenWithCustomTransition();
	}
	
	internal Screen2D openScreen
	{
		set
		{
			_openScreen = value;
		}
		get
		{
			return _openScreen;
		}
	}
	
	/**
		Opens the screen with the specified id.
	*/
	public void OpenScreen(int id)
	{
		foreach(Screen2D screen in screens)
		{
			if(screen.id == id)
			{
				if(_openScreen == screen) return;
				screen.Open();
				_openScreen = screen;
				return;
			}
		}
		
		Debug.Log("No Screen2D with id " + id + " exists. Did you add it to the Array in your Screen2DManager?");
	}
	
	/**
		This stores the screen with the provided id so that you can open it later. Call this method when closing a screen so that 
	*/
	public void QueueScreen(int id)
	{
		foreach(Screen2D screen in screens)
		{
			if(screen.id == id)
			{
				_queuedScreen = screen;
				return;
			}
		}
		
		Debug.Log("No Screen2D with id " + id + " exists. Did you add it to the Array in your Screen2DManager?");
	}
	
	/**
		Opens the queued screen if there is one by calling Screen2D.Open()
	*/
	public void OpenQueuedScreen()
	{
		if(_queuedScreen)
		{
			_queuedScreen.Open();
			_openScreen = _queuedScreen;
			_queuedScreen = null;
			return;
		}
		
		Debug.Log("No queued screen!");
	}
	
	/**
		Opens the queued screen if there is one, but unlike OpenQueuedScreen, this calls Screen2D.OpenWithCustomTransition()
	*/
	public void OpenQueuedScreenCustom()
	{
		if(_queuedScreen)
		{
			_queuedScreen.OpenWithCustomTransition();
			_openScreen = _queuedScreen;
			_queuedScreen = null;
			return;
		}
		
		Debug.Log("No queued screen!");
	}
	
	/**
		Closes the open screen.
	*/
	public void CloseScreen()
	{
		if(_openScreen)
		{
			_openScreen.Close();
			_openScreen = null;
		}
		else 
		{
			Debug.Log("No Screen2D Open.");
		}
	}
	
	/**
		Closes the Screen with the specified id.
	*/
	public void CloseScreen(int id)
	{
		if(_openScreen.id == id)
		{
			_openScreen.Close();
			_openScreen = null;
		}
		else 
		{
			screens[id].Close();
		}
	}
}
