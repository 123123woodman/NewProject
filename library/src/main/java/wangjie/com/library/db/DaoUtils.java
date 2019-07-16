package wangjie.com.library.db;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import wangjie.com.library.base.base.BaseApplication;
import wangjie.com.library.bean.LogInfoBean;
import wangjie.com.library.dao.LogInfoBeanDao;


/**
 * Created by Administrator on 2018/4/10.
 */

public class DaoUtils {

    private LogInfoBeanDao logInfoBeanDao;

    public DaoUtils() {
        logInfoBeanDao = BaseApplication.getDaoSession().getLogInfoBeanDao();
    }

    public long insertLogInfo(LogInfoBean logInfoBean) {
        return logInfoBeanDao.insert(logInfoBean);
    }

    public List<LogInfoBean> querryAll() {
        List<LogInfoBean> logInfoBeen = logInfoBeanDao.queryBuilder().orderDesc(LogInfoBeanDao.Properties.Id).list();
        return logInfoBeen;
    }

    public void insertLogInfo(List<LogInfoBean> logInfoBeanList) {
        logInfoBeanDao.insertInTx(logInfoBeanList);
    }

    public void cleanLogInfo() {
        logInfoBeanDao.deleteAll();
    }

    public List<LogInfoBean> queryLogInfo(String parame) {
        Query<LogInfoBean> query = logInfoBeanDao.queryBuilder().where(LogInfoBeanDao.Properties.LogInfo.eq(parame)).build();
        return query.list();
    }

}
