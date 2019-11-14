package wangjie.com.qidianhuyu.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import wangjie.com.library.base.activity.BaseActivity;
import wangjie.com.library.base.fragment.BaseFragment;
import wangjie.com.qidianhuyu.R;
import wangjie.com.qidianhuyu.R2;
import wangjie.com.qidianhuyu.ali.net.NetWatchdog;
import wangjie.com.qidianhuyu.ali.utils.PermissionUtils;
import wangjie.com.qidianhuyu.fragment.MainHomeFragment;
import wangjie.com.qidianhuyu.fragment.MainMineFragment;
import wangjie.com.qidianhuyu.fragment.MainMoneyFragment;
import wangjie.com.qidianhuyu.ui.view.BottomBar;
import wangjie.com.qidianhuyu.ui.view.BottomBarTab;

public class MainActivity extends BaseActivity {

    private static final int PERMISSION_REQUEST_CODE = 1001;

    /**
     * 网络状态监听器
     */
    private NetWatchdog netWatchdog;

    /**
     * 权限列表
     */
    String[] permission = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * 系统授权设置的弹框
     */
    AlertDialog openAppDetDialog;

    @BindView(R2.id.fl_container)
    FrameLayout frameLayout;
    @BindView(R2.id.activity_main_bottombar)
    BottomBar bottomBar;

    private BaseFragment[] baseFragments = new BaseFragment[3];
    private final int FIRST = 0;
    private final int SECOND = 1;
    private final int THIRD = 2;
    @Override
    public void initParameter() {

        BaseFragment homeFragment = findFragment(MainHomeFragment.class);
        if (homeFragment == null) {
            baseFragments[FIRST] = MainHomeFragment.newInstance();
            baseFragments[SECOND] = MainMoneyFragment.newInstance();
            baseFragments[THIRD] = MainMineFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, FIRST, baseFragments);
        } else {
            baseFragments[FIRST] = homeFragment;
            baseFragments[SECOND] = findFragment(MainMoneyFragment.class);
            baseFragments[THIRD] = findFragment(MainMineFragment.class);
        }

        bottomBar.addItem(new BottomBarTab(this, 0, "看视频", R.color.white))
                  .addItem(new BottomBarTab(this, R.mipmap.qiandai, null, R.color.white))
                  .addItem(new BottomBarTab(this, 0, "我", R.color.balck));

        bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(baseFragments[position], baseFragments[prePosition]);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    setStatusBar(position);
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    public int getLayOutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStatusBar(FIRST);
        }
        //申请权限
        checkPermission();
        // 网络监听
        initNetWatchDog();
    }

    /**
     * 检查权限
     */
    private void checkPermission() {
        boolean checkResult = PermissionUtils.checkPermissionsGroup(this, permission);
        if (!checkResult) {
            PermissionUtils.requestPermissions(this, permission, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean isAllGranted = true;

            // 判断是否所有的权限都已经授予了
            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }

            if (isAllGranted) {
                // 如果所有的权限都授予了
                Toast.makeText(this, "get All Permisison", Toast.LENGTH_SHORT).show();
            } else {
                // 弹出对话框告诉用户需要权限的原因, 并引导用户去应用权限管理中手动打开权限按钮
                showPermissionDialog();
            }
        }
    }

    private void showPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.app_name)
                + getResources().getString(R.string.alivc_little_dialog_permission_tips));
        builder.setPositiveButton(getResources().getString(R.string.alivc_little_main_dialog_setting), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intent);
            }
        });
        builder.setCancelable(false);
        builder.setNegativeButton(getResources().getString(R.string.alivc_little_main_dialog_not_setting), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //finish();
            }
        });
        if (null == openAppDetDialog) {
            openAppDetDialog = builder.create();
        }
        if (null != openAppDetDialog && !openAppDetDialog.isShowing()) {
            openAppDetDialog.show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setStatusBar(int showFragmentId) {
        View decorView = getWindow().getDecorView();
        if (showFragmentId == 0) {
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            int option = View.SYSTEM_UI_FLAG_VISIBLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.BLUE);
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        netWatchdog.startWatch();
    }

    @Override
    protected void onStop() {
        super.onStop();
        netWatchdog.stopWatch();
    }

    /**
     * 网络监听
     */
    public void initNetWatchDog() {
        netWatchdog = new NetWatchdog(this);
        netWatchdog.setNetConnectedListener(new MyNetConnectedListener(this));
        netWatchdog.setNetChangeListener(new MyNetChangeListern());
    }

    /**
     * 网络断开重连监听
     */
    private static class MyNetConnectedListener implements NetWatchdog.NetConnectedListener {
        private WeakReference<MainActivity> weakReference;

        MyNetConnectedListener(MainActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void onReNetConnected(boolean isReconnect) {
            if (isReconnect) {
                //网络重连
                Log.e("Test", "onReNetConnected......");
            }
        }

        @Override
        public void onNetUnConnected() {
            //网络断开
            Log.e("Test", "onNetUnConnected......");
        }
    }

    /**
     * 网络变化
     */
    private static class MyNetChangeListern implements NetWatchdog.NetChangeListener {

        @Override
        public void onWifiTo4G() {
            Log.e("Test", "------WifiTo4G-------");
        }

        @Override
        public void on4GToWifi() {
            Log.e("Test", "-----4GToWifi--------");
        }

        @Override
        public void onNetDisconnected() {
            Log.i("Test", "-----无网络---------");
        }
    }
}
