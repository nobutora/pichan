using UnityEngine;
using System.Collections;

public class ScreenManager : MonoBehaviour {
	
	/// <summary>
	/// インスタンス
	/// </summary>
	private static ScreenManager mInstance;
	public static ScreenManager Instance
	{
		get { return mInstance; }	
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
	}
	
	// Update is called once per frame
	void Update () {
	
	}
	
	/// <summary>
	/// 仮想ビュー領域を取得
	/// </summary>
	public Vector2 VirtualViewSize
	{
		get { return GameManager.ScreenSize; }	
	}
	
	/// <summary>
	/// ビューサイズを取得
	/// </summary>
	public static Vector2 ViewSize
	{
		get { return ScreenSizeKeeper.Instance.ViewSize; }	
	}
	/// <summary>
	/// ビューのスケールを取得
	/// </summary>
	public static Vector2 ViewScale
	{
		get { return ScreenSizeKeeper.Instance.ViewScale; }	
	}
	
	/// <summary>
	/// 仮想領域上のx座標をビュー座標上のx座標に変換
	/// </summary>
	/// <param name="x">
	/// A <see cref="System.Single"/>
	/// </param>
	/// <returns>
	/// A <see cref="System.Single"/>
	/// </returns>
	public static float VitToViewX( float x )
	{
		return x * ViewScale.x;
	}
	
	/// <summary>
	/// 仮想領域上のy座標をビュー座標上のy座標に変換
	/// </summary>
	/// <param name="y">
	/// A <see cref="System.Single"/>
	/// </param>
	/// <returns>
	/// A <see cref="System.Single"/>
	/// </returns>
	public static float VitToViewY( float y )
	{
		return y * ViewScale.y;
	}
	
	/// <summary>
	/// ビュー座標上のx座標を仮想領域上のx座標に変換
	/// </summary>
	/// <param name="x">
	/// A <see cref="System.Single"/>
	/// </param>
	/// <returns>
	/// A <see cref="System.Single"/>
	/// </returns>
	public static float ViewToVitX( float x )
	{
		return x * (1/ViewScale.x);
	}
	
	/// <summary>
	/// ビュー座標上のy座標を仮想領域上のy座標に変換
	/// </summary>
	/// <param name="y">
	/// A <see cref="System.Single"/>
	/// </param>
	/// <returns>
	/// A <see cref="System.Single"/>
	/// </returns>
	public static float ViewToVitY( float y )
	{
		return y * (1/ViewScale.y);
	}
}
