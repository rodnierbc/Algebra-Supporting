package com.epicodus.algebrasupporting.Adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.algebrasupporting.Constants;
import com.epicodus.algebrasupporting.R;
import com.epicodus.algebrasupporting.models.SolveResult;
import com.epicodus.algebrasupporting.models.SolveResult;
import com.epicodus.algebrasupporting.ui.ResultSolveDetailActivity;
import com.epicodus.algebrasupporting.util.ItemTouchHelperViewHolder;
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

public class FirebaseResultSolveViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    View mView;
    Context mContext;
    public CardView mResultSolveCardView;

    public FirebaseResultSolveViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindResultSolve(SolveResult solveResult) {
        mResultSolveCardView = (CardView) mView.findViewById(R.id.resultSolveCardView);
        TextView mInputInterpretationTextView = (TextView) mView.findViewById(R.id.inputInterpretationTextView);
        TextView mResultsTextView = (TextView) mView.findViewById(R.id.resultsTextView);
        mInputInterpretationTextView.setText(solveResult.getInputInterpretationPlainText());
        mResultsTextView.setText(solveResult.getResultsPlainText());
    }
    @Override
    public void onItemSelected() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_on);
        set.setTarget(itemView);
        set.start();
    }

    @Override
    public void onItemClear() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_off);
        set.setTarget(itemView);
        set.start();
    }
}
