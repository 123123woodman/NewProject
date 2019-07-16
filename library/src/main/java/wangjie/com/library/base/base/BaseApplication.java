package wangjie.com.library.base.base;

import android.app.Application;

import javax.inject.Inject;

import wangjie.com.library.component.ActivityComponent;
import wangjie.com.library.component.AppComponent;
import wangjie.com.library.dao.DaoMaster;
import wangjie.com.library.dao.DaoSession;
import wangjie.com.library.utils.ToastUtils;

public class BaseApplication extends Application {

    public static DaoMaster daoMaster;
    public static DaoSession daoSession;

    private static BaseApplication baseApplication;

    @Inject
    ToastUtils toastUtils;

    @Override
    public void onCreate() {
        super.onCreate();

        baseApplication = this;

        AppConfig.INSTANCE.initConfig(this);
        
    }

    public static BaseApplication getBaseApplication() {
        return baseApplication;
    }

    private static DaoMaster getDaoMaster() {

        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(getBaseApplication().getBaseContext(), "irisLock", null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }

        return daoMaster;

    }

    public AppComponent getAppComponent() {
        return AppConfig.INSTANCE.getAppComponent();
    }

    public ActivityComponent getActivityComponent() {
        return AppConfig.INSTANCE.getActivityComponent();
    }

    public ToastUtils getToastUtils() {
        return toastUtils;
    }

    public void releaseComponent() {
        AppConfig.INSTANCE.releaseAppComponent();
    }

    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

}
