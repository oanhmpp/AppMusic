package com.example.appmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appmusic.Model.Admin;
import com.example.appmusic.R;

import java.util.List;

public class AdminAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Admin> listFunctions;

    public AdminAdapter(Context context, int layout, List<Admin> listFunctions) {
        this.context = context;
        this.layout = layout;
        this.listFunctions = listFunctions;
    }

    @Override
    public int getCount() {
        return listFunctions.size();
    }

    @Override
    public Object getItem(int position) {
        return listFunctions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        // lấy context
        if (view == null) { // lần đầu chạy nó sẽ null
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            // ánh xạ view
            viewHolder = new ViewHolder();
            viewHolder.imgFunction = (ImageView) view.findViewById(R.id.imgFunction);

            viewHolder.txtFunctionName = (TextView) view.findViewById(R.id.txtFunctionName);
            view.setTag(viewHolder); // giữ view đã ánh xạ
        } else {
            viewHolder = (ViewHolder) view.getTag(); // lấy ra view đã ánh xạ
        }

        // gán giá trị
        Admin admin = listFunctions.get(i); // lấy ra đối tượng Song
        viewHolder.imgFunction.setImageResource(admin.getImage());
        viewHolder.txtFunctionName.setText(admin.getTitle());
        return view;
    }
}
class ViewHolder {
    TextView txtFunctionName;
    ImageView imgFunction;

}
