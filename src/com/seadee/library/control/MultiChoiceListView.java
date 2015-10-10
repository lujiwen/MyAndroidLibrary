package com.seadee.library.control;

import java.util.ArrayList;
import java.util.List;

import com.seadee.library.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;


public class MultiChoiceListView extends SDListView  {

	public MultiChoiceListView(Context context, AttributeSet attrs,List<MultiValue> values) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context,attrs, values);
	}
	
	public MultiChoiceListView(Context context,AttributeSet attrs,String[] data,boolean[] defvalue)
	{
		super(context, attrs);
		boolean[] ch = new boolean[data.length];
		
		for(int i=0;i<ch.length;i++)
		{
			ch[i] = false;
		}
		
		if(defvalue != null)
		{
			int length = ch.length > defvalue.length?defvalue.length:ch.length;
			for(int i=0; i<length;i++)
			{
				ch[i] = defvalue[i];
			}
		}
		List<MultiValue> values = new ArrayList<MultiValue>();
		for(int i=0;i<data.length;i++)
		{
			values.add(new MultiValue(data[i],ch[i]));
		}
		init(context,attrs,values);
	}
	
	MultiListAdaper multiAdapter;
	
	private void init(Context context,AttributeSet attrs,List<MultiValue> values)
	{
		this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		this.setCacheColorHint(Color.TRANSPARENT);
		this.setDivider(new ColorDrawable(Color.LTGRAY));
		this.setDividerHeight(1);
		this.setAlwaysDrawnWithCacheEnabled(true);
		multiAdapter = new MultiListAdaper(context,values);
		this.setAdapter(multiAdapter);
	}

	public List<MultiValue> getListMultiValue(){
		return multiAdapter.getListMultiValue();
	}
	
	public MultiListAdaper getMultiListAdaper(){
		return multiAdapter;
	}
	
	public boolean[] getChoosedItems()
	{
		return multiAdapter.getChoosedItems();
	}
	
	public void setHoverColor(int hovercolor)
	{
		multiAdapter.setHoverColor(hovercolor);
	}
	
	public class MultiHolder{
		TextView tv;
		CheckBox cb;
	}
	
	public static class MultiValue{
		String label;
		boolean ischecked;
		
		public MultiValue(String label,boolean ischecked)
		{
			this.label = label;
			this.ischecked = ischecked;
		}
	}
	
	public class MultiListAdaper extends ArrayAdapter<MultiValue>
	{
		
		LayoutInflater inflater;
		List<MultiValue> values;
		int hovercolor=Color.LTGRAY;
		boolean[] result;
		
		public List<MultiValue> getListMultiValue(){
			return values;
		}
		
		public boolean[] getChoosedItems()
		{
			return result;
		}
		
		public void setHoverColor(int hovercolor)
		{
			this.hovercolor = hovercolor;
		}
		
		public MultiListAdaper(Context context,List<MultiValue> values) {
			super(context, R.layout.sd_multi_choice, values);
			// TODO Auto-generated constructor stub
			inflater = LayoutInflater.from(context);
			this.values = values;
			result = new boolean[values.size()];
			for(int i = 0;i < result.length;i++)
			{
				result[i] = values.get(i).ischecked;
			}
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			MultiHolder holder = new MultiHolder();
			
			if(convertView == null)
			{
				convertView = inflater.inflate(R.layout.sd_multi_choice, null);
				
				holder.tv = (TextView) convertView.findViewById(R.id.text);
				holder.cb = (CheckBox) convertView.findViewById(R.id.check);
				
				convertView.setOnHoverListener(mOnHoverListener);
				convertView.setOnTouchListener(mOnTouchListener);
				final CheckBox cb=holder.cb;
				final int pos=position;
				convertView.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						cb.performClick();
					}
					
				});

				cb.setOnCheckedChangeListener(new OnCheckedChangeListener(){

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						result[pos] = isChecked;
					}
					
				});
				
				convertView.setTag(holder);
			}
			else
			{
				holder = (MultiHolder) convertView.getTag();
				holder.tv.setText("");
				holder.cb.setChecked(false);
			}

			holder.tv.setText(values.get(position).label);
			holder.cb.setChecked(values.get(position).ischecked);
			
			return convertView;
		}
		
		private OnHoverListener mOnHoverListener = new OnHoverListener()
		{

			@Override
			public boolean onHover(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction())
				{
				case MotionEvent.ACTION_HOVER_ENTER:
					v.setBackgroundColor(hovercolor);
					break;
				case MotionEvent.ACTION_HOVER_EXIT:
					v.setBackgroundColor(Color.TRANSPARENT);
					break;
				}
				return false;
			}
			
		};
		
		private OnTouchListener mOnTouchListener = new View.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction())
				{
				case MotionEvent.ACTION_DOWN:
					v.setBackgroundColor(hovercolor);
					break;
				case MotionEvent.ACTION_UP:
					v.setBackgroundColor(Color.TRANSPARENT);
					break;
				}
				return false;
			}
			
		};
	}
	
	

}
