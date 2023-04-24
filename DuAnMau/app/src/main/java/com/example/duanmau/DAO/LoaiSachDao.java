package com.example.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.database.DbHelper;
import com.example.duanmau.model.LoaiSach;
import com.example.duanmau.model.Sach;
import com.example.duanmau.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDao {
    private SQLiteDatabase db;
    public LoaiSachDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(LoaiSach obj){
        ContentValues values = new ContentValues();
        values.put("TENLOAI",obj.getTenLoai());
        return db.insert("LOAISACH",null,values);
    }
    public int update (LoaiSach obj){
        ContentValues values = new ContentValues();
        values.put("TENLOAI",obj.getTenLoai());
        return db.update("LOAISACH",values,"MALOAI=?",new String[]{String.valueOf(obj.getMaLoai())});
    }
    public int delete(String id){
        return db.delete("LOAISACH","MALOAI=?", new String[]{id});
    }
    @SuppressLint("Range")
    public List<LoaiSach> getData(String sql, String...selectionArgs){
        List<LoaiSach> list = new ArrayList<LoaiSach>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            LoaiSach obj = new LoaiSach();
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("MALOAI"))));
            obj.setTenLoai(c.getString(c.getColumnIndex("TENLOAI")));
            list.add(obj);
        }
        return list;
    }
    public List<LoaiSach> getAll(){
        String sql = "SELECT * FROM LOAISACH";
        return getData(sql);
    }
    public LoaiSach getID(String id){
        String sql = "SELECT * FROM LOAISACH WHERE MALOAI=?";
        List<LoaiSach> list = getData(sql,id);
        return list.get(0);
    }

}
