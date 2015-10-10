package com.seadee.library.utils;

import com.seadee.library.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SDToast extends android.widget.Toast{
	
	static final String TAG = "Toast";
	Context context;
	private static int gravity=Gravity.RIGHT | Gravity.BOTTOM;
	private static int xoffset=0;
	private static int yoffset=0;
	
	public interface OnContentClickListener
	{
		public void OnContentClick();
	}
	
	private OnContentClickListener onContentClickListener;
	
	
    protected SDToast(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
	}
    
    public SDToast(Context context,OnContentClickListener onContentClickListener) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.onContentClickListener = onContentClickListener;
	}
    
    
    public void setGravity(int gravity,int xoffset,int yoffset)
    {
    	SDToast.gravity=gravity;
    	SDToast.xoffset=SDDisplay.px2dip(context,xoffset);
    	SDToast.yoffset=SDDisplay.px2dip(context,yoffset);
    }
    
    public static Toast makeText(final Context context, int textid, int duration) {
    	return makeText(context, context.getString(textid), duration);
    }
    
    public static Toast makeText(final Context context, CharSequence text, int duration) {
    	LayoutInflater inflater = LayoutInflater.from(context);
		View layout = inflater.inflate(R.layout.sd_custom_notify,null);
		ImageView iv = (ImageView)layout.findViewById(R.id.imageMsg);
   	 	TextView tv=(TextView)layout.findViewById(R.id.textViewShowMsg);
   	 	tv.setText(text);
   	 	Toast toast = new Toast(context.getApplicationContext());
   	 	toast.setGravity(gravity, xoffset, yoffset);
   	 	toast.setDuration(duration);
   	 	toast.setView(layout);
     	return toast;
    }
    
    public static Toast makeText(final Context context, CharSequence text, int duration,final OnContentClickListener onContentClickListener) {
    	LayoutInflater inflater = LayoutInflater.from(context);
		View layout = inflater.inflate(R.layout.sd_custom_notify,null);
		ImageView iv = (ImageView)layout.findViewById(R.id.imageMsg);
   	 	TextView tv=(TextView)layout.findViewById(R.id.textViewShowMsg);
   	 	Toast toast = new Toast(context.getApplicationContext());
   	 	toast.setGravity(gravity, xoffset, yoffset);
   	 	toast.setDuration(duration);
   	 	toast.setView(layout);
	    tv.setText(text);
		 tv.setOnClickListener(new OnClickListener(){
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(onContentClickListener != null)
					onContentClickListener.OnContentClick();
			}
			 
		 });
		 iv.setOnClickListener(new OnClickListener(){
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(onContentClickListener != null)
					onContentClickListener.OnContentClick();
			}
			 
		 });
		 return toast;
    }
}
