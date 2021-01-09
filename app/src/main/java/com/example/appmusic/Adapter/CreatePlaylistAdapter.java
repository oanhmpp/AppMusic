package com.example.appmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appmusic.Model.Song;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CreatePlaylistAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Song> listBaiHat;

    public CreatePlaylistAdapter(Context context, int layout, List<Song> listBaiHat) {
        this.context = context;
        this.layout = layout;
        this.listBaiHat = listBaiHat;
    }

    @Override
    // trả về số dòng mà hiển thị cho ListView
    public int getCount() {
        return listBaiHat.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return Integer.parseInt(listBaiHat.get(position).getIDSong());
    }

    @Override
    // đọc từng dòng rồi trả về view
    public View getView(int i, View view, ViewGroup viewGroup) {
        com.example.appmusic.Adapter.ViewHolderBaiHat viewHolder;
        // lấy context
        if (view == null) { // lần đầu chạy nó sẽ null
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            // ánh xạ view
            viewHolder = new com.example.appmusic.Adapter.ViewHolderBaiHat();
            viewHolder.imgMyPlayList = (ImageView) view.findViewById(R.id.imgMyPlayList);
            viewHolder.txtNameMyPlayList = (TextView) view.findViewById(R.id.txtNameMyPlayList);
            viewHolder.txtAuthorMyPlayList = (TextView) view.findViewById(R.id.txtAuthorMyPlayList);
            view.setTag(viewHolder); // giữ view đã ánh xạ
        } else {
            viewHolder = (com.example.appmusic.Adapter.ViewHolderBaiHat) view.getTag(); // lấy ra view đã ánh xạ
        }

        // gán giá trị
        final Song baiHat = listBaiHat.get(i); // lấy ra đối tượng Song
        //viewHolder.imgSong.setImageResource(account.getImage());
        // dùng thư viện Picasso để load hình ảnh
        Picasso.with(context).load(baiHat.getImageSong()).into(viewHolder.imgMyPlayList);
        viewHolder.txtNameMyPlayList.setText(baiHat.getNameSong());
        viewHolder.txtAuthorMyPlayList.setText(baiHat.getSinger());

        return view;
    }
}

// class ViewHolder để làm giảm thiểu việc ánh xạ mỗi dòng ListView, làm tăng tốc load dữ liệu
class ViewHolderBaiHat {
    TextView txtNameMyPlayList, txtAuthorMyPlayList;
    ImageView imgMyPlayList;

}
