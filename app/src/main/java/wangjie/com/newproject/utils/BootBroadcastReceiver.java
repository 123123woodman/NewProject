package wangjie.com.newproject.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import wangjie.com.newproject.activity.MainActivity;

/**
 * Created by Administrator on 2018/4/16.
 */

public class BootBroadcastReceiver extends BroadcastReceiver{

    static final String ACTION = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("TTTT", "手机开机了----" + intent.getAction());
        if (intent.getAction().equals(ACTION)) {
            Log.i("TTTT", "手机开机了");
            Intent mainActivityIntent = new Intent(context, MainActivity.class);  // 要启动的Activity
            mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mainActivityIntent);
        }
    }
}
