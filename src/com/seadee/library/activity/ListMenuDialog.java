package com.seadee.library.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.seadee.library.R;


public class ListMenuDialog extends Dialog {
	Window window;
	WindowManager.LayoutParams lp;
	ListView listview;
	Context context;
	List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
	String broadcastname;
	
	public ListMenuDialog(Context context,String broadcastname)
	{
		super(context,R.style.dialog);
		setContentView(R.layout.sd_menu_list_dialog);
		listview=(ListView)findViewById(R.id.listViewRightMenu);
		window=getWindow();
		lp=window.getAttributes();
		lp.flags |= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL   
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		window.setAttributes(lp);
		this.context=context;
		this.broadcastname=broadcastname;
		setSize(0,0);
	}
	
	public void addData(int imageid,String title,String shortcut)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("image", imageid);
		map.put("title", title+","+shortcut);
		listItems.add(map);
	}
	
	public void loadData(List<ListDialogData> listdata)
	{
		listItems.clear();
		for(int i=0;i<listdata.size();i++)
		{
			addData(listdata.get(i));
		}
	}
	
	public ListView getListView()
	{
		return listview;
	}
	
	
	public void addData(ListDialogData data)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("image", data.imageid);
		map.put("title", data.title);
		listItems.add(map);
	}
	
	public static class ListDialogData{
		int imageid;
		String title;
		String shortcut;
		public static ListDialogData makeData(int imageid,String title,String shortcut){
			ListDialogData listdialogdata=new ListDialogData();
			listdialogdata.imageid=imageid;
			listdialogdata.title=title+","+shortcut;
			return listdialogdata;
		}
	}
	
	public void addData(List<ListDialogData> listdata)
	{
		for(int i=0;i<listdata.size();i++)
		{
			addData(listdata.get(i));
		}
	}
	
	public void show()
	{
		ListMenuDialogAdapter adapter=new ListMenuDialogAdapter(context,listItems,R.layout.sd_menu_list_dialog_line,
				new String[]{"title","image"},new int[]{R.id.title,R.id.image},broadcastname);
		listview.setAdapter(adapter);
		super.show();
	}
	
	public void clear()
	{
		listview.setAdapter(null);
		listItems.clear();
	}
	
	public void setGravity(int gravity,int x,int y)
	{
		window.setGravity(gravity);
		lp.x=x;
		lp.y=y;
		window.setAttributes(lp);
	}
	
	public void setAlpha(float alpha)
	{
		lp.alpha=alpha;
		window.setAttributes(lp);
	}
	
	public void setSize(int width,int height)
	{
		width=width==0?ViewGroup.LayoutParams.WRAP_CONTENT:width;
		height=height==0?ViewGroup.LayoutParams.WRAP_CONTENT:height;
		lp.width=width;
		lp.height=height;
		window.setAttributes(lp);
	}
	
}
