package wangjie.com.qidianhuyu.fragment;

import android.os.Bundle;

import wangjie.com.library.base.fragment.BaseFragment;
import wangjie.com.qidianhuyu.R;

public class MainProjectFragment extends BaseFragment {

    public static MainProjectFragment newInstance() {
        Bundle args = new Bundle();
        MainProjectFragment mainProjectFragment = new MainProjectFragment();
        mainProjectFragment.setArguments(args);
        return mainProjectFragment;
    }

    @Override
    public void initParameter() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_main_project;
    }
}
