package wangjie.com.qidianhuyu.entity.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import wangjie.com.qidianhuyu.entity.data.BaseData;

public class BaseResponse<T extends BaseData> {

    @SerializedName("data")
    private List<T> data;
    @SerializedName("errorCode")
    int errorCode;
    @SerializedName("errorMsg")
    String errorMsg;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
