using UnityEngine;
using System.Collections;

public class ScrollManager : MonoBehaviour {
	
	/// <summary>
	/// インスタンス
	/// </summary>
	private static ScrollManager mInstance;
	public static ScrollManager Instance { 
		get { return mInstance; }	
	}
	
	public GameObject Transtion;	//スクロールを行うオブジェクト
	
	public float ScrollValue = 10.0f;		//! スクロール値
	
	/// <summary>
	/// スクロール値
	/// </summary>
	private Vector2 vScroll;
	
	public const float ScrollMaxY = 180.0f;//320.0f;
	
	/// <summary>
	/// スクロールを含めた表示範囲を取得
	/// </summary>
	public static Vector2 ScrollViewSize 
	{
		get {
			return new Vector2(GameManager.ScreenSize.x, GameManager.ScreenSize.y + ScrollMaxY);				
		}
	}
	
	/// <summary>
	/// Gets the scroll velocity scale.
	/// </summary>
	/// <value>
	/// The scroll velocity scale.
	/// </value>
	public float ScrollVelocityScale
	{
		get { return ScrollValue / 10.0f ; }	
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
		
		vScroll = Vector2.zero;
	}
	
	void Update()
	{

		if(GameManager.Instance.isStop())
		{
			return;
		}


		Vector3 vPos = Transtion.transform.localPosition;
		//vPos.x = vScroll.x;
		vPos.y = -vScroll.y;
		Transtion.transform.localPosition = vPos;
	}
	
	/// <summary>
	/// 画面の中心座標を返す
	/// </summary>
	public Vector2 ViewCenter
	{
		get {
			return new Vector2(GameManager.ScreenSize.x/2, (GameManager.ScreenSize.y)/2+vScroll.y);	
		}
	}
	/// <summary>
	/// 画面サイズを返す
	/// </summary>
	public Vector2 ViewSize
	{
		get {
			return GameManager.ScreenSize;
		}
	}
	
	/// <summary>
	/// 縦スクロール値を設定
	/// </summary>
	/// <param name="height">
	/// A <see cref="スクロール値"/>
	/// </param>
	public void setHeight( float height )
	{
		vScroll.y = height;
		
	}
	/// <summary>
	/// 縦スクロール値に加算
	/// </summary>
	/// <param name="height">
	/// A <see cref="System.Single"/>
	/// </param>
	public void addHeight( float height )
	{
		vScroll.y += height;
		vScroll.y = Mathf.Clamp(vScroll.y, 0.0f, ScrollMaxY );
	}
	
	/// <summary>
	/// スクロール後の座標を返す
	/// </summary>
	/// <param name="vPosition">
	/// A <see cref="Vector3"/>
	/// </param>
	/// <returns>
	/// A <see cref="Vector3"/>
	/// </returns>
	public Vector3 Translation( Vector3 vPosition )
	{
		return new Vector3(vPosition.x, vPosition.y-vScroll.x, vPosition.z );
	}
	
	/// <summary>
	/// スクリーンの天井を超えているかのチェック
	/// </summary>
	/// <returns>
	/// The over check.
	/// </returns>
	/// <param name='vPosition'>
	/// If set to <c>true</c> v position.
	/// </param>
	public bool CheckTopOver( Vector3 vPosition )
	{
		if( vPosition.y >=(GameManager.ScreenSize.y + ScrollMaxY - 50.0f) )
		{
			return true;
		}
		return false;
	}
	/// <summary>
	/// スクリーンの底を超えているかのチェック
	/// </summary>
	/// <returns>
	/// The bottom over.
	/// </returns>
	/// <param name='vPosition'>
	/// If set to <c>true</c> v position.
	/// </param>
	public bool CheckBottomOver( Vector3 vPosition )
	{
		if( vPosition.y <= 50.0f )
		{
			return true;
		}
		return false;
	}
	
	/// <summary>
	/// スクリーン領域内にクリッピングする
	/// </summary>
	/// <param name="vPosition">
	/// A <see cref="Vector3"/>
	/// </param>
	/// <returns>
	/// A <see cref="Vector3"/>
	/// </returns>
	public Vector3 ClipingPosition( Vector3 vPosition )
	{
		float y = Mathf.Clamp( vPosition.y, 50.0f, GameManager.ScreenSize.y + ScrollMaxY - 50.0f );
		return new Vector3( vPosition.x, y, vPosition.z );
	}
	
	/// <summary>
	/// ステージのサイズを取得
	/// </summary>
	/// <returns>
	/// A <see cref="Vector3"/>
	/// </returns>
	public Vector2 StageSize
	{
		get {
			return new Vector2( GameManager.ScreenSize.x, GameManager.ScreenSize.y+vScroll.y );
		}
	}
}
