package wangjie.com.library.net;

import android.content.Context;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiClient {

    private static final String DEVICE_TYPE = "android";

    private Context mContext;
    private File mCacheLocation;
    private Retrofit mRetrofit;
    private String mToken;

    public RestApiClient(Context context, File cacheLocation) {
        mContext = context;
        mCacheLocation = cacheLocation;
    }

    public RestApiClient(Context context) {
        mContext = context;
    }

    public RestApiClient setToken(String token) {
        mToken = token;
        mRetrofit = null;
        return this;
    }

    private OkHttpClient newRetrofitClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        // 设置缓存路径
//        if (mCacheLocation != null) {
//            File cacheDir = new File(mCacheLocation, UUID.randomUUID().toString());
//            Cache cache = new Cache(cacheDir, 1024);
//            builder.cache(cache);
//        }
        // 设置超时时间
        builder.connectTimeout(RESTfulFactory.CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        builder.readTimeout(RESTfulFactory.READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        // 添加请求头
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder()
                        //                    .addHeader("token", SPModel.getToken())
                        .build();
                Log.i("TAG", request.toString() + " " + request.url());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Response response = chain.proceed(request);
                response.message();
                return response;
            }
        });

        try {
            builder.sslSocketFactory(SSLFactory.getSSLSocketFactory(mContext));
            builder.hostnameVerifier(SSLFactory.getHostnameVerifier());

        } catch (Exception e) {
            e.printStackTrace();
        }
        // 添加OkHttp日志打印器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(RESTfulFactory.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        builder.addInterceptor(logging);
        return builder.build();
    }

    private Retrofit getRetrofit() {
        if (mRetrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(RESTfulFactory.HttpsUrl);
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            builder.client(newRetrofitClient());

            mRetrofit = builder.build();
        }

        return mRetrofit;
    }

    public  <T> T get(Class<T> clazz) {
        return getRetrofit().create(clazz);
    }

//    @SuppressWarnings("unchecked")
//    public  <T> T getByProxy(Class<T> clazz) {
//        T t = get(clazz);
//        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[] { clazz },
//                new ResponseErrorProxy(t, this));
//    }

//    public <T> T getUrlService(Class<T> clazz) {
//        return getRetrofit().create(clazz);
//    }

}
