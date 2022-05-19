package com.example.capston;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class menu_select extends Fragment {
//    Fragment fragment1, fragment2;
//    final int NUM_PAGES = 2;
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.menu, container, false);
//
//        fragment1 = new FeedFragment();
//        fragment2 = new ArtistFragment();
//
//        // 툴바에 타이틀 넣기
//        Toolbar toolbar = view.findViewById(R.id.toolbar);
//        toolbar.setTitle("BTS");
//
//        // 뷰페이저에 어댑터 연결
//        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager2);
//        viewPager2.setAdapter(new viewPagerAdapter(this));
//        viewPager2.setCurrentItem(0);
//
//        // 탭과 뷰페이저 연결
//        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
//        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                if(position == 0) {
//                    tab.setText("Feed");
//                } else {
//                    tab.setText("Artist");
//                }
//            }
//        });
//        tabLayoutMediator.attach();
//
//        return view;
//    }
//
//
//    // 뷰페이저2 어댑터
//    private class viewPagerAdapter extends FragmentStateAdapter {
//        public viewPagerAdapter(Fragment fragment) {
//            super(fragment);
//        }
//
//        @NonNull
//        @Override
//        public Fragment createFragment(int position) {
//            if(position == 0) {
//                return fragment1;
//            } else {
//                return fragment2;
//            }
//        }
//
//        @Override
//        public int getItemCount() {
//            return NUM_PAGES;
//        }
//    }

}