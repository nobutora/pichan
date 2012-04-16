using UnityEngine;
using System.Collections;

public class ATwitter : MonoBehaviour {
	
	private bool connected = false;
    private bool tweeted = false;
	
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	
	/// <summary>
	/// Tweet the specified text.
	/// </summary>
	/// <param name='text'>
	/// Text.
	/// </param>
	public void tweet( string text )
	{
#if UNITY_WEBPLAYER
		tweetForWebPlayer( text );
#else
		tweetForWebPlayer( text );
        ////StartCoroutine(Twitter.API.PostTweet(m_Tweet, CONSUMER_KEY, CONSUMER_SECRET, m_AccessTokenResponse,
         //      new Twitter.PostTweetCallback(this.OnPostTweet)));
#endif
	}
	
	/// <summary>
	/// WEBPLAYERの場合のツイート
	/// </summary>
	/// <param name='tweet'>
	/// Tweet.
/// </param>
	private void tweetForWebPlayer( string tweet )
	{
		//Application.ExternalCall ("connect");
	    //Application.ExternalCall ("post", "https://api.twitter.com/1/statuses/update.json?status=" + WWW.EscapeURL (tweet), gameObject.name, "Call");		
		Library.openWebSite("https://twitter.com/intent/tweet?text=" + WWW.EscapeURL (tweet));
		tweeted = true;
	}
}
