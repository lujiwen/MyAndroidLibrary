package com.seadee.library.control;

import com.seadee.library.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class SDProgressBar extends ProgressBar {
	Context context;
	
	public SDProgressBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public SDProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public SDProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);
	}
	
	public void init(Context context)
	{
		this.context=context;
		this.setBackgroundResource(R.drawable.sd_ic_progressbar_background);
		this.setProgressDrawable(this.getResources().getDrawable(R.drawable.sd_progress_bar));
	}
	
		

}
