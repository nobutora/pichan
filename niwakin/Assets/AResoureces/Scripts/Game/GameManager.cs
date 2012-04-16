using UnityEngine;
using System.Collections;

public class GameManager : MonoBehaviour {
	
	//! インスタンス
	private static GameManager thisInstance;	//! シングルトンであるべき
	/// <summary>
	/// 自分自身を返します,かならずこのクラスはシングルトンにしてください。
	/// </summary>



	private float counter = 0;
	private ATwitter mTwitter;
	
	public const float FIRST_COUNTER = 2.5f;
	

	public int maneger_Type ;

	public const int MANEGER_GAME  = 0;
	public const int MANEGER_TITLE = 1;

	public static GameManager Instance
	{
		get {
			return thisInstance;	
		}
	}
	/// <summary>
	/// ゲームマネージャのフロー
	/// </summary>
	public enum FLOW
	{
		WAITPLAY,			//! ゲーム開始待ち
		PLAY,				//! ゲームプレイ中
		PLAY_TIME,

		GAMEOVER,			//! ゲームオーバー

	}
	private FLOW mFlow;
	/// <summary>
	/// ゲームオーバーか
	/// </summary>
	/// <returns>
	/// A <see cref="System.Boolean"/>
	/// </returns>
	public bool isGameOver()
	{
		if( mFlow == GameManager.FLOW.GAMEOVER )
		{
			return true;
		}
		return false;
	}

	public bool isStop()
	{
		if( mFlow == GameManager.FLOW.PLAY_TIME
			//||
			//mFlow == GameManager.FLOW.GAMEOVER 
			)
		{
			return true;
		}
		return false;
	}

	public bool isTime()
	{
		if( mFlow == GameManager.FLOW.PLAY_TIME )
		{
			return true;
		}
		return false;
	}

	public void setTime()
	{
		mFlow = GameManager.FLOW.PLAY_TIME;
	}

	public bool isPlaying()
	{
		if( mFlow == GameManager.FLOW.PLAY )
		{
			return true;
		}
		return false;
	}

	public void setPlay()
	{
		mFlow = GameManager.FLOW.PLAY ;
	}
	

	public void setGameOver()
	{
		mFlow = GameManager.FLOW.GAMEOVER ;
	}


	//! 定数系
	/// <summary>
	/// スクリーンサイズ
	/// </summary>
	public static Vector2 ScreenSize {

		get { 
			
			//return new Vector2(1024,768); 	

				//return new Vector2(480,320); 								
				//if(SystemInfo.operatingSystem.Contains("iPad")){
				//	return new Vector2(1024,768); 					
				//}else
				//if(SystemInfo.operatingSystem.Contains("iPhone")){
				//	return new Vector2(480,320); 					
				//}

				//if(Application.platform == RuntimePlatform.IPhonePlayer)
				//{
				//	return new Vector2(480,320); 
				//}else
				{
					return new Vector2(800,480); 
				}
			}	
	}


	public static Vector2 ScreenSizeGui {

		get { 
				{
					return new Vector2(800,480); 
				}
			}	
	}
	
	
	public static Vector2 ScreenSizeVirtual {

		get { 
			
				//return new Vector2(1024,768); 	

				//return new Vector2(480,320); 								
				if(SystemInfo.operatingSystem.Contains("iPad")){
					return new Vector2(1024,768); 					
				}else
				if(SystemInfo.operatingSystem.Contains("iPhone")){
					return new Vector2(480,320); 					
				}else
				{
					return new Vector2(800,480); 
				}
			}	
	}


	//! ゲーム用パラメータ
	
	private bool mFirstScore = false;
	private int mScore;
	private int mDead;
	private int mReturn;
	public const int FREND_GET = 10;
	public const int FREND_RETURN = 1;

	private float PointWaitTime = 0;
	private int pointWait = 0;
	public const int POINT_WAIT_MAX = 2;
	
	private int EffectNum = 0;
	private const int EFFECT_MAX = 50;

	private int ChikinNum = 0;
	private const int CHIKIN_MAX = 50;
	
	private float gameOvertimer = 0.0f;
	private float GAMEOVER_TIME_MAX = 6.0f;

	public void addEffect()
	{
		EffectNum++;
	}
	public void delEffect()
	{
		EffectNum--;
	}
	public bool getEffectOk()
	{
		if(EffectNum >= EFFECT_MAX)
		{
			return false;
		}
		return true;
	}

	public void addChikin()
	{
		ChikinNum++;
	}
	public void delChikin()
	{
		ChikinNum--;
		mDeadUp();
	}
	public bool getChikinOk()
	{
		if(ChikinNum >= CHIKIN_MAX)
		{
			return false;
		}
		return true;
	}


	public void mScoreUp()
	{
		//mScore += FREND_GET;
		//pointEffectSetting(FREND_GET);
		if(Library.getSeNum(Library.SE_VOICE) <= 3)
		{
			Library.addSeNum(Library.SE_VOICE, 1);
			GameObject refObj = (GameObject)Resources.Load("Sound/sound_se_Voice");			
			GameObject obj = (GameObject)Instantiate( refObj );
			soundMove sound =(soundMove)obj.GetComponent( typeof(soundMove));
			sound.type = Library.SE_VOICE;
		}
	}
	public void mDeadUp()
	{
		mDead++;
	}
	public void mAddReturn()
	{
		mReturn++;
		mScore += FREND_RETURN;

		if(Library.getSeNum(Library.SE_POINT_UP) <= 3)
		{
			Library.addSeNum(Library.SE_POINT_UP, 1);
			GameObject refObj = (GameObject)Resources.Load("Sound/sound_se_getPoint");			
			GameObject obj = (GameObject)Instantiate( refObj );
			soundMove sound =(soundMove)obj.GetComponent( typeof(soundMove));
			sound.type = Library.SE_POINT_UP;
		}
		//pointEffectSetting(FREND_RETURN);
	}

	private void pointEffectSetting(int score)
	{
		PointWaitTime = 1.0f;
	    pointWait += score;		
	}

	private void pointEffectCounter()
	{
		if( PointWaitTime >= 1.0f)
		{
			PointWaitTime += Time.deltaTime;
			if(PointWaitTime >= POINT_WAIT_MAX)
			{
				PointEffectCreate();
			}
		}
	}

	private void PointEffectCreate()
	{

		GameObject player = GameObject.FindGameObjectWithTag( "Player" );

		if( player == null)
		{
			return;
		}

		GameObject refObj = (GameObject)Resources.Load("Effect/point_Num");
		
		GameObject obj = (GameObject)Instantiate( refObj );
		
		if( mFirstScore == false)
		{
			GameObject obj2 = (GameObject)Instantiate( refObj );
			mFirstScore = true;
			Vector3 position2 = new Vector3(
			-3000,
			-3000,
			0 );

			obj2.transform.position = position2;				
		}
	
		GameObject efParent = GameObject.Find( "PlayerManager" );
		obj.transform.parent = efParent.transform ;
		


		Vector3 position = new Vector3(
			player.transform.position.x + Random.Range( -10.0f, 10.0f ),
			player.transform.position.y + Random.Range( -10.0f, 10.0f ),
			0 );

		obj.transform.position = position;	


		pointget point = (pointget)obj.gameObject.GetComponent(typeof(pointget));

		point.showScore = pointWait;

		PointWaitTime = 0;
	    pointWait = 0;

	
	}
	public int getScore()
	{
		return mScore;
	}
	public int getDead()
	{
		return mDead;
	}
	public int getReturn()
	{
		return mReturn;
	}	
	
	public float getScreenW()
	{
		return Screen.width / GameManager.ScreenSize.x ;
	}

	public float getScreenH()
	{
		return Screen.height / GameManager.ScreenSize.y ;
	}

	public float getScreenW_GUI()
	{
		return Screen.width / GameManager.ScreenSizeGui.x ;
	}

	public float getScreenH_GUI()
	{
		return Screen.height / GameManager.ScreenSizeGui.y ;
	}

	/// <summary>
	/// 現在のゲームレベル
	/// </summary>
	public const int LV_MAX = 30;
	public int GameLevel
	{

		get {


			int nowLv = 0;
			int score = getScore();
			if( score >= 25 )
			{
				int lvup = 0;
				int count = score;
				while(count > 0)
				{
					if(count >= 15)
					{
						lvup++;
					}
					count -= 15 ;
				}
				nowLv = 5 + lvup ;
			}else
			if( score >  20 )
			{
				nowLv = 4 ;
			}else
			if( score >  15 )
			{
				nowLv = 3 ;
			}else			
			if( score >  10 )
			{
				nowLv = 2 ;
			}else
			if( score >  5 )
			{
				nowLv = 1 ;
			}

			int lv = Library.getLvSave( )* 3;
			if( lv >= nowLv )
			{
				nowLv = lv;
			}

			if( nowLv >= LV_MAX )
			{
				nowLv = LV_MAX ;
			}
			//nowLv = LV_MAX ;
			return nowLv;	
		}
	}
	
	void Awake() {
		gameObject.AddComponent("ATwitter");	
	}
	
	// Use this for initialization
	void Start () {
		thisInstance = this;
		mTwitter = (ATwitter)GetComponent(typeof(ATwitter));	//! ツイッター
		
		mFlow = GameManager.FLOW.WAITPLAY;
		mScore = 0;
		mReturn = 0;
		
		Application.targetFrameRate = 55;

		//READY EF
		if(maneger_Type == MANEGER_GAME  )
		{
			GameObject  refObj = (GameObject)Resources.Load("Effect/READY2");
			GameObject obj = (GameObject)Instantiate( refObj );
			GameObject viewobj = GameObject.Find( "2D" );	
			obj.transform.parent = viewobj.transform;
			GameObject bgmobj = GameObject.Find("Sound_BgmObj");
			bgmobj.BroadcastMessage("StartBgm",SendMessageOptions.DontRequireReceiver);
		}
		//150 130
		
	


		
	
	}
	
	// Update is called once per frame
	void Update () {

		

		if(maneger_Type == MANEGER_GAME  )
		{
			switch(mFlow)
			{
				case FLOW.WAITPLAY:
				{
					counter += Time.deltaTime;
					if( counter >= FIRST_COUNTER )
					{
						mFlow = FLOW.PLAY;
						//GO eF
					}
					break;		
				}
				case FLOW.PLAY:
				{
					if(PlayerManager.Instance.GetPlayerLife() == false)
					{
						if( gameOvertimer == 0 )
						{
							GameObject bgmobj = GameObject.Find("Sound_BgmObj");
							bgmobj.BroadcastMessage("StopBgm",SendMessageOptions.DontRequireReceiver);
						}
						gameOvertimer+=Time.deltaTime;
						if( gameOvertimer >= GAMEOVER_TIME_MAX )
						{
							GameObject refObj = (GameObject)Resources.Load("Effect/Black");
							GameObject gmo = (GameObject)Instantiate( refObj );
							BlackBack data = (BlackBack)gmo.gameObject.GetComponent(typeof(BlackBack));
							data.type = BlackBack.TYPE_GBO;	
						
							GameObject viewobj = GameObject.Find( "2D" );
	
							data.transform.parent = viewobj.transform;
						
						
							setGameOver();
							Library.gameSave( mScore );

						}
					}
					break;
				}
				case FLOW.GAMEOVER:
				{
					break;		
				}
			}
	
	
			pointEffectCounter();
		}else
		if(maneger_Type == MANEGER_TITLE )
		{
			
		}

		
		soundMainus();

	}
	private float counterSound = 0;
	private void soundMainus()
	{
		counterSound += Time.deltaTime;
		if(counterSound >= 1.0f)
		{
			for(int i = 0; i < Library.MAX_SE; i++)		
			{
				if(Library.getSeNum(i) >= 1)
				{
					Library.addSeNum(i, -1);
				}		
			}
			counterSound = 0;
		}
	}

	/// <summary>
	/// 次のトラップを設定する
	/// </summary>
	public void setNextTrap()
	{
		EnemyManager man = EnemyManager.Instance;
		if( man )
		{
			man.setupNextTraps();
		}
	}
	
	/// <summary>
	/// 次の仲間を設定する
	/// </summary>
	public void setNextFriend()
	{
		TeamManager man = TeamManager.Instance;
		if( man )
		{
			man.setupNextFriends();
		}
	}
	
	/// <summary>
	/// プレイヤーの入力を開始する
	/// </summary>
	public void beginPlayerInput()
	{
		PlayerManager.Instance.beginInput();
	}
	/// <summary>
	/// プレイヤーの入力を終了する
	/// </summary>
	public void endPlayerInput()
	{
		PlayerManager.Instance.endInput();
	}
	/// <summary>
	/// プレイヤーに上向きの力を与える
	/// </summary>
	public void UpforceToPlayer()
	{
		PlayerManager.Instance.Upforce();
	}
	/// <summary>
	/// プレイヤーに下向きの力を与える
	/// </summary>
	public void DownforceToPlayer()
	{
		PlayerManager.Instance.Downforce();
	}
	
	/// <summary>
	/// ゲームの記録をツイートする
	/// </summary>
	public void TweetGameRecord()
	{
		string text = 
			"ニワトリキングダムで鶏を" + mScore + "匹救出した！ http://fagstudio.com/ #niwakin";
		mTwitter.tweet(text);
	}
}


