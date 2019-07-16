package wangjie.com.library.base.presenter;

import wangjie.com.library.base.activity.BaseActivity;

public abstract class BasePresenter<T extends BaseActivity> {

     public abstract T getUI();

}
