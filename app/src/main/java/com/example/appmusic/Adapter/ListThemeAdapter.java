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

import com.example.appmusic.Model.Theme;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListThemeAdapter
//        extends RecyclerView.Adapter<ListThemeAdapter.ViewHolder>
{
    Context context;
    ArrayList<Theme> listTheme;
//
//    public ListThemeAdapter(Context context, ArrayList<Theme> listChuDe) {
//        this.context = context;
//        this.listTheme = listTheme;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.line_danh_sach_all_chu_de, parent, false);
//        return new ListThemeAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        // gán data cho mỗi item
//        Theme theme = listTheme.get(position);
//        Picasso.get().load(theme.getImageTheme()).into(holder.imgAllChuDe);
//        holder.txtNameAllChuDe.setText(theme.getNameTheme());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return listTheme.size();
//    }
//
//    // View Holder để tăng tốc việc ánh xạ lại cho View
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView txtNameAllTheme, txtAuthorAllTheme;
//        ImageView imgAllTheme;
//
//        public ViewHolder(@NonNull final View itemView) {
//            super(itemView);
//            txtNameAllTheme = itemView.findViewById(R.id.txtNameAllTheme);
//            txtAuthorAllTheme = itemView.findViewById(R.id.txtAuthorAllTheme);
//            imgAllTheme = itemView.findViewById(R.id.imgAllTheme);
//
//            // sự kiện khi nhấn vào hình từng ietm trong Tất cả các chủ đề (phần mở rộng bên trong)
//            // Nó sẽ chuyển qua màn hình các thể loại tương ứng với chủ đề đó
//            imgAllTheme.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // gửi data và chuyển qua màn hình danh sách các bài hát
//                    Intent intent = new Intent(context, DanhSachTheLoaiTheoChuDeActivity.class);
//                    intent.putExtra("chu_de", listChuDe.get(getPosition()));// key put vào trùng với key playlist
//                    context.startActivity(intent);
//                }
//            });
//        }
//    }
}
