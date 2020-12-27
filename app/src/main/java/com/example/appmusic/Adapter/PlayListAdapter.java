package com.example.appmusic.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.appmusic.Model.PlayList;

import java.util.ArrayList;
import java.util.List;

public class PlayListAdapter extends ArrayAdapter<PlayList> {

    public PlayListAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
        super(context, resource, objects);
    }
}
