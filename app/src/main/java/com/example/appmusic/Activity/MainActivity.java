package com.example.appmusic.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.appmusic.Adapter.MainViewPagerAdapter;
import com.example.appmusic.Fragment.Fragment_Create_Playlists;
import com.example.appmusic.Fragment.Fragment_Find;
import com.example.appmusic.Fragment.Fragment_Home;
import com.example.appmusic.Fragment.Fragment_Playlist;
import com.example.appmusic.Fragment.Fragment_Profile_Group;
import com.example.appmusic.Fragment.Fragment_SongHot;
import com.example.appmusic.Fragment.Fragment_nav_header;
import com.example.appmusic.Model.User;
import com.example.appmusic.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

//import com.felix.bottomnavygation.BottomNav;
//import com.felix.bottomnavygation.ItemNav;
public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager; // lướt qua lại các màn hình
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private BottomNavigationMenuView bottomNav;
    private DrawerLayout drawerLayout;
    private BottomAppBar bottomAppBar;
    private ActionBarDrawerToggle drawerToggle; // điều khiển việc đóng mở DrawerLayout

//    private TextView txtUserName;
    //khai báo nav_header
    TextView txtUsername;
    String name="";

    NavigationView navigationView;
    NavigationMenuItemView itemNavHome, itemNavSearch, itemNavNoti, itemNavProfile;
    ArrayList<User> arrUser;

    public MainActivity() {
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//goi intent tu login
        Intent intent = getIntent();
        arrUser = (ArrayList<User>) intent.getSerializableExtra("user");
//gọi tabs dưới màn hình
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_Home(), "HOME");
        adapter.addFragment(new Fragment(), "FIND");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
        addControls();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Home()).commit();
            navigationView.setCheckedItem(R.id.nav_home);

        }
        addEvents();
        addEvensTabLayout();
    }
//sự kiện khi nhấn tab dưới (chưa ra)
    private void addEvensTabLayout() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Home()).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Find()).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void addEvents() {
        FragmentManager manager = getSupportFragmentManager();
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(manager);

        mainViewPagerAdapter.addFragment(new Fragment_Home(), "Home");
        mainViewPagerAdapter.addFragment(new Fragment_Find(), "Search");
        viewPager.setAdapter(mainViewPagerAdapter); // set cho view pager

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.nav_view,new Fragment_nav_header()).commit();
//                Log.d("aaaaaaaaaaa", "toisw ddaay");
//                NavigationUsername.setText(arrUser.get(0).getUserName());
//                NavigationAdmin.setText(arrUser.get(0).getAdmin());

                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Home()).commit();
                        break;
                    case R.id.nav_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Profile_Group()).commit();
                        break;
                    case R.id.nav_playlist:
                        // bắt buộc phải đăng nhập mới cho tạo playlist
                        if (arrUser != null && arrUser.size() > 0) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Create_Playlists()).commit();
                            break;
                        } else {
                            // chưa có dữ liệu thì gửi qua trang login
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            //intent.putExtra("state", state);
                            startActivity(intent);
                        }
                    case R.id.nav_favorite:
                        break;
                    case R.id.nav_downloaded:
                        break;
                    case R.id.nav_send:
                        break;
                    case R.id.nav_logout:
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;

                }
                //sau khi mở thì tắt menu bên trái
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void addControls() {
        // ánh xạ cho phần menu và drawer layout
        toolbar = (Toolbar) findViewById(R.id.toolBarMain);
        setSupportActionBar(toolbar);
        //ten user
        txtUsername = findViewById(R.id.txtUsername);
        //admin
//        NavigationAdmin = findViewById(R.id.NavigationAdmin);
//        txtUsername.setText(arrUser.get(0).getUserName());

        if (arrUser != null && arrUser.size() > 0) {
            getSupportActionBar().setTitle("Hello: " + arrUser.get(0).getUserName());
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //gọi nav_header ở đây
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        viewPager = (ViewPager) findViewById(R.id.myViewPaper);

//        final BadgeIndicator badgeIndicator = new BadgeIndicator(this, android.R.color.holo_red_dark, android.R.color.white);
//
//        bottomNav = findViewById(R.id.bottomNav);
//        itemNavHome = new ItemNav(this, R.drawable.feed, R.drawable.feed_sel, "Home");
//        bottomNav.addItemNav(itemNavHome);
//        itemNavSearch = new ItemNav(this, R.drawable.explore, R.drawable.explore_sel, "Search");
//        bottomNav.addItemNav(itemNavSearch);
//        itemNavNoti = new ItemNav(this, R.drawable.atividades, R.drawable.atividades_sel, "Notification").addColorAtive(android.R.color.holo_blue_bright);
//        bottomNav.addItemNav(itemNavNoti);
//        itemNavProfile = new ItemNav(this, R.drawable.perfil, R.drawable.perfil_sel, "Profile").addProfileColorAtive(android.R.color.holo_red_dark).addProfileColorInative(android.R.color.black);
//        bottomNav.addItemNav(itemNavProfile);
//
//        bottomNav.setTabSelectedListener(listener);
//        bottomNav.build();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mList = new ArrayList<>();
        private final List<String> mTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            return mList.get(i);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mList.add(fragment);
            mTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }
}