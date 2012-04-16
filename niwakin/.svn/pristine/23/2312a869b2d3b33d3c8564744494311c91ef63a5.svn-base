using UnityEngine;
using System.Collections;

/// <summary>
/// プレイヤーベースクラス
/// 当たり判定と基本の動作を実装します。
/// </summary>
public class PiyoBase : MonoBehaviour {
	
	/// <summary>
	/// ユニットタイプ
	/// </summary>
	public enum UNITTYPE
	{
		OBJECT,			//! 何もしない
		PLAYER,			//! プレイヤーキャラ
		FRIEND,			//! 仲間キャラ
		OPTION,			//! オプション
	}
	private UNITTYPE	mUnitType;			//! 属性
	/// <summary>
	/// ユニットタイプ
	/// </summary>
	public UNITTYPE UnitType 
	{
		get { return mUnitType; }	
	}
	
	/// <summary>
	/// ユニットタイプを設定
	/// </summary>
	/// <param name="type">
	/// A <see cref="UNITTYPE"/>
	/// </param>
	protected void setUnitType( UNITTYPE type )
	{
		mUnitType = type;	
	}
	/// <summary>
	/// にわとりタイプ
	/// </summary>
	public enum PIYOTYPE
	{
		/// <summary>
		/// ピヨ吉
		/// </summary>
		PIYO_WHITE,
		/// <summary>
		/// ピヨ介
		/// </summary>
		PIYO_RED,
		/// <summary>
		/// ピヨ蔵
		/// </summary>
		PIYO_GOLD,
	}
	//焼死
	private bool isFlameDead = false;
	private int flameTime = 0;
	private const int FLAME_MAX_TIME = 40;
	private const int FLAME_MAX_PL_TIME = 110;
	
	private float alphe = 1.0f;
	
	/// <summary>
	/// にわとりを作成
	/// </summary>
	/// <param name="type">
	/// A <see cref="にわとりタイプ"/>
	/// </param>
	/// <returns>
	/// A <see cref="GameObject"/>
	/// </returns>
	public static GameObject Create(PIYOTYPE type)
	{
		string strPath = "";
		
		switch( type )
		{
		case PIYOTYPE.PIYO_WHITE:
			strPath = "Piyokichi";
			break;
		case PIYOTYPE.PIYO_RED:
			strPath = "Piyosuke";
			break;
		case PIYOTYPE.PIYO_GOLD:
			strPath = "Piyozou";
			break;
		}
		
		if ( strPath.Length > 0 )
		{
			return (GameObject)Resources.Load( strPath );
		}
		return null;
	}
	
	
	// Use this for initialization
	void Start () {
	}
	
	// Update is called once per frame
	void Update () {
		
		if(GameManager.Instance.isStop())
		{
			return;
		}


		if( PlayerManager.Instance != null )
		{
			if(PlayerManager.Instance.GetFlameAll())
			{
				if( this.tag == Library.PlayerTag ||
					this.tag == Library.FriendTag )
				{
					FlameChikinSet();
				}
			}
			//kikan		
			if(PlayerManager.Instance.GetReturnAll())
			{
				if(this.isFlameDead)
				{
				}else
				if( this.tag == Library.FriendTag )
				{
					FriendBase friend = (FriendBase)gameObject.GetComponent(typeof(FriendBase));
					friend.setReturn();
				}
			}
		}


		if(isGetFlameDead())
		{
			FlameMove();
			return;
		}
		
		
		Move();
	}
	

	
	/// <summary>
	/// マイフレームの入力更新
	/// </summary>
	protected virtual void Move()
	{
		float yMove = Input.GetAxis ("Mouse Y") * 48.0f;
		Vector3 vPos = transform.localPosition;
		vPos.y = Mathf.Clamp( vPos.y + yMove, 0, GameManager.ScreenSize.y );
		
		float xMove = Input.GetAxis ("Mouse X") * 30.0f;
		vPos.x = Mathf.Clamp( vPos.x + xMove, 0, GameManager.ScreenSize.x );
		
		//
		transform.localPosition = vPos;	
	}



	
	//! 座標を取得
	public Vector3 getPosition()
	{
		return transform.localPosition;

	}
	
	void OnTriggerEnter( Collider col )
	{
		//! 当たった相手がはぐれた仲間の場合
		if( this.tag == Library.PlayerTag ||
			this.tag == Library.FriendTag )
		{

			if( col.tag == Library.LonelyTag )
			{
				if( GameManager.Instance.getChikinOk() )
				{
					//! 仲間に参加
					FriendBase friend = (FriendBase)col.gameObject.GetComponent(typeof(FriendBase));
					friend.JoinTeam(this.transform.localPosition);
					GameManager.Instance.mScoreUp();
					GameManager.Instance.addChikin();
				}
				//col.gameObject.join();
			}
			
		}
		
		bool flame = false;
		
		//! 当たった相手が炎の場合
		if( col.tag == Library.FlameTag &&
			this.tag == Library.FriendTag)
		{
			flame = true;
		}
		
		if(this.tag == Library.PlayerTag ||
			this.tag == Library.FriendTag)
		{
			if( col.tag == Library.EnemyTag )
			{
				flame = true;
			}
		}
		
		if(flame)
		{
			//炎のエフェクトが出てチキンになる
			FlameChikinSet();
		}
		
		
	}
	
	public void FlameChikinSet()
	{
		if(this.isFlameDead)
		{
			return ;
		}
		if( this.tag == Library.PlayerTag )
		{
			PlayerManager.Instance.Stopforce();
			GameObject refObj ;

			refObj = (GameObject)Resources.Load("Effect/chicken_damege");			

			//! 作成
			GameObject obj = (GameObject)Instantiate( refObj );
			GameObject efParent = GameObject.Find( "PlayerManager" );
			obj.transform.parent = efParent.transform ;
			
			Vector3 position = new Vector3( 
				transform.position.x ,
				transform.position.y ,
				1 );
			obj.transform.position = position;

			Vector3 scale = new Vector3( 
				1 ,
				1 ,
				1 );
			obj.transform.localScale = scale;
			
			DestroyObject( gameObject );
		}else
		{			
			this.isFlameDead = true;
			Sprite2D data = (Sprite2D)GetComponent(typeof(Sprite2D));
			data.fps = 22;			
		}
		GameManager.Instance.delChikin();
		if(Library.getSeNum(Library.SE_FIRE) <= 5)
		{
			Library.addSeNum(Library.SE_FIRE, 1);
			GameObject refObj = (GameObject)Resources.Load("Sound/sound_se_Fire");			
			GameObject obj = (GameObject)Instantiate( refObj );
			soundMove sound =(soundMove)obj.GetComponent( typeof(soundMove));
			sound.type = Library.SE_FIRE;
		}
		FlameEffect();
	}
	
	void FlameMove()
	{
				
		int num = 1;
		if( this.tag == Library.PlayerTag )
		{
			num = 3;
		}
		else if( this.tag == Library.FriendTag )
		{
			num = 7;			
		}
		if( flameTime % num == 0)
		{
			//FlameEffect();
		}

		flameTime++;
		

		bool end = false;

		if(this.tag == Library.PlayerTag)
		{
			if( flameTime == (FLAME_MAX_PL_TIME / 2) )
			{
				PlayerManager.Instance.SetFlameAll();
			}else
			if( flameTime >= FLAME_MAX_PL_TIME )
			{
				end = true;
			}
		}else
		{
			if( flameTime >= FLAME_MAX_TIME )
			{	
				end = true;
			}
		}		

		if(end)
		{
			GameObject refObj ;

			if(this.tag == Library.PlayerTag)
			{
				refObj = (GameObject)Resources.Load("Effect/chikin_big");			
			}else
			{
				refObj = (GameObject)Resources.Load("Effect/chikin");			
			}
			//! 作成
			GameObject obj = (GameObject)Instantiate( refObj );
			GameObject efParent = GameObject.Find( "PlayerManager" );
			obj.transform.parent = efParent.transform ;
			
			Vector3 position = new Vector3( 
				transform.position.x ,
				transform.position.y ,
				1 );
			obj.transform.position = position;
			Vector3 scale = new Vector3( 
				transform.localScale.x /5*4,
				transform.localScale.y /5*4,
				1 );
			obj.transform.localScale = scale;
				


			DestroyObject( gameObject );
			return ;
		}


		bool alpheEvent = false;
		if(this.tag == Library.PlayerTag)
		{
			if( flameTime >= (FLAME_MAX_PL_TIME / 2) )
			{
				alpheEvent = true ;
			}
		}else
		{
			if( flameTime >= FLAME_MAX_TIME / 2 )
			{	
				alpheEvent = true ;
			}
		}
		if(alpheEvent)
		{
			alpheChange(alphe);
			alphe -= 0.05f;
			if(alphe <= 0.3f)
			{
				alphe = 0.3f;
			}
		}
		
		
		//徐々に下降
		Vector3 vPos = transform.localPosition;
		vPos.y = vPos.y - 0.5f;
		vPos.x = vPos.x - 0.5f;		
		transform.localPosition = vPos;	

	}
	
	void FlameEffect()
	{
		GameObject refObj = (GameObject)Resources.Load("Effect/Flame_Chikin");			
		//! 作成
		GameObject obj = (GameObject)Instantiate( refObj );

		GameObject efParent = GameObject.Find( "PlayerManager" );
		obj.transform.parent = efParent.transform ;

		int ranX = Random.Range(-20 , 20);
		int ranY = Random.Range(-20 , 20);
		Vector3 position = new Vector3( 
			transform.position.x + ranX,
			transform.position.y + ranY,
			1 );
		obj.transform.position = position;
		
		//ranX = Random.Range(-15 , 15);
		//ranY = Random.Range(-15 , 15);
		//Vector3 scale = new Vector3( 
		//	obj.transform.localScale.x + (float)ranX / 100,
		//	obj.transform.localScale.y + (float)ranY / 100,
		//	1 );
		//obj.transform.localScale = scale;

		


	}
	
	bool isGetFlameDead()
	{
		return this.isFlameDead;
	}
	
	void alpheChange( float alphe )
	{

		Material mat = this.renderer.material;
        //mat.shader = Shader.Find("Transparent/Diffuse");

        Color color = mat.color;
        color.r = 1.0f;
        color.b = 1.0f;
        color.g = 1.0f;
        color.a = alphe ;
        mat.color = color;
	}
}
