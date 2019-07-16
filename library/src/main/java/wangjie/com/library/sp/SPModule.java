package wangjie.com.library.sp;

public class SPModule {

    private static final String KEY_APPISLOGIN = "keyappzilogin";//检测是否登录
    private static final String KEY_APPISREGISTER = "keyappisregister";//检测是否注册
    private static final String KEY_APPACCOUNT = "keyappaccount";//账号
    private static final String KEY_APPPASSWORD = "keyapppassword";//密码

    /**
     * 注销账号
     */
    public static void setCancelAccount() {
        setAppisregister(false);
        setAppislogin(false);
        setAppaccount(null);
        setApppassword(null);
    }

    /**
     * 判断app是否登录
     * @param appislogin
     */
    public static void setAppislogin(boolean appislogin) {
        SPUtils.getInstance().putBoolean(KEY_APPISLOGIN, appislogin);
    }
    public static boolean getAppisLogin(String path) {
        if (!path.contains("needlogin"))
            return true;
        return SPUtils.getInstance().getBoolean(KEY_APPISLOGIN, false);
    }

    /**
     * 判断是否注册
     */
    public static void setAppisregister(boolean appisregister) {
        SPUtils.getInstance().putBoolean(KEY_APPISREGISTER, appisregister);
    }
    public static boolean getAppisregister() {
        return SPUtils.getInstance().getBoolean(KEY_APPISREGISTER, false);
    }

    /**
     * 账号
     */
    public static void setAppaccount(String appaccount) {
        SPUtils.getInstance().putString(KEY_APPACCOUNT, appaccount);
    }
    public static String getAppaccount() {
        return SPUtils.getInstance().getString(KEY_APPACCOUNT, null);
    }

    /**
     * 密码
     */
    public static void setApppassword(String apppassword) {
        SPUtils.getInstance().putString(KEY_APPPASSWORD, apppassword);
    }
    public static String getKeyApppassword() {
        return SPUtils.getInstance().getString(KEY_APPPASSWORD, null);
    }

}
