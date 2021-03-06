package com.example.appmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Activity.ListCategorybyThemeActivity;
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
    //tạo view cho mỗi item
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.line_themes,parent,false);
        return new ListThemeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Theme theme=listTheme.get(position);
        Picasso.with(context).load(theme.getImageTheme()).into(holder.imgTheme);
    }

    @Override
    public int getItemCount() {
        return listTheme.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTheme;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTheme=itemView.findViewById(R.id.imageviewThemes);
            imgTheme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, ListCategorybyThemeActivity.class);
                    intent.putExtra("Theme",listTheme.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
