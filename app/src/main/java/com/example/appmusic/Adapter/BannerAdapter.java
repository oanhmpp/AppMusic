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
import androidx.viewpager.widget.PagerAdapter;

import com.example.appmusic.Activity.ListSongActivity;
import com.example.appmusic.Model.Advertisement;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter  extends PagerAdapter {
    Context context;
    ArrayList<Advertisement> arrayListBanner; //mãng quảng cáo

    public BannerAdapter(Context context, ArrayList<Advertisement> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }
//trả về view tùy theo object định hình
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    //định hình object (mỗi object tương trưng cho mỗi page
    //Nhìn view mẫu sẽ đi thiết kế cho những page còn lại
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.line_banner,null);
        //ánh xạ view trong phần banner vào
        ImageView imageViewBackgroundBanner=view.findViewById(R.id.imageViewBackgroundBanner);// backgỏ
        ImageView imageSongBanner=view.findViewById(R.id.imageViewBanner);
        TextView txtTitleSongBanner=view.findViewById(R.id.textviewTitleSongBanner);
        TextView txtContent=view.findViewById(R.id.textviewindex);
        //gắn giá trị
        Picasso.with(context).load(arrayListBanner.get(position).getImageAdver()).into(imageViewBackgroundBanner);
        Picasso.with(context).load(arrayListBanner.get(position).getImageSong()).into(imageSongBanner);
        txtTitleSongBanner.setText(arrayListBanner.get(position).getNameSong());
        txtContent.setText(arrayListBanner.get(position).getContentAdver());

        // sự kiện khi nhấn vào page trên quảng cáo
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Bạn đã click", Toast.LENGTH_LONG).show();
                // chuyển và gửi dữ liệu qua màn hình danh sách các bài hát
                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra("banner", arrayListBanner.get(position));
                context.startActivity(intent);
            }
        });

        //add vào viewpager
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
