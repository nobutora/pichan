using UnityEngine;
using System.Collections;

public class Verticalmissile : EnemyBase {
	
	public bool downflag = false;	//! これが0でない場合は下向き
	
	private float mAcc;
	
	// Use this for initialization
	void Start () {
		Vector3 vPos = transform.localPosition;
		Quaternion rot = Quaternion.identity;		
		//! 初回のX座標は固定
		
		if( downflag )
		{
			//! 下向き
			rot = new Quaternion(0,0,90,0);
			vPos.y = ScrollManager.ScrollViewSize.y + 50.0f;
			
			
			mAcc = -2.9f;
		}
		else
		{
			//! 上向き
			rot = new Quaternion(0,0,270,0);	
			vPos.y = - 50.0f;
			mAcc = 2.9f;
		}
		
		transform.localRotation = rot;
		transform.localPosition = vPos;
		
		transform.localScale = Vector3.one;
	}
	
	// Update is called once per frame
	void Update () {
		
		base.Move();
		MoveAfter();
		
		Vector3 vPos = transform.localPosition;
		if( vPos.x < -60 ) {
			//! 終了
			nextFlow( FLOW.EXIT );
		}
	}
	
	/// <summary>
	/// 行動フロー
	/// </summary>
	void MoveAfter() 
	{
		bool bNextFlag = false;
		switch( Step )
		{
		case 0: //! 移動
			const float time = 0.4f;
			
			if( Timer >= time ) {
				Timer = time;
				bNextFlag = true;
			}
			float y;
			if( downflag )
			{
				y = Mathf.Lerp( ScrollManager.Instance.StageSize.y + 50.0f, ScrollManager.Instance.StageSize.y-70.0f, Timer/time );
			}
			else
			{
				y = Mathf.Lerp( -50.0f, 70.0f, Timer/time );
			}
			setPositionY( y );
			if( bNextFlag ) {
				NextStep( 1 );
			}
			break;
			
		case 1:	//! 停止
			if( Timer > 0.2f ) {
				NextStep( 2 );	
			}
			break;
			
		case 2:	//! 突進
			{
				float v = 12.0f*Timer + mAcc * Timer*Timer / 2;
				addPositionY( v );
			
			}	break;
				
		}
	}
}
