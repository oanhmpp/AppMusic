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

import com.example.appmusic.Model.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListCategoryByThemeAdapter
//        extends RecyclerView.Adapter<ListCategoryByThemeAdapter.ViewHolder>
{
    Context context;
    ArrayList<Category> listTheLoai;
//
//    public ListCategoryByThemeAdapter(Context context, ArrayList<Category> listTheLoai) {
//        this.context = context;
//        this.listTheLoai = listTheLoai;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.line_danh_sach_the_loai_theo_chu_de, parent, false);
//        return new DanhSachTheLoaiTheoChuDeAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        // gán data cho mỗi item
//        TheLoai theLoai = listTheLoai.get(position);
//        Picasso.get().load(theLoai.getHinhTheLoai()).into(holder.imgTheLoaiTheoChuDe);
//        holder.txtNameTheLoaiTheoChuDe.setText(theLoai.getTenTheLoai());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return listTheLoai.size();
//    }
//
//    // View Holder để tăng tốc việc ánh xạ lại cho View
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView txtNameTheLoaiTheoChuDe, txtAuthorTheLoaiTheoChuDe;
//        ImageView imgTheLoaiTheoChuDe;
//
//        public ViewHolder(@NonNull final View itemView) {
//            super(itemView);
//            txtNameTheLoaiTheoChuDe = itemView.findViewById(R.id.txtNameTheLoaiTheoChuDe);
//            txtAuthorTheLoaiTheoChuDe = itemView.findViewById(R.id.txtAuthorTheLoaiTheoChuDe);
//            imgTheLoaiTheoChuDe = itemView.findViewById(R.id.imgTheLoaiTheoChuDe);
//
//            // sự kiện khi nhấn vào hình từng ietm trong Tất cả các chủ đề (phần mở rộng bên trong)
//            // Nó sẽ chuyển qua màn hình các thể loại tương ứng với chủ đề đó
//            imgTheLoaiTheoChuDe.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // gửi data và chuyển qua màn hình danh sách các bài hát
//                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
//                    intent.putExtra("idtheloai", listTheLoai.get(getPosition()));// key put vào trùng với key playlist
//                    context.startActivity(intent);
//                }
//            });
//        }
//    }
}
