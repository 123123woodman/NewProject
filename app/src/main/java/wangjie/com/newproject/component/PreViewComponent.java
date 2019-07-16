package wangjie.com.newproject.component;

import dagger.Component;
import wangjie.com.library.component.AppComponent;
import wangjie.com.library.scope.PerActivity;
import wangjie.com.newproject.activity.PreViewActivity;
import wangjie.com.newproject.module.PreViewModule;

/**
 * Created by Administrator on 2018/3/29.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {PreViewModule.class})
public interface PreViewComponent {

    void ingect(PreViewActivity preViewActivity);

}
