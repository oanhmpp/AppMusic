package com.example.appmusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Activity.ListSongActivity;
import com.example.appmusic.Adapter.ListCategoryByThemeAdapter;
import com.example.appmusic.Model.Category;
import com.example.appmusic.Model.Category_Theme;
import com.example.appmusic.Model.Theme;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIServer;
import com.example.appmusic.Service.DataService;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Category_Theme extends Fragment {

    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtViewMore; // nút xem thêm cho chủ đề và thể loại
//    ListCategoryByThemeAdapter listCategoryByThemeAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("GGGGGGGGG","fragmnet theme category today");
        view = inflater.inflate(R.layout.fragment_theme_category_today,container,false);

        // ánh xạ view
        txtViewMore = view.findViewById(R.id.txtViewMoreThemeCategory);
        // sự kiện khi nhấn xem thêm ở chủ đề và thể loại
        horizontalScrollView =view.findViewById(R.id.horizontal_scollview);
        getData();
        return view;
    }

//
   // hàm lấy data chủ đề và thể loại
    private void getData() {
        DataService dataService = APIServer.getService();
        Call<Category_Theme> call= dataService.getCategoryMusic();
        call.enqueue(new Callback<Category_Theme>() {
            @Override
            public void onResponse(Call<Category_Theme> call, Response<Category_Theme> response) {
                Category_Theme category_theme = response.body();
//                Log.d("BBBBBBBBvBB",category_theme.getTheme().get(0).getNameTheme());// chưa ra
                //lấy dữ liệu của chủ đề
                final ArrayList<Theme>  themesArrayList = new ArrayList<>();
                themesArrayList.addAll(category_theme.getTheme());
                //lấy dữ liệu của thể loại
               final  ArrayList<Category>  categoriesArrayList = new ArrayList<>();
                categoriesArrayList.addAll(category_theme.getCategory());

                //tạo viewgroup tồi đưa vào horizontal sau
                //đang đứng ở fragment nên lấy activity
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                //gán lại kích thước cho cardview
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(480, 480);
                layoutParams.setMargins(5, 10, 5, 15);

                //Phần chủ chủ đề
                //mỗi mảng có hình ảnh nên dùng for
                for (int i = 0; i < themesArrayList.size(); i++) {
                    //truyền tham số của màn hình
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10); // bo góc cardview
                    //đưa hình ảnh vào cardview
                    ImageView imageView = new ImageView(getActivity());
                    //chỉnh lại kích thước hình ảnh
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    //kiểm tra lại khác null thì thực hiện
                    if (themesArrayList.get(i).getImageTheme() != null) {
                        //gán dữ liệu online vào cho màn hình này
                        Picasso.with(getActivity()).load(themesArrayList.get(i).getImageTheme()).into(imageView);
                    }
                    //gọi lại để cố định cho cardview
                    cardView.setLayoutParams(layoutParams);
                    // đưa hình ảnh vào cardview
                    cardView.addView(imageView);
                    //đưa vào trong phần linear
                    linearLayout.addView(cardView);
                }

                //Phần thể loại
                //mỗi mảng có hình ảnh nên dùng for
                for (int j = 0; j < (themesArrayList.size()); j++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    //kiểm tra lại khác nul thì thực hiện
                    if (categoriesArrayList.get(j).getImageCategory() != null) {
                        //gán dữ liệu hình ảnh online vào cho màn hình này
                        Picasso.with(getActivity()).load(categoriesArrayList.get(j).getImageCategory()).into(imageView);
                    }
                    //gọi lại để cố định cho cardview
                    cardView.setLayoutParams(layoutParams);
                    // đưa hình ảnh vào cardview
                    cardView.addView(imageView);
                    //đưa vào trong phần linear
                    linearLayout.addView(cardView);

                    //bắt sự kiện
                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getActivity(), ListSongActivity.class);
                            intent.putExtra("idCategory", categoriesArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                //đưa vào horizontal
                horizontalScrollView.addView(linearLayout);
            }

//            @Override
//            public void onResponse(Call<Category_Theme> call, Response<Category_Theme> response) {
//
//            }

            @Override
            public void onFailure(Call<Category_Theme> call, Throwable t) {
                Log.d("test","Lỗi fragment category theme");

            }
        });
    }


}



