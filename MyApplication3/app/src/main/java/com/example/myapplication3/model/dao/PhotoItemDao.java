package com.example.myapplication3.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication3.model.PhotoItem;

import java.util.List;

@Dao
public interface PhotoItemDao {
    @Insert
    void insert(List<PhotoItem> photoItems);

    @Delete
    void delete(List<PhotoItem> photoItems);

    @Update
    void update(List<PhotoItem> photoItems);

    @Query("SELECT * from " + PhotoItem.PhotoTabName)
    LiveData<List<PhotoItem>> getAllPhotoItems();

    @Query("SELECT * FROM " + PhotoItem.PhotoTabName + " WHERE _id = :id LIMIT 1")
    LiveData<PhotoItem> getPhotoItemById(int id);

    @Query("SELECT * FROM " + PhotoItem.PhotoTabName + " ORDER BY dateAdded DESC LIMIT 1")
    LiveData<PhotoItem> getNewestPhotoItem();


}
