package wangjie.com.video;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.TextureView;

/**
 * Created by Administrator on 2018/10/11.
 */

public class MyTextureView extends TextureView{
    private Point mVideoSize;

    public MyTextureView(Context context) {
        super(context);
        init();
    }

    public MyTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyTextureView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        mVideoSize = new Point(0, 0);
    }

    @Override
    public void setRotation(float rotation) {
        if (rotation != getRotation()) {
            super.setRotation(rotation);
        }
        requestLayout();
    }

    public void setVideoSize(Point videoSize) {
        if (videoSize != null && !videoSize.equals(mVideoSize)) {
            mVideoSize =videoSize;
            requestLayout();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int rotation = (int) getRotation();
        int mVideoWidth = mVideoSize.x;
        int mVideoHeight = mVideoSize.y;
        //view反转要交换参数
        if (rotation == 90 || rotation == 270) {
            int tempMeasureSpec = heightMeasureSpec;
            heightMeasureSpec = widthMeasureSpec;
            widthMeasureSpec = tempMeasureSpec;
        }

        //获取默认宽高
        int width = getDefaultSize(mVideoWidth, widthMeasureSpec);
        int height = getDefaultSize(mVideoHeight, heightMeasureSpec);

        if (mVideoWidth > 0 && mVideoHeight > 0) {
            int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
            int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
            int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
            if (widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode == MeasureSpec.EXACTLY) {
                width = widthSpecSize;
                height = heightSpecSize;
                if (mVideoWidth * height > mVideoHeight * width) {
                    height = mVideoHeight * width / mVideoWidth;
                } else {
                    width = mVideoWidth * height / mVideoHeight;
                }
            } else if (widthSpecMode == MeasureSpec.EXACTLY) {
                width = widthSpecSize;
                height = mVideoHeight * width / mVideoWidth;
                if (heightSpecMode == MeasureSpec.AT_MOST && height > heightSpecSize) {
                    height = heightSpecSize;
                    width = mVideoWidth * height / mVideoHeight;
                }
            } else if (heightSpecMode == MeasureSpec.EXACTLY) {
                height = heightSpecSize;
                width = mVideoWidth * height / mVideoHeight;
                if (widthSpecMode == MeasureSpec.AT_MOST && width > widthSpecSize) {
                    width = widthSpecSize;
                    height = mVideoHeight * width / mVideoWidth;
                }
            } else {
                width = widthSpecSize;
                height = heightSpecSize;
                if (width == MeasureSpec.AT_MOST && width > widthSpecSize) {
                    height = mVideoHeight * width / mVideoWidth;
                }
                if (height == MeasureSpec.AT_MOST && height > heightSpecSize) {
                    width = mVideoWidth * height / mVideoHeight;
                }
            }
        } else  {
            //没有尺寸使用默认尺寸
        }
        setMeasuredDimension(width, height);

    }

}
