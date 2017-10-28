package com.epicodus.neverlonely.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import com.epicodus.neverlonely.R;

/**
 * Created by katsiarynamashokha on 10/23/17.
 */

public class TimePickerFragment extends DialogFragment {
    private TimePicker mTimePicker;
    public static final String EXTRA_TIME = "com.epicodus.neverlonely.time";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_time, null);
        mTimePicker = v.findViewById(R.id.dialog_time_picker);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.time_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int hour, minutes;
                        if (Build.VERSION.SDK_INT < 23) {
                            hour = mTimePicker.getCurrentHour();
                            minutes = mTimePicker.getCurrentMinute();
                        } else {
                            hour = mTimePicker.getHour();
                            minutes = mTimePicker.getMinute();
                        }
                        String timeString = String.format("%d:%d", hour, minutes);
                        sendResult(Activity.RESULT_OK, timeString);
                    }
                })
                .create();
    }

    private void sendResult(int resultCode, String time) {
        if(getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, time);
        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
