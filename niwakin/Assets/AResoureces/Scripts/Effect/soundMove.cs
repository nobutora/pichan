using UnityEngine;

using System.Collections;



public class soundMove : MonoBehaviour {

	public AudioClip Source;

	public int type;

	// Use this for initialization

	void Start () {

		StartCoroutine( PlaySound() );
	}

	IEnumerator PlaySound(){

		audio.PlayOneShot( this.Source );

		yield return new WaitForSeconds(this.Source.length);
		
		Destroy( gameObject );

	}

	void OnDestroy()
	{
		//Library.addSeNum(type, -1);
	}

	// Update is called once per frame

	void Update () {

	

	}

}

