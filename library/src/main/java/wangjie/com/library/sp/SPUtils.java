package wangjie.com.library.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;
import java.util.Set;

import wangjie.com.library.base.base.BaseApplication;

/**
 * SharedPreferences工具类
 *
 * @author wangheng
 */
public class SPUtils {

    /**
     * SharedPreferences的实例 *
     */
    private SharedPreferences mSP = null;

    private SharedPreferences mWSP = null;

    /**
     * 不需要外部创建实例 *
     */
    private SPUtils() {

    }

    /**
     * 得到SPUtils的单例
     *
     * @return 得到SPUtils
     */
    public static SPUtils getInstance() {
        return Singleton.INSTANCE;
    }

    /**
     * 得到SharedPreferences
     *
     * @return 得到全局SP
     */
    private synchronized SharedPreferences getPrefrences() {
        if (null == mSP) {
            //use the context in CoreLibrary instead0
            //mSP = CoreLibrary.getInstance().getContext().getSharedPreferences("LockState", Context.MODE_MULTI_PROCESS);
            mSP = PreferenceManager.getDefaultSharedPreferences(
                    BaseApplication.getBaseApplication().getBaseContext());
        }
        return mSP;
    }


    private synchronized SharedPreferences getLockStatePrefrences() {
        if (null == mWSP) {
            mWSP = BaseApplication.getBaseApplication().getBaseContext().getSharedPreferences("wangjie", Context.MODE_MULTI_PROCESS);
        }
        return mWSP;
    }
    /**
     * clear:清空SP. <br/>
     *
     */
    public synchronized void clear() {
        getPrefrences().edit().clear().apply();
    }

    /**
     * 把指定的String-String键值对放入SP中
     *
     * @param key key
     * @param value value
     */
    public synchronized void putString(String key, String value) {
        getPrefrences().edit().putString(key, value).apply();
    }


    /**
     * 得到SP中指定Key的String值
     *
     * @param key key
     * @param defaultValue 默认值
     *
     * @return value
     */
    public String getString(String key, String defaultValue) {
        return getPrefrences().getString(key, defaultValue);
    }

    /**
     * 把指定的String-int键值对放入SP中
     *
     * @param key key
     * @param value value
     */
    public synchronized void putInt(String key, int value) {
        getPrefrences().edit().putInt(key, value).apply();
    }

    /**
     * 得到SP中指定Key的int值
     *
     * @param key key
     * @param defaultValue 默认值
     *
     * @return
     */
    public int getInt(String key, int defaultValue) {
        return getPrefrences().getInt(key, defaultValue);
    }

    /**
     * 把指定的String-long键值对放入SP中
     *
     * @param key key
     * @param value value
     */
    public synchronized void putLong(String key, long value) {
        getPrefrences().edit().putLong(key, value).apply();
    }

    /**
     * 得到SP中指定Key的long值
     *
     * @param key key
     * @param defaultValue 默认值
     *
     * @return value
     */
    public long getLong(String key, long defaultValue) {
        return getPrefrences().getLong(key, defaultValue);
    }

    /**
     * 把指定的String-float键值对放入SP中
     *
     * @param key key
     * @param value value
     */
    public synchronized void putFloat(String key, float value) {
        getPrefrences().edit().putFloat(key, value).apply();
    }

    /**
     * 得到SP中指定Key的float值
     *
     * @param key keh
     * @param defaultValue 默认值
     *
     * @return value
     */
    public float getFloat(String key, float defaultValue) {
        return getPrefrences().getFloat(key, defaultValue);
    }

    /**
     * 把指定的String-Set<String>键值对放入SP中
     *
     * @param key key
     * @param values 默认值
     */
    public synchronized void putStringSet(String key, Set<String> values) {
        getPrefrences().edit().putStringSet(key, values).apply();
    }

    /**
     * 得到SP中指定Key的Set<String>值
     *
     * @param key key
     * @param defaultValues 默认值
     *
     * @return value
     */
    public Set<String> getStringSet(String key, Set<String> defaultValues) {
        return getPrefrences().getStringSet(key, defaultValues);
    }

    public synchronized void putStringSet(Map<String, Object> values) {
        SharedPreferences.Editor editor = getPrefrences().edit();
        for (String key : values.keySet()) {
            if (values.get(key)instanceof Boolean) {
                editor.putBoolean(key, (Boolean) values.get(key));
            } else if (values.get(key) instanceof String) {
                editor.putString(key, (String) values.get(key));
            } else if (values.get(key) instanceof Integer) {
                editor.putInt(key, (Integer) values.get(key));
            } else {
                editor.putString(key, (String) values.get(key));
            }
        }
        boolean b = editor.commit();
    }

    /**
     * 把指定的String-boolean键值对放入SP中
     *
     * @param key key
     * @param value value
     */
    public synchronized void  putBoolean(String key, boolean value) {
        getPrefrences().edit().putBoolean(key, value).apply();
    }

    /**
     * 得到SP中指定Key的boolean值
     *
     * @param key key
     * @param defaultValue 默认值
     *
     * @return value
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return getPrefrences().getBoolean(key, defaultValue);
    }

    /**
     * 这个类存在的唯一意义就是为了创建SPUtils的单例
     *
     * @author wangheng
     */
    private static final class Singleton {
        private static final SPUtils INSTANCE = new SPUtils();
    }
}
