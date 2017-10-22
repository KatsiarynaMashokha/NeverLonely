package com.epicodus.neverlonely;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by katsiarynamashokha on 10/21/17.
 */

public class EventFragment extends Fragment {
    private Event mEvent;
    @Bind(R.id.details_title_text_view) TextView mDetailsTitleTextView;
    @Bind(R.id.author_text_view) TextView mAuthorTextView;
    @Bind(R.id.details_description_text_view) TextView mDetailsDescriptionTextView;
    @Bind(R.id.date_text_view) TextView mDateTextView;
    @Bind(R.id.location_text_view) TextView mLocationTextView;
    @Bind(R.id.max_attendees_text_view) TextView mMaxAttendeesTextView;
    @Bind(R.id.current_attendees_text_view) TextView mCurrentAttendeesTextView;
    @Bind(R.id.join_button) Button mJoinButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID eventId = (UUID) getActivity().getIntent()
                .getSerializableExtra(EventActivity.EXTRA_EVENT_ID);
        mEvent = EventsCart.get(getActivity()).getEvent(eventId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, v);

        mDetailsTitleTextView.setText(mEvent.getTitle());
        mAuthorTextView.setText(mEvent.getOrganizer());
        mDetailsDescriptionTextView.setText(mEvent.getDescription());
        mDateTextView.setText(mEvent.getDate());
        mLocationTextView.setText(mEvent.getLocation());
        mMaxAttendeesTextView.setText(String.valueOf(mEvent.getMaxNumberOfAttendees()));
        mCurrentAttendeesTextView.setText(String.valueOf(mEvent.getCurrentNumOfAttendees()));
        mJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEvent.addNewAttendee();
                mCurrentAttendeesTextView.setText(String.valueOf(mEvent.getCurrentNumOfAttendees()));
            }
        });
        return v;
    }
}
