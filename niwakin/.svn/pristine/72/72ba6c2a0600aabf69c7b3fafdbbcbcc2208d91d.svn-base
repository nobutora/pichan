using UnityEngine;
using System.Collections;

public class chicken_damage : MonoBehaviour {
	
	float counter = 0.0f;
		
	float MAX_TIME = 2.0f;
	// Use this for initialization
	void Start () {
	
	}
	bool flame = false;
	// Update is called once per frame
	void Update () {
	

		counter += Time.deltaTime;
		if( counter >= (MAX_TIME / 3) )
		{
			if(flame == false)
			{
				PlayerManager.Instance.SetFlameAll();
				GameObject refObj = (GameObject)Resources.Load("Effect/Flame_Chikin");			
				//! 作成
				GameObject obj = (GameObject)Instantiate( refObj );
		
				GameObject efParent = GameObject.Find( "PlayerManager" );
				obj.transform.parent = efParent.transform ;
				int ranX = Random.Range(-20 , 20);
				int ranY = Random.Range(-20 , 20);
				Vector3 position = new Vector3( 
					transform.position.x + ranX,
					transform.position.y + ranY,
					1 );
				obj.transform.position = position;
				flame = true;
			}
			Color color = this.renderer.material.color;
	        color.r = 1.0f;
	        color.b = 1.0f;
	        color.g = 1.0f;
	        color.a -= 0.005f;
	        this.renderer.material.color = color;
		}
		if(counter >= MAX_TIME)
		{
			//Chicken Chnge
			GameObject refObj ;
	
			refObj = (GameObject)Resources.Load("Effect/chikin_big");			
			
			//! 作成
			GameObject obj = (GameObject)Instantiate( refObj );
			GameObject efParent = GameObject.Find( "PlayerManager" );
			obj.transform.parent = efParent.transform ;
			
			Vector3 position = new Vector3( 
				transform.position.x ,
				transform.position.y ,
				1 );
			obj.transform.position = position;
			Vector3 scale = new Vector3( 
				transform.localScale.x /5*4,
				transform.localScale.y /5*4,
				1 );
			obj.transform.localScale = scale;
				
	
	
			DestroyObject( gameObject );
		}
	}
}
