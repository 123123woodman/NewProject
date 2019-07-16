package wangjie.com.newproject.data.xiaoliu;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2019/5/20.
 */

public class People {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("createTime")
    private String createTime;
    @SerializedName("status")
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
