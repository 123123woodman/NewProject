package wangjie.com.library;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import wangjie.com.library.arouter.ArouterConstant;
import wangjie.com.library.base.activity.BaseActivity;
import wangjie.com.library.component.DaggerLoginComponent;
import wangjie.com.library.component.LoginComponent;
import wangjie.com.library.module.LoginModule;
import wangjie.com.library.sp.SPModule;
import wangjie.com.library.utils.StringUtils;
import wangjie.com.library.utils.ToastUtils;

@Route(path = ArouterConstant.ACTIVITY_LOGINACTIVITY)
public class LoginActivity extends BaseActivity {
//curl 55 SSL_write() returned SYSCALL, errno = 10053
    @BindView(R2.id.loginactivity_account)
    EditText account;
    @BindView(R2.id.loginactivity_password)
    EditText passWord;
    @BindView(R2.id.loginactivity_login)
    Button login;

    @Inject
    StringUtils stringUtils;

    @Autowired
    String path;

    LoginComponent loginComponent;

    @Inject
    ToastUtils toastUtils;

    @Override
    public void initParameter() {

        loginComponent = DaggerLoginComponent.builder()
                .appComponent(getAppComponent())
                .loginModule(new LoginModule())
                .build();
        loginComponent.ingect(this);
        ARouter.getInstance().inject(this);

    }

    @OnClick({R2.id.loginactivity_account, R2.id.loginactivity_password, R2.id.loginactivity_login, R2.id.loginactivity_register})
    public void onClick(View view) {
        if (view.getId() == R.id.loginactivity_login) {
            if (!SPModule.getAppisregister()) {
                toastUtils.showCustomToast("请先注册");
                return;
            }
            if (stringUtils.sentencedEmpty(account.getText().toString()) || stringUtils.sentencedEmpty(passWord.getText().toString())){
                toastUtils.showCustomToast("账号或密码不得为空");
                return;
            }
            if (stringUtils.sentencedEmpty(SPModule.getAppaccount()) || stringUtils.sentencedEmpty(SPModule.getKeyApppassword())) {
                toastUtils.showCustomToast("请先注册");
                return;
            }
            if (!account.getText().toString().equals(SPModule.getAppaccount()) || !passWord.getText().toString().equals(SPModule.getKeyApppassword())) {
                toastUtils.showCustomToast("账号或密码不正确");
                return;
            }
            ARouter.getInstance().build(path).navigation();
            SPModule.setAppislogin(true);
        } else if (view.getId() == R.id.loginactivity_register) {
            if (stringUtils.sentencedEmpty(account.getText().toString()) || stringUtils.sentencedEmpty(passWord.getText().toString())) {
                toastUtils.showCustomToast("账号为空或密码不得为空");
                return;
            }
            if (!stringUtils.sentencedEmpty(SPModule.getAppaccount()) && account.getText().toString().equals(SPModule.getAppaccount())) {
                toastUtils.showCustomToast("账号已存在");
                return;
            }
            SPModule.setAppaccount(account.getText().toString());
            SPModule.setApppassword(passWord.getText().toString());
            SPModule.setAppisregister(true);
            SPModule.setAppislogin(true);
            ARouter.getInstance().build(path).navigation();
        }
    }

    @Override
    public int getLayOutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
