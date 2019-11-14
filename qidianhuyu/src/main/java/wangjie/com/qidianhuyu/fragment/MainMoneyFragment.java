package wangjie.com.qidianhuyu.fragment;

import android.os.Bundle;

import wangjie.com.library.base.fragment.BaseFragment;
import wangjie.com.qidianhuyu.R;

public class MainMoneyFragment extends BaseFragment {

    public static MainMoneyFragment newInstance() {
        Bundle args = new Bundle();
        MainMoneyFragment mainTaskFragment = new MainMoneyFragment();
        mainTaskFragment.setArguments(args);
        return mainTaskFragment;
    }

    @Override
    public void initParameter() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_main_navi;
    }
}
