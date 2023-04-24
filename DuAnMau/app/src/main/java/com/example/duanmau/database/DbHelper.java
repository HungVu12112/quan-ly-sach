package com.example.duanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "DUANMAU";
    private static final int DB_VERSION =1;
    static final String CREATE_TABLE_THU_THU ="CREATE TABLE THUTHU (MATT PRIMARY KEY,HOTEN TEXT NOT NULL,MATKHAU TEXT NOT NULL)";
    static final String CREATE_TABLE_THANH_VIEN = "CREATE TABLE THANHVIEN (MATV INTEGER PRIMARY KEY AUTOINCREMENT,HOTEN TEXT NOT NULL,NAMSINH TEXT NOT NULL)";
    static final String CREATE_TABLE_LOAI_SACH = "CREATE TABLE LOAISACH (MALOAI INTEGER PRIMARY KEY AUTOINCREMENT,TENLOAI TEXT NOT NULL)";
    static final String CREATE_TABLE_SACH = "CREATE TABLE SACH (MASACH INTEGER PRIMARY KEY AUTOINCREMENT,TENSACH TEXT NOT NULL,GIATHUE INTEGER NOT NULL,MALOAI INTEGER REFERENCES LOAISACH(MALOAI))";
    static final String CREATE_TABLE_PHIEU_MUON ="CREATE TABLE PHIEUMUON(MAPM INTEGER PRIMARY KEY AUTOINCREMENT,MATT TEXT REFERENCES THUTHU(MATT),MATV INTEGER REFERENCES THANHVIEN(MATV),MASACH INTEGER REFERENCES SACH(MASACH),TIENTHUE INTEGER NOT NULL,NGAY DATE NOT NULL,TRASACH INTEGER NOT NULL)";
    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(CREATE_TABLE_THU_THU);
      db.execSQL(CREATE_TABLE_THANH_VIEN);
      db.execSQL(CREATE_TABLE_LOAI_SACH);
      db.execSQL(CREATE_TABLE_SACH);
      db.execSQL(CREATE_TABLE_PHIEU_MUON);
      db.execSQL(Data_SQLlite.INSERT_THU_THU);
      db.execSQL(Data_SQLlite.INSERT_THANH_VIEN);
      db.execSQL(Data_SQLlite.INSERT_LOAI_SACH);
      db.execSQL(Data_SQLlite.INSERT_SACH);
      db.execSQL(Data_SQLlite.INSERT_PHIEU_MUON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableThuThu = "drop table if exists THUTHU";
        db.execSQL(dropTableThuThu);
        String dropTableThanhVien = "drop table if exists THANHVIEN";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists LOAISACH";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = "drop table if exists SACH";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PHIEUMUON";
        db.execSQL(dropTablePhieuMuon);
        onCreate(db);
    }
}
