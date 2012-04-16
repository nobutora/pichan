
package jp.co.se.android.FlameP.data;

import java.util.Random;

import jp.co.se.android.FlameP.Main.*;
import jp.co.se.android.FlameP.lib.Library;
import jp.co.se.android.FlameP.lib.ShopData;
import jp.co.se.android.FlameP.draw.*;
import jp.co.se.android.FlameP.scene.Play;
import jp.co.se.android.FlameP.data.Effect;



//
class EffectData 
{
	int point_x = 0;
	int point_y = 0;
	int power = 1;
	
	int hit_rarge = 0;
	
	//
	final int MAX_EX = 30;
	final int MAX_EX_NUM = 10;

	int exData[] = new int[MAX_EX];
	int exData2[] = new int[MAX_EX];
	
	int NewExData[][] = new int[MAX_EX_NUM][MAX_EX];
	
	
	int counter = 0;
	int effect_type = 0;
	
	boolean is_Life = false;
	//攻撃系
	boolean attackOK = false;
	//ミサイル無効
	boolean missile_sloo= false;
	//時間へラン無効
	boolean Time_Free= false;
	//時間へラン無効
	boolean Time_Free_harf= false;
	//ミサイル無効
	boolean enemy_sloo= false;

	//[effect_type]

	
	
	public EffectData( )
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
		 this.effect_type = 0;		 
		 this.point_y = 0;	
		 this.counter = 0;
		 this.power = 1;
		 for(int i = 0; i < MAX_EX; i++)
		 {
			 this.exData[i] = 0;
			 this.exData2[i] = 0;
		 }
		 
		 this.missile_sloo = false;
		 this.Time_Free = false;
		 this.Time_Free_harf = false;
		 this.enemy_sloo = false;
	 }
	 
	 private void release()
	 {	 
	 }
	 
	 public boolean moveEffect(GameAI pgame)
	 {
		 switch ( this.effect_type ) {
			case Effect.EFFECT_PI_FLAME:
			case Effect.EFFECT_PI_FLAME_LOW:
			case Effect.EFFECT_PI_FLAME_CAS:
			case Effect.EFFECT_PI_FLAME_CHARGE2:
			case Effect.EFFECT_PI_FLAME_CHARGE3:
			case Effect.EFFECT_PI_FLAME_HOMING:
			{
				boolean end = false;
				if(this.effect_type  == Effect.EFFECT_PI_FLAME_HOMING)
				{
					end = moveHoming(pgame);
				}else
				//
				if(this.effect_type == Effect.EFFECT_PI_FLAME_LOW)
				{
					this.point_x += (int)(10);
					this.point_y -= (int)(20);					
				}else
				if(this.effect_type == Effect.EFFECT_PI_FLAME)
				{
					this.point_x += (int)(16);
					this.point_y -= (int)(32);
				}else
				if(this.effect_type == Effect.EFFECT_PI_FLAME_CAS)
				{
					this.point_x += (int)((float)24 * (float)MainData.Width_Size);
					this.point_y -= (int)((float)48 * (float)MainData.Height_Size);	
				}else
			    if(this.effect_type == Effect.EFFECT_PI_FLAME_CHARGE2)
				{
					this.point_x += (int)((float)10 * (float)MainData.Width_Size);
					this.point_y -= (int)((float)17 * (float)MainData.Height_Size);	
				}else
			    if(this.effect_type == Effect.EFFECT_PI_FLAME_CHARGE3)
				{
					this.point_x += (int)((float)10 * (float)MainData.Width_Size);
					this.point_y -= (int)((float)17 * (float)MainData.Height_Size);	
				}
				
				this.counter++;
				
				
				
				if(this.effect_type == Effect.EFFECT_PI_FLAME_CAS&&
					this.point_y <= 100 )
				{
					end = true;
				}else				
				if(counter >= 45 ||
					this.point_y <= -50 )
				{
					end = true;
				}
				
				if(end)
				{
					this.is_Life = false;
					return true;
				}
				
				
				
				break;
			}
			
			case Effect.EFFECT_PI_FLAME_SMAL:
			{
				this.point_x += (int)((float)this.exData[0] * (float)MainData.Width_Size);
				
				this.point_y -= (int)((float)this.exData[1] * (float)MainData.Height_Size);
				
				this.counter++;
				
				
				if(counter >= 30)
				{
					this.is_Life = false;
					return true;
				}
				break;
			}
			case Effect.EFFECT_PI_FLAME_BLESE:
			{
				
				this.point_x += (int)((float)6+Library.random.nextInt(4) * (float)MainData.Width_Size);
				this.point_y -= (int)((float)10+Library.random.nextInt(5) * (float)MainData.Height_Size);	
				
				 

				counter++;
				
				this.hit_rarge = 5 + counter*3/2;
				if(counter >= 5 + this.exData[0] / 5)
				{
					this.is_Life = false;
					return true;
				}
				break;
			}
			
			case Effect.EFFECT_PI_FLAME_SNIPE:
			{
				
				this.point_x += (int)((float)this.exData[2] * (float)MainData.Width_Size);
				
				this.point_y += (int)((float)this.exData[3] * (float)MainData.Height_Size);
				
				this.counter++;
				if(counter >= 35)
				{
					this.is_Life = false;
					return true;
				}
				break;
			}
			
			case Effect.EFFECT_PI_FLAME_SNIPE_TARGET:
			{
				this.counter++;
				if(counter >= 7)
				{
					this.is_Life = false;
					return false;					
				}
				break;
			}
			
			case Effect.EFFECT_PI_FLAME_PARTICLE:
			{
				this.counter++;
				
//				if(counter <= 7)
//				{
//					this.point_x += (int)(this.exData[1]);
//					
//					
//				}else
				{				
					this.point_x -= (int)(Library.random.nextInt(10)-5);
				}
				
				this.point_y += (int)(this.exData[2]);
				this.exData[2]--;
				
				if(counter >= 24)
				{
					this.is_Life = false;
					return false;										
				}
				break;
			}
			
			case Effect.EFFECT_FLAME_HIT:
			{
				this.counter++;
				
				int randam = Library.random.nextInt(3);
				
				if(randam == 0)
				{
//					Play.pEffect.createEffect(Effect.EFFECT_PI_FLAME_PARTICLE ,
//						 this.point_x + Library.random.nextInt(60)+20,
//						 this.point_y + Library.random.nextInt(10)-30);
				}
				
				if(counter >= 9)
				{
					this.is_Life = false;					
				}
				break;
			}
			
			case Effect.EFFECT_FLAME_HIT_NEW:
			{
				acticon_Flame_Hit();				
				break;
			}
			case Effect.EFFECT_BOBM_BLUST:
			{
				acticon_Bobm_Blust();
				break;
			}
			
			case Effect.EFFECT_END:
			{
				this.counter++;	
				
				
				if(counter >= 30)
				{
					this.is_Life = false;					
				}
				break;
			}
			
			case Effect.EFFECT_POINT:
			{
				this.counter++;				
				if(counter >= 15)
				{
					this.is_Life = false;					
				}
				this.point_y --;
				break;
			}
			
			
			
			case Effect.EFFECT_PI_FLAME_POWER:
			{
				
				int move = 0;
				
				move = (int)(Library.random.nextInt(55)* (float)MainData.Width_Size) - 10;
				
				this.point_x += move;
				
				move = (int)((float)45 * (float)MainData.Height_Size) - 
				(int)(Library.random.nextInt(10)* (float)MainData.Height_Size);
				
				this.point_y -= move ;
				this.counter++;
				
				
				this.counter++;				
				if(counter >= 45)
				{
					this.is_Life = false;					
				}
				
				if(this.point_y <= -1)
				{
					this.is_Life = false;					
				}
				break;
			}
			
			default:
				break;
		}
		 
		 
		 
		 
		 return false;
	 }
	 
	 public boolean moveHoming(GameAI pgame)
	 {
		 if(this.counter <= 5)
		 {
			 this.exData[2] = 6;
	         this.exData[3] = -12;	
		 }else
		 //ホーミング設定
		 if(this.counter >= 6 && this.counter % 3  == 0 && this.counter <= 12)
		 {
		 	if( pgame.getEnemyPointNear(this.point_x , this.point_y))
			{
		 		double rot = 0;
		 		int targetX = pgame.getEnemyPointNearRetX();
		 		int targetY = pgame.getEnemyPointNearRetY();		 		

		 		rot = Library.getTargetAngle( 
		 			this.point_x , this.point_y ,
		 			targetX , targetY);		 		
		 		//移動量
		 		double mx = Library.getAngleMoveX( rot , 35 );
		 		double my = Library.getAngleMoveY( rot , 35 );
		 		
		 		this.exData[2] = (int)mx;
		 		this.exData[3] = (int)my;       
			}
		 }else
		 if(this.counter >= 25)
		 {
			 return true;
		 }
		 
		 {
			this.point_x += (int)(this.exData[2]);
			this.point_y += (int)(this.exData[3]);		
		 }
		 return false;
	 }
	 
	 
	 public void acticon_Flame_Hit()
	 {
		this.counter++;						
		
		boolean flag = false;
		for(int i = 0; i < hit_EfNum ; i ++)
		{
			//x
			this.NewExData[0][i] += Library.random.nextInt(6);
			//y
			this.NewExData[1][i] -= 3 + Library.random.nextInt(5) ;
			//a
			this.NewExData[2][i] -= 5 + Library.random.nextInt(35) ;
			//r
			//this.NewExData[3][i] -= Library.random.nextInt(8) ;
			//g
			this.NewExData[4][i] -= Library.random.nextInt(27) ;
			if(this.NewExData[4][i] <= 0)
			{
				this.NewExData[4][i] = 0;
			}
			//b
			this.NewExData[5][i] -= Library.random.nextInt(27) ;
			if(this.NewExData[5][i] <= 0)
			{
				this.NewExData[5][i] = 0;
			}
			//rarge
			this.NewExData[6][i] -= 5 + Library.random.nextInt(20) ;
			if(this.NewExData[6][i] <= 0)
			{
				this.NewExData[6][i] = 0;
			}
			
			if(this.NewExData[6][i] <= 0 ||
				this.NewExData[2][i] <= 0)
			{
				if(counter <= 5)
				{
					if(Library.random.nextInt(2) == 0)
					{
						//作り直し
						create_Flame_Point_Hit( i );
						flag = true;
					}
				}else
				{
				}
			}else
			{
				flag = true;
			}
		}
		if(flag == false)
		{
			this.is_Life = false;
		}		
	 }
	 
	 public void acticon_Bobm_Blust()
	 {
		 this.counter++;	
		 if(this.counter >= 20)
		 {
			 this.is_Life = false;
		 }
	 }
	 
	 public void create_Flame_Point_Hit(int i )
	 {
		 //x
		 this.NewExData[0][i] = this.point_x + Library.random.nextInt(30) - 15;
		 //y
		 this.NewExData[1][i] = this.point_y + Library.random.nextInt(20) - 10;		 
		 //a
		 this.NewExData[2][i] = 180 + Library.random.nextInt(75);
		 //r
		 this.NewExData[3][i] = 150 + Library.random.nextInt(100);
		 //g
		 this.NewExData[4][i] = 150 + Library.random.nextInt(50);
		 //b
		 this.NewExData[5][i] = 50 + Library.random.nextInt(40);
		 //rarge
		 this.NewExData[6][i] = (exData[8] * 20) + Library.random.nextInt(exData[8] * 20);		 
	 }
	 
	 
	 
	 
	 public void drawEffect(Graphics g)
	 {	
		 switch ( this.effect_type ) {
			case Effect.EFFECT_PI_FLAME:
			case Effect.EFFECT_PI_FLAME_LOW:				
			case Effect.EFFECT_PI_FLAME_CAS:
			case Effect.EFFECT_PI_FLAME_SMAL:
			case Effect.EFFECT_PI_FLAME_SNIPE:
			case Effect.EFFECT_PI_FLAME_BLESE:
			case Effect.EFFECT_PI_FLAME_CHARGE2:
			case Effect.EFFECT_PI_FLAME_CHARGE3:
			case Effect.EFFECT_PI_FLAME_HOMING:
			{
				
				int drawX = 0;
				int drawY = 0;
				int drawW = 0;
				int drawH = 0;
				
				int w = 0; 
				int h = 0;

				switch ( this.effect_type ) {
				
					case Effect.EFFECT_PI_FLAME_BLESE:
					{
						drawX = 0;
						if(Library.random.nextInt(2) == 0)
						{
							
						}else
						{
							drawX = 64;						
						}	
						drawW = 64;
						drawH = 96;
						
						w = drawW*(this.counter+8)/25; 
						h = drawH*(this.counter+8)/25;
						
						break;
					}
					
					case Effect.EFFECT_PI_FLAME:
					case Effect.EFFECT_PI_FLAME_LOW:
					case Effect.EFFECT_PI_FLAME_CAS:
					case Effect.EFFECT_PI_FLAME_SNIPE:
					case Effect.EFFECT_PI_FLAME_CHARGE2:
					case Effect.EFFECT_PI_FLAME_CHARGE3:
					case Effect.EFFECT_PI_FLAME_HOMING:					
					{
						drawX = 128;
						drawY = 0;
						drawW = 32;
						drawH = 32;
						w = drawW+5; 
						h = drawH+5;		
						
						if(this.effect_type == Effect.EFFECT_PI_FLAME||
							this.effect_type == Effect.EFFECT_PI_FLAME_HOMING)
						{
							w = 50 ;
							h = 50 ;
						}else
						if(this.effect_type == Effect.EFFECT_PI_FLAME_LOW )
						{
							w = 35 ;
							h = 35 ;
						}else
						if(this.effect_type == Effect.EFFECT_PI_FLAME_CHARGE3)
						{
							w = drawW+35;
							h = drawH+35;
						}else
						if(this.effect_type == Effect.EFFECT_PI_FLAME_CHARGE2)
						{
							w = drawW+35; 
							h = drawH+35;
						}else
						if(this.effect_type == Effect.EFFECT_PI_FLAME_CAS)
						{
							g.setColor(Graphics.getColorOfRGB( 255, 255, 55, 255 ));							
							w = 25 ;
							h = 25 ;
						}else
						if(this.effect_type == Effect.EFFECT_PI_FLAME_SNIPE)
						{
							g.setColor(Graphics.getColorOfRGB( 255, 255, 55, 255 ));							
							w = 65; 
							h = 65;
						}
						
						break;
					}
					case Effect.EFFECT_PI_FLAME_SMAL:
					{
						drawX = 159;
						drawY = 3;
						drawW = 13;
						drawH = 18;
						w = drawW; 
						h = drawH;
						break;
					}
				}
				g.setColor(Graphics.getColorOfRGB( 255, 255, 255, 255 ));
				UI.drawEffectPict( g ,
						this.point_x - w / 2 , this.point_y - h / 2,
						 w, h,
						 drawX, drawY, drawW, drawH );
				
				

				if(MainData.DEBUG_MODE_DRAW)
				{
					{
						UI.drawFillRect( g ,
							this.point_x - hit_rarge/2 ,
							this.point_y - hit_rarge/2 ,

							hit_rarge ,
							hit_rarge ,
							 20 , 20, 170, 150);
						
					}
				}
				
				break;
			}
			
			case Effect.EFFECT_PI_FLAME_SNIPE_TARGET:
			{
				UI.drawEffectPict(g, 
					this.point_x  - 64 / 2, this.point_y ,
					180 + this.counter % 2 * 64, 7,
					64, 64);	
				break;
			}
			
			case Effect.EFFECT_PI_FLAME_PARTICLE:
			{
				int alphe = 250;
				if(counter%2 == 0)
				{
					alphe = 110;
				}
				if(this.exData[0] == 0)
				{
					UI.drawFillRect(g, this.point_x, this.point_y, 3, 3,
							84, 84,84, alphe);					
				}else
				{				
					UI.drawFillRect(g, this.point_x, this.point_y, 3, 3,
							255, 0,0, alphe);
				}
				
				break;			
			}
			
			case Effect.EFFECT_FLAME_HIT:
			{
				UI.drawEffectPict(g, 
					this.point_x , this.point_y - 96 / 2,
					this.counter % 2 * 64, 0, 64, 96);	
				break;
			}
			
			case Effect.EFFECT_FLAME_HIT_NEW:
			{
				drawFlameHit(g);				
				break;
			}
			
			case Effect.EFFECT_BOBM_BLUST:
			{
				drawBobmBlst(g);	
				break;
			}
			
			case Effect.EFFECT_END:
			{
				UI.drawEffectPict(g, 
					this.point_x , this.point_y ,
					0, 96, 165, 35);	
				break;
			}
			
			case Effect.EFFECT_POINT:
			{
				UI.drawNum(g, this.point_x , this.point_y, this.exData[0], 1);				
				break;
			}			
			
			case Effect.EFFECT_PI_FLAME_POWER:
			{
				if(MainData.DEBUG_MODE_DRAW)
				{
					for(int i = 0; i < GameAI.POWER_FLAME_MAX ; i++)
					{
						UI.drawFillRect( g ,
							GameAI.POWER_FLAME_X[i] - GameAI.POWER_FLAME_W[i] ,
							GameAI.POWER_FLAME_Y[i] - GameAI.POWER_FLAME_W[i] ,
							GameAI.POWER_FLAME_W[i] * 2,
							GameAI.POWER_FLAME_W[i] * 2,
							200 , 50, 50, 100);					
					}
				}
				
				
				
				//�ｽ�ｽ
//				int max = 5;
//				
//				int countMax = 4;
//
//				
//				int nowCounter = counter * max + max;
//				
//				if(counter >= countMax)
//				{
//					nowCounter = countMax * max + max;					
//				}			
//				
//				for(int i = 0 ; i < nowCounter -1; i++)
//				{
//					if(counter * max -  (5 * max) >= i)
//					{
//						continue;
//					}
//					
//					if(this.exData[i] == 0)
//					{
//						continue;
//					}
//					
//					
//					//Library.TraseMsg("i"+i);
//					int randx = Library.random.nextInt(4+i*2) - (2+i);
//					int randy = Library.random.nextInt(4+i*2) - (2+i);
//					
//					if(randy <= 0)
//					{
//						continue;
//					}
//					
//					if(Library.random.nextInt(2) == 0)
//					{
//						UI.drawEffectPict(g,
//							this.exData[i] + randx, this.exData2[i] + randy,
//							0, 0, 64, 96);
//					}else
//					{
//						UI.drawEffectPict(g, 
//							this.exData[i] + randx, this.exData2[i] + randy,
//							64, 0, 64, 96);						
//					}					
//				}		
				int drawx = 0;
				
				if(Library.random.nextInt(2) == 0)
				{
					
				}else
				{
					drawx = 64;						
				}	
				int w = 64 / 2 + this.counter * 6;
				int h = 96 / 2 + this.counter * 6;
				
				if(this.counter >= 40)
				{
					int alphe = 255 - (this.counter-39) * 30;
					g.setColor(Graphics.getColorOfRGB( 255, 255, 255, alphe ));
				}else
				{				
					g.setColor(Graphics.getColorOfRGB( 255, 255, 255, 255 ));
				}
				UI.drawEffectPict(g,
						this.point_x, this.point_y,
						w,h,
						drawx, 0, 64, 96);
				g.setColor(Graphics.getColorOfRGB( 255, 255, 255, 255 ));
				break;
			}
			
			default:
				break;
		}
		 
	 }
	 
	 private static final int hit_EfNum = 8;
	 public void drawFlameHit(Graphics g)
	 {
		 for(int i = 0 ; i < hit_EfNum; i ++)
		 {
			if(this.NewExData[6][i] <= 0 ||
				this.NewExData[2][i] <= 0)
			{
				continue;
			}
			int x = this.NewExData[0][i];
			int y = this.NewExData[1][i];
			int a = this.NewExData[2][i];
			int r = this.NewExData[3][i];
			int gr= this.NewExData[4][i];
			int b = this.NewExData[5][i];
			int rarge = this.NewExData[6][i];
			
			g.setColor(Graphics.getColorOfRGB( r, gr, b, a ));
			UI.drawEffectPict(g,
					x, y,
					rarge,rarge,
					0+Library.random.nextInt(2)*64, 0,
					64, 96);
		 }
	 }
	 
	 public void drawBobmBlst(Graphics g)
	 {
		 int alpha = 40 - (this.counter * 2);
		 
		 
		 g.setAlpha(alpha);
		 g.setColor(Graphics.getColorOfRGB( 255, 255, 255, alpha ));
		 int size = this.counter * 60;
		 g.drawCircle(this.point_x ,
				 this.point_y  ,
				 size );
	 }
	 
	 public void create(int type , int x , int y)
	 {
		 create( type , x , y, null);
	 }
	 public void create(int type , int x , int y,int num[])
	 {
		 this.init();
		 
		 this.effect_type = type;		 
		 this.point_y = y;	
		 this.point_x = x;	
		 this.counter = 0;	
		 this.is_Life = true;
		 this.exData = new int[MAX_EX];
		 
		 this.NewExData = new int[MAX_EX_NUM][MAX_EX];
		 
		 if(num != null)
		 {
			 for(int i = 0; i < num.length; i++)
			 {
				 this.exData[i] = num[i];
			 }
		 }
		 
		 
		 if(type == Effect.EFFECT_PI_FLAME ||
			type == Effect.EFFECT_PI_FLAME_LOW ||	 
		    type == Effect.EFFECT_PI_FLAME_CAS||
			 type == Effect.EFFECT_PI_FLAME_SMAL||
			 type == Effect.EFFECT_PI_FLAME_SNIPE||
			 type == Effect.EFFECT_PI_FLAME_BLESE||
			 type == Effect.EFFECT_PI_FLAME_CHARGE2||
			 type == Effect.EFFECT_PI_FLAME_CHARGE3||
			 type == Effect.EFFECT_PI_FLAME_HOMING
		 )
		 {
			this.attackOK = true;			
		 }else
		 {
			this.attackOK = false;
		 }
		 
		 if(type == Effect.EFFECT_PI_FLAME_BLESE)
		 {
			 if(this.exData[0] >= ShopData.FLAME_TOUCHE_LONG_MAX_TIME[ShopData.FLAME_TYPE_FLAME])
			 {
				 this.exData[0] = ShopData.FLAME_TOUCHE_LONG_MAX_TIME[ShopData.FLAME_TYPE_FLAME];
			 }
			 
			 this.point_y += Library.random.nextInt(40)-20;	
			 this.point_x += Library.random.nextInt(40)-20;	
			 
			 
		 }else		 
		 if(type == Effect.EFFECT_PI_FLAME_SMAL)
		 {
			 this.point_y += Library.random.nextInt(40)-20;	
			 this.point_x += Library.random.nextInt(40)-20;	
			 this.exData[0] = Library.random.nextInt(17);
			 this.exData[1] = 9 - Library.random.nextInt(2);
		 }else
		 if(type == Effect.EFFECT_PI_FLAME_SNIPE)
		 {			 
			 //方向決める
			 double rot = Library.getTargetAngle( 
	 			this.point_x , this.point_y ,
	 			num[0] , num[1]);		 
			 
			 double mx = Library.getAngleMoveX( rot , 45 );
		 	 double my = Library.getAngleMoveY( rot , 45 );
			 
			 this.exData[2] = (int)mx; 
			 this.exData[3] = (int)my; 			 
		 }
		 
		 this.flameStatus(type);
		 
		 
		 
		 if(type == Effect.EFFECT_FLAME_HIT)
		 {
			 //this.point_y -= 30;			 
		 }else
		 if(type == Effect.EFFECT_PI_FLAME_PARTICLE)
		 {
			 if(Library.random.nextInt() % 2 == 0)
			 {
				 this.exData[0] = 0;
			 }else
			 {
				 this.exData[0] = 1;
			 }
			 this.exData[1] = Library.random.nextInt() % 20-10;			 
			 this.exData[2] = Library.random.nextInt() % 20-5;			 
			 
		 }
		 
	 }
	 
	 private void flameStatus(int type)
	 {
		 this.missile_sloo = false;
		 this.Time_Free = false;
		 this.Time_Free_harf = false;
		 this.enemy_sloo = false;
		 
		 int FlameStatusType = 0;
		 switch (type) {
			case Effect.EFFECT_PI_FLAME:
			{
				FlameStatusType = ShopData.FLAME_TYPE_NORMAL;
				break;
			}
			case Effect.EFFECT_PI_FLAME_LOW:
			{
				FlameStatusType = ShopData.FLAME_TYPE_LOW;
				break;
			}
			case Effect.EFFECT_PI_FLAME_CAS:
			{
				FlameStatusType = ShopData.FLAME_TYPE_CASTAM;
				this.counter = 10;
				break;
			}
			case Effect.EFFECT_PI_FLAME_SMAL:
			{
				FlameStatusType = ShopData.FLAME_TYPE_SMOAL;
				break;
			}
			case Effect.EFFECT_PI_FLAME_SNIPE:
			{
				FlameStatusType = ShopData.FLAME_TYPE_SNIPING;				
				break;
			}
			case Effect.EFFECT_PI_FLAME_HOMING:
			{
				FlameStatusType = ShopData.FLAME_TYPE_HOMING;	
				break;
			}
			case Effect.EFFECT_PI_FLAME_BLESE:
			{
				FlameStatusType = ShopData.FLAME_TYPE_FLAME;				
				break;
			}
			case Effect.EFFECT_PI_FLAME_CHARGE2:
			{
				FlameStatusType = ShopData.FLAME_TYPE_CHARGE_2;				
				break;
			}
			case Effect.EFFECT_PI_FLAME_CHARGE3:
			{
				FlameStatusType = ShopData.FLAME_TYPE_CHARGE_3;				
				break;
			}
		default:
			break;
		}
		 
		this.hit_rarge = ShopData.FLAME_RARGE[FlameStatusType];
		this.power = ShopData.FLAME_POWERS[FlameStatusType];
		this.missile_sloo = ShopData.FLAME_MISSILE_SLOO[FlameStatusType];
		this.Time_Free = ShopData.FLAME_TIME_SLOO[FlameStatusType];
		this.Time_Free_harf = ShopData.FLAME_TIME_SLOOHALF[FlameStatusType];

		
		this.enemy_sloo = ShopData.FLAME_ENEMY_SLOO[FlameStatusType]; 
	 }
	 
}


/**
 * Effect
 * 
 */
public class Effect
{
	
	private EffectData[]	data;
	
	public final static int EFFECT_MAX = 30;
	
	
	/** ノーマル炎 */
	public static final int EFFECT_PI_FLAME = 0;
	/** 炎あたったエフェクト */
	public static final int EFFECT_FLAME_HIT= 1;
	/**  */
	public static final int EFFECT_POINT	= 2;
	/**  */
	public static final int EFFECT_END		= 3;
	/** */
	public static final int EFFECT_PI_FLAME_POWER = 4;
	
	public static final int EFFECT_PI_FLAME_SMAL = 5;

	public static final int EFFECT_PI_FLAME_CAS = 6;

	public static final int EFFECT_PI_FLAME_SNIPE = 7;

	public static final int EFFECT_PI_FLAME_SNIPE_TARGET = 8;

	//炎火の粉エフェクト
	public static final int EFFECT_PI_FLAME_PARTICLE = 9;
	
	public static final int EFFECT_PI_FLAME_BLESE = 10;

	//チャージLV2
	public static final int EFFECT_PI_FLAME_CHARGE2 = 11;
	
	//チャージLV3
	public static final int EFFECT_PI_FLAME_CHARGE3 = 12;
	
	
	/** 炎あたったエフェクト新型 */
	public static final int EFFECT_FLAME_HIT_NEW = 13;

	//劣化炎
	public static final int EFFECT_PI_FLAME_LOW = 14;	
	/** 爆発衝撃 */
	public static final int EFFECT_BOBM_BLUST	= 15;
	
	//ホーミング
	public static final int EFFECT_PI_FLAME_HOMING = 16;

	
	 public Effect( )
	 {
		this.init();
		//Library.TraseMsg("EnemyNew");
	 }
	 public void dispose()
	 {
		 this.release();
	 }
	 
	 
	 //
	 public void init()
	 {
		 this.data = new EffectData[ EFFECT_MAX ];
		 
		 for(int i=0; i < EFFECT_MAX ; i++){		 
			 this.data[ i ] = new EffectData();
		 }
	 }
	 
	 private void release()
	 {	 
	 }
	 

	 public int procEffect(GameAI pgame)
	 {		 
		 int flameNOhit = 0;
		 for(int i=0; i < EFFECT_MAX ; i++){		 
			if( this.data[i].is_Life)
			{
				boolean ok = this.data[i].moveEffect(pgame);
				
				if(this.data[i].Time_Free)
				{
					continue;
				}
				
				if(ok)
				{
					if(this.data[i].Time_Free_harf)
					{
						flameNOhit = 2;
					}else
					{
						flameNOhit = 1;
					}
				}
			}
		 }
		 
		 return flameNOhit;
	 }

	 public void drawEffectAll(Graphics g)
	 {		 
		 for(int i=0; i < EFFECT_MAX ; i++){		 
			if( this.data[i].is_Life)
			{
				this.data[i].drawEffect(g);
			}
		 }
	 }	 
	 
	 public void createEffect( int type , int x , int y)
	 {
		 createEffect( type , x , y , null );
	 }
	 
	 public void createEffect( int type , int x , int y , int exNum[])
	 {
		 for(int i=0; i < EFFECT_MAX ; i++){		 
			if( this.data[i].is_Life == false)
			{
				this.data[i].create(type , x , y , exNum);				
				break;
			}
		 }
	 } 
	 
	 public void deleteEffect(  int exNum)
	 {		 
		 this.data[exNum].is_Life = false;
	 }
	 
	 public void deleteEffectAll()
	 {		 
		 for(int i = 0 ; i < EFFECT_MAX ; i++)
		 {
			 this.data[i].is_Life = false;
		 }
	 }
	 
	 //
	 public EffectData[] getFlameDatas()
	 {
			

		 int idx = 0;
		 
		 EffectData Flames[] = new EffectData[EFFECT_MAX];
		 
		 for(int i = 0 ; i < EFFECT_MAX; i++)
		 {
			 Flames[i] = null;
		 }
		 
		 for(int i=0; i < EFFECT_MAX ; i++){		 
			if( this.data[i].is_Life &&
				this.data[i].attackOK)
			{
				Flames[idx] = this.data[i];
				idx++;
			}
		 }
		 return Flames;
	 }
	
	 
	 public int[] getFlameNum()
	 {
		 int idx = 0;
		 
		 int Flames[] = new int[EFFECT_MAX];
		 
		 for(int i = 0 ; i < EFFECT_MAX; i++)
		 {
			 Flames[i] = -1;
		 }
		 
		 for(int i=0; i < EFFECT_MAX ; i++){		 
			if( this.data[i].is_Life  &&
				this.data[i].attackOK)
			{
				Flames[idx] = i;
				idx++;
			}
		 }
		 return Flames;
	 }
	 
	 public boolean isEffectTypeLife(int type)
	 {
		 
		 for(int i=0; i < EFFECT_MAX ; i++){		 
			if( this.data[i].is_Life &&
				this.data[i].effect_type == type )
			{
				return true;
			}
		 }
		 
		 return false;
	 }
	 
}