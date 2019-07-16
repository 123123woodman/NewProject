package wangjie.com.library.adapter;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import wangjie.com.library.utils.DebugInfo;

/**
 * Created by someHui on 4/12/16.
 */
public class ListModel<T extends Serializable> implements IPageModel<T> {
    private final List<T> mItems = new ArrayList<>();
    private T getCurrentTag(int pos){
        return mItems.get(Math.min(pos,mItems.size()-1));
    }

    @Override
    public void setList(List<T> list) {
        mItems.clear();
        if (list!=null){
            Log.i("WWWW", this.hashCode() + "/" + new DebugInfo() + "" +list.size());
            mItems.addAll(list);
        }
    }

    @Override
    public void addList(List<T> list) {
        if (list!=null){
            mItems.addAll(list);
        }
    }

    @Override
    public List<T> getList() {
        return mItems;
    }

    @Override
    public T get(int position) {
        return mItems.get(position);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }
}
