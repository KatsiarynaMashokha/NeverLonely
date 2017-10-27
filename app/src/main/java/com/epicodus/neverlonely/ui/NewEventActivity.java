package com.epicodus.neverlonely.ui;

import android.support.v4.app.Fragment;

public class NewEventActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new NewEventFragment();
    }
}
