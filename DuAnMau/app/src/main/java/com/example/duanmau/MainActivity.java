package com.example.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.duanmau.DAO.Demo;
import com.example.duanmau.frameActivity.DoanhThuFragment;
import com.example.duanmau.frameActivity.DoiMatKhauFragment;
import com.example.duanmau.frameActivity.QuanLyLoaiSachFragment;
import com.example.duanmau.frameActivity.QuanLyPhieuMuonFragment;
import com.example.duanmau.frameActivity.QuanLySachFragment;
import com.example.duanmau.frameActivity.QuanLyThanhVienFragment;
import com.example.duanmau.frameActivity.ThuThuFragment;
import com.example.duanmau.frameActivity.TopFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private Toolbar mtoolbar;
    private  NavigationView mnavigationView;
    private FrameLayout mframeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mnavigationView = findViewById(R.id.id_navView);
        drawerLayout = findViewById(R.id.drawer_layout);
        mtoolbar = findViewById(R.id.id_toolbar);
        mframeLayout = findViewById(R.id.id_framelayout);
        setSupportActionBar(mtoolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,mtoolbar,0,0);
        toggle.syncState();
        mnavigationView.setNavigationItemSelectedListener(this);
        Demo demo = new Demo(getApplicationContext());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();

         if (id==R.id.quanlysach){
            replaceFragment(new QuanLySachFragment());
        }
        else if (id==R.id.quanlyloaisach){
            replaceFragment(new QuanLyLoaiSachFragment());
        }
        else if (id ==R.id.quanlythanhvien){
            replaceFragment(new QuanLyThanhVienFragment());
        }
        else if (id==R.id.quanlyphieumuon){
            replaceFragment(new QuanLyPhieuMuonFragment());
        }
        else if (id==R.id.tongke){
            replaceFragment(new TopFragment());
        }
         else if (id==R.id.doanhthu){
             replaceFragment(new DoanhThuFragment());
         }
        else if (id==R.id.doimatkhau){
            replaceFragment(new DoiMatKhauFragment());
        }
        else if (id==R.id.dangxuat){
            Intent intent = new Intent(MainActivity.this,LoginApplication.class);
            startActivity(intent);
            finish();
        }
        else if (id==R.id.thoat){
            System.exit(0);
        }
        drawerLayout.closeDrawer(mnavigationView);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.id_framelayout,fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(mnavigationView)){
            drawerLayout.closeDrawer(mnavigationView);
        }
        super.onBackPressed();
    }
}