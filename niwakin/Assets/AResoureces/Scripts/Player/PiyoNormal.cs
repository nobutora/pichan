using UnityEngine;
using System.Collections;

public class PiyoNormal : PlayerBase {
	
	private Sprite2D mSprite;
	
	// Use this for initialization
	void Start () {
		mSprite = (Sprite2D)GetComponent(typeof(Sprite2D));
		
		
	}

}
