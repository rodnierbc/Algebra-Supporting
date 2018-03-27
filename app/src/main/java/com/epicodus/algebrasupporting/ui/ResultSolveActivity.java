package com.epicodus.algebrasupporting.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    static final int PICK_CONTACT_REQUEST = 1;  // The request code
    public static final String TAG = ResultSolveActivity.class.getSimpleName();
    public ArrayList<ArrayList<String>> solveResultArrayList;
    @BindView(R.id.solveResultShowStepsButton)
    Button mSolveResultShowStepsButton;
    @BindView(R.id.solveResultSaveButton)
    Button mSolveResultSaveButton;
    @BindView(R.id.solveResultSendButton)
    Button mSolveResultSendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_solve_step);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String interpretationSolve = intent.getStringExtra("interpretationSolve");
        String inputForVariable = intent.getStringExtra("inputForVariable");
        getSolveResult(interpretationSolve);

        mSolveResultShowStepsButton.setVisibility(View.INVISIBLE);
        mSolveResultSaveButton.setVisibility(View.INVISIBLE);
        mSolveResultSendButton.setVisibility(View.INVISIBLE);

        mSolveResultShowStepsButton.setOnClickListener(this);
        mSolveResultSaveButton.setOnClickListener(this);
        mSolveResultSendButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if(v == mSolveResultShowStepsButton) {
            Fragment  resultSolveStepByStepFragment= ResultSolveStepByStepFragment.newInstance(solveResultArrayList);
            replaceFragment(resultSolveStepByStepFragment);
        }
        else if(v == mSolveResultSaveButton){
            //implement logic for to create object and save in the data base the corresponding record.
        }
        else if(v == mSolveResultSendButton){
            pickContact();
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
                        mSolveResultShowStepsButton.setVisibility(View.VISIBLE);
                        mSolveResultSaveButton.setVisibility(View.VISIBLE);
                        mSolveResultSendButton.setVisibility(View.VISIBLE);

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
    private void pickContact() {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        if (requestCode == PICK_CONTACT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // Get the URI that points to the selected contact
                Uri contactUri = data.getData();
                // We only need the NUMBER column, because there will be only one row in the result
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

                // Perform the query on the contact to get the NUMBER column
                // We don't need a selection or sort order (there's only one result for the given URI)
                // CAUTION: The query() method should be called from a separate thread to avoid blocking
                // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
                // Consider using CursorLoader to perform the query.
                Cursor cursor = getContentResolver()
                        .query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                // Retrieve the phone number from the NUMBER column
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(column);
                String smsBody = "Hello";
                sendSMS(number, smsBody);

                // Do something with the phone number...
            }
        }
    }

    protected void sendSMS(String number, String smsBody) {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , number);
        smsIntent.putExtra("sms_body"  , smsBody);

        try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ResultSolveActivity.this,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}
