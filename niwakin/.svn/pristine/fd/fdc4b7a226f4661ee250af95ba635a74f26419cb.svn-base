using UnityEngine;
using System.Collections;

public class VirtualPad : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		
	}
	
	/// <summary>
	/// マウスタッチ
	/// </summary>
	void OnMouseDown() {
#if false
		transform.localRotation = new Quaternion(0,0,0.0f,0);
	    Ray ray  = Camera.main.ScreenPointToRay (Input.mousePosition);
	    RaycastHit hit;
		
		//! 入力開始
//		GameManager.Instance.beginPlayerInput();
		
		
		Sprite2D sp = (Sprite2D)GetComponent( typeof(Sprite2D) );
		if( sp )
		{
			sp.GoToFrame( 1 );	
		    if (collider.Raycast (ray, out hit, 100.0f)) {
		        if( hit.textureCoord.y > 0.5f ) {
					transform.localRotation = new Quaternion(0,0,0.0f,0);
					GameManager.Instance.UpforceToPlayer();
				} else {
					transform.localRotation = new Quaternion(0,0,180.0f,0);
					GameManager.Instance.DownforceToPlayer();
				}
				Debug.Log("HIT:" + hit.textureCoord.y.ToString());
		    }
			
		}
		
		//! 入力終了
//		GameManager.Instance.endPlayerInput();
#endif
	}
	void OnMouseDrag()
	{
		Debug.Log("DRAG:");
		OnMouseDown();
	}
	
	void OnMouseUp() {
		Sprite2D sp = (Sprite2D)GetComponent( typeof(Sprite2D) );
		if( sp )
		{
			sp.GoToFrame( 0 );	
		}			
	}
}
