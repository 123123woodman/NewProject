package wangjie.com.newproject.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/23.
 */
public class NewsFocus implements Serializable{

    @SerializedName("articleType")
    String articleType;

    @SerializedName("classfy")
    String classfy;

    @SerializedName("id")
    String id;

    @SerializedName("image")
    String image;

    @SerializedName("pubDate")
    String pubDate;

    @SerializedName("topicUrl")
    String topicUrl;

    @SerializedName("url")
    String url;

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getClassfy() {
        return classfy;
    }

    public void setClassfy(String classfy) {
        this.classfy = classfy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTopicUrl() {
        return topicUrl;
    }

    public void setTopicUrl(String topicUrl) {
        this.topicUrl = topicUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NewsFocus{" +
                "articleType='" + articleType + '\'' +
                ", classfy='" + classfy + '\'' +
                ", id='" + id + '\'' +
                ", image='" + image + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", topicUrl='" + topicUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
