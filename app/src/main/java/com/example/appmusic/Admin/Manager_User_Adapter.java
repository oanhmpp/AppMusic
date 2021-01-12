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

import com.example.appmusic.Model.User;
import com.example.appmusic.R;

import java.util.List;

public class Manager_User_Adapter extends BaseAdapter {
    private Manager_User_Activity context;
    private int layout;
    private List<User> listUser;

    public Manager_User_Adapter(Manager_User_Activity context, int layout, List<User> listUser) {
        this.context = context;
        this.layout = layout;
        this.listUser = listUser;
    }

    //trả về số dòng hiển thị
    @Override
    public int getCount() {
        return listUser.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return Integer.parseInt(listUser.get(position).getUserName());
    }


    //đọc từng dòng rồi trả về view
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        // lấy context
        if (view == null) { // lần đầu chạy nó sẽ null
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            // ánh xạ view
            viewHolder = new ViewHolder();
            viewHolder.imgUser = (ImageView) view.findViewById(R.id.imgUser);
            viewHolder.imgEdit = (ImageView) view.findViewById(R.id.imgEdit);
            viewHolder.imgRemove = (ImageView) view.findViewById(R.id.imgRemove);
            viewHolder.txtUsername = (TextView) view.findViewById(R.id.txtUsername);
            viewHolder.txtPassword = (TextView) view.findViewById(R.id.txtPassword);
            view.setTag(viewHolder); // giữ view đã ánh xạ
        } else {
            viewHolder = (ViewHolder) view.getTag(); // lấy ra view đã ánh xạ
        }

        // gán giá trị
        final User user = listUser.get(i); // lấy ra đối tượng Song
        //viewHolder.imgSong.setImageResource(account.getImage());
        // dùng thư viện Picasso để load hình ảnh
//        Picasso.with(context).load(account.getHinhAnh()).into(viewHolder.imgUser);
        viewHolder.txtUsername.setText(user.getUserName());
        viewHolder.txtPassword.setText(user.getPassword());


        // bắt sự kiện update và delete bài hát cho ListView ở đây
        viewHolder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "name_user = " + user.getUserName(), Toast.LENGTH_LONG).show();
                // gởi dữ liệu bài hát qua màn hình sửa bài hát
                Intent intent = new Intent(context, Update_User_Activity.class);
                intent.putExtra("data", user);
                context.startActivity(intent);

            }
        });
        //bắt sự kiện xoá user
        viewHolder.imgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Id = " + user.getUserName(), Toast.LENGTH_LONG).show();
//                context.deleteUser(user.getUserName());
                deleteUser(user.getUserName(), user.getUserName());
            }
        });

        return view;
    }

    public void deleteUser(final String userName, String name) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Xoá User!");
        dialog.setMessage("Bạn có chắc chắn muốn xoá " + name + " không?");
        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // gọi hàm xoá bên QuanLyUserActivity
                context.deleteUser(userName);
            }
        });
        dialog.show();

    }

    class ViewHolder {
        TextView txtUsername, txtPassword;
        ImageView imgUser, imgEdit, imgRemove;

    }
}
