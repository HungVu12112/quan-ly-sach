package com.example.duanmau.frameActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.duanmau.DAO.ThuThuDao;
import com.example.duanmau.R;
import com.example.duanmau.model.ThuThu;
import com.google.android.material.textfield.TextInputEditText;


public class DoiMatKhauFragment extends Fragment {
    TextInputEditText edPassOld,edPass,edRePass;
    Button btnSave,btnCancel;
    ThuThuDao dao;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_doi_mat_khau,container,false);
        edPassOld = v.findViewById(R.id.edPassOld);
        dao = new ThuThuDao(getActivity());
        edPass = v.findViewById(R.id.edPassChange);
        edRePass = v.findViewById(R.id.edRePassChange);
        btnSave = v.findViewById(R.id.btnSaveChange);
        btnCancel = v.findViewById(R.id.btnCancelChange);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassOld.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE",Context. MODE_PRIVATE);
                String user = preferences.getString("USERNAME","");
                if (validate()>0){
                    ThuThu thuthu = dao.getId(user);
                    thuthu.setMatKhau(edPass.getText().toString());
                    if (dao.updatePass(thuthu) > 0){
                        Toast.makeText(getActivity(),"Thay đôie mật khẩu thành công",Toast.LENGTH_SHORT).show();
                        edPassOld.setText("");
                        edPass.setText("");
                        edRePass.setText("");
                    }
                    else{
                        Toast.makeText(getActivity(),"Thay đôie mật khẩu không thành công",Toast.LENGTH_SHORT).show();
                    }
                }
            }
         });
        return v;

    }

    public int validate(){
        int check = 1;
        if (edPassOld.getText().length()==0||edPass.getText().length()==0||edRePass.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải điền đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        else{
            SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = preferences.getString("PASSWORD","");
            String pass = edPass.getText().toString();
            String repass = edRePass.getText().toString();
            if (!passOld.equals(edPassOld.getText().toString())){
                Toast.makeText(getContext(),"Mật khẩu cũ sai",Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(repass)){
                Toast.makeText(getContext(),"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
                check = -1;

            }
        }
        return check;
    }
}