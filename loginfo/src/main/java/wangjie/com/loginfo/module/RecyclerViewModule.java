package wangjie.com.loginfo.module;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import wangjie.com.library.scope.PerActivity;
import wangjie.com.loginfo.adapter.LogInfoRecyclerAdapter;
import wangjie.com.loginfo.presenter.LogInfoPresenter;

/**
 * Created by Administrator on 2018/4/3.
 */
@Module
public class RecyclerViewModule {

    private Context context;

    public RecyclerViewModule(Context context) {this.context = context;}

    @Provides @PerActivity
    public LogInfoRecyclerAdapter provideRecyclerViewAdapter() {return new LogInfoRecyclerAdapter();}

    @Provides @PerActivity
    public List<String> provideList() {return  new ArrayList<String>();}

    @Provides @PerActivity
    public LogInfoPresenter provideLogInfo() {return new LogInfoPresenter(context);}


}
