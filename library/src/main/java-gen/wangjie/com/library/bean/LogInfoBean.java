package wangjie.com.library.bean;

import org.greenrobot.greendao.annotation.*;

import java.io.Serializable;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "LOG_INFO_BEAN".
 */
@Entity
public class LogInfoBean implements Serializable {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String logInfo;

    @NotNull
    private String log;

    @NotNull
    private String ll;

    @Generated
    public LogInfoBean() {
    }

    public LogInfoBean(Long id) {
        this.id = id;
    }

    @Generated
    public LogInfoBean(Long id, String logInfo, String log, String ll) {
        this.id = id;
        this.logInfo = logInfo;
        this.log = log;
        this.ll = ll;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getLogInfo() {
        return logInfo;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setLogInfo(@NotNull String logInfo) {
        this.logInfo = logInfo;
    }

    @NotNull
    public String getLog() {
        return log;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setLog(@NotNull String log) {
        this.log = log;
    }

    @NotNull
    public String getLl() {
        return ll;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setLl(@NotNull String ll) {
        this.ll = ll;
    }

}
