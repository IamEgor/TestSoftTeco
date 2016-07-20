package com.example.yegor.testsoftteco.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yegor.testsoftteco.R;
import com.example.yegor.testsoftteco.view.GridPagerContent;
import com.example.yegor.testsoftteco.view.GridPagerItemAttributes;

import java.util.ArrayList;
import java.util.List;


public class PagerContentFragment extends Fragment implements GridPagerContent.OnChoose {

    public static final String PARAMS = "PARAMS";

    private GridPagerContent grid;

    public static PagerContentFragment newInstance(List<GridPagerItemAttributes> itemParams) {


        Bundle args = new Bundle();
        args.putParcelableArrayList(PARAMS, new ArrayList<>(itemParams));

        PagerContentFragment fragment = new PagerContentFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        grid = (GridPagerContent) inflater.inflate(R.layout.item_kind_of_grid, container, false);
        grid.setOnChoose(this);

        ArrayList<GridPagerItemAttributes> parcelableArrayList = getArguments().getParcelableArrayList(PARAMS);
        grid.setParams(parcelableArrayList);

        return grid;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onToggleChoose(int id, int position) {
        Toast.makeText(getContext(), "id - " + id + ", position = " + position, Toast.LENGTH_SHORT).show();
    }

}
