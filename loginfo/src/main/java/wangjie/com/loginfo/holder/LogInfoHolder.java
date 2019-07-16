package wangjie.com.loginfo.holder;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import wangjie.com.library.adapter.AHolder;
import wangjie.com.library.bean.LogInfoBean;
import wangjie.com.loginfo.R2;

/**
 * Created by Administrator on 2018/4/2.
 */

public class LogInfoHolder extends AHolder {

    @BindView(R2.id.loginfo_item_opendoor)
    TextView logInfoOpenDoor;


    public LogInfoHolder(View itemView) {
        super(itemView);
    }

    public void bindHodle(LogInfoBean logInfoBean) {

        logInfoOpenDoor.setText(logInfoBean.getLogInfo());
    }
}
