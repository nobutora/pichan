package jp.co.se.android.FlameP.activity;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnKeyListener;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
//import android.opengl.GLSurfaceView;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import jp.co.se.android.FlameP.R;
import jp.co.se.android.FlameP.Main.MainData;
//import jp.co.se.android.FlameP.Main.GLRender;
import jp.co.se.android.FlameP.Sound.SoundPlay;
import jp.co.se.android.FlameP.Sound.se_Play;
import jp.co.se.android.FlameP.lib.DataSave;
import jp.co.se.android.FlameP.lib.Library;
import jp.co.se.android.FlameP.lib.ShopData;
import jp.co.se.android.FlameP.lib.listData;
import jp.co.se.android.FlameP.save.saveDataBase;

import android.provider.MediaStore;


//import javax.microedition.khronos.egl.EGLConfig;  

//import android.content.Context;
//import android.opengl.GLSurfaceView.Renderer;  
//import android.opengl.GLSurfaceView;  


public class ItemSelect extends Activity
{
	 public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    
	    Thread.setDefaultUncaughtExceptionHandler(new jp.co.se.android.FlameP.lib.bagtrease(getBaseContext()));
		    
	    ConstantClass.setBgmSelectOn(true);
	    try
	    {
	    	
	    	setContentView(jp.co.se.android.FlameP.R.layout.listskillview); 
	    	nowSelect = 0;
	    	listSetting();
	    	
	    	TextView title = (TextView) findViewById( R.id.textViewTitle );
	    	if(ConstantClass.getItem_View() == ConstantClass.ITEM_VIEW_SHOP)
	    	{
	    		title.setText("SHOP");
	    	}else
    		if(ConstantClass.getItem_View() == ConstantClass.ITEM_VIEW_SKILL)
	    	{
	    		title.setText("SETTING");	    		
	    	}
		    
	    }catch (Exception e) {
			// TODO: handle exception
	    	Library.showToastDebug(this, "onCreat ERREe"+e.toString(),1);
	    	
		}
	 }
	// 表示するtwitterデータのリスト   
	private ArrayList<bgmSelect.ListData> list = null;   
	// ListAdapter   
	private listData adapter = null;  
	
	
	private int nowList = 0;
	private static final int LIST_NORMAL = 0;
	private static final int LIST_LOAD	 = 1;	

	 private static Handler handler= new Handler();

	 private int[] listID = new int [ShopData.FLAME_TYPE_MAX];
	 
	 private int nowSelect = 0;
	 
	 private void listSetting()
	 {
		list = new ArrayList<bgmSelect.ListData>();  
		listID = new int [ShopData.FLAME_TYPE_MAX];
		int idx = 0;
	    for(int i = 1 ; i < ShopData.FLAME_TYPE_MAX ; i ++ )
        {
	    	bgmSelect.ListData data =  new bgmSelect().new ListData();
	    	
	    	data.type = bgmSelect.LIST_TYPE_BGM;
	    	
	    	
	    	if(ConstantClass.getItem_View() == ConstantClass.ITEM_VIEW_SHOP)
	    	{
	    		data.msg  = ShopData.FLAME_NAME[i] + 
	    		"\n" + ShopData.FLAME_PRICE[i]+"　羊";
		    	if(ShopData.FLAME_FREE[i])
				{
		    		continue;
				}
		    	if(ShopData.FLAME_NO_SHOPING[i])
				{
					continue;
				}
				if(Library.nowLank() < ShopData.FLAME_RANK[i] )
				{
					continue;				 
				}
				
				if(DataSave.getWeponOk(i))
				{
					continue;
				}
				if(Library.getManey() < ShopData.FLAME_PRICE[i])
		    	{
		    		data.strType = bgmSelect.STR_RED;	    		
		    	}else
		    	{
		    		data.strType = bgmSelect.STR_NORMAL;
		    	}
	    	}else
    		if(ConstantClass.getItem_View() == ConstantClass.ITEM_VIEW_SKILL)
	    	{
    			data.msg  = ShopData.FLAME_NAME[i];
    			if(DataSave.getWeponOk(i)==false)
				{
    				if(ShopData.FLAME_FREE[i] == false)
    				{
    		    		continue;
    				}
				}
    			if(Library.nowEquip() == i )
    	    	{
    	    		data.strType = bgmSelect.STR_BLUE;	    		
    	    	}else
    	    	{
    	    		data.strType = bgmSelect.STR_NORMAL;
    	    	}
	    	}

    		data.ok_flag = false;
	    	if(nowSelect == i - 1)
	    	{
	    		data.ok_flag = true;
	    	}
			
			
	    	//data.msg2 = ShopData.FLAME_PRICE[i]+"　羊";
	    	

	    	listID[idx] = i;
	    	idx++;
	    	
	    	list.add(data);
        }

	    adapter = new listData(this , 
				R.layout.listbase , list,
				"曲リスト");  
	    
	    ListView listView = (ListView)this.findViewById( jp.co.se.android.FlameP.R.id.listviewItem);    
        // アダプターを設定します       
        listView.setAdapter(adapter);    
        

        // リストビューのアイテムがクリックされた時に呼び出されるコールバックリスナーを登録します        
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener(){        	 
       	 public void onItemClick(AdapterView<?> parent, View view,                 
       		 int position, long id)
	         {
       		 	if( position == nowSelect )
       		 	{
       		 		int selectIdx = listID[nowSelect];
       		 		//購入？
	       		 	if(ConstantClass.getItem_View() == ConstantClass.ITEM_VIEW_SHOP)
	    	    	{
	       		 		buyOk(selectIdx);
	    	    	}else
    	    		if(ConstantClass.getItem_View() == ConstantClass.ITEM_VIEW_SKILL)
	    	    	{
	       		 		Equip(selectIdx);	    	    		
	    	    	}
       		 		
       		 		
       		 	}else
       		 	{
	       		 	nowSelect = position;
	       		 	int selectIdx = listID[position];
	       		 	//購入処理
	       		 	//Library.SE_Play( se_Play.SOUND_SE_GOLD );
	       		 	
	       		 	
	       		 	setSkillDraw( selectIdx );
       		 	}
	         }
        });
        
        int selectIdx = listID[nowSelect];
        //int imageID = ShopData.FLAME_PICT[nowSelect];		 	
		setSkillDraw( selectIdx );

	 }
	 
	 private void Equip(final int idx)
	 {
		 Library.setEquip(idx);
		 listSetting();
	 }
	 
	 private void buyOk(final int idx)
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
					    AlertDialog.Builder ad=new AlertDialog.Builder(ItemSelect.this);
					    ad.setTitle(""+ShopData.FLAME_NAME[idx]);
					    ad.setMessage("購入しますか？\n所持金　"
					    		+Library.getManey()+"羊"
					    		+"\n値段 "+ShopData.FLAME_PRICE[idx]+" 羊");
					    
					    ad.setInverseBackgroundForced(false);
					    err = 2;
					    
					    ad.setPositiveButton("購入",
					    	new DialogInterface.OnClickListener()
						    {
						        public void onClick(DialogInterface dialog,int whichButton) {
						        	DataSave.setDataBaseWeponTime(idx, ShopData.SHOP_LENTAL_TIME);
						        	Library.SE_Play( se_Play.SOUND_SE_GOLD );
						        	Library.addManey(-ShopData.FLAME_PRICE[idx]);
						        	nowSelect = 0;
							    	listSetting();
									//セーブ
						        }
						    }
					    );
					    err = 3;
					    ad.setNegativeButton("やめる",
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
	 
	 
	 private void setSkillDraw(int idx)
	 {
		 int imageID = ShopData.FLAME_PICT[idx];
		 if(imageID == 0)
	 	 {
	 		//デフォ画像
	 		//imageID = R.drawable.wepon_pict_def;
	 	 }		 
		 LinearLayout layout = (LinearLayout) findViewById( R.id.skill_view );
	     Drawable pict = getResources().getDrawable(imageID);       		    
	 	 layout.setBackgroundDrawable(pict);

	 	 String helpText = "";
	 	 
	 	 for(int i = 0; i < ShopData.FLAME_HELP_LEN; i ++)
	 	 {
	 		 if(ShopData.FLAME_HELP[idx][i] == null)
	 		 {
	 			 continue;
	 		 }
	 		 helpText += ShopData.FLAME_HELP[idx][i]+"\n";
	 	 }
	 	//説明文 
	 	TextView help = (TextView) findViewById( R.id.textViewHelp );

	 	help.setText(helpText);
	 	//残金
	 	TextView maney = (TextView) findViewById( R.id.TextManey );

	 	maney.setText("所持金"+Library.getManey()+"羊");
	 }
	 
	 
	 
	 //戻る
	 public void back(View v)
     {
		 backScene();
     }
	 
	 private void backScene()
	 {
		 ConstantClass.moveActivity = true;
	     ConstantClass.setBgmSelectOn(false);
	     //Library.BGM_RELEASE();
	     ConstantClass.moveActivityTime = ConstantClass.MOVE_ACTIVITY_TIME;
		 try{
			//Thread.sleep(200);
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
        	backScene();
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
        Library.showToastDebug(this, "2 onPause");
    }
	 
	 protected void onResume() {    
		 
		 
		 Library.BGM_Play(this,this);
    	super.onResume();      
    	//Library.BGM_Play(this,this);
    	//Library.BGM_RELEASE();
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
        //Library.BGM_STOP_ALL();
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
}



