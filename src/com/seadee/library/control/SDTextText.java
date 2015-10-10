package com.seadee.library.control;

import com.seadee.library.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * virtualink:label string
 * virtualink:textsize dimen
 * virtualink:content string
 * virtualink:inputtype int 
 * virtualink:inputenable boolean; enable or disable the EditText control 
 * virtualink : hint string,set the hint
 * virtualink:hovercolor color
 * virtualink:backcolor color
 */

public class SDTextText extends LinearLayout {
	
	public SDTextText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context,null);
	}

	public SDTextText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context,attrs);
	}

	public SDTextText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context,attrs);
	}
	
	View view;
	TextView ltv;
	TextView ctv;
	SDEditText et;
	boolean inputenable;
	
	private void init(Context context,AttributeSet attrs)
	{
		LayoutInflater inflater = LayoutInflater.from(context);
		view = inflater.inflate(R.layout.sd_text_text, this);
		ltv = (TextView)view.findViewById(R.id.text);
		ctv = (TextView)view.findViewById(R.id.content);
		et = (SDEditText)view.findViewById(R.id.input);
		
		if (attrs != null)
		{
			 TypedArray type = context.obtainStyledAttributes(attrs,R.styleable.virtualink);
		
			 String label = type.getString(R.styleable.virtualink_label);
			 int inputtype = type.getInt(R.styleable.virtualink_inputtype,1);
			 int textsize = type.getDimensionPixelSize(R.styleable.virtualink_textsize, 18);
			 String content = type.getString(R.styleable.virtualink_content);
			 inputenable = type.getBoolean(R.styleable.virtualink_inputenable, true);
			 hint = type.getString(R.styleable.virtualink_hint);
			 hovercolor = type.getColor(R.styleable.virtualink_hovercolor, Color.LTGRAY);
			 backcolor = type.getColor(R.styleable.virtualink_backcolor, Color.WHITE);
			 
			 this.setLabel(label);
			 this.setInputType(inputtype);
			 this.setTextSize(textsize);
			 this.setContentText(content);
			 this.setBackgroundColor(backcolor);
			 
			 type.recycle();
		}
		
		if(inputenable)
			this.setOnClickListener(mOnClickListener);
		et.setOnFocusChangeListener(mOnFocusChangeListener);
		et.setOnKeyListener(new OnKeyListener()
		{

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if(keyCode == KeyEvent.KEYCODE_ENTER)
				{
					setInputMode(false);
				}
				return false;
				
			}
			
		});
	}
	
	private OnClickListener mOnClickListener = new OnClickListener()
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			setInputMode(!(et.isFocused() && et.getVisibility() == View.VISIBLE));
		}
		
	};
	
	private OnFocusChangeListener mOnFocusChangeListener = new OnFocusChangeListener()
	{

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			setInputMode(hasFocus);
		}
		
	};
	

	public TextView getLabelTextView()
	{
		return ltv;
	}
	
	public void setLabel(String label)
	{
		ltv.setText(label);
	}
	
	public String getLabel()
	{
		return ltv.getText().toString();
	}
	
	public TextView getContentTextView()
	{
		return ctv;
	}
	
	public void setContentText(String content)
	{
		ctv.setText(content);
		et.setText(content);
	}
	
	public void setInputType(int inputtype)
	{
		ctv.setInputType(inputtype);
		et.setInputType(inputtype);
	}
	
	public String getContentText()
	{
		return et.getText().toString();
	}
	
	public void setTextSize(float size)
	{
		ltv.setTextSize(size);
		ctv.setTextSize(size);
		et.setTextSize(size);
	}
	
	public void setInputMode(boolean isInputMode)
	{
		if(isInputMode)
		{
			et.setVisibility(View.VISIBLE);
			ctv.setVisibility(View.GONE);
			et.requestFocus();
			et.setSelection(et.getText().length());
		}
		else
		{
			ctv.setText(et.getText().toString());
			et.setVisibility(View.GONE);
			ctv.setVisibility(View.VISIBLE);
		}
	}
	
	public boolean isInputMode()
	{
		return et.getVisibility()==View.VISIBLE?true:false;
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
