//#define DEBUG

using UnityEngine;
using System.Collections;

#if UNITY_EDITOR && (UNITY_IPHONE || UNITY_ANDROID)
	public enum TouchState
	{
		None = 0,
		Began = 1,
		Moved = 2,
		Stationary = 3,
		Ended = 4
	}
	
#endif

[AddComponentMenu("Quad UI/Quad UI (Main)")]
/**
	This is a Singleton class. Only one QuadUI object may be instantiated at any given time, otherwise an exception is thrown.
	This class is sealed and cannot be extended.
	
	This is your top-level UI manager. All interactive events are dispatched from here. This class also has some helper Gizmos for laying out your UI in the Scene View.
	
	QuadUI is platform-independent as it takes advantage of Unity's platform-dependent compilation to filter out unnecessary user interaction events on platforms.
	For example: A touch interface has no "Mouse Over" event, so anything like that will not be compiled when working within a Mobile build setting such as iOS or Android.
*/
public sealed class QuadUI : MonoBehaviour 
{
	
	/**		
		The screen resolution of your deployment platform. This is used to draw out your wireframe canvas in the Scene View.
		
		Note: This enum is Build-Setting dependent, and therefore different values will be available for different build settings.
		
		For iPhone:
		
			Custom = 0, iPhonePortrait = 1, iPhoneLandscape = 2, iPadPortrait = 3, iPadLandscape = 4, iPhone4Portrait = 5, iPhone4Landscape = 6
		
		For Android:
		
			Custom = 0, HTCLegendTall = 1, HTCLegendWide = 2, NexusOneTall = 3, NexusOneWide = 4, MotorolaDroidTall = 5, MotorolaDroidWide = 6, TegraTabletTall = 7, TegraTabletWide = 8
		
	*/
	public enum Resolution
	{
		#if !UNITY_IPHONE
		Custom = 0
		#endif
		
		#if UNITY_IPHONE
		iPhonePortrait = 0,
		iPhoneLandscape = 1,
		iPadPortrait = 2,
		iPadLandscape = 3,
		iPhone4Portrait = 4,
		iPhone4Landscape = 5
		#endif
		
		#if UNITY_ANDROID
		,HTCLegendTall = 1,
		HTCLegendWide = 2,
		NexusOneTall = 3,
		NexusOneWide = 4,
		MotorolaDroidTall = 5,
		MotorolaDroidWide = 6,
		TegraTabletTall = 7,
		TegraTabletWide = 8
		#endif
	}
	
	#if UNITY_EDITOR && (UNITY_IPHONE || UNITY_ANDROID)
	
	TouchState _touchState;
	bool _mouseDown = false;
	Vector3 _mousePos;
	Vector2 _deltaMousePos;
	
	public bool remote = false;
	#endif
	#if UNITY_IPHONE || UNITY_ANDROID
	
	//we know that there is a maximum of 5 touches, and by that logic a touch's finger id can never be higher than 4.
	InteractiveElement2D[] _prevElements = new InteractiveElement2D[5];//{null,null,null,null,null};
	InteractiveElement2D[] _currElements = new InteractiveElement2D[5];//{null,null,null,null,null};
	//ArrayList _prevEls = new ArrayList(5);
	/**
	 * Enables/Disables multitouch capabilities.
	 * */
	public bool multitouch = true;
	/**
	 * Sets the maximum number of touches that it will register at any given time. Min 2, max 5.
	 * Note: This is only taken into account when multitouch is enabled.
	 * */
	public int multitouchCap = 5;
	#endif
	
	static QuadUI _instance;
	
	/**
		Time.timeScale independent deltaTime. TimescaleIndependentAnimation uses this variable to power its animations.
	*/
	public static float deltaTime;
	
	/**
		If this is false, this class will not perform any automatic raycasts on input events and your UI will effectively be disabled.
	*/
	public bool allowUserInput = true;
	
	/**
		This is the orthographic camera that will fill your screen space. All raycasting is done from here with the Camera's layerBitMask taken into account to filter out non-UI colliders.
	*/
	public Camera cam;
	
	/**
		This should match the resolution your are building the UI for.
	*/
	public QuadUI.Resolution resolution;
	
	float _currTime = 0;
	float _timeAtLastFrame = 0;
	
	int layerBitMask = 1;
	InteractiveElement2D _prevElement;
	InteractiveElement2D _currentElement;
	
	/**
		If QuadUI.resolution is set to Custom, define the dimensions here.
	*/
	public Vector2 screenDimensions = new Vector2(1024,768);
	
	/** Should QuadUI check for resize events? */
	public bool resizable = true;
	
	void Awake()
	{
		if(_instance && _instance != this)
		{
			throw new System.Exception("Singleton Error: More than one [QuadUI] object instantiated at the same time. Please insure only one is instantiated at any given time. If you are moving from scene to scene, make sure you call QuadUI.OnLevelEnd() before you change scenes.");
		}
		_instance = this;
		
		//#if !UNITY_EDITOR
		if(resizable)
		{
			AdaptResolution(Screen.width,Screen.height);
		}
		//#endif
		
		//Screen.SetResolution((int)screenDimensions.x,(int)screenDimensions.y,Screen.fullScreen);		
		layerBitMask = cam.cullingMask;
	}
	
	void Update ()
	{
		CalculateDeltaTime();
		
		if(!allowUserInput) return;
	
		#if UNITY_IPHONE || UNITY_ANDROID
					
			#if UNITY_EDITOR
				
				if(remote) TouchInputUpdate();
				else EmulatedTouchInputUpdate();
			
			#else
				
				TouchInputUpdate();
			
			#endif
		
		#else
			
			MouseInputUpdate();
		
		#endif
		
	}
	
	#region Resolution
	
	static void AdaptResolution(int width, int height)
	{
		//Are the screen dimensions different than the one we published with?
		Vector2 _ss = _instance.screenDimensions;
		//print(_ss);
		if(_ss.x != width && _ss.y != height)
		{
			//print("Screen size reportedly different from when published. QuadUI will scale changes.");
			
			//if so we need to adjust the Camera's position and orthographicSize accordingly!
			DetermineResolution(width,height);
			_instance.screenDimensions = new Vector2(width,height);
			_instance.cam.transform.localPosition = new Vector3(width/2,height/2,_instance.cam.transform.localPosition.z);
			_instance.cam.orthographicSize = height/2;
			
			//tell all of our Screens that we may need to adjust positioning!
			_instance.BroadcastMessage("OnResolutionChange",SendMessageOptions.DontRequireReceiver);
		}
	}
	
	/**
	 * If you are changing the resolution of the screen, call this instead of Screen.SetResolution
	 * This function calls Screen.SetResolution, but also BroadcastMessage for the function OnResolutionChange() which can be found in Screen2D to reposition your 2D elements!
	 * If resizable is set to false, this function will do nothing.
	 * Note: If you are designing for multiple resolutions, animations done with the Unity Animation Tool will probably not work the way you'd expect.
	 */
	public static void ModifyResolution(int width, int height, bool fullscreen)
	{
		if(!_instance.resizable) return;
		
		bool _goAhead = true;
		#if UNITY_IPHONE || UNITY_ANDROID
		print("Modifying the resolution on a mobile device is not recommended, and probably won't even work!");
		_goAhead = false;		
		#endif
		
		if(!_goAhead) return;
		
		Screen.SetResolution(width,height,fullscreen);
		
		DetermineResolution(width,height); //change our resolution enum.
		_instance.screenDimensions = new Vector2(width,height);
		_instance.cam.transform.localPosition = new Vector3(width/2,height/2,_instance.cam.transform.localPosition.z);
		_instance.cam.orthographicSize = height/2;
		
		
		_instance.BroadcastMessage("OnResolutionChange",SendMessageOptions.DontRequireReceiver);		
	}
	
	/** Returns the center pixel of the Screen 
	 */ 
	public static Vector2 screenCenter
	{
		get{return new Vector2(_instance.screenDimensions.x/2,_instance.screenDimensions.y/2);}
	}
		
	static void DetermineResolution(int width, int height)
	{		
		#if UNITY_IPHONE
		switch(width)
		{
			case 320:
				_instance.resolution = QuadUI.Resolution.iPhonePortrait;
			break;
			case 480:
				_instance.resolution = QuadUI.Resolution.iPhoneLandscape;
			break;
			case 768:
				_instance.resolution = QuadUI.Resolution.iPadPortrait;
			break;
			case 1024:
				_instance.resolution = QuadUI.Resolution.iPadLandscape;
			break;
			case 640:
				_instance.resolution = QuadUI.Resolution.iPhone4Portrait;
			break;
			case 960:
				_instance.resolution = QuadUI.Resolution.iPhone4Landscape;
			break;
			default:
				_instance.resolution = QuadUI.Resolution.iPhone4Landscape;
				//throw new System.Exception("ERROR: Resolution ("+width+"x"+height+") not possible on iOS!");
				break;
		}
		#endif
		
		#if UNITY_ANDROID
		switch(width)
		{
			case 320:
				_instance.resolution = QuadUI.Resolution.HTCLegendTall;
			break;
			case 480:					
				if(height == 320) _instance.resolution = QuadUI.Resolution.HTCLegendWide;
				else if(height == 800) _instance.resolution = QuadUI.Resolution.NexusOneTall;
				else if(height == 854) _instance.resolution = QuadUI.Resolution.HTCLegendWide;
				else _instance.resolution = QuadUI.Resolution.Custom;
			break;
			case 800:
				_instance.resolution = QuadUI.Resolution.NexusOneWide;
			break;
			case 854:
				_instance.resolution = QuadUI.Resolution.MotorolaDroidWide;
			break;
			case 600:
				_instance.resolution = QuadUI.Resolution.TegraTabletTall;
			break;
			case 1024:
				_instance.resolution = QuadUI.Resolution.TegraTabletWide;
			break;
			default:
				_instance.resolution = QuadUI.Resolution.Custom;
			break;
		}
		#endif
			
		#if !UNITY_ANDROID && !UNITY_IPHONE
		_instance.resolution = QuadUI.Resolution.Custom;
		#endif
		
		//print("resolution determined to be: " + _instance.resolution);
	}
	
	#endregion
	
	#region Inputs
	
	#if !UNITY_IPHONE && !UNITY_ANDROID
	void MouseInputUpdate()
	{
		RaycastHit hit;
		Ray ray;
		
		ray = (Ray) cam.ScreenPointToRay(Input.mousePosition);
		
		if(Physics.Raycast(ray, out hit, cam.farClipPlane, layerBitMask))
		{
			_currentElement = (InteractiveElement2D) hit.collider.GetComponent(typeof(InteractiveElement2D));
			
			if(_prevElement != null && _currentElement != _prevElement)
			{
				_prevElement.OnOut();
				_prevElement = null;
			}
			
			if(Input.GetMouseButtonDown(0))
			{
				//Mouse Down
				if(_currentElement) _currentElement.OnDown((Vector2)Input.mousePosition);
			}
			else if(Input.GetMouseButtonUp(0))
			{
				//Mouse Up
				if(_currentElement) _currentElement.OnUp((Vector2)Input.mousePosition);
			}
			else if(Input.GetMouseButton(0))
			{
				if(_currentElement)
				{
					_currentElement.OnHold((Vector2)Input.mousePosition);
				}
			}
			else
			{
				//Mouse Over
				if(_currentElement && _currentElement != _prevElement) _currentElement.OnOver((Vector2)Input.mousePosition);
			}
			
			_prevElement = _currentElement;
		}
		else
		{
			//We are over nothing, did we just leave something?
			if(_currentElement)
			{
				_currentElement.OnOut();
				
			}
			
			if(_prevElement && _prevElement != _currentElement)
			{
				_prevElement.OnOut();
			}
				
			_currentElement = null;
			_prevElement = null;	
		}
	}
	#endif	
	#if UNITY_EDITOR && (UNITY_IPHONE || UNITY_ANDROID)
	
	/*
	 * Determines an emulated touch phase (TouchState) for use in Editor to test UI without the use of a Remote.
	 * Note: Only available in Editor when under a Mobile build setting. 
	 * 
	 */
	
	void GetState()
	{
		Vector3 _prevMousePos = _mousePos;
		
		if(!_mouseDown && Input.GetMouseButton(0))
		{
			_touchState = TouchState.Began;
			_mouseDown = true;
			_mousePos = Input.mousePosition;
		}
		else if(_mouseDown && !Input.GetMouseButton(0))
		{
			_touchState = TouchState.Ended;
			_mouseDown = false;
		}
		else if(!_mouseDown && !Input.GetMouseButton(0))
		{
			_touchState = TouchState.None;
			_mouseDown = false;
		}
		else // _mouseDown && pressing down
		{
			if(Input.mousePosition == _mousePos)
			{
				_touchState = TouchState.Stationary;
			}
			else
			{
				_touchState = TouchState.Moved;
			}
			
			_mousePos = Input.mousePosition;
			_deltaMousePos = (Vector2) _mousePos - (Vector2) _prevMousePos;
		}
	}
	
	/*
	 * Essentially does the same thing that TouchInputUpdate() does, but uses the mouse emulated touch as opposed to real touch data.
	 * Note: Only available in Editor when under a Mobile build setting. 
	 * 
	 */
	
	void EmulatedTouchInputUpdate()
	{		
		//Grab the state of the Mouse
		GetState();
		
		if(_touchState != TouchState.None)
		{
			Ray ray;
			RaycastHit hit;
			
			ray = (Ray) cam.ScreenPointToRay(_mousePos);
			
			if(Physics.Raycast(ray,out hit,cam.farClipPlane,layerBitMask))
			{
				
				_currentElement = (InteractiveElement2D) hit.collider.GetComponent(typeof(InteractiveElement2D));
				
				if((_currentElement && _prevElement) && (_currentElement != _prevElement)) _prevElement.OnCanceled();
				
				if(_touchState == TouchState.Began)
				{				
					if(_currentElement) _currentElement.OnDown((Vector2) _mousePos);
				}
				else if(_touchState == TouchState.Stationary)
				{
					if(_currentElement) _currentElement.OnStationary(); //SendMessage("OnStationary",0,SendMessageOptions.DontRequireReceiver);
				}
				else if(_touchState == TouchState.Moved)
				{
					if(_currentElement) _currentElement.OnMoved((Vector2) _mousePos,_deltaMousePos); //SendMessage("OnMoved",(Vector2) _mousePos,SendMessageOptions.DontRequireReceiver);
				}
				else if(_touchState == TouchState.Ended)
				{				
					if(_currentElement) _currentElement.OnUp((Vector2) _mousePos);
				}
				
				_prevElement = _currentElement;
				_currentElement = null;
			}
			else
			{
				//We are over nothing, did we just leave something?
				if(_prevElement) _prevElement.OnCanceled();
				_prevElement = null;
			}
		}
	}
	#endif
	
	#if UNITY_IPHONE || UNITY_ANDROID
	
	void TouchInputUpdate()
	{
		Ray ray;	
		RaycastHit hit;
		
		//account for multitouch capabilities
		int _numTouches = Input.touchCount;
		if(multitouch && _numTouches > multitouchCap) _numTouches = multitouchCap;
		
		if(_numTouches == 0)
		{
			foreach(InteractiveElement2D ie in _currElements)
			{
				if(ie != null) ie.OnCanceled();
			}
			
			_currElements = new InteractiveElement2D[5];
		}
		else if(!multitouch && _numTouches > 1)
		{
			_numTouches = 1;
		}

		//iterate through our touches
		for(int i = 0; i < _numTouches; i++)
		{
			//cache our touch
			Touch touch = Input.touches[i];
			int fingerId = touch.fingerId;
			
			//cast a ray from out touch point on the screen into our world
			ray = (Ray) cam.ScreenPointToRay(touch.position);
			
			//print("finger id: " + touch.fingerId);
			
			//check if the ray hit anything on our layer	
			if(Physics.Raycast(ray,out hit,cam.farClipPlane,layerBitMask))
			{
				//grab the current element.
				_currentElement = (InteractiveElement2D) hit.collider.GetComponent(typeof(InteractiveElement2D));
				
				//is there an element to deal with?
				if(_currentElement)
				{
					//store that element in an array
					_currElements[fingerId] = _currentElement;
					
					//was this element was interacted with either this frame or last?
					if(_currentElement.state != InteractiveElement2D.InteractionState.None)
					{
						
						//was this the finger that was interacting with this element last frame?
						if(_currentElement == _prevElements[fingerId])
						{
							
							switch(touch.phase)
							{
								case TouchPhase.Stationary:
								if(_currentElement.state == InteractiveElement2D.InteractionState.Up) _currentElement.OnDown(touch.position);
								else _currentElement.OnStationary(); //SendMessage("OnStationary",touch.position,SendMessageOptions.DontRequireReceiver);
								break;
								
								case TouchPhase.Moved:
								_currentElement.OnMoved(touch.position,touch.deltaPosition); //SendMessage("OnMoved",touch.position,SendMessageOptions.DontRequireReceiver);
								break;
								
								case TouchPhase.Ended:
								_currentElement.OnUp(touch.position);
								_currElements[fingerId] = null;
								break;
								
								case TouchPhase.Canceled:
								_currentElement.OnCanceled();
								_currElements[fingerId] = null;
								break;
							}
						}
						else //if this is a different finger but in the same loop, we may be able to override based on the current state.
						{
							switch(touch.phase)
							{
								//a touch should only register as began if it occurred the same time as another finger left.
								case TouchPhase.Began:
								if(_currentElement.state == InteractiveElement2D.InteractionState.Up) _currentElement.OnDown(touch.position);
								break;
								
								case TouchPhase.Stationary:
								if(_currentElement.state == InteractiveElement2D.InteractionState.Up && touch.deltaTime <= Time.deltaTime) _currentElement.OnDown(touch.position);
								break;
							}
						}
					}
					else
					{
						//by this logic we know that if this element's state is none, the only way to activate it is to begin a touch on it.
						if(touch.phase == TouchPhase.Began) _currentElement.OnDown(touch.position);
						else _currElements[fingerId] = null;
					}						
				}
				else
				{
					Debug.Log("WARNING: QuadUI Raycast did not find an InteractiveElement2D, but hit a Collider. Was this intended? Make sure your layering is correct.");
					_currElements[fingerId] = null;
				}
			}
			else //no collider was hit, nullify this slot in the array
			{
				
				//print("no collider hit");
				if(_currElements[fingerId] != null)
				{
					//print("last known was: " + _currElements[touch.fingerId].gameObject.name);
					_currElements[fingerId].OnCanceled();
					_currElements[fingerId] = null;
				}
				else
				{
					//print("no collider hit, last known was null. do nothing... ");
				}
				
			}
					
		}
		
		_prevElements = _currElements;	
	}
	
	#endif
	#endregion
	
	void CalculateDeltaTime()
	{
		_currTime = Time.realtimeSinceStartup;
		deltaTime = _currTime - _timeAtLastFrame;
		_timeAtLastFrame = _currTime;
	}
	
	/**
		Static Read only. Returns the Instantiation of this Singleton.
	*/
	public static QuadUI Instance
	{
		get
		{
			if(_instance == null) print("No QuadUI instantiation! You must have an instance of the singleton QuadUI instantiated before accessing this!");
			return _instance;
		}
	}
	
	/**
		Static communication to the Singleton instance's allowUserInput variable. Use this to toggle the activation of your UI. For more info see QuadUI.allowUserInput.
	*/
	public static bool Interactive
	{
		get
		{
			if(_instance == null) print("No QuadUI instantiation! You must have an instance of the singleton QuadUI instantiated before accessing this!");
			return _instance.allowUserInput;
		}
		set
		{
			if(_instance == null) print("No QuadUI instantiation! You must have an instance of the singleton QuadUI instantiated before accessing this!");
			_instance.allowUserInput = value;
		}
	}
	
	/**
		It is imperative to call this method before moving to another Level, especially if the next level contains another instantiation of QuadUI.
	*/
	public static void OnLevelEnd()
	{
		_instance = null;
	}
	
	#if DEBUG
	void OnGUI()
	{
		Rect rect = new Rect(0,0,250,150);
		GUI.Box(rect,"");
		
		GUILayout.BeginArea(rect);		

		#if UNITY_IPHONE && UNITY_EDITOR		
		
		for(int i = 0; i < _prevElements.Length; i++)
		{
			if(_prevElements[i] != null)
				GUILayout.Label("["+i+"]   " + _prevElements[i].gameObject.name + " | " + _prevElements[i].state);
			else
				GUILayout.Label("["+i+"]   null");
		}
		
		#endif
		GUILayout.Label("resolution: " + Screen.width + "x" + Screen.height);
		GUILayout.EndArea();
	}
	#endif	
	
	
	
	void OnDrawGizmos()
	{
		if(!cam) return;
		
		int screenWidth = 480;
		int screenHeight = 320;
		
		#if UNITY_IPHONE
			switch(resolution)
			{
				case QuadUI.Resolution.iPhonePortrait:
					screenWidth = 320;
					screenHeight = 480;
				break;
				case QuadUI.Resolution.iPhoneLandscape:
					screenWidth = 480;
					screenHeight = 320;
				break;
				case QuadUI.Resolution.iPadPortrait:
					screenWidth = 768;
					screenHeight = 1024;
				break;
				case QuadUI.Resolution.iPadLandscape:
					screenWidth = 1024;
					screenHeight = 768;
				break;
				case QuadUI.Resolution.iPhone4Portrait:
					screenWidth = 640;
					screenHeight = 920;
				break;
				case QuadUI.Resolution.iPhone4Landscape:
					screenWidth = 920;
					screenHeight = 640;
				break;
			}
		#endif
		
		#if UNITY_ANDROID
			switch(resolution)
			{
				case QuadUI.Resolution.HTCLegendTall:
					screenWidth = 320;
					screenHeight = 480;
				break;
				case QuadUI.Resolution.HTCLegendWide:
					screenWidth = 480;
					screenHeight = 320;
				break;
				case QuadUI.Resolution.NexusOneTall:
					screenWidth = 480;
					screenHeight = 800;
				break;
				case QuadUI.Resolution.NexusOneWide:
					screenWidth = 800;
					screenHeight = 480;
				break;
				case QuadUI.Resolution.MotorolaDroidTall:
					screenWidth = 480;
					screenHeight = 854;
				break;
				case QuadUI.Resolution.MotorolaDroidWide:
					screenWidth =854;
					screenHeight = 480;
				break;
				case QuadUI.Resolution.TegraTabletTall:
					screenWidth = 600;
					screenHeight = 1024;
				break;
				case QuadUI.Resolution.TegraTabletWide:
					screenWidth = 1024;
					screenHeight = 600;
				break;
				case QuadUI.Resolution.Custom:
					screenWidth = (int) screenDimensions.x;
					screenHeight = (int) screenDimensions.y;
				break;
			}
		#endif
			
		#if !UNITY_ANDROID && !UNITY_IPHONE
			screenWidth = (int) screenDimensions.x;
			screenHeight = (int) screenDimensions.y;
		#endif

		Vector3 bb = new Vector3(screenWidth,screenHeight,cam.farClipPlane);
		Vector3 loc = cam.transform.position;
		Gizmos.color = new Color(.1F,.1F,.1F,1);
		Gizmos.DrawWireCube(new Vector3(loc.x,loc.y,loc.z+(.5F*cam.farClipPlane)),bb);
	}
}