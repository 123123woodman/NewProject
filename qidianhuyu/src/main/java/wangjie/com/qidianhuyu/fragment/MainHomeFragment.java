package wangjie.com.qidianhuyu.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import wangjie.com.library.base.fragment.BaseFragment;
import wangjie.com.qidianhuyu.R;
import wangjie.com.qidianhuyu.R2;
import wangjie.com.qidianhuyu.ali.net.data.LittleVIdeoInfo;
import wangjie.com.qidianhuyu.ali.utils.Common;
import wangjie.com.qidianhuyu.ali.utils.ScreenUtils;
import wangjie.com.qidianhuyu.ali.view.video.AlivcVideoPlayView;
import wangjie.com.qidianhuyu.ali.view.video.videolist.AlivcVideoListView;
import wangjie.com.qidianhuyu.ali.view.video.videolist.IVideoSourceModel;

public class MainHomeFragment extends BaseFragment {

    private static final String TAG = "MainHomeFragment";
    private static final int PERMISSION_REQUEST_CODE = 1001;

    /**
     * assets目录文件拷贝工具类
     */
    private Common commonUtils;
    /**
     * 当前请求的视频列表最后数据的主键id，用于查询下一页数据
     */
    private int mLastVideoId = -1;
    /**
     * 数据请求是否为加载更多数据
     */
    private boolean isLoadMoreData = false;
    /**
     * 封面地址
     */
    private String mThumbnailPath;
    /**
     * 视频描述
     */
    private String mVideoDesc;
    /**
     * 判断视频是否正在显示视频列表
     */
    private boolean isHome = true;


    @BindView(R2.id.video_play)
    AlivcVideoPlayView videoPlayView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onLazyInitView(Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //初始化页面
        initView();
        // 注册编辑完成之后跳转的页面
        loadPlayList(mLastVideoId);
    }

    /**
     * 初始化View
     */
    protected void initView() {
        videoPlayView.setOnRefreshDataListener(new MyRefreshDataListener(this));

        float screenWidth = ScreenUtils.getWidth(_Activity);
        float screenHeight = ScreenUtils.getRealHeight(_Activity);
        if (screenHeight / screenWidth > 2) {
            int viewHeight = (int)(screenHeight - ScreenUtils.getNavigationHeight(_Activity));
            ViewGroup.LayoutParams layoutParams = videoPlayView.getLayoutParams();
            layoutParams.height = viewHeight;
            videoPlayView.setLayoutParams(layoutParams);
        }
    }

    private List<IVideoSourceModel> getList() {
        List<IVideoSourceModel> list = new ArrayList<>();
        LittleVIdeoInfo littleVIdeoInfo = new LittleVIdeoInfo();
        littleVIdeoInfo.setVideoUri("http://txmov2.a.yximgs.com/upic/2019/11/07/17/BMjAxOTExMDcxNzIxMjVfNjk2MzA1OTA3XzE5Mjg3NDQzMzQxXzJfMw==_b_Bca391162e4e03dec4c9bc511a41678cd.mp4");
        littleVIdeoInfo.setUserName("王杰");
        littleVIdeoInfo.setUUID("111111111111111");
        LittleVIdeoInfo littleVIdeoInfo1 = new LittleVIdeoInfo();
        littleVIdeoInfo1.setVideoUri("http://alimov2.a.yximgs.com/upic/2019/11/03/20/BMjAxOTExMDMyMDQzMDNfMTMxOTYxNjIxMF8xOTE3NTA4NzE5OV8yXzM=_b_B7cb0e0886ea775314df0ef4f3f9a356d.mp4?tag=1-1573704853-h-0-n1e31z70tg-dd9b385a3c9f0ae3&type=hot");
        littleVIdeoInfo1.setUserName("王杰");
        littleVIdeoInfo1.setUUID("222222222222222");
        LittleVIdeoInfo littleVIdeoInfo2 = new LittleVIdeoInfo();
        littleVIdeoInfo2.setVideoUri("http://txmov2.a.yximgs.com/upic/2019/11/08/18/BMjAxOTExMDgxODE3NTZfMTAwMzUxNTkyNV8xOTMxOTI1NTI3OV8xXzM=_b_B53d1ca4e9d8327eac66dff8c0d6eaf1a.mp4?tag=1-1573637786-h-0-o8u4hg2ibk-11c0eae0b756293f&type=hot");
        littleVIdeoInfo2.setUserName("王杰");
        littleVIdeoInfo2.setUUID("333333333333333");
        LittleVIdeoInfo littleVIdeoInfo3 = new LittleVIdeoInfo();
        littleVIdeoInfo3.setVideoUri("http://alimov2.a.yximgs.com/upic/2019/11/12/17/BMjAxOTExMTIxNzUyNDVfODMwODkzNzc2XzE5NDU3MjAyMDk1XzFfMw==_b_B9d5fe4c4466cab92c76a8393ce44dd7f.mp4?tag=1-1573637786-h-0-fwlxzygqt6-f91a53852670b686");
        littleVIdeoInfo3.setUserName("王杰");
        littleVIdeoInfo3.setUUID("44444444444444");
        LittleVIdeoInfo littleVIdeoInfo4 = new LittleVIdeoInfo();
        littleVIdeoInfo4.setVideoUri("http://hwmov.a.yximgs.com/upic/2019/11/12/01/BMjAxOTExMTIwMTQ2MjlfMTQyNzYzMzEzMV8xOTQ0MTUwNzY2M18xXzM=_b_Bf49697708ea1ac2ed7da55e5c1953ebb.mp4?tag=1-1573637786-h-0-cjjzrlyvpb-c7ec32eae031c518");
        littleVIdeoInfo4.setUserName("王杰");
        littleVIdeoInfo4.setUUID("5555555555555555");
        LittleVIdeoInfo littleVIdeoInfo5 = new LittleVIdeoInfo();
        littleVIdeoInfo5.setVideoUri("http://hwmov.a.yximgs.com/upic/2019/10/31/14/BMjAxOTEwMzExNDMyNTRfNTIxODIyOTI1XzE5MDQ3NzAzNzAxXzFfMw==_b_B8d5ec7867b61d88c09b3e43a1d8ac96b.mp4?tag=1-1573637786-h-0-h4r3ebqv9x-46f8e84fe1323fa0");
        littleVIdeoInfo5.setUserName("王杰");
        littleVIdeoInfo5.setUUID("6666666666666666");
        LittleVIdeoInfo littleVIdeoInfo6 = new LittleVIdeoInfo();
        littleVIdeoInfo6.setVideoUri("http://txmov2.a.yximgs.com/upic/2019/11/12/17/BMjAxOTExMTIxNzUyNDBfMTQ3Njk2NTI5Nl8xOTQ1NzIxNjE0N18yXzM=_b_B0e4aae403764191ed07c3447a7cd2ca6.mp4?tag=1-1573637786-h-0-uap3oupjlh-fa11a26c96a9efed&type=hot");
        littleVIdeoInfo6.setUserName("王杰");
        littleVIdeoInfo6.setUUID("77777777777777777");
        LittleVIdeoInfo littleVIdeoInfo7 = new LittleVIdeoInfo();
        littleVIdeoInfo7.setVideoUri("http://txmov2.a.yximgs.com/upic/2019/11/11/14/BMjAxOTExMTExNDQ1MTlfOTE0NjE1OTVfMTk0MjQyMTczMzhfMV8z_b_Bf1d10173605a03d17b7e2ab2de32119c.mp4?tag=1-1573637786-h-0-cikablesoo-f2d3b924e70017f0&type=hot");
        littleVIdeoInfo7.setUserName("王杰");
        littleVIdeoInfo7.setUUID("8888888888888888");
        LittleVIdeoInfo littleVIdeoInfo8 = new LittleVIdeoInfo();
        littleVIdeoInfo8.setVideoUri("http://alimov2.a.yximgs.com/upic/2019/11/13/09/BMjAxOTExMTMwOTQyMDhfNDA0NDkyODI2XzE5NDcyOTE3MTIyXzFfMw==_b_B2603b6860b6c1d026c9c6ba76e7c8b47.mp4?tag=1-1573637786-h-0-bcnyh5cx2c-8fdaab6f40b27796");
        littleVIdeoInfo8.setUserName("王杰");
        littleVIdeoInfo8.setUUID("99999999999999999");
        LittleVIdeoInfo littleVIdeoInfo9 = new LittleVIdeoInfo();
        littleVIdeoInfo9.setVideoUri("http://alimov2.a.yximgs.com/upic/2019/11/13/09/BMjAxOTExMTMwOTQyMDhfNDA0NDkyODI2XzE5NDcyOTE3MTIyXzFfMw==_b_B2603b6860b6c1d026c9c6ba76e7c8b47.mp4?tag=1-1573637786-h-0-bcnyh5cx2c-8fdaab6f40b27796");
        littleVIdeoInfo9.setUserName("王杰");
        littleVIdeoInfo9.setUUID("0000000000000000");
        list.add(littleVIdeoInfo);
        list.add(littleVIdeoInfo1);
        list.add(littleVIdeoInfo2);
        list.add(littleVIdeoInfo3);
        list.add(littleVIdeoInfo4);
        list.add(littleVIdeoInfo5);
        list.add(littleVIdeoInfo6);
        list.add(littleVIdeoInfo7);
        list.add(littleVIdeoInfo8);
        list.add(littleVIdeoInfo9);
        return list;
    }
    /**
     * 获取播放列表数据
     *
     * @param id 请求第pageNo页数据
     */
    private List<IVideoSourceModel> baseList = new ArrayList<>();
    private void loadPlayList(final int id) {
        Log.d(TAG, "pageNo:" + id);

        if (isLoadMoreData) {
            baseList.addAll(getList());
            videoPlayView.addMoreData1(baseList);
        } else {
            baseList.addAll(getList());
            videoPlayView.refreshVideoList(baseList);
        }
        //网络请求
    }

    @Override
    public boolean onBackPressedSupport() {
        return super.onBackPressedSupport();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }



    @Override
    public void onResume() {
        super.onResume();
        if (isHome) {
            videoPlayView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isHome) {
            videoPlayView.onPause();
        }

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        if (commonUtils != null) {
            commonUtils.onDestroy();
            commonUtils = null;
        }

        if (videoPlayView != null) {
            videoPlayView.onDestroy();
        }

        super.onDestroy();
    }

    /**
     * 视频播放列表刷新、加载更多事件监听
     */
    private static class MyRefreshDataListener implements AlivcVideoListView.OnRefreshDataListener {
        WeakReference<MainHomeFragment> weakReference;

        MyRefreshDataListener(MainHomeFragment mainHomeFragment) {
            weakReference = new WeakReference<>(mainHomeFragment);
        }

        @Override
        public void onRefresh() {

            MainHomeFragment mainHomeFragment = weakReference.get();
            if (mainHomeFragment != null) {
                mainHomeFragment.isLoadMoreData = false;
                mainHomeFragment.mLastVideoId = -1;
                mainHomeFragment.loadPlayList(mainHomeFragment.mLastVideoId);
            }
        }

        @Override
        public void onLoadMore() {
            MainHomeFragment mainHomeFragment = weakReference.get();
            if (mainHomeFragment != null) {
                mainHomeFragment.isLoadMoreData = true;
                mainHomeFragment.loadPlayList(mainHomeFragment.mLastVideoId);
            }
        }
    }

    public static MainHomeFragment newInstance() {

        Bundle args = new Bundle();

        MainHomeFragment mainHomeFragment = new MainHomeFragment();
        mainHomeFragment.setArguments(args);
        return mainHomeFragment;
    }

    @Override
    public void initParameter() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_video_list;
    }
}
