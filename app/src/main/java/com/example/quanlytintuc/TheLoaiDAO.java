package com.example.quanlytintuc;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quanlytintuc.TheLoai;

import java.util.List;

@Dao
public interface TheLoaiDAO {

    @Insert()
    void insert(TheLoai theLoai);

    @Update
    void update(TheLoai theLoai);

    @Delete
    void delete(TheLoai theLoai);

    @Query("DELETE FROM tb_theloai ")
    void deleteAll();

    @Query("SELECT * FROM tb_theloai ")
    LiveData<List<TheLoai>> getALLTheLoai();
}
