package wangjie.com.library.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by someHui on 4/18/16.
 */
public abstract class AAdapter<Holder extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<Holder> implements IUIAdapter<Holder> {
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return newHolder(parent.getContext());
    }
}
