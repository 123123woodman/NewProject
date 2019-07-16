package wangjie.com.library.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import wangjie.com.library.base.base.BaseApplication;
import wangjie.com.library.component.ActivityComponent;
import wangjie.com.library.component.AppComponent;


/**
 * Created by Administrator on 2018/3/21.
 */
public abstract class BaseActivity extends AppCompatActivity{

    private Unbinder unbinder;

    public AppComponent getAppComponent(){
        return ((BaseApplication)getApplication()).getAppComponent();
    }

    public ActivityComponent getActivityComponent() {
        return ((BaseApplication)getApplication()).getActivityComponent();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayOutId());
        unbinder = ButterKnife.bind(this);

        initParameter();

    }

    public abstract void initParameter();

    public abstract int getLayOutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
