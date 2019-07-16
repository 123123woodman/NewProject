package wangjie.com.newproject.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/5/9.
 */

public class YiZhaoYun {

//    @SerializedName("status")
//    String status;
//
//    @SerializedName("msg")
//    String msg;
//
//    @SerializedName("sid")
//    String sid;


    @SerializedName("code")
    String code;

    @SerializedName("message")
    String message;

    @SerializedName("data")
    String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "YiZhaoYun{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    //    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public String getSid() {
//        return sid;
//    }
//
//    public void setSid(String sid) {
//        this.sid = sid;
//    }
//
//    @Override
//    public String toString() {
//        return "YiZhaoYun{" +
//                "status='" + status + '\'' +
//                ", msg='" + msg + '\'' +
//                ", sid='" + sid + '\'' +
//                '}';
//    }
}
