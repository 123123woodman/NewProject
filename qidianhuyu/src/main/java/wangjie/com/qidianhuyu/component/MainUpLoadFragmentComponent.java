package wangjie.com.qidianhuyu.component;

import dagger.Component;
import wangjie.com.library.component.AppComponent;
import wangjie.com.library.scope.PerActivity;
import wangjie.com.qidianhuyu.fragment.MainUpLoadFragment;
import wangjie.com.qidianhuyu.module.MainUpLoadModule;
import wangjie.com.qidianhuyu.presenter.MainUpLoadPresenter;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {MainUpLoadModule.class})
public interface MainUpLoadFragmentComponent {

    void ingect(MainUpLoadFragment mainUpLoadFragment);

    MainUpLoadPresenter getWeChatPresenter();

}
