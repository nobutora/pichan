
package jp.co.se.android.FlameP.draw;


//import Exception;

import android.content.res.Resources;
import android.graphics.*;

import jp.co.se.android.FlameP.draw.Graphics;
import jp.co.se.android.FlameP.Main.*;
import jp.co.se.android.FlameP.lib.*;
import jp.co.se.android.FlameP.data.*;
import jp.co.se.android.FlameP.scene.*;

import jp.co.se.android.FlameP.R;

/**
 * MainCanvas
 * 
 */
public class UI
{
	//ステージ
	public static Bitmap StageImage;
	//ステージ2
	public static Bitmap StageImage2;

	//大本背景
	public static Bitmap BackBase;
	
	//アイコン
	public static Bitmap IconImage;
	public static Bitmap IconImageTurn;
	
	//敵
	public static Bitmap EnemyImage;
	public static Bitmap EnemyImageTurn;
	

	//ピーちゃん
	public static Bitmap PlayerImage;
	public static Bitmap PlayerImageTurn;
	
	//エフェクト
	public static Bitmap EffectImage;

	//店舗
	public static Bitmap ShopImage;
	
	
	//炎
	//public static Bitmap Flame[];
	

	//タイトル店描画
	public static Bitmap[] ShopTitleImage;
	//タイトル店背景
	public static Bitmap[] ShopTitleImageBack;
	//タイトル店背景
	public static Bitmap[] ShopTitleImageBack_Part;

	public static final int SHOP_BACK_RUN = 1;
	
	
	public static Bitmap   ShopTitleImageBack_Part_Angle;
	
	//数字
	public static Bitmap NumberPict;
	
	//アイコンスイッチ
	public static Bitmap Icon_Swiche;
	
	
	//タイトル
	public static Bitmap TitleGrahe;
	
	public static final int FLAME_PICT_NUM = 2;
	
	
	public UI()
	{
		//init();
	}
	public void dispose()
	{
		this.release();
	}
	 
	 
	 public static void init(Graphics g , Resources r)
	 {

		 AllReadData(g,r);
		 g.setFontSize( 16 );
		 animeInit();
		 InitSwichtPoint();
		 
	 }
	 
	 
	 
	 public static void release()
	 {
		 if(StageImage != null)
		 {
			 StageImage.recycle();
			 StageImage = null;
		 }	
		 
		 
		 //GMO
		 if(StageImage2 != null)
		 {
			 StageImage2.recycle();
			 StageImage2 = null;
		 }		
		 
		 if(BackBase != null)
		 {
			 BackBase.recycle();
			 BackBase = null;
		 }		
		 

		 
		 if(EnemyImage != null)
		 {
			 EnemyImage.recycle();
			 EnemyImage = null;
		 }

		 if(EnemyImageTurn != null)
		 {
			 EnemyImageTurn.recycle();
			 EnemyImageTurn = null;
		 }

		 if(PlayerImage != null)
		 {
			 PlayerImage.recycle();
			 PlayerImage = null;
		 }
		 if(PlayerImageTurn != null)
		 {
			 PlayerImageTurn.recycle();
			 PlayerImageTurn = null;
		 }

		 if(EffectImage != null)
		 {
			 EffectImage.recycle();
			 EffectImage = null;
		 }

		 if(ShopImage != null)
		 {
			 ShopImage.recycle();
			 ShopImage = null;
		 }
		 
//		 if(Flame != null)
//		 {
//			 for(int i = 0; i < FLAME_PICT_NUM; i++)
//			 {
//				 Flame[i].recycle();
//				 Flame[i] = null;
//			 }
//		 }
			

		 if(IconImage != null)
		 {
			 IconImage.recycle();
			 IconImage = null;
		 }

		 if(IconImageTurn != null)
		 {
			 IconImageTurn.recycle();
			 IconImageTurn = null;
		 }
		 
		 if(ShopTitleImage != null)
		 {
			 for(int i = 0 ; i < ShopTitleImage.length ; i++)
			 {
				 if(ShopTitleImage[i] != null)
				 {
					 ShopTitleImage[i].recycle();
					 ShopTitleImage[i] = null;
				 }
			 }
		 }
		 
		 if(ShopTitleImageBack != null)
		 {
			 for(int i = 0 ; i < shopImageBack.length ; i++)
			 {
				 if(ShopTitleImageBack[i] != null)
				 {
					 ShopTitleImageBack[i].recycle();
					 ShopTitleImageBack[i] = null;
				 }
			 }
		 }
		 if(ShopTitleImageBack_Part != null)
		 {
			 for(int i = 0 ; i < ShopTitleImageBack_Part.length ; i++)
			 {
				 if(ShopTitleImageBack_Part[i] != null)
				 {
					 ShopTitleImageBack_Part[i].recycle();
					 ShopTitleImageBack_Part[i] = null;
				 }
			 }
		 }
		 
		 if(ShopTitleImageBack_Part_Angle != null)
		 {
			 ShopTitleImageBack_Part_Angle.recycle();
			 ShopTitleImageBack_Part_Angle = null;
		 }
		 
		 if(NumberPict != null)
		 {
			 NumberPict.recycle();
			 NumberPict = null;
		 }
		 
		 if(Icon_Swiche != null)
		 {
			 Icon_Swiche.recycle();
			 Icon_Swiche = null;
		 }
		 
		 if(TitleGrahe != null)
		 {
			 TitleGrahe.recycle();
			 TitleGrahe = null;			 
		 }
		 
		 
	 }
	 
	 
	 
	 
	 private static void AllReadData(Graphics g , Resources r)
	 {
		// Library.TraseMsg("AllReadData aa");

		 int err = 0;
		 try
		 {
			 int id = 0;
			 //
			 Library.TraseMsg("AllReadData1");
			 err = 1;

			 err = 8;
			 Library.TraseMsg("AllReadData7");

			 if(BackBase == null)
			 {
				 id = jp.co.se.android.FlameP.R.drawable.haikei2;
				 BackBase = g.createBitMap( r , id , 0 );
	
				 if(BackBase == null)
				 {
					 Library.TraseMsg("ERR BackBase");
				 }
			 }	
			 
			 if(NumberPict == null)
			 {
				 id = jp.co.se.android.FlameP.R.drawable.number;
				 NumberPict = g.createBitMap( r , id  , 0);
				 if(NumberPict == null)
				 {
					 Library.TraseMsg("ERR NumberPict");
				 }
			 }
			 err = 9;
			 Library.TraseMsg("AllReadData8");

			 if(Icon_Swiche == null)
			 {
				 id = jp.co.se.android.FlameP.R.drawable.item_icon;
				 Icon_Swiche = g.createBitMap( r , id , 0 );
				 if(Icon_Swiche == null)
				 {
					 Library.TraseMsg("ERR NumberPict");
				 }
			 }
			 
			 if(TitleGrahe == null)
			 {
				 id = jp.co.se.android.FlameP.R.drawable.title;
				 TitleGrahe = g.createBitMap( r , id,0 );
				 if(TitleGrahe == null)
				 {
					 Library.TraseMsg("ERR NumberPict");
				 }
			 }
			 
			 
			 err = 10;
			 Library.TraseMsg("AllReadData9");


			 
			 err = 11;

			 //アイコン
			 if(IconImage == null)
			 {		
				 
				 id = jp.co.se.android.FlameP.R.drawable.ui;
				 IconImage = g.createBitMap( r , id , 0 );
	
				 IconImageTurn = g.setFlipMode( IconImage , Graphics.FLIP_HORIZONTAL );			 
				 
				 if(IconImage == null)
				 {
					 Library.TraseMsg("ERR IconImage");
				 }
			 }	 
			 Library.TraseMsg("AllReadData10");

			 
			 
			 
		 }catch (Exception e) {
			// TODO: handle exception
			 int a = 0;
		}
		 
		 
		 //ReadShopTitleData( g , r);
	 }
	 
	 private static final int shopImageList[] = {R.drawable.shop_image1,R.drawable.shop_image2,R.drawable.shop_image3,R.drawable.shop_image4};
	 private static final int shopImageBack[] = {R.drawable.shop_back1,R.drawable.shop_back2};
	 private static final int shopImageBack_Part[] = {R.drawable.shop_back_part1,R.drawable.shop_back_part2};
	 
	 public static void ReadShopTitleData(Graphics g , Resources r)
	 {
		 
		 if(TitleGrahe != null)
		 {
			 TitleGrahe.recycle();
			 TitleGrahe = null;			 
		 }
		 
		 int err = 0;
		 Library.TraseMsg("AllReadData11");

		 try
		 {
			 int id = 0;
			 
			 if(StageImage == null)
			 {
				 id = jp.co.se.android.FlameP.R.drawable.back;			 
				 StageImage = g.createBitMap( r , id , 0);
				 
				 if(StageImage == null)
				 {
					 Library.TraseMsg("ERR StageImage");
				 }
			 }	
			 Library.TraseMsg("AllReadData2");

			 err = 2;
			 
			 //GMO
			 if(StageImage2 == null)
			 {
				 if(MainData.DEBUG_MODE_EMU)
				 {
					 StageImage2 = StageImage;
				 }else
				 {
					 id = jp.co.se.android.FlameP.R.drawable.back2;
					 StageImage2 = g.createBitMap( r , id , 0 );
		
					 if(StageImage2 == null)
					 {
						 Library.TraseMsg("ERR StageImage2");
					 }
				 }
			 }		
			 err = 3;
			 Library.TraseMsg("AllReadData3");


			 
			 err = 4;

			 if(EnemyImage == null)
			 {
				 id = jp.co.se.android.FlameP.R.drawable.enemy;
				 EnemyImage = g.createBitMap( r , id  , 0);
	
				 EnemyImageTurn = g.setFlipMode( EnemyImage , Graphics.FLIP_HORIZONTAL );			 
				 
				 
				 if(EnemyImage == null)
				 {
					 Library.TraseMsg("ERR EnemyImage");
				 }
			 }
			 err = 5;
			 Library.TraseMsg("AllReadData4");

			 if(PlayerImage == null)
			 {
				 id = jp.co.se.android.FlameP.R.drawable.player;
				 PlayerImage = g.createBitMap( r , id  , 0);
	
				 if(PlayerImage == null)
				 {
					 Library.TraseMsg("ERR PlayerImage");
				 }
				 PlayerImageTurn = g.setFlipMode( PlayerImage , Graphics.FLIP_HORIZONTAL );
				 
			 }
			 err = 6;
			 Library.TraseMsg("AllReadData5");

			 if(EffectImage == null)
			 {
				 id = jp.co.se.android.FlameP.R.drawable.effect;
				 EffectImage = g.createBitMap( r , id  , 0);
				 if(EffectImage == null)
				 {
					 Library.TraseMsg("ERR EffectImage");
				 }
			 }
			 Library.TraseMsg("AllReadData6");

			 err = 7;
			 if(ShopImage == null)
			 {
				 id = jp.co.se.android.FlameP.R.drawable.shop;
				 ShopImage = g.createBitMap( r , id  , 0);
				 if(ShopImage == null)
				 {
					 Library.TraseMsg("ERR ShopImage");
				 }
			 }

			 {
				 err = 1;

				 ShopTitleImage = new Bitmap[shopImageList.length];
				 
				 for(int i = 0 ; i < shopImageList.length ; i++)
				 {
					 id = shopImageList[i];
					 //int id = shopImageList[0];			 
					 ShopTitleImage[i] = g.createBitMap( r , id , 0 );
				 }
			 }	 
			 Library.TraseMsg("AllReadData12");

			 err = 2;
			 {
				 ShopTitleImageBack = new Bitmap[shopImageBack.length];
				 ShopTitleImageBack_Part = new Bitmap[shopImageBack.length];
				
				 for(int i = 0 ; i < shopImageBack.length ; i++)
				 {
									 
					 if(MainData.DEBUG_MODE_EMU)
					 {
						 ShopTitleImageBack[i] = ShopTitleImage[0];
						 ShopTitleImageBack_Part[i] = ShopTitleImage[0];
					 }else
					 {
						 id = shopImageBack[i];
						 ShopTitleImageBack[i] = g.createBitMap( r , id ,0 );
						 Library.TraseMsg("AllReadData13");
						 id = shopImageBack_Part[i];	
						 ShopTitleImageBack_Part[i] = g.createBitMap( r , id  , 0);
					 }
				 }
				 
				 ShopTitleImageBack_Part_Angle = g.createBitMap( r , R.drawable.shop_back_part2_angle  , 0);
			 }	 
			 
		 }catch (Exception e) {
			// TODO: handle exception

			 int a = 0;
		 }
		 
		 Library.TraseMsg("AllReadData15");

	 }
	 
	 public static void drawBackGround(Graphics g)
	 {
		 //大本背景
//		 g.drawBitMapScale(BackBase ,
//			0 , 0 ,
//			300 , 300 ,
//			0 , 0 , 
//			(int)(MainData.SCREEN_WIDTH_TRUE  + 200),
//			(int)(MainData.SCREEN_HEIGHT_TRUE + 200 ),
//			false );
		 g.setColor(Graphics.getColorOfRGB( 0, 0, 0, 255 ));
		 g.fillRect(0, 0, MainData.SCREEN_WIDTH_TRUE  + 200, MainData.SCREEN_HEIGHT_TRUE + 200, false );
	 }
	 
	 public static void drawBackGroundEnd(Graphics g)
	 {
		 //左
//		 g.drawBitMapScale(BackBase ,
//			0 , 0 ,
//			300 , 300 ,
//			0 , 0 , 
//			(int)(MainData.SCREEN_MOVE_X ),
//			(int)(MainData.SCREEN_HEIGHT_TRUE ),
//			false );
//		 //migi 		 
//		 g.drawBitMapScale(BackBase ,
//			0 , 0 ,
//			300 , 300 ,
//			MainData.SCREEN_WIDTH_TRUE - MainData.SCREEN_MOVE_X, 0 , 
//			(int)(MainData.SCREEN_MOVE_X ),
//			(int)(MainData.SCREEN_HEIGHT_TRUE ),
//			false );
		 
		 
		 
		 g.setColor(Graphics.getColorOfRGB( 0, 0, 0, 255 ));
		 //右
		 g.fillRect(0, 0, 
				 MainData.SCREEN_MOVE_X, MainData.SCREEN_HEIGHT_TRUE * 2, false );
		 //左
		 g.fillRect(MainData.SCREEN_WIDTH_TRUE - MainData.SCREEN_MOVE_X, 0,		 
				 MainData.SCREEN_MOVE_X ,
				 MainData.SCREEN_HEIGHT_TRUE  * 2 ,
				 false );
		 //上
		 g.fillRect(MainData.SCREEN_MOVE_X, 0,		 
				 MainData.SCREEN_WIDTH_DEF ,
				 MainData.SCREEN_MOVE_Y , false );
		 //sita
		 g.fillRect(MainData.SCREEN_MOVE_X, MainData.SCREEN_MOVE_Y + MainData.SCREEN_HEIGHT_DEF ,		 
				 MainData.SCREEN_WIDTH_DEF ,
				 MainData.SCREEN_MOVE_Y , false );
	 }
	 
	 
	 
	//普通
	 public static int DRAW_TYPE_NORMAL = 0;
	 //2重
	 public static int DRAW_TYPE_DBUL = 1;
	 //左へ行く
	 public static int DRAW_TYPE_LEFT = 2;
	 //真ん中
	 public static int DRAW_TYPE_CENTER = 4;
	 //文字半角
	 public static int DRAW_TYPE_HARF_STR = 8;
	 
	 
	 //文字描画
	 public static void DrawString(Graphics g,
			 String str , int x , int y)
	 {
		 
		 DrawString( g , str ,
		   x ,  y ,
		   Library.COLOR_RGB(255, 255, 255) ,
		   0,
		   DRAW_TYPE_NORMAL);
	 }
	 
	 public static void DrawString(Graphics g,
			 String str , int x , int y , int color)
	 {
		 DrawString( g , str ,
		   x ,  y ,
		   color ,
		   0,
		   DRAW_TYPE_NORMAL);
	 }
	 
	 public static void DrawString(Graphics g,
			 String str , int x , int y, 
			 int color ,
			 int color2,
			 int type)
	 {
		 int fontSize = g.getFontSize();
		 
		 
		 g.setColor(color);
		 
		 int stringLong = str.length();
		 
		 if( 0 < (type & DRAW_TYPE_HARF_STR))
		 {
			 stringLong = stringLong / 2; 
		 }
		 
		 int xPoint = 0;
		 
		 if( 0 < (type & DRAW_TYPE_NORMAL) )
		 {
			 
		 }
		 if( 0 < (type & DRAW_TYPE_LEFT))
		 {
			 xPoint = - stringLong * fontSize;
		 }
		 if( 0 < (type & DRAW_TYPE_CENTER))
		 {
			 xPoint = (int)((float)(- stringLong/2 * fontSize/2)* (float)MainData.Width_Size);
		 }
		 if(type == DRAW_TYPE_DBUL)
		 {		 
			 int gosa = fontSize / 10;
			 if(gosa <= 1)
			 {
				 gosa = 1;
			 }
			 g.setColor(color2);
			 g.drawString(str , x+gosa+xPoint, y);
			 g.drawString(str , x+xPoint, y + gosa);			 
		 }

		 g.setColor(color);
		 g.drawString(str , x+xPoint, y);
		 
		 g.setAlpha(255);

	 }
	 
	 public static final int NORMAL_MODE = 0;
	 public static final int PREVIEW_MODE = 1;
	 private static final int VIEW_MODE_Y_UP = -200;
	 private static int view_Mode = 0;
	 
	 public static void DRAW_UI_MODE(int mode)
	 {
		 view_Mode = mode;
	 }
	 
	 public static final int SHOP_NO = 1;
	 public static final int SHOP_BOMB = 2;

	 
	 //ステージ描画ほぼずっと呼ばれる
	 public static void StageDraw(Graphics g , int type , int lank)
	 {	
		 int yUp = 0;
		 if(view_Mode == PREVIEW_MODE)
		 {
			 yUp = VIEW_MODE_Y_UP;
		 }
		 
		
		 
		 
		 g.setColor( Graphics.getColorOfRGB( 255, 255, 255, 255 ) );
		 
		 if(type == SHOP_BOMB)
		 {
			 g.drawBitMapScale( StageImage2 ,
				0 - 10 , yUp -10,
				MainData.SCREEN_WIDTH_DEF + 20 , MainData.SCREEN_HEIGHT_DEF + 20, true);
		 }else
		 {
			 g.drawBitMapScale( StageImage ,
				-10 , yUp -10 ,
				MainData.SCREEN_WIDTH_DEF + 20 , MainData.SCREEN_HEIGHT_DEF + 20, true);
			 
			 drawShop( g , lank);
		 }
	 }
	 
	 public static void TitleDraw(Graphics g)
	 {		 		 

//		 int strX = 5;
//
//		 int strY = 19;		 
//		 
//		 g.setFontSize(Graphics.SIZE_SMALL);
//		 DrawString( g , "現在額" ,
//			strX , 
//			strY ,
//			Graphics.getColorOfRGB( 255, 255, 10,255 ),
//			Graphics.getColorOfRGB( 32, 32, 32, 255 ),
//			DRAW_TYPE_DBUL );
//		 g.setFontSize(Graphics.SIZE_TINY);
//		 
//		 drawManey( g ) ;
//		 
//		 drawNextManey( g , Library.getManey() );
	 }

	 
	 public static boolean DrawShopTitle(Graphics g , int counter , int nowShop)
	 {
		 switch (nowShop) {
			case ShopData.BASE_TYPE_NO_SHOP:
				drawFirstShop(g , counter,nowShop);
				break;
			case ShopData.BASE_TYPE_LEVEL1:
				drawFestivalShop(g , counter,nowShop);
				break;
			case ShopData.BASE_TYPE_LEVEL2:
				drawYataiShop(g , counter,nowShop);
				break;
			case ShopData.BASE_TYPE_LEVEL3:
				drawTrackShop(g , counter,nowShop);
				break;

		default:
			break;
		 }
		 
		 return false;
	 }
	 public static final int MOVE_SPEED_FIRST = 3 ;
	 private static boolean drawFirstShop(Graphics g , int counter , int nowShop)
	 {
		 //背景
		 drawTitleBackNormal(g , counter , nowShop);
		 
		 //キャラ
		 int nowCount = counter % (MOVE_SPEED_FIRST * 11);
		 
		 int charaBaseX = 0;
		 int charaBaseY = 0;
		 int baseImage = ShopData.BASE_IMAGE[nowShop];
		//★焼き鳥
		 int yakiX = 97 +charaBaseX;
		 int yakiY = 177+charaBaseY;

		 int yakiDrawX = 150;
		 int yakiDrawY = 0;
		 int yakiDrawW = 32;
		 int yakiDrawH = 95;
		 
		 boolean erase = false;
		 if(nowCount < MOVE_SPEED_FIRST * 2 )
		 {
			 //全部ある			 
		 }else
		 if(nowCount < MOVE_SPEED_FIRST * 4 )
		 {
			 //いっこくった 
			 yakiDrawX = 182;
			 yakiDrawY = 0;
			 yakiDrawW = 29;
			 yakiDrawH = 95;
		 }else
		 if(nowCount < MOVE_SPEED_FIRST * 6 )
		 {
			//2個食った
			 yakiDrawX = 211;
			 yakiDrawY = 0;
			 yakiDrawW = 29;
			 yakiDrawH = 95;
		 }else
		 if(nowCount < MOVE_SPEED_FIRST * 8 )
		 {
			//3個食った
			 yakiDrawX = 240;
			 yakiDrawY = 0;
			 yakiDrawW = 29;
			 yakiDrawH = 95;
		 }
		 else
		 if(nowCount < MOVE_SPEED_FIRST * 9 )
		 {
			//てさげ
			 yakiDrawX = 240;
			 yakiDrawY = 0;
			 yakiDrawW = 29;
			 yakiDrawH = 95;
			 
			 yakiY += 30;
		 }
		 else
		 if(nowCount < MOVE_SPEED_FIRST * 10 )
		 {
			// 消える
			 erase = true;
		 }
		 else
		 if(nowCount < MOVE_SPEED_FIRST * 11 )
		 {
			// 手提げ　2個フッ化t
			 yakiY += 25;
		 }
		 if(erase == false)
		 {
			 g.drawBitMapScale(ShopTitleImage[baseImage],
					 yakiDrawX , yakiDrawY ,
					 yakiDrawW , yakiDrawH ,
					 yakiX , yakiY , yakiDrawW , yakiDrawH ,
					 true );
		 }
		 
		 //★頭
		 int headX = 100 +charaBaseX;
		 int headY = 176 +charaBaseY;

		 int headDrawX = 300;
		 int headDrawY = 0;
		 int headDrawW = 128;
		 int headDrawH = 78;
		 if(nowCount < MOVE_SPEED_FIRST * 6 )
		 {
			 if(nowCount % (MOVE_SPEED_FIRST * 2) <= MOVE_SPEED_FIRST )
			 {
				 //閉じる
				
			 }else
			 {
				 headDrawX = 300;
				 headDrawY = 79;
				 headDrawW = 128;
				 headDrawH = 78;
			 }		 
		 }
		 g.drawBitMapScale(ShopTitleImage[baseImage] ,
				 headDrawX , headDrawY ,
				 headDrawW , headDrawH ,
			headX , headY , headDrawW , headDrawH ,
			true );
		 
		 //★体
		 int bodyX = 145 +charaBaseX;
		 int bodyY = 246 +charaBaseY;

		 int bodyDrawX = 48;
		 int bodyDrawY = 0;
		 int bodyDrawW = 96;
		 int bodyDrawH = 116;
		 g.drawBitMapScale(ShopTitleImage[baseImage] ,
				 bodyDrawX , bodyDrawY ,
				 bodyDrawW , bodyDrawH ,
				 bodyX , bodyY , bodyDrawW , bodyDrawH ,
				 true );
		 //★手
		 int handX = 97 +charaBaseX;
		 int handY = 262 +charaBaseY;

		 int handDrawX = 0;
		 int handDrawY = 0;
		 int handDrawW = 48;
		 int handDrawH = 80;
		 if(nowCount < MOVE_SPEED_FIRST * 8 )
		 {
			 handY -= 15 ;
		 }else
		 if(nowCount < MOVE_SPEED_FIRST * 9 )
		 {
			 //手さげ		 
			 handDrawX = 0;
			 handDrawY = 132;
			 handDrawW = 48;
			 handDrawH = 65;
		 }else
		 if(nowCount < MOVE_SPEED_FIRST * 10 )
		 {
			//てなし
			 handDrawX = 0;
			 handDrawY = 200;
			 handDrawW = 48;
			 handDrawH = 65;
		 }else
		 if(nowCount < MOVE_SPEED_FIRST * 11 )
		 {
			//手さげ		 
			 handDrawX = 0;
			 handDrawY = 132;
			 handDrawW = 48;
			 handDrawH = 65;
		 }
		 g.drawBitMapScale(ShopTitleImage[baseImage] ,
				 handDrawX , handDrawY ,
				 handDrawW , handDrawH ,
				 handX , handY , handDrawW , handDrawH ,
				 true );
		 
		 
		 
		 return false;
	 }
	 
	 
	 private static boolean drawFestivalShop(Graphics g , int counter , int nowShop)
	 {
		 //背景
		 drawTitleBackNormal(g , counter , nowShop);
		 
		 //キャラ
		 int nowCount = counter % (MOVE_SPEED_FIRST * 4);
		 		
		 int baseImage = ShopData.BASE_IMAGE[nowShop];
		
		 int mainX = 100 ;
		 int mainY = 124 ;
		 int mainDrawX = 0;
		 int mainDrawY = 0;
		 int mainDrawW = 247;
		 int mainDrawH = 264;		 
		 
		 g.drawBitMap(ShopTitleImage[baseImage] ,
			 mainX , mainY,
			 mainDrawX , mainDrawY , mainDrawW , mainDrawH );
		 
		 //左腕
		 int LeftArmX = mainX + 66  ;
		 int LeftArmY = mainY + 101 ;
		 int LeftArmDrawX = 258;
		 int LeftArmDrawY = 0;
		 int LeftArmDrawW = 44;
		 int LeftArmDrawH = 92;		 
		 
		 if(nowCount < MOVE_SPEED_FIRST * 1 )
		 {
			 //上
		 }else
		 if(nowCount < MOVE_SPEED_FIRST * 2 )
		 {
			 //中
			 LeftArmDrawX = 307;
			 LeftArmDrawY = 0;
			 LeftArmDrawW = 44;
			 LeftArmDrawH = 95;	
		 }
		 else
		 if(nowCount < MOVE_SPEED_FIRST * 3 )
		 {
			 //下
			 LeftArmDrawX = 355;
			 LeftArmDrawY = 0;
			 LeftArmDrawW = 44;
			 LeftArmDrawH = 95;	
		 }
		 else
		 if(nowCount < MOVE_SPEED_FIRST * 4 )
		 {
			 //中
			 LeftArmDrawX = 307;
			 LeftArmDrawY = 0;
			 LeftArmDrawW = 44;
			 LeftArmDrawH = 95;	
		 }
		 g.drawBitMap(ShopTitleImage[baseImage] ,
			 LeftArmX , LeftArmY,
			 LeftArmDrawX , LeftArmDrawY ,
			 LeftArmDrawW , LeftArmDrawH );		 
		 
		 
		 //右腕
		 int RightArmX = mainX + 139 ;
		 int RightArmY = mainY + 98;
		 int RightArmDrawX = 256;
		 int RightArmDrawY = 100;
		 int RightArmDrawW = 39;
		 int RightArmDrawH = 98;		 
		 
		 if(nowCount < MOVE_SPEED_FIRST * 1 )
		 {
			 //上
		 }else
		 if(nowCount < MOVE_SPEED_FIRST * 2 )
		 {
			 //中
			 RightArmDrawX = 296;
			 RightArmDrawY = 100;
			 RightArmDrawW = 39;
			 RightArmDrawH = 98;	
		 }
		 else
		 if(nowCount < MOVE_SPEED_FIRST * 3 )
		 {
			 //下
			 RightArmDrawX = 337;
			 RightArmDrawY = 100;
			 RightArmDrawW = 43;
			 RightArmDrawH = 98;	
		 }
		 else
		 if(nowCount < MOVE_SPEED_FIRST * 4 )
		 {
			 //中
			 RightArmDrawX = 296;
			 RightArmDrawY = 100;
			 RightArmDrawW = 39;
			 RightArmDrawH = 98;	
		 }
		 g.drawBitMap(ShopTitleImage[baseImage] ,
			 RightArmX , RightArmY,
			 RightArmDrawX , RightArmDrawY ,
			 RightArmDrawW , RightArmDrawH );
		 
		 
		//頭
		 int HedArmX = mainX + 91 ;
		 int HedArmY = mainY + 115;
		 int HedArmDrawX = 256;
		 int HedArmDrawY = 210;
		 int HedArmDrawW = 74;
		 int HedArmDrawH = 59;		 
		 
		 if(nowCount < MOVE_SPEED_FIRST * 1 )
		 {
			 //上
		 }else
		 if(nowCount < MOVE_SPEED_FIRST * 2 )
		 {
			 //中
			 HedArmDrawX = 331;
			 HedArmDrawY = 210;
			 HedArmDrawW = 74;
			 HedArmDrawH = 59;	
		 }
		 else
		 if(nowCount < MOVE_SPEED_FIRST * 3 )
		 {
			 //下
			 HedArmDrawX = 406;
			 HedArmDrawY = 210;
			 HedArmDrawW = 74;
			 HedArmDrawH = 59;	
		 }
		 else
		 if(nowCount < MOVE_SPEED_FIRST * 4 )
		 {
			 //中
			 HedArmDrawX = 331;
			 HedArmDrawY = 210;
			 HedArmDrawW = 74;
			 HedArmDrawH = 59;		
		 }
		 g.drawBitMap(ShopTitleImage[baseImage] ,
			 HedArmX , HedArmY,
			 HedArmDrawX , HedArmDrawY ,
			 HedArmDrawW , HedArmDrawH );
		 
		 
		 return false;
	 }
	 
	 
	 private static boolean drawYataiShop(Graphics g , int counter , int nowShop)
	 {
		 //背景
		 drawTitleBackMoveing(g , counter , nowShop);
		 drawTitleBackMovingFlont(g , counter , nowShop,0);

		 //キャラ
		 int nowCount = counter % (MOVE_SPEED_FIRST * 4);
		 int yPluse = nowCount % 2 * 5;
		 		
		 int baseImage = ShopData.BASE_IMAGE[nowShop];
		
		 int mainX = 100 ;
		 int mainY = 220 +yPluse;
		 int mainDrawX = 0;
		 int mainDrawY = 0;
		 int mainDrawW = 230;
		 int mainDrawH = 105;		 
		 
		 g.drawBitMap(ShopTitleImage[baseImage] ,
			 mainX , mainY,
			 mainDrawX , mainDrawY , mainDrawW , mainDrawH );
		 
		 
		 //足
		 int LegArmX = mainX + 137;
		 int LegArmY = mainY + 79;
		 int LegArmDrawX = 0;
		 int LegArmDrawY = 0;
		 int LegArmDrawW = 36;
		 int LegArmDrawH = 36;		 
		 
		 //角度
		 int angle = 72 * counter ;
		 
		 g.drawBitMapScale(ShopTitleImageBack_Part_Angle ,
				 LegArmDrawX , LegArmDrawY ,
				 LegArmDrawW +10, LegArmDrawH +10,
				 LegArmX ,LegArmY ,LegArmDrawW + 15, LegArmDrawH + 15,
				 false , angle );
		 //声
		 int VoiceArmX = mainX + 234;
		 int VoiceArmY = mainY + 8;
		 int VoiceArmDrawX = 256;
		 int VoiceArmDrawY = 62;
		 int VoiceArmDrawW = 40;
		 int VoiceArmDrawH = 56;		 
		 
		 if(counter % 10 >= 5)
		 {
			 g.drawBitMapScale(ShopTitleImage[baseImage] ,
					 VoiceArmDrawX , VoiceArmDrawY ,
					 VoiceArmDrawW , VoiceArmDrawH ,
					 VoiceArmX ,VoiceArmY ,VoiceArmDrawW , VoiceArmDrawH ,
					 true  );
		 }
		 //前
		 drawTitleBackMovingFlont(g , counter , nowShop,1);
		 
		 return false;
	 }
	 
	 private static boolean drawTrackShop(Graphics g , int counter , int nowShop)
	 {
		 //背景
		 drawTitleBackMoveing(g , counter , nowShop);
		 drawTitleBackMovingFlont(g , counter , nowShop,0);

		 //キャラ
		 int nowCount = counter % (MOVE_SPEED_FIRST * 4);
		 int yPluse = nowCount % 2 * 5;
		 		
		 int baseImage = ShopData.BASE_IMAGE[nowShop];
		
		 int mainX = 100 ;
		 int mainY = 220 +yPluse;
		 int mainDrawX = 0;
		 int mainDrawY = 0;
		 int mainDrawW = 156;
		 int mainDrawH = 111;		 
		 
		 g.drawBitMap(ShopTitleImage[baseImage] ,
			 mainX , mainY,
			 mainDrawX , mainDrawY , mainDrawW , mainDrawH );
		 
		 //前
		 drawTitleBackMovingFlont(g , counter , nowShop,1);
		 
		 return false;
	 }
	 
	 public static void DrawTitleUnderMenu( Graphics g )
	 {	
		 int color = Graphics.getColorOfRGB( 159, 223, 63, 255 );
		 int color2= Graphics.getColorOfRGB( 191, 255, 95, 255 );
		 
		 int hight = 80;
		 int len = 5;
		 
		  
		 //内部
		 for(int i = 0; i < len; i++ )
		 {
			 if(i % 2 == 0)
			 {
				 g.setColor( color );
			 }else
			 {
				 g.setColor( color2 );
			 }
			 g.fillRect( 0 ,
				 400 + i * hight,
				 480 ,
				 hight);
		 }
	 }
	 public static void DrawTitleMenuAll( Graphics g )
	 {	
		 int color = Graphics.getColorOfRGB( 159, 223, 63, 255 );
		 int color2= Graphics.getColorOfRGB( 191, 255, 95, 255 );
		 
		 int hight = 80;
		 int len = 10;
		 
		  
		 //内部
		 for(int i = 0; i < len; i++ )
		 {
			 if(i % 2 == 0)
			 {
				 g.setColor( color );
			 }else
			 {
				 g.setColor( color2 );
			 }
			 g.fillRect( 0 ,
				 i * hight,
				 480 ,
				 hight);
		 }
	 }
	 public static final int ICON_OK = 0;
	 public static final int ICON_DAME = 1;


	 
	 
	 public static final int ARROW_W = 64;
	 public static final int ARROW_H = 128;

	 public static final int ARROW_X_L 	= 6;
	 public static final int ARROW_Y 	= 526;
	 public static final int ARROW_X_R  = MainData.SCREEN_WIDTH_DEF - ARROW_W - ARROW_X_L;

	 
	 //矢印切り替え
	 public static void drawArowPage( Graphics g , int icon[] )
	 {
		 int drawX = 0;
		 int drawY = 0;
		 int drawW = 0;
		 int drawH = 0;		 
		 //←
		 
		 if(icon[0] == ICON_OK )
		 {
			 drawX = 160;
			 drawY = 160;
			 drawW = ARROW_W;
			 drawH = ARROW_H;	
		 }else
		 if(icon[0] == ICON_DAME )
		 {
			 drawX = 224;
			 drawY = 160;
			 drawW = ARROW_W;
			 drawH = ARROW_H;
		 }
		 
		 g.drawBitMap( IconImage,
			ARROW_X_L, ARROW_Y ,
			drawX, drawY, drawW , drawH );
		 
		 //みぎ
		 
		 if(icon[1] == ICON_OK )
		 {
			 drawX = 160;
			 drawY = 160;
			 drawW = ARROW_W;
			 drawH = ARROW_H;	
		 }else
		 if(icon[1] == ICON_DAME )
		 {
			 drawX = 224;
			 drawY = 160;
			 drawW = ARROW_W;
			 drawH = ARROW_H;
		 }

		 g.drawBitMap( IconImageTurn ,
			ARROW_X_R, ARROW_Y ,
			IconImageTurn.getWidth() - drawX - drawW,
			drawY,
			drawW,
			drawH );
		 
	 }
	 
	 private static int counrerTitleLog = 15;
	 
	 public static void drawTitleLog( Graphics g )
	 {
		 
		 if(counrerTitleLog <= 1)
		 {
			 counrerTitleLog = 0;
		 }else
		 {
			 counrerTitleLog--;			 
		 }
		 
		 int Log1W = 366;
		 int Log1H = 60;
		 int Log1X = ( MainData.SCREEN_WIDTH_DEF - Log1W ) / 2 + counrerTitleLog * 30;
		 int Log1Y = 16 ;
		 
		 g.drawBitMap( IconImage ,
		 	Log1X, Log1Y ,
		 	274,
		 	75,
		 	Log1W,
		 	Log1H );
		 
		 int Log2W = 320;
		 int Log2H = 61;
		 int Log2X = ( MainData.SCREEN_WIDTH_DEF - Log2W) / 2 - counrerTitleLog * 30 ;
		 int Log2Y = 80 ;
		 
		 g.drawBitMap( IconImage ,
		 	Log2X, Log2Y ,
		 	320,
		 	138,
		 	Log2W,
		 	Log2H);
		 
	 }
	 
	 
	 private static final int MAX_CLOUD = 3;
	 private static int 	CloudsX[] = new int [MAX_CLOUD];
	 private static int 	CloudsY[] = new int [MAX_CLOUD];
	 private static boolean CloudsRight[] = new boolean [MAX_CLOUD];
	 private static int 	CloudsType[] = new int [MAX_CLOUD];
	 private static int 	CloudsWait[] =  {20,100,180};

	 
	 private static void drawTitleBackNormal(Graphics g , int counter , int nowShop)
	 {
		 int backImage = ShopData.BASE_IMAGE_BACK[nowShop];
		 
		 g.drawBitMapScale(ShopTitleImageBack[backImage] ,
					0 , 0 ,
					480 , 400 ,
					0 , 0 , MainData.SCREEN_WIDTH_DEF , MainData.SCREEN_HEIGHT / 2 ,
					true );
		 
		 //雲とか
		 for(int i = 0; i < MAX_CLOUD; i++)
		 {
			 if(CloudsWait[i] >= 1)
			 {
				 CloudsWait[i]--;
				 if(CloudsWait[i] == 0)
				 {
					 if(Library.random.nextInt() % 2 == 0)
					 {
						 CloudsRight[i] = true;
						 CloudsX[i] = -110;
					 }else
					 {
						 CloudsRight[i] = false ;
						 CloudsX[i] = MainData.SCREEN_WIDTH + 110;						 
					 }
					 
					 CloudsType[i] = Library.random.nextInt() % 2;
					 
					 CloudsY[i] = Library.random.nextInt() % 80 + 100;
				 }
				 continue;
			 }
			 
			 //雲移動
			 if( counter % MOVE_SPEED_FIRST == 0 )
			 {
				 boolean out = false;
				 if(CloudsRight[i])
				 {
					 CloudsX[i] += Library.getDecodeX(10);
					 if(CloudsX[i] > MainData.SCREEN_WIDTH_DEF + Library.getDecodeX(50))
					 {
						 out = true;
					 }
				 }else
				 {
					 CloudsX[i] -= Library.getDecodeX(10);					 
					 if(CloudsX[i] < - Library.getDecodeX(90) )
					 {
						 out = true;
					 }
				 }
				 if(out)
				 {
					 CloudsWait[i] = Library.random.nextInt() % 40 + 10;
					 continue;
				 }
			 }
			 
			 
			 //雲ﾀｲﾌﾟ
			 int cloudX = CloudsX[i];
			 int cloudY = CloudsY[i];
			 int cloudDrawX = 13;
			 int cloudDrawY = 37;
			 int cloudDrawW = 108;
			 int cloudDrawH = 52;
			 if(CloudsType[i] == 1 )
			 {
				 cloudDrawX = 2;
				 cloudDrawY = 104;
				 cloudDrawW = 107;
				 cloudDrawH = 61;
			 }
			 int cloudDrawW2 = cloudDrawW;
			 int cloudDrawH2 = cloudDrawH;
//			 if(counter % 8 >= 4)
//			 {
//				 
//			 }else
//			 {
//				 cloudDrawW2 = (int)((float)(cloudDrawW * 0.75f));
//				 cloudDrawH2 = (int)((float)(cloudDrawH * 1.25f));
//			 }
			 
			 g.drawBitMapScale(ShopTitleImageBack_Part[backImage] ,
				 cloudDrawX , cloudDrawY ,
				 cloudDrawW , cloudDrawH ,
				 cloudX , cloudY , cloudDrawW2 , cloudDrawH2 ,
				 true );
		 }
		 
		 //太陽とか
		 int sanX = 300;
		 int sanY = 30;
		 int sanDrawX = 150;
		 int sanDrawY = 0;
		 int sanDrawW = 169;
		 int sanDrawH = 160;
		 if(counter % (MOVE_SPEED_FIRST*2) >= MOVE_SPEED_FIRST)
		 {
			 sanDrawX = 150;
			 sanDrawW = 179;
			 sanDrawY = 160;
			 sanDrawH = 170;
		 }
		 g.drawBitMapScale(ShopTitleImageBack_Part[backImage] ,
			sanDrawX , sanDrawY ,
			sanDrawW , sanDrawH ,
			sanX , sanY , sanDrawW , sanDrawH ,
			true );
		 
	 }
		 

	 
	 private static void drawTitleBackMoveing(Graphics g , int counter , int nowShop)
	 {
		 //背景
		 g.drawBitMapScale(ShopTitleImageBack[SHOP_BACK_RUN] ,
					0 , 0 ,
					480 , 400 ,
					0 , 0 , MainData.SCREEN_WIDTH , MainData.SCREEN_HEIGHT / 2 ,
					true );		 
	 }
	 
	 private static final int MAX_OBJ_FLONT = 3;
	 private static int objFlont_X[][] = new int[2][MAX_OBJ_FLONT];
	 private static int objFlont_Y[][] = new int[2][MAX_OBJ_FLONT];
	 private static int objFlont_Wait[][] = {{20,40,60,80,100},{20,40,60,80,100}};
	 private static int objFlont_Type[][] = new int[2][MAX_OBJ_FLONT];
	 private static final int OBJ_STONE = 0;
	 private static final int OBJ_WOOD  = 1;
	 private static final int OBJ_HOUSE  = 2;

	 private static void drawTitleBackMovingFlont(Graphics g , int counter , int nowShop , int type)
	 {
		//石とかが流れてくる
		 for(int i = 0; i < MAX_OBJ_FLONT; i++)
		 {
			 if(objFlont_Wait[type][i] >= 1)
			 {
				 objFlont_Wait[type][i]--;
				 if(objFlont_Wait[type][i] == 0)
				 {
					 //int ran = Library.random.nextInt() % 2;
					 //objFlont_Type[type][i] = ran;
					 if(type == 0)
					 {
//						 if(objFlont_Type[type][i] == OBJ_STONE)
//						 {
//							 objFlont_X[type][i] = MainData.SCREEN_WIDTH + 40 +Library.random.nextInt() % 40;
//							 objFlont_Y[type][i] = Library.random.nextInt() % 20 + 260;
//						 }else
//						 if(objFlont_Type[type][i] == OBJ_WOOD)
						 int ran = Library.random.nextInt() % 2;
						 if(ran == 0)
						 {
							 objFlont_Type[type][i] = OBJ_WOOD;
							 {
								 objFlont_X[type][i] = MainData.SCREEN_WIDTH_DEF + 40 +Library.random.nextInt() % 40;
								 objFlont_Y[type][i] = 165;
							 }
						 }else
						 {
							 objFlont_Type[type][i] = OBJ_HOUSE;
							 {
								 objFlont_X[type][i] = MainData.SCREEN_WIDTH_DEF + 40 +Library.random.nextInt() % 40;
								 objFlont_Y[type][i] = 165;
							 }
						 }
					 }else
					 {
						// if(objFlont_Type[type][i] == OBJ_STONE)
						 objFlont_Type[type][i] = OBJ_STONE;
						 {
							 objFlont_X[type][i] = MainData.SCREEN_WIDTH_DEF + 40 +Library.random.nextInt() % 40;
							 objFlont_Y[type][i] = Library.random.nextInt() % 40 + 370;
						 }
//						 else
//						 if(objFlont_Type[type][i] == OBJ_WOOD)
//						 {
//							 objFlont_X[type][i] = MainData.SCREEN_WIDTH + 40 +Library.random.nextInt() % 40;
//							 objFlont_Y[type][i] = Library.random.nextInt() % 40 + 290;
//						 }
					 }
				 }
				 continue;
			 }
			 objFlont_X[type][i] -= 14;
			 if(objFlont_X[type][i] <= -110)
			 {
				 objFlont_Wait[type][i] = 30;
			 }

			 int drawx = 0;
			 int drawy = 0;
			 int draww = 0;
			 int drawh = 0;
			 if(objFlont_Type[type][i] == OBJ_STONE)
			 {
				 drawx = 16;
				 drawy = 130;
				 draww = 30;
				 drawh = 28;
			 }else
			 if(objFlont_Type[type][i] == OBJ_WOOD)
			 {
				 drawx = 12;
				 drawy = 2;
				 draww = 48;
				 drawh = 117;
			 }else
			 if(objFlont_Type[type][i] == OBJ_HOUSE)
			 {
				 drawx = 64;
				 drawy = 0;
				 draww = 128;
				 drawh = 117;
			 }
			 g.drawBitMapScale(ShopTitleImageBack_Part[SHOP_BACK_RUN] ,
					 drawx , drawy ,
					 draww , drawh ,
				objFlont_X[type][i] , objFlont_Y[type][i] , draww , drawh ,
				true );

		 }
	 }
	 
	 
	 public static void DrawWindow(Graphics g,
			 int x,int y , int width , int hight)
	 {
		 int color = Graphics.getColorOfRGB( 154, 154, 214, 255 );
		 
		 DrawWindow( g,
				 x, y , width , hight, color);
		 
		 
	 }
	 /**
	  * ウィンドウ描画
	  * @param g
	  */
	 public static void DrawWindow(Graphics g,
			 int x,int y , int width , int hight,int color)
	 {
		 
		 //塗りつぶし
		 g.setColor(color);
		 
		 int xmove = Library.getDecodeX(4);
		 int ymove = Library.getDecodeY(4);

		 int xmainus = xmove*2;
		 int ymainus = ymove*2;

		 //内部
		 g.fillRect(x+xmove,
				 y+ymove,
				 width-xmove*2,
				 hight-ymove*2+1);
		 
		 //左上
		 g.drawBitMapScale(IconImage , 
		 	0 , 240 , 6 , 6,
		 	x , y , 
				 4 , 4
	     );
		 //左下
		 g.drawBitMapScale(IconImage , 
				 	0 , 246 , 6 , 6,
				 	x , y + hight - ymove, 
				 4 , 4
	     );
		 //右上
		 g.drawBitMapScale(IconImage ,
				 	6 , 240 , 6 , 6,
				 	 x +width - xmove, y , 
				 4 , 4
	     );
		 //右下
		 g.drawBitMapScale(IconImage ,				 
				 6 , 246 , 6 , 6,
				 x +width - xmove, y + hight - ymove, 
				 4 , 4
	     );
		 
		 //上
		 g.drawBitMapScale( IconImage,
				 4, 240, 4, 6,
				    x+xmove, y,
				    width - xmainus , 4 );		 
		 //←
		 g.drawBitMapScale( IconImage,
				 0, 244, 6, 4,
				    x, y+ymove,
				    4 , hight - ymainus
				     );		 		 
		 //右
		 g.drawBitMapScale( IconImage,
				 
				 0, 244, 6, 4,
				    x+ width - xmove, y+ymove,
				    4 , hight -ymainus );		 		 
		 //下
		 g.drawBitMapScale( IconImage,
				 4, 240, 4, 6,
			     x+xmove, y+ hight - ymove,
				 width - xmainus , 4
		  );				 
	 }

	 public static final int COLOR_BLACK = Graphics.getColorOfRGB( 0, 0, 0, 255 );

	 public static final int S_COLOR_ON = Graphics.getColorOfRGB( 155, 155, 155, 255 );
	 public static final int S_COLOR_OFF = Graphics.getColorOfRGB( 235, 235, 235, 255 );

	 public static final int S_COLOR_ON_EQ 	= Graphics.getColorOfRGB( 218, 241, 130, 255 );
	 public static final int S_COLOR_OFF_EQ = Graphics.getColorOfRGB( 96, 229 , 37, 255 );

	 public static final int S_COLOR_ON_SEL 	= Graphics.getColorOfRGB( 159 , 255 , 159, 255 );
	 public static final int S_COLOR_OFF_SEL	= Graphics.getColorOfRGB( 63 , 223 , 159, 255 );

	 /**
	  * スイッチ描画
	  * @param g
	  */
	 public static void DrawSwicheMsg(Graphics g,
			 String msg,
			 int x,int y , int width , int hight , boolean flag, int colorOff , int colorOn)
	 {
		
		 DrawSwicheMsg( g,
				  msg,  Graphics.SIZE_TINY ,
				  x, y ,  width ,  hight ,  flag,  colorOff , colorOn);
	 }
	 public static void DrawSwicheMsg(Graphics g,
			 String msg, int Strsize,
			 int x,int y , int width , int hight , boolean flag, int colorOff , int colorOn)
	 {
		 DrawSwiche( g ,
				  x, y ,  width ,  hight ,  flag , colorOff , colorOn );
		 //メッセージ
		 int length = msg.length();
		 
		 
		 int strX = x + width/2  -
		 Strsize / 2 * length;

		 int strY = y + hight/2  +
		 Strsize / (3) ;

		 g.setFontSize(Strsize );
		 
		 DrawString( g , msg ,
			strX , 
			strY ,
			Graphics.getColorOfRGB( 0, 0, 0,255 ),
			Graphics.getColorOfRGB( 180, 180, 180, 255 ),
			DRAW_TYPE_DBUL );
	 }
	 public static void DrawSwicheMsg(Graphics g,
			 String msg,
			 int x,int y , int width , int hight , boolean flag)
	 {
		 DrawSwicheMsg( g,
				  msg,
				  x, y , width , hight , flag, S_COLOR_ON , S_COLOR_OFF);
	 }
	 
	 
	 public static int SwichePict_X[] = {
		 0,66,132,198,
		 0,66,132,198,
		 0,0,0,0,
		 264,0
	 };	 
	 public static int SwichePict_Y[] = {
		 0,0,0,0,
		 45,45,45,45,
		 0,0,0,0,
		 0,0
	 };
	 
	 public static int SwichePict_ShopX[] ;
	 public static int SwichePict_ShopY[] ;

	 private static void InitSwichtPoint()
	 {
		 SwichePict_X = new int [ShopData.FLAME_TYPE_MAX];
		 SwichePict_Y = new int [ShopData.FLAME_TYPE_MAX];

		 int type = ShopData.FLAME_TYPE_NORMAL;
		 SwichePict_X[type] = 0;
		 SwichePict_Y[type] = 0;
		 type = ShopData.FLAME_TYPE_CASTAM;
		 SwichePict_X[type] = 66;
		 SwichePict_Y[type] = 0;
		 type = ShopData.FLAME_TYPE_SMOAL;
		 SwichePict_X[type] = 132;
		 SwichePict_Y[type] = 0;
		 type = ShopData.FLAME_TYPE_SNIPING;
		 SwichePict_X[type] = 198;
		 SwichePict_Y[type] = 0;
		 type = ShopData.FLAME_TYPE_HOMING;
		 SwichePict_X[type] = 264;
		 SwichePict_Y[type] = 0;

		 SwichePict_ShopX = new int [ShopData.BASE_TYPE_MAX];
		 SwichePict_ShopY = new int [ShopData.BASE_TYPE_MAX];

		 type = ShopData.BASE_TYPE_NO_SHOP;
		 SwichePict_ShopX[type] = 0;
		 SwichePict_ShopY[type] = 135;

		 type = ShopData.BASE_TYPE_LEVEL1;
		 SwichePict_ShopX[type] = 66;
		 SwichePict_ShopY[type] = 135;

		 type = ShopData.BASE_TYPE_LEVEL2;
		 SwichePict_ShopX[type] = 132;
		 SwichePict_ShopY[type] = 135;

		 type = ShopData.BASE_TYPE_LEVEL3;
		 SwichePict_ShopX[type] = 198;
		 SwichePict_ShopY[type] = 135;
	 }
	 
	 public static int SwichePict_W = 66;
	 public static int SwichePict_H = 45;

	 
	 public static void DrawSwichePict(Graphics g,
			 int pictType ,
			 int x,int y , int width , int hight , boolean flag, int colorOff , int colorOn,
			 boolean gold ,int goldNum , boolean shop)
	 {
		 DrawSwiche( g ,
				  x, y ,  width ,  hight ,  flag , colorOff , colorOn );
		 //絵の描画
		 
		 int drawx = 0;
		 int drawy = 0;
		 if(shop)
		 {
			 drawx = SwichePict_ShopX[pictType] ;
			 drawy = SwichePict_ShopY[pictType];
		 }else
		 {
			 drawx = SwichePict_X[pictType] ;
			 drawy = SwichePict_Y[pictType];
			 
		 }
		 
		 g.drawBitMap( Icon_Swiche , 
			 x + 15 , y + 15, 
			 drawx , drawy ,
			 SwichePict_W , SwichePict_H);
		 //値段
		 if(gold)
		 {
			 drawNum(g,
					 x+86, y+76,
					 goldNum , 0.2f, true, true, false);
		 }		 
	 }
	 
	 
	 
	 
	 
	 
	 public static void DrawSwiche(Graphics g,
			 int x,int y , int width , int hight , boolean flag)
	 {
		 //塗りつぶし
		 DrawSwiche( g,
				  x, y , width , hight ,  flag, S_COLOR_ON , S_COLOR_OFF);
	 }
	 
	 
	 public static void DrawSwiche(Graphics g,
			 int x,int y , int width , int hight , boolean flag , int colorOff , int colorOn)
	 {
		//塗りつぶし
		 if(flag)
		 {
			 g.setColor(colorOff);
		 }else
		 {
			 g.setColor(colorOn);
		 }
		 int kadohaba = 15;

		 

		 int haba = 15;
		 
		 //内部
		 g.fillRect(x+haba-1,
				 y+haba-1,
				 width-haba*2+3,
				 hight-haba*2+2);

		 

		 //左側
		 g.drawBitMapScale( IconImage,
				 315, 0, 3, 15,
				    x + haba-1, y,
				    width - haba*2 +5, haba );	

		 //下側
		 g.drawBitMapScale( IconImage,
				 315, 0, 3, 15,
			     x+haba-1, y+ hight - haba,
			     width - haba*2+5 , haba );	
		 
		 //左端
		 g.drawBitMap(IconImage , x , y , 
				 	300 , 0 , 16 , 75);
		 //右端
		 g.drawBitMap(IconImage , x +width - kadohaba, y , 
				 317 , 0 , 16 , 75);
				 
	 }
	 
	 
	 
	 public static final int CUR_WIDTH = 80;
	 /**
	  * カーソル
	  * System.currentTimeMillis()
	  * @param g
	  */
	 public static void DrawCur(Graphics g,
			 int x,int y ,long time)
	 {
		 //�_��
		 g.drawBitMap( IconImage,
				    x, y,
				    9, 0, 15 , 13 );
	 }
	 
	 public static final int CUR_SPACE = 35;
	 
	 public static void WindowMsgCur(Graphics g,
			 String msg[] , int cur,
			 int yPluse,int widthPluse ,long time)
	 {	 
		 
		 int msgNum = msg.length;
		 
		 int ySpace = CUR_SPACE;
		 
		 int hight  = msgNum * ySpace + 10;
			 
		 int width = 120 + widthPluse;
		 
		 int x = (MainData.SCREEN_WIDTH_DEF - width)/2;
		 
		 int y = (MainData.SCREEN_HEIGHT_DEF - hight)/2 + yPluse;
		 
		 //�E�B���h�E
		 DrawWindow(g, x, y, width, hight);
		 //�J�[�\��
		 DrawCur( g,
				 x + 10
				 , y + 10 + cur * (ySpace)  ,time);
		 //����
		 for(int i = 0; i < msgNum ; i++)
		 {
			 DrawString( g , msg[i] ,
				x + 28, 
				y + 20 + i * ySpace ,
				Graphics.getColorOfRGB( 255, 255, 245,255 ),
				Graphics.getColorOfRGB( 10, 10, 210, 255 ),
				DRAW_TYPE_DBUL );
		 }
		 
//		 //X�K��
//		 int x = (MainData.SCREEN_WIDTH_DEF - width)/2;
		 
	 }
	 
	 public static void WindowMsg(Graphics g,
			 String msg[] , 
			 int yPluse,int widthPluse)
	 {	 
		 int msgNum = msg.length;
		 int hight  = msgNum * 25 + 10;
			 
		 int width = 120 + widthPluse;
		 
		 int x = (MainData.SCREEN_WIDTH - width)/2;
		 
		 int y = (MainData.SCREEN_HEIGHT - hight)/2 + yPluse;
		 
		 //�E�B���h�E
		 DrawWindow(g, x, y, width, hight);
				 
		 //����
		 for(int i = 0; i < msgNum ; i++)
		 {			 
			 DrawString( g , msg[i] , x + 28
					 , y + 20 + i * 25 ,
						Graphics.getColorOfRGB( 255, 255, 245,255 ),
						Graphics.getColorOfRGB( 10, 10, 210, 255 ),
						DRAW_TYPE_DBUL );
		 }
	 }
	 
	 
	 public static void WindowMsgCurAndMsg(Graphics g,
			 String msg[] , int cur,long time ,
			 String msgMain[] )
	 {	 
		 //下の選択し
		 g.setAlpha( 255 );
		 WindowMsgCur( g,
				  msg ,  cur,
				  40, 0 , time);
		 g.setAlpha( 255 );
		 //上のメッセージ
		 WindowMsg( g,
				 msgMain , 
				  -40  ,
				  90 );
	 }
	 
	 public static void FeedInOut(Graphics g , int alphe,int r,int green, int b)
	 {
		 if(alphe >= 255)
		 {
			 alphe = 255;
		 }else
	     if(alphe <= 0)
		 {
			 alphe = 0;			 
		 }
		 
		 g.setColor( Graphics.getColorOfRGB( r, green, b, alphe ) );
		 
		 g.fillRect( 0 , 0 , MainData.SCREEN_WIDTH ,
				 MainData.SCREEN_HEIGHT , true );
		 g.setAlpha(255);

	 }
	 
	 public static void FeedInOut(Graphics g , int alphe)
	 {
		 FeedInOut( g ,  alphe,0,0, 0);
	 }
	 
	 public static void drawPushStart(Graphics g , int alpha)
	 {		 		
		int width = 78;
		int height = 10;
		g.setAlpha( alpha );
		g.drawBitMap( IconImage,
				MainData.SCREEN_WIDTH_DEF / 2 - width / 2,
				MainData.SCREEN_HEIGHT_DEF / 2 - height / 2,
				    24, 1, width , height );		 
		g.setAlpha( 255 );
	 }
	 
	 
	 public static void drawEnemy(Graphics g , int type , 
			 int anime ,
			 int x , int y )
	 {
		 drawEnemy( g ,  type , 
				  anime ,
				  x ,  y ,false);
	 }
	 public static void drawEnemy(Graphics g , int type , 
			 int anime ,
			 int x , int y ,boolean turn)
	 {	
		 drawEnemy(  g ,  type , 
		  anime ,
		  x ,  y , turn , 1.0f);
		 
	 }
	 public static void drawEnemy(Graphics g , int type , 
			 int anime ,
			 int x , int y ,boolean turn , float big )
	 {	
		 int width = 64;
		 
//		 Library.TraseMsg("anime"+anime);
//		 Library.TraseMsg("x"+x);
		 int yUp = 0;
		 if(view_Mode == PREVIEW_MODE)
		 {
			 yUp = VIEW_MODE_Y_UP;
		 }
		 
		 int typeHeight = type;
		 int TYPE_HEIGHT_CHANGE = 8;
		 int TYPE_W_CHANGE = 0;
		 if(typeHeight >= TYPE_HEIGHT_CHANGE)
		 {
			 typeHeight -= TYPE_HEIGHT_CHANGE;
			 TYPE_W_CHANGE = 256;
		 }
		 
		 if(turn)
		 {
			 
			 //Bitmap AfBit = g.setFlipMode( EnemyImage , Graphics.FLIP_HORIZONTAL );			 
			 //Library.TraseMsg("turn");
			 g.drawBitMapScale( EnemyImageTurn,
				 EnemyImageTurn.getWidth() - anime * width - width - TYPE_W_CHANGE,
				 typeHeight * width, 
				 width , width ,
					(int)(x - (width/2*(big-1))),
					(int)(y - (width/2*(big-1)))+yUp,
					(int)(width * big),
					(int)(width * big)
					);
		 
		 }else
		 {
			 
			 g.drawBitMapScale( EnemyImage,
				anime * width + TYPE_W_CHANGE , typeHeight * width,
				width , width ,
				(int)(x - (width/2*(big-1))),
				(int)(y - (width/2*(big-1)))+yUp,
				(int)(width * big),
				(int)(width * big)
				);	
		 }
		 
	 }
	 
	 
	 //
	 public static final int PLAY_ANIME_NORMAL  = 0;
	 public static final int PLAY_ANIME_ATTACK  = 1;
	 public static final int PLAY_ANIME_BOM1	= 2;
	 public static final int PLAY_ANIME_BOM2	= 3;
	 public static final int PLAY_ANIME_BOM3	= 4;
	 public static final int PLAY_ANIME_SUPER	= 5;
	 public static final int PLAY_ANIME_SUPER_DRAY= 6;
	 public static final int PLAY_ANIME_MAX		= 7;
	 
	 private static int DrawPlayerPictX[] = new int[PLAY_ANIME_MAX];
	 private static int DrawPlayerPictY[] = new int[PLAY_ANIME_MAX];
	 private static int DrawPlayerPictW[] = new int[PLAY_ANIME_MAX];
	 private static int DrawPlayerPictH[] = new int[PLAY_ANIME_MAX];

	 private static void animeInit()
	 {
		int type = PLAY_ANIME_NORMAL;
		DrawPlayerPictX[type] = 0;
		DrawPlayerPictY[type] = 0;
		DrawPlayerPictW[type] = 128;
		DrawPlayerPictH[type] = 224;
		
		type = PLAY_ANIME_ATTACK ;
		DrawPlayerPictX[type] = 128;
		DrawPlayerPictY[type] = 0;
		DrawPlayerPictW[type] = 128;
		DrawPlayerPictH[type] = 224;

		type = PLAY_ANIME_BOM1 ;
		DrawPlayerPictX[type] = 256;
		DrawPlayerPictY[type] = 0;
		DrawPlayerPictW[type] = 150;
		DrawPlayerPictH[type] = 224;

		type = PLAY_ANIME_BOM2 ;
		DrawPlayerPictX[type] = 160;
		DrawPlayerPictY[type] = 224;
		DrawPlayerPictW[type] = 147;
		DrawPlayerPictH[type] = 224;
		
		type = PLAY_ANIME_BOM3 ;
		DrawPlayerPictX[type] = 0;
		DrawPlayerPictY[type] = 224;
		DrawPlayerPictW[type] = 143;
		DrawPlayerPictH[type] = 229;

		type = PLAY_ANIME_SUPER ;
		DrawPlayerPictX[type] = 416;
		DrawPlayerPictY[type] = 0;
		DrawPlayerPictW[type] = 180;
		DrawPlayerPictH[type] = 236;

		type = PLAY_ANIME_SUPER_DRAY;
		DrawPlayerPictX[type] = 128;
		DrawPlayerPictY[type] = 448;
		DrawPlayerPictW[type] = 128;
		DrawPlayerPictH[type] = 224;

	 }
	 
	 public static void drawPlayer(Graphics g ,
			 int anime ,
			 int x , int y )
	 {	
		 int yUp = 0;
		 
		 
		 
		 boolean shake = false;
		 
		 switch (anime) {
			case PLAY_ANIME_BOM1:
			case PLAY_ANIME_SUPER:
				shake = true;
				break;
	
			default:
				break;
		}
		 
		 if(view_Mode == PREVIEW_MODE)
		 {
			 yUp = VIEW_MODE_Y_UP;
		 }
		 g.setColor(Graphics.getColorOfRGB( 255, 255, 255, 255 ));		 
		 
		 if(anime == UI.PLAY_ANIME_BOM3)
		 {
			 drawDeadAnime(g , x , y + yUp);
			 return;
		 }
		 if(shake)
		 {
			 x += Library.random.nextInt() % 16 - 8;
			 y += Library.random.nextInt() % 16 - 8;
			 if(anime == PLAY_ANIME_SUPER)
			 {
				 x -= 30;
			 }
		 }
		 
		 g.drawBitMap( PlayerImage ,
			x ,
			y+yUp ,
			DrawPlayerPictX[anime] ,
			DrawPlayerPictY[anime] ,
			DrawPlayerPictW[anime] ,
			DrawPlayerPictH[anime] );			 
	 }
	 
	 private static int DeadAnimeCount = 0;
	 private static int DeadAnimeVect  = 0;
	 private static int DeadAnimePoint = 0;
	 
	 public static void drawDeadAnime(Graphics g , int x, int y)
	 {
		 g.setColor(Graphics.getColorOfRGB( 255, 255, 255, 255 ));		 
		 
		 if(view_Mode == PREVIEW_MODE)
		 {
			 y += VIEW_MODE_Y_UP;
		 }
		 DeadAnimeCount++;
		 if(DeadAnimeCount % 14 == 0)
		 {
			 if(DeadAnimeVect == 0)
			 {
				 DeadAnimeVect = 1;				 
			 }else
			 {
				 DeadAnimeVect = 0;
			 }
		 }
		 
		 
		 if(DeadAnimeVect == 0)
		 {
			 DeadAnimePoint += 3;

			 g.drawBitMap( PlayerImage ,
				x + DeadAnimePoint,
				y ,
				DrawPlayerPictX[PLAY_ANIME_BOM3] ,
				DrawPlayerPictY[PLAY_ANIME_BOM3] ,
				DrawPlayerPictW[PLAY_ANIME_BOM3] ,
				DrawPlayerPictH[PLAY_ANIME_BOM3] );		 
		 }else
		 if(DeadAnimeVect == 1)
		 {
			 DeadAnimePoint -= 3;
			 g.drawBitMap( PlayerImageTurn ,
				x + DeadAnimePoint,
				y ,
				
				PlayerImageTurn.getWidth() - 
				DrawPlayerPictX[PLAY_ANIME_BOM3] -
				DrawPlayerPictW[PLAY_ANIME_BOM3] ,
				
				DrawPlayerPictY[PLAY_ANIME_BOM3] ,
				DrawPlayerPictW[PLAY_ANIME_BOM3] ,
				DrawPlayerPictH[PLAY_ANIME_BOM3] );	
		 }
		 
	 }

	 
	 public static void drawPlayerPowerUpDray(Graphics g ,
			int count, int x , int y )
	 {
		 int shakeX = Library.random.nextInt() % 10;
		 int shakeY = Library.random.nextInt() % 10;
		 
		 x += shakeX;
		 y += shakeY;
		 
		 drawPlayer( g ,
				 PLAY_ANIME_SUPER_DRAY  ,
				 x , y );
		 
		 
		 int drawx[] = {320,448,320,448,320,448};

		 int drawy[] = {256,256,384,384,512,512};
		 
		 for(int i = 0; i < 6; i ++)
		 {
			 if(count <= i * 1 + 1)
			 {
				 g.drawBitMap( PlayerImage ,
					x + 4,
					y + 4,
					drawx[i], drawy[i] , 128 , 128 );
				 break;
			 }			 
		 }
	 }
	 
	 
	 //エフェクト
	 public static void drawEffectPict(Graphics g ,
			 int x , int y,
			 int xPoint , int yPoint ,int Drawwidth , int DrawHeight)
	 {		
		
		 
		 drawEffectPict( g ,
				 x , y,
				 Drawwidth,  DrawHeight,
				 xPoint ,  yPoint , Drawwidth , DrawHeight);
	 }
	 
	 public static void drawEffectPict(Graphics g ,
			 int x , int y,
			 int w, int h,
			 int xPoint , int yPoint ,int Drawwidth , int DrawHeight)
	 {
		 int yUp = 0;
		 if(view_Mode == PREVIEW_MODE)
		 {
			 yUp = VIEW_MODE_Y_UP;
		 }
		 g.drawBitMapScale( EffectImage ,
				 xPoint , yPoint ,
				 Drawwidth , DrawHeight ,
					x ,y+yUp, w ,  h);
	 }
	 
	 
	 public static void drawFlameEffectPict(Graphics g ,
			 int type , 
			 int alphe,
			 int x , int y,
			 int w, int h,
			 int xPoint , int yPoint ,int Drawwidth , int DrawHeight,int angle)
	 {
//		 int yUp = 0;
//		 if(view_Mode == PREVIEW_MODE)
//		 {
//			 yUp = VIEW_MODE_Y_UP;
//		 }
//		 
//		 g.setColor(Graphics.getColorOfRGB( 255, 255, 255, alphe ));
//		 
//		 drawImageAngle( g, Flame[type],x,y+yUp,
//				 w , h ,
//				 xPoint , yPoint , Drawwidth , DrawHeight ,
//				 angle );		 
//		 g.setColor(Graphics.getColorOfRGB( 255, 255, 255, 255 ));
	 }
	 
	 
	 
	 public static void drawFlameEffect(Graphics g , int x , int y,int rarge, int colar)
	 {
		 g.setFontSize(rarge);
		 DrawString( g,
			"●" ,  x ,  y , colar);
		 
		 g.setFontSize(Graphics.SIZE_TINY);
	 }
	 
	 
	 /**
	  * 
	  * @param g
	  * @param image
	  * @param x
	  * @param y
	  * @param w
	  * @param h
	  * @param drawx
	  * @param drawy
	  * @param width
	  * @param height
	  * @param th 角度
	  */

	 public static void drawImageAngle(Graphics g, Bitmap image,int x,int y,
			 int w , int h ,
			 int drawx , int drawy , int width , int height ,
			 int th )
	 {
			int []mn = new int[6];

			//Library.TraseMsg("th"+th);
			//Library.TraseMsg("large"+large);
			float rad = 3.141592f * (360-th) / 180;        
			int cos1 = (int)(Math.cos(rad) * 4096);
			int sin1 = (int)(Math.sin(rad) * 4096);
			

			
			
			int w1 = width/2;
			int h1 = height/2;

			mn[0] = (cos1);
			mn[1] = (-sin1);
			mn[2] = (cos1 * (-w1) - sin1 * (-h1) + x * 4096);
			mn[3] = (sin1);
			mn[4] = (cos1);
			mn[5] = (sin1 * (-w1) + cos1 * (-h1) + y * 4096);

			g.drawBitMapScale( image ,
					drawx , drawy ,
					width , height ,
						x ,y, w ,  h);
	}
	 

	 //�h��Ԃ�
	 public  static void drawFillRect(Graphics g 
			 , int x, int y,
			 int width , int height ,
			 int colr , int colg, int colb, int alphe)
	 {
		 g.setColor( Graphics.getColorOfRGB( colr, colg, colb, alphe ) );
		 g.fillRect( x , y , width ,
				 height );
		 g.setAlpha(255);

	 }
	 //SCOA�`��
	 public static void drawScoa(Graphics g ) 
	 {
		 g.drawBitMap( IconImage ,
				3,
				3,
				0, 23 ,
				35 , 9 );
	 }
	 
	 //LV�`��
	 public static void drawLv(Graphics g ) 
	 {
		 g.drawBitMap( IconImage ,
				170,
				3,
				35, 23 ,
				19 , 9 );
	 }
	 
	 //NEXT�`��
	 public static void drawNext(Graphics g ) 
	 {
		 g.drawBitMap( IconImage ,
				170,
				14,
				54, 23 ,
				38 , 9 );
	 }
	 
	 //CHAIN�`��
	 public static void drawChain(Graphics g ) 
	 {
		 g.drawBitMap( IconImage ,
				52,
				44,
				92, 19 ,
				64 , 13 );
	 }
	 
	 //LIFE�`��
	 public static void drawLife(Graphics g , int life) 
	 {
		 int width = 21;
		 
		 for(int i = 0 ; i < life; i++)
		 {
			 g.drawBitMap( IconImage ,
					5 + width * i,
					17,
					9, 0 ,
					15 , 13 );
		 }
	 }


	 public static void drawPar(Graphics g ,int x ,int y,int par) 
	 {
		 int width = 21;
		 
		 int maneyX = x;
		 int maneyY = y;
		 
		 drawNum( g ,  maneyX ,  maneyY  ,
				 par , 2);
		 
		 g.drawBitMap( IconImage ,
			 maneyX + 18 ,
			 maneyY - 2,
			209, 36 ,
			18 , 22 );
	 }
	 
	 public static void drawManey(Graphics g) 
	 {
		 int maneyX = 190;
		 int maneyY = 3;
		 
		 drawManey( g ,maneyX ,maneyY, Library.getManey()); 
	 }
	 
	 public static void drawManey(Graphics g ,int x ,int y,int maney) 
	 {
		 int width = 21;
		 
		 int maneyX = x;
		 int maneyY = y;
		 
		 drawNum( g ,  maneyX ,  maneyY  ,
				 maney , 2);
		 
		 g.drawBitMap( IconImage ,
			 maneyX + 18 ,
			 maneyY + 2 ,
			160, 17 ,
			18 , 16 );
	 }
	 
	 //タイトル最初のアイコン位置他
	 public static final int TitleIcon_W = 150;
	 public static final int TitleIcon_H = 150;
	 
	 
	 public static int TitleIcon_x[] = {160,0,320,0,320,160};
	 public static int TitleIcon_y[] = {300,375,375,525,525,600};
	 public static int TitleIcon_derawx[] = {0,150,300,450,450,300};
	 public static int TitleIcon_derawy[] = {300,300,300,300,150,150};

	 //ショップカテゴリーアイコン位置他
	 public static final int TitleShopCategory_W[] = {300,300,300};
	 public static final int TitleShopCategory_H[] = {75,75,75};
	 
	 private static int ShopCat_BaseX ;
	 
	 
	 public static final int Title_Title_Menu_x[] 	= new int [6];
	 public static final int Title_Title_Menu_y[] 	= {450,540,540,630,630,720};
	 public static final String Title_Title_MenuStr[] = {"スタート","セッティング","ショップ","ゲーム設定","遊び方","終了"};
	 
	 public static final int Title_Menu_W[] = {300,200,200,200,200,200};
	 public static final int Title_Menu_H[] = {75,75,75,75,75,75};

	 public static final int Title_Menu_STR_SIZE[] = {38,20,20,20,20,20};
	 
	 
	 public static int Title_Title_Menu_x2[] 			= new int [6];
	 public static int Title_Title_Menu_y2[] 	= {450,550,650};
	 public static String Title_Title_MenuStr2[] = {"ショップ","遊び方","ゲーム設定"};
	 
	 
	 public static int TitleShopCategory_x[] = new int [6];
	 public static int TitleShopCategory_y[] = {450,550,650};
	 public static String TitleShopCategoryStr[] = {"お店改築","炎購入","もどる"};
	 
	 public static int TitleSettingCategory_x[] = new int [6];
	 public static int TitleSettingCategory_y[] = {450,540,630,720};
	 public static String TitleSettingCategoryStr[] = {"炎変更","お店変更","レコード","もどる"};
	 public static final int TitleSettingCategory_W[] = {300,300,300,300};
	 public static final int TitleSettingCategory_H[] = {75,75,75,75};
	 
	 public static int TitleDataShow_x[] = new int [6];
	 public static int TitleDataShow_y[] = {630,720};
	 public static String TitleDataShowStr[] = {"ランキング","もどる"};
	 public static final int TitleDataShow_W[] = {300,300};
	 public static final int TitleDataShow_H[] = {75,75};
	 
	 public static void intIconPoint()
	 {
		 ShopCat_BaseX =  (MainData.SCREEN_WIDTH - Library.getDecodeX(TitleShopCategory_W[0])) / 2;

		 Title_Title_Menu_x[0]  = ShopCat_BaseX;
		 Title_Title_Menu_x[1]  = 20;
		 Title_Title_Menu_x[2]  = 240;
		 Title_Title_Menu_x[3]  = 20;
		 Title_Title_Menu_x[4]  = 240;
		 Title_Title_Menu_x[5]  = 140;

		 Title_Title_Menu_x2[0] = ShopCat_BaseX;
		 Title_Title_Menu_x2[1] = ShopCat_BaseX;
		 Title_Title_Menu_x2[2] = ShopCat_BaseX;
		 
		 TitleShopCategory_x[0] = ShopCat_BaseX;
		 TitleShopCategory_x[1] = ShopCat_BaseX;
		 TitleShopCategory_x[2] = ShopCat_BaseX;
		 		 
		 TitleSettingCategory_x[0] = ShopCat_BaseX;
		 TitleSettingCategory_x[1] = ShopCat_BaseX;
		 TitleSettingCategory_x[2] = ShopCat_BaseX;
		 TitleSettingCategory_x[3] = ShopCat_BaseX;
		 
		 TitleDataShow_x[0] = ShopCat_BaseX;
		 TitleDataShow_x[1] = ShopCat_BaseX;
		 TitleDataShow_x[2] = ShopCat_BaseX;
		 TitleDataShow_x[3] = ShopCat_BaseX;		 
	 }
	 
	 
	 public static void drawIconCommand(int type, Graphics g,int select )
	 {
		 int max = 0;
		 int x[] = TitleShopCategory_x;
		 int y[] = TitleShopCategory_y;
		 String strs[] = TitleShopCategoryStr;
		 int width[] = TitleShopCategory_W;
		 int hight[] = TitleShopCategory_H;
		 int strSize[] = {38,38,38,38,38,38,38,38} ;
		 
		 if(type == Title.TITLE_STEP_START)
		 {
			 max = Title.MAX_TITLE_CUR;
			 x 	   = Title_Title_Menu_x;
			 y	   = Title_Title_Menu_y;
			 width = Title_Menu_W;
			 hight = Title_Menu_H;
			 strs  = Title_Title_MenuStr;	
			 strSize = Title_Menu_STR_SIZE;
		 }else
		 if(type == Title.TITLE_STEP_START_2)
		 {
			 max = Title.MAX_SHOP_CAT_CUR;
			 x 	   = Title_Title_Menu_x2;
			 y	   = Title_Title_Menu_y2;
			 strs  = Title_Title_MenuStr2;			 
		 }else
		 if(type == Title.TITLE_STEP_SHOP)
		 {
			 max = Title.MAX_SHOP_CAT_CUR;
		 }else
		 if(type == Title.TITLE_STEP_SETTING)
		 {
			 max   = Title.MAX_SETTING_CUR;
			 x 	   = TitleSettingCategory_x;
			 y	   = TitleSettingCategory_y;
			 strs  = TitleSettingCategoryStr;
			 width = TitleSettingCategory_W;
			 hight = TitleSettingCategory_H;
		 }else
		 if(type == Title.TITLE_STEP_DATA_SHOW)
		 {
			 max = Title.MAX_SHOP_DATA_CUR;
			 x 	   = TitleDataShow_x;
			 y	   = TitleDataShow_y;
			 strs  = TitleDataShowStr;			 
		 }
	 
		 
		 g.setAlpha(255);
		 
	
		
		 
		 //一覧
		 for(int i = 0; i < max ; i++)
		 {
			 int xpoint = 0;
			 int ypoint = 0;
			 
			 xpoint = x[i];
			 ypoint = y[i];
			 
			 if(i == select)
			 {
				 
			 }else
			 {
				 DrawSwicheMsg( g,
					 strs[i],
					 strSize[i],
					 xpoint ,
					 ypoint ,
					 width[i] , hight[i] ,
					 false ,
					 S_COLOR_ON , S_COLOR_OFF);
			 }
		 }		 
		 
		 if(select != -1)
		 {
			 DrawSwicheMsg( g,
				 strs[select],
				 strSize[select],
				 x[select] ,
				 y[select] ,
				 width[select] , hight [select],
				 true ,
				 S_COLOR_ON , S_COLOR_OFF);
		 }
	 }
	 
	 public static void drawTitleManey(Graphics g , int maney)
	 {
		 DrawWindow(g, 0, 400 , 480, 50);
		 
		 float big = 0.7f ;		 
		 drawNum( g , 445  , 400 ,
			maney , big , true , true);		 
	 }
	 
	 public static void drawTitleCommand(int type , Graphics g,int select , int count)
	 {
		 ShopCat_BaseX = (MainData.SCREEN_WIDTH - TitleShopCategory_W[0]) / 2;
		 int max = 0;
		 int x[] = TitleIcon_x;
		 int y[] = TitleIcon_y;
		 int drawx[] = TitleIcon_derawx;
		 int drawy[] = TitleIcon_derawy;
		 if(type == Title.COMMAND_TYPE_TITLE)
		 {
			 max = Title.MAX_TITLE_CUR;
			 x = TitleIcon_x;
			 y = TitleIcon_y;
			 
			 drawx = TitleIcon_derawx;
			 drawy = TitleIcon_derawy;
		 }else
		 if(type == Title.COMMAND_TYPE_MENU)
		 {	
			 select -= Title.MAX_TITLE_CUR;
			 max = Title.MAX_MENU_CUR;
		 }
		 
		 int alpha = 255 - 50 * count;
		 
		 g.setAlpha(alpha);
		 
	
		 
		 
		 //一覧
		 for(int i = 0; i < max ; i++)
		 {
			 int xpoint = 0;
			 int ypoint = 0;
			 int xpointDraw = 0;
			 int ypointDraw = 0;
			 if(type == Title.COMMAND_TYPE_TITLE)
			 {
				 xpoint = x[i];
				 ypoint = y[i];
				 xpointDraw = drawx[i];
				 ypointDraw = drawy[i];
			 }
			 if(i == select)
			 {
				 
			 }else
			 {
				 g.drawBitMap( IconImage ,
					xpoint ,
					ypoint ,
					xpointDraw , ypointDraw ,
					UI.TitleIcon_W , UI.TitleIcon_H );	
			 }
		 }		 
		 
		 if(select != -1)
		 {
			 g.drawBitMapScale( IconImage ,
					drawx[select],drawy[select],
					UI.TitleIcon_W, UI.TitleIcon_H,
					x[select] - UI.TitleIcon_W/2,
					y[select] - UI.TitleIcon_H/2, 
					UI.TitleIcon_W  * 2,
					UI.TitleIcon_H  * 2);		 
		 }
		 
		 
//		 DrawSwiche( g,
//				 32,222 ,300 , 75 ,
//				 false, S_COLOR_ON , S_COLOR_OFF);
		 
	 }

	 public static void drawNum(Graphics g , int x , int y ,
			 int Num , float Big )
	 {
		 drawNum( g , x , y ,
				  Num , Big , false , false , false);
	 }
	 
	 public static void drawNum(Graphics g , int x , int y ,
			 int Num , float Big , boolean conma , boolean gold )
	 {
		 drawNum( g ,  x ,  y ,
				  Num ,  Big ,  conma ,  gold , false , false);
	 }
	 
	 public static void drawNum(Graphics g , int x , int y ,
			 int Num , float Big , boolean conma ,
			 boolean gold , boolean par )
	 {
		 drawNum( g ,  x ,  y ,
				  Num ,  Big ,  conma ,  gold , par , false);		 
	 }
	 
	 //数字
	 public static void drawNum(Graphics g , int x , int y ,
			 int Num , float Big , boolean conma ,
			 boolean gold , boolean par , boolean hiki)
	 {
		 //Num = 9000;
		 if(Num <= -1)
		 {
			 Num = 1;
		 }
		 int ex = Num;
		 int len = 1;
		 while(ex >= 10)
		 {
			 if(ex >= 10)
			 {
				 ex /= 10;
				 len++;
			 }
		 }
		 
		 boolean sen = false;
		 if(len >= 7)
		 {
			 sen = true;
		 }
		 
		 
		 int numLen = 1; 
		 
		 int NumDrawX[] = {64,0,64,128,192,0,64,128,192,0};
		 int NumDrawY[] = {192,0,0,0,0,96,96,96,96,192};
		 int NumWidth = 64;
		 int NumHeight= 96;
		 
		 int conmaAddCount = 0;
		 int conmaAddNum = 0;
		 
		 int goldAddWidth = 0;
		 
		 int baseNumBigW = (int)((float)(NumWidth * Big));
		 int baseNumBigH = (int)((float)(NumWidth * Big));

		 int baseConmaBigW = (int)((float)(32 * Big));
		 int baseConmaBigH = (int)((float)(32 * Big));

		 int baseGoldBigW = (int)((float)(NumWidth * Big));
		 int baseGoldBigH = (int)((float)(NumWidth * Big));

		 int addWBase = 101;
		 //羊描画
		 if(gold|hiki|par)
		 {
			 if(gold)
			 {
				 g.drawBitMapScale( NumberPict ,
				 
					128 ,192 ,
					69 , 96 ,				
					
				 	x - (int)((float)((40  ) * Big)),
					y - (int)((float)((6)  *Big)),
					baseGoldBigW,
					baseGoldBigH
					
				 );
			 }else
			 if(par)
			 {
				 g.drawBitMapScale( NumberPict ,
					276 , 0 ,
					71 , 96 ,				
					
				 	x - (int)((float)((40  ) * Big)),
					y - (int)((float)((6)  *Big)),
					baseGoldBigW,
					baseGoldBigH
					
				 );
			 }else
			 if(hiki)
			 {
				 g.drawBitMapScale( NumberPict ,
					276 , 192 ,
					69 , 96 ,				
					
				 	x - (int)((float)((40  ) * Big)),
					y - (int)((float)((6)  *Big)),
					baseGoldBigW,
					baseGoldBigH
					
				 );
			 }
				
			 goldAddWidth = (int)((float)(((addWBase)* Big))) ;
		 }
		 int firstNum = 0;
		 //１００万以上
		 if(sen )
		 {
			 //千
			 g.drawBitMapScale( NumberPict ,
				354 , 0 ,
				66 , 96 ,				
				
			 	x - (int)((float)((5 + addWBase  ) * Big)),
				y - (int)((float)((6)  *Big)),
				baseGoldBigW , 
				baseGoldBigH			
			 );
			 goldAddWidth += (int)((float)(((65)* Big))) ; 
			 firstNum = 3;
			 numLen = 1000;
		 }
		 
		 
		 
		 
		 for(int i = firstNum; i < len ; i++)
		 {
			 int baseX = goldAddWidth + conmaAddNum * (int)((float)(22* Big));
			 //コンマ描画
			 if(conma)
			 {
				 conmaAddCount++;
				 if(conmaAddCount >= 4)
				 {
					 conmaAddCount = 1;
					
					 //コンマ
					 g.drawBitMapScale( NumberPict ,
					 
						224 , 224 ,
						32 , 32 ,				
						
					 	x - (i-firstNum) * baseNumBigW - baseX + (int)((float)((36)* Big)),
						y + (int)((float)((29)* Big)),
						baseConmaBigW,
						baseConmaBigH
						
					 );
					 conmaAddNum++;
				 }
			 }
			 //数字描画
			 baseX = goldAddWidth + conmaAddNum * (int)((float)(22* Big));
			 numLen *= 10;
		 
			 ex = Num % numLen;
			 
			 if( i >= 1)
			 {
				 ex /= (numLen / 10);
			 }
			 
			 g.drawBitMapScale( NumberPict ,
					 
				NumDrawX[ex] , NumDrawY[ex] ,
				NumWidth , NumHeight ,				
				
			 	x - (i-firstNum) * baseNumBigW - baseX,
				y,
				baseNumBigW,
				baseNumBigH
				
			 );
		 }
	 }
	 
	 public static int debugLv = 0;
	 public static void drawDebug(Graphics g , int toucheX , int toucheY ,String msg)
	 {		 
		 if(MainData.DEBUG_MODE_DRAW == false)
		 {
			 return;
		 }
		 
		 int y = 13;
		 int ySpace = 13;
		 g.setColor( Graphics.getColorOfRGB( 0, 0, 0, 255 ) );
		 
		 
		 String[] msgs = {"DEBUG_MODE",
				 "debug1 " + MainData.g_debug ,
				 "debug2 " + MainData.g_debug2,
				 "Msg 0 " + MainData.g_debug_MSG[0],
     			 "Msg 1 " + MainData.g_debug_MSG[1],
     			 "Msg 2 " + MainData.g_debug_MSG[2],
     			 "Msg 3 " + MainData.g_debug_MSG[3],
     			 "Msg 4 " + MainData.g_debug_MSG[4],

		 };

		 
		 g.setFontSize(Graphics.SIZE_TINY);

		 for(int i = 0; i < msgs.length; i++)
		 {
			 DrawString(  g,
				msgs[i], 5 , y + i * ySpace , 
					 Library.COLOR_RGB(250, 250, 250) ,
					 Library.COLOR_RGB(0, 0, 0),
					 DRAW_TYPE_DBUL);
		 }
		 g.setAlpha(255);

	 }
	 
	 
	 
	 public static void drawResultIcon(Graphics g)
	 {
		 int width = 78;
		 int ResultX = MainData.SCREEN_WIDTH_DEF / 2 - width / 2;
		 int ResultY = 30;
		 
		 g.drawBitMap( IconImage ,
			ResultX ,
			ResultY ,
			178, 16 ,
			width , 17 );
	 }
	 
	 public static void drawResultEnemy(Graphics g,
			 int len , int Type , int Num , int point)
	 {

		 //●絵ねミー描画
		 int drawLen = 80;
		 
		 //X位置
		 int drawEnemyX = 40;		 
		 if(len % 2 == 1)
		 {
			 drawEnemyX = 280;
		 }
		 
		 int drawEnemyY = 120 + drawLen * (len / 2);
		 
		 //Library.TraseMsg("drawEnemyY"+drawEnemyY);
		 
		 //敵
		 drawEnemy( g , Type , 
				 MainData.AllCounter % Enemy.ANIME_MAX[Type] ,
				 drawEnemyX , drawEnemyY - 4 );
		
		 //●匹		 
		 int NumX = drawEnemyX + 190 ;
		 int NumY = drawEnemyY;
		 
		 drawNum( g ,  NumX ,  NumY  ,
				 Num , 0.3f , false , false , false , true);
		 
	 
		 //お金
		 int maneyX = NumX ;
		 int maneyY = NumY + 30 ;
		 
		 drawNum( g ,  maneyX ,  maneyY  ,
				 point  , 0.3f , false , true );		 
	 }
	 
	 public static void drawResultEnemy(Graphics g,
			 int Maney , int NowManey )
	 {
		 drawResultEnemy( g,
				  Maney , NowManey ,0.0f,0.0f );		 
	 }
	 public static void drawResultEnemy(Graphics g,
			 int Maney , int NowManey ,float par,float parPluse)
	 {
		 //利率
		 if(par != 0.0f)
		 {
			 int par_x =440;
			 int par_y = 180;
			 drawNum( g ,  par_x ,  par_y  ,
					 (int)(par * 100) , 1.0f , false , false , true , false);
			 //文字
			 int par_str_x = 40;
			 int par_str_y = 240 ;			 
			 g.setFontSize(64);
			 DrawString(g, "利率", par_str_x, par_str_y,
				 Graphics.getColorOfRGB( 255, 255, 10,255 ),
				 Graphics.getColorOfRGB( 22, 22, 22,255 ) ,				 
				 DRAW_TYPE_DBUL);
		 }
		 
		 //被害　OR　収益
		 if(parPluse != 0.0f)
		 {
			 boolean mainus = false;
			 if(parPluse < 0)
			 {
				 mainus = true;
				 parPluse *= -1;
			 }
			 int maney_x = 440;
			 int maney_y = 400;
			 drawNum( g , maney_x , maney_y ,
					 (int)(parPluse * 100) , 1.0f , false , false , true , false);
			 //文字
			 int maney_str_x = 40;
			 int maney_str_y = 390;	
			 g.setFontSize(64);
			 if(mainus )
			 {
				 DrawString(g, "爆発被害－", maney_str_x, maney_str_y,
					 Graphics.getColorOfRGB( 0, 15, 255,255 ),
					 Graphics.getColorOfRGB( 22, 22, 22,255 ) ,
					 DRAW_TYPE_DBUL);				 
			 }else
			 {
				 DrawString(g, "店収益＋", maney_str_x, maney_str_y,
					 Graphics.getColorOfRGB( 255, 15, 10,255 ),
					 Graphics.getColorOfRGB( 22, 22, 22,255 ) ,
					 DRAW_TYPE_DBUL);
			 }
		 }
		 //収入
		 {
			 int maney_x = 440;
			 int maney_y = 540;
			 drawNum( g , maney_x , maney_y ,
				 (int)(Maney) , 1.0f ,
				 true , true , false , false);
			 //文字
			 int maney_str_x = 40;
			 int maney_str_y = 530;	
			 g.setFontSize(64);
			 DrawString(g, "収入", maney_str_x, maney_str_y,
				 Graphics.getColorOfRGB( 255, 15, 10,255 ),
				 Graphics.getColorOfRGB( 22, 22, 22,255 ) ,
				 DRAW_TYPE_DBUL);
		 }

		 //総額
		 {
			 int maney_x = 440;
			 int maney_y = 680;
			 drawNum( g ,  maney_x ,  maney_y  ,
					 (int)(NowManey) , 1.0f ,
					 true , true , false , false);
			 //文字
			 int maney_str_x = 40;
			 int maney_str_y = 670;			 
			 g.setFontSize(64);
			 DrawString(g, "総額", maney_str_x, maney_str_y,
				 Graphics.getColorOfRGB( 255, 15, 10,255 ),
				 Graphics.getColorOfRGB( 22, 22, 22,255 ) ,
				 DRAW_TYPE_DBUL);
		 }
	 }
	 
	 public static final int TIME_TYPE_NORMAL = 0;
	 public static final int TIME_TYPE_DOWN   = 1;
	 public static final int TIME_TYPE_UP	  = 2;
	 
	 public static void drawTimerGage(Graphics g ,int timer ,int type) 
	 {
		 //時計
		 int timeIconX = 5;
		 int timeIconY = 10;	

		 int timeDrawX = 0;
		 int timeDrawY = 0;
		 int timeDrawW = 34;
		 int timeDrawH = 36;		 

		 if(type == TIME_TYPE_NORMAL)
		 {
			 timeDrawX = 0;
			 timeDrawY = 320;
		 }else
		 if(type == TIME_TYPE_DOWN)
		 {
			 timeDrawX = 34;
			 timeDrawY = 320;
		 }else
		 if(type == TIME_TYPE_UP)
		 {
			 timeDrawX = 68;
			 timeDrawY = 320;		 
		 }
		 g.drawBitMap( IconImage ,
			 timeIconX ,
			 timeIconY ,
			 timeDrawX, timeDrawY ,
			 timeDrawW , timeDrawH );
		 
		 
		 int timeX = 42;
		 int timeY = 11;		 
		 
		 //ゲージ元
		 g.drawBitMapScale(IconImage, 0, 360, 240 , 40,
		 	timeX, timeY, 206, 40);
		 
		 //ゲージバー
		 if(timer >= 1)
		 {
			 float time = timer;
			 float gage = (float)(time / Play.MAX_TIME );
			 int width = 190;			 
			 width *= gage;
			 
			 g.drawBitMap
			 (IconImage,
				timeX + 8 , timeY + 10,
				0 , 400  ,
				width , 20 );
			 
		 }
		 
	 }
	 
	 public static void drawPowerGage(Graphics g ,int power,int powerMax,int lvMax) 
	 {
		 int maxWidth = 64;
		 
		 int powerX = MainData.SCREEN_WIDTH - 82 ;
		 int powerY = MainData.SCREEN_HEIGHT - 46;
		 

		 //原型
		 g.drawBitMap( IconImage ,
				 powerX ,
				 powerY ,
					0, 200 ,
					70 , 20 );
		 
		 if(lvMax >= 1)
		 {
			 int lv = 0;
			 while(true)
			 {
				 if(power >= powerMax)
				 {
					 power -= powerMax;
					 lv++;
				 }else
				 {
					 break;
				 }
			 }
			 
			 if(lv >= lvMax )
			 {
				 power = powerMax;
			 }
			 
			 drawNum(g, 
				powerX + 3,
				powerY - 20,
				lv, 1);
		 }
		 
		 
		 if(power >= 1)
		 {
			 //Library.TraseMsg("time"+time);
			 float gage = (float)((float)power / (float)powerMax );
			 
			// Library.TraseMsg("gage"+gage);
			 
			
			 int Width = maxWidth;
			 
			 Width *= gage;
			 
			 g.drawBitMap( IconImage ,
				powerX + 3,
				powerY + 3,
				0, 221 ,
				Width , 14 );
		 }
		 
	 }
	 
	 private static void drawNextManey(Graphics g ,int maney) 
	 {
		 int x = 190;
		 int y = 30;
		 
		 int idx = Library.nowLank();
		 
		 if( idx  >= ShopData.GUREAD_MAX)
		 {
			 //drawManey( g , x , y, 0);
		 }else{
			 drawManey( g , x , y, ShopData.NEXT_GOLD[idx]);
			 
			 int strX = 5 ;

			 int strY = 40;		 
			 
			 g.setFontSize(Graphics.SIZE_SMALL);
			 DrawString( g , "目標額" ,
				strX , 
				strY ,
				Graphics.getColorOfRGB( 255, 20, 20,255 ),
				Graphics.getColorOfRGB( 40, 40, 40, 255 ),
				DRAW_TYPE_DBUL );
			 g.setFontSize(Graphics.SIZE_TINY);
		 }
		 
		 
	 }
	 
	 public static final int SHOP_BASE_X = 350;
	 public static final int SHOP_BASE_Y = 700;

	 /**
	  * @param lank
	  */
	 public static void drawShop(Graphics g , int lank)
	 {		
		 int yUp = 0;
		 if(view_Mode == PREVIEW_MODE)
		 {
			 yUp = VIEW_MODE_Y_UP;
		 }
		 g.setAlpha( 255 );
		 int shopX = SHOP_BASE_X + +SHOP_X_PLUSE[lank];
		 int shopY = SHOP_BASE_Y + yUp;
		 
		 drawShop( g ,  lank , shopX , shopY );
	 }
	 
	 public static int SHOP_WIDTH[] = {
		 80,
		 106,
		 134,
		 156,
		 36,37,58,117,108};	 
	 public static int SHOP_HEIGHT[] = {
		 80,
		 87,
		 102,
		 110,
		 35,58,65,75,68};
	 public static int SHOP_X[] = {
		 280,
		 396,
		 258,
		 0,
		 0,79,37,123,121};
	 public static int SHOP_Y[] = {
		 0,
		 3,
		 93,
		 90,
		 0,0,58,82,0};
	 
	 public static int SHOP_X_PLUSE[] = {0,0,0,0,0,0,-10,-30,-20};
	 
//	 public static String SHOP_NAME[] = {"お祭り屋台","木製リヤカー屋台",
//		 "トラック屋台","居住スペース付き店舗",
//		 "本格店舗","都心貸しビル１F",
//		 "料亭風焼き鳥屋","焼き鳥城",
//		 "ぴーちゃん式甲型"
//		 
//	 };
	 private static boolean ShopAnime = false;
	 private static int shopAnimeCount = 0;
	 
	 private static int shopAnimeW = 0;
	 private static int shopAnimeH = 0;
	 private static int shopAnimeWMax = 0;
	 private static int shopAnimeHMax = 0;
	 
	 public static void setShopAnime(int lank)
	 {
		 ShopAnime = true;
		 shopAnimeCount = 0;
		 shopAnimeW = SHOP_WIDTH[lank];
		 shopAnimeH = SHOP_HEIGHT[lank];
		 shopAnimeWMax = shopAnimeW;
		 shopAnimeHMax = shopAnimeH;
	 }
	 /**
	  * @param lank
	  */
	 public static void drawShop(Graphics g , int lank , int x , int y)
	 {
		 int shopX = x;
		 int shopY = y;
		 int width = SHOP_WIDTH[lank];
		 int height = SHOP_HEIGHT[lank];
		 
		 int drawX = SHOP_X[lank];
		 int drawY = SHOP_Y[lank];
		 
		 if(ShopAnime)
		 {
			 if(shopAnimeCount >= 0 &&
				 shopAnimeCount <= 3)
			 { 
				 shopAnimeW += shopAnimeWMax / 8;
				 shopAnimeH -= shopAnimeHMax / 6;
			 }else
			 if(shopAnimeCount >= 3 &&
				 shopAnimeCount <= 6)
			 { 
				 shopAnimeW -= shopAnimeWMax / 8;
				 shopAnimeH += shopAnimeHMax / 6;
			 }else
			 {
				 ShopAnime = false;
			 }
			 
			 g.drawBitMapScale(
				ShopImage,
				drawX,drawY ,
				width , height, 
				shopX - shopAnimeW / 2,
				(shopY - height / 2) + (shopAnimeHMax - shopAnimeH),
				shopAnimeW ,
				shopAnimeH 
			);			 
			 shopAnimeCount++;
		 }else
		 {
			 g.drawBitMap( ShopImage ,
				shopX - width/2,
				shopY - height/2,
				drawX, drawY ,
				width , height );
		 }
	 }
	 
	 /**
	  * @param g
	  * @param maney
	  */
	 public static void drawUpGrade( Graphics g ) 
	 {
		 int x = MainData.SCREEN_WIDTH / 2 ;
		 int y = 60;
		 
		 g.setFontSize( Graphics.SIZE_SMALL );
		 
		 
		 UI.DrawString( g , "CONGRATULATIONS" ,
			x , 
			y ,
			Graphics.getColorOfRGB( 255, 250, 0,255 ),
			Graphics.getColorOfRGB( 120, 120, 120, 255 ),
			UI.DRAW_TYPE_DBUL|DRAW_TYPE_CENTER );		 
		 UI.DrawString( g , "UP　GREADE!" ,
			x , 
			y + 30,
			Graphics.getColorOfRGB( 255, 0, 0,255 ),
			Graphics.getColorOfRGB( 120, 120, 120, 255 ),
			UI.DRAW_TYPE_DBUL|DRAW_TYPE_CENTER );
		 
		 g.setFontSize( Graphics.SIZE_TINY );
		 
	 }
	 
	 public static String helpStr[][] =
	 {
		 //物語
		 {"昔か今か、あるところにぴーちゃんという",
		  "火を吹くひよこがおったそうな。",
		  "",
		  "ぴーちゃんは焼き鳥屋で",
		  "作っていたそうな。",
		  "",
		  "これは一人のひよこによる、",
		  "炎の焼き鳥奮闘記である・・・・！",
		 },
		 
		 //操作
		 {"制限時間内に決定キーで",
		  "炎弾を飛ばし、空を飛ぶ鳥などを",
		  "焼いてお金を稼ぎます。",
		  "",
		  "ミサイルに炎を当ててしまうと",
		  "ゲーム終了となり、その回の稼ぎが",
		  "減少してしまいます。",
		  "SOFT1キーでギブアップできます。",
		 },
		 //ゲームの目的
		 {"お金を稼いで焼き鳥屋を大きくして",
		  "いきましょう。",
		  "",
		  "目標金額に達するたびに",
		  "焼き鳥屋がグレードアップします。",
		  "君は最終段階まで稼げるか？！",
		 },
		 //ワンポイント
		 {"・炎は確実に当てよう。 ",
		  "炎をはずしてしまうと、制限時間が",
		  "減ってしまうので、確実に当てましょう。",
		  "逆に炎を当てると制限時間が増えます。",
		  "",
		  " ・引き際が肝心 ",
		  "ミサイルが多くなってきて、炎を当て",
		  "てしまいそうならギブアップしよう。",
		  "引くのも勇気だ！",
		 },
		//アイテム
		 {"鳥を燃やすとアイテムが",
		  "落ちてくることがあります。",
		  "",
		  "・鷹の爪",
		  "とうがらし。これをとると、",
		  "一定時間MAXパワーで火を吹く。",
		  "ミサイルも爆発させることなく",
		  "燃やしてしまうぞ！",
		  "",
		  "・金の卵",
		  "これをとると、清算時の収入が",
		  "約10%増えるぞ。"
		 },

		//お問い合わせ
		 {"バグや問題点など",
		  "ございましたらこちらにお願いします。",
		  "[gasutorageta@hotmail.co.jp]",
		 },
		 
		//製作者
		 {" ・（ｸﾞﾗﾌｨｯｸ）スコーピオン",
		  "[http://heavysco.web.fc2.com/]",
		  " ・（ﾌﾟﾛｸﾞﾗﾑ）ガストラゲタ",
		  "[http://gasutorageta.web.fc2.com/",
		  "index.html]",
		  "アプリHP(ﾀｲﾄﾙからもいけます)",
		  "[http://gasutorageta.web.fc2.com/",
		  "APLI/TOP/apTop.html]"
		 }
	 };
	 	 
	 public static String helpTitleStr[] =
	 {"物語",
	 "操作",
	 "ゲームの目的",
	 "ワンポイント",
	 "アイテム",
	 "お問い合わせ先",
	 "その他"}
 	 ;

	
	 

	 
	 
	 
	 public static void drawHelpStr(Graphics g,
			 int help)
	 {	
		 g.setAlpha( 255 );

		 //����
		 int x = MainData.SCREEN_WIDTH_DEF / 2 ;
		 int y = 60;
		 
		 //���
		 int arrowX = 20 -9 ;
		 int arrowX2 = MainData.SCREEN_WIDTH_DEF - 20;
		 int arrowY = y - 15;
		 //		 
		 //Bitmap afImg = g.setFlipMode( IconImage ,Graphics.FLIP_HORIZONTAL );			
		 //Library.TraseMsg("turn");	
		 int width = 9;
		 g.drawBitMap(IconImageTurn ,
				 arrowX ,
				 arrowY ,
			 //drawX
				 IconImageTurn.getWidth() - 128 - width,
			 //drawY
			 96 ,
			 width , 20 );
		 
		 //�݂����
		 g.drawBitMap( IconImage ,
				 arrowX2 ,
				 arrowY ,
				128, 96 ,
				9 , 20 );
		 
		 
		 g.setFontSize( Graphics.SIZE_SMALL );
		 
		 DrawString(g, helpTitleStr[help],
				 x, y, Library.COLOR_RGB(255, 255, 255),
				 Library.COLOR_RGB(255, 255, 255),
				 DRAW_TYPE_CENTER );
		 
		 g.setFontSize( Graphics.SIZE_TINY );
		 //������
		 int helpX = MainData.SCREEN_WIDTH_DEF / 2 ;
		 int helpY = 35 + y;
		 int helpSpace = 18;
		 for(int i = 0; i < helpStr[help].length; i++)
		 {
			 DrawString(g, helpStr[help][i],
				 helpX, helpY + helpSpace * i,
				 Library.COLOR_RGB(255, 255, 255),
				 Library.COLOR_RGB(255, 255, 255),
				 DRAW_TYPE_CENTER );
		 }
		 
	 }


	 
//	 public static void drawImageAngle(Graphics g, Image image,int x,int y,int th,int large,
//			 int drawx , int drawy , int width , int height )
//	 {
//		int []mn = new int[6];
//
//		float rad = 3.141592f * (360-th) / 180;        
//		int cos1 = (int)(Math.cos(rad) * large);
//		int sin1 = (int)(Math.sin(rad) * large);
//
//		int w1 = width/2;
//		int h1 = height/2;
//
//		mn[0] = (cos1);
//		mn[1] = (-sin1);
//		mn[2] = (cos1 * (-w1) - sin1 * (-h1) + x * 4096);
//		mn[3] = (sin1);
//		mn[4] = (cos1);
//		mn[5] = (sin1 * (-w1) + cos1 * (-h1) + y * 4096);
//
//		g.drawBitMap(image,mn,drawx,drawy,width,height);    
//	}
	 
	 
//	 public static void drawShopChange(Graphics g , int nowLank , int max)
//	 {
//		 g.setAlpha( 255 );
//
//		 drawShop( g , nowLank ,
//			MainData.SCREEN_WIDTH  / 2 ,
//			MainData.SCREEN_HEIGHT / 2 );
//		 
//		 int move = 0;
//		 
//		 long time = System.currentTimeMillis() % 1000;
//		 
//		 if(time >= 750)
//		 {
//			 move = 0;
//		 }else
//		 if(time >= 500)
//		 {
//			 move = 2;
//		 }else
//		 if(time >= 250)
//		 {
//			 move = 4;
//		 }else
//		 {
//			 move = 2;			 
//		 }
//		 
//		 //右矢印
//		 int ARROW_X = MainData.SCREEN_WIDTH - 20+move;
//		 int ARROW_Y = MainData.SCREEN_HEIGHT / 2;
//		 if(nowLank < max)
//		 {
//			
//			 g.drawBitMap(IconImage , ARROW_X, ARROW_Y,
//					 128,96,9,20);   
//		 }
//		 
//		 //左やじるし
//		 if(nowLank >= 1)
//		 {
////			 Bitmap afImg = g.setFlipMode( IconImage ,Graphics.FLIP_HORIZONTAL );
////				
//			 int ARROW_X2 =  11-move;
//			 
//			 //Bitmap afImg = g.setFlipMode( IconImage ,Graphics.FLIP_HORIZONTAL );
//			 int width = 9;
//			 g.drawBitMap(IconImageTurn , ARROW_X2, ARROW_Y,
//					 IconImageTurn.getWidth() - 128 - width,96,
//				 9,20);
//
////			 if(MainData.g_debug == 1)
////			 {
////				 g.drawBitMap(afImg , ARROW_X2, ARROW_Y,
////						 0,0,
////						 1000,1000);
////			 }
//		 }
//		 
//		 
//		 g.setFontSize( Graphics.SIZE_SMALL );
//		 DrawString(g, SHOP_NAME[nowLank],
//			 MainData.SCREEN_WIDTH / 2 ,
//			 MainData.SCREEN_HEIGHT/2 + 120,
//			 Library.COLOR_RGB(255, 255, 255),
//			 Library.COLOR_RGB(155, 155, 155),
//			 DRAW_TYPE_CENTER | DRAW_TYPE_DBUL );		 
//		 g.setFontSize( Graphics.SIZE_TINY );
//	 }

	 //炎
	 public static final int PUSH_ICON_FLAE = 1;
	 //ﾒﾆｭｰ
	 public static final int PUSH_ICON_MENU = 2;
	 //音量
	 public static final int PUSH_ICON_SOUND= 3;
	 //ホーム
	 public static final int PUSH_ICON_HOME= 4;

	 
	 
	 //アンドロイドアイコン
	 public static void drawPushIcon(Graphics g , int type,boolean toush)
	 {
		 if(toush)
		 {
			 g.setColor(Graphics.getColorOfRGB( 55, 55, 55, 255 ));
		 }
		 
		 if(type == PUSH_ICON_MENU)
		 {
			 if(toush)
			 {
				 
				 g.drawBitMap( IconImage ,
					Play.MENU_POINT_X,
					Play.MENU_POINT_Y,				
					224 , 128 , Play.MENU_POINT_W , Play.MENU_POINT_H);
			 }else
			 {
				 g.drawBitMap( IconImage ,
					Play.MENU_POINT_X,
					Play.MENU_POINT_Y,						
					224 , 96 , Play.MENU_POINT_W , Play.MENU_POINT_H);				 
			 }
			 
		 }
		 else
		 if(type == PUSH_ICON_SOUND)
		 {
			 int windowX = MainData.SCREEN_WIDTH_DEF -80;
			 int windowY = MainData.SCREEN_HEIGHT_DEF -112;
			 
			 int sound = Library.MAX_VAL - Library.getSound();
			 
			 if(toush)
			 {
				 g.drawBitMap( IconImage ,
						 windowX,
							windowY,				
					160 , 192 , 80 , 32);
			 }else
			 {
				 g.drawBitMap( IconImage ,
						windowX,
						windowY,				
						160 ,
						160 
						, 80 , 32);
			 }
			 
			 
			 
			 drawNum(g, windowX + 64  ,
					 windowY + 6  ,
					 sound, 2);
			 
			 
			 
		 }
	 
		 g.setColor(Graphics.getColorOfRGB( 255, 255, 255, 255 ));
	 }
	 
	 public static final int MENU_ICON_X = 390;
	 public static final int MENU_ICON_Y = 715;
	 public static final int MENU_ICON_W = 64;
	 public static final int MENU_ICON_H = 64;
	 
	 //アンドロイドアイコン
	 public static void drawPushMenu(Graphics g )
	 {
		 g.setColor(Graphics.getColorOfRGB( 255, 255, 255, 255 ));
		 g.drawBitMap( IconImage ,
				 MENU_ICON_X ,
				 MENU_ICON_Y ,				
			0 ,
			128 
			, MENU_ICON_W , MENU_ICON_H);
	 }
	 
	 
	 public static int logGraheCount = 10;
	 public static void drawTitleLogGrahe(Graphics g)
	 {
		 drawTitleLog( g );

		 int width = 288;
		 int height = 349;
		 g.drawBitMap( TitleGrahe ,
			(MainData.SCREEN_WIDTH_DEF - Library.getDecodeX(width))/2,
			MainData.SCREEN_HEIGHT_DEF - Library.getDecodeY(height) - Library.getDecodeY(100) + logGraheCount * Library.getDecodeY(30),
			0 , 0 , width , height);				 
		 if(logGraheCount >= 1)
		 {
			 logGraheCount--;			 
		 }
	 }
	 
	 
	 
}