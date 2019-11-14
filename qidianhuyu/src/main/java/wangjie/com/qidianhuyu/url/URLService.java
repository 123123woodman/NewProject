package wangjie.com.qidianhuyu.url;

import io.reactivex.Observable;
import retrofit2.http.GET;
import wangjie.com.qidianhuyu.entity.response.BaseResponse;
import wangjie.com.qidianhuyu.entity.wx.WXChartTitle;

public interface URLService {

    @GET("chapters/json")
    Observable<BaseResponse<WXChartTitle>> getWXChartTitle();

}
