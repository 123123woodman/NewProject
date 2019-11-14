package wangjie.com.qidianhuyu.entity.wx;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import wangjie.com.qidianhuyu.entity.data.BaseData;

public class WXChartTitle extends BaseData {

    @SerializedName("courseId")
    private int courseId;
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("order")
    private int order;
    @SerializedName("parentChapterId")
    private int parentChapterId;
    @SerializedName("userControlSetTop")
    private boolean userControlSetTop;
    @SerializedName("visible")
    private int visible;
    @SerializedName("children")
    private List<ChapterBean> children;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public boolean isUserControlSetTop() {
        return userControlSetTop;
    }

    public void setUserControlSetTop(boolean userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public List<ChapterBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChapterBean> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "WXChartTitle{" +
                "courseId=" + courseId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", parentChapterId=" + parentChapterId +
                ", userControlSetTop=" + userControlSetTop +
                ", visible=" + visible +
                ", children=" + children +
                '}';
    }
}
