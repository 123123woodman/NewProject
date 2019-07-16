package wangjie.com.newproject.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/22.
 */
public class NewsData implements Serializable{

    @SerializedName("articleType")
    String articleType;

    @SerializedName("channelId")
    String channelId;

    @SerializedName("channelName")
    String channelName;

    @SerializedName("classfy")
    String classfy;

    @SerializedName("count")
    String count;

    @SerializedName("downs")
    String downs;

    @SerializedName("id")
    String id;

    @SerializedName("image")
    String image;

    @SerializedName("imageList")
    String imageList;

    @SerializedName("title")
    String title;

    @SerializedName("type")
    String type;

    @SerializedName("ups")
    String ups;

    @SerializedName("url")
    String url;

    @SerializedName("voteId")
    String voteId;

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public String getUps() {
        return ups;
    }

    public void setUps(String ups) {
        this.ups = ups;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageList() {
        return imageList;
    }

    public void setImageList(String imageList) {
        this.imageList = imageList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDowns() {
        return downs;
    }

    public void setDowns(String downs) {
        this.downs = downs;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getClassfy() {
        return classfy;
    }

    public void setClassfy(String classfy) {
        this.classfy = classfy;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "NewsData{" +
                "articleType='" + articleType + '\'' +
                ", channelId='" + channelId + '\'' +
                ", channelName='" + channelName + '\'' +
                ", classfy='" + classfy + '\'' +
                ", count='" + count + '\'' +
                ", downs='" + downs + '\'' +
                ", id='" + id + '\'' +
                ", image='" + image + '\'' +
                ", imageList='" + imageList + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", ups='" + ups + '\'' +
                ", url='" + url + '\'' +
                ", voteId='" + voteId + '\'' +
                '}';
    }
}
