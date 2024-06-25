package com.example.myapplication3.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication3.model.PhotoItem;
import com.example.myapplication3.model.SwipeModel;

import java.util.List;

public class SwipeViewModel extends ViewModel {
    private final SwipeModel model;

    public SwipeViewModel() {
        model = new SwipeModel();
    }

    public LiveData<List<PhotoItem>> getPhotoItems() {
        return model.getPhotoItems();
    }

    public void updatePhotoItems(List<PhotoItem> items) {
        
    }
}
