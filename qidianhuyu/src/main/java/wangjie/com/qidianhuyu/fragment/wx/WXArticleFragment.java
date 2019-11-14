package wangjie.com.qidianhuyu.fragment.wx;

import android.os.Bundle;

import wangjie.com.library.base.fragment.BaseFragment;

public class WXArticleFragment extends BaseFragment {
    private static String AUTHORID = "authorid";
    private int auhtorid = 0;

    public static WXArticleFragment newInstance(int authorId) {
        Bundle args = new Bundle();
        args.putInt(AUTHORID, authorId);
        WXArticleFragment wxArticleFragment = new WXArticleFragment();
        wxArticleFragment.setArguments(args);
        return wxArticleFragment;
    }

    @Override
    public void onLazyInitView(Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    @Override
    public void initParameter() {
        Bundle bundle = getArguments();
        auhtorid = bundle.getInt(AUTHORID);
    }

    @Override
    public int getLayout() {
        return 0;
    }
}
