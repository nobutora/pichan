package jp.co.se.android.FlameP.lib;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import jp.co.se.android.FlameP.Main.MainData;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;


//データベースヘルパーの定義(1)
public class DataSave extends SQLiteOpenHelper {
	
	private final static String DB_NAME ="DataBase.db";//DB名
	
	private final static String DB_TABLE="Data";   //テーブル名
	
	
	private final static int    DB_VERSION=2;      //バージョン

	public static final int DATA_IDX_NO 	= 0;
	public static final int DATA_IDX_WEPON_START_TIME= 1;	
	public static final int DATA_IDX_WEPON_TIME_LONG = 2;		
	public static final int DATA_IDX_MAX	= 3;
	
	public static final String[] SaveLineName =
	{"no","WeponTime","WeponTimeLong"};
	
	
	private final static String DB_BASE ="Base";   //テーブル名　購入物など

	public static final int BASE_IDX_NO 	= 0;
	public static final int BASE_IDX_BUY	= 1;	
	public static final int BASE_IDX_MAX	= 2;
	
	public static final String[] SaveBaseLineName =
	{"no","BuyOk"};	
	
	
	
	
    //データベースヘルパーのコンストラクタ(2)
    public DataSave(Context context , MainData Meisi) {
        super(context,DB_NAME,null,DB_VERSION);
        pMain = Meisi;
    }
    
	private static MainData pMain;
	
    
    //データベースの生成(3)
    @Override
    public void onCreate(SQLiteDatabase db) {
     
//    	for(int i = 0 ; i < DATA_IDX_MAX; i++)
//    	{
//    		LineName += SaveLineName[i] + " ";
//    	}
    	
    	//Library.showDebugDialog(pMeisi.getActivity(), "onCreate1");
    	String sql = "";   
        sql += "create table "+DB_TABLE+" (";   
        sql += " "+SaveLineName[0]+" text";
        sql += ","+SaveLineName[1]+" text";
        sql += ","+SaveLineName[2]+" text";
        sql += ")";   
        db.execSQL(sql);   
        
        sql = "";   
        sql += "create table "+DB_BASE+" (";   
        sql += " "+SaveBaseLineName[0]+" text";
        sql += ","+SaveBaseLineName[1]+" text";
        sql += ")";   
        db.execSQL(sql);   
    }

    //データベースのアップグレード(4)
    @Override
    public void onUpgrade(SQLiteDatabase db,
        int oldVersion,int newVersion) {
    	
    	db.execSQL(
	        "drop talbe if exists "+DB_TABLE);
    	
    	db.execSQL(
    	        "drop talbe if exists "+DB_BASE);    	
        onCreate(db);
    }
    
    
    public static int SAVE_WRITE_MAX_ERR = 1;
    public static int SAVE_WRITE_FAILED_ERR = 2;
    
    //書き込み
    public int writeDBAll(SQLiteDatabase db)
	throws Exception 
	{ 
    	int err = 0;
    	int idx = 0;
    	ContentValues values=new ContentValues();
    	try
    	{
    		for(int i = 0 ;
	    		i < ShopData.FLAME_TYPE_MAX;
	    		i++)
	    	{
    			for(int f = 0 ;
		    		f < DataSave.DATA_IDX_MAX;
		    		f++)
    			{
    				String val = AllData[i][f];
					values.put(SaveLineName[f], val );	
    			}
    			String msg = SaveLineName[DATA_IDX_NO]+" = " + i;
	        	//更新
    			int result = db.update(DB_TABLE,values, 
	        			msg , null);  
	        	if(result == -1)
	        	{
	        		db.insert(DB_TABLE,"",values);
	        	}
	    	}
    		values=new ContentValues();
    		for(int i = 0 ;
    		i < ShopData.BASE_TYPE_MAX;
    		i++)
	    	{
				for(int f = 0 ;
		    		f < DataSave.BASE_IDX_MAX;
		    		f++)
				{
					String val = AllDataBase[i][f];
					values.put(SaveBaseLineName[f], val );	
				}
				String msg = SaveBaseLineName[BASE_IDX_NO]+" = " + i;
	        	//更新
	        	int result = db.update(DB_BASE,values, 
	        			msg , null);   
	        	if(result == -1)
	        	{
	        		db.insert(DB_BASE,"",values);
	        	}
	    	}
    		

	        //更新
	        readAllDB( db  );
	        err = 7;
	        
	        

    	}catch (Exception e) {
			// TODO: handle exception
    		Library.showDebugDialog( pMain.getActivity() ,
    				"writeDB errMsg " + e.toString() );

    		Library.showDebugDialog( pMain.getActivity() ,
    				"writeDB err " + err + "idx" + idx);
    		return SAVE_WRITE_FAILED_ERR;
		}
    	return 0;	
	}
    
    public static int DATA_MAX = 50;
    
    //新規作成
    public int writeDBFirst(SQLiteDatabase db)
	throws Exception 
	{ 
    	int err = 0;
    	int idx = 0;
    	ContentValues values=new ContentValues();
    	ContentValues values2=new ContentValues();
    	try
    	{
    		int max = DATA_MAX;
    		
    		if(ShopData.FLAME_TYPE_MAX >= DATA_MAX)
    		{
    			max = ShopData.FLAME_TYPE_MAX;
    		}
    		for(int i = 0 ;
	    		i < max;
	    		i++)
	    	{
    			for(int f = 0 ;
		    		f < DataSave.DATA_IDX_MAX;
		    		f++)
    			{
    				if(f==0)
    				{
    					values.put(SaveLineName[f], ""+i );	    					
    				}else
    				{
    					values.put(SaveLineName[f], "0" );	
    				}
    			}
    			db.insert(DB_TABLE,"",values);
    			
    			
	    	}    		
	        
    		for(int i = 0 ;
    		i < max;
    		i++)
    		{
				for(int f = 0 ;
	    		f < DataSave.BASE_IDX_MAX;
	    		f++)
				{
					if(f==0)
					{
						values2.put(SaveBaseLineName[f], ""+i );	    					
					}else
					{
						values2.put(SaveBaseLineName[f], "0" );	
					}
				}
				db.insert(DB_BASE,"",values2);
    		}
			
    		
	        //更新
	        readAllDB( db  );
	        err = 7;
	        
	        

    	}catch (Exception e) {
			// TODO: handle exception
    		Library.showDebugDialog( pMain.getActivity() ,
    				"writeDB errMsg " + e.toString() );

    		Library.showDebugDialog( pMain.getActivity() ,
    				"writeDB err " + err + "idx" + idx);
    		return SAVE_WRITE_FAILED_ERR;
		}
    	return 0;	
	}
    
    
    
    

    //データベースからの読み込み(7)
    public String[] readDB(SQLiteDatabase db , int id )
    	throws Exception 	
    {
        Cursor c=db.query(DB_TABLE, SaveLineName ,
        		null,null,null,null,null);
        if (c.getCount()==0)
        	throw new Exception();
        c.moveToFirst();

        for(int i = 0 ; i < ShopData.FLAME_TYPE_MAX; i ++ )
        {
	        String no = c.getString(0);	        
	        if(id == Integer.parseInt(no))
	        {
	        	break;
	        }
	        c.moveToNext();
        }

        String str[] = new String[DATA_IDX_MAX];

        for(int i = 0 ; i < DATA_IDX_MAX; i++)
        {
        	str[i] = c.getString(i);
        }
        c.close();
        return str;
    }     
    
    private static String AllData[][] = new String[ShopData.FLAME_TYPE_MAX][DATA_IDX_MAX];        
    public int Save_DataNum = 0;
    
    private static String AllDataBase[][] = new String[ShopData.BASE_TYPE_MAX][BASE_IDX_MAX];        
    public int Save_Data_Base_Num = 0;

    
    //データベースからの読み込み(7)
    public void readAllDB(SQLiteDatabase db  )
    	throws Exception 	
    {
    	int err = 0;
    	try
    	{
	        Cursor c=db.query(DB_TABLE, SaveLineName ,
	        		null,null,null,null,null);	        
	        AllData = new String[ShopData.FLAME_TYPE_MAX][DATA_IDX_MAX];        
	        Save_DataNum = 0;
	        if (c.getCount()==0)
	        {
	        	writeDBFirst(db);
	        	return;
	        }
	        c.moveToFirst();
	
	        
	        do
	        {
		        for(int f = 0 ; f < DATA_IDX_MAX; f++)
		        {
		        	AllData[Save_DataNum][f] = c.getString(f);
		        	
	//	        	Library.showDialog( pMeisi.getActivity(),
	//	       	 			"effff",
	//	       	 		"str[Save_DataNum][f] "+str[Save_DataNum][f] +"f"+f);   
		        }
		        Save_DataNum++;
		        if(Save_DataNum >= ShopData.FLAME_TYPE_MAX)
		        {
		        	break;
		        }
	        }while(c.moveToNext());
	        c.close();
	        
	        
	        
	        //ショップなど
	        c=db.query(DB_BASE, SaveBaseLineName ,
	        		null,null,null,null,null);	        
	        AllDataBase = new String[ShopData.BASE_TYPE_MAX][BASE_IDX_MAX];        
	        Save_Data_Base_Num = 0;
	        if (c.getCount()==0)
	        {
	        	writeDBFirst(db);
	        	return;
	        }
	        c.moveToFirst();
	        
	        do
	        {
		        for(int f = 0 ; f < BASE_IDX_MAX; f++)
		        {
		        	AllDataBase[Save_Data_Base_Num][f] = c.getString(f);
		        }
		        Save_Data_Base_Num++;
		        if(Save_Data_Base_Num >= ShopData.BASE_TYPE_MAX)
		        {
		        	break;
		        }
	        }while(c.moveToNext());
	        c.close();
	        
	        
	        
	        
	        
	        
	        
    	}catch (Exception e) {
			// TODO: handle exception
    		
    		Library.showDialog( pMain.getActivity(),
    			"readPictAllDB",
    			"readAllDB err "+err+"Save_DataNum"+Save_DataNum); 
		}


        
       
    }     
    
    public static String[][] getDataBase()
    {
    	return AllData;
    }
    
    public static String[][] getDataBaseShop()
    {
    	return AllDataBase;
    }
    
    public String[] getDataBaseID(int num)
    {
    	int idx = -1;
    	try
    	{
	    	for(int i = 0; i < ShopData.FLAME_TYPE_MAX; i++)
	    	{
	    		if(Integer.parseInt(AllData[i][DATA_IDX_NO]) == num)
	    		{
	    			idx = i;
	    			//Library.showDebugDialog(pMeisi.getActivity() ,
	    			//		"getDataBaseID"+idx );
	    			break;
	    		}
	    	}
	    	if(idx == -1)
	    	{
	    		return null;
	    	}
    	}catch (Exception e) {
			// TODO: handle exception
		}
    	
    	return AllData[idx];
    }
    
    public String[] getDataBaseShopID(int num)
    {
    	int idx = -1;
    	try
    	{
	    	for(int i = 0; i < ShopData.BASE_TYPE_MAX; i++)
	    	{
	    		if(Integer.parseInt(AllDataBase[i][BASE_IDX_NO]) == num)
	    		{
	    			idx = i;
	    			//Library.showDebugDialog(pMeisi.getActivity() ,
	    			//		"getDataBaseID"+idx );
	    			break;
	    		}
	    	}
	    	if(idx == -1)
	    	{
	    		return null;
	    	}
    	}catch (Exception e) {
			// TODO: handle exception
		}
    	
    	return AllDataBase[idx];
    }
    
    public static void setDataBaseWeponTime(int ID,long TimeLong)
    {
    	AllData[ID][DATA_IDX_WEPON_START_TIME] = ""+System.currentTimeMillis()/1000;
    	AllData[ID][DATA_IDX_WEPON_TIME_LONG] = ""+TimeLong;
    }
    
    public static void setDataBaseShop(int ID)
    {
    	AllDataBase[ID][BASE_IDX_BUY] = "1";
    }
    
    public String getDataBaseWeponTime(int num)
    {
	    String[] database = getDataBaseID(num);
	
	    int Nuber = Integer.parseInt(database[DataSave.DATA_IDX_NO]) + 1;
	    
	    String msg = 
			"No "+(Nuber)+"　" +					
			database[DataSave.DATA_IDX_WEPON_START_TIME]+"　";
	    return msg;
    }
    
    public static boolean getWeponOk(int idx)
    {
    	String[][] SaveData = DataSave.getDataBase();
    	
		long Wepontime = Integer.parseInt( SaveData[idx][DataSave.DATA_IDX_WEPON_TIME_LONG]);

		if(Wepontime >= 1)
		{
			return true;
		}		
		return false;
    }
    
    public static long getWeponRemainderTime(int ID)
    {
    	String[][] SaveData = DataSave.getDataBase();
    	int idx = 0;
    	for(int i = 0; i < ShopData.FLAME_TYPE_MAX; i++)
    	{
    		int PutID = Integer.parseInt( SaveData[i][DataSave.DATA_IDX_NO]);
    		
    		if(PutID == ID)
    		{
    			idx = i;
    			break;
    		}
    	}      
    	//
		 long WeponStarttime = Integer.parseInt( SaveData[idx][DataSave.DATA_IDX_WEPON_START_TIME]);
		 //時間数
		 long Wepontime = Integer.parseInt( SaveData[idx][DataSave.DATA_IDX_WEPON_TIME_LONG]);
		 //現在時間
		 long nowTime = System.currentTimeMillis()/1000;
		 //残り時間
		 long WeponRemainderTime = WeponStarttime - nowTime + Wepontime;

		 return WeponRemainderTime;
		 
    }
    
    public int getDataNum()
    {
    	return Save_DataNum;
    }
    
    public static void DataDelete(SQLiteDatabase db)
    {
    	for(int i = 0; i < ShopData.FLAME_TYPE_MAX; i ++)
    	{
    		for(int f = 1; f < DATA_IDX_MAX; f ++)
        	{
    			AllData[i][f] = "0"; 
        	}
    	}
    	for(int i = 0; i < ShopData.BASE_TYPE_MAX; i ++)
    	{
    		for(int f = 1; f < BASE_IDX_MAX; f ++)
        	{
    			AllDataBase[i][f] = "0"; 
        	}
    	}
    	
    }
    
}


