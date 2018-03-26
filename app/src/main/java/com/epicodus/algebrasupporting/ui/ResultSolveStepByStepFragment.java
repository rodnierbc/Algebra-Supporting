package com.epicodus.algebrasupporting.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.algebrasupporting.Constants;
import com.epicodus.algebrasupporting.R;
import com.epicodus.algebrasupporting.models.SolveResult;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultSolveStepByStepFragment extends Fragment {

    @BindView(R.id.possibleIntermediateStepsTextView) TextView mPossibleIntermediateStepsTextView;
    @BindView(R.id.resultSolveStepByStepImageView) ImageView mResultSolveStepByStepImageView;

    private ArrayList<ArrayList<String>> mSolveResultStepByStepArrayList;
    private SolveResult mSolveResult;

    public static ResultSolveStepByStepFragment newInstance(ArrayList<ArrayList<String>> mSolveResultStepByStepArrayList) {
        ResultSolveStepByStepFragment resultSolveStepByStepFragment = new ResultSolveStepByStepFragment();
        Bundle args = new Bundle();
        args.putParcelable("solveResultStepByStep", Parcels.wrap(mSolveResultStepByStepArrayList));
        resultSolveStepByStepFragment.setArguments(args);
        return resultSolveStepByStepFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSolveResultStepByStepArrayList = Parcels.unwrap(getArguments().getParcelable("solveResultStepByStep"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_solve_step_by_step, container, false);
        ButterKnife.bind(this, view);

        for(int i=0; i<mSolveResultStepByStepArrayList.size(); i++){
            for(int j=0; j<mSolveResultStepByStepArrayList.get(i).size(); j++) {
                if (mSolveResultStepByStepArrayList.get(i).get(j).equals(Constants.WOLFRAM_ALPHA_POSSIBLE_INTERMEDIATE_STEPS_TITLE)) {
                    mPossibleIntermediateStepsTextView.setText(mSolveResultStepByStepArrayList.get(i).get(j));
                    //Picasso.with(view.getContext()).load(mSolveResultArrayList.get(i).get(j+1)).into(mInputInterpretationImageImageView);
                    Picasso.with(view.getContext())
                            .load(mSolveResultStepByStepArrayList.get(i).get(j + 1))
//                            .resize(1500, 150)
//                            .centerCrop()
                            .into(mResultSolveStepByStepImageView);
                }
            }

        }
        return view;
    }
}
