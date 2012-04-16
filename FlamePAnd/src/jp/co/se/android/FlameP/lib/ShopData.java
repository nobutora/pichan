
package jp.co.se.android.FlameP.lib;

import jp.co.se.android.FlameP.scene.Title;



// �\���p�̃N���X
public class ShopData  {
	
	
	//���X�܂Ȃǂ̃X�e�[�^�X�Œ�l===================================
	
	
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
	
	
	
	
	
	//�����̃X�e�[�^�X�Œ�l===================================
	
	//���ʂ̉�
	public static final int FLAME_TYPE_NORMAL  = 1;
	//�J�X�^���̉�
	public static final int FLAME_TYPE_CASTAM  = 2;
	//�g�U�̉�
	public static final int FLAME_TYPE_SMOAL   = 3;
	//�X�i�C�v�̉�
	public static final int FLAME_TYPE_SNIPING = 4;
	//���[�U�[�̉�
	public static final int FLAME_TYPE_LASER   = 5;
	//�Ή�����
	public static final int FLAME_TYPE_FLAME   = 6;
	//�I�[�\�h�b�N�X�`���[�W
	public static final int FLAME_TYPE_CHARGE  = 7;
	//�I�[�\�h�b�N�X�`���[�W �X�ɂ͏���
	public static final int FLAME_TYPE_CHARGE_2  = 8;
	//�I�[�\�h�b�N�X�`���[�W �X�ɂ͏���
	public static final int FLAME_TYPE_CHARGE_3  = 9;

	//�ԉ�
	public static final int FLAME_TYPE_FLOWER	 = 10;

	//��
	public static final int FLAME_TYPE_LOW  = 11;

	//�z�[�~���O
	public static final int FLAME_TYPE_HOMING = 12;
	//��ލő�
	public static final int FLAME_TYPE_MAX     = 13;

	public static final int FLAME_HELP_LEN = 4;
	
	//�O�͎̂Ă�	
	public static String FLAME_NAME[];
	public static String FLAME_HELP[][];
	//�U����
	public static int FLAME_POWERS[];
	//�f�Bڲ
	public static int FLAME_DRAY[];
	//�l�i
	public static int FLAME_PRICE[];
	//�͈�
	public static  int FLAME_RARGE[];
	//�����N
	public static  int FLAME_RANK[];
	//�G
	public static  int FLAME_PICT[];
	//����
	public static  boolean FLAME_FREE[];
	//�~�T�C���X���[
	public static  boolean FLAME_MISSILE_SLOO[];
	//���ԃX���[
	public static  boolean FLAME_TIME_SLOO[];
	//���ԃX���[ ����
	public static  boolean FLAME_TIME_SLOOHALF[];
	
	//���R�Ƀ^�b�`
	public static  boolean FLAME_TOUCHE_FREE[];
	//���������]
	public static  boolean FLAME_TOUCHE_LONG[];
	//�������}�b�N�X
	public static  int FLAME_TOUCHE_LONG_MAX_TIME[];
	//������ �Q�[�W�̐�
	public static  int FLAME_TOUCHE_LONG_MAX_GAGE[];
	//�ђ�
	public static  boolean FLAME_ENEMY_SLOO[];
	//�X����֎~
	public static  boolean FLAME_NO_SHOPING[];
	
	
	
	//�X�Ƃ�
	
	//����
	public static final int BASE_TYPE_NO_SHOP  = 0;
	public static final int BASE_TYPE_LEVEL1  = 1;
	public static final int BASE_TYPE_LEVEL2  = 2;
	public static final int BASE_TYPE_LEVEL3  = 3;
	public static final int BASE_TYPE_MAX       = 4;
	
	//�A�b�v�O���[�h�Ȃ��
	public static  boolean BASE_UPGREADE[];
	public static  int BASE_PRICE[];
	public static  int BASE_IMAGE[];
	public static  int BASE_IMAGE_BACK[];
	public static String BASE_NAME[];
	public static String BASE_HELP[][];	
	//�����N
	public static  int BASE_RANK[];
	//�~�T�C����Q�@��
	public static  int BASE_MISSILE_DAMEGE[];
	//���P���A�b�v�@��
	public static  int BASE_GOLD_ADD[];
	
	
	
	
	//2����
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
		//�m�[�}���V���b�g�ݒ�
		type = FLAME_TYPE_NORMAL;
		FLAME_NAME[type]	="�m�[�}���V���b�g";
		
		FLAME_HELP[type][0]	="���ʂ̉��e���o���B";		
		FLAME_HELP[type][1]	="�o�����X��������������Ȃ��B";		

		FLAME_POWERS[type]	= 2 ;
		FLAME_DRAY[type]	= 4 ;
		FLAME_RARGE[type]	= 50;
		FLAME_PRICE[type]	= 0;
		FLAME_RANK[type]	= 0;
		FLAME_FREE[type]    = true;
		//FLAME_PICT[type]  	= jp.co.se.android.FlameP.R.drawable.wepon_pict1;
		
		
		
		//�򉻂�����Ɛݒ�
		type = FLAME_TYPE_LOW;
		FLAME_NAME[type]	="�򉻃V���b�g";
		
		FLAME_HELP[type][0]	="���ʂ̉��e���o���B";		
		FLAME_HELP[type][1]	="�o�����X��������������Ȃ��B";		

		FLAME_POWERS[type]	= 1;
		FLAME_DRAY[type]	= 6;
		FLAME_RARGE[type]	= 35;
		FLAME_PRICE[type]	= 0;
		FLAME_RANK[type]	= 0;
		//FLAME_PICT[type]  	= jp.co.se.android.FlameP.R.drawable.wepon_pict1;
		FLAME_NO_SHOPING[type] = true;

		
		//�A�˃V���b�g�ݒ�
		type = FLAME_TYPE_CASTAM;
		FLAME_NAME[type]	= "�X���[���V���b�g";
		FLAME_HELP[type][0]	= "�A�˂Ƒ��x�ɗD��鉊�e";		
		FLAME_HELP[type][1]	= "�З͂ƌ��ʔ͈͂͏��Ȃ߁B";	
		FLAME_HELP[type][2]	= "�����͂������Ƃ�������è�����Ȃ߁B";	

		FLAME_POWERS[type]	= 1;
		FLAME_DRAY[type]	= 2;
		FLAME_RARGE[type]	= 25;
		FLAME_PRICE[type]	= 750;
		FLAME_RANK[type]	= 0;
		FLAME_TIME_SLOOHALF[type] = true;
		//FLAME_PICT[type]	= jp.co.se.android.FlameP.R.drawable.wepon_pict4;

		//�g�U�V���b�g�ݒ�
		type = FLAME_TYPE_SMOAL;
		FLAME_NAME[type]	="�g�U�V���b�g";

		FLAME_HELP[type][0]	="���������e�𐁂��B";	
		FLAME_HELP[type][1]	="����Ɠ����ɏ������΂̕����o���B";			
		FLAME_HELP[type][2]	="�΂̕��̓~�T�C���ɂ������Ă�";	
		FLAME_HELP[type][3]	="���������肵�Ȃ��B";	

		FLAME_POWERS[type]	=1;
		FLAME_DRAY[type]	=8;
		FLAME_RARGE[type]	=5;
		FLAME_PRICE[type]	=18000;
		FLAME_RANK[type]	=2;
		FLAME_MISSILE_SLOO[type] = true;
		FLAME_TIME_SLOO[type] = true;
		//FLAME_PICT[type]  	= jp.co.se.android.FlameP.R.drawable.wepon_pict2;
		
		//�z�[�~���O
		type = FLAME_TYPE_HOMING;
		FLAME_NAME[type]	="�z�[�~���O�V���b�g";
		FLAME_HELP[type][0]	="���ˌ㏭���������ԋ߂��̒���";		
		FLAME_HELP[type][1]	="�������Ă����B";
		
		FLAME_POWERS[type]	=2;
		FLAME_DRAY[type]	=5;
		FLAME_RARGE[type]	=35;
		FLAME_PRICE[type]	=7500;
		FLAME_RANK[type]	=1;

		
		//�D���ȏꏊ�ɉ����o����
		type = FLAME_TYPE_SNIPING;
		FLAME_NAME[type]	="�X�i�C�p�[�V���b�g";
		FLAME_HELP[type][0]	="�^�b�`�����ꏊ�ɉ����o���B";		
		FLAME_HELP[type][1]	="���x�ƈЗ͂ɗD���B";		

		
		FLAME_POWERS[type]	=3;
		FLAME_DRAY[type]	=9;
		FLAME_RARGE[type]	=60;
		FLAME_PRICE[type]	=50000;
		FLAME_RANK[type]	=3;
		FLAME_TOUCHE_FREE[type] = true; 
		//FLAME_PICT[type]  	= jp.co.se.android.FlameP.R.drawable.wepon_pict3;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//���[�U�[
		type = FLAME_TYPE_LASER;
		FLAME_NAME[type]	="�A���h���C�h";
		FLAME_HELP[type][0]	="���Ԍo�߂Ŏg�p�\�ɁB�����ԃs�[�����";		
		FLAME_HELP[type][1]	="�A���h���C�h�ɂȂ�A�Ή����[�U�[�����Ă�悤�ɂȂ�B";	
		FLAME_HELP[type][2]	="���i�͗򉻃V���b�g�B";
		
		FLAME_POWERS[type]	=4;
		FLAME_DRAY[type]	=4;
		FLAME_RARGE[type]	=3;
		FLAME_PRICE[type]	=15000;
		FLAME_RANK[type]	=5;
		FLAME_TIME_SLOO[type] = true;
		
		//����
		type = FLAME_TYPE_FLAME;
		FLAME_NAME[type]	="�Ή�����";
		
		FLAME_HELP[type][0]	="���ˌ^�̉����o����B�{�^���𒷉��������";		
		FLAME_HELP[type][1]	="�Ή����傫���Ȃ��Ă����B";	
		FLAME_HELP[type][2]	="�{�^���𗣂����u�ԉ��͏�����B";	
		FLAME_POWERS[type]	=1;
		FLAME_DRAY[type]	=10;
		FLAME_RARGE[type]	=1;
		FLAME_PRICE[type]	=300;
		FLAME_RANK[type]	=4;
		FLAME_TOUCHE_LONG[type] = true;
		FLAME_TIME_SLOO[type] = true;
		FLAME_TOUCHE_LONG_MAX_TIME[type] = 70;
		//FLAME_PICT[type]  	= jp.co.se.android.FlameP.R.drawable.wepon_pict5;
		
		//�`���[�W
		type = FLAME_TYPE_CHARGE;
		FLAME_NAME[type]	="�`���[�W";
		FLAME_HELP[type][0]	="�{�^���𒷉����Ŋђʒe�����Ă�";		
		FLAME_HELP[type][1]	="�������߂�قǉ��̑傫���A�b�v�B";	
		FLAME_HELP[type][2]	="�ő�`���[�W";			
		FLAME_HELP[type][3]	="�Ń~�T�C���j��U�����\�B";			

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
		
		//�ԉ�
		type = FLAME_TYPE_FLOWER;
		FLAME_NAME[type]	="�ԉ�";
		
		FLAME_HELP[type][0]	="���ɂ����邱�Ƃŉԉ�";		
		FLAME_HELP[type][1]	="����������B";	
		FLAME_HELP[type][2]	="�ԉ΂̓~�T�C���𖳎�����B";			
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
		
		
		//�V���b�v�Ƃ�		
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
		
		BASE_NAME[type] = "���X�Ȃ�";
		BASE_HELP[type][0] = "�܂����X���Ȃ���ԁI";
		BASE_HELP[type][1] = "���������߂Ă��X�𔃂����I";
		BASE_HELP[type][2] = "л�ق̔�Q�������I";
		BASE_RANK[type] = 0;
		BASE_IMAGE[type] = 0;
		BASE_IMAGE_BACK[type] = 0;
		BASE_MISSILE_DAMEGE[type] = 0;

		type = BASE_TYPE_LEVEL1;
		
		BASE_NAME[type]    = "���Ղ艮��";
		BASE_HELP[type][0] = "���Ղ�̂Ƃ��ɕ����Ă��鉮��I";
		BASE_HELP[type][1] = "������������Ƒ����邼�I";
		BASE_HELP[type][2] = "л�ق̔�Q�͏��Ȃ߁I";
		BASE_RANK[type] = 0;
		BASE_IMAGE[type] = 1;
		BASE_IMAGE_BACK[type] = 0;
		BASE_MISSILE_DAMEGE[type] = 15;
		BASE_GOLD_ADD[type] = 10;
		
		type = BASE_TYPE_LEVEL2;
		
		BASE_NAME[type]    = "�ؐ��Զ�����";
		BASE_HELP[type][0] = "�Զ��̂������ŋ@���͂��������I";
		BASE_HELP[type][1] = "�������C���������邼�I";
		BASE_HELP[type][2] = "л�ق̔�Q�����������邼�I";
		BASE_RANK[type] = 0;
		BASE_IMAGE[type] = 2;
		BASE_IMAGE_BACK[type] = 1;
		BASE_MISSILE_DAMEGE[type] = 25;
		BASE_GOLD_ADD[type] = 20;

		type = BASE_TYPE_LEVEL3;
		BASE_NAME[type]    = "�ׯ�����";
		BASE_HELP[type][0] = "�ҁ[�����͖Ƌ��������Ă����I";
		BASE_HELP[type][1] = "�������Ȃ��Ȃ������邼�I";
		BASE_HELP[type][2] = "л�ٔ�Q��������x�����邼�I";
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