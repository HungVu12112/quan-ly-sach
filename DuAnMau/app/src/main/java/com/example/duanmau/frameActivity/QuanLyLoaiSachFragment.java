package com.example.duanmau.frameActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duanmau.DAO.LoaiSachDao;
import com.example.duanmau.DAO.ThanhVienDao;
import com.example.duanmau.R;
import com.example.duanmau.adapter.LoaiSachAdapter;
import com.example.duanmau.adapter.ThanhVienAdapter;
import com.example.duanmau.model.LoaiSach;
import com.example.duanmau.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuanLyLoaiSachFragment extends Fragment {
    ListView listView;
    ArrayList<LoaiSach> list;
    static LoaiSachDao dao;
    LoaiSachAdapter adapter;
    LoaiSach item;
    FloatingActionButton fabLS;
    Dialog dialog;
    EditText edMaLoaiS,edTenLoaiS;
    Button btnSaveLS,btnCancelLS;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quan_ly_loai_sach2, container, false);
        listView = v.findViewById(R.id.lvLoaiSach);
        fabLS = v.findViewById(R.id.fabLS);
        dao = new LoaiSachDao(getActivity());
        capNhat();
        fabLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),0);
                return false;
            }
        });
        return v;
    }
    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.loaisach_dialog);
        edMaLoaiS = dialog.findViewById(R.id.edMaLoaiSach);
        edTenLoaiS =dialog.findViewById(R.id.edTenLoaiS);
        btnCancelLS = dialog.findViewById(R.id.btnCancelLS);
        btnSaveLS = dialog.findViewById(R.id.btnSaveLS);
        edMaLoaiS.setEnabled(false);
        if (type!=0){
            edMaLoaiS.setText(String.valueOf(item.getMaLoai()));
            edTenLoaiS.setText(item.getTenLoai());

        }
        btnCancelLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSaveLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new LoaiSach();
                item.setTenLoai(edTenLoaiS.getText().toString());

                if (validate()>0){
                    if (type==0){
                        if (dao.insert(item)>0){
                            Toast.makeText(getContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(),"Thêm không thành công",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.setMaLoai(Integer.parseInt(edMaLoaiS.getText().toString()));
                        if (dao.update(item)>0){
                            Toast.makeText(getContext(),"Sủa thành công",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getContext(),"Sửa không thành công",Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhat();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public int validate(){
        int check =1;
        if(edTenLoaiS.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
    void capNhat(){
        list = (ArrayList<LoaiSach>) dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(),this,list);
        listView.setAdapter(adapter);
    }
    public void xoa(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(id);
                capNhat();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }


}