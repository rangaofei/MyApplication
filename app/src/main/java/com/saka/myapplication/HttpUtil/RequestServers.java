package com.saka.myapplication.HttpUtil;

import com.saka.myapplication.Models.HistoryModle;
import com.saka.myapplication.Models.IdForSearch;
import com.saka.myapplication.Models.Joke;
import com.saka.myapplication.Models.PhoneModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by saka on 2017/1/2.
 */

public interface RequestServers {

    @GET("idcard/index")
    Call<IdForSearch> getIdCardInfo(@Query("key") String key, @Query("cardno") String cardno);

    @GET("joke/content/list.from")
    Call<Joke> getJoke(@Query("key") String key, @Query("pagesize") int pagesize, @Query("time") String time);

    @GET("/mobile/get")
    Call<PhoneModel> getPhoneInfo(@Query("phone") String phonenum, @Query("key") String key);

    @GET("japi/toh")
    Call<HistoryModle> getHistory(@Query("key") String key, @Query("v") String v,
                                  @Query("month") int month, @Query("day") int day);
}
