package com.seadee.library.control;
import com.seadee.library.R;
import com.seadee.library.common.SDParsePadding;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;


public class SDButton extends Button {

	/*
	 * virtualink : hint string,set the hint
	 */

	int paddingleft = 5;
	int paddingright = 5;
	int paddingtop = 5;
	int paddingbottom = 5;
	
	String hint = "";

	public SDButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		 init(context,null);
	}
	
	public SDButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context,attrs);
	}	

	public SDButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context,attrs);
	}
	
	public void setHint(String hint)
	{
		this.hint = hint;
	}
	
	private void init(Context context,AttributeSet attrs)
	{
		setBackgroundResource(R.drawable.sd_ic_btn_normal);
		if(attrs != null)
		{
			int padding = SDParsePadding.getPadding(context, attrs, 5);
			paddingleft = SDParsePadding.getPaddingLeft(context, attrs, 0);
			paddingtop = SDParsePadding.getPaddingTop(context, attrs, 0);
			paddingright = SDParsePadding.getPaddingRight(context, attrs, 0);
			paddingbottom = SDParsePadding.getPaddingBottom(context, attrs, 0);
			
			if(paddingleft == 0)
				paddingleft = padding;
			
			if(paddingtop == 0)
				paddingtop = padding;

			if(paddingright == 0)
				paddingright = padding;
			
			if(paddingbottom == 0)
				paddingbottom = padding;
			
			 TypedArray type = context.obtainStyledAttributes(attrs,R.styleable.virtualink);
			 hint = type.getString(R.styleable.virtualink_hint);
			 type.recycle();
		}
		
		this.setPadding(paddingleft, paddingtop, paddingright, paddingbottom);		
	}

	@Override
	public boolean onHoverEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction())
		{
		case MotionEvent.ACTION_HOVER_ENTER:
			setBackgroundResource(R.drawable.sd_ic_btn_hover);
			this.setPadding(paddingleft, paddingtop, paddingright, paddingbottom);	
			if(hintTv != null && hint != null && !hint.isEmpty())
			{
				hintTv.setText(hint);
			}
			break;
		case MotionEvent.ACTION_HOVER_EXIT:
			setBackgroundResource(R.drawable.sd_ic_btn_normal);
			this.setPadding(paddingleft, paddingtop, paddingright, paddingbottom);	
			if(hintTv != null && hint != null && !hint.isEmpty())
			{
				hintTv.setText("");
			}
			break;
		}
		return super.onHoverEvent(event);
	}
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			setBackgroundResource(R.drawable.sd_ic_btn_hover);
			this.setPadding(paddingleft, paddingtop, paddingright, paddingbottom);		
			if(hintTv != null && hint != null && !hint.isEmpty())
			{
				hintTv.setText(hint);
			}
			break;
		case MotionEvent.ACTION_UP:
			setBackgroundResource(R.drawable.sd_ic_btn_normal);
			this.setPadding(paddingleft, paddingtop, paddingright, paddingbottom);	
			if(hintTv != null && hint != null && !hint.isEmpty())
			{
				hintTv.setText("");
			}
			break;
		}
		return super.onTouchEvent(event);
	}
	
	

	public void setPadding(int value) {
		// TODO Auto-generated method stub
		this.paddingleft = value;
		this.paddingright = value;
		this.paddingtop = value;
		this.paddingbottom =  value;
		super.setPadding(value, value, value, value);
	}



	TextView hintTv;
	public void setHintTextView(TextView tv)
	{
		this.hintTv = tv;
	}

}
