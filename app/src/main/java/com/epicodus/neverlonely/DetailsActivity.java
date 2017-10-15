package com.epicodus.neverlonely;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {
    private static final String EVENT_KEY = "EVENT";
    @Bind(R.id.details_title_text_view) TextView mDetailsTitleTextView;
    @Bind(R.id.author_text_view) TextView mAuthorTextView;
    @Bind(R.id.details_description_text_view) TextView mDetailsDescriptionTextView;
    @Bind(R.id.date_text_view) TextView mDateTextView;
    @Bind(R.id.location_text_view) TextView mLocationTextView;
    @Bind(R.id.max_attendees_text_view) TextView mMaxAttendeesTextView;
    @Bind(R.id.current_attendees_text_view) TextView mCurrentAttendeesTextView;
    @Bind(R.id.join_button) Button mJoinButton;

    private Event clickedEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        clickedEvent = (Event) bundle.getSerializable(EVENT_KEY);
        String title = clickedEvent.getTitle();
        String description = clickedEvent.getDescription();
        String organizer = clickedEvent.getOrganizer();
        String date = clickedEvent.getDate();
        String location = clickedEvent.getLocation();
        int maxNumOfAttendees = clickedEvent.getMaxNumberOfAttendees();
        int currentNumOfAttendees = clickedEvent.getCurrentNumOfAttendees();

        mDetailsTitleTextView.setText(title);
        mDetailsDescriptionTextView.setText(description);
        mAuthorTextView.setText("by " + organizer);
        mDetailsDescriptionTextView.setText(description);
        mDateTextView.setText(date);
        mLocationTextView.setText(location);
        mMaxAttendeesTextView.setText(String.valueOf(maxNumOfAttendees));
        mCurrentAttendeesTextView.setText(String.valueOf(currentNumOfAttendees));
        mJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedEvent.addNewAttendee();
                mCurrentAttendeesTextView.setText(String.valueOf(clickedEvent.getCurrentNumOfAttendees()));
            }
        });
    }
}
