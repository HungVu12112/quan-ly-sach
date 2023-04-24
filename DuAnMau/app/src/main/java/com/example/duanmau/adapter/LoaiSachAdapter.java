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
import com.example.duanmau.frameActivity.QuanLyLoaiSachFragment;
import com.example.duanmau.frameActivity.QuanLyThanhVienFragment;
import com.example.duanmau.model.LoaiSach;
import com.example.duanmau.model.ThanhVien;

import java.util.ArrayList;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    QuanLyLoaiSachFragment fragment;
    private ArrayList<LoaiSach> list;
    TextView tvMaLoaiS,tvTenLoaiS;
    ImageView imageViewLS;

    public LoaiSachAdapter(@NonNull Context context, QuanLyLoaiSachFragment fragment, ArrayList<LoaiSach> list) {
        super(context, 0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loaisach_item,null);
        }
        final LoaiSach item = list.get(position);
        if (item!=null){
            tvMaLoaiS = v.findViewById(R.id.tvMaLoaiS);
            tvMaLoaiS.setText("Mã loại sách:"+item.getMaLoai());
            tvTenLoaiS = v.findViewById(R.id.tvTenLoaiS);
            tvTenLoaiS.setText("Tên loại sách:"+item.getTenLoai());
            imageViewLS = v.findViewById(R.id.imgDeleteLS);
        }
        imageViewLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMaLoai()));
            }
        });
        return v;
    }
}
