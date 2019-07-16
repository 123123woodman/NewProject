package wangjie.com.library.component;

import dagger.Component;
import wangjie.com.library.module.ActivityModule;
import wangjie.com.library.scope.SubActivity;

/**
 * Created by Administrator on 2018/3/21.
 */
@SubActivity
@Component(modules = {ActivityModule.class})
public interface ActivityComponent{

}
