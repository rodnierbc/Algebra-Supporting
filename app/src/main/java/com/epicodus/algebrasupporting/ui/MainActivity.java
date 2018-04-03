package com.epicodus.algebrasupporting.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.epicodus.algebrasupporting.Adapters.MainOptionsArrayAdapter;
import com.epicodus.algebrasupporting.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mainOptionsList) ListView mMainOptionsList;
    @BindView(R.id.savedResultsSolveButton)
    Button mSavedResultsSolveButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    private String[] mOptions = new String[] {"Evaluate","Solve","Plot"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSavedResultsSolveButton.setOnClickListener(this);

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
        mAuth = FirebaseAuth.getInstance();
        if(mAuth != null){
           FirebaseUser user = mAuth.getCurrentUser();
           if(user != null){
               getSupportActionBar().setTitle("Welcome, " + user.getEmail());
           }
        }
    }
    @Override
    public void onClick(View v) {
        if(v == mSavedResultsSolveButton) {
            Intent intent = new Intent(MainActivity.this, SavedResultSolveListActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        if (id == R.id.action_login) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}
