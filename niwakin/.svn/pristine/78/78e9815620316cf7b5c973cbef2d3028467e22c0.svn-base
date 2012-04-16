using UnityEngine;
using System.Collections;

/// <summary>
/// にわとりのタイプを判定して必要なコンポーネントをロードする
/// </summary>
public class PlayerComponentController : MonoBehaviour {
	
	public PiyoBase.UNITTYPE	UnitType;
	
	// Use this for initialization
	void Start () {
		
		// ユニットタイプに合わせてコンポーネントのロード
		switch( UnitType )
		{
		case PiyoBase.UNITTYPE.PLAYER:
			gameObject.AddComponent( typeof(PlayerBase) );
			break;
		case PiyoBase.UNITTYPE.FRIEND:
			gameObject.AddComponent( typeof(FriendBase) );
			break;
		}
				
	}
	
	// Update is called once per frame
	void Update () {
	
	}
	//あたり
		/// <summary>
	/// 当たり判定処理
	/// </summary>
	/// <param name="col">
	/// A <see cref="Collider"/>
	/// </param>
	
	
	
}
