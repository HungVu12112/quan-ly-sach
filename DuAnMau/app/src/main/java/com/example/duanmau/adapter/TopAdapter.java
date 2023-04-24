package com.example.duanmau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duanmau.R;
import com.example.duanmau.frameActivity.TopFragment;
import com.example.duanmau.model.Top;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    TopFragment topFragment;
    ArrayList<Top> lists;
    TextView tvSach,tvSoLuong;
    ImageView imgDel;
    public TopAdapter(@NonNull Context context, TopFragment topFragment, ArrayList<Top> lists) {
        super(context, 0,lists);
        this.context = context;
        this.topFragment = topFragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.top_item,null);
        }
        final Top item =lists.get(position);
        if (item != null){
            tvSach = v.findViewById(R.id.tvSachTop);
            tvSach.setText("Sách :"+item.getTenSach());
            tvSoLuong = v.findViewById(R.id.tvSL);
            tvSoLuong.setText("Số lượng :"+item.getSoLuong());
        }
        return v;
    }
}
