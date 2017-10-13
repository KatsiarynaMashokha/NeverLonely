package com.epicodus.neverlonely;
import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by katsiarynamashokha on 10/13/17.
 */

public class EventsArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mEvents;
    private String[] mDates;

    public EventsArrayAdapter(Context mContext, int resource, String[] mEvents, String[] mDates) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mEvents = mEvents;
        this.mDates = mDates;
    }

    @Override
    public int getCount() {
        return mEvents.length;
    }

    @Override
    public Object getItem(int position) {
        String event = mEvents[position];
        String date = mDates[position];
        return String.format("%s\n%s", event, date);
    }
}
