package com.epicodus.neverlonely;

/**
 * Created by katsiarynamashokha on 10/13/17.
 */

public class Event {
    private String mTitle;
    private String mDate;
    private String mLocation;
    private String mNumberOfParticipants;
    private String mOrganizer;

    public Event(String title, String date) {
        mTitle = title;
        mDate = date;
    }

    public Event(String title, String date, String location, String numberOfParticipants, String organizer) {
        mTitle = title;
        mDate = date;
        mLocation = location;
        mNumberOfParticipants = numberOfParticipants;
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

    public String getNumberOfParticipants() {
        return mNumberOfParticipants;
    }

    public String getOrganizer() {
        return mOrganizer;
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

    public void setNumberOfParticipants(String numberOfParticipants) {
        mNumberOfParticipants = numberOfParticipants;
    }

    public void setOrganizer(String organizer) {
        mOrganizer = organizer;
    }
}
