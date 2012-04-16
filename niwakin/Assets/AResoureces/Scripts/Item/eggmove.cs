using UnityEngine;
using System.Collections;

public class eggmove : EffectBase {

	const float MAX_MOVEY = 1.0f;	
	const int MAX_MOVE_TIME = 25;	

	private float moveY = MAX_MOVEY;
	private bool  moveLeft = false;

	public int type = 0;
	
	private float angle = 0;

	// Use this for initialization
	void Start () {

		//float w = GameManager.ScreenSize.x / Library.getDefultWindowW();		
		//float h = GameManager.ScreenSize.y / Library.getDefultWindowH();

		//float a = ScrollManager.Instance.ViewSize.x;		
		//float b = ScrollManager.Instance.ViewSize.y;

		//Screen.width;
		//Screen.height

		//Resources[] c = Screen.resolutions;

			



		//Vector3 scale = new Vector3(
		//	transform.localScale.x * w,
		//	transform.localScale.y * h,
		//	1 );
	
		//transform.localScale = scale;
		

		if( type == 0 )
		{
			transform.localScale = new Vector3( -0.5f , /// GameManager.Instance.getScreenW(), 
				1.0f , /// GameManager.Instance.getScreenH(), 
				1.0f );
			firstEffect ( 2  , 1 , 1.0f );
		}else{
			firstEffect ( 1  , 1 , 1.0f );
			transform.localScale = new Vector3( -0.8f ,/// GameManager.Instance.getScreenW(), 
				0.8f ,/// GameManager.Instance.getScreenH(), 
				1.0f );
		}

		GameObject obj = transform.Find("grasu").gameObject;

		Material mat = obj.renderer.material;
        //mat.shader = Shader.Find("Transparent/Diffuse");

        Color color = mat.color;
        color.r = 1.0f;
        color.b = 1.0f;
        color.g = 1.0f;
        color.a = 0.5f ;
        mat.color = color;

		SetPictChange( 0 , 0 );
	}
	
	// Update is called once per frame
	void Update () {
		
		if(GameManager.Instance.isStop())
		{
			return;
		}

		Move();
		//! 初期状態		
		ScrollMove();
	}

	void Move()
	{
		Vector3 vPos = transform.localPosition;
		vPos.y += moveY;
		
		Quaternion Rot = transform.localRotation;
		if(moveLeft)
		{
			moveY += MAX_MOVEY / MAX_MOVE_TIME;
			if(moveY >= 0)
			{
				moveLeft = false;
				moveY = MAX_MOVEY;
			}
		}else
		{
			moveY -= MAX_MOVEY / MAX_MOVE_TIME;	
			if(moveY <= 0)
			{
				moveLeft = true;
				moveY = -MAX_MOVEY;
			}		
		}
		transform.localPosition = vPos;

		SetAngle(angle);
		angle += 2;


		if(vPos.y <= -50 || vPos.x < -50.0f ) {
			DestroyObject( gameObject );	
		}

	}

	
	//ぶつかった時
	void OnTriggerEnter( Collider col )
	{
		//! 当たった相手がぷれいやー　
		if( col.tag == "Player" )
		{			
			// kaisyuu
			//Niwatori
			//TeamManager.Instance. ;
			
		
			//GameObject pref = PiyoBase.Create( PiyoBase.UNITTYPE.FRIEND );
			//GameObject refObj = (GameObject)Instantiate(pref);
			//GameObject obj ;

			int pluse = GameManager.Instance.GameLevel;			

			int ran = Random.Range( 2 , 3 + pluse);
			
			if(type == 1)
			{
				ran = Random.Range( 3 , 6 + pluse  );
			}


			for(int i = 0 ; i < ran ; i++)
			{
				float ranX = col.transform.localPosition.x ;
				float ranY = col.transform.localPosition.y ;
				Vector3 position = new Vector3(
					ranX ,
					ranY ,
					1 );
				TeamManager.Instance.DirectCreate( position );
			}
			
			if(Library.getSeNum(Library.SE_ITEM) <= 3)
			{
				Library.addSeNum(Library.SE_POINT_UP, 1);
				GameObject refObj = (GameObject)Resources.Load("Sound/sound_se_getItem");			
				GameObject obj = (GameObject)Instantiate( refObj );
				soundMove sound =(soundMove)obj.GetComponent( typeof(soundMove));
				sound.type = Library.SE_ITEM;
			}


			GameObject.DestroyObject( gameObject );
		}



	}
}
