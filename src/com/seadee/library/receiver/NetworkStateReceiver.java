/*
   Network State Receiver

   Copyright 2013 Thinstuff Technologies GmbH, Author: Martin Fleisz

   This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. 
   If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
*/

package com.seadee.library.receiver;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.http.conn.util.InetAddressUtils;

import com.seadee.library.utils.Log;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class NetworkStateReceiver extends BroadcastReceiver {
	OnNetworkChangeListener l;
	
	public interface OnNetworkChangeListener
	{
		public void onNetworkChanged(Context context,Intent intent);
	}

	private final static String TAG="NetworkStateReceiver";
	ConnectivityManager connectmanager;
	boolean wifistate=false;
	boolean ethernetstate=false;
	boolean FirstConnect=true;
	Context context;
	private static NetworkStateReceiver instance;
	
	public static String getLocalIpAddress(Context context) {
		final String IPTAG="getLocalIpAddress";
		String nullAddress="0.0.0.0";
	     try { 
	    	 for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	             NetworkInterface intf = en.nextElement();
	             for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                 InetAddress inetAddress = enumIpAddr.nextElement();
	                 String ip_address=inetAddress.getHostAddress();
	                 if (!inetAddress.isLoopbackAddress()&&InetAddressUtils.isIPv4Address(ip_address))	 
	                	 return ip_address;
	             }
	           }  
	     } catch (SocketException ex) {
	         Log.e(IPTAG, ex.toString());
	     }
		return nullAddress;
	} 
	
	
	public static NetworkStateReceiver getInstance()
	{
		return instance;
	}

	
	public void regNetworkReciever(Context context,OnNetworkChangeListener l)
	{
		this.context=context;
		this.l = l;
		
		instance=this;
		IntentFilter mFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(this,mFilter);  
	}
	
	public void unregNetworkReciever(Context context)
	{
		context.unregisterReceiver(this);
	}
	
	public boolean isWifiConnected()
	{
		if(connectmanager==null)
			connectmanager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return connectmanager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
	}
	
	public boolean isEthernetConnected()
	{
		if(connectmanager==null)
			connectmanager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return connectmanager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET).isConnected();
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if(connectmanager==null)
			connectmanager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(l != null)
			l.onNetworkChanged(context, intent);
	}
}
