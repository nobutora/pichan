package jp.co.se.android.FlameP.activity;

import android.app.Activity;

/** ��ʑJ�ڌ�Activity�Ɋւ���萔�N���X */
public class ConstantClass {
	//�̔�����p���
	/** ���[�U�ݒ���͉�� */	
	public final static Class<bgmSelect> bgmSelect = jp.co.se.android.FlameP.activity.bgmSelect.class;

	public final static Class<ItemSelect> ItemSelect = jp.co.se.android.FlameP.activity.ItemSelect.class;
	
	
	public final static Class<LankingList> lankingList = jp.co.se.android.FlameP.activity.LankingList.class;
	
	
	//bgm�Z���N�g�N����
	private  static boolean bgmSelectOn = false;
	public static boolean getBgmSelectOn()
	{
		return bgmSelectOn ;
	}
	public static void setBgmSelectOn(boolean on)
	{
		bgmSelectOn = on;
	}
	
	public static boolean moveActivity = false;
	
	public static final int MOVE_ACTIVITY_TIME = 15;
	public static int moveActivityTime = MOVE_ACTIVITY_TIME;
	
	public static final int ITEM_VIEW_SHOP 	= 0;
	public static final int ITEM_VIEW_SKILL = 1;
	
	public static final int ITEM_VIEW_BASE_SHOP = 2;
	public static final int ITEM_VIEW_BASE_SET	= 3;
	
	
	private static int Item_View_Type 		= 0;
	public static void setItem_View(int viewtype)
	{
		Item_View_Type = viewtype;
	}
	public static int getItem_View()
	{
		return Item_View_Type;
	}
	
}