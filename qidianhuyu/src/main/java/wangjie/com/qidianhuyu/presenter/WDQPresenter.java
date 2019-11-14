package wangjie.com.qidianhuyu.presenter;

import android.content.Context;

import wangjie.com.library.base.presenter.BasePresenter;
import wangjie.com.qidianhuyu.ui.WDQActivity;

public class WDQPresenter extends BasePresenter<WDQActivity> {
    private Context context;

    @Override
    public WDQActivity getUI() {
        return (WDQActivity) context;
    }

}
