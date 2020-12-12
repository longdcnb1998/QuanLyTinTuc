package com.example.quanlytintuc;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_tintuc",foreignKeys = @ForeignKey(entity = TheLoai.class,parentColumns = "ma",childColumns = "maTL"),indices = {@Index(value = "maTL")})
public class Tintuc {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ma")
    private int ma;
    @ColumnInfo(name = "maTL")
    private int maTL;
    @ColumnInfo(name = "tieude")
    private String tieude;
    @ColumnInfo(name = "chitiet")
    private String chitiet;
    @ColumnInfo(name = "linkanh")
    private String linkanh;
    @ColumnInfo(name = "theloai")
    private String theloai;
    @ColumnInfo(name = "ngaydang")
    private long ngaydang;

    public Tintuc(int maTL, String tieude, String chitiet, String linkanh, String theloai, long ngaydang) {
        this.maTL = maTL;
        this.tieude = tieude;
        this.chitiet = chitiet;
        this.linkanh = linkanh;
        this.theloai = theloai;
        this.ngaydang = ngaydang;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public int getMaTL() {
        return maTL;
    }

    public void setMaTL(int maTL) {
        this.maTL = maTL;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public String getLinkanh() {
        return linkanh;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public long getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(long ngaydang) {
        this.ngaydang = ngaydang;
    }
}
