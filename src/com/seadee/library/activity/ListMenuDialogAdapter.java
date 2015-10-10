package com.seadee.library.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.seadee.library.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnHoverListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ListMenuDialogAdapter extends android.widget.SimpleAdapter {
	
	public final static String TAG="SimpleAdapter";
	private Context context;
	LayoutInflater mInflater;
	String[] from;
	int[] to;
	List<Map<String,Object>> listItems;
	public boolean firstChange=true;
	LocalBroadcastManager localbroadcast;
	String broadcastname;
	int left,top,right,bottom;
	
	@SuppressWarnings("unchecked")
	public ListMenuDialogAdapter(Context context, List<? extends Map<String, ?>> data,
			int resource, String[] from, int[] to,String broadcastname) {
		super(context, data, resource, from, to);
		localbroadcast=LocalBroadcastManager.getInstance(context);
		this.context=context;
		mInflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.from=from;
		this.to=to;
		this.listItems=(ArrayList<Map<String,Object>>)data;
		this.broadcastname=broadcastname;
		// TODO Auto-generated constructor stub
	}
	
	static class ListViewHolder
	{
		ImageView icon;
		TextView text;
		TextView shortcut;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ListViewHolder holder;
		final int pos=position;

		if(convertView==null)
		{
			holder=new ListViewHolder();
			convertView=mInflater.inflate(R.layout.sd_menu_list_dialog_line,null);
			holder.icon=(ImageView)convertView.findViewById(R.id.image);
			holder.text=(TextView)convertView.findViewById(R.id.title);
			holder.shortcut=(TextView)convertView.findViewById(R.id.shortcut);
			
			
			left=convertView.getPaddingLeft();
            top=convertView.getPaddingTop();
            right=convertView.getPaddingRight();
            bottom=convertView.getPaddingBottom();
			convertView.setTag(holder);
			convertView.setOnHoverListener(mOnHoverListener);
			convertView.setOnTouchListener(mOnTouchListener);
			convertView.setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(broadcastname);
					intent.putExtra("MenuIndex", pos);
					localbroadcast.sendBroadcast(intent);
				}
				
			});
		}
		else
		{
			holder=(ListViewHolder)convertView.getTag();
			holder.text.setText("");
			holder.icon.setImageDrawable(null);
			holder.shortcut.setText("");
		}
		
		Map<String,Object> map = listItems.get(position);
		String title=(String)map.get(from[0]);
		int image=(Integer)map.get(from[1]);
		
		try{
			if(image != 0)
				holder.icon.setImageDrawable(context.getResources().getDrawable(image));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(title!=null && !title.isEmpty())
		{
			String[] array=title.split(",");
			holder.text.setText(array[0]);
			if(array.length>=2)
			{
				holder.shortcut.setText(array[1]);
			}
		}
		
		return convertView;
	}
	
	private OnHoverListener mOnHoverListener = new OnHoverListener(){

        @Override
        public boolean onHover(View v, MotionEvent event) {
            // TODO Auto-generated method stub
            switch(event.getAction())
            {
                case MotionEvent.ACTION_HOVER_ENTER:
                    v.setBackgroundResource(R.drawable.sd_ic_list_hover);
                    v.setPadding(left,top ,right ,bottom );
                    break;
                case MotionEvent.ACTION_HOVER_EXIT:
                    v.setBackgroundColor(Color.TRANSPARENT);
                    v.setPadding(left,top ,right ,bottom );
                    break;
            }
            return false;
        }
         
     };
     
     private OnTouchListener mOnTouchListener = new OnTouchListener(){

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			 switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    v.setBackgroundResource(R.drawable.sd_ic_list_hover);
                    v.setPadding(left,top ,right ,bottom );
                    break;
                case MotionEvent.ACTION_UP:
                    v.setBackgroundColor(Color.TRANSPARENT);
                    v.setPadding(left,top ,right ,bottom );
                    break;
            }
			return false;
		}
   	  
     };
	
}
