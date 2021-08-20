package com.elysino.androidtask.retrofit;


import com.elysino.androidtask.datamodel.Main;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("weather")
    Call<Main> getWeather(@Query("q") String cityName,
                          @Query("appid") String apiKey);
}
