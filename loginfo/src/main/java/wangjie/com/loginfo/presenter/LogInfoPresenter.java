package wangjie.com.loginfo.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import wangjie.com.library.base.presenter.BasePresenter;
import wangjie.com.library.bean.LogInfoBean;
import wangjie.com.loginfo.ui.LogInfoActivity;

/**
 * Created by Administrator on 2018/4/3.
 */

public class LogInfoPresenter extends BasePresenter<LogInfoActivity> {
    private Context uiContext;
    List<LogInfoBean> iconList;
    List<LogInfoBean> list;

    public LogInfoPresenter(Context uiContext) { this.uiContext = uiContext; }

    private LogInfoBean logInfoBean;

    public void refreshUi() {

        iconList = getUI().logInfoDaoUtils.querryAll();

        getUI().logInfoRecyclerAdapter.updateList(iconList);

    }

    public void addIcon() {
        list = new ArrayList<LogInfoBean>();
        logInfoBean = new LogInfoBean(null, "怎么还是你", "王捷", "qwe");
        list.add(logInfoBean);
        logInfoBean = new LogInfoBean(null, "123木头人", "王捷捷", "qwe");
        list.add(logInfoBean);
        logInfoBean = new LogInfoBean(null, "呵呵", "王捷捷", "qwe");
        list.add(logInfoBean);
        logInfoBean = new LogInfoBean(null, "虹娘科技", "王捷捷", "qwe");
        list.add(logInfoBean);

        getUI().logInfoDaoUtils.insertLogInfo(list);
//        for (int i = 0; i < 50; i++) {
//            logInfoDao.insertLogInfo(list);
//        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                logInfoDao.insertLogInfo(list);
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                logInfoDao.insertLogInfo(list);
//            }
//        }).start();

        iconList = getUI().logInfoDaoUtils.querryAll();

        getUI().logInfoRecyclerAdapter.updateList(iconList);

    }

    public void cleanAll() {
        getUI().logInfoDaoUtils.cleanLogInfo();

        iconList = getUI().logInfoDaoUtils.querryAll();
        getUI().logInfoRecyclerAdapter.updateList(iconList);
    }


    @Override
    public LogInfoActivity getUI() {
        return (LogInfoActivity) uiContext;
    }
}
