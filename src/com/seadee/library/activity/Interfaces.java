package com.seadee.library.activity;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Interfaces {
	public interface ActivityListener{
		public void addCustomTitleBar();
		public int getScreenWidth();
		public int getScreenHeight();
		public void setGravity(int gravity);
		public void setPosition(int x,int y);
		public void addPosition(int offsetx,int offsety);
		public void setSize(int width,int height);
		public void addSize(int offsetwidth,int offsetheight);
		public void setTitle(String title);
		public void setIcon(int iconid);
		public void switchFullScreen();
		public void setFullScreen(boolean isFull);
		public boolean isFullScreen();
		public ImageView getCloseBtn();
		public TextView getTitleText();
		public ImageView getMaxBtn();
		public ImageView getMinBtn();
		public ImageView getHelpBtn();
		public ImageView getTitleIcon();
		public RelativeLayout getTitleBar();
	}
}
