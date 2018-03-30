package com.epicodus.algebrasupporting.Adapters;

/**
 * Created by rodnier.borrego on 3/29/18.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.algebrasupporting.models.SolveResult;
import com.epicodus.algebrasupporting.ui.ResultSolveDetailFragment;

import java.util.ArrayList;

public class ResultSolvePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<SolveResult> mSolveResults;

    public ResultSolvePagerAdapter(FragmentManager fm, ArrayList<SolveResult> solveResults) {
        super(fm);
        mSolveResults = solveResults;
    }

    @Override
    public Fragment getItem(int position) {
        return ResultSolveDetailFragment.newInstance(mSolveResults.get(position));
    }

    @Override
    public int getCount() {
        return mSolveResults.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mSolveResults.get(position).getInputInterpretationPlainText();
    }
}
