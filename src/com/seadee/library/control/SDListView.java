package com.seadee.library.control;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;


public abstract class SDListView extends ListView {
	
	public SDListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public SDListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public SDListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void setHoverColor(int hovercolor);

	/*
	 * this can be called only when the layout in items is LinearLayout
	 */
	public void setListViewHeightBasedOnChildren() {  
        ListAdapter listAdapter = this.getAdapter();   
        if (listAdapter == null) { 
            return;  
        }  

        int totalHeight = 0;  
        for (int i = 0; i < listAdapter.getCount(); i++) {  
            View listItem = listAdapter.getView(i, null, this);  
            listItem.measure(0, 0); 
            totalHeight += listItem.getMeasuredHeight();  
        }  
        
        ViewGroup.LayoutParams params = this.getLayoutParams();  
        params.height = totalHeight + (this.getDividerHeight() * (listAdapter.getCount() - 1));  
  //      Log.i("MultiListView's Height is ",params.height);
        setLayoutParams(params);  
    }

}
