package com.epicodus.algebrasupporting.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.epicodus.algebrasupporting.Adapters.MainOptionsArrayAdapter;
import com.epicodus.algebrasupporting.R;

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

        final MainOptionsArrayAdapter adapter = new MainOptionsArrayAdapter(this, android.R.layout.simple_list_item_1, mOptions ); //must match constructor!
        mMainOptionsList.setAdapter(adapter);

        mMainOptionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String option = adapter.getItem(i).toString();
                Intent intent = new Intent(MainActivity.this, SolveActivity.class);
                startActivity(intent);
            }
        });

    }
}
