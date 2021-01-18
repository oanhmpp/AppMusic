package com.example.appmusic.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Activity.ListSongActivity;

import com.example.appmusic.Activity.PlayMusicActivity;
import com.example.appmusic.Model.Song;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIServer;
import com.example.appmusic.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchSongAdapter extends RecyclerView.Adapter<SearchSongAdapter.ViewHolder> {

    Context context;
    ArrayList<Song> arrListSong;

public SearchSongAdapter (Context context,ArrayList<Song> arrListSong){
    this.context=context;
    this.arrListSong=arrListSong;
}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.line_search_song,parent,false);
    return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Song song = arrListSong.get(position);
    holder.txtNameSong.setText(song.getNameSong());
    holder.txtSingle.setText(song.getSinger());
        Picasso.with(context).load(song.getImageSong()).into(holder.imgSong);

    }

    @Override
    public int getItemCount() {
        return arrListSong.size();
    }

    // class view holder để tăng tóc độ ánh xạ các component
    public class ViewHolder extends RecyclerView.ViewHolder{
    TextView txtNameSong,txtSingle;
    ImageView imgSong,imgLikes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameSong =itemView.findViewById(R.id.textviewsearchNameSong);
            txtSingle= itemView.findViewById(R.id.textviewsearchNameSingle);

            imgSong= itemView.findViewById(R.id.imageviewSearchSong);
            imgLikes =itemView.findViewById(R.id.imageViewLikes);


//imgLikes.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        // khi click vào thì sẽ đổi thành trái tim đỏ
//        imgLikes.setImageResource(R.drawable.iconloved);
//        // tương tác lên Server update lại lượt thích
//        DataService dataService = APIServer.getService();
//        // chỉ update 1 lần 1 lượt thích, update theo id bài hát
//        Call<String> callback = dataService.UpdateLikes("1",arrListSong.get(getPosition()).getNameSong());
//        // gửi lên Server
//        callback.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
////                String ketqua= response.body();
////                if(ketqua.equals("success")){
////                    Toast.makeText(context,"Like",Toast.LENGTH_SHORT).show();
////
////                }else{
////                    Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
////                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
////                Toast.makeText(context, "Loved", Toast.LENGTH_LONG).show();
//            }
//        });
//        // sau khi đã thích thì set  lại disable
//        imgLikes.setEnabled(false);
//    }
//
//});
// xử lí khi nhấn vào từng item bài hát
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // chuyển dữ liệu qua màn hình play nhạc
                    Intent intent= new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("playmusic",arrListSong.get(getPosition()));
                    context.startActivity(intent);


                }
            });
        }


    }
}
