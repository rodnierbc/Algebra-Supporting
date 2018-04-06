package com.epicodus.algebrasupporting.util;

/**
 * Created by rodnier.borrego on 4/4/18.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
