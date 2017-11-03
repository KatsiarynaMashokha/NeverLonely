package com.epicodus.neverlonely.util;

/**
 * Created by katsiarynamashokha on 11/2/17.
 */

public interface ItemTouchHelperViewHolder {
    // Will handle updating the appearance of a selected item while the user is dragging-and-dropping it
    void onItemSelected();
    // Will remove the 'selected' state
    void onItemClear();
}
