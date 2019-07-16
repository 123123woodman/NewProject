package wangjie.com.video;

/**
 * Created by Administrator on 2018/10/11.
 */

public class MyMediaPlayerManager {

    public static MyMediaPalyer FIRST_FLOOR_VIIDEOPLAYER;
    public static MyMediaPalyer SECOND_FLOOR_VIDEOPLAYER;

    public static void setFirstFloorViideoplayer(MyMediaPalyer firstFloorViideoplayer) {
        FIRST_FLOOR_VIIDEOPLAYER = firstFloorViideoplayer;
    }

    public static void setSecondFloorVideoplayer(MyMediaPalyer secondFloorVideoplayer) {
        SECOND_FLOOR_VIDEOPLAYER = secondFloorVideoplayer;
    }

    public static MyMediaPalyer getFirstFloorVideoPlayer() {
        return FIRST_FLOOR_VIIDEOPLAYER;
    }

    public static MyMediaPalyer getSecondFloorVideoplayer() {
        return SECOND_FLOOR_VIDEOPLAYER;
    }

    public static MyMediaPalyer getCurrentVideoplayer() {
        if (SECOND_FLOOR_VIDEOPLAYER != null) {
            return SECOND_FLOOR_VIDEOPLAYER;
        }
        return FIRST_FLOOR_VIIDEOPLAYER;
    }

    public static void completeAll() {
        if (FIRST_FLOOR_VIIDEOPLAYER != null) {
            //FIRST_FLOOR_VIIDEOPLAYER.co
            FIRST_FLOOR_VIIDEOPLAYER = null;
        } else if (SECOND_FLOOR_VIDEOPLAYER != null) {
            SECOND_FLOOR_VIDEOPLAYER = null;
        }
    }
}
