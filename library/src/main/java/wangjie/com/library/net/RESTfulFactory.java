package wangjie.com.library.net;


import wangjie.com.library.BuildConfig;

/**
 * Created by Administrator on 2018/3/22.
 */
public class RESTfulFactory {

    public static String URL_BASE = "http://mrobot.pcauto.com.cn/v2/cms/channels/";

    public static String URL_BASE_YZY = "http://yanshan.dev.wiscape.cn";

    public static String URL = "http://blog.csdn.net/dingyingying521/article/ls/78468620";

    public static String HttpsUrl = "https://192.168.1.242:8443/";

    public static String XIAOLIUHTTPS = "http://yanshan.dev.wiscape.cn";

    public static String GITHUB = "https://api.github.com";

    /**
     * 是否是debug模式
     */
    public static final boolean DEBUG = BuildConfig.DEBUG;

    /**
     * 连接超时时间
     */
    public static final int CONNECT_TIMEOUT_MILLIS = 15 * 1000; // 15s

    /**
     * 响应超时时间
     */
    public static final int READ_TIMEOUT_MILLIS = 20 * 1000; // 20s

    /**
     * App ID
     */
    public static final String APP_KEY = "android";

    /**
     * App Secret
     */
    public static final String APP_SECRET = "afegewlnbnl987nfelwn";


//    public static String getAppRootPath() {
//        return SDCardUtil.getRootPath() + File.separator + APP_NAME;
//    }

    //public static String getAppImagePath() {
//        return getAppRootPath() + File.separator + "image";
//    }

}
