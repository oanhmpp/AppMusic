package com.example.appmusic.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.appmusic.Adapter.ViewPagerPlayListAdapter;
import com.example.appmusic.Fragment.Fragment_Music_Disk;
import com.example.appmusic.Fragment.Fragment_Play_List_Songs;
import com.example.appmusic.Model.Song;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayMusicActivity extends AppCompatActivity {
    Toolbar toolbarPlayMusic;
    TextView txtTimeSong, txtTimeTotal, txtNameSong, txtNameSinger;
    SeekBar seekBarSong;
    ImageButton imgButtonShuff, imgButtonPre, imgButtonPlay, imgButtonNext, imgButtonRepeat, imgButtonLove, imgButtonList;
    ViewPager viewPagerPlayMusic;
    public static ArrayList<Song> arrSong = new ArrayList<>();
    public static ViewPagerPlayListAdapter adapterMusic;
    Fragment_Music_Disk fragment_music_disk;
    Fragment_Play_List_Songs fragment_play_list_songs;
    MediaPlayer mediaPlayer;
    int position = 0;
    int old_position ;
    boolean repeat  = false;
    boolean checkRandom = false;
    boolean next = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        // kiểm tra tín hiệu mạng
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        getDataFromIntent();    //PHẢI LẤY DỮ LIỆU TRƯỚC KHI addControls() THÌ MỚI CÓ BÀI HÁT ĐỂ PHÁT
//        CreateNotification.createNotification(getApplicationContext(), arrSong.get(position),
//                R.drawable.ic_pause_black_24dp, position, listSong.size() - 1);
//        isPlaying = true;
////        unregisterReceiver(broadcastReceiver);
//        if (mediaPlayer != null) {
//            Log.d("fff", "aaaaaaaaa");
//            mediaPlayer.stop();
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
        init();
        eventClick();
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapterMusic.getItem(position) != null) {
                    if (arrSong.size() > 0) {
                        fragment_music_disk.playMusic(arrSong.get(position).getImageSong());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }

        }, 500);
        imgButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgButtonPlay.setImageResource(R.drawable.iconplay);
                }else{
                    mediaPlayer.start();
                    imgButtonPlay.setImageResource(R.drawable.iconpause);
                }
            }
        });

        // tạo sự kiện cho nút lặp lại
        imgButtonRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( repeat = false){
                    if(checkRandom = true){
                        checkRandom = false;
                        imgButtonRepeat.setImageResource(R.drawable.iconsyned);
                        imgButtonShuff.setImageResource(R.drawable.iconshuffled);
                    }
                    imgButtonRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }else {
                    imgButtonRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });

        // tạo sự kiện cho nút random bài hát
        imgButtonShuff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( checkRandom = false){
                    if(repeat = true){
                        repeat = false;
                        imgButtonShuff.setImageResource(R.drawable.iconshuffled);
                        imgButtonRepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgButtonRepeat.setImageResource(R.drawable.iconshuffled);
                    checkRandom = true;
                }else {
                    imgButtonShuff.setImageResource(R.drawable.iconsuffle);
                    repeat = false;
                }
            }
        });

        // sự kiện cho nút kéo thời gian phát nhạc
        seekBarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // test được
        // tạo sự kiện cho nút next
        imgButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                old_position = position;
                // kiểm tra nếu có dữ liệu
                if (arrSong.size() > 0) {
                    if (mediaPlayer.isPlaying() && mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(position < (arrSong.size())){
                        imgButtonPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat) {
                            position = position;
                        } else {
                            position++;
                            if (position == arrSong.size()) {
                                position = 0;
                            }
                            if (checkRandom) {
                                Random random = new Random();
                                int viTriRandom = random.nextInt(arrSong.size());
                                position = viTriRandom;
                            }
                        }
                    }

                    new Playmp3().execute(arrSong.get(position).getLinkSong());
                    imgButtonPlay.setImageResource(R.drawable.iconpause);
                    fragment_music_disk.playMusic(arrSong.get(position).getImageSong());
                    fragment_music_disk.txtNameSinger.setText(arrSong.get(position).getSinger());
                    fragment_music_disk.txtNameSong.setText(arrSong.get(position).getNameSong());
                    getSupportActionBar().setTitle(arrSong.get(position).getNameSong());
//                    updateTime();
//                    if (fragment_music_disk.objectAnimator != null) {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                            fragment_music_disk.objectAnimator.resume();
//                        }
//                    }
//                    CreateNotification.createNotification(PlayMusicActivity.this, arrSong.get(position),
//                            R.drawable.ic_pause_black_24dp, position, arrSong.size() - 1);
//                    isPlaying = true;
                }
                imgButtonPre.setClickable(false);
                imgButtonNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgButtonPre.setClickable(true);
                        imgButtonNext.setClickable(true);
                    }
                }, 5000);
            }
        });

        //test được
        // sự kiện cho nút nghe bài trước
        imgButtonPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                old_position = position;
                if (arrSong.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (repeat) {
                        position = position;
                    } else {
                        position--;
                        if (position == -1) {
                            position = arrSong.size() - 1;
                        }
                        if (checkRandom) {
                            Random random = new Random();
                            int viTriRandom = random.nextInt(arrSong.size());
                            position = viTriRandom;
                        }
                    }
                    new Playmp3().execute(arrSong.get(position).getLinkSong());
                    imgButtonPlay.setImageResource(R.drawable.iconpause);
                    fragment_music_disk.playMusic(arrSong.get(position).getImageSong());
                    fragment_music_disk.txtNameSinger.setText(arrSong.get(position).getSinger());
                    fragment_music_disk.txtNameSong.setText(arrSong.get(position).getNameSong());
//                    updateTime();
//                    if (fragment_disk.objectAnimator != null) {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                            fragment_disk.objectAnimator.resume();
//                        }
//                    }
//                    CreateNotification.createNotification(PlayNhacActivity.this, arrSong.get(position),
//                            R.drawable.ic_pause_black_24dp, position, arrSong.size() - 1);
//                    isPlaying = true;

                }
                imgButtonPre.setClickable(false);
                imgButtonNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgButtonPre.setClickable(true);
                        imgButtonNext.setClickable(true);
                    }
                }, 5000);
            }
        });
    }
    private void getDataFromIntent () {
        Intent intent = getIntent();
        arrSong.clear();
        if (intent != null) {
            if (intent.hasExtra("playmusic")) {
                Song song = intent.getParcelableExtra("playmusic");
//            Toast.makeText(this,song.getNameSong(), Toast.LENGTH_LONG).show();
                arrSong.add(song);
            }
            if (intent.hasExtra("songs")) {
                ArrayList<Song> arraySong = intent.getParcelableArrayListExtra("songs");
//            for (int i = 0 ; i < arrSong.size();i++){
//                Log.d("BBB",arrSong.get(i).getNameSong());
//            }
                arrSong = arraySong;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init () {
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
                finish();
                mediaPlayer.stop();
                arrSong.clear();

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
        fragment_music_disk = (Fragment_Music_Disk) adapterMusic.getItem(1);
        if (arrSong.size() > 0) {
            // set lại title bài hát đó
            getSupportActionBar().setTitle(arrSong.get(0).getNameSong());
            new Playmp3().execute(arrSong.get(0).getLinkSong());
            // sau khi bài hát đã play thì set lại icon thành đang play nhạc
            imgButtonPlay.setImageResource(R.drawable.iconpause);

        }
    }

    class Playmp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String song) {
            super.onPostExecute(song);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(song);
                //goi ham prepare de phat
                mediaPlayer.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            timeSong();
        }
    }

    private void timeSong () {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarSong.setMax(mediaPlayer.getDuration());
    }

}