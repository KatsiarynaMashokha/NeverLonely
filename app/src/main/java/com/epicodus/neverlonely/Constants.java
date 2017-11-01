package com.epicodus.neverlonely;

/**
 * Created by katsiarynamashokha on 10/22/17.
 */

public class Constants {
    public static final String OPEN_WEATHER_API_KEY = BuildConfig.OPEN_WEATHER_API_KEY;
    public static final String OPEN_WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    public static final String OPEN_WEATHER_LOCATION_QUERY_PARAM = "q";
    public static final String OPEN_WEATHER_UNITS_QUERY_PARAM = "units";
    public static final String OPEN_WEATHER_UNITS_VALUE = "imperial";
    public static final String OPEN_WEATHER_API_ID_QUERY_PARAM = "APPID";
    public static final String PREFERENCE_EMAIL_KEY = "email";

    // Firebase variables
    public static final String FIREBASE_CHILD_USERS = "users";
    public static final String FIREBASE_CHILD_MY_EVENTS = "events";

    // Intent extras
    public static final String INTENT_EXTRA_POSITION = "position";
    public static final String INTENT_EXTRA_EVENTS = "events";


    // Font title
    public static final String TITLE_FONT_NAME = "fonts/grandhotel.ttf";
}
