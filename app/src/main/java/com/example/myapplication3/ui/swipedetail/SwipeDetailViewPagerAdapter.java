package com.example.myapplication3.ui.swipedetail;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
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

    public SwipeDetailViewPagerAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SwipeDetailItemViewBinding binding = SwipeDetailItemViewBinding.inflate(inflater, parent, false);
        return new DetailViewHolder(binding);
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

    public static class DetailViewHolder extends RecyclerView.ViewHolder {

        private final SwipeDetailItemViewBinding binding;

        public DetailViewHolder(SwipeDetailItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
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
    }

}
