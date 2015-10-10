package com.seadee.library.activity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.seadee.library.R;
import com.seadee.library.activity.ListMenuDialog.ListDialogData;
import com.seadee.library.common.Common;
import com.seadee.library.common.SDRegEventListener;
import com.seadee.library.utils.SDDisplay;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/*
 * usage sample:
 	SDMenuBar.MenuItem menuhello;
	SDMenuBar.MenuItem menuworld;
	SDMenuBar sdmenubar;
	@Override
	public void onCreateMenuBar(SDMenuBar menubar) {
		// TODO Auto-generated method stub
		List<ListDialogData> data=new ArrayList<ListDialogData>();
		data.add(ListDialogData.makeData(R.drawable.sd_ic_btn_hover, "New"));
		data.add(ListDialogData.makeData(R.drawable.sd_ic_btn_normal, "Save"));
		menuhello=menubar.newMenu("File", null, data);
		List<ListDialogData> data2=new ArrayList<ListDialogData>();
		data2.add(ListDialogData.makeData(R.drawable.sd_ic_btn_normal, "Find"));
		data2.add(ListDialogData.makeData(R.drawable.sd_ic_btn_hover, "Replace"));
		menuworld=menubar.newMenu("Edit", null, data2);
		sdmenubar=menubar;	
	}
	
	@Override
	public void parseMenu(int menuindex) {
		// TODO Auto-generated method stub
		if(sdmenubar.getCurrentMenuItemView()==menuhello)
		{
			switch(menuindex)
			{
			case 0:
				Log.i("File-New");break;
			case 1:
				Log.i("File-Save");break;
			}
		}
		else if(sdmenubar.getCurrentMenuItemView()==menuworld)
		{
			switch(menuindex)
			{
			case 0:
				Log.i("Edit-Find");break;
			case 1:
				Log.i("Edit-Replace");break;
			}
		}
	}
 */

public class SDMenuBar extends LinearLayout{
	List<SDMenuItem> mList=new ArrayList<SDMenuItem>();
	Context context;
	int size=20;
	ListMenuDialog listDialog;
	MenuItem curtextview;
	
	public class MenuItem extends TextView{
		
		public MenuItem(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		public MenuItem(Context context, AttributeSet attrs) {
			super(context, attrs);
			// TODO Auto-generated constructor stub
		}

		public MenuItem(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			// TODO Auto-generated constructor stub
		}
	};
	
	public class SDMenuItem
	{
		TextView textview;
		List<ListDialogData> listdata;
		protected SDMenuItem(TextView textview,List<ListDialogData> listdata)
		{
			this.textview=textview;
			this.listdata=listdata;
		}
	}
	
	public SDMenuBar(Context context,String broadcastname) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
		setVisibility(View.GONE);
		setOrientation(HORIZONTAL);
		setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)); 
		setBackgroundResource(R.drawable.sd_ic_menubar_background);
		listDialog=new ListMenuDialog(context,broadcastname);
	}
	
	public ListMenuDialog getMenuDialog()
	{
		return listDialog;
	}
	
	public ListView getListView()
	{
		return listDialog.getListView();
	}
	
	
	public MenuItem newMenu(String str,final String methodname,final List<ListDialogData> data){
		setVisibility(View.VISIBLE);
		MenuItem textview=new MenuItem(context);
		LayoutParams lp=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		int len=SDDisplay.px2dip(this.context, 4);
		textview.setPadding(len, len/2,len, len/2);
		lp.setMargins(len*3/4, len*3/4, len*3/4, len*3/4);
		textview.setTextSize(size);
		textview.setLayoutParams(lp);
		addView(textview);
		try{
			textview.setText(str);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		SDRegEventListener.registerHoverListener(textview, R.drawable.sd_ic_menu_background, 0);
		SDMenuItem menuItem=new SDMenuItem(textview,data);
		mList.add(menuItem);
		
		textview.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int[] location=new int[2];
				v.getLocationOnScreen(location);
				listDialog.setGravity(Gravity.LEFT|Gravity.TOP, location[0], location[1]+v.getHeight()+3);
				for(int i=0;i<mList.size();i++)
				{
					if(mList.get(i).textview==(MenuItem)v)
					{
						if(mList.get(i).listdata!=null)
						{
							listDialog.loadData(mList.get(i).listdata);
							curtextview=(MenuItem)v;
							listDialog.show();
						}
						break;
					}
				}
				if(methodname!=null && !methodname.isEmpty())
				{
					try {
						Method method=context.getClass().getMethod(methodname);
						method.invoke(context);
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
		return textview;
	}
	
	public MenuItem getCurrentMenuItemView()
	{
		return curtextview;
	}

	public int getMenuNumber()
	{
		return mList.size();
	}
	
	public TextView getAt(int n)
	{
		if(n>=mList.size())
			return null;
		return mList.get(n).textview;
	}
	
	public List<SDMenuItem> getMenuList()
	{
		return mList;
	}
	
	public void setMenuTextSize(int size)
	{
		this.size=size;
		for(int i=0;i<mList.size();i++)
		{
			mList.get(i).textview.setTextSize(size);
		}
	}
	
	public void hide()
	{
		setVisibility(View.GONE);
	}
	
	public void show()
	{
		if(mList.size()!=0)
			setVisibility(View.VISIBLE);
	}
	
	public void removeAllMenus()
	{
		removeAllViews();
		mList.clear();
		setVisibility(View.GONE);
	}
	
	public void remove(TextView textview)
	{
		removeView(textview);
		mList.remove(textview);
		if(mList.isEmpty())
			setVisibility(View.GONE);
	}
	
	public void cancelMenuDialog()
	{
	    listDialog.hide();   
	}
}
