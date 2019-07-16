package wangjie.com.video.ui;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import wangjie.com.library.arouter.ArouterConstant;
import wangjie.com.library.base.activity.BaseActivity;
import wangjie.com.video.MyMediaPlayerManager;
import wangjie.com.video.R;
import wangjie.com.video.R2;
import wangjie.com.video.component.DaggerVideoComponent;
import wangjie.com.video.component.VideoComponent;
import wangjie.com.video.module.VideoModule;
import wangjie.com.video.presenter.VideoPresenter;

@Route(path = ArouterConstant.ACTIVITY_VIDEOACTIVITY, name = "王捷")
public class VideoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R2.id.voleumadd)
    public Button voleumAdd;
    @BindView(R2.id.voleumminus)
    public Button voleumMinus;
    @BindView(R2.id.stop)
    public Button videoStop;
    @BindView(R2.id.start)
    public Button videoStart;
    @BindView(R2.id.video_native)
    public Button videoNative;

    private VideoComponent videoComponent;

    @Inject
    public VideoPresenter videoPresenter;

    @Override
    public void initParameter() {
        videoComponent = DaggerVideoComponent.builder()
                .appComponent(getAppComponent())
                .videoModule(new VideoModule(this))
                .build();
        videoComponent.ingect(this);

        videoPresenter.test();

        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Throwable e) {
            Log.e("TAG", "loadLibraries error", e);
        }
    }

    int i = 0;
    @OnClick({R2.id.voleumadd, R2.id.voleumminus, R2.id.start, R2.id.stop, R2.id.video_native})
    public void onClick(View view) {
        int i1 = view.getId();
        if (i1 == R.id.voleumadd) {
            i += 1;
//                MyVideoManager.getInstance().ijkMediaPlayer.setVolume(i, i);
            MyMediaPlayerManager.getCurrentVideoplayer().changeVoleum(i);
        } else if (i1 == R.id.voleumminus) {
            i -= 1;
            MyMediaPlayerManager.getCurrentVideoplayer().changeVoleum(i);
//                MyVideoManager.getInstance().ijkMediaPlayer.setVolume(i, i);
        } else if (i1 == R.id.stop) {
            MyMediaPlayerManager.getCurrentVideoplayer().pauseVideo();
        } else if (i1 == R.id.start) {
            MyMediaPlayerManager.getCurrentVideoplayer().releaseVideo();
        } else if (i1 == R.id.video_native) {
            MyMediaPlayerManager.getCurrentVideoplayer().getVideoNative();
        }
    }

    @Override
    public int getLayOutId() {
        return R.layout.activity_video;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
