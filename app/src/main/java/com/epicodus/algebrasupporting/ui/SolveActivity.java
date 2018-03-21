package com.epicodus.algebrasupporting.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import com.epicodus.algebrasupporting.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by rodnier.borrego on 3/16/18.
 */

public class SolveActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.inputInterpretationSolve)
    EditText mInputInterpretationSolve;
    @BindView(R.id.inputForVariable)
    EditText minputForVariable;
    @BindView(R.id.computeSolveButton) Button mComputeSolveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve);
        ButterKnife.bind(this);

        mComputeSolveButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if(v == mComputeSolveButton) {
            String interpretationSolve = mInputInterpretationSolve.getText().toString();
            String inputForVariable = mInputInterpretationSolve.getText().toString();
            Intent intent = new Intent(SolveActivity.this, ResultSolveActivity.class);
            intent.putExtra("interpretationSolve", interpretationSolve);
            intent.putExtra("inputForVariable", inputForVariable);
            startActivity(intent);
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
