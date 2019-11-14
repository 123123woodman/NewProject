package wangjie.com.qidianhuyu.ali.view.video.videolist;

public enum VideoQuality {
    /**
     *原画
     */
    DEFAULT("OD"),
    /**
     *标清
     */
    DOWNLOAD("LD"),
    /**
     *流畅
     */
    PLAY("FD");

    VideoQuality(String value) {
        this.mValue = value;
    }
    private String mValue;

    public String getValue() {
        return mValue;
    }
}
