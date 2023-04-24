package com.example.duanmau.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duanmau.DAO.SachDao;
import com.example.duanmau.DAO.ThanhVienDao;
import com.example.duanmau.R;
import com.example.duanmau.frameActivity.QuanLyPhieuMuonFragment;
import com.example.duanmau.model.PhieuMuon;
import com.example.duanmau.model.Sach;
import com.example.duanmau.model.ThanhVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    QuanLyPhieuMuonFragment fragment;
    private ArrayList<PhieuMuon> lists;
    TextView tvMaPM,tvTenTV ,tvTenSach,tvTienThue,tvNgay,tvTraSach;
    ImageView imgDel;
    SachDao sachDao;
    ThanhVienDao thanhVienDao;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public PhieuMuonAdapter(@NonNull Context context, QuanLyPhieuMuonFragment fragment, ArrayList<PhieuMuon> lists) {
        super(context, 0,lists);
        this.context = context;
        this.lists =lists;
        this.fragment =fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.phieu_muon_item,null);
        }
        final PhieuMuon item = lists.get(position);
        if(item != null){

            tvMaPM = v.findViewById(R.id.tvMaPM);
            tvMaPM.setText("Mã phiếu mượn:"+item.getMaPM());
            sachDao = new SachDao(context);
            Sach sach = sachDao.getID(String.valueOf(item.getMaSach()));
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách:"+sach.getTenSach());
            thanhVienDao = new ThanhVienDao(context);
            ThanhVien thanhVien = thanhVienDao.getId(String.valueOf(item.getMaTV()));
            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Thành viên"+thanhVien.getHoTen());
            tvTienThue = v.findViewById(R.id.tvTienThue);
            tvTienThue.setText("Tiền thuê:"+item.getTienThue());
            tvNgay= v.findViewById(R.id.tvNgayPM);
            tvNgay.setText("Ngày thuế:"+sdf.format(item.getNgay()));
            tvTraSach = v.findViewById(R.id.tvTraSach);
            if (item.getTraSach()==1){
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã trả sách");
            }
            else{
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa trả sách");
            }
            imgDel= v.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMaPM()));
            }
        });
        return v;

    }
}
