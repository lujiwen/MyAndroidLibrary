package com.seadee.library.activity;

import com.seadee.library.R;
import com.seadee.library.common.Common;
import com.seadee.library.common.SDRegEventListener;
import com.seadee.library.utils.SDDisplay;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class SDActivityListener  implements Interfaces.ActivityListener{
	
	ImageView closeButton;
	ImageView maxButton;
	ImageView minButton;
	ImageView helpButton;
	TextView textTitle;
	ImageView imageTitle;
	LinearLayout toolbar;
	LinearLayout menubar;
	RelativeLayout titlebar;

	Activity activity;
	int primary_width_dip,primary_height_dip,primary_x_dip,primary_y_dip;
	Window window;
	WindowManager.LayoutParams lp;
	DisplayMetrics metrics;
	
	boolean isFullScreen=false;
	
	public SDActivityListener(Activity activity)
	{	
		this.activity=activity;
		window=activity.getWindow();
        lp=window.getAttributes();
        primary_x_dip=px2dip(lp.x);
        primary_y_dip=px2dip(lp.y);
        primary_width_dip=px2dip(lp.width);
        primary_height_dip=px2dip(lp.height);
        metrics=getDisplayMetrics();
	}
	
	public int px2dip(float pxValue) {
       return SDDisplay.px2dip(activity, pxValue);
    }

    public int dip2px(float dipValue) {
        return SDDisplay.dip2px(activity, dipValue);
    }
    
    public int px2sp(Context context, float pxValue) {
        return SDDisplay.px2sp(activity, pxValue);
    }

    public int sp2px(Context context, float spValue) {
        return SDDisplay.sp2px(activity, spValue);
    }

	public void addCustomTitleBar()
	{
		window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.sd_title_bar);  
		titlebar=(RelativeLayout)activity.findViewById(R.id.titlebar);
		closeButton=(ImageView)activity.findViewById(R.id.closeButtonImage);
		maxButton=(ImageView)activity.findViewById(R.id.maxButtonImage);
		minButton=(ImageView)activity.findViewById(R.id.minButtonImage);
		helpButton=(ImageView)activity.findViewById(R.id.helpButtonImage);
		imageTitle=(ImageView)activity.findViewById(R.id.imageTitle);
		textTitle=(TextView)activity.findViewById(R.id.textTitle);
		
		SDRegEventListener.setImageSource(closeButton,activity.getResources().getDrawable(R.drawable.sd_icon_action_close),
				activity.getResources().getDrawable(R.drawable.sd_icon_action_close_hover));

		closeButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Common.simulateKey(KeyEvent.KEYCODE_BACK);
			}
			
		});
	}
	
	public DisplayMetrics getDisplayMetrics()
	{
		DisplayMetrics metrics = new DisplayMetrics(); 
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics); 
		return metrics;
	}
	
	public int getScreenWidth()
	{
		return px2dip(metrics.widthPixels);
	}
	
	public int getScreenHeight()
	{
		return px2dip(metrics.heightPixels);
	}
	
	public void setGravity(int gravity)
	{
		 window.setGravity(gravity);
	}
	
	public void addPosition(int offsetxdp,int offsetydp)
	{
	    primary_x_dip+=offsetxdp;
	    primary_y_dip+=offsetydp;
		lp.x=dip2px(primary_x_dip);
		lp.y=dip2px(primary_y_dip);
		window.setAttributes(lp);		
	}
	
	public void addSize(int offsetwidthdp,int offsetheightdp)
	{
	    primary_width_dip+=offsetwidthdp;
	    primary_height_dip+=offsetheightdp;
		lp.width=dip2px(primary_width_dip);
		lp.height=dip2px(primary_width_dip);
		window.setAttributes(lp);
	}
	
	public void setPosition(int xdip,int ydip)
	{
	     primary_x_dip=xdip;
	     primary_y_dip=ydip;
        lp.x=dip2px(xdip);
        lp.y=dip2px(ydip);
        window.setAttributes(lp); 
	}
	
	@Override
	public void setSize(int dpwidth, int dpheight) {
		// TODO Auto-generated method stub
	    int width = dpwidth > 0?dip2px(dpwidth):dpwidth;
	    int height= dpheight >0?dip2px(dpheight):dpheight;
        width = width == 0?LayoutParams.WRAP_CONTENT:width;
        height = height == 0?LayoutParams.WRAP_CONTENT:height;
        lp.width=width;
        lp.height=height;
        primary_width_dip=dpwidth;
        primary_height_dip=dpheight;
        window.setAttributes(lp); 
	}

	@Override
	public void setTitle(String title) {
	    if(title!=null)
	        getTitleText().setText(title);	
	}
	
	@Override
	public void setIcon(int iconid)
	{
	    if(iconid != 0)
	    {
	        try{
	            getTitleIcon().setImageResource(iconid);
	        }
	       catch(Exception e){
	           e.printStackTrace();
	       }
	    }
	}
	
	public boolean isFullScreen()
	{
		return isFullScreen;
	}
	
	public void switchFullScreen()
	{
		isFullScreen=!isFullScreen;
		setFullScreen(isFullScreen);
	}
	
	public void setFullScreen(boolean isFull)
	{
		if(isFull)
		{
			lp.x=0;
			lp.y=0;
			lp.width=LayoutParams.MATCH_PARENT;
			lp.height=LayoutParams.MATCH_PARENT;
			lp.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);  
			 window.setAttributes(lp);
			 window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		}
		else
		{   
			lp.x=dip2px(primary_x_dip);
			lp.y=dip2px(primary_y_dip);
			lp.width=dip2px(primary_width_dip);
			lp.height=dip2px(primary_height_dip);
	         lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;    
	         window.setAttributes(lp);    
	         window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);  
		}
	}

	
	public RelativeLayout getTitleBar()
	{
		return titlebar;
	}
	
	public ImageView getCloseBtn()
	{
		return closeButton;
	}
	
	public ImageView getMaxBtn()
	{
		return maxButton;
	}
	
	public ImageView getMinBtn()
	{
		return minButton;
	}
	
	public ImageView getHelpBtn()
	{
		return helpButton;
	}
	
	public ImageView getTitleIcon()
	{
		return imageTitle;
	}
	
	public TextView getTitleText()
	{
		return textTitle;
	}

}

