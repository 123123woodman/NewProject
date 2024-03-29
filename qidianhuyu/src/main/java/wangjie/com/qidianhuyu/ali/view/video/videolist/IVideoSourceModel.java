package wangjie.com.qidianhuyu.ali.view.video.videolist;

import com.aliyun.player.source.UrlSource;
import com.aliyun.player.source.VidSts;

public interface IVideoSourceModel {
    /**
     * 获取资源播放方式
     * @return
     */
    VideoSourceType getSourceType();

    /**
     * 获取视频url相关信息，用于播放该资源，使用URL播放
     * @return
     */
    UrlSource getUrlSource();

    /**
     * 获取视频sts信息，用于播放该资源,使用vid+STS方式播放
     * @return
     */
    VidSts getVidStsSource();

    /**
     * 获取首帧路径
     * @return
     */
    String getFirstFrame();

    /**
     * 获取视频的唯一标示
     * @return
     */
    String getUUID();

    /**
     * 視頻地址
     */
    String getUri();

}
