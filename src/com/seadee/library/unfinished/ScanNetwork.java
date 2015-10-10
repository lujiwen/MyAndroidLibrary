package com.seadee.library.unfinished;
import java.util.ArrayList;
import java.util.List;

import com.seadee.library.utils.Log;

import android.content.Context;

public class ScanNetwork {
	
	public static final String TAG="ScanNetwork";

	@SuppressWarnings("unused")
	private Context context;
	Runtime runtime=Runtime.getRuntime();
	String ipaddress;
	String ipforehead;
	boolean result[]=new boolean[256];

	public ScanNetwork(Context context){
		this.context = context;
		this.ipaddress="192.168.0.1";
		this.ipforehead=getForeheadIP("192.168.0.167");
		Log.i(TAG,"ip address is "+ipaddress);
		Log.i(TAG,"ip forehead is "+ipforehead);
	}

	public List<String> scan()
	{
		if(ipforehead==null || ipforehead.isEmpty())
		{
			return null;
		}
		Thread thread[]=new Thread[256];
		for(int i=0;i<256;i++)
		{
			final int fi=i;
			thread[i]=new Thread(new Runnable()
			{

				@Override
				public void run() {
					// TODO Auto-generated method stub
					result[fi]=ping(ipforehead+fi);
				}
				
			});
			thread[i].start();
		}

		boolean threadrunning=true;
		while(threadrunning)
		{
			threadrunning=false;
			for(int i=0;i<256;i++)
			{
				if(thread[i].isAlive())
				{
					threadrunning=true;
					
					break;
				}
			}
			
		}

		Log.i(TAG,"2222222222");

		List<String> list=new ArrayList<String>();
		for(int i=0;i<256;i++)
		{
			if(result[i])
			{
				list.add(ipforehead+i);
			}
		}
		
		for(int i=0;i<list.size();i++)
			Log.i(TAG,list.get(i));
		return list;
	}
	
	private boolean ping(String ip)
	{
		boolean result=false;
		Process proc=null;
		try
		{
			proc=runtime.exec("ping -c 1 "+ip);
			int status=proc.waitFor();
			if(status==0)
			{
				result=true;
			}
			else
			{
				result=false;
			}
		}
		catch(Exception e)
		{
			result=false;
			e.printStackTrace();
		}
		return result;
	}
	
	private String getForeheadIP(String ip)
	{
		if(ip==null)
			return null;
		if(ip.equals("") || ip.equals("0.0.0.0"))
			return null;
		return ip.substring(0,ip.lastIndexOf(".")+1);
	}

	public static String getLocalDeviceName()
	{
		return android.os.Build.MODEL;
	} 

} 