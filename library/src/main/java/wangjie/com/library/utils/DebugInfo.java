package wangjie.com.library.utils;

/**
 * Created by Administrator on 2017/10/26.
 */
public class DebugInfo extends Exception {

    public int line() {
        StackTraceElement[] trace = getStackTrace();
        if (trace == null || trace.length == 0) {
            return -1;
        }
        return trace[0].getLineNumber();
    }

    public String fun() {
        StackTraceElement[] trace = getStackTrace();
        if (trace == null || trace.length == 0) {
            return "";
        }
        return trace[0].getMethodName();
    }

    public DebugInfo() {
        super();
    }

    @Override
    public String toString() {
        return line() + "|" + fun() + "|";
    }
}
