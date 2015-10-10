package com.seadee.library.control;

import com.seadee.library.R;
import com.seadee.library.common.SDParsePadding;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

public class SDEditText extends EditText {
	
	/*
	 * virtualink:hint string
	 */
	
	int paddingleft = 5;
	int paddingright = 5;
	int paddingtop = 5;
	int paddingbottom = 5;
	
	String hint = "";

	public SDEditText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context,null);
	}
	
	public SDEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context,attrs);
	}

	public SDEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context,attrs);
	}
	
	private void init(Context context,AttributeSet attrs)
	{
		setBackgroundResource(R.drawable.sd_icon_edit_background);
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
			this.setPadding(paddingleft, paddingtop, paddingright, paddingbottom);	
			if(hintTv != null && hint != null && !hint.isEmpty())
			{
				hintTv.setText(hint);
			}
			break;
		case MotionEvent.ACTION_HOVER_EXIT:
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
			this.setPadding(paddingleft, paddingtop, paddingright, paddingbottom);	
			if(hintTv != null && hint != null && !hint.isEmpty())
			{
				hintTv.setText(hint);
			}
			break;
		case MotionEvent.ACTION_UP:
			this.setPadding(paddingleft, paddingtop, paddingright, paddingbottom);		
			if(hintTv != null && hint != null && !hint.isEmpty())
			{
				hintTv.setText("");
			}
			break;
		}
		return super.onTouchEvent(event);
	}
	
	public void setPasswdVisible(boolean isVisible)
    {
        if(isVisible)
            setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        else
           setTransformationMethod(PasswordTransformationMethod.getInstance());
    }
    
    public void oppositePwdVisible()
    {
        setPasswdVisible(getTransformationMethod()==PasswordTransformationMethod.getInstance());
    }

    @Override
	public void setPadding(int left, int top, int right, int bottom) {
		// TODO Auto-generated method stub
		this.paddingleft = left;
		this.paddingright = right;
		this.paddingtop = top;
		this.paddingbottom =  bottom;
		super.setPadding(left, top, right, bottom);
	}
    
    TextView hintTv;
	public void setHintTextView(TextView tv)
	{
		this.hintTv = tv;
	}
	
	public void setHint(String hint)
	{
		this.hint = hint;
	}
	
}
