package com.epicodus.neverlonely;

import java.io.Serializable;

/**
 * Created by katsiarynamashokha on 10/13/17.
 */

public class Event implements Serializable {
    private String mTitle;
    private String mDescription;
    private String mDate;
    private String mLocation;
    private int mMaxNumberOfAttendees;
    private String mOrganizer;
    private int mCurrentNumOfAttendees = 0;

    public Event(String title, String date) {
        mTitle = title;
        mDate = date;

    }

    public Event(String title, String description, String date, String location, int maxNumberOfAttendees, String organizer) {
        mTitle = title;
        mDescription = description;
        mDate = date;
        mLocation = location;
        mMaxNumberOfAttendees = maxNumberOfAttendees;
        mOrganizer = organizer;
    }

    // Getters
    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public String getLocation() {
        return mLocation;
    }

    public int getMaxNumberOfAttendees() {
        return mMaxNumberOfAttendees;
    }

    public String getOrganizer() {
        return mOrganizer;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getCurrentNumOfAttendees() {
        return mCurrentNumOfAttendees;
    }

    // Setters
    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public void setMaxNumberOfAttendees(int maxNumberOfAttendees) {
        mMaxNumberOfAttendees = maxNumberOfAttendees;
    }

    public void setOrganizer(String organizer) {
        mOrganizer = organizer;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setCurrentNumOfAttendees(int currentNumOfAttendees) {
        mCurrentNumOfAttendees = currentNumOfAttendees;
    }

    public void addNewAttendee() {
        mCurrentNumOfAttendees++;
    }

}
