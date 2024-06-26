package com.example.myapplication3.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication3.databinding.FragmentOneBinding;
import com.example.myapplication3.model.PhotoItem;
import com.example.myapplication3.ui.swipedetail.SwipeDetailActivity;
import com.example.myapplication3.viewmodel.SwipeViewModel;

import java.util.List;


public class FragmentOne extends Fragment {
    private FragmentOneBinding binding;
    private RecyclerViewAdapter<RecyclerView.ViewHolder> recyclerViewAdapter;
    private List<PhotoItem> mediaFiles;
    private SwipeViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOneBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewAdapter = new RecyclerViewAdapter<RecyclerView.ViewHolder>(this.getContext());
        recyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(FragmentOne.this.getContext(), SwipeDetailActivity.class));
                Toast.makeText(FragmentOne.this.getContext(), mediaFiles.get(position).getDisplayName(), Toast.LENGTH_SHORT).show();
            }
        });
        GridLayoutManager gridLayoutManager = getGridLayoutManager();
        binding.recycleView.setLayoutManager(gridLayoutManager);
        binding.recycleView.setAdapter(recyclerViewAdapter);

        viewModel = new SwipeViewModel();
        viewModel.getPhotoItems().observeForever(new Observer<List<PhotoItem>>() {
            @Override
            public void onChanged(List<PhotoItem> photoItems) {
                initReceycler(photoItems);
            }
        });
    }

    @NonNull
    private GridLayoutManager getGridLayoutManager() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(FragmentOne.this.getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (recyclerViewAdapter.getItemViewType(position)) {
                    case RecyclerViewAdapter.TYPE_BIG:
                        return 2;
                    case RecyclerViewAdapter.TYPE_SMALL:
                        return 1;
                    default:
                        return 1;
                }
            }
        });
        return gridLayoutManager;
    }

    private void initReceycler(List<PhotoItem> photoItems) {
        this.mediaFiles = photoItems;
        recyclerViewAdapter.updateView(photoItems);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}