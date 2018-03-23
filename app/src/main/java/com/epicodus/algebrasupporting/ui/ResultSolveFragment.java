package com.epicodus.algebrasupporting.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.algebrasupporting.R;
import com.epicodus.algebrasupporting.models.SolveResult;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultSolveFragment extends Fragment {

    @BindView(R.id.inputInterpretationTitleTextView) TextView mInputInterpretationTitleTextView;
    @BindView(R.id.inputInterpretationImageImageView) TextView mInputInterpretationImageImageView;
    @BindView(R.id.resultsTitleTextView) TextView mResultsTitleTextView;
    @BindView(R.id.resultsImageImageView) TextView mResultsImageImageView;

    private ArrayList<String> mSolveResultArrayList;
    private SolveResult mSolveResult;

    public static ResultSolveFragment newInstance(ArrayList solveResult) {
        ResultSolveFragment resultSolveFragment = new ResultSolveFragment();
        Bundle args = new Bundle();
        args.putParcelable("solveResult", Parcels.wrap(solveResult));
        resultSolveFragment.setArguments(args);
        return resultSolveFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSolveResultArrayList = Parcels.unwrap(getArguments().getParcelable("solveResult"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_solve_step, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mRestaurant.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        mNameLabel.setText(mRestaurant.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mRestaurant.getCategories()));
        mRatingLabel.setText(Double.toString(mRestaurant.getRating()) + "/5");
        mPhoneLabel.setText(mRestaurant.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mRestaurant.getAddress()));

        return view;
    }
}