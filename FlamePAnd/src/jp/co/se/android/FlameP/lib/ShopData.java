
package jp.co.se.android.FlameP.lib;

import jp.co.se.android.FlameP.scene.Title;



// 表示用のクラス
public class ShopData  {
	
	
	//■店舗などのステータス固定値===================================
	
	
	public static int NEXT_GOLD[] = {
		1500,
		10000,
		30000,
		65000,
	    100000,
		250000,
		500000,
		1200000};
//		2000,
//		10000,
//		30000,
//		50000,
//	    150000,
//		400000,
//		1050000,
//		40500000};

	public static int GUREAD_MAX = 4;
	
	
	
	
	
	//■炎のステータス固定値===================================
	
	//普通の炎
	public static final int FLAME_TYPE_NORMAL  = 1;
	//カスタムの炎
	public static final int FLAME_TYPE_CASTAM  = 2;
	//拡散の炎
	public static final int FLAME_TYPE_SMOAL   = 3;
	//スナイプの炎
	public static final int FLAME_TYPE_SNIPING = 4;
	//レーザーの炎
	public static final int FLAME_TYPE_LASER   = 5;
	//火炎放射
	public static final int FLAME_TYPE_FLAME   = 6;
	//オーソドックスチャージ
	public static final int FLAME_TYPE_CHARGE  = 7;
	//オーソドックスチャージ 店には乗らん
	public static final int FLAME_TYPE_CHARGE_2  = 8;
	//オーソドックスチャージ 店には乗らん
	public static final int FLAME_TYPE_CHARGE_3  = 9;

	//花火
	public static final int FLAME_TYPE_FLOWER	 = 10;

	//劣化
	public static final int FLAME_TYPE_LOW  = 11;

	//ホーミング
	public static final int FLAME_TYPE_HOMING = 12;
	//種類最大
	public static final int FLAME_TYPE_MAX     = 13;

	public static final int FLAME_HELP_LEN = 4;
	
	//０は捨てる	
	public static String FLAME_NAME[];
	public static String FLAME_HELP[][];
	//攻撃力
	public static int FLAME_POWERS[];
	//ディﾚｲ
	public static int FLAME_DRAY[];
	//値段
	public static int FLAME_PRICE[];
	//範囲
	public static  int FLAME_RARGE[];
	//ランク
	public static  int FLAME_RANK[];
	//絵
	public static  int FLAME_PICT[];
	//無料
	public static  boolean FLAME_FREE[];
	//ミサイルスルー
	public static  boolean FLAME_MISSILE_SLOO[];
	//時間スルー
	public static  boolean FLAME_TIME_SLOO[];
	//時間スルー 半分
	public static  boolean FLAME_TIME_SLOOHALF[];
	
	//自由にタッチ
	public static  boolean FLAME_TOUCHE_FREE[];
	//長尾しか脳
	public static  boolean FLAME_TOUCHE_LONG[];
	//長尾氏マックス
	public static  int FLAME_TOUCHE_LONG_MAX_TIME[];
	//長尾氏 ゲージの数
	public static  int FLAME_TOUCHE_LONG_MAX_GAGE[];
	//貫通
	public static  boolean FLAME_ENEMY_SLOO[];
	//店売り禁止
	public static  boolean FLAME_NO_SHOPING[];
	
	
	
	//店とか
	
	//初期
	public static final int BASE_TYPE_NO_SHOP  = 0;
	public static final int BASE_TYPE_LEVEL1  = 1;
	public static final int BASE_TYPE_LEVEL2  = 2;
	public static final int BASE_TYPE_LEVEL3  = 3;
	public static final int BASE_TYPE_MAX       = 4;
	
	//アップグレードならば
	public static  boolean BASE_UPGREADE[];
	public static  int BASE_PRICE[];
	public static  int BASE_IMAGE[];
	public static  int BASE_IMAGE_BACK[];
	public static String BASE_NAME[];
	public static String BASE_HELP[][];	
	//ランク
	public static  int BASE_RANK[];
	//ミサイル被害　％
	public static  int BASE_MISSILE_DAMEGE[];
	//取り単価アップ　％
	public static  int BASE_GOLD_ADD[];
	
	
	
	
	//2時間
	public static final long SHOP_LENTAL_TIME = 60 *  60 * 2;
	
	public static void DataSetting()
	{
		FLAME_NAME = new String[FLAME_TYPE_MAX];
		FLAME_HELP = new String[FLAME_TYPE_MAX][FLAME_HELP_LEN];
		FLAME_POWERS = new int[FLAME_TYPE_MAX];
		FLAME_DRAY = new int[FLAME_TYPE_MAX];
		FLAME_PRICE = new int[FLAME_TYPE_MAX];
		FLAME_RARGE = new int[FLAME_TYPE_MAX];
		FLAME_RANK = new int[FLAME_TYPE_MAX];
		FLAME_PICT = new int[FLAME_TYPE_MAX];

		
		
		FLAME_FREE = new boolean[FLAME_TYPE_MAX];
		FLAME_MISSILE_SLOO = new boolean[FLAME_TYPE_MAX];
		FLAME_TIME_SLOO = new boolean[FLAME_TYPE_MAX];
		FLAME_TIME_SLOOHALF = new boolean[FLAME_TYPE_MAX];
		FLAME_TOUCHE_FREE = new boolean[FLAME_TYPE_MAX]; 
		FLAME_TOUCHE_LONG = new boolean[FLAME_TYPE_MAX]; 
		FLAME_TOUCHE_LONG_MAX_TIME = new int[FLAME_TYPE_MAX]; 
		
		FLAME_TOUCHE_LONG_MAX_GAGE = new int[FLAME_TYPE_MAX]; 
		
		FLAME_ENEMY_SLOO = new boolean[FLAME_TYPE_MAX]; 
		FLAME_NO_SHOPING = new boolean[FLAME_TYPE_MAX]; 


		
		int type = 0;
		//ノーマルショット設定
		type = FLAME_TYPE_NORMAL;
		FLAME_NAME[type]	="ノーマルショット";
		
		FLAME_HELP[type][0]	="普通の炎弾を出す。";		
		FLAME_HELP[type][1]	="バランスがいいかもしれない。";		

		FLAME_POWERS[type]	= 2 ;
		FLAME_DRAY[type]	= 4 ;
		FLAME_RARGE[type]	= 50;
		FLAME_PRICE[type]	= 0;
		FLAME_RANK[type]	= 0;
		FLAME_FREE[type]    = true;
		//FLAME_PICT[type]  	= jp.co.se.android.FlameP.R.drawable.wepon_pict1;
		
		
		
		//劣化しょっと設定
		type = FLAME_TYPE_LOW;
		FLAME_NAME[type]	="劣化ショット";
		
		FLAME_HELP[type][0]	="普通の炎弾を出す。";		
		FLAME_HELP[type][1]	="バランスがいいかもしれない。";		

		FLAME_POWERS[type]	= 1;
		FLAME_DRAY[type]	= 6;
		FLAME_RARGE[type]	= 35;
		FLAME_PRICE[type]	= 0;
		FLAME_RANK[type]	= 0;
		//FLAME_PICT[type]  	= jp.co.se.android.FlameP.R.drawable.wepon_pict1;
		FLAME_NO_SHOPING[type] = true;

		
		//連射ショット設定
		type = FLAME_TYPE_CASTAM;
		FLAME_NAME[type]	= "スモールショット";
		FLAME_HELP[type][0]	= "連射と速度に優れる炎弾";		
		FLAME_HELP[type][1]	= "威力と効果範囲は少なめ。";	
		FLAME_HELP[type][2]	= "炎をはずしたときのﾍﾟﾅﾙﾃｨが少なめ。";	

		FLAME_POWERS[type]	= 1;
		FLAME_DRAY[type]	= 2;
		FLAME_RARGE[type]	= 25;
		FLAME_PRICE[type]	= 750;
		FLAME_RANK[type]	= 0;
		FLAME_TIME_SLOOHALF[type] = true;
		//FLAME_PICT[type]	= jp.co.se.android.FlameP.R.drawable.wepon_pict4;

		//拡散ショット設定
		type = FLAME_TYPE_SMOAL;
		FLAME_NAME[type]	="拡散ショット";

		FLAME_HELP[type][0]	="小さい炎弾を吹く。";	
		FLAME_HELP[type][1]	="それと同時に小さい火の粉も出す。";			
		FLAME_HELP[type][2]	="火の粉はミサイルにあたっても";	
		FLAME_HELP[type][3]	="爆発したりしない。";	

		FLAME_POWERS[type]	=1;
		FLAME_DRAY[type]	=8;
		FLAME_RARGE[type]	=5;
		FLAME_PRICE[type]	=18000;
		FLAME_RANK[type]	=2;
		FLAME_MISSILE_SLOO[type] = true;
		FLAME_TIME_SLOO[type] = true;
		//FLAME_PICT[type]  	= jp.co.se.android.FlameP.R.drawable.wepon_pict2;
		
		//ホーミング
		type = FLAME_TYPE_HOMING;
		FLAME_NAME[type]	="ホーミングショット";
		FLAME_HELP[type][0]	="発射後少ししたら一番近くの鳥に";		
		FLAME_HELP[type][1]	="向かっていく。";
		
		FLAME_POWERS[type]	=2;
		FLAME_DRAY[type]	=5;
		FLAME_RARGE[type]	=35;
		FLAME_PRICE[type]	=7500;
		FLAME_RANK[type]	=1;

		
		//好きな場所に炎を出せる
		type = FLAME_TYPE_SNIPING;
		FLAME_NAME[type]	="スナイパーショット";
		FLAME_HELP[type][0]	="タッチした場所に炎を出す。";		
		FLAME_HELP[type][1]	="速度と威力に優れる。";		

		
		FLAME_POWERS[type]	=3;
		FLAME_DRAY[type]	=9;
		FLAME_RARGE[type]	=60;
		FLAME_PRICE[type]	=50000;
		FLAME_RANK[type]	=3;
		FLAME_TOUCHE_FREE[type] = true; 
		//FLAME_PICT[type]  	= jp.co.se.android.FlameP.R.drawable.wepon_pict3;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//レーザー
		type = FLAME_TYPE_LASER;
		FLAME_NAME[type]	="アンドロイド";
		FLAME_HELP[type][0]	="時間経過で使用可能に。一定期間ピーちゃんが";		
		FLAME_HELP[type][1]	="アンドロイドになり、火炎レーザーが撃てるようになる。";	
		FLAME_HELP[type][2]	="普段は劣化ショット。";
		
		FLAME_POWERS[type]	=4;
		FLAME_DRAY[type]	=4;
		FLAME_RARGE[type]	=3;
		FLAME_PRICE[type]	=15000;
		FLAME_RANK[type]	=5;
		FLAME_TIME_SLOO[type] = true;
		
		//放射
		type = FLAME_TYPE_FLAME;
		FLAME_NAME[type]	="火炎放射";
		
		FLAME_HELP[type][0]	="放射型の炎が出せる。ボタンを長押しすると";		
		FLAME_HELP[type][1]	="火炎が大きくなっていく。";	
		FLAME_HELP[type][2]	="ボタンを離した瞬間炎は消える。";	
		FLAME_POWERS[type]	=1;
		FLAME_DRAY[type]	=10;
		FLAME_RARGE[type]	=1;
		FLAME_PRICE[type]	=300;
		FLAME_RANK[type]	=4;
		FLAME_TOUCHE_LONG[type] = true;
		FLAME_TIME_SLOO[type] = true;
		FLAME_TOUCHE_LONG_MAX_TIME[type] = 70;
		//FLAME_PICT[type]  	= jp.co.se.android.FlameP.R.drawable.wepon_pict5;
		
		//チャージ
		type = FLAME_TYPE_CHARGE;
		FLAME_NAME[type]	="チャージ";
		FLAME_HELP[type][0]	="ボタンを長押しで貫通弾が放てる";		
		FLAME_HELP[type][1]	="長くためるほど炎の大きさアップ。";	
		FLAME_HELP[type][2]	="最大チャージ";			
		FLAME_HELP[type][3]	="でミサイル破壊攻撃が可能。";			

		FLAME_POWERS[type]	=2;
		FLAME_DRAY[type]	=8;
		FLAME_RARGE[type]	=16;
		FLAME_PRICE[type]	=20;
		FLAME_RANK[type]	=7;
		FLAME_TOUCHE_LONG[type] = true;
		FLAME_TIME_SLOO[type] = true;
		FLAME_TOUCHE_LONG_MAX_TIME[type] = 10;
		FLAME_TOUCHE_LONG_MAX_GAGE[type] = 2;
		
		type = FLAME_TYPE_CHARGE_2;
		FLAME_POWERS[type]	=4;
		FLAME_RARGE[type]	=32;
		FLAME_NO_SHOPING[type] = true;
		FLAME_ENEMY_SLOO[type] = true;
		
		
		type = FLAME_TYPE_CHARGE_3;
		FLAME_POWERS[type]	=7;
		FLAME_RARGE[type]	=52;
		FLAME_NO_SHOPING[type] = true;
		FLAME_ENEMY_SLOO[type] = true;
		
		//花火
		type = FLAME_TYPE_FLOWER;
		FLAME_NAME[type]	="花火";
		
		FLAME_HELP[type][0]	="鳥にあたることで花火";		
		FLAME_HELP[type][1]	="が発生する。";	
		FLAME_HELP[type][2]	="花火はミサイルを無視する。";			
		FLAME_POWERS[type]	=2;
		FLAME_DRAY[type]	=8;
		FLAME_RARGE[type]	=16;
		FLAME_PRICE[type]	=20;
		FLAME_RANK[type]	=6;
		FLAME_TOUCHE_LONG[type] = true;
		FLAME_TIME_SLOO[type] = true;
		FLAME_NO_SHOPING[type] = true;
		FLAME_TOUCHE_LONG_MAX_TIME[type] = 10;
		FLAME_TOUCHE_LONG_MAX_GAGE[type] = 2;
		
		
		//ショップとか		
		DataSettingShop();
	}
	
	public static void DataSettingShop()
	{
		BASE_UPGREADE = new boolean[BASE_TYPE_MAX];		
		BASE_PRICE = new int[BASE_TYPE_MAX];
		
		BASE_IMAGE = new int[BASE_TYPE_MAX];
		BASE_IMAGE_BACK = new int[BASE_TYPE_MAX];
		
		BASE_NAME = new String[BASE_TYPE_MAX];
		BASE_HELP = new String[BASE_TYPE_MAX][FLAME_HELP_LEN];
		BASE_RANK = new int[BASE_TYPE_MAX];
		BASE_MISSILE_DAMEGE = new int[BASE_TYPE_MAX];
		BASE_GOLD_ADD	 	= new int[BASE_TYPE_MAX];
	
		
		int type = BASE_TYPE_NO_SHOP;
		
		BASE_NAME[type] = "お店なし";
		BASE_HELP[type][0] = "まだお店がない状態！";
		BASE_HELP[type][1] = "お金をためてお店を買おう！";
		BASE_HELP[type][2] = "ﾐｻｲﾙの被害が半減！";
		BASE_RANK[type] = 0;
		BASE_IMAGE[type] = 0;
		BASE_IMAGE_BACK[type] = 0;
		BASE_MISSILE_DAMEGE[type] = 0;

		type = BASE_TYPE_LEVEL1;
		
		BASE_NAME[type]    = "お祭り屋台";
		BASE_HELP[type][0] = "お祭りのときに沸いてくる屋台！";
		BASE_HELP[type][1] = "利率がちょっと増えるぞ！";
		BASE_HELP[type][2] = "ﾐｻｲﾙの被害は少なめ！";
		BASE_RANK[type] = 0;
		BASE_IMAGE[type] = 1;
		BASE_IMAGE_BACK[type] = 0;
		BASE_MISSILE_DAMEGE[type] = 15;
		BASE_GOLD_ADD[type] = 10;
		
		type = BASE_TYPE_LEVEL2;
		
		BASE_NAME[type]    = "木製ﾘﾔｶｰ屋台";
		BASE_HELP[type][0] = "ﾘﾔｶｰのおかげで機動力が増した！";
		BASE_HELP[type][1] = "利率が気持ち増えるぞ！";
		BASE_HELP[type][2] = "ﾐｻｲﾙの被害も少し増えるぞ！";
		BASE_RANK[type] = 0;
		BASE_IMAGE[type] = 2;
		BASE_IMAGE_BACK[type] = 1;
		BASE_MISSILE_DAMEGE[type] = 25;
		BASE_GOLD_ADD[type] = 20;

		type = BASE_TYPE_LEVEL3;
		BASE_NAME[type]    = "ﾄﾗｯｸ屋台";
		BASE_HELP[type][0] = "ぴーちゃんは免許を持っていた！";
		BASE_HELP[type][1] = "利率がなかなか増えるぞ！";
		BASE_HELP[type][2] = "ﾐｻｲﾙ被害もある程度増えるぞ！";
		BASE_RANK[type] = 0;
		BASE_IMAGE[type] = 3;
		BASE_IMAGE_BACK[type] = 1;
		BASE_MISSILE_DAMEGE[type] = 40;
		BASE_GOLD_ADD[type] = 35;

	
	
	}
	
	public static int getShopListPageMax(int list)
	{
		int max = 0;
		
		if(list == Title.SHOP_SWICHE_WEPON)
		{
			max = ShopData.FLAME_TYPE_MAX;
		}else
		{
			
		}
		int result = 0;
		for(int i = 0; i < max; i++)
		{
			if(FLAME_RANK[i] <= Library.nowLank())
			{
				result++;
			}
		}
		return result;
	}
	
	
	
}