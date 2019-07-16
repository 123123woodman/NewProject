package wangjie.com.library.loadview;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import wangjie.com.library.R;
import wangjie.com.library.base.base.BaseApplication;

/**
 * Created by Administrator on 2018/4/12.
 */

public class CustomProgressBar extends Drawable{

    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mLevel = 0;
    private int mBackground = BaseApplication.getBaseApplication().getBaseContext().getResources().getColor(R.color.green);
    private int mTopColor = BaseApplication.getBaseApplication().getResources().getColor(R.color.colorAccent);
    private int mPadding = 0;
    private int mPragressBarHeight = 10;
    private int mBarWidth = 8;
    private boolean mHideWhenFinish = false;

    public void setBackgroundColor(int color) {
        mBackground = color;
    }

    public void setTopColor(int color) {
        mTopColor = color;
    }

    public void setPadding(int padding) {
        mPadding = padding;
    }



    @Override
    protected boolean onLevelChange(int level) {
        mLevel = level;
        invalidateSelf();
        return true;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (mHideWhenFinish && mLevel == 0) {
            return;
        }
        drawBar(canvas, 10000, mBackground);
        drawBar(canvas, mLevel, mTopColor);
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    private void drawBar(Canvas canvas, int level, int color) {
        Rect bounds = getBounds();
        int length = (bounds.width() - 2 * mPadding) * level / 10000;
        int xpos = bounds.left + mPadding;
        int ypos = bounds.bottom - mPadding - mBarWidth;
        mPaint.setColor(color);
        canvas.drawRect(xpos, ypos, xpos + length, ypos + mBarWidth, mPaint);
    }


}
