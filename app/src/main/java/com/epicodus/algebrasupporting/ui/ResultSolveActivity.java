package com.epicodus.algebrasupporting.ui;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.epicodus.algebrasupporting.R;
import com.epicodus.algebrasupporting.services.WolframAlphaService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by rodnier.borrego on 3/17/18.
 */

public class ResultSolveActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = ResultSolveActivity.class.getSimpleName();
    public ArrayList<ArrayList<String>> solveResultArrayList;
    @BindView(R.id.solveResultShowStepsButton)
    Button mSolveResultShowStepsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_solve_step);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String interpretationSolve = intent.getStringExtra("interpretationSolve");
        String inputForVariable = intent.getStringExtra("inputForVariable");
        getSolveResult(interpretationSolve);
        mSolveResultShowStepsButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if(v == mSolveResultShowStepsButton) {
            Fragment  resultSolveStepByStepFragment= ResultSolveStepByStepFragment.newInstance(solveResultArrayList);
            replaceFragment(resultSolveStepByStepFragment);
        }
    }

    private void getSolveResult(String interpretationSolve) {
        final WolframAlphaService wolframAlphaService = new WolframAlphaService();
        wolframAlphaService.findResultStepByStepSolve(interpretationSolve, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                solveResultArrayList = wolframAlphaService.processResults(response);
                ResultSolveActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Fragment resultSolveFragment = ResultSolveStepFragment.newInstance(solveResultArrayList);
                        setDefaultFragment(resultSolveFragment);
                    }
                });
            }


        });

    }
    private void setDefaultFragment(Fragment defaultFragment) {
        this.replaceFragment(defaultFragment);
    }
    // Replace current Fragment with the destination Fragment.
    public void replaceFragment(Fragment destFragment)
    {
        // First get FragmentManager object.
        FragmentManager fragmentManager = this.getSupportFragmentManager();

        // Begin Fragment transaction.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the layout holder with the required Fragment object.
        fragmentTransaction.replace(R.id.solveResultFragmentsContentFrameLayout, destFragment);

        // Commit the Fragment replace action.
        fragmentTransaction.commit();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
