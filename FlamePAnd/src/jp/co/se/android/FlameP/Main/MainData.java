package jp.co.se.android.FlameP.Main;


import javax.microedition.khronos.opengles.GL10;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.res.*;
import android.content.*;
import android.graphics.*;
import android.os.IBinder;
import android.os.Process;
import android.view.*;
import android.view.ViewGroup.LayoutParams;

import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;


import android.os.*;
import android.provider.ContactsContract.CommonDataKinds.Website;
import android.content.SharedPreferences.*;
import android.database.sqlite.SQLiteDatabase;




import jp.co.se.android.FlameP.FlamePAndActivity;
import jp.co.se.android.FlameP.lib.Library;
import jp.co.se.android.FlameP.lib.DataSave;

import jp.co.se.android.FlameP.lib.ShopData;
import jp.co.se.android.FlameP.Sound.SoundPlay;
import jp.co.se.android.FlameP.draw.*;
import jp.co.se.android.FlameP.save.saveDataBase;
import jp.co.se.android.FlameP.scene.*;
import jp.co.se.android.FlameP.Sound.*;
import android.view.Menu;


/**
 * MainCanvas
 * 
 */
public class MainData extends SurfaceView
	implements SurfaceHolder.Callback,Runnable
{

	 public static final int	KEY_0		 = KeyEvent.KEYCODE_0;
	 public static final int	KEY_1		 = KeyEvent.KEYCODE_1;
	 public static final int	KEY_2		 = KeyEvent.KEYCODE_2;
	 public static final int	KEY_3		 = KeyEvent.KEYCODE_3;
	 public static final int	KEY_4		 = KeyEvent.KEYCODE_4;
	 public static final int	KEY_5		 = KeyEvent.KEYCODE_5;
	 public static final int	KEY_6		 = KeyEvent.KEYCODE_6;
	 public static final int	KEY_7		 = KeyEvent.KEYCODE_7;
	 public static final int	KEY_8		 = KeyEvent.KEYCODE_8;
	 public static final int	KEY_9		 = KeyEvent.KEYCODE_9;
	 public static final int	KEY_SOFT1	 = KeyEvent.KEYCODE_ALT_LEFT;
	 public static final int	KEY_SOFT2	 = KeyEvent.KEYCODE_ALT_RIGHT;
	 public static final int	KEY_LEFT	 = KeyEvent.KEYCODE_DPAD_LEFT;
	 public static final int	KEY_UP		 = KeyEvent.KEYCODE_DPAD_UP;
	 public static final int	KEY_RIGHT	 = KeyEvent.KEYCODE_DPAD_RIGHT;
	 public static final int	KEY_DOWN	 = KeyEvent.KEYCODE_DPAD_DOWN;
	 //public static final int	KEY_ASTERISK = KeyEvent.KEYCODE_A;
	 //public static final int	KEY_SHARP	 = KeyEvent.KEYCODE_SHARP;
	 public static final int	KEY_SELECT	 = KeyEvent.KEYCODE_DPAD_CENTER;
	 public static final int	KEY_CLEAR	 = KeyEvent.KEYCODE_CLEAR;

	 public static final int	KEY_BACK	 =  KeyEvent.KEYCODE_BACK;
	 public static final int	KEY_HOME	 =  KeyEvent.KEYCODE_HOME;
	 public static final int	KEYCODE_MENU =  KeyEvent.KEYCODE_MENU;
	
	
	private SurfaceHolder holder;
	
	private Graphics g;
	private Thread	thread;
	
	public static final int SCENE_TITLE = 0;

	public static final int SCENE_PLAY	 = 1;

	public static final boolean DEBUG_MODE = false ;
	public static final boolean DEBUG_CM = false ;
	public static final boolean DEBUG_MODE_DRAW = true ;
	
	public static final boolean DEBUG_MODE_EMU = false ;

	public static final boolean DEBUG_MODE_SAVE = false ;
	
	
	public static final int DEBUG_NUMBER  = 0;
	
	
	public int NowScene = SCENE_TITLE;
	
	public static String[]	appParams;

	public Title 	g_Title;
	public Play		g_Play;
	public DataSave dqSave;  
	private SQLiteDatabase db;  
	
	public static SoundPlay sound ;
	
	public static se_Play   se_sound ;

	public Activity activityMain;
	
	public FlamePAndActivity gflameMain;
	
	public SharedPreferences pref;
	 
	public MainData(Context context,Activity activity,
			FlamePAndActivity flameMain) {
		super(context);
		int err = 0;
		//try
		{
			
	
			gflameMain = flameMain;
			err=1;
			activityMain = activity;
			// TODO 自動生成されたコンストラクター・スタブ	
			holder = getHolder();
			holder.addCallback(this);
			err=2;
			
			g = new Graphics(holder);
			
		
			//キー操作受け取り
			setFocusableInTouchMode(true);
			
			sound = new SoundPlay(this.getContext());
			
			
			//sound.SoundCreateAll(this.getActivity(), this.getContext() );
	        saveDataBase.loadBgmList(this.getActivity());

			
			
			se_sound = new se_Play();
			se_sound.se_load(this.getContext());
			err=3;

			
			//Mr mp = MediaPlayer.create(context, id);
	
			g_Title = new Title(this);
			err=4;

			g_Play  = new Play(this);		
			
			dqSave = new DataSave(this.getContext() , this);
			db = dqSave.getWritableDatabase();
			err=5;
			
			Setting set = new Setting(handler);
			

			ShopData.DataSetting();
			err=6;
			Library.LoadManey(this);
			
			 
			UI.init( g , getResources() );
			try
			{
				dqSave.readAllDB( db  );
				err=7;
			}catch (Exception e) {
				// TODO: handle exception
				Library.showDebugDialog(getActivity(), "MainData err2   +err"+err);
			}
			
			
			 pref = Library.read_xml( this , "save" );
			 
		}
		//catch (Exception e)
		//{
		//	Library.showDebugDialog(getActivity(), "MainData err   +err"+err);
		//}
		
//		AlertDialog.Builder ad=new AlertDialog.Builder(activity);
//	    ad.setTitle("aa");
//	    ad.setMessage("aa");
//	    ad.setPositiveButton("OK",new DialogInterface.OnClickListener() {
//	          public void onClick(DialogInterface dialog,int whichButton) {
//	          }
//	    });
//	    ad.create();
//	    ad.show();
		//Library.showDialog(gflameMain.getActivity(), "aaaa", "aaaaa");
	}
	
	public void loadUIDataAfter()
	{
		UI.ReadShopTitleData( g , getResources() );
	}
	
	public Activity getActivity()
	{
		return gflameMain.getActivity() ;
	}
	
	public SQLiteDatabase getdb()
	{
		return db;
	}
	
	Handler handler= new Handler();
	
	public void test() {
	  new Thread(new Runnable() {
	    public void run() {
	      handler.post(new Runnable() {
	    	public void run() {
	    		Library.showDialog(gflameMain.getActivity(), "aaaa", "aaaaa");
	        }
	      });
	    }
	  }).start();
	}

	

	public static int SCREEN_WIDTH  = 0;
	public static int SCREEN_HEIGHT = 0;

	//honnmono
	public static int SCREEN_WIDTH_TRUE  = 0;
	public static int SCREEN_HEIGHT_TRUE = 0;

	//480
	//800
	public static int SCREEN_WIDTH_DEF  = 480;
	public static int SCREEN_HEIGHT_DEF = 800;
	
	public static int SCREEN_MOVE_X = 0;
	public static int SCREEN_MOVE_Y = 0;

	
	

	public static float Width_Size = 1.0f;
	public static float Height_Size = 1.0f;

	public static float Width_Size_True = 0.0f;
	public static float Height_Size_True = 0.0f;

	
	public void setScreenSize(int w , int h)
	{
		SCREEN_WIDTH = w;
		SCREEN_HEIGHT = h;
		
		SCREEN_WIDTH_TRUE= w;
		SCREEN_HEIGHT_TRUE= h;
		
		if(DEBUG_MODE)
		{
			//SCREEN_WIDTH = 240;
			//SCREEN_HEIGHT = 320;
		}
		
		
		//
		Width_Size_True =(float) ((float)SCREEN_WIDTH  / (float)SCREEN_WIDTH_DEF);
		Height_Size_True =(float)( (float)SCREEN_HEIGHT / (float)SCREEN_HEIGHT_DEF);

		if(Width_Size_True >= 1.0f)
		{
			Width_Size_True = 1.0f;
		}
		if(Height_Size_True >= 1.0f)
		{
			Height_Size_True = 1.0f;
		}
		
		SCREEN_WIDTH = SCREEN_WIDTH_DEF;
		SCREEN_HEIGHT = SCREEN_HEIGHT_DEF;
		if( w > SCREEN_WIDTH_DEF)
		{
			SCREEN_MOVE_X = (w - SCREEN_WIDTH_DEF) / 2;			
		}
		if( h > SCREEN_HEIGHT_DEF)
		{
			SCREEN_MOVE_Y = (h - SCREEN_HEIGHT_DEF) / 2;			
		}
		
		
		
		

	}
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
    	super.onConfigurationChanged(newConfig);
    	Configuration config = getResources().getConfiguration();
		// Landscape(横長)
		
//		int orient = config.orientation;
//		
//		if( orient == Configuration.ORIENTATION_LANDSCAPE ) { 
//			if(w > h)
//			{
//				config.orientation = Configuration.ORIENTATION_PORTRAIT;
//			}
//		}
    	
    	
    }
	protected void onSizeChanged(int w ,int h , int oldw,int oldh)
	{
		
		//Library.TraseMsg("w");
		//setScreenSize( w , h);		
	}
	
	public void setErrMsg(String msg)
	{
		if(MainData.DEBUG_MODE)
		{
			errMsg = msg;
		}
		//errMsgADD += msg+ "\n" ;
	}
//	public void setErrmsgInit()
//	{
//		errMsgADD = "";
//	}
//	public String getErrmsg()
//	{
//		return errMsgADD;
//	}
	
	public String getErrmsgOne()
	{
		return errMsg;
	}
	public boolean errSaveOk = false;
	public void saveDebugErr()
	{
		if(DEBUG_MODE_SAVE )
		{
			if(errSaveOk)
			{
			   String msg = this.getErrmsgOne();
		 	   Library.setErrMsgSave(msg);
		 	   Library.SaveDataBase( this , true);
			}
		}
	}
	
	String errMsg = "";
	String errMsgADD = "";
	
	public static int AllCounter = 0;
	//public void run(GL10 gl) {
	public void run() {
		
		setScreenSize(getWidth() , getHeight());
		
		UI.intIconPoint();
		
		int err = 0;
		int count = 0;
			// TODO 自動生成されたメソッド・スタブ
			while( thread != null )
			{
				nowTimeDebug = System.currentTimeMillis();
				if(AllCounter % 3 == 0)
				{
					se_Play.seOkInit();
				}
				AllCounter++;
				if(AllCounter >= Integer.MAX_VALUE)
				{
					AllCounter = 0;
				}
		 	  
				setErrMsg("11");
				saveDebugErr();
				//try
				{
					g.lock();
					err = 1;
					g.setColor(Library.COLOR_RGB(255, 255, 255));
					
					g.setCanvasScale();
					
//			        g.fillRect(0, 0,
//			        	getWidth(), getHeight() ,false);
			        //UI.drawBackGround( g );
					err = 2;	
					setErrMsg("22");
					if(MainData.DEBUG_MODE)
			        {
						//saveDebugErr();
			        }
			        //キー
			        switch(getNowScene())
			        {
			        	case SCENE_TITLE:
			        	{
			        		if(Library.getDialogStop() == false)
			        		{
				        		int nowLV = g_Title.MainProc();
				        		if(nowLV >= 1)
				        		{
				        			setErrMsg("33");
				        			//g_Play.setGameLv(nowLV);	        	
				        			this.NowScene = SCENE_PLAY;	        			
				        			
				        			gflameMain.removeAd();
				        			setErrMsg("44");
				        			g_Play.init();
				        		}
			        		}else
			        		{
			        			//Library.setDialogStop(false);
			        		}
			        		saveDebugErr();
			        		setErrMsg("55");
			        		if(MainData.DEBUG_MODE)
					        {
								//saveDebugErr();
					        }
			        		g_Title.MainDraw(g);	        		

			        		saveDebugErr();
			        		setErrMsg("66");
			        		break;
			        	}
			        	case SCENE_PLAY:
			        	{
			        		setErrMsg("77");
							err = 3;
							if(MainData.DEBUG_MODE)
					        {
								saveDebugErr();
					        }
							//saveDebugErr();
			        		if(Library.getDialogStop() == false)
			        		{
			        			Library.setTimeCheck( 0 );
				        		if(g_Play.PlayProc() == 1)
				        		{
									err = 4;
				        			this.NowScene = SCENE_TITLE;
				        			gflameMain.showAd();
				        			Library.BGM_Play(this.getActivity(),this.getContext());
				        			//BGM
				        			//Library.BGM_Play( SoundPlay.SOUND_BGM_TITLE );
				        			//setSoftKey(0 , "�I��");
				     			   // setSoftKey(1 , "");
									err = 5;
									setErrMsg("88");
				        		}
			        		}else
			        		{
			        			//Library.setDialogStop(false);
			        		}
			        		setErrMsg("99");
			        		if(MainData.DEBUG_MODE)
					        {
								//saveDebugErr();
					        }
							err = 6;
		        			Library.setTimeCheck( 1 );
			        		g_Play.PlayDraw(g);
		        			Library.setTimeCheck( 2 );
			        		setErrMsg("100");
							saveDebugErr();
							err = 7;
			        		break;
			        	}
			        }
			        if(MainData.DEBUG_MODE)
			        {
						//saveDebugErr();
						//int a[] = null;
						//a[0] = 0;
			        }
			        setErrMsg("1000");
			        //saveDebugErr();
					err = 8;
					 //デバック描画
					UI.drawDebug( g ,
			        	getToushPushX() ,
		        		getToushPushY() ,
		        		errMsg
			        );
			        if(DEBUG_MODE)
			        {
				        //デバックコマンド
				        DebugProc();
			        }
			        setErrMsg("1001");
			        //saveDebugErr();
			        err = 9;
			        this.KeyDown = -1;
					err = 10;
			        toushInit();
					err = 11;
					
					if(SCREEN_WIDTH_TRUE > SCREEN_WIDTH_DEF ||
							SCREEN_HEIGHT_TRUE > SCREEN_HEIGHT_DEF	)
					{
						UI.drawBackGroundEnd(g);
					}
			        g.unlock();			        
					err = 12;
					
        			Library.setTimeCheck( 3 );
					try{
						Thread.sleep(55);
					} catch( Exception e ) {
						
					}
			        setErrMsg("1002");
					err = 13;
					saveDebugErr();					
				}
				//catch( Exception e ) {
				//	Library.showDebugDialog(getActivity(), "Runerr"+err+"\nERR"+e.toString());
					
				//}
			} 
			
			//setErrMsg("EriaOut");
			//saveDebugErr();
		}


	
	
    public static int g_debug = 0;
    public static int g_debug2 = 0;
    public static int g_debug3 = 0;
    public static int g_debug4 = 0;

    
    public static String g_debug_MSG[] = new String[10];
    public static long nowTimeDebug = 0;
    /** ボタン押した瞬間*/
    int KeyDown = 0;
    /** 今現在押している*/
    int KeyDownNow = 0;
    
    boolean downNow = false;

    /** 　*/
    int KeyRelese = 0;
    
    
	public boolean onKeyDown(int keyCode ,
			KeyEvent event)
	{
		
		this.KeyDown = keyCode;
		this.KeyDownNow = keyCode;
		
		if(keyCode == KeyEvent.KEYCODE_BACK){
			Library.setDialogStop(false);
			//setErrMsg("aaaaaaa");
			return true;
		}

		
		return super.onKeyDown(keyCode, event);
	}
	public boolean onKeyUp(int keyCode ,
			KeyEvent event)
	{
		this.KeyDownNow = -1;
		return super.onKeyUp(keyCode, event);
	}
    
    public int getNowKey()
    {
    	//Library.TraseMsg("KeyDownNow"+KeyDownNow);
    	return this.KeyDownNow;
    }
    
    public int getDownKey()
    {
    	return this.KeyDown;
    }
    
    public int getReleseKey()
    {
    	return this.KeyRelese;
    }
    
    public int getNowScene()
    {
    	return this.NowScene;
    }
    
    
    int toushXDown = 0;
    int toushYDown = 0;
    
    //押した瞬間
    int toushXPush = 0;
    int toushYPush = 0;
    int toushAction = 0;
    
    public void InitToush()
    {
    	toushXDown = 0;
        toushYDown = 0;
        
        //押した瞬間
        toushXPush = 0;
        toushYPush = 0;
        toushAction = -1;
    }
    
    //タッチ
    public boolean onTouchEvent(MotionEvent event)
    {

    	if(Library.getDialogStop())
    	{
    		InitToush();
    		return true;
    	}
    	
    	int touchAct = (int)event.getAction();


    	this.toushAction = touchAct;
    	
    	switch (touchAct) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_MOVE:

				if(touchAct == MotionEvent.ACTION_DOWN)
				{
					toushXPush = (int)(event.getX() );
					toushYPush = (int)(event.getY() );
				}
				
		    	toushXDown = (int)(event.getX());
		    	toushYDown = (int)(event.getY());
				break;
			case MotionEvent.ACTION_UP:
				toushInit();
				toushXDown = -1;
				toushYDown = -1;				
				break;
	
			default:
				break;
		}
    	

    	
    	
    	return true;
    }
    private void toushInit()
    {
    	toushXPush = -1;
    	toushYPush = -1;
    	//this.toushAction = -1;
    }
    
    public boolean getToush()
    {
    	if(toushXDown >= 0||
   			toushYDown >= 0)
    	{
    		return true;
    	}
    	
    	return false;
    }
    
    public int getToushDownX()
    {
    	return toushXDown;
    }
    public int getToushDownY()
    {
    	return toushYDown;
    }

    public int getToushPushX()
    {
    	return toushXPush;
    }
    public int getToushPushY()
    {
    	return toushYPush;
    }
    public int getToushEvent()
    {
    	return toushAction;
    }

    
    
    
    void DebugProc()
    {
    	if(DEBUG_MODE == false)
    	{
    		return;
    	}
    	
    	//Library.TraseMsg("KeyDownNow"+KeyDownNow);

    	
//    	if(downNow == false)
//    	{
//    		return ;    		
//    	}
    	
    	if(getToushDownX() == 0)
    	{
    		return;
    	}
    	if(getToushDownY() == 0)
    	{
    		return;
    	}
    	if(getToushDownY() <= 100 && 
    			getToushDownX() >= SCREEN_WIDTH / 2)
    	{
    		g_debug++;
    		
    		g_Play.getPlayer().SetPowerUp();
    		
    		Library.addManey(500000);
    		
    	}else
		if(getToushDownY() <= 100 && 
				getToushDownX() <= SCREEN_WIDTH / 2 &&
				getToushDownX() >= 1)
    	{
    		g_debug--;
    	}else
		if(getToushDownY() >= SCREEN_HEIGHT - 100 && 
				getToushDownX() >= SCREEN_WIDTH / 2)
    	{
    		g_debug2++;
    	}else
		if(getToushDownY() >= SCREEN_HEIGHT - 100 && 
				getToushDownX() <= SCREEN_WIDTH / 2 &&
				getToushDownX() >= 1)
    	{
    		g_debug2--;
    	}
    	
    }
    
    public String TextStr = "";
    public int Text_type = 0;
    public void TEXT_Init()
    {
    	TextStr = "�ҁ[�����";
    }
    
    public String Get_Text()
    {
    	return TextStr;
    }
    
    public int Get_Text_Type()
    {
    	return Text_type;
    }
    
    public void TEXT_Start()
    {
    	//imeOn(TextStr,TextBox.DISPLAY_ANY,TextBox.KANA); 
    }
    
    
    public Context getContextMain()
    {
		return getContext();
    }
    
    //アプリ終了
    public void terminateGame()
    {
    	
    	//音楽停止
    	Library.BGM_STOP_ALL();    	
    	
    	g_Title.setStep(Title.TITLE_STEP_GAME_END);
    	//activityMain.finish();
    	//IApplication.getCurrentApp().terminate();
    	//Process.killProcess(Process.myPid());  
    }
    public void terminateGame2()
    {
    	
    	
    	//音楽停止
//    	Library.BGM_STOP_ALL();    	
//    	Library.BGM_RELEASE();
//    	se_Play.se_release(getContext());
//    	
//    	activityMain.finish();
//    	UI.release();
//       	
//    	try{
//			Thread.sleep(500);
//		} catch( Exception e ) {
//			
//		}
    	
    	
    	//IApplication.getCurrentApp().terminate();
    	activityMain.finish();
    }
	
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO 自動生成されたメソッド・スタブ
		thread = new Thread(this);
		thread.start();
	}

	
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO 自動生成されたメソッド・スタブ
		thread = null ;		
	}
	
	public SharedPreferences getSharedPreferencesMain(String name ,int status)
	{		
		SharedPreferences data = getContextMain().getSharedPreferences(name,status);
		return data;
	}
	
	
    String[] SiteURL = {"http://gasutorageta.web.fc2.com/APLI/TOP/apTop.html"};
    
    public void SiteGo()
    {
    	    	//IApplication.getCurrentApp().launch( IApplication.LAUNCH_BROWSER, SiteURL );
    	//WebView webView = (WebView)findViewById(jp.co.se.android.FlameP.R.layout.main);

    	//webView.loadUrl("http://gasutorageta.web.fc2.com/APLI/TOP/apTop.html");    
    	//webView.requestFocus();

    	gflameMain.SiteOpen( );
    	//終了
    	//terminateGame();
    }
    
    public static final int MENU_ID_HELP  = (Menu.FIRST+1);
    public static final int MENU_ID_HOME  = (Menu.FIRST+2);
	public static final int MENU_ID_GOLD  = (Menu.FIRST+3);
	public static final int MENU_ID_SOUND = (Menu.FIRST+4);

	private static final int LAYOUT_PADDING = 10;
	private SeekBar bar;
	
	View dialogView;
	
	public void createBar(final int vol)
	{
		new Thread(new Runnable() {
		    public void run() {
		      handler.post(new Runnable() {
		    	public void run() {
		    		
		    		gflameMain.showDialogBar();
		    		
			        }
			      }	
		      	);
		      }
		    }).start();        
	}
	
	public void createLanking()
	{
		new Thread(new Runnable() {
		    public void run() {
		      handler.post(new Runnable() {
		    	public void run() {
		    		
		    		gflameMain.showLanking();
		    		
			        }
			      }	
		      	);
		      }
		    }).start();        
	}
	
	
	
	
	public void createMenu(Menu menu)
    {
//		menu.clear();
//    	if(getNowScene() == SCENE_TITLE)
//    	{
//    		menu.add(Menu.NONE, MENU_ID_HELP	, Menu.NONE, "説明");
//			menu.add(Menu.NONE, MENU_ID_HOME	, Menu.NONE, "ホームページ");	
//			menu.add(Menu.NONE, MENU_ID_GOLD	, Menu.NONE, "お金初期化");	
//			menu.add(Menu.NONE, MENU_ID_SOUND	, Menu.NONE, "サウンド");	
//    	}else
//    	{
//    		
//    	}
    }
    
    public void SelectMenu(MenuItem item)
    {
    	if(getNowScene() == SCENE_TITLE)
    	{
    		g_Title.TitleMenuProc(item.getItemId());
    	}    	
    }

	
}