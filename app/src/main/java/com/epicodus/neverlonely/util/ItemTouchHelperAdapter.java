package com.epicodus.neverlonely.util;

/**
 * Created by katsiarynamashokha on 11/1/17.
 */

public interface ItemTouchHelperAdapter {
    // Will be called each time the user moves an item by dragging it across the touch screen
    boolean onItemMove(int fromPosition, int toPosition);
    // Called when an item has been dismissed with a swipe motion
    void onItemDismiss(int position);
}
