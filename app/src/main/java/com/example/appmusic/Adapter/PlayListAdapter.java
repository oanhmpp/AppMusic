package com.example.appmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appmusic.Model.PlayList;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlayListAdapter extends ArrayAdapter<PlayList> {

    public PlayListAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
        super(context, resource, objects);
    }

    // class view holder để tăng tóc độ ánh xạ các component
    class ViewHolder {
        TextView txtNamePlayList;
        ImageView imgBgPlayList, imgPlayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView =  layoutInflater.inflate(R.layout.line_playlist,null);
            viewHolder = new ViewHolder();
            // anh xa
            viewHolder.txtNamePlayList = convertView.findViewById(R.id.txtNamePlayList);
            viewHolder.imgPlayList = convertView.findViewById(R.id.imgPlayList);
            viewHolder.imgBgPlayList = convertView.findViewById(R.id.imgBackgroundPlayList);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PlayList playList = getItem(position);
        Picasso.with(getContext()).load(playList.getBackground()).into(viewHolder.imgBgPlayList);
        Picasso.with(getContext()).load(playList.getImageIcon()).into(viewHolder.imgPlayList);
        viewHolder.txtNamePlayList.setText(playList.getNamePlayList());

        return convertView;
    }
}
// giu lai nhung gia tri anh xa
