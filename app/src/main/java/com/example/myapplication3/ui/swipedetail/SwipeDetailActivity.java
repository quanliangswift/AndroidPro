package com.example.myapplication3.ui.swipedetail;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.myapplication3.databinding.ActivitySwipeDetailBinding;
import com.example.myapplication3.model.PhotoItem;
import com.example.myapplication3.viewmodel.SwipeViewModel;
import com.example.myapplication3.ui.swipedetail.SwipeDetailViewPagerAdapter;

import java.util.List;

public class SwipeDetailActivity extends AppCompatActivity {
    private ActivitySwipeDetailBinding activitySwipeDetailBinding;
    private SwipeViewModel viewModel;
    private SwipeDetailViewPagerAdapter swipeDetailViewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activitySwipeDetailBinding = ActivitySwipeDetailBinding.inflate(getLayoutInflater());

        setContentView(activitySwipeDetailBinding.getRoot());

        swipeDetailViewPagerAdapter = new SwipeDetailViewPagerAdapter(this);
        activitySwipeDetailBinding.swipeViewPager.setAdapter(swipeDetailViewPagerAdapter);
        activitySwipeDetailBinding.swipeViewPager.setPageTransformer(new SwipeDetailCardStackTransformer());
        viewModel = new SwipeViewModel();
        viewModel.getPhotoItems().observeForever(new Observer<List<PhotoItem>>() {
            @Override
            public void onChanged(List<PhotoItem> photoItems) {
                initReceycler(photoItems);
            }
        });
    }

    private void initReceycler(List<PhotoItem> photoItems) {
        swipeDetailViewPagerAdapter.udateData(photoItems);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activitySwipeDetailBinding = null;
    }
}
