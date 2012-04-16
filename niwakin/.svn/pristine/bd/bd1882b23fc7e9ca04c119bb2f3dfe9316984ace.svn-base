using UnityEngine;
using System.Collections;

public class Ready : EffectBase {
	

	//public Texture ReadyTexture ;

	// Use this for initialization
	float counter = 0.0f;
	float alphe = 1.0f;
	float angle = 0.0f;

	
	//void OnGUI () 
	//{

	//	GameManager manager = GameManager.Instance;
		
	//	float w  =  ReadyTexture.width * 1.5f * manager.getScreenW() ;
	//	float h  =  ReadyTexture.height* 1.5f * manager.getScreenH() ;
	//
	//	float x = Screen.width / 2 - 
	//			w / 2;
		
	//	float y = Screen.height / 2 - 
	//			h / 2;
	//
	//	Vector2 pivotPoint  = new Vector2( Screen.width / 2 , Screen.height / 2 );

	//	if( angle < 360 )
	//	{
	//		angle += 10;
	//	}else
	//	{
	//		alphe -= 0.020f ;
	//		GUI.color = new Color ( 1.0f , 1.0f , 1.0f , alphe);
	//		if( alphe <= 0 )
	//		{
	//			DestroyObject( gameObject );
	//			GameObject  refObj = (GameObject)Resources.Load("Effect/Go");
	//			GameObject obj = (GameObject)Instantiate( refObj );
	//
	//			return;
	//		}
	//	}
	//	GUIUtility.RotateAroundPivot( angle, pivotPoint);

		//Back
	//	GUI.DrawTexture( new Rect(
	//		x ,
	//		y ,
	//		w ,
	//		h ),
	//	 ReadyTexture );

		


	//}

	void Start () {
		firstEffect ( 1 , 1 , alphe );

		//Vector3 vPos = transform.localPosition;
		//vPos.y = 350 * GameManager.Instance.getScreenH();
		//vPos.x = 400 * GameManager.Instance.getScreenW();

		//vPos.y = 130 ;
		//vPos.x = 150 ;
		//transform.localPosition = vPos;
		transform.localPosition = 
			new Vector3(GameManager.ScreenSize.x / 2 , GameManager.ScreenSize.y / 2 , -1);
		transform.localScale = new Vector3(2 , 2 , 0);
	}
	
	// Update is called once per frame
	void Update () {
		counter += Time.deltaTime;

		//kaiten
		if( angle < 360 )
		{
			angle += 20;
			//SetAngle( angle );
			
			return;
		}else
		//kieru
		{
			SetAngle( 0 );
			alphe -= 0.025f ;
			if( alphe <= 0 )
			{
				alphe = 0.0f ;
				DestroyObject( gameObject );
				GameObject  refObj = (GameObject)Resources.Load("Effect/Go");
				GameObject obj = (GameObject)Instantiate( refObj );
				GameObject viewobj = GameObject.Find( "2D" );	
				obj.transform.parent = viewobj.transform;
				
				//Instantiate.StartBgm();
				return;
			}
			SetAlphe( alphe );
		}
	}
}
