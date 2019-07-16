package wangjie.com.newproject.data.xiaoliu;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2019/5/20.
 */

public class XiaoLiuSourse {
    @SerializedName("code")
    private String code;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<People> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<People> getData() {
        return data;
    }

    public void setData(List<People> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "XiaoLiuSourse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
