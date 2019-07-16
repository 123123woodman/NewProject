package wangjie.com.video.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import wangjie.com.library.scope.PerActivity;
import wangjie.com.video.presenter.VideoPresenter;

@Module
public class VideoModule {
    public Context context;
    public VideoModule(Context context) {this.context = context;}

    @Provides @PerActivity
    public VideoPresenter provideVdeoPresenter() {return new VideoPresenter(context);}
}
