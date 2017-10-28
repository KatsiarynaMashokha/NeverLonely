package com.epicodus.neverlonely.ui;

import android.support.v4.app.Fragment;

/**
 * Created by katsiarynamashokha on 10/21/17.
 */

public class EventListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new EventListFragment();
    }
}
