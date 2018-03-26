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

public class ResultSolveStepFragment extends Fragment {

    @BindView(R.id.inputInterpretationTitleTextView) TextView mInputInterpretationTitleTextView;
    @BindView(R.id.inputInterpretationImageImageView) ImageView mInputInterpretationImageImageView;
    @BindView(R.id.resultsTitleTextView) TextView mResultsTitleTextView;
    @BindView(R.id.resultsImageImageView) ImageView mResultsImageImageView;
    @BindView(R.id.plotTitleTextView) TextView mPlotTitleTextView;
    @BindView(R.id.plotImageImageImageView) ImageView mPlotImageImageImageView;

    private ArrayList<ArrayList<String>> mSolveResultArrayList;
    private SolveResult mSolveResult;

    public static ResultSolveStepFragment newInstance(ArrayList<ArrayList<String>> mSolveResultArrayList) {
        ResultSolveStepFragment resultSolveFragment = new ResultSolveStepFragment();
        Bundle args = new Bundle();
        args.putParcelable("solveResult", Parcels.wrap(mSolveResultArrayList));
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

        for(int i=0; i<mSolveResultArrayList.size(); i++){
            for(int j=0; j<mSolveResultArrayList.get(i).size(); j++){
                if(mSolveResultArrayList.get(i).get(j).equals(Constants.WOLFRAM_ALPHA_INPUT_INTERPRETATION_TITLE)){
                    mInputInterpretationTitleTextView.setText(mSolveResultArrayList.get(i).get(j));
                    //Picasso.with(view.getContext()).load(mSolveResultArrayList.get(i).get(j+1)).into(mInputInterpretationImageImageView);
                    Picasso.with(view.getContext())
                            .load(mSolveResultArrayList.get(i).get(j+1))
                            .resize(1500, 150)
                            .centerCrop()
                            .into(mInputInterpretationImageImageView);
                }
                else if(mSolveResultArrayList.get(i).get(j).equals(Constants.WOLFRAM_ALPHA_RESULTS_TITLE)){
                    mResultsTitleTextView.setText(mSolveResultArrayList.get(i).get(j));
                   // Picasso.with(view.getContext()).load(mSolveResultArrayList.get(i).get(j+1)).into(mResultsImageImageView);
                    Picasso.with(view.getContext())
                            .load(mSolveResultArrayList.get(i).get(j+1))
                            .resize(400, 100)
                            .centerCrop()
                            .into(mResultsImageImageView);
                }
                //else if(mSolveResultArrayList.get(i).get(j).equals(Constants.WOLFRAM_ALPHA_ROOT_PLOT_TITLE)){
                    mPlotTitleTextView.setText(mSolveResultArrayList.get(2).get(0));
                    // Picasso.with(view.getContext()).load(mSolveResultArrayList.get(i).get(j+1)).into(mResultsImageImageView);
                    Picasso.with(view.getContext())
                            .load(mSolveResultArrayList.get(2).get(1))
                            .resize(400, 100)
                            .centerCrop()
                            .into(mPlotImageImageImageView);
                //}
            }
        }
        return view;
    }
}