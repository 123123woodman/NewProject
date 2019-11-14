package wangjie.com.qidianhuyu.fragment;

import android.os.Bundle;

import wangjie.com.library.base.fragment.BaseFragment;
import wangjie.com.qidianhuyu.R;

public class MainMineFragment extends BaseFragment {

    public static MainMineFragment newInstance() {
        Bundle bundle = new Bundle();
        MainMineFragment mainMineFragment = new MainMineFragment();
        mainMineFragment.setArguments(bundle);
        return mainMineFragment;
    }

    @Override
    public void initParameter() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_main_mine;
    }
}
