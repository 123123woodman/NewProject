package wangjie.com.video;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2018/10/11.
 */

public class MyMediaPlayerStandard extends MyMediaPalyer{

    public MyMediaPlayerStandard(@NonNull Context context) {
        super(context);
    }

    public MyMediaPlayerStandard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.mystandard_view;
    }

}
