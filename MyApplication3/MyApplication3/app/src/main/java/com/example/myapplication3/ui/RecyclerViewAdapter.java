package com.example.myapplication3.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication3.databinding.SwipeBigCardBinding;
import com.example.myapplication3.databinding.SwipeSmallCardBinding;
import com.example.myapplication3.databinding.TabItemViewBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_SMALL = 0;
    private static final int TYPE_BIG = 1;
    private final List<String> data;
    private OnItemClickListener listener;

    // Constructor to initialize the data list
    public RecyclerViewAdapter(List<String> data) {
        this.data = data;
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
        return data.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_SMALL) {
            ((SmallCardHolder) holder).binding.dateTextview.setText(data.get(position));
        } else if (viewType == TYPE_BIG) {
            ((BigCardHolder) holder).binding.dateTextview.setText(data.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return TYPE_SMALL;
        }
        return TYPE_BIG;
    }

    public class SmallCardHolder extends RecyclerView.ViewHolder {
        private final SwipeSmallCardBinding binding;

        public SmallCardHolder(SwipeSmallCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

    public class BigCardHolder extends RecyclerView.ViewHolder {
        private final SwipeBigCardBinding binding;

        public BigCardHolder(SwipeBigCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
