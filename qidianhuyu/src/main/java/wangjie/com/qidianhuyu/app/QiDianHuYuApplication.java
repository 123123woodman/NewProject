package wangjie.com.qidianhuyu.app;

import android.support.annotation.NonNull;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;
import wangjie.com.library.base.base.BaseApplication;

public class QiDianHuYuApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(true)
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(@NonNull Exception e) {

                    }
                }).install();
    }
}
