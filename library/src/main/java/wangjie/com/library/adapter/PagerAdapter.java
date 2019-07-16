package wangjie.com.library.adapter;

import android.util.Log;

import java.io.Serializable;
import java.util.List;

import wangjie.com.library.utils.DebugInfo;


public abstract class PagerAdapter<T extends Serializable, Model extends IPageModel<T>, Holder extends AHolder> extends AAdapter<Holder> {
    protected Model mPagerAdapterModel;

    public PagerAdapter() {
        mPagerAdapterModel = initModel();
        Log.i("WWWW", new DebugInfo() + "" +mPagerAdapterModel.hashCode() + "");
    }

    public void updateList(List<T> list) {
        mPagerAdapterModel.setList(list);
        try {
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mergeList(List<T> list) {
        mPagerAdapterModel.addList(list);
        try {
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeItem(int position) {
        List<T> list = mPagerAdapterModel.getList();
        list.remove(position);
//        mPagerAdapterModel.setList(list);
        try {
            notifyItemRemoved(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract Model initModel();

    public T getCurrentItem(int pos) {
        if (pos >= 0) {
            return mPagerAdapterModel.get(pos);
        } else {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        if (mPagerAdapterModel == null) {
            Log.i("QQQQ", new DebugInfo() + "null");
        } else {
            Log.i("WWWW", new DebugInfo() + "" + mPagerAdapterModel.hashCode() + "");
            Log.i("QQQQ", new DebugInfo() + "" + mPagerAdapterModel.getCount());
        }
        return mPagerAdapterModel == null ? 0 : mPagerAdapterModel.getCount();
    }

    public abstract int getLayoutId();
}
