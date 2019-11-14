package wangjie.com.qidianhuyu.ali.view.video;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.aliyun.downloader.nativeclass.JniDownloader;
import com.aliyun.player.IPlayer;

import java.util.ArrayList;
import java.util.List;

import wangjie.com.qidianhuyu.ali.net.data.LittleMineVideoInfo;
import wangjie.com.qidianhuyu.ali.utils.DensityUtils;
import wangjie.com.qidianhuyu.ali.view.ShareDialog;
import wangjie.com.qidianhuyu.ali.view.video.videolist.AlivcVideoListView;
import wangjie.com.qidianhuyu.ali.view.video.videolist.IVideoSourceModel;
import wangjie.com.qidianhuyu.ali.widget.CircleProgressBar;


/**
 * 播放界面, 负责initPlayerSDK以及各种view
 */
public class AlivcVideoPlayView extends FrameLayout {


    private static final String TAG = "AlivcVideoPlayView";
    private Context context;
    private AlivcVideoListView videoListView;

    /**
     * 刷新数据listener (下拉刷新和上拉加载)
     */
    private AlivcVideoListView.OnRefreshDataListener onRefreshDataListener;
    /**
     * 视频缓冲加载view
     */
    private LoadingView mLoadingView;


    /**
     * 分享选择提示窗
     *
     * @param context
     */

    private ShareDialog mShareDialog;

    //private LittleVideoListAdapter mVideoAdapter;
    private LittleVideoListAdapter littleVideoListAdapter;

    public AlivcVideoPlayView(@NonNull Context context) {
        this(context, null);
    }

    public AlivcVideoPlayView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        initPlayListView();
        initLoadingView();
    }
    private CircleProgressBar mProgressBar;

    private int mClickPosition;

    /**
     * 初始化视频列表
     */
    private void initPlayListView() {
        videoListView = new AlivcVideoListView(context);
        //创建adapter，需要继承BaseVideoListAdapter
        littleVideoListAdapter = new LittleVideoListAdapter(context);
//        mApapter.setItemBtnClick(new LittleVideoListAdapter.OnItemBtnClick() {
//            @Override
//            public void onDownloadClick(int position) {
//                mClickPosition = position;
//
//                if (mShareDialog == null) {
//                    mShareDialog = new ShareDialog();
//                    mShareDialog.setItemSelectedListenr(new ShareDialog.OnItemSelectedListener() {
//                        @Override
//                        public void onDownloadClick() {
//
//                            /**
//                             * 准备分享
//                             */
//
//                        }
//
//                        @Override
//                        public void onDeleteClick() {
//                            /**
//                             * 暂不启用
//                             */
//                        }
//
//                    });
//                }
//            }
//        });
        //给AlivcVideoListView实例化对象添加adapter
        videoListView.setAdapter(littleVideoListAdapter);
        videoListView.setVisibility(VISIBLE);
        //设置sdk播放器实例化对象数量
        videoListView.setPlayerCount(1);
        //设置下拉、上拉监听进行加载数据
        videoListView.setOnRefreshDataListener(new AlivcVideoListView.OnRefreshDataListener() {
            @Override
            public void onRefresh() {
                if (onRefreshDataListener != null) {
                    onRefreshDataListener.onRefresh();
                }
            }

            @Override
            public void onLoadMore() {
                if (onRefreshDataListener != null) {
                    onRefreshDataListener.onLoadMore();
                }
            }
        });
        //设置视频缓冲监听
        videoListView.setLoadingListener(new IPlayer.OnLoadingStatusListener() {
            @Override
            public void onLoadingBegin() {
                mLoadingView.start();
            }

            @Override
            public void onLoadingEnd() {
                mLoadingView.cancle();
            }

            @Override
            public void onLoadingProgress(int var1, float var2) {

            }
        });

        videoListView.setOnCompleteListern(new IPlayer.OnCompletionListener() {
            @Override
            public void onCompletion() {

            }
        });
        //添加到布局中
        addSubView(videoListView);
    }

    private void initLoadingView() {
        mLoadingView = new LoadingView(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                5);
        params.setMargins(0, 0, 0, DensityUtils.dip2px(getContext(), 4));
        params.gravity = Gravity.BOTTOM;
        addView(mLoadingView, params);
    }

    /**
     * addSubView 添加子view到布局中
     *
     * @param view 子view
     */
    private void addSubView(View view) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        addView(view, params);
    }

    public void refreshVideoList(List<IVideoSourceModel> datas) {
        List<IVideoSourceModel> videoList = new ArrayList<>();
        videoList.addAll(datas);
        videoListView.refreshData(videoList);
        //取消加载loading
        mLoadingView.cancle();
    }

    /**
     * 刷新视频列表数据
     *
     * @param datas
     * @param position
     */
    public void refreshVideoList(List<LittleMineVideoInfo.VideoListBean> datas, int position) {
        List<IVideoSourceModel> videoList = new ArrayList<>();
        videoList.addAll(datas);
        videoListView.refreshData(videoList, position);
        //取消加载loading
        mLoadingView.cancle();
    }


    /**
     * 添加更多視頻
     */
    public void addMoreData1(List<IVideoSourceModel> data) {
        List<IVideoSourceModel> videoList = new ArrayList<>();
        videoList.addAll(data);
        videoListView.addMoreData(videoList);
        //取消加载loading
        mLoadingView.cancle();
    }

    /**
     * 添加更多视频
     *
     * @param datas
     */
    public void addMoreData(List<? extends BaseVideoSourceModel> datas) {
        List<IVideoSourceModel> videoList = new ArrayList<>();
        videoList.addAll(datas);
        videoListView.addMoreData(videoList);
        //取消加载loading
        mLoadingView.cancle();
    }

    /**
     * 设置下拉刷新数据listener
     *
     * @param listener OnRefreshDataListener
     */
    public void setOnRefreshDataListener(AlivcVideoListView.OnRefreshDataListener listener) {
        this.onRefreshDataListener = listener;
    }

    public void onStart() {

    }

    public void onResume() {
        videoListView.setOnBackground(false);

    }

    public void onStop() {
        mLoadingView.cancle();
    }

    public void onPause() {

        videoListView.setOnBackground(true);

    }

    public void onDestroy() {
        context = null;
    }

    /**
     * 视频列表获取失败
     */
    public void loadFailure() {
        mLoadingView.cancle();
        videoListView.loadFailure();
    }

}
