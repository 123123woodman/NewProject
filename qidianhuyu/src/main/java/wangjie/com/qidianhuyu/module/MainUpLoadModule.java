package wangjie.com.qidianhuyu.module;

import dagger.Module;
import dagger.Provides;
import wangjie.com.library.scope.PerActivity;
import wangjie.com.qidianhuyu.fragment.MainUpLoadFragment;
import wangjie.com.qidianhuyu.presenter.MainUpLoadPresenter;

@Module
public class MainUpLoadModule {

    private MainUpLoadFragment mainUpLoadFragment;

    public MainUpLoadModule(MainUpLoadFragment mainUpLoadFragment) {
        this.mainUpLoadFragment = mainUpLoadFragment;
    }

    @Provides
    @PerActivity
    public MainUpLoadPresenter provideWeChatPresenter() {
        return new MainUpLoadPresenter(mainUpLoadFragment);
    }
}
