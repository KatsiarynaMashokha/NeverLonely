package com.epicodus.neverlonely.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.neverlonely.R;
import com.epicodus.neverlonely.models.Event;
import com.epicodus.neverlonely.models.EventsCart;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by katsiarynamashokha on 10/22/17.
 */

public class NewEventFragment extends Fragment implements View.OnClickListener{
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;
    public static final String TAG = NewEventFragment.class.getSimpleName();
    @Bind(R.id.save_button) Button mSaveButton;
    @Bind(R.id.title_edit_text) EditText mTitleEditText;
    @Bind(R.id.description_edit_text) EditText mDescriptionEditText;
    @Bind(R.id.date_edit_text) Button mDateEditText;
    @Bind(R.id.time_edit_text) Button mTimeEditText;
    PlaceAutocompleteFragment mPlaceAutocompleteFragment;
    @Bind(R.id.max_attendees_edit_text) EditText mMaxAttendeesEditText;
    @Bind(R.id.zip_edit_text) EditText mZipEditText;
    @OnClick(R.id.exit_button)
            public void exit() {
        startActivity(new Intent(NewEventFragment.this.getActivity(), EventListActivity.class));
    }
    String location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_new_event, container, false);
        ButterKnife.bind(this, view);
        mDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.setTargetFragment(NewEventFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });
        mTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog = new TimePickerFragment();
                dialog.setTargetFragment(NewEventFragment.this, REQUEST_TIME);
                dialog.show(manager, DIALOG_TIME);
            }
        });
        mMaxAttendeesEditText.setInputType(InputType.TYPE_CLASS_NUMBER);

        mPlaceAutocompleteFragment = (PlaceAutocompleteFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.place_autocomplete_fragment);
        ((EditText)mPlaceAutocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input)).setTextColor(-1);
        ((EditText)mPlaceAutocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input)).setHint(R.string.location);
        ((EditText)mPlaceAutocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input)).setHintTextColor(-1);


        mPlaceAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                location = place.getAddress().toString();
            }

            @Override
            public void onError(Status status) {
            }
        });

        mSaveButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK) {
            return;
        }
        if(requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mDateEditText.setText(dateConverter(date));
        }
        if(requestCode == REQUEST_TIME) {
            String timeString = data
                    .getStringExtra(TimePickerFragment.EXTRA_TIME);
            mTimeEditText.setText(timeString);
        }
    }

    @Override
    public void onClick(View v) {
        String title = mTitleEditText.getText().toString();
        String description = mDescriptionEditText.getText().toString();
        String date = mDateEditText.getText().toString();
        String time = mTimeEditText.getText().toString();
        String zip = mZipEditText.getText().toString();
        String attendeesString = mMaxAttendeesEditText.getText().toString();

        if (title.length() < 5 || attendeesString.isEmpty() || date.length() < 8 || time.length() < 4 ||
                location.length() < 10 || description.length() < 10 || zip.length() != 5) {
            Toast.makeText(getActivity(), R.string.enter_more_event_details, Toast.LENGTH_SHORT).show();
            return;
        }
        Event newEvent = new Event(title, description, date, time, location, Integer.valueOf(attendeesString), "null", zip);
        EventsCart.get(getActivity()).addNewEvent(newEvent);
        Toast.makeText(getActivity(), R.string.event_success_added, Toast.LENGTH_SHORT).show();
        mTitleEditText.getText().clear();
        mDescriptionEditText.getText().clear();
        mMaxAttendeesEditText.getText().clear();
        mZipEditText.getText().clear();
        Intent intent = new Intent(getActivity(), EventListActivity.class);
        startActivity(intent);

        // Print all events to console
        List<Event> events = EventsCart.get(getActivity()).getEvents();
        for (Event e : events) {
            System.out.println(e.getTitle());
        }
    }

    private String dateConverter(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("MMMM dd, YYYY");
        return df.format(date);
    }
}
