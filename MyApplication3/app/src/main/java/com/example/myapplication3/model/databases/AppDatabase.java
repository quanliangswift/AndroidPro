package com.example.myapplication3.model.databases;

import androidx.lifecycle.Observer;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication3.common.Helper.MediaHelper;
import com.example.myapplication3.common.app.MyApp;
import com.example.myapplication3.model.PhotoItem;
import com.example.myapplication3.model.dao.PhotoItemDao;

import java.util.List;

@Database(entities = {PhotoItem.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public final static String databaseName = "app_database";

    public abstract PhotoItemDao photoItemDao();

    public static void scanMediaFiles() {
        PhotoItemDao dao = MyApp.getAppDatabase().photoItemDao();
        dao.getNewestPhotoItem().observeForever(new Observer<PhotoItem>() {
            @Override
            public void onChanged(PhotoItem photoItem) {
                long startData = photoItem == null ? 0 : photoItem.getDateAdded();
                List<PhotoItem> newPhotoItems = MediaHelper.getMediaFiles(startData);
                if (!newPhotoItems.isEmpty()) {
                    DatabaseExecutor.getExecutor().execute(new Runnable() {
                        @Override
                        public void run() {
                            dao.insert(newPhotoItems);
                        }
                    });
                }
            }
        });
    }
}
