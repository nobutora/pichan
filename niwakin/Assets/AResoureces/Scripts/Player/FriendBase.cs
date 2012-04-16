using UnityEngine;
using System.Collections;

/// <summary>
/// 仲間キャラの行動クラスです。
/// </summary>
public class FriendBase : PiyoBase {
	
	public GameObject mPlayer;
	public Vector3 mOffsetPosition;
	private Vector3 mPosition;
	//private Vector3 mSrcOffsetPosition;	//
	private int mTeamMemberId;
	
	private float mTimeMoveToPosition;	//! 低位置に移動するためのタイマー
	
	private bool mLonely;				//! チームに属していない

	private bool mReturn;
	private float mReturnMoveX;
	private float mReturnMoveY;
	
	private Vector3 myFirstPosition ;
	
	private uint mAttr;					//!　属性フラグ
	private const uint ATTR_LONELY =   0x00000001;		//! はぐれ
	private const uint ATTR_MOVETPOS = 0x00000002;		//! 定位置に移動中
	/// <summary>
	/// はぐれ状態
	/// </summary>
	public bool AtLonely {
		set { mAttr |= ATTR_LONELY; }
		get { return (mAttr & ATTR_LONELY) != 0; }
	}
	public bool AtMoveToPos = false;
	

	

	// Use this for initialization
	void Start () {
		mOffsetPosition = Vector3.zero;
		mPosition = Vector3.zero;
		mTeamMemberId = 0;
		mTimeMoveToPosition = 0;
		//! プレイヤータグを追従するプレイヤーとして登録
		mPlayer = GameObject.FindGameObjectWithTag( "Player" );
		
		//! チーム内の位置を取得
		mOffsetPosition = getTeamPosition();
		transform.localScale = new Vector3( -0.75f, 0.75f, 1.0f );
		
		//! 最初はチームに属していない
		//! はぐれキャラセットアップ
		setupAlone();
	}
	
	/// <summary>
	/// マイフレームの行動
	/// </summary>
	protected override void Move()
	{

		if(PlayerManager.Instance.GetReturnAll())
		{
			if(mLonely == false)
			{
				setReturn();
			}
		}

		if( mLonely ) 
		{
			//! まだチームに属していない
			MoveLonely();
		}
		else
		if( mReturn )
		{
			MoveReturn();
		}
		else
		{	
			//! チームに属している
			MoveTeam();
		}
		//更新
		UpdateTransform();
	}
	
	/// <summary>
	/// チームに属している場合の行動
	/// </summary>
	protected virtual void MoveTeam()
	{
		if( mPlayer )
		{
			if( AtMoveToPos )
			{
				//! 復帰行動


				float reviveTime = 0.4f;
				mTimeMoveToPosition += Time.deltaTime;
				if( mTimeMoveToPosition > reviveTime ) 
				{
					mTimeMoveToPosition = reviveTime;
					AtMoveToPos = false;	
					return ;
				}
				
				//Vector3 vParentPos = mPlayer.transform.localPosition;
				//Vector3 vParentPos = Vector3.Lerp(mPlayer.transform.localPosition , myFirstPosition);				
				Vector3 vParentPos = myFirstPosition;

				Vector3 vTargetPos = mOffsetPosition + mPlayer.transform.localPosition;
				Vector3 vPos = Vector3.Lerp(vParentPos, vTargetPos, mTimeMoveToPosition/reviveTime);
				mPosition = vPos;
			}
			else
			{
				Vector3 vParentPos = mPlayer.transform.localPosition;
				Vector3 vPos = mOffsetPosition;
				
				//
				mPosition = vParentPos + vPos;	
			}
		}

	}
	
	/// <summary>
	/// チームに属していない場合の行動
	/// </summary>
	protected virtual void MoveLonely()
	{
		Vector3 vPos = mPosition;

		float move = 80.0f / 3 * (3 + GameManager.Instance.GameLevel/2);

		vPos.x -= move * Time.deltaTime;

		mPosition = vPos;	
		
		//! 画面外で削除
		if( vPos.x < -50.0f ) {
			DestroyObject( gameObject );	
		}
	}

	protected virtual void MoveReturn()
	{
		Vector3 vPos = mPosition;
		vPos.x += mReturnMoveX;
		vPos.y += mReturnMoveY;

		mPosition = vPos;	
		
		//! 画面外で削除
		if( vPos.y > 750.0f ) {
			//SCOA UP 
			GameManager.Instance.mAddReturn();

			DestroyObject( gameObject );	
		}
	}
	
	/// <summary>
	/// トランスフォームの更新
	/// </summary>
	public void UpdateTransform()
	{
		//スクロール後の座標に変換
		transform.localPosition = ScrollManager.Instance.Translation(mPosition);
	}
	
		/// <summary>
	/// チームに参加します。
	/// </summary>
	public void JoinTeam( Vector3 position)
	{
		myFirstPosition = position;
		mLonely = false;
		tag = Library.FriendTag;			//! タグを変更
		
		//! 向きを右向きに
		Vector3 vScale = transform.localScale;
		vScale.x = -Mathf.Abs(vScale.x);
		transform.localScale = vScale;
		
		//! 定位置へ移動
		AtMoveToPos = true;
		mTimeMoveToPosition = 0;
	}
	
	public void setReturn()
	{
		if(mReturn )
		{
			return;
		}
		GameManager.Instance.delChikin();
		mReturn = true ;
		mLonely = false;
		Sprite2D data = (Sprite2D)GetComponent(typeof(Sprite2D));
		data.fps = 22;
		mReturnMoveX = Random.Range(1.0f, 7.0f );
		mReturnMoveY = Random.Range(1.0f, 7.0f );

	}

	
	/// <summary>
	/// はぐれキャラのセットアップ
	/// </summary>
	private void setupAlone()
	{
		
		mLonely = true;
		mReturn = false;
		tag = Library.LonelyTag;
		
		//! 位置を設定
		Vector3 vPos = new Vector3( GameManager.ScreenSize.x + 60.0f +  Random.Range(-10.0f, 120.0f 
								+ GameManager.Instance.GameLevel * 20.0f ) ,
		                           	Random.Range(32.0f, ScrollManager.Instance.StageSize.y - 32.0f ),
		                           0);
		vPos = TeamManager.Instance.getDirevtPoint(vPos);
		
		mPosition = vPos;
		UpdateTransform();
	}
	
	/// <summary>
	/// チーム内の番号から位置を取得
	/// </summary>
	/// <returns>
	/// A <see cref="Vector3"/>
	/// </returns>
	protected Vector3 getTeamPosition()
	{
		//mTeamMemberId
		Vector3 vPos;
		
		int lv = GameManager.Instance.GameLevel;
		if( lv >= 5)
		{
			lv = 5;
		}

		float xran = 140.0f / 9 * (9 + lv );
		vPos.x = Random.Range( -xran , xran );

		float yran = 40.0f / 9 * (9 + lv );
		vPos.y = Random.Range( -yran , yran );
		if( vPos.y >= 0 ) {
			vPos.y += 30.0f;
		} else {
			vPos.y += -30.0f;
		}
		vPos.z = Random.Range( 0.0f, 20.0f );
		return vPos;
	}
}
