package wangjie.com.library.component;

import dagger.Component;
import wangjie.com.library.LoginActivity;
import wangjie.com.library.module.LoginModule;
import wangjie.com.library.scope.PerActivity;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {LoginModule.class})
public interface LoginComponent {
    void ingect(LoginActivity loginActivity);
}
