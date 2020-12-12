package com.example.quanlytintuc;


import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TheLoaiRepository {
    private TheLoaiDAO theLoaiDAO;
    private LiveData<List<TheLoai>> allTheLoai;

    public TheLoaiRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        theLoaiDAO = database.theLoaiDAO();
        allTheLoai = theLoaiDAO.getALLTheLoai();
    }

    public void insert(TheLoai theLoai) {
        new InsertTheLoaiAsyncTask(theLoaiDAO).execute(theLoai);
    }

    public void update(TheLoai theLoai) {
        new UpdateTheLoaiAsyncTask(theLoaiDAO).execute(theLoai);
    }

    public void delete(TheLoai theLoai) {
        new DeleteTheLoaiAsyncTask(theLoaiDAO).execute(theLoai);
    }

    public void deleteAllTheLoai() {
        new DeleteAllTheLoaiAsyncTask(theLoaiDAO).execute();
    }

    public LiveData<List<TheLoai>> getAllTheLoai() {
        return allTheLoai;
    }

    private static class InsertTheLoaiAsyncTask extends AsyncTask<TheLoai, Void, Void> {

        private TheLoaiDAO theLoaiDAO;

        public InsertTheLoaiAsyncTask(TheLoaiDAO theLoaiDAO) {
            this.theLoaiDAO = theLoaiDAO;
        }

        @Override
        protected Void doInBackground(TheLoai... theLoais) {
            theLoaiDAO.insert(theLoais[0]);
            return null;
        }
    }

    private static class UpdateTheLoaiAsyncTask extends AsyncTask<TheLoai, Void, Void> {

        private TheLoaiDAO theLoaiDAO;

        public UpdateTheLoaiAsyncTask(TheLoaiDAO theLoaiDAO) {
            this.theLoaiDAO = theLoaiDAO;
        }

        @Override
        protected Void doInBackground(TheLoai... theLoais) {
            theLoaiDAO.update(theLoais[0]);
            return null;
        }
    }

    private static class DeleteTheLoaiAsyncTask extends AsyncTask<TheLoai, Void, Void> {

        private TheLoaiDAO theLoaiDAO;

        public DeleteTheLoaiAsyncTask(TheLoaiDAO theLoaiDAO) {
            this.theLoaiDAO = theLoaiDAO;
        }

        @Override
        protected Void doInBackground(TheLoai... theLoais) {
            theLoaiDAO.delete(theLoais[0]);
            return null;
        }
    }

    private static class DeleteAllTheLoaiAsyncTask extends AsyncTask<Void, Void, Void> {

        private TheLoaiDAO theLoaiDAO;

        public DeleteAllTheLoaiAsyncTask(TheLoaiDAO theLoaiDAO) {
            this.theLoaiDAO = theLoaiDAO;
        }

        @Override
        protected Void doInBackground(Void... Void) {
            theLoaiDAO.deleteAll();
            return null;
        }
    }

}
