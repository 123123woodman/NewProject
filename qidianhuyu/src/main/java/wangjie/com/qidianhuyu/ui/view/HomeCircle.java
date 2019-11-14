package wangjie.com.qidianhuyu.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import wangjie.com.qidianhuyu.R;

public class HomeCircle extends FrameLayout {

    private ImageView mFirstCircle, mSecondCircle, mThirdCircle;
    private Context context;

    public HomeCircle(Context context) {
        this(context, null);
    }

    public HomeCircle(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public HomeCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        //第一层圆
        mFirstCircle = new ImageView(context);
        LayoutParams layoutParams = new LayoutParams(50, 50);
        layoutParams.gravity = Gravity.CENTER;
        mFirstCircle.setImageResource(R.drawable.yuanhuan1);
        mFirstCircle.setLayoutParams(layoutParams);
        mFirstCircle.setBackgroundColor(getResources().getColor(R.color.white));
        addView(mFirstCircle);

        //第二层圆
        mSecondCircle = new ImageView(context);
        LayoutParams layoutParams1 = new LayoutParams(30, 30);
        layoutParams.gravity = Gravity.CENTER;
        mSecondCircle.setImageResource(R.drawable.yuanhuan2);
        mSecondCircle.setLayoutParams(layoutParams1);
        mSecondCircle.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        addView(mSecondCircle);

        //第三层圆
        mThirdCircle = new ImageView(context);
        LayoutParams layoutParams2 = new LayoutParams(15, 15);
        layoutParams2.gravity = Gravity.CENTER;
        mThirdCircle.setImageResource(R.drawable.yuanhuan3);
        mThirdCircle.setLayoutParams(layoutParams);
        mThirdCircle.setBackgroundColor(getResources().getColor(R.color.lavender));
        addView(mThirdCircle);
    }
}
