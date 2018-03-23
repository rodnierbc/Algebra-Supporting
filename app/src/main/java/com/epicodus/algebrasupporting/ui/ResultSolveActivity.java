package com.epicodus.algebrasupporting.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.epicodus.algebrasupporting.R;
import com.epicodus.algebrasupporting.models.SolveResult;
import com.epicodus.algebrasupporting.services.WolframAlphaService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by rodnier.borrego on 3/17/18.
 */

public class ResultSolveActivity extends AppCompatActivity {
    public static final String TAG = ResultSolveActivity.class.getSimpleName();
    public ArrayList<ArrayList<String>> solveResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_solve_step);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String interpretationSolve = intent.getStringExtra("interpretationSolve");
        String inputForVariable = intent.getStringExtra("inputForVariable");

        getSolveResult(interpretationSolve);
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
                solveResult = wolframAlphaService.processResults(response);

                ResultSolveActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        String example = solveResult.getInputInterpretationPlainText();
                        Log.v(TAG,example);

                    }
                });
            }
        });
    }
}
