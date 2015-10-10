package com.seadee.library.activity;

import java.util.UUID;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class SDActivity extends Activity  {
	
	public class MenuReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if(action.equals(broadcastname))
			{
				parseMenu(intent.getExtras().getInt("MenuIndex"));
				menubar.cancelMenuDialog();
			}
			else if(action.equals(HINTBROADCASTNAME))
			{
				showHint(intent.getExtras().getString("HINT"));
			}
		}
	}
	
	public final static String HINTBROADCASTNAME = "com.seadee.rdp.hint";

	final static String TAG="Activity";
	Interfaces.ActivityListener activitylistener;
	
	protected Interfaces.ActivityListener getActivityListener()
	{
		return activitylistener;
	}
	
	public class ActivityParams{
	    int iconid;
	    String title;
	    int width;
	    int height;
	    
	    public ActivityParams()
	    {
	        
	    }
	    
	    public ActivityParams(int iconid,String title,int width,int height)
	    {
	        this.iconid=iconid;
	        this.title=title;
	        this.width=width;
	        this.height=height;
	    }
	}
	
	public abstract void onCreateMenuBar(SDMenuBar menubar);
	public abstract void parseMenu(int menuindex);
	public abstract void onCreateToolBar(SDToolBar toolbar);
	public abstract void onCreateStatusBar(SDStatusBar statusbar);
	public abstract ActivityParams specialActivity();

	MenuReceiver menureceiver;
	LocalBroadcastManager localbroadcast;
	String broadcastname;
	
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(bundle);
		activitylistener=new SDActivityListener(this);	
		menureceiver=new MenuReceiver();
		UUID uuid=UUID.randomUUID();
		broadcastname=uuid.toString();
		localbroadcast=LocalBroadcastManager.getInstance(this);
		IntentFilter intentfilter = new IntentFilter(broadcastname);
		intentfilter.addAction(HINTBROADCASTNAME);
		localbroadcast.registerReceiver(menureceiver,intentfilter);
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		localbroadcast.unregisterReceiver(menureceiver);
		super.onDestroy();
	}
	
	
    int xInView,yInView,xDownInScreen,yDownInScreen,xInScreen,yInScreen;
	RelativeLayout titlebar;
	Rect tb_rect;
	boolean isClickInTitlebar=false;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			if(titlebar==null)
				titlebar=activitylistener.getTitleBar();
			if(tb_rect==null)
				tb_rect=new Rect(titlebar.getLeft(),titlebar.getTop(),titlebar.getLeft()
						+titlebar.getWidth(),titlebar.getTop()+titlebar.getHeight());
			xInView=(int)event.getX();
			yInView=(int)event.getY();	
			isClickInTitlebar=tb_rect.contains(xInView,yInView);
			xInScreen=xDownInScreen=(int)event.getRawX();
			yInScreen=yDownInScreen=(int)event.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			if(isClickInTitlebar)
				activitylistener.addPosition((int)event.getRawX()-xInScreen, (int)event.getRawY()-yInScreen);
			xInScreen=(int)event.getRawX();
			yInScreen=(int)event.getRawY();
			break;
		case MotionEvent.ACTION_UP:
			if(menubar.getMenuDialog().isShowing())
			{
				menubar.getMenuDialog().hide();
			}
			break;
		}
		return super.onTouchEvent(event);
	}
	
	public SDMenuBar getMenuBar()	{
		return menubar;
	}
	
	public SDToolBar getToolBar(){
		return toolbar;
	};
	
	public SDStatusBar getStatusBar(){
		return statusbar;
	}
	
	public void showHint(String hint)
	{
		statusbar.showHint(hint);
	}
	
	public TextView getHintTextView()
	{
		return statusbar.getHintTextView();
	}

	SDMenuBar menubar;
	SDToolBar toolbar;
	SDStatusBar statusbar;
	
	@Override
    public void setContentView(int layoutResID) {
        setContentView(LayoutInflater.from(this).inflate(layoutResID, null));
    }

	
	@Override
	public void setContentView(View view, LayoutParams params) {
		// TODO Auto-generated method stub
		view.setLayoutParams(params);
		setContentView(view);
	}
	
	
	@Override
	public void setContentView(View view) {	
		// TODO Auto-generated method stub
		RelativeLayout relative=new RelativeLayout(this);
		relative.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		SDStatusBar statusbar=new SDStatusBar(this);
		onCreateStatusBar(statusbar);
		statusbar.setId(0x1f020002);

		SDToolBar toolbar=new SDToolBar(this,statusbar.getHintTextView());
		onCreateToolBar(toolbar);
		toolbar.setId(0x1f020001);
		
		SDMenuBar menubar=new SDMenuBar(this,broadcastname);
		onCreateMenuBar(menubar);
		menubar.setId(0x1f020000);
		
		RelativeLayout.LayoutParams status_lp=new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		status_lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		status_lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		
		RelativeLayout.LayoutParams menubar_lp=new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		menubar_lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		menubar_lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		RelativeLayout.LayoutParams toolbar_lp=new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		toolbar_lp.addRule(RelativeLayout.BELOW,menubar.getId());
		toolbar_lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		RelativeLayout.LayoutParams view_lp=new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		view_lp.addRule(RelativeLayout.ABOVE,statusbar.getId());
		view_lp.addRule(RelativeLayout.BELOW,toolbar.getId());
		view_lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		
		relative.addView(statusbar,status_lp);
		relative.addView(menubar,menubar_lp);
		relative.addView(toolbar,toolbar_lp);
		relative.addView(view,view_lp);
		
		super.setContentView(relative);
		setActivityParam();
		this.menubar = menubar;
		this.toolbar = toolbar;
		this.statusbar = statusbar;
	}
	
	private void setActivityParam()
	{
	    ActivityParams activityParams = new ActivityParams();
	   activityParams = specialActivity();
		getActivityListener().addCustomTitleBar();
		if(activityParams!=null)
		{
			if(activityParams.iconid != 0)
			{
				getActivityListener().setIcon(activityParams.iconid);
			}
			if(activityParams.title != null)
			{
				getActivityListener().setTitle(activityParams.title);
				getWindow().setTitle(activityParams.title);
			}
    		getActivityListener().setSize(activityParams.width,activityParams.height);
		}
	}
	
	
	public int getScreenWidth()
	{
		return activitylistener.getScreenWidth();
	}
	public int getScreenHeight()
	{
		return activitylistener.getScreenHeight();
	}
	public void setGravity(int gravity)
	{
		activitylistener.setGravity(gravity);
	}
	public void setPosition(int x,int y)
	{
		activitylistener.setPosition(x, y);
	}
	public void addPosition(int offsetx,int offsety)
	{
		activitylistener.addPosition(offsetx, offsety);
	}
	public void setSize(int width,int height)
	{
		activitylistener.setSize(width, height);
	}
	public void addSize(int offsetwidth,int offsetheight)
	{
		activitylistener.addSize(offsetwidth, offsetheight);
	}
	public void setTitle(String title)
	{
		activitylistener.setTitle(title);
	}
	public void setIcon(int iconid)
	{
		activitylistener.setIcon(iconid);
	}
	public void switchFullScreen()
	{
		activitylistener.switchFullScreen();
	}
	public void setFullScreen(boolean isFull)
	{
		activitylistener.setFullScreen(isFull);
	}
	public boolean isFullScreen()
	{
		return activitylistener.isFullScreen();
	}
	public ImageView getCloseBtn()
	{
		return activitylistener.getCloseBtn();
	}
	public TextView getTitleText()
	{
		return activitylistener.getTitleText();
	}
	public ImageView getMaxBtn()
	{
		return activitylistener.getMaxBtn();
	}
	public ImageView getMinBtn()
	{
		return activitylistener.getMinBtn();
	}
	public ImageView getHelpBtn()
	{
		return activitylistener.getHelpBtn();
	}
	public ImageView getTitleIcon()
	{
		return activitylistener.getTitleIcon();
	}
	public RelativeLayout getTitleBar()
	{
		return activitylistener.getTitleBar();
	}
	
    
}
