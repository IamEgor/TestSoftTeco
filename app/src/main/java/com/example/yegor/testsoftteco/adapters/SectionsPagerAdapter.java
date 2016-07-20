package com.example.yegor.testsoftteco.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yegor.testsoftteco.fragments.PagerContentFragment;
import com.example.yegor.testsoftteco.view.GridPagerContent;
import com.example.yegor.testsoftteco.view.GridPagerItemAttributes;

import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private List<GridPagerItemAttributes> params;
    private int tabs_count;

    public SectionsPagerAdapter(FragmentManager fm, List<GridPagerItemAttributes> params) {
        super(fm);

        this.params = params;
        tabs_count = (int) Math.ceil(1. * params.size() / GridPagerContent.MAX_ELEMENTS);
    }

    @Override
    public Fragment getItem(int position) {

        int elementsCount = params.size();

        int start = position * GridPagerContent.MAX_ELEMENTS;
        int finish = start + GridPagerContent.MAX_ELEMENTS < elementsCount ?
                start + GridPagerContent.MAX_ELEMENTS : elementsCount;

        return PagerContentFragment.newInstance(params.subList(start, finish));
    }

    @Override
    public int getCount() {
        return tabs_count;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

}