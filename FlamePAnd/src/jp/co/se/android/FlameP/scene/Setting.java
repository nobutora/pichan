
package jp.co.se.android.FlameP.scene;

//import Exception;




import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.MotionEvent;
import jp.co.se.android.FlameP.Main.*;
import jp.co.se.android.FlameP.lib.DataSave;
import jp.co.se.android.FlameP.lib.Library;
import jp.co.se.android.FlameP.lib.ShopData;

import jp.co.se.android.FlameP.activity.ConstantClass;
import jp.co.se.android.FlameP.activity.ItemSelect;
import jp.co.se.android.FlameP.activity.bgmSelect;
import jp.co.se.android.FlameP.activity.bgmSelect.ListData;
import jp.co.se.android.FlameP.draw.*;

import jp.co.se.android.FlameP.Sound.*;


/**
 * MainCanvas
 * セッティング画面の描画 操作　クラス
 */
public class Setting
{
	
	private  static int nowView = 0;
	
	private static int drawWeponKind[] = new int[ShopData.FLAME_TYPE_MAX] ;

	private static int kindNum = 0;
	
	private static int nowtoush = 0;
	
	private static boolean SettingDemo = false;
	
	
	
	public static final int MAX_DRAW_KIND = 7;
	
	private static final int BASE_Y_POINT = 400;
	
	private static final int IconBaseX = 64; 		 
	private static final int IconBaseY = Setting.BASE_Y_POINT + 64; 
	public static final int Icon_W = 96;
	public static final int Icon_H = 75;

	public static final int Icon_Ws[] = {96,96,96,96,96,96,126};
	public static final int Icon_Hs[] = {75,75,75,75,75,75,75};
	
	public static final int IconX[] = {
		0+IconBaseX,
		128+IconBaseX,
		256+IconBaseX,
		0+IconBaseX,
		128+IconBaseX,
		256+IconBaseX,
		//Back
		3
	};
	
	public static final int ICON_PICT_SHOP_BASE = 9;
	
	
	public static final int IconY[] = {
		IconBaseY,
		IconBaseY,
		IconBaseY,
		IconBaseY+96,
		IconBaseY+96,
		IconBaseY+96,
		691		
	};
	public static final int IconCenterX = 128+IconBaseX;
	public static final int IconCenterY = IconBaseY + 64;

	
	private static boolean nowWindow = false;
	
	
	static private  MainData pAm;
	
	public static void setMain( MainData main )
	{
		pAm = main;
	}
	
	public void resume()
	{
		 //Library.BGM_RESUME();
	}
	 
	 private static Handler handler;
	 
	 public Setting(Handler h)
	 {
		this.init();
		handler = h;
	 }
	 public void dispose()
	 {
		 this.release();
	 }
	 
	 
	 private void init()
	 {
			
		 nowView = 0;
			
		 drawWeponKind = new int[ShopData.FLAME_TYPE_MAX] ;

		 kindNum = 0;
			
	 }
	 private void release()
	 {	 
	 }
	 
	 public static void setDrawList(Activity act,int type)
	 {
		 setDrawList( act , type , false );		 
	 }
	 
	 public static void setDrawList( Activity act, int type , boolean first )
	 {
		 try
		 {
			 SettingDemo = true;
			 nowView = type;
			 nowtoush = 0;
			 
			 
			 if( nowView == ConstantClass.ITEM_VIEW_SKILL)
	    	 {
				 for(int i= 0; i < drawWeponKind.length; i++)
				 {
					 if(Library.nowEquip() == drawWeponKind[i])
					 {
						 nowtoush = i;
						 break;
					 }
				 }
	    	 }
			 
			 Setting.kindNum = 0;
			 
			 
			 int max = 0;
			 int firstNum = 0;
			 
			 Setting.drawWeponKind = new int[ShopData.FLAME_TYPE_MAX] ;
			 
			 if( nowView == ConstantClass.ITEM_VIEW_SHOP ||	
				 nowView == ConstantClass.ITEM_VIEW_SKILL)
	    	 {
				 max = ShopData.FLAME_TYPE_MAX ;
				 firstNum = 1;
	    	 }else
			 if(  nowView == ConstantClass.ITEM_VIEW_BASE_SET)
	    	 {   
				 max = Library.nowLank()+1 ;
	    	 }else
	    	 if(nowView == ConstantClass.ITEM_VIEW_BASE_SHOP)
	    	 {
				 max = 1 ;
			 }
			 
			 for(int i = firstNum ; i < max ; i ++ )
	         {
				if( nowView == ConstantClass.ITEM_VIEW_BASE_SET)
				{
					Setting.drawWeponKind[Setting.kindNum] = i;				
				}else
				if( nowView == ConstantClass.ITEM_VIEW_BASE_SHOP)
				{
					if( Library.nowLank() >= ShopData.BASE_TYPE_MAX-1)
					{
						break;
					}
					Setting.drawWeponKind[Setting.kindNum] = Library.nowLank()+1;
				}else			 
				//装備
		    	if( nowView == ConstantClass.ITEM_VIEW_SHOP)
		    	{
			    	if(ShopData.FLAME_FREE[i])
					{
			    		continue;
					}
			    	if(ShopData.FLAME_NO_SHOPING[i])
					{
						continue;
					}
					if(Library.nowLank() < ShopData.FLAME_RANK[i] )
					{
						continue;				 
					}
					
					if(DataSave.getWeponOk(i))
					{
						continue;
					}
					Setting.drawWeponKind[Setting.kindNum] = i;
		    	}else
	    		if(nowView == ConstantClass.ITEM_VIEW_SKILL)
		    	{
	    			if(ShopData.FLAME_FREE[i])
					{
					}else
	    			if(DataSave.getWeponOk(i)==false)
					{
	    				if(ShopData.FLAME_FREE[i] == false)
	    				{
	    		    		continue;
	    				}
					}
	    			Setting.drawWeponKind[Setting.kindNum] = i;
		    	}
	
		    	
		    	Setting.kindNum++;
	        }
			 
			 
			 nowtoush = 0;
			 
			 if(first)
			 {
				 if(nowView == ConstantClass.ITEM_VIEW_SKILL)
			     {
					 for(int i = 0 ; i < ShopData.FLAME_TYPE_MAX ; i ++ )
			         {
						 if(Setting.drawWeponKind[i] == Library.nowEquip())
						 {
							 nowtoush = i;
							 break;
						 }
			         }
			     }
				 if(nowView == ConstantClass.ITEM_VIEW_BASE_SET)
			     {
					 nowtoush = Library.getShop();
			     }
			 }
		 }catch (Exception e) {
			// TODO: handle exception
			 Library.TraseMsg("setDrawList err" + e.toString());
			 
			 Library.showDebugDialog(act, "setDrawList err"+e.toString());
		}
		 
	 }
	 
	 
	 
	 
	 
	 
	 public static boolean procSetting( Play play , Activity act , int downX , int downY, 
			 int downKey,int toush , boolean toushUp)
	 {
		 if(buyOkTimer >= 1)
		 {
			 buyOkTimer--;
		 }
		 int err = 0;
		 //try
		 {
			 boolean back = false;
			 
			 if(nowView == ConstantClass.ITEM_VIEW_BASE_SHOP ||
					 nowView == ConstantClass.ITEM_VIEW_BASE_SET )
			 {
				 
			 }else
			 {
				 play.PlayProc();
			 }
			 err = 1;
			 //if(toushUp)
			 {
				 if(toush == MAX_DRAW_KIND - 1)
				 {
					 back = true;
					 err = 11;
				 }else
				 if( toush >= 0 &&
						 toush < kindNum)
				 {
					 err = 12;
					 //押したときの処理
					 //if(toushUp)
					 {
						 err = 13;
						 if( nowView == ConstantClass.ITEM_VIEW_SHOP)
				    	 {
							//購入
							buyOk(Setting.drawWeponKind[toush] , act);
						 }else
						 if( nowView == ConstantClass.ITEM_VIEW_SKILL)
				    	 {
							//装備
							Library.SE_Play( se_Play.SOUND_SE_EQUIP );
							Equip(Setting.drawWeponKind[toush]);
				    	 }else
						 if( nowView == ConstantClass.ITEM_VIEW_BASE_SHOP)
				    	 {
							//お店購入
							
							buyOk( Setting.drawWeponKind[toush] , act);
				    	 }else
						 if( nowView == ConstantClass.ITEM_VIEW_BASE_SET)
				    	 {
							//お店　切り替え
							Library.SE_Play( se_Play.SOUND_SE_EQUIP );
							Library.setShop(Setting.drawWeponKind[toush]);
							Library.SaveDataBase(pAm);
				    	 }
						 err = 14;
					 }
					 err = 15;
					 //if(drawWeponKind[toush] != 0)
					 {
						 nowtoush = toush;
					 }
					 err = 16;
				 }
			 }
			 err = 2;
			 
			 if(downKey == MainData.KEY_BACK ||
				 back)
			 {
				 SettingDemo = false;
				 nowWindow = false;
				 Library.SE_Play( se_Play.SOUND_SE_CANCEL );
				 return true;
			 }
			 err = 3;
		 }
//		 catch (Exception e) {
//			// TODO: handle exception
//			 Library.TraseMsg("procSetting e"+e.toString()+"\n"+err);
//			 int a = 0;
//			 Library.showDebugDialog(act, "procSetting " +e.toString());
//		}
		 return false;
	 }
	 
	 private static void Equip(final int idx)
	 {
		 Library.setEquip(idx);
		 Library.SaveDataBase(pAm);
	 }
	 
	
	 private static int buyOkTimer = 0;
	 private static void buyOk( final int idx , final Activity act)
	 {
		 if(buyOkTimer >= 1)
		 {
			 return;
		 }
		 buyOkTimer = 10;
		 if(nowWindow)
		 {
			 return;
		 }
		 
		 int chackManey = 0;
		 if( nowView == ConstantClass.ITEM_VIEW_SHOP)
    	{
			 chackManey = ShopData.FLAME_PRICE[idx];
    	}else
 		if( nowView == ConstantClass.ITEM_VIEW_BASE_SHOP)
    	{
 			chackManey = ShopData.NEXT_GOLD[Library.nowLank()];
    	}
		 if(Library.getManey() < chackManey)
		 {
			 Library.SE_Play( se_Play.SOUND_SE_BUZAR );
			 return; 
		 }
		 Library.SE_Play( se_Play.SOUND_SE_CURSOL ); 

		 
		 nowWindow = true;
		 new Thread(new Runnable() {
			    public void run() {
			      handler.post(new Runnable() {
			    	  public void run() 
			    {
					 int  err = 0;
					 try
					 {
						err = 1;
					    AlertDialog.Builder ad=new AlertDialog.Builder(act);
					    String Title = "";
					    String maney = "";
					    
					    if( nowView == ConstantClass.ITEM_VIEW_SHOP)
				    	{
					    	Title = ""+ShopData.FLAME_NAME[idx];
						    maney = ""+ShopData.FLAME_PRICE[idx];
				    	}else
			    		if( nowView == ConstantClass.ITEM_VIEW_BASE_SHOP)
				    	{
			    			Title = ""+ShopData.BASE_NAME[Library.nowLank()+1];
						    maney = ""+ShopData.NEXT_GOLD[Library.nowLank()];
				    	}
					    ad.setTitle(""+Title);
					    ad.setMessage("購入しますか？\n所持金　"
					    		+Library.getManey()+"羊"
					    		+"\n値段 "+maney+" 羊");
					    
					    ad.setInverseBackgroundForced(false);
					    err = 2;
					    
					    ad.setPositiveButton("購入",
					    	new DialogInterface.OnClickListener()
						    {
						        public void onClick(DialogInterface dialog,int whichButton) {
						        	
						        	if( nowView == ConstantClass.ITEM_VIEW_BASE_SHOP)
							    	{
						        		Library.addManey(-ShopData.NEXT_GOLD[Library.nowLank()]);		
							        	Library.setLank(Library.nowLank() + 1);
							        	Library.setShop(Library.nowLank() );							        	
							        					        		
							    	}else
							    	{
							    		DataSave.setDataBaseWeponTime(idx, ShopData.SHOP_LENTAL_TIME);
							        	Library.addManey(-ShopData.FLAME_PRICE[idx]);	
							    	}

						        	Library.SE_Play( se_Play.SOUND_SE_GOLD );
						        	setDrawList(act , nowView);
									//セーブ
						        	try
						        	{
						        		Library.SaveDataBase(pAm);
						        		pAm.dqSave.writeDBAll( pAm.getdb() );
						        	}catch (Exception e) {
										// TODO: handle exception
									}
						        	nowWindow = false;
						        	buyOkTimer = 10;
						        }
						    }
					    );
					    err = 3;
					    ad.setNegativeButton("やめる",
					    	new DialogInterface.OnClickListener() 
					    	{				
								//@Override
								public void onClick(DialogInterface dialog,int whichButton) {
									nowWindow = false;
						        }
							}
					    );
				    	ad.setCancelable(true);

					    err = 4;
					    ad.create();
					    ad.show();

					 }catch (Exception e) {
						// TODO: handle exception
					}
			    }
		      });
		    }
		  }).start();
		 
	 }
	 
	 public static boolean getNowDemo()
	 {
		 return SettingDemo;
	 }
	 public static int getNowSelect()
	 {
		 return drawWeponKind[nowtoush]; 
	 }
	 
	 public static int getNowView()
	 {
		return nowView;
	 }
	 
	 
	 
	 
	 
	 public static void drawSetting(Graphics g , int select , Play play)
	 {
		 UI.DRAW_UI_MODE(UI.PREVIEW_MODE);
		 //プレビュー
		 if(nowView == ConstantClass.ITEM_VIEW_BASE_SHOP ||
				 nowView == ConstantClass.ITEM_VIEW_BASE_SET )
		 {
		 }else
		 {
			 play.PlayDraw(g);
		 }
		 
		 UI.DrawTitleUnderMenu( g );
		 {
			 UI.drawTitleManey(g , Library.getManey());
		 }
		 
		 if(nowView == ConstantClass.ITEM_VIEW_BASE_SHOP)
		 {
			 if(Library.nowLank() >=  ShopData.BASE_TYPE_MAX)
			 {
				 return;
			 }
		 }
		 
		 
		 //アイコン描画
		 drawIcons( g , select);		 
		 //説明
		 drawHelp( g , select );
	 }
	 
	 private static void drawIcons(Graphics g , int select)
	 {
		 try
		 {
			 int color  = UI.S_COLOR_ON;
			 int color2 = UI.S_COLOR_OFF;
			 if( nowView == ConstantClass.ITEM_VIEW_BASE_SHOP)
		     { 
				 if(kindNum != 0)
				 {
					 int chackManey = ShopData.NEXT_GOLD[Library.nowLank()];
					 
					 int colorNow = UI.S_COLOR_OFF;
					 
					 if(Library.getManey() < chackManey)
					 {
						 colorNow = UI.S_COLOR_ON;
					 }
					 
					 UI.DrawSwichePict( g ,
						drawWeponKind[0] ,
						IconCenterX, IconCenterY,
						Icon_W , Icon_H ,
						true ,
						colorNow , colorNow ,
						true,ShopData.NEXT_GOLD[Library.nowLank()] , true );
				 }
				 return;
			 }
			 
			 
			 for(int i = 0; i < kindNum; i ++)
			 {
				 boolean selectok = false;
				 if(i == select)
				 {
					 selectok = true;
				 }
				 if( nowView == ConstantClass.ITEM_VIEW_SHOP ||
					 nowView == ConstantClass.ITEM_VIEW_SKILL)
		    	 {
					 if(Setting.drawWeponKind[i] == 0)
					 {
						 break;
					 }
		    	 }
				 
	
				 boolean gold = true;
				 int goldNum = ShopData.FLAME_PRICE[drawWeponKind[i]];
				 
				 color  = UI.S_COLOR_ON;
				 color2 = UI.S_COLOR_OFF;
				 if(nowView == ConstantClass.ITEM_VIEW_BASE_SET)
				 {
					 int nowEq = Library.getShop();
					 if(drawWeponKind[i] == nowEq)
					 {
						 color  = UI.S_COLOR_ON_EQ;
						 color2 = UI.S_COLOR_OFF_EQ;
					 }	
					 gold = false;
				 }else
				 if( nowView == ConstantClass.ITEM_VIEW_SKILL)
		    	 {
					 int nowEq = Library.nowEquip();
					 if(drawWeponKind[i] == nowEq)
					 {
						 color  = UI.S_COLOR_ON_EQ;
						 color2 = UI.S_COLOR_OFF_EQ;
					 }	
					 gold = false;
		    	 }else
				 if(nowtoush == i)
				 {
					 color  = UI.S_COLOR_ON_SEL;
					 color2 = UI.S_COLOR_OFF_SEL;
				 }
				 boolean shop = false;
				 if( nowView == ConstantClass.ITEM_VIEW_BASE_SET ||
						 nowView == ConstantClass.ITEM_VIEW_BASE_SHOP)
			     {
					 shop = true;
			     }
				 UI.DrawSwichePict( g ,
					drawWeponKind[i ] ,
					IconX[i], IconY[i],
					Icon_W , Icon_H ,
					selectok ,
					color , color2 ,gold,goldNum , shop);
			 }
		 }catch (Exception e) {
			// TODO: handle exception
			 Library.TraseMsg("drawIcons err" + e.toString());
		}
	 }
	 
	 private static void drawHelp(Graphics g, int select)
	 {
		 //名前ウィンドウ
		 //ウィンドウ
		 int Namex = 0;
		 int Namey = 256 + Setting.BASE_Y_POINT  ;
		 int nameH = 42 ;
		 UI.DrawWindow(g, Namex , Namey , 480, nameH );
		 String name = "";
		 if( nowView == ConstantClass.ITEM_VIEW_SHOP ||
				 nowView == ConstantClass.ITEM_VIEW_SKILL)
    	 {
	 		 if(ShopData.FLAME_NAME[drawWeponKind[nowtoush]] == null)
	 		 {
	 		 }else
	 		 {
	 			 name = ShopData.FLAME_NAME[drawWeponKind[nowtoush]];
	 		 }
    	 }else
    	 {
    		 if(nowView == ConstantClass.ITEM_VIEW_BASE_SHOP &&
    			 Library.nowLank() >= ShopData.GUREAD_MAX - 1 )
    		 {
    			 
    		 }else    		 
	 		 if(ShopData.BASE_NAME[drawWeponKind[nowtoush]] == null)
	 		 {
	 		 }else
	 		 {
	 			 name = ShopData.BASE_NAME[drawWeponKind[nowtoush]];	    		 
	 		 }
    	 }
		 g.setFontSize( 22 );
		 UI.DrawString( g , name ,
			Namex + 30 ,
			Namey + 26 ,
			Graphics.getColorOfRGB( 0, 0, 0,255 ),
			Graphics.getColorOfRGB( 180, 180, 180, 255 ),
			UI.DRAW_TYPE_DBUL );
 
		 
		 
		 //説明
		 int basex = 0;
		 int basey = Namey + nameH;
		 //ウィンドウ
		 UI.DrawWindow(g, basex , basey , 480, 102  );
		 //メッセージ
		 
		 boolean selectOk = false;
		 if(select == MAX_DRAW_KIND - 1)
		 {
			 selectOk = true;
		 }
		 //戻る　ボタン
		 UI.DrawSwicheMsg( g,
			 "戻る",
			 20,
			 4 + basex,
			 4  + basey,
			 124 , 75 ,
			 selectOk ,
			 UI.S_COLOR_ON , UI.S_COLOR_OFF);
		 

		 if(Setting.kindNum <= 0)
		 {
			 return;
		 }
		 
		 
		 //説明
		 String helpText = "";
 		 int shopx = basex + 135 ;
		 int shopy = basey + 30  ;
			
	 	 for(int i = 0; i < ShopData.FLAME_HELP_LEN; i ++)
	 	 {
	 		 if( nowView == ConstantClass.ITEM_VIEW_SHOP ||
				 nowView == ConstantClass.ITEM_VIEW_SKILL)
	    	 {
		 		 if(ShopData.FLAME_HELP[drawWeponKind[nowtoush]][i] == null)
		 		 {
		 			 continue;
		 		 }
		 		 helpText = ShopData.FLAME_HELP[drawWeponKind[nowtoush]][i];
	    	 }else
	    	 {
		 		 if(ShopData.BASE_HELP[drawWeponKind[nowtoush]][i] == null)
		 		 {
		 			 continue;
		 		 }
		 		 helpText = ShopData.BASE_HELP[drawWeponKind[nowtoush]][i];	    		 
	    	 }

	 		 UI.DrawString( g , helpText ,
		 		shopx ,
		 		shopy + i * (20 ),
				Graphics.getColorOfRGB( 0, 0, 0,255 ),
				Graphics.getColorOfRGB( 180, 180, 180, 255 ),
				UI.DRAW_TYPE_DBUL );
	 	 }
	 	
	 }
}