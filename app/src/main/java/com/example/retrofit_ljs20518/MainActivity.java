package com.example.retrofit_ljs20518;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.retrofit_ljs20518.Data.Data;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressbar);
        getArticleData();
    }

    private void getArticleData() {


        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://conduit.productionready.io/")
                .addConverterFactory(GsonConverterFactory.create()).client(client)
                .build();

        GetArticle article = retrofit.create(GetArticle.class);

        Log.d(TAG, "getArticleData: article" + article+retrofit);

        Call<Data> articleCall = article.getArticle();

        Log.d(TAG, "getArticleData: articleCall"+articleCall);


        articleCall.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data data = response.body();

                Log.d(TAG, "onResponse: data.articles :"+data.articles);
                recyclerView.setAdapter(new ArticleAdapter(data.articles, MainActivity.this));

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(MainActivity.this, "불러오기에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: 데이터를 불러오는데에 실패하였습니다."+t);
            }
        });

    }
}