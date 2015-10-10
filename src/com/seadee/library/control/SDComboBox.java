package com.seadee.library.control;

import java.util.ArrayList;
import java.util.List;

import com.seadee.library.R;
import com.seadee.library.control.SDListDialog.LDData;
import com.seadee.library.control.SDListDialog.SDListArrayAdapter.ListViewHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;


/*
 * virtualink:entries string-array,eg:virtualink:entries="@array/listarray"
 */

public class SDComboBox extends SDEditText {
	
	Context context;
	int num = 0;
	float bi = 1.1f;
	
	public SDComboBox(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context,null);
	}

	public SDComboBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context,attrs);
	}

	public SDComboBox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context,attrs);
	}
	
	SDListDialog listdialog;
	 List<LDData> listdata;
	 CharSequence[] value;
	
	private void init(Context context,AttributeSet attrs)
	{
        Drawable drawable = getResources().getDrawable(R.drawable.sd_icon_action_list);
        drawable.setBounds(0, 0, 32, 32);
        setCompoundDrawables(null, null, drawable, null); 
        this.setInputable(false);
        this.setFocusable(false);
        
        listdata=new ArrayList<LDData>();
        if(attrs != null)
        {
        	 TypedArray type = context.obtainStyledAttributes(attrs,R.styleable.virtualink);
        	 value = type.getTextArray(R.styleable.virtualink_entries);
        	 
        	 for(CharSequence t:value)
        	 {
        		 listdata.add(new LDData(0,t.toString()));
        	 }
        	 
        	 type.recycle();
        }

        listdialog =  new SDListDialog(context,true,listdata,null,null);
        
        this.setOnClickListener(mOnClickListener);
        listdialog.getListView().setOnItemClickListener(mOnItemClickListener);
	}
	
	public void setDefault(int n)
	{
		if(n<listdata.size() && n>=0)
			this.setComboText(listdata.get(n).listitem);
	}
	
	public int getChoosed()
	{
		CharSequence cur=this.getText();
		for(int i=0;i<value.length;i++)
		{
			if(cur == value[i])
				return i;
		}
		return -1;
	}
	
	public void setVerNum(int num,float bi){
		this.num = (num<=0?this.num:num);
		this.bi = (bi<=0?this.bi:bi);
	}
	
	private OnItemClickListener mOnItemClickListener = new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			setComboText(((ListViewHolder)view.getTag()).text.getText().toString());
			listdialog.cancel();
		}
		
	};
	
	private OnClickListener mOnClickListener = new OnClickListener()
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(listdialog.isShowing())
			{
				listdialog.cancel();
			}
			else
			{
				listdialog.setSize(SDComboBox.this.getWidth(), (int)(SDComboBox.this.getHeight()* bi * (num==0?listdata.size():num)));
				int[] pos = new int[2];
				SDComboBox.this.getLocationOnScreen(pos);
				listdialog.setGravity(Gravity.LEFT|Gravity.TOP,pos[0], pos[1]+SDComboBox.this.getHeight());
				listdialog.show();
				
			}
		}
		
	};
	
	private InputFilter[] editfilter = new InputFilter[]{
			new InputFilter(){
				
				@Override
				public CharSequence filter(CharSequence source, int start,
						int end, Spanned dest, int dstart, int dend) {
					// TODO Auto-generated method stub
					return source.length() < 1 ? dest.subSequence(dstart, dend):"";
				}
			}
	};
	
	private InputFilter[] nullfilter = new InputFilter[]{
			new InputFilter(){
				
				@Override
				public CharSequence filter(CharSequence source, int start,
						int end, Spanned dest, int dstart, int dend) {
					// TODO Auto-generated method stub
					return null;
				}
			}
	};
	
	public void setComboText(String content)
	{
		setInputable(true);
		this.setText(content);
		setInputable(false);
	}

	public void setInputable(boolean isInputable)
	{
		 if (isInputable) {  
			 this.setFilters(nullfilter);
		 }
		 else
		 {
			 this.setFilters(editfilter);
		 }
	         
	}

}
