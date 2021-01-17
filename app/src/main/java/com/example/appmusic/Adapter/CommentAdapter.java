package com.example.appmusic.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Model.Comment;
import com.example.appmusic.R;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    Context context;
    ArrayList<Comment> comments;

    public CommentAdapter(Context context, ArrayList<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.line_comment,parent,false);

        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment cmt = comments.get(position);
        holder.txtNameUser.setText(cmt.getNameUser());
        holder.txtContentCmt.setText(cmt.getContentComment());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder{

        TextView txtNameUser, txtContentCmt;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNameUser =itemView.findViewById(R.id.tvNameUser);
            txtContentCmt =itemView.findViewById(R.id.tvContentCmt);

//            //bắt sự kiện chuyển qua phần list comment
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, PlayMusicActivity.class);
//                    intent.putExtra("album", comments.get(getPosition()));
//                    context.startActivity(intent);
//                }
//            });
        }
    }
}
