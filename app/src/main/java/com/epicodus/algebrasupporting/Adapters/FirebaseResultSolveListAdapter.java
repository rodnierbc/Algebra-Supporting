package com.epicodus.algebrasupporting.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.algebrasupporting.models.SolveResult;
import com.epicodus.algebrasupporting.ui.ResultSolveDetailActivity;
import com.epicodus.algebrasupporting.util.ItemTouchHelperAdapter;
import com.epicodus.algebrasupporting.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by rodnier.borrego on 4/6/18.
 */

public class FirebaseResultSolveListAdapter  extends FirebaseRecyclerAdapter<SolveResult, FirebaseResultSolveViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    private ChildEventListener mChildEventListener;
    private ArrayList<SolveResult> mSolveResults = new ArrayList<>();

    public FirebaseResultSolveListAdapter(Class<SolveResult> modelClass, int modelLayout, Class<FirebaseResultSolveViewHolder> viewHolderClass, Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mSolveResults.add(dataSnapshot.getValue(SolveResult.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void populateViewHolder(final FirebaseResultSolveViewHolder viewHolder, SolveResult model, int position) {
        viewHolder.bindResultSolve(model);
        viewHolder.mResultSolveCardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ResultSolveDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("solveResults", Parcels.wrap(mSolveResults));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mSolveResults, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mSolveResults.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase() {
        for (SolveResult solveResult : mSolveResults) {
            int index = mSolveResults.indexOf(solveResult);
            DatabaseReference ref = getRef(index);
            solveResult.setIndex(Integer.toString(index));
            ref.setValue(solveResult);
        }
    }
    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }

}
