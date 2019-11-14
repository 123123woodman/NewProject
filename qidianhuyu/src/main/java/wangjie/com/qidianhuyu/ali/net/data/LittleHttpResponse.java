package wangjie.com.qidianhuyu.ali.net.data;


import android.support.annotation.NonNull;

/**
 * http response
 * 包含公共部分和独有部分
 */
public class LittleHttpResponse {
    public boolean result;
    public String message;
    public int code;



    /**
     * 生成随机用户
     */
    public class LittleUserInfoResponse extends LittleHttpResponse {
        public LittleUserInfo data;
        @NonNull
        @Override
        public String toString() {
            return "LittleUserInfoResponse{" +
                   "userData=" + data +
                   '}';
        }
    }


}
