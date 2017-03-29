package net.teligen.httpposttest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/3/23.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("getEncryDey")
    Call<TestEntity> readTrackList(@FieldMap Map<String, String> map);    //map为复杂的查询参数，Call为回调处理


}
