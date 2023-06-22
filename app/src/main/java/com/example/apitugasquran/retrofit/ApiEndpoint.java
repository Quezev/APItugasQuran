package com.example.apitugasquran.retrofit;

import com.example.apitugasquran.Audiomodel.Audio;
import com.example.apitugasquran.surahmodel.Chapters;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("chapters/?language=id")
    Call<Chapters> getSurah();

    @GET("chapter_recitations/33?language=id")
    Call<Audio> getAudio();
}
