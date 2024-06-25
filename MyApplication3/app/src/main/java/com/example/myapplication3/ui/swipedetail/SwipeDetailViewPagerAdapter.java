package com.example.myapplication3.ui.swipedetail;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication3.common.app.MyApp;
import com.example.myapplication3.common.utils.GlideUtils;
import com.example.myapplication3.databinding.SwipeDetailItemViewBinding;
import com.example.myapplication3.model.PhotoItem;


import java.util.ArrayList;
import java.util.List;


public class SwipeDetailViewPagerAdapter extends RecyclerView.Adapter<SwipeDetailViewPagerAdapter.DetailViewHolder> {

    List<PhotoItem> datas = new ArrayList<>();
    private final Context context;
    private final LayoutInflater inflater;
    private final OnSwipeListener onSwipeListener;

    public SwipeDetailViewPagerAdapter(Context context, OnSwipeListener onSwipeListener) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.onSwipeListener = onSwipeListener;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SwipeDetailItemViewBinding binding = SwipeDetailItemViewBinding.inflate(inflater, parent, false);
        return new DetailViewHolder(binding, onSwipeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        PhotoItem photoItem = datas.get(position);
        holder.setData(photoItem, position);
    }

    public void udateData(List<PhotoItem> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public interface OnSwipeListener {
        void onSwipedLeft(int position);

        void onSwipedRight(int position);

        void onSwipeProgress(float progress);

        void onSwipeBegin();

        void onSwipeEnd();
    }

    public static class DetailViewHolder extends RecyclerView.ViewHolder {

        private final SwipeDetailItemViewBinding binding;
        private final OnSwipeListener onSwipeListener;
        private float downX;
        private float downY;
        private boolean isDragging = false;

        public DetailViewHolder(SwipeDetailItemViewBinding binding, OnSwipeListener onSwipeListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.onSwipeListener = onSwipeListener;
//            itemView.setOnTouchListener(this);
        }

        public void setData(PhotoItem photoItem, int position) {
            if (photoItem.getMediaType() == MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE) {
                binding.imageView.setVisibility(View.VISIBLE);
                binding.videoView.setVisibility(View.GONE);
                GlideUtils.load(MyApp.getmContext(), photoItem.getData(), binding.imageView);
            } else {
                binding.imageView.setVisibility(View.GONE);
                binding.videoView.setVisibility(View.VISIBLE);
                binding.videoView.setVideoPath(photoItem.getData());
                binding.videoView.start();
            }
        }

//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
////            return false;
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    downX = event.getX();
//                    downY = event.getY();
//                    isDragging = false;
//                    onSwipeListener.onSwipeBegin();
//                    return true;
//                case MotionEvent.ACTION_MOVE:
//                    float deltaX = event.getX() - downX;
//                    v.setTranslationX(deltaX);
//                    float deltaY = event.getY() - downY;
//                    v.setTranslationY(deltaY);
//                    onSwipeListener.onSwipeProgress(deltaX / v.getWidth());
//                    return true;
//                case MotionEvent.ACTION_UP:
//                case MotionEvent.ACTION_CANCEL:
//                    onSwipeListener.onSwipeEnd();
//                    float finalDeltaX = event.getX() - downX;
//                    if (Math.abs(finalDeltaX) > ((float) v.getWidth() / 4)) {
//                        boolean isRightSwipe;
//                        if (finalDeltaX > 0) {
//                            isRightSwipe = true;
//                        } else {
//                            isRightSwipe = false;
//                        }
//                        float targetX = isRightSwipe ? v.getWidth() : -v.getWidth();
//                        v.animate().translationX(targetX).setDuration(200).withEndAction(() -> {
//                            v.setTranslationX(0);
//                            v.setTranslationY(0);
//                        }).start();
//                        if (isRightSwipe) {
//                            onSwipeListener.onSwipedRight(getAdapterPosition());
//                        } else {
//                            onSwipeListener.onSwipedLeft(getAdapterPosition());
//                        }
//
//                    } else {
//                        v.animate().translationX(0).translationY(0).setDuration(100).start();
//                    }
//                    return true;
//            }
//            return false;
//        }
    }


}
