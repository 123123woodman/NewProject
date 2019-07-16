package wangjie.com.library.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import wangjie.com.library.base.base.BaseApplication;
import wangjie.com.library.db.DaoUtils;
import wangjie.com.library.utils.GridDividerItemDecoration;
import wangjie.com.library.utils.StringUtils;
import wangjie.com.library.utils.ToastUtils;

/**
 * Created by Administrator on 2018/3/21.
 */
@Module
public class AppModule {

    BaseApplication mApp;

    public AppModule(BaseApplication application) {
        this.mApp = application;
    }

    @Provides @Singleton
    public ToastUtils provideToastUtils() {
        return new ToastUtils(mApp);
    }

    @Provides @Singleton
    public GridDividerItemDecoration provideGridDividerItemDecoration(){return new GridDividerItemDecoration();}

    @Provides @Singleton
    public DaoUtils provideDaoUtils() {return new DaoUtils();}

    @Provides@Singleton
    public StringUtils provideStringUtils() {return new StringUtils();}

}
