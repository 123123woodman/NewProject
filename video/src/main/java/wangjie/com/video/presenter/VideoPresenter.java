package wangjie.com.video.presenter;

import android.content.Context;
import android.util.Log;

import wangjie.com.library.base.presenter.BasePresenter;
import wangjie.com.video.ui.VideoActivity;

public class VideoPresenter extends BasePresenter<VideoActivity> {

    private Context uiContext;
    public VideoPresenter(Context uiContext) {this.uiContext = uiContext;}

    public void test() {
        Log.i("RRRR", "已经打通" + getUI().voleumAdd.getText().toString());
    }

    @Override
    public VideoActivity getUI() {
        return (VideoActivity) uiContext;
    }
}
