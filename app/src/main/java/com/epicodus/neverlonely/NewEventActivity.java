package com.epicodus.neverlonely;

import android.support.v4.app.Fragment;

public class NewEventActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new NewEventFragment();
    }
}
