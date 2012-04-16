using UnityEngine;
using System.Collections;

public class GoEffect : EffectBase {
	

	float counter = 0.0f;
	float alphe = 1.0f;
	float angle = 0.0f;

	

	void Start () {
		firstEffect ( 1 , 1 , alphe );
		
		transform.localPosition = new Vector3(GameManager.ScreenSize.x / 2 , GameManager.ScreenSize.y / 2 , -1);
		transform.localScale = new Vector3(2 , 1 , 0);
	}
	
	// Update is called once per frame
	void Update () {
		counter += Time.deltaTime;


		//kieru
		{
			alphe -= 0.025f ;
			if( alphe <= 0 )
			{
				alphe = 0.0f ;
				DestroyObject( gameObject );
				return;
			}
			SetAlphe( alphe );
		}
	}

}
