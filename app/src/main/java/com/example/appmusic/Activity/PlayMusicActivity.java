package com.example.appmusic.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.appmusic.Adapter.ViewPagerPlayListAdapter;
import com.example.appmusic.Fragment.Fragment_Music_Disk;
import com.example.appmusic.Fragment.Fragment_Play_List_Songs;
import com.example.appmusic.Model.Song;
import com.example.appmusic.R;

import java.util.ArrayList;

public class PlayMusicActivity extends AppCompatActivity {
    Toolbar toolbarPlayMusic;
    TextView txtTimeSong,txtTimeTotal;
    SeekBar seekBarSong ;
    ImageButton imgButtonShuff,imgButtonPre,imgButtonPlay,imgButtonNext,imgButtonRepeat,imgButtonLove,imgButtonList;
    ViewPager viewPagerPlayMusic;
    public static ArrayList<Song> arrSong = new ArrayList<>();
    public static ViewPagerPlayListAdapter adapterMusic;
    Fragment_Music_Disk fragment_music_disk;
    Fragment_Play_List_Songs fragment_play_list_songs;
    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        init();
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        arrSong.clear();
        if(intent != null){
            if(intent.hasExtra("playmusic")){
                Song song = intent.getParcelableExtra("playmusic");
//            Toast.makeText(this,song.getNameSong(), Toast.LENGTH_LONG).show();
                arrSong.add(song);
            }
            if(intent.hasExtra("songs")){
                ArrayList<Song> arraySong = intent.getParcelableArrayListExtra("songs");
//            for (int i = 0 ; i < arrSong.size();i++){
//                Log.d("BBB",arrSong.get(i).getNameSong());
//            }
                arrSong = arraySong;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init() {
        toolbarPlayMusic = (Toolbar) findViewById(R.id.toolbarPlayMusic);
        txtTimeSong = (TextView) findViewById(R.id.txtTimeSong);
        txtTimeTotal = (TextView) findViewById(R.id.txtTimeTotal);
        seekBarSong = (SeekBar) findViewById(R.id.seekBarSong);
        imgButtonShuff = (ImageButton) findViewById(R.id.imgButtonShuff);
        imgButtonPre = (ImageButton) findViewById(R.id.imgButtonPre);
        imgButtonPlay = (ImageButton) findViewById(R.id.imgButtonPlay);
        imgButtonNext = (ImageButton) findViewById(R.id.imgButtonNext);
        imgButtonRepeat = (ImageButton) findViewById(R.id.imgButtonRepeat);
        viewPagerPlayMusic = (ViewPager) findViewById(R.id.viewpagerMusic);
        //imgDisk = (ImageView) findViewById(R.id.imgDisk);


        // vì đã xoá action bar nên thay thế bằng SupportAction
        setSupportActionBar(toolbarPlayMusic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlayMusic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlayMusicActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
//                finish();
//                mediaPlayer.stop();
//                listSong.clear();

            }
        });
        toolbarPlayMusic.setTitleTextColor(Color.WHITE);
        // gắn ViewPager
        adapterMusic = new ViewPagerPlayListAdapter(getSupportFragmentManager());
        fragment_music_disk = new Fragment_Music_Disk(); // new ra Fragment đĩa nhạc
        fragment_play_list_songs = new Fragment_Play_List_Songs(); // new ra Fragment danh sách bài hát
//        fragment_loi_bai_hat = new Fragment_Loi_Bai_Hat();
        // thêm vào View Pager (View Pager để chuyển qua lại các màn hình)

        // Fragment danh sách sẽ nằm bên trái Fragment đĩa nhạc
        adapterMusic.addFragment(fragment_play_list_songs);   //VỊ TRÍ 0
        adapterMusic.addFragment(fragment_music_disk);                     //VỊ TRÍ 1
//        adapteadapterMusicrNhac.addFragment(fragment_loi_bai_hat);              //VỊ TRÍ 2
        viewPagerPlayMusic.setAdapter(adapterMusic);
        viewPagerPlayMusic.setCurrentItem(1);

        fragment_play_list_songs = (Fragment_Play_List_Songs) adapterMusic.getItem(0);
        fragment_music_disk = (Fragment_Music_Disk) adapterMusic.getItem(1);
        viewPagerPlayMusic.setAdapter(adapterMusic);
//        fragment_loi_bai_hat = (Fragment_Loi_Bai_Hat) adapterMusic.getItem(2);
        // kiểm tra danh sách bài hát mà có dữ liệu thì sẽ play ca khúc đầu tiên
//        if (listSong.size() > 0) {
//            // set lại title bài hát đó
//            getSupportActionBar().setTitle(arrSong.get(pos).getTenBaiHat());
//            new PlayMP3().execute(listSong.get(position).getLinkBaiHat());
//            // sau khi bài hát đã play thì set lại icon thành đang play nhạc
//            imgPlay.setImageResource(R.drawable.iconpause1);
//
//        }
    }
}