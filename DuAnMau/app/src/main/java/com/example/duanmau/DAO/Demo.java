package com.example.duanmau.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.database.DbHelper;
import com.example.duanmau.model.ThanhVien;
import com.example.duanmau.model.ThuThu;

public class Demo {
    private SQLiteDatabase db;
    ThuThuDao thuThuDao;
    ThanhVienDao thanhVienDao;
    static final String TAG ="//==========";
    public Demo(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        thanhVienDao = new ThanhVienDao(context);

    }
    public void thanhVien(){
        ThanhVien tV = new ThanhVien(1,"VU HAi HUNg","2003");
//        if (thanhVienDao.insert(tV)>0){
//            Log.i(TAG,"Theem thanhf coong ");
//        }
//        else{
//            Log.i(TAG,"Them thanhf vieen thaaats baij ");
//        }
        Log.i(TAG,"=================");
        Log.i(TAG,"Toong so thanhf vieen "+thanhVienDao.getAll( ).size());

        Log.i(TAG,"=========sau khi sua========");
        tV = new ThanhVien(2,"Dangwe trung tha h ","2003");
        thanhVienDao.update(tV);
        Log.i(TAG,"Toong so thanhf vieen "+thanhVienDao.getAll( ).size());
        thanhVienDao.delete("1");
        Log.i(TAG,"=========sau khi xoa========");
        Log.i(TAG,"Toong so thanhf vieen "+thanhVienDao.getAll( ).size());
    }
    public void thuthu(){
        ThuThu tt = new ThuThu("2","hungadmin","123");
        Log.i(TAG,"=================");
        Log.i(TAG,"Toong so thanhf vieen "+thuThuDao.getAll().size());
        tt = new ThuThu("admin","Hungadmin","123456");
        Log.i(TAG,"=========sua========");
        Log.i(TAG,"Toong so thanhf vieen "+thuThuDao.getAll().size());
    }
}
