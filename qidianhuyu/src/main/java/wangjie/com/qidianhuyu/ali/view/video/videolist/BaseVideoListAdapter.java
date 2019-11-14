package wangjie.com.qidianhuyu.ali.view.video.videolist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import wangjie.com.qidianhuyu.ali.utils.image.ImageLoaderImpl;
import wangjie.com.qidianhuyu.ali.utils.image.ImageLoaderOptions;
import wangjie.com.qidianhuyu.ali.utils.image.ImageLoaderRequestListener;
import wangjie.com.qidianhuyu.ali.view.video.LittleVideoListAdapter;

/**
 * 视频列表的adapter
 */
public abstract class BaseVideoListAdapter<VH extends BaseVideoListAdapter.BaseHolder, T extends IVideoSourceModel> extends RecyclerView.Adapter<VH> {
    public static final String TAG = LittleVideoListAdapter.class.getSimpleName();
    protected List<T> list;
    protected Context context;

    private Point mScreenPoint = new Point();

    public BaseVideoListAdapter(Context context, List<T> urlList) {
        this.context = context;
        this.list = urlList;
        //获取屏幕宽高
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        mScreenPoint.x = displayMetrics.widthPixels;
        mScreenPoint.y = displayMetrics.heightPixels;

    }
    public BaseVideoListAdapter(Context context) {
        this(context, new ArrayList<T>());
    }

    @Override
    public void onBindViewHolder(final VH holder, int position) {

        Log.d(TAG, "onBindViewHolder position:" + position);
        final IVideoSourceModel video = list.get(position);
        String coverPath = "http://alivc-demo-vod.aliyuncs.com/606a1932980c4659b0cea0c002c5f4a2/snapshots/normal/1DDF26E2-16850C34008-1103-1445-334-2638600001.jpg";
        final ImageView iv = holder.getCoverView();
        new ImageLoaderImpl().loadImage(context, coverPath, new ImageLoaderOptions.Builder()
                                        .asBitmap()
                                        .placeholder(android.R.color.black)
                                        .thumbnail(0.1f)
                                        .build()
        ).listener(new ImageLoaderRequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(String exception, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, boolean isFirstResource) {
                float aspectRatio = (float)resource.getWidth() / resource.getHeight();
                float screenRatio = mScreenPoint.x / (float)mScreenPoint.y;
                Log.d(TAG, "aspectRatio : " + aspectRatio + " ,screenRatio : " + screenRatio + "\n mScreenPoint : " + mScreenPoint.toString());
                if (aspectRatio <= (9f / 16f + 0.01) && aspectRatio >= (9f / 16f - 0.01) //考虑到float值不精确的原因取一个范围值 视频比例 = 9/16
                        && (screenRatio < 9f / 16f - 0.01) //屏幕宽高比例小于9/16(长手机)
                   ) {
                    float height = holder.getContainerView().getHeight();
                    float width = height * resource.getWidth() / resource.getHeight();
                    ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
                    layoutParams.width = (int)width;
                    layoutParams.height = (int)height;
                    iv.setLayoutParams(layoutParams);

                } else {
                    //获取屏幕宽度
                    float screenWith = mScreenPoint.x;
                    ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
                    //获取imageview的高度
                    float height = screenWith * resource.getHeight() / resource.getWidth();
                    layoutParams.width = (int)screenWith;
                    layoutParams.height = (int)height;
                    iv.setLayoutParams(layoutParams);
                    Log.d(TAG, "bitmap width : " + screenWith + " height : " + height);
                }
                return false;
            }
        }).into(iv);

    }
    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    /**
     * 刷新数据
     * @param list
     */
    public void refreshData(List<T> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     * @param list
     */
    public void addMoreData(List<T> list) {
        this.list.addAll(list);
        notifyItemRangeInserted(this.list.size() - list.size(), list.size());
    }

    public List<T> getDataList() {
        return list;
    }

    public abstract class BaseHolder extends RecyclerView.ViewHolder  {
        public BaseHolder(View itemView) {
            super(itemView);
        }
        public abstract ImageView getCoverView();
        public abstract ViewGroup getContainerView();
    }
}
