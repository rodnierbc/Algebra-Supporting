package com.epicodus.algebrasupporting.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.algebrasupporting.Constants;
import com.epicodus.algebrasupporting.R;
import com.epicodus.algebrasupporting.models.SolveResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultSolveSaveFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.inputInterpretationTextView) TextView mInputInterpretationTextView;
    @BindView(R.id.possibleIntermediateStepsTextView) TextView mPossibleIntermediateStepsTextView;
    @BindView(R.id.resultsTextView) TextView mResultsTextView;
    @BindView(R.id.descriptionHeaderTextView) TextView mDescriptionHeaderTextView;
    @BindView(R.id.descriptionEditText)
    EditText descriptionEditText;
    @BindView(R.id.addDescriptionButton)
    Button mAddDescriptionButton;

    private SolveResult mSolveResult;

    public static ResultSolveSaveFragment newInstance(SolveResult mSolveResultModel) {
        ResultSolveSaveFragment resultSolveSaveFragment = new ResultSolveSaveFragment();
        Bundle args = new Bundle();
        args.putParcelable("solveResult", Parcels.wrap(mSolveResultModel));
        resultSolveSaveFragment.setArguments(args);
        return resultSolveSaveFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSolveResult = Parcels.unwrap(getArguments().getParcelable("solveResult"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_solve_save, container, false);
        ButterKnife.bind(this, view);
        mAddDescriptionButton.setOnClickListener(this);
        mInputInterpretationTextView.setText(mSolveResult.getInputInterpretationPlainText());
        mPossibleIntermediateStepsTextView.setText(mSolveResult.getPossibleIntermediateStepsPlainText());
        mResultsTextView.setText(mSolveResult.getResultsPlainText());
        return view;
    }
    @Override
    public void onClick(View v) {
        if(v == mAddDescriptionButton) {
            mSolveResult.setDescription(descriptionEditText.getText().toString());
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference resultSolveRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_RESULT_SOLVE)
                    .child(uid);
            DatabaseReference pushRef = resultSolveRef.push();
            String pushId = pushRef.getKey();
            mSolveResult.setPushId(pushId);
            pushRef.setValue(mSolveResult);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }

    }
}
