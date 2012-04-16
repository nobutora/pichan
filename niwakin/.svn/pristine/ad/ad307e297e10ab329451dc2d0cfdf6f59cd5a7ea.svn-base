using UnityEngine;
using System.Collections;
using System.IO;

//なんでも屋
public class Library : MonoBehaviour {
	
	public const string saveStrScore = "score";
	public const string saveStrLv	 = "GameLv";
	
	public const int MAX_SAVE_LV = 3;
	
	public const bool DEBUG = true;



	public const int SE_FIRE = 0;	
	public const int SE_POINT_UP = 1;	
	public const int SE_ITEM = 2;	
	public const int SE_VOICE = 3;	


	public const int MAX_SE = 10;
	public static int[] SeNums = new int[MAX_SE];
	

	public static int getSeNum(int type)
	{
		return SeNums[type];
	}
	public static void addSeNum(int type , int num)
	{
		SeNums[type] += num;

		if(SeNums[type] <= 0)
		{
			SeNums[type] = 0;
		}
	}

	public static bool getDebug()
	{
		return DEBUG;
	}


	public static string UnTag
	{
		get { return "Untagged"; }	
	}	
	//タグ
	public static string FlameTag
	{
		get { return "Flame"; }	
	}
	public static string EnemyTag
	{
		get { return "Enemy"; }	
	}
	public static string PlayerTag
	{
		get { return "Player"; }	
	}
		/// <summary>
	/// はぐれたキャラクターのタグ名
	/// </summary>
	public static string LonelyTag {
		get { return "LonelyPlayer"; }	
	}
	
	/// <summary>
	/// 仲間キャラクターのタグ
	/// </summary>
	public static string FriendTag {
		get { return "FriendMember"; }	
	}
	
	public static int getMAXScore()
	{
		return 9999999;
	}
	public static int getMAXChikin()
	{
		return 50;
	}
	public static int getMAXScoreLen()
	{
		return 7;
	}
	public static int getMAXScoreEffectLen()
	{
		return 4;
	}
	public static int getMAXScoreEffect()
	{
		return 9999;
	}
	

	public static int getDefultWindowW()
	{
		return 800;
	}
	public static int getDefultWindowH()
	{
		return 480;
	}

	public static FileStream getFileStream()	
	{
#if false	// test
		Directory.CreateDirectory("save");
		FileStream    BinaryFile = new FileStream(saveStr, FileMode.Create, FileAccess.ReadWrite);
		return BinaryFile;
#else
		//PlayerPrefs pref;

		return null;	
#endif

	}
	
	
	public static void testsave()
	{
		FileStream    BinaryFile = new FileStream("test-cs.dat", FileMode.Create, FileAccess.ReadWrite);
        BinaryReader  Reader     = new BinaryReader(BinaryFile);
       // BinaryWriter  Writer     = new BinaryWriter(BinaryFile);
        
       // Writer.Write('1');           // char型  (1)
       // Writer.Write(123);           // int型   (4)
       // Writer.Write(456.789);       // double型(8)
       // Writer.Write("test string"); // 文字列型(12)

        char   ReadCharacter;
        int    ReadInteger;
        double ReadDouble;
        string ReadString;
        
        BinaryFile.Seek(0, SeekOrigin.Begin);
        ReadCharacter = Reader.ReadChar();
        ReadInteger   = Reader.ReadInt32();
        ReadDouble    = Reader.ReadDouble();
        ReadString    = Reader.ReadString();
   
        Debug.Log("Character: " + ReadCharacter);
        Debug.Log("Integer: "   + ReadInteger);
        Debug.Log("Double: "    + ReadDouble);
        Debug.Log("String: "    + ReadString);	
	}

	public static void gameSave(int score)
	{
		if( score > getHightScore() )
		{
			//BinaryFile = getFileStream();
	        //BinaryWriter  Writer     = new BinaryWriter(BinaryFile);	        
	        //Writer.Write(score);           // int型   (4)
			//Writer.Close();			
			PlayerPrefs.SetInt( saveStrScore , score );
			PlayerPrefs.Save();	
			nowScore = score;
		}
	}
	public static int getHightScore( )
	{
		if(nowScore == -1)
		{
			nowScore = PlayerPrefs.GetInt( "score" );
		}
		return nowScore;
	}

	public static void LvSave(int lv)
	{
		if( lv >= MAX_SAVE_LV)
		{
			lv = 0;
		}
		PlayerPrefs.SetInt( saveStrLv , lv );
		PlayerPrefs.Save();	
		nowLv = lv;
	}

	public static int nowLv = -1;
	public static int nowScore = -1;


	public static int getLvSave( )
	{
		if(nowLv == -1)
		{
			nowLv = PlayerPrefs.GetInt( saveStrLv );
		}
		return nowLv;
	}

	public static int getHightScore(FileStream BinaryFile )
	{
		//testsave();
		
		int data = PlayerPrefs.GetInt(saveStrScore);
		
		if(data >= 0)
		{
			return data;
		}

		if( BinaryFile == null )
		{
			return 0;
		}

        BinaryReader  Reader     = new BinaryReader(BinaryFile);

        int    ReadInteger = 0;
        
        BinaryFile.Seek(0, SeekOrigin.Begin);
		
		//if( Reader.BaseStream.Length == 0)
		//{
		//	return 0;
		//}else
		try
		{
			{
				ReadInteger   = Reader.ReadInt32();
			}
		}catch
		{
		}
   
		Reader.Close();
		
        Debug.Log("Integer: "   + ReadInteger);
		return ReadInteger;
	}
	
	public static  bool getToush()
	{
			
		bool ok = false;
				
		if(Input.GetMouseButton(0))
		{
			ok = true;
		}else
		if(Input.touchCount >= 1)
		{
			if(Input.GetTouch(0).phase == TouchPhase.Began)
			{
				ok = true;
			}
		}
		return ok;
	}
	

	public static void openWebSite(string url)
	{	
		
		if(SystemInfo.operatingSystem.Contains("Android"))
		{
			
			#if UNITY_ANDROID
				AndroidJavaClass cUnityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer"); 
				AndroidJavaObject jo = cUnityPlayer.GetStatic<AndroidJavaObject>("currentActivity"); 
		
				AndroidJavaClass uriclass = new AndroidJavaClass("android.net.Uri");
				AndroidJavaObject uriobj = uriclass.CallStatic<AndroidJavaObject>( "parse", url );
				AndroidJavaObject intentObject = new AndroidJavaObject("android.content.Intent", "android.intent.action.VIEW", uriobj );  
								
				jo.Call( "startActivity", intentObject );
			
				//! 解放
				intentObject.Dispose();
				uriobj.Dispose();
				uriclass.Dispose();
				jo.Dispose();
				cUnityPlayer.Dispose();

			#endif
		}else
		{
			Application.OpenURL(url);
		}
	}
}