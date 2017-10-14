package com.epicodus.neverlonely;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity{
    @Bind(R.id.events_list_view) ListView mEventsListView;
    @Bind(R.id.events_around_text_view) TextView mEventsAroundTextView;
    private ArrayList<Event> events = new ArrayList<>();
    private static final String EVENT_KEY = "EVENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface grandHotelFont = Typeface.createFromAsset(getAssets(), "fonts/grandhotel.ttf");
        mEventsAroundTextView.setTypeface(grandHotelFont);

        final EventsArrayAdapter adapter = new EventsArrayAdapter(this, events);
        adapter.add(new Event("Friday happy hour", "Want to go for a happy hour to some bar in Greenwich Village",
                "Friday, November 16",  "Greenwich Village", 5, "Andy Lee"));
        adapter.add(new Event("Metropolitan museum visit", "There is a new exposition going on in a Brooklym museum. " +
                "Would love to visit it",  "Thursday, November 22", "Brooklyn Museum", 3, "Kim Brown"));
        adapter.add(new Event("Bowling at Brooklyn Bowl", "Tuesday, November 12"));
        adapter.add(new Event("Central park afternoon walk", "Monday, November 12"));
        adapter.add(new Event("Dog Walk in Prospect Park", "Friday, November 16"));
        adapter.add(new Event("Skating at Rockfeller Center", "Thursday, November 22"));
        adapter.add(new Event("Friday happy hour", "Friday, November 16"));
        adapter.add(new Event("Metropolitan museum visit", "Thursday, November 22"));
        adapter.add(new Event("Bowling at Brooklyn Bowl", "Tuesday, November 12"));
        adapter.add(new Event("Central park afternoon walk", "Monday, November 12"));
        adapter.add(new Event("Dog Walk in Prospect Park", "Friday, November 16"));
        adapter.add(new Event("Skating at Rockfeller Center", "Thursday, November 22"));

        mEventsListView.setAdapter(adapter);
        mEventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event clickedEvent = (Event) mEventsListView.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(EVENT_KEY, clickedEvent);
                Intent detailsIntent = new Intent(MainActivity.this, DetailsActivity.class);
                detailsIntent.putExtras(bundle);
                startActivity(detailsIntent);
            }
        });
    }
}
