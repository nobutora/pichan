using UnityEngine;
using System.Collections;

public class scoreDraw : MonoBehaviour {

	public static int WIDTH_NUM = 64;
	public static int HEIGHT_NUM = 64;
	public int showScore;

	public int type = 0;

	public static int TYPE_SCORE = 0;
	public static int TYPE_TITLE_SCORE = 1;
	public static int TYPE_RESULT_SCORE = 2;
	public static int TYPE_RESULT_DEATH = 3;


	private SpriteManager manager;
	private Sprite[] time;
	
	public void Start () {
		time = new Sprite[Library.getMAXScoreLen()];
		GameObject manegerObj = null;
		
		if( type == TYPE_SCORE)
		{
			manegerObj = GameObject.Find("Num_MNG") ;
			manegerObj.transform.localPosition =
			new Vector3(0 ,0  , -1);
		}else
		if( type == TYPE_TITLE_SCORE)
		{
			manegerObj = GameObject.Find("Num_MNG") ;
			manegerObj.transform.localPosition =
			new Vector3(0 ,0  , -1);
		}else
		if( type == TYPE_RESULT_SCORE)
		{
			manegerObj = GameObject.Find("Num_MNG_Result") ;
			manegerObj.transform.localPosition =
			new Vector3(0 ,0  , -2);
		}else
		if( type == TYPE_RESULT_DEATH)
		{
			manegerObj = GameObject.Find("Num_MNG_Result") ;
			manegerObj.transform.localPosition =
			new Vector3(0 ,0  , -2);
			//new Vector3(730, 400 , -2);				
			
		}




		manager =(SpriteManager)manegerObj.GetComponent( typeof(SpriteManager));

		for(int i = 0 ; i < Library.getMAXScoreLen(); i++)
		{
			time[i] = manager.AddSprite( gameObject, WIDTH_NUM-2, HEIGHT_NUM, 0, HEIGHT_NUM, WIDTH_NUM-2, HEIGHT_NUM, false );
			time[i].offset.x = -WIDTH_NUM * 1.5f * (i + 1);
			time[i].offset.y = 0 ;
			//if( type == TYPE_RESULT_DEATH)
			//{
			//	time[i].offset.x = -WIDTH_NUM * 1.5f * (i + 1)+1435;
			//	time[i].offset.y = 820 ;
			//}
			
			time[i].SetSizeXY( time[i].width * 1.5f, time[i].height * 1.5f);
		}

		updateNumbers( 1 );
	}
	void OnDestroy()
	{
		for( int i=0 ; i<time.Length ; i++ )
		{
			manager.RemoveSprite( time[i] );
		}
		manager = null;
	}
	
	public void Update () {
	
		
		if( GameManager.Instance.getScore() != showScore )
		{
			if(type == TYPE_RESULT_DEATH)
			{
				updateNumbers( GameManager.Instance.getDead() );
			}else
			if( type == TYPE_SCORE ||
				type == TYPE_RESULT_SCORE )
			{
				updateNumbers( GameManager.Instance.getScore() );
			}else
			if( type == TYPE_TITLE_SCORE)
			{
				updateNumbers( Library.getHightScore() );
			}
		}
	}
	
	public void updateNumbers(int newNum)
	{
		int fignum = Library.getMAXScoreLen();
		int tmp = newNum;
		
		if( tmp >= Library.getMAXScore() ) {
			tmp = Library.getMAXScore();
		}
		
		int count;
		for( count=0 ; count<fignum ; count++ )
		{
			time[count].hidden = false;
			
			int num;
			num = tmp%10;
			setNumber( manager, time[count], num );
			
			if( tmp < 10 ) {
				break;
			}
			tmp = tmp/10;
		}
		for( count++ ; count < fignum ; count++ )
		{
			time[count].hidden = true;	
		}
		
		showScore = newNum;
	}
	
	static public void setNumber (SpriteManager man, Sprite spr,int num) {
		if( num < 10 ) {
			int x,y;
			x = num;
			y = 0;
			
			spr.lowerLeftUV = man.PixelCoordToUVCoord(x*WIDTH_NUM, 0);
		}
	}
}
