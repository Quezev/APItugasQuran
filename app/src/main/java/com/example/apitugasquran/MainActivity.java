package com.example.apitugasquran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.apitugasquran.Audiomodel.Audio;
import com.example.apitugasquran.Audiomodel.AudioFilesItem;
import com.example.apitugasquran.retrofit.ApiService;
import com.example.apitugasquran.surahmodel.Chapters;
import com.example.apitugasquran.surahmodel.ChaptersItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AdapterMain adapter;
    private RecyclerView recyclerView;
    private List<ChaptersItem> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataFromApi();
        setUpView();
        setUpRecycleview();
    }

    private void setUpRecycleview() {
        adapter = new AdapterMain(results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recycle);
    }

    private void getDataFromApi(){
        ApiService.endpoint().getSurah().enqueue(new Callback<Chapters>() {
            @Override
            public void onResponse(Call<Chapters> call, Response<Chapters> response) {
                if (response.isSuccessful()){
                    List<ChaptersItem> results = response.body().getChapters();
                    Log.d("Main", results.toString());
                    adapter.setData(results);
                }
            }

            @Override
            public void onFailure(Call<Chapters> call, Throwable t) {
                Log.d("ErrorMain", toString());

            }
        });
    }
}