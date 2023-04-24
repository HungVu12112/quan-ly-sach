package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.DAO.ThuThuDao;

public class LoginApplication extends AppCompatActivity {
    Button btnLogin,btnThoat;
    EditText edusername,edpass;
    CheckBox chkBox;
    String strUser,strPass;
    ThuThuDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_application);
        btnLogin = findViewById(R.id.login);
        btnThoat = findViewById(R.id.id_thoat);
        edusername = findViewById(R.id.username);
        edpass = findViewById(R.id.pass);
        chkBox = findViewById(R.id.chkBox);
        edpass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        dao = new ThuThuDao(this);
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String user = pref.getString("USERNAME","");
        String pass = pref.getString("PASSWORD","");
        Boolean rem = pref.getBoolean("REMEMBER",false);
        edusername.setText(user);
        edpass.setText(pass);
        chkBox.setChecked(rem);

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               edusername.setText("");
               edpass.setText("");
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkLogin();
            }
        });
    }
    public void rememberUser(String u,String p,boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status){
            edit.clear();
        }
        else{
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        edit.commit();
    }
    public void checkLogin(){
        strUser = edusername.getText().toString();
        strPass = edpass.getText().toString();
        if (strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tên đăng nhập và mật khẩu không được bỏ trống",Toast.LENGTH_SHORT).show();
        }
        else{
            if (dao.checkLogin(strUser,strPass)>0){
                Toast.makeText(getApplicationContext(),"Login thành công",Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strPass,chkBox.isChecked());
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("user",strUser);
                startActivity(i);
                finish();
            }else{
                Toast.makeText(getApplicationContext(),"Tên đăng nhập và mật khẩu không đúng ",Toast.LENGTH_SHORT).show();
            }
        }
    }
}