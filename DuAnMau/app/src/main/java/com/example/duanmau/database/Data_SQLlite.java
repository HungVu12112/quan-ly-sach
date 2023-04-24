package com.example.duanmau.database;

public class Data_SQLlite {
    public static final String INSERT_THU_THU= "INSERT INTO THUTHU(MATT,HOTEN,MATKHAU) VALUES ('admin','HungAdmin','admin')," +
            "('thanhthao123','ThanhThaoAdmin','thanhthao123')";
    public static final String INSERT_THANH_VIEN = "INSERT INTO THANHVIEN(HOTEN,NAMSINH) VALUES ('Đinh Thị Thuý','2003')," +
            "('Thiều Văn Tú','2003')," +
            "('Trần Duy Hà','2003'),('Âu Vĩnh Đạt','2003')";
    public static final String INSERT_LOAI_SACH ="INSERT INTO LOAISACH(TENLOAI) VALUES ('Tiếng anh cơ bản'),('Lập trình android cơ bản'),('Lập trình android nâng cao'),('Lập trình web cơ bản')";
    public static final String INSERT_SACH ="INSERT INTO SACH (TENSACH,GIATHUE,MALOAI) VALUES ('Tớ Học Lập Trình','2000','1')," +
            "('Lập Trình Và Cuộc Sống','3000','2')," +
            "('Code Dạo Kí Sự','2000','3')";

    public static final String INSERT_PHIEU_MUON ="INSERT INTO PHIEUMUON(MATT,MATV,MASACH,TIENTHUE,NGAY,TRASACH) VALUES ('admin','1','1','2000','2022/10/09',0)," +
            "('admin','2','2','2000','2022/10/11',1)," +
            "('admin','3','3','2000','2022/10/12',0)," +
            "('admin','4','1','2000','2022/10/13',0)," +
            "('admin','2','4','2000','2022/10/14',0)," +
            "('admin','3','2','2000','2022/10/15',1)," +
            "('admin','4','2','2000','2022/10/15',0)," +
            "('admin','2','3','2000','2022/10/16',1)," +
            "('admin','3','4','2000','2022/10/17',0)," +
            "('admin','4','1','2000','2022/10/18',1)," +
            "('admin','2','1','2000','2022/10/19',0)";
}
