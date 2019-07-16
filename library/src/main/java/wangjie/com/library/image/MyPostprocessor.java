package wangjie.com.library.image;

//import com.facebook.imagepipeline.request.BaseRepeatedPostProcessor;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.facebook.imagepipeline.request.BaseRepeatedPostProcessor;

/**
 * Created by Administrator on 2018/4/12.
 */

public class MyPostprocessor extends BaseRepeatedPostProcessor{

    MyPostprocessor myPostprocessor;

    private int mColor = Color.TRANSPARENT;

    public MyPostprocessor() {
        myPostprocessor = this;
    }

    @Override
    public String getName() {
        return "myPostprocessor";
    }

//    @Override
//    public void process(Bitmap destBitmap, Bitmap sourceBitmap) {
//        Log.i("PPPP", new DebugInfo() + "");
//        for (int x = 0; x < destBitmap.getWidth(); x++) {
//            for (int y = 0; y < destBitmap.getHeight(); y++) {
//                destBitmap.setPixel(destBitmap.getWidth() - x, y, sourceBitmap.getPixel(x, y));
//            }
//        }
//    }

    public void setColor(int color) {
        mColor = color;
        update();
    }

    @Override
    public void process(Bitmap bitmap) {
        for (int x = 0; x < bitmap.getWidth(); x+=2) {
            for (int y = 0; y < bitmap.getHeight(); y+=2) {
                bitmap.setPixel(x, y, mColor);
            }
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        String familyName = "斜体";
        Typeface font = Typeface.create(familyName, Typeface.ITALIC);
        paint.setColor(Color.RED);
        paint.setTypeface(font);
        paint.setTextSize(30);
        paint.setAlpha(80);
        Rect rect = new Rect();
        paint.getTextBounds("万恶的水印", 0, "万恶的水印".length(), rect);
        int textWidth = rect.width();
        canvas.drawText("万恶的水印", width - textWidth - 50, height - 50, paint);

//        int width = bitmap.getWidth();
//        int height = bitmap.getHeight();
//        Canvas canvas = new Canvas(bitmap);
//        Paint paint = new Paint();
//        paint.setAlpha(255);
//        Bitmap bitma = BitmapFactory.decodeResource(MyApplication.getMyApplication().getResources(), R.drawable.admin_down);
//        int markWidth = bitma.getWidth();
//        canvas.drawBitmap(bitma, width - markWidth - 50, 50, paint);
    }


}
