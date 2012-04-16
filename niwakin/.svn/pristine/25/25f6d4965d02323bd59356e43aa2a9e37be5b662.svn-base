using UnityEngine;
using System.Collections;

public class PlayerBase : PiyoBase {
	
	private float velocity;		//! 速度
	private float acc;			//! 加速度
	public TeamManager mTeamManager;
	private float Height;
	
	
	// Use this for initialization
	void Start () {
		Height = 0;
		
		velocity = 0;
		acc = 0;
		
		//! プレイヤーマネージャに自分を登録する
		PlayerManager.Instance.Player = this;
	}
	
	
	
	/// <summary>
	/// マイフレームの入力更新
	/// </summary>
	protected override void Move()
	{
	
		if( Application.isPlaying)
		{
			float plX = 290.0f;
			if(transform.localPosition.x < plX )
			{
				Vector3 vPos = transform.localPosition;
				vPos.x = transform.localPosition.x + 10;
				if( vPos.x >= plX )
				{
					vPos.x = 290.0f;
				}
				transform.localPosition = vPos;
			}
		}

		//BigChange
		//float bigChange = -1.0f - 1.0f * (GameManager.Instance.GameLevel * 0.1f);
		//float size = transform.localScale.x;
		//if( size > bigChange)
		//{
		//	transform.localScale = 
		//		new Vector3( 
		//		transform.localScale.x - Time.deltaTime / 10,
		//		transform.localScale.y + Time.deltaTime / 10 ,
		//		0 );
		//}
		
		/*
		float yMove = Input.GetAxis ("Mouse Y") * 48.0f;
		Vector3 vPos = transform.localPosition;
		vPos.y = Mathf.Clamp( vPos.y + yMove, 0, ScrollManager.ScrollViewSize.y );
		
		//float xMove = Input.GetAxis ("Mouse X") * 30.0f;
		//vPos.x = Mathf.Clamp( vPos.x + xMove, 0, GameManager.ScreenSize.x );
		
		float topY = vPos.y - (ScrollManager.Instance.ViewCenter.y + ScrollManager.Instance.ViewSize.y/2 - 100.0f);
		float underY = (ScrollManager.Instance.ViewCenter.y - ScrollManager.Instance.ViewSize.y/2 + 100.0f) - vPos.y;
		if( topY > 0 )
		{
			MoveScroll( topY );		
		}
		else if( underY > 0 )
		{
			MoveScroll( -underY );	
		}
		
		
		//
		transform.localPosition = ScrollManager.Instance.ClipingPosition( vPos );
		*/	
	}
	
	/// <summary>
	/// スクロール値更新
	/// </summary>
	private void MoveScroll(float height)
	{
		//スクロール値を設定
		ScrollManager.Instance.addHeight( height );
	}
	
	
	/// <summary>
	/// 仲間に追加
	/// </summary>
	/// <param name="obj">
	/// A <see cref="GameObject"/>
	/// </param>
	public void Join(GameObject obj)
	{
		
	}

	public void FireDead()
	{
		FlameChikinSet();
	}
	
	/// <summary>
	/// 力を加える
	/// </summary>
	/// <param name="f">
	/// A <see cref="System.Single"/>
	/// </param>
	public int addForce( float f )
	{
		float force = f * 2.0f;

		force = force / 6 * (6 + GameManager.Instance.GameLevel / 2);

		Vector3 vPos = transform.localPosition;
		vPos.y = Mathf.Clamp( vPos.y + force, 0, ScrollManager.ScrollViewSize.y );
		
		//float xMove = Input.GetAxis ("Mouse X") * 30.0f;
		//vPos.x = Mathf.Clamp( vPos.x + xMove, 0, GameManager.ScreenSize.x );
		
		float topY = vPos.y - (ScrollManager.Instance.ViewCenter.y + ScrollManager.Instance.ViewSize.y/2 - 100.0f);
		float underY = (ScrollManager.Instance.ViewCenter.y - ScrollManager.Instance.ViewSize.y/2 + 100.0f) - vPos.y;
		if( topY > 0 )
		{
			MoveScroll( topY );		
		}
		else if( underY > 0 )
		{
			MoveScroll( -underY );	
		}
		
		int retvalue = 0;
		if( ScrollManager.Instance.CheckTopOver( vPos ) )
		{
			retvalue = 1;
		}
		else if( ScrollManager.Instance.CheckBottomOver( vPos ) )
		{
			retvalue = 2;
		}
		
		transform.localPosition = ScrollManager.Instance.ClipingPosition( vPos );
		
		return retvalue;
	}
}
