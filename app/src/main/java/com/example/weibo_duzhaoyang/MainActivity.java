package com.example.weibo_duzhaoyang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.weibo_duzhaoyang.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager2 vp;
    TabLayout tab;

    List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        vp = findViewById(R.id.vp);
        tab = findViewById(R.id.tab);
        String[] titles = new String[]{"推荐", "我的"};
        int[] icons = new int[] {R.drawable.recommend_icon, R.drawable.mine_icon};
        fragments = new ArrayList<>();
        MineFragment mineFragment = new MineFragment();
        RecommendFragment recommendFragment = new RecommendFragment();
        fragments.add(recommendFragment);
        fragments.add(mineFragment);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, fragments);
        vp.setAdapter(adapter);
        new TabLayoutMediator(tab, vp, (tab, position) -> {

            tab.setText(titles[position]);
            tab.setIcon(ContextCompat.getDrawable(this, icons[position]));
        }).attach();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}