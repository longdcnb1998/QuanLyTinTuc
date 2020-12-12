package com.example.quanlytintuc;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TinTucDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Tintuc tintuc);

    @Update
    void update(Tintuc tintuc);

    @Delete
    void delete(Tintuc tintuc);

    @Query("DELETE FROM tb_tintuc ")
    void deleteAll();

    @Query("SELECT * FROM tb_tintuc WHERE ma =:id ORDER BY ngaydang  DESC")
    LiveData<List<Tintuc>> getALLTinTuc(int id);
}
