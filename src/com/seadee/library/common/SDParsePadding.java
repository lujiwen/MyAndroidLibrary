package com.seadee.library.common;

import com.seadee.library.utils.SDDisplay;

import android.content.Context;
import android.util.AttributeSet;

public class SDParsePadding {
	
	private static int getPaddingValue(Context context,AttributeSet attrs,String attr,int defalutvalue) {
		// TODO Auto-generated method stub
		if(attrs == null)
		{
			return defalutvalue;
		}
		String padding=attrs.getAttributeValue("http://schemas.android.com/apk/res/android", attr);
		if(padding == null || padding.isEmpty())
		{
			return defalutvalue;
		}
		int paddingvalue = defalutvalue;
		if(padding!=null && !padding.isEmpty())
		{
			char n;
			String result="";
			for(int i=0;i<padding.length();i++)
			{
				n=padding.charAt(i);
				if(n>='0' && n<= '9')
					result+=n;
				else
					break;
			}
			try{
				paddingvalue=Integer.valueOf(result);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		if(padding.contains("dip") || padding.contains("dp"))
		{
			return SDDisplay.dip2px(context, paddingvalue);
		}
		else if(padding.contains("px"))
		{
			return paddingvalue;
		}
		else		
			return paddingvalue;
	}
	
	
	public static int getPadding(Context context,AttributeSet attrs,int defalutvalue)
	{
		return  getPaddingValue(context,attrs,"padding",defalutvalue);
	}
	
	public static int getPaddingLeft(Context context,AttributeSet attrs,int defalutvalue)
	{
		return  getPaddingValue(context,attrs,"paddingLeft",defalutvalue);
	}
	
	public static int getPaddingTop(Context context,AttributeSet attrs,int defalutvalue)
	{
		return  getPaddingValue(context,attrs,"paddingTop",defalutvalue);
	}
	
	public static int getPaddingRight(Context context,AttributeSet attrs,int defalutvalue)
	{
		return  getPaddingValue(context,attrs,"paddingRight",defalutvalue);
	}
	
	public static int getPaddingBottom(Context context,AttributeSet attrs,int defalutvalue)
	{
		return  getPaddingValue(context,attrs,"paddingBottom",defalutvalue);
	}
	
}
