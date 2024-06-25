package com.example.myapplication3.ui.swipedetail;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import java.util.logging.Logger;

public class SwipeDetailCardStackTransformer implements ViewPager2.PageTransformer {
    private static final float SCALE_FACTOR = 0.85f; // 缩放因子
    private static final float ALPHA_FACTOR = 0.5f;  // 透明度因子
    private static final float TRANSLATION_Y_FACTOR = 0.0f; // Y轴位移因子
    private static final float TRANSLATION_X_FACTOR = 1f; // Y轴位移因子

    @Override
    public void transformPage(@NonNull View page, float position) {
        float absPosition = Math.abs(position);
//        Log.d("ql", String.valueOf(position));
        if (position > -1 && position < 0) {

        } else if (position >= 0 && position <= 1) {
            page.setScaleX(SCALE_FACTOR + (1 - SCALE_FACTOR) * (1 - absPosition));
            page.setScaleY(SCALE_FACTOR + (1 - SCALE_FACTOR) * (1 - absPosition));
            page.setAlpha(ALPHA_FACTOR + (1 - ALPHA_FACTOR) * (1 - position));
            page.setTranslationY(-page.getHeight() * TRANSLATION_Y_FACTOR * absPosition);
            page.setTranslationX(-page.getWidth() * TRANSLATION_X_FACTOR * absPosition);
            page.setTranslationZ(-absPosition);
        } else {
            page.setAlpha(0);
            page.setTranslationZ(0);
        }
    }


}
