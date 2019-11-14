package wangjie.com.qidianhuyu.ali.constants;

import android.os.Environment;

import wangjie.com.qidianhuyu.ali.view.video.videolist.VideoQuality;


/**
 *
 */
public class LittleVideoParamConfig {

    public static class Player {
        /**
         * 视频播放的region
         */
        public static String REGION = "cn-shanghai";
        /**
         * 是否打开播放器日志开关
         */
        public static boolean LOG_ENABLE = true;
    }

    /**
     * 视频列表缓存目录
     */
    public static String DIR_CACHE = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AlivcQuVideo/cache";


}
