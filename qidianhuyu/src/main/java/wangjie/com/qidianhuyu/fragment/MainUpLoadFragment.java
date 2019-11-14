package wangjie.com.qidianhuyu.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import wangjie.com.library.base.fragment.BaseFragment;
import wangjie.com.qidianhuyu.R;
import wangjie.com.qidianhuyu.R2;
import wangjie.com.qidianhuyu.adapter.ExamplePagerAdapter;
import wangjie.com.qidianhuyu.component.DaggerMainUpLoadFragmentComponent;
import wangjie.com.qidianhuyu.component.MainUpLoadFragmentComponent;
import wangjie.com.qidianhuyu.entity.wx.WXChartTitle;
import wangjie.com.qidianhuyu.module.MainUpLoadModule;
import wangjie.com.qidianhuyu.presenter.MainUpLoadPresenter;
import wangjie.com.qidianhuyu.titles.ScaleTransitionPagerTitleView;

public class MainUpLoadFragment extends BaseFragment {

    private static final String[] CHANNELS = new String[]{"CUPCAKE", "DONUT", "ECLAIR", "GINGERBREAD", "HONEYCOMB", "ICE_CREAM_SANDWICH", "JELLY_BEAN", "KITKAT", "LOLLIPOP", "M", "NOUGAT"};
    private List<WXChartTitle> mDataList;
    private ExamplePagerAdapter mExamplePagerAdapter;
    private MainUpLoadFragmentComponent weUpLoadFragmentComponent;

    @BindView(R2.id.wechat_magicindicator)
    MagicIndicator magicIndicator;
    @BindView(R2.id.wechar_vp)
    ViewPager viewPager;

    private TextView textView;

    @Inject
    MainUpLoadPresenter mainUpLoadPresenter;


    public static MainUpLoadFragment newInstance() {
        Bundle args = new Bundle();
        MainUpLoadFragment mainUpLoadFragment = new MainUpLoadFragment();
        mainUpLoadFragment.setArguments(args);
        return mainUpLoadFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.test_text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainUpLoadPresenter.refreshUI();
            }
        });
        initParameter();
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
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
    }

    @Override
    public void initParameter() {

        weUpLoadFragmentComponent = DaggerMainUpLoadFragmentComponent.builder()
                .appComponent(getAppComponent())
                .mainUpLoadModule(new MainUpLoadModule(this))
                .build();
        weUpLoadFragmentComponent.ingect(this);

        mainUpLoadPresenter.refreshUI();
    }

    public void refresh(List<WXChartTitle> wxChartTitles) {
        mDataList = wxChartTitles;
        mExamplePagerAdapter = new ExamplePagerAdapter(mDataList);

        viewPager.setAdapter(mExamplePagerAdapter);
        initMagicIndicator();

    }

    private void initMagicIndicator() {
        magicIndicator.setBackgroundColor(Color.parseColor("#fafafa"));
        CommonNavigator commonNavigator = new CommonNavigator(_Activity);
        commonNavigator.setScrollPivotX(0.45f);
        commonNavigator.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
//                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
//                simplePagerTitleView.setText(mDataList.get(index));
//                simplePagerTitleView.setNormalColor(Color.parseColor("#9e9e9e"));
//                simplePagerTitleView.setSelectedColor(R.color.white);
//                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        viewPager.setCurrentItem(index);
//                    }
//                });
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index).getName());
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setNormalColor(Color.GRAY);
                simplePagerTitleView.setSelectedColor(Color.WHITE);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 6));
                indicator.setLineWidth(UIUtil.dip2px(context, 10));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.WHITE);
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_main_wechat;
    }
}
