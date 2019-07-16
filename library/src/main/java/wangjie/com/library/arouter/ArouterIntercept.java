package wangjie.com.library.arouter;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

import wangjie.com.library.sp.SPModule;

@Interceptor(priority = 1, name = "登录拦截器")
public class ArouterIntercept implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.i("WWWW", "----------------->" + postcard.getGroup() + "---"  +  "---" + postcard.getPath() + "----" + SPModule.getAppisLogin(postcard.getPath()));
        if (SPModule.getAppisLogin(postcard.getPath())) {
            callback.onContinue(postcard);
        } else {
            callback.onInterrupt(null);
        }

    }

    @Override
    public void init(Context context) {

    }
}
