package com.example.apitugasquran;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.apitugasquran.Audiomodel.Audio;
import com.example.apitugasquran.Audiomodel.AudioFilesItem;
import com.example.apitugasquran.retrofit.ApiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    TextView Idsurah;
    Button button;
    private MediaPlayer mediaPlayer;
    private AdapterAudio adapterAudio;
    private List<Audio> suara = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getDataFromApi();

        int id = getIntent().getIntExtra("id",1);
        Idsurah = findViewById(R.id.idsur);
        Idsurah.setText("Surah Ke: "+(id));

        int urutan = getIntent().getIntExtra("urutan",1);
        Idsurah = findViewById(R.id.urutansur);
        Idsurah.setText("Urutan turunnya surah: "+(urutan));

        int jumlahay = getIntent().getIntExtra("jumlahay",1);
        Idsurah = findViewById(R.id.jumay);
        Idsurah.setText("Jumlah Ayat: "+(jumlahay));

        String namasurah = getIntent().getExtras().getString("namasurah");
        Idsurah = findViewById(R.id.namasur);
        Idsurah.setText("Nama Surah: "+(namasurah));

        String tempat = getIntent().getExtras().getString("tempat");
        Idsurah = findViewById(R.id.tempsur);
        Idsurah.setText("Tempat turunnya Surah: "+(tempat));

        String namaarab = getIntent().getExtras().getString("namaarab");
        Idsurah = findViewById(R.id.namaar);
        Idsurah.setText("("+(namaarab)+")");

        String artisur = getIntent().getExtras().getString("artisur");
        Idsurah = findViewById(R.id.artisurat);
        Idsurah.setText("Terjemahan Surah: "+(artisur));

        button = findViewById(R.id.buttonplay);
        button.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()){
                pauseAudio();
            }else {
                playAudio(String.valueOf(suara));
            }
        });

    }

    private void playAudio(String play) {
        try{
            mediaPlayer.reset();
            mediaPlayer.setDataSource(play);
            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (IOException r){
            r.printStackTrace();
        }
    }

    private void pauseAudio() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    private void getDataFromApi() {
        ApiService.endpoint().getAudio().enqueue(new Callback<Audio>() {
            @Override
            public void onResponse(Call<Audio> call, Response<Audio> response) {
                if (response.isSuccessful()){
                    List<AudioFilesItem> suara =response.body().getAudioFiles();
                    Log.d("Audio",suara.toString());
                    adapterAudio.setData(suara);


                }
            }

            @Override
            public void onFailure(Call<Audio> call, Throwable t) {
                Log.d("ErrorMainau", toString());
            }


        });
    }
}