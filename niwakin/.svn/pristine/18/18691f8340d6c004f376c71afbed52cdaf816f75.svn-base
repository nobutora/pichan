using UnityEngine;
using System.Collections;

public class TrapNameManager : MonoBehaviour {
	/// <summary>
	/// インスタンス
	/// </summary>
	private static TrapNameManager	mInstance;
	public static TrapNameManager Instance
	{
		get {
			return mInstance;	
		}
	}
	/// <summary>
	/// アニメーション状態
	/// </summary>
	private enum Step
	{
		/// <summary>
		/// 待機
		/// </summary>
		Wait,
		/// <summary>
		/// 再生リクエスト
		/// </summary>
		PlayReq,
		/// <summary>
		/// 再生
		/// </summary>
		Play,
		/// <summary>
		/// 終了
		/// </summary>
		End,
	}
	
	//
	private Step AnimationStep;		//アニメーション状態
	private string TrapName;		//! トラップ名
	private string ShowText;		//! 表示テキスト
	
	public TextMesh TextMeshObject;	//! テキストメッシュ
	
	/// <summary>
	/// 表示テキストを取得
	/// </summary>
	public string Text 
	{
		get { return ShowText; }	
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
		
		AnimationStep = Step.Wait;
		TrapName = "";
		ShowText = "";
	}
	
	// Update is called once per frame
	void Update () {
		switch( AnimationStep )
		{
			//再生リクエスト
		case Step.PlayReq:
				ShowText = TrapName;
				AnimationStep = Step.Play;
				break;
			//再生
		case Step.Play:
				TextMeshObject.text = ShowText;
				AnimationStep = Step.End;
				break;
			//終了
		case Step.End:
				break;
			
		}
	}
	
	/// <summary>
	/// テキストアニメーションを開始
	/// </summary>
	/// <param name="trapname">
	/// A <see cref="S表示するトラップ名"/>
	/// </param>
	public void PlayAnimation( string trapname )
	{
		ShowText = "";
		TrapName = trapname;
		AnimationStep = Step.PlayReq;
	}
}
