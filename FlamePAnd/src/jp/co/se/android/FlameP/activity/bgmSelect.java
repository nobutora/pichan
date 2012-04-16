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
//import android.opengl.GLSurfaceView;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import jp.co.se.android.FlameP.R;
import jp.co.se.android.FlameP.Main.MainData;
//import jp.co.se.android.FlameP.Main.GLRender;
import jp.co.se.android.FlameP.Sound.SoundPlay;
import jp.co.se.android.FlameP.lib.Library;
import jp.co.se.android.FlameP.lib.listData;
import jp.co.se.android.FlameP.save.saveDataBase;

import android.provider.MediaStore;


//import javax.microedition.khronos.egl.EGLConfig;  

//import android.content.Context;
//import android.opengl.GLSurfaceView.Renderer;  
//import android.opengl.GLSurfaceView;  


public class bgmSelect extends Activity
{
	 public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    
	    Thread.setDefaultUncaughtExceptionHandler(new jp.co.se.android.FlameP.lib.bagtrease(getBaseContext()));
		    
	    ConstantClass.setBgmSelectOn(true);
	    try
	    {
	    	
	    	setContentView(jp.co.se.android.FlameP.R.layout.listview); 
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
		 this.list = new ArrayList<ListData>();  
	    for(int i = 0 ; i < SoundPlay.SOUND_SOUND_MAX ; i ++ )
        {
	    	ListData data = new ListData();
	    	
	    	data.msg = SoundPlay.sound_Str_List[i];
	    	if(Library.getNowBgm() == i)
	    	{
	    		data.ok_flag = true;
	    	}else
	    	{
	    		data.ok_flag = false;
	    	}
	    	list.add(data);
        }
	    
	    if(Library.getSoundLoadNum() >= 1)
	    {
	    	ListData data = new ListData();
	    	data.msg = "";
	    	data.msg2 = "";
	    	data.strType = 0;	    	
	    	data.type = LIST_NO_DRAW;
	    	list.add(data);
	    }
	    

	    for(int i = 0 ; i < Library.getSoundLoadNum() ; i ++ )
        {
	    	ListData data = new ListData();	    	
	    	data.msg = SoundPlay.Load_Bgm_Name +(1+i);
	    	if(Library.getNowBgm() == i + SoundPlay.SOUND_SOUND_MAX )
	    	{
	    		data.ok_flag = true;
	    	}else
	    	{
	    		data.ok_flag = false;
	    	}	
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
       		 	Library.showToastDebug(bgmSelect.this,
       		 		"position"+position);
       		 	try
       		 	{
	       		 	if(position >= SoundPlay.SOUND_SOUND_MAX)
	       		 	{
	       		 		if(position == SoundPlay.SOUND_SOUND_MAX)
	       		 		{
	       		 			return;
	       		 		}
	       		 		position--;
	       		 		if(Library.getNowBgm() == position)
	       		 		{
		       		 		final int fiPosition = position;
		       		 		final int LoadPosition = fiPosition - SoundPlay.SOUND_SOUND_MAX;
			       		 	new Thread(new Runnable() {
			    			    public void run() {
			    			      handler.post(new Runnable() {
			    			    	  public void run() 
			    			    {
			    					 int  err = 0;
			    					 try
			    					 {
			    						err = 1;
			    					    AlertDialog.Builder ad=new AlertDialog.Builder(bgmSelect.this);
			    					    ad.setTitle(SoundPlay.Load_Bgm_Name + (LoadPosition+1));
			    					    ad.setMessage("読み込んだ曲を消去しますか？");
			    					    ad.setInverseBackgroundForced(false);
			    					    err = 2;
			    					    
			    					    ad.setPositiveButton("消去",
			    					    	new DialogInterface.OnClickListener()
			    						    {
			    						        public void onClick(DialogInterface dialog,int whichButton) {
			    						        	if(Library.LoadSoundDel(bgmSelect.this ,
			    										LoadPosition) == false)
			    									{
//			    										Library.BGM_STOP_ALL();
//			    										Library.BGM_Play(bgmSelect.this,
//				    					       		 		bgmSelect.this,
//				    					       		 		0 );
			    									}else
			    									{
//			    										if(fiPosition < Library.getNowBgm())
//			    										{
//			    											Library.setNowBgm(Library.getNowBgm()-1);
//			    										}
			    									}
			    						        	Library.BGM_Play(bgmSelect.this,
				    					       		 		bgmSelect.this,
				    					       		 		0 );
			    									listSetting();
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
			       		  return;
	       		 		}		       		 	
	       		 	}
	       		 	
	       		 	{
		       		 	Library.BGM_STOP_ALL();
			       		 	Library.BGM_Play(bgmSelect.this,
			       		 			bgmSelect.this,
			       		 			position);	       		 
			       		 	listSetting();
	       		 	}
       		 	}catch (Exception e) {
					// TODO: handle exception
       		 		Library.showToastDebug(bgmSelect.this,
           		 		"onItemClickERR "+e.toString());
				}
	         }
        });
        

	 }
	 
	 
	 
	 boolean chackBocks[] = new boolean [ SoundPlay.SOUND_SOUND_ALL_MAX ];
	 
	 //チェックボックスセット
	 private void listSettingCheack()
	 {
		this.list = new ArrayList<ListData>();  
	    
		
	    for(int i = 0 ; i < Library.getSoundLoadNum() ; i ++ )
        {
	    	ListData data = new ListData();	    	
	    	data.msg = SoundPlay.Load_Bgm_Name +(1+i);
	    	data.msg2= "";
	    	data.strType= 0;	    	
	    	data.type = LIST_CHEACK_BOX;
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
       		 	Library.showToastDebug(bgmSelect.this,
       		 		"position"+position);
       		 	try
       		 	{
	       		 	CheckBox userCheckBox = (CheckBox) view.findViewById(jp.co.se.android.FlameP.R.id.checkBoxDel);
	       		 	
	       		 	if(chackBocks[position])
	       		 	{
	       		 		userCheckBox.setChecked(false);
	       		 		chackBocks[position] = false;
	       		 	}else
	       		 	{
	       		 		userCheckBox.setChecked(true);
	       		 		chackBocks[position] = true;
	       		 	}

       		 	}catch (Exception e) {
					// TODO: handle exception
       		 		Library.showToastDebug(bgmSelect.this,
           		 		"onItemClickERR "+e.toString());
				}
	         }
        });
		 
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
	     saveDataBase.storeBgm(this);	
		 try{
			Thread.sleep(200);
		 }catch( Exception e ) {			
		 }
		 finish();		 
	 }
	 
	 public static final int MUSIC_RESULT = 1;
	 //取り込み
	 public void music_load(View v)
     {
		 ConstantClass.moveActivity = true;
		 ConstantClass.moveActivityTime = ConstantClass.MOVE_ACTIVITY_TIME;
		 Intent intent = new Intent(Intent.ACTION_GET_CONTENT);  
		 intent.setType("audio/*");
		 startActivityForResult(Intent.createChooser(intent, "音楽ファイルを選択"),MUSIC_RESULT);   
     }
	 
	 public void music_delete(View v)
	 {
		 if(Library.getSoundLoadNum() <= 0)
		 {
			Library.showToast(this, "読み込んだ音楽がありません！"); 
		 }else
		 {
			 nowList = LIST_LOAD;
			 this.chackBocks = new boolean [ SoundPlay.SOUND_SOUND_ALL_MAX ];
			 setContentView(jp.co.se.android.FlameP.R.layout.listradio); 
			 listSettingCheack();
		 }
		 
		 
	 }
	 
	 public void del_back(View v)
	 {
		 nowList = LIST_NORMAL;
		 setContentView(jp.co.se.android.FlameP.R.layout.listview); 
		 listSetting();
	 }
	 
	 
	 public void All_Del(View v)
	 {
		 CheckBox userCheckBox = (CheckBox) findViewById(jp.co.se.android.FlameP.R.id.checkBoxAll);
		 
		 boolean ok = userCheckBox.isChecked();
		
		 if(ok)
		 {
			 userCheckBox.setChecked(false);
		 }else
		 {
			 userCheckBox.setChecked(true);			 
		 }
		 
		 for(int i = 0 ; i < SoundPlay.SOUND_SOUND_ALL_MAX; i++)
		 {
			 if(ok)
			 {
				 this.chackBocks[i]=false;
			 }else
			 {
				 this.chackBocks[i]=true;				 
			 }
		 }
		 this.chackBocks = new boolean [ SoundPlay.SOUND_SOUND_ALL_MAX ];
		 
		 
		 listSettingCheack();
	 }
	 
	 public void delMusic(View v)
	 {
		 for(int i = 0 ; i < Library.getSoundLoadNum() ; i ++ )
	     {
			 if(this.chackBocks[i])
			 {
				 if(Library.LoadSoundDel(bgmSelect.this ,
					i) == false)
				 {
					Library.BGM_STOP_ALL();
					Library.BGM_Play(bgmSelect.this,
		   		 		bgmSelect.this,
		   		 		0 );
				 }else
				 {
					if(i < Library.getNowBgm())
					{
						Library.setNowBgm(Library.getNowBgm()-1);
					}
				 }
			 }
		 }
		 
		 setContentView(jp.co.se.android.FlameP.R.layout.listview); 
		 listSetting();
	 }
	 
	 
	  @Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent data)  
	  { 
		  if(data == null)
		  {
			  return;
		  }
		  int err = 0;
		  Library.showToastDebug(this, "onActivityResult",1);
		  if (requestCode == MUSIC_RESULT )
		  {
			  try
			  {
				  Uri uri = data.getData();
				  Library.showToastDebug(this, "uri"+uri.toString(),1);
				  
//				  Library.showToastDebug(this, "data.getDataString()"+data.getDataString(),1);
//				  Library.showToastDebug(this, "data.getPackage()"+data.getPackage(),1);
//				  Library.showToastDebug(this, "data.getScheme()"+data.getScheme(),1);
				  
				  if(Library.LoadSoundAdd(this,uri.toString())==false)
				  {
					  Library.showDialog(this, "エラー", "そのファイルは\nもう読み込んでるよ!");
				  }
				  err = 1;
				  
				  File file = new File(uri.toString());
		            
		            //パスからファイル名を取得する
		          String fileName = file.getName();
		          Library.showToastDebug(this, "fileName"+fileName,1);

				  
				  
//			  Cursor personCursor = managedQuery(uri, null, null, null, null); 
//              if (personCursor.moveToFirst()) { 
//            	  int albumimageIndex = 
//            		  personCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ART); 
//            	  	String albumart = personCursor.getString(albumimageIndex); 
//	              	Library.showToastDebug(bgmSelect.this,
//	           		 		"albumart"+albumart,1);
//              } 
			  
			  
			  
		         //ファイルパスを取得したい場合は下記のようにContentResolverを活用する
		         //ContentResolver経由でファイルパスを取得
//		         ContentResolver cr = getContentResolver();
//		         String[] columns = {MediaStore.Images.Media.DATA };
//		         
//		         err = 2;
//				  
//		         
//		         
//		         
//		         Cursor c = cr.query(uri, columns, null, null, null);
//		         
//		         err = 3;
//		         if (c.moveToFirst()) { 
//	            	  int albumimageIndex = 
//	            		  c.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ART); 
//	            	  err = 4;
//	            	  String albumart = c.getString(albumimageIndex); 
//	            	  err = 5;
//		              	Library.showToastDebug(bgmSelect.this,
//		           		 		"albumart"+albumart,1);
//	              } 
		     } catch (Exception e){
		     // } catch (FileNotFoundException e) {
		         // TODO 自動生成された catch ブロック
		         e.printStackTrace();
		         Library.showToastDebug(this, 
		        		 "EERRRR"
		        		 +e.toString()+"\n"+err,1);
		         return;
		     }
		 }
		 listSetting();
		  
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
        Library.showToastDebug(this, "2 onPause");
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
    
   
    public static final int LIST_TYPE_BGM     = 0;
    public static final int LIST_TYPE_ITEM 	  = 1;
    public static final int LIST_NO_DRAW   	  = 2;
    public static final int LIST_CHEACK_BOX   = 3;
    public static final int LIST_TYPE_RANK 	  = 4;
    

    public static final int STR_NORMAL 	= 0;
    public static final int STR_RED 	= 1;
    public static final int STR_BLUE 	= 2;
    
    public class ListData
    {
    	boolean ok_flag = false;
    	String msg = "";
    	String msg2 = "";
    	
    	int type = 0;
    	
    	int strType = 0;
    	
    	public int getStrType ()
    	{
    		return strType;
    	}
    	public int getType ()
    	{
    		return type;
    	}
    	public boolean getFlag ()
    	{
    		return ok_flag;
    	}
    	public String getMsg ()
    	{
    		return msg;
    	}
    	public String getMsg2 ()
    	{
    		return msg2;
    	}
    }
    
    
}



