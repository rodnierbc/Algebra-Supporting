package com.epicodus.algebrasupporting.ui;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.epicodus.algebrasupporting.R;
import com.epicodus.algebrasupporting.Adapters.ResultSolvePagerAdapter;
import com.epicodus.algebrasupporting.models.SolveResult;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ResultSolveDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private ResultSolvePagerAdapter adapterViewPager;
    ArrayList<SolveResult> mSolveResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_solve_detail);
        ButterKnife.bind(this);

        mSolveResults = Parcels.unwrap(getIntent().getParcelableExtra("solveResult"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new ResultSolvePagerAdapter(getSupportFragmentManager(), mSolveResults);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}

