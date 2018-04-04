package com.epicodus.algebrasupporting.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.algebrasupporting.Constants;
import com.epicodus.algebrasupporting.R;
import com.epicodus.algebrasupporting.models.SolveResult;
import com.epicodus.algebrasupporting.models.SolveResult;
import com.epicodus.algebrasupporting.ui.ResultSolveDetailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by rodnier.borrego on 3/29/18.
 */

public class FirebaseResultSolveViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebaseResultSolveViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindResultSolve(SolveResult solveResult) {
        TextView mInputInterpretationTextView = (TextView) mView.findViewById(R.id.inputInterpretationTextView);
        TextView mResultsTextView = (TextView) mView.findViewById(R.id.resultsTextView);
        mInputInterpretationTextView.setText(solveResult.getInputInterpretationPlainText());
        mResultsTextView.setText(solveResult.getResultsPlainText());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<SolveResult> solveResults = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RESULT_SOLVE).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    solveResults.add(snapshot.getValue(SolveResult.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, ResultSolveDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("solveResults", Parcels.wrap(solveResults));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
