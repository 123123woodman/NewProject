package wangjie.com.video;

import android.content.Context;
import android.media.AudioManager;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2018/10/11.
 * 此类注释是设置TextureView
 * 以及一些公共控件
 */

public abstract class MyMediaPalyer extends FrameLayout{

//    @Bind(R.id.surface_control)
    ViewGroup mSurfaceControl;

    private AudioManager audioManager;

    public MyMediaPalyer(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public MyMediaPalyer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyMediaPalyer(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public MyMediaPalyer(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        initView(context);
//    }

    public void initView(Context context) {
        View.inflate(context, getLayoutId(), this);
        mSurfaceControl = (ViewGroup) findViewById(R.id.surface_control);
        audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        prepareVideoPalyer();
    }

    public void initTextureView() {
        try {
            removeTextureView();
            MyVideoManager.textureView = new MyTextureView(getContext());
            MyVideoManager.textureView.setSurfaceTextureListener(MyVideoManager.getInstance());
//            MyVideoManager.textureView.setRotation(0);
        } catch (Throwable e) {
            Log.e("TAG", "" + e);
        }

    }

    public void addTextureView() {
        try {
            LayoutParams layoutParams
                    = new LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    Gravity.CENTER);
            mSurfaceControl.addView(MyVideoManager.textureView, layoutParams);
        } catch (Exception w) {
            Log.e("TAG", "" + w);
        }
    }

    public void removeTextureView() {
        MyVideoManager.saveSurfaceTexture = null;
        if (MyVideoManager.textureView != null && MyVideoManager.textureView.getParent() != null) {
            ((ViewGroup)MyVideoManager.textureView.getParent()).removeView(MyVideoManager.textureView);
        }
    }

    public void prepareVideoPalyer() {
        initTextureView();
        addTextureView();
        MyMediaPlayerManager.FIRST_FLOOR_VIIDEOPLAYER = this;
        //MyVideoManager.getInstance().ijkMediaPlayer.start();
        //AudioManager audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
    }

    public abstract int getLayoutId();

    public void videoSizeChange() {
        if (MyVideoManager.textureView != null) {
            MyVideoManager.textureView.setVideoSize(MyVideoManager.getInstance().getVideoSize());
        }
    }

    public static AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            switch (i) {
                case AudioManager.AUDIOFOCUS_LOSS:
                    //如果参数为这个则说明声音丢失建议释放所有资源，以免对用户造成不适
                    //releaseAllVideo();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    //短暂的声音不同步先暂停视频
//                    if (MyVideoManager.getInstance().mediaPlayer != null &&
//                            MyVideoManager.getInstance().mediaPlayer.isPlaying()) {
//                        MyVideoManager.getInstance().mediaPlayer.pause();
//                    }
                    break;
            }
        }
    };

    public void changeVoleum(int voleum) {
        int voleumm =  audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        Log.i("TAG", "voleum------->" + voleumm);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, voleum, 0);
    }

    public void stopVideo() {
        MyVideoManager.getInstance().ijkMediaPlayer.stop();
    }

    public void startVideo() {
        MyVideoManager.getInstance().ijkMediaPlayer.start();
    }

    public void pauseVideo() {
        MyVideoManager.getInstance().ijkMediaPlayer.pause();
    }

    public void releaseVideo() {
        MyVideoManager.getInstance().ijkMediaPlayer.seekTo(150000);
        MyVideoManager.getInstance().ijkMediaPlayer.start();
    }

    public void getVideoNative() {
        long x = MyVideoManager.getInstance().ijkMediaPlayer.getWangjie();
        long y = MyVideoManager.getInstance().ijkMediaPlayer.getCurrentPosition();
        long w = MyVideoManager.getInstance().ijkMediaPlayer.getDuration();
        Log.i("SDSD", "x------------>" + x + "-----y----->" + y + "------w-------->" + w);
    }

    public void resetVideo() {
        MyVideoManager.getInstance().ijkMediaPlayer.reset();
    }

}
