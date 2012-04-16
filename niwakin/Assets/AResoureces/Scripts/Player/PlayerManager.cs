using UnityEngine;
using System.Collections;

public class PlayerManager : MonoBehaviour {
	
	//!インスタンス
	private static PlayerManager mInstance;
	public static PlayerManager Instance {
		get { return mInstance; }	
	}
	
	public float velocity;	//! 速度
	public float force;	//! 力
	public float acc;		//! 加速度
	public float time;		//! 速度用時間
	private bool stop = false;
	private bool flameAll = false;
	private bool ReturnAll = false;
	private int returnCounter = 0;
	public bool bTouch;		//! タッチ状態
	public bool bTouchOld;		//! 前回タッチ状態
	
	private	GameObject plPoint ;
	
	/// <summary>
	/// Gets the VELOCIT y_ MA.
	/// </summary>
	/// <value>
	/// The VELOCIT y_ MA.
	/// </value>
	public static float VELOCITY_MAX { 
		get { return 3.2f; }
	}
	/// <summary>
	/// Gets the VELOCIT y_ MI.
	/// </summary>
	/// <value>
	/// The VELOCIT y_ MI.
	/// </value>
	public static float VELOCITY_MIN {
		get { return -3.2f; }	
	}
	/// <summary>
	/// Gets the ACCMA.
	/// </summary>
	/// <value>
	/// The ACCMA.
	/// </value>
	public static float ACCMAX {
		get { return 8.0f; }
	}


	/// <summary>
	/// これを設定する必要がある
	/// </summary>
	public PlayerBase	Player;

	// Use this for initialization
	void Start () {
		
		plPoint = GameObject.FindGameObjectWithTag( "Player" );

		if( mInstance == null )
		{
			mInstance = this;
		}
		else
		{
			Destroy( this );	
		}
		
		
		velocity = 0;
		force = 0;
		acc = 0;
		time = 0;
		bTouch = false;
		bTouchOld = false;
	}
	
	// Update is called once per frame
	void Update () {
	
		if(GameManager.Instance.isStop())
		{
			return;
		}

		UpdateForce();
		
		
		if( Player )
		{
			if( this.ReturnAll)
			{
				this.returnCounter++;
				if(this.returnCounter >= 20)
				{
					this.ReturnAll = false;
				}
			}
			if(this.stop)
			{
				return ;
			}

			if( force != 0 )
			{
				float f = force;
				int rval = Player.addForce( f );
				if( rval > 0 )
				{
					//! rvalが0より大きい値が帰ってきた場合は天井か底に当たっているので
					//! その場合は加速度をリセットしてあげる
					velocity = 0;
					time = 0;	
				}
				
				force = 0;
			}
		}
	}
	
	/// <summary>
	/// 力の計算
	/// </summary>
	private void UpdateForce()
	{
		velocity += acc * time ;/// GameManager.Instance.getScreenH() ;
		if( velocity > VELOCITY_MAX ) {
			velocity = VELOCITY_MAX;
		} else if( velocity < VELOCITY_MIN) {
			velocity = VELOCITY_MIN;
		}
		force = velocity;
	}
	
	/// <summary>
	/// 入力開始
	/// </summary>
	public void beginInput()
	{
		//! 入力判定用フラグを落とす
		bTouch = false;
	}
	/// <summary>
	/// 入力終了
	/// </summary>
	public void endInput()
	{
		//! 入力終了時に処理が必要ならばここに
		bTouchOld = bTouch;
	}
	
	/// <summary>
	/// 上向きの力を加える
	/// </summary>
	public void Upforce()
	{
		bTouch = true;		
		if( bTouchOld == false )
		{
			//! 前回タッチしていなくて今回初めてタッチした場合
			time = 1.0f;
			acc = 0;
		}
		else
		{
			time = Time.deltaTime;
			acc = ACCMAX;
		}
		//velocity = 3.0f + v ;/// GameManager.Instance.getScreenH();
	}
	/// <summary>
	/// 下向きの力を加える
	/// </summary>
	public void Downforce()
	{
		bTouch = false;
		if( bTouchOld == true )
		{
			//! 前回タッチしていてここにきた場合はタッチされなくなったということ
			time = 1.0f;
			acc = 0;
		}
		else
		{
			time = Time.deltaTime;
			acc = -ACCMAX;
		}
		//velocity = -3.0f + v ;/// GameManager.Instance.getScreenH() ;
	}
	/// 動きを消す
	public void Stopforce()
	{
		stop = true;
	}
	public void SetFlameAll()
	{
		flameAll = true;
	}
	public bool GetFlameAll()
	{
		return flameAll ;
	}

	public void SetReturnAll()
	{
		ReturnAll = true;
		returnCounter = 0;
	}
	public bool GetReturnAll()
	{
		return ReturnAll ;
	}
	public float GetPlayerY()
	{

		if( plPoint )
		{			
			return plPoint.transform.localPosition.y;
		}
		return 0;
	}	

	public bool GetPlayerLife()
	{
		if(plPoint != null )
		{
			return true;
		}
		return false;
	}


}
