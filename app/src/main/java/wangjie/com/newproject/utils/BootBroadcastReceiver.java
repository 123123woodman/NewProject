package wangjie.com.newproject.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import wangjie.com.newproject.activity.MainActivity;

/**
 * Created by Administrator on 2018/4/16.
 */

public class BootBroadcastReceiver extends BroadcastReceiver{

    static final String ACTION = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION)) {
            Intent mainActivityIntent = new Intent(context, MainActivity.class);  // 要启动的Activity
            mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mainActivityIntent);
        }
    }
}
