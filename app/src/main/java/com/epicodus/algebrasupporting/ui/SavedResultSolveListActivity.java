package com.epicodus.algebrasupporting.ui;

/**
 * Created by rodnier.borrego on 3/29/18.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.epicodus.algebrasupporting.Adapters.FirebaseResultSolveListAdapter;
import com.epicodus.algebrasupporting.Constants;
import com.epicodus.algebrasupporting.R;
import com.epicodus.algebrasupporting.Adapters.FirebaseResultSolveViewHolder;
import com.epicodus.algebrasupporting.models.SolveResult;
import com.epicodus.algebrasupporting.util.OnStartDragListener;
import com.epicodus.algebrasupporting.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rodnier.borrego on 3/29/18.
 */

public class SavedResultSolveListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mAlgebraSupportingReference;
    private FirebaseResultSolveListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_saved_results_solve);
        ButterKnife.bind(this);
        setUpFirebaseAdapter();

    }
    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_RESULT_SOLVE)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        mFirebaseAdapter = new FirebaseResultSolveListAdapter(SolveResult.class,
                R.layout.result_solve_list_item, FirebaseResultSolveViewHolder.class,
                query, this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}

