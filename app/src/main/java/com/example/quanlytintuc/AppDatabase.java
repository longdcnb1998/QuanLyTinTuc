package com.example.quanlytintuc;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {TheLoai.class, Tintuc.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase mInstance;

    public abstract TheLoaiDAO theLoaiDAO();

    public abstract TinTucDAO tinTucDAO();


    public static synchronized AppDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context, AppDatabase.class, "qltt.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Log.d("ONCREATE", "Database has been created.");
                            new PopulateDbAsyncTask(mInstance).execute();
                        }

                        @Override
                        public void onOpen(@NonNull SupportSQLiteDatabase db) {
                            super.onOpen(db);
                        }

                        @Override
                        public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                            super.onDestructiveMigration(db);
                        }
                    }).build();
        }
        return mInstance;
    }

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private TheLoaiDAO theLoaiDAO;


        public PopulateDbAsyncTask(AppDatabase mInstance) {
            theLoaiDAO = mInstance.theLoaiDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            theLoaiDAO.insert(new TheLoai("Kinh Doanh", "The Loai tin tuc kinh doanh"));
            theLoaiDAO.insert(new TheLoai("Tai Chinh", "The Loai tin tuc tai chinh"));
            return null;
        }
    }
}
