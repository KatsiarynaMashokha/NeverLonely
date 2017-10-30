# Never Lonely App
####  Never Lonely is an Android App is written in Java. Published on 10/13/2017.
#### By **Katsiaryna Mashokha**
## Description
Have you ever been in a situation when you wanted to do something but you had to skip it because all your friends were busy? Of course you have. It happened to me as well.
I love loneliness, but for some things to enjoy you really need someone being around you. We are all busy all the time. Very often your and your friends free time differ.
For example, my ex roommate: we loved spending time together, but due to our completely different schedules we had to miss on many things.
Also lots of research was done about how lonely people are they come to the same conclusion: percentage who finds themselves lonely is very high.
Thus I want to solve this problem about feeling lonely and not being able to do things we love by 'Never Lonely' app. Using this app you can create an event you want to
do, like a museum visit or just a walk, and other people can join you. If you don't like loud companies, just specify the maximum number of people you want to join you, as well
as some other characteristics. Download the app and have some good time!


## Setup/Installation Requirements
Download or clone the following project from the gitHub by tapping "Download" or using 'git clone' from the terminal. Then run it on Android studio emulator or on your Android device.
On order to successfully run the application, you are required to use the following API keys:
1) Google Places API key (https://developers.google.com/places/web-service/get-api-key). The key needs to be places in AndroidManifest.xml file
right below the following line: 'android:name="com.google.android.geo.API_KEY"'
2) Open Weather Map API key (https://openweathermap.org/api). The key need to be placed into build.gradle (Module: app) file
into the following section  buildTypes.each {it.buildConfigField 'String', 'OPEN_WEATHER_API_KEY', OpenWeatherMapApiKey } instead of OpenWeatherMapApiKey.

## Support and contact details
For any concerns or questions email to: katsiarynamashokha@gmail.com

### License
Copyright (c) 2017 **_Katsiaryna Mashokha_**