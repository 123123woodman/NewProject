package wangjie.com.library.utils;

/**
 * Created by Administrator on 2018/3/30.
 */

public class ThreadSleepUtils {

    public static void threadSleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
