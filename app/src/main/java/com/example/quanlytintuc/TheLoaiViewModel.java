package com.example.quanlytintuc;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.quanlytintuc.TheLoai;
import com.example.quanlytintuc.TheLoaiRepository;

import java.util.List;

public class TheLoaiViewModel extends AndroidViewModel {

    private TheLoaiRepository repository;
    private LiveData<List<TheLoai>> data;
    List<TheLoai> listTheLoai;

    public TheLoaiViewModel(@NonNull Application application) {
        super(application);
        repository = new TheLoaiRepository(application);
    }

    public LiveData<List<TheLoai>> getTheLoais() {
        return repository.getAllTheLoai();
    }

    public void insert(TheLoai theLoai) {
        repository.insert(theLoai);
    }


    public void delete(TheLoai theLoai) {
        repository.delete(theLoai);
    }

    public void update(TheLoai theLoai) {
        repository.update(theLoai);
    }

    public void deleteAll() {
        repository.deleteAllTheLoai();
    }
}
