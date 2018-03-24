package com.epicodus.algebrasupporting.Adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by rodnier.borrego on 3/16/18.
 */

public class MainOptionsArrayAdapter extends ArrayAdapter{
    private Context mContext;
    private String[] mOptions;

    public MainOptionsArrayAdapter(Context mContext, int resource, String[] mOptions) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mOptions = mOptions;
    }

    @Override
    public Object getItem(int position) {
        return mOptions[position];
    }
    @Override
    public int getCount() {
        return mOptions.length;
    }
}

