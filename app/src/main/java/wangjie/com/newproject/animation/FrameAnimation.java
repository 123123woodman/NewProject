package wangjie.com.newproject.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.ByteArrayOutputStream;

import wangjie.com.library.api.ConstantImageApi;
import wangjie.com.newproject.R;
import wangjie.com.newproject.application.MyApplication;
import wangjie.com.library.utils.ThreadSleepUtils;

/**
 * Created by Administrator on 2018/3/28.
 */

public class FrameAnimation extends SurfaceView implements SurfaceHolder.Callback, Runnable{

    private SurfaceHolder mSurfaceHolder;

    private boolean mIsThreadRunning = true; // 线程运行开关
    public static boolean mIsDestroy = false;// 是否已经销毁

    private Canvas mCanvas;
    private Bitmap mBitmap;// 显示的图片

    private boolean isRegisterSuccess, isVerificationSuccess, isDiscernSuccess;

    private int mGapTime = 10;// 每帧动画持续存在的时间

    private OnFrameFinishedListener mOnFrameFinishedListener;// 动画监听事件

    private byte[] imageBytes;

    private static byte[] finallyBytes;

    private int totalCount = ConstantImageApi.srcFrameAnimationId.length;//资源总数

    private int mCurrentIndext;// 当前动画播放的位置

    public FrameAnimation(Context context) {
        this(context, null);
        initView();
    }

    public FrameAnimation(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public FrameAnimation(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initView();

    }

    private void initView() {

        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(this);

        // 白色背景
        setZOrderOnTop(true);
        setZOrderMediaOverlay(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsThreadRunning = false;
        ThreadSleepUtils.threadSleep(mGapTime);
        mIsDestroy = true;
    }

    /**
     * 制图方法
     */
    private void drawView() {
        // 无资源文件退出
        if (isShoutDown()) {
            Log.e("frameview", "the bitmapsrcIDs is null");

            mIsThreadRunning = false;

            return;
        }

        // 锁定画布
        if(mSurfaceHolder != null){
            mCanvas = mSurfaceHolder.lockCanvas();
        }
        try {
            if (mSurfaceHolder != null && mCanvas != null) {

                mCanvas.drawColor(Color.WHITE);
                mBitmap = decodeSampledBitmapFromdrive(finallyBytes, 100, 100);
                finallyBytes = null;
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.STROKE);
                Rect mSrcRect, mDestRect;
                mSrcRect = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
                mDestRect = new Rect(0, 0, getWidth(), getHeight());
                mCanvas.drawColor(getResources().getColor(R.color.colorPrimaryDark));
                mCanvas.drawBitmap(mBitmap, mSrcRect, mDestRect, paint);

                // 播放到最后一张图片
                if (mCurrentIndext == totalCount - 1) {
                    //TODO 设置重复播放
                    //播放到最后一张，当前index置零
                    mCurrentIndext = 0;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            mCurrentIndext++;

            if(mCurrentIndext >= totalCount){
                mCurrentIndext = 0;
            }
            if (mCanvas != null) {
                // 将画布解锁并显示在屏幕上
                if(mSurfaceHolder!=null){
                    mSurfaceHolder.unlockCanvasAndPost(mCanvas);
                }
            }

            if (mBitmap != null) {
                // 收回图片
                mBitmap.recycle();
            }

        }
    }

    @Override
    public void run() {
        if (mOnFrameFinishedListener != null) {
            mOnFrameFinishedListener.onStart();
        }

        // 每隔150ms刷新屏幕
        while (mIsThreadRunning) {
            getImageBytes(ConstantImageApi.srcFrameAnimationId[mCurrentIndext]);
            drawView();
            ThreadSleepUtils.threadSleep(mGapTime);
        }

        if (mOnFrameFinishedListener != null) {
            mOnFrameFinishedListener.onStop();
        }
    }

    /**
     * 开始动画
     */
    public void start() {
        if (!mIsDestroy) {
            mCurrentIndext = 0;
            mIsThreadRunning = true;
            new Thread(this).start();
        } else {
            // 如果SurfaceHolder已经销毁抛出该异常
            try {
                throw new Exception("IllegalArgumentException:Are you sure the SurfaceHolder is not destroyed");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置每帧时间
     */
    public void setGapTime(int gapTime) {
        this.mGapTime = gapTime;
    }

    /**
     * 结束动画
     */
    public void stop() {
        mIsThreadRunning = false;
    }

    /**
     * 继续动画
     */
    public void reStart() {
        mIsThreadRunning = false;
    }

    /**
     * 设置动画监听器
     */
    public void setOnFrameFinisedListener(OnFrameFinishedListener onFrameFinishedListener) {

        this.mOnFrameFinishedListener = onFrameFinishedListener;

    }

    /**
     * 动画监听器
     *
     * @author qike
     */
    public interface OnFrameFinishedListener {

        /**
         * 动画开始
         */
        void onStart();

        /**
         * 动画结束
         */
        void onStop();
    }

    /**
     * 当用户点击返回按钮时，停止线程，反转内存溢出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 当按返回键时，将线程停止，避免surfaceView销毁了,而线程还在运行而报错
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mIsThreadRunning = false;
        }

        return super.onKeyDown(keyCode, event);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromdrive(byte[] bytes, int width, int height) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        options.inSampleSize = calculateInSampleSize(options, width, height);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
    }

    /**
     * 提取图片
     */
    public void getImageBytes(int id) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), id, options);
        options.inSampleSize = calculateInSampleSize(options, 100, 100);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id, options);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
        byte[] initialBytes = bos.toByteArray();
        //imageBytes = new byte[initialBytes.length];
        System.arraycopy(initialBytes, 0, MyApplication.imageByte, 0, initialBytes.length);
        finallyBytes = new byte[initialBytes.length];
        System.arraycopy(MyApplication.imageByte, 0, finallyBytes, 0, finallyBytes.length);
        //imageBytes = null;
    }

    /**
     * 是否完成预览
     */
    private boolean isShoutDown() {
        return (isDiscernSuccess || isRegisterSuccess || isVerificationSuccess);
    }

}
