package wangjie.com.qidianhuyu.ali.net;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import wangjie.com.qidianhuyu.ali.constants.AlivcLittleHttpConfig;
import wangjie.com.qidianhuyu.ali.constants.AlivcLittleServerApiConstants;
import wangjie.com.qidianhuyu.ali.net.data.LittleHttpResponse;
import wangjie.com.qidianhuyu.ali.net.data.LittleUserInfo;
import wangjie.com.qidianhuyu.ali.view.mine.AlivcLittleUserManager;

/**
 * http manager
 */

public class LittleHttpManager {
    private static LittleHttpManager mInstance;
    private HttpEngine mHttpEngine;
    public static LittleHttpManager getInstance() {
        if (mInstance == null) {
            synchronized (LittleHttpManager.class) {
                if (mInstance == null) {
                    mInstance = new LittleHttpManager();
                }
            }
        }
        return mInstance;
    }
    public void init(Context context){
        mHttpEngine = new HttpEngine(context);
    }
    /**
     * 生成新用户
     *
     * @param callback OnResponseCallback
     */
    public void newGuest(HttpEngine.HttpResponseResultCallback<LittleHttpResponse.LittleUserInfoResponse> callback) {
        Request request = new Request.Builder()
            .url(AlivcLittleServerApiConstants.URL_NEW_GUEST)
            .get()
            .build();
        mHttpEngine.request(request, LittleHttpResponse.LittleUserInfoResponse.class, callback);
    }

    /**
     * 拼接get 请求的url
     *
     * @param baseUrl baseUrl
     * @param params  要拼接的参数
     * @return
     */
    private String urlAppend(String baseUrl, HashMap<String, String> params) {
        StringBuffer absUrl = new StringBuffer();
        absUrl.append(baseUrl);
        int size = 0;
        if (params != null && params.size() > 0) {
            Set<Map.Entry<String, String>> entries = params.entrySet();
            for (Map.Entry<String, String> item : entries) {
                if (size == 0) {
                    absUrl.append("?" + item.getKey() + "=" + item.getValue());
                    size++;
                } else {
                    absUrl.append("&" + item.getKey() + "=" + item.getValue());
                }
            }
        }
        return absUrl.toString();
    }

//    /**
//     * 获取视频列表
//     *
//     * @param token
//     * @param pageIndex
//     * @param pageSize
//     * @param callback
//     */
//    public void requestRecommonVideoList( String token, int pageIndex, int pageSize, int id,
//                                          HttpEngine.HttpResponseResultCallback<LittleHttpResponse
//                                                  .LittleMyVideoListResponse> callback) {
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put(AlivcLittleHttpConfig.RequestKey.FORM_KEY_PAGE_INDEX, pageIndex + "");
//        hashMap.put(AlivcLittleHttpConfig.RequestKey.FORM_KEY_PAGE_SIZE, pageSize + "");
//        hashMap.put(AlivcLittleHttpConfig.RequestKey.FORM_KEY_TOKEN, token);
//        if (id > 0) {
//            hashMap.put(AlivcLittleHttpConfig.RequestKey.FORM_KEY_ID, id + "");
//        }
//        Request request = new Request.Builder().url(
//                urlAppend(AlivcLittleServerApiConstants.URL_GET_RECOMMEND_VIDEO_LIST, hashMap))
//                .get()
//                .build();
//        mHttpEngine.request(request, LittleHttpResponse.LittleMyVideoListResponse.class, callback);
//    }


    public void cancelRequest(String cancelUrl) {
        mHttpEngine.cancel(cancelUrl);
    }

}
