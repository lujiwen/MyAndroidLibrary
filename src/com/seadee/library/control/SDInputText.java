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
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * virtualink:label string
 * virtualink:content string
 * virtualink:textsize integer
 * virtualink:singleline string "true"
 * virtualink:inputtype int 0x00000001(text) 0x00000002(number) 0x00000081(pwd), from EditText.InputType
 * virtualink:maxlength int
 * virtualink:spaceenable boolean,set the right view visibility.
 * virtualink : hint string,set the hint
 * virtualink:hovercolor color
 * virtualink:backcolor color
 */

public class SDInputText extends LinearLayout {
	TextView tv;
	SDEditText et;
	View right;
	View view;

	public SDInputText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context,null);
	}

	public SDInputText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context,attrs);
	}
	
	private void init(Context context,AttributeSet attrs)
	{
		LayoutInflater inflater = LayoutInflater.from(context);
		view = inflater.inflate(R.layout.sd_input_text, this);
		tv = (TextView)view.findViewById(R.id.textView1);
		et = (SDEditText)view.findViewById(R.id.sDEditText1);
		right = findViewById(R.id.right);
		
		if (attrs != null)
		{
			 TypedArray type = context.obtainStyledAttributes(attrs,R.styleable.virtualink);
			 String label = type.getString(R.styleable.virtualink_label);
			 String content = type.getString(R.styleable.virtualink_content);
			 int textsize = type.getDimensionPixelSize(R.styleable.virtualink_textsize, 18);
			 boolean singleline = type.getBoolean(R.styleable.virtualink_singleline, false);
			 int inputtype = type.getInteger(R.styleable.virtualink_inputtype, 0x00000001);
			 int maxlength = type.getInteger(R.styleable.virtualink_maxlength,-1);
			 boolean spaceenable = type.getBoolean(R.styleable.virtualink_spaceenable,false);
			 hint = type.getString(R.styleable.virtualink_hint);
			 hovercolor = type.getColor(R.styleable.virtualink_hovercolor, Color.LTGRAY);
			 backcolor = type.getColor(R.styleable.virtualink_backcolor, Color.WHITE);
			 
			 setLabel(label);
			 setContent(content);
			 setTextSize(textsize);
			 setSingleLine(singleline);
			 setInputType(inputtype);
			 setMaxLength(maxlength);
			 setSpaceEnable(spaceenable);
			 this.setBackgroundColor(backcolor);
			 type.recycle(); 

		}
		
		setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getEditText().requestFocus();
			}
			
		});
		et.setOnFocusChangeListener(mOnFocusChangeListener);
	}
	
	private OnFocusChangeListener mOnFocusChangeListener = new OnFocusChangeListener(){

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			if(hasFocus)
			{
				view.setBackgroundColor(hovercolor);
				if(hintTv != null && hint != null && !hint.isEmpty())
				{
					hintTv.setText(hint);
				}
			}
			else
			{
				view.setBackgroundColor(backcolor);
				if(hintTv != null && hint != null && !hint.isEmpty())
				{
					hintTv.setText(hint);
				}
			}
		}
		
	};
	

	public View getView()
	{
		return view;
	}
	
	public SDEditText getEditText()
	{
		return et;
	}
	
	public TextView getTextView()
	{
		return tv;
	}
	
	public void setTextSize(float size)
	{
		et.setTextSize(size);
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
	
	public void setContent(String content)
	{
		et.setText(content);
	}
	
	public void setText(String content)
	{
		et.setText(content);
	}
	
	public String getText()
	{
		return et.getText().toString();
	}
	
	public String getContent()
	{
		return et.getText().toString();
	}
	
	public void setSingleLine(boolean isSingleLine)
	{
		et.setSingleLine(isSingleLine);
	}
	
	public void setInputType(int type)
	{
		et.setInputType(type);
	}
	
	public void setMaxLength(int maxems)
	{
		et.setMaxEms(maxems);
	}
	
	public void setSpaceEnable(boolean enable)
	{
		right.setVisibility(enable?View.VISIBLE:View.GONE);
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
