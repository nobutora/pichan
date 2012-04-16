using UnityEngine;
using System.Collections;

public class Wing : ScrollBehaviour {

	const float MAX_MOVEX = 8.0f;	
	const int MAX_MOVE_TIME = 55;	

	private float moveX = MAX_MOVEX;
	private bool  moveLeft = false;
	// Use this for initialization
	void Start () {

		
		
		transform.localScale = new Vector3( -0.4f ,//* GameManager.Instance.getScreenW(),
		 0.4f , //* GameManager.Instance.getScreenH(),
		 1.0f );

		//Vector3 scale = new Vector3(
		//	transform.localScale.x * w,
		//	transform.localScale.y * h,
		//	1 );
	
		//transform.localScale = scale;
	}
	
	// Update is called once per frame
	void Update () {
		

		if(GameManager.Instance.isStop())
		{
			return;
		}

		Move();
		//! 初期状態		
		Scroll(1.5f);
	}

	void Move()
	{
		Vector3 vPos = transform.localPosition;
		vPos.y -= 0.4f;
		vPos.x += moveX;
		
		Quaternion Rot = transform.localRotation;
		if(moveLeft)
		{
			moveX += MAX_MOVEX / MAX_MOVE_TIME;
			if(moveX >= 0)
			{
				moveLeft = false;
				moveX = MAX_MOVEX;
			}
			Rot.z += -0.007f;

		}else
		{
			moveX -= MAX_MOVEX / MAX_MOVE_TIME;	
			if(moveX <= 0)
			{
				moveLeft = true;
				moveX = -MAX_MOVEX;
			}		
			Rot.z += 0.007f;
		}
		transform.localPosition = vPos;
		transform.localRotation = Rot;


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
			PlayerManager.Instance.SetReturnAll();
			GameObject.DestroyObject( gameObject );
			if(Library.getSeNum(Library.SE_ITEM) <= 3)
			{
				Library.addSeNum(Library.SE_POINT_UP, 1);
				GameObject refObj = (GameObject)Resources.Load("Sound/sound_se_getItem");			
				GameObject obj = (GameObject)Instantiate( refObj );
				soundMove sound =(soundMove)obj.GetComponent( typeof(soundMove));
				sound.type = Library.SE_ITEM;
			}
		}



	}

	

}
