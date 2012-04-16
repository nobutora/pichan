
package jp.co.se.android.FlameP.lib;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

//import func.Library.HttpConnection;

import android.graphics.Color;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.*;
import android.database.sqlite.SQLiteDatabase;

import jp.co.se.android.FlameP.FlamePAndActivity;
import jp.co.se.android.FlameP.Main.MainData;
import jp.co.se.android.FlameP.Sound.*;

import jp.co.se.android.FlameP.activity.bgmSelect;
import jp.co.se.android.FlameP.draw.*;
import jp.co.se.android.FlameP.save.saveDataBase;
import jp.co.se.android.FlameP.scene.*;


// 表示用のクラス
public class Library  {
    
	 //ランダム
	 public static final Random	random			= new Random();
	
	 public static FlamePAndActivity pFlame;
	 
	 public static void setFlameP(FlamePAndActivity flame)
	 {
		 pFlame = flame;
	 }
	 
	 public Library()
	 {
		this.init();
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
	
	 public static void TraseMsg(String msg)
	 {
		 if(MainData.DEBUG_MODE )
		 {
			 System.out.println(" Trase: "+msg);
		 }
	 }

	 
	 public static int COLOR_RGB(int r , int g , int b)
	 {
		 return COLOR_ARGB(255 , r , g , b );
	 }

	 public static int COLOR_ARGB(int alphe ,int r , int g , int b)
	 {
		 return Color.argb(alphe , r , g , b );
	 }
	 
	 public static double getTargetAngle(int basex ,int basey , int targetX , int targetY)
	 {
		 double rot = Math.atan2(targetY - basey ,
 				targetX - basex );
 		 return rot * 180 / Math.PI;
	 }
	 
	 public static double getAngleMoveX(double rot , int speed )
	 {
		 double mx = ( Math.cos(rot * Math.PI / 180) * speed);
		 
 		 return mx;
	 }
	 public static double getAngleMoveY(double rot , int speed )
	 {
		 double my = ( Math.sin(rot * Math.PI / 180) * speed);
		 
 		 return my;
	 }
	 
	 /**
	  * ��ʉ�
	  * @param Num
	  */
	 public static void SE_Play(int Num)
	 {
		 se_Play.se_play( Num );
	 }
	 /**
	  * BGM
	  * @param Num
	  */
	 public static void BGM_Play(Activity act, Context con, int Num)
	 {
		 if(LoadingEnd == false)
		 {
			 return;
		 }
		 if(act != null)
		 {
			 Library.showToastDebug(act, "i_Bgm"+i_Bgm+
					 "\n　Num"+Num,2);
		 }
		 if(i_Bgm == -1)
		 {
			 return;
		 }


		 if(i_Bgm != Num)
		 {
			 if(BGM_STOP_ALL()==false)
			 {
				 Library.showToastDebug(act, "1 BGM_STOP_ALL   ERR ");				 
			 }
			 if(act != null)
			 {
				 Library.showToastDebug(act, "BGM_Play STOP ");
			 }
		 }
		 i_Bgm = Num;
		 if(MainData.sound.PlayNew( Num,con, act)==false)
		 {
			 if(act != null)
			 {
				 Library.showToastDebug(act, "Play 失敗");
			 }
		 }
	 }
	 public static void BGM_Play(int Num,Context con)
	 {

		 BGM_Play( null ,con, Num);
	 }
	 public static void BGM_Play(Activity act , Context con)
	 {

		 BGM_Play( act , con , i_Bgm);
	 }
	 
	 public static boolean BGM_STOP_ALL(Activity act)
	 {
		 showToastDebug(act, "BGM_STOP_ALL",2);
		 return BGM_STOP_ALL();
	 }
	 public static boolean BGM_STOP_ALL()
	 {
		 if(MainData.sound != null)
		 {
			return MainData.sound.Stop();
		 }
		 return false;
	 }
	 

	 public static void BGM_RELEASE()
	 {
		 MainData.sound.dispose();
	 }
	 
	 public static void BGM_RESUME()
	 {
		 //Library.TraseMsg("BGM_RESUME");
		 MainData.sound.BgmRePlay();
	 }
	 public static boolean LoadSoundDel(Activity act , int idx)
	 {
		 return MainData.sound.LoadSoundDel(act, idx);
	 }
	 public static boolean LoadSoundAdd(Activity act , String url)
	 {
		 return MainData.sound.LoadSoundAdd(act, url);
	 }
	 public static void LoadSound(Activity act, String url[])
	 {
		 MainData.sound.LoadSound( act, url);
	 }
	 
	 
	 public static int getNowBgm()
	 {
		 return i_Bgm;
	 }
	 public static void setNowBgm(int bgm)
	 {
		 i_Bgm = bgm;
	 }
	 public static String getNowBgm_name()
	 {
		 if(i_Bgm < SoundPlay.SOUND_SOUND_MAX)
		 {
			 return SoundPlay.sound_Str_List[i_Bgm];
		 }else
		 {
			 return SoundPlay.Load_Bgm_Name + ""+ 
			 	(i_Bgm - SoundPlay.SOUND_SOUND_MAX+1);
		 }		 
	 }
	 
	 public static int getSoundVol()
	 {
		return  MainData.sound.getvolume();
	 }
	 public static int getSoundLoadNum()
	 {
		return  MainData.sound.getLoadBgmNum();
	 }
	 public static String[] getUriList()
	 {
		return  MainData.sound.getUriList();
	 }
	 
	 private static String[]  LankNames;
	 public static String[] GetLankName()
	 {
		 return LankNames;
	 }
	 public static void setLankName(String[] name)
	 {
		 LankNames = name;
	 }
	 
	 private static String[]  LankScore;
	 public static String[] GetLankScore()
	 {
		 return LankScore;
	 }
	 public static void setLankScore(String[] name)
	 {
		 LankScore = name;
	 }
	 
	 
	 static public  int SCOA_NAME_BYTE = 12;
	 
	 
	 static final int SCOA_SAVE_IDX = 0;
	 
	 static final int SCOA_SAVE_VAR = 1;


	 static final int SCOA_MANEY_IDX = 1;

	 static final int SCOA_MANEY_VAR = 2;
	 //���o�[�W����
	 static final int SCOA_MANEY_VAR_NO_SHOP = 1;

	 static final int NOW_SAVE_VAR = 2;

	 
	 /**
	  * セーブ要素
	  */
	 
	 static String s_ErrMSG = "";
	 
	 static String s_Lank_Name = "";
	 //殺害数
	 static int i_Destroy_All = 0;
	 //金合計
	 static  int i_Maney_All = 0;
	 //金
	 static  int i_Maney = 0;
	 //サウンド
	 static  int i_Sound = 0;
	 //効果音
	 static  int i_Se = 0;
	 //BGM
	 static  int i_Bgm = -1;
	 //店
	 static int  i_Shop = 0;
	 //現在のランク
	 static int  i_Lank = 0;
	 //現在の装備
	 static int  i_Equip = 0;
	 
	 static final int MAX_MANEY = Integer.MAX_VALUE;
	 
	 public static final int NOW_VAR = 2;
	 
	
	 public static int getShop()
	 {
		 return i_Shop;
	 }
	 public static void setShop(int shop)
	 {
		 if(shop >= ShopData.GUREAD_MAX)
		 {
			 i_Shop = ShopData.GUREAD_MAX - 1;
		 }else
		 {
			 i_Shop = shop;
		 }
	 }
	 
	 public static void setLank(int lank)
	 {
		 if(lank >= ShopData.GUREAD_MAX)
		 {
			 i_Lank = ShopData.GUREAD_MAX - 1;
		 }else
		 {
			 i_Lank = lank;
		 }
	 }
	 public static int nowLank()
	 {
		return i_Lank;
	}

	 public static final String EquipSaveName = "Equip";
	 public static void setEquip(int Equip)
	 {
		 i_Equip = Equip;
	 }
	 public static int nowEquip()
	 {
		return i_Equip;
	}
	 
	 // Read 
	 public static SharedPreferences read_xml(MainData main, String xmlName ) { 	 
		 
		 return main.getSharedPreferencesMain( 
			xmlName, Activity.MODE_WORLD_READABLE | Activity.MODE_WORLD_WRITEABLE );
	 }
	 
	 public static void SaveDataBase(MainData main){
		 SaveDataBase( main , false );
	 }	 
	 public static void SaveDataBase(MainData main ,boolean debug){
		 
		 try{
			 
			 Editor edit = main.pref.edit();

			 if(debug)
			 {
				 if(MainData.DEBUG_MODE)
				 {
					 edit.putString( "ERRMSG", s_ErrMSG ); 
				 }
				 edit.commit();
				 return;
			 }
			 

			 //VAR
			 edit.putInt( "Var", NOW_VAR ); 
			 edit.putString( "LankName", s_Lank_Name ); 
			
			 
			 //お金合計
			 edit.putInt( "money_all", i_Maney_All ); 

			 //殺害合計
			 edit.putInt( "destroy_all", i_Destroy_All ); 
			 //お金
			 edit.putInt( "money", i_Maney ); 
			 //サウンド
			 edit.putInt( "sound", i_Sound ); 
			 //SE
			 edit.putInt( "se", i_Se ); 
			 //BGM
			 edit.putInt( "bgm", i_Bgm );
			 //店
			 edit.putInt( "shop", getShop()); 
			 //ランク
			 edit.putInt( "lank", nowLank());
			 //装備
			 edit.putInt( EquipSaveName, nowEquip()); 
			 
			 edit.commit();
			 
		 }catch (Exception e) {
			// TODO: handle exception
		}
		 
	}
	 public static boolean LoadingEnd = false;
	 
	 public static void LoadManey(MainData main)
	 {
		 Library.showToastDebug(main.getActivity()
				 , "LoadManey",3);
		 try{
			 SharedPreferences pref = read_xml( main, "save" );
			 
			 int var = pref.getInt( "Var", 0 ); 
			 
			 
			 s_Lank_Name = pref.getString( "LankName", "" ); 
			 
			 if(MainData.DEBUG_MODE)
			 {
				 s_ErrMSG = pref.getString( "ERRMSG", "" ); 
			 }
			 
			 
			 i_Destroy_All = pref.getInt( "destroy_all", 0 ); 		
			 //お金
			 i_Maney = pref.getInt( "money", FIRST_MANEY ); 			
			 
			 if(i_Maney <= 0)
			 {
				 i_Maney = 0 ;
			 }
			 
			 if(var <= 1)
			 {
				 i_Maney_All = i_Maney; 	
			 }else
			 {
				 i_Maney_All = pref.getInt( "money_all", FIRST_MANEY ); 		
			 }
			 //サウンド
			 i_Sound = pref.getInt( "sound", FIRST_BGM_VOL ); 
			 //se
			 i_Se = pref.getInt( "se", FIRST_SE_VOL );
			 //bgm
			 i_Bgm = pref.getInt( "bgm", 0 );
			 
			 //店
			 //サウンド
			 int shop = pref.getInt( "shop", 0 ); 
			 setShop(shop);	

			 int lank = pref.getInt( "lank", 0 ); 
			 setLank(lank);	

			 int equip = pref.getInt( EquipSaveName, FIRST_EQUIP );
			 setEquip(equip);
			 
			 
		 }catch (Exception e) {
			// TODO: handle exception
		}
		 Library.setSound(Library.getSound());			
	     Library.setSe(Library.getSe());
		 LoadingEnd = true;
	 }
	 
	public static int getManey()
	{
		return i_Maney;
		//return 9999999;

	}

	public static int getManeyALL()
	{
		return i_Maney_All;
		//return 9999999;

	}
	public static int getDestroyALL()
	{
		return i_Destroy_All;
		//return 9999999;

	}

	public static void setLankName(String Name)
	{
		s_Lank_Name = Name;
	}
	public static String getLankName()
	{
		return s_Lank_Name;
	}
	
	public static void setErrMsgSave(String Name)
	{
		s_ErrMSG = Name;
	}
	public static String getErrMsg()
	{
		return s_ErrMSG;
	}
	
	public static final int FIRST_MANEY = 0;
	
	//public static final int FIRST_MANEY = 1000000;
	
	public static final int FIRST_EQUIP = 1;
	public static final int FIRST_BGM_VOL = 40;
	public static final int FIRST_SE_VOL  = 40;
	public static void InitData(MainData main)
	{
		i_Maney = FIRST_MANEY;
		i_Maney_All = FIRST_MANEY;
		i_Destroy_All = 0;
		i_Sound = FIRST_BGM_VOL;
		i_Se    = FIRST_SE_VOL;
				
		s_Lank_Name = "";
		setShop(0);
		setLank(0);
		setEquip(FIRST_EQUIP);
		
		DataSave.DataDelete(main.getdb());
		try
		{
			main.dqSave.writeDBAll(main.getdb());
			SaveDataBase(main);				 
		}catch (Exception e) {
			// TODO: handle exception
		}
		 
	}
	

	public static void addDestroy(int add)
	{
		i_Destroy_All += add;
	}
	
	public static void addManey(int maney)
	{
		i_Maney += maney;
		if(maney >= 1)
		{
			i_Maney_All += maney;
		}
		if(i_Maney_All >= MAX_MANEY)
		{
			i_Maney_All = MAX_MANEY;			
		}
		if(i_Maney >= MAX_MANEY)
		{
			i_Maney = MAX_MANEY;			
		}
	}
	
	
	public static final int MAX_VAL = 100;
	public static int getSound()
	{
		return i_Sound;
	}
	public static void setSound(int volue)
	{
		i_Sound = volue;
		
		if(i_Sound <= 0)
		{
			i_Sound = 0;
		}
		
		if(i_Sound >= MAX_VAL)
		{
			MainData.sound.SetVolue(MAX_VAL);			
		}else
		{
			MainData.sound.SetVolue(i_Sound);
		}
	}
	
	public static int getSe()
	{
		return i_Se;
	}
	public static void setSe(int volue)
	{
		i_Se = volue;
		
		if(i_Se <= 0)
		{
			i_Se = 0;
		}
		
		if(i_Se >= MAX_VAL)
		{
			MainData.se_sound.setVolume(MAX_VAL);			
		}else
		{
			MainData.se_sound.setVolume(i_Se);
		}
	}
	
	 public static void GameEnd(MainData main)
	 {
		 saveDataBase.storeBgm(main.getActivity());		 
		 Library.SaveDataBase(main);
		 
		 
		 
		 try
		 {
			 main.dqSave.writeDBAll(main.getdb());
		 }catch (Exception e) {
			// TODO: handle exception
		 }
		 
		 main.terminateGame();	 
	 }
	
	 static String Baseurl = "http://gasutorageta.web.fc2.com/download/doja/";
		
	 
	 public static String getSaverData(String sUrl) {  
	 
		 sUrl += Baseurl;
	 
	    HttpClient objHttp = new DefaultHttpClient();   
	    HttpParams params = objHttp.getParams();   
	    HttpConnectionParams.setConnectionTimeout(params, 1000); //接続のタイムアウト   
	    HttpConnectionParams.setSoTimeout(params, 1000); //データ取得のタイムアウト   
	    String sReturn = "";   
	    try {   
	        HttpGet objGet   = new HttpGet(sUrl);   
	        HttpResponse objResponse = objHttp.execute(objGet);   
	        if (objResponse.getStatusLine().getStatusCode() < 400){   
	            InputStream objStream = objResponse.getEntity().getContent();   
	            InputStreamReader objReader = new InputStreamReader(objStream);   
	            BufferedReader objBuf = new BufferedReader(objReader);   
	            StringBuilder objJson = new StringBuilder();   
	            String sLine;   
	            while((sLine = objBuf.readLine()) != null){   
	                objJson.append(sLine);   
	            }   
	            sReturn = objJson.toString();   
	            objStream.close();   
	        }   
	    } catch (IOException e) {   
	        return null;   
	    }      
	    return sReturn;   
	}  

	 public static int YesNoCommandToush(int toushX , int toushY)
	 {
		 int commandX = 66;
		 int commandY = 164;
		 int height = UI.CUR_SPACE;		 
		 int width = 175;
		 
		 
		 
		 commandX = (int)((float)commandX * (float)MainData.Width_Size);
		 commandY = (int)((float)commandY * (float)MainData.Height_Size);
		 width = (int)((float)width * (float)MainData.Width_Size);
		 height = (int)((float)height * (float)MainData.Height_Size);
		 
		 for(int i = 0; i < 2 ; i++)
		 {
			 if(toushX >= commandX && 
				 toushX <= commandX + width&&

				 toushY >= commandY+height*i && 
				 toushY <= commandY+height*i + height )
			 {
				 return i;
			 }
		 }
		 return -1;
	 }
	 
	 
	 public static void showToast(final Activity activity,final String text) {
		 
		 new Thread(new Runnable() {
			    public void run() {
			      handler.post(new Runnable() {
			    	public void run() {
			    		Toast.makeText(activity, text,Toast.LENGTH_SHORT).show();
			    	}
			      });
			    }
			  }).start();
		 
	}
	 
	 public static void showToastDebug(final Activity activity,String text,int num) {
		 if(MainData.DEBUG_MODE_DRAW)
		 {
			 if(MainData.DEBUG_NUMBER == num ||
					 MainData.DEBUG_NUMBER == 0)
			 {
				 showToast(activity, text);
			 }
		 }
	}
	 public static void showToastDebug(final Activity activity,String text) {
		 if(MainData.DEBUG_MODE_DRAW)
		 {
			 showToastDebug( activity, text,0);
		 }
	}
	 
	 
	 
	 private static Handler handler= new Handler();
	 //ダイアログ
	 public static void showDialog(final Activity activity,
		      final String title, final String text) {
		 if(DialogStop)
		 {
			 return;
		 }
		 DialogStop = true;
		 	  new Thread(new Runnable() {
			    public void run() {
			      handler.post(new Runnable() {
			    	public void run() {
			    		 DialogStop = true;
			    		AlertDialog.Builder ad=new AlertDialog.Builder(activity);
					      ad.setTitle(title);
					      ad.setMessage(text);
					      ad.setInverseBackgroundForced(false);
					      ad.setPositiveButton("OK",new DialogInterface.OnClickListener() {
					          public void onClick(DialogInterface dialog,int whichButton) {
					              activity.setResult(Activity.RESULT_OK);
					              DialogStop = false; 
					          }
					      });
					      ad.create();
					      ad.show();
			        }
			      });
			    }
			  }).start();

		 
		      
		    }    	
	 public static void showDebugDialog(final Activity activity,String text) {
			if(MainData.DEBUG_MODE_DRAW)
			{
				showDialog( activity,
			       "DebugDialog", text);
			}
		}
	 //============================================
	 //ダイアログ2択
	 //
	 
	 public static int getDecodeX(int x)
	 {
		 //return (int)((float)x*(float)MainData.Width_Size)+MainData.SCREEN_MOVE_X;		 
		 return x;
	 }
	 public static int getDecodeY(int y)
	 {
		 //return (int)((float)y*(float)MainData.Height_Size)+MainData.SCREEN_MOVE_Y;		 
		 return y;
	 }

	 public static int getDecodeXTrue(int x)
	 {
		 return (int)((float)x*(float)MainData.Width_Size_True)+MainData.SCREEN_MOVE_X;		 
	 }
	 public static int getDecodeYTrue(int y)
	 {
		 return (int)((float)y*(float)MainData.Height_Size_True)+MainData.SCREEN_MOVE_Y;		 
	 }

	 
	 //ゲーム終了選択
	 public static final int DIALOG_GAME_END = 1;
	 public static final int DIALOG_GOLD_INIT = 2;
	 public static final int DIALOG_GOLD_INIT2 = 3;
	 public static final int DIALOG_GOLD_INIT3 = 4;
	 public static final int DIALOG_RETRAY_TITLE = 5;
	 public static final int DIALOG_TIME_SET	 = 6;

	 public static boolean DialogStop = false;
	 
	 public static boolean getDialogStop()
	 {
		 return DialogStop;
	 }
	 
	 public static void setDialogStop(boolean flag)
	 {
		 DialogStop = false;
	 }
	 
	 //●ダイアログ発動
	 public static void showDialogOkNo
		(
			final int scene,
			final Activity activity,
			final MainData data,
			final String title,
			final String text,
			final String msg[]) 
		{
			 if(DialogStop)
			 {
				 return;
			 }
			 DialogStop = true;
			 new Thread(new Runnable() {
			    public void run() {
			      handler.post(new Runnable() {
			    	  public void run() 
			    {
			  		 
			  		data.setErrMsg("bbbbbbbb");

					 int  err = 0;
					 try
					 {
						err = 1;
					    AlertDialog.Builder ad=new AlertDialog.Builder(activity);
					    ad.setTitle(title);
					    ad.setMessage(text);
					    ad.setInverseBackgroundForced(false);
					    err = 2;
					    
					    ad.setPositiveButton(msg[0],
					    	new DialogInterface.OnClickListener()
						    {
						        public void onClick(DialogInterface dialog,int whichButton) {
						            activity.setResult(Activity.RESULT_OK);
						            DialogResultOk(scene,data,activity);
						            DialogStop = false;
						        }
						    }
					    );
					    err = 3;
					    ad.setNegativeButton(msg[1],
					    	new DialogInterface.OnClickListener() 
					    	{				
								//@Override
								public void onClick(DialogInterface dialog,int whichButton) {
						            activity.setResult(Activity.RESULT_OK);
						            DialogResultNon(scene,data,activity);
						            DialogStop = false;
						        }
							}
					    );
					    
					    ad.setOnCancelListener(new DialogInterface.OnCancelListener()
					    	{
						    	public void onCancel(DialogInterface dialog)
						    	{
						    		data.setErrMsg("ddddddd");
						    		DialogStop = false;
					    		}
				    		}
					    );
				    	
				    	// 閉じる
//				    	ad.setOnDismissListener(
//				    			new DialogInterface.OnDismissListener()
//				    		{
//						    	public void onDismiss(DialogInterface dialog){
//						    	}
//				    		}
//				    	);




				    	ad.setCancelable(false);

					    err = 4;
					    ad.create();
					    ad.show();

					    err = 5;
					    //ad.setIndeterminate(true);
					   
					    
					    ad.setOnKeyListener(new OnKeyListener() {
					    	//@Override
					    	public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
					    		if (keyCode == KeyEvent.KEYCODE_BACK) {
					    			dialog.dismiss();
					    			//閉じるときにしたい処理
					    			DialogStop = false;
					    			data.setErrMsg("cccccccccc");
					    			return true;
					    		}
					    		return false;
					    	}
					    });

					 }catch (Exception e) {
						// TODO: handle exception
						 showDebugDialog(activity, "err"+err);
					}
			    }
		      });
		    }
		  }).start();
		}    	
		
	 	//●ダイアログOK挙動
		public static void DialogResultOk(int scene, final MainData data,
				final Activity activity	) 
		{
			
			switch (scene) {
				case DIALOG_GAME_END:
					
					data.g_Title.DialogActionOk(DIALOG_GAME_END);
					
					break;
				
				case DIALOG_GOLD_INIT:
				{
					data.g_Title.DialogActionOk(DIALOG_GOLD_INIT);					
					break;
				}
				case DIALOG_GOLD_INIT2:
				{
					data.g_Title.DialogActionOk(DIALOG_GOLD_INIT2);					
					break;
				}
				case DIALOG_GOLD_INIT3:
				{
					data.g_Title.DialogActionOk(DIALOG_GOLD_INIT3);					
					break;
				}
				case DIALOG_RETRAY_TITLE:
				{
					data.g_Play.DialogActionOk(DIALOG_RETRAY_TITLE);					
					break;
				}
				case DIALOG_TIME_SET:
				{
					data.g_Play.DialogActionOk(DIALOG_TIME_SET);					
					break;
				}
					
				
				default:
					break;
			}
		}
		
	 	//●ダイアログNO挙動
		public static void DialogResultNon(int scene, final MainData data,
				final Activity activity	) 
		{
			
			switch (scene) {
				case DIALOG_RETRAY_TITLE:
				{
					data.g_Play.DialogActionNo(DIALOG_RETRAY_TITLE);					
					break;
				}
				default:
					break;
			}
		}

		public static void setTimeCheck(int num )
		{
			 MainData.g_debug_MSG[num] = ""+ ( System.currentTimeMillis() - MainData.nowTimeDebug );
		}

		//===========================================

		public static final String URL_SET_LANK = "http://gasu.m35.coreserver.jp/Barning/BirningLank.cgi";

		public static String cgi_NetWork(Activity act,
				String url , String name , String data)
		{
			String result = "";
			//Library.showToastDebug(act,"name"+name+"\ndata"+data ,3);
			
			
			int err = 0;
			try
		    {
				Library.showToastDebug(act,"url"+url ,1);
				err = 1;
		        HttpPost httpPost = new HttpPost(url);
		        err = 2;
		        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
		        err = 3;
		        // POST データの設定
		        //-------------------------------------------------------------
		        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		        // 共通接続処理用パラメータをヘッダに追加
		        nameValuePair.add(0, new BasicNameValuePair("name", name ));
		        nameValuePair.add(1, new BasicNameValuePair("tweet", data ));
		        err = 4;
		    	httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8" ));
		    	err =5;
		    	// Webメソッド実施
		        HttpResponse httpResponse = defaultHttpClient.execute( httpPost );
		        err = 6;
		        int status = httpResponse.getStatusLine().getStatusCode();
		       // Library.showToastDebug(act,"status"+status ,1);
		        // エラーステータスの場合
//		        if ( status != HttpStatus.SC_OK )
//		        {
//		            throw new Exception( "HttpStatus:" + String.valueOf(status) );
//		        }
		        err = 7;
		        // データ取得
		        result = EntityUtils.toString( httpResponse.getEntity());
		        err = 8;
		       // Library.showToastDebug(act,"result"+result ,1);
		        err = 9;
		    }catch (Exception e) {
				// TODO: handle exception
		        Library.showDebugDialog(act,"err"+err+"\n"+e.toString() );

			}
		    return result;
		}
		
		public static final int MAX_LANKING = 10;
		public class httpGetData 
		{
			public String[] name = new String[MAX_LANKING];
			public String[] scoa = new String[MAX_LANKING];
		}
		
		
		public static final String URL_GET_LANK = "http://gasu.m35.coreserver.jp/Barning/lank.txt";
		public static httpGetData http4data(Activity act, String path,String code)
	    {
	    	httpGetData httpdata = new Library(). new httpGetData();
	    	String line = "a" ;
	    	int err = 0;
	    	try {
	    		err = 1;
		    	// URLコネクションの生成
		    	URL url = new URL(path) ;
		    	HttpURLConnection urlCon = (HttpURLConnection)url.openConnection() ;

		    	// HTTPのメソッドをGETに
		    	urlCon.setRequestMethod("GET") ;
		    	err = 2;
		    	// 接続
		    	urlCon.connect() ;
		    	err = 3;
		    	// コンソールに出力

		    	BufferedReader reader = new BufferedReader(new InputStreamReader(urlCon.getInputStream(), code)) ;
		    	
		    	if(reader == null)
		    	{
		    		return null;
		    	}
		    	
		    	//String line ;
		    	int idx = 0;
		    	while((line = reader.readLine()) != null) {
		    		int number = 0;
		    		String st = "";
		    		for(int i = 0 ; i < line.length(); i++)
		    		{
			    		char c = line.charAt(i);
			    		if(c == ',')
			    		{
			    			if(number == 0)
			    			{
			    				httpdata.name[idx] = st;
			    				//Library.showToastDebug(act,"httpdata.title[idx]"+ httpdata.name[idx] ,1);
			    			}else
		    				if(number == 1)
			    			{
			    				httpdata.scoa[idx] = st;
			    				//Library.showToastDebug(act,"httpdata.pubDate[idx]"+ httpdata.scoa[idx] ,1);

			    			}
			    			
			    			number++;
			    			st = "";
			    			continue;
			    		}
			    		if(c == ';')
			    		{
			    			idx++;
			    			break;
			    		}
			    		if(st == "//")
			    		{
			    			break;
			    		}
			    		st += c;
		    		}
		    		
		    		
		    		
		    		
		    	}
		    	err = 4;
		    	reader.close() ;
	    	} catch(Exception e) {
	    		Library.showToastDebug(act,"GetmysarverData"+ err+"\n"+e.toString() ,1);
	    		return null;
	    	}
	    	return httpdata;
	    }
}