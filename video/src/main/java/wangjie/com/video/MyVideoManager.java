package wangjie.com.video;

import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.IjkTimedText;
import wangjie.com.library.api.ConstantVideoApi;

/**
 * Created by Administrator on 2018/10/11.
 */

public class MyVideoManager extends MyMidiaPalyerListern implements TextureView.SurfaceTextureListener{

    private String TAG = "MyVideoPalyer";
    public static MyTextureView textureView;
    public static MyVideoManager myVideoManager;
    public static SurfaceTexture saveSurfaceTexture;
    private final int HANDLER_PREPARE = 0;
    private final int HANDLER_RELEASE = 2;
    private MyMediaHandler myMediaHandler;
    private HandlerThread handlerThread;
    private Handler mainHandler;
    public IjkMediaPlayer ijkMediaPlayer;
    private int currentViewWidth = 0;
    private int currentViewHeight = 0;

    public static MyVideoManager getInstance() {
        if (myVideoManager == null) {
            myVideoManager = new MyVideoManager();
        }
        return myVideoManager;
    }

    public MyVideoManager() {
        mainHandler = new Handler();
        handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        myMediaHandler = new MyMediaHandler(handlerThread.getLooper());
    }

    private class MyMediaHandler extends Handler {
        public MyMediaHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HANDLER_PREPARE:
                    try {
                        createPlayer();
                        ijkMediaPlayer.setOnBufferingUpdateListener(MyVideoManager.this);
                        ijkMediaPlayer.setOnCompletionListener(MyVideoManager.this);
                        ijkMediaPlayer.setOnErrorListener(MyVideoManager.this);
                        ijkMediaPlayer.setOnInfoListener(MyVideoManager.this);
                        ijkMediaPlayer.setOnPreparedListener(MyVideoManager.this);
                        ijkMediaPlayer.setOnSeekCompleteListener(MyVideoManager.this);
                        ijkMediaPlayer.setOnTimedTextListener(MyVideoManager.this);
                        ijkMediaPlayer.setOnVideoSizeChangedListener(MyVideoManager.this);
                        ijkMediaPlayer.setDataSource(ConstantVideoApi.DAGU_GIRL);
                        ijkMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        ijkMediaPlayer.setScreenOnWhilePlaying(true);
                        ijkMediaPlayer.prepareAsync();
                        ijkMediaPlayer.setSurface(new Surface(saveSurfaceTexture));
                        ijkMediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case HANDLER_RELEASE:

                    break;
            }
        }
    }

    private void createPlayer() {
        ijkMediaPlayer = new IjkMediaPlayer();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N){}
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", IjkMediaPlayer.SDL_FCC_RV32);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 1);
        //ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "http-detect-range-support", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", "all");
        //isLive();


//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "ijkmeta-delay-init", 1);
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "opensles", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "soundtouch", 1);//调节视频倍速开关 V
        //ijkMediaPlayer.setSpeed(8.0f);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "an", 0); //禁用音频 V
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "vn", 0);//禁用视频  V
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "nodisp", 0);//禁用图形显示
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "volume", 100);//set startup volume 0=min 100=max 暂定  实际测试100有声音且可调小于100不可调且没声音
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "fast", 1);//non spec compliant optimizations  暂定
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "loop", 1);//循环播放次数 V
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "infbuf", 1);//无限读 V（1）
//        ijkMediaPlayer.
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 0);//当cpu慢时放弃帧
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,"seek-at-start", 0);//设置播放偏移量 V
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "subtitle", 1);//解码字幕流
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "af", null);//音频滤镜
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "vf0", null);//视频滤镜
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "rdftspeed", 10);//rdft速度
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "find_stream_info", 1);//阅读解码流填补缺失
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "max-fps", 31);//视频中帧数大于最大值的帧数
////        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", "fcc-_es2");
////        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", "fcc-i420");
////        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", "fcc-yv12");
////        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", "fcc-rv16");
////        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", "fcc-rv24");
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", "fcc-rv32");
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 1);//准备立即播放
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "video-pictq-size", 10);//max picture queue frame count
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "max-buffer-size", 15 * 1024 * 1024);//缓存大小 V
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "min-frames", 10);//停止于都最小帧
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "first-high-water-mark-ms", 5 * 1000);//HW加速
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "next-high-water-mark-ms", 10 * 1000);
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "last-high-water-mark-ms", 20 * 1000);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "packet-buffering", 1);// 1:打开缓存 0:关闭缓存 V
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "sync-av-start", 1);//同步音视频启动时间
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "vno-time-adjust", 1);//返回流媒体实时时间
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "enable-accurate-seek", 0);//精确寻找
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "accurate-seek-timeout", 5 * 1000);//精确寻找超时
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "skip-calc-frame-rate", 0);//计算帧率
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "async-init-decoder", 0);//异步创建编码器
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", "all");//抛弃所有帧
    }

    //硬解及相关参数
    private void dxav() {
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-handle-resolution-change", 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-all-videos", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-avc", "0");
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-hevc", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-mpeg2", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-mpeg4", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-sync", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-default-name", "ijk");
    }

    private void isLive() {
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT,"analyzemaxduration",100);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT,"analyzeduration",1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT,"probesize", 1024);
    }

    private void preparePlayer() {
        releasePlayer();
        Message message = new Message();
        message.what = HANDLER_PREPARE;
        myMediaHandler.handleMessage(message);
    }

    private void releasePlayer() {
        Message message = new Message();
        message.what = HANDLER_RELEASE;
        myMediaHandler.handleMessage(message);
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Log.i("TAGGG", "onSurfaceTextureAvailable");
        if (saveSurfaceTexture == null) {
            saveSurfaceTexture = surface;
            preparePlayer();
        } else {
            textureView.setSurfaceTexture(surface);
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    public void onPrepared(IMediaPlayer mp) {
        ijkMediaPlayer.start();
    }

    @Override
    public void onCompletion(IMediaPlayer mp) {

    }

    @Override
    public void onBufferingUpdate(IMediaPlayer mp, int percent) {
        Log.i("TAG", "percent----->" + percent + "---" + ijkMediaPlayer.getVideoCachedDuration() + "---" + ijkMediaPlayer.getVideoCachedBytes() + "---" + ijkMediaPlayer.getVideoCachedPackets() + "---" + ijkMediaPlayer.getDuration() + "---");
    }

    @Override
    public void onSeekComplete(IMediaPlayer mp) {

    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sar_num, int sar_den) {
        currentViewWidth = width;
        currentViewHeight = height;
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                if (MyMediaPlayerManager.getCurrentVideoplayer() != null) {
                    MyMediaPlayerManager.getCurrentVideoplayer().videoSizeChange();
                }
            }
        });
    }

    @Override
    public boolean onError(IMediaPlayer mp, int what, int extra) {

        if (what == IMediaPlayer.MEDIA_INFO_VIDEO_INTERRUPT) {
            Log.i("ZZZZ", "what------->" + what + "---extra--->" + extra);
//            MyMediaPlayerManager.getCurrentVideoplayer().resetVideo();
//            MyMediaPlayerManager.getCurrentVideoplayer().startVideo();
        }

        return false;
    }

    @Override
    public boolean onInfo(IMediaPlayer mp, int what, int extra) {
        Log.i("TAG", "what------>" + what + "---extra----" + extra);
        return false;
    }

    @Override
    public void onTimedText(IMediaPlayer mp, IjkTimedText text) {

    }

    public Point getVideoSize() {
        if (currentViewWidth != 0 && currentViewHeight != 0) {
            return new Point(currentViewHeight, currentViewHeight);
        } else {
            return null;
        }
    }
}
