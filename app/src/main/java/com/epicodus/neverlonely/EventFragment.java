package com.epicodus.neverlonely;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by katsiarynamashokha on 10/21/17.
 */

public class EventFragment extends Fragment {
    private Event mEvent;
    private static final String EVENT_ID = "event_id";
    @Bind(R.id.details_title_text_view) TextView mDetailsTitleTextView;
    @Bind(R.id.author_text_view) TextView mAuthorTextView;
    @Bind(R.id.details_description_text_view) TextView mDetailsDescriptionTextView;
    @Bind(R.id.date_text_view) TextView mDateTextView;
    @Bind(R.id.location_text_view) TextView mLocationTextView;
    @Bind(R.id.max_attendees_text_view) TextView mMaxAttendeesTextView;
    @Bind(R.id.current_attendees_text_view) TextView mCurrentAttendeesTextView;
    @Bind(R.id.weather_text_view) TextView mWeatherTextView;
    @Bind(R.id.join_button) Button mJoinButton;

    // Creates fragment instance and bundles up and sets its arguments. When EventActivity needs an instance, it can call this method.
    public static EventFragment newInstance(UUID eventId) {
        Bundle args = new Bundle();
        args.putSerializable(EVENT_ID, eventId);
        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID eventId = (UUID) getArguments().getSerializable(EVENT_ID);
        mEvent = EventsCart.get(getActivity()).getEvent(eventId);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, v);

        mDetailsTitleTextView.setText(mEvent.getTitle());
        mAuthorTextView.setText(mEvent.getOrganizer());
        mDetailsDescriptionTextView.setText(mEvent.getDescription());
        mDateTextView.setText(mEvent.getDate());
        mLocationTextView.setText(mEvent.getLocation());
        mLocationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = mEvent.getLocation();
                //TODO: implement address parsing.
                Uri geolocation =  Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway%2C+CA");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(geolocation);
                if(intent.resolveActivity(getActivity().getPackageManager())!= null) {
                    startActivity(intent);
                }
            }
        });

        mMaxAttendeesTextView.setText(String.valueOf(mEvent.getMaxNumberOfAttendees()));
        mCurrentAttendeesTextView.setText(String.valueOf(mEvent.getCurrentNumOfAttendees()));
        mJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEvent.addNewAttendee();
                mCurrentAttendeesTextView.setText(String.valueOf(mEvent.getCurrentNumOfAttendees()));
            }
        });
        getWeather(mEvent.getZip());
        return v;
    }

    private void getWeather(String location) {
        final WeatherService weatherService = new WeatherService();
        weatherService.findWeather(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String weather = weatherService.processResult(response);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mWeatherTextView.setText(weather);
                    }
                });
            }
        });
    }
}
