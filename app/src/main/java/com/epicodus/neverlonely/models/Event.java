package com.epicodus.neverlonely.models;

import org.parceler.Parcel;

import java.util.UUID;

/**
 * Created by katsiarynamashokha on 10/13/17.
 */

@Parcel
public class Event{
    String title;
    String description;
    String date;
    String time;
    String location;
    int maxNumberOfAttendees;
    String organizer;
    String id;
    int currentNumOfAttendees = 0;
    String zip;
    String index;

    public Event() {
    }

    public Event(String title, String description, String date, String time, String location, int maxNumberOfAttendees,
                 String organizer, String zip) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
        this.maxNumberOfAttendees = maxNumberOfAttendees;
        this.organizer = organizer;
        this.id = UUID.randomUUID().toString();
        this.zip = zip;
        this.index = "not_specified";
    }

//    public Event(String mTitle, String mDescription, String mDate, String mTime, String mLocation, int mMaxNumberOfAttendees,
//                 String mOrganizer, String mId, int mCurrentNumOfAttendees, String mZip) {
//        this.mTitle = mTitle;
//        this.mDescription = mDescription;
//        this.mDate = mDate;
//        this.mTime = mTime;
//        this.mLocation = mLocation;
//        this.mMaxNumberOfAttendees = mMaxNumberOfAttendees;
//        this.mOrganizer = mOrganizer;
//        this.mId = mId;
//        this.mCurrentNumOfAttendees = mCurrentNumOfAttendees;
//        this.mZip = mZip;
//    }

    // Getters


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public int getMaxNumberOfAttendees() {
        return maxNumberOfAttendees;
    }

    public String getOrganizer() {
        return organizer;
    }

    public String getId() {
        return id;
    }

    public int getCurrentNumOfAttendees() {
        return currentNumOfAttendees;
    }

    public String getZip() {
        return zip;
    }

    public String getIndex() {
        return index;
    }

    // Setters


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMaxNumberOfAttendees(int maxNumberOfAttendees) {
        this.maxNumberOfAttendees = maxNumberOfAttendees;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCurrentNumOfAttendees(int currentNumOfAttendees) {
        this.currentNumOfAttendees = currentNumOfAttendees;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void addNewAttendee() {
        currentNumOfAttendees++;
    }
}
