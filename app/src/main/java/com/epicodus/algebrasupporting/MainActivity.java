package com.epicodus.algebrasupporting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.mainOptionsList) ListView mMainOptionsList;

    private String[] mOptions = new String[] {"Evaluate","Solve","Plot"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MainOptionsArrayAdapter adapter = new MainOptionsArrayAdapter(this, android.R.layout.simple_list_item_single_choice, mOptions ); //must match constructor!
        mMainOptionsList.setAdapter(adapter);

        mMainOptionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String restaurant = ((TextView)view).getText().toString();

            }
        });

    }
}
