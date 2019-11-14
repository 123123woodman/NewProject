package wangjie.com.qidianhuyu.ali.net.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.aliyun.player.source.VidSts;

import java.util.List;
import wangjie.com.qidianhuyu.ali.view.video.BaseVideoSourceModel;
import wangjie.com.qidianhuyu.ali.view.video.videolist.VideoSourceType;

/**
 * 视频信息 javaBean
 *
 */
public class LittleMineVideoInfo implements Parcelable {

    private int total;
    private List<VideoListBean> videoList;

    private LittleMineVideoInfo(Parcel in) {
        total = in.readInt();
        videoList = in.createTypedArrayList(VideoListBean.CREATOR);
    }

    public static final Creator<LittleMineVideoInfo> CREATOR = new Creator<LittleMineVideoInfo>() {
        @Override
        public LittleMineVideoInfo createFromParcel(Parcel in) {
            return new LittleMineVideoInfo(in);
        }

        @Override
        public LittleMineVideoInfo[] newArray(int size) {
            return new LittleMineVideoInfo[size];
        }
    };

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<VideoListBean> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<VideoListBean> videoList) {
        this.videoList = videoList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(total);
        dest.writeTypedList(videoList);
    }

    public static class VideoListBean extends BaseVideoSourceModel implements Parcelable {

        private String videoId;

        private int duration;

        @Override
        public VideoSourceType getSourceType() {
            return VideoSourceType.TYPE_URL;
        }

        @Override
        public VidSts getVidStsSource() {
            return super.getVidStsSource();
        }

        @Override
        public String getUUID() {
            return videoId;
        }

        @Override
        public String getUri() {
            return null;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.videoId);
            dest.writeInt(this.duration);
            dest.writeString(this.id);
            dest.writeString(this.title);
            dest.writeString(this.description);
            dest.writeString(this.coverUrl);
            dest.writeString(this.creationTime);
            dest.writeString(this.status);
            dest.writeString(this.firstFrameUrl);
            dest.writeInt(this.size);
            dest.writeInt(this.cateId);
            dest.writeString(this.cateName);
            dest.writeString(this.tags);
            dest.writeString(this.shareUrl);
            dest.writeParcelable(this.user, flags);
        }

        public VideoListBean(Parcel in) {
            this.videoId = in.readString();
            this.duration = in.readInt();
            this.id = in.readString();
            this.title = in.readString();
            this.description = in.readString();
            this.coverUrl = in.readString();
            this.creationTime = in.readString();
            this.status = in.readString();
            this.firstFrameUrl = in.readString();
            this.size = in.readInt();
            this.cateId = in.readInt();
            this.cateName = in.readString();
            this.tags = in.readString();
            this.shareUrl = in.readString();
            this.user = in.readParcelable(UserBean.class.getClassLoader());
        }

        public static final Creator<VideoListBean> CREATOR = new Creator<VideoListBean>() {
            @Override
            public VideoListBean createFromParcel(Parcel source) {
                return new VideoListBean(source);
            }

            @Override
            public VideoListBean[] newArray(int size) {
                return new VideoListBean[size];
            }
        };
    }
}
