package wangjie.com.qidianhuyu.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wangjie.com.library.base.presenter.BaseFragmentPresenter;
import wangjie.com.library.net.ExceptionUtils;
import wangjie.com.library.net.RestApiClient;
import wangjie.com.qidianhuyu.entity.response.BaseResponse;
import wangjie.com.qidianhuyu.entity.wx.WXChartTitle;
import wangjie.com.qidianhuyu.fragment.MainUpLoadFragment;
import wangjie.com.qidianhuyu.url.URLService;

public class MainUpLoadPresenter extends BaseFragmentPresenter<MainUpLoadFragment> {

    private MainUpLoadFragment mainUpLoadFragment;

    public MainUpLoadPresenter(MainUpLoadFragment fragment) {
        this.mainUpLoadFragment = fragment;
    }

    public void refreshUI() {
        getList();
    }
    //private List<WxChartBaseBean> list = new ArrayList<WxChartBaseBean>();
    private List<WXChartTitle> wxChartTitles;
    @SuppressLint("NewApi")
    private void getList() {
        new RestApiClient(Objects.requireNonNull(getUI().getActivity()).getApplicationContext()).get(URLService.class).getWXChartTitle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<WXChartTitle>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<WXChartTitle> value) {
                        wxChartTitles = value.getData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        ExceptionUtils.handleHttpException(e);
                    }

                    @Override
                    public void onComplete() {
                        getUI().refresh(wxChartTitles);
                    }
                });
    }

    @Override
    public MainUpLoadFragment getUI() {
        return mainUpLoadFragment;
    }

}
