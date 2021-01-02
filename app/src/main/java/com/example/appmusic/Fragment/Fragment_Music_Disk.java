package com.example.appmusic.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.text.BreakIterator;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Music_Disk extends Fragment {
    View view;
    public TextView txtNameSong,txtNameSinger;
    CircleImageView circleImageView;
    ObjectAnimator objectAnimator;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_music_disk,container,false);
        circleImageView = view.findViewById(R.id.imgDisk);
        txtNameSong = (TextView) view.findViewById(R.id.txtNameSong);
        txtNameSinger = (TextView) view.findViewById(R.id.txtNameSinger);
        objectAnimator = ObjectAnimator.ofFloat(circleImageView,"rotation",0f,360);
        objectAnimator.setDuration(10);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        return view;
    }
    public void playMusic(String img) {
        Picasso.with(getActivity()).load(img).into(circleImageView);
    }
}
