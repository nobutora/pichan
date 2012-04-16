package jp.co.se.android.FlameP.activity;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnKeyListener;
import android.database.Cursor;
import android.graphics.PixelFormat;
//import android.opengl.GLSurfaceView;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import jp.co.se.android.FlameP.FlamePAndActivity;
import jp.co.se.android.FlameP.R;
import jp.co.se.android.FlameP.Main.MainData;
//import jp.co.se.android.FlameP.Main.GLRender;
import jp.co.se.android.FlameP.Sound.SoundPlay;
import jp.co.se.android.FlameP.activity.bgmSelect.ListData;
import jp.co.se.android.FlameP.lib.Library;
import jp.co.se.android.FlameP.lib.MessageDialogUtility;
import jp.co.se.android.FlameP.lib.listData;
import jp.co.se.android.FlameP.lib.Library.httpGetData;
import jp.co.se.android.FlameP.save.saveDataBase;

import android.provider.MediaStore;


//import javax.microedition.khronos.egl.EGLConfig;  

//import android.content.Context;
//import android.opengl.GLSurfaceView.Renderer;  
//import android.opengl.GLSurfaceView;  


public class LankingList extends Activity
{
	
	 private ProgressDialog progressDialog;
	
	 public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    
	    Thread.setDefaultUncaughtExceptionHandler(new jp.co.se.android.FlameP.lib.bagtrease(getBaseContext()));
		    
	    ConstantClass.setBgmSelectOn(true);
	    try
	    {
	    	
	    	setContentView(jp.co.se.android.FlameP.R.layout.list_lankview); 
			LayoutInflater inflater = LayoutInflater.from(this);   
			dialogViewLank = inflater.inflate( R.layout.list_lankview , null);

	    	
	    	listSetting();
		    
	    }catch (Exception e) {
			// TODO: handle exception
	    	Library.showToastDebug(this, "onCreat ERREe"+e.toString(),1);	    	
		}
	   
	 }
	 
	 
	// 表示するtwitterデータのリスト   
	private ArrayList<ListData> list = null;   
	// ListAdapter   
	private listData adapter = null;  
	
	
	private int nowList = 0;
	private static final int LIST_NORMAL = 0;
	private static final int LIST_LOAD	 = 1;	

	 private static Handler handler= new Handler();

	 private void listSetting()
	 {
		//ランキング　データ
 		Library.httpGetData dataList = new Library(). new httpGetData();
 		boolean load = false;
 		if(Library.GetLankName() == null)
 		{
 			load = true;
 		}else
 		if(Library.GetLankScore() == null)
 		{
 			load = true;
 		}else
 		if(Library.GetLankName()[0].length() <= 0)
 		{
 			load = true;
 		}else
 		if(Library.GetLankScore()[0].length() <= 0)
 		{
 			load = true;
 		}
 		if(load)
 		{
 			
 			LoadingShow();
 			
 	       (new Thread(runnable3)).start();
 		}else
 		{
 			dataList.name = Library.GetLankName();
 			dataList.scoa = Library.GetLankScore();
 			ListUpdate( dataList );
 		}
 		
	 }
	 
	 private void LoadingShow()
	 {
		 progressDialog = new ProgressDialog(this);
	        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        progressDialog.setMessage("通信中です...");
	        progressDialog.setCancelable(true);
	        progressDialog.show();
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 void ListUpdate(Library.httpGetData dataList )
	 {
		 try
		 {
			 LankingList.this.list = new ArrayList<ListData>();  
		    for(int i = 0 ; i < Library.MAX_LANKING ; i ++ )
	        {
		    	bgmSelect.ListData data = new bgmSelect().new ListData();
		    	
		    	if(i == 9)
		    	{
			    	data.msg = (i+1)+"ST  " + dataList.name[i];
		    	}else
		    	{
			    	data.msg = (i+1)+"ST  " + dataList.name[i];		    		
		    	}
		    	data.type = bgmSelect.LIST_TYPE_RANK;
		    	data.msg2 = dataList.scoa[i] + "羊" ;
		    	data.ok_flag = true;
		    	 LankingList.this.list.add(data);
	        }
		    
		    LankingList.this.adapter = new listData(this , 
					R.layout.listbase , list,
					"曲リスト");  
		    
		    ListView listView = (ListView)this.findViewById( R.id.listviewLank );    
	        // アダプターを設定します       
	        listView.setAdapter(adapter);
	        ManeyNameUp();
		 }catch (Exception e) {
			// TODO: handle exception
			 Library.showDebugDialog( LankingList.this , "err"+e.toString());
		}
       
	 }
	 
	 private void ManeyNameUp()
	 {
		 
	        //しょじきん
			TextView text = (TextView)findViewById(R.id.TextMyManey);
			text.setText( 	  "MY SCORE      " + Library.getManeyALL() + "羊");
			
	 		//名前
			text = (TextView)findViewById(R.id.LankName);
			if(Library.getLankName() == null)
			{
				text.setText( "LANKING NAME  ----" );			
			}else
			if(Library.getLankName().length() == 0)
			{
				text.setText( "LANKING NAME  ----" );			
			}else
			{
				text.setText( "LANKING NAME  " + Library.getLankName());
			}
	 }
	 
	 View dialogViewLank;
	 private void loadingScoaLank(Library.httpGetData data)
	 {
		 Library.setLankName(data.name);
		 Library.setLankScore(data.scoa);
		 saveDataBase.storeBgm(LankingList.this);	
		 ListUpdate( data );
		 //scoaListUpdate(data);
	 }
//	 private void scoaListUpdate(Library.httpGetData data )
//	 {
//		 int textId[] =
// 		{
// 			R.id.Lank1,
// 			R.id.Lank2,
// 			R.id.Lank3,
// 			R.id.Lank4,
// 			R.id.Lank5,
// 			R.id.Lank6,
// 			R.id.Lank7,
// 			R.id.Lank8,
// 			R.id.Lank9,
// 			R.id.Lank10
// 		};
// 		
// 		for(int i = 0 ; i < Library.MAX_LANKING; i++)
// 		{
// 			TextView text = (TextView)dialogViewLank.findViewById(textId[i]);
// 			text.setText( (i+1)+"ST " + data.name[i] + " " + data.scoa[i] + "羊" );
// 		}
//	 }
	 
	 private void showNameEdit() {
 		Library.setDialogStop(true);


 		
		//保存
		LayoutInflater inflater = LayoutInflater.from(LankingList.this);   
		final View dialogViewName = inflater.inflate(jp.co.se.android.FlameP.R.layout.name_edit, null);
		
		
		
		final AlertDialog.Builder dialogNameEdit = new AlertDialog.Builder(LankingList.this);  
		dialogNameEdit.setView(dialogViewName);
		//タイトル
		dialogNameEdit.setTitle("名前設定");
		
		dialogNameEdit.setMessage("ランキングで使用する名前を\n設定してください。\n（注意）一度設定した名前はデータ\n削除するまで変更できません。\n最大6文字です。");
 				
			
		
		dialogNameEdit.setPositiveButton("名前決定", new DialogInterface.OnClickListener(){   
	        public void onClick(DialogInterface dialog, int idx) {	        	
	        	//名前の内容を受け取る
	        	final TextView inpText =
	        		(TextView)dialogViewName.findViewById(R.id.EditName);	
	        	
	        	String s = inpText.getText().toString();	        	
	        	//名前が6文字以上1いかならだめ
	        	if(s.length() <= 0)
	        	{
	        		Library.showDialog( LankingList.this , "エラー" , "せめて一文字以上に\nしてください。" );	        		
	        	}else
        		if(s.length() > 6)
	        	{
	        		Library.showDialog( LankingList.this , "エラー" , "6文字以下に\nしてください。" );	        		
	        	}else
	        	{
	        		//おｋ
	        		Library.setLankName(s);
	        		ManeyNameUp();
	        	}
	        }
	    });
		dialogNameEdit.setNegativeButton("戻る", new DialogInterface.OnClickListener(){   
	        public void onClick(DialogInterface dialog, int idx) {
    			
	        }
	    }); 
		
		dialogNameEdit.show();
	}
	 
	 
	 
	 //戻る
	 public void back(View v)
     {
		 backScene();
     }
	 
	 private void backScene()
	 {
	     ConstantClass.setBgmSelectOn(false);
	     //Library.BGM_RELEASE();
	     ConstantClass.moveActivity = true;
	     ConstantClass.moveActivityTime = ConstantClass.MOVE_ACTIVITY_TIME;
	     saveDataBase.storeBgm(LankingList.this);	
		 try{
			Thread.sleep(200);
		 }catch( Exception e ) {			
		 }
		 finish();		 
	 }
	 
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
        
		 
		if(keyCode==KeyEvent.KEYCODE_HOME){
            //viewFlipper.showPrevious();
    		Library.BGM_STOP_ALL();
        } 
		if(keyCode==KeyEvent.KEYCODE_BACK){
        	//viewFlipper.showPrevious();
        	if(nowList == LIST_LOAD)
        	{
        		nowList = LIST_NORMAL;
        	}else
        	{
        		backScene();
        	}
        	return true;
        }
        return false;
      }
	 
	 protected void onPause()
    {
        super.onPause();
        //gLSurfaceView.onPause();
        {
        	//Library.BGM_STOP_ALL();
        }
        Library.showToastDebug(LankingList.this, "2 onPause");
    }
	 
	 protected void onResume() {    
    	super.onResume();      
    	//Library.BGM_Play(this,this);
    	//Library.BGM_RELEASE();
    	Library.BGM_Play(this,this);
    	Library.showToastDebug(this, "2 onResume");
    }  
    
    protected void onRestart()
    {
    	//Library.BGM_STOP_ALL();
        super.onRestart();
        Library.BGM_Play(this,this);
        Library.showToastDebug(this, "2 onRestart");
    }
    @Override     
    protected void onStop() {     
        super.onStop();    
        //Library.TraseMsg("2 Stop");
        Library.showToastDebug(this, "2 Stop");
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
       //Library.TraseMsg("DESTROY");
       Library.showToastDebug(this, "2 onDestroy");
//       if(pMain.getDownKey() != MainData.KEY_BACK)
//       {
//    	   gemeEnd();
//       
       
       
    }
   
    public void LankUpLoad(View v)
    {
    	
    	ProgressDialog progressDialog = new ProgressDialog(LankingList.this);
	     progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	     progressDialog.setMessage("Loading...");
	     progressDialog.setCancelable(false);
    	
    	
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
		 
	
		 
		 LoadingShow();
        
        (new Thread(runnable2)).start();
    }
    
    

    
    
    
    
    
    
    
    
    
    Library.httpGetData dataUpdate;
	 public void LankUpdate(View v)
    {		 
//		 new Thread(new Runnable() {
//				public void run() {
//					 try{
//		 MessageDialogUtility.Show(LankingList.this, R.string.load );
//					 }catch (Exception e) {
//						// TODO: handle exception
//					}
//				}
//				 }).start();
		 
		 LoadingShow();
		 (new Thread(runnable)).start();
//        
//        new Thread(new Runnable() {
//				public void run() {
//					 try{
//							Thread.sleep(1200);
//						 }catch( Exception e ) {			
//						 }
////		 new Thread(new Runnable(){
////			  public void run() {
////			    handler.post(new Runnable() {
////			      public void run() {
//			    	  
//			    			
//				 dataUpOk = true;
//				 //MessageDialogUtility.Close();
//				}
//		 }).start();
		 
//		 while(true)
//		 {
//			 if(dataUpOk)
//			 {
//				 loadingScoaLank( dataUpdate );
//				 
//				 break;
//			 }
//		 }
    }
   
	 
    private Runnable runnable2 = new Runnable(){
        public void run() {
        	
        	Looper.prepare();
        	
        	Library.cgi_NetWork( LankingList.this  ,
		  			Library.URL_SET_LANK , Library.getLankName() ,
		  			""+Library.getManeyALL());
        	dataUpdate = Library.http4data( LankingList.this , Library.URL_GET_LANK ,"UTF-8");
		
		 	if(dataUpdate == null)
		 	{
			 Library.showDialog( LankingList.this, "エラー", "リスト取得に失敗しました。");
			 progressDialog.dismiss();
			 return;
		 	}
		 	
			 int scoaLow = Integer.parseInt( dataUpdate.scoa[Library.MAX_LANKING - 1 ]);
			 if(Library.getManeyALL() < scoaLow)
			 {
				 Library.showDialog( LankingList.this, "残念", "ランキング外です。精進が足りん！");
				 progressDialog.dismiss();
				 return;
			 }
			 
		 	//loadingScoaLank( dataUpdate );
	    	 // progressDialog.dismiss();
//			 	
			 new Thread(new Runnable(){
			  public void run() {
			    handler.post(new Runnable() {
			      public void run() {
			    	  loadingScoaLank( dataUpdate );
			    	  progressDialog.dismiss();
			      }
			      });
			  }
			 }).start();
        }
    };
	 private Runnable runnable = new Runnable(){
        public void run() {
        	
             dataUpdate = Library.http4data( LankingList.this , Library.URL_GET_LANK ,"UTF-8");
			 if(dataUpdate == null)
			 {
				Library.showDialog( LankingList.this, "エラー", "リスト取得に失敗しました。");
				progressDialog.dismiss();
				return;
			 }	
			 
			 new Thread(new Runnable(){
			  public void run() {
			    handler.post(new Runnable() {
			      public void run() {
			    	  loadingScoaLank( dataUpdate );
			    	  progressDialog.dismiss();
			      }
			      });
			  }
			 }).start();
        }
    };
    private Runnable runnable3 = new Runnable(){
        public void run() {
        	
        	dataUpdate = Library.http4data( LankingList.this , Library.URL_GET_LANK ,"UTF-8");
    		
 	 		if(dataUpdate == null)
 	 		{
 	 			Library.showDialog( LankingList.this, "エラー", "リスト取得に失敗しました。");
 	 			progressDialog.dismiss();
 	 			return;
 	 		}	
			 
			 new Thread(new Runnable(){
			  public void run() {
			    handler.post(new Runnable() {
			      public void run() {
			    	  loadingScoaLank( dataUpdate );
			    	  progressDialog.dismiss();
			      }
			      });
			  }
			 }).start();
        }
    };
}



