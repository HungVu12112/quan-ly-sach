package com.example.duanmau.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duanmau.DAO.LoaiSachDao;
import com.example.duanmau.DAO.SachDao;
import com.example.duanmau.R;
import com.example.duanmau.frameActivity.QuanLySachFragment;
import com.example.duanmau.model.LoaiSach;
import com.example.duanmau.model.PhieuMuon;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends ArrayAdapter<Sach> {
    Context context;
    QuanLySachFragment fragment;
    List<Sach> list;
    TextView tvMaSach, tvTenSach, tvGiaThue, tvLoai;
    ImageView imgDelete;

    public SachAdapter(@NonNull Context context, QuanLySachFragment fragment, List<Sach> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_item, null);
        }
        final Sach item = list.get(position);
        if (item != null) {
            LoaiSachDao loaiSachDao = new LoaiSachDao(context);
            LoaiSach loaiSach = loaiSachDao.getID(String.valueOf(item.getMaLoai()));
//            SachDao sachDao = new SachDao(context);
//            ArrayList<Sach> list = sachDao.getSach();
//            ArrayList<LoaiSach> loaiSach = sachDao.getIdSach(String.valueOf(list.get(position).getMaLoai()));
//            try {
//                for (LoaiSach loaiSach1 : loaiSach) {
//                    Log.i("CHECKSACH", loaiSach1.getTenLoai());
//                }
//                Log.i("CHECKSACH", position+"");
//
//            } catch (Exception e) {
//
//            }
            tvMaSach = v.findViewById(R.id.tvMaSach);
            tvMaSach.setText("Mã sách :" + item.getMaSach());

            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách :" + item.getTenSach());

            tvGiaThue = v.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText("Giá thuê :" + item.getGiaThue());

            tvLoai = v.findViewById(R.id.tvLoai);
             tvLoai.setText("Loại sách:"+loaiSach.getTenLoai());
            imgDelete = v.findViewById(R.id.imgDeleteLS);
        }
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMaSach()));
            }
        });
        return v;
    }
}
