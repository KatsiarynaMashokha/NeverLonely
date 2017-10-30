package com.epicodus.neverlonely.models;

import org.parceler.Parcel;

import java.util.UUID;

/**
 * Created by katsiarynamashokha on 10/13/17.
 */

@Parcel
public class Event{
    String mTitle;
    String mDescription;
    String mDate;
    String mTime;
    String mLocation;
    int mMaxNumberOfAttendees;
    String mOrganizer;
    String mId;
    int mCurrentNumOfAttendees = 0;
    String mZip;

    public Event() {
    }

    public Event(String mTitle, String mDescription, String mDate, String mTime, String mLocation, int mMaxNumberOfAttendees,
                 String mOrganizer, String mZip) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mLocation = mLocation;
        this.mMaxNumberOfAttendees = mMaxNumberOfAttendees;
        this.mOrganizer = mOrganizer;
        this.mId = UUID.randomUUID().toString();
        this.mZip = mZip;
    }

    public Event(String mTitle, String mDescription, String mDate, String mTime, String mLocation, int mMaxNumberOfAttendees,
                 String mOrganizer, String mId, int mCurrentNumOfAttendees, String mZip) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mLocation = mLocation;
        this.mMaxNumberOfAttendees = mMaxNumberOfAttendees;
        this.mOrganizer = mOrganizer;
        this.mId = mId;
        this.mCurrentNumOfAttendees = mCurrentNumOfAttendees;
        this.mZip = mZip;
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

    public String getId() {
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
