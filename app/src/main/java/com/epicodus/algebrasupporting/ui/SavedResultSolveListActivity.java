package com.epicodus.algebrasupporting.ui;

/**
 * Created by rodnier.borrego on 3/29/18.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.algebrasupporting.Constants;
import com.epicodus.algebrasupporting.R;
import com.epicodus.algebrasupporting.Adapters.FirebaseResultSolveViewHolder;
import com.epicodus.algebrasupporting.models.SolveResult;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rodnier.borrego on 3/29/18.
 */

public class SavedResultSolveListActivity extends AppCompatActivity {
    private DatabaseReference mAlgebraSupportingReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_saved_results_solve);
        ButterKnife.bind(this);

        mAlgebraSupportingReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RESULT_SOLVE);
        setUpFirebaseAdapter();
    }
    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<SolveResult, FirebaseResultSolveViewHolder>
                (SolveResult.class, R.layout.result_solve_list_item, FirebaseResultSolveViewHolder.class,
                        mAlgebraSupportingReference) {

            @Override
            protected void populateViewHolder(FirebaseResultSolveViewHolder viewHolder,
                                              SolveResult model, int position) {
                viewHolder.bindResultSolve(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}

