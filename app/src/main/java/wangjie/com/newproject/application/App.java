package wangjie.com.newproject.application;

import android.content.Context;

/**
 * Created by Administrator on 2018/4/3.
 */

public class App {

    public static Context getContext() {
        return MyApplication.getMyApplication().getBaseContext();
    }

}
