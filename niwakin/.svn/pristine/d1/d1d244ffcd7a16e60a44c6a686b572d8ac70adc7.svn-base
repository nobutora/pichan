//#define DEBUG
#define QUADUI_1_2
//#define QUADUI_1_3 

using UnityEngine;
using UnityEditor;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System;
using EqualsEquals;

/**
	QuadUI Editor v. 1.2.9 public beta 9/6/2011
	by Joseph Pasek (equalsequals)
	jp@equals-equals.com
	
	www.quadui.com | www.equals-equals.com
	
	Acknowledgements:
	
		I want to give many thanks to Keli Hlodversson (http://keli.dk/) for providing the ObjExporter on the Unify Wiki. 
		I integrated the scripts into this Editor class to provide the functionality to save the generated meshes.  
		Big time saver, you are an awesome programmer.
		
	
	NOTICE:
		 
		This script is still in beta and is without a doubt still quite buggy. 
		For the sake of advancement and improvement of this script I ask of you to please not modify any of the scripts provided with QuadUI if they are not working to your needs, but instead to submit a bug report to me.
		
		You can report bugs/request features here: http://blog.equals-equals.com/quadui/bugs/
				
**/

#region Structs

[System.Serializable]
struct Frame
{
	public int id;
	public bool selected;
	public int width;
	public int height;
	public Vector2 position;
	public string state;
	
	public Frame(int _id, bool _selected, int w, int h, Vector2 pos, string _state)
	{
		id = _id;
		selected = _selected;
		width = w;
		height = h;
		position = pos;
		state = _state;
	}
}

#endregion

public class QuadUIEditor : EditorWindow 
{
	Rect canvas = new Rect(320,65,512,512);
	
	//Window Stuff
	QuadUIEditor _window;
	Material edMat;
	
	GUILayoutOption[] NONE = new GUILayoutOption[0];
	
	#region Enums
	
	public enum RegistrationPoint
	{
		#if QUADUI_1_2
		Center = 0,
		TopLeft = 1,
		BottomLeft = 2,
		TopRight = 3,
		BottomRight = 4
		#elif QUADUI_1_3
		TopLeft,TopMiddle,TopRight,LeftMiddle,Center,RightMiddle,BottomLeft,BottomMiddle,BottomRight
		#endif
	}
	
	public enum QuadUIClass
	{
		None = -1,
		Static = 0,
		Button = 1,
		Toggle = 2,
		Sprite = 3,
		InteractiveSprite = 4
		#if QUADUI_1_3
		,CustomConfiguration = 5
		#endif
	}
	
	public enum QuadUIComplexComponent
	{
		Toolbar = 0,
		Clock = 1,
		Counter = 2,
		SelectionGrid = 3,
		ResizableBar = 4,
		RadioButtons = 5,
		ScrollView = 6
	}
	
	public enum ToolMode
	{
		Draw = 0,
		Move = 1
	}
	
	#endregion
	
	#region Editor Properties
	GUISkin skin;
	//Tool Icons
	Texture _moveIcon;
	Texture _moveIconActive;
	Texture _drawIcon;
	Texture _drawIconActive;
	Texture _alphaIcon;
	Texture _rgbIcon;
	Texture _zoominIcon;
	Texture _zoomoutIcon;
	Texture _snapIcon;
	Texture _snapIconActive;
	#endregion
	
	#region Quad Params
	//Quad Parameters
	string _name = "";
	Material _material;
	int _layer = 0;
	QuadUIClass _class = QuadUIClass.None;
	QuadUIClass _prevClass = QuadUIClass.None;
	RegistrationPoint _reg = RegistrationPoint.TopLeft;
	[SerializeField]
	Frame[] _frames;
	Vector2 widthHeight = new Vector2(0,0);
	Vector2 _uv = new Vector2(0,0);
	#endregion
	
	#region Camera Params
	//Camera Parameters
	string _camName = "2D Camera";
	QuadUI.Resolution _specialRes = (QuadUI.Resolution) 0;
	Vector2 res = new Vector2(480,320);
	int[] _cullingMask = new int[1];
	#endregion
	
	#region Editor Params
	//Editor Parameters
	Color _selectedColor = Color.magenta;
	Color _dragColor = Color.green;
	Color _crosshairColor = Color.cyan;
	Color _gridColor = new Color(1,1,1,.25F);
	bool _showGrid = true;
	int _gridSize = 32;
	string _meshOutputPath = "Assets/Models/UI/Quads/GeneratedQuads";
	string _prefabOutputPath = "Assets/Prefabs/UI";
	#endregion
	
	#region Quad Generation Vars
	//for quad generation
	Vector3[] newVertices;
	Vector2[] newUV;
	int[] newTriangles;
	#endregion
	
	#region Complex Component Vars
	
	#if QUADUI_1_3
	QuadUIComplexComponent complexComponentType = QuadUIComplexComponent.Toolbar;
	#endif	
	
	#endregion
	
	#region User Interaction and UI Vars
	
	int _zoom = 1;
	
	//toolbar
	int currentPage = 0;
	
	//User Interaction
	bool mouseDown = false;
	Vector2 pressPos;
	Vector2 currentPos;
	
	//Snap Parameters
	bool _snapGrid = false;

	//View RGB/A modes
	bool showAlpha = false;
	
	Vector2 snappedPos;
			
	Rect dragRect;
	//bool constrainToSquare = false;
	
	int numFrames;
	int currentFrame = 0;
	
	float scrollX = 0;
	float scrollY = 0;
	Vector2 scrollOffset = Vector2.zero;
	
	float thumbsScrollX = 0;
	
	ToolMode toolMode = ToolMode.Draw;
	
	#endregion
	
	#region Setup
	
	[MenuItem ("GameObject/New QuadUI Environment")]
	static void EnvSetup()
	{
		//our basic environment setup.
		
		GameObject _gObj = new GameObject("Quad UI");
		_gObj.transform.position = Vector3.zero;
		
		_gObj.AddComponent<QuadUI>();
		
		GameObject _gObj2 = new GameObject("2D");
		_gObj2.transform.position = Vector3.zero;
		_gObj2.transform.rotation = Quaternion.Euler(new Vector3(180,0,0));
		_gObj2.transform.parent = _gObj.transform;
		
		//open the editor window just 'cuz you can!
		Init();
	}
	
	[MenuItem ("Window/QuadUI Editor")]
	static void Init() 
	{
		QuadUIEditor window = (QuadUIEditor)EditorWindow.GetWindow (typeof (QuadUIEditor),false,"Quad UI");
		window._window = window;
		
		window.GetAssets(window);
		
		window._frames = new Frame[0];
	}  
	
	void GetAssets(QuadUIEditor window)
	{
		window.edMat = new Material(Shader.Find("Hidden/QuadUI/editor"));
		
		window._snapIcon = (Texture) AssetDatabase.LoadAssetAtPath("Assets/Editor/QuadUI/Editor Assets/icons/tool_icon-snap.png",typeof(Texture));
		window._snapIconActive = (Texture) AssetDatabase.LoadAssetAtPath("Assets/Editor/QuadUI/Editor Assets/icons/tool_icon-snap_active.png",typeof(Texture));
			
		window._drawIcon = (Texture) AssetDatabase.LoadAssetAtPath("Assets/Editor/QuadUI/Editor Assets/icons/tool_icon-marquee.png",typeof(Texture));
		window._drawIconActive = (Texture) AssetDatabase.LoadAssetAtPath("Assets/Editor/QuadUI/Editor Assets/icons/tool_icon-marquee_active.png",typeof(Texture));
			
		window._moveIcon = (Texture) AssetDatabase.LoadAssetAtPath("Assets/Editor/QuadUI/Editor Assets/icons/tool_icon-move.png",typeof(Texture));
		window._moveIconActive = (Texture) AssetDatabase.LoadAssetAtPath("Assets/Editor/QuadUI/Editor Assets/icons/tool_icon-move_active.png",typeof(Texture));
			
		window._rgbIcon = (Texture) AssetDatabase.LoadAssetAtPath("Assets/Editor/QuadUI/Editor Assets/icons/tool_icon-rgb.png",typeof(Texture));
		window._alphaIcon = (Texture) AssetDatabase.LoadAssetAtPath("Assets/Editor/QuadUI/Editor Assets/icons/tool_icon-alpha.png",typeof(Texture));
			
		window._zoominIcon = (Texture) AssetDatabase.LoadAssetAtPath("Assets/Editor/QuadUI/Editor Assets/icons/tool_icon-zoomin.png",typeof(Texture));
		window._zoomoutIcon = (Texture) AssetDatabase.LoadAssetAtPath("Assets/Editor/QuadUI/Editor Assets/icons/tool_icon-zoomout.png",typeof(Texture));
			
		window.skin = (GUISkin) AssetDatabase.LoadAssetAtPath("Assets/Editor/QuadUI/Editor Assets/QuadUI Skin.GUISkin",typeof(GUISkin));
	}
	
	void OnFocus()
	{
		//Debug.Log("focused!");
		if(!_window)
		{
			QuadUIEditor window = (QuadUIEditor)EditorWindow.GetWindow (typeof (QuadUIEditor),false,"Quad UI");
			window._window = window;
			window.GetAssets(window);
			if(_frames == null) window._frames = new Frame[0];
		}
		else
		{
			_window.GetAssets(_window);	
		}
	}
	
	#endregion
	
	#region Meat
	
	void OnGUI () 
	{
		if(!_window)
		{
			QuadUIEditor window = (QuadUIEditor)EditorWindow.GetWindow (typeof (QuadUIEditor),false,"Quad UI");
			window._window = window;
		}
		
		if(EditorApplication.isPlayingOrWillChangePlaymode)
		{
			GUI.Label(new Rect(_window.position.width/2-100,_window.position.height/2-10,200,20),"Tool not available during Play Mode.");
			//_material = null;
			return;
		}
		else if(EditorApplication.isCompiling)
		{
			GUI.Label(new Rect(_window.position.width/2-70,_window.position.height/2-10,200,20),"Compiling. Please wait.");
			//_material = null;
			return;
		}
		
		#if QUADUI_1_3
		canvas = new Rect(canvas.x,65, Mathf.Clamp(_window.position.width - 350,512,Mathf.Infinity), Mathf.Clamp(_window.position.height - 280,512,Mathf.Infinity));
		
		#endif
		
		Rect activeArea = new Rect(canvas.x - 15, canvas.y - 15, canvas.width + 30, canvas.height + 30);
		GUI.Box(activeArea,"");
		
		//our QuadUI.Resolution enum is based on compiler arguements, so this will clamp the length to avoid out of bounds errors when swapping build platforms.
		int __enumLength = 0;
		#if UNITY_IPHONE
		__enumLength = 6;
		#elif UNITY_ANDROID
		__enumLength = 9;
		#endif		
		_specialRes = (QuadUI.Resolution) ((int) Mathf.Clamp((int) _specialRes,0,__enumLength - 1));
				
		GUI.Box(canvas,"");
				
		//let's grab our event
		Event evt = Event.current;
		
		if(evt.type == EventType.MouseDown)
		{
			mouseDown = true;
			
			/*
			if(evt.shift) constrainToSquare = true; 
			else constrainToSquare = false;
			*/
			
			if(_snapGrid && _showGrid)
			{	
				currentPos = pressPos = snappedPos;
			}
			else
			{
				currentPos = pressPos = evt.mousePosition;
			}
		}
		else if(evt.type == EventType.MouseUp && mouseDown == true)
		{
			mouseDown = false;
		}
		else if(evt.type == EventType.MouseDrag)
		{
			currentPos = evt.mousePosition;
			
			
			if(_snapGrid && _showGrid)
			{
				currentPos = snappedPos;
			}
			else
			{
				currentPos = evt.mousePosition;
			}
			
			/*
			
			if(evt.shift) constrainToSquare = true; 
			else constrainToSquare = false;
			
			*/
		}
		
		//Debug.Log("global: " + evt.mousePosition + ", local: " + ConvertToCanvasSpace(evt.mousePosition) + ", back to global: " + ConvertToScreenSpace(ConvertToCanvasSpace(evt.mousePosition)));
		
		DrawLeftNavigation();
		
		//Debug.Log(constrainToSquare);
		
		if(_material)
		{
			Texture img = _material.GetTexture("_MainTex");
			if(edMat == null) edMat = new Material(Shader.Find("Hidden/QuadUI/editor"));
			
			//setup basic GL stuff here
			
			if(img)
			{								
				if(img.width > 2048 || img.height > 2048)
				{
					if(EditorUtility.DisplayDialog("Oversized Texture!", "The Texture you are using exceeds the maximum size (2048px) in at least one dimension. Textures can be no larger than 2048px.","OK"))
					{
						_material = null;
					}
				}
				
				GUILayout.BeginArea(canvas);
				
				//Rect imgRect = new Rect(320,20,img.width,img.height);
				Rect imgRect = new Rect(-scrollX,-scrollY,img.width*_zoom,img.height*_zoom);//new Rect(0,0,img.width,img.height);
				
				//draw sprite sheet texture
				if(showAlpha)
					 EditorGUI.DrawTextureAlpha(imgRect,img);
				else
					EditorGUI.DrawPreviewTexture(imgRect,img,null);
				
				GUILayout.EndArea();
				
				GUILayout.BeginArea(new Rect(canvas.x-15,canvas.y+canvas.height+15,canvas.width+30,15));
				if(_zoom * img.width > canvas.width) scrollX = GUILayout.HorizontalScrollbar(scrollX,0,0,(img.width*_zoom) - (img.width / (img.width/canvas.width)),NONE);//skin.GetStyle("horizontalscrollbar"));
				GUILayout.EndArea();
		
				GUILayout.BeginArea(new Rect(canvas.x+canvas.width+15,canvas.y-15,15,canvas.height+30));
				if(_zoom * img.height > canvas.height) scrollY = GUILayout.VerticalScrollbar(scrollY,0,0,(img.height*_zoom) - (img.height / (img.height/canvas.height)),new GUILayoutOption[1]{GUILayout.Height(canvas.height+30)});//skin.GetStyle("verticalscrollbar"));
				GUILayout.EndArea();
				
				imgRect = new Rect(canvas.x,canvas.y,img.width,img.height);
				if(_zoom == 1 && (img.width <= canvas.width && img.height <= canvas.height)) scrollX = scrollY = 0;
				scrollOffset = new Vector2(-scrollX,-scrollY);
				
				if(_showGrid)
				{
					GL.PushMatrix();
					edMat.SetPass(0);
					GL.LoadPixelMatrix();
					GL.Begin(GL.LINES);
					GL.Color(_gridColor);
					
					for(int i = 1; i < canvas.width / _gridSize; i++)
					{
						
						int _lineX = (int) (canvas.x + scrollOffset.x) + (_gridSize * _zoom) * i;
						int _lineY = (int) (canvas.y + scrollOffset.y) + (_gridSize * _zoom) * i;
						
						//vertical
						
						if(_lineX <= canvas.x + canvas.width && _lineX >= canvas.x)
						{
							//top
							GL.Vertex(new Vector3(_lineX, canvas.y, 0)); //(canvas.y + scrollOffset.y)
							//bottom
							GL.Vertex(new Vector3(_lineX, canvas.y + canvas.height, 0)); //(canvas.y + scrollOffset.y) + (imgRect.height * _zoom)
						}
						
						//horizontal
						
						if(_lineY <= canvas.y + canvas.height && _lineY >= canvas.y)
						{
							//left
							GL.Vertex(new Vector3(canvas.x, _lineY, 0)); //(canvas.x + scrollOffset.x)
							//right
							GL.Vertex(new Vector3(canvas.x + canvas.width, _lineY, 0)); //(canvas.x + scrollOffset.x) + (imgRect.width * _zoom)
						}
					}
					
					GL.End();
					GL.PopMatrix();
				}
				
				//draw selection outline
				GL.PushMatrix();
				edMat.SetPass(0);
				GL.LoadPixelMatrix();
				GL.Begin(GL.LINES);
				
				Vector2 tl = ConvertUVToScreenSpace();
				Vector2 br = new Vector2(widthHeight.x * _zoom, widthHeight.y * _zoom);
				
				GL.Color(_selectedColor);
				
				//topLine
				if(tl.y >= canvas.y && tl.y <= canvas.y + canvas.height)
				{
					GL.Vertex(new Vector3(Mathf.Clamp(tl.x,canvas.x,canvas.x+canvas.width), tl.y, 0));
					GL.Vertex(new Vector3(Mathf.Clamp(tl.x + br.x,canvas.x,canvas.x+canvas.width), tl.y, 0));
				}
				
				//left line
				if(tl.x >= canvas.x && tl.x <= canvas.x + canvas.width)
				{
					GL.Vertex(new Vector3(tl.x, Mathf.Clamp(tl.y,canvas.y,canvas.y+canvas.height), 0));
					GL.Vertex(new Vector3(tl.x, Mathf.Clamp(tl.y + br.y,canvas.y,canvas.y+canvas.height), 0));
				}
				
				//right line
				if(tl.x + br.x >= canvas.x && tl.x + br.x <= canvas.x + canvas.width)
				{				
					GL.Vertex(new Vector3(tl.x + br.x, Mathf.Clamp(tl.y,canvas.y,canvas.y+canvas.height), 0));
					GL.Vertex(new Vector3(tl.x + br.x, Mathf.Clamp(tl.y + br.y,canvas.y,canvas.y+canvas.height), 0));
				}
				
				//bottom line
				if(tl.y + br.y >= canvas.y && tl.y + br.y <= canvas.y + canvas.height)
				{				
					GL.Vertex(new Vector3(Mathf.Clamp(tl.x,canvas.x,canvas.x+canvas.width), tl.y + br.y, 0));
					GL.Vertex(new Vector3(Mathf.Clamp(tl.x + br.x,canvas.x,canvas.x+canvas.width), tl.y + br.y, 0));
				}
				
				GL.End();
				GL.PopMatrix();
				

				//Handle User Interaction Here!!!
				
				if(mouseDown)
				{
					
					if(activeArea.Contains(pressPos))//(pressPos.x > canvas.x - 10 && pressPos.x < canvas.x+canvas.width + 13) && pressPos.y < canvas.y + canvas.height + 8)
					{
						if(toolMode == ToolMode.Draw)
						{												
							dragRect = new Rect(pressPos.x,pressPos.y,currentPos.x-pressPos.x,currentPos.y-pressPos.y);
							//dragBox = ConvertQuadToCanvasSpace(dragRect);
						}
						else if(toolMode == ToolMode.Move)
						{
							dragRect = new Rect(currentPos.x,currentPos.y,widthHeight.x*_zoom,widthHeight.y*_zoom);
						}
					
						GL.PushMatrix();
						edMat.SetPass(0);
						GL.LoadPixelMatrix();
						GL.Begin(GL.LINES);
						
						DrawQuadBox(dragRect,_dragColor);

						GL.End();
						GL.PopMatrix();	
					}
				}
				else
				{
					if(evt.type == EventType.MouseUp)
					{
						if(activeArea.Contains(evt.mousePosition))//(evt.mousePosition.x > canvas.x - 20 && evt.mousePosition.x < canvas.x+canvas.width + 13) && evt.mousePosition.y < canvas.y + canvas.height + 18)
						{
							Rect r = new Rect(0,0,0,0);
							
							if(toolMode == ToolMode.Draw)
							{																								
								//During our clicking and dragging we were working in global space, but we need to change that information to localized units so the UV maps are correct
								
								Vector2 localizedCurrentPos = ConvertToCanvasSpace(currentPos);
								Vector2 localizedPressPos = ConvertToCanvasSpace(pressPos);
								
								//Find the delta of these 2 localized vectors and we will then know what direction the user was dragging in
								
								float dX = localizedCurrentPos.x - localizedPressPos.x;
								float dY = localizedCurrentPos.y - localizedPressPos.y;
								
								//We also want to clamp our 2 localized vectors so that they are no larger than the image max size and dont start in negatives.
								
								localizedPressPos = new Vector2(Mathf.Clamp(localizedPressPos.x,0,img.width),Mathf.Clamp(localizedPressPos.y,0,img.height));
								localizedCurrentPos = new Vector2(Mathf.Clamp(localizedCurrentPos.x,0,img.width),Mathf.Clamp(localizedCurrentPos.y,0,img.height));
								
								//check our drag delta to find what direction we're moving in
																								
								if(dX > 0 && dY > 0)
								{
									//started TL
									r = new Rect(localizedPressPos.x,localizedPressPos.y,localizedCurrentPos.x-localizedPressPos.x,localizedCurrentPos.y-localizedPressPos.y);
								}

								else if(dX > 0 && dY < 0)
								{
									//started BL
									r = new Rect(localizedPressPos.x,localizedCurrentPos.y,localizedCurrentPos.x-localizedPressPos.x,localizedPressPos.y - localizedCurrentPos.y);
								}
								else if(dX < 0 && dY < 0)
								{
									//started BR
									r = new Rect(localizedCurrentPos.x,localizedCurrentPos.y,localizedPressPos.x - localizedCurrentPos.x,localizedPressPos.y - localizedCurrentPos.y);
								}
								else if(dX < 0 && dY > 0)
								{
									//started TR
									r = new Rect(localizedCurrentPos.x,localizedPressPos.y,localizedPressPos.x - localizedCurrentPos.x,localizedCurrentPos.y - localizedPressPos.y);
								}
								
								/*
								if(constrainToSquare)
								{
									r = new Rect(r.x,r.y,r.height,r.height);
								}
								*/
							}
							else if(toolMode == ToolMode.Move)
							{
								Vector2 localizedCurrentPos = ConvertToCanvasSpace(currentPos);
								r = new Rect(localizedCurrentPos.x,localizedCurrentPos.y,widthHeight.x,widthHeight.y);
								
								if(localizedCurrentPos.x + widthHeight.x > img.width || localizedCurrentPos.y+widthHeight.y > img.height || localizedCurrentPos.x < 0 || localizedCurrentPos.y < 0)
								{
									if(!EditorUtility.DisplayDialog("Out of Bounds!", "The Rectangle you are assigning has some of it's area outside of the bounds of your Texture. Depending on your Texture's wrap mode, this can produce some unexpected results.","OK","Cancel"))
									{
										r = new Rect(_uv.x,_uv.y,widthHeight.x,widthHeight.y);
									}
								}								
								
								//if(_frames.Length > 0)_frames[currentFrame].position = new Vector2(Mathf.Clamp(r.x-320,0,img.width),Mathf.Clamp(r.y-20,0,img.height));
							}
							
							_uv = new Vector2(r.x,r.y);
							widthHeight = new Vector2(r.width,r.height);
							
							if(_frames.Length > 0)_frames[currentFrame].position = _uv;							
						}
						
					}
				}
				
				Rect _infoRect = new Rect(0,canvas.y+canvas.height+20,300,185);
				GUI.Box(_infoRect,"Info:");
				
				//mouse position considering we're within the texture
				if(activeArea.Contains(evt.mousePosition))//new Rect(canvas.x-20,canvas.y-20,(canvas.x-20)+(canvas.width+28),(canvas.y-20)+(canvas.height+28)).Contains(evt.mousePosition))
				{
					Vector2 _lS = ConvertToCanvasSpace(evt.mousePosition);
					
					
					GUILayout.BeginArea(_infoRect);
					GUILayout.Space(20);
					GUILayout.Label("   Mouse: x: " + _lS.x + " y: " + _lS.y);
					GUILayout.EndArea();
					
					if(_snapGrid && _showGrid)
					{
						
						int _x = (int) evt.mousePosition.x - (int) (canvas.x + scrollOffset.x);
						int _y = (int) evt.mousePosition.y - (int) (canvas.y + scrollOffset.y);
						
						float uX = _x / (_gridSize * _zoom);
						float uY = _y / (_gridSize * _zoom);
						
						_x = (int) Mathf.Round(uX) * (_gridSize * _zoom) + (int) (canvas.x + scrollOffset.x);
						_y = (int) Mathf.Round(uY) * (_gridSize * _zoom) + (int) (canvas.y + scrollOffset.y);
						
						snappedPos = new Vector2(_x,_y);
						
						GL.PushMatrix();
						edMat.SetPass(0);
						GL.LoadPixelMatrix();
						GL.Begin(GL.LINES);
						GL.Color(_crosshairColor);
						
						//vertical
						if((_x >= canvas.x && _x <= canvas.x+canvas.width) && (_y >= canvas.y && _y <= canvas.y+canvas.height))
						{
							GL.Vertex(new Vector3(_x,_y-10,0));
							GL.Vertex(new Vector3(_x,_y+10,0));
						}
						//horizontal
						if((_x >= canvas.x && _x <= canvas.x+canvas.width) && (_y >= canvas.y && _y <= canvas.y+canvas.height))
						{
							GL.Vertex(new Vector3(_x-10,_y,0));
							GL.Vertex(new Vector3(_x+10,_y,0));
						}
						
						GL.End();
						GL.PopMatrix();
					}
				}
			}
			
			#if DEBUG
			
				GUI.Box(new Rect(10,_window.position.height-120,130,80),"");
				GUILayout.BeginArea(new Rect(15,_window.position.height-120,150,100));
				GUILayout.Label("DEBUG:", EditorStyles.boldLabel);
				
				//GUILayout.Label("uv: " + ConvertUVToScreenSpace()); 
				
				GUILayout.Label("scrollX: " + scrollX);
				GUILayout.Label("scrollY: " + scrollY);
				
				//GUILayout.Label("Snap (G): " + snappedPos);
				//GUILayout.Label("Snap (L): " + ConvertToCanvasSpace(snappedPos));
				
				//GUILayout.Label("Pressed: " + ConvertToCanvasSpace(pressPos));
				//GUILayout.Label("Current: " + ConvertToCanvasSpace(currentPos));
				GUILayout.Label("Width: " + Mathf.Abs(ConvertToCanvasSpace(currentPos).x - ConvertToCanvasSpace(pressPos).x));
				GUILayout.Label("Height: " + Mathf.Abs(ConvertToCanvasSpace(currentPos).y - ConvertToCanvasSpace(pressPos).y));
				
				//GUILayout.Label("Tool: " + toolMode);
				//GUILayout.Label("Current Frame: " + currentFrame);
				//if(_frames != null) GUILayout.Label("Frames: " + _frames.Length);
				
				//else GUILayout.Label("Frames: null");
				
				GUILayout.EndArea();
			
			#endif
		}
		
		/* Thumbs */
		DrawThumbsArea();
		/* Tools */
		DrawRightToolbar();
		
		Repaint();
	}	
	
	#endregion
	
	#region Space Conversion Utils
	
	Vector3 ConvertSSToGL(Vector2 pos)
	{
		//The point here is to get GL to draw stuff correctly because the coordinates of y are inverted.
		
		Vector3 v3 = new Vector3(pos.x,(float) _window.position.height-pos.y,0);
		return v3;
		
	}
	
	Vector2 ConvertUVToScreenSpace()
	{
		Vector2 v2;
		if(_frames.Length > 1)
		{
			v2 = new Vector2((_frames[currentFrame].position.x * _zoom) + scrollOffset.x + canvas.x, (_frames[currentFrame].position.y * _zoom) + scrollOffset.y + canvas.y);
		}
		else
		{
			v2 = new Vector2((_uv.x * _zoom) + scrollOffset.x + canvas.x, (_uv.y * _zoom) + scrollOffset.y + canvas.y);
		}
		
		return v2;
	}
	
	Vector2 ConvertToCanvasSpace(Vector2 coords)
	{
		//our coordinate - the canvas offset, - the offset caused by our scroll bars / zoom
		int _localX = ((int) (coords.x - canvas.x) - (int) scrollOffset.x) / _zoom;
		int _localY = ((int) (coords.y - canvas.y) - (int) scrollOffset.y) / _zoom;
		
		return new Vector2(_localX, _localY);
	}
	
	Vector2 ConvertToScreenSpace(Vector2 coords)
	{
		int _globalX = ((int) (coords.x + canvas.x) + (int) scrollOffset.x) * _zoom;
		int _globalY = ((int) (coords.y + canvas.y) + (int) scrollOffset.y) * _zoom;
		
		return new Vector2(_globalX, _globalY);
	}
	
	Vector2[] ConvertQuadToCanvasSpace(Rect r)
	{
		Vector2[] verts = new Vector2[4];
		
		verts[0] = ConvertToCanvasSpace(new Vector2(r.x, r.y));
		verts[1] = ConvertToCanvasSpace(new Vector2(r.x + r.width, r.y));
		verts[2] = ConvertToCanvasSpace(new Vector2(r.x, r.y + r.height));
		verts[3] = ConvertToCanvasSpace(new Vector2(r.x + r.width, r.y + r.height));
		
		return verts;
	}
	
	#endregion
	
	#region General Utils
	void DrawQuadBox(Rect r,Color c)
	{		
		GL.Color(c);
		//top line				
		GL.Vertex(new Vector3(r.x,r.y,0));
		GL.Vertex(new Vector3(r.x+r.width,r.y,0));
		//left line				
		GL.Vertex(new Vector3(r.x,r.y,0));
		GL.Vertex(new Vector3(r.x,r.y+r.height,0));
		//right line				
		GL.Vertex(new Vector3(r.x+r.width,r.y,0));
		GL.Vertex(new Vector3(r.x+r.width,r.y+r.height,0));
		//bottom line				
		GL.Vertex(new Vector3(r.x,r.y+r.height,0));
		GL.Vertex(new Vector3(r.x+r.width,r.y+r.height,0));
	}
	
	void FixFrames()
	{
		if(_frames == null) _frames = new Frame[0];
		if(numFrames > 0 && currentFrame < 0) currentFrame = 0;
		
		if(_frames.Length != numFrames) 
		{
			Frame[] _tmp = new Frame[_frames.Length];
			
			for(int i = 0; i < _tmp.Length; i++)
			{
				_tmp[i] = _frames[i];
			}
			
			_frames = new Frame[numFrames];
			
			if(_tmp.Length < numFrames)
			{
				for(int j = 0; j < _tmp.Length; j++)
				{
					_frames[j] = _tmp[j];
				}
			}
			else
			{
				for(int j = 0; j < _frames.Length; j++)
				{
					_frames[j] = _tmp[j];
				}
			}
		}
		
		if(currentFrame >= numFrames) currentFrame = numFrames-1;
	}
	
	Rect DetermineRect(Vector3 v1,Vector3 v2,Vector3 v3,Vector3 v4,float w,float h)
	{
		//with these 4 given verts, we need to order them so that they go lt,tr,bl,br
		
		//lets grab our top left. we'll do this by finding the point with the closest proximity to our local 0,0
		float v1dist = Vector3.Distance(v1,new Vector3(320,20,0));
		float v2dist = Vector3.Distance(v2,new Vector3(320,20,0));
		float v3dist = Vector3.Distance(v3,new Vector3(320,20,0));
		float v4dist = Vector3.Distance(v4,new Vector3(320,20,0));
						
		Vector3[] vs = new Vector3[4]{v1,v2,v3,v4};
		float[] dists = new float[4]{v1dist,v2dist,v3dist,v4dist};
		Vector3 topLeft = v1;
		float topLeftDist = v1dist;
		
		for(int i = 1; i < dists.Length; i++)
		{
			if(dists[i] < topLeftDist)
			{
				topLeftDist = dists[i];
				topLeft = vs[i];
			}
		}
		
		//now that we have our top left, we can find top right
		Vector3 topRight = new Vector3(topLeft.x+w,topLeft.y,0);
		Vector3 bottomRight= new Vector3(topLeft.x+w,topLeft.y+h,0);
		
		return new Rect(topLeft.x,topLeft.y,topRight.x-topLeft.x,bottomRight.y-topRight.y);
	}
	
	#endregion
	
	#region Interface Navigation
	
	void DrawLeftNavigation()
	{
		GUI.Box(new Rect(0,50,300,canvas.height+30),"");
		
		GUILayoutOption[] leftOptions = new GUILayoutOption[3];
		leftOptions[0] = GUILayout.MinWidth(300);
		leftOptions[1] = GUILayout.MaxWidth(300);
		leftOptions[2] = GUILayout.MinHeight(480);
		GUILayout.BeginArea(new Rect(0,0,300,480));
		EditorGUILayout.BeginVertical(leftOptions);
		
		
		string[] titles;
		#if QUADUI_1_2
		
		GUILayout.Space(20);
		titles = new string[3]{"Quad","Camera","Settings"};
		
		#elif QUADUI_1_3
		
		titles = new string[4]{"Quad","Complex","Camera","Settings"};
		
		#endif
		
		
		currentPage = GUILayout.Toolbar(currentPage, titles);
		GUILayout.Space(10);
		
		switch(currentPage)
		{
			case 0:
				GUILayout.Label ("Create a New Quad:", EditorStyles.boldLabel);
				GUILayout.Space(20);
				_material = (Material) EditorGUILayout.ObjectField (_material, typeof(Material));
				GUILayout.Space(10);
				_name = EditorGUILayout.TextField ("Name:", _name);
				GUILayout.Space(10);
				
				#if QUADUI_1_2
				_reg = (RegistrationPoint) EditorGUILayout.EnumPopup ("Pivot Point:", _reg);
				#elif QUADUI_1_3
				EditorGUILayout.PrefixLabel("Quad Pivot Point:");
				GUILayout.BeginHorizontal(new GUILayoutOption[1]{GUILayout.Width(200)});
				GUILayout.Space(100);
				_reg = (RegistrationPoint) GUILayout.SelectionGrid((int)_reg,new string[9]{"","","","","","","","",""},3,new GUILayoutOption[1]{GUILayout.Width(100)});
				GUILayout.EndHorizontal();
				GUILayout.Space(20);
				#endif
			
				_layer = EditorGUILayout.LayerField("Layer:", _layer);
				GUILayout.Space(20);
				_prevClass = _class;
				_class = (QuadUIClass) EditorGUILayout.EnumPopup ("Class:", _class);

				GUILayout.Space(10);
			
				switch(_class)
				{
					case QuadUIClass.None: //We don't want to be part of the framework, just generate a quad.
					currentFrame = -1;
					numFrames = 0;
					FixFrames();
					_uv = EditorGUILayout.Vector2Field("UV Top-Left:", _uv);
					widthHeight = EditorGUILayout.Vector2Field("Quad Width and Height", widthHeight);
					break;
					
					case QuadUIClass.Static:
					numFrames = 0;
					currentFrame = -1;
					FixFrames();
					_uv = EditorGUILayout.Vector2Field("UV Top-Left:", _uv);
					widthHeight = EditorGUILayout.Vector2Field("Quad Width and Height", widthHeight);
					break;
					
					case QuadUIClass.Button:
					
					numFrames = 3;
					FixFrames();			
					if(_prevClass != _class) _frames[0].position = _uv;
					_frames[0] = new Frame(0, _frames[0].selected, (int) widthHeight.x, (int) widthHeight.y, _frames[0].position, "Up State");
					_frames[1] = new Frame(1, _frames[1].selected, (int) widthHeight.x, (int) widthHeight.y, _frames[1].position, "Down State");
					_frames[2] = new Frame(2, _frames[2].selected, (int) widthHeight.x, (int) widthHeight.y, _frames[2].position, "Over State");
					
					_frames[currentFrame].position = EditorGUILayout.Vector2Field(_frames[currentFrame].state + "'s UV Top-Left:", _frames[currentFrame].position);
					widthHeight = EditorGUILayout.Vector2Field("Quad Width and Height:", widthHeight);
					
					break;
					
					case QuadUIClass.Toggle:
					
					numFrames = 4;
					FixFrames();			
					if(_prevClass != _class) _frames[0].position = _uv;
					_frames[0] = new Frame(0, _frames[0].selected, (int) widthHeight.x, (int) widthHeight.y, _frames[0].position, "Off State");
					_frames[1] = new Frame(1, _frames[1].selected, (int) widthHeight.x, (int) widthHeight.y, _frames[1].position, "Off Hover State");
					_frames[2] = new Frame(2, _frames[2].selected, (int) widthHeight.x, (int) widthHeight.y, _frames[2].position, "On State");
					_frames[3] = new Frame(3, _frames[3].selected, (int) widthHeight.x, (int) widthHeight.y, _frames[3].position, "On Hover State");
					
					_frames[currentFrame].position = EditorGUILayout.Vector2Field(_frames[currentFrame].state + "'s UV Top-Left:", _frames[currentFrame].position);
					widthHeight = EditorGUILayout.Vector2Field("Quad Width and Height:", widthHeight);
					
					break;
					
					case QuadUIClass.Sprite:
					numFrames = Mathf.Clamp(EditorGUILayout.IntField("# Frames:",numFrames),2,1000);
					FixFrames();
					if(_prevClass != _class) _frames[0].position = _uv;
					for(int i = 0; i < numFrames; i++)
					{
						_frames[i] = new Frame(i, _frames[i].selected, (int) widthHeight.x, (int) widthHeight.y, _frames[i].position, "Frame "+(i+1));
					}
					
					_frames[currentFrame].position = EditorGUILayout.Vector2Field(_frames[currentFrame].state + "'s UV Top-Left:", _frames[currentFrame].position);
					GUILayout.Space(20);
					widthHeight = EditorGUILayout.Vector2Field("Quad Width and Height:", widthHeight);
					
					break;
					
					case QuadUIClass.InteractiveSprite:
					numFrames = Mathf.Clamp(EditorGUILayout.IntField("# Frames:",numFrames),2,1000);
					FixFrames();
					if(_prevClass != _class) _frames[0].position = _uv;
					for(int i = 0; i < numFrames; i++)
					{
						_frames[i] = new Frame(i, _frames[i].selected, (int) widthHeight.x, (int) widthHeight.y, _frames[i].position, "Frame "+(i+1));
					}
					
					_frames[currentFrame].position = EditorGUILayout.Vector2Field(_frames[currentFrame].state + "'s UV Top-Left:", _frames[currentFrame].position);
					GUILayout.Space(20);
					widthHeight = EditorGUILayout.Vector2Field("Quad Width and Height:", widthHeight);
					break;
				}
								
				GUILayout.Space(20);
				
			
				if(GUILayout.Button("Bake!")) BakeQuad();
			
				//GUILayout.Space(40);
				//_zoom = EditorGUILayout.IntSlider(_zoom,1,4);	
			
			break;
			
			#if QUADUI_1_3
			
			case 1:
			
			//GUILayout.Label ("Complex Component Definition", EditorStyles.boldLabel);
			complexComponentType = (QuadUIComplexComponent) EditorGUILayout.EnumPopup("Class:", complexComponentType);
			
			break;
			
			case 2:
			
			#elif QUADUI_1_2
			case 1:
			#endif
			
			GUILayout.Label ("Define Screen Space", EditorStyles.boldLabel);
			_camName = EditorGUILayout.TextField ("Name", _camName);
			
			
			
			#if UNITY_IPHONE
			_specialRes = (QuadUI.Resolution) EditorGUILayout.EnumPopup("Resolution: ", _specialRes, new GUILayoutOption[0]{});
			
			switch(_specialRes)
			{
				case QuadUI.Resolution.iPadLandscape:
				res = new Vector2(1024,768);
				break;
				
				case QuadUI.Resolution.iPadPortrait:
				res = new Vector2(768,1024);
				break;
				
				case QuadUI.Resolution.iPhone4Landscape:
				res = new Vector2(960,640);
				break;
				
				case QuadUI.Resolution.iPhone4Portrait:
				res = new Vector2(640,960);
				break;
				
				case QuadUI.Resolution.iPhoneLandscape:
				res = new Vector2(480,320);
				break;
				
				case QuadUI.Resolution.iPhonePortrait:
				res = new Vector2(320,480);
				break;
			}
			
			#elif UNITY_ANDROID
			_specialRes = (QuadUI.Resolution) EditorGUILayout.EnumPopup("Resolution: ", _specialRes, new GUILayoutOption[0]{});
			
			switch(_specialRes)
			{
				case QuadUI.Resolution.HTCLegendTall:
					res = new Vector2(320,480);
				break;
				case QuadUI.Resolution.HTCLegendWide:
					res = new Vector2(480,320);
				break;
				case QuadUI.Resolution.NexusOneTall:
					res = new Vector2(480,800);
				break;
				case QuadUI.Resolution.NexusOneWide:
					res = new Vector2(800,480);
				break;
				case QuadUI.Resolution.MotorolaDroidTall:
					res = new Vector2(480,854);
				break;
				case QuadUI.Resolution.MotorolaDroidWide:
					res = new Vector2(854,480);
				break;
				case QuadUI.Resolution.TegraTabletTall:
					res = new Vector2(600,1024);
				break;
				case QuadUI.Resolution.TegraTabletWide:
					res = new Vector2(1024,600);
				break;
				case QuadUI.Resolution.Custom:
					res = EditorGUILayout.Vector2Field("Screen Size", res);
				break;
			}
			
			#else
			res = EditorGUILayout.Vector2Field("Screen Size", res);
			#endif
			
			_cullingMask[0] = EditorGUILayout.LayerField("Culling Mask ",_cullingMask[0]);
			GUILayout.Space(20);
			if(GUILayout.Button("Generate 2D Camera")) GenerateCamera();
			
			break;
			
			#if QUADUI_1_3
			case 3:
			#elif QUADUI_1_2
			case 2:
			#endif
			GUILayout.Label ("Tool Settings", EditorStyles.boldLabel);
			_selectedColor = EditorGUILayout.ColorField("Selected", _selectedColor);
			_dragColor = EditorGUILayout.ColorField("Drag", _dragColor);
			_gridColor = EditorGUILayout.ColorField("Grid", _gridColor);
			_crosshairColor = EditorGUILayout.ColorField("Crosshairs", _crosshairColor);
			
			_showGrid = EditorGUILayout.BeginToggleGroup("Show Grid",_showGrid);
			_gridSize = Mathf.Max(EditorGUILayout.IntField("Grid Size", _gridSize),2);
			EditorGUILayout.EndToggleGroup();
			_meshOutputPath = EditorGUILayout.TextField("Mesh Path", _meshOutputPath);
			_prefabOutputPath = EditorGUILayout.TextField("Prefab Path", _prefabOutputPath);
			
			break;
		}	
		
		EditorGUILayout.EndVertical();
		GUILayout.EndArea();
	}
	
	void DrawThumbsArea()
	{
		if(_frames.Length == 0) return;
		
		Rect _thumbsAreaLabel = new Rect(canvas.x-15, canvas.y + canvas.height + 30, Mathf.Clamp(canvas.width+30, 512, Mathf.Infinity), 20);
		Rect _thumbsAreaRect = new Rect(canvas.x-15, canvas.y + canvas.height + 50, Mathf.Clamp(canvas.width+30, 512, Mathf.Infinity), 155);
		GUI.Label(_thumbsAreaLabel, "Frames:", EditorStyles.largeLabel);		
		GUI.Box(new Rect(_thumbsAreaRect.x,_thumbsAreaRect.y,_thumbsAreaRect.width,_thumbsAreaRect.height-15), "");
		
		/* Thumbs */
		if(_frames == null) _frames = new Frame[0];
		
		if(_frames.Length > 4)
		{
		
			GUILayout.BeginArea(_thumbsAreaRect);//new Rect(320,715,512,15));
			GUILayout.Space(140);
			thumbsScrollX = GUILayout.HorizontalScrollbar(thumbsScrollX,10,0,(numFrames * 130)-(canvas.width+30));
			GUILayout.EndArea();
		}
		
		GUILayout.BeginArea(_thumbsAreaRect);//new Rect(320,565,512,140)); //this is our panel
		
		if(_frames.Length > 0)
		{
			for(int i = 0; i < _frames.Length; i++)
			{
				//bool _sel = false;
				//if(i == currentFrame) _sel = true;
				
				GUILayout.BeginArea(new Rect((i*130)-thumbsScrollX,0,120,140)); //this is our bubble.
			
				GUIContent _gc = new GUIContent();
				
				_gc.text = _frames[i].state;
				_gc.tooltip = "Thumb Nail";
				
				if(currentFrame == i) GUI.Box(new Rect(0,0,120,140),_gc,skin.GetStyle("Box"));
				else GUI.Box(new Rect(0,0,120,140),_gc);
				
			
				float _thumbScale;
				if(widthHeight.x >= widthHeight.y) _thumbScale = Mathf.Min(100/widthHeight.x, 1F);
				else _thumbScale = Mathf.Min(100/widthHeight.y, 1F);
			 
				GUILayout.BeginArea(new Rect(10 + (50-(widthHeight.x*_thumbScale)/2),20 + (50-(widthHeight.y*_thumbScale)/2),widthHeight.x * _thumbScale, widthHeight.y * _thumbScale)); //this crops our texture
			
				if(_material)
				{
					if(showAlpha) EditorGUI.DrawTextureAlpha(new Rect(-_frames[i].position.x * _thumbScale, -_frames[i].position.y * _thumbScale, _material.mainTexture.width * _thumbScale, _material.mainTexture.height * _thumbScale) ,_material.mainTexture);
					else EditorGUI.DrawPreviewTexture(new Rect(-_frames[i].position.x * _thumbScale, -_frames[i].position.y * _thumbScale, _material.mainTexture.width * _thumbScale, _material.mainTexture.height * _thumbScale) ,_material.mainTexture, null);
				}
			
				GUILayout.EndArea();
			
				//GUI.Label(new Rect(5,120,110,20), "Up");
				
				bool _on = false; 
				if(currentFrame == i) _on = true;
				
				if(GUI.Toggle(new Rect(0,0,120,140), _on,"", GUIStyle.none))
				//if(GUI.Toggle(new Rect (50, 120, 15, 15), _on,"", EditorStyles.radioButton))
				{
					_frames[currentFrame].selected = false;
					_frames[i].selected = true;
					currentFrame = i;
				}
				
				GUI.Toggle(new Rect (50, 120, 15, 15), _on,"", EditorStyles.radioButton);
			
				GUILayout.EndArea();
			}
		}
		
		GUILayout.EndArea();
	}
	
	void DrawRightToolbar()
	{
		#if QUADUI_1_2
		GUILayout.BeginArea(new Rect(canvas.x + canvas.width + 40,canvas.y - 15,52,250));
			
			GUI.Box(new Rect(0,0,52,250),"");
			
			GUIContent _toolGC = new GUIContent();
			
			//Draw Rectangle Tool
			_toolGC.tooltip = "Draw Rectangle";
			_toolGC.text = "";
			if(toolMode == ToolMode.Draw) _toolGC.image = _drawIconActive;
			else _toolGC.image = _drawIcon;
				
			if(GUI.Button(new Rect(10,10,32,32), _toolGC)) toolMode = ToolMode.Draw;
			
			//Move Rectangle
			_toolGC.tooltip = "Move Rectangle";
			_toolGC.text = "";
			if(toolMode == ToolMode.Move) _toolGC.image = _moveIconActive;
			else _toolGC.image = _moveIcon;
			
			if(GUI.Button(new Rect(10,50,32,32), _toolGC)) toolMode = ToolMode.Move;
			
			//Zoom in
			_toolGC.tooltip = "Zoom In";
			_toolGC.text = "";
			_toolGC.image = _zoominIcon;
			if(GUI.Button(new Rect(10,90,32,32), _toolGC)) if(_material) _zoom = Mathf.Clamp(_zoom + 1, 1, 4);
			
			//Zoom out
			_toolGC.tooltip = "Zoom Out";
			_toolGC.text = "";
			_toolGC.image = _zoomoutIcon;
			if(GUI.Button(new Rect(10,130,32,32), _toolGC)) if(_material) _zoom = Mathf.Clamp(_zoom - 1, 1, 4);
			
			// RGB/A Mode
			if(showAlpha)
			{
				_toolGC.tooltip = "Switch to RGB Mode";
				_toolGC.text = "";
				_toolGC.image = _rgbIcon;
			}
			else
			{
				_toolGC.tooltip = "Switch to Alpha Mode";
				_toolGC.text = "";
				_toolGC.image = _alphaIcon;
			}
			if(GUI.Button(new Rect(10,170,32,32), _toolGC)) showAlpha = !showAlpha;
			
			//Snaps Toggle
			_toolGC.tooltip = "Toggle Grid Snapping";
			_toolGC.text = "";
			if(_snapGrid) _toolGC.image = _snapIconActive;
			else _toolGC.image = _snapIcon;
				
			if(GUI.Button(new Rect(10,210,32,32), _toolGC)) _snapGrid = !_snapGrid;
			
		
		#elif QUADUI_1_3
		Rect _toolbarRect = new Rect(canvas.x - 15,0,canvas.width + 30,45);
		
		GUILayout.BeginArea(_toolbarRect);
		GUI.Box(new Rect(0,0,_toolbarRect.width,_toolbarRect.height),"");
		GUILayout.Space(5);
		GUILayout.BeginHorizontal();
			GUILayout.Space(5);
			GUILayoutOption[] _glo = new GUILayoutOption[2]{GUILayout.Width(32),GUILayout.Height(32)};
		
			GUIContent _toolGC = new GUIContent();
			
			//Draw Rectangle Tool
			_toolGC.tooltip = "Draw Rectangle";
			_toolGC.text = "";
			if(toolMode == ToolMode.Draw) _toolGC.image = _drawIconActive;
			else _toolGC.image = _drawIcon;
				
			if(GUILayout.Button(_toolGC,_glo)) toolMode = ToolMode.Draw;
			
			//Move Rectangle
			_toolGC.tooltip = "Move Rectangle";
			_toolGC.text = "";
			if(toolMode == ToolMode.Move) _toolGC.image = _moveIconActive;
			else _toolGC.image = _moveIcon;
			
			if(GUILayout.Button(_toolGC,_glo)) toolMode = ToolMode.Move;
			
			//Zoom in
			_toolGC.tooltip = "Zoom In";
			_toolGC.text = "";
			_toolGC.image = _zoominIcon;
			if(GUILayout.Button(_toolGC,_glo)) if(_material) _zoom = Mathf.Clamp(_zoom + 1, 1, 4);
			
			//Zoom out
			_toolGC.tooltip = "Zoom Out";
			_toolGC.text = "";
			_toolGC.image = _zoomoutIcon;
			if(GUILayout.Button(_toolGC,_glo)) if(_material) _zoom = Mathf.Clamp(_zoom - 1, 1, 4);
			
			// RGB/A Mode
			if(showAlpha)
			{
				_toolGC.tooltip = "Switch to RGB Mode";
				_toolGC.text = "";
				_toolGC.image = _rgbIcon;
			}
			else
			{
				_toolGC.tooltip = "Switch to Alpha Mode";
				_toolGC.text = "";
				_toolGC.image = _alphaIcon;
			}
			if(GUILayout.Button(_toolGC,_glo)) showAlpha = !showAlpha;
			
			//Snaps Toggle
			_toolGC.tooltip = "Toggle Grid Snapping";
			_toolGC.text = "";
			if(_snapGrid) _toolGC.image = _snapIconActive;
			else _toolGC.image = _snapIcon;
				
			if(GUILayout.Button(_toolGC,_glo)) _snapGrid = !_snapGrid;		
		
		GUILayout.EndHorizontal();
		
		#endif
		
		GUILayout.EndArea();
	}
	
	#endregion
	
	#region Asset Generation
	
	void GenerateCamera()
	{		
		GameObject go = new GameObject();
		go.AddComponent(typeof(Camera));
		
		go.name = _camName;
		Camera cam = (Camera) go.GetComponent(typeof(Camera));
		go.transform.position = new Vector3(res.x/2,-res.y/2,-100);
		go.transform.forward = Vector3.forward;
		cam.orthographic = true;
		cam.orthographicSize = res.y/2;
		cam.depth = 90;
		cam.farClipPlane = 200;
		cam.clearFlags = CameraClearFlags.Depth;
		cam.cullingMask = 1 << _cullingMask[0];
	}
	
	void ConnectQuad()
	{
		Debug.Log(AssetDatabase.LoadAllAssetsAtPath(_meshOutputPath + "/" + _name + ".obj"));
		foreach(UnityEngine.Object o in AssetDatabase.LoadAllAssetsAtPath(_meshOutputPath + "/" + _name + ".obj"))
		{
			Debug.Log(o);
		}
	}
	
	void BakeQuad()
	{
		Debug.Log("Quad will be baked.");
		
		/* First step, construct the mesh */
		
		Mesh mesh = new Mesh();
		
		Vector2 tmpUV = _uv;
		if(_class != QuadUIClass.None && _class != QuadUIClass.Static) _uv = _frames[0].position;
		
		CalcVerts(); // we calc our verts and uv's
		
		_uv = tmpUV;
		
		mesh.vertices = newVertices;
		mesh.uv = newUV;
		mesh.triangles = newTriangles;
		mesh.normals = new Vector3[4] {Vector3.forward,Vector3.forward,Vector3.forward,Vector3.forward};
		
		mesh.name = _name + " Generated Quad";
		
		/* Now we have to draw up all the components our game object will have */
		
		System.Type[] components;
		
		if(_class == QuadUIClass.None) components = new System.Type[5];
		else components = new System.Type[6];
		
		components[0] = typeof(Animation);
		components[1] = typeof(MeshFilter);
		components[2] = typeof(MeshRenderer);
		components[3] = typeof(Quad2D);
		
		if(_class != QuadUIClass.None && _class != QuadUIClass.Static) components[4] = typeof(MeshCollider);
		
		switch(_class)
		{
			case QuadUIClass.Button:
			components[5] = typeof(Button2D);
			break;
			
			case QuadUIClass.Toggle:
			components[5] = typeof(Toggle2D);
			break;
			
			case QuadUIClass.Sprite:
			components[5] = typeof(Sprite2D);
			break;
			
			case QuadUIClass.InteractiveSprite:
			components[5] = typeof(InteractiveElement2D);
			break;
			
			case QuadUIClass.Static:
			components[5] = typeof(Element2D);
			break;
		}
		
		//Create the output directory if it doesn't exist
		
		EQEQIO.CreateTargetFolder(_prefabOutputPath);
		
		//Create an empty prefab
		
		bool _overridePrefab = true;
		
		if(AssetDatabase.LoadAssetAtPath(_prefabOutputPath+"/"+_name+".prefab",typeof(GameObject)) != null)
		{
			if(EditorUtility.DisplayDialog("Warning! Overriding Prefab!", "There is currently a Prefab with this name already in the target folder. Override?","OK","Cancel"))
			{
				_overridePrefab = true;
			}
			else
			{
				_overridePrefab = false;
			}
		}
		
		UnityEngine.Object p = null;
		
		if(_overridePrefab)
		{
			p = EditorUtility.CreateEmptyPrefab(_prefabOutputPath+"/"+_name+".prefab");
		}
		
		GameObject go = new GameObject();
		go.name = _name;
		go.AddComponent(components[0]);
		go.AddComponent(components[1]);
		go.AddComponent(components[2]);
		go.AddComponent(components[3]);
		go.AddComponent(components[4]);
		if(components.Length > 5) go.AddComponent(components[5]);
		
		switch(_class)
		{
			case QuadUIClass.Button:
			Button2D _eqButton = (Button2D) go.GetComponent(typeof(Button2D));
			_eqButton.quad = (Quad2D) go.GetComponent(typeof(Quad2D));
			_eqButton.frames = new Vector2[_frames.Length];
			/*_eqButton.rollOverUV = */_eqButton.frames[2] = _frames[2].position;
			/*_eqButton.pressUV = */_eqButton.frames[1] = _frames[1].position;
			/*_eqButton.normalUV =*/_eqButton.frames[0] = _frames[0].position;
			
			//_eqButton
			break;
			
			case QuadUIClass.Toggle:
			Toggle2D _eqToggle = (Toggle2D) go.GetComponent(typeof(Toggle2D));
			_eqToggle.quad = (Quad2D) go.GetComponent(typeof(Quad2D));
			_eqToggle.frames = new Vector2[_frames.Length];
			
			/*_eqToggle.offUV = */_eqToggle.frames[0] = _frames[0].position;
			/*_eqToggle.offOverUV = */_eqToggle.frames[1] = _frames[1].position;
			/*_eqToggle.onUV = */_eqToggle.frames[2] = _frames[2].position;
			/*_eqToggle.onOverUV = */_eqToggle.frames[3] = _frames[3].position;
			
			break;
			
			case QuadUIClass.Sprite:
			Sprite2D _eqSprite = (Sprite2D) go.GetComponent(typeof(Sprite2D));
			_eqSprite.quad = (Quad2D) go.GetComponent(typeof(Quad2D));
			_eqSprite.frames = new Vector2[_frames.Length];
			for(int i = 0; i < _frames.Length; i++)
			{
				_eqSprite.frames[i] = _frames[i].position;
			}
			break;
			
			case QuadUIClass.InteractiveSprite:
			InteractiveElement2D _eqIntSprite = (InteractiveElement2D) go.GetComponent(typeof(InteractiveElement2D));
			_eqIntSprite.quad = (Quad2D) go.GetComponent(typeof(Quad2D));
			_eqIntSprite.frames = new Vector2[_frames.Length];
			for(int i = 0; i < _frames.Length; i++)
			{
				_eqIntSprite.frames[i] = _frames[i].position;
			}
			break;
			
			case QuadUIClass.Static:
			Element2D _eqElement = (Element2D) go.GetComponent(typeof(Element2D));
			_eqElement.quad = (Quad2D) go.GetComponent(typeof(Quad2D));
			break;
		}
		
		go.renderer.material = _material;
		go.layer = _layer;
		
		MeshFilter mf = (MeshFilter) go.GetComponent(typeof(MeshFilter));
		mf.mesh = mesh;
		
		bool _overrideMesh = true;
		
		
		if(AssetDatabase.LoadMainAssetAtPath(_meshOutputPath + "/" + _name + ".obj") != null)
		{
			if(EditorUtility.DisplayDialog("Warning! Overriding Mesh!", "There is currently a .OBJ with this name already in the target folder. Override? \n \n Note: A temporary Quad will be generated if you do not override. This Quad will not be retained when Unity closes.","OK","Cancel"))
			{
				_overrideMesh = true;
			}
			else
			{
				_overrideMesh = false;
			}
		}
		
		if(_overrideMesh)
		{
		
			QuadExporter exporter = new QuadExporter(_name,_meshOutputPath);
			string mfPath = exporter.ExportObj(mf);
			
			Debug.Log("Exported Mesh to: " + mfPath);
			Debug.Log("assets: " + AssetDatabase.LoadAllAssetsAtPath(_meshOutputPath + "/" + _name + ".obj"));
			
			AssetDatabase.Refresh();
			
			foreach(UnityEngine.Object ueo in AssetDatabase.LoadAllAssetsAtPath(_meshOutputPath + "/" + _name + ".obj"))
			{
				if(ueo.GetType() == typeof(MeshFilter))
				{
					Debug.Log("found mesh filter");
					mf.mesh = (ueo as MeshFilter).sharedMesh;
				}
			}
			
			if(go.collider)
			{
				Debug.Log("found mesh collider");
				(go.collider as MeshCollider).sharedMesh = mf.sharedMesh;
			}
			
			if(_reg == RegistrationPoint.BottomLeft || _reg == RegistrationPoint.BottomRight)
			{
				go.transform.rotation = Quaternion.Euler(0,180,180);
			}
			
		}
		
		if(_overridePrefab) EditorUtility.ReplacePrefab(go,p);
		
		AssetImporter ai = (AssetImporter) AssetImporter.GetAtPath(_meshOutputPath + "/" + _name + ".obj");

		if(ai)
		{
			Debug.Log("Successfully found Model Importer. Overriding Material generation.");
			ModelImporter mi = (ModelImporter) ai;
			mi.generateMaterials = ModelImporterGenerateMaterials.None;
			Debug.Log(_meshOutputPath + "/Materials/" + _name +"-"+_name+ "mat.mat should be deleted.");
			AssetDatabase.DeleteAsset(_meshOutputPath + "/Materials/" + _name +"-"+_name+ "mat.mat");
		}
		else
		{
			Debug.Log("Failed to delete generated Material.");
		}
		
		AssetDatabase.Refresh();
		
		if(_overridePrefab)
		{
			Editor.DestroyImmediate(go,true);
			EditorUtility.InstantiatePrefab(p);
		}
			
		_name = "New Quad";
	}
	
	#endregion
	
	#region Mesh Utils
	
	Vector3 CalcPivot()
	{
		Vector3 tV3 = Vector3.zero;
		
		switch(_reg)
		{
			case RegistrationPoint.Center:
			tV3 = Vector3.zero;	
			break;
			
			case RegistrationPoint.TopLeft:
			tV3 = new Vector3(-1,1,0);	
			break;
			
			case RegistrationPoint.BottomLeft:
			tV3 = new Vector3(-1,-1,0);	
			break;
			
			case RegistrationPoint.TopRight:
			tV3 = new Vector3(1,1,0);	
			break;
			
			case RegistrationPoint.BottomRight:
			tV3 = new Vector3(1,-1,0);	
			break;
		}
		
		return tV3;
	}
	
	void CalcVerts()
	{
		newVertices = new Vector3[4];
		
		Rect rect = new Rect(0,0,widthHeight.x,widthHeight.y);
		
		if(_reg == RegistrationPoint.TopLeft)
		{
			Debug.Log("reg top left");
			newVertices[0] = new Vector3(rect.x,-rect.y,0F); //top left
			newVertices[1] = new Vector3(rect.width,-rect.y,0F); //top right
			newVertices[2] = new Vector3(rect.x,-rect.height,0F); //bot left
			newVertices[3] = new Vector3(rect.width,-rect.height,0F); //bot right
		}
		else if(_reg == RegistrationPoint.TopRight)
		{
			Debug.Log("reg top right");
			newVertices[0] = new Vector3(-rect.width,rect.y,0F); //top left
			newVertices[1] = new Vector3(rect.x,rect.y,0F); //top right
			newVertices[2] = new Vector3(-rect.width,-rect.height,0F); //bot left
			newVertices[3] = new Vector3(rect.x,-rect.height,0F); //bot right
		}
		else if(_reg == RegistrationPoint.Center)
		{
			Debug.Log("reg center");
			newVertices[0] = new Vector3(-rect.width/2,rect.height/2,0F); //top left
			newVertices[1] = new Vector3(rect.width/2,rect.height/2,0F); //top right
			newVertices[2] = new Vector3(-rect.width/2,-rect.height/2,0F); //bot left
			newVertices[3] = new Vector3(rect.width/2,-rect.height/2,0F); //bot right
		}
		else if(_reg == RegistrationPoint.BottomLeft)
		{
			Debug.Log("reg center");
			newVertices[0] = new Vector3(rect.x,-rect.height,0F); //top left
			newVertices[1] = new Vector3(rect.width,-rect.height,0F); //top right
			newVertices[2] = new Vector3(rect.x,rect.y,0F); //bot left
			newVertices[3] = new Vector3(rect.width,rect.y,0F); //bot right
		}
		else if(_reg == RegistrationPoint.BottomRight)
		{
			Debug.Log("reg center");
			newVertices[0] = new Vector3(-rect.width,-rect.height,0F); //top left
			newVertices[1] = new Vector3(rect.x,-rect.height,0F); //top right
			newVertices[2] = new Vector3(-rect.width,rect.y,0F); //bot left
			newVertices[3] = new Vector3(rect.x,rect.y,0F); //bot right
		}
		
		newTriangles = new int[6] {0,1,2,3,2,1};
		
		newUV = new Vector2[4];
		
		newUV[0] = CalcUV(new Vector2(_uv.x,_uv.y)); //top left
		newUV[1] = CalcUV(new Vector2(_uv.x+rect.width,_uv.y)); //top right
		newUV[2] = CalcUV(new Vector2(_uv.x,_uv.y+rect.height)); //bottom left
		newUV[3] = CalcUV(new Vector2(_uv.x+rect.width,_uv.y+rect.height)); //bottom right
		
	}
	
	Vector2 CalcUV(Vector2 xy)
	{
		Texture _tex = _material.GetTexture("_MainTex");
		Rect t = new Rect(0,0,_tex.width,_tex.height);
		
		return new Vector2(xy.x / ((float)t.width), ((float)t.height-xy.y) / ((float)t.height));
	}
	
	#endregion
}