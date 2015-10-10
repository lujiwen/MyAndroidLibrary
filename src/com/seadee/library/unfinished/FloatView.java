package com.seadee.library.unfinished;

import com.seadee.library.utils.SDDisplay;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class FloatView extends LinearLayout {
	@SuppressWarnings("unused")
	private final String TAG="FloatView";
    public static int viewWidth;  
    public static int viewHeight;  
    private WindowManager windowManager;  
    private WindowManager.LayoutParams mParams;  
    private float xInScreen;  
    private float yInScreen;  
    private float xDownInScreen;  
    private float yDownInScreen;  
    private float xInView;  
    private float yInView; 
    Context context;
    public static int rightX;
    public static int rightY;

	public FloatView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
		windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);  
        ImageView option=new ImageView(context);
 //       option.setImageResource(R.drawable.icon_bookmark_settings);
   //     option.setBackgroundResource(R.drawable.ic_menu_background);
        option.setPadding(5, 5, 5, 5);
        addView(option);
        viewWidth = option.getLayoutParams().width;  
        viewHeight = option.getLayoutParams().height;
        rightX=option.getRight();
        rightY=option.getTop();
	}

	private void updateViewPosition() {  
		if(mParams==null)
			return;
        mParams.x = (int) (xInScreen - xInView);  
        mParams.y = (int) (yInScreen - yInView); 
        windowManager.updateViewLayout(this, mParams);  
    }  

	 @Override
	public boolean onHoverEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onHoverEvent(event);
	}


	@Override  
	    public boolean onTouchEvent(MotionEvent event) {  
	        switch (event.getAction()) {  
	        case MotionEvent.ACTION_DOWN:  
	            xInView = event.getX();  
	            yInView = event.getY();  
	            xDownInScreen = event.getRawX();  
	            yDownInScreen = event.getRawY();  
	            xInScreen = event.getRawX();  
	            yInScreen = event.getRawY();  
	            break;  
	        case MotionEvent.ACTION_MOVE:  
	            xInScreen = event.getRawX();  
	            yInScreen = event.getRawY();  
	            updateViewPosition();  
	            break;  
	        case MotionEvent.ACTION_UP:  
	            if (Math.abs(xInScreen-xDownInScreen)<3  && Math.abs(yInScreen-yDownInScreen)<3) {  

	            }  
	            break;  
	        default:  
	            break;  
	        }  
	        return false;  
	    }  

	 
	 public void setParams(WindowManager.LayoutParams params) {  
	        mParams = params;  
	    } 
	 
	 public static FloatView createFloatView(Activity activity) {  
	        WindowManager windowManager = activity.getWindowManager();  
	        int screenWidth = SDDisplay.getDisplayMetrics(activity).widthPixels;
	        FloatView smallWindow = new FloatView(activity);   
	        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();  
	        lp.type = WindowManager.LayoutParams.TYPE_PHONE;  
	                lp.format = PixelFormat.RGBA_8888;  
	                lp.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL  
	                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;  
	                lp.gravity = Gravity.LEFT | Gravity.TOP;  
	                lp.width = FloatView.viewWidth;  
	                lp.height = FloatView.viewHeight;  
	                lp.x = screenWidth;  
	        smallWindow.setParams(lp);
	        windowManager.addView(smallWindow, lp);  
	        
	        return smallWindow;

	    }  
	  
	    public static void removeFloatView(Activity activity,FloatView smallWindow) {  
	    	WindowManager windowManager = activity.getWindowManager();  
	        if (smallWindow != null) {  
	            windowManager.removeView(smallWindow);  
	            smallWindow = null;  
	        }
	    }  
	      
}
