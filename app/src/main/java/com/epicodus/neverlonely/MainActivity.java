package com.epicodus.neverlonely;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.layout.simple_list_item_1;


public class MainActivity extends AppCompatActivity {
    @Bind(R.id.events_list_view) ListView mEventsListView;
    @Bind(R.id.events_around_text_view) TextView mEventsAroundTextView;

    private String[] events = {"Friday happy hour", "Metropolitan museum visit", "Bowling at Brooklyn Bowl",
            "Central park afternoon walk", "Dog Walk in Prospect Pakr", "Skating at Rockfeller Center"};
    private String[] dates = {"Friday, November 16", "Thursday, November 22", "Tuesday, November 12",
            "Monday, November 12", "Friday, November 16", "Thursday, November 22"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        EventsArrayAdapter adapter = new EventsArrayAdapter(this, simple_list_item_1, events, dates);
        mEventsListView.setAdapter(adapter);

        Typeface grandHotelFont = Typeface.createFromAsset(getAssets(), "fonts/grandhotel.ttf");
        mEventsAroundTextView.setTypeface(grandHotelFont);

    }
}
