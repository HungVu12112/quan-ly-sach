package com.example.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.database.DbHelper;
import com.example.duanmau.model.LoaiSach;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDao {
    private SQLiteDatabase db;
    public SachDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(Sach obj){
        ContentValues values = new ContentValues();
        values.put("TENSACH",obj.getTenSach());
        values.put("GIATHUE",obj.getGiaThue());
        values.put("MALOAI",obj.getMaLoai());
        return db.insert("SACH",null,values);
    }
    public int update(Sach obj){
        ContentValues values = new ContentValues();
        values.put("TENSACH",obj.getTenSach());
        values.put("GIATHUE",obj.getGiaThue());
        values.put("MALOAI",obj.getMaSach());
        return db.update("SACH",values,"MASACH=?", new String[]{String.valueOf(obj.getMaSach())});
    }
    public int delete(String id){
        return db.delete("SACH","MASACH=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<Sach> getData(String sql, String...selectionArgs){
        List<Sach> list = new ArrayList<Sach>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            Sach obj = new Sach();
            obj.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("MASACH"))));
            obj.setTenSach(c.getString(c.getColumnIndex("TENSACH")));
            obj.setGiaThue(Integer.parseInt(c.getString(c.getColumnIndex("GIATHUE"))));
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("MALOAI"))));
            list.add(obj);
        }
        return list;
    }
    public List<Sach> getAll(){
        String sql = "SELECT * FROM SACH";
        return getData(sql);
    }
    public Sach getID(String id){
        String sql = "SELECT * FROM SACH WHERE MASACH=?";
        List<Sach> list = getData(sql,id);
        return list.get(0);
    }
    public ArrayList<Sach> getSach(){
        ArrayList<Sach> list = new ArrayList<>();
        Cursor cursor =db.rawQuery("SELECT * FROM SACH",null);
        if (cursor.getCount()!=0){
            while (cursor.moveToNext()){
                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)));
            }
        }
        return list;
    }
    public ArrayList<LoaiSach> getIdSach(String id){
        ArrayList<LoaiSach> list = new ArrayList<>();
        Cursor cursor =db.rawQuery("SELECT * FROM LOAISACH WHERE MALOAI =?",new String[]{id});
        if (cursor.getCount()!=0){
            while (cursor.moveToNext()){
                list.add(new LoaiSach(cursor.getInt(0),cursor.getString(1)));
            }

        }
        return list;
    }
}
