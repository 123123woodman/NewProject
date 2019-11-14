package wangjie.com.qidianhuyu.ali.view.video;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import wangjie.com.qidianhuyu.R;
import wangjie.com.qidianhuyu.ali.net.data.LittleVIdeoInfo;
import wangjie.com.qidianhuyu.ali.view.video.videolist.BaseVideoListAdapter;

public class LittleVideoListAdapter extends BaseVideoListAdapter<LittleVideoListAdapter.MyHolder, LittleVIdeoInfo> {
    public static final String TAG = LittleVideoListAdapter.class.getSimpleName();
    public LittleVideoListAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public LittleVideoListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view_pager, viewGroup, false);
        return new LittleVideoListAdapter.MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull LittleVideoListAdapter.MyHolder myHolder, final int position) {
        super.onBindViewHolder(myHolder, position);
    }

    public final class  MyHolder extends BaseVideoListAdapter.BaseHolder {
        private ImageView thumb;
        public FrameLayout playerView;
        private ViewGroup mRootView;
        MyHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG,"new PlayerManager");
            thumb = itemView.findViewById(R.id.img_thumb);
            playerView = itemView.findViewById(R.id.player_view);
            mRootView = itemView.findViewById(R.id.root_view);
        }

        @Override
        public ImageView getCoverView() {
            return thumb;
        }

        @Override
        public ViewGroup getContainerView() {
            return mRootView;
        }

    }
}

