package jp.co.se.android.FlameP;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.PixelFormat;
//import android.opengl.GLSurfaceView;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;

import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import jp.co.se.android.FlameP.Main.MainData;
//import jp.co.se.android.FlameP.Main.GLRender;
import jp.co.se.android.FlameP.Sound.SoundPlay;
import jp.co.se.android.FlameP.Sound.se_Play;
import jp.co.se.android.FlameP.lib.Library;
import jp.co.se.android.FlameP.lib.bagtrease;
import jp.co.se.android.FlameP.save.saveDataBase;
import jp.co.se.android.FlameP.scene.Setting;
import jp.co.se.android.FlameP.activity.ConstantClass;
import jp.co.se.android.FlameP.activity.bgmSelect;
import jp.co.se.android.FlameP.draw.*;

import jp.co.se.android.FlameP.R;

import javax.microedition.khronos.opengles.GL10;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

//import javax.microedition.khronos.egl.EGLConfig;  

//import android.content.Context;
//import android.opengl.GLSurfaceView.Renderer;  
//import android.opengl.GLSurfaceView;  


public class FlamePAndActivity extends Activity implements  Handler.Callback
	{
    
	//private MainData mainTh;
	
    //private GLSurfaceView gLSurfaceView;

    private MainData pMain;
    
    public AdView adView;
    private Handler mHandler; 
    public static final String ACOUNT_ID = "a14f1b833f786bb";
    RelativeLayout  layoutMain ;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
     
        //BGM起動中
        if(ConstantClass.getBgmSelectOn())
        {
        	finish();
        	return;
        }
        
        layoutMain = new RelativeLayout(this);
        //
      
        mHandler = new Handler(this); 
        
        saveDataBase.setPackageName(this);
        
        requestWindowFeature( Window.FEATURE_NO_TITLE );               
        getWindow().setFormat(PixelFormat.TRANSLUCENT);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        pMain = new MainData(this,this,this);

        layoutMain.addView(pMain);  
        
        if(MainData.DEBUG_CM == false)
        {
	        createAd();
        }
        setContentView(layoutMain);
        
        Setting.setMain(pMain);
        
        
        
        Thread.setDefaultUncaughtExceptionHandler(new jp.co.se.android.FlameP.lib.bagtrease(getBaseContext()));
        
        //if(MainData.DEBUG_MODE)
        {
        	String bag = bagtrease.getBag(this);
        }
    }
    public void removeAd() { 
    	if(MainData.DEBUG_CM == false)
        {
	    	try
	        { 
	    		
	    		mHandler.post(new Runnable() {
	    	        public void run() {
	    	        	adView.setVisibility(AdView.GONE);
	    	        	adView.stopLoading();
	    	        }
	    	      });
	    		
	    		
	        	
	        } catch (Exception e) {
				// TODO: handle exception
	        	Library.TraseMsg(e.toString());
			}
        }
    }
    
    public void createAd()
    {
    	if(MainData.DEBUG_CM == false)
        {
	    	if(layoutMain != null) { 
	        	
	        	adView = new AdView(this, AdSize.BANNER, ACOUNT_ID); 
	            AdRequest req = new AdRequest(); 
	//            if(MainData.DEBUG_MODE)
	//            {
	//            	req.addTestDevice(AdRequest.TEST_EMULATOR); 
	//            }
	            //req.addTestDevice(TEST_DEVICE_ID); 
	            adView.loadAd(req);
	            
	        	FrameLayout.LayoutParams params = new FrameLayout.LayoutParams( 
	                    FrameLayout.LayoutParams.MATCH_PARENT, 
	                    FrameLayout.LayoutParams.WRAP_CONTENT, 
	                    Gravity.BOTTOM); 
	        	layoutMain.addView(adView , params); 
	        	//layoutMain = null; 
	        } 
        }
    }
    
    public void showAd() { 
    	if(MainData.DEBUG_CM == false)
        {
	    	mHandler.post(new Runnable() {
		        public void run() {
		        	adView.setVisibility(AdView.VISIBLE);
		        }
	    	});
        }        
    } 
    private final int FP = ViewGroup.LayoutParams.FILL_PARENT; 
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT; 

    
    private WebView webview;

    public void GameDataOpen()
    {  
    	Uri uri = Uri.parse("http://gasutorageta.web.fc2.com/APLI/GAME/AndPI/data/GameData.html");
		Intent i = new Intent(Intent.ACTION_VIEW,uri);
		startActivity(i); 
    }
    public void GameHelpOpen()
    {  
    	Uri uri = Uri.parse("http://gasutorageta.web.fc2.com/APLI/GAME/AndPI/help/GameHelp.html");
		Intent i = new Intent(Intent.ACTION_VIEW,uri);
		startActivity(i); 
    }
    
    
    public void SiteOpen()
    {  
    	Uri uri = Uri.parse("http://gasutorageta.web.fc2.com/APLI/TOP/apTop.html");
		Intent i = new Intent(Intent.ACTION_VIEW,uri);
		startActivity(i); 
    	
//        try
//        {
//	        WebView webView=new WebView(this);
//	        setContentView(webView); 
//	        webView.loadUrl("http://npaka.net");
//	    }catch (Exception e) {
//			// TODO: handle exception
//			 
//			 Library.TraseMsg("err "+e.toString());
//			 
//		}
//        
        //webview.getSettings().setJavaScriptEnabled(true);
        //webview.loadUrl("http://www.google.co.jp/"); 

    	
    	
//        LinearLayout linearLayout = new LinearLayout(this);
//        linearLayout.setOrientation(LinearLayout.VERTICAL);
//        setContentView(linearLayout);
//
//
//        LinearLayout ctlLinearLayout = new LinearLayout(this);
//        ctlLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
//
//
//        webview = new WebView(this);
//        webview.loadUrl("http://www.yahoo.co.jp/");
//
//        linearLayout.addView(ctlLinearLayout, createParam(FP, WC));
//        linearLayout.addView(webview, createParam(WC, WC));
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	
    	
    	if(keyCode==KeyEvent.KEYCODE_HOME){
            //viewFlipper.showPrevious();
    		Library.BGM_STOP_ALL();
        }
    	if(keyCode==KeyEvent.KEYCODE_BACK){
          //viewFlipper.showPrevious();
        	return true;
        }
        return false;
      }
    
    private LinearLayout.LayoutParams createParam(int w, int h){
        return new LinearLayout.LayoutParams(w, h);
    }

    
    protected void onResume() {    
    	
    	super.onResume();      
    	Library.BGM_Play(this,this);
    	//Library.BGM_RELEASE();
    	Library.showToastDebug(this, "onResume",3);

    }  
    
    protected void onRestart()
    {
    	//Library.BGM_STOP_ALL();
        super.onRestart();
        //Library.BGM_Play(this,this);
        Library.showToastDebug(this, "onRestart",3);
        if(changeSoundName)
    	{
    		updateNowSoundName();
    	}
        //if(MainData.se_LoadOk)
        {
        	Library.BGM_Play(this,this);
        }
    }
    
    protected void onPause()
    {
        super.onPause();
        //gLSurfaceView.onPause();
        if(bgmNostop == false)
        {
        	//Library.BGM_STOP_ALL();
        }
        bgmNostop = false;
        Library.showToastDebug(this, "onPause");

    }
    
    
    @Override     
    protected void onStop() {     
        super.onStop();    
        Library.TraseMsg("onStop");
        Library.showToastDebug(this, "onStop");
        if(ConstantClass.moveActivity == false)
        {
        	Library.BGM_STOP_ALL();
        }
        ConstantClass.moveActivity = false;

        
        //gemeEnd();
    }     
    @Override 
    protected void onDestroy() { 
       super.onDestroy();  
       Library.TraseMsg("DESTROY");
       Library.showToastDebug(this, "onDestroy");
       

  
       
       
       
	   	Library.BGM_STOP_ALL();    	
		Library.BGM_RELEASE();
		se_Play.se_release(this);
		
		UI.release();
       if(MainData.DEBUG_CM == false)
       {
    	   adView.destroy();
       }
       layoutMain = null; 
//       if(pMain.getDownKey() != MainData.KEY_BACK)
//       {
//    	   gemeEnd();
//       
       
       Process.killProcess(Process.myPid());  
    }
    
    
    @Override  
    public boolean onPrepareOptionsMenu(Menu menu)
    {      
    	//pMain.createMenu(menu);    	
  	  	return super.onPrepareOptionsMenu(menu);  
    }    
    @Override   
    public boolean onOptionsItemSelected(MenuItem item) {
  	  boolean ret = true;
  	  //ret = super.onOptionsItemSelected(item);

  	  //pMain.SelectMenu(item);    
  	  return ret;	  
    }
    


    
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
//        gl.glViewport(0, 0, width, height);
//        gl.glMatrixMode(GL10.GL_PROJECTION);
//        gl.glLoadIdentity();
//        GLU.gluOrtho2D(gl, 0.0f, width, 0.0f, height);
    }
    
    
    public Activity getActivity()
	{
		return (Activity)this;
	}
    
    final int CHANGE_TEXT = 0;
    final int CHANGE_BACKGROUND = 1;

    final int COLOR_TABLE[] = {
        0xffffffff, 0xffc0c0c0, 0xff808080, 0xff000000,
        0xffffc0c0, 0xffff6060, 0xffff0000, 0xff800000,
        0xffffe0c0, 0xffffb060, 0xffff8000, 0xff804000,
        0xffffffc0, 0xffffff60, 0xffffff00, 0xff808000,
        0xffe0ffc0, 0xffb0ff60, 0xff80ff00, 0xff408000,
        0xffc0ffc0, 0xff60ff60, 0xff00ff00, 0xff008000,
        0xffc0ffe0, 0xff60ffb0, 0xff00ff80, 0xff008040,
        0xffc0ffff, 0xff60ffff, 0xff00ffff, 0xff008080,
        0xffc0e0ff, 0xff60b0ff, 0xff0080ff, 0xff004480,
        0xffc0c0ff, 0xff6060ff, 0xff0000ff, 0xff000080,
        0xffe0c0ff, 0xffb060ff, 0xff8000ff, 0xff400080,
        0xffffc0ff, 0xffff60ff, 0xffff00ff, 0xff800080,
        0xffffc0e0, 0xffff60b0, 0xffff0080, 0xff800040
    };

    int mColor = 0xffffffff;
    int mTextColor = 0xffc0c0c0;
    int mBackgroundColor = 0xff000000;

    LayoutInflater inflater;   
    View dialogView;


	
	boolean bgmNostop = false;
    public void SoundChange(View v)
    {
    	try
		{
    		ConstantClass.moveActivityTime = ConstantClass.MOVE_ACTIVITY_TIME;
    		ConstantClass.moveActivity = true;
    		//Library.BGM_STOP_ALL();
			Intent intent = new Intent(this, jp.co.se.android.FlameP.activity.ConstantClass.bgmSelect );
			startActivity(intent);			
			
			bgmNostop = true;
			changeSoundName = true;
		}catch(Exception ex){
			Log.e("TopPageActivity_ERROR:", ex.toString());
		}
    }
    
    public void openLankList()
    {    	
    	new Thread(new Runnable() {
		    public void run() {
		      handler.post(new Runnable() {
		    	  public void run() 
		    	  {
			    	ConstantClass.moveActivityTime = ConstantClass.MOVE_ACTIVITY_TIME;
					ConstantClass.moveActivity = true;
					//Library.BGM_STOP_ALL();
					Intent intent = new Intent (FlamePAndActivity.this, ConstantClass.lankingList );
					startActivity(intent);
					bgmNostop = true;
					//changeSoundName = true;
		    	}
		      });
		    }
		  }).start();
    }
    
    public void gotoItemList()
    {
    	ConstantClass.moveActivityTime = ConstantClass.MOVE_ACTIVITY_TIME;
    	ConstantClass.moveActivity = true;
    	Intent intent = 
    		new Intent(this, jp.co.se.android.FlameP.activity.ConstantClass.ItemSelect );
		startActivity(intent);
    }
    
    
    //初期化
    public void InitializationGame(View v)
    {
    	pMain.g_Title.goGoldInit();
    }
    
    private static Handler handler= new Handler();
    
    public void GameDataSite(View v)
    {
    	new Thread(new Runnable() {
		    public void run() {
		      handler.post(new Runnable() {
		    	  public void run() 
		    {
				 int  err = 0;
				 try
				 {
					err = 1;
				    AlertDialog.Builder ad=new AlertDialog.Builder(FlamePAndActivity.this);
				    ad.setTitle("ゲーム情報");
				    ad.setMessage("サイト移動しますが\nよろしいですか？");
				    ad.setInverseBackgroundForced(false);
				    err = 2;
				    
				    ad.setPositiveButton("はい",
				    	new DialogInterface.OnClickListener()
					    {
					        public void onClick(DialogInterface dialog,int whichButton) {
					        	GameDataOpen();
					        }
					    }
				    );
				    err = 3;
				    ad.setNegativeButton("いいえ",
				    	new DialogInterface.OnClickListener() 
				    	{				
							//@Override
							public void onClick(DialogInterface dialog,int whichButton) {
								
					        }
						}
				    );
			    	ad.setCancelable(true);

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
    public void GameHelpSite()
    {
    	new Thread(new Runnable() {
		    public void run() {
		      handler.post(new Runnable() {
		    	  public void run() 
		    {
				 int  err = 0;
				 try
				 {
					err = 1;
				    AlertDialog.Builder ad=new AlertDialog.Builder(FlamePAndActivity.this);
				    ad.setTitle("ゲーム説明");
				    ad.setMessage("サイト移動しますが\nよろしいですか？");
				    ad.setInverseBackgroundForced(false);
				    err = 2;
				    
				    ad.setPositiveButton("はい",
				    	new DialogInterface.OnClickListener()
					    {
					        public void onClick(DialogInterface dialog,int whichButton) {
					        	GameHelpOpen();
					        }
					    }
				    );
				    err = 3;
				    ad.setNegativeButton("いいえ",
				    	new DialogInterface.OnClickListener() 
				    	{				
							//@Override
							public void onClick(DialogInterface dialog,int whichButton) {
								
					        }
						}
				    );
			    	ad.setCancelable(true);

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
    private boolean changeSoundName = false;
    TextView Soundtext ;
	

    int nowBgmBar = 0;
    int nowSeBar = 0;
    int nowBgm = 0;
    
	public void showDialogBar() {
 		Library.setDialogStop(true);

		//保存
		nowBgmBar = Library.getSoundVol();
		nowSeBar = Library.getSe();
		nowBgm = Library.getNowBgm();
		
		LayoutInflater inflater = LayoutInflater.from(this);   
		final View dialogView = inflater.inflate(jp.co.se.android.FlameP.R.layout.bar, null);
		
		final SeekBar seekBar = (SeekBar)dialogView.findViewById(jp.co.se.android.FlameP.R.id.seekbar); 
		
		seekBar.setMax(100);
		seekBar.setProgress(Library.getSoundVol());
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {   
		// トラッキング開始時に呼び出されます    
		public void onStartTrackingTouch(SeekBar seekBar) {  
				int value = seekBar.getProgress();
	
	    		Library.setSound(value);
			}
		// トラッキング中に呼び出されます  
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
				int value = seekBar.getProgress();
	
	    		Library.setSound(value);
			}            
		// トラッキング終了時に呼び出されます        
		public void onStopTrackingTouch(SeekBar seekBar) {   
				int value = seekBar.getProgress();
				Library.showToastDebug(getActivity(),"value setSound "+value);

	    		Library.setSound(value);
			}    
		});

		final SeekBar seekBar2 = (SeekBar)dialogView.findViewById(jp.co.se.android.FlameP.R.id.seekbar2); 
		
		seekBar2.setMax(100);
		seekBar2.setProgress(Library.getSe());
		seekBar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {   
		// トラッキング開始時に呼び出されます    
		public void onStartTrackingTouch(SeekBar seekBar) {  
				int value = seekBar.getProgress();
	
				Library.setSe(value);
			}
		// トラッキング中に呼び出されます  
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
				int value = seekBar.getProgress();
	
				Library.setSe(value);
			}            
		// トラッキング終了時に呼び出されます        
		public void onStopTrackingTouch(SeekBar seekBar) {   
				int value = seekBar.getProgress();
	
				Library.setSe(value);
				Library.showToastDebug(getActivity(),"value se "+value);
			}    
		});
		
		final AlertDialog.Builder alertGameMenu = new AlertDialog.Builder(this);  
		alertGameMenu.setView(dialogView);
		//タイトル
		alertGameMenu.setTitle("せってい");
		
		//現在の曲
		Soundtext =
	    	(TextView)dialogView.findViewById(jp.co.se.android.FlameP.R.id.TextSoundName);

		updateNowSoundName();
		
		alertGameMenu.setPositiveButton("OK", new DialogInterface.OnClickListener(){   
	        public void onClick(DialogInterface dialog, int idx) {
	        	
	        	final SeekBar seekBar = (SeekBar)dialogView.findViewById(jp.co.se.android.FlameP.R.id.seekbar); 
	    		int value = seekBar.getProgress();
	    		Library.setSound(value);
	    		final SeekBar seekBar2 = (SeekBar)dialogView.findViewById(jp.co.se.android.FlameP.R.id.seekbar2); 
	    		value = seekBar2.getProgress();
	    		Library.setSe(value);
	    		
	        	DialogOk();
	        }
	    });   
		alertGameMenu.setNegativeButton("CANCEL", new DialogInterface.OnClickListener(){   
	        public void onClick(DialogInterface dialog, int idx) {
    			DialogCancel();

	        }
	    }); 
		
		alertGameMenu.setOnKeyListener(new OnKeyListener() {
	    	//@Override
	    	public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
	    		if (keyCode == KeyEvent.KEYCODE_BACK) {
	    			dialog.dismiss();
	    			//閉じるときにしたい処理
	    			DialogCancel();
	    			return true;
	    		}
	    		return false;
	    	}
	    });
		alertGameMenu.show();
	}
	
	private void DialogOk()
	{
		Library.SaveDataBase(pMain);
		Library.BGM_Play(getActivity(),FlamePAndActivity.this);
 		Library.SE_Play( se_Play.SOUND_SE_EQUIP );
 		Library.setDialogStop(false);
	}
	
	private void DialogCancel()
	{
		Library.setSound(nowBgmBar);
		Library.setSe(nowSeBar);
		//Library.setNowBgm(nowBgm);
 		Library.setDialogStop(false);

		//Library.BGM_STOP_ALL();
		//Library.BGM_Play(getActivity(),FlamePAndActivity.this);		
 		Library.SaveDataBase(pMain);
	}
	
	private void updateNowSoundName()
	{
		//現在の曲
		Soundtext.setText(Library.getNowBgm_name());
		changeSoundName = false;
		Library.BGM_Play(getActivity(),FlamePAndActivity.this);
	}
	public boolean handleMessage(Message arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	
	View dialogViewLank;
	public void showLanking() {
 		Library.setDialogStop(true);


 		
		//保存
		LayoutInflater inflater = LayoutInflater.from(this);   
		dialogViewLank = inflater.inflate(jp.co.se.android.FlameP.R.layout.lank, null);
		
		
		
		AlertDialog.Builder dialogLank = new AlertDialog.Builder(this);  
		dialogLank.setView(dialogViewLank);
		//タイトル
		dialogLank.setTitle("ランキング");
		
		

 		//ランキング　データ
 		Library.httpGetData data = new Library(). new httpGetData();
 		boolean load = false;
 		if(Library.GetLankName() == null)
 		{
 			load = true;
 		}else
 		if(Library.GetLankScore() == null)
 		{
 			load = true;
 		}
 		
 		if(load)
 		{
 			data = Library.http4data( this , Library.URL_GET_LANK ,"UTF-8");
 	 		if(data == null)
 	 		{
 	 			Library.showDialog(this, "エラー", "リスト取得に失敗しました。");
 	 			return;
 	 		}

 			loadingScoaLank( data);

 		}else
 		{
 			data.name = Library.GetLankName();
 			data.scoa = Library.GetLankScore();

 		}
 		
		scoaListUpdate(data);


 		//しょじきん
		TextView text = (TextView)dialogViewLank.findViewById(R.id.TextMyManey);
		text.setText( "" + Library.getManeyALL() + "羊");
		
 		//名前
		TextView text2 = (TextView)dialogViewLank.findViewById(R.id.LankName);
		if(Library.getLankName() == null)
		{
			text2.setText( "----" );			
		}else
		{
			text2.setText( "" + Library.getLankName());
		}
		
		
//		alertGameMenu.setPositiveButton("スコア送信", new DialogInterface.OnClickListener(){   
//	        public void onClick(DialogInterface dialog, int idx) {	        	
//	        	Library.cgi_NetWork( FlamePAndActivity.this  ,
//	        			Library.URL_SET_LANK , "ぴ" , ""+Library.getManeyALL() );
//	        }
//	    });
		dialogLank.setNegativeButton("戻る", new DialogInterface.OnClickListener(){   
	        public void onClick(DialogInterface dialog, int idx) {
    			
	        }
	    }); 
		
		dialogLank.show();
	}
	 public void LankUpLoad(View v)
     {
		 //ランキングネームがないなら入力させる
		 boolean dame = false;
		 if(Library.getLankName() == null)
		 {
			 dame = true;
		 }else		 
		 if(Library.getLankName().length() <= 0)
		 {
			 dame = true;			 
		 }
		 if(dame)
		 {
			 showNameEdit();
			 return;
		 }
		 
		 Library.cgi_NetWork( FlamePAndActivity.this  ,
		  			Library.URL_SET_LANK , Library.getLankName() ,
		  			""+Library.getManeyALL());
		 Library.httpGetData data = Library.http4data( this , Library.URL_GET_LANK ,"UTF-8");
		 
		 loadingScoaLank( data );		 
     }
	 public void LankUpdate(View v)
     {		 
		 Library.httpGetData data = Library.http4data( this , Library.URL_GET_LANK ,"UTF-8");
		 if(data == null)
 		 {
 			Library.showDialog(this, "エラー", "リスト取得に失敗しました。");
 			return;
 		 }
		 loadingScoaLank( data);
     }
	 
	 private void loadingScoaLank(Library.httpGetData data)
	 {
		 Library.setLankName(data.name);
		 Library.setLankScore(data.scoa);
		 saveDataBase.storeBgm(this);	
		 scoaListUpdate(data);
	 }
	 
	 private void scoaListUpdate(Library.httpGetData data )
	 {
		 int textId[] =
 		{
 			R.id.Lank1,
 			R.id.Lank2,
 			R.id.Lank3,
 			R.id.Lank4,
 			R.id.Lank5,
 			R.id.Lank6,
 			R.id.Lank7,
 			R.id.Lank8,
 			R.id.Lank9,
 			R.id.Lank10
 		};
 		
 		for(int i = 0 ; i < Library.MAX_LANKING; i++)
 		{
 			TextView text = (TextView)dialogViewLank.findViewById(textId[i]);
 			text.setText( (i+1)+"ST " + data.name[i] + " " + data.scoa[i] + "羊" );
 		}
	 }

	 
	 
	 
	 
	 
	 
	 
	 
	 public void showNameEdit() {
 		Library.setDialogStop(true);


 		
		//保存
		LayoutInflater inflater = LayoutInflater.from(this);   
		final View dialogViewName = inflater.inflate(jp.co.se.android.FlameP.R.layout.name_edit, null);
		
		
		
		final AlertDialog.Builder dialogNameEdit = new AlertDialog.Builder(this);  
		dialogNameEdit.setView(dialogViewName);
		//タイトル
		dialogNameEdit.setTitle("名前設定");
		
		dialogNameEdit.setMessage("ランキングで使用する名前を\n設定してください。\n（注意）一度設定した名前はデータ\n削除するまで変更できません。\n最大１０文字です。");
 				
			
		
		dialogNameEdit.setPositiveButton("名前決定", new DialogInterface.OnClickListener(){   
	        public void onClick(DialogInterface dialog, int idx) {	        	
	        	//名前の内容を受け取る
	        	final TextView inpText =
	        		(TextView)dialogViewName.findViewById(R.id.EditName);	
	        	
	        	String s = inpText.getText().toString();	        	
	        	//名前が１０文字以上1いかならだめ
	        	if(s.length() <= 0)
	        	{
	        		Library.showDialog( FlamePAndActivity.this , "エラー" , "せめて一文字以上に\nしてください。" );	        		
	        	}else
        		if(s.length() > 10)
	        	{
	        		Library.showDialog( FlamePAndActivity.this , "エラー" , "１０文字以下に\nしてください。" );	        		
	        	}else
	        	{
	        		//おｋ
	        		Library.setLankName(s);
	        	}
	        }
	    });
		dialogNameEdit.setNegativeButton("戻る", new DialogInterface.OnClickListener(){   
	        public void onClick(DialogInterface dialog, int idx) {
    			
	        }
	    }); 
		
		dialogNameEdit.show();
	}
	 
}