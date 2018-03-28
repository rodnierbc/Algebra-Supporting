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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultSolveStepByStepFragment extends Fragment {

    @BindView(R.id.possibleIntermediateStepsTextView) TextView mPossibleIntermediateStepsTextView;
    @BindView(R.id.resultSolveStepByStepImageView) ImageView mResultSolveStepByStepImageView;

    private JSONObject mSolveResultStepByStepJSON;
    private SolveResult mSolveResult;

    public static ResultSolveStepByStepFragment newInstance(JSONObject mSolveResultStepByStepJSON) {
        ResultSolveStepByStepFragment resultSolveStepByStepFragment = new ResultSolveStepByStepFragment();
        Bundle args = new Bundle();
        args.putParcelable("solveResultStepByStep", Parcels.wrap(mSolveResultStepByStepJSON.toString()));
        resultSolveStepByStepFragment.setArguments(args);
        return resultSolveStepByStepFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String json = Parcels.unwrap(getArguments().getParcelable("solveResultStepByStep"));
        try {
            mSolveResultStepByStepJSON = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_solve_step_by_step, container, false);
        ButterKnife.bind(this, view);

        Iterator<?> keys = mSolveResultStepByStepJSON.keys();
        while (keys.hasNext()){
            String key = (String)keys.next();
            try {
                if ( key.equals(Constants.WOLFRAM_ALPHA_POSSIBLE_INTERMEDIATE_STEPS_TITLE)) {
                    JSONArray items = mSolveResultStepByStepJSON.getJSONArray(key);
                    mPossibleIntermediateStepsTextView.setText(key);
                    Picasso.with(view.getContext()).load(items.getString(0)).into(mResultSolveStepByStepImageView);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return view;
    }
}
