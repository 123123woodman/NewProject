package wangjie.com.loginfo.component;

import java.util.List;

import dagger.Component;
import wangjie.com.library.component.ActivityComponent;
import wangjie.com.library.component.AppComponent;
import wangjie.com.library.scope.PerActivity;
import wangjie.com.loginfo.adapter.LogInfoRecyclerAdapter;
import wangjie.com.loginfo.module.RecyclerViewModule;
import wangjie.com.loginfo.presenter.LogInfoPresenter;
import wangjie.com.loginfo.ui.LogInfoActivity;


/**
 * Created by Administrator on 2018/4/3.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {RecyclerViewModule.class})
public interface RecyclerViewComponent extends ActivityComponent {

    void ingect(LogInfoActivity logInfoActivity);

    LogInfoRecyclerAdapter getRecyclerViewAdapter();

    List<String> getlist();

    LogInfoPresenter getLogInfoPresenter();
}
