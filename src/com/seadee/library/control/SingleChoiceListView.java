package com.seadee.library.control;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.seadee.library.R;

public class SingleChoiceListView extends SDListView {
	
	public SingleChoiceListView(Context context, AttributeSet attrs,String[] values,int choosed) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context,attrs, values,choosed);
	}
	
	SingleListAdaper singleAdapter;
	
	private void init(Context context,AttributeSet attrs,String[] values,int choosed)
	{
		this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		this.setCacheColorHint(Color.TRANSPARENT);
		this.setDivider(new ColorDrawable(Color.LTGRAY));
		this.setDividerHeight(1);
		this.setAlwaysDrawnWithCacheEnabled(true);
		singleAdapter = new SingleListAdaper(context,values,choosed);
		this.setAdapter(singleAdapter);
	}
	
	public void setHoverColor(int hovercolor)
	{
		singleAdapter.setHoverColor(hovercolor);
	}
	
	public SingleListAdaper getSingleListAdaper(){
		return singleAdapter;
	}
	
	public int getChoosedItem()
	{
		return singleAdapter.getChoosedItem();
	}
	
	public class SingleHolder{
		TextView tv;
		RadioButton rb;
	}
	
	
	public class SingleListAdaper extends ArrayAdapter<String>
	{
		
		LayoutInflater inflater;
		int hovercolor = Color.LTGRAY;
		String[] values;
		int choosed;
		
		public String[] getListSingleValue(){
			return values;
		}
		
		public int getChoosedItem()
		{
			return choosed;
		}

		public void setHoverColor(int hovercolor)
		{
			this.hovercolor = hovercolor;
		}
		
		public SingleListAdaper(Context context,String[] values,int choosed) {
			super(context, R.layout.sd_single_choice, values);
			// TODO Auto-generated constructor stub
			inflater = LayoutInflater.from(context);
			this.values = values;
			this.choosed=choosed;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			SingleHolder holder = new SingleHolder();

			if(convertView == null)
			{
				convertView = inflater.inflate(R.layout.sd_single_choice, null);
				
				holder.tv = (TextView) convertView.findViewById(R.id.text);
				holder.rb = (RadioButton) convertView.findViewById(R.id.radio);

				convertView.setOnHoverListener(mOnHoverListener);
				convertView.setOnTouchListener(mOnTouchListener);
				final RadioButton rb = holder.rb;
				
				convertView.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						rb.performClick();
					}
					
				});
				
				final int pos=position;
				holder.rb.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						choosed = pos;
						SingleListAdaper.this.notifyDataSetChanged();
					}
					
				});

				convertView.setTag(holder);
			}
			else
			{
				holder = (SingleHolder) convertView.getTag();
				holder.tv.setText("");
				holder.rb.setChecked(false);
			}
			
			holder.tv.setText(values[position]);
			holder.rb.setChecked(position == choosed);
			
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
					v.setBackgroundColor(Color.LTGRAY);
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
