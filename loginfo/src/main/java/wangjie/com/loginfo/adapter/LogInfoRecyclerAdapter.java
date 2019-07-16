package wangjie.com.loginfo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import wangjie.com.library.adapter.ListModel;
import wangjie.com.library.adapter.PagerAdapter;
import wangjie.com.library.bean.LogInfoBean;
import wangjie.com.library.utils.ItemSlideHelper;
import wangjie.com.loginfo.R;
import wangjie.com.loginfo.app.LogInfoApplication;
import wangjie.com.loginfo.holder.LogInfoHolder;

/**
 * Created by Administrator on 2018/4/3.
 */

public class LogInfoRecyclerAdapter extends PagerAdapter<LogInfoBean, ListModel<LogInfoBean>, LogInfoHolder> implements ItemSlideHelper.Callback{


    public LogInfoRecyclerAdapter() {}

    @Override
    public LogInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) LogInfoApplication.getBaseApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(getLayoutId(), null);

        LogInfoHolder logInfoHolder = new LogInfoHolder(view);
        ButterKnife.bind(logInfoHolder, view);
        return logInfoHolder;
    }

    @Override
    public ListModel<LogInfoBean> initModel() {
        return new ListModel<>();
    }

    @Override
    public void onBindViewHolder(LogInfoHolder holder, int position) {
        LogInfoBean logInfoBean = getCurrentItem(position);

        holder.bindHodle(logInfoBean);
    }

    @Override
    public LogInfoHolder newHolder(Context context) {
        return null;
    }

    @Override
    public int getHorizontalRange(RecyclerView.ViewHolder holder) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder getChildViewHolder(View childView) {
        return null;
    }

    @Override
    public View findTargetView(float x, float y) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getLayoutId() {
        return R.layout.loginfo_recycler_item;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }
}
