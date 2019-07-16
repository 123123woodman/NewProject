package wangjie.com.newproject.test;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.OnClick;
import wangjie.com.library.apt.ContextView;
import wangjie.com.library.arouter.ArouterConstant;
import wangjie.com.library.base.activity.BaseActivity;
import wangjie.com.library.utils.DebugInfo;
import wangjie.com.newproject.R;

/**
 * Created by Administrator on 2018/5/7.
 */

@ContextView(R.layout.activity_client)
@Route(path = ArouterConstant.ACTIVITY_CLIENTACTIVITY)
public class ClientActivity extends BaseActivity {

    Integer[] integers = new Integer[] {12, 32, 2, 23, 45, 9, 2, 23, 1, 89, 22, 1, 12, 2};

    private String TAG;

    @BindView(R.id.connectService)
    Button connectService;

    public static void lanuch(BaseActivity activity) {
        Intent intent = new Intent(activity, ClientActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void initParameter() {
        TAG = getClass().getName();
        int temp;
        int t = 0;
        for (int i = 0; i < integers.length; i++) {
            for (int j = i + 1; j < integers.length; j++) {
                if (integers[i] > integers[j]) {
                    temp = integers[i];
                    integers[i] = integers[j];
                    integers[j] = temp;
                }
                t += 1;
            }
        }
        StringBuilder sb = new StringBuilder(integers.length);
        for (Integer integer : integers) {
            sb.append(" ");
            sb.append(integer);
        }
        Log.i(TAG, new DebugInfo() + sb.toString() + "-->" + t);
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

    @OnClick(R.id.connectService)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.connectService:
                initParameter();
                break;
        }
    }



}
