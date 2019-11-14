package wangjie.com.qidianhuyu.ali.net;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import wangjie.com.qidianhuyu.ali.constants.AlivcLittleHttpConfig;
import wangjie.com.qidianhuyu.ali.net.data.LittleHttpResponse;
import wangjie.com.qidianhuyu.ali.utils.AppInfoUtils;
import wangjie.com.qidianhuyu.ali.utils.ThreadUtils;

/**
 * http Engine
 */
public class HttpEngine {
    private static final int NET_CONNECT_TIME_OUT = 10;
    private static final int NET_READ_TIME_OUT = 10;

    private ConcurrentHashMap<String, Call> map = new ConcurrentHashMap<>();
    private OkHttpClient okHttpClient;
    private Context mContext;
    private String mAppName,mPackageName,mSignature,mVersionName;
    private int mVersionCode;

    public HttpEngine(final Context context) {
        mContext = context;
        mAppName = URLEncoder.encode(AppInfoUtils.getAppName(context));
        mPackageName = context.getPackageName();
        List<String> signatures = AppInfoUtils.getSingInfo(mContext,context.getPackageName() ,
            AppInfoUtils.MD5);
        mSignature = signatures.get(0);
        mVersionName = AppInfoUtils.getVersionName(context);
        mVersionCode = AppInfoUtils.getVersionCode(context);
        okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(NET_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(NET_READ_TIME_OUT, TimeUnit.SECONDS)
            //通过拦截器向header添加包信息
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                        .addHeader(AlivcLittleHttpConfig.RequestKey.FORM_KEY_APP_NAME, mAppName)
                        .addHeader(AlivcLittleHttpConfig.RequestKey.FORM_KEY_PACKAGE_NAME,mPackageName)
                        .addHeader(AlivcLittleHttpConfig.RequestKey.FORM_KEY_PACKAGE_SIGNATURE, mSignature)
                        .addHeader(AlivcLittleHttpConfig.RequestKey.FORM_KEY_VERSION_NAME, mVersionName)
                        .addHeader(AlivcLittleHttpConfig.RequestKey.FORM_KEY_VERSION_CODE, mVersionCode+"")
                        .build();
                    return chain.proceed(request);
                }
            })
            .build();

    }

    public <T extends LittleHttpResponse> void request(Request request, final Class<T> responseClass,
                                                       final HttpResponseResultCallback<T> callback) {
        Call call = okHttpClient.newCall(request);

        map.put(request.url().toString(), call);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                callbackPost(new Runnable() {

                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onResponse(false, "request failure, error message : " + e.getMessage(), null);
                        }
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();

                try {
                    final T resp = gson.fromJson(result, responseClass);
                    if (resp == null) {
                        return;
                    }

                    String message = resp.message;

                    if (resp.result) {
                        message += (" [code=" + resp.code + "] ");
                    }

                    if (callback != null) {
                        final String finalErrorMessage = message;
                        callbackPost(new Runnable() {
                            @Override
                            public void run() {
                                callback.onResponse(resp.result, finalErrorMessage, resp);
                            }
                        });
                    }
                } catch (Exception e) {
                    onFailure(call, new IOException(e.getMessage()));
                }

            }
        });
    }

    private void callbackPost(Runnable runnable) {
        ThreadUtils.runOnUiThread(runnable);
    }

    public void cancel(String url) {
        if (map != null && map.containsKey(url)) {
            Call remove = map.remove(url);
            remove.cancel();
        }
    }

    public interface HttpResponseResultCallback<T> {
        void onResponse(boolean result, String message, T data);

    }
}