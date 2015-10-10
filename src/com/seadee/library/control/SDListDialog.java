package com.seadee.library.control;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnHoverListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.seadee.library.R;
import com.seadee.library.control.SDListDialog.LDData;
import com.seadee.library.utils.FloatDialog;


public class SDListDialog extends FloatDialog{
	SDListArrayAdapter adapter;
	LayoutParams lp;
	ListView listview;
	RelativeLayout relativetop;
	RelativeLayout relativebottom;
	Context context;

	public SDListDialog(Context context, boolean ischoke,List<LDData> listdata,View header,View footer) {
		super(context, ischoke);
		// TODO Auto-generated constructor stub
		init(context,ischoke,listdata,header,footer);
	}
	
	private void init(Context context, boolean ischoke,List<LDData> listdata,View header,View footer)
	{	
		this.context=context;
		if(listdata == null)
		{
			listdata = new ArrayList<LDData>();
		}
	    View view=LayoutInflater.from(context).inflate(R.layout.sd_list_dialog, null);
        
        relativetop=(RelativeLayout)view.findViewById(R.id.listtop);
        relativebottom=(RelativeLayout)view.findViewById(R.id.listbottom);
       
        this.setContentView(view);
        listview=(ListView)findViewById(R.id.listView);
        try{
            if(header!=null)
                listview.addHeaderView(header);
            if(footer!=null)
                listview.addFooterView(footer);
        }catch(Exception e){
            e.printStackTrace();
         }
        setBackGroundStyle(true);
        lp=this.getWindow().getAttributes();
        adapter = new SDListArrayAdapter(context,R.layout.sd_list_dialog,listdata);
        listview.setAdapter(adapter);
	}
	
	public void addtop(View top,View bottom)
	{
	    try{
            if(top!=null)
                relativetop.addView(top);
            if(bottom!=null)
                relativebottom.addView(bottom);
        }catch(Exception e){
            e.printStackTrace();
         }
	}
	
	public ListView getListView()
	{
	    return listview;
	}
	
	public List<LDData> getListLDData()
	{
		return adapter.listdata;
	}
	
	public int getListDataNum()
	{
		return getListLDData().size();
	}
	
	public void setTopBGDrawable(int imageid)
	{
	    relativetop.setBackgroundResource(imageid);
	}
	
	public void setDownBGDrawable(int imageid)
	{
	    relativebottom.setBackgroundResource(imageid);
	}
	
	public void setTopBGDrawable(Drawable drawable)
    {
        relativetop.setBackground(drawable);
    }
    
    public void setDownBGDrawable(Drawable drawable)
    {
        relativebottom.setBackground(drawable);
    }
	
	public RelativeLayout getRelativeLayoutTop()
	{
	    return relativetop;
	}

	public void setBackGroundStyle(boolean ismenustyle)
	{
	    int imageid=ismenustyle?R.drawable.sd_ic_menu_background:R.drawable.sd_ic_dialog_boarder;
	    this.getWindow().setBackgroundDrawableResource(imageid);
	}
	
	public void setWidth(int width)
	{
	    lp.width=width;
	    this.getWindow().setAttributes(lp);
	}
	
	public void addData(int id,String data)
	{
		adapter.listdata.add(new LDData(id,data));
		adapter.notifyDataSetChanged();
	}
	
	public void addData(List<LDData> listData)
    {
        adapter.listdata.addAll(listData);
        adapter.notifyDataSetChanged();
    }
	
	public void removeData(int i)
	{
	    if(i<adapter.listdata.size())
	    {
	        adapter.listdata.remove(i);
	        adapter.notifyDataSetChanged();
	    }
	}
	
	public void clearData()
	{
	    adapter.listdata.clear();
	    adapter.notifyDataSetChanged();
	}

	public static class LDData{
		public int listimageid;
		public String listitem;
		
		public LDData(int id,String item)
		{
			this.listimageid = id;
			this.listitem = item;
		}
	}

	public class SDListArrayAdapter extends ArrayAdapter<LDData>{
	 private Context context;
	 List<LDData> listdata;
	 int left,top,right,bottom;
	 
	 public SDListArrayAdapter(Context context, int textViewResourceId, List<LDData> listdata)
	 {
		 super(context, textViewResourceId, listdata);
		 this.listdata=listdata;
		 this.context=context;
	 }
	 
	public class ListViewHolder
	{
		ImageView icon;
		TextView text;
	}
	 
	 public void additem(int id,String item)
	 {
	     listdata.add(new LDData(id,item));
	 }
	 
	 public void removeitem(int index)
	 {
	     listdata.remove(index);
	 }
	
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent)
	 {
	      View curView = convertView;
	      ListViewHolder holder=new ListViewHolder();
         if (curView == null)
         {
             LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             curView = vi.inflate(R.layout.sd_list_dialog_line, null); 
             holder.icon=(ImageView)curView.findViewById(R.id.image);
             holder.text=(TextView)curView.findViewById(R.id.title);
             left=curView.getPaddingLeft();
             top=curView.getPaddingTop();
             right=curView.getPaddingRight();
             bottom=curView.getPaddingBottom();
             curView.setOnHoverListener(mOnHoverListener);
             curView.setOnTouchListener(mOnTouchListener);
         }
         else
         {
        	 holder=(ListViewHolder)curView.getTag();
         }
        
        int imageid = listdata.get(position).listimageid;
        try{
	        if( imageid != 0)
	        	holder.icon.setImageResource(imageid);
	        	holder.text.setText(listdata.get(position).listitem);
        }catch(Exception e)
        {
        	e.printStackTrace();
        }
          curView.setTag(holder);
          return curView;
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

 
	
}
