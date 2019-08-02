package wangjie.com.newproject.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import wangjie.com.library.api.ConstantImageApi;
import wangjie.com.library.apt.ContextView;
import wangjie.com.library.arouter.ArouterConstant;
import wangjie.com.library.arouter.LoginNavigationCallbackImpl;
import wangjie.com.library.base.activity.BaseActivity;
import wangjie.com.library.image.ImageFactory;
import wangjie.com.library.net.ExceptionUtils;
import wangjie.com.library.net.RestApiClient;
import wangjie.com.library.sp.SPModule;
import wangjie.com.library.utils.GridDividerItemDecoration;
import wangjie.com.library.utils.ToastUtils;
import wangjie.com.newproject.R;
import wangjie.com.newproject.application.NativeMethod;
import wangjie.com.newproject.component.DaggerMainComponent;
import wangjie.com.newproject.component.MainComponent;
import wangjie.com.newproject.data.HttpsData;
import wangjie.com.newproject.data.YiZhaoYun;
import wangjie.com.newproject.module.MainModule;
import wangjie.com.newproject.network.Info;
import wangjie.com.newproject.network.URLService;
import wangjie.com.newproject.presnter.MainPresenter;
import wangjie.com.newproject.test.ReadBmp;
import wangjie.com.newproject.test.TcpTest;

@ContextView(R.layout.activity_main)
@Route(path = ArouterConstant.ACTIVITY_MAINACTIVITY)
public class MainActivity extends BaseActivity {

    private String TAG = "KKKK";
    private Subscription mSubscription;

    @BindView(R.id.main_button)
    Button mainButton;

    @BindView(R.id.main_gc)
    Button mainGc;

    @BindView(R.id.main_recycler)
    Button mainRecycler;

    @BindView(R.id.main_fresco)
    SimpleDraweeView simpleDraweeView;

    @BindView(R.id.main_clean_fresco)
    Button mainCleanFrescoButton;

    @BindView(R.id.main_change_color)
    Button mainChangeColor;

    @BindView(R.id.main_ishavebitmap)
    Button mainIsHaveBitmap;

    @BindView(R.id.main_text)
    EditText mainText;

    @BindView(R.id.main_binder)
    Button mainBinder;

    @BindView(R.id.readBmp)
    Button mainReadBmp;

    @BindView(R.id.sendIris)
    Button mainSendIris;

    @BindView(R.id.videoPlayer)
    Button mainVideo;

    @BindView(R.id.main_flowable)
    Button mainFlowable;

    @BindView(R.id.main_https)
    Button mainHttps;

    @BindView(R.id.main_cancel_account)
    Button mainCancelAccount;

    @BindView(R.id.main_exit_account)
    Button mainExitAccount;

    @Inject
    MainPresenter mainPresenter;

    @Inject
    MainComponent mainComponent;

    @Inject
    GridDividerItemDecoration gridDividerItemDecoration;

    @Inject
    NativeMethod nativeMethod;

    @Inject
    ToastUtils toastUtils;

//    PointF point = new PointF(0.5f, 0.5f);

    @Override
    public void initParameter() {

        //mainText.setBackgroundResource(R.drawable.edit_style);

        mainComponent = DaggerMainComponent.builder()
                            .appComponent(getAppComponent())
                            .mainModule(new MainModule())
                            .build();

        mainComponent.ingect(this);

        mainButton.setText(nativeMethod.fuck());

        ImageFactory.getInstance().getFrescoLoader().clearFrescoCatch();
        ImageFactory.getInstance().getFrescoLoader().showImage(simpleDraweeView, ConstantImageApi.MONKEY_D_LUFFY_001, null);

        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Throwable e) {
            Log.e("TAG", "loadLibraries error", e);
        }

        startFlowable();
    }

    @Override
    public int getLayOutId() {
        for (Class c = getClass(); c != Context.class; c = c.getSuperclass()) {
            ContextView contextView = (ContextView) c.getAnnotation(ContextView.class);
            if (contextView != null) {
                return contextView.value();
            }
        }
        return 0;
    }

    @OnClick({R.id.main_button, R.id.main_gc, R.id.main_recycler, R.id.main_clean_fresco,
            R.id.main_change_color, R.id.main_ishavebitmap, R.id.main_text, R.id.main_binder, R.id.readBmp, R.id.sendIris,
            R.id.videoPlayer, R.id.main_flowable, R.id.main_https, R.id.main_cancel_account, R.id.main_exit_account})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_button:
                PreViewActivity.lanuch(this);
                ARouter.getInstance()
                        .build(ArouterConstant.ACTIVITY_PREVIEWACTIVITY)
                        .navigation();
                break;
            case R.id.main_gc:
                //photoString();
                System.gc();
                break;
            case R.id.main_recycler:
                ARouter.getInstance()
                        .build(ArouterConstant.ACTIVITY_LOGINFOACTIVITY)
                        .navigation(this, new LoginNavigationCallbackImpl());
                break;
            case R.id.main_clean_fresco:
//                ImagePipeline imagePipeline = Fresco.getImagePipeline();
//                imagePipeline.evictFromCache(Uri.parse("http://image.baidu.com/search/down?tn=download&ipn=dwnl&word=download&ie=utf8&fr=result&url=http%3A%2F%2Fpic.58pic.com%2F01%2F35%2F65%2F51bOOOPICb0.jpg&thumburl=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D1629028640%2C4159944060%26fm%3D27%26gp%3D0.jpg"));
//                imagePipeline.evictFromDiskCache(Uri.parse("http://image.baidu.com/search/down?tn=download&ipn=dwnl&word=download&ie=utf8&fr=result&url=http%3A%2F%2Fpic.58pic.com%2F01%2F35%2F65%2F51bOOOPICb0.jpg&thumburl=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D1629028640%2C4159944060%26fm%3D27%26gp%3D0.jpg"));
//                imagePipeline.evictFromMemoryCache(Uri.parse("http://image.baidu.com/search/down?tn=download&ipn=dwnl&word=download&ie=utf8&fr=result&url=http%3A%2F%2Fpic.58pic.com%2F01%2F35%2F65%2F51bOOOPICb0.jpg&thumburl=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D1629028640%2C4159944060%26fm%3D27%26gp%3D0.jpg"));
//                imagePipeline.clearMemoryCaches();
//                imagePipeline.clearDiskCaches();
//                imagePipeline.clearCaches();

//                try {
//                    //权限设置
//                    Process p = Runtime.getRuntime().exec("su");
//                    //获取输出流
//                    OutputStream outputStream = p.getOutputStream();
//                    DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
//                    String cmd = "kill-server\n" + "setprop service.adb.tcp.port 5555\n" +
//                                        "stop adbd\n" + "start adbd\n";
//                    //将命令写入
//                    dataOutputStream.writeBytes(cmd);
//                    //提交命令
//                    dataOutputStream.flush();
//                    //关闭流操作
//                    dataOutputStream.close();
//                    outputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                break;
            case R.id.main_change_color:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new TcpTest().getEnrollInfo("1003", "0");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
//                boolean isChange = android.os.SystemClock.setCurrentTimeMillis(ChangeTimeUtils.stringToLong("2018-04-19 16:20:50", "yyyy-MM-dd HH:mm:ss"));
                //myPostprocessor.setColor(color[Math.abs(new Random().nextInt())%8]);
                break;
            case R.id.main_ishavebitmap:
//                ImagePipeline imagePipeline1 = Fresco.getImagePipeline();

//                try {
//                    MessageDigest digest = MessageDigest.getInstance("MD5");
//                    byte[] bytes = digest.digest("你好".getBytes());
//                    String result = "";
//                    for (byte b : bytes) {
//                        String temp = Integer.toHexString(b & 0xff);
//                        if (temp.length() == 1) {
//                            temp = "0" + temp;
//                        }
//                        result += temp;
//                    }
//                    Log.i("QQQQ", new DebugInfo() + result);
//                } catch (NoSuchAlgorithmException e) {
//                    e.printStackTrace();
//                }
                break;
            case R.id.main_binder:
                ARouter.getInstance()
                        .build(ArouterConstant.ACTIVITY_CLIENTACTIVITY)
                        .navigation();
                break;
            case R.id.readBmp:
                ReadBmp.convert8bit();
                //ReadBmp.readBmp();
                break;
            case R.id.sendIris:
                Info info= new Info(photoString());
                Gson gson=new Gson();
                String obj=gson.toJson(info);
                RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),obj);
                new RestApiClient(getApplicationContext()).get(URLService.class).getMsgResponse(body)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<YiZhaoYun>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(YiZhaoYun value) {
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                ExceptionUtils.handleHttpException(e);
                            }

                            @Override
                            public void onComplete() {
                                Log.i("CCCC" ,"onComplete");
                            }
                        });

                break;
            case R.id.videoPlayer:
                ARouter.getInstance()
                        .build(ArouterConstant.ACTIVITY_VIDEOACTIVITY)
                        .greenChannel()
                        .navigation();
                break;
            case R.id.main_flowable:
                mSubscription.request(48);
                break;
            case R.id.main_https:
                new RestApiClient(getApplicationContext()).get(URLService.class).getHttpsinfo()

//                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
//
//                        return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
//                            @Override
//                            public ObservableSource<?> apply(Object o) throws Exception {
//                                return Observable.just(1).delay(1000, TimeUnit.MILLISECONDS);
//                            }
//                        });
//                    }
//                })

                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<HttpsData>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(HttpsData value) {
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                ExceptionUtils.handleHttpException(e);
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
                break;
            case R.id.main_cancel_account:
                SPModule.setCancelAccount();
                break;
            case R.id.main_exit_account:
                SPModule.setAppislogin(false);
                break;
        }
    }

    //Flowable异步背压
    private void startFlowable() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) {

                Log.d(TAG, "观察者可接收事件数量 = " + emitter.requested());
                boolean flag; //设置标记位控制

                // 被观察者一共需要发送500个事件
                for (int i = 0; i < 500; i++) {
                    flag = false;

                    // 若requested() == 0则不发送
                    while (emitter.requested() == 0) {
                        if (!flag) {
                            Log.d(TAG, "不再发送");
                            flag = true;
                        }
                    }
                    // requested() ≠ 0 才发送
                    Log.d(TAG, "发送了事件" + i + "，观察者可接收事件数量 = " + emitter.requested());
                    emitter.onNext(i);


                }
            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io()) // 设置被观察者在io线程中进行
                .observeOn(AndroidSchedulers.mainThread()) // 设置观察者在主线程中进行
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d(TAG, "onSubscribe");
                        mSubscription = s;
                        // 初始状态 = 不接收事件；通过点击按钮接收事件
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "接收到了事件" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.w(TAG, "onError: ", t);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });

    }

    //获取sd卡跟目录并拼接路径
    public String getSDFile() {
        String savePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            savePath = Environment.getExternalStorageDirectory().toString() + File.separator + "123.jpg";
        }
        return savePath;
    }

        public String photoString() {

                BitmapFactory.Options opts=new BitmapFactory.Options();


                int height = opts.outHeight;
                int width = opts.outWidth;
                System.out.println("高："+height);
                System.out.println("宽："+width);
                int inSampleSize=1;
                int reqHeight=800;
                int reqWidth=480;
                if(height>reqHeight || width>reqWidth){
                    final  int heightRatio = Math.round((float) height / (float) reqHeight);
                    final int widthRatio = Math.round((float) width / (float) reqWidth);
                    inSampleSize=heightRatio<widthRatio ? heightRatio:widthRatio;
                }
                opts.inSampleSize=inSampleSize;//采样率：宽高缩小四倍，大小就缩小16倍。
                System.out.println("inSampleSize(采样率)："+inSampleSize);

                Bitmap bitmap = BitmapFactory.decodeFile(getSDFile(), opts);//2M 1997568
                System.out.println("大小："+bitmap.getRowBytes() * bitmap.getHeight());
                //调用完全档接口测试图片上传。
                //将Bitmap转换成字符串
                String photoString;
                ByteArrayOutputStream bStream=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,bStream);

                byte[]bytes=bStream.toByteArray();
                photoString= Base64.encodeToString(bytes,Base64.DEFAULT);
                return photoString;


        }



    @Override
    protected void onStop() {
        super.onStop();
    }

    static {
        System.loadLibrary("native-lib");
    }



    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
