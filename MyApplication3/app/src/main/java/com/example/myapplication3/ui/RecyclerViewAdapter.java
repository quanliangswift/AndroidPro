package com.example.myapplication3.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication3.common.app.MyApp;
import com.example.myapplication3.common.utils.GlideUtils;
import com.example.myapplication3.databinding.SwipeBigCardBinding;
import com.example.myapplication3.databinding.SwipeSmallCardBinding;
import com.example.myapplication3.databinding.TabItemViewBinding;
import com.example.myapplication3.model.PhotoItem;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_SMALL = 0;
    public static final int TYPE_BIG = 1;
    private List<PhotoItem> mediaFiles = new ArrayList<>();
    private OnItemClickListener listener;

    // Constructor to initialize the data list


    public void updateView(List<PhotoItem> mediaFiles) {
        this.mediaFiles = mediaFiles;
        
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_SMALL) {
            SwipeSmallCardBinding binding = SwipeSmallCardBinding.inflate(inflater, parent, false);
            SmallCardHolder smallCardHolder = new SmallCardHolder(binding);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = smallCardHolder.getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            return smallCardHolder;
        } else {
            SwipeBigCardBinding binding = SwipeBigCardBinding.inflate(inflater, parent, false);
            BigCardHolder bigCardHolder = new BigCardHolder(binding);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = bigCardHolder.getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            return bigCardHolder;
        }

    }

    @Override
    public int getItemCount() {
        return mediaFiles.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        PhotoItem photoItem = mediaFiles.get(position);
        if (viewType == TYPE_SMALL) {
            ((SmallCardHolder) holder).setData(photoItem, position);
        } else if (viewType == TYPE_BIG) {
            ((BigCardHolder) holder).setData(photoItem, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 0) {
            return TYPE_BIG;
        }
        return TYPE_SMALL;
    }

    public class SmallCardHolder extends RecyclerView.ViewHolder {
        private final SwipeSmallCardBinding binding;

        public SmallCardHolder(SwipeSmallCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(PhotoItem photoItem, int position) {
            binding.dateTextview.setText(photoItem.getDisplayName());
            if (position % 2 == 0) {
                binding.imgPhoto.setVisibility(View.INVISIBLE);
                binding.imgPhoto2.setVisibility(View.INVISIBLE);
                binding.imageView2.setVisibility(View.VISIBLE);
            } else {
                binding.imageView2.setVisibility(View.INVISIBLE);
                binding.imgPhoto.setVisibility(View.VISIBLE);
                binding.imgPhoto2.setVisibility(View.VISIBLE);
                GlideUtils.load(MyApp.getmContext(), photoItem.getData(), binding.imgPhoto);
                GlideUtils.load(MyApp.getmContext(), photoItem.getData(), binding.imgPhoto2);
            }
        }
    }

    public class BigCardHolder extends RecyclerView.ViewHolder {
        private final SwipeBigCardBinding binding;

        public BigCardHolder(SwipeBigCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(PhotoItem photoItem, int position) {
            binding.dateTextview.setText(photoItem.getDisplayName());
        }
    }
}
