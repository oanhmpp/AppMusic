package com.example.appmusic.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.appmusic.Adapter.BannerAdapter;
import com.example.appmusic.Model.Advertisement;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIServer;
import com.example.appmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBanner extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner,container,false);
        mapping();
        getData();
        return view;
    }

    private void mapping() {
        viewPager=view.findViewById(R.id.viewPager);
        circleIndicator=view.findViewById(R.id.indicatorDefault);
    }

    public void getData(){
        DataService dataService= APIServer.getService();
        Call<List<Advertisement>> callback =dataService.getDataBanner();
        callback.enqueue(new Callback<List<Advertisement>>() {
            @Override
            public void onResponse(Call<List<Advertisement>> call, Response<List<Advertisement>> response) {
                ArrayList<Advertisement> banners= (ArrayList<Advertisement>) response.body();
//                Log.d("BBB",banners.get(0).getIDAdver());
                bannerAdapter=new BannerAdapter(getActivity(),banners);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);

                handler=new Handler();
                runnable=new Runnable() {
                    @Override
                    public void run() {
                        //kiểm tra item hiện tại
                        currentItem=viewPager.getCurrentItem();
                        //tăng vị trí đang đứng
                        currentItem++;
                        //vượt kích thước thì trở về page đầu tiên
                        if(currentItem>=viewPager.getAdapter().getCount()){
                            currentItem=0;
                        }
                        //mỗi lần chạy xong set dữ liệu lên
                        viewPager.setCurrentItem(currentItem,true);
                        //thời gian chờ 4 giây
                        handler.postDelayed(runnable,4000);

                    }
                };
                handler.postDelayed(runnable,4000);

            }

            @Override
            public void onFailure(Call<List<Advertisement>> call, Throwable t) {

            }
        });
    }
}
