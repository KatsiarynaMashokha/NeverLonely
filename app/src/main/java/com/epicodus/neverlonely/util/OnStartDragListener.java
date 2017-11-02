package com.epicodus.neverlonely.util;

import android.support.v7.widget.RecyclerView;

/**
 * Created by katsiarynamashokha on 11/1/17.
 */

public interface OnStartDragListener {
    //  Will be called when the user begins a 'drag-and-drop' interaction with the touchscreen
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
