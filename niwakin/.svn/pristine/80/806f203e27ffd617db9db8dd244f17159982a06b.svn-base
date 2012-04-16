using UnityEngine;
using System.Collections;

public class InrangeAutoKeeper : MonoBehaviour {
	
	public QuadUI UIMain;
	private Rect InScreenRect ;
	public int fOutRangeLength;
	public Vector4 InRangeRect;
	
	void Start()
	{
		InScreenRect = new Rect(0,0,UIMain.screenDimensions.x,UIMain.screenDimensions.y);
	}
	
	void Update () {
		InRangeRect = new Vector4((InScreenRect.xMin-fOutRangeLength) * transform.localScale.x,
								(InScreenRect.yMin-fOutRangeLength) * transform.localScale.y,
								(InScreenRect.xMax+fOutRangeLength) * transform.localScale.x,
								(InScreenRect.yMax+fOutRangeLength) * transform.localScale.y);
								
		foreach( Transform child in transform )
		{
			//! 領域チェック
			if( child.position.x < InRangeRect.x ||
				child.position.x > InRangeRect.z ||
				child.position.y < InRangeRect.y ||
				child.position.y > InRangeRect.w )
				{
				/*
					//! test用
					int debugLogType = 0;
					if( child.position.x < InRangeRect.x ) {
						debugLogType = 1;
					} else if ( child.position.x > InRangeRect.z ) { 
						debugLogType = 2;
					} else if ( child.position.y < InRangeRect.y ) {
						debugLogType = 3;
					} else if ( child.position.y > InRangeRect.w ) {
						debugLogType = 4;
					}
					//Debug.Log( "child.position.x > InRangeRect.z : " + child.position.x.ToString() + "," + InRangeRect.z.ToString() );
					
					//Debug.Log("destroy: type:" + debugLogType.ToString() + "name[" + child.gameObject.name + "] " + child.position.ToString() );
				*/
					//! 範囲を超えたら自動で削除
					Destroy( child.gameObject );
				}
		}
	
	}
	
}
