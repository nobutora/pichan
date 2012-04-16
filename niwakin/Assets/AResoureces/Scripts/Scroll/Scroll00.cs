﻿using UnityEngine;
using System.Collections;

public class Scroll00 : MonoBehaviour {
	
	public Vector2 UVOffset;
	// Use this for initialization
	void Start () {
		UVOffset = Vector2.zero;
		
	}
	
	// Update is called once per frame
	void Update () {
		//!スクロール値にあわせてスクロールスピードが変わります
		float velocity = 0.2f * ScrollManager.Instance.ScrollVelocityScale;

		velocity = velocity * (1.0f + GameManager.Instance.GameLevel * 0.25f );
		

		UVOffset.x = Mathf.Repeat(UVOffset.x+(velocity*Time.deltaTime), 1.0f );
		renderer.material.SetTextureOffset( "_MainTex", UVOffset );
	}
}
