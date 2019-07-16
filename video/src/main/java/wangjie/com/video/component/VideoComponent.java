package wangjie.com.video.component;

import dagger.Component;
import wangjie.com.library.component.AppComponent;
import wangjie.com.library.scope.PerActivity;
import wangjie.com.video.module.VideoModule;
import wangjie.com.video.presenter.VideoPresenter;
import wangjie.com.video.ui.VideoActivity;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {VideoModule.class})
public interface VideoComponent {
    void ingect(VideoActivity videoActivity);

    VideoPresenter getVideoPresenter();
}
