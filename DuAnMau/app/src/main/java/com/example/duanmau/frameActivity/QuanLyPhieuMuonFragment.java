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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.DAO.PhieuMuonDao;
import com.example.duanmau.DAO.SachDao;
import com.example.duanmau.DAO.ThanhVienDao;
import com.example.duanmau.R;
import com.example.duanmau.adapter.PhieuMuonAdapter;
import com.example.duanmau.adapter.SachSpinerAdapter;
import com.example.duanmau.adapter.ThanhVienSpAdapter;
import com.example.duanmau.model.PhieuMuon;
import com.example.duanmau.model.Sach;
import com.example.duanmau.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class QuanLyPhieuMuonFragment extends Fragment {
    ListView lvPhieuMuon;
    ArrayList<PhieuMuon> list;
    static PhieuMuonDao dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;
    FloatingActionButton fabPM;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV,spSach;
    TextView tvNgay,tvTienThue;
    CheckBox chkTraSach;
    Button btnSave,btnCancel;
    ThanhVienSpAdapter thanhVienSpAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDao thanhVienDao;
    ThanhVien thanhVien;
    int maThanhVien;

    SachSpinerAdapter sachSpinerAdapter;
    ArrayList<Sach> listSach;
    SachDao sachDao;
    Sach sach;
    int maSach,tienThue;
    int positionTV,positionSach;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quan_ly_phieu_muon2,container,false);
        lvPhieuMuon =v.findViewById(R.id.lvPhieuMuon);
        dao = new PhieuMuonDao(getActivity());

        fabPM = v.findViewById(R.id.fabPM);
        fabPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oprnDialog(getActivity(),0);
            }
        });
        lvPhieuMuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                oprnDialog(getActivity(),1);
                return false;
            }
        });
        capNhatLv();
        return v;
    }
    void capNhatLv(){
        list =(ArrayList<PhieuMuon>) dao.getAll();
        adapter =new PhieuMuonAdapter(getActivity(),this,list);
        lvPhieuMuon.setAdapter(adapter);
    }
    protected void oprnDialog(final Context context,final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.phieu_muon_dialog);
         edMaPM = dialog.findViewById(R.id.edMaPM);
         spTV = dialog.findViewById(R.id.spMaTV);
         spSach = dialog.findViewById(R.id.spMaSach);
         tvNgay = dialog.findViewById(R.id.tvNgay);
         tvTienThue = dialog.findViewById(R.id.tvTienThue);
         chkTraSach = dialog.findViewById(R.id.chkTraSach);
         btnCancel = dialog.findViewById(R.id.btnCancelPM);
         btnSave = dialog.findViewById(R.id.btnSavePM);
         tvNgay.setText("Ngày thuê:"+sdf.format(new Date()));
         edMaPM.setEnabled(false);
         thanhVienDao = new ThanhVienDao(context);
         listThanhVien = new ArrayList<ThanhVien>();
         listThanhVien = (ArrayList<ThanhVien>) thanhVienDao.getAll();
         thanhVienSpAdapter = new ThanhVienSpAdapter(context,listThanhVien);
         spTV.setAdapter(thanhVienSpAdapter);
         spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 maThanhVien = listThanhVien.get(position).getMaTV();
//                 Toast.makeText(context,"Chọn"+listThanhVien.get(position).getHoTen(),Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });
         sachDao = new SachDao(context);
         listSach = new ArrayList<Sach>();
         listSach = (ArrayList<Sach>) sachDao.getAll();
         sachSpinerAdapter = new SachSpinerAdapter(context,listSach);
         spSach.setAdapter(sachSpinerAdapter);
         spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 maSach = listSach.get(position).getMaSach();
                 tienThue = listSach.get(position).getGiaThue();
                 tvTienThue.setText("Tiền thuê:"+tienThue);
//                 Toast.makeText(context,"Chọn"+listSach.get(position).getTenSach(),Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });
         if (type!= 0){
             edMaPM.setText(String .valueOf(item.getMaPM()));
             for (int i =0;i<listThanhVien.size();i++)
                 if (item.getMaTV() ==(listThanhVien.get(i).getMaTV())){
                     positionTV =i;
                 }
             spTV.setSelection(positionTV);
             for (int i =0;i<listSach.size();i++)
                 if (item.getMaSach()==(listSach.get(i).getMaSach())){
                     positionSach =i;
                 }
             spSach.setSelection(positionSach);
             tvNgay.setText("Ngày thuê:"+sdf.format(item.getNgay()));
             tvTienThue.setText("Tiền Thuê:"+item.getTienThue());
             if (item.getTraSach()==1){
                 chkTraSach.setChecked(true);
             }else {
                 chkTraSach.setChecked(false);
             }
         }
         btnCancel.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 dialog.dismiss();
             }
         });
         btnSave.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 item = new PhieuMuon();
                 item.setMaSach(maSach);
                 item.setMaTV(maThanhVien);
                 item.setNgay(new Date());
                 item.setTienThue(tienThue);
                 if (chkTraSach.isChecked()){
                     item.setTraSach(1);
                 }
                 else {
                     item.setTraSach(0);
                 }
                 if (type == 0){
                     if (dao.insert(item)>0){
                         Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                     }
                     else {
                         Toast.makeText(context,"Thêm thất bại",Toast.LENGTH_SHORT).show();
                     }
                 }else {
                        item.setMaPM(Integer.parseInt(edMaPM.getText().toString()));
                        if (dao.update(item)>0){
                            Toast.makeText(context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(context,"Sửa thất bại",Toast.LENGTH_SHORT).show();
                        }
                 }
                 capNhatLv();
                 dialog.dismiss();
             }
         });
        dialog.show();
    }
    public void xoa(final  String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
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
}