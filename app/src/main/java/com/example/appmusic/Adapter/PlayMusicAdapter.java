package com.example.appmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Model.Song;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlayMusicAdapter extends RecyclerView.Adapter<PlayMusicAdapter.ViewHolder>{
    ViewHolder viewHolder;
    Context context;
    ArrayList<Song> arrSong ;
    View view;
    public PlayMusicAdapter(Context context, ArrayList<Song> arrSong) {
        this.context = context;
        this.arrSong = arrSong;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.line_play_music,parent,false);
        return new ViewHolder(view);
    }

     @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Song song = arrSong.get(position);
    viewHolder = new ViewHolder(view);
    holder.txtSinger.setText(song.getSinger());
    holder.txtIndex.setText(position+1+"");
    holder.txtNameSong.setText(song.getNameSong());
    Picasso.with(context).load(song.getImageSong()).into(viewHolder.imgSongPlayMusic);
    }

    @Override
    public int getItemCount() {
        return arrSong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtIndex,txtNameSong, txtSinger;
        ImageView imgSongPlayMusic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIndex = itemView.findViewById(R.id.txtIndexPlayMusic);
            txtNameSong = itemView.findViewById(R.id.txtNamePlayMusic);
            txtSinger = itemView.findViewById(R.id.txtAuthorPlayMusic);
            imgSongPlayMusic = itemView.findViewById(R.id.imgSongPlayMusic);
        }
    }
}
