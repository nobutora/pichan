package jp.co.se.android.FlameP.save;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.util.HashMap;

import jp.co.se.android.FlameP.Sound.SoundPlay;
import jp.co.se.android.FlameP.lib.Library;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.Toast;



public class saveDataBase implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String PACKAGE_NAME ;
	
	public static void setPackageName (Activity act)
	{
		PACKAGE_NAME  = act.getPackageName();
	}
	
	
	
	
	// アクセストークンを保存するファイル名を生成する
	private static File createFileName(String name , Activity act) {
		
		String s = "/data/data/" + saveDataBase.PACKAGE_NAME + "/"+name+".txt";;
		return new File(s);
	}
	
	
	
	
	
	//★BGMリストの保存
	private static String SAVE_BGM = "Save_Bgm";

	
	    
	@SuppressWarnings("unused")
	private class bgmList implements Serializable {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * 
		 */
		
		
		private int size = 0;
		//音楽URL
	    private String[] mUrl ;
		//ランキング名前
	    private String[] mLName ;
		//ランキングスコア
	    private String[] mLScore ;
	    

	    @SuppressWarnings("unused")
		public bgmList(Activity act ,String url[],String LName[],String LScore[]) {
	    	size = 0;
	    	mUrl = new String[2];
	    	try
	    	{
		    	int idx = 0;
		    	if(url != null)
		    	{
					this.size = url.length;
					Library.showToastDebug(act, "this.size"+this.size,1);			
				    mUrl  = new String[this.size];
		    	}
			    mLName = new String[Library.MAX_LANKING];
			    mLScore = new String[Library.MAX_LANKING];

		    	for(int i = 0; i < this.size; i++)
		    	{
		    		if(url[i] == null)
		    		{
		    			break;
		    		}
		    		mUrl[i] = url[i];
					Library.showToastDebug(act, "save1  "+i,1);			
		    	}
		    	
		    	if(LName != null)
		    	{
			    	for(int i = 0; i < Library.MAX_LANKING ; i++)
			    	{
			    		mLName[i] = LName[i];
			    		mLScore[i]= LScore[i];
			    	}
		    	}
		    	
	    	}catch (Exception e) {
				// TODO: handle exception
	    		Library.showToastDebug(act, "bgmListERR  "+e.toString(),1);	
			}
	    }
	    
	   
	    @SuppressWarnings("unused")
		public String[] getUrl(Activity act) {
	    	
	        
	        return mUrl;
	    }
	    
	    public String[] getLName(Activity act) {
	    	
	        
	        return mLName;
	    }
	    
	    public String[] getLScore(Activity act) {
	    	
	        
	        return mLScore;
	    }
	    
	    public int getSize() {
	    	
	        
	        return size;
	    }
	}
	// BGM　URLを保存する
	public static void storeBgm( final Activity act) {
		

		ObjectOutputStream os = null;
		Library.showToastDebug(act, "storeBgm",1);			
		int err = 0;
		try {
			bgmList data = new saveDataBase().new bgmList(act,
					Library.getUriList() ,
					Library.GetLankName(),
					Library.GetLankScore());			
			os = new ObjectOutputStream(new FileOutputStream(createFileName(SAVE_BGM,act)));
			err = 1;
			
			os.writeObject(data);
			
			os.flush();
			err = 2;			
			os.close();
			err = 3;
		} catch (IOException e) {
			e.printStackTrace();
			Library.showToastDebug(act, "storeBgm ERR "+e.toString()+"\n"+err,1);			
		} finally {
			if (os != null) {
				try { os.close(); } catch (IOException e) { e.printStackTrace(); }
			}
		}
		
		
	}
	
	
	//アイコン画像リストの保存	
	// アイコンリストをファイルから読み込む
	@SuppressWarnings({ "unchecked", "unused" })
	public static String[] loadBgmList(Activity act) {

		Library.showToastDebug(act, "loadBgmList",1);
		
		ObjectInputStream is = null;
		// FileOutputStream fos = openFileOutput("SaveData.dat", MODE_PRIVATE);     ObjectOutputStream oos = new ObjectOutputStream(fos);     oos.writeObject(data);     oos.close(); 
		//FileChannel.MapMode.READ_WRITE
		int err = 0;
		
		try {
			err = 1;
			is = new ObjectInputStream(new FileInputStream(createFileName(SAVE_BGM,act)));
			if(is == null)
			{
				return null; 
			}
			err = 2;
			err = 3;
			bgmList BgmListData = (bgmList) is.readObject();
			if(BgmListData == null)
			{
				return null; 
			}
			err = 4;
			
			err = 6;
			is.close();
			
			;
			Library.showToastDebug(act, "BgmListData.getSize()"+
					BgmListData.getSize(),1);
			
			if(BgmListData.getUrl(act) != null)
			{
				Library.LoadSound(act,
					BgmListData.getUrl(act));
			}
			
			if(BgmListData.getLName(act) != null)
			{
				Library.setLankName(BgmListData.getLName(act));
			}
			if(BgmListData.getLScore(act) != null)
			{
				Library.setLankScore(BgmListData.getLScore(act));
			}
			err = 7;
			return BgmListData.mUrl;

		} catch (IOException e) {
			Library.LoadSound(act,
				null);
			return null; //ファイルが読めない（存在しない）場合はnullを返す

		} catch (Exception e) {
			Library.LoadSound(act,
					null);
			return null;

		} finally {
			if (is != null) {
				try { is.close(); } catch (IOException e) { e.printStackTrace(); }
			}
		}
		
	}
	
	
	
	
	
	
}