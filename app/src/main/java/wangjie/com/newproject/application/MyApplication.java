package wangjie.com.newproject.application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import wangjie.com.library.base.base.BaseApplication;

//import com.facebook.drawee.backends.pipeline.Fresco;
//import com.facebook.imagepipeline.cache.CountingMemoryCache;
//import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;
//import com.facebook.imagepipeline.core.ImagePipelineConfig;

//Error:(6, 0) Gradle DSL method not found: 'url()'
//        Possible causes:<ul><li>The project 'NewProject' may be using a version of the Android Gradle plug-in that does not contain the method (e.g. 'testCompile' was added in 1.1.0).
//        <a href="fixGradleElements">Upgrade plugin to version 2.3.2 and sync project</a></li><li>The project 'NewProject' may be using a version of Gradle that does not contain the method.
//        <a href="open.wrapper.file">Open Gradle wrapper file</a></li><li>The build file may be missing a Gradle plugin.
//        <a href="apply.gradle.plugin">Apply Gradle plugin</a></li>

/**
 * Created by Administrator on 2018/3/21.
 */
public class MyApplication extends BaseApplication {

    private static MyApplication myApplication;

    public static MyApplication getMyApplication() {
        return myApplication;
    }

    public static byte[] imageByte = new byte[5*1024*1024];

    public RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        //腾讯Bugly
        //CrashReport.initCrashReport(getApplicationContext(), "注册时申请的APPID", false);
        //启动Leakcanary
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        refWatcher = LeakCanary.install(this);


        myApplication = this;


    }

}
