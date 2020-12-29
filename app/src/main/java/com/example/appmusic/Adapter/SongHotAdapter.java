package com.example.appmusic.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Model.Song;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SongHotAdapter extends  RecyclerView.Adapter<SongHotAdapter.ViewHolder> {
    Context context;
    ArrayList<Song> arrSong;

    public SongHotAdapter(Context context, ArrayList<Song> arrSong) {
        this.context = context;
        this.arrSong = arrSong;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater  =  LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.line_song_hot,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    // gan du lieu vao
        Song song  = arrSong.get(position);
        holder.txtNameSong.setText(song.getNameSong());
        holder.txtAuthor.setText(song.getNameSong());
        Picasso.with(context).load(song.getImageSong()).into(holder.imgSong);
    }

    @Override
    public int getItemCount() {
        return arrSong.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNameSong, txtAuthor;
        ImageView imgDownload, imgLikes, imgSong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameSong = itemView.findViewById(R.id.txtNameSong);
            txtAuthor = itemView.findViewById(R.id.txtAuthorSong);
            imgDownload = itemView.findViewById(R.id.imgDownload);
            imgLikes = itemView.findViewById(R.id.imgLikes);
            imgSong  = itemView.findViewById(R.id.imgSong);
        }
    }
}
