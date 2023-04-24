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
import com.example.duanmau.frameActivity.QuanLyThanhVienFragment;
import com.example.duanmau.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    QuanLyThanhVienFragment fragment;
    private ArrayList<ThanhVien> list;
    TextView tvMaTV,tvTenTV,tvNamSinh;
    ImageView imageView;

    public ThanhVienAdapter(@NonNull Context context, QuanLyThanhVienFragment fragment, ArrayList<ThanhVien> list) {
        super(context, 0, list);
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
            v = inflater.inflate(R.layout.thanhvien_item,null);
        }
        final ThanhVien item = list.get(position);
        if (item!=null){
            tvMaTV = v.findViewById(R.id.tvMaTV);
            tvMaTV.setText("Mã thành viên:"+item.getMaTV());
            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Tên thành viên:"+item.getHoTen());
            tvNamSinh =v.findViewById(R.id.tvNamTV);
            tvNamSinh.setText("Năm sinh:"+item.getNamSinh());
            imageView = v.findViewById(R.id.imgDelete);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMaTV()));
            }
        });
        return v;
    }
}
