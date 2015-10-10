package com.seadee.library.control;

import com.seadee.library.R;
import com.seadee.library.activity.SDActivity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * virtualink:label string
 * virtualink:checked boolean
 * virtualink:textsize dimension
 * virtualink : hint string,set the hint
 * virtualink:hovercolor color
 * virtualink:backcolor color
 */

public class SDCheckText extends LinearLayout {
	
	public SDCheckText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context,null);
	}

	public SDCheckText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context,attrs);
	}

	public SDCheckText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context,attrs);
	}
	
	View view;
	TextView tv;
	CheckBox cb;
	private void init(Context context,AttributeSet attrs)
	{
		LayoutInflater inflater = LayoutInflater.from(context);
		view = inflater.inflate(R.layout.sd_check_text, this);
		tv = (TextView)view.findViewById(R.id.text);
		cb = (CheckBox)view.findViewById(R.id.check);

		if (attrs != null)
		{
			 TypedArray type = context.obtainStyledAttributes(attrs,R.styleable.virtualink);
		
			 String label = type.getString(R.styleable.virtualink_label);
			 boolean ischecked =  type.getBoolean(R.styleable.virtualink_checked, false);
			 int textsize = type.getDimensionPixelSize(R.styleable.virtualink_textsize, 18);
			 hint = type.getString(R.styleable.virtualink_hint);
			 hovercolor = type.getColor(R.styleable.virtualink_hovercolor, Color.LTGRAY);
			 backcolor = type.getColor(R.styleable.virtualink_backcolor, Color.WHITE);
			 
			 this.setLabel(label);
			 this.setChecked(ischecked);
			 this.setTextSize(textsize);
			 this.setBackgroundColor(backcolor);
			 
			 type.recycle();
		}
		
		this.setOnClickListener(mOnClickListener);	
	}

	private OnClickListener mOnClickListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			cb.performClick();
		}
		 
	};
	

	
	public TextView getTextView()
	{
		return tv;
	}
	
	public void setTextSize(float size)
	{
		tv.setTextSize(size);
	}

	public void setLabel(String label)
	{
		tv.setText(label);
	}
	
	public String getLabel()
	{
		return tv.getText().toString();
	}
	
	public CheckBox getCheckBox()
	{
		return cb;
	}
	
	public void setChecked(boolean ischecked)
	{
		cb.setChecked(ischecked);
	}
	
	public boolean getChecked()
	{
		return cb.isChecked();
	}
	
	@Override
	public boolean onHoverEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction())
		{
		case MotionEvent.ACTION_HOVER_ENTER:
			this.setBackgroundColor(hovercolor);
			if(hintTv != null && hint != null && !hint.isEmpty())
			{
				hintTv.setText(hint);
			}
			break;
		case MotionEvent.ACTION_HOVER_EXIT:
			this.setBackgroundColor(backcolor);
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
			this.setBackgroundColor(hovercolor);	
			if(hintTv != null && hint != null && !hint.isEmpty())
			{
				hintTv.setText(hint);
			}
			break;
		case MotionEvent.ACTION_UP:
			this.setBackgroundColor(backcolor);	

			break;
		}
		return super.onTouchEvent(event);
	}
	
	TextView hintTv;
	String hint;
	int hovercolor = Color.LTGRAY;
	int backcolor = Color.WHITE;
	public void setHintTextView(TextView tv)
	{
		this.hintTv = tv;
	}
	
	public void setHint(String hint)
	{
		this.hint = hint;
	}

}
