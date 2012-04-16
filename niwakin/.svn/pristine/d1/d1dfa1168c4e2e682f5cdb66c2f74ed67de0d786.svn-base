using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class TeamManager : MonoBehaviour {
	
	private static TeamManager mInstance;	//! 自分自身
	private List<PiyoBase.PIYOTYPE>	mStandbyFriends;	//! まだ登録されていない仲間
	private List<FriendBase>	mFriends;			//! 登録済みの仲間
	private int	mFriendCount;					//! 仲間の数
	
	private bool mShowFriend;					//! 仲間が表示されるフラグ
	
	private Vector3 DirectPoint;
	private bool	isDirect = false;
	private float DirectCount = 0;

	/// <summary>
	/// 自分のインスタンスを返します
	/// </summary>
	public static TeamManager Instance
	{
		get { return mInstance; }	
	}
	
	// Use this for initialization
	void Start () {
		mInstance = this;
		
		mStandbyFriends = new List<PiyoBase.PIYOTYPE>(10);
		mFriends = new List<FriendBase>(30);
		mFriendCount = 0;
		mShowFriend = false;
	}
	
	// Update is called once per frame
	void Update () {
		
		if(isDirect)
		{
			DirectCount += Time.deltaTime;
			if( DirectCount >= 2.0f )
			{
				isDirect = false;
			}
			return;
		}

		if( mShowFriend )
		{
			Ready();
			mShowFriend = false;
		}
		else
		{
		}
	}
	
	/// <summary>
	/// フレンドをチームに追加
	/// </summary>
	/// <param name="obj">
	/// A <see cref="追加するフレンド"/>
	/// </param>
	public void addFriend( GameObject obj )
	{
		FriendBase fr = (FriendBase)obj.GetComponent(typeof(FriendBase));
		
		//! PiyoBase系のオブジェクトのみ
		mFriends.Add( fr );
	}
	
	/// <summary>
	/// スタンバイを画面に表示
	/// </summary>
	public void Ready()
	{

		int baseTime = Random.Range( 1 , 1 + GameManager.Instance.GameLevel / 2 );

		for(int i = 0; i < baseTime; i++)
		{
			foreach( PiyoBase.PIYOTYPE type in mStandbyFriends )
			{
				GameObject pref = PiyoBase.Create( type );
				GameObject refObj = (GameObject)Instantiate(pref);
				refObj.transform.parent = gameObject.transform;
				PlayerComponentController ctrl = (PlayerComponentController)refObj.GetComponent(typeof(PlayerComponentController));
				ctrl.UnitType = PiyoBase.UNITTYPE.FRIEND;
				this.addFriend( refObj );
				next();
			}
		}
		//! 配列のクリア
		mStandbyFriends.Clear();
	}
	

	
	public void DirectCreate( Vector3 point )
	{
		DirectPoint = point ;
		isDirect = true;
		DirectCount = 0;
		//foreach( PiyoBase.PIYOTYPE type in mStandbyFriends )
		{
			GameObject pref = PiyoBase.Create( PiyoBase.PIYOTYPE.PIYO_WHITE );
			GameObject refObj = (GameObject)Instantiate(pref);
			refObj.transform.parent = gameObject.transform;
			PlayerComponentController ctrl = (PlayerComponentController)refObj.GetComponent(typeof(PlayerComponentController));
			ctrl.UnitType = PiyoBase.UNITTYPE.FRIEND;
			this.addFriend( refObj );
			next();
		}
		//! 配列のクリア
		//mStandbyFriends.Clear();
	}
	public Vector3 getDirevtPoint(Vector3 retPoint)
	{
		if(isDirect)
		{		
			float ranX = DirectPoint.x + Random.Range(-20 , 20);
			float ranY = DirectPoint.y + Random.Range(-20 , 20);
			Vector3 position = new Vector3(
				ranX ,
				ranY ,
				1 );			
			retPoint = DirectPoint;
		}
		return retPoint;
	}

	

	/// <summary>
	/// フレンドの作成
	/// </summary>
	/// <param name="type">
	/// A <see cref="PiyoBase.UNITTYPE"/>
	/// </param>
	/// <param name="num">
	/// A <see cref="System.Int32"/>
	/// </param>
	public void addReadyFriends( PiyoBase.PIYOTYPE type, int num )
	{
		for( int i=0 ; i<num ; i++ )
		{
			mStandbyFriends.Add( type );
			//mFriends.Add( PiyoBase.Create( type ) );
		}
		
	}
	
	/// <summary>
	/// 次の仲間を設定
	/// </summary>
	public void setupNextFriends()
	{
		addReadyFriends(PiyoBase.PIYOTYPE.PIYO_WHITE, 1);
		mShowFriend = true;
	}
	
	/// <summary>
	/// 次のターンへ
	/// </summary>
	public void next()
	{
		
	}
}
