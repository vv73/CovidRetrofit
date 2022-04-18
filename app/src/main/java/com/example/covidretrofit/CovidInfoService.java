package com.example.covidretrofit;

import com.example.covidretrofit.history.CovidHistory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CovidInfoService {
    
    @GET("history")
    @Headers({
            "X-RapidAPI-Host: covid-193.p.rapidapi.com",
            "X-RapidAPI-Key: e9161004edmshb6f5018f435910ep1d19f5jsnc898be80e850"
    })
    Call<CovidHistory> covidHistory(@Query("country") String country, @Query("day") String day);
}
