package com.epicodus.neverlonely.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.neverlonely.Constants;
import com.epicodus.neverlonely.R;
import com.epicodus.neverlonely.models.Event;
import com.epicodus.neverlonely.models.EventsCart;
import com.epicodus.neverlonely.services.WeatherService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by katsiarynamashokha on 10/21/17.
 */

public class EventFragment extends Fragment {
    public static final String TAG = EventFragment.class.getSimpleName();
    private Event mEvent;
    private static final int REQUEST_CONFIRMATION = 0;
    private static final String DIALOG_CONFIRM = "DialogConfirm";
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

    private DatabaseReference mJoinedEventReference;
    private ValueEventListener mEventListener;

    // Creates fragment instance and bundles up and sets its arguments. When EventActivity needs an instance, it can call this method.
    public static EventFragment newInstance(String eventId) {
        Bundle args = new Bundle();
        args.putSerializable(EVENT_ID, eventId);
        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String eventId = (String) getArguments().getSerializable(EVENT_ID);
        mEvent = EventsCart.get(getActivity()).getEvent(eventId);

        mJoinedEventReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_MY_EVENTS);

        mEventListener = mJoinedEventReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot event : dataSnapshot.getChildren()) {
                    String title = event.getValue().toString();
                    Log.d("Events updated", "event : " + title);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, v);

        mDetailsTitleTextView.setText(mEvent.getTitle());
        mAuthorTextView.setText(mEvent.getOrganizer());
        mDetailsDescriptionTextView.setText(mEvent.getDescription());
        mDateTextView.setText(mEvent.getDate() + " @ " + mEvent.getTime());
        mLocationTextView.setText(mEvent.getLocation());
        mLocationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = mEvent.getLocation();
                String replacedAddress = address.replaceAll("\\s+","+").replaceAll(",","");
                Log.v(TAG, replacedAddress);

                Uri geolocation =  Uri.parse( "geo:0,0?q=" + replacedAddress);
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
                FragmentManager fm = getActivity().getSupportFragmentManager();
                ConfirmationDialogFragment dialogFragment = ConfirmationDialogFragment.newInstance();
                dialogFragment.setTargetFragment(EventFragment.this, REQUEST_CONFIRMATION);
                dialogFragment.show(fm, DIALOG_CONFIRM);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            mEvent.addNewAttendee();
            mCurrentAttendeesTextView.setText(String.valueOf(mEvent.getCurrentNumOfAttendees()));
            saveEventToMyEvents(mEvent);
        }
    }

    private void saveEventToMyEvents(Event event) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = currentUser.getUid();

        DatabaseReference eventsReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_MY_EVENTS)
                .child(uid);

        DatabaseReference pushReference = eventsReference.push();
        pushReference.setValue(event);
    }

    // Remove the listener from our Firebase node when the activity is destroyed
    @Override
    public void onDestroy() {
        super.onDestroy();
        mJoinedEventReference.removeEventListener(mEventListener);
    }
}
