package com.example.quanlytintuc;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_theloai")
public class TheLoai {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ma")
    private int ma;
    @ColumnInfo(name = "ten")
    private String ten;
    @ColumnInfo(name = "mota")
    private String mota;

    private boolean duocchon = false;

    public boolean isDuocchon() {
        return duocchon;
    }

    public void setDuocchon(boolean duocchon) {
        this.duocchon = duocchon;
    }

    public TheLoai(String ten, String mota) {
        this.ten = ten;
        this.mota = mota;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
