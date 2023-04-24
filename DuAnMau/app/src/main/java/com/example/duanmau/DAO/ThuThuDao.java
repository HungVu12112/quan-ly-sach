package com.example.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.database.DbHelper;
import com.example.duanmau.model.ThanhVien;
import com.example.duanmau.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDao {
    private SQLiteDatabase db;
    public ThuThuDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(ThuThu obj){
        ContentValues values = new ContentValues();
        values.put("MATT",obj.getMaTT());
        values.put("HOTEN",obj.getHoTen());
        values.put("MATKHAU",obj.getMatKhau());
        return db.insert("THUTHU",null,values);
    }
    public int updatePass(ThuThu obj){
        ContentValues values = new ContentValues();
        values.put("HOTEN",obj.getHoTen());
        values.put("MATKHAU",obj.getMatKhau());
        return db.update("THUTHU",values,"MATT=?",new String[]{obj.getMaTT()});
    }
    public int delete(String id){
        return db.delete("THUTHU","MATT",new String[]{id});
    }
    @SuppressLint("Range")
    public List<ThuThu> getData(String sql, String...selectionArgs){
        List<ThuThu> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            ThuThu obj = new ThuThu();
            obj.setMaTT(c.getString(c.getColumnIndex("MATT")));
            obj.setHoTen(c.getString(c.getColumnIndex("HOTEN")));
            obj.setMatKhau(c.getString(c.getColumnIndex("MATKHAU")));
            Log.i("///====",obj.toString());
            list.add(obj);
        }
        return list;
    }
    public List<ThuThu> getAll(){
        String sql = "SELECT * FROM THUTHU";
        return getData(sql);
    }
    public ThuThu getId(String id){
        String sql ="SELECT * FROM THUTHU WHERE MATT=?";
        List<ThuThu> list = getData(sql,id);
        return list.get(0);
    }
    public int checkLogin(String id,String password){
        String sql = " SELECT * FROM THUTHU WHERE MATT=? AND MATKHAU=?" ;
        List <ThuThu> list =getData(sql,id,password) ;
        if (list.size()==0)
            return -1 ;
        return 1 ;
    }
}
