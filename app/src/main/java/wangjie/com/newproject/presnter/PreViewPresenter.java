package wangjie.com.newproject.presnter;

import javax.inject.Inject;

import wangjie.com.newproject.animation.FrameAnimation;

/**
 * Created by Administrator on 2018/3/29.
 */

public class PreViewPresenter {
    @Inject
    public PreViewPresenter() {}

    private FrameAnimation frameAnimation;

    public void initFrameAnimation(FrameAnimation frameAnimation) {

        this.frameAnimation = frameAnimation;

        frameAnimation.setOnFrameFinisedListener(new FrameAnimation.OnFrameFinishedListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onStop() {

            }
        });

        frameAnimation.setGapTime(10);

    }

    public void startFrameAnimation() {
        frameAnimation.start();
    }
}
