
package jp.co.se.android.FlameP.lib;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import jp.co.se.android.FlameP.R;
import jp.co.se.android.FlameP.activity.bgmSelect;
import jp.co.se.android.FlameP.activity.bgmSelect.ListData;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class listData extends ArrayAdapter {   
	
	private ArrayList items;   
	private LayoutInflater inflater;  
	private String TitleName;
	
	
	@SuppressWarnings("unchecked")
	public listData(Context context, int textViewResourceId,   
		ArrayList<?> items , String name ) {   
		super(context, textViewResourceId, items);  

		this.items = items;   
		this.inflater = (LayoutInflater) context.  
		getSystemService(Context.LAYOUT_INFLATER_SERVICE);   
		this.TitleName = name;
		
	}   
	  
	@Override  
	public View getView(int position, View convertView, ViewGroup parent) {   

		ListData item = (ListData)items.get(position); 
		View view = convertView;   
		if (item != null) {   
			// ビューを受け取る   
			if (view == null) {   
			// 受け取ったビューがnullなら新しくビューを生成   
//				if(item.getLoad())
//				{
//					view = inflater.inflate(R.layout.listbase_load, null);   
//				}else
//				{
//					view = inflater.inflate(R.layout.listbase, null);   
//				}
				if(item.getType() == bgmSelect.LIST_CHEACK_BOX)
				{
					view = inflater.inflate(R.layout.listradio , null);   
					CheckBox userCheckBox = (CheckBox) view.findViewById(jp.co.se.android.FlameP.R.id.checkBoxAll);
					userCheckBox.setText(item.getMsg());
					return view; 
				}else
				if(item.getType() == bgmSelect.LIST_NO_DRAW)
				{
					view = inflater.inflate(R.layout.listbase_nodraw, null);   
					return view; 
				}else
				if(item.getType() == bgmSelect.LIST_TYPE_RANK)
				{
					view = inflater.inflate(R.layout.listbase_rank, null);   					
				}
				
				else
				if(item.getType() == bgmSelect.LIST_TYPE_ITEM)
				{
					view = inflater.inflate(R.layout.listbase_item, null);   					
				}else
				{
					view = inflater.inflate(R.layout.listbase, null);   
				}
			}
			
			TextView text = (TextView)view.findViewById(R.id.ListText); 
			
			
			if(item.getStrType() == bgmSelect.STR_BLUE)
			{
				text.setTextColor(Library.COLOR_RGB( 30, 60, 255));
			}else
			if(item.getStrType() == bgmSelect.STR_RED)
			{
				text.setTextColor(Library.COLOR_RGB( 255, 30, 30));				
			}else
			if(item.getFlag())
			{
				text.setTextColor(Library.COLOR_RGB( 12, 12, 12));
				//text.setBackgroundColor(Library.COLOR_RGB(244, 12, 12));
			}else
			{
				text.setTextColor(Library.COLOR_RGB( 212, 212, 212));
				//text.setBackgroundColor(R.color.background_menu);				
			}
			text.setText(item.getMsg());
			
			if(item.getType() == bgmSelect.LIST_TYPE_RANK)
			{
				text = (TextView)view.findViewById(R.id.ListText2); 
				
				
				if(item.getStrType() == bgmSelect.STR_BLUE)
				{
					text.setTextColor(Library.COLOR_RGB( 30, 60, 255));
				}else
				if(item.getStrType() == bgmSelect.STR_RED)
				{
					text.setTextColor(Library.COLOR_RGB( 255, 30, 30));				
				}else
				if(item.getFlag())
				{
					text.setTextColor(Library.COLOR_RGB( 12, 12, 12));
					//text.setBackgroundColor(Library.COLOR_RGB(244, 12, 12));
				}else
				{
					text.setTextColor(Library.COLOR_RGB( 212, 212, 212));
					//text.setBackgroundColor(R.color.background_menu);				
				}
				
				text.setText(item.getMsg2());
			}
			
//			if(item.getType() == bgmSelect.LIST_TYPE_ITEM)
//			{
//				TextView text2 = (TextView)view.findViewById(R.id.ListText02); 
//				
//				
//				if(item.getFlag())
//				{
//					text2.setTextColor(Library.COLOR_RGB( 12, 12, 12));
//					//text2.setBackgroundColor(Library.COLOR_RGB(244, 12, 12));
//				}else
//				{
//					text2.setTextColor(Library.COLOR_RGB( 212, 212, 212));
//					//text2.setBackgroundColor(R.color.background_menu);				
//				}
//				if(item.getStrType() == bgmSelect.STR_RED)
//				{
//					text2.setTextColor(Library.COLOR_RGB( 255, 0, 0));				
//				}
//				text2.setText(item.getMsg2());
//			}
			
		}
		return view;
	}
}   