package com.example.appmusic.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appmusic.Adapter.CommentAdapter;
import com.example.appmusic.Adapter.ViewPagerPlayListAdapter;
import com.example.appmusic.Admin.AddUserActivity;
import com.example.appmusic.Admin.Manager_User_Activity;
import com.example.appmusic.Fragment.Fragment_Music_Disk;
import com.example.appmusic.Fragment.Fragment_Play_List_Songs;
import com.example.appmusic.Model.Comment;
import com.example.appmusic.Model.Song;
import com.example.appmusic.Model.User;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIServer;
import com.example.appmusic.Service.DataService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayMusicActivity extends AppCompatActivity {
    RecyclerView recyclerViewComment;
    Toolbar toolbarPlayMusic;
    TextView txtTimeSong, txtTimeTotal;
    SeekBar seekBarSong;
    ImageButton imgButtonShuff, imgButtonPre, imgButtonPlay, imgButtonNext, imgButtonRepeat, imgButtonLove, imgButtonList;
    ViewPager viewPagerPlayMusic;
    EditText txtContentCmt;
    Button btnPost;
    String idSong;
    public static ArrayList<Song> arrSong = new ArrayList<>();
    public static ArrayList<Comment> comments = new ArrayList<>();
    public static ViewPagerPlayListAdapter adapterMusic;
    Fragment_Music_Disk fragment_music_disk;
    Fragment_Play_List_Songs fragment_play_list_songs;
    MediaPlayer mediaPlayer;
    int position = 0;
    int old_position = 0;
    boolean repeat = false;
    boolean checkRandom = false;
    boolean next = false;
    String content;
    CommentAdapter commentAdapter;
    ArrayList<User> listUser;

    String urlInsert="https://oanhnguyen1999.000webhostapp.com/Server/addCmt.php";


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
        // lay ten nguoi dung de cmt
        // lấy dữ liệu từ màn hình login qua ( admin)
        Intent intent = getIntent();
        listUser = (ArrayList<User>) intent.getSerializableExtra("user");

        init();
        eventClick();
        getDataComment();
    }

    private void getDataComment() {
        DataService dataService = APIServer.getService(); // khởi tạo  DataService, lấy đường dẫn
        Log.d("idSong2",arrSong.get(position).getIDSong()+"idSong2");
        try {
            Call<List<Comment>> callBack = dataService.getDataComment();// gọi pthuc trả về mảng các cmt
            callBack.enqueue(new Callback<List<Comment>>() {
                @Override
                public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                    // sự kiện lăng nghe thành côngn
                    comments = (ArrayList<Comment>) response.body(); // trả về mảng dữ liệu
                    // in ra xem kết quả
//                Log.d("B", comments.get(0).getContentComment());
                    commentAdapter = new CommentAdapter(PlayMusicActivity.this, comments);

                    recyclerViewComment.setLayoutManager(new LinearLayoutManager(PlayMusicActivity.this, RecyclerView.VERTICAL, false));
                    recyclerViewComment.setAdapter(commentAdapter);
//                eventClick();
                }

                // sự kiện thất bại
                @Override
                public void onFailure(Call<List<Comment>> call, Throwable t) {
                    Log.d("BBBBBBBBBBB", arrSong.size() + "");
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("Errrorrrrrrrr", "Loi");
        }
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapterMusic.getItem(position);
                if (arrSong.size() > 0) {
                    fragment_music_disk.playMusic(arrSong.get(position).getImageSong());
//                    Log.d("HHHHHHHH", arrSong.get(position).getIDSong()+arrSong.get(position).getLinkSong() + position + "sdfg");

                    handler.removeCallbacks(this);
                } else {
                    handler.postDelayed(this, 300);
                }
            }

        }, 500);
        imgButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgButtonPlay.setImageResource(R.drawable.iconplay);
                } else {
                    mediaPlayer.start();
                    imgButtonPlay.setImageResource(R.drawable.iconpause);
                }
            }
        });

        // chưa ra :(
        // tạo sự kiện cho nút lặp lại
        imgButtonRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat) {
                    repeat = false;
                    imgButtonRepeat.setImageResource(R.drawable.iconrepeat);
                } else {
                    repeat = true;
                    checkRandom = false; //vì mình muốn tại 1 thời điểm chỉ có thể chọn 1 cái: repeat hoặc random
                    imgButtonRepeat.setImageResource(R.drawable.iconsyned);
                    imgButtonShuff.setImageResource(R.drawable.iconsuffle);
                }
            }
        });

        // test được ròi
        // tạo sự kiện cho nút random bài hát
        imgButtonShuff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkRandom) {
                    if (repeat = true) {
                        repeat = false;
                        imgButtonShuff.setImageResource(R.drawable.iconshuffled);
                        imgButtonRepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgButtonShuff.setImageResource(R.drawable.iconshuffled);
                    checkRandom = true;
                } else {
                    imgButtonShuff.setImageResource(R.drawable.iconsuffle);
                    repeat = false;
                }
            }
        });

        // test được rồi
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
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        // test đc mà tên bài hát nó di tới bài thứ 3
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
                    // nếu mà lặp lại là true (tức là có chọn chức năng lặp lại)
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

                    new Playmp3().execute(arrSong.get(position).getLinkSong());
                    // in ra bài thứ 3 nè
                    imgButtonPlay.setImageResource(R.drawable.iconpause);
                    fragment_music_disk.playMusic(arrSong.get(position).getImageSong());
                    fragment_music_disk.txtNameSinger.setText("Tên ca sĩ: " + arrSong.get(position).getSinger());
                    fragment_music_disk.txtNameSong.setText("Tên bài hát: " + arrSong.get(position).getNameSong());
                    getSupportActionBar().setTitle(arrSong.get(position).getNameSong());
                    updateTime();
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
                    fragment_music_disk.txtNameSinger.setText("Tên ca sĩ: " + arrSong.get(position).getSinger());
                    fragment_music_disk.txtNameSong.setText("Tên bài hát: " + arrSong.get(position).getNameSong());
                    updateTime();
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

        // sự kiện nút cmt
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lấy giá trị người dùng nhập
                txtContentCmt = (EditText) findViewById(R.id.editTextComment);

                content = txtContentCmt.getText().toString();
                // kiểm tra người dùng có nhập đầy đủ thông tin hay k?
                if (content.isEmpty()) {
                    Toast.makeText(PlayMusicActivity.this, "Vui lòng nhập bình luận!", Toast.LENGTH_LONG).show();
                } else {
                    addCmt(urlInsert);
                    txtContentCmt.setText("");
                }
            }
        });

    }

    private void addCmt(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // đẩy dữ liệu lên nên dùng POST
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // phần nhận kết quả
                Log.d("kq",response.trim());
                if (response.trim().equalsIgnoreCase("Success")) {
                    Toast.makeText(PlayMusicActivity.this, "Thêm thành công", Toast.LENGTH_LONG).show();
                    // sau đó chuyển màn hình về MainActivity
//                    Intent intent = new Intent(PlayMusicActivity.this, PlayMusicActivity.class);
//                    startActivity(intent);

                } else {
                    Toast.makeText(PlayMusicActivity.this, "Thêm thất bại", Toast.LENGTH_LONG).show();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // phần lỗi
                Toast.makeText(PlayMusicActivity.this, "Lỗi " + error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        ) {
            // đẩy dữ liệu lên Server
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // lấy giá trị người dùng nhập
//                String content = txtContentCmt.getText().toString();
//                String nameUser = listUser.get(0).getUserName();
//                Log.d("content",content);
//                Log.d("content","content");
                // khởi tạo Map để đẩy dữ liệu vào
                Map<String, String> map = new HashMap<>();
                map.put("ContentComment", content); // key khi đẩy data lên phải trùng với key khi viết mã PHP để lấy
                map.put("NameUser", LoginActivity.nameUser); // key khi đẩy data lên phải trùng với key khi viết mã PHP để lấy
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void getDataFromIntent() {
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

        //commenteditTextComment
        recyclerViewComment = (RecyclerView) findViewById(R.id.recyclerViewComment);
        btnPost = (Button) findViewById(R.id.btnPost);

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
            updateTime();
        }
    }

    private void timeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarSong.setMax(mediaPlayer.getDuration());
    }

    private void updateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBarSong.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    // set lai thoi gian cho thanh thoi gian
                    txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    // dam bao cho no chay lien tuc
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);

        // chuyen bai hat
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    old_position = position;
                    // kiểm tra nếu có dữ liệu
                    if (arrSong.size() > 0) {
                        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
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
                        new Playmp3().execute(arrSong.get(position).getLinkSong());
                        imgButtonPlay.setImageResource(R.drawable.iconpause);
                        fragment_music_disk.playMusic(arrSong.get(position).getImageSong());
                        fragment_music_disk.txtNameSinger.setText("Tên ca sĩ: " + arrSong.get(position).getSinger());
                        fragment_music_disk.txtNameSong.setText("Tên bài hát: " + arrSong.get(position).getNameSong());
                        idSong = arrSong.get(position).getIDSong();
                        Log.d("idSong",idSong+"idSong");
                        getSupportActionBar().setTitle(arrSong.get(position).getNameSong());
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
                    next = false;
                    handler1.removeCallbacks(this); // xoá cái cũ đi

                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);// sau khoang 1s
    }
}