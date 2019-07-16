package wangjie.com.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Created by someHui on 4/18/16.
 */
public interface IUIAdapter<Holder extends RecyclerView.ViewHolder> {
    Holder newHolder(Context context);
}
