package com.epicodus.algebrasupporting.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.epicodus.algebrasupporting.R;

import butterknife.ButterKnife;

/**
 * Created by rodnier.borrego on 3/17/18.
 */

public class ResultSolveActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_solve_step);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String interpretationSolve = intent.getStringExtra("interpretationSolve");
        String inputForVariable = intent.getStringExtra("inputForVariable");

        getRestaurants(interpretationSolve);
    }
}
