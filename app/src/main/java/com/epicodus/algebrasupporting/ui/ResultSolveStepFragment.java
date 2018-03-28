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

import org.json.JSONObject;
import org.json.*;
import org.parceler.Parcels;

import java.security.Key;
import java.util.ArrayList;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultSolveStepFragment extends Fragment {

    @BindView(R.id.inputInterpretationTitleTextView) TextView mInputInterpretationTitleTextView;
    @BindView(R.id.inputInterpretationImageImageView) ImageView mInputInterpretationImageImageView;
    @BindView(R.id.resultsTitleTextView) TextView mResultsTitleTextView;
    @BindView(R.id.resultsImageImageView) ImageView mResultsImageImageView;
    @BindView(R.id.plotTitleTextView) TextView mPlotTitleTextView;
    @BindView(R.id.plotImageImageImageView) ImageView mPlotImageImageImageView;

    private JSONObject mSolveResultJSON;
    private SolveResult mSolveResult;

    public static ResultSolveStepFragment newInstance(JSONObject mSolveResultJSON) {
        ResultSolveStepFragment resultSolveFragment = new ResultSolveStepFragment();
        Bundle args = new Bundle();
        args.putParcelable("solveResult", Parcels.wrap(mSolveResultJSON.toString()));
        resultSolveFragment.setArguments(args);
        return resultSolveFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String json = Parcels.unwrap(getArguments().getParcelable("solveResult"));
        try {
            mSolveResultJSON = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_solve_step, container, false);
        ButterKnife.bind(this, view);

            Iterator<?> keys = mSolveResultJSON.keys();
            while (keys.hasNext()){
                String key = (String)keys.next();
                try {
                    if ( key.equals(Constants.WOLFRAM_ALPHA_INPUT_INTERPRETATION_TITLE)) {
                        JSONArray items = mSolveResultJSON.getJSONArray(key);
                        mInputInterpretationTitleTextView.setText(key);
                        Picasso.with(view.getContext()).load(items.getString(0)).into(mInputInterpretationImageImageView);
                    }
                    else if( key.equals(Constants.WOLFRAM_ALPHA_RESULTS_TITLE)) {
                        JSONArray items = mSolveResultJSON.getJSONArray(key);
                        mResultsTitleTextView.setText(key);
                        Picasso.with(view.getContext()).load(items.getString(0)).into(mResultsImageImageView);
                    }
                    else if(key.equals(Constants.WOLFRAM_ALPHA_ROOT_PLOT_TITLE)) {
                        JSONArray items = mSolveResultJSON.getJSONArray(key);
                        mPlotTitleTextView.setText(key);
                        Picasso.with(view.getContext()).load(items.getString(0)).into(mPlotImageImageImageView);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        return view;
    }
}