package com.example.myapplication3.ui;

import com.example.myapplication3.R;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication3.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        BottomNavigationView bottomNavigationView = binding.bottomNavigationView;
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
//                .findFragmentById(binding.navHostFragmentContainer.getId());
//
//        NavController navController = navHostFragment.getNavController();
//        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        viewPagerAdapter = new ViewPagerAdapter(this);
        binding.viewPager.setAdapter(viewPagerAdapter);

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
//                switch (menuItem.getItemId()) {
//                    case R.id.nav_swipe:
//                        binding.viewPager.setCurrentItem(0);
//                        return true;
//                    case R.id.nav_clean:
//                        binding.viewPager.setCurrentItem(1);
//                        return true;
//                    case R.id.nav_secret:
//                        binding.viewPager.setCurrentItem(2);
//                        return true;
//                    default:
//                        return false;
//                }
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
    }
}