package wangjie.com.library.component;

import javax.inject.Singleton;

import dagger.Component;
import wangjie.com.library.base.base.BaseApplication;
import wangjie.com.library.db.DaoUtils;
import wangjie.com.library.module.AppModule;
import wangjie.com.library.utils.GridDividerItemDecoration;
import wangjie.com.library.utils.StringUtils;
import wangjie.com.library.utils.ToastUtils;

/**
 * Created by Administrator on 2018/3/21.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void ingect(BaseApplication application);

    //全局toast
    ToastUtils getToastUtils();

    GridDividerItemDecoration getGridDividerItemDecoration();

    DaoUtils getDaoUtils();

    StringUtils getStringUtils();

}
