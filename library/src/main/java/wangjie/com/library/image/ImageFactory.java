package wangjie.com.library.image;

public class ImageFactory {

    private ImageWrapper frescoLoader;

    public static ImageFactory getInstance() {
        return Singleton.INSTANCE;
    }

    /**
     * ImageFactory单例
     */
    private static final class Singleton{
        public static final ImageFactory INSTANCE = new ImageFactory();
    }

    /**
     * 创建FrescoLoader
     */
    public <T, OPTION extends ImageWrapper.ImageOption> ImageWrapper<T, OPTION> getFrescoLoader() {
        if (frescoLoader == null) {
            synchronized (ImageFactory.class) {
                if (frescoLoader == null) {
                    frescoLoader = new FrescoLoader();
                }
                return frescoLoader;
            }
        }
        return frescoLoader;
    }

}

