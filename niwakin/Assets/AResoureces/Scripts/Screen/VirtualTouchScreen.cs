using UnityEngine;
using System.Collections;

public class VirtualTouchScreen : MonoBehaviour {
	

	GameObject BlackScreen;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		
		float w = Screen.width / GameManager.ScreenSizeGui.x ;
		float h = Screen.height / GameManager.ScreenSizeGui.y;


		float x = 0;
		float y = 0;
		bool mouseok = false;

		bool back = false;

		if(Input.GetMouseButton(0))
		{
			x = Input.mousePosition.x;
			y = Input.mousePosition.y;
			mouseok = true;
		}else
		if(Input.touchCount >= 1)
		{
			if(Input.GetTouch(0).phase == TouchPhase.Began)
			{
				x = Input.GetTouch(0).position.x;
				y = Input.GetTouch(0).position.y;
				mouseok = true;
			}
		}
			

		if(Application.platform == RuntimePlatform.Android)
		{
			if(Input.GetKey(KeyCode.Escape))
			{
				back = true;
			}
		}
		else{
			if( Input.GetKey(KeyCode.Escape ) ||
				Input.GetKey(KeyCode.BackQuote) )
			{
				back = true;
			}
		}

		if( GameManager.Instance.isTime())
		{
			playTime(x,y,w,h,mouseok,back);
			return;			
		}else
		//
		// pLAY
		if( GameManager.Instance.isPlaying() )
		{ 	
			playTouch(x,y,w,h,mouseok,back);
			return;
		}
	}
	

	private void playTouch(float x , float y , float w , float h , bool mouseok , bool back )
	{
		//! 入力開始
		GameManager.Instance.beginPlayerInput();
		
		//MENU
		if(isMenuToush( x , y , w , h ) || back)
		{
			GameObject refObj = (GameObject)Resources.Load("Effect/Black");		
			BlackScreen = (GameObject)Instantiate( refObj );
			BlackBack data = (BlackBack)BlackScreen.gameObject.GetComponent(typeof(BlackBack));
			data.type = BlackBack.TYPE_MENU;
			GameObject viewobj = GameObject.Find( "2D" );
	
			data.transform.parent = viewobj.transform;
			GameManager.Instance.setTime();
			
		}else
		if( mouseok ||
			Input.GetAxisRaw("Vertical") == 1||
			Input.GetKey(KeyCode.Space))
		{
			//!　タッチ中は上向き
			GameManager.Instance.UpforceToPlayer();
		}
		else
		{
			//! 何もしなければ下向き
			GameManager.Instance.DownforceToPlayer();
		}
		
		
		if( Input.GetButton( "Fire1" ) )
		{
			//Debug.Log("fire1");	
		}
		if( Input.touchCount > 0 && 
      		Input.GetTouch(0).phase == TouchPhase.Moved )
		{
			Debug.Log("test");	
		}
		if( Input.touchCount > 0 )
		{
			Debug.Log("tete");	
		}
		/*
		if( Input.GetAxis ("Mouse X") != 0 )
		{
			Debug.Log("Mouse X");
		}
		*/


		//! 入力終了
		GameManager.Instance.endPlayerInput();

		

		//if(back)
		//{
		//	Application.Quit();
		//}
	}

	private void playTime(float x , float y , float w , float h, bool mouseok , bool back)
	{
		//TURN
		if(back)
		{			
			//GameManager.Instance.setPlay();
			//DestroyObject( BlackScreen );
		}
	}


	private bool isMenuToush( float x , float y , float w , float h)
	{
		int menu_W = 64;
		if( x > (736 - menu_W / 2) * w && x < (736 + menu_W / 2) * w 
			&& y < (64 + menu_W / 2 ) * h && y > (64 - menu_W / 2) * h )
		{
			return true;
		}
		return false;
	}


}
