package wangjie.com.newproject.utils;

import wangjie.com.newproject.R;
import wangjie.com.newproject.application.MyApplication;

/**
 * Created by Administrator on 2018/4/12.
 */

public interface ColorInterface {
    int[] color =
            {MyApplication.getMyApplication().getResources().getColor(R.color.green), MyApplication.getMyApplication().getResources().getColor(R.color.colorAccent),
                    MyApplication.getMyApplication().getResources().getColor(R.color.balck), MyApplication.getMyApplication().getResources().getColor(R.color.colorPrimary),
                    MyApplication.getMyApplication().getResources().getColor(R.color.colorPrimaryDark), MyApplication.getMyApplication().getResources().getColor(R.color.orange),
                    MyApplication.getMyApplication().getResources().getColor(R.color.purple), MyApplication.getMyApplication().getResources().getColor(R.color.yellow)};
}
