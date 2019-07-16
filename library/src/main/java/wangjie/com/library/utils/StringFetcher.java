package wangjie.com.library.utils;

import wangjie.com.library.base.base.BaseApplication;

public class StringFetcher {

    public static String getString(int id) {
        return BaseApplication.getBaseApplication().getString(id);
    }

}