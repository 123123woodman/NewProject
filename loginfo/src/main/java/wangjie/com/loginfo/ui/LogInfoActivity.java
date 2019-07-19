package wangjie.com.loginfo.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import wangjie.com.library.arouter.ArouterConstant;
import wangjie.com.library.base.activity.BaseActivity;
import wangjie.com.library.db.DaoUtils;
import wangjie.com.library.utils.GridDividerItemDecoration;
import wangjie.com.loginfo.R;
import wangjie.com.loginfo.R2;
import wangjie.com.loginfo.adapter.LogInfoRecyclerAdapter;
import wangjie.com.loginfo.component.DaggerRecyclerViewComponent;
import wangjie.com.loginfo.component.RecyclerViewComponent;
import wangjie.com.loginfo.module.RecyclerViewModule;
import wangjie.com.loginfo.presenter.LogInfoPresenter;

/**
 *2019年7月19号10:53分再次提交到dev分支并提交到远程仓
 */

@Route(path = ArouterConstant.ACTIVITY_LOGINFOACTIVITY)
public class LogInfoActivity extends BaseActivity {

    @BindView(R2.id.activity_loginfo_recycler)
    public RecyclerView recyclerView;

    @BindView(R2.id.recycler_button)
    public Button button;

    @BindView(R2.id.recycler_button2)
    public Button button2;

    @BindView(R2.id.recycler_clean)
    public Button buttonClean;

    @Inject
    LogInfoPresenter logInfoPresenter;

    @Inject
    public LogInfoRecyclerAdapter logInfoRecyclerAdapter;

    @Inject
    List<String> list;

    RecyclerViewComponent recyclerViewComponent;

    @Inject
    GridDividerItemDecoration gridDividerItemDecoration;

    @Inject
    public DaoUtils logInfoDaoUtils;

    GridLayoutManager gridLayoutManager;

    private int i = 0;


    @Override
    public void initParameter() {
        recyclerViewComponent = DaggerRecyclerViewComponent.builder()
                .appComponent(getAppComponent())
                .recyclerViewModule(new RecyclerViewModule(this))
                .build();

        recyclerViewComponent.ingect(this);

        list.add("wq");

        gridLayoutManager = new GridLayoutManager(this, 2);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                if(logInfoRecyclerAdapter.getCurrentItem(position).getLogInfo().equals("虹娘科技"))
                    return 2;
                return 1;

            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridDividerItemDecoration.setParameter(1, getResources().getColor(R.color.balck));
        recyclerView.addItemDecoration(gridDividerItemDecoration);
        recyclerView.setAdapter(logInfoRecyclerAdapter);
        logInfoPresenter.refreshUi();

    }

    @Override
    public int getLayOutId() {
        return R.layout.activity_loginfo;
    }

    @OnClick({R2.id.recycler_button, R2.id.recycler_button2, R2.id.recycler_clean})
    public void onClick(View view) {
        int i1 = view.getId();
        if (i1 == R.id.recycler_button) {
            logInfoPresenter.addIcon();
//                i++;
//                if(i > 3)
//                    gridLayoutManager.setSpanCount(5);
        } else if (i1 == R.id.recycler_clean) {
            logInfoPresenter.cleanAll();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerViewComponent = null;
    }
}
