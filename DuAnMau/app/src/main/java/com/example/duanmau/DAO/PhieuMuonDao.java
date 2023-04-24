package com.example.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.database.DbHelper;
import com.example.duanmau.model.PhieuMuon;
import com.example.duanmau.model.Sach;
import com.example.duanmau.model.Top;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDao {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public PhieuMuonDao (Context context){
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("MATT",obj.getMaTT());
        values.put("MATV",obj.getMaTV());
        values.put("MASACH",obj.getMaSach());
        values.put("NGAY",sdf.format(obj.getNgay()));
        values.put("TIENTHUE",obj.getTienThue());
        values.put("TRASACH",obj.getTraSach());
        return db.insert("PHIEUMUON",null,values);
    }
    public int update(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("MATT",obj.getMaTT());
        values.put("MATV",obj.getMaTV());
        values.put("MASACH",obj.getMaSach());
        values.put("NGAY",sdf.format(obj.getNgay()));
        values.put("TIENTHUE",obj.getTienThue());
        values.put("TRASACH",obj.getTraSach());
        return db.update("PHIEUMUON",values,"MAPM=?", new String[]{String.valueOf(obj.getMaPM())});
    }
    public int delete(String id){
        return db.delete("PHIEUMUON","MAPM=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<PhieuMuon> getData(String sql, String...selectionArgs){
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            PhieuMuon obj = new PhieuMuon();
            obj.setMaPM(Integer.parseInt(c.getString(c.getColumnIndex("MAPM"))));
            obj.setMaTT(c.getString(c.getColumnIndex("MATT")));
            obj.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("MATV"))));
            obj.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("MASACH"))));
            obj.setTienThue(Integer.parseInt(c.getString(c.getColumnIndex("TIENTHUE"))));
            try {
                obj.setNgay(sdf.parse(c.getString(c.getColumnIndex("NGAY"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            obj.setTraSach(Integer.parseInt(c.getString(c.getColumnIndex("TRASACH"))));
            list.add(obj);
        }
        return list;
    }
    public List<PhieuMuon> getAll(){
        String sql = "SELECT * FROM PHIEUMUON";
        return getData(sql);
    }
    public PhieuMuon getID(String id){
        String sql = "SELECT * FROM PHIEUMUON WHERE MAPM=?";
        List<PhieuMuon> list = getData(sql,id);
        return list.get(0);
    }
    @SuppressLint("Range")
    public List<Top> getTop() {
        String sqlTop = " SELECT MASACH, count(MASACH) as SOLUONG FROM PHIEUMUON GROUP BY MASACH ORDER BY SOLUONG DESC LIMIT 10 ";
        List<Top> list = new ArrayList<Top>();
        SachDao sachDAO = new SachDao(context);
        Cursor c = db.rawQuery(sqlTop, null);
        while (c.moveToNext()) {
            Top top = new Top();
            Sach sach = sachDAO.getID(c.getString(c.getColumnIndex("MASACH")));
            top.setTenSach(sach.getTenSach());
            top.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("SOLUONG"))));
            list.add(top);
        }
        return list;
    }
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT SUM(TIENTHUE) as DOANHTHU FROM PHIEUMUON WHERE NGAY BETWEEN ? AND ?";
        List <Integer> list = new ArrayList < Integer > ( ) ;
        Cursor c = db.rawQuery (sqlDoanhThu, new String[]{tuNgay ,denNgay});
        while (c.moveToNext()){
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("DOANHTHU"))));
            }
            catch (Exception e){
                list.add(0);
            }

        }
        return list.get(0);
    }
}
