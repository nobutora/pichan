using UnityEngine;
using System.Collections;

public class GrayFilter : MonoBehaviour {
	
	private SpriteManager Manager;
	private Sprite Filter;
	// Use this for initialization
	void Start () {
		
		GameObject refObj = GameObject.Find( "SPM_Filter" );
		Manager = (SpriteManager)refObj.GetComponent( typeof(SpriteManager) );
		Filter = Manager.AddSprite( gameObject, 4,4,0,0,4,4, false );
		
		Filter.offset = new Vector3(240.0f, -400.0f, 0);
		Filter.SetDrawLayer( 5 );
		Filter.SetSizeXY( 500, 820 );
		
		
		Debug.Log("FilterOn");
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
