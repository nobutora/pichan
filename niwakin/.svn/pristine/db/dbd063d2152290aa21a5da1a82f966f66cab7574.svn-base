using UnityEngine;
using System.Collections;

public class bommove : ScrollBehaviour {
	
	
	
	
	
	// Use this for initialization
	void Start () {
	
		
	}
	
	// Update is called once per frame
	void Update () {
		
		//while( transform.GetChildCount() != 0 ){
		//	wait( 2.5f );
		//}
		
		if(transform.GetChildCount() == 0)
		{
			GameObject.Destroy( gameObject );
		}
		
		// scroll 
		Scroll();
	}
	
	private IEnumerator wait(float time)
	{
		yield return new WaitForSeconds(time);
	}
}
