package com.seadee.library.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnHoverListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.seadee.library.activity.SDActivity;

public class SDRegEventListener {
	
	public static void registerHoverListener(final View view,final int enterImageId,final int exitImageId,final TextView tv, final String hint)
	{
		final int left=view.getPaddingLeft();
		final int top=view.getPaddingTop();
		final int right=view.getPaddingRight();
		final int bottom=view.getPaddingBottom();
		view.setOnHoverListener(new OnHoverListener(){

			@Override
			public boolean onHover(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction())
				{
				case MotionEvent.ACTION_HOVER_ENTER:
					v.setBackgroundResource(enterImageId);
					v.setPadding(left,top ,right ,bottom );
					if(tv != null && hint !=null && !hint.isEmpty())
						tv.setText(hint);
					break;
				case MotionEvent.ACTION_HOVER_EXIT:
					if(exitImageId != 0)
						v.setBackgroundResource(exitImageId);
					else
						v.setBackgroundColor(Color.TRANSPARENT);
					v.setPadding(left,top ,right ,bottom );
					if(tv != null)
						tv.setText("");
					break;
				}
				return false;
			}
			
		});
		
		view.setOnTouchListener(new OnTouchListener(){
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction())
				{
				case MotionEvent.ACTION_DOWN:
					view.setBackgroundResource(enterImageId);
					v.setPadding(left,top ,right ,bottom );
					if(tv != null && hint !=null && !hint.isEmpty())
						tv.setText(hint);
					break;
				case MotionEvent.ACTION_UP:
					if(exitImageId != 0)
						v.setBackgroundResource(exitImageId);
					else
						v.setBackgroundColor(Color.TRANSPARENT);
					v.setPadding(left,top ,right ,bottom);
					if(tv != null && hint !=null && !hint.isEmpty())
						tv.setText("");
					break;
				}
				return false;
			}
			
		});
	}
	
	public static void broadcastShowHint(Context context,String hint)
	{
		if(hint != null)
		{
			Intent intent=new Intent(SDActivity.HINTBROADCASTNAME);
			intent.putExtra("HINT", hint);
			LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
		}
	}
	
	public static void registerHoverListener(final View view,final int enterImageId,final int exitImageId)
	{
		final int left=view.getPaddingLeft();
		final int top=view.getPaddingTop();
		final int right=view.getPaddingRight();
		final int bottom=view.getPaddingBottom();
		view.setOnHoverListener(new OnHoverListener(){

			@Override
			public boolean onHover(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction())
				{
				case MotionEvent.ACTION_HOVER_ENTER:
					v.setBackgroundResource(enterImageId);
					v.setPadding(left,top ,right ,bottom );
					
					break;
				case MotionEvent.ACTION_HOVER_EXIT:
					if(exitImageId != 0)
						v.setBackgroundResource(exitImageId);
					else
						v.setBackgroundColor(Color.TRANSPARENT);
					v.setPadding(left,top ,right ,bottom );
					break;
				}
				return false;
			}
			
		});
		
		view.setOnTouchListener(new OnTouchListener(){
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction())
				{
				case MotionEvent.ACTION_DOWN:
					view.setBackgroundResource(enterImageId);
					v.setPadding(left,top ,right ,bottom );
					break;
				case MotionEvent.ACTION_UP:
					if(exitImageId != 0)
						v.setBackgroundResource(exitImageId);
					else
						v.setBackgroundColor(Color.TRANSPARENT);
					v.setPadding(left,top ,right ,bottom );
					break;
				}
				return false;
			}
			
		});
	}
	
	public static void setImageSource(final ImageView imageview,final Drawable primaryDrawable,final Drawable hoverDrawable)
	{
		if(imageview!=null)
		{
			imageview.setVisibility(View.VISIBLE);
			imageview.setImageDrawable(primaryDrawable);
			imageview.setOnHoverListener(new OnHoverListener(){
	
				@Override
				public boolean onHover(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					switch(event.getAction())
					{
					case MotionEvent.ACTION_HOVER_ENTER:
						imageview.setImageDrawable(hoverDrawable);
						break;
					case MotionEvent.ACTION_HOVER_EXIT:
						if(hoverDrawable == null)
							imageview.setImageDrawable(new ColorDrawable(Color.TRANSPARENT));
						else
							imageview.setImageDrawable(primaryDrawable);
						break;
					default:
						break;
					}
					return false;
				}
				
			});
			imageview.setOnTouchListener(new OnTouchListener(){
	
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch(event.getAction())
					{
					case MotionEvent.ACTION_DOWN:
						imageview.setImageDrawable(hoverDrawable);
						break;
					case MotionEvent.ACTION_UP:
						if(hoverDrawable == null)
							imageview.setImageDrawable(new ColorDrawable(Color.TRANSPARENT));
						else
						break;
					}
					return false;
				}
				
			});
		}
	}
}
