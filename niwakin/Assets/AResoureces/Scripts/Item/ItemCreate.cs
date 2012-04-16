using UnityEngine;
using System.Collections;

public class ItemCreate : MonoBehaviour {
	
	//ITEM_TIPE
	private const int ITEM_WING = 0;
	private const int ITEM_EGG  = 1;
	private const int ITEM_CAPSULE  = 2;

	private const int ITEM_MAX  = 3;

	private int[] ITEM_INTERVAL;
	private int[] ITEM_LV      ;

	private int[] ITEM_CREATE_X;
	private int[] ITEM_CREATE_Y;

	private int[] ITEM_CREATE_X_RAN;
	private int[] ITEM_CREATE_Y_RAN;
	
	private float[] itemWait = new float[ITEM_MAX];	
	private int[] itemInterval = new int[ITEM_MAX];	

	private float updateTimer = 0;
	// Use this for initialization
	void Start () {
		firstSetting();
	}
	
	// Update is called once per frame
	void Update () {
		
		if(GameManager.Instance.isStop())
		{
			return;
		}
		//if(updateTimer <= 100)
		//{
		//	updateTimer++;
		//	if(updateTimer == 100)
		///	{
		//		firstSetting();	
		//	}
		//}
	
		waitUp();

	}

	void firstSetting()
	{

		ITEM_INTERVAL = new int[ITEM_MAX];
		ITEM_LV       = new int[ITEM_MAX];
		ITEM_CREATE_X = new int[ITEM_MAX];
		ITEM_CREATE_Y = new int[ITEM_MAX];

		ITEM_CREATE_X_RAN = new int[ITEM_MAX];
		ITEM_CREATE_Y_RAN = new int[ITEM_MAX];
		
		//WING
		int type = ITEM_WING;	
		
		ITEM_INTERVAL[type] = 20;
		//ITEM_INTERVAL[type] = 250;
		
		
		ITEM_LV[type] = 0;
		ITEM_CREATE_X[type] = (int)GameManager.ScreenSize.x + 60;
		ITEM_CREATE_Y[type] = (int)ScrollManager.Instance.StageSize.y;
		ITEM_CREATE_X_RAN[type] = (int)ScrollManager.Instance.StageSize.x/10;
		ITEM_CREATE_Y_RAN[type] = (int)-ScrollManager.Instance.StageSize.y / 6;
			//(int)ScrollManager.Instance.StageSize.y - 100;
		
		// EGG
		type = ITEM_EGG;
		
		//ITEM_INTERVAL[type] = 90;
		ITEM_INTERVAL[type] = 8;
		
		
		ITEM_LV[type] = 0;
		ITEM_CREATE_X[type] = (int)GameManager.ScreenSize.x + 60;
		ITEM_CREATE_Y[type] = (int)ScrollManager.Instance.StageSize.y - (int)ScrollManager.Instance.StageSize.y/9;
		ITEM_CREATE_X_RAN[type] = (int)ScrollManager.Instance.StageSize.x/10;
		ITEM_CREATE_Y_RAN[type] = (int)-ScrollManager.Instance.StageSize.y + (int)ScrollManager.Instance.StageSize.y/9;
			//(int)ScrollManager.Instance.StageSize.y - 50;

		// 
		type = ITEM_CAPSULE;
		
		//ITEM_INTERVAL[type] = 90;
		ITEM_INTERVAL[type] = 8;
				
		ITEM_LV[type] = 0;
		ITEM_CREATE_X[type] = (int)GameManager.ScreenSize.x + 60;
		ITEM_CREATE_Y[type] = (int)ScrollManager.Instance.StageSize.y - (int)ScrollManager.Instance.StageSize.y/9;
		ITEM_CREATE_X_RAN[type] = (int)ScrollManager.Instance.StageSize.x/10;
		ITEM_CREATE_Y_RAN[type] = (int)-ScrollManager.Instance.StageSize.y + (int)ScrollManager.Instance.StageSize.y/9;

		for(int i = 0; i < ITEM_MAX; i++)
		{
			setInterval(i);
		}
	}

	void setInterval(int type)
	{
		int lv = GameManager.Instance.GameLevel;

		//int baseTime = ITEM_INTERVAL[type] /7 * (7 - lv);
		int baseTime = ITEM_INTERVAL[type] ;

		int ran = baseTime;

		//if(type == ITEM_WING)
		//{
		//	lv = GameManager.Instance.GameLevel;
		//	if(lv >= 5)
		//	{
		//		lv = 5;
		//	}
		//	ran += lv * 5;
		//}
		itemInterval[type] = baseTime + Random.Range(0 , ran );
	}
	
	void waitUp()
	{
		for(int type = 0; type < ITEM_MAX; type++)
		{
			//LV Chack
			if( GameManager.Instance.GameLevel >= ITEM_LV[type])
			{
				if( type != ITEM_EGG)
				{
					itemWait[type]+= Time.deltaTime;
					if( itemWait[type] > itemInterval[type] )
					{
						create(type);
						
					}
				}
			}
		}
	}

	void create(int type)
	{
		GameObject refObj = null;

		switch(type)
		{
			case ITEM_WING:
			{
				refObj = (GameObject)Resources.Load("Item/wing");
				break;
			}
			case ITEM_EGG:
			{
				refObj = (GameObject)Resources.Load("Item/egg");
				break;
			}
			case ITEM_CAPSULE:
			{
				refObj = (GameObject)Resources.Load("Item/capsule");
				break;
			}
		}

		GameObject obj = (GameObject)Instantiate( refObj );


		obj.transform.parent = transform;
		
		obj.transform.localPosition = Vector3.zero;
		//obj.transform.localScale = Vector3.one;
		//obj.transform.localRotation = Quaternion.identity;

		int ranX = ITEM_CREATE_X[type] + Random.Range(0 , ITEM_CREATE_X_RAN[type]);
		int ranY = ITEM_CREATE_Y[type] + Random.Range(0 , ITEM_CREATE_Y_RAN[type]);
		Vector3 position = new Vector3(
			ranX,
			ranY,
			1 );
		obj.transform.localPosition = ScrollManager.Instance.Translation(position);

		


		itemWait[type] = 0;
		setInterval( type) ;
		
		
		//! トラップ名を設定
		//TrapNameManager.Instance.PlayAnimation( "type"+type + "x" +ranX + "y" +ranY);
	}

}
