
package jp.co.se.android.FlameP.Sound;

import java.util.HashMap;

import jp.co.se.android.FlameP.Main.*;
import jp.co.se.android.FlameP.lib.*;

import android.R.raw;


import android.R;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.SoundPool;

import android.app.Activity;
import android.content.Context;



public class se_Play implements OnCompletionListener
{
	private static int se_IdList[]
  	 = {
  		 jp.co.se.android.FlameP.R.raw.se0,
  		 jp.co.se.android.FlameP.R.raw.se1,
  		 jp.co.se.android.FlameP.R.raw.se2,
  		 jp.co.se.android.FlameP.R.raw.se3,
  		 jp.co.se.android.FlameP.R.raw.se4,
  		 jp.co.se.android.FlameP.R.raw.se6,
  		 jp.co.se.android.FlameP.R.raw.se8,
  		 jp.co.se.android.FlameP.R.raw.bom19_a,
  		 jp.co.se.android.FlameP.R.raw.beep14,
  		 jp.co.se.android.FlameP.R.raw.equip,
  		 
  		 
  	 };

	public final static int SOUND_SE_CURSOL 	= 0;
	public final static int SOUND_SE_SELECT 	= 1;
	public final static int SOUND_SE_CANCEL 	= 2;
	public final static int SOUND_SE_GET		= 3;	
	public final static int SOUND_SE_GOLD		= 4;
	public final static int SOUND_SE_FLAME		= 5;
	public final static int SOUND_SE_BLES		= 6;
	public final static int SOUND_SE_BOMB		= 7;
	public final static int SOUND_SE_BUZAR		= 8;
	public final static int SOUND_SE_EQUIP		= 9;
	
	public static final int MAX_SE 				= 10;
	
	private static SoundPool soundPool; 

	private static HashMap<Integer, Integer> soundPoolMap; 

	private static int Volum = 100;

	public static final float MAX_VOLUME = 1.0f;
	
	//読み込み
	public void se_load(Context cont) {

		soundPool = new SoundPool(MAX_SE, AudioManager.STREAM_MUSIC, 100); 
	
		soundPoolMap = new HashMap<Integer, Integer>(); 
	
		for (int i=0; i<MAX_SE; i++) 
		{	
			soundPoolMap.put(i, 
				soundPool.load(cont , se_IdList[i], 1));
		}
	}
	
	private static boolean is_sePlayOk[] = new boolean[MAX_SE];

	public static void seOkInit()
	{
		for(int i  = 0 ; i < MAX_SE; i++)
		{
			is_sePlayOk[i] = false;
		}
	}
	
	public static void se_play(int no) {

		if(is_sePlayOk[no])
		{
			return;
		}		
		is_sePlayOk[no] = true;
		float volume = (float)((float)se_Play.Volum / 100.0f);
		
		if(volume >= 1.0f)
		{
			volume = 1.0f;
		}		
		
		// vol_l, vol_r,　pri, rate
		soundPool.play(soundPoolMap.get(no), volume, volume, 1, 0, 1f); 
	}
	
	public static int getVolume()
	{
		return se_Play.Volum;
	}
	
	public static void setVolume(int vol)
	{
		se_Play.Volum = vol;
	}
	
	public static void se_release(Context cont) {	
		for (int i=0; i<MAX_SE; i++) 
		{	
			soundPool.resume(i);
		}
	}
	
	public void onCompletion(MediaPlayer mp) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
}