
package jp.co.se.android.FlameP.data;

//import Exception;

import java.io.InputStream;
import java.util.Random;

import jp.co.se.android.FlameP.lib.Library;
import jp.co.se.android.FlameP.lib.ShopData;
import jp.co.se.android.FlameP.draw.*;
import jp.co.se.android.FlameP.scene.Play;
import jp.co.se.android.FlameP.scene.Setting;



/**
 * MainCanvas
 * 
 */
public class Player
{
	public static final int PLAYER_DEF_X = GameAI.FLAME_X - 120;
	public static final int PLAYER_DEF_Y = GameAI.FLAME_Y;

	int point_x = PLAYER_DEF_X;
	int point_y = PLAYER_DEF_Y;
	
	//�ｽ�ｽ�ｽ�ｽ�ｽ�ｽ�ｽX�ｽe�ｽ[�ｽ^�ｽX========================
	int flameType = ShopData.FLAME_TYPE_NORMAL;

	int flameTypeBox[] = {ShopData.FLAME_TYPE_NORMAL , 
			ShopData.FLAME_TYPE_SMOAL};
	public static final int MAX_BOX = 2;
	int nowBoxLen = 0;
	
	//======================================
	
	
	
	//==============================================
	
	//int point_x = 3;
	//int point_y = 200;
	
	int counter = 0;
	//int attack_dray = 0;
	int Player_type = PLAYER_NORMAL;
	
	//int Scoa = 0;
	//int Chain = 0;
	
	private int i_PowerUpTime = 0;
	
	//[Player_type]
	public static final int PLAYER_NORMAL  = 0;
	public static final int PLAYER_ATTACK  = 1;
	public static final int PLAYER_DEAD	   = 2;
	public static final int PLAYER_POWERUP_DERAY = 3;
	public static final int PLAYER_POWERUP = 4;
	public static final int PLAYER_DEAD_2	   = 5;
	public static final int PLAYER_DEAD_3	   = 6;
	
	public static final int PLAYER_WIDTH = 128;
	public static final int PLAYER_HIGHT = 224;
	
	
	public static int MAX_PLAY_LIFE = 3;
	public int Play_Life = MAX_PLAY_LIFE;
	
	 public Player( )
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
		 this.Player_type = PLAYER_NORMAL;		 
		 this.counter = 0;
		 //this.Scoa  = 0;
		 this.Play_Life = MAX_PLAY_LIFE;
		 //this.Chain = 0;
		 
		 
		 i_PowerUpTime = 0;
		 
		 this.LongToush = 0;
	 }
	 
	 private void release()
	 {	 
	 }
	 
	 public static final int POWER_FLAME_X = PLAYER_DEF_X+75;
	 public static final int POWER_FLAME_Y = PLAYER_DEF_Y-30;
	 
	 
	 public void PlayerProc()
	 {
		 switch (this.Player_type) {
			case PLAYER_NORMAL:
			{
				break;
			}
			case PLAYER_ATTACK:
			{
				this.counter++;
				int type = this.flameType;
				if(Setting.getNowDemo())
				{
					type = Setting.getNowSelect();
				}
				
				if(this.counter >= ShopData.FLAME_DRAY[type])
				{
					this.Player_type = PLAYER_NORMAL;	
//					if(this.attack_dray == -1)
//					{
//						this.attack_dray = 0;						
//					}else{
//						this.attack_dray = 5;
//					}
				}
				break;
			}
			
			case PLAYER_POWERUP_DERAY:
			{
				this.counter++;
				if(this.counter >= 7)
				{
					this.Player_type = PLAYER_POWERUP;	
					this.counter = 0;
				}
				break;
			}
			
			case PLAYER_POWERUP:
			{
				this.counter++;
				
				//if(this.counter % 2 == 0)
				{
					Play.pEffect.createEffect( Effect.EFFECT_PI_FLAME_POWER , POWER_FLAME_X, POWER_FLAME_Y );
				}
				
				if(this.counter >= 16 )
				{
					this.Player_type = PLAYER_NORMAL;	
					//Play.pEffect.deleteEffectAll();
				}
				break;
			}
		 }
	 }
	 
	 
	 
	 
	 public void PlayerDraw(Graphics g)
	 {
		 switch (this.Player_type) {
			case PLAYER_NORMAL:
			{				
				this.drawNormal( g);
				break;
			}
			
			case PLAYER_ATTACK:
			{				
				this.drawAttack( g);
				break;
			}
			
			case PLAYER_DEAD:
			{
				this.drawDead( g);
				break;				
			}
			case PLAYER_DEAD_2:
			{
				this.drawDead_2( g);
				break;				
			}
			
			case PLAYER_POWERUP_DERAY:				
			{			
				this.drawPowerUpDray( g);
				break;
			}
			
			case PLAYER_POWERUP:
			{	
				this.drawPoWerUp( g);				
				break;
			}
			
			case PLAYER_DEAD_3:
			{
				this.drawBomShok( g);
				break;
			}
			
			default:
				break;
		}		 
	 }
	 
	 private void drawNormal(Graphics g)
	 {
		 UI.drawPlayer( g ,
				 UI.PLAY_ANIME_NORMAL ,
				 point_x ,  point_y );
	 }
	 
	 private void drawAttack(Graphics g)
	 {
		 UI.drawPlayer( g ,
				 UI.PLAY_ANIME_ATTACK ,
				 point_x ,  point_y );
	 }

	 private void drawDead(Graphics g)
	 {
		 
		 this.counter++;		 
		 UI.drawPlayer( g ,
			 UI.PLAY_ANIME_BOM1 ,
			 point_x ,  point_y );
	 }

	 private void drawDead_2(Graphics g)
	 {		 
		 UI.drawPlayer( g ,
			 UI.PLAY_ANIME_BOM2 ,
			 point_x ,  point_y );
	 }
	 
	 private void drawDead_3(Graphics g)
	 {		 
		 UI.drawPlayer( g ,
			 UI.PLAY_ANIME_BOM3 ,
			 point_x ,  point_y );
	 }
	 
	 private void drawPoWerUp(Graphics g)
	 {
		 UI.drawPlayer( g ,
				 UI.PLAY_ANIME_SUPER ,
				 point_x ,  point_y );
	 }

	 private void drawBomShok(Graphics g)
	 {
		 UI.drawDeadAnime(g , 
			point_x ,  point_y );	
	 }
	 
	 
	 private void drawPowerUpDray(Graphics g)
	 {
		 //とりあえずノーマル
//		 UI.drawPlayer( g ,
//			 UI.PLAY_ANIME_NORMAL ,
//			 point_x ,  point_y );
		 
		 UI.drawPlayerPowerUpDray (g ,
		 this.counter ,
		 point_x ,  point_y );
	 }
	 
	 
	 public void PlayerDrawStatus(Graphics g 
			 , int lv , int next )
	 {
//		 int SCOA_X = 69;
//		 UI.drawScoa( g ) ;
		 
//		 UI.drawNum(g, SCOA_X, SCOA_Y, this.Scoa, 1);
//
//		 int LV_X = 230;
//		 int LV_Y = 3;
//		 //LV
////		 UI.drawLv( g );
////		 UI.drawNum(g, LV_X, LV_Y, lv, 1);
////		 //NEXT
////		 UI.drawNext( g );
////		 UI.drawNum(g, LV_X, LV_Y + 12, next, 1);
////		 //LIFE
////		 UI.drawLife( g , this.Play_Life);
//		 //CHAIN
//		 UI.drawChain( g );
//		 UI.drawNum(g, 30,
//				 42 , this.Chain, 2);		 
		 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 public int LongToush = 0;
	 public void AttackLongToush(int type)
	 {
		 LongToush++;
		 
		 int gage = ShopData.FLAME_TOUCHE_LONG_MAX_GAGE[type] + 1;
		 
		 
		 
		 if(LongToush >= ShopData.FLAME_TOUCHE_LONG_MAX_TIME[type] * gage)
		 {
			 LongToush = ShopData.FLAME_TOUCHE_LONG_MAX_TIME[type] * gage;
		 }
		 
		 //this.Player_type = PLAYER_ATTACK;
		 this.counter = 0;
	 }
	 
	 public int getMaxGage(int type)
	 {
		 int gage = ShopData.FLAME_TOUCHE_LONG_MAX_GAGE[type] + 1;
		 
		 return ShopData.FLAME_TOUCHE_LONG_MAX_TIME[type] * gage;
	 }
	 
	 public void BlesuMainusTime()
	 {
		 if(this.Player_type == PLAYER_ATTACK)
		 {
			 this.Player_type = PLAYER_NORMAL;
		 }
		 LongToush-= 5;
		 if(LongToush <= 0)
		 {
			 LongToush  = 0;
		 }
	 }
	 public void InitLongToush()
	 {
		 this.LongToush = 0;
	 }
	 public int getLongToush()
	 {
		 return this.LongToush;
	 }
	 
	 
	 public boolean AttackOn()
	 {
		 
		 if( this.getPlayerCondition() == PLAYER_NORMAL )
		 {
			 //Library.TraseMsg("huxaiya");
			 this.Player_type = PLAYER_ATTACK;
			 this.counter = 0;
			 return true;
		 }

		 return false;
	 }
	 
	 public void SetDead()
	 {
		 this.Player_type = PLAYER_DEAD;
	 }
	 
	 public void SetDead2()
	 {
		 this.Player_type = PLAYER_DEAD_2;
	 }
	 
	 public void SetDead3()
	 {
		 
		 this.Player_type = PLAYER_DEAD_3;
	 }
	 
	 public void SetPowerUp()
	 {
		 this.Player_type = PLAYER_POWERUP_DERAY;
		 counter = 0;
	 }
	 
	 public void SetWepon(int type)
	 {
		 this.flameType = type;
	 }
	 
	 public void WeponTrade()
	 {
		 this.nowBoxLen++;
		 if(this.nowBoxLen >= MAX_BOX)
		 {
			 this.nowBoxLen = 0;
		 }
		 this.flameType = this.flameTypeBox[this.nowBoxLen];
	 }
	 
	 
	 public void drayCut()
	 {
	 }
	 
 
	 public void Lifedown()
	 {
		 //this.Play_Life--;
		 //this.ChainReset();
	 }
	 public void setPlayerCondition(int type)
	 {
		this.Player_type = type;
	 }
	 
	 public int getPlayerCondition()
	 {
		return this.Player_type;
	 }
	 public int getNowFlameType()
	 {
		return this.flameType;
	 }
	 
	 public int getNowFlameLen()
	 {
		return this.nowBoxLen;
	 }
	 
	 
	 public int getNowPower()
	 {
		return ShopData.FLAME_POWERS[this.flameType];
	 }
	 
	 
	 public int getLife()
	 {
		 return this.Play_Life;
	 }
	 
	 
	 
}


