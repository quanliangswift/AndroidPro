package com.example.myapplication3.ui;

import com.example.myapplication3.R;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication3.databinding.ActivityMainBinding;
import com.example.myapplication3.model.databases.AppDatabase;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 123; // 定义权限请求代码

    private ActivityMainBinding binding;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewPagerAdapter = new ViewPagerAdapter(this);
        binding.viewPager.setAdapter(viewPagerAdapter);
        binding.viewPager.setUserInputEnabled(false);
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.nav_swipe) {
                    binding.viewPager.setCurrentItem(0);
                    return true;
                } else if (menuItem.getItemId() == R.id.nav_clean) {
                    binding.viewPager.setCurrentItem(1);
                    return true;
                } else if (menuItem.getItemId() == R.id.nav_secret) {
                    binding.viewPager.setCurrentItem(2);
                    return true;
                }
                return false;
            }
        });

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        binding.bottomNavigationView.setSelectedItemId(R.id.nav_swipe);
                        break;
                    case 1:
                        binding.bottomNavigationView.setSelectedItemId(R.id.nav_clean);
                        break;
                    case 2:
                        binding.bottomNavigationView.setSelectedItemId(R.id.nav_secret);
                        break;
                }
            }
        });
        checkPermissions();
    }

    private void checkPermissions() {
        String[] permissions;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions = new String[]{
                    Manifest.permission.READ_MEDIA_VIDEO,
                    Manifest.permission.READ_MEDIA_IMAGES,
                    Manifest.permission.READ_MEDIA_AUDIO
            };
        } else {
            permissions = new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
        }
        ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }
            if (allGranted) {
                // 权限已授予，执行你的逻辑
                AppDatabase.scanMediaFiles();
            } else {
                // 权限被拒绝，显示相应的提示
            }
        }
    }
}