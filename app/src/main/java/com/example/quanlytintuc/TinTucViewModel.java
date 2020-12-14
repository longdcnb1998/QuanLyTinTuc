package com.example.quanlytintuc;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TinTucViewModel extends AndroidViewModel {

    private TinTucRepository repository;

    public TinTucViewModel(@NonNull Application application) {
        super(application);
        repository = new TinTucRepository(application);
    }

    public LiveData<List<Tintuc>> getAllTinTuc(int id) {
        return repository.getAllTinTuc(id);
    }

    public void insert(Tintuc tintuc) {
        repository.insert(tintuc);
    }


    public void delete(Tintuc tintuc) {
        repository.delete(tintuc);
    }


    public void update(Tintuc tintuc) {
        repository.update(tintuc);
    }

    public void deleteAll(int id) {
        repository.deleteAllTinTuc(id);
    }
}
