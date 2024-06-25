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

public class SwipeDetailActivity extends AppCompatActivity implements SwipeDetailViewPagerAdapter.OnSwipeListener {
    private ActivitySwipeDetailBinding activitySwipeDetailBinding;
    private SwipeViewModel viewModel;
    private SwipeDetailViewPagerAdapter swipeDetailViewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activitySwipeDetailBinding = ActivitySwipeDetailBinding.inflate(getLayoutInflater());

        setContentView(activitySwipeDetailBinding.getRoot());

        swipeDetailViewPagerAdapter = new SwipeDetailViewPagerAdapter(this, this);
//        activitySwipeDetailBinding.swipeViewPager.setUserInputEnabled(false);
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

    @Override
    public void onSwipedLeft(int position) {
//        左滑删除
        // 左滑处理逻辑：滑动到下一个 item
        int nextItem = position + 1;
        if (nextItem < swipeDetailViewPagerAdapter.getItemCount()) {
            activitySwipeDetailBinding.swipeViewPager.setCurrentItem(nextItem, true);
        }
    }

    @Override
    public void onSwipedRight(int position) {
//        右滑保存
        int nextItem = position + 1;
        if (nextItem < swipeDetailViewPagerAdapter.getItemCount()) {
            activitySwipeDetailBinding.swipeViewPager.setCurrentItem(nextItem, true);
        }
    }

    @Override
    public void onSwipeProgress(float progress) {
        activitySwipeDetailBinding.swipeViewPager.fakeDragBy(-progress * activitySwipeDetailBinding.swipeViewPager.getWidth());
    }

    @Override
    public void onSwipeBegin() {
        activitySwipeDetailBinding.swipeViewPager.beginFakeDrag();
    }

    @Override
    public void onSwipeEnd() {
        activitySwipeDetailBinding.swipeViewPager.endFakeDrag();
    }
}
