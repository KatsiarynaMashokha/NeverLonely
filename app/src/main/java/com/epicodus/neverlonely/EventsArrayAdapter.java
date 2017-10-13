package com.epicodus.neverlonely;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by katsiarynamashokha on 10/13/17.
 */

public class EventsArrayAdapter extends ArrayAdapter<Event> {

    public EventsArrayAdapter(Context context, ArrayList<Event> events) {
        super(context, 0, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event event = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_for_list_view, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.title_text_view);
        TextView dateTextView = convertView.findViewById(R.id.date_text_view);

        titleTextView.setText(event.getTitle());
        dateTextView.setText(event.getDate());
        return convertView;
    }
}
