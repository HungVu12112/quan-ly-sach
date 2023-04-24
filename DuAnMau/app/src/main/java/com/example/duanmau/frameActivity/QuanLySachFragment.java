package com.example.duanmau.frameActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duanmau.DAO.LoaiSachDao;
import com.example.duanmau.DAO.SachDao;
import com.example.duanmau.R;
import com.example.duanmau.adapter.LoaiSachSPAdapter;
import com.example.duanmau.adapter.SachAdapter;
import com.example.duanmau.model.LoaiSach;
import com.example.duanmau.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class QuanLySachFragment extends Fragment {
    ListView lvSach;
    SachDao sachDao;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaSach,edTenSach,edGiaThue;
    Spinner spinner;
    Button btnSave,btnCancel;
    SachAdapter adapter;
    Sach item;
    List<Sach> list;
    LoaiSachSPAdapter spAdapter;
    ArrayList<LoaiSach> listLoaiSach;
    LoaiSachDao loaiSachDao;
    LoaiSach loaiSach;
    int maLoaiSach,position;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quan_ly_sach2,container,false);
        lvSach = v.findViewById(R.id.lvSach);
        sachDao = new SachDao(getActivity());
        capNhatLv();
        fab = v.findViewById(R.id.fabSach);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        lvSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        return v;

    }
    void capNhatLv(){
        list = (List<Sach>) sachDao.getAll();
        adapter = new SachAdapter(getActivity(),this,list);
        lvSach.setAdapter(adapter);
    }
    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               sachDao.delete(Id);
               capNhatLv();
               dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
    protected  void openDialog(final Context context,final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.sach_dialog);
        edMaSach = dialog.findViewById(R.id.edMaSach);
        edTenSach = dialog.findViewById(R.id.edTenSach);
        edGiaThue = dialog.findViewById(R.id.edGiaThue);
        spinner = dialog.findViewById(R.id.spLoaiSach);
        btnCancel = dialog.findViewById(R.id.btnCancelSach);
        btnSave = dialog.findViewById(R.id.btnSaveSach);

        listLoaiSach = new ArrayList<LoaiSach>();
        loaiSachDao = new LoaiSachDao(context);
        listLoaiSach= (ArrayList<LoaiSach>) loaiSachDao.getAll();

        spAdapter = new LoaiSachSPAdapter(context,listLoaiSach);
        spinner.setAdapter(spAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = listLoaiSach.get(position).getMaLoai();
                Toast.makeText(context,"Chọn"+listLoaiSach.get(position).getTenLoai(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edMaSach.setEnabled(false);
        if (type != 0) {
            edMaSach.setText(String.valueOf(item.getMaSach()));
            edTenSach.setText(item.getTenSach());
            edGiaThue.setText(String.valueOf(item.getGiaThue()));
            for (int i = 0; i < listLoaiSach.size(); i++)
                if (item.getMaLoai() == (listLoaiSach.get(i)).getMaLoai()) {
                    position = i;
                }
                Log.i("demo", "posSach" + position);
                spinner.setSelection(position);
            }
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item = new Sach();
                    item.setTenSach(edTenSach.getText().toString());
                    item.setGiaThue(Integer.parseInt(edGiaThue.getText().toString()));
                    item.setMaLoai(maLoaiSach);
                    if (validate()>0){
                        if (validate()>0){
                            if (type==0){
                                if (sachDao.insert(item)>0){
                                    Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context,"Thêm thất bại",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                item.setMaSach(Integer.parseInt(edMaSach.getText().toString()));
                                if (sachDao.update(item)>0){
                                    Toast.makeText(context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(context,"Sửa thất bại",Toast.LENGTH_SHORT).show();
                                }
                            }
                            capNhatLv();
                            dialog.dismiss();
                        }
                    }
                }
            });
            dialog.show();
        }

    public int validate(){
        int check = 1;
        if (edTenSach.getText().length()==0 || edGiaThue.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check =-1;
        }
        return check;
    }
}