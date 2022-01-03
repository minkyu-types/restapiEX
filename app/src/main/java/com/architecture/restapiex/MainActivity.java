package com.architecture.restapiex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.architecture.restapiex.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    public String email, password;

    private RecyclerView.Adapter mainAdapter;
    private RetrofitClient retrofitClient;
    private RetrofitInterface retrofitInterface;
    private String API_KEY = "eac7bab89627b0067e6a69a668b740bc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view_main = activityMainBinding.getRoot();
        setContentView(view_main);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        activityMainBinding.recyclerView.setLayoutManager(linearLayoutManager);

        retrofitInterface.getBoxOffice(API_KEY, "20220101").enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                BoxOfficeResult boxOfficeResult = result.getBoxOfficeResult();
                Log.d("retrofit", "Data fetch success");

                mainAdapter = new MovieAdapter(boxOfficeResult.getDailyBoxOfficeList());
                activityMainBinding.recyclerView.setAdapter(mainAdapter);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
}