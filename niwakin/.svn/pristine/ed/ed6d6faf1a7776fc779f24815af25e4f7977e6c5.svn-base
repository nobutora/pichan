using UnityEngine;
using System.Collections;

public class ScreenSizeKeeper : MonoBehaviour {
	
	/// <summary>
	/// スクリーンサイズキーパー
	/// </summary>

	public int sizeType = 0;

	public const int SIZE_TITLE = 1;
	public const int SIZE_GAME = 0;

	private static ScreenSizeKeeper	mInstance;
	public static ScreenSizeKeeper Instance
	{
		get { return mInstance; }	
	}
	

	void OnGUI () 
	{
		string str = "aaa" ;
		
		//WebCamTexture("2D Camera");

		UnityEngine.Camera camera = Camera.main;
		
//		str = "size"+camera.orthographicSize + "Screen.width" +Screen.width
//		 + "Screen.height " +Screen.height;
		if(SystemInfo.operatingSystem.Contains("Android"))
		{
			str = "Android";
		}else
		if(SystemInfo.operatingSystem.Contains("iPhone"))
		{				
			str = "iPhone";
		}
		
		//GUI.Label( new Rect(
		//	100 ,
		//	200 ,
		//	1200,
		//	1200 ),str);
	}

	// Use this for initialization
	void Start () {
		
		

		if( mInstance == null )
		{
			mInstance = this;
		}
		else
		{
			Destroy( this );	
		}
		
		//スクリーンサイズに合わせてscaleを変更
		Vector2 vScreenSize = GameManager.ScreenSizeVirtual;

		float scaleX = Screen.width / vScreenSize.x;
		float scaleY = Screen.height / vScreenSize.y;
		
		float addX = 0;
		float addY = 0;
		if(SystemInfo.operatingSystem.Contains("iPhone"))
		{
			
		    scaleX *= 0.9f;
			scaleY *= 0.9f;
		 	addX = -130;
		 	addY = -60;
			if(sizeType == SIZE_TITLE)
			{
				addY -= 15;
				scaleY *= 1.1f;
			}else
			if(sizeType == SIZE_GAME)
			{	
				
			}
			//UnityEngine.Camera camera = Camera.main;
			//camera.orthographicSize = 315.0f;
		}else
		{
			//scaleX *= 1.45f;
			//scaleY *= 1.45f;
			//addX = -120;
			//addY = -40;
		}

		transform.localScale = new Vector3(scaleX,scaleY,1);
		Vector3 vPos = Vector3.zero;
		vPos.x = -Screen.width/2 + addX;
		vPos.y = -Screen.height/2+ addY;
		transform.localPosition = vPos;
	}
	
	/// <summary>
	/// ビューのスケール値を取得
	/// </summary>
	public Vector2 ViewScale
	{
		get { return new Vector2(transform.localScale.x, transform.localScale.y); }
	}
	
	/// <summary>
	/// ビューサイズを取得
	/// </summary>
	public Vector2 ViewSize
	{
		get { return Vector2.Scale(ViewScale,GameManager.ScreenSize); }
	}
	
}
