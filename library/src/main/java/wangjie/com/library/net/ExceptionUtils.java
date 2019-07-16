package wangjie.com.library.net;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import wangjie.com.library.base.base.BaseApplication;

public class ExceptionUtils {

    /**
     * 对应HTTP的状态码
     */
    private static final int BAD_REQUEST = 400;
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int METHOD_NOT_ALLOWED = 405;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int CONFLICT = 409;
    private static final int PRECONDITION_FAILED = 412;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    /**
     * server状态码
     */
    public static final int CODE_SUCCESS = 200; //完美
    public static final int CODE_TOKEN_INVALID = 401; //token实效
    public static final int CODE_NO_MISSING_PARAMETER = 40000; //缺少参数
    public static final int CODE_NO_OTHER = 40001; //其他信息
    public static final int CODE_UNIFY_TOAST = 40002; //统一提示

    public static void ServerException(int code, String dispalyMessage) {
        if (code == CODE_SUCCESS) {
            ServerException serverException = new ServerException();
            serverException.setCode(code);
            serverException.setDisplayMessage(dispalyMessage);
            handleHttpException(serverException);
        }
    }

    public static void handleHttpException(Throwable throwable) {
        HttpException ex;
        if (throwable instanceof retrofit2.HttpException) {
            retrofit2.HttpException httpException = (retrofit2.HttpException) throwable;
            ex = new HttpException(httpException, ErrorCode.HTTP_ERROR);
            switch (httpException.code()) {
                case BAD_REQUEST:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case METHOD_NOT_ALLOWED:
                case REQUEST_TIMEOUT:
                case CONFLICT:
                case PRECONDITION_FAILED:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                case GATEWAY_TIMEOUT:
                    default:
                    ex.setDisplayMessage("网络错误" + httpException.code());
                    break;
            }
        } else if (throwable instanceof ServerException) {
            ServerException serverException = (ServerException) throwable;
            ex = new HttpException(throwable, ErrorCode.SERVER_ERROR);
            switch (serverException.getCode()) {
                case CODE_TOKEN_INVALID:
                    ex.setDisplayMessage("重新登录");
                    break;
                case CODE_NO_MISSING_PARAMETER:
                    ex.setDisplayMessage("缺少参数");
                case CODE_UNIFY_TOAST:
                    ex.setDisplayMessage("统一提示");
                    default:
                        ex.setDisplayMessage(serverException.getDisplayMessage());
            }
        } else if (throwable instanceof JsonParseException
                    || throwable instanceof JSONException
                    || throwable instanceof ParseException) {
                ex = new HttpException(throwable, ErrorCode.PARSE_ERROR);
                ex.setDisplayMessage("解析异常");
        } else if (throwable instanceof ConnectException) {
                ex = new HttpException(throwable, ErrorCode.NET_ERROT);
                ex.setDisplayMessage("连接失败");
        } else if (throwable instanceof UnknownHostException) {
                ex = new HttpException(throwable, ErrorCode.NET_ERROT);
                ex.setDisplayMessage("网络未连接");
        } else if (throwable instanceof SocketTimeoutException) {
                ex = new HttpException(throwable, ErrorCode.NET_ERROT);
                ex.setDisplayMessage("服务器响应超时");
        } else {
            ex = new HttpException(throwable, ErrorCode.UNKNOWN);
            ex.setDisplayMessage("未知异常");
        }
        BaseApplication.getBaseApplication().getToastUtils().showToast(ex.getDisplayMessage());
    }
}
