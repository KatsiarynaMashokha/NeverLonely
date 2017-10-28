package com.epicodus.neverlonely.models;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by katsiarynamashokha on 10/13/17.
 */

public class Event implements Serializable {
    private String mTitle;
    private String mDescription;
    private String mDate;
    private String mTime;
    private String mLocation;
    private int mMaxNumberOfAttendees;
    private String mOrganizer;
    private UUID mId;
    private int mCurrentNumOfAttendees = 0;
    private String mZip;


    public Event(String title, String date) {
        mTitle = title;
        mDate = date;
        mId = UUID.randomUUID();

    }

    public Event(String title, String description, String date, String time, String location, int maxNumberOfAttendees, String organizer) {
        mTitle = title;
        mDescription = description;
        mDate = date;
        mTime = time;
        mLocation = location;
        mMaxNumberOfAttendees = maxNumberOfAttendees;
        mOrganizer = organizer;
        mId = UUID.randomUUID();
    }

    public Event(String title, String description, String date, String time, String location, int maxNumberOfAttendees, String organizer, String zip) {
        mTitle = title;
        mDescription = description;
        mDate = date;
        mTime = time;
        mLocation = location;
        mMaxNumberOfAttendees = maxNumberOfAttendees;
        mOrganizer = organizer;
        mId = UUID.randomUUID();
        mZip = zip;
    }

    // Getters
    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public String getTime() {
        return mTime;
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

    public UUID getId() {
        return mId;
    }

    public String getZip() {
        return mZip;
    }

    // Setters
    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public void setTime(String time) {
        mTime = time;
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
