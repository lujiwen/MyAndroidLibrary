package com.seadee.library.utils;

import java.io.File;

import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

/*
<uses-permission android:name="android.permission.INTERNET" />  
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
 */

public class SDDownloadUtils {
	
	public interface OnDownloadListener{
		public void OnDownloadDone(Cursor cursor,long downloadId);
	}
	

	long downloadId;
	
	OnDownloadListener downloadlistener;
	
	public void setOnDownloadListener(OnDownloadListener downloadlistener)
	{
		this.downloadlistener = downloadlistener;
	}
	
	DownloadManager downloadManager;
	Request request;
	Context context;
	
	public static String testUrl = "http://192.168.0.4/test.rar";
	
	public SDDownloadUtils(Context context)
	{
		downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
		this.context = context;
	}
	
	public SDDownloadUtils setParameters(String url,String title)
	{
		Uri uri = Uri.parse(url);
		request = new Request(uri);
		request.setVisibleInDownloadsUi(true);
		File file = new File(url);

		if(title!=null && !title.isEmpty())
		{
			request.setTitle(title);
		}
		else{
			request.setTitle(file.getName());
		}
		return this;
	}
	
	public long startDownload()
	{
		downloadId = downloadManager.enqueue(request);
		return downloadId;
	}

	public static String getDownloadName(Cursor c)
	{
		if(c!=null)
		{
			try{
				return c.getString(c.getColumnIndexOrThrow(DownloadManager.COLUMN_LOCAL_FILENAME));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return "";
	}
	
	private BroadcastReceiver downloadBroadcastReceiver = new BroadcastReceiver()
	{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			
			if(action == DownloadManager.ACTION_DOWNLOAD_COMPLETE){
				Query query = new Query();
				query.setFilterById(downloadId);
				Cursor c = downloadManager.query(query);
				if(c.moveToFirst()){
					int index  = c.getColumnIndex(DownloadManager.COLUMN_STATUS);
					if(DownloadManager.STATUS_SUCCESSFUL == c.getInt(index))
					{
						if(downloadlistener!=null)
							downloadlistener.OnDownloadDone(c,downloadId);
					}
					else{
						downloadManager.remove(downloadId);
						SDToast.makeText(context, "Download Error!", Toast.LENGTH_LONG).show();
					}
					c.close();
				}
				else{
					downloadManager.remove(downloadId);
					SDToast.makeText(context, "Download Error!", Toast.LENGTH_LONG).show();
				}
			}
			else if(action == DownloadManager.ACTION_NOTIFICATION_CLICKED)
			{
				viewDownloadUI();
			}
		}
		
	};
	
	public void viewDownloadUI()
	{
		context.startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));
	}
	
	public void registerDownloadBroadcast(Context context)
	{
		IntentFilter i = new IntentFilter();
		i.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
		i.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
		context.registerReceiver(downloadBroadcastReceiver, i);
	}
	
	public void unregisterDownloadBroadcast(Context context)
	{
		context.unregisterReceiver(downloadBroadcastReceiver);
	}
}
