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
        new UpdateStudentAsyncTask(tinTucDAO).execute(tintuc);
    }

    public void delete(Tintuc tintuc) {
        new DeleteStudentAsyncTask(tinTucDAO).execute(tintuc);
    }

    public void deleteAllTheLoai() {
        new DeleteAllStudentAsyncTask(tinTucDAO).execute();
    }

    public LiveData<List<Tintuc>> getAllTinTuc(int id) {
        return tinTucDAO.getALLTinTuc(id);
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

    private static class UpdateStudentAsyncTask extends AsyncTask<Tintuc, Void, Void> {

        private TinTucDAO tinTucDAO;

        public UpdateStudentAsyncTask(TinTucDAO tinTucDAO) {
            this.tinTucDAO = tinTucDAO;
        }

        @Override
        protected Void doInBackground(Tintuc... tintucs) {
            tinTucDAO.update(tintucs[0]);
            return null;
        }
    }

    private static class DeleteStudentAsyncTask extends AsyncTask<Tintuc, Void, Void> {

        private TinTucDAO tinTucDAO;

        public DeleteStudentAsyncTask(TinTucDAO tinTucDAO) {
            this.tinTucDAO = tinTucDAO;
        }

        @Override
        protected Void doInBackground(Tintuc... tintucs) {
            tinTucDAO.delete(tintucs[0]);
            return null;
        }
    }

    private static class DeleteAllStudentAsyncTask extends AsyncTask<Void, Void, Void> {

        private TinTucDAO tinTucDAO;

        public DeleteAllStudentAsyncTask(TinTucDAO tinTucDAO) {
            this.tinTucDAO = tinTucDAO;
        }

        @Override
        protected Void doInBackground(Void... Void) {
            tinTucDAO.deleteAll();
            return null;
        }
    }

}
