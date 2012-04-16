using UnityEngine;
using System.Collections;

/// <summary>
/// トラップ共通処理
/// </summary>
public class TrapBase : MonoBehaviour {
	
	public string TrapName;		//! トラップ名

	// Use this for initialization
	void Start () {
		TextMesh tMesh = (TextMesh)GetComponentInChildren(typeof(TextMesh));
		tMesh.text = TrapName;
	
		//! トラップ名を設定
		TrapNameManager.Instance.PlayAnimation( TrapName );
	}
	
	// Update is called once per frame
	void Update () {
		 Move();
		
		Component[] enemys = (Component[])GetComponentsInChildren(typeof(EnemyBase));
		
		bool bAllEnd = true;
		foreach( Component em in enemys )
		{
			EnemyBase v = (EnemyBase)em;
			if( v.isEnd() == false )
			{
				bAllEnd = false;
				break;
			}
		}
		//! 全アニメーションが終了した
		if( bAllEnd )
		{
			DestroyObject( gameObject );	
		}
		
		MoveAfter();
	}
	
	public int getTrapNum()
	{
		int num = 0;
		Component[] enemys = (Component[])GetComponentsInChildren(typeof(EnemyBase));
		foreach( Component em in enemys )
		{
			EnemyBase v = (EnemyBase)em;
			if( v.isEnd() == false )
			{
				num++;
			}
		}
		return num;
	}
		
	/// <summary>
	///派生先で行う行動処理 
	/// </summary>
	protected virtual void Move()
	{
	}
	/// <summary>
	///派生先で行うポスト行動 
	/// </summary>
	protected virtual void MoveAfter()
	{
	}




	
}
