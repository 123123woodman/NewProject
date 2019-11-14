package wangjie.com.qidianhuyu.ali.net.data;

import com.aliyun.player.source.UrlSource;
import com.aliyun.player.source.VidSts;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import wangjie.com.qidianhuyu.ali.view.video.videolist.IVideoSourceModel;
import wangjie.com.qidianhuyu.ali.view.video.videolist.VideoSourceType;

public class LittleVIdeoInfo implements IVideoSourceModel, Serializable {

    @SerializedName("videoUri")
    private String videoUri;
    private String UUID;
    private String userName;
    private String videodepict;
    private String videoType;
    private String firstImage;

    public String getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(String videoUri) {
        this.videoUri = videoUri;
    }

    public String getVideodepict() {
        return videodepict;
    }

    public void setVideodepict(String videodepict) {
        this.videodepict = videodepict;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    @Override
    public VideoSourceType getSourceType() {
        return VideoSourceType.TYPE_URL;
    }

    @Override
    public UrlSource getUrlSource() {
        return null;
    }

    @Override
    public VidSts getVidStsSource() {
        return null;
    }

    @Override
    public String getFirstFrame() {
        return null;
    }

    @Override
    public String getUUID() {
        return UUID;
    }

    @Override
    public String getUri() {
        return videoUri;
    }
}
