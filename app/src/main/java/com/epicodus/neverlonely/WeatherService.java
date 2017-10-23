package com.epicodus.neverlonely;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by katsiarynamashokha on 10/22/17.
 */

public class WeatherService {
    public static void findWeather(String location, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.OPEN_WEATHER_BASE_URL).newBuilder()
                .addQueryParameter(Constants.OPEN_WEATHER_LOCATION_QUERY_PARAM, location)
                .addQueryParameter(Constants.OPEN_WEATHER_UNITS_QUERY_PARAM, Constants.OPEN_WEATHER_UNITS_VALUE)
                .addQueryParameter(Constants.OPEN_WEATHER_API_ID_QUERY_PARAM, Constants.OPEN_WEATHER_API_KEY);

        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();

        // A call is a request that has been prepared for execution
        Call call = client.newCall(request);
        // Schedules the request to be executed at some point in the future
        call.enqueue(callback);
    }

    public String processResult(Response response) {
        String description = "";
        try {
            String jsonData = response.body().string();
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray weatherJsonArray = jsonObject.getJSONArray("weather");
            description = weatherJsonArray.getJSONObject(0).getString("description");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return description;
    }
}
