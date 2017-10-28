package com.epicodus.neverlonely.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.epicodus.neverlonely.R;
import com.epicodus.neverlonely.models.Event;
import com.epicodus.neverlonely.models.EventsCart;

import java.util.List;
import java.util.UUID;

public class EventPagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private List<Event> mEvents;
    public static final String EXTRA_EVENT_ID =
            "com.epicodus.neverlonely.event_id";

    public static Intent newIntent(Context packageContext, UUID eventId) {
        Intent intent = new Intent(packageContext, EventPagerActivity.class);
        intent.putExtra(EXTRA_EVENT_ID, eventId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_pager);

        UUID eventId = (UUID) getIntent().getSerializableExtra(EXTRA_EVENT_ID);

        mViewPager = findViewById(R.id.event_view_pager);
        mEvents = EventsCart.get(this).getEvents();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Event event = mEvents.get(position);
                return EventFragment.newInstance(event.getId());
            }

            @Override
            public int getCount() {
                return mEvents.size();
            }
        });

        // Make ViewPager show the clicked event
        for(int i = 0; i < mEvents.size(); i++) {
            if(mEvents.get(i).getId().equals(eventId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
