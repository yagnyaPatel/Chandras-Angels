package edu.ucsb.munchease;

import com.google.gson.*;

import java.util.ArrayList;

// Static class
public class RestaurantParser {
    // Private constructor to enforce static class
    private RestaurantParser() { }

    public static Restaurant parseRestaurantFromYelp(String jsonInput) throws InvalidJsonException {
        // Get entire object
        JsonElement element = new JsonParser().parse(jsonInput);
        JsonObject response = element.getAsJsonObject();

        String alias = response.get("alias").getAsString();
        String name = response.get("name").getAsString();
        String url = response.get("url").getAsString();
        String rating = response.get("rating").getAsString();
        int reviewCount = response.get("review_count").getAsInt();
        String price = response.get("price").getAsString();

        // Get display address from location object
        JsonObject location = response.get("location").getAsJsonObject();
        JsonArray addrArray = location.get("display_address").getAsJsonArray();
        // Iterate through array to concatenatae to string
        String address = new String();
        for(int i = 0; i < addrArray.size(); i++) {
            address += addrArray.get(i).getAsString();
            // Add newline to all lines except last
            if(i < addrArray.size() - 1)
                address += "\n";
        }

        // Get latitude and longitude from coordinates array
        JsonObject coordinates = response.get("coordinates").getAsJsonObject();
        double latitude = coordinates.get("latitude").getAsDouble();
        double longitude = coordinates.get("longitude").getAsDouble();

        // Parse "hours" element into RestaurantSchedule
        JsonArray hours = response.get("hours").getAsJsonArray();
        RestaurantSchedule schedule = parseScheduleFromYelp(hours);

        // Construct return object - Initialize votes to 0, as this is brand new from a Yelp return
        return new Restaurant(alias, name, url, rating, reviewCount, address, price, latitude, longitude, schedule, 0);
    }

    /**
     *
     * @param hoursArray - JsonArray representing the "hours" element in a Yelp return
     * @return A new RestaurantSchedule object parsed from the Yelp return
     */
    public static RestaurantSchedule parseScheduleFromYelp(JsonArray hoursArray) throws InvalidJsonException {
        // Initialize ArrayList
        ArrayList<DaySchedule> daySchedules = new ArrayList<DaySchedule>();

        // Get array of schedules to iterate through
        JsonObject hours = hoursArray.get(0).getAsJsonObject();
        JsonArray openArray = hours.get("open").getAsJsonArray();

        for(int i = 0; i < openArray.size(); i++) {
            JsonObject open = openArray.get(i).getAsJsonObject();
            boolean isOvernight =  open.get("is_overnight").getAsBoolean();
            String start = open.get("start").getAsString();
            String end = open.get("end").getAsString();
            int yelpDay = open.get("day").getAsInt();

            int day = getCalendarDayFromYelpDay(yelpDay);

            DaySchedule ds = new DaySchedule(isOvernight, start, end, day);
            daySchedules.add(ds);
        }

        // Get isOpen from Yelp response and update
        boolean isOpen = hours.get("is_open_now").getAsBoolean();

        return new RestaurantSchedule(isOpen, daySchedules);
    }



    // Helper functions


    // Helper - Convert day format used in Yelp JSON to the day format that the object and database uses
    // Yelp: 0 - 6, with Monday = 0
    // Calendar: 1 - 7, with Sunday = 1;
    private static int getCalendarDayFromYelpDay(int yelpDay) {
        int cDay = yelpDay + 2;
        if(cDay == 8) // Sunday
            cDay = 1;

        return cDay;
    }
}
