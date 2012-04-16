using UnityEngine;
using System.Collections;

public class rescueMove : MonoBehaviour {
	
	bool creatscore = false;
	// Use this for initialization
	void Start () {
		transform.localPosition = 
			new Vector3(GameManager.ScreenSize.x / 2 , GameManager.ScreenSize.y  , -2);
		transform.localScale = new Vector3(1.2f , 1.2f , 0);
	}
	// Update is called once per frame
	void Update () {

		if( transform.localPosition.y < GameManager.ScreenSize.y / 4 * 3)
		{
			if(creatscore == false)
			{
				GameObject  refObj = (GameObject)Resources.Load("Effect/score");
				GameObject obj = (GameObject)Instantiate( refObj );
				GameObject viewobj = GameObject.Find( "2D" );	
				obj.transform.parent = viewobj.transform;
				obj.transform.localPosition = 
				new Vector3(GameManager.ScreenSize.x / 2 , GameManager.ScreenSize.y / 2  , -2);

				scoreDraw data = (scoreDraw)obj.gameObject.GetComponent(typeof(scoreDraw));
				data.showScore = GameManager.Instance.getScore();	

				data.type = scoreDraw.TYPE_RESULT_SCORE;
				creatscore = true;
				
				//ALL DELETE
				GameObject ScreenObj = GameObject.Find("ScreenManager") ;
				DestroyObject(ScreenObj);
				GameObject TransitionObj = GameObject.Find("Transition") ;
				DestroyObject(TransitionObj);

			}
		}else
		{
			transform.localPosition = new Vector3( 
			transform.localPosition.x ,
			transform.localPosition.y - Time.deltaTime * 140 ,
			-2 );
		}
	}
}
