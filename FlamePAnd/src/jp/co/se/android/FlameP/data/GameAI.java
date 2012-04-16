
package jp.co.se.android.FlameP.data;


import jp.co.se.android.FlameP.Main.*;
import jp.co.se.android.FlameP.lib.Library;
import jp.co.se.android.FlameP.lib.ShopData;
import jp.co.se.android.FlameP.draw.*;
import jp.co.se.android.FlameP.scene.Play;



/**
 * MainCanvas
 * 
 */
public class GameAI
{
	protected MainData	pAm = null;
	final int COUNT_LVUP_VAL = 10;
	
	final int MAX_ENEMY = 50;
	
	final int MAX_LV = 99;
	
	Enemy EnemyData[] = new Enemy[MAX_ENEMY];
	
	int GameCount = 0;
	int GameLv    = 0;
	int GameCountLvUp = 0;
	
	int m_i_nowStage = 0;
	
	public static final int STAGE_TITLE = 0;
	public static final int STAGE_1 = 1;
	public static final int STAGE_2 = 2;
	public static final int STAGE_MAX = 3;
	
	
	
	final int GAME_COUNT_NEXT = 150;
	
	 public GameAI(MainData data)
	 {
		 
		 pAm = data;
		 //this.init();
		 initEnemyCreate();
	 }
	 public void dispose()
	 {
		 this.release();
	 }
	 
	 
	 //
	 public void init()
	 {	 
//		 for(int i = 0; i < MAX_ENEMY ; i++)
//		 {
//			 Library.TraseMsg("i"+i);
//			 EnemyData[i] = new Enemy();
//		 }
		
		 initDestroy();
		 
		 chain = 0;
		 chainBournus = 0;
		 GameCount = 0;
		 GameLv = 0;
		 this.m_i_nowStage = 0;
	 }
	 
	 private void release()
	 {	 
		 for(int i = 0; i < MAX_ENEMY; i++)
		 {
			 EnemyData[i].dispose(); 
		 }
	 }
	 
	 public void AllNew()
	 { 
		 //Library.TraseMsg("AllNew");
		 for(int i = 0; i < MAX_ENEMY;i++)
		 {
			 EnemyData[i] = new Enemy();
		 }
	 }
	 
	 public int move( boolean createOff , int condision , boolean bom)
	 {		 
		 int lost = 0;
		 try
		 {
			 int ret = 0;
			 
			 int life = 0;
			 for(int i = 0; i < MAX_ENEMY; i++)
			 {
				if( EnemyData[i].getNoLife() == false)
				{
					life++;
				}
			 }
			 GameCount++;
			 GameCountLvUp++;
			 if(GameCountLvUp >= GAME_COUNT_NEXT)
			 {
				 GameLv++;
				 if(GameLv >= MAX_LV)
				 {
					 GameLv = MAX_LV;
				 }
				 UI.debugLv = GameLv;
				 GameCount = 0;		
				 GameCountLvUp = 0;
			 }
			 
			 int interVal = 15 - GameLv / 12;
			 
			 if(interVal <= 10)
			 {
				 interVal = 10;
			 }
			 
			 if(MainData.DEBUG_MODE)
			 {
				 GameLv = 50;
			 }
			 
			 if(bom == false && GameCount % interVal == 0)
			 {
				 int enemyNum = 5 + GameLv / 5;
				 //Library.TraseMsg("life"+life);
				 if(createOff == false &&
						 life  <= enemyNum )
				 {
					 //if(Library.random.nextInt( 3 ) == 0 )
					 {
						 int createNum = 0;	
						 for(int i = 0; i < MAX_ENEMY; i++)
						 {
							if( EnemyData[i].getLife() == false)
							{	
								int type = -1;
								
								type = stageEnemyCreate( this.m_i_nowStage , GameLv , condision );
								
								int big = EnemyBig( this.m_i_nowStage , GameLv , type );
								
								if(type != -1 )
								{
									if(Stage_EnemyCounter[this.m_i_nowStage][type] >= 1)
									{
										countCreateStop[type] = Stage_EnemyCounter[this.m_i_nowStage][type];
									}
									EnemyData[i].create( type , GameLv , big );
									life++;
								}
								if(life  <= enemyNum )
								{
									createNum++;
									if(createNum >= 3 + GameLv / 13)
									{
										break;
									}
								}else
								{
									break;
								}
							}
						 }		
					 }
					// Library.TraseMsg("life"+life);
				 }
			 }
			 //=============================
			 
			
			 for(int i = 0; i < MAX_ENEMY; i++)
			 {
				if( EnemyData[i].getLife() )
				{				
					//Library.TraseMsg("i"+i);
					if(bom)
					{
						if(EnemyData[i].enemy_Bomb)
						{
							ret = EnemyData[i].move(0);
						}
					}else
					{
						ret = EnemyData[i].move(0);
						if(ret == 2)
						{
							lost = 2;
						}
					}
	//				else
	//				if(ret == -1)
	//				{
	//					lost = -1;
	//				}
				}
			 }
		 }catch (Exception e) {
			// TODO: handle exception
			 Library.showDebugDialog(pAm.getActivity(), "ERR GameAi move " );
		}
		 
		 return lost;
	 }

	 private static final int ENEMY_LV_RAN = 10;
	 private int Stage_EnemyOk[][] = new int[STAGE_MAX][Enemy.ENEMY_TYPE_MAX];
	 
	 private int Stage_EnemyRand[][][] = new int[STAGE_MAX][Enemy.ENEMY_TYPE_MAX][ENEMY_LV_RAN];
	 private int Stage_EnemyLv[][][] = new int[STAGE_MAX][Enemy.ENEMY_TYPE_MAX][ENEMY_LV_RAN];
	 
	 private int Stage_EnemyBig[][][] = new int[STAGE_MAX][Enemy.ENEMY_TYPE_MAX][ENEMY_LV_RAN];
	 private int Stage_EnemyBigLv[][][] = new int[STAGE_MAX][Enemy.ENEMY_TYPE_MAX][ENEMY_LV_RAN];
	 private int Stage_EnemyBigRand[][][] = new int[STAGE_MAX][Enemy.ENEMY_TYPE_MAX][ENEMY_LV_RAN];
	 
	 //一回出現したら一定買うと出ない
	 private int Stage_EnemyCounter[][] = new int[STAGE_MAX][Enemy.ENEMY_TYPE_MAX];

	 private  void initEnemyCreate()
	 {
		 //★タイトルお試しステージ
		 int nowStageInit = STAGE_TITLE;
		 int nowType = 0;
		 int lv[] = new int[10];
		 int randam[] = new int[10];

		 int big[] = new int[10];
		 int biglv[] = new int[10];
		 int bigrandam[] = new int[10];

		 
		 //鶏
		 nowType = Enemy.ENEMY_CHIKIN;
		 Stage_EnemyOk[nowStageInit][nowType]  = 1;
		
		 lv[0] = 0; 
		 randam[0] = 20;
		 setRandamData( nowStageInit , nowType , lv , randam , 1 );
		 
		 biglv[0] = 0; 
		 big[0] = 5;
		 bigrandam[0] = 22;
		 setBigData(nowStageInit , nowType , big , biglv , bigrandam , 1 );
		 
		 Stage_EnemyCounter[nowStageInit][nowType]= 0;
		 //カラス
		 nowType = Enemy.ENEMY_CARASU;
		 Stage_EnemyOk[nowStageInit][nowType]  = 1;

		 lv[0] = 0;
		 randam[0] = 10;
		 setRandamData( nowStageInit , nowType , lv , randam , 1 );

		 biglv[0] = 0; 
		 big[0] = 5;
		 bigrandam[0] = 60;
		 setBigData(nowStageInit , nowType , big , biglv , bigrandam , 1 );
		 
		 Stage_EnemyCounter[nowStageInit][nowType]= 0;
		 //鯨
		 nowType = Enemy.ENEMY_CARASU;
		 Stage_EnemyOk[nowStageInit][nowType]  = 1;		 
		 lv[0] = 0;
		 randam[0] = 10;
		 setRandamData( nowStageInit , nowType , lv , randam , 1 );

		 biglv[0] = 0; 
		 big[0] = 10;
		 bigrandam[0] = 40;
		 setBigData(nowStageInit , nowType , big , biglv , bigrandam , 1 );

		 Stage_EnemyCounter[nowStageInit][nowType]= 0;
		 
		 
		 //★ステージ1
		 nowStageInit = STAGE_1;
		 //鶏
		 nowType = Enemy.ENEMY_CHIKIN;
		 Stage_EnemyOk[nowStageInit][nowType]  = 1;
		 
		 lv[0] = 0; lv[1] = 5; lv[2] = 10;
		 randam[0] = 20; randam[1] = 20; randam[2] = 20;
		 setRandamData( nowStageInit , nowType , lv , randam , 3 );
		 
		 biglv[0] = 40;biglv[1] = 30;biglv[2] = 20; biglv[3] = 15; biglv[4] = 10; biglv[5] = 5; biglv[6] = 0; 
		 big[0] = 30;big[1] = 25;big[2] = 22;big[3] = 20;big[4] = 15;big[5] = 10;
		 big[6] = 5;
		 //big[6] = 15;
		 bigrandam[0] = 15;bigrandam[1] = 20;bigrandam[2] = 30;bigrandam[3] = 35;bigrandam[4] = 40;bigrandam[5] = 45;
		 bigrandam[6] = 50;
		 //bigrandam[6] = 1;
		 setBigData(nowStageInit , nowType , big , biglv , bigrandam , 7 );

		 
		 Stage_EnemyCounter[nowStageInit][nowType]= 0;
		 //カラス
		 nowType = Enemy.ENEMY_CARASU;
		 Stage_EnemyOk[nowStageInit][nowType]  = 1;
		 lv[0] = 0; lv[1] = 6; lv[2] = 12;
		 randam[0] = 10; randam[1] = 10; randam[2] = 15;
		 setRandamData( nowStageInit , nowType , lv , randam , 3 );

		 biglv[0] = 40; biglv[1] = 30; biglv[2] = 0; 
		 big[0] = 10;big[1] = 7;big[2] = 5;
		 bigrandam[0] = 45;bigrandam[1] = 50;bigrandam[2] = 60;
		 
		 setBigData(nowStageInit , nowType , big , biglv , bigrandam , 3 );

		 Stage_EnemyCounter[nowStageInit][nowType]= 0;
		 //鯨
		 nowType = Enemy.ENEMY_FISH;
		 Stage_EnemyOk[nowStageInit][nowType]  = 1;
		 lv[0] = 3; lv[1] = 7; lv[2] = 14;
		 randam[0] = 8; randam[1] = 9; randam[2] = 11;
		 setRandamData( nowStageInit , nowType , lv , randam , 3 );
		 
		 biglv[0] = 50;biglv[1] = 40;biglv[2] = 30; biglv[3] = 25; biglv[4] = 15; biglv[5] = 8; biglv[6] = 0; 
		 big[0] = 30;big[1] = 25;big[2] = 22;big[3] = 20;big[4] = 15;big[5] = 10;big[6] = 5;
		 bigrandam[0] = 25;bigrandam[1] = 30;bigrandam[2] = 40;bigrandam[3] = 45;bigrandam[4] = 50;bigrandam[5] = 55;
		 bigrandam[6] = 60;
		 
		 setBigData(nowStageInit , nowType , big , biglv , bigrandam , 7 );
		 Stage_EnemyCounter[nowStageInit][nowType]= 2;
		 //カラス2
		 nowType = Enemy.ENEMY_CARASU2;
		 Stage_EnemyOk[nowStageInit][nowType]  = 1;
		 lv[0] = 4; lv[1] = 8; lv[2] = 16; lv[3] = 0;
		 randam[0] = 6; randam[1] = 8; randam[2] = 10; randam[3] = 14;
		 setRandamData( nowStageInit , nowType , lv , randam , 4 );		 
		 
		 biglv[0] = 40; biglv[1] = 30; biglv[2] = 0; 
		 big[0] = 10;big[1] = 7;big[2] = 5;
		 bigrandam[0] = 25;bigrandam[1] = 30;bigrandam[2] = 45;
		 
		 setBigData(nowStageInit , nowType , big , biglv , bigrandam , 3 );
		 Stage_EnemyCounter[nowStageInit][nowType]= 5;

		 //クリムゾン
		 nowType = Enemy.ENEMY_CHIKIN2;
		 Stage_EnemyOk[nowStageInit][nowType]  = 1;
		 
		 lv[0] = 12; lv[1] = 30; lv[2] = 45;
		 randam[0] = 1; randam[1] = 1; randam[2] = 1;
		 setRandamData( nowStageInit , nowType , lv , randam , 3 );
		 
		 biglv[0] = 50; biglv[1] = 40; biglv[2] = 0; 
		 big[0] = 15;big[1] = 10;big[2] = 5;
		 bigrandam[0] = 3;bigrandam[1] =10;bigrandam[2] = 20;
		 
		 setBigData(nowStageInit , nowType , big , biglv , bigrandam , 3 );
		 Stage_EnemyCounter[nowStageInit][nowType]= 100;
		 

		 //ゴールデン
		 nowType = Enemy.ENEMY_CHIKIN3;
		 Stage_EnemyOk[nowStageInit][nowType]  = 1;
		 
		 lv[0] = 30; lv[1] = 40; lv[2] = 50;
		 randam[0] = 1; randam[1] = 2; randam[2] = 3;
		 setRandamData( nowStageInit , nowType , lv , randam , 3 );
		 
		 biglv[0] = 50; biglv[1] = 40; biglv[2] = 0; 
		 big[0] = 15;big[1] = 10;big[2] = 5;
		 bigrandam[0] = 3;bigrandam[1] =10;bigrandam[2] = 20;
		 
		 setBigData(nowStageInit , nowType , big , biglv , bigrandam , 3 );
		 Stage_EnemyCounter[nowStageInit][nowType]= 50;

		 
		 //ミサイル１
		 nowType = Enemy.ENEMY_MISAIL;
		 Stage_EnemyOk[nowStageInit][nowType]  = 1;
		 		 
		 lv[0] = 1; lv[1] = 8; lv[2] = 17; lv[3] = 23; lv[4] = 28; lv[5] = 34;
		 randam[0] = 3; randam[1] = 7; randam[2] = 8; randam[3] = 9; randam[4] = 10; randam[5] = 11;
		 setRandamData( nowStageInit , nowType , lv , randam , 6 );
		 
		 biglv[0] = 30; biglv[1] = 20; biglv[2] = 0; 
		 big[0] = 15;big[1] = 10;big[2] = 5;
		 bigrandam[0] = 20;bigrandam[1] = 25;bigrandam[2] = 35;

		 setBigData(nowStageInit , nowType , big , biglv , bigrandam , 3 );
		 Stage_EnemyCounter[nowStageInit][nowType]= 0;
		 
		 //ミサイル2
		 nowType = Enemy.ENEMY_AT_MISAIL;
		 Stage_EnemyOk[nowStageInit][nowType]  = 1;

		 lv[0] = 8; lv[1] = 18; lv[2] = 28; lv[3] = 32;
		 randam[0] = 2; randam[1] = 3; randam[2] = 4; randam[3] = 5;
		 setRandamData( nowStageInit , nowType , lv , randam , 4 );
		 
		 biglv[0] = 40; biglv[1] = 30; biglv[2] = 0; 
		 big[0] = 15;big[1] = 10;big[2] = 5;
		 bigrandam[0] = 15;bigrandam[1] = 20;bigrandam[2] = 25;
		 
		 setBigData(nowStageInit , nowType , big , biglv , bigrandam , 3 );
		 Stage_EnemyCounter[nowStageInit][nowType]= 0;
	 }
	 
	 private void setRandamData(int nowStage , int nowType , int lv[] , int randam[] ,int len)
	 {
		 for(int i = 0; i < ENEMY_LV_RAN; i++)
		 {
			 if( i >= len)
			 {
				 Stage_EnemyRand[nowStage][nowType][i]= -1;
				 Stage_EnemyLv[nowStage][nowType][i]= -1;
			 }else
			 {
				 Stage_EnemyRand[nowStage][nowType][i]= randam[i];
				 Stage_EnemyLv[nowStage][nowType][i]= lv[i];	
			 }
		 }
	 }
	 
	 private void setBigData(int nowStage , int nowType , int big[], int lv[] , int randam[] ,int len)
	 {
		 for(int i = 0; i < ENEMY_LV_RAN; i++)
		 {
			 if( i >= len)
			 {
				 Stage_EnemyBig[nowStage][nowType][i] = -1;
				 Stage_EnemyBigLv[nowStage][nowType][i] = -1;
				 Stage_EnemyBigRand[nowStage][nowType][i] = -1;
			 }else
			 {			 
				 Stage_EnemyBig[nowStage][nowType][i] = big[i];
				 Stage_EnemyBigLv[nowStage][nowType][i] = lv[i];
				 Stage_EnemyBigRand[nowStage][nowType][i] = randam[i];
			 }
		 }
	 }
	 private int countCreateStop[] = new int [Enemy.ENEMY_TYPE_MAX];
	 private static final int NO_CREATE_TIME = 100;
	 private int stageEnemyCreate( int stage, int lv,int condision )
	 {
		 int randamMax = 0;
		 int type[] = new int [Enemy.ENEMY_TYPE_MAX];
		 int rand[] = new int [Enemy.ENEMY_TYPE_MAX];
		 int randIdx = 0;
		 
		 for(int i = 0 ; i < Enemy.ENEMY_TYPE_MAX; i++)
		 {
			 if(countCreateStop[i] >= 1)
			 {
				 countCreateStop[i]--;
				 continue;
			 }
			 
			 if(Stage_EnemyOk[stage][i] == 0)
			 {
				 continue;
			 }
			 //LV
			 if(Stage_EnemyLv[stage][i][0] > lv)
			 {
				 continue;
			 }
			 //ランダム
			 int randValIdx = 0;
			 for(int f = 0; f < ENEMY_LV_RAN; f++)
			 {
				 if(Stage_EnemyLv[stage][i][f] == -1 )
				 {
					 break;
				 }
				 if(Stage_EnemyLv[stage][i][f] <= lv)
				 {
					 randValIdx++;
					 continue;
				 }else
				 {
					 break;
				 }
			 }
			 
			 if(Stage_EnemyRand[stage][i][randValIdx] <= 0)
			 {
				 if(randValIdx >= 1)
				 {
					 randValIdx--;
				 }
			 }
			 
			 randamMax += Stage_EnemyRand[stage][i][randValIdx];
			 
			 type[randIdx] = i;
			 rand[randIdx] = Stage_EnemyRand[stage][i][randValIdx];
			 randIdx++;			 
		 }
		                    
		 int randamVal = Library.random.nextInt(randamMax );
			
		 
		 for(int i = 0 ; i < randIdx; i++)
		 {
			 if(randamVal < rand[i])				 
			 {
				 return type[i];
			 }else
			 {
				 randamVal -= rand[i];
			 }
		 }
		 return -1;
	 }
	 
	 
	 private int EnemyBig( int stage, int lv , int type )
	 {
		 for(int i = 0 ; i < ENEMY_LV_RAN ; i++)
		 {
			 if(Stage_EnemyBigLv[stage][type][i] == -1)
			 {
				 break;
			 }
			 if(lv >= Stage_EnemyBigLv[stage][type][i])
			 {
				 int randam = Library.random.nextInt(Stage_EnemyBigRand[stage][type][i]);
				 if(randam == 0 )
				 {
					return Library.random.nextInt(Stage_EnemyBig[stage][type][i])+1;
				 }
			 }
		 }
		 return 0;
	 }
	 
	 
	 
	 
	 
	 
	 public void draw(Graphics g)
	 {
		 for(int i = 0; i < MAX_ENEMY; i++)
		 {
			if( EnemyData[i].getLife())
			{
				EnemyData[i].draw(g);				
			}
		 }
	 }
	 public void drawBobm(Graphics g)
	 {
		 for(int i = 0; i < MAX_ENEMY; i++)
		 {
			if( EnemyData[i].enemy_Bomb)
			{
				EnemyData[i].draw(g);				
			}
		 }
	 }
	 
	 
	 public void setStage(int stage)
	 {
		 this.m_i_nowStage = stage;
	 }
	 public void setGameLv(int lv)
	 {
		 GameLv = lv;
	 }
	 
	 public int getGameLv()
	 {
		 return GameLv;
	 }
	 
	 
	 public int getEnemyManey()
	 {
		 int maney = 0;
		 
		 for(int i = 0; i < Enemy.ENEMY_TYPE_MAX; i++)
		 {
			 maney += DestroyEnemyPoint[i];
		 }		 
		 return maney;
	 }
	 
	 public float getResultPar()
	 {
		 return ResultPar;
	 }
	 public float getResultParADD()
	 {
		 return ResultPar_ADD;
	 }
	 
	 public int getResultNowGold()
	 {
		 int result = 0;
		 for(int i = 0 ; i < Enemy.ENEMY_TYPE_MAX; i++)
		 { 
			 if(DestroyEnemy[i] >= 1)
			 {
				 result += DestroyEnemyPoint[i];
			 }
		 }
		 return result;
	 }
	 
//	 public static int getEnemyPoint(int type)
//	 {
//		 return Enemy.EnemyPoint( type);
//	 }
	 
	 public int GetDestroyKind()
	 {
		 int kind = 0;
		 for(int i = 0 ; i < Enemy.ENEMY_TYPE_MAX; i++)
		 {
			 if(DestroyEnemy[i] >= 1)
			 {
				 kind++;				 
			 }
		 }
		 return kind;
	 }
	 
	 public int[] GetDestroyEnemy()	 
	 {
		 return DestroyEnemy;
	 }
	 public int GetDestroyEnemyNum()
	 {
		 return DestroyEnemyNum;
	 }
	 
	 
//	 public final static int FLAME_MAX = 7;
//	 public final static int FLAME_X[] = {90,110,130,150,170,190,210};
//	 public final static int FLAME_Y[] = {180,160,140,120,100,80,60};

	 //初期座標
	 public final static int FLAME_X = 160;
	 public final static int FLAME_Y = 520;

	 //public final static int FLAME_W	= 6;
	 //public final static int FLAME_H	= 6;
	 //public final static int FLAME_MOVE_X = 4;
	 //public final static int FLAME_MOVE_Y = 8;
	
	 
	 public int[] DestroyEnemy = new int [Enemy.ENEMY_TYPE_MAX];	 
	 public int[] DestroyEnemyPoint = new int [Enemy.ENEMY_TYPE_MAX];	 	 
	 public int DestroyEnemyNum = 0;
	 float ResultPar = 1.0f;
	 float ResultPar_ADD = 0.0f;
	 int chain = 0;
	 int chainBournus = 0;
	 
	 public int BombCharaNum = 0;
	 
	 public int FlameHit()
	 {
		 
		EffectData FlameData[] = Play.pEffect.getFlameDatas();

		
		int Nums[] = Play.pEffect.getFlameNum();
		 
		if(MainData.DEBUG_MODE)
		{
//			for(int i = 0 ; i < Enemy.ENEMY_TYPE_MAX; i++)
//			{
//				DestroyEnemy[i]= 1;
//			}
		}
		 
		 for(int e = 0; e < MAX_ENEMY; e++)
		 {
			if( EnemyData[e].getNoLife() == false)
			{
				//Library.TraseMsg("EnemyData[e].point_x"+EnemyData[e].point_x);
				//Library.TraseMsg("EnemyData[e].point_y"+EnemyData[e].point_y);
				for(int f = 0 ; f < Effect.EFFECT_MAX; f++)
				{
					if(FlameData[f] == null)
					{
						continue;
					}
					
					//Library.TraseMsg("XFlame[idx]"+XFlame[f]);
					//Library.TraseMsg("YFlame[idx]"+YFlame[f]);
					
					int hitW =(int)((float)(FlameData[f].hit_rarge*(float)MainData.Width_Size)/2);
					int hitH =(int)((float)(FlameData[f].hit_rarge*(float)MainData.Height_Size)/2);
					
					int EneW = (int)((float)(EnemyData[e].GetWidth()*(float)MainData.Width_Size)/2);
					int EneH = (int)((float)(EnemyData[e].GetWidth()*(float)MainData.Height_Size)/2);
					
					if(EnemyData[e].is_Missile )
					{
						if(FlameData[f].missile_sloo)
						{
							continue;
						}
					}
					
					if(EnemyData[e].point_x  + EneW >=
						FlameData[f].point_x - hitW && 
						EnemyData[e].point_x  - EneW <=
							FlameData[f].point_x + hitW )
					{
						if(EnemyData[e].point_y  + EneH >=
							FlameData[f].point_y - hitH && 
							EnemyData[e].point_y  - EneH <=
								FlameData[f].point_y + hitH)
						{
							
							//ミサイル無効化じゃないなら
							if(EnemyData[e].is_Missile )
							{
								float damege = 0.0f;
								//GAMEOVER
								if(EnemyData[e].enemy_type == Enemy.ENEMY_MISAIL)
								{					
									damege = 0.25f;
								}
								else
								if(EnemyData[e].enemy_type == Enemy.ENEMY_AT_MISAIL )								
								{
									damege = 0.45f;
								}
								else
								if(EnemyData[e].enemy_type == Enemy.ENEMY_JS_MISAIL )
								{
									damege = 0.65f;
								}
								
								damege += (float)((Library.random.nextInt(20)) * 0.01f);
								damege *= EnemyData[e].enemy_draw_Big;
								//お店による補正
								if(ShopData.BASE_MISSILE_DAMEGE[Library.getShop()] == 0)
								{
									damege /= 2;
								}else
								{
									damege += (float)((Library.random.nextInt(ShopData.BASE_MISSILE_DAMEGE[Library.getShop()]) )
										* 0.01f);
								}
								
								ResultPar_ADD = - ResultPar * damege;
								
								EnemyData[e].enemy_Bomb = true;
								return 1;
							}
							
							{
								pAm.setErrMsg("EnemyFlameHit 2");

								int TimePluse = Play.FLAME_HIT_TIME - (GameLv / 9);
								
								if(TimePluse <= 4)
								{
									TimePluse = 4;
								}
								//HIT
								if(EnemyData[e].EnemyFlameHit(FlameData[f].power) == 1)
								{
									FlameHitPointUp(e);
									GameCountLvUp += 5 ;
									Play.SetTimer(Play.GetTimer() + TimePluse ,UI.TIME_TYPE_UP);
								}else
								{
									Play.SetTimer(Play.GetTimer() + TimePluse / 3 ,UI.TIME_TYPE_UP);
								}
								
								
								
								
								//TODO 貫通属性の有無
								
								if(FlameData[f].enemy_sloo)
								{
									
								}else{
									Play.pEffect.deleteEffect(Nums[f]);								
									FlameData[f] = null;									
								}
								//pAm.setErrMsg("Enemy_y"+EnemyData[e].point_y+"Flamepoint_y"+FlameData[f].point_y+
								//		"\nEnemy_x"+EnemyData[e].point_x+"Flamepoint_x"+FlameData[f].point_x);
								break;
							}
							
						}
					}
				}
			}
		 }
		 
		 
		 //if(x >=)
		 return 0;
	 }
	 
	 public final static int POWER_FLAME_MAX = 7;
	 public final static int POWER_FLAME_X[] = {
		 Player.POWER_FLAME_X+80,
		 Player.POWER_FLAME_X+120,
		 Player.POWER_FLAME_X+160,
		 Player.POWER_FLAME_X+200,
		 Player.POWER_FLAME_X+220,
		 Player.POWER_FLAME_X+240,
		 Player.POWER_FLAME_X+260};
	 public final static int POWER_FLAME_Y[] = {
		 Player.POWER_FLAME_Y,
		 Player.POWER_FLAME_Y-60,
		 Player.POWER_FLAME_Y-100,
		 Player.POWER_FLAME_Y-140,
		 Player.POWER_FLAME_Y-180,
		 Player.POWER_FLAME_Y-210,
		 Player.POWER_FLAME_Y-260};
	 public final static int POWER_FLAME_W[] = {
		 60,
		 60,
		 90,
		 90,
		 90,
		 90,
		 110};
	 //バーストモード
	 public int FlameHitPower()
	 {
		 if(GameCount % 2 == 0)
		 {
			 return 0;
		 }
		 
		 for(int e = 0; e < MAX_ENEMY; e++)
		 {
			if( EnemyData[e].getNoLife() == false)
			{
				for(int f = 0 ; f < POWER_FLAME_MAX; f++)
				{
					if(EnemyData[e].point_x  + EnemyData[e].GetWidth() >=
						POWER_FLAME_X[f] - POWER_FLAME_W[f] && 
						EnemyData[e].point_x  - EnemyData[e].GetWidth() <=
							POWER_FLAME_X[f] + POWER_FLAME_W[f])
					{
						if(EnemyData[e].point_y  + EnemyData[e].GetWidth() >=
							POWER_FLAME_Y[f] - POWER_FLAME_W[f] && 
							EnemyData[e].point_y  - EnemyData[e].GetWidth() <=
								POWER_FLAME_Y[f] + POWER_FLAME_W[f] )
						{
							//HIT
							if(EnemyData[e].EnemyFlameHit(1) == 1)
							{
								FlameHitPointUp(e);
								GameCountLvUp += 5 ;
								Play.SetTimer(Play.GetTimer() + Play.FLAME_HIT_TIME / 2,UI.TIME_TYPE_UP);
							}else
							{
								Play.SetTimer(Play.GetTimer() + Play.FLAME_HIT_TIME / 6 ,UI.TIME_TYPE_UP);
							}			
							break;
						}
					}
				}
			}
		 }
		 
		 
		 //if(x >=)
		 return 0;
	 }
	 
	 private void FlameHitPointUp(int type)
	 {

		EnemyData[type].EnemyFlameHit(1);
	
		DestroyEnemy[EnemyData[type].enemy_type]++;		
		DestroyEnemyPoint[EnemyData[type].enemy_type] += EnemyData[type].point;
		
		DestroyEnemyNum++;
		 chain++;
		
		chainBournus+= chainBournus;
		
		
		
		ResultPar += 0.004f * EnemyData[type].getBig();
		
		float upPar = getGameLv()/10*0.001f;
		ResultPar += upPar;
		
		if(EnemyData[type].enemy_type == Enemy.ENEMY_CHIKIN3 )
		{
			ResultPar += 0.50f+ (Library.random.nextInt() % 30) * 0.01f;
		}

	 }
	 
	 
	 public void initDestroy()
	 {
		 for(int i = 0 ; i < Enemy.ENEMY_TYPE_MAX; i++)
		 {
			 DestroyEnemy[i] = 0;
			 DestroyEnemyPoint[i] = 0;
		 }
		 DestroyEnemyNum = 0;
		 ResultPar = 1.0f;
		 ResultPar_ADD = 0.0f;
	 }
	 
	 public void EnameyAllDelete()
	 {
		 for(int i = 0 ; i < MAX_ENEMY; i++)
		 {
			 EnemyData[i].desh();
		 }
	 }
	 
	 public void EnameyAllDeleteBobm()
	 {
		 for(int i = 0 ; i < MAX_ENEMY; i++)
		 {
			 if(EnemyData[i].enemy_Bomb == false)
			 {
				 EnemyData[i].desh();
			 }
		 }
	 }
	 
	 
	 
	 
	 public void chainDelete()
	 {
		 chain = 0;
	 }
	 
	 private int getPointResultX = 0;
	 private int getPointResultY = 0;
	 //近い敵検索
	 public boolean getEnemyPointNear(int x , int y)
	 {
		 final int MAXVAL = 5000;
		 
		 int val[] = new int[MAX_ENEMY];
		 for(int i = 0; i < MAX_ENEMY; i++)
		 {
			 boolean non = true;
			if( EnemyData[i].getNoLife() == false)
			{
				int nowVal = 0;
				if( EnemyData[i].is_Missile)
				{
				}else
				{
					if(x > EnemyData[i].point_x)
					{
						nowVal = x - EnemyData[i].point_x;
					}else
					{
						nowVal = EnemyData[i].point_x - x;
					}
					
					if(y > EnemyData[i].point_y)
					{
						nowVal += y - EnemyData[i].point_y;
					}else
					{
						nowVal += EnemyData[i].point_y - y;
					}					
					val[i] = nowVal;
					non = false;
				}
			}
			if(non)
			{
				val[i] = MAXVAL;
			}
		 }	
		 
		 int lastVal = MAXVAL;
		 int lastIdx = -1;
		 for(int i = 0; i < MAX_ENEMY; i++)
		 {
			if( lastVal > val[i])
			{
				lastVal = val[i];
				lastIdx = i;
			}
		 }
		 
		 if(lastIdx == -1)
		 {
			 return false;
		 }else
		 {		 
			 getPointResultX = EnemyData[lastIdx].point_x;
			 getPointResultY = EnemyData[lastIdx].point_y;		 
		 }
		 return true;
	 }
	 public int getEnemyPointNearRetX()
	 {
		 return getPointResultX;
	 }
	 public int getEnemyPointNearRetY()
	 {
		 return getPointResultY;
	 }
	 
}