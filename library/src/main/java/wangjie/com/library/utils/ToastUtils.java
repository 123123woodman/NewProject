package wangjie.com.library.utils;

import android.app.Application;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import wangjie.com.library.R;
import wangjie.com.library.R2;

/**
 * Created by Administrator on 2018/3/21.
 */
public class ToastUtils {
    private Application mApp;

    public ToastUtils(Application application) {
        this.mApp = application;
    }

    /**
     * 检查上下文是否为kong
     */
    private void checkNull() {
        if (mApp == null) {
            throw new NullPointerException("ToastUtils context is not null，please first init");
        }
    }

    private Toast toast;
    public void showToast(String conent) {
        checkNull();
        if(toast == null) {
            toast = Toast.makeText(mApp, conent, Toast.LENGTH_SHORT);
        } else {
            toast.setText(conent);
        }
        toast.show();
    }

    public void showCustomToast(String text) {
        new Builder()
                .setDuration(1000)
                .setGravity(Gravity.CENTER)
                .setXOffset(0)
                .setYOffset(0)
                .setText(text)
                .setTextColor(mApp.getResources().getColor(R.color.orange))
                .setTextSize(mApp.getResources().getDimension(R.dimen.custom_toast_size))
                .build()
                .show();
    }
    private Toast customToast;
    public class Builder{
        private int duration;
        private float horizontalMargin, verticalMargin;
        private int gravity, xOffset, yOffset;
        private CharSequence text;
        private int viewId;
        private int elevation;
        private int backgroundColor, textColor;
        private float radius;
        private float textSize;

        @BindView(R2.id.custom_toast_text)
        TextView customToastText;
        @BindView(R2.id.custom_toast_image)
        SimpleDraweeView customToastImage;

        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder setHorizontalMargin(float horizontalMargin) {
            this.horizontalMargin = horizontalMargin;
            return this;
        }

        public Builder setVerticalMargin(float verticalMargin) {
            this.verticalMargin = verticalMargin;
            return this;
        }

        public Builder setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setXOffset(int xOffset) {
            this.xOffset = xOffset;
            return this;
        }

        public Builder setYOffset(int yOffset) {
            this.yOffset = yOffset;
            return this;
        }

        public Builder setText(CharSequence text) {
            this.text = text;
            return this;
        }

        public Builder setViewId(int viewId) {
            this.viewId = viewId;
            return this;
        }

        public Builder setElevation(int elevation) {
            this.elevation = elevation;
            return this;
        }

        public Builder setBackgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder setRadius(float radius) {
            this.radius = radius;
            return this;
        }

        public Builder setTextColor(int color) {
            textColor = color;
            return this;
        }

        public Builder setTextSize(float size) {
            textSize = size;
            return this;
        }

        public Builder build() {
            if (customToast == null) {
                customToast = new Toast(mApp);
            }
//            toast.setDuration(duration);

            customToast.setGravity(gravity, xOffset, yOffset);
//            toast.setMargin(horizontalMargin, verticalMargin);
            if (viewId == 0) {
                LinearLayout rootView = (LinearLayout) LayoutInflater.from(mApp).inflate(R.layout.custom_toast, null);
                ButterKnife.bind(Builder.this, rootView);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    //rootView.setElevation(elevation);
//                    rootView.setCardElevation(elevation);
//                }
//                rootView.setCardBackgroundColor(backgroundColor);
//                rootView.setRadius(radius);
                customToastText.setText(text);
                customToastText.setTextColor(textColor);
                customToastText.setTextSize(textSize);
                customToast.setView(rootView);
            } else {
                View view = LayoutInflater.from(mApp).inflate(viewId, null);
                customToast.setView(view);
            }

            return this;
        }

        public void show() {
            if (customToast == null) {
                throw new NullPointerException("请先初始化Toasr");
            }
            customToast.show();
        }
    }


}
