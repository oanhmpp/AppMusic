package com.example.appmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Model.Theme;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListThemeAdapter extends RecyclerView.Adapter<ListThemeAdapter.ViewHolder> {
    Context context;
    ArrayList<Theme> listTheme;

    public ListThemeAdapter(Context context, ArrayList<Theme> listTheme) {
        this.context = context;
        this.listTheme = listTheme;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.line_theme_category_today,parent,false);
        return new ListThemeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Theme theme=listTheme.get(position);
//        Picasso.with(context).load(theme.getImageTheme()).into(holder.i);
    }

    @Override
    public int getItemCount() {
        return listTheme.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
