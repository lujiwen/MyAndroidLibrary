package com.seadee.library.utils;

import com.seadee.library.BuildConfig;

public class Log {
	public static boolean LOGFLAG=BuildConfig.DEBUG;

	public static void i(int log){
		if(LOGFLAG)
		{
			try{
				android.util.Log.i("The integer varible is ",Integer.toString(log));
			}
			catch(Exception e){}
		}
		return;
	}
	
	public static void i(String log){
		if(LOGFLAG)
		{
			try{
				android.util.Log.i("The string varible is ",log);
			}
			catch(Exception e){}
		}
		return;
	}
	
	public static void i(String log1,int log2){
		if(LOGFLAG)
		{
			try{
				android.util.Log.i(log1,Integer.toString(log2));
			}
			catch(Exception e){}
		}
		return;
	}
	
	public static void i(String log1,int... logs){
		if(LOGFLAG)
		{
			String log="";
			try{
				for(int i:logs)
				{
					log+=i+",";
				}
				android.util.Log.i(log1,log);
			}
			catch(Exception e){}
		}
		return;
	}
	
	public static void i(String log1,boolean log2){
		if(LOGFLAG)
		{
			try{
				android.util.Log.i(log1,Boolean.toString(log2));
			}
			catch(Exception e){}
		}
		return;
	}
	
	public static void i(String log1,Double log2){
		if(LOGFLAG)
		{
			try{
				android.util.Log.i(log1,Double.toString(log2));
			}
			catch(Exception e){}
		}
		return;
	}
	
	public static void i(String log1,float log2){
		if(LOGFLAG)
		{
			try{
				android.util.Log.i(log1,Float.toString(log2));
			}
			catch(Exception e){}
		}
		return;
	}
	
	public static void i(String log1,String log2){
		if(LOGFLAG)
		{
			try{
				android.util.Log.i(log1,log2);
			}
			catch(Exception e){}
		}
		return;
	}
	
	public static void i(String log1,String... logs){
		if(LOGFLAG)
		{
			try{
				String log="";
				for(String i:logs)
				{
					log+=i+",";
				}
				android.util.Log.i(log1,log);
			}
			catch(Exception e){}
		}
		return;
	}
	
	public static void i(String log1,String log2,String log3){
		if(LOGFLAG)
		{
			try{
				android.util.Log.i(log1,log2+log3);
			}
			catch(Exception e){}
		}
		return;
	}
	
	public static void i(String log1,String log2,int log3){
		if(LOGFLAG)
		{
			try{
				android.util.Log.i(log1,log2+log3);
			}
			catch(Exception e){}
		}
		return;
	}
	
	public static void e(String log1,String log2){
		if(LOGFLAG)
		{
			try{
				android.util.Log.e(log1,log2);
			}
			catch(Exception e){}
		}
		return;
	}
	
	public static void e(String log1,Exception exception){
		if(LOGFLAG)
		{
			try{
				android.util.Log.e(log1, exception.toString(),exception);
				exception.printStackTrace();
			}
			catch(Exception e){}
		}
		return;
	}
	
	public static void e(String log1,String log2,Exception exception){
		if(LOGFLAG)
		{
			try{
				android.util.Log.e(log1,log2,exception);
				exception.printStackTrace();
			}
			catch(Exception e){}
		}
		return;
	}
	
	
	
	public static void w(String log1,String log2){
		if(LOGFLAG)
		{
			try{
				android.util.Log.w(log1,log2);
			}
			catch(Exception e){}
		}
		return;
	}
	public static void v(String log1,String log2){
		if(LOGFLAG)
		{
			try{
				android.util.Log.v(log1,log2);
			}
			catch(Exception e){}
		}
		return;
	}
	public static void d(String log1,String log2){
		if(LOGFLAG)
		{
			try{
				android.util.Log.d(log1,log2);
			}
			catch(Exception e){}
		}
		return;
	}

}
