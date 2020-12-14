package com.example.quanlytintuc;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TinTucRepository {
    private TinTucDAO tinTucDAO;

    public TinTucRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        tinTucDAO = database.tinTucDAO();
    }

    public void insert(Tintuc tintuc) {
        new InsertTinTucAsyncTask(tinTucDAO).execute(tintuc);
    }

    public void update(Tintuc tintuc) {
        new UpdateTinTucAsyncTask(tinTucDAO).execute(tintuc);
    }

    public void delete(Tintuc tintuc) {
        new DeleteTinTucAsyncTask(tinTucDAO).execute(tintuc);
    }

    public LiveData<List<Tintuc>> getAllTinTuc(int id) {
        return tinTucDAO.getALLTinTuc(id);
    }

    public void deleteAllTinTuc(int id) {
        tinTucDAO.deleteAll(id);
    }

    private static class InsertTinTucAsyncTask extends AsyncTask<Tintuc, Void, Void> {

        private TinTucDAO tinTucDAO;

        public InsertTinTucAsyncTask(TinTucDAO tinTucDAO) {
            this.tinTucDAO = tinTucDAO;
        }

        @Override
        protected Void doInBackground(Tintuc... tintucs) {
            tinTucDAO.insert(tintucs[0]);
            return null;
        }
    }

    private static class UpdateTinTucAsyncTask extends AsyncTask<Tintuc, Void, Void> {

        private TinTucDAO tinTucDAO;

        public UpdateTinTucAsyncTask(TinTucDAO tinTucDAO) {
            this.tinTucDAO = tinTucDAO;
        }

        @Override
        protected Void doInBackground(Tintuc... tintucs) {
            tinTucDAO.update(tintucs[0]);
            return null;
        }
    }

    private static class DeleteTinTucAsyncTask extends AsyncTask<Tintuc, Void, Void> {

        private TinTucDAO tinTucDAO;

        public DeleteTinTucAsyncTask(TinTucDAO tinTucDAO) {
            this.tinTucDAO = tinTucDAO;
        }

        @Override
        protected Void doInBackground(Tintuc... tintucs) {
            tinTucDAO.delete(tintucs[0]);
            return null;
        }
    }


}
