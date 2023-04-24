package com.example.duanmau.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duanmau.R;
import com.example.duanmau.model.LoaiSach;

import java.util.ArrayList;

public class LoaiSachSPAdapter extends ArrayAdapter<LoaiSach> {
    Context context;
    ArrayList<LoaiSach> lists;
    TextView tvMaLoaiSach,tvTenLoaiSach;
    public LoaiSachSPAdapter(@NonNull Context context, ArrayList<LoaiSach> lists) {
        super(context, 0,lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loaisach_item_spiner,null);
        }
        final LoaiSach item = lists.get(position);
        if (item != null){
            tvMaLoaiSach = v.findViewById(R.id.tvMaLoaiSachSp);
            tvMaLoaiSach.setText(item.getMaLoai()+".");
            tvTenLoaiSach =v.findViewById(R.id.tvTenLoaiSachSp);
            tvTenLoaiSach.setText(item.getTenLoai());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loaisach_item_spiner,null);
        }
        final LoaiSach item = lists.get(position);
        tvMaLoaiSach = v.findViewById(R.id.tvMaLoaiSachSp);
        tvMaLoaiSach.setText(item.getMaLoai()+".");
        tvTenLoaiSach =v.findViewById(R.id.tvTenLoaiSachSp);
        tvTenLoaiSach.setText(item.getTenLoai());
        return v;
    }
}
