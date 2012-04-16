
using UnityEngine;
using System.Collections;

public class chikinMove : EffectBase {
	
	float alphe = 1.0f;
	float count = 0;

	float movex = 0;
	float movey = 0;
	
	float angle = 0.0f;
	

	// Use this for initialization
	void Start () {
		movex = Random.Range( -4.0f, 4.0f );
		movey = Random.Range( -4.0f, 4.0f );
		Quaternion vroll = transform.localRotation;
		
		//vroll.z = Random.Range( 0.0f, 1.0f );
		//transform.localRotation = vroll;
		
		angle = Random.Range( 0.0f, 360.0f );

		transform.localScale = new Vector3( -1.0f, 1.0f, 1.0f );

	}
	
	// Update is called once per frame
	void Update () {
	
		if(GameManager.Instance.isStop())
		{
			return;
		}

		SetAlphe( alphe );

		count += Time.timeSinceLevelLoad;
		if( count >= 50)
		{
			alphe -= 0.005f ;
			if( alphe <= 0 )
			{
				DestroyObject( gameObject );
			}			
			Vector3 vPos = transform.localPosition;
			vPos.y += movey;
			movey -= 0.04f;
			vPos.x += movex;

			transform.localPosition = vPos;
			
			//Quaternion vroll = transform.localRotation;
			//vroll.z += 0.15f;
			//transform.localRotation = vroll;
			angle += 3.0f;
			transform.eulerAngles = new Vector3(0, 0, angle);

			ScrollMove();
		}
		
		// scroll 

	}
	
	
	
	
}
