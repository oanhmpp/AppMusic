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
import com.example.appmusic.Model.Category;
import com.example.appmusic.Model.Category_Theme;
import com.example.appmusic.Model.Theme;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListCategoryByThemeAdapter extends RecyclerView.Adapter<ListCategoryByThemeAdapter.ViewHolder>
//        extends RecyclerView.Adapter<ListCategoryByThemeAdapter.ViewHolder>
{
    Context context;
    ArrayList<Category> listCategory;

    public ListCategoryByThemeAdapter(Context context, ArrayList<Category> listCategory) {
        this.context = context;
        this.listCategory = listCategory;
    }

    @NonNull
    @Override
    //gắn layout vào
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //gắn layout
        View view = inflater.inflate(R.layout.line_theme_category_today, parent, false);
        //sau khi ánh xạ xog biến view sẽ gắn vào itemview
        return new ViewHolder(view);
    }

    @Override
    //thực hiện gắn giá trị
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // gán data cho mỗi item
        Category category = listCategory.get(position);
        holder.txtNameCategoryByTheme.setText(category.getNameCategory());
        Picasso.with(context).load(category.getImageCategory()).into(holder.imgCategoryByTheme);
//        Picasso.with(context).load(theLoai.getImageCategory()).into(holder.imgCategoryByTheme);

    }

    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    // View Holder để tăng tốc việc ánh xạ lại cho View
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameCategoryByTheme, txtAuthorCategoryByTheme;
        ImageView imgCategoryByTheme;

        public ViewHolder( View itemView) {
            super(itemView);
            imgCategoryByTheme = itemView.findViewById(R.id.imgThemeCategory);
            txtNameCategoryByTheme = itemView.findViewById(R.id.txtNameCategoryTheme);
//            txtAuthorCategoryByTheme = itemView.findViewById(R.id.txtAuthorCategoryByTheme);


            // sự kiện khi nhấn vào hình từng ietm trong Tất cả các chủ đề (phần mở rộng bên trong)
            // Nó sẽ chuyển qua màn hình các thể loại tương ứng với chủ đề đó
            imgCategoryByTheme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // gửi data và chuyển qua màn hình danh sách các bài hát
                    Intent intent = new Intent(context, ListSongActivity.class);
//                    intent.putExtra("idtheloai", listCategory.get(getPosition()));// key put vào trùng với key playlist
                    context.startActivity(intent);
                }
            });
        }
    }
}
