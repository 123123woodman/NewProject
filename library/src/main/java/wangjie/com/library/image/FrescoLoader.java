package wangjie.com.library.image;

import android.net.Uri;
import android.support.annotation.DrawableRes;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.HashMap;

import wangjie.com.library.R;
import wangjie.com.library.base.base.BaseApplication;

/**
 * Fresco实现类
 */
public class FrescoLoader extends BaseImageLoader<DraweeView, FrescoLoader.FrescoOption>{

    private HashMap<DraweeView, DraweeHierarchy> hashMap;
    private GenericDraweeHierarchy hierarchy;
    private GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder;

    public static class FrescoOption implements ImageWrapper.ImageOption{
        private ResizeOptions mResizeOptions;

        public ResizeOptions getResizeOptions() {
            return mResizeOptions;
        }

        public void setResizeOptions(ResizeOptions resizeOptions) {
            mResizeOptions = resizeOptions;
        }
    }

    private ProgressBarDrawable progressBarDrawable;

    /**
     * 自定义进度条
     */
    private ProgressBarDrawable getProgressBarDrawable() {
        if (progressBarDrawable == null) progressBarDrawable = new ProgressBarDrawable();
        progressBarDrawable.setBackgroundColor(BaseApplication.getBaseApplication().getResources().getColor(R.color.green));
        progressBarDrawable.setColor(BaseApplication.getBaseApplication().getResources().getColor(R.color.colorAccent));
        progressBarDrawable.setPadding(0);
        progressBarDrawable.setBarWidth(5);
        return progressBarDrawable;
    }

    /**
     * 自定义Hierarchy待完善
     * @return
     */
    public GenericDraweeHierarchy hierarchy() {
            genericDraweeHierarchyBuilder = new GenericDraweeHierarchyBuilder(BaseApplication.getBaseApplication().getResources());
            hierarchy = genericDraweeHierarchyBuilder
            .setPlaceholderImage(BaseApplication.getBaseApplication().getResources().getDrawable(R.drawable.admin_down))
            .setProgressBarImage(progressBarDrawable)
            .setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY)
            .setFailureImage(BaseApplication.getBaseApplication().getResources().getDrawable(R.drawable.admin_danger))
            //.setActualImageFocusPoint(point)
            .build();
            return hierarchy;
    }

    /**
     * 自定义ImageQuest待完善
     * @param builder
     * @return
     */
    public ImageRequest getImageRequest (ImageRequestBuilder builder) {
        MyPostprocessor myPostprocessor = new MyPostprocessor();
        ImageRequest request = builder
            .setLocalThumbnailPreviewsEnabled(true)
            .setAutoRotateEnabled(true)
            .setPostprocessor(myPostprocessor)
            .setResizeOptions(new ResizeOptions(10, 10))
            .build();
        return request;
    }

    private ImageRequestBuilder getBuilder(Uri uri) {
        return ImageRequestBuilder.newBuilderWithSource(uri);
    }

    private ImageRequestBuilder getBuilder(@DrawableRes int id) {
        return ImageRequestBuilder.newBuilderWithResourceId(id);
    }

    private void showImage(DraweeView draweeView, ImageRequestBuilder builder, FrescoOption options) {

        GenericDraweeHierarchy hierarchy = (GenericDraweeHierarchy) draweeView.getHierarchy();
        hierarchy.setProgressBarImage(getProgressBarDrawable());
        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);

        ImageRequest request = getImageRequest(builder);

        DraweeController controller = Fresco.newDraweeControllerBuilder()
            .setTapToRetryEnabled(true)
            //.setLowResImageRequest(ImageRequest.fromUri("http://image.baidu.com/search/down?tn=download&ipn=dwnl&word=download&ie=utf8&fr=result&url=http%3A%2F%2Fpic.58pic.com%2F01%2F35%2F65%2F51bOOOPICb0.jpg&thumburl=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D1629028640%2C4159944060%26fm%3D27%26gp%3D0.jpg"))
            //.setImageRequest(ImageRequest.fromUri("http://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F7%2F573424eb89411.jpg&thumburl=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D164958517%2C1169001158%26fm%3D27%26gp%3D0.jpg"))
            .setImageRequest(request)
            //.setFirstAvailableImageRequests(imageRequests, true)
            .build();
        draweeView.setController(controller);
    }


    @Override
    public void showImage(DraweeView imageView, Uri uri, FrescoOption imageOption) {
        showImage(imageView, getBuilder(uri), imageOption);
    }

    @Override
    public void showImage(DraweeView imageView, int resauseId, FrescoOption imageOption) {
        showImage(imageView, getBuilder(resauseId), imageOption);
    }

    /**
     * 清空缓存
     */
    @Override
    public void clearFrescoCatch() {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearCaches();
    }

}
