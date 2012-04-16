using UnityEngine;
using System.Collections;

public class bomAnime : EffectBase {
	
	private int _uvTieX = 8;
    private int _uvTieY = 1;
    public int _fps = 10;

	private float alphe = 0.8f;    	
	
	public float Big_Pluse = 0.0f;	


	private float Big_PluseX = 0.0f;	
	private float Big_PluseY = 0.0f;	

	private float W ;	
	private float H ;	

	private float W_F ;	
	private float H_F ;	

	private float counter = 0;

	private float animeTime = 0.0f;	// Use this for initialization

	void Start () {

		W = 1.0f / (float)( _uvTieX ) * 0.1f;	
		H = 1 * 0.1f;

		W_F	= W ;/// GameManager.Instance.getScreenW();
		H_F	= H ;/// GameManager.Instance.getScreenH();
		

		firstEffect (_uvTieX , _uvTieY , alphe );
		SetScale( W , H  );


		Big_PluseX = Big_Pluse / 18.0f * (18.0f + (float)GameManager.Instance.GameLevel);/// GameManager.Instance.getScreenW();
		Big_PluseY = Big_Pluse / 18.0f * (18.0f + (float)GameManager.Instance.GameLevel);/// GameManager.Instance.getScreenH();

	}
	// Update is called once per frame
	void Update () {
	
		if(GameManager.Instance.isStop())
		{
			return;
		}

		counter += (Time.deltaTime * _fps);
	
		if( counter <= 10.0f + (float)GameManager.Instance.GameLevel / 17 )
		{

			W += W_F * Big_PluseX;
			H += H_F * Big_PluseY;
			SetScale( W , H );
		}
		else
		{
			this.tag = Library.UnTag;
			alphe -= 0.03f;
			SetAlphe(alphe);
			
			animeTime = MoveEffect ( _uvTieX ,  _uvTieY , _fps , animeTime );
			alphe -= 0.03f;
			SetAlphe(alphe);
			if(GetIdxCount() >= 5 )
			{
				DestroyObject( gameObject );
			}
		}
		ScrollMove();
	}

}

