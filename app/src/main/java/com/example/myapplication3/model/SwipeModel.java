package com.example.myapplication3.model;

import androidx.lifecycle.LiveData;

import com.example.myapplication3.common.app.MyApp;
import com.example.myapplication3.model.dao.PhotoItemDao;

import java.util.List;

public class SwipeModel {
    private final PhotoItemDao photoItemDao;

    public SwipeModel() {
        photoItemDao = MyApp.getAppDatabase().photoItemDao();
    }

    public LiveData<List<PhotoItem>> getPhotoItems() {
        return photoItemDao.getAllPhotoItems();
    }
}
