package wangjie.com.newproject.component;

import dagger.Component;
import wangjie.com.library.component.ActivityComponent;
import wangjie.com.library.component.AppComponent;
import wangjie.com.library.scope.PerActivity;
import wangjie.com.newproject.activity.MainActivity;
import wangjie.com.newproject.module.MainModule;

/**
 * Created by Administrator on 2018/3/21.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {MainModule.class})
public interface MainComponent extends ActivityComponent {
    void ingect(MainActivity activity);

}
