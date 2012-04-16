using UnityEngine;
using System.Collections;

public class BlackBack : MonoBehaviour {
	


	 public int type = 0;
	 public GUISkin castamGUI ;

	 private GUIStyle m_BackgroundStyle = new GUIStyle();        // Style for background tiling
	 private Texture2D m_FadeTexture;                // 1x1 pixel texture used for fading
	 private Color m_CurrentScreenOverlayColor = new Color(255,255,255,0); // default starting color: black and fully transparrent
	 private Color m_TargetScreenOverlayColor = new Color(25,25,25,25);  // default target color: black and fully transparrent
  	 private Color m_DeltaColor = new Color(25,25,25,0);        // the delta-color is basically the "speed / second" at which the current color should change
	 private int m_FadeGUIDepth = -1000;             // make sure this texture is drawn on top of everything	

	 private int count = 0;
	

	 public const int TYPE_MENU = 0;
	 public const int TYPE_GBO  = 1;
	 public const int TYPE_TITLE= 2;
	 public const int TYPE_FEED_IN= 3;
	


	 private SpriteManager  Manager;
	 private Sprite 		Filter;
	
	 private float alphe = 1.0f;

	 // Use this for initialization
 	 void Start () {

		GameObject refObj = GameObject.Find("Filter_MNG");
		Manager = (SpriteManager)refObj.GetComponent( typeof(SpriteManager));
		Filter = Manager.AddSprite( gameObject , 4, 4, 0 , 0 , 4, 4, false );
		
		Filter.offset = new Vector3( Screen.width / 2 , Screen.height / 2 , -2 );
		Filter.SetDrawLayer( 5 );
		Filter.SetSizeXY( Screen.width * 10, Screen.height * 10);
		
		
		alphe = 0.0f;
		
		Awake();
		setAlphe();
		//SOUND
		if(type == TYPE_MENU)
		{
			GameObject seObj = (GameObject)Resources.Load("Sound/sound_se_Menu");
			GameObject obj = (GameObject)Instantiate( seObj );
		}
	 }

	public void clean()
	{	
		if( Filter != null)
		{
			Manager.RemoveSprite( Filter );	
			Filter = null;
			alphe = 0.0f;
			Manager = null;
		}

        
		DestroyObject( gameObject );
	}
	
	void setAlphe()
	{
		if( Filter != null)
		{
			Filter.SetColor( new Color( 0 , 0 , 0, alphe));
		}
	}
	
	// Update is called once per frame
	void Update () {
		//OnGUI();	
		setAlphe();
	}		
		
	private void Awake()
    {       
        m_FadeTexture = new Texture2D(1, 1);        
        m_BackgroundStyle.normal.background = m_FadeTexture;
        SetScreenOverlayColor(m_CurrentScreenOverlayColor);

		m_CurrentScreenOverlayColor.r = 0.0f;
		m_CurrentScreenOverlayColor.g = 0.0f;        
		m_CurrentScreenOverlayColor.b = 0.0f;     
   
		switch(type)
		{
			case TYPE_MENU:
			{
				alphe = 0.5f;
				break;
			}
			case TYPE_GBO:
			{
				alphe = 0.0f;
				break;
			}
			case TYPE_TITLE:
			{
				alphe = 0.0f;
				break;
			}
			case TYPE_FEED_IN:
			{
				alphe = 1.0f;
				break;
			}
		}
		
        // TEMP:
        // usage: use "SetScreenOverlayColor" to set the initial color, then use "StartFade" to set the desired color & fade duration and start the fade
        //SetScreenOverlayColor(new Color(0,0,0,1));
        //StartFade(new Color(1,0,0,1), 5);
    }
	
	   
	// draw the texture and perform the fade:
    private void OnGUI()
    {   
		GUI.skin = castamGUI;

        if ( alphe > 0)
        {           
			//SetScreenOverlayColor(m_CurrentScreenOverlayColor);

			//GUI.depth = m_FadeGUIDepth;

            //GUI.Label(new Rect(0, 0, Screen.width, Screen.height ), m_FadeTexture, m_BackgroundStyle);
        }
		
		int w = 200;
		int h = 100;
		
		int x = 50;
		int y = 340;
		int movex = 250;
		

		switch(type)
		{
			case TYPE_MENU:
			{
				if( GUI.Button( new Rect( 
					x * GameManager.Instance.getScreenW_GUI() ,
					y * GameManager.Instance.getScreenH_GUI() ,
					w * GameManager.Instance.getScreenW_GUI() ,
					h * GameManager.Instance.getScreenH_GUI() )
					, "Retry" ) )
				{
					retry();
				}
				
				if( GUI.Button( new Rect( 
					(x + movex) * GameManager.Instance.getScreenW_GUI() ,
					y * GameManager.Instance.getScreenH_GUI() ,
					w * GameManager.Instance.getScreenW_GUI() ,
					h * GameManager.Instance.getScreenH_GUI() )
					, "Title" ) )
				{
					goTitle();
				}
	
				if( GUI.Button( new Rect( 
					(x + movex*2) * GameManager.Instance.getScreenW_GUI() ,
					y * GameManager.Instance.getScreenH_GUI() ,
					w * GameManager.Instance.getScreenW_GUI() ,
					h * GameManager.Instance.getScreenH_GUI() )
					, "Back" ) )
				{
					GameManager.Instance.setPlay();
					// Del
					clean();
					GameObject seObj = (GameObject)Resources.Load("Sound/sound_se_Menu");
					GameObject obj = (GameObject)Instantiate( seObj );
				}
				break;
			}

			case TYPE_GBO:
			{
				if( alphe < 1.0f)
				{
					alphe += Time.deltaTime / 3;
					
					if( alphe >= 1.0f )
					{
						alphe = 1.0f;
					
						GameObject viewobj = GameObject.Find( "2D" );	

						int dead = GameManager.Instance.getDead();
						int deadLen = 0;
						while(dead >= 10)
						{	
							dead /= 10;
							deadLen++;
						}
						int point = GameManager.Instance.getScore();						
						int pointLen = 0;
						while(point >= 10)
						{	
							point /= 10;
							pointLen++;
						}
						int baseNiwaX = 650;
						int baseNiwaY = 160;
						int score = GameManager.Instance.getScore();
						int deadNum = GameManager.Instance.getDead();
						//Score
						{
							GameObject  refObj = (GameObject)Resources.Load("Effect/score");
							{
								GameObject obj = (GameObject)Instantiate( refObj );
								obj.transform.parent = viewobj.transform;
								obj.transform.localPosition = 
								new Vector3(baseNiwaX+100, baseNiwaY +10 , -2);		
								scoreDraw data = (scoreDraw)obj.gameObject.GetComponent(typeof(scoreDraw));
								data.showScore = GameManager.Instance.getScore();		
								data.type = scoreDraw.TYPE_RESULT_SCORE;
								obj.transform.localScale = 
									new Vector3( 0.8f ,  0.8f  , 1 );
							}
							{
								GameObject obj = (GameObject)Instantiate( refObj );
								obj.transform.parent = viewobj.transform;
								obj.transform.localPosition = 
								new Vector3(720, 330 , -2);		
								scoreDraw data = (scoreDraw)obj.gameObject.GetComponent(typeof(scoreDraw));
								data.showScore = GameManager.Instance.getScore();		
								data.type = scoreDraw.TYPE_RESULT_DEATH;
								obj.transform.localScale = 
									new Vector3( 0.25f ,  0.25f  , 1 );
							}
						}
						//Chicken
						{
							GameObject  refObj = (GameObject)Resources.Load("Effect/Result_Chicken");
						
							float big = 0.8f;
							int fps = GameManager.Instance.getScore() / 5 + 1;	
							if(fps >= 20)
							{
								fps = 20;
							}
							int niwaUpPoint = 10;
							int num = GameManager.Instance.getScore() / niwaUpPoint + 1;
							if(num >= 12)
							{
								num = 12;
							}
	
							for(int i = 0; i < num; i++)
							{
								GameObject obj = (GameObject)Instantiate( refObj );
								obj.transform.parent = viewobj.transform;
								obj.transform.localScale = 
									new Vector3( big ,  big  , 1 );					
								Sprite2D data = (Sprite2D)obj.GetComponent(typeof(Sprite2D));
								data.fps = fps;
								obj.transform.localPosition = 
									new Vector3( baseNiwaX  - pointLen * 80 - i % 4 * 64 - 40 ,
									baseNiwaY + i /4 * 64  , -3);
							}
	
							int kingScore = niwaUpPoint * 12;
							if(score > kingScore)
							{
								big = 0.8f + (-kingScore + score )* 0.001f;
								if(big >= 2.0f)
								{
									big = 2.0f;
								}

								GameObject  refObjKing = (GameObject)Resources.Load("Effect/Result_King");
								GameObject obj = (GameObject)Instantiate( refObjKing );
								obj.transform.parent = viewobj.transform;
								obj.transform.localScale = 
									new Vector3( big ,  big  , 1 );					
								Sprite2D data = (Sprite2D)obj.GetComponent(typeof(Sprite2D));
								data.fps = fps;
								obj.transform.localPosition = 
									new Vector3( baseNiwaX  - pointLen * 80 - 5 * 64 -20 ,
									baseNiwaY + 64  , -3);
							}
						

						}
						//ChickenMeet
						{
							GameObject  refObj = (GameObject)Resources.Load("Effect/Result_Chicken_Meet");
							int num = deadNum / 100+1;
							if(num >= 13)
							{
								num = 13;
							}
							for(int i = 0; i < num; i++)
							{
								GameObject obj = (GameObject)Instantiate( refObj );
								obj.transform.parent = viewobj.transform;
									obj.transform.localPosition = 
									new Vector3( 670 - deadLen * 24 - i * 32,  330  , -3);	
								obj.transform.localScale = 
									new Vector3( 0.5f ,  0.5f  , 1 );				
							}
						}
						//Result
						{
								GameObject  refObj = (GameObject)Resources.Load("Effect/Result");
								GameObject obj = (GameObject)Instantiate( refObj );
								obj.transform.parent = viewobj.transform;
									obj.transform.localPosition = 
									new Vector3( 400,  400  , -3);	
								obj.transform.localScale = 
									new Vector3( 1.2f ,  1.2f  , 1 );	
						}

						


						//ALL DELETE
						GameObject ScreenObj = GameObject.Find("ScreenManager") ;
						DestroyObject(ScreenObj);
						GameObject TransitionObj = GameObject.Find("Transition") ;
						DestroyObject(TransitionObj);





					}
					return;
				}
	
				if( GUI.Button( new Rect( 
					x * GameManager.Instance.getScreenW_GUI() ,
					y * GameManager.Instance.getScreenH_GUI() ,
					w * GameManager.Instance.getScreenW_GUI() ,
					h * GameManager.Instance.getScreenH_GUI() )
					, "Retry" ) )
				{
					retry();
				}
				
				if( GUI.Button( new Rect( 
					(x + movex) * GameManager.Instance.getScreenW_GUI() ,
					y * GameManager.Instance.getScreenH_GUI() ,
					w * GameManager.Instance.getScreenW_GUI() ,
					h * GameManager.Instance.getScreenH_GUI() )
					, "Title" ) )
				{
					goTitle();
				}
	
				if( GUI.Button( new Rect( 
					(x + movex*2) * GameManager.Instance.getScreenW_GUI() ,
					y * GameManager.Instance.getScreenH_GUI() ,
					w * GameManager.Instance.getScreenW_GUI() ,
					h * GameManager.Instance.getScreenH_GUI() )
					, "Tweet" ) )
				{
					//! tweet
					GameManager.Instance.TweetGameRecord();
					//GameManager.Instance.setPlay();
					// Del
					//DestroyObject( gameObject );
				}
				break;
			}

			case TYPE_TITLE:
			{
				titleDraw();
				break;
			}
			case TYPE_FEED_IN:
			{
				if( alphe > 0.0f)
				{
					alphe -= Time.deltaTime / 2 ;
				}else{
					clean();
				}
				break;
			}
		}

    }

	private void titleDraw()
	{
		int w = 200;
		int h = 100;
		
		int x = 50;
		int y = 340;
		int movex = 250;
		int movey = -50;

		if( GUI.Button( new Rect(
			(x + movex) * GameManager.Instance.getScreenW_GUI() ,
			(y + movey) * GameManager.Instance.getScreenH_GUI() ,
			(w  ) * GameManager.Instance.getScreenW_GUI() ,
			(h + 50) * GameManager.Instance.getScreenH_GUI() )
			, "Start" ) )
		{
			Application.LoadLevel("gamebody");
		}

		if( GUI.Button( new Rect( 
			x * GameManager.Instance.getScreenW_GUI() ,
			y * GameManager.Instance.getScreenH_GUI() ,
			w * GameManager.Instance.getScreenW_GUI() ,
			h * GameManager.Instance.getScreenH_GUI() )
			, "Help" ) )
		{
			retry();
		}
		
		if( GUI.Button( new Rect( 
			(x + movex * 2 ) * GameManager.Instance.getScreenW_GUI() ,
			y * GameManager.Instance.getScreenH_GUI() ,
			w * GameManager.Instance.getScreenW_GUI() ,
			h * GameManager.Instance.getScreenH_GUI() )
			, "Site" ) )
		{
			retry();
		}

		//if( GUI.Button( new Rect( 
		//	(x + movex*2) * GameManager.Instance.getScreenW() ,
		//	y * GameManager.Instance.getScreenH() ,
		//	w * GameManager.Instance.getScreenW() ,
		//	h * GameManager.Instance.getScreenH() )
		//	, "Close" ) )
		//{
		//	GameManager.Instance.setPlay();
			// Del
		//	DestroyObject( gameObject );
		//}
	}
	
	private void retry()   
	{ 
		//! 再起動 Application.loadedLevel
		Application.LoadLevel( Application.loadedLevel );	
	}
	
	private void goTitle()
	{
		Application.LoadLevel("StartScene");
	}
	    
	private void Turn()
	{
		GameManager.Instance.setPlay();
	}

    // instantly set the current color of the screen-texture to "newScreenOverlayColor"
    // can be usefull if you want to start a scene fully black and then fade to opague
    public void SetScreenOverlayColor(Color newScreenOverlayColor)
    {
        m_CurrentScreenOverlayColor = newScreenOverlayColor;
        m_FadeTexture.SetPixel(0, 0, m_CurrentScreenOverlayColor);
        m_FadeTexture.Apply();
    }
	    
}
