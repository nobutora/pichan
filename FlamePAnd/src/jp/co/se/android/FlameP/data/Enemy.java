
package jp.co.se.android.FlameP.data;

//import Exception;

import java.io.InputStream;
import java.util.Random;

import jp.co.se.android.FlameP.Main.*;
import jp.co.se.android.FlameP.lib.Library;
import jp.co.se.android.FlameP.lib.ShopData;
import jp.co.se.android.FlameP.draw.*;
import jp.co.se.android.FlameP.Sound.*;
import jp.co.se.android.FlameP.scene.Play;


/**
 * MainCanvas
 * 
 */
public class Enemy
{
	int point_x = 0;
	int point_y = 0;
	
	boolean moveVact = false;
	
	boolean is_Life = false;
	boolean is_Missile = false;
	
	int anime = 0;
	
	int point = 0;
	int counter = 0;
	int enemy_type = 0;
	int enemy_draw_type = 0;

	boolean enemy_Bomb = false;
	int enemy_Width = 0;
	
	float enemy_draw_Big = 1.0f;
	
	int i_Life = 0;
	
	public static final int ENEMY_DRAW_SIZE = 64;
	
	
	//[enemy_type]
	public static final int ENEMY_CHIKIN 	= 0;
	public static final int ENEMY_CARASU 	= 1;
	public static final int ENEMY_FISH 		= 2;
	public static final int ENEMY_CHIKIN2 	= 3;
	public static final int ENEMY_CARASU2 	= 4;
	public static final int ENEMY_FISH2 	= 5;
	public static final int ENEMY_CHIKIN3 	= 6;	
	public static final int ENEMY_MISAIL 	= 7;
	public static final int ENEMY_AT_MISAIL = 8;
	public static final int ENEMY_JS_MISAIL = 9;
	
	
	public static final int ENEMY_CHIKIN_TITLE= 10;
	
	public static final int ENEMY_TYPE_MAX  = 11;
	
	public static final int ANIME_MAX[] = 
	{2,1,3,2,1,3,2,1,1,1};
	
	
	public static final int ENEMY_TYPE_LINE  = 9;
	
	
	int enemy_Condition = 0;
	//[enemy_Condition]
	public static final int CONDITION_NORMAL = 0;
	public static final int CONDITION_FLAME	 = 1;
	
	static final int FREE_MAX = 5;
	int[] free = new int [FREE_MAX];
	
	
	 public Enemy( )
	 {
		//this.init();
		//Library.TraseMsg("EnemyNew");
	 }
	 public void dispose()
	 {
		 this.release();
	 }
	 
	 
	 //
	 public void init()
	 {
		 this.enemy_type = 0;		 
		 this.point_y = 0;		 
		 this.anime = 0;
		 this.counter = 0;
		 this.enemy_Condition = 0;
		 
		 this.i_Life = 1;
		 free = null;
		 free = new int [FREE_MAX];
		 
		 this.enemy_draw_Big = 1.0f;
		 this.is_Missile = false;
	 }
	 
	 private void release()
	 {	 
	 }
	 
	 public void create( int type , int lv , int big)
	 {
		this.init();

		if(Library.random.nextInt() % 2 == 0)
		{
			this.moveVact = true;
			this.point_x = (int)((-Library.getDecodeX(30)- Library.random.nextInt(Library.getDecodeX(30))) *this.enemy_draw_Big);
		}else
		{
			this.moveVact = false;
			this.point_x = MainData.SCREEN_WIDTH_DEF + 
				(int)((Library.getDecodeX(30) + Library.random.nextInt(Library.getDecodeX(30)) )*this.enemy_draw_Big);
		}
		
		
		//Library.TraseMsg("create this.point_x"+this.point_x);
		
		this.enemy_type = type;	
		this.enemy_draw_type = this.enemy_type;
		//Library.TraseMsg("this.enemy_type"+this.enemy_type);
		
		switch (this.enemy_type) {
			case ENEMY_CHIKIN:
			case ENEMY_CHIKIN_TITLE:
			{
				this.point_y = Library.getDecodeY(500) - Library.random.nextInt(Library.getDecodeY(200));					
				this.i_Life = 2;
				 this.enemy_Width = 45;
				 this.enemy_draw_type = ENEMY_CHIKIN;
				 this.point = 2;
				break;
			}
			
			case ENEMY_CHIKIN2:
			case ENEMY_CHIKIN3:
			{
				this.point_y = Library.getDecodeY(400) - Library.random.nextInt(Library.getDecodeY(150));
				this.i_Life = 1;
				this.enemy_Width = 55;
				//this.enemy_draw_Big = 1.3f;
				if(this.enemy_type == ENEMY_CHIKIN2)
				{
					this.point = 30;
				}else
				if(this.enemy_type == ENEMY_CHIKIN3)
				{
					this.point = 300;
				}

				break;
			}
			
			case ENEMY_FISH:
			{
				this.point_y = Library.getDecodeY(440) - Library.random.nextInt(Library.getDecodeY(200));	
				this.free[0] = Library.random.nextInt(2);	
				this.i_Life = 3;
				this.enemy_Width = 55;
				this.point = 10;
				break;
			}
			
			case ENEMY_FISH2:
			{
				this.point_y = Library.getDecodeY(400) - Library.random.nextInt(Library.getDecodeY(250));	
				this.free[0] = Library.random.nextInt(2);				
				this.i_Life = 5;
				this.enemy_Width = 75;
				this.enemy_draw_Big = 1.5f;				
				this.moveVact = false;
				this.point = 100;
				break;
			}
			
			case ENEMY_CARASU:
			case ENEMY_CARASU2:
			{
				this.point_y = Library.getDecodeY(150) + Library.random.nextInt(Library.getDecodeY(100));	
				this.i_Life = 1;
				 this.enemy_Width = 40;
				 if(this.enemy_type == ENEMY_CHIKIN2)
				{
					this.point = 7;
				}else
				if(this.enemy_type == ENEMY_CHIKIN3)
				{
					this.point = 20;
				}
				break;
			}
			
			case ENEMY_MISAIL:
			{
				this.point_y = Library.getDecodeY(450) - Library.random.nextInt(Library.getDecodeY(250));
				this.i_Life = 1;
				this.is_Missile = true;
				 this.enemy_Width = 10;
				 this.point = 80;
				break;				
			}
			
			case ENEMY_AT_MISAIL:
			{
				this.point_y = Library.getDecodeY(450) - Library.random.nextInt(Library.getDecodeY(350));
				this.i_Life = 1;
				this.is_Missile = true;
				 this.enemy_Width = 25;
				 this.point = 120;
				break;								
			}
			
			case ENEMY_JS_MISAIL:
			{
				this.point_y = Library.getDecodeY(500) - Library.random.nextInt(Library.getDecodeY(400));
				this.enemy_draw_Big = 2.0f;
				
				this.moveVact = false;
				this.i_Life = 1;
				this.is_Missile = true;
				this.enemy_Width = 50;
				this.point = 300;
				break;												
			}
			
		default:
			break;
		}
		
		if(big >= 2)
		{
			int rand = Library.random.nextInt(big)+1;
			if(rand >= 1)
			{
				this.enemy_draw_Big = (float)(1.0f + rand * 0.1f);
				this.point += rand * 2;
				this.point = this.point + (int)(this.point * rand * 0.15f);			
				this.enemy_Width = this.enemy_Width + (int)(this.enemy_Width * rand * 0.1f);
				this.i_Life += big / 3;
			}
		}
		
		
		this.is_Life = true;
	 }
	 
	 public void desh()
	 {
		 if(this.is_Life)
		 {
			 this.is_Life = false;
		 }
	 }
	 
	 public int EnemyFlameHit(int power)
	 {
		 this.i_Life -= power;
		 
		 int exNum[] = new int [10];
		 exNum[8] = power ;
		 
		 if(i_Life <= 0)
		 {
			 exNum[8]++;
			 this.enemy_Condition = CONDITION_FLAME;
			 
			 this.counter = 0;
			 
			 
			 
			 Play.pEffect.createEffect(Effect.EFFECT_FLAME_HIT_NEW ,
				 this.point_x - ENEMY_DRAW_SIZE / 2,
				 this.point_y - ENEMY_DRAW_SIZE / 2, exNum );
			 
			 Library.SE_Play( se_Play.SOUND_SE_FLAME );
			 
			 return 1;
		 }else
		 {
			 Play.pEffect.createEffect(Effect.EFFECT_FLAME_HIT_NEW ,
				 this.point_x - ENEMY_DRAW_SIZE / 2,
				 this.point_y - ENEMY_DRAW_SIZE / 2,exNum);
		 }
		 return 0;
	 }
	 
	 
	 
	 public int move(int lv )
	 {		 
		 
		 switch (this.enemy_type) {

		 	case ENEMY_CHIKIN:
		 	case ENEMY_CHIKIN_TITLE:
		 	{
		 		return this.chikinAI( lv );
				//break;
		 	}
		 	case ENEMY_CHIKIN2:
		 	{
		 		return this.chikinAI( lv , 1);	 		
		 	}
		 	
		 	case ENEMY_CHIKIN3:
		 	{
		 		return this.chikinAI( lv , 2);
		 	}
		 	
			case ENEMY_FISH:
			{		
				return this.fishAI( lv );	
				//break;
			}
			case ENEMY_FISH2:
			{		
				return this.fishAI( lv , 1);	
				//break;
			}
			
			case ENEMY_CARASU:
			{
				return this.carasuAI( lv );
			}
			case ENEMY_CARASU2:
			{
				return this.carasuAI( lv , 1 );
			}
			
			
			case ENEMY_MISAIL:
			{
				return this.misailAI( lv , 0);				
			}
			
			case ENEMY_AT_MISAIL:
			{
				return this.misailAI( lv , 1);				
			}
			
			case ENEMY_JS_MISAIL:
			{
				
				return this.misailAI( lv , 2);
			}
			
			
		default:
			break;
		}
		 return 0;
	 }
	 
//	 public static int EnemyPoint(int type)
//	 {
//		 int point = 0;
//		 
//		 switch (type) {
//
//		 	case ENEMY_CHIKIN:
//		 	{
////		 		point += lv + 2;
//		 		point = 2;
//		 		break;
//		 	}
//		 	case ENEMY_CHIKIN_TITLE:
//		 	{
//		 		point = 0;
//		 		break;
//		 	}
//		 	
//			case ENEMY_CARASU:
//			{
////		 		point += lv * 2 + 5;
//		 		point = 10;
//		 		break;
//			}
//			case ENEMY_FISH:
//			{
////		 		point += lv * 3 + 5;			
//		 		point = 15;			
//
//		 		break;
//			}
//			
//			case ENEMY_CHIKIN2:
//			{
////		 		point += lv * 3 + 5;			
//		 		point = 30;			
//		 		break;
//			}
//			
//			case ENEMY_CHIKIN3:
//			{
//				point = 60;	
//				break;
//			}
//			
//			case ENEMY_CARASU2:
//			{
////		 		point += lv * 3 + 5;			
//		 		point = 30;			
//		 		break;
//			}
//			case ENEMY_FISH2:
//			{
////		 		point += lv * 3 + 5;			
//		 		point = 100;
//		 		break;
//			}
//
//			case ENEMY_MISAIL:
//			{
////		 		point += lv * 3 + 5;			
//		 		point = 100;
//		 		break;
//			}
//			case ENEMY_AT_MISAIL:
//			{
////		 		point += lv * 3 + 5;			
//		 		point = 150;
//		 		break;
//			}
//			
//			case ENEMY_JS_MISAIL:
//			{
////		 		point += lv * 3 + 5;			
//		 		point = 300;
//		 		break;
//			}
//		 }
//		 
//		 point = point ;
//		 
//		 return point;
//	 }
	 
	 private int ScreenOut()
	 {
		 if(this.moveVact)
		 {
			 if(this.point_x >= MainData.SCREEN_WIDTH_DEF + 60*this.enemy_draw_Big)
			 {
				 this.desh();
				 return -1;
			 }
		 }else{			 
			 if(this.point_x <= -60*this.enemy_draw_Big)
			 {
				 this.desh();
				 return -1;
			 }
		 }
		 return 0;
	 }
	 
	 private boolean moveShop()
	 {
		 int width = 20;
		 
		 int move = (int)((float)width * (float)MainData.Width_Size );
		 width = move;

		 
		 boolean xOK = false;
		 if(this.point_x > UI.SHOP_BASE_X + width)
		 {
			 this.point_x -= width;
		 }else
		 if(this.point_x < UI.SHOP_BASE_X - width)
		 {
			 this.point_x += width;
		 }else
		 {
			 xOK = true;
		 }
		 
		 boolean yOK = false;
		 if(this.point_y > UI.SHOP_BASE_Y + width)
		 {
			 this.point_y -= width;
		 }else
		 if(this.point_y < UI.SHOP_BASE_Y - width)
		 {
			 this.point_y += width;
		 }else
		 {
			 yOK = true;
		 }
		 
		 if(xOK && yOK)
		 {
			 if(this.enemy_type == ENEMY_CHIKIN3)
			 {
				 Library.SE_Play(se_Play.SOUND_SE_GOLD );				 
			 }else
			 {
				 if(this.enemy_draw_Big >= 1.5f)
				 {
					 Library.SE_Play(se_Play.SOUND_SE_EQUIP);
					 UI.setShopAnime(Library.getShop());
				 }else
				 {
					 Library.SE_Play(se_Play.SOUND_SE_GET);
				 }
			 }
			 return true;
		 }
		 
		 return false;
	 }
	 
	 
	 private boolean movePlayer()
	 {
		 int width = 25;
		 
		 int move = (int)((float)width * (float)MainData.Width_Size );
		 width = move;
		 
		 boolean xOK = false;
		 
		 int PlayerX = Player.PLAYER_DEF_X+10;
		 int PlayerY = Player.PLAYER_DEF_Y;
		 
		 
		 if(this.point_x > PlayerX + width)
		 {
			 this.point_x -= width;
		 }else
		 if(this.point_x < PlayerX - width)
		 {
			 this.point_x += width;
		 }else
		 {
			 xOK = true;
		 }
		 
		 boolean yOK = false;
		 if(this.point_y > PlayerY + width)
		 {
			 this.point_y -= width;
		 }else
		 if(this.point_y < PlayerY - width)
		 {
			 this.point_y += width;
		 }else
		 {
			 yOK = true;
		 }
		 
		 if(xOK && yOK)
		 {
			 Library.SE_Play(se_Play.SOUND_SE_EQUIP);
			 return true;
		 }
		 
		 return false;
	 }
	 
	 
	 
	 
	 private int chikinAI(int lv)
	 {
		 return chikinAI( lv , 0 );
	 }
	 
	 private int chikinAI(int lv , int type )
	 {
		
		 if(this.enemy_Condition == CONDITION_NORMAL)
		 {
			 int X_MOVE_MAX = 25;
			 
			 //int moveX = 5 + lv * 3 / 2;
			 
			 int move = 11;
			 int moveX = 0;
			 
			 if(type == 1 )
			 {
				 moveX = 20;
			 }else
			 if(type == 2 )
			 {
				 moveX = 6;
			 }				 
			 
			 moveX = move - (int)((this.enemy_draw_Big-1.0f) * 3);
			 
			 if(moveX <= 1)
			 {
				 moveX = 1;
			 }
			 
			 if(this.moveVact)
			 {
				 this.point_x+= moveX;				 
			 }else{
				 this.point_x-= moveX;
			 }
			 this.counter++;
			
			 if(this.counter % 2 == 0)
			 {
				this.anime++; 
				if(this.anime >= 2)
				{
					this.anime = 0;
				}
			 }
			 
			 return ScreenOut();
			 
		 }else if(this.enemy_Condition == CONDITION_FLAME)
		 {
			 //Library.TraseMsg("FlameDead");
			 this.counter++;
			 this.point_y++;
			 this.anime = 2;
			 if(this.counter >= 7)
			 {
				 if(type == 1)
				 {
					 if(movePlayer())
					 {
						 this.desh();	
						 
						 return 2;
					 }
				 }				 
				 else
				 if(moveShop())
				 {
					 this.desh();	
					 
					 return 1;
				 }
			 }
		 }
		 return 0;
	 }

	 private int misailAI(int lv , int type)
	 {
		 if(this.enemy_Bomb)
		 {
			 //”j—ô
			 missileBom();
		 }else
		 {
			 
			 if(this.enemy_Condition == CONDITION_NORMAL)
			 {
				 int moveX = 8;
				 
				 if(type == 1)
				 {
					 moveX = 16;				 
				 }
				 if(type == 2)
				 {
					 moveX = 15;				 
				 }
				 
				 moveX -= (int)((this.enemy_draw_Big-1.0f) * 4);
				 
				 if(moveX <= 1)
				 {
					 moveX = 1;
				 }
				 
				 if(this.moveVact)
				 {
					 this.point_x+= moveX;				 
				 }else{
					 this.point_x-= moveX;
				 }
				 this.counter++;
				
	//			 if(this.counter % 5 == 0)
	//			 {
	//				this.anime++; 
	//				if(this.anime >= 2)
	//				{
	//					this.anime = 0;
	//				}
	//			 }
				 
				 return ScreenOut();
				 
			 }
			 if(this.enemy_Condition == CONDITION_FLAME)
			 {
				 this.desh();
			 }
		 }
		 return 0;
	 }

	
	 private void missileBom()
	 {
		 Play.bombCount++;
		 if(Play.bombCount >= Play.BOMB_TIME + 15)
		 {
			 this.desh();
		 }
	 }
	 
	 
	 
	 
	 private int fishAI(int  lv)
	 {
		 return fishAI( lv , 1 );
	 }
	 
	 private int fishAI(int  lv , int type)
	 {
		 
		 if(this.enemy_Condition == CONDITION_NORMAL)
		 {
			 int X_MOVE_MAX = 35;
			 
			 //int moveX = 2 + lv  * 2;

			 int moveX = 20;

			 if(type == 1)
			 {
				 moveX = 10;
			 }
			 
			 moveX -= (int)((this.enemy_draw_Big-1.0f) * 5);
			 
			 if(moveX <= 1)
			 {
				 moveX = 1;
			 }
			 
			 
			 if(this.moveVact)
			 {
				 this.point_x+= moveX;				 
			 }else{
				 this.point_x-= moveX;
			 }
			 this.counter++;
			 
			 
			 int moveY = 14;
				

			 moveY -= (int)((this.enemy_draw_Big-1.0f) * 2);
			 
			 if(moveY <= 1)
			 {
				 moveY = 1;
			 }
			 
			 if(this.free[0] == 0)
			 {
				 this.point_y += moveY;
			 }else{
				 this.point_y -= moveY;				 
			 }
			 
			 if(this.counter % 4 == 0)
			 {
				 if(this.free[0] == 0)
				 {
					 this.free[0] = 1;
				 }else
				 {
					 this.free[0] = 0;
				 }
			 }
			 
			 
			 if(this.counter % 3 == 0)
			 {
				this.anime++; 
				if(this.anime >= 3)
				{
					this.anime = 0;
				}
			 }
			 
			 return ScreenOut();
			 
		 }else if(this.enemy_Condition == CONDITION_FLAME)
		 {
			 //Library.TraseMsg("FlameDead");
			 this.counter++;
			 this.point_y++;
			 this.anime = 3;
			 if(this.counter >= 7)
			 {
				 if(moveShop())
				 {
					 this.desh();			 
					 return 1;
				 }
			 }
		 }
		 return 0;
	 }
	 
	 private int carasuAI(int  lv)
	 {
		 return carasuAI( lv , 1 );	 
	 }
	 private int carasuAI(int  lv , int type )
	 {
		 
		// Library.TraseMsg("this.enemy_Condition"+this.enemy_Condition);
		 
		 
		 if(this.enemy_Condition == CONDITION_NORMAL)
		 {
			 
			 int Xmove = 10 ;
			 int Ymove = 5 ;
			 
			 if(type == 1)
			 {
				 Xmove = 30;
				 Ymove = 10;
			 }
			 
			 Xmove -= (int)((this.enemy_draw_Big-1.0f) * 3);
			 
			 if(Xmove <= 1)
			 {
				 Xmove = 1;
			 }
			 
			 Ymove -= (int)((this.enemy_draw_Big-1.0f) * 3);
			 
			 if(Ymove <= 1)
			 {
				 Ymove = 1;
			 }
			 
			 int move = (int)((float)Xmove * (float)MainData.Width_Size );
			 Xmove = move;
			 
			 move = (int)((float)Ymove * (float)MainData.Height_Size );
			 Ymove = move;

			 
			 this.point_y += Ymove;
			 if(this.moveVact)
			 {
				 this.point_x+= Xmove;				 
			 }else{
				 this.point_x-= Xmove;
			 }
			 			 
			// Library.TraseMsg("point_y"+point_y);
			// Library.TraseMsg("point_x"+point_x);
			 
			 this.counter++;
			 this.anime = 0;
			 
			 return ScreenOut();
		 }else if(this.enemy_Condition == CONDITION_FLAME)
		 {
			 //Library.TraseMsg("FlameDead");
			 this.counter++;
			 this.point_y++;
			 if(this.counter % 2 == 0)
			 {
				 this.anime = 1;
			 }else
			 {
				 this.anime = 2;
			 }
			 
			 if(this.counter >= 7)
			 {
				 if(moveShop())
				 {
					 this.desh();			 
					 return 1;
				 }
			 }
		 }
		 return 0;
	 }
	 
	 
	 public void draw(Graphics g)
	 {
		 if(MainData.DEBUG_MODE_DRAW)
		{
			UI.drawFillRect( g ,
					this.point_x - this.enemy_Width / 2,
					this.point_y - this.enemy_Width / 2,
					this.enemy_Width , this.enemy_Width ,
					 24 , 250, 30, 140);
		}
		 
		 if(this.enemy_Bomb)
		 {
			 //‰~ƒƒCƒ“
			 if(Play.bombCount >= Play.BOMB_TIME_START)
			 {
				 int alpha = 255;
				 if(Play.bombCount >= Play.BOMB_TIME)
				 {
					 alpha  -=(Play.bombCount - Play.BOMB_TIME) * 20;
				 }
				 if(alpha >= 1)
				 {
					 g.setAlpha(alpha);
					 g.setColor(Graphics.getColorOfRGB( 255, 255, 255, alpha ));
					 int size = (Play.bombCount - Play.BOMB_TIME_START) * 30;
					 g.drawCircle(this.point_x ,
							 this.point_y  ,
							 size );
				 }
			 }			
			 //‰~ƒTƒu
			 if(Play.bombCount <= Play.BOMB_TIME )
			 {
				 if(Play.bombCount % 4 == 0)
				 {
					 Play.pEffect.createEffect(Effect.EFFECT_BOBM_BLUST ,
							 this.point_x ,
							 this.point_y );
				 }
				 int alpha = 255;
				 if(Play.bombCount >= Play.BOMB_TIME_START)
				 {
					 alpha  -=(Play.bombCount - 4) * 20;
				 }
				 if(alpha >= 1)
				 {
					 g.setColor(Graphics.getColorOfRGB( 255, 255, 255, alpha ));
					 UI.drawEnemy(g, this.enemy_draw_type,
							 this.anime, 
							 this.point_x - ENEMY_DRAW_SIZE / 2,
							 this.point_y - ENEMY_DRAW_SIZE / 2,
							 this.moveVact , 
							 this.enemy_draw_Big + Play.bombCount * 0.10f);
				 }
			 }			
		 }else
		 {
			 UI.drawEnemy(g, this.enemy_draw_type,
					 this.anime, 
					 this.point_x - ENEMY_DRAW_SIZE / 2,
					 this.point_y - ENEMY_DRAW_SIZE / 2,
					 this.moveVact , this.enemy_draw_Big );
		 }
		 
		
	 
	 }
	 
	 public float getBig()
	 {
		 
		 return this.enemy_draw_Big;	 
	 }
	 
	 public boolean getLife()
	 {
		 
		 return this.is_Life;	 
	 }
	 
	 public boolean getNoLife()
	 {
		 if(this.is_Life == false)
		 {
			 return true;
		 }
		 if(this.enemy_Condition == CONDITION_FLAME)
		 {
			 return true;
		 }
		 
		 return false;		 
	 }
	 
	 public int GetWidth()
	 {		 
		 return this.enemy_Width;	 
	 }
	 
}