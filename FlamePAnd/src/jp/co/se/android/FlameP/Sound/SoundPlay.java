
package jp.co.se.android.FlameP.Sound;

import java.io.File;
import java.net.URI;

import jp.co.se.android.FlameP.Main.*;
import jp.co.se.android.FlameP.activity.bgmSelect;
import jp.co.se.android.FlameP.lib.*;
import jp.co.se.android.FlameP.save.saveDataBase;

import android.R.raw;


import android.R;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;

import android.app.Activity;
import android.content.Context;



public class SoundPlay implements OnCompletionListener
{
	public final static int SOUND_BGM_TITLE	= 0;

	public final static int SOUND_BGM_TITLE_1	= 1;
	public final static int SOUND_BGM_TITLE_2	= 2;
	public final static int SOUND_BGM_TITLE_4	= 3;

	
	public final static int SOUND_SOUND_MAX	= 4;
	

	
	public final static int SOUND_SOUND_ALL_MAX	= 40;
	
	private int volume = 0;
	 

	 MediaPlayer m_soundNew[] = new MediaPlayer[SOUND_SOUND_ALL_MAX];
	 
	 
	 private int LoadBgmNum = 0;
	 private String UriList[];
	 
	 
	 private static int sound_IdList[]
	 = {
		 jp.co.se.android.FlameP.R.raw.bgm1,
		 jp.co.se.android.FlameP.R.raw.bgm2,
		 jp.co.se.android.FlameP.R.raw.bgm3,		 
		 jp.co.se.android.FlameP.R.raw.bgm4
	 };
	 public static String sound_Str_List[]
   	 = {
   		 "Sound1",
   		 "Sound2",
   		 "Sound3",
   		 "Sound4",

	 };
	 
	 public static String Load_Bgm_Name = "Load";
	 
 
	 public SoundPlay(Context context)
	 {
		 
	 }
	 public void dispose()
	 {
	  this.release();
	 }
	 private void init()
	 {
	 }

	 @SuppressWarnings({ "unused", "static-access" })
	public void LoadSound(Activity act, String url[])
	 {
		 int err = 0;
		 try
		 {
			 err = 1;
			 m_soundNew = new MediaPlayer[SOUND_SOUND_ALL_MAX];
			 for(int i= 0 ; i < SOUND_SOUND_MAX ; i++)
			 {
				 m_soundNew[i] = createStatus(act ,sound_IdList[i]);
			 }
			 err = 2;
			 
			 
			 LoadBgmNum = 0;
			 if(url == null)
			 {
				 return;
			 }
			 if(url.length == 0)
			 {
				 return;
			 }
			 err = 3;
			 UriList = new String[url.length];

			 err = 4;
			 for(int i= 0 ; i < url.length; i++)
			 {
				 if(url[i] == null)
				 {
					 break;
				 }
				 err = 5;
				 UriList[LoadBgmNum] = url[i];
				 Uri uri = Uri.parse(url[i]);
				 m_soundNew[SOUND_SOUND_MAX+LoadBgmNum] = createStatus( act ,uri);
				 
				 LoadBgmNum++;
				 err = 6;
			 }
		 }catch (Exception e) {
			// TODO: handle exception
			 Library.showToastDebug(act, "ERR LoadSound" + e.toString()+"\nerr"+err,3);
		}
	 }
	 
	 public MediaPlayer createStatus( Activity act ,int id)
	 {
		 MediaPlayer mi = MediaPlayer.create(act, id);
		 createStatus2( mi ,  act );
		 return mi;
	 }
	 public MediaPlayer createStatus(Activity act , Uri uri)
	 {
		 MediaPlayer mi = MediaPlayer.create(act, uri);
		 createStatus2( mi ,  act );
		 return mi;
	 }
	 public void createStatus2(MediaPlayer mi , Activity act )
	 {
		 mi.setAudioStreamType(AudioManager.STREAM_MUSIC);
		 mi.setOnCompletionListener(this);
		 mi.setLooping(true);
	 }
	 
	 @SuppressWarnings("static-access")
	public boolean LoadSoundAdd(Activity act,String url)
	 {		 
		 if(LoadBgmNum+SOUND_SOUND_MAX >= SOUND_SOUND_ALL_MAX)
    	 {
			 Library.showDialog(act, "“Ç‚Ýž‚ÝŽ¸”s", "‚±‚êˆÈã“Ç‚Ýž‚ß‚Ü‚¹‚ñB");
			 return false;
    	 }
		 if(LoadBgmNum >= 1)
    	 {
	    	for(int i = 0; i < LoadBgmNum; i++)
	    	{
	    		if(UriList[i].equals(url))
	    		{
	    			return false;
	    		}
	    	}
    	 }
		 
		 Library.showToastDebug(act, "LoadSoundAdd",1);
		 String[] damey = UriList;
	    	
		 LoadBgmNum++;	
		 UriList = new String[LoadBgmNum];

    	if(LoadBgmNum >= 2)
    	{
    		Library.showToastDebug(act, "2LoadSoundAdd",1);
	    	for(int i = 0; i < LoadBgmNum-1; i++)
	    	{
	    		UriList[i] = damey[i];
	    	}
    	}
    	Library.showToastDebug(act, "LoadBgmNum"+LoadBgmNum,1);
    	UriList[LoadBgmNum-1] = url;
    	Uri uri = Uri.parse(url);
    	m_soundNew[SOUND_SOUND_MAX+LoadBgmNum-1] =
    		createStatus( act ,uri);

    	Library.showToastDebug(act, "UriList[LoadBgmNum-1]"+UriList[0].toString(),1);

    	return true;
	 }
	 public boolean LoadSoundDel(Activity act,int idx)
	 {		 
		 
		 String[] damey = UriList;
	    	
		 LoadBgmNum--;	
		 UriList = new String[LoadBgmNum];

		 if(LoadBgmNum == 0)
		 {
			 return true;
		 }		 
    	 {
    		Library.showToastDebug(act, "2LoadSoundAdd",1);
    		int UriListIdx = 0;
	    	for(int i = 0; i < LoadBgmNum+1; i++)
	    	{
	    		if(idx == i)
	    		{
	    			continue;
	    		}
	    		UriList[UriListIdx] = damey[i];
	    		UriListIdx++;
	    	}
    	 }
		 if( Library.getNowBgm() == idx +SoundPlay.SOUND_SOUND_MAX)
		 {
			 return false;
		 }

    	return true;
	 }
	 
	 private void release()
	 {
		 for(int channelNo = 0 ; channelNo < SOUND_SOUND_ALL_MAX; channelNo++)
		 {
			 if(m_soundNew[channelNo] == null)
			 {
				 return;
			 }
			 try
			 {
				 this.m_soundNew[channelNo].stop();
				 this.m_soundNew[channelNo].release();
				 this.m_soundNew[channelNo] = null;
			 }
			 catch (Exception e) {
				// TODO: handle exception
				 Library.TraseMsg("Sound release err");
			}
			// m_soundNew[channelNo] = new MediaPlayer();
		 }
	 }
	 
	 public boolean PlayNew(
			 int channelNo , Context context , Activity act)
	 {
		 if(m_soundNew[channelNo] == null)
		 {
			 m_soundNew[channelNo] = new MediaPlayer();
			 return false;
		 }
		 int err = 0;
		 try
		 {
			 err = 1;
			 if(m_soundNew[channelNo].isPlaying() == false)
			 {
				 Library.showToastDebug(act, "PlayNew",2);
				 err = 2;
				 
				 m_soundNew[channelNo].start();
				 err = 8;				 
				 SetVolue(volume);
			 }
		 }catch (Exception e) {
			// TODO: handle exception
			 Library.showToastDebug(act, "PlayNew ERR "+e.toString()+"\n"+err,3);

		 }
		 
		 return true;
	 }
	 
	 
	 public boolean Stop()
	 {
		 for(int channelNo = 0 ; channelNo < SOUND_SOUND_ALL_MAX; channelNo++)
		 {
			 if(this.m_soundNew[channelNo] != null)
			 {
				 if(this.m_soundNew[channelNo].isPlaying())
				 {
					 this.m_soundNew[channelNo].pause();
					 //this.m_soundNew[channelNo].seekTo(0);
				 }
				 //this.m_soundNew[channelNo].stop();
			 }
		 }
		 return true;
		 //this.m_soundNew.release();
	 }
	 
	 public boolean SetVolue(int vol)
	 {
		 volume = vol;
		 float volSet = (float)volume * 0.01f;
		 
		 if(volSet >= 1.0f)
		 {
			 volSet = 1.0f;
		 }
		 for(int channelNo = 0 ; channelNo < SOUND_SOUND_ALL_MAX; channelNo++)
		 {
			 if(m_soundNew[channelNo] == null)
			 {
				 	break;
			 }
			 this.m_soundNew[channelNo].setVolume(volSet, volSet);		
		 }
		 return true;
	 }
	 
	 /**
	  *
	  */
	 public void BgmRePlay()
	 {
		 
		 //this.m_soundNew.setLooping( true );		
		 //this.m_soundNew.replay( );	
	 }
	public void onCompletion(MediaPlayer mp) {
		
	}
	
	public boolean isPlaying(int channelNo)
	{
		return false;
	}
	
	public int getLoadBgmNum()
	{
		return LoadBgmNum;
	}
	public String[] getUriList()
	{
		return UriList;
	}
	
	public int getvolume()
	{
		return volume;
	}
	
}