package com.seadee.library.utils;

import java.util.List;

import com.seadee.library.R;
import com.seadee.library.control.MultiChoiceListView;
import com.seadee.library.control.SDButton;
import com.seadee.library.control.MultiChoiceListView.MultiValue;
import com.seadee.library.control.SingleChoiceListView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SDAlertDialog extends FloatDialog {
	
	Context context;
	TextView titleText;
	ImageView titleIcon;
	TextView msgText;
	SDButton positiveBtn,neutralBtn,negativeBtn;
	LinearLayout headLayout,contentLayout,bottomLayout;

	public final static String TAG = "SDAlertDialog";
	
	public class ALERTSTYLE{
		public final static int INFO = 0x00000001;
		public final static int WARNING = 0x00000010;
		public final static int ERROR = 0x000000100;
		
		public final static int YNC = 0x00001000;
		public final static int YN = 0x00010000;
		public final static int Y = 0x00100000;
		public final static int CC = 0x01000000;   //confirm and cancel
		public final static int C = 0x10000000;    //confirm only
	}

	public SDAlertDialog(Context context) {
		super(context, true);
		// TODO Auto-generated constructor stub
		init(context);
	}
	
	public SDAlertDialog(Context context,Boolean ischoke)
	{
		super(context,ischoke);
		init(context);
	}
	
	private void init(Context context)
	{
		this.context=context;
		this.setContentView(R.layout.sd_alert_dialog);
		this.getWindow().setBackgroundDrawableResource(R.drawable.sd_ic_dialog_boarder);
		titleText = (TextView)findViewById(R.id.title);
		titleIcon = (ImageView)findViewById(R.id.icon);
		msgText = (TextView)findViewById(R.id.msg);
		positiveBtn = (SDButton)findViewById(R.id.positive);
		neutralBtn = (SDButton)findViewById(R.id.neutral);
		negativeBtn = (SDButton)findViewById(R.id.negative);
		headLayout = (LinearLayout)findViewById(R.id.head);
		contentLayout = (LinearLayout)findViewById(R.id.content);
		bottomLayout = (LinearLayout)findViewById(R.id.bottom);
		
	}
	
	

	public SDAlertDialog setStyle(int alertstyle)
	{
		if((alertstyle & ALERTSTYLE.INFO) > 0)	{
			this.setTitle(context.getResources().getString(R.string.prompt));
			this.setIcon(R.drawable.sd_icon_information);
		}
		else if((alertstyle & ALERTSTYLE.WARNING) > 0)
		{
			this.setTitle(context.getResources().getString(R.string.warning));
			this.setIcon(R.drawable.sd_icon_warning);
		}
		else if((alertstyle & ALERTSTYLE.ERROR) > 0)
		{
			this.setTitle(context.getResources().getString(R.string.error));
			this.setIcon(R.drawable.sd_icon_error);
		}
		
		this.hidePositiveButton();
		this.hideNeutralButtom();
		this.hideNegativeButtom();
		
		if((alertstyle & ALERTSTYLE.YNC) > 0)	{
			this.setPositiveButton(context.getResources().getString(R.string.yes), mOnCancelClickListener);
			this.setNeutralButton(context.getResources().getString(R.string.cancel), mOnCancelClickListener);
			this.setNegativeButton(context.getResources().getString(R.string.no), mOnCancelClickListener);
		}
		else if((alertstyle & ALERTSTYLE.YN) > 0)	{
			this.setPositiveButton(context.getResources().getString(R.string.yes), mOnCancelClickListener);
			this.setNegativeButton(context.getResources().getString(R.string.no), mOnCancelClickListener);
		}
		else if((alertstyle & ALERTSTYLE.Y) > 0)	{
			this.setPositiveButton(context.getResources().getString(R.string.cancel), mOnCancelClickListener);
		}
		else if((alertstyle & ALERTSTYLE.CC) > 0)	{
			this.setPositiveButton(context.getResources().getString(R.string.confirm), mOnCancelClickListener);
			this.setNegativeButton(context.getResources().getString(R.string.cancel), mOnCancelClickListener);
		}
		else if((alertstyle & ALERTSTYLE.C) > 0)	{
			this.setPositiveButton(context.getResources().getString(R.string.confirm), mOnCancelClickListener);
		}
			
		return this;
	}

	
	public View getBottomView()
	{
		return bottomLayout;
	}
	
	public SDAlertDialog hidePositiveButton()
	{
		getPositiveBtn().setVisibility(View.INVISIBLE);
		return this;
	}
	
	public SDAlertDialog hideNeutralButtom()
	{
		getNeutralBtn().setVisibility(View.GONE);
		return this;
	}
	
	public SDAlertDialog hideNegativeButtom()
	{
		getNegativeBtn().setVisibility(View.GONE);
		return this;
	}
	
	public SDAlertDialog hideBottomView()
	{
		getBottomView().setVisibility(View.INVISIBLE);
		return this;
	}
	
	public View.OnClickListener mOnCancelClickListener = new View.OnClickListener()
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			cancel();
		}
		
	};
	
	
	public SDAlertDialog setView(View view)
	{
		msgText.setVisibility(View.GONE);
		contentLayout.addView(view);
		return this;
	}
	
	public SDAlertDialog setTitle(String title)
	{
		titleText.setText(title);
		return this;
	}
	
	public SDAlertDialog setIcon(int iconid)
	{
		titleIcon.setVisibility(View.VISIBLE);
		titleIcon.setImageResource(iconid);
		return this;
	}
	
	public SDAlertDialog setIcon(Drawable icon)
	{
		titleIcon.setImageDrawable(icon);
		return this;
	}
	
	public SDAlertDialog setMessage(String msg)
	{
		msgText.setText(msg);
		return this;
	}
	
	public MultiChoiceListView setMultiChoiceItems(List<MultiValue> values)
	{
		MultiChoiceListView multilist= new MultiChoiceListView(context, null, values);
		multilist.setListViewHeightBasedOnChildren();
		this.setView( multilist);
		return multilist;
	}
	
	public MultiChoiceListView setMultiChoiceItems(String[] values,boolean[] choosed)
	{
		MultiChoiceListView multilist= new MultiChoiceListView(context, null, values,choosed);
		multilist.setListViewHeightBasedOnChildren();
		this.setView( multilist);
		return multilist;
	}
	
	public SingleChoiceListView setSingleChoiceItems(String[] items,int choosed)
	{
		SingleChoiceListView singlelist= new SingleChoiceListView(context, null, items,choosed);
		singlelist.setListViewHeightBasedOnChildren();
		this.setView(singlelist);
		return singlelist;
	}
	
	public SDAlertDialog setPositiveButton(String str,View.OnClickListener l)
	{
		positiveBtn.setVisibility(View.VISIBLE);
		positiveBtn.setText(str);
		positiveBtn.setOnClickListener(l);
		return this;
	}

	public SDAlertDialog setNeutralButton(String str,View.OnClickListener l)
	{
		neutralBtn.setVisibility(View.VISIBLE);
		neutralBtn.setText(str);
		neutralBtn.setOnClickListener(l);
		return this;
	}
	
	public SDAlertDialog setNegativeButton(String str,View.OnClickListener l)
	{
		negativeBtn.setVisibility(View.VISIBLE);
		negativeBtn.setText(str);
		negativeBtn.setOnClickListener(l);
		return this;
	}
	
	public TextView getTitleTextView()
	{
		return titleText;
	}
	
	public ImageView getTitleImageView()
	{
		return titleIcon;
	}
	
	public TextView getMessageTextView()
	{
		return msgText;
	}
	
	public SDButton getPositiveBtn()
	{
		return positiveBtn;
	}
	
	public SDButton getNeutralBtn()
	{
		return neutralBtn;
	}
	
	public SDButton getNegativeBtn()
	{
		return negativeBtn;
	}
	

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_ENTER)
		{
			positiveBtn.performClick();
		}
		return super.onKeyUp(keyCode, event);
	}

}
