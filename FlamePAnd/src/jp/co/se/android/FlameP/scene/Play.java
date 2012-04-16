
package jp.co.se.android.FlameP.scene;

//import Exception;

import java.io.InputStream;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;

import jp.co.se.android.FlameP.Main.*;
import jp.co.se.android.FlameP.lib.DataSave;
import jp.co.se.android.FlameP.lib.Library;
import jp.co.se.android.FlameP.lib.ShopData;

import jp.co.se.android.FlameP.activity.ConstantClass;
import jp.co.se.android.FlameP.draw.*;
import jp.co.se.android.FlameP.Sound.*;
import jp.co.se.android.FlameP.data.*;



/**
 * MainCanvas
 * 
 */
public class Play
{
	protected MainData	pAm = null;
	
	private static final int MAX_TITLE_CUR = 3;
	
	private GameAI pGame = null;
	
	private Player pPlayer = null;
	
	public static Effect pEffect = null;
	
	boolean titleDemo = false;
	
	

	
	public Play(MainData data)
	 {
		pAm = data;
		pGame = new GameAI(data);
		
		pPlayer = new Player();

		pEffect = new Effect();
		
		this.init();
		
		
	 }
	
	
	 public void dispose()
	 {
		 this.release();
	 }
	 
	 
	 //����
	 public void init()
	 {
		 pAm.setErrMsg("Play Init ");
		this.PlayStep = PLAY_STEP_WAIT;
		
		this.i_Dead_Counter = 0;
		
		pGame.init();
		
		pGame.AllNew();
		
		pGame.setStage(GameAI.STAGE_1);
		pGame.setGameLv(0);
		
		pPlayer.init();
		
		titleDemo = false;
		
		resultStep = 0;
		
		resultCount = 0;
		
		pGame.initDestroy();
		
		bombCount = 0;
		bombBack = false;
		
		timer = MAX_TIME ;
		
		UI.DRAW_UI_MODE(UI.NORMAL_MODE);
		
		pEffect.init();
		
		
		timerTypeTime = 0;
		timerType = 0;
	 }
	 

	 public void setTitleDemo()
	 {
		 this.PlayStep = PLAY_STEP_PLAYING;	
		 this.titleDemo = true;
		 
		 //pGame.setLv(15);
		 
		 Library.TraseMsg("�ł�����");
		 
	 }
	 
	 
	 private void release()
	 {	 
		 //pGame.dispose();
	 }
	 
 
	 private int Yes_No = 0;
	 private final int YES_COMMAND = 0;
	 private final int NO_COMMAND = 1;
	 
	 private int i_Counter = 0;
	 
	 
	 private int i_Dead_Counter = 0;
	 private final int DEAD_TIME = 20;
	 
	 //[PlayStep]
	 //������
	 private final int PLAY_STEP_WAIT  = 0 ;
	 //�v���C��
	 public static final int PLAY_STEP_PLAYING	 = 1 ;
	 //�|�[�Y
	 public static final int PLAY_STEP_POZE	 = 2 ;
	 //�^�C�g���֍s���܂���
	 private final int PLAY_STEP_TITLE_OK	 = 3 ;
	 //�Q�[���I�[���@�[
	 private final int PLAY_STEP_GAME_OVER 	= 4;
	 //�����L���O
	 private final int PLAY_STEP_LANK_IN	 		= 5;
	 //�����N�ɏ�炸�^�C�g����
	 private final int PLAY_STEP_GAME_OVER_TITLE	= 6;
	 //���O�I�[�o�[
	 private final int PLAY_STEP_NAME_OVER	 		= 7;
	 //���U���g
	 private final int PLAY_STEP_RESULT				= 8;
	 private final int PLAY_BOMB 					= 9;
	 
	 

	 private int PlayStep = PLAY_STEP_WAIT;
	 
	 //public static int FLAME_MAX = 25;
	 
	 public static final int MAX_TIME = 450;
	 
	 public static int timer = 0;
	 
	 public static final int FLAME_NOHIT_TIME = 22;
	 public static final int FLAME_HIT_TIME = 15;

	 public int PlayProc()
	 {
		 pAm.setErrMsg("PlayProc 1 ");

	
		 //pAm.saveDebugErr();
		 
		 int ret = 0;
		 try
		 {
			 if(GotoTitle)
			 {
				 GotoTitle = false;
				 return 1;
			 }
			 
			 if(this.titleDemo)
			 {
				 pGame.setGameLv(5);
				 pGame.setStage(GameAI.STAGE_TITLE);	
				 SetTimer(500 , -1);
			 }
			 
			 int nowKey = pAm.getNowKey();		 
			 
			 int downKey = pAm.getDownKey();
			 //pAm.saveDebugErr();
			 pAm.setErrMsg("PlayProc 2 ");

			 pAm.saveDebugErr();
			 if(this.titleDemo == false)
			 {
			 
				 switch ( this.PlayStep ) {
				 	
				 	case PLAY_STEP_WAIT:
				 	{
				 		procStartWait( nowKey);
				 		
				 		break;
				 	}
				 	
				 	//プレイ中
				 	case PLAY_STEP_PLAYING:
				 	{
				 		procPlayCommand(nowKey,downKey);
				 		break;
				 	}
				 	
				 	
				 	
				 	//�|�[�Y
		//			 	case PLAY_STEP_POZE:
		//			 	{
		//			 		procPlayPoze(nowKey);		 		
		//			 		break;
		//			 	}
				 	
				 	//�^�C�g���֍s���Ă����v
				 	case PLAY_STEP_TITLE_OK:
				 	{
				 		ret = procPlayTitleOk(nowKey);
				 		break;
				 	}
				 	
				 	case  PLAY_STEP_GAME_OVER:
				 	{
				 		procPlayDead(nowKey);
				 		break;
				 	}
				 	
				 	
				 	case  PLAY_STEP_GAME_OVER_TITLE:
				 	{
				 		ret = procGameOverTitle( nowKey);
				 		break;
				 	}
				 	
				 	case  PLAY_STEP_LANK_IN:
				 	{
				 		ret = procLanlIn( nowKey );
				 		break;
				 	}
				 	
				 	case  PLAY_STEP_NAME_OVER:
				 	{
				 		PlayNameOver( nowKey );
				 		break;
				 	}
				 	
				 	case PLAY_STEP_RESULT:
				 	{
				 		ret = PlayResult( nowKey );
				 		break;
				 	}
				 	
				 	case PLAY_BOMB:
				 	{
				 		PlayBomb( nowKey );
				 		break;			 		
				 	}
		
				default:
					break;
				}
			 }else
			 {
				 procPlayCommand(nowKey,downKey);
			 }
			 pAm.saveDebugErr();
			 pAm.setErrMsg("PlayProc 3 ");
			 //pAm.saveDebugErr();
			 if(this.PlayStep == PLAY_STEP_PLAYING ||
				this.PlayStep == PLAY_STEP_RESULT  ||
				this.PlayStep == PLAY_BOMB ||
				this.PlayStep == PLAY_STEP_GAME_OVER )
			 {
				 boolean flag = false;
				 
				 if(this.PlayStep == PLAY_STEP_RESULT)
				 {
					 flag = true;
				 }
				 
				 boolean bom = false;
				 if(this.PlayStep == PLAY_BOMB ||
					 this.PlayStep == PLAY_STEP_GAME_OVER)
				 {
					 bom = true;
				 }
				 pAm.setErrMsg("pGame.move ");

				 int pointRet = pGame.move( flag , pPlayer.getPlayerCondition() , bom );
				 
				 if(pointRet == 2 ||
					(MainData.DEBUG_MODE && nowKey == MainData.KEY_9 ))
				 {
					 pPlayer.SetPowerUp();
				 }
				 
				 //this.ScoaSet(pointRet);
				 pAm.setErrMsg("PlayerProc ");
				 //pAm.saveDebugErr();
				 pPlayer.PlayerProc();
				 
				 pAm.setErrMsg("procEffect ");
				 pAm.saveDebugErr();
				 //
				 
				 int Nohit = pEffect.procEffect(pGame);
				 
				 if(Nohit >= 1)
				 {
					 int timerMainus = FLAME_NOHIT_TIME + pGame.getGameLv() / 2;
					 if(Nohit == 2)
					 {
						 timerMainus /= 4; 
					 }
					  SetTimer(timer - timerMainus 
							  ,UI.TIME_TYPE_DOWN);
					  pGame.chainDelete();
				 }
				 pAm.setErrMsg("PlayProc 5 ");
				 pAm.saveDebugErr();
				 if( pEffect.isEffectTypeLife(Effect.EFFECT_PI_FLAME_POWER) )
				 {
					 //
					 pGame.FlameHitPower( );
				 }else			  
				 {
					 int hittype = pGame.FlameHit( );
					 if(hittype >= 1)
					 {
						 //GAMEOVER
						 this.PlayStep = PLAY_BOMB;
						 
						 //げぇ！
						 pPlayer.SetDead();
						 
						 bombCount = 0;
						 Library.SE_Play( se_Play.SOUND_SE_BUZAR );
						 
					 }
				 }
			 }
			
			 pAm.setErrMsg("PlayProc 6 ");
			 pAm.saveDebugErr();
			 //
			 if(ret == 1)
			 {
				
				 //this.init(); 
			 }	 
		 }catch (Exception e) {
			 Library.showDebugDialog(pAm.getActivity(), "Play Proc ERR  " );
			 if(MainData.DEBUG_MODE_SAVE)
			 {
				 pAm.saveDebugErr();
				 pAm.terminateGame2();
			 }
			// TODO: handle exception
		 }
		 //pAm.setErrMsg("PlayProc 7 ");
		 
		 return ret;
	 }
	 
	 int StartWaitCounter = 0;
	 public static final int MAX_START_COUNT = 30;
	 
	 private void procStartWait(int Key)
	 {
		 int toushX = pAm.getToushDownX();
		 int toushY = pAm.getToushDownY();
		 
		 if(toushX >= 1)
		 {
			this.StartWaitCounter = 0;
			this.PlayStep = PLAY_STEP_PLAYING;			 
			pPlayer.SetWepon(Library.nowEquip());
	 		Library.SE_Play( se_Play.SOUND_SE_SELECT );
		 }
	 }
	 
	 public static final int TOUSH_TYPE_CHARA = 1;
	 public static final int CHARA_POINT_X = Player.PLAYER_DEF_X;
	 public static final int CHARA_POINT_Y = Player.PLAYER_DEF_Y;
	 public static final int CHARA_POINT_W = Player.PLAYER_WIDTH;
	 public static final int CHARA_POINT_H = Player.PLAYER_HIGHT;
	 
	 public static final int TOUSH_TYPE_MENU= 2;
	 public static final int MENU_POINT_X = MainData.SCREEN_WIDTH -168;
	 public static final int MENU_POINT_Y = MainData.SCREEN_HEIGHT -32;
	 public static final int MENU_POINT_W = 64;
	 public static final int MENU_POINT_H = 32;
	 
	 public static final int TOUSH_TYPE_WEPON= 3;
	 public static final int WEPON_POINT_X = MENU_POINT_X + MENU_POINT_W;
	 public static final int WEPON_POINT_Y = MENU_POINT_Y;
	 public static final int WEPON_POINT_W = MENU_POINT_W;
	 public static final int WEPON_POINT_H = MENU_POINT_H;
	 
	 
	 private boolean toushOkResult(int type ,int toushX , int toushY)
	 {
		 int okW = CHARA_POINT_W;
		 int okH = CHARA_POINT_H;
			
		 int okX = CHARA_POINT_X;
		 int okY = CHARA_POINT_Y;
		 pAm.setErrMsg("toushOkResult 1 ");

		 if(type == TOUSH_TYPE_CHARA)
		 {			 
			 okW = CHARA_POINT_W+20;
			 okH = CHARA_POINT_H+20;
				
			 okX = CHARA_POINT_X-10;
			 okY = CHARA_POINT_Y-10;
			 int nowType = pPlayer.getNowFlameType();
			 if(ShopData.FLAME_TOUCHE_FREE[nowType])
			 {
				 if(toushX >= 1)
				 {
					 //自由タッチ
					 return true;
				 }else
				 {
					 return false;
				 }
			 }
			 
		 }else
		 if(type == TOUSH_TYPE_MENU)
		 {
			 okW = UI.MENU_ICON_W;
			 okH = UI.MENU_ICON_H;
				
			 okX = UI.MENU_ICON_X;
			 okY = UI.MENU_ICON_Y;
		 }else
		 if(type == TOUSH_TYPE_WEPON)
		 {
			 okW = WEPON_POINT_W;
			 okH = WEPON_POINT_H;
				
			 okX = WEPON_POINT_X;
			 okY = WEPON_POINT_Y;
		 }
		 
		 
		
		 okX = (int)((float)okX * (float)MainData.Width_Size_True)
		 	+ MainData.SCREEN_MOVE_X;
		 okY = (int)((float)okY * (float)MainData.Height_Size_True )
		 	+ MainData.SCREEN_MOVE_Y;
		 okW = (int)((float)okW * (float)MainData.Width_Size_True);
		 okH = (int)((float)okH * (float)MainData.Height_Size_True );
		 pAm.setErrMsg("toushOkResult 2 ");

		 
		 if((toushX >= okX -Title.TOUSH_COMAND_UP&&
			 toushX <= okX + okW + Title.TOUSH_COMAND_UP)&&
			(toushY >= okY -Title.TOUSH_COMAND_UP&&
			 toushY <= okY + okH + Title.TOUSH_COMAND_UP)			 
			)
		 {
			 
			return true;
		 }
		 pAm.setErrMsg("toushOkResult 3 ");

		 return false;
	 }
	 


	 
	 
	 boolean ToushOK = false;
//	 boolean ToushMenu = false;
	 boolean ToushWepon = false;
	 //
	 private void procPlayCommand(int Key , int downKey)
	 {
		 pAm.setErrMsg("procPlayCommand 1 ");

		 int toushX = pAm.getToushDownX();
		 int toushY = pAm.getToushDownY();

		 int toushNowX = pAm.getToushPushX();

		 if( pPlayer.getPlayerCondition() !=
			 Player.PLAYER_POWERUP)
		 {
			 //SetTimer(timer - (1 + pGame.getGameLv() / 8) );
			 SetTimer(timer - 1 ,-1);

			 if(GetTimer() <= 0)
			 {
				 ResultStart();
				 return;
			 }
		 }
		 pAm.setErrMsg("procPlayCommand 2 ");
		 //pAm.saveDebugErr();
		 if(this.titleDemo)
		 {
			 this.ToushOK = true;
		 }else
		 if( pPlayer.getPlayerCondition() ==
			 Player.PLAYER_POWERUP)
		 {
			 //タッチできん
		 }else{
			 this.ToushOK	 = toushOkResult( TOUSH_TYPE_CHARA , toushX ,  toushY);
		 }
		 
		 
		 int nowType = pPlayer.getNowFlameType();
		 
		 if(this.titleDemo)
		 {
			 nowType =  Setting.getNowSelect();
			 if(nowType <= 0)
			 {
				 this.ToushOK = false;
			 }
		 }
		 
		 
		 //チャージの場合はなしたら発射
		 if(this.ToushOK == false)
		 {
			 if(nowType == ShopData.FLAME_TYPE_CHARGE)
			 {
				 if(pPlayer.getLongToush() >= 1)
				 {
					 this.ToushOK = true;
				 }
			 }
		 }
		 pAm.setErrMsg("procPlayCommand 3 ");
		 if(this.titleDemo )
		 {
			 if(nowType == ShopData.FLAME_TYPE_CHARGE)
			 {
				 if(pPlayer.getLongToush() <= pPlayer.getMaxGage(nowType))
				 {
					 this.ToushOK = true;
				 }
			 }
		 }

		 //めぬぅ
		 boolean menu = toushOkResult( TOUSH_TYPE_MENU , toushX ,  toushY);
		
		//ゲーム終了
		 if((menu || Key == MainData.KEY_BACK) &&
				 this.titleDemo == false)
		 {
			endDialog();
			pAm.setErrMsg("procPlayCommand 4 ");
		 }		 
		 else
		 if(this.ToushOK)
		 {
			
			 pAm.setErrMsg("procPlayCommand 6 ");
			 boolean flameOK = false;
			 
			 
			 if(ShopData.FLAME_TOUCHE_LONG[nowType])
			 {
				 int Long = pPlayer.getLongToush();
				 
				 if(nowType == ShopData.FLAME_TYPE_CHARGE)
				 {
					 if(toushX == -1)
					 {
						 flameOK = true;
					 }else{
						 flameOK = false;
						 pPlayer.setPlayerCondition(Player.PLAYER_NORMAL);
					 }
				 }else				 
				 if(nowType == ShopData.FLAME_TYPE_FLAME)
				 {
					 if(Long % 2 == 0)
					 {
					    flameOK = true;
					 }else
					 {
						 
					 }
					 pPlayer.setPlayerCondition(Player.PLAYER_ATTACK);
				 }
				 pPlayer.AttackLongToush(nowType);
			 }
			 else if(pPlayer.AttackOn())
			 {
				 flameOK = true;
			 }
				 
				 
			 pAm.setErrMsg("procPlayCommand 7 ");
			 pAm.saveDebugErr();
				 
			 
			 
				 
			 if(flameOK)
			 {
				 if(Library.random.nextInt(4) <= 2)
				 {
					 Library.SE_Play( se_Play.SOUND_SE_BLES );
				 }
				 
				 pAm.setErrMsg("procPlayCommand 7-1 ");
				 pAm.saveDebugErr();
				 int effectType = 0;
				 int num[] = new int [1];
				 boolean normal = true;
				 
				 switch (nowType) {
				 
				 
				 
				 
					case ShopData.FLAME_TYPE_NORMAL:
						 effectType = Effect.EFFECT_PI_FLAME;
						break;
					case ShopData.FLAME_TYPE_CASTAM:
						 effectType = Effect.EFFECT_PI_FLAME_CAS;
						break;
					case ShopData.FLAME_TYPE_HOMING:
						 effectType = Effect.EFFECT_PI_FLAME_HOMING;
						break;
						
					case ShopData.FLAME_TYPE_CHARGE:
					{
						 if(pPlayer.getLongToush() >= 
							 ShopData.FLAME_TOUCHE_LONG_MAX_TIME[nowType])
						 {
							 effectType = Effect.EFFECT_PI_FLAME_CHARGE2;
						 }else
						 if(pPlayer.getLongToush() >= 
							 ShopData.FLAME_TOUCHE_LONG_MAX_TIME[nowType]*2)
						 {
							 effectType = Effect.EFFECT_PI_FLAME_CHARGE3;
						 }
						 
						 pPlayer.setPlayerCondition(Player.PLAYER_ATTACK);
						 pPlayer.InitLongToush();
						 break;
					}
						
						
					case ShopData.FLAME_TYPE_SMOAL:
						
						
						//4個くらい炎を出す
						for(int i = 0; i < 3; i++)
						{
							int randam = Library.random.nextInt(3);
							
							if( i <= 1 ||randam >= 1)
							{
								pEffect.createEffect( Effect.EFFECT_PI_FLAME_SMAL
										 , GameAI.FLAME_X , GameAI.FLAME_Y );
							}
						}
						effectType = Effect.EFFECT_PI_FLAME_LOW;
						break;
					
					case ShopData.FLAME_TYPE_FLAME:
					{
						normal = false; 

						int Long = pPlayer.getLongToush();
						
						int exNum[] = new int[1];
						exNum[0] = Long;

						
						{
							pEffect.createEffect( Effect.EFFECT_PI_FLAME_BLESE
									 , GameAI.FLAME_X , GameAI.FLAME_Y , exNum);
						}
						break;
					}
						
					case ShopData.FLAME_TYPE_SNIPING:
					{
						if(this.titleDemo)
						{
							toushX = 100 + Library.random.nextInt(350) ;
							toushY = 300 + Library.random.nextInt(300) ;
						}
						if(toushX == -1)
						{
							return;
						}else
						{
							toushX = (int)((float)(toushX/MainData.Width_Size));
							toushY = (int)((float)(toushY/MainData.Height_Size));
							
							toushX -= MainData.SCREEN_MOVE_X;
							toushY -= MainData.SCREEN_MOVE_Y;
							//ターゲット表示						 
							 pEffect.createEffect( Effect.EFFECT_PI_FLAME_SNIPE_TARGET
									 , toushX , toushY , num);
							 
							 effectType = Effect.EFFECT_PI_FLAME_SNIPE;
							 num = new int [2];
							 
							 //(float)MainData.Width_Size)
							 	//+ MainData.SCREEN_MOVE_X
							 num[0] = toushX ;
							 num[1] = toushY ;
						}
						 
						// return;
						break;
					}
						
					default:
						break;
				}
				 
				 pAm.setErrMsg("createEffect");
				 pAm.saveDebugErr();
				 if(normal)
				 {
					 pEffect.createEffect( effectType
					 , GameAI.FLAME_X , GameAI.FLAME_Y , num);
				 }
				 pAm.setErrMsg("createEffect ENd ");
				 pAm.saveDebugErr();
			 }
		 }
		 //何も押してないとき
		 else
		 {
			 nowType = pPlayer.getNowFlameType();
			 
			
			 
			 if(ShopData.FLAME_TOUCHE_LONG[nowType])
			 {
				 pPlayer.BlesuMainusTime();
			 }
		 }
		 
		 pAm.setErrMsg("procPlayCommand 8 ");
		 //pAm.saveDebugErr();
		 pAm.setErrMsg("procPlayCommand9 ");
		 //this.ToushWepon = toushOkResult( TOUSH_TYPE_WEPON , toushX ,  toushY);
	 }
	 
	 
	public GameAI getGameAI()
	{
		return pGame;
	}

	public Player getPlayer()
	{
		return pPlayer;
	}
	
	 
	 public static int  GetTimer()
	 {
		 return timer;
	 }
	 public static int  GetTimerType()
	 {
		 return timerType;
	 }
	 public static int timerType = UI.TIME_TYPE_NORMAL;
	 public static int timerTypeTime = 0;
	 public static void SetTimer(int time , int type)
	 {
		 if(type != -1)
		 {
			 timerType = type;
			 timerTypeTime = 3;
		 }
		 timer = time;
		 
		 if(timer >= MAX_TIME)
		 {
			 timer = MAX_TIME;
		 }
	 }
	 
	 private static Handler handler= new Handler();
	 private void endDialog()
	 {
		 Library.SE_Play( se_Play.SOUND_SE_CANCEL );
		 this.PlayStep = Play.PLAY_STEP_POZE;

		 final Play pl = this;
		 new Thread(new Runnable() {
			    public void run() {
			      handler.post(new Runnable() {
			    	  public void run() 
			    {
					 int  err = 0;
					 try
					 {
						err = 1;
					    AlertDialog.Builder ad=new AlertDialog.Builder(pAm.getActivity());
					    String Title = "";
					    String maney = "";
					    
					    
					    ad.setTitle("めにゅー");
					    ad.setMessage("終了して清算しますか？");
					    
					    ad.setInverseBackgroundForced(false);
					    err = 2;
					    
					    ad.setPositiveButton("清算しとく",
					    	new DialogInterface.OnClickListener()
						    {
						        public void onClick(DialogInterface dialog,int whichButton) {
						        	//終わり
						        	SetTimer(0,-1);
						        	pl.PlayStep = Play.PLAY_STEP_PLAYING;
						        }
						    }
					    );
					    err = 3;
					    ad.setNegativeButton("まだ続ける！",
					    	new DialogInterface.OnClickListener() 
					    	{				
								//@Override
								public void onClick(DialogInterface dialog,int whichButton) {
									pl.PlayStep = Play.PLAY_STEP_PLAYING;
						        }
							}
					    );
				    	
//					    ad.setOnKeyListener(new OnKeyListener() {
//					    	//@Override
//					    	public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//					    		if (keyCode == KeyEvent.KEYCODE_BACK) {
//					    			dialog.dismiss();
//					    			//閉じるときにしたい処理
//					    			pl.PlayStep = Play.PLAY_STEP_PLAYING;
//					    			return true;
//					    		}
//					    		return false;
//					    	}
//					    });
					    
					    
					    
					    ad.setCancelable(false);

				    	
				    	
					    err = 4;
					    ad.create();
					    ad.show();

					 }catch (Exception e) {
						// TODO: handle exception
					}
			    }
		      });
		    }
		  }).start();
	 }
	 
	 
	 
	 private int procPlayTitleOk(int Key)
	 {
		 boolean move = false;
		 boolean back = false;
		 
		 int toushX = pAm.getToushPushX();
		 int toushY = pAm.getToushPushY();
		 int toush = Library.YesNoCommandToush( toushX , toushY);
		 boolean ok = false;
		 if(toush >= 0)
		 {
			 if(this.Yes_No == toush)
			 {
				 ok = true;
			 }else
			 {
				 move = true;
				 this.Yes_No = toush;
			 }
		 }
		 
		 
		 //�ړ��L�[
		 if(Key == MainData.KEY_UP)
		 {
			 this.Yes_No = YES_COMMAND;
			 move = true;
		 }else
		 if(Key == MainData.KEY_DOWN)
		 {
			 this.Yes_No = NO_COMMAND;
			 move = true;
		 }
		 //����L�[
		 else if(Key == MainData.KEY_SELECT || ok )
		 {
			 Library.SE_Play( se_Play.SOUND_SE_SELECT );
			 if(this.Yes_No == YES_COMMAND)
			 {
				 //�I��
				 this.ResultStart();
			 }else
			 {
				 back = true;
			 }			 					 
		 }
		 //�߂�
		 else if(Key == MainData.KEY_BACK  )
		 {
			 Library.SE_Play( se_Play.SOUND_SE_CANCEL );
			 back = true;
		 }
		 
		 if(back)
		 {
			 this.PlayStep = PLAY_STEP_POZE;
		 }
		 
		 //���ǂ��r�d�@
		 if(move)
		 {
			 Library.SE_Play( se_Play.SOUND_SE_CURSOL );
		 }
		 return 0;
	 }
	 
	 int lanking = 0;
	 
	 private int procPlayDead(int Key)
	 {
		//pPlayer.SetDead();
	 		
		 bombCount++;
		 
		 
		//ショートカット
		int toushX = pAm.getToushDownX();
		if(Key == MainData.KEY_BACK ||
			toushX >= 1)
		{
			i_Dead_Counter = DEAD_TIME;
		}
		 
 		if(i_Dead_Counter >= DEAD_TIME)
 		{
 			 this.PlayStep = PLAY_STEP_RESULT;
 			 this.resultStep = RESULT_START;
 		}else
 		{
	 		i_Dead_Counter++;
 		}
 		return 0;
	 }
	 
	 private int procGameOverTitle(int Key)
	 {
		if(Key == MainData.KEY_SELECT ||
			Key == MainData.KEY_BACK||
			Key == MainData.KEY_SOFT2)
		{
			Library.SE_Play( se_Play.SOUND_SE_SELECT );
			return 1;
		} 
		return 0;
	 }
	 
	 boolean nameStart = false;
	 private int procLanlIn(int Key)
	 {
		if(Key == MainData.KEY_BACK ||
			Key == MainData.KEY_SELECT)
		{
			Library.SE_Play( se_Play.SOUND_SE_SELECT );
			//�Ȃ���			
			pAm.TEXT_Start();			
			nameStart = true;
			
		} 
		else
		if(Key == MainData.KEY_SOFT2)
		{
			Library.SE_Play( se_Play.SOUND_SE_SELECT );
			return 1;
		}
		
		if(nameStart )
		{
			if(pAm.Get_Text_Type() >= 1)
			{
				String Name;
				
				Name = pAm.Get_Text();
				
				if(Name.getBytes().length > Library.SCOA_NAME_BYTE)
				{
					this.PlayStep = PLAY_STEP_NAME_OVER;
					//pAm.setSoftKey(0 , "");
	 			    //pAm.setSoftKey(1 , "");
				}else{
					//Library.ScoaSetDirect(pPlayer.getScoa(), lanking , Name);
				}
				nameStart = false;
			}
		}
		
		return 0;
		
	 }
	 
	 private void PlayNameOver(int Key)
	 {
		if(Key == MainData.KEY_BACK ||
			Key == MainData.KEY_SELECT)
		{
			this.PlayStep = PLAY_STEP_LANK_IN;
		 	//pAm.setSoftKey(0 , "���t��");
		    //pAm.setSoftKey(1 , "����");
		    Library.SE_Play( se_Play.SOUND_SE_SELECT );
		}
	 }
	 
	 public static int bombCount = 0;
	 boolean bombBack = false;
	 public static final int BOMB_TIME = 50;
	 public static final int BOMB_TIME_START = 20;

	 private void PlayBomb(int Key)
	 {
		 bombCount++;
		 pAm.setErrMsg("PlayBomb 1");
		 if(bombCount == 1)
		 {		
			 pGame.EnameyAllDeleteBobm();
			 pEffect.deleteEffectAll();
			 Library.BGM_STOP_ALL();
		 }
		 
		 //ショートカット
		 int toushX = pAm.getToushDownX();
		 if(( Key == MainData.KEY_BACK ||
			 toushX >= 1))
		 {
			 if(bombCount >= 3 && 	
				  bombCount <= BOMB_TIME_START)
			 {
				 bombCount = BOMB_TIME_START;
			 }else
			 if(bombCount >= BOMB_TIME_START)
			 {
				 bombCount = BOMB_TIME;
			 }
		 }
		 pAm.setErrMsg("PlayBomb 2");
		 if( bombCount >= BOMB_TIME_START &&
				 bombCount <= BOMB_TIME_START + 5)
		 {
			 Library.SE_Play( se_Play.SOUND_SE_BOMB );	
		 }
		 
		 
		 pAm.setErrMsg("PlayBomb 3");
		 
		 //爆破終了
		 if(bombCount >= BOMB_TIME)
		 {
			 bombBack = true;
			 pPlayer.SetDead2();
			 
			 
			 this.PlayStep = PLAY_STEP_GAME_OVER;
		 }
	 }
	 
	 int resultStep = 0;
	 //�G�t�F�N�g�Œ�
	 final int RESULT_START = 0;
	 //���[��
	 final int RESULT_SCOA 	= 1;
	 //���[���ҋ@
	 final int RESULT_SCOA_WAIT 	= 2;
	 //��s�p�[�Z���g�ҋ@
	 final int RESULT_BANK_PAR_WAIT	= 3;
	 //��s�p�[�Z���g
	 final int RESULT_BANK_PAR 		= 4;
	 
	 final int RESULT_BANK_PAR_ADD_WAIT	= 5;
	 //��s�p�[�Z���g
	 final int RESULT_BANK_PAR_ADD		= 6;

	 
	 //��s��
	 final int RESULT_BANK 			= 7;
	 //��s��
	 final int RESULT_BANK_WAIT 	= 8;
	 //�A�b�v�O���[dp
	 final int RESULT_UPGRADE 		= 9;
	 //��s��
	 final int RESULT_GO_TITLE 		= 10;
	 
	 
	 
	 int resultCount = 0;
	 int[] Destroy = new int[Enemy.ENEMY_TYPE_MAX];
	 int DestroyNum = 0;
	 int nowdisoResult = 0;
	 int getManey = 0;
	 float ResultPar = 0;
	 float ResultParAdd = 0;

	 int getManeyFinal = 0;
	 int getManeyFinal2 = 0;
	 
	 int i_nowLank  = 0;
	 int i_nextLank = 0;
	 
	 private int PlayResult(int Key)
	 {
		 timer = 0;
		 pAm.setErrMsg("PlayResult 1");
		 int toushX = pAm.getToushPushX();
		 int toushY = pAm.getToushPushY();
		 int toush = Library.YesNoCommandToush( toushX , toushY);
		 boolean ok = false;
		 boolean move = false;
		 if(toush >= 0)
		 {
			 if(this.Yes_No == toush)
			 {
				 ok = true;
			 }else
			 {
				 move = true;
				 this.Yes_No = toush;
			 }
		 }
		 pAm.setErrMsg("PlayResult 2");
		 if(toushX >= 0)
		 {
			 Key = MainData.KEY_SELECT;
		 }
		 pAm.setErrMsg("PlayResult 3");
		switch (this.resultStep) {
		
			//���G�t�F�N�g
			case RESULT_START:
			{
				if(resultCount >= 3)
				{
					if(Key == MainData.KEY_BACK ||
						Key == MainData.KEY_SELECT||
						resultCount >= 40 ||
						pAm.getToush())
					{
						Library.SE_Play( se_Play.SOUND_SE_SELECT );
						
						this.resultStep = RESULT_SCOA;
						
						this.Destroy = this.pGame.GetDestroyEnemy();
						
						this.DestroyNum = this.pGame.GetDestroyKind();
						
						this.nowdisoResult = -1;
						
						this.resultCount = 0;
						this.resultStep = RESULT_SCOA;
	
						 this.getManey = pGame.getEnemyManey();
						 
						 this.ResultPar = pGame.getResultPar();
						 
						 if(this.ResultPar <= 0.0f)
						 {
							 this.ResultPar = 0.0f;
						 }
						 this.ResultParAdd = pGame.getResultParADD();						 
						 
						 if(this.ResultParAdd >= 0.0f)
						 {
							 this.ResultParAdd = ResultPar * ShopData.BASE_GOLD_ADD[Library.getShop()]*0.01f;
						 }
						 
						 if(this.ResultParAdd <= -1.5f)
						 {
							 pPlayer.SetDead3();
						 }
						 
						 this.getManeyFinal =(int)( this.getManey * this.ResultPar );
						 this.getManeyFinal2 =(int)( this.getManey *( this.ResultPar + this.ResultParAdd ));
						 
						 if(this.getManeyFinal <= 0)
						 {
							 this.getManeyFinal = 0;
						 }
						 
						 if(this.getManeyFinal2 <= 0)
						 {
							 this.getManeyFinal2 = 0;
						 }
						 
						 Library.TraseMsg("this.getManey "+this.getManey);
	
						 pEffect.deleteEffectAll();
						 
						 this.i_nowLank = Library.nowLank();
						 Library.BGM_STOP_ALL();
						 
						 break;
					}
				}
				
				this.resultCount++;
				break;
			}	
			
			case RESULT_SCOA:
			{
				if(Key == MainData.KEY_BACK ||
					Key == MainData.KEY_SELECT||
					resultCount == 0 ||
					 pAm.getToush())
				{
					if(this.DestroyNum > this.nowdisoResult)
					{
						this.nowdisoResult++;
						Library.TraseMsg("nowdisoResult"+nowdisoResult);
					}else
					{
						this.resultStep = RESULT_SCOA_WAIT;
						Library.TraseMsg("RESULT_SCOA END");
						//pAm.setSoftKey(0 , "����");
		 			    //pAm.setSoftKey(1 , "");

						break;
					}
				}
				if(this.resultCount >= 10)
				{
					this.resultCount = 0;				
				}else
				{
					this.resultCount++;
				}
				break;
			}
			
			//
			case RESULT_SCOA_WAIT:
			{
				if(Key == MainData.KEY_BACK ||
					Key == MainData.KEY_SELECT||
					this.resultCount >= 20  ||
							 pAm.getToush())
				{
					this.resultCount = 0;	
					//���̃X�e�b�v��
					this.resultStep = RESULT_BANK_PAR_WAIT;
					Library.SE_Play( se_Play.SOUND_SE_SELECT );
					//pAm.setSoftKey(0 , "");
	 			    //pAm.setSoftKey(1 , "");
				}
				this.resultCount++;
				
				break;
			}
			
			case RESULT_BANK_PAR_WAIT:
			{
				if(Key == MainData.KEY_BACK ||
					Key == MainData.KEY_SELECT||
					this.resultCount >= 20  ||
							 pAm.getToush())
				{
					this.resultCount = 0;	
					//���̃X�e�b�v��
					this.resultStep = RESULT_BANK_PAR;
					Library.SE_Play( se_Play.SOUND_SE_SELECT );
				}
				this.resultCount++;
				break;
			}
			
			case RESULT_BANK_PAR:
			{
				if(Key == MainData.KEY_BACK ||
					Key == MainData.KEY_SELECT||
					this.resultCount >= 20  ||
							 pAm.getToush())
				{
					this.resultStep = RESULT_BANK_PAR_ADD_WAIT;
					
					this.resultCount = 0;
					//this.resultStep = RESULT_BANK;
					
					this.ResultPar += this.ResultParAdd;
					
					Library.SE_Play( se_Play.SOUND_SE_SELECT );
				}
				this.resultCount++;
				break;
			}
			
			case RESULT_BANK_PAR_ADD_WAIT:
			{
				if(Key == MainData.KEY_BACK ||
					Key == MainData.KEY_SELECT||
					this.resultCount >= 20  ||
							 pAm.getToush())
				{
					this.resultStep = RESULT_BANK_PAR_ADD;
					
					this.resultCount = 0;	
					//���̃X�e�b�v��
					this.resultStep = RESULT_BANK;
					Library.SE_Play( se_Play.SOUND_SE_SELECT );
				}
				this.resultCount++;
				break;
			}
			
			case RESULT_BANK_PAR_ADD:
			{
				if(Key == MainData.KEY_BACK ||
					Key == MainData.KEY_SELECT||
					this.resultCount >= 20  ||
							 pAm.getToush())
				{
					this.resultCount = 0;	
					//���̃X�e�b�v��
					this.resultStep = RESULT_BANK;
					Library.SE_Play( se_Play.SOUND_SE_SELECT );
				}
				this.resultCount++;
				break;
			}
			
			//����s����
			case RESULT_BANK:
			{
				int addManey = this.getManeyFinal2 / 25 + 5;
				
				if(this.getManeyFinal2 > addManey)
				{
					Library.addManey(addManey);
					this.getManeyFinal2 -= addManey;		
					
				}else{
									
					Library.addManey(this.getManeyFinal2);
					this.getManeyFinal2 = 0;	
				}
				
				if(this.resultCount % 5 == 0)
				{
					Library.SE_Play( se_Play.SOUND_SE_GET );
				}
				
				if(Key == MainData.KEY_BACK ||
					Key == MainData.KEY_SELECT ||
							 pAm.getToush())
				{
					Library.addManey(this.getManeyFinal2);
					this.getManeyFinal2 = 0;
				}
				
				if(this.getManeyFinal2 <= 0)
				{
					Library.addDestroy(pGame.GetDestroyEnemyNum());
					this.resultCount = 0;	
					
					this.resultStep = RESULT_BANK_WAIT;
					Library.SaveDataBase(pAm);
					Library.TraseMsg("RESULT_BANK END");
					Library.SE_Play( se_Play.SOUND_SE_GOLD );
				}
				this.resultCount++;
				
				break;
			}

			//����s�E�F�C�g
			case RESULT_BANK_WAIT:
			{
				if(Key == MainData.KEY_BACK ||
					Key == MainData.KEY_SELECT||
					this.resultCount >= 30 ||
							 pAm.getToush())
				{
					this.resultCount = 0;	
					
					{
						DialogGoRetry();
						//this.resultStep = RESULT_GO_TITLE;
					}
					Library.TraseMsg("RESULT_BANK_WAIT END");
				}
				this.resultCount++;
				
				break;
			}

			

			case RESULT_UPGRADE:
			{
				if(this.resultCount >= 26 ||
						Key == MainData.KEY_BACK )
				{
					if(this.resultCount >= 80 ||
						Key == MainData.KEY_BACK ||
								 pAm.getToush())
					{
						DialogGoRetry();
						//this.resultStep = RESULT_GO_TITLE;
					}
				}
				
				if(this.resultCount == 10)
				{
					//Library.SE_Play( se_Play.SOUND_SE_GUREAD_UP );
				}
				
				this.resultCount++;
				break;
			}
			
			case RESULT_GO_TITLE:
			{
				 boolean back = false;
				 
				 //�ړ��L�[
				 if(Key == MainData.KEY_UP)
				 {
					 this.Yes_No = YES_COMMAND;
					 move = true;
				 }else
				 if(Key == MainData.KEY_DOWN)
				 {
					 this.Yes_No = NO_COMMAND;
					 move = true;
				 }
				 //����L�[
				 else if(Key == MainData.KEY_SELECT || ok)
				 {
					 Library.SE_Play( se_Play.SOUND_SE_SELECT );
					 if(this.Yes_No == YES_COMMAND)
					 {
						 //�I��
						 back = true;
					 }else
					 {
						 //��ײ
						 this.init();
						 Library.BGM_Play(pAm.getActivity(),pAm.getContext());
					 }			 					 
				 }
				 //�߂�
				 else if(Key == MainData.KEY_BACK  )
				 {
					 Library.SE_Play( se_Play.SOUND_SE_CANCEL );
					 back = true;
				 }
				 
				 if(back)
				 {
					 return 1;
				 }
				 
				 //���ǂ��r�d�@
				 if(move)
				 {
					 Library.SE_Play( se_Play.SOUND_SE_CURSOL );
				 }
				break;
			}
			
		default:
			break;
		}
		pAm.setErrMsg("PlayResult 4");
		return 0;
	 }
	 
	 
	 private void ResultStart()
	 {
		 Play.pEffect.createEffect(Effect.EFFECT_END ,
				MainData.SCREEN_WIDTH_DEF / 2 -83,
				MainData.SCREEN_HEIGHT_DEF / 2 - 15);
		 
		 this.PlayStep = PLAY_STEP_RESULT;
		 this.resultStep = RESULT_START;
		 
	 }
	 
	 public void PlayDraw(Graphics g)
	 {
		 pAm.setErrMsg("PlayDraw 1");
		 try
		 {
			 int type = 0;
			 if(bombBack)
			 {
				 type = UI.SHOP_BOMB;
			 }else
			 if(this.PlayStep == PLAY_STEP_RESULT &&
					 this.resultStep != RESULT_START)
			 {
				 type = UI.SHOP_NO;			 
			 }
			
			 UI.StageDraw( g , type , Library.getShop());
			 
			 pAm.setErrMsg("PlayDraw 2");
			 
			 {
				 if(this.PlayStep != PLAY_BOMB )
				 {
					 pGame.draw(g);
				 }
				 
				 pPlayer.PlayerDraw( g);
				 pAm.setErrMsg("PlayDraw 3");

				 if(titleDemo == false)
				 {
					 //タイマー
					
					 drawTimer( g);
	
					 
					 
					 
					 //利率
					 float par = pGame.getResultPar() * 100;
					 int NumX = MainData.SCREEN_WIDTH_DEF - 40;
					 int NumY = 4;
					 UI.drawNum( g ,
						 NumX ,
						 NumY ,
						 (int)par , 0.7f  , true ,false , true);
					 
					 //稼いだ金額
					 int parNum = pGame.getResultNowGold();
					 int ParNumX = MainData.SCREEN_WIDTH_DEF -
					 40 ;
					 int ParNumY = 54;
					 UI.drawNum( g ,
							 ParNumX ,
							 ParNumY ,
							 (int)parNum , 0.7f  , true ,true);
					 
					 g.setFontSize(Graphics.SIZE_TINY);
					 pAm.setErrMsg("PlayDraw 4");

					 
					 
					 
	
				 }
				 pEffect.drawEffectAll( g);
				 
				 {
					 
				 }
				 pAm.setErrMsg("PlayDraw 5");

				 if(this.PlayStep == PLAY_BOMB ||
						this.PlayStep == PLAY_STEP_GAME_OVER)
				 {			 
					 
					 pGame.drawBobm( g);
					 if(this.PlayStep == PLAY_STEP_GAME_OVER)
					 {
						 g.setShake( 0 , 0);
					 }else
					 {
						 g.setShake( Library.random.nextInt(Library.getDecodeX(20))-Library.getDecodeX(10) , Library.random.nextInt(Library.getDecodeY(20)-Library.getDecodeY(10)));
					 }
				 }
				 if(titleDemo == false)
				 {
	//				 pPlayer.PlayerDrawStatus( g , pGame.getLv(),
	//					 pGame.getNext() );
				 }
			 }
			 pAm.setErrMsg("PlayDraw6");

			 
			 switch ( this.PlayStep ) {
			 	
			 	case PLAY_STEP_WAIT:
			 	{
			 		this.drawPlayPushStart( g );
			 		break;
			 	}
			 	
			 	case PLAY_STEP_PLAYING:
			 	{
			 		if(titleDemo == false)
			 		{
				 		//UI.drawPushIcon( g , UI.PUSH_ICON_FLAE,this.ToushOK);			 		
				 		//UI.drawPushIcon( g , UI.PUSH_ICON_MENU,this.ToushMenu);
				 		UI.drawPushMenu( g );
			 		}
					 pAm.setErrMsg("PlayDraw 7");

			 		//炎決定範囲
			 		if(MainData.DEBUG_MODE_DRAW)
			 		{
			 			UI.drawFillRect(g, CHARA_POINT_X, CHARA_POINT_Y,
		 					CHARA_POINT_W, CHARA_POINT_H, 
		 					255, 55, 55, 150);
			 		}
			 		//メニュ範囲
			 		if(MainData.DEBUG_MODE_DRAW)
			 		{
			 			UI.drawFillRect(g, MENU_POINT_X, MENU_POINT_Y,
			 					MENU_POINT_W, MENU_POINT_H, 
		 					55, 55, 255, 150);
			 		}
			 		
			 		 int nowType = pPlayer.getNowFlameType();
					 
			 		 
			 		 //長尾氏火力
					 if(ShopData.FLAME_TOUCHE_LONG[nowType])
					 {
						 int power = pPlayer.getLongToush();
						 int powerMax =ShopData.FLAME_TOUCHE_LONG_MAX_TIME[nowType];
						 
	
						 int lvMax = ShopData.FLAME_TOUCHE_LONG_MAX_GAGE[nowType];
	
						 
						 UI.drawPowerGage( g , power, powerMax ,lvMax);
			 		 }
			 		
			 		//デバック武装切り替え
	//		 		if(MainData.DEBUG_MODE)
	//		 		{
	//		 			if(pPlayer.getNowFlameLen()==0)
	//		 			{
	//		 				UI.drawFillRect(g, MENU_POINT_X+MENU_POINT_W, MENU_POINT_Y,
	//			 					MENU_POINT_W, MENU_POINT_H, 
	//		 					255, 255, 35, 150);
	//		 			}else
	//		 			{
	//		 				UI.drawFillRect(g, MENU_POINT_X+MENU_POINT_W, MENU_POINT_Y,
	//			 					MENU_POINT_W, MENU_POINT_H, 
	//		 					34, 255, 255, 150);
	//
	//		 			}
	//		 		}
			 		
			 		break;
			 	}
			 	
	//		 	case PLAY_STEP_POZE:
	//		 	{
	//		 		this.drawPlayPoze(g);
	//		 		break;
	//		 	}
			 	
			 	case PLAY_STEP_TITLE_OK:
			 	{
			 		this.drawPlayTitleOk(g);
			 		break;
			 	}
			 	
			 	case PLAY_STEP_GAME_OVER:
			 	{
			 		this.drawPlayDead( g);
			 		break;
			 	}
			 	
			 	case PLAY_STEP_GAME_OVER_TITLE:
			 	{
			 		break;
			 	}
			 	
			 	case PLAY_STEP_NAME_OVER:
			 	{
			 		
			 		break;
			 	}
			 	
			 	case PLAY_STEP_LANK_IN:
			 	{
			 		//this.drawLankIn( g);
			 		break;
			 	}
			 	
			 	case PLAY_STEP_RESULT:
			 	{
			 		this.drawResult( g);
			 		break;
			 	}
			 	
			 	case PLAY_BOMB:
			 	{
			 		//drawBomb( g);
			 		break;
			 	}
			 }		
		 }catch (Exception e) {
			// TODO: handle exception
			 pAm.setErrMsg("PlayDraw 8");
			 Library.showDebugDialog(pAm.getActivity(), "ERR Play draw "+e.toString() );
			 if(MainData.DEBUG_MODE_SAVE)
			 {
				 //pAm.saveDebugErr();
				 pAm.terminateGame2();
			 }
		}
		 
		 pAm.setErrMsg("PlayDraw 9");

	 }
	 
	 private void drawTimer(Graphics g)
	 {
		 UI.drawTimerGage(g , GetTimer() , GetTimerType());
		 if(timerTypeTime >= 1)
		 {
			 timerTypeTime--;
			 if(timerTypeTime == 0)
			 {
				 timerType = UI.TIME_TYPE_NORMAL;				 
			 }
		 }
	 }
	 
	 
	 
	 private void drawPlayPushStart(Graphics g)
	 {
		 pAm.setErrMsg("drawPlayPushStart 1");

		 g.setFontSize(40);
		 UI.DrawString(g, "PUSH START", 
			MainData.SCREEN_WIDTH/2,
			MainData.SCREEN_HEIGHT/2,
			Graphics.getColorOfRGB( 244, 244, 0,255 ),
			 Graphics.getColorOfRGB( 20, 20, 20, 255 ),
			 UI.DRAW_TYPE_DBUL|UI.DRAW_TYPE_CENTER
				
		 );
		 g.setFontSize(Graphics.SIZE_TINY);

	 }
	 
	 
	 private void drawPlayTitleOk(Graphics g)
	 {	 
		 UI.FeedInOut( g , 100 );
		 
		 String msg[] = {"結果発表へ","やめとく"};

		 String msgMain[] = {"終了して結果発表に行って","いいかい？"};
		 
		 UI.WindowMsgCurAndMsg( g,
				 msg , Yes_No , System.currentTimeMillis() ,
				 msgMain );
	 }
	 
	 //
	 private void drawPlayDead(Graphics g)
	 {	 
	 }



	
	 
	//リザルト
	 private void drawResult(Graphics g)
	 {
		 switch (this.resultStep) {
			
			//●エフェクト
			case RESULT_START:
			{
				break;
			}
			
			case RESULT_SCOA:
			case RESULT_SCOA_WAIT:
			{
				UI.FeedInOut( g , 100 );	

				UI.drawResultIcon( g );
				int draw = 0;
				
				int drawPoint = 0;
				if(this.nowdisoResult >= Enemy.ENEMY_TYPE_LINE)
				{
					drawPoint = Enemy.ENEMY_TYPE_LINE-1;					
				}
				
				draw += drawPoint;
				for(int i = drawPoint ; i < Enemy.ENEMY_TYPE_MAX + drawPoint; i++)
				{
					
					if( draw >= this.nowdisoResult)
					{
						break;
					}
					
					if(this.Destroy[i] >= 1)
					{
						UI.drawResultEnemy(g, draw - drawPoint, i , this.Destroy[i], pGame.DestroyEnemyPoint[i]);
						draw++;
					}
				}
				
				break;
			}
			
			case RESULT_BANK_PAR_WAIT:
			{
				UI.FeedInOut( g , 100 );	

				UI.drawResultIcon( g );
				
				UI.drawResultEnemy( g,
					 this.getManey , Library.getManey() );
				break;
			}
			case RESULT_BANK_PAR:
			{
				UI.FeedInOut( g , 100 );	

				UI.drawResultIcon( g );
				
				UI.drawResultEnemy( g,
						 this.getManeyFinal , Library.getManey(), this.ResultPar , 0.0f);
				break;
			}
			
			case RESULT_BANK_PAR_ADD:
			case RESULT_BANK_PAR_ADD_WAIT:
			case RESULT_BANK:
			case RESULT_BANK_WAIT:
			{
				UI.FeedInOut( g , 100 );	

				UI.drawResultIcon( g );
				
				UI.drawResultEnemy( g,
						 this.getManeyFinal2 , Library.getManey(), this.ResultPar , this.ResultParAdd);
				break;
			}
			
			
			case RESULT_GO_TITLE:
			{
				
				if(bombBack)
				{
				}else
				{
					UI.FeedInOut( g , 100 );	
					
					String msg[] = {"ﾀｲﾄﾙ","ﾘﾄﾗｲ"};
	
					String msgMain[] = {"ﾀｲﾄﾙに戻る？","ﾘﾄﾗｲする？"};
					 
					UI.WindowMsgCurAndMsg( g,
						msg , this.Yes_No , System.currentTimeMillis() ,
						msgMain );		
				}
				break;
			}
			
			
		 }
	 }

	 
	 
	 //ダイアログOK挙動
	 public void DialogActionOk(int act)
	 {
		 switch (act) {
		 	
		 	case Library.DIALOG_RETRAY_TITLE:
		 	{
		 		GotoTitle = true;
		 	
		 		break;
		 	}
		 	case Library.DIALOG_TIME_SET:
		 	{
		 		SetTimer(0,-1);
		 
		 		Library.showDebugDialog(pAm.getActivity(),"終了");
		 		break;
		 	}
		default:
			break;
		}
	 }
	 private boolean GotoTitle = false;
	 public void DialogActionNo(int act)
	 {
		 switch (act) {
		 	
		 	case Library.DIALOG_RETRAY_TITLE:
		 	{
		 		this.init();
				Library.BGM_Play(pAm.getActivity(),pAm.getContext());

		 		break;
		 	}
		default:
			break;
		}
	 }
	 
	 private void DialogGoRetry()
	 {
		 if(Library.getDialogStop())
		 {
			 return;
		 }
		 if(bombBack)
			{
				String msg[] = {"お、おう","てやんでぃ！\nﾘﾄﾗｲだ！"};
		 		Library.showDialogOkNo
				(
					Library.DIALOG_RETRAY_TITLE,
					pAm.gflameMain.getActivity(),
					pAm,
					"GAME　OVER",
					"ﾀｲﾄﾙに戻る？\nﾘﾄﾗｲする？",
					msg);
			}else
			{
				String msg[] = {"ﾀｲﾄﾙに戻る","ﾘﾄﾗｲする"};
		 		Library.showDialogOkNo
				(
					Library.DIALOG_RETRAY_TITLE,
					pAm.gflameMain.getActivity(),
					pAm,
					"GAME　RETRY",
					"ﾀｲﾄﾙに戻る？\nﾘﾄﾗｲする？",
					msg);
			}
	}
}