package wangjie.com.library.base.base;

import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import wangjie.com.library.component.ActivityComponent;
import wangjie.com.library.component.AppComponent;
import wangjie.com.library.component.DaggerActivityComponent;
import wangjie.com.library.component.DaggerAppComponent;
import wangjie.com.library.module.ActivityModule;
import wangjie.com.library.module.AppModule;
import wangjie.com.library.utils.DebugInfo;

public enum AppConfig {

    INSTANCE;

    private BaseApplication application;
    private AppComponent appComponent;
    private ActivityComponent activityComponent;

    public void initConfig(BaseApplication application) {
        this.application = application;

        initArouter();
        initFresco();
        initComponent();
    }

    /**
     * 初始化dagger2 baseComponent
     */
    private void initComponent() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(application)).build();
        activityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule()).build();
        appComponent.ingect(application);
    }

    /**
     * 初始化Arouter
     */
    private void initArouter() {
//        if ("isDebug") {
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
        ARouter.init(application);
    }

    /**
     * 初始化Fresco
     * @return
     */
    public void initFresco() {
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(application)
                .setImageCacheStatsTracker(imageCacheStatsTracker)
                .setDecodeMemoryFileEnabled(true)
                .setDownsampleEnabled(false)
                .build();

        Fresco.initialize(application, config);
    }

    /**
     * Fresco
     */
    ImageCacheStatsTracker imageCacheStatsTracker = new ImageCacheStatsTracker() {
        @Override
        public void onBitmapCachePut() {
            Log.i("VVVV", new DebugInfo() + "");
        }

        @Override
        public void onBitmapCacheHit() {
            Log.i("VVVV", new DebugInfo() + "");
        }

        @Override
        public void onBitmapCacheMiss() {
            Log.i("VVVV", new DebugInfo() + "");
        }

        @Override
        public void onMemoryCachePut() {
            Log.i("VVVV", new DebugInfo() + "");
        }

        @Override
        public void onMemoryCacheHit() {
            Log.i("VVVV", new DebugInfo() + "");
        }

        @Override
        public void onMemoryCacheMiss() {
            Log.i("VVVV", new DebugInfo() + "");
        }

        @Override
        public void onStagingAreaHit() {
            Log.i("VVVV", new DebugInfo() + "");
        }

        @Override
        public void onStagingAreaMiss() {
            Log.i("VVVV", new DebugInfo() + "");
        }

        @Override
        public void onDiskCacheHit() {
            Log.i("VVVV", new DebugInfo() + "");
        }

        @Override
        public void onDiskCacheMiss() {
            Log.i("VVVV", new DebugInfo() + "");
        }

        @Override
        public void onDiskCacheGetFail() {
            Log.i("VVVV", new DebugInfo() + "");
        }

        @Override
        public void registerBitmapMemoryCache(CountingMemoryCache<?, ?> bitmapMemoryCache) {
            Log.i("VVVV", new DebugInfo() + "");
        }

        @Override
        public void registerEncodedMemoryCache(CountingMemoryCache<?, ?> encodedMemoryCache) {
            Log.i("VVVV", new DebugInfo() + "");
        }
    };


    public AppComponent getAppComponent() {
        return appComponent;
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    public void releaseAppComponent() {
        appComponent = null;
    }

}
