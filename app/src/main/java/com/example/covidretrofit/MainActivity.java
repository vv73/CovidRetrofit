package com.example.covidretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.covidretrofit.history.CovidHistory;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://covid-193.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CovidInfoService service = retrofit.create(CovidInfoService.class);

        Call<CovidHistory> call = service.covidHistory( "usa", "2020-06-02");

        String[] countries = {"usa", "canada","russia", "macao"};
        for(int i = 0; i < countries.length; i++) {
            call = service.covidHistory( countries[i], "2020-06-02");
            call.enqueue(new Callback<CovidHistory>() {
                @Override
                public void onResponse(Call<CovidHistory> call, Response<CovidHistory> response) {
                    CovidHistory covidHistory = response.body();
                    if (covidHistory != null) {
                        List<com.example.covidretrofit.history.Response> r =  covidHistory.getResponse();
                        if (r.size() > 0)
                            Log.d("Covid", r.get(0).getCountry() + r.get(0).getDeaths().getTotal()+"");
                    }
                }

                @Override
                public void onFailure(Call<CovidHistory> call, Throwable t) {

                }
            });
        }
    }
}