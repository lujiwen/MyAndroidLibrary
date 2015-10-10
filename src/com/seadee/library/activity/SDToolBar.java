package com.seadee.library.activity;

import java.util.ArrayList;
import java.util.List;

import com.seadee.library.R;
import com.seadee.library.common.Common;
import com.seadee.library.common.SDRegEventListener;
import com.seadee.library.utils.SDDisplay;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SDToolBar extends LinearLayout {
	List<SDToolItem> mList=new ArrayList<SDToolItem>();
	Context context;
	
	
	public class SDToolItem extends ImageView{
		public SDToolItem(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		public SDToolItem(Context context, AttributeSet attrs) {
			super(context, attrs);
			// TODO Auto-generated constructor stub
		}

		public SDToolItem(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			// TODO Auto-generated constructor stub
		}
	}

	public SDToolBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}
	
	public SDToolBar(Context context,TextView hintTv) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
		this.hintTv = hintTv;
	}
	
	private void init(Context context)
	{
		this.context=context;
		setVisibility(View.GONE);
		setOrientation(HORIZONTAL);
		setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)); 
		setBackgroundResource(R.drawable.sd_ic_toolbar_background);
	}
	
	public SDToolItem newTool(int drawableid){
		setVisibility(View.VISIBLE);
		SDToolItem tool=new SDToolItem(context);
		LayoutParams lp=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		int dp=SDDisplay.px2dip(context,5);
		tool.setPadding(dp,dp,dp,dp);
		lp.setMargins(dp,dp,dp,dp);
		tool.setLayoutParams(lp);
		addView(tool);
		try{
			tool.setImageResource(drawableid);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		mList.add(tool);
		SDRegEventListener.registerHoverListener(tool, R.drawable.sd_ic_menu_background, 0);
		return tool;
	}

	public SDToolItem newTool(int drawableid,String hint){
		SDToolItem tool = newTool(drawableid);
		SDRegEventListener.registerHoverListener(tool, R.drawable.sd_ic_menu_background, 0,hintTv,hint);
		return tool;
	}
	
	public SDToolItem newDivider()
	{
		SDToolItem tool=new SDToolItem(context);
		tool.setImageResource(R.drawable.sd_icon_toolbar_divider);
		LayoutParams lp=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		int dp=SDDisplay.px2dip(context,2);
        tool.setPadding(dp,dp,dp,dp);
        lp.setMargins(dp,dp,dp,dp);
		tool.setLayoutParams(lp);
		addView(tool);
		mList.add(tool);
		return tool;
	}
	
	public SDToolItem newListTool()
	{
		SDToolItem tool=new SDToolItem(context);
		tool.setImageResource(R.drawable.sd_icon_action_list);
		LayoutParams lp=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		int dp=SDDisplay.px2dip(context,5);
        tool.setPadding(dp,dp,dp,dp);
        lp.setMargins(dp,dp,dp,dp);
		tool.setLayoutParams(lp);
		addView(tool);
		SDRegEventListener.registerHoverListener(tool, R.drawable.sd_ic_menu_background, 0);
		mList.add(tool);
		return tool;
	}
	
	TextView hintTv;
	public void setHintTextView(TextView tv)
	{
		this.hintTv = tv;
	}

	public int getToolsCount()
	{
		return mList.size();
	}
	
	public SDToolItem getAt(int n)
	{
		if(n>mList.size())
			return null;
		return mList.get(n);
	}
	
	public List<SDToolItem> getMenuList()
	{
		return mList;
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
	
	public void remove(SDToolItem imageview)
	{
		removeView(imageview);
		mList.remove(imageview);
		if(mList.isEmpty())
			setVisibility(View.GONE);
	}
	
}
