package com.epicodus.neverlonely;

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

import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by katsiarynamashokha on 10/22/17.
 */

public class NewEventFragment extends Fragment implements View.OnClickListener{
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    @Bind(R.id.save_button) Button mSaveButton;
    @Bind(R.id.title_edit_text) EditText mTitleEditText;
    @Bind(R.id.description_edit_text) EditText mDescriptionEditText;
    @Bind(R.id.date_edit_text) EditText mDateEditText;
    @Bind(R.id.time_edit_text) EditText mTimeEditText;
    @Bind(R.id.location_edit_text) EditText mLocationEditText;
    @Bind(R.id.max_attendees_edit_text) EditText mMaxAttendeesEditText;

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
        mMaxAttendeesEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
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
            mDateEditText.setText(date.toString());
        }
    }

    @Override
    public void onClick(View v) {
        String title = mTitleEditText.getText().toString();
        mTitleEditText.getText().clear();
        String description = mDescriptionEditText.getText().toString();
        mDescriptionEditText.getText().clear();
        String date = mDateEditText.getText().toString();
        mDateEditText.getText().clear();
        String time = mTimeEditText.getText().toString();
        mTimeEditText.getText().clear();
        String location = mLocationEditText.getText().toString();
        mLocationEditText.getText().clear();
        int attendees = Integer.parseInt(mMaxAttendeesEditText.getText().toString());
        mMaxAttendeesEditText.getText().clear();
        Event newEvent =  new Event(title, description, date, time, location, attendees, "null");
        EventsCart.get(getActivity()).addNewEvent(newEvent);
        // Print all events to console
        List<Event> events = EventsCart.get(getActivity()).getEvents();
        for(Event e : events) {
            System.out.println(e.getTitle());
        }
    }
}
