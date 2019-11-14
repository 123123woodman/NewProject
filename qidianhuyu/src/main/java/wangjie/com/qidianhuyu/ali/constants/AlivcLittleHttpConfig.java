package wangjie.com.qidianhuyu.ali.constants;

/**
 * http config
 * 主要是表单请求的key值
 */
public class AlivcLittleHttpConfig {

    public interface RequestKey {
        //app 包相关信息
        String FORM_KEY_APP_NAME = "appName";
        String FORM_KEY_PACKAGE_NAME = "packageName";
        String FORM_KEY_PACKAGE_SIGNATURE = "packageSignature";
        String FORM_KEY_VERSION_NAME = "appVersionName";
        String FORM_KEY_VERSION_CODE = "appVersionCode";

    }
    /**
     * 视频列表每页数据
     */
    public static final int DEFAULT_PAGE_SIZE = 10;
}
