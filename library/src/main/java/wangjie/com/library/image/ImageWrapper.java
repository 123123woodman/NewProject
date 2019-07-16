package wangjie.com.library.image;

import android.net.Uri;

import java.io.File;

/**
 * showImage包装接口
 */
public interface ImageWrapper<TARGET extends Object, OPTION extends ImageWrapper.ImageOption> {
    interface ImageOption{}

    /**
     * uri
     * @param imageView
     * @param uri
     * @param imageOption
     */
    void showImage(TARGET imageView, Uri uri, OPTION imageOption);

    /**
     * string
     * @param imageView
     * @param uri
     * @param imageOption
     */
    void showImage(TARGET imageView, String uri, OPTION imageOption);

    /**
     * file
     * @param imageView
     * @param file
     * @param imageOption
     */
    void showImage(TARGET imageView, File file, OPTION imageOption);

    /**
     * int
     * @param imageView
     * @param resauseId
     * @param imageOption
     */
    void showImage(TARGET imageView, int resauseId, OPTION imageOption);

    /**
     * 清空fresco缓存
     */
    void clearFrescoCatch();
}
