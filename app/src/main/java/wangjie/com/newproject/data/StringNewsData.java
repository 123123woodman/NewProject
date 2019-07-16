package wangjie.com.newproject.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/23.
 */
public class StringNewsData<T, U, I, O> {

    @SerializedName("data")
    ArrayList<T> data;

    @SerializedName("focus")
    ArrayList<U> focus;

    @SerializedName("topData")
    ArrayList<I> topData;

    @SerializedName("topFocus")
    ArrayList<O> topFocus;

    @SerializedName("pageNo")
    String pageNo;

    @SerializedName("pageSize")
    String pageSize;

    @SerializedName("total")
    String total;

    @SerializedName("status")
    String status;

    @SerializedName("msg")
    String msg;

    public ArrayList<U> getFocus() {
        return focus;
    }

    public void setFocus(ArrayList<U> focus) {
        this.focus = focus;
    }

    public ArrayList<I> getTopData() {
        return topData;
    }

    public void setTopData(ArrayList<I> topData) {
        this.topData = topData;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

    public ArrayList<O> getTopFocus() {
        return topFocus;
    }

    public void setTopFocus(ArrayList<O> topFocus) {
        this.topFocus = topFocus;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "StringNewsData{" +
                "data=" + data +
                ", focus=" + focus +
                ", topData=" + topData +
                ", topFocus=" + topFocus +
                ", pageNo='" + pageNo + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", total='" + total + '\'' +
                ", status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
