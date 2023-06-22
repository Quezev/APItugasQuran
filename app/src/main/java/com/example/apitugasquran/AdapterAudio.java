package com.example.apitugasquran;

import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apitugasquran.Audiomodel.Audio;
import com.example.apitugasquran.Audiomodel.AudioFilesItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdapterAudio extends RecyclerView.Adapter<AdapterAudio.Adapterholder> {
    List<AudioFilesItem> suara = new ArrayList<>();
    private MediaPlayer mediaPlayer;

    public AdapterAudio (List<AudioFilesItem> suara){
        this.suara = suara;
        mediaPlayer = new MediaPlayer();
    }

    @NonNull
    @Override
    public AdapterAudio.Adapterholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main2, parent,false);
        return new Adapterholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAudio.Adapterholder holder, int position) {
        AudioFilesItem audio = suara.get(position);
    }

    private void pauseAudio() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    @Override
    public int getItemCount() {
        return suara.size();
    }

    public void setData(List<AudioFilesItem> suara){
        suara.clear();
        suara.addAll(suara);
        notifyDataSetChanged();
    }

    public class Adapterholder extends RecyclerView.ViewHolder {
        Button button;
        public Adapterholder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.buttonplay);

        }
    }
}
