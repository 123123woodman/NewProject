package wangjie.com.qidianhuyu.ali.view.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import wangjie.com.qidianhuyu.R;
import wangjie.com.qidianhuyu.ali.net.data.LittleVIdeoInfo;

/**
 * 视频信息, 包含用户头像, 用户昵称, 视频标题
 */
public class VideoInfoView extends RelativeLayout {

    private ImageView userIcon;
    private TextView userNickName;
    private TextView title;

    public VideoInfoView(Context context) {
        this(context, null);
    }

    public VideoInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_video_cotent_view, this, true);
        userIcon = view.findViewById(R.id.iv_user_icon);
        userNickName = view.findViewById(R.id.tv_user_nick_name);
        title = view.findViewById(R.id.tv_title);
    }

    public void setVideoInfo(LittleVIdeoInfo videoInfo) {
//        if (videoInfo.getUser() != null) {
//            new ImageLoaderImpl().loadImage(getContext(), videoInfo.getUser().getAvatarUrl()).into(userIcon);
//            userNickName.setText(videoInfo.getUser().getUserName());
//        } else {
            //  如果没有用户信息用默认名称和图标
            userIcon.setImageResource(R.drawable.temp_user_icon);
            userNickName.setText(getResources().getString(R.string.alivc_little_play_anonymous));
        //}

        title.setText(videoInfo.getUserName());
    }
}
