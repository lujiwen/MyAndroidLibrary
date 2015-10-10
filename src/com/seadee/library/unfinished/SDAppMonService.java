package com.seadee.library.unfinished;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import java.util.List;

import com.seadee.library.utils.Log;

public class SDAppMonService extends Service {
    private ActivityManager am;
    List<RunningAppProcessInfo> old_apps;
    List<RunningAppProcessInfo> new_apps;
    RunningAppProcessInfo cur_item;

    @Override
    public IBinder onBind(Intent arg0) {

        return null;
    }

    @Override
    public void onCreate() {
        am=(ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
    }

    @Override
    public void onStart(Intent intent, int startId) {
       new Thread(){
         public void run()
         {
             while(true)
             {
            new_apps=am.getRunningAppProcesses();
            int size=new_apps.size();
            for(int i=0;i<size;i++)
              {
                cur_item=new_apps.get(i);
                 if(!old_apps.contains(cur_item))
                   {
                       Log.i("New opened process "+i,cur_item.processName);
                   }
                 else
                   {
                      old_apps.remove(cur_item);
                   }
              }
            size=old_apps.size();
            for(int i=0;i<size;i++)
              {
                cur_item=old_apps.get(i);
                if(!new_apps.contains(cur_item))
                  {
                    Log.i("Last closed process "+i,cur_item.processName);
                  }
              }
            Log.i("=====================");
            old_apps=new_apps;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             }
         }
       }.start();
    }

}