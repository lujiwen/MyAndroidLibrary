package com.seadee.library.common;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;

import com.seadee.library.activity.SDActivity;
import com.seadee.library.utils.Log;

import android.app.Activity;
import android.app.Dialog;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnHoverListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageView;


public class Common {
	
	public static void startPackage(Activity context,String packageName,String className)
	{
		if(className.startsWith("./"))
		{
			className=packageName+className.replace("./", ".");
		}
		Intent intent = new Intent();
		intent.setComponent(new ComponentName(packageName, className));
		intent.setAction(Intent.ACTION_VIEW);
		context.startActivity(intent);
	}
	
	
	public static void updateLanguage(Locale locale) {
		try {
			Object objIActMag;
			Class<?> clzIActMag = Class.forName("android.app.IActivityManager");
			Class<?> clzActMagNative = Class.forName("android.app.ActivityManagerNative");
			Method mtdActMagNative$getDefault = clzActMagNative.getDeclaredMethod("getDefault");
			objIActMag = mtdActMagNative$getDefault.invoke(clzActMagNative);
			Method mtdIActMag$getConfiguration = clzIActMag.getDeclaredMethod("getConfiguration");
			Configuration config = (Configuration) mtdIActMag$getConfiguration.invoke(objIActMag);
			config.locale = locale;

			@SuppressWarnings("rawtypes")
			Class[] clzParams = { Configuration.class };
			Method mtdIActMag$updateConfiguration = clzIActMag.getDeclaredMethod(
					"updateConfiguration", clzParams);
			mtdIActMag$updateConfiguration.invoke(objIActMag, config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getSdStoragePath(Context context)
	{
		String path = "none";
		ArrayList<String> totalDevicesList;
		totalDevicesList = new ArrayList<String>();
		StorageManager stmg = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
		String[] list = getVolumePaths(stmg);
		for(int i = 0; i < list.length; i++)
		{
			totalDevicesList.add(list[i]);
		}
		for(int i = 0; i < totalDevicesList.size(); i++)
		{
			if(!totalDevicesList.get(i).equals(Environment.getExternalStorageDirectory().getPath()))
			{
				if(totalDevicesList.get(i).contains("sd"))
				{
					path = totalDevicesList.get(i);
					return path;
				}
			}
		}
		return path;
	}
	
	public static String[] getVolumePaths(StorageManager mStorageManager) { 
        String[] paths = null; 
        Method mMethodGetPaths; 
        try { 
            mMethodGetPaths = mStorageManager.getClass() 
                    .getMethod("getVolumePaths"); 
            try { 
                paths = (String[]) mMethodGetPaths.invoke(mStorageManager); 
            } catch (IllegalArgumentException e) { 
                e.printStackTrace(); 
            } catch (IllegalAccessException e) { 
                e.printStackTrace(); 
            } catch (InvocationTargetException e) { 
                e.printStackTrace(); 
            } 
        } catch (NoSuchMethodException e) { 
            e.printStackTrace(); 
        } 
        
        return paths; 
    } 
	
	public static String getUsbStoragePath(Context context)
	{
		String path = "none";
		ArrayList<String> totalDevicesList;
		totalDevicesList = new ArrayList<String>();
		StorageManager stmg = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
		String[] list = getVolumePaths(stmg);
		for(int i = 0; i < list.length; i++)
		{
			totalDevicesList.add(list[i]);
		}
		
		for(int i = 0; i < totalDevicesList.size(); i++)
		{
			if(!totalDevicesList.get(i).equals(Environment.getExternalStorageDirectory().getPath()))
			{
				if(totalDevicesList.get(i).contains("usb"))
				{
					path = totalDevicesList.get(i);
					return path;
				}
			}
		}
		return path;
	}
	
	public static String execCommand(String command) throws IOException {  
		Runtime runtime = Runtime.getRuntime();  
		Process proc = runtime.exec(command); 
		InputStream inputstream = proc.getInputStream();  
		InputStreamReader inputstreamreader = new InputStreamReader(inputstream);  
		BufferedReader bufferedreader = new BufferedReader(inputstreamreader);  
		String line = "";  
		StringBuilder sb = new StringBuilder(line);  
		while ((line = bufferedreader.readLine()) != null) {  
			sb.append(line);  
			sb.append('\n');  
		}  
		try {  
		if (proc.waitFor() != 0) {
			Log.i("Common","exit value = " + proc.exitValue());  
		}  
		} catch (InterruptedException e) {  
			e.printStackTrace();
		}  
		return sb.toString();  
	}  
	
	public static void oppositePwdVisible(EditText passwdEdit)
	{
		if(passwdEdit.getTransformationMethod()==PasswordTransformationMethod.getInstance())
			passwdEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
		else
			passwdEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
	}
	

	
	
	public static void simulateKey(final int KeyCode) {
		new Thread() 
		{ 
			public void run() { 
			   try {     
			   Instrumentation inst = new Instrumentation();     
			   inst.sendKeyDownUpSync(KeyCode);    
			   } catch (Exception e) {
			        e.printStackTrace();
			   }   
			}  
		}.start(); 
	}
	
	public static long getDirectorySize(String path)
	{
		StatFs statfs=new StatFs(path);
		return statfs.getAvailableBlocks()*statfs.getBlockSize();
	}
	
}
