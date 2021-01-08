package com.example.appmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Activity.ListSongActivity;
import com.example.appmusic.Model.Album;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends  RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> mangalbum;
    public AlbumAdapter(Context context, ArrayList<Album> mangalbum){
        this.context =context;
        this.mangalbum=mangalbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.line_album,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
    Album album = mangalbum.get(position);
    holder.txtcasialbum.setText(album.getNameSingerAlbum());
        holder.txttenalbum.setText(album.getNameAlbum());
        Picasso.with(context).load(album.getImageAlbum()).into(holder.imghinhAlbum);
    }

    @Override
    public int getItemCount() {
        return mangalbum.size();
    }

  class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imghinhAlbum;
        TextView txttenalbum, txtcasialbum;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        txttenalbum =itemView.findViewById(R.id.textviewtenalbum);
        txtcasialbum =itemView.findViewById(R.id.textviewtencasialbum);
        imghinhAlbum =itemView.findViewById(R.id.imageviewalbum);

        //bắt sự kiện chuyển qua phần Fragment Album Hot
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra("album", mangalbum.get(getPosition()));
                context.startActivity(intent);
            }
        });

        // sự kiện khi nhấn vào RecyvleView ở phần Album
//        imghinhAlbum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // gửi data và chuyển qua màn hình danh sách các bài hát
//                Intent intent = new Intent(context, PlayListAdapter.class);
//                intent.putExtra("all_album", mangalbum.get(getPosition()));// key put vào trùng với key playlist
//                context.startActivity(intent);
//            }
//        });
    }
}
}
