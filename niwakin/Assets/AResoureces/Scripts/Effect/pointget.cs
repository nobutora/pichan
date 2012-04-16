using UnityEngine;
using System.Collections;

public class pointget : MonoBehaviour {

	public static int WIDTH_NUM = 32;
	public static int HEIGHT_NUM = 32;
	public int showScore;				//
	private SpriteManager manager;
	private Sprite[] time;

	private int counter = 0;
	private static int END_COUNT = 30;
	// Use this for initialization
	public void Start () {

		GameObject obj = GameObject.Find("Num_MNG_Smal") ;
		manager = (SpriteManager)obj.GetComponent( typeof(SpriteManager));
	
		time = new Sprite[Library.getMAXScoreEffectLen()];
	
		


		for(int i = 0 ; i < Library.getMAXScoreEffectLen(); i++)
		{
			int wMainus = 1;
			int hMainus = 1;

			time[i] = manager.AddSprite( gameObject,
				WIDTH_NUM - wMainus *2 , HEIGHT_NUM - hMainus * 2 - 1 ,
				wMainus, HEIGHT_NUM - hMainus + 1,
  				WIDTH_NUM - wMainus * 2 ,
				HEIGHT_NUM - hMainus* 2 - 1,
				false );
			time[i].offset.x = -(WIDTH_NUM + WIDTH_NUM / 2 + 10) / 2 * (i + 1) + 20;
			time[i].offset.x *= GameManager.Instance.getScreenW();
			
			time[i].offset.y = 30 ;
			//time[i].SetSizeXY( time[i].width/2 , time[i].height/2 );
		}

		updateNumbers( showScore );
	}
	public void clean()
	{	
		for( int i=0 ; i<time.Length ; i++ )
		{
			manager.RemoveSprite( time[i] );
			time[i] = null;
		}
        manager = null;
		DestroyObject( gameObject );
	}
	
	// Update is called once per frame
	public void Update () {

		if(GameManager.Instance.isStop())
		{
			return;
		}

		Vector3 position = new Vector3( 
			transform.position.x ,
			transform.position.y + 1,
			0 );
		transform.position = position;	
		
		updateNumbers( showScore );

		counter++;
		if(counter >= END_COUNT)
		{
			clean();
		}

	}
	
	public void updateNumbers(int newNum)
	{
		int fignum = Library.getMAXScoreEffectLen();	//
		int tmp = newNum;
		
		if( tmp >= Library.getMAXScoreEffect() ) {
			tmp = Library.getMAXScoreEffect();
		}
		
		int count;
		for( count=0 ; count<fignum ; count++ )
		{
			time[count].hidden = false;
			
			int num;
			num = tmp%10;
			setNumber( manager, time[count], num   , count);
			
			if( tmp < 10 ) {
				break;
			}
			tmp = tmp/10;
			
		}
		for( count++ ; count < fignum ; count++ )
		{
			time[count].hidden = false;	
			setNumber( manager, time[count], 10 , count , false );
		}
		
		showScore = newNum;
	}

	static public void setNumber (SpriteManager man, Sprite spr,int num , int count) {
		setNumber ( man, spr, num , count , true );
	}	
	static public void setNumber (SpriteManager man, Sprite spr,int num , int count , bool draw) {
		//if( num < 10 ) 
		{

			
			int x = 0,y = 0;

 			if(draw)
			{
				x = num;
				y = 0;
				spr.SetSizeXY( WIDTH_NUM * GameManager.Instance.getScreenW(),
					HEIGHT_NUM * GameManager.Instance.getScreenH() );

			//time[i].SetSizeXY( time[i].width/2* GameManager.Instance.getScreenW()
			 //, time[i].height/2 * GameManager.Instance.getScreenH());

			}else
			{			
				spr.SetSizeXY( 0.0f ,0.0f );
			}
			spr.lowerLeftUV = man.PixelCoordToUVCoord( x * WIDTH_NUM  , 0);
		}
	}
}
