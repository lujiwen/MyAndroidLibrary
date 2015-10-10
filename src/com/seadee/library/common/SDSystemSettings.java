package com.seadee.library.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

public class SDSystemSettings {

	public static class PowerManager{

		public static void setOffScreen(Context context,int time)
		{
			try {
					 //Settings.System.putInt(context.getContentResolver(),Settings.System.STAY_ON_WHILE_PLUGGED_IN,1);
					Settings.System.putInt(context.getContentResolver(), android.provider.Settings.System.SCREEN_OFF_TIMEOUT ,time*1000);
		    } catch (NumberFormatException e) {
		    	e.printStackTrace();
		    }
		}
	}
}
