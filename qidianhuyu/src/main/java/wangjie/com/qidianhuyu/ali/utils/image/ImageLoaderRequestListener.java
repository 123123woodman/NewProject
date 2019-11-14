package wangjie.com.qidianhuyu.ali.utils.image;


/**
 * @date 2018/12/06
 * <p>描述: imageLoader listener
 */
public interface ImageLoaderRequestListener<R> {

    boolean onLoadFailed(String exception, boolean isFirstResource);

    boolean onResourceReady(R resource, boolean isFirstResource);
}
