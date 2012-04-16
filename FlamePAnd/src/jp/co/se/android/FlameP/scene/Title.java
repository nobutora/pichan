


package jp.co.se.android.FlameP.scene;

//import Exception;




import android.app.Activity;
import android.view.MotionEvent;
import jp.co.se.android.FlameP.Main.*;
import jp.co.se.android.FlameP.lib.DataSave;
import jp.co.se.android.FlameP.lib.Library;
import jp.co.se.android.FlameP.lib.ShopData;

import jp.co.se.android.FlameP.activity.ConstantClass;
import jp.co.se.android.FlameP.draw.*;

import jp.co.se.android.FlameP.Sound.*;


/**
 * MainCanvas
 * 
 */
public class Title
{
	protected MainData	pAm = null;
	
	Play	g_TitlePlay;
	

	 public void resume()
	 {
		 //Library.BGM_RESUME();
	 }
	 
	 
	public Title(MainData data)
	 {
		pAm = data;
		this.init();
		
		g_TitlePlay  = new Play(pAm);
		
	 }
	 public void dispose()
	 {
		 this.release();
	 }
	 
	 
	 private void init()
	 {
	 }
	 
	 
	 
	 private void release()
	 {	 
	 }
	 
	 private boolean TitleFirst = false;
	 private boolean shopFirst = false;
	 
	 private int Yes_No = 0;
	 private final int YES_COMMAND = 0;
	 private final int NO_COMMAND = 1;
	 
	 //[TitleCur]
	 private int TitleCur = 0;
	 private int CurAnime = 0;
	 
	 private static final int GAME_START 		= 0;
	 //private static final int GAME_LANKING = 1;
	 private static final int GAME_CHARA_DATA 	= 1;
	 private static final int GAME_SHOP 		= 2;	 
	 private static final int GAME_DATA_CONFIG 	= 3;	
	 private static final int GAME_HELP		 	= 4;
	 private static final int GAME_END		 	= 5;
	 public static final int MAX_TITLE_CUR 		= 6;
	 
	 public static final int MAX_TITLE_CUR2 	= 3;
	
	 public static final int ARROW_L_PUSH = 0;
	 public static final int ARROW_R_PUSH = 1;
	 

	 public static final int MAX_SHOP_CAT_CUR 	= 3;
	 
	 public static final int MAX_SHOP_DATA_CUR 	= 2;
	 
	 public static final int MAX_SETTING_CUR 	= 4;
	 
	 private static final int MENU_SHOP 	= 3;
	 //private static final int GAME_LANKING = 1;
	 private static final int MENU_BASE 	= 4;	
	 //private static final int MENU_WEPON 	= 5;
	 private static final int MENU_BACK 	= 5;
	 public static final int  MAX_MENU_CUR 	= 3;

	 
	 //
	 private int TitleLv = 1;
	 
	 
	 //[TitleStep]
	 public static final int TITLE_STEP_FIRST_LOG  		= 0 ;
	 public static final int TITLE_STEP_START   		= 1 ;
	 public static final int TITLE_STEP_HOME	  		= 3 ;
	 public static final int TITLE_STEP_HELP	  		= 4 ;
	 public static final int TITLE_STEP_UPGREADE 		= 5 ;
	 public static final int TITLE_STEP_SHOP_CHANGE	= 6 ;	 
	 public static final int TITLE_STEP_MENU			= 7 ;	 
	 public static final int TITLE_STEP_SHOP			= 8 ;
	 public static final int TITLE_STEP_GAME_END 		= 9 ;
	 
	 public static final int TITLE_STEP_SETTING 		= 10 ;
	 
	 public static final int TITLE_STEP_START_2   		= 11 ;

	 public static final int TITLE_STEP_SETTING_EQ 		= 12 ;
	 
	 public static final int TITLE_STEP_SHOP_EQ 		= 13 ;
	 
	 public static final int TITLE_STEP_DIALOG			= 14 ;
	 
	 public static final int TITLE_STEP_DATA_SHOW 		= 15 ;
	 
	 private int TitleStep = TITLE_STEP_FIRST_LOG;
	 
	 
	 public static final int CUR_ANIME_MAX = 10;
	 
	 
	 public void TitleMenuProc(int type)
	 {
		 switch (type) {
		 
			
		 
			case MainData.MENU_ID_HELP:
			{
				this.TitleStep = TITLE_STEP_HELP;
				break;
			}
			case MainData.MENU_ID_GOLD:
			{
				this.goGoldInit();
				
				//this.TitleStep = TITLE_STEP_GOLD_INIT;
		 		//this.Yes_No = NO_COMMAND;
				break;
			}
			
			case MainData.MENU_ID_HOME:
			{
				this.TitleStep = TITLE_STEP_HOME;
				break;
			}
			
			case MainData.MENU_ID_SOUND:
			{
				pAm.createBar(Library.getSound());
				break;				
			}

		default:
			break;
		}		 
	 }
	 
	 
	 
	 public int MainProc()
	 {

		 
		 if(this.TitleFirst == false)
		 {
			 
			 this.TitleFirst = true;
			 
			 //pAm.setSoftKey(0 , "�I��");			 
			 
			 
			 Library.setSound(Library.getSound());
			 
			 Library.setSe(Library.getSe());
			 
			 //Library.BGM_STOP();
			 

			 g_TitlePlay.init();
			 
			 g_TitlePlay.setTitleDemo();
			 if(this.shopFirst == false)
			 {
				 this.setShopList(this.nowShopList);
				 this.shopFirst = true;
			 }
			 this.cheackEquipOk();
			 
		 }
		 int downKey = pAm.getDownKey();
		 
		 int ret = 0;
		 
		 switch(this.TitleStep)
		 {
			 case TITLE_STEP_FIRST_LOG:
			 {
				 this.procTitleLog( COMMAND_TYPE_TITLE ,downKey );
				 break;
			 }
		 
		 	case TITLE_STEP_START:
		 	{
		 		ret = this.procTitleMain( COMMAND_TYPE_TITLE ,downKey );
		 		break;
		 	}
		 	
		 	case TITLE_STEP_GAME_END:
		 	{
		 		 this. procGameEnd();
		 		break;
		 	}
		 	
		 	case TITLE_STEP_HELP:
		 	{
		 		this.procHELP( downKey );
		 		break;
		 	}
		 	
		 	case TITLE_STEP_UPGREADE:
		 	{
		 		this.procUpgreade( downKey ); 
		 		break;
		 	}
		 			 	
		 	case TITLE_STEP_SHOP_CHANGE:
		 	{
		 		this.procShopChange( downKey );
		 		break;
		 	}
		 	
		 	case TITLE_STEP_MENU:
		 	{
		 		ret = this.procTitleMain( COMMAND_TYPE_MENU ,downKey );

		 		break;
		 	}
		 	
		 	case TITLE_STEP_SHOP:
		 	{
		 		procShoping(downKey);
		 		break;
		 	}
		 	
		 	case TITLE_STEP_SETTING:
		 	{
		 		procSetting(downKey );
		 		break;
		 	}
		 	
		 	case TITLE_STEP_DATA_SHOW:
		 	{
		 		procDataShow(downKey );
		 		break;
		 	}
		 	
		 	case TITLE_STEP_DIALOG:
		 	{
		 		if(Library.getDialogStop() == false)
		 		{
		 			this.TitleStep = TITLE_STEP_START;
		 		}
		 		break;
		 	}
		 	
		 	case TITLE_STEP_SETTING_EQ:
		 	case TITLE_STEP_SHOP_EQ:
		 	{
		 		int toushX = pAm.getToushDownX();
				 int toushY = pAm.getToushDownY();

				 boolean upper = false;
				 if(pAm.getToushEvent() == MotionEvent.ACTION_UP)
				 {
					 upper = true;
				 }
				
				 pAm.InitToush();
		 		 if(Setting.procSetting( g_TitlePlay, pAm.getActivity() ,
		 				 toushX , toushY , downKey , toushCommand , upper))
		 		 {
		 			//戻り
		 			if(this.TitleStep == TITLE_STEP_SETTING_EQ)
		 			{
		 				this.TitleStep = TITLE_STEP_SETTING;
		 			}else
	 				if(this.TitleStep == TITLE_STEP_SHOP_EQ)
		 			{
		 				this.TitleStep = TITLE_STEP_SHOP;
		 			}
		 			this.toushCommand = -1;
		 			return 0;
		 		 }
		 		 if(upper )
		 		 {
		 			 this.toushCommand = -1;
		 		 }
		 		
		 		 
		 		 int view = Setting.getNowView();
				 if(view == ConstantClass.ITEM_VIEW_BASE_SHOP)
				 {
					 this.TitleCommandToush(COMMAND_TYPE_BUY_BASE , toushX ,  toushY);
				 }else
				 {
			 		 this.TitleCommandToush(COMMAND_TYPE_ITEM, toushX ,  toushY);					 
				 }
		 		 break;
		 	}		 	
		 }
		 //g_TitlePlay.PlayProc();
		 
		 //dLibrary.TraseMsg("downKey"+downKey);
		 
		 return ret;
	 }
	 
	 public static final int TOUSH_COMAND_UP = 7;
	 
	 int toushCommand = 0;
	 int toushCommand_Arrow = -1;

	 
	 public static final int COMMAND_TYPE_TITLE = 1;
	 public static final int COMMAND_TYPE_MENU = 2;
	 public static final int COMMAND_TYPE_SHOP = 3;
	 public static final int COMMAND_TYPE_ITEM = 4;
	 public static final int COMMAND_TYPE_BUY_BASE = 5;
	 public static final int COMMAND_TYPE_SET_MENU = 6;
	 public static final int COMMAND_TYPE_DARA_SHOW = 7;

	 
	 private void TitleCommandToush(int type , int toushX , int toushY)
	 {
		 //int commandX = UI.TitleIcon_x[type];
		 //int commandY = UI.TitleIcon_y[type];
		 toushCommand = -1;
		 
		 int max = 0;
		 int[] x = null;
		 int[] y = null;
		 int[] w = null;
		 int[] h = null;
		 if(type == COMMAND_TYPE_TITLE)
		 {
			 max = MAX_TITLE_CUR; 
			 x = UI.Title_Title_Menu_x;
			 y = UI.Title_Title_Menu_y;
			 w = UI.Title_Menu_W;
			 h = UI.Title_Menu_H;
		 }else
		 if(type == COMMAND_TYPE_MENU)
		 {
			 max = MAX_MENU_CUR;
		 }else
		 if(type == COMMAND_TYPE_SHOP)
		 {
			 max = MAX_SHOP_CAT_CUR; 
			 x = UI.TitleShopCategory_x;
			 y = UI.TitleShopCategory_y;
			 w = UI.TitleShopCategory_W;
			 h = UI.TitleShopCategory_H;
		 }else
		 if(type == COMMAND_TYPE_SET_MENU)
		 {
			 max = MAX_SETTING_CUR; 
			 x 	   = UI.TitleSettingCategory_x;
			 y	   = UI.TitleSettingCategory_y;
			 w = UI.TitleSettingCategory_W;
			 h = UI.TitleSettingCategory_H;
		 }else
		 
		 if(type == COMMAND_TYPE_ITEM)
		 {
			 max = Setting.MAX_DRAW_KIND; 
			 x = Setting.IconX;
			 y = Setting.IconY;
			 w = Setting.Icon_Ws;
			 h = Setting.Icon_Hs;			 
		 }else
		 if(type == COMMAND_TYPE_BUY_BASE)
		 {
			 max = Setting.MAX_DRAW_KIND; 
			 x = new int[Setting.MAX_DRAW_KIND];
			 y = new int[Setting.MAX_DRAW_KIND];
			 x[0] = Setting.IconCenterX;
			 y[0] = Setting.IconCenterY;
			 int turnNum = Setting.MAX_DRAW_KIND-1;
			 x[turnNum] = Setting.IconX[turnNum];
			 y[turnNum] = Setting.IconY[turnNum];
			 w = Setting.Icon_Ws;
			 h = Setting.Icon_Hs;
		 }else
		 if(type == COMMAND_TYPE_DARA_SHOW)
		 {
			 max = MAX_SHOP_DATA_CUR ; 
			 x = UI.TitleDataShow_x;
			 y = UI.TitleDataShow_y;
			 w = UI.TitleDataShow_W;
			 h = UI.TitleDataShow_H;	
		 }

		 
		 
		 
		 for(int i = 0; i < max ; i++)
		 {
			 if(toushX >= Library.getDecodeXTrue(x[i]) && 
				 toushX <= Library.getDecodeXTrue(x[i]) + 
				 Library.getDecodeXTrue(w[i])				 
				 &&
				 toushY >= Library.getDecodeYTrue(y[i]) && 
				 toushY <= Library.getDecodeYTrue(y[i]) + 
				 Library.getDecodeYTrue(h[i]))
			 {
				 if(type == COMMAND_TYPE_MENU)
				 {
					 toushCommand = i + MAX_TITLE_CUR;
				 }else
				 {
					 toushCommand = i;
				 }
				 return;
			 } 
		 }
	 }

	 private void TitleCommandToushArrow(int toushX , int toushY)
	 {
		 //int commandX = UI.TitleIcon_x[type];
		 //int commandY = UI.TitleIcon_y[type];
		 toushCommand_Arrow = -1;
		 
		 int max = 2;
		 int[] x = {UI.ARROW_X_L , UI.ARROW_X_R};
		 int[] y = {UI.ARROW_Y , UI.ARROW_Y};
		 int w = UI.ARROW_W;
		 int h = UI.ARROW_H;
		 
		 
		 for(int i = 0; i < max ; i++)
		 {
			 if(toushX >= x[i] && 
				 toushX <= x[i] + w				 
				 &&
				 toushY >= y[i] && 
				 toushY <= y[i] + h)
			 {
				 toushCommand_Arrow = i;	
				 return;
			 } 
		 }
	 }
	 
	 
	 private int procTitleLog(int type , int Key)
	 {
		 if(feed >= 1)
		 {
			 feed--;
			 if(feed == 0)
			 {
				 this.TitleStep = TITLE_STEP_START;			 
				 Library.BGM_Play(pAm.getActivity(),pAm.getContext());
				 titleWait = TITLE_WAIT_MAX;
				 pAm.loadUIDataAfter();
				 
			 }
			 return 0;
		 }
		 if(this.titleWait >= 1)
		 {
			 this.titleWait--;
			 toushCommand = -1;
			 return 0;
		 }
		 
		 int toushX = pAm.getToushDownX();
		 int toushY = pAm.getToushDownY();

		 if(toushX >= 1 ||
			toushY >= 1)
		 {
			 feed = 5;
			 Library.SE_Play( se_Play.SOUND_SE_SELECT );
//			 int err[] = null;
//			 
//			 if(err[0]  >= 0)
//			 {
//				 
//			 }
			 if(MainData.DEBUG_MODE_SAVE)
	         {
	     	    String msg = Library.getErrMsg();
	     	    if(msg != null)
	     	    {
	     	    	Library.showDebugDialog( pAm.getActivity() , msg);
	     	    }
	     	    pAm.errSaveOk = true;
	     	    Library.setErrMsgSave("");
	         }
		 }
		
//		 
		 if(Key == MainData.KEY_BACK )
		 {
			 //if(MainData.DEBUG_MODE)
	         {
				 pAm.terminateGame2();
	         }
		 }
		 
		 return 0;
	 }
	 
	 
	 private int feed = 0;
	 private int titleWait = TITLE_WAIT_MAX;
	 private static final int TITLE_WAIT_MAX = 10;
	 private int titleMain_Page = 0;
	 
	 private int procTitleMain(int type , int Key)
	 {
		 if(this.titleWait >= 1)
		 {
			 this.titleWait--;
			 toushCommand = -1;
			 return 0;
		 }
		 
		 int toushX = pAm.getToushDownX();
		 int toushY = pAm.getToushDownY();

		 boolean back = false;
	 
		 if(pAm.getToushEvent() == MotionEvent.ACTION_UP)
		 {
			 pAm.InitToush();

			//Library.cgi_NetWork( pAm.getActivity() ,
				//	 Library.URL_SET_LANK , "ぴ" , "1000" );
			 
//			 if(this.toushCommand_Arrow == ARROW_L_PUSH)
//			 {
//				 if(titleMain_Page == 1)
//				 {
//					 titleMain_Page = 0;
//				 }
//				 return 0;
//			 }else
//			 if(this.toushCommand_Arrow == ARROW_R_PUSH)
//			 {
//				 if(titleMain_Page == 0)
//				 {
//					 titleMain_Page = 1;
//				 }
//				 return 0;
//			 }
			 
			 if(titleMain_Page == 0)
			 {
				 switch( this.toushCommand )
				 {
				 	case GAME_START:
				 	{
				 		Library.SE_Play( se_Play.SOUND_SE_CURSOL );
				 		this.TitleFirst = false;
				 		Library.SaveDataBase(pAm);
				 		this.toushCommand = -1;				 		
				 		this.cheackEquipOk();
				 		return this.TitleLv;
				 	}
				 	case GAME_CHARA_DATA:
				 	{
				 		Library.SE_Play( se_Play.SOUND_SE_CURSOL );
				 		this.TitleStep = TITLE_STEP_SETTING;
				 		break;
				 	}
				 	case GAME_SHOP:
				 	{
				 		this.TitleStep = TITLE_STEP_SHOP;
				 		Library.SE_Play( se_Play.SOUND_SE_CURSOL );
				 		break;
				 	}
				 	case GAME_HELP:
				 	{
				 		this.TitleStep = TITLE_STEP_DIALOG;
				 		pAm.gflameMain.GameHelpSite();
				 		Library.SE_Play( se_Play.SOUND_SE_CURSOL );
				 		break;
				 	}
				 	case GAME_DATA_CONFIG:
				 	{
				 		this.TitleStep = TITLE_STEP_DIALOG;
				 		Library.setDialogStop(true);
				 		pAm.createBar(Library.getSound());				 		
				 		Library.SE_Play( se_Play.SOUND_SE_SELECT );
				 		break;
				 	}
				 	case GAME_END:
				 	{
				 		this.goEnd();
				 		break;
				 	}
				 }
			 }else
			 if(titleMain_Page == 1)
			 {
				 switch( this.toushCommand )
				 {
				 	case GAME_SHOP:
				 	{
				 		this.TitleStep = TITLE_STEP_SHOP;
				 		break;
				 	}
				 	case GAME_HELP:
				 	{
				 		pAm.gflameMain.GameHelpSite();
				 		break;
				 	}
				 	case GAME_DATA_CONFIG:
				 	{
				 		pAm.createBar(Library.getSound());
				 		break;
				 	}
				 }
			 }
			 
	 		this.toushCommand = -1;
		 }
		 
		 
		 if(ConstantClass.moveActivityTime >= 1)
		 {
			 ConstantClass.moveActivityTime--;
		 }else
		 if(Key == MainData.KEY_BACK ||
			 back)
		 {
			 {
				 if(type == COMMAND_TYPE_TITLE)
				 {
					 this.goEnd();
				 }else if(type == COMMAND_TYPE_MENU)
				 {
					 this.TitleStep = TITLE_STEP_START;
					 Library.SE_Play( se_Play.SOUND_SE_CANCEL );
				 }
			 }
		 }
		 
		 this.TitleCommandToush(type, toushX ,  toushY);

		 //this.TitleCommandToushArrow( toushX , toushY);
		 
		 return 0;
	 }
	 
	 
	 
	 
	 
	 private int endTime = 0;
	 private static final int MAX_ENDTIME = 10;
	 private void procGameEnd()
	 {
		 endTime++;
		 if(endTime >= MAX_ENDTIME)
		 {
			 pAm.terminateGame2();
		 }
	 }
	 
	 private void goEnd()
	 {
		 if(Library.getDialogStop())
		 {
			 return;
		 }
		 Library.SE_Play( se_Play.SOUND_SE_CANCEL );

		 if(MainData.DEBUG_MODE)
		 {
			 Library.GameEnd(pAm);
		 }else
		 {
			 
			 String msg[] = {"はい","いいえ"};
	 		 Library.showDialogOkNo
			 (
				Library.DIALOG_GAME_END,
				pAm.gflameMain.getActivity(),
				pAm,
				"終了",
				"ゲームを終了しますか？",
				msg);
		 }
	 }
	 
	 
	 public void goGoldInit()
	 {
		 if(Library.getDialogStop())
		 {
			 return;
		 }
		 Library.SE_Play( se_Play.SOUND_SE_SELECT );

		 String msg[] = {"はい","いいえ"};
 		 Library.showDialogOkNo
		 (
			Library.DIALOG_GOLD_INIT,
			pAm.gflameMain.getActivity(),
			pAm,
			"初期化",
			"データを初期化しますか？",
			msg);
	 }
	 private void goGoldInit2()
	 {
		 Library.SE_Play( se_Play.SOUND_SE_SELECT );

		 String msg[] = {"お、おう","やっぱやめる"};
		 
		 Library.setDialogStop(false);
		 
 		 Library.showDialogOkNo
		 (
			Library.DIALOG_GOLD_INIT2,
			pAm.gflameMain.getActivity(),
			pAm,
			"初期化",
			"本当に初期化していいのですな？",
			msg);
	 }
	 private void goGoldInit3()
	 {
		 Library.SE_Play( se_Play.SOUND_SE_SELECT );
		 Library.setDialogStop(false);
		 String msg[] = {"しつこい！","や、やめとく"};
 		 Library.showDialogOkNo
		 (
			Library.DIALOG_GOLD_INIT3,
			pAm.gflameMain.getActivity(),
			pAm,
			"初期化",
			"2度と元に戻せませんぞ？",
			msg);
	 }
	 
	 private void goGoldInit4()
	 {
		 Library.SE_Play( se_Play.SOUND_SE_SELECT );
		 Library.InitData(pAm);
		 Library.setDialogStop(false);
		 Library.showDialog(pAm.gflameMain.getActivity(), "初期化完了",
			"初期化しました！貧乏ですな！");		 
	 }

	 //★上スイッチ
	 public static final int SHOP_SWICHE_WEPON = 0;
	 public static final int SHOP_SWICHE_BASE = 1;
	 public static final int SHOP_SWICHE_BACK = 2;
	 
	 public static final int MAX_SHOP_SWICHE = 3;	 
	 private boolean ShopSwiche[] = new boolean[MAX_SHOP_SWICHE];

	 public static final int SHOP_SWICHE_X = 3;
	 public static final int SHOP_SWICHE_Y = 67;
	 
	 
	 
	 //★コマンド部分
	 
	 public static final int MAX_SHOP_COMMAND = 4;	 
	 //public static final int SHOP_COMMAND_X = 3;
	 public static final int SHOP_COMMAND_X = 3;

	 public static final int SHOP_COMMAND_Y = SHOP_SWICHE_Y + 33;
	 public static final int SHOP_COMMAND_W = MainData.SCREEN_WIDTH - 6;
	 public static final int SHOP_COMMAND_H = 32;
	 public static final int SHOP_COMMAND_SPACE = 36;
	 
	 private boolean ShopCommand[] = new boolean[MAX_SHOP_COMMAND];
	 private int nowShopCommandButton = 0;

	 //★行動ボタン
	 //左右
	 public static final int ACT_SWICHE_H = 32;
	 public static final int ACT_SWICHE_W = 32;
	 public static final int ACT_SWICHE_X = 73;
	 public static final int ACT_SWICHE_X2= MainData.SCREEN_WIDTH - 73 - ACT_SWICHE_W ;
	 public static final int ACT_SWICHE_Y = MainData.SCREEN_HEIGHT - ACT_SWICHE_H - 3 ;
	 public static final int ACT_SWICHE_Y2= 239 ;
	 

	 //購入　装着
	 public static final int ACT_SWICHE_W3 = 64;
	 public static final int ACT_SWICHE_X3= (MainData.SCREEN_WIDTH - 3) / 2 - ACT_SWICHE_W3/2 ;

	 public static final int SHOP_ACT_SWICHE_LEFT  = 0;
	 public static final int SHOP_ACT_SWICHE_RIGHT = 1;
	 public static final int SHOP_ACT_SWICHE_BUY_EQ= 2;
	 public static final int MAX_SHOP_ACT_SWICHE  = 3;
	 private boolean ShopActSwiche[] = new boolean[MAX_SHOP_ACT_SWICHE];


	 //ショップページ
	 private int shopNowPage = 0;
	 private int shopMaxPage = 0;
	 private int shopDarawNumMax = 0;
	 private int nowShopList = SHOP_SWICHE_WEPON;
	 private int shopDrawLineNupId[] = new int[MAX_SHOP_COMMAND];	 
	 
	 
	 private static final int SHOP_UPGREADE	 	= 0;
	 private static final int SHOP_WAPON	 	= 1;	
	 private static final int SHOP_BACK		 	= 2;
	 
	 private void procShoping(int downKey)
	 {
		 int toushX = pAm.getToushDownX();
		 int toushY = pAm.getToushDownY();

		 boolean back = false;
	 
		 if(pAm.getToushEvent() == MotionEvent.ACTION_UP)
		 {
			 //pAm.InitToush();
			 switch( this.toushCommand )
			 {
			 	case SHOP_UPGREADE:
			 	{
			 		Setting.setDrawList( pAm.getActivity() , ConstantClass.ITEM_VIEW_BASE_SHOP , true);			 					 		
			 		this.TitleStep = TITLE_STEP_SHOP_EQ;
			 		break;
			 	}
			 	case SHOP_WAPON:
			 	{
			 		//ConstantClass.setItem_View(ConstantClass.ITEM_VIEW_SHOP);
			 		//pAm.gflameMain.gotoItemList();
			 		
			 		Setting.setDrawList( pAm.getActivity() , ConstantClass.ITEM_VIEW_SHOP ,true);			 					 		
			 		this.TitleStep = TITLE_STEP_SHOP_EQ;
			 		break;
			 	}
			 	case SHOP_BACK:
			 	{
			 		back = true;
			 		break;
			 	}
			 }
			 pAm.InitToush();
		 }
		 
		 if(downKey == MainData.KEY_BACK ||
			 back)
		 {
			 this.TitleStep = TITLE_STEP_START;
			 Library.SE_Play( se_Play.SOUND_SE_CANCEL );
		 }
		 
		 this.TitleCommandToush(COMMAND_TYPE_SHOP , toushX ,  toushY);
	 }
	 private static final int SETTING_EQUIP	 	= 0;
	 private static final int SETTING_SHOP	 	= 1;	
	 private static final int SETTING_DATA		 	= 2;	 
	 private static final int SETTING_BACK		 	= 3;
	 private void procSetting(int downKey)
	 {
		 int toushX = pAm.getToushDownX();
		 int toushY = pAm.getToushDownY();

		 boolean back = false;
	 
		 if(pAm.getToushEvent() == MotionEvent.ACTION_UP)
		 {
			 //pAm.InitToush();
			 switch( this.toushCommand )
			 {
			 	case SETTING_EQUIP:
			 	{
			 		//ConstantClass.setItem_View(ConstantClass.ITEM_VIEW_SKILL);
			 		//pAm.gflameMain.gotoItemList();
			 		try
			 		{
			 			Setting.setDrawList( pAm.getActivity() , ConstantClass.ITEM_VIEW_SKILL,true);			 					 		
			 		}catch (Exception e) {
						// TODO: handle exception
			 			e.toString();
					}
			 		
			 		this.TitleStep = TITLE_STEP_SETTING_EQ;
			 		break;
			 	}
			 	case SETTING_SHOP:
			 	{
			 		Setting.setDrawList( pAm.getActivity() , ConstantClass.ITEM_VIEW_BASE_SET , true);			 					 		
			 		this.TitleStep = TITLE_STEP_SETTING_EQ;
			 		break;
			 	}
			 	case SETTING_DATA:
			 	{
			 		this.TitleStep = TITLE_STEP_DATA_SHOW;
			 		break;
			 	}
			 	case SETTING_BACK:
			 	{
			 		back = true;
			 		break;
			 	}
			 }
			 pAm.InitToush();
		 }
		 
		 if(downKey == MainData.KEY_BACK ||
			 back)
		 {
			 this.TitleStep = TITLE_STEP_START;
			 Library.SE_Play( se_Play.SOUND_SE_CANCEL );
		 }
		 
		 this.TitleCommandToush(COMMAND_TYPE_SET_MENU , toushX ,  toushY);
	 }
	 
	 private static final int DATA_LANK	 	= 0;
	 private static final int DATA_TURN	 	= 1;	
	 //データ閲覧
	 private void procDataShow(int downKey)
	 {
		 int toushX = pAm.getToushDownX();
		 int toushY = pAm.getToushDownY();

		 boolean back = false;
	 
		 if(pAm.getToushEvent() == MotionEvent.ACTION_UP)
		 {
			 //pAm.InitToush();
			 switch( this.toushCommand )
			 {
			 	case DATA_LANK:
			 	{
			 		//ﾗﾝｷﾝｸﾞ
			 		pAm.gflameMain.openLankList();
			 		break;
			 	}
			 	case DATA_TURN:
			 	{
			 		back = true;
			 		break;
			 	}
			 }
			 pAm.InitToush();
		 }
		 
		 if(downKey == MainData.KEY_BACK ||
			 back)
		 {
			 this.TitleStep = TITLE_STEP_SETTING;
			 Library.SE_Play( se_Play.SOUND_SE_CANCEL );
		 }
		 
		 this.TitleCommandToush(COMMAND_TYPE_DARA_SHOW , toushX ,  toushY);
	 }
	 
	 private void swicheClear()
	 {
		 for(int i = 0 ; i < MAX_SHOP_SWICHE; i++)
		 {
			 this.ShopSwiche[i] = false;
		 }
		 
		 for(int f = 0 ; f < MAX_SHOP_COMMAND; f++)
		 {
			 this.ShopCommand[f] = false;
		 }
		 
		 for(int f = 0 ; f < MAX_SHOP_ACT_SWICHE; f++)
		 {
			 this.ShopActSwiche[f] = false;
		 }
		 
	 }
	 
	 
	 private boolean shopSeicheMove()
	 {
		 boolean listMove = false;
		 if(this.ShopSwiche[SHOP_SWICHE_WEPON])
		 {
			 this.nowShopList = SHOP_SWICHE_WEPON;
			 listMove = true;
		 }else
		 if(this.ShopSwiche[SHOP_SWICHE_BASE])
		 {
			 this.nowShopList = SHOP_SWICHE_BASE;
			 listMove = true;
		 }else
		 if(this.ShopSwiche[SHOP_SWICHE_BACK])
		 {
			return true;
		 }
		 
		 if(listMove)
		 {
			 this.setShopList(this.nowShopList);
		 }
		 return false;
	 }
	 
	 
	 public static final int SELECT_NO 		= 0 ;
	 public static final int SELECT_OK 		= 1 ;
	 public static final int SELECT_EQUIP 	= 2 ;
	 public static final int SELECT_NORMAL 	= 3 ;
	 
	 private int getShopCommandStatus()
	 {
		 if(nowShopCommandButton == -1)
		 {
			 return SELECT_NO;
		 }
		 
		 int idx = nowShopCommandButton + 
		 	MAX_SHOP_COMMAND * this.shopNowPage;
		 int ID = this.shopDrawLineNupId[idx];			 
		 
		 if(this.nowShopList == SHOP_SWICHE_WEPON)
		 {
			 long time = DataSave.getWeponRemainderTime(ID);
	
			 if(Library.nowEquip() == ID)
			 {
				 return SELECT_EQUIP;
			 }
			 if(time >= 1 || ShopData.FLAME_FREE[ID])
			 {
				 return SELECT_OK;			 
			 }
		 }else
		 if(this.nowShopList == SHOP_SWICHE_BASE)
		 {
			 String str[] = pAm.dqSave.getDataBaseShopID(ID);
			 
			 int buy = Integer.parseInt( str[DataSave.BASE_IDX_BUY] );
			 
			 if(buy >= 1 )
			 {
				 return SELECT_OK;			 
			 }
		 }
		 
		 
		 return SELECT_NORMAL;
	 }
	 
	 private void cheackEquipOk()
	 {
//		 long time = DataSave.getWeponRemainderTime(Library.nowEquip());
//		 
//		 if(time <= -1)
//		 {
//			Library.setEquip(1); 
//			Library.SaveDataBase(pAm);
//		 }
	 }
	 private void CommandSwicheInit()
	 {
		 nowShopCommandButton = -1;
		 ShopCommand = new boolean[MAX_SHOP_COMMAND];
	 }
	 
	 private boolean shopActMove()
	 {
		 boolean listMove = false;
		 if(this.ShopActSwiche[SHOP_ACT_SWICHE_LEFT])
		 {
			 this.shopNowPage--;
			 if(this.shopNowPage <= -1 )
			 {
				 this.shopNowPage = this.shopMaxPage-1;
			 }
			 CommandSwicheInit();
		 }else
		 if(this.ShopActSwiche[SHOP_ACT_SWICHE_RIGHT])
		 {
			 this.shopNowPage++;
			 if(this.shopNowPage >= this.shopMaxPage)
			 {
				 this.shopNowPage = 0;
			 }
			 CommandSwicheInit();
		 }else
		 if(this.ShopActSwiche[SHOP_ACT_SWICHE_BUY_EQ])
		 {
			 
			 int type = getShopCommandStatus();
			 
			 
			 
			 if(type == SELECT_NO)
			 {			 
			 }else
			 if(type == SELECT_OK)
			 {

				if(this.nowShopList == SHOP_SWICHE_WEPON)
				{
					int idx = nowShopCommandButton + 
				 	MAX_SHOP_COMMAND * this.shopNowPage;
					int ID = this.shopDrawLineNupId[idx];
					//購入積み　装備
					Library.setEquip(ID);
					//セーブ
					Library.SaveDataBase(pAm);
				}
				Library.SE_Play( se_Play.SOUND_SE_SELECT );

			 }else
			 if(type == SELECT_NORMAL)
			 {
				 int err = 0;
				 try
				 {
					 int maney = 0;
					 err = 1;
					 int idx = nowShopCommandButton + 
					 	MAX_SHOP_COMMAND * this.shopNowPage;
					 int ID = this.shopDrawLineNupId[idx];
					 err = 2;
					 boolean upGreade = false;
					 
					 //金チェック
					 if(this.nowShopList == SHOP_SWICHE_WEPON)
					 {
						 maney = ShopData.FLAME_PRICE[ID];	
					 }else
					 if(this.nowShopList == SHOP_SWICHE_BASE)						 
					 {
						 if(ShopData.BASE_UPGREADE[ID])
						 {
							 maney = ShopData.NEXT_GOLD[Library.nowLank()];
						 }else
						 {
							 maney = ShopData.BASE_PRICE[ID];	
						 }
					 }
					 
					 if( Library.getManey() < maney )
					 {
						 Library.SE_Play( se_Play.SOUND_SE_CANCEL );
						 return false;
					 }
					 
					 //購入
					 if(this.nowShopList == SHOP_SWICHE_WEPON)
					 {
						 DataSave.setDataBaseWeponTime(ID, ShopData.SHOP_LENTAL_TIME);
					 }else
					 {
						 
						 //ショップ系購入
						 if(ShopData.BASE_UPGREADE[ID])
						 {
							 Library.setLank(Library.nowLank()+1);
							 upGreade = true;
						 }else
						 {
							 DataSave.setDataBaseShop(ID);							 
						 }
					 }
					 
					 Library.addManey(-maney);
					 err = 3;
					 //セーブ
					 Library.SaveDataBase(pAm);
					 err = 4;
					 pAm.dqSave.writeDBAll( pAm.getdb() );
					 err = 5;
					 Library.SE_Play( se_Play.SOUND_SE_GOLD );
					 if(upGreade)
					 {
						 //アップグレードへ
						 this.TitleStep = TITLE_STEP_UPGREADE;
						 this.resultCount = 0;
					 }
				 }catch (Exception e) {
					// TODO: handle exception
					 Library.showDebugDialog(pAm.getActivity(), "BuyErr "+err);
				}
			 }
		 }
		 
		 return false;
	 }
	 
	 
	 private void setShopList(int list)
	 {
		 this.nowShopList = list;
		 this.shopNowPage = 0;
		 
		 if(this.nowShopList == SHOP_SWICHE_WEPON)
		 {
			 this.shopDarawNumMax = this.SetShopLineNup();			 
			 this.shopMaxPage = this.shopDarawNumMax / MAX_SHOP_COMMAND + 1;
		 }else
		 if(this.nowShopList == SHOP_SWICHE_BASE)
		 {
			 this.shopDarawNumMax = this.SetShopLineNup();			 
			 this.shopMaxPage = this.shopDarawNumMax / MAX_SHOP_COMMAND + 1;
		 }
		 CommandSwicheInit();
		 
	 }
	 
	 private int SetShopLineNup()
	 {
		 int idx = 0;
		 
		 int max = ShopData.FLAME_TYPE_MAX;
		 if(this.nowShopList == SHOP_SWICHE_BASE)
		 {
			 max = ShopData.BASE_TYPE_MAX;
		 }
		 this.shopDrawLineNupId = new int[max];

		 for(int i = 1;
		 		i < max; i++)
		 {
			 if(this.nowShopList == SHOP_SWICHE_WEPON)
			 {
				 if(ShopData.FLAME_NO_SHOPING[i])
				 {
					 continue;
				 }
				 if(Library.nowLank() < ShopData.FLAME_RANK[i] )
				 {
					 continue;
				 }
			 }else
			 if(this.nowShopList == SHOP_SWICHE_BASE)
			 {
				 if(Library.nowLank() < ShopData.BASE_RANK[i] )
				 {
					 continue;
				 }
			 }
			 this.shopDrawLineNupId[idx]=i;
			 idx++;
		 }
		 return idx;
	 }
			 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 public static final int MAXHELP = 7;

	 public static final int TOIAWASE = 5;
	 
	 
	 private int NowHelp = 0;
	 
	 private void procHELP(int Key)
	 {
		 boolean back = false;
		 boolean move = false;
		 
		 int toushX = pAm.getToushPushX();
		 boolean ok = false;
		 
		 int sideSize = 50;
		 
		 sideSize = (int)((float)sideSize * (float)MainData.Width_Size);

		 
		 if(toushX >= 0)
		 {
			 if(toushX <= sideSize)
			 {
				 NowHelp--;
				 move = true; 
			 }else
			 if(toushX >= MainData.SCREEN_WIDTH - sideSize)
			 {
				 NowHelp++;
				 move = true; 				 
			 }else
			 {
				 ok = true;
			 }
		 }
		 
		 
		 if(Key == MainData.KEY_LEFT )
		 {
			 NowHelp--;
			 move = true;
		 }else
		 if(Key == MainData.KEY_RIGHT )
		 {
			 NowHelp++;
			 move = true;
		 }
		 
		 if(Key == MainData.KEY_SELECT || ok)
		 {
			 Library.SE_Play( se_Play.SOUND_SE_SELECT );
			 
			 {
				 back = true;
			 }			 					 
		 }
		 else if(Key == MainData.KEY_BACK  )
		 {
			 //Library.SE_Play( se_Play.SOUND_SE_CANCEL );
			 back = true;
		 }
		 
		 if(move)
		 {
			 Library.SE_Play( se_Play.SOUND_SE_CURSOL );			 
			 if(this.NowHelp <= -1)
			 {
				 this.NowHelp = MAXHELP - 1;
			 }else	
			 if(this.NowHelp >= MAXHELP)
			 {
				this.NowHelp = 0;
			 }
		 }
		 
		 if(back)
		 {
			 this.gotoFirst();
		 }
		 
	 }
	 
	 private int resultCount = 0;
	 
	 private void procUpgreade(int Key)	 
	 {
		if(this.resultCount >= 26  )
		{
			int toushX = pAm.getToushDownX();
			if(Key == MainData.KEY_BACK||
				toushX >= 1)
			{
				this.TitleStep = TITLE_STEP_SHOP;
			}
		}
		
		if(this.resultCount == 10)
		{
			//Library.SE_Play( se_Play.SOUND_SE_GUREAD_UP );
		}
		this.resultCount++;
	 }
	 
	 
	 
	 
	 public void procVarsionUp(int Key)
	 {
		 int toushX = pAm.getToushPushX();
			 
		 if(Key == MainData.KEY_SELECT ||
			Key == MainData.KEY_BACK||
			Key == MainData.KEY_SOFT2||
			toushX >= 0)
		 {
			 
			 //IApplication.getCurrentApp().launch( IApplication.LAUNCH_VERSIONUP, null );
			 
			 
			 this.gotoFirst();
			 Library.SE_Play( se_Play.SOUND_SE_SELECT );
		 }
	 }

	 private int selectShop = 0;	 
	 private void procShopChange(int Key)
	 {
		 boolean move = false;
		 boolean back = false;
		 
		 int toushX = pAm.getToushPushX();
		 
		 int sideSize = 80;
		 
		 sideSize = (int)((float)sideSize * (float)MainData.Width_Size);

		 
		 boolean ok = false;
		 if(toushX >= 0)
		 {
			 if(toushX <= sideSize)
			 {
				 selectShop--;
				 move = true; 
			 }else
			 if(toushX >= MainData.SCREEN_WIDTH - sideSize)
			 {
				 selectShop++;
				 move = true; 				 
			 }else
			 {
				 ok = true;
			 }
		 }
		 
		 if( ok)
		 {
			 Library.SE_Play( se_Play.SOUND_SE_SELECT );
			 back = true;
			 Library.setShop(this.selectShop);
			 Library.SaveDataBase(pAm);
		 }
		 else if(Key == MainData.KEY_BACK  )
		 {
			 back = true;
		 }
		 if(back)
		 {
			 this.gotoFirst();
		 }
		 
		 if(move)
		 {
			 Library.SE_Play( se_Play.SOUND_SE_CURSOL );
			 
			 if(this.selectShop > Library.nowLank())
			 {
				 this.selectShop = Library.nowLank();
			 }else
			 if(this.selectShop <= 0)
			 {
				 this.selectShop = 0;
			 }			 
		 }
	 }
	 
	 
	 
	 private void gotoFirst()
	 {
		 this.TitleStep = TITLE_STEP_START;
		 //pAm.setSoftKey(0 ,"�I��");
		 this.setSoundSoft();
		 pAm.InitToush();
		 
		 this.titleWait = TITLE_WAIT_MAX;
	 }
	 
	 private void setSoundSoft()
	 {
		 //String st = "����"+(Library.MAX_VAL - Library.getSound());
		 //pAm.setSoftKey(1 , st);
	 }
	 
	//ダイアログOK挙動
	 public void DialogActionOk(int act)
	 {
		 switch (act) {
		 	case Library.DIALOG_GAME_END:
		 	{
		 		Library.GameEnd(pAm);
		 		break;	
		 	}
		 	
		 	case Library.DIALOG_GOLD_INIT:
		 	{
		 		goGoldInit4();
		 		//goGoldInit2();
		 		break;
		 	}
		 	
		 	case Library.DIALOG_GOLD_INIT2:
		 	{
		 		goGoldInit3();
		 		break;
		 	}
		 	case Library.DIALOG_GOLD_INIT3:
		 	{
		 		goGoldInit4();
		 		break;
		 	}
		default:
			break;
		}
		 
	 }
	 
	 
	//ダイアログNO挙動
	 public void DialogActionNo(int act)
	 {
		 switch (act) {
		 
		default:
			break;
		}
		 
	 }
	 
	 public void setStep(int step)
	 {
		 this.TitleStep = step;
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 private int drawCounter = 0;
	 
	 public void MainDraw(Graphics g)
	 {
		 //g_TitlePlay.PlayDraw(g);
		 
		 if(this.TitleStep == TITLE_STEP_FIRST_LOG)
		 {
			 this.drawTitleFirstLog( g );
			 return ;
		 }
		 
		 //UI.TitleDraw(g);
		 boolean noShop = false;
		 if(this.TitleStep == TITLE_STEP_SETTING_EQ ||
		 	this.TitleStep == TITLE_STEP_SHOP_EQ)
	 	 { 
			 if(Setting.getNowView() == ConstantClass.ITEM_VIEW_SHOP)
			 {
				 noShop = true;				 
			 }else
			 if(Setting.getNowView() == ConstantClass.ITEM_VIEW_SKILL )
			 {
				 noShop = true;				 
			 }
	 	 }
		 
		 
		 if( noShop == false )
		 {
			 drawCounter++;
			 
			 int shop = Library.getShop();
			 
			 if(UI.DrawShopTitle(g , drawCounter , shop ))
			 {
				 drawCounter = 0;
			 }
			 if(drawCounter >= 30000)
			 {
				 drawCounter = 0;
			 }
			 
			 //UI.drawTitleLog(g);
		 }
		 
		 
		 if(this.TitleStep == TITLE_STEP_SETTING_EQ ||
		 	this.TitleStep == TITLE_STEP_SHOP_EQ)
	 	 { 
	 		Setting.drawSetting(g , this.toushCommand , g_TitlePlay );
	 		return;
	 	 }

		 
		 UI.DrawTitleUnderMenu( g );
		 {
			 UI.drawTitleManey(g , Library.getManey());
		 }
		 switch(this.TitleStep)
		 {
		 
		 
		 	case TITLE_STEP_START:		 		
		 	case TITLE_STEP_DIALOG:
		 	{
 		 		this.drawTitleMain( g );
				break;
		 	}
		 	
		 	case TITLE_STEP_GAME_END:
		 	{
 		 		this.drawTitleEnd( g );
		 		break;
		 	}
		 	
		 	case TITLE_STEP_HELP:
		 	{
		 		this.drawHelp( g );
		 		break;
		 	}
		 	
		 	case TITLE_STEP_UPGREADE:
		 	{
		 		this.drawUpgreade( g );
		 		break;
		 	}
//		 	
//		 	case TITLE_STEP_SHOP_CHANGE:
//		 	{
//		 		drawShopChange(g);
//		 		break;
//		 	}
		 	
		 	case TITLE_STEP_MENU:
		 	{
				 UI.drawTitleCommand(Title.COMMAND_TYPE_MENU , g , this.toushCommand , this.titleWait);

		 		break;
		 	}
		 	
		 	case TITLE_STEP_SHOP:
		 	{
		 		UI.drawIconCommand(TITLE_STEP_SHOP,  g,this.toushCommand );
		 		
		 		
		 		//drawShoping(g);
		 		break;
		 	}
		 	
		 	case TITLE_STEP_SETTING:
		 	{
		 		UI.drawIconCommand(TITLE_STEP_SETTING,  g,this.toushCommand );
		 		break;		 		
		 	}
		 	
		 	case TITLE_STEP_DATA_SHOW:
		 	{
		 		drawDataShow( g );
		 		break;
		 	}
		 	
		 	
		 }
		 
	 }
	 private void drawDataShow(Graphics g)
	 {
 		//ウィンドウ
		UI.DrawWindow(g, 0, 450 , 480, 114 );
 		//総収入
		float big = 0.7f ;		 
		UI.drawNum( g , 445  , 455,
			Library.getManeyALL() , big , true , true);	
 		//総鳥数		 
		UI.drawNum( g , 445  , 506 ,
			Library.getDestroyALL() , big , false , false,
			false , true );	 		

		 //文字
		 int maney_str_x = 10;
		 int maney_str_y = 492 ;
		 g.setFontSize(40);
		 UI.DrawString(g, "総収入", maney_str_x, maney_str_y,
			 Graphics.getColorOfRGB( 255, 15, 10,255 ),
			 Graphics.getColorOfRGB( 22, 22, 22,255 ) ,
			 UI.DRAW_TYPE_DBUL );
		 int bard_str_x = maney_str_x;
		 int bard_str_y = maney_str_y + 54 ;			 
		 g.setFontSize(40);
		 UI.DrawString(g, "総鳥数", bard_str_x, bard_str_y,
			 Graphics.getColorOfRGB( 255, 15, 10,255 ),
			 Graphics.getColorOfRGB( 22, 22, 22,255 ) ,
			 UI.DRAW_TYPE_DBUL );
		 
 		UI.drawIconCommand(TITLE_STEP_DATA_SHOW,  g,this.toushCommand );		 
	 }
	 
	 private void drawTitleFirstLog(Graphics g)
	 {
		 UI.DrawTitleMenuAll( g );
		 
		 
		 UI.drawTitleLogGrahe( g );
		 
		 if(this.titleWait >= 1)
		 {
			 UI.FeedInOut(g, this.titleWait * 30);
		 }
		 
		 if(feed >= 1)
		 {
			 UI.FeedInOut(g, (5 - this.feed) * 60);			 
		 }
	 }
	 
	 private void drawTitleMain(Graphics g)
	 {
		 
		 //UI.drawPushIcon( g , UI.PUSH_ICON_SOUND , soundOK);
		 //UI.drawTitleCommand( Title.COMMAND_TYPE_TITLE , g , this.toushCommand , this.titleWait);
		 
		 //if(titleMain_Page == 0)
		 {
			 UI.drawIconCommand(TITLE_STEP_START,  g,this.toushCommand );
		 }
		 
		 
		 
		 
		 
//		 else
//		 if(titleMain_Page == 1)
//		 {
//			 UI.drawIconCommand(TITLE_STEP_START_2,  g,this.toushCommand );			 
//		 }
	 		
		 //矢印
//		 int icon[] = { UI.ICON_DAME , UI.ICON_OK };
//		 
//		 
//		 if(titleMain_Page == 0)
//		 {
//			 icon[0] = UI.ICON_DAME ;
//			 icon[1] = UI.ICON_OK ;
//		 }else
//		 if(titleMain_Page == 1)
//		 {
//			 icon[0] = UI.ICON_OK ;
//			 icon[1] = UI.ICON_DAME ;
//		 }
//		 UI.drawArowPage( g , icon );
		 if(this.titleWait >= 1)
		 {
			 UI.FeedInOut(g, this.titleWait * 30);
		 }
		 
		
		 
		 
	 }
	 
	 private void drawTitleEnd(Graphics g)
	 {
		 int alphe = endTime * 30;
		 UI.FeedInOut(g, alphe , 0, 0, 0);
	 }
	 

	 private void drawHelp(Graphics g)
	 {		 	
		 UI.FeedInOut( g , 200 );	
		 UI.drawHelpStr( g,
				 this.NowHelp );
	 }

	 private void drawUpgreade(Graphics g)
	 {		 	
		 UI.FeedInOut( g , 200 );
			
			
			
		if(this.resultCount <= 3)
		{
			UI.drawShop( g , Library.nowLank()-1,
						MainData.SCREEN_WIDTH / 2 , MainData.SCREEN_HEIGHT / 2);
		}
		else
		if(this.resultCount <= 10)
		{
			if(this.resultCount % 2 == 0)
			{
				UI.drawShop( g , Library.nowLank()-1 ,
						MainData.SCREEN_WIDTH / 2 , MainData.SCREEN_HEIGHT / 2);
			}
		}
		else
		if(this.resultCount <= 18)
		{				
			if(this.resultCount % 2 == 0)
			{
				UI.drawShop( g , Library.nowLank()-1 ,
						MainData.SCREEN_WIDTH / 2 , MainData.SCREEN_HEIGHT / 2);
			}

			int alphe = (this.resultCount - 17) * 30;
			UI.FeedInOut( g , alphe , 255, 255, 255);
		}
		else
		if(this.resultCount <= 26)
		{
			UI.drawShop( g , Library.nowLank() ,
				MainData.SCREEN_WIDTH / 2 , MainData.SCREEN_HEIGHT / 2);

			int alphe = 255 - (this.resultCount - 25) * 30;
			UI.FeedInOut( g , alphe , 255, 255, 255);
		}
		else
		{
			//アップグレード表示
			UI.drawUpGrade( g ) ;
			UI.drawShop( g , Library.nowLank() ,
				MainData.SCREEN_WIDTH / 2 , MainData.SCREEN_HEIGHT / 2);					
		}
	 }
	 
	 
//	 
//	 private void drawShopChange(Graphics g)
//	 {
//		 UI.FeedInOut( g , 100 );
//		 UI.drawShopChange( g ,  this.selectShop ,Library.nowLank() );
//	 }
	 
	 
	 
	 
	 public static final int HOUR = 3600;
	 public static final int MINITU = 60;
	 
	 
	 
	 
	 private void drawShoping(Graphics g)
	 {
		 //★うぃんどう
		 int err = 0;
		 int err2 = 0;

		 
		 int WindowY = 64;
		 int windowH = 210;
		 
		 int WindowY2 =  WindowY + windowH;
		 int windowH2 = MainData.SCREEN_HEIGHT - windowH - WindowY - 38;

		 int WindowY3 =  WindowY2 + windowH2;
		 int windowH3 = MainData.SCREEN_HEIGHT - WindowY3;

		 //ウィンドウ一個目
		 UI.DrawWindow(g, 0, WindowY, MainData.SCREEN_WIDTH, windowH);
		 //ウィンドウ2個目
		 UI.DrawWindow(g, 0, WindowY2, MainData.SCREEN_WIDTH, windowH2);
		 //ウィンドウ3個目
		 UI.DrawWindow(g, 0, WindowY3, MainData.SCREEN_WIDTH, windowH3);

		 int spaceX = 3;
		 int spaceY = 3;
		 
		 //ページ
		 //ウィンドウ4個目
		 int windowX4 = MainData.SCREEN_WIDTH / 2- 32; 
		 int windowY4 = 37+WindowY +142; 

		 UI.DrawWindow(g, windowX4,
				 windowY4, 64, 28,Graphics.getColorOfRGB( 222, 222, 222, 255 ));
		 String PageMsg = (this.shopNowPage+1)+"/"+this.shopMaxPage;
		 UI.DrawString( g , PageMsg,
			 windowX4 + 22,
			 windowY4 + 18,
			 Graphics.getColorOfRGB( 0, 0, 0,255 ),
			 Graphics.getColorOfRGB( 180, 180, 180, 255 ),
			 UI.DRAW_TYPE_DBUL );
	 
		 
		 //★スイッチ
		 int swicheW = (MainData.SCREEN_WIDTH-spaceX*2) / 3;
		 int swicheY = 64 + spaceY;
		 
		 String msgs[] = {"炎タイプ","建物とか","戻る"};
		 
		 if(this.TitleStep == TITLE_STEP_SHOP)
		 {
			 for(int i = 0 ; i < MAX_SHOP_SWICHE; i++)
			 {
				 UI.DrawSwicheMsg( g , msgs[i],
					 spaceX + swicheW * i ,swicheY ,
					 swicheW , 32 ,
					 this.ShopSwiche[i] );
			 }
		 }
		 
		 //★項目一覧
		 int CommandY = SHOP_COMMAND_Y ;
		 int CommandX = SHOP_COMMAND_X;
		 int CommandW = SHOP_COMMAND_W;
		 int CommandH = SHOP_COMMAND_H;
		 int CommandSpace = SHOP_COMMAND_SPACE;
		 
		 int offColor = Graphics.getColorOfRGB( 223, 255, 127, 255 );
		 int onColor  = Graphics.getColorOfRGB( 255, 159, 63 , 255 );
		 
		 String[][] SaveData;
		 
		 if(this.nowShopList == Title.SHOP_SWICHE_WEPON)
		 {
			 SaveData = DataSave.getDataBase();
		 }
		 else
		 if(this.nowShopList == Title.SHOP_SWICHE_BASE)		 
		 {
			 SaveData = DataSave.getDataBaseShop();
		 }else
		 {
			 SaveData = new String[111][4];		 
		 }
		 
		 try
		 {
			 for(int i = 0 ; i < MAX_SHOP_COMMAND; i++)
			 {
				 if(this.shopNowPage * MAX_SHOP_COMMAND + i >= this.shopDarawNumMax)
				 {
					 break;
				 }
				 
				 err = 1;
				 UI.DrawSwiche( g , 
					 CommandX ,CommandY + CommandSpace * i,
					 CommandW , CommandH ,
					 this.ShopCommand[i] ,
					 offColor , onColor);
				 err = 2;
				 
				 //文字
				 int strX = CommandX + 14;
				 int strY = CommandY + 20 + i * CommandSpace;


				 err = 3;

				 g.setFontSize(Graphics.SIZE_TINY);
				 
				 String msg;
				 

				 err = 4;
				 int idx = i+ this.shopNowPage * MAX_SHOP_COMMAND;
				 int id = this.shopDrawLineNupId[idx];
				 if(this.nowShopList == Title.SHOP_SWICHE_WEPON)
				 {					 
					 //Library.showDebugDialog( pAm.getActivity(),
					//		 "idx "+idx);
					 msg  = ShopData.FLAME_NAME[id];
					 
				 }else
				 if(this.nowShopList == Title.SHOP_SWICHE_BASE)
				 {					 
					 //Library.showDebugDialog( pAm.getActivity(),
					//		 "idx "+idx);
					 msg  = ShopData.BASE_NAME[id];
					 
				 }else
				 {
					 msg = "";
				 }
				 UI.DrawString( g , msg ,
					strX , 
					strY ,
					Graphics.getColorOfRGB( 0, 0, 0,255 ),
					Graphics.getColorOfRGB( 180, 180, 180, 255 ),
					UI.DRAW_TYPE_DBUL );
				 err = 6;
				 
				 
				 
				 String DrawMsg = "";
				 int moneyX = CommandX + 14 + 135;
				 int moneyY = CommandY + 20 + i * CommandSpace;
				 if(this.nowShopList == Title.SHOP_SWICHE_WEPON)
				 {	
					 //
					 long WeponStarttime = Integer.parseInt( SaveData[id][DataSave.DATA_IDX_WEPON_START_TIME]);
					 //時間数
					 long Wepontime = Integer.parseInt( SaveData[id][DataSave.DATA_IDX_WEPON_TIME_LONG]);
					 //現在時間
					 long nowTime = System.currentTimeMillis()/1000;
					 //残り時間
					 long WeponDrawTime = WeponStarttime - nowTime + Wepontime;
					 err = 7;
					 //座標
					 int money = ShopData.FLAME_PRICE[id];
					 
					 err = 71;
					 
					 
					 if(ShopData.FLAME_FREE[id])
					 {
						 err = 72;
						 DrawMsg = "無料";
					 }else
					 if(WeponDrawTime <= 0)
					 {
						 //値段
						 
						 err = 73;
						 DrawMsg = Integer.toString(money)+"羊";
					 }else
					 {
						 err = 74;
						 int hour 	=0;
						 int minute =0;
						 int second =0;
						 
						 long timeSecondAll = WeponDrawTime;
						 while(true)
						 {
							 err = 75;
							 if(timeSecondAll >= HOUR)
							 {
								 hour++;
								 timeSecondAll -= HOUR;
							 }else
							 {
								 break;
							 }
						 }
						 err = 76;
						 while(true)
						 {
							 if(timeSecondAll >= MINITU)
							 {
								 minute++;
								 timeSecondAll -= MINITU;
							 }else
							 {
								 break;
							 }
						 }
						 err = 77;
						 second = (int)timeSecondAll;
						 
						 DrawMsg = "残り　"+hour+":"+minute+":"+second;
						 err = 78;
					 }
					 err = 79;
					 
				 }else
				 if(this.nowShopList == Title.SHOP_SWICHE_BASE)
				 {
					 int buy = Integer.parseInt( SaveData[id][DataSave.BASE_IDX_BUY]);
					 if(buy >= 1)
					 {
						 err = 72;
						 DrawMsg = "購入済み";
					 }else
					 {
						 int money = ShopData.BASE_PRICE[id];
						 
						 if(ShopData.BASE_UPGREADE[id])
						 {
							 money = ShopData.NEXT_GOLD[Library.nowLank()];
						 }
						 DrawMsg = Integer.toString(money)+"羊";
					 }
					 
				 }
				 
				 UI.DrawString( g , DrawMsg ,
							moneyX , 
							moneyY ,
							Graphics.getColorOfRGB( 0, 0, 0,255 ),
							Graphics.getColorOfRGB( 180, 180, 180, 255 ),
							UI.DRAW_TYPE_DBUL );
				 
				 err = 701;
				 
				 //装備中
				 if(this.nowShopList == Title.SHOP_SWICHE_WEPON)
				 {
					 if(Library.nowEquip() == id)
					 {
						 int EquipX = CommandX + 14 + 217;
						 int EquipY = CommandY + 20 + i * CommandSpace;
						 err = 702;
						 
						 UI.DrawString( g , "装備中" ,
							EquipX , 
							EquipY ,
							Graphics.getColorOfRGB( 90, 75, 252,255 ),
							Graphics.getColorOfRGB( 120, 120, 120, 255 ),
							UI.DRAW_TYPE_DBUL );
					 }
				 }
				 
				 err = 8;
			 }
			 int helpX = 14;
			 int helpY = 293;
			 int helpSpace = 20;
			 String help="";
			 int helpIdx = -1;
			 
			 err = 7;
			 for(int f = 0 ; f < MAX_SHOP_COMMAND; f++)
			 {
				 if(this.ShopCommand[f])
				 {
					 helpIdx = f  + this.shopNowPage * MAX_SHOP_COMMAND;
				 }
			 }
			 if(helpIdx >= 0)
			 {
				 int helpid = this.shopDrawLineNupId[helpIdx];
				 err = 8;
				 //★説明			 
				 for(int i = 0 ; i < ShopData.FLAME_HELP_LEN; i ++)
				 {
					 err2 = i;
					 if(this.nowShopList == Title.SHOP_SWICHE_WEPON)
					 {	
						 help = ShopData.FLAME_HELP[helpid][i];
					 }else
					 if(this.nowShopList == Title.SHOP_SWICHE_BASE)
					 {	
						 help = ShopData.BASE_HELP[helpid][i];
					 }
					 if(help == null)
					 {
						 break;
					 }
					 err = 9;
					 if(help.length() >= 1)
					 {					 
						 UI.DrawString( g , help ,
								 helpX , 
								 helpY + helpSpace * i,
							Graphics.getColorOfRGB( 0, 0, 0,255 ),
							Graphics.getColorOfRGB( 180, 180, 180, 255 ),
							UI.DRAW_TYPE_DBUL );
						 err = 10;
					 }else
					 {
						 break;
					 }
				 }
			 }
			 err = 11;
		 }catch (Exception e) {
			// TODO: handle exception
			 Library.showDebugDialog( pAm.getActivity(),
					 "drawShoping err2 "+err2  + "err"+err+" errSTR "+e.toString());
		}
		 
		 
		 
		 
		 
		 //★左右ボタン		 
		 //左
		 UI.DrawSwicheMsg( g , "←",
			 Title.ACT_SWICHE_X , Title.ACT_SWICHE_Y2,
			 Title.ACT_SWICHE_W , Title.ACT_SWICHE_H ,
			 this.ShopActSwiche[SHOP_ACT_SWICHE_LEFT] );
		 //→
		 UI.DrawSwicheMsg( g , "→", 
				 Title.ACT_SWICHE_X2 , Title.ACT_SWICHE_Y2,
				 Title.ACT_SWICHE_W , Title.ACT_SWICHE_H ,
				 this.ShopActSwiche[SHOP_ACT_SWICHE_RIGHT] );
		 //決定
		 String SwicheName = "";
		 
		 
		 int type = getShopCommandStatus();
		 if(type == SELECT_NO)
		 {
			 SwicheName=  "未選択"; 
		 }else
		 if(type == SELECT_OK)
		 {
			 SwicheName=  "装備"; 
		 }else
		 if(type == SELECT_EQUIP)
		 {
			 SwicheName=  "装備中"; 
		 }else
		 if(type == SELECT_NORMAL)
		 {
			 SwicheName=  "購入"; 
		 }
		 
		 UI.DrawSwicheMsg( g , SwicheName,
				 Title.ACT_SWICHE_X3 , Title.ACT_SWICHE_Y,
				 Title.ACT_SWICHE_W3 , Title.ACT_SWICHE_H ,
				 this.ShopActSwiche[SHOP_ACT_SWICHE_BUY_EQ] );

	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}