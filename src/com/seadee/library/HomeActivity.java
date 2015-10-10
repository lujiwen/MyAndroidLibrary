package com.seadee.library;

import java.util.ArrayList;
import java.util.List;

import com.seadee.library.activity.ListMenuDialog.ListDialogData;
import com.seadee.library.activity.SDActivity;
import com.seadee.library.activity.SDMenuBar;
import com.seadee.library.activity.SDStatusBar;
import com.seadee.library.activity.SDToolBar;
import com.seadee.library.control.MultiChoiceListView;
import com.seadee.library.control.MultiChoiceListView.MultiValue;
import com.seadee.library.control.SDProgressBar;
import com.seadee.library.control.SingleChoiceListView;
import com.seadee.library.utils.Log;
import com.seadee.library.utils.SDAlertDialog;
import com.seadee.library.utils.SDDownloadUtils;
import com.seadee.library.utils.SDAlertDialog.ALERTSTYLE;
import com.seadee.library.utils.SDDownloadUtils.OnDownloadListener;
import com.seadee.library.utils.SDToast;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class HomeActivity extends SDActivity  {
	SDProgressBar progressbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sd_main);
		progressbar=(SDProgressBar)findViewById(R.id.SDProgressBar1);
		progressbar.setMax(100);
		progressbar.setProgress(50);
	}
	SDMenuBar.MenuItem menuhello;
	SDMenuBar.MenuItem menuworld;
	SDMenuBar sdmenubar;
	@Override
	public void onCreateMenuBar(SDMenuBar menubar) {
		// TODO Auto-generated method stub
		List<ListDialogData> data=new ArrayList<ListDialogData>();
		data.add(ListDialogData.makeData(R.drawable.sd_ic_btn_hover, "New","Ctrl+K"));
		data.add(ListDialogData.makeData(R.drawable.sd_ic_btn_normal, "Save",""));
		menuhello=menubar.newMenu("File", null, data);
		List<ListDialogData> data2=new ArrayList<ListDialogData>();
		data2.add(ListDialogData.makeData(R.drawable.sd_ic_btn_normal, "Find",""));
		data2.add(ListDialogData.makeData(R.drawable.sd_ic_btn_hover, "Replace",""));
		menuworld=menubar.newMenu("Edit", null, data2);
		sdmenubar=menubar;
	}
	
	@Override
	public void parseMenu(int menuindex) {
		// TODO Auto-generated method stub
		if(sdmenubar.getCurrentMenuItemView()==menuhello)
		{
			switch(menuindex)
			{
			case 0:
				SDToast.makeText(this, "sss", Toast.LENGTH_LONG).show();;
				break;
			case 1: 
				
				break;
			}
		}
		else if(sdmenubar.getCurrentMenuItemView()==menuworld)
		{
			switch(menuindex)
			{
			case 0:
				Log.i("Edit-Find");break;
			case 1:
				Log.i("Edit-Replace");break;
			}
		}
	}
	
	public void show(View v)
	{
		SDDownloadUtils download = new SDDownloadUtils(this);
		download.setOnDownloadListener(new OnDownloadListener(){

			@Override
			public void OnDownloadDone(Cursor cursor, long downloadId) {
				// TODO Auto-generated method stub
				
			}

	
			
		});
		download.registerDownloadBroadcast(this);
		download.setParameters(SDDownloadUtils.testUrl, null).startDownload();
	}
		
	@Override
	public void onCreateToolBar(SDToolBar toolbar) {
		// TODO Auto-generated method stub
		toolbar.newTool(R.drawable.sd_ic_btn_hover,"multialertdialog").setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SDAlertDialog alert=new SDAlertDialog(HomeActivity.this);
				alert.setStyle(ALERTSTYLE.WARNING|ALERTSTYLE.YNC);
				List<MultiValue> values = new ArrayList<MultiValue>();
				values.add(new MultiValue("aaa",true));
				values.add(new MultiValue("bbb",true));
				values.add(new MultiValue("aaa",true));
				values.add(new MultiValue("bbb",true));
				values.add(new MultiValue("aaa",true));
				values.add(new MultiValue("bbb",true));
				values.add(new MultiValue("aaa",true));
				values.add(new MultiValue("bbb",true));
				values.add(new MultiValue("aaa",true));
				values.add(new MultiValue("bbb",true));
				values.add(new MultiValue("aaa",true));
				values.add(new MultiValue("bbb",true));
				values.add(new MultiValue("aaa",true));
				values.add(new MultiValue("bbb",true));
				values.add(new MultiValue("aaa",true));
				values.add(new MultiValue("bbb",true));
				final MultiChoiceListView multilist = alert.setMultiChoiceItems(values);
				alert.getPositiveBtn().setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						boolean[] result = multilist.getChoosedItems();
						for(int i=0;i<result.length;i++)
						{
							Log.i("result is ",result[i]);
						}
					}
					
				});
				alert.show();
			}
			
		});
		toolbar.newDivider();
		toolbar.newTool(R.drawable.sd_ic_btn_hover).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SDAlertDialog alert=new SDAlertDialog(HomeActivity.this);
				alert.setStyle(ALERTSTYLE.WARNING|ALERTSTYLE.YNC);
				String[] items=new String[]{"aa","bb"};
				final SingleChoiceListView singlelist = alert.setSingleChoiceItems(items,-1);
				alert.getPositiveBtn().setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int result = singlelist.getChoosedItem();
						Log.i("result is ",result);
					}
					
				});
				alert.show();
			}
			
		});
		
		toolbar.newTool(R.drawable.sd_ic_btn_hover).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SDAlertDialog alert=new SDAlertDialog(HomeActivity.this);
				alert.setStyle(ALERTSTYLE.WARNING|ALERTSTYLE.YNC);
				alert.setMessage("a\nb\nc\nc\ne\nd\na\nb\n");
				alert.setSizeScale(0.3f, 0);
				alert.show();
			}
			
		});
	}

	@Override
	public void onCreateStatusBar(SDStatusBar statusbar) {
		// TODO Auto-generated method stub
		statusbar.newStatus(1);
		statusbar.showHint("aaaaaa");
	}

    @Override
    public ActivityParams specialActivity() {
        // TODO Auto-generated method stub
        return new ActivityParams(0,"sss",1000,600);
    }


}
