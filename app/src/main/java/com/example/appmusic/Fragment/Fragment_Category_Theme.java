package com.example.appmusic.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.appmusic.Model.Category;
import com.example.appmusic.Model.Category_Theme;
import com.example.appmusic.Model.Theme;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIServer;
import com.example.appmusic.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Category_Theme extends Fragment {

    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtViewMore; // nút xem thêm cho chủ đề và thể loại


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_theme_category_today, container, false);
//        // ánh xạ view
        horizontalScrollView = view.findViewById(R.id.horizontal_scollview);
        txtViewMore = view.findViewById(R.id.txtViewMoreThemeCategory);
//        // sự kiện khi nhấn xem thêm ở chủ đề và thể loại
//        // Load tất cả các chủ đề trong db lên
//        txtViewMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // chuyển qua màn hình DanhSachChuDeActivity
//                Intent intent = new Intent(getActivity(), DanhSachChuDeActivity.class);
//                //intent.putParcelableArrayListExtra("listchude", lis)
//                startActivity(intent);
//            }
//        });
        getData();
        return view;
    }

//
   // hàm lấy data chủ đề và thể loại
    private void getData() {
        DataService dataService = APIServer.getService();
        Call<Category_Theme> callBack = dataService.getCategoryMusic();
        callBack.enqueue(new Callback<Category_Theme>() {
            @Override
            public void onResponse(Call<Category_Theme> call, Response<Category_Theme> response) {
                Category_Theme category_theme = response.body();
                Log.d("BBB",category_theme.getTheme().get(1).getImageTheme());
                //lấy dữ liệu của chủ đề
                final ArrayList<Theme> themesArrayList = new ArrayList<>();
                themesArrayList.addAll(category_theme.getTheme());
                //lấy dữ liệu của thể loại
                final ArrayList<Category> categoriesArrayList = new ArrayList<>();
                categoriesArrayList.addAll(category_theme.getCategory());

                //tạo viewgroup tồi đưa vào horizontal sau
                //đang đứng ở fragment nên lấy activity
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                //gán lại kích thước cho cardview
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(580, 250);
                layoutParams.setMargins(5, 10, 5, 15);

                //Phần chủ chủ đề
                //mỗi mảng có hình ảnh nên dùng for
                for (int i = 0; i < (themesArrayList.size()); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    //kiểm tra lại khác nul thì thực hiện
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
                        //gán dữ liệu online vào cho màn hình này
                        Picasso.with(getActivity()).load(categoriesArrayList.get(j).getImageCategory()).into(imageView);
                    }
                    //gọi lại để cố định cho cardview
                    cardView.setLayoutParams(layoutParams);
                    // đưa hình ảnh vào cardview
                    cardView.addView(imageView);
                    //đưa vào trong phần linear
                    linearLayout.addView(cardView);
                }
                //đưa vào horizontal
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<Category_Theme> call, Throwable t) {

            }
        });
    }
//            @Override
//            public void onResponse(Call<ChuDeVaTheLoai> call, Response<ChuDeVaTheLoai> response) {
//                ChuDeVaTheLoai chuDeVaTheLoai = response.body();
//                //Toast.makeText(getContext(), "Xin chào", Toast.LENGTH_LONG).show();
//                //Log.d("BBBBBBBBBBBBBBBBBBBB", chuDeVaTheLoai.getTheLoai().get(0).getHinhTheLoai());
//                // danh sách chủ để
//                final ArrayList<ChuDe> listChuDe = new ArrayList<>();
//                listChuDe.addAll(chuDeVaTheLoai.getChuDe());
//                // danh sách thể loại
//                final ArrayList<TheLoai> listTheLoai = new ArrayList<>();
//                listTheLoai.addAll(chuDeVaTheLoai.getTheLoai());
//
//                // khi đưa ảnh vào horizontal thì phải có view group
//                LinearLayout linearLayout = new LinearLayout(getActivity()); // lấy màn hình mà chứa activity này
//                linearLayout.setOrientation(LinearLayout.HORIZONTAL); // set nằm ngang
//
//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(480, 480);
//                layoutParams.setMargins(10, 20, 10, 30);
//
//                // duyệt list chủ đề
//                for (int i = 0; i < listChuDe.size(); i++) {
//                    CardView cardView = new CardView(getActivity());
//                    cardView.setRadius(10); // bo góc cho card view
//                    ImageView imageView = new ImageView(getActivity());
//                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                    // kiểm tra nếu nó có hình ảnh
//                    if (listChuDe.get(i).getHinhChuDe() != null) {
//                        /// gán dữ liệu hình ảnh
//                        Picasso.get().load(listChuDe.get(i).getHinhChuDe()).into(imageView);
//                    }
//                    cardView.setLayoutParams(layoutParams);
//                    cardView.addView(imageView);
//                    linearLayout.addView(cardView);
//
//                    final int position = i;
//
//                    // sự kiện khi nhấn vào từng chủ đề bên ngoài màn hình (màn hình chính)
//                    // chuyển đến màn hình DanhSachTheLoaiTheoChuDe
//                    imageView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            // sau đó gửi dữ liệu bài hát qua màn hình DanhSachBaiHat
//                            Intent intent = new Intent(getActivity(), DanhSachTheLoaiTheoChuDeActivity.class);
//                            intent.putExtra("chu_de", listChuDe.get(position));
//                            startActivity(intent);
//
//                        }
//                    });
//                }
//
//                // duyệt list thể loại
//                for (int i = 0; i < listTheLoai.size(); i++) {
//
//                    CardView cardView = new CardView(getActivity());
//                    cardView.setRadius(10); // bo góc cho card view
//                    ImageView imageView = new ImageView(getActivity());
//                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                    // kiểm tra nếu nó có hình ảnh
//                    if (listTheLoai.get(i).getHinhTheLoai() != null) {
//                        /// gán dữ liệu hình ảnh
//                        Picasso.get().load(listTheLoai.get(i).getHinhTheLoai()).into(imageView);
//                    }
//                    cardView.setLayoutParams(layoutParams);
//                    cardView.addView(imageView);
//                    linearLayout.addView(cardView);
//
//                    final int position = i;
//
//                    // sự kiện khi nhấn vào từng thể loại bên ngoài màn hình (màn hình chính)
//                    imageView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            // sau đó gửi dữ liệu bài hát qua màn hình DanhSachBaiHat
//                            Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
//                            intent.putExtra("idtheloai", listTheLoai.get(position));
//                            startActivity(intent);
//
//                        }
//                    });
//                }
//                horizontalScrollView.addView(linearLayout);
//            }
//
//            @Override
//            public void onFailure(Call<ChuDeVaTheLoai> call, Throwable t) {
//
//            }
//        });

}



