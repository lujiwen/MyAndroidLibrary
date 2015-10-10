package com.seadee.library.activity;

import java.util.ArrayList;
import java.util.List;

import com.seadee.library.R;
import com.seadee.library.utils.SDDisplay;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SDStatusBar extends LinearLayout {
	Context context;
	int size=16;
	List<TextView> mList=new ArrayList<TextView>();

	public SDStatusBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
		setVisibility(View.GONE);
		setOrientation(HORIZONTAL);
		setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)); 
		setBackgroundResource(R.drawable.sd_ic_statusbar_background);
	}
	
	public void show()
	{
		setVisibility(View.VISIBLE);
	}
	
	public TextView newStatus(int layoutweight)
	{
		setVisibility(View.VISIBLE);
		TextView text=new TextView(context);
		LayoutParams lp=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		int len=SDDisplay.px2dip(this.context, 4);
        text.setPadding(len, len/2,len, len/2);
        lp.setMargins(len*3/4, len*3/4, len*3/4, len*3/4);
		lp.weight=layoutweight;
		text.setTextSize(size);
		text.setLayoutParams(lp);
		addView(text);
		
		ImageView divider=new ImageView(context);
		divider.setPadding(len, len/2,len, len/2);
		divider.setImageResource(R.drawable.sd_icon_toolbar_divider);
		LayoutParams divider_lp=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		divider_lp.setMargins(len*3/4, len*3/4, len*3/4, len*3/4);
		
		addView(divider,divider_lp);
		mList.add(text);
		return text;
	}
	
	
	public void setTextSize(int size)
	{
		this.size=size;
		for(int i=0;i<mList.size();i++)
		{
			mList.get(i).setTextSize(size);
		}
	}
	
	public void showHint(int n,String hint)
	{
		if(n<mList.size())
		{
			mList.get(n).setText(hint);
		}
	}
	
	public TextView getHintTextView()
	{
		if(mList.size() > 0)
			return mList.get(0);
		return null;
	}
	
	public void showHint(String hint)
	{
		showHint(0,hint);
	}

}
