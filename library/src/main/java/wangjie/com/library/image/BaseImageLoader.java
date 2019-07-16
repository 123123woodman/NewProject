package wangjie.com.library.image;

import android.net.Uri;
import android.widget.ImageView;

import java.io.File;

public abstract class BaseImageLoader<TARGET extends ImageView, OPTION extends ImageWrapper.ImageOption>
        implements ImageWrapper<TARGET, OPTION>{

    @Override
    public void showImage(TARGET imageView, File file, OPTION imageOption) {
        showImage(imageView, file == null ? Uri.EMPTY : Uri.fromFile(file), imageOption);
    }

    @Override
    public void showImage(TARGET imageView, String uri, OPTION imageOption) {
        showImage(imageView, uri == null ? Uri.EMPTY : Uri.parse(uri), imageOption);
    }

}
