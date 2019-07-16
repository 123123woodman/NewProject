package wangjie.com.library.net;

import org.apache.http.conn.ConnectTimeoutException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import wangjie.com.library.R;
import wangjie.com.library.utils.StringFetcher;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
import static wangjie.com.library.Constants.HttpCode.HTTP_NETWORK_ERROR;

public class ResponseErrorProxy implements InvocationHandler {

    public static final String TAG = ResponseErrorProxy.class.getSimpleName();

    private Object mProxyObject;
    private RestApiClient mRestApiClient;

    public ResponseErrorProxy(Object proxyObject, RestApiClient restApiClient) {
        mProxyObject = proxyObject;
        mRestApiClient = restApiClient;
    }

    @Override
    public Object invoke(Object proxy, final Method method, final Object[] args) {
        return Observable.just(null)
                .flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Exception {
                        try {
                            return (Observable<?>) method.invoke(mProxyObject, args);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                })
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                                ResponseError error = null;
                                if (throwable instanceof ConnectTimeoutException
                                        || throwable instanceof SocketTimeoutException
                                        || throwable instanceof UnknownHostException
                                        || throwable instanceof ConnectException) {
                                    error = new ResponseError(HTTP_NETWORK_ERROR,
                                            StringFetcher.getString(R.string.toast_error_network));
                                } if (error.getStatus() == HTTP_UNAUTHORIZED) {
                                    //return refreshTokenWhenTokenInvalid();
                                } else {
                                    return Observable.error(error);
                                }
                                return null;
                            }
                        });
                    }
                });
    }

//    private Observable<?> refreshTokenWhenTokenInvalid() {
//        synchronized (ResponseErrorProxy.class) {
//            Map<String, String> params = new HashMap<>();
//            params.put(PARAM_CLIENT_ID, AppConfig.APP_KEY);
//            params.put(PARAM_CLIENT_SECRET, AppConfig.APP_SECRET);
//            params.put(PARAM_GRANT_TYPE, "refresh_token");
//            params.put(PARAM_REFRESH_TOKEN, AppCookie.getRefreshToken());
//
//            return mRestApiClient.tokenService()
//                    .refreshToken(params)
//                    .doOnNext(new Action1<Token>() {
//                        @Override
//                        public void call(Token token) {
//                            AppCookie.saveAccessToken(token.getAccessToken());
//                            AppCookie.saveRefreshToken(token.getRefreshToken());
//                            mRestApiClient.setToken(token.getAccessToken());
//                        }
//                    });
//        }
//    }
}
