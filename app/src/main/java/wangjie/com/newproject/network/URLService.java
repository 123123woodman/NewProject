package wangjie.com.newproject.network;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import wangjie.com.newproject.data.HttpsData;
import wangjie.com.newproject.data.NewsData;
import wangjie.com.newproject.data.NewsFocus;
import wangjie.com.newproject.data.NewsTopData;
import wangjie.com.newproject.data.NewsTopFocus;
import wangjie.com.newproject.data.StringNewsData;
import wangjie.com.newproject.data.YiZhaoYun;
import wangjie.com.newproject.data.githttps.GitHttpsData;
import wangjie.com.newproject.data.xiaoliu.XiaoLiuSourse;

/**
 * Created by Administrator on 2018/3/22.
 */
public interface URLService {

    @POST("1?")
    Observable<StringNewsData<NewsData, NewsFocus, NewsTopData, NewsTopFocus>> getNewsInfo(@Query("pageNo") String pageNo,
                                                                                           @Query("Size") String Size,
                                                                                           @Query("serialIds") String serialIds,
                                                                                           @Query("v") String v,
                                                                                           @Query("chId") String chId);

    @POST("v1.0/api/archives/createArchives")
    Observable<YiZhaoYun> getMsgResponse(@Body RequestBody info);

    @GET("TomcatTest/SetInfo")
    Observable<HttpsData> getHttpsinfo();

    @GET("v1.0/api/classify/selectClassify")
    Observable<XiaoLiuSourse> getXiaoLiu();

    @GET(" ")
    Observable<GitHttpsData> getGit();

}
