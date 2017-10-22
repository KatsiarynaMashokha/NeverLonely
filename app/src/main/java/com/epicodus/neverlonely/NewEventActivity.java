package com.epicodus.neverlonely;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewEventActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.save_button) Button mSaveButton;
    @Bind(R.id.title_edit_text) EditText mTitleEditText;
    @Bind(R.id.description_edit_text) EditText mDescriptionEditText;
    @Bind(R.id.date_edit_text) EditText mDateEditText;
    @Bind(R.id.time_edit_text) EditText mTimeEditText;
    @Bind(R.id.location_edit_text) EditText mLocationEditText;
    @Bind(R.id.max_attendees_edit_text) EditText mMaxAttendeesEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        ButterKnife.bind(this);
        mMaxAttendeesEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        mSaveButton.setOnClickListener(this);
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
        EventsCart.get(this).addNewEvent(newEvent);
        List<Event> events = EventsCart.get(this).getEvents();
        for(Event e : events) {
            System.out.println(e.getTitle());
        }
    }
}
