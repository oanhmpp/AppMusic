package com.example.appmusic.Admin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmusic.Model.Song;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Manager_All_Songs_Adapter extends BaseAdapter {
    private Manager_All_Songs_Activity context;
    private int layout;
    private List<Song> listSong;

    public Manager_All_Songs_Adapter(Manager_All_Songs_Activity context, int layout, List<Song> listSong) {
        this.context = context;
        this.layout = layout;
        this.listSong = listSong;
    }

    @Override
    public int getCount() {
        return listSong.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return Integer.parseInt(listSong.get(position).getIDSong());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderSong viewHolder;
        // lấy context
        if (view == null) { // lần đầu chạy nó sẽ null
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            // ánh xạ view
            viewHolder = new ViewHolderSong();
            viewHolder.imgManagerSong = (ImageView) view.findViewById(R.id.imgManagerSong);
            viewHolder.imgEditSong = (ImageView) view.findViewById(R.id.imgEditSong);
            viewHolder.imgRemoveSong = (ImageView) view.findViewById(R.id.imgRemoveSong);
            viewHolder.txtNameManagerSong = (TextView) view.findViewById(R.id.txtNameManagerSong);
            viewHolder.txtAuthorManagerSong = (TextView) view.findViewById(R.id.txtAuthorManagerSong);
            view.setTag(viewHolder); // giữ view đã ánh xạ
        } else {
            viewHolder = (ViewHolderSong) view.getTag(); // lấy ra view đã ánh xạ
        }

        // gán giá trị
        final Song song = listSong.get(i); // lấy ra đối tượng Song
        // dùng thư viện Picasso để load hình ảnh
        //chưa test trường hợp chiều dài text bé hơn 5
        //song.getImageSong().equals(song.getImageSong().length()<5)
        if(song.getImageSong()==""||song.getImageSong().isEmpty()) {//nếu link ảnh rỗng thì tahy ảnh khác
            Picasso.with(context).load(R.drawable.ic_person_outline_white_24dp).into(viewHolder.imgManagerSong);
        }else {
            Picasso.with(context).load(song.getImageSong()).into(viewHolder.imgManagerSong);
        }
        viewHolder.txtNameManagerSong.setText(song.getNameSong());
        viewHolder.txtAuthorManagerSong.setText(song.getSinger());


        // bắt sự kiện update và delete bài hát cho ListView ở đây
        viewHolder.imgEditSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Id = " + String.valueOf(song.getIDSong()), Toast.LENGTH_LONG).show();
                // gởi dữ liệu bài hát qua màn hình sửa bài hát
                Intent intent = new Intent(context, Update_Song_Activity.class);
                intent.putExtra("data", song);
                context.startActivity(intent);

            }
        });
        viewHolder.imgRemoveSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Id = " + song.getIDSong(), Toast.LENGTH_LONG).show();
                deleteSong(Integer.parseInt(song.getIDSong()), song.getNameSong());
            }
        });

        return view;
    }
    public void deleteSong(final int id, String name) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Xoá bài hát!");
        dialog.setMessage("Bạn có chắc chắn muốn xoá [" + name + "] không?");
        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // gọi hàm xoá bên QuanLyBaiHatActivity
                context.deleteSong(id);
            }
        });
        dialog.show();

    }

}
// class ViewHolder để làm giảm thiểu việc ánh xạ mỗi dòng ListView, làm tăng tốc load dữ liệu
class ViewHolderSong{
    TextView txtNameManagerSong, txtAuthorManagerSong;
    ImageView imgManagerSong, imgEditSong, imgRemoveSong;

}
