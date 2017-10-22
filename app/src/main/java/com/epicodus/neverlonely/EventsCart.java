package com.epicodus.neverlonely;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by katsiarynamashokha on 10/21/17.
 */

public class EventsCart {
    private static EventsCart sEventsCart;
    private List<Event> mEvents;

    public static EventsCart get(Context context) {
        if(sEventsCart == null) {
            sEventsCart = new EventsCart(context);
        }
        return sEventsCart;
    }

    private EventsCart(Context context) {
        mEvents = new ArrayList<>();
        mEvents.add(new Event("Friday happy hour", "Want to go for a happy hour to some bar in Greenwich Village",
                "Friday, November 16", "5:45pm", "Greenwich Village", 5, "Andy Lee"));
        mEvents.add(new Event("Metropolitan museum visit", "There is a new exposition going on in a Brooklym museum. " +
                "Would love to visit it",  "Thursday, November 22", "11:30am", "Brooklyn Museum", 3, "Kim Brown"));
        mEvents.add(new Event("Bowling at Brooklyn Bowl", "Tuesday, November 12"));
        mEvents.add(new Event("Central park afternoon walk", "Monday, November 12"));
        mEvents.add(new Event("Dog Walk in Prospect Park", "Friday, November 16"));
        mEvents.add(new Event("Skating at Rockfeller Center", "Thursday, November 22"));
        mEvents.add(new Event("Friday happy hour", "Friday, November 16"));
        mEvents.add(new Event("Metropolitan museum visit", "Thursday, November 22"));
        mEvents.add(new Event("Bowling at Brooklyn Bowl", "Tuesday, November 12"));
        mEvents.add(new Event("Central park afternoon walk", "Monday, November 12"));
        mEvents.add(new Event("Dog Walk in Prospect Park", "Friday, November 16"));
        mEvents.add(new Event("Skating at Rockfeller Center", "Thursday, November 22"));
    }

    public List<Event> getEvents() {
        return mEvents;
    }

    public Event getEvent(UUID id) {
        for(Event event : mEvents) {
            if(event.getId().equals(id)) {
                return event;
            }
        }
        return null;
    }

    public void addNewEvent(Event event) {
        mEvents.add(event);
    }
}
