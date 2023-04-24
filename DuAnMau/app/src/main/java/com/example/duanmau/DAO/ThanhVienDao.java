package com.example.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.database.DbHelper;
import com.example.duanmau.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDao {
    private SQLiteDatabase db;

    public ThanhVienDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public long insert(ThanhVien obj){
        ContentValues values = new ContentValues();
        values.put("HOTEN",obj.getHoTen());
        values.put("NAMSINH",obj.getNamSinh());
        return db.insert("THANHVIEN",null,values);
    }
    public int update (ThanhVien obj){
        ContentValues values = new ContentValues();
        values.put("HOTEN",obj.getHoTen());
        values.put("NAMSINH",obj.getNamSinh());
        return db.update("THANHVIEN",values,"MATV=?",new String[]{String.valueOf(obj.getMaTV())});
    }
    public int delete(String id){
        return db.delete("THANHVIEN","MATV=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<ThanhVien> getData(String sql, String...selectionArgs){
        List<ThanhVien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            ThanhVien obj = new ThanhVien();
            obj.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("MATV"))));
            obj.setHoTen(c.getString(c.getColumnIndex("HOTEN")));
            obj.setNamSinh(c.getString(c.getColumnIndex("NAMSINH")));
            list.add(obj);
        }
        return list;
    }
    public List<ThanhVien> getAll(){
        String sql = "SELECT * FROM THANHVIEN";
        return getData(sql);
    }
    public ThanhVien getId(String id){
        String sql ="SELECT * FROM THANHVIEN WHERE MATV=?";
        List<ThanhVien> list = getData(sql,id);
        return list.get(0);
    }
}
