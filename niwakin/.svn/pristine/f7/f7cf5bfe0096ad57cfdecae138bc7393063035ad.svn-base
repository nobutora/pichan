using UnityEngine;
using System.Collections;

public class Trapmissile : TrapBase {
	
	/// <summary>
	/// 設定レベル
	/// </summary>
	public int SettingLevel = 0;
	
	
	//! ミサイルラッシュ用
	private int launchCursor;
	private float launchTimer;
	
	// Use this for initialization
	void Start () {
		
		launchCursor = -1;
		launchTimer = 0;
		switch( SettingLevel )
		{
		case 0:
				setupMissileNormal();
				break;
		case 1:
				setupMissileNormal();
				break;
		case 2:	//!ミサイルラッシュ
				setupMissileRush();
				break;
		}
		//! トラップ名を設定
		//TrapNameManager.Instance.PlayAnimation( TrapName );
	}
	//!　ミサイル
	void setupMissileNormal()
	{	
		EnemyBase[] enemys = GetComponentsInChildren<EnemyBase>();
		if( enemys != null )
		{
			float basey = Random.Range( +32.0f, ScrollManager.Instance.StageSize.y-32.0f );
			for( int i = 0 ; i<enemys.Length ; i++ )
			{
				float y = basey + Random.Range( 35.0f, i * 94.0f );
				if(y >= ScrollManager.Instance.StageSize.y)
				{
					y -= ScrollManager.Instance.StageSize.y;
				}
				EnemyBase em = enemys[i];
				em.setPositionY( y );
				em.setPositionZ( Random.Range( -0.001f, -0.5f ) );
				em.playFlow();
			}
		}	
	}	
	//!　ミサイルラッシュ
	void setupMissileRush()
	{
		float y = 32;
		
		EnemyBase[] enemys = GetComponentsInChildren<EnemyBase>();
		if( enemys != null )
		{
			int blankIndex = Random.Range(0, enemys.Length);
			for( int i=0 ; i<enemys.Length ; i++ )
			{
				bool bSet = true;
				//! レベル２以上はひとつだけ何もしない
				if( SettingLevel >= 2 && i == blankIndex ) {
					y+=64*2;
				}
				if( bSet ) {
					EnemyBase em = enemys[i];
					em.setPositionY( y );	
					em.setPositionZ( Random.Range( -0.001f, -0.5f ) );
				}
				y+=43;
			}
		}
		
		launchCursor = 0;
	}
	
	
	// Update is called once per frame
	protected override void Move () {
		if( launchCursor >= 0 )
		{
			launchTimer += Time.deltaTime;
			
			EnemyBase[] enemys = GetComponentsInChildren<EnemyBase>();
			if( launchCursor >= enemys.Length ) {
				//! 全ミサイルを発射済み
				launchCursor = -1;
			} else {
				//! 動作	
				if ( launchTimer >= 0.15f )
				{
					//! 発射
					enemys[launchCursor].playFlow();
					launchTimer = 0;
					launchCursor++;
				}
			}
		}
	}
}
