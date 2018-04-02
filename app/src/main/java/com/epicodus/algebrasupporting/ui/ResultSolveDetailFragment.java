package com.epicodus.algebrasupporting.ui;

/**
 * Created by rodnier.borrego on 3/29/18.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.algebrasupporting.Constants;
import com.epicodus.algebrasupporting.R;
import com.epicodus.algebrasupporting.models.SolveResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultSolveDetailFragment extends Fragment {
    @BindView(R.id.inputInterpretationTextView1) TextView mInputInterpretationTextView1;
    @BindView(R.id.possibleIntermediateStepsTextView) TextView mPossibleIntermediateStepsTextView;
    @BindView(R.id.resultsTextView) TextView mResultsTextView;
    @BindView(R.id.descriptionTextView) TextView mDescriptionTextView;


    private SolveResult mSolveResult;

    public static ResultSolveDetailFragment newInstance(SolveResult mSolveResultModel) {
        ResultSolveDetailFragment resultSolveDetailFragment = new ResultSolveDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("solveResults", Parcels.wrap(mSolveResultModel));
        resultSolveDetailFragment.setArguments(args);
        return resultSolveDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSolveResult = Parcels.unwrap(getArguments().getParcelable("solveResults"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_solve_detail, container, false);
        ButterKnife.bind(this, view);

        mInputInterpretationTextView1.setText(mSolveResult.getInputInterpretationPlainText());
        mPossibleIntermediateStepsTextView.setText(mSolveResult.getPossibleIntermediateStepsPlainText());
        mResultsTextView.setText(mSolveResult.getResultsPlainText());
        mDescriptionTextView.setText(mSolveResult.getDescription());

        return view;
    }

}
