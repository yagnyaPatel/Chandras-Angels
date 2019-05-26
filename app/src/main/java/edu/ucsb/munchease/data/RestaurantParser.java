package edu.ucsb.munchease.data;

import com.google.gson.*;

import java.util.ArrayList;

// Static class
public class RestaurantParser {
    // Private constructor to enforce static class
    private RestaurantParser() { }

    public static Restaurant parseRestaurantFromYelpResponse(String jsonInput) throws InvalidJsonException {
        // Get entire object
        JsonElement element = new JsonParser().parse(jsonInput);
        JsonObject response = element.getAsJsonObject();
        // TODO deal with hours
        // Ensure response has all needed elements
        // Don't check for "hours", as business search returns objects without schedule info
        if(!(response.has("alias") &&
                response.has("name") &&
                response.has("url") &&
                response.has("rating") &&
                response.has("review_count") &&
                response.has("price") &&
                response.has("location") &&
                response.has("coordinates"))) {
            throw new InvalidJsonException();
        }

        String alias = response.get("alias").getAsString();
        String name = response.get("name").getAsString();
        String url = response.get("url").getAsString();
        String rating = response.get("rating").getAsString();
        int reviewCount = response.get("review_count").getAsInt();
        String price = response.get("price").getAsString();

        // Get display address from location object
        JsonObject location = response.get("location").getAsJsonObject();

        // Check for display_address sub element before parsing
        if(!location.has("display_address")) {
            throw new InvalidJsonException();
        }

        // Parse address
        JsonArray addrArray = location.get("display_address").getAsJsonArray();
        // Iterate through array to concatenatae to string
        String address = new String();
        for(int i = 0; i < addrArray.size(); i++) {
            address += addrArray.get(i).getAsString();
            // Add newline to all lines except last
            if(i < addrArray.size() - 1) {
                address += "\n";
            }
        }

        // Get latitude and longitude from coordinates array
        JsonObject coordinates = response.get("coordinates").getAsJsonObject();

        // Ensure sub elements exist before accessing
        if(!(coordinates.has("latitude") &&
                coordinates.has("longitude"))) {
            throw new InvalidJsonException();
        }

        double latitude = coordinates.get("latitude").getAsDouble();
        double longitude = coordinates.get("longitude").getAsDouble();

        // Parse "hours" element into RestaurantSchedule, if the response has hours info
        RestaurantSchedule schedule = null;
        if(response.has("hours")) {
            JsonArray hours = response.get("hours").getAsJsonArray();
            schedule = parseScheduleFromYelpResponse(hours);
        }

        // Construct return object - Initialize votes to 0, as this is brand new from a Yelp return
        return new Restaurant(alias, name, url, rating, reviewCount, price, address, latitude, longitude, schedule, 0);
    }

    /**
     *
     * @param hoursArray - JsonArray representing the "hours" element in a Yelp return
     * @return A new RestaurantSchedule object parsed from the Yelp return
     */
    public static RestaurantSchedule parseScheduleFromYelpResponse(JsonArray hoursArray) throws InvalidJsonException {
        // Initialize ArrayList
        ArrayList<DaySchedule> daySchedules = new ArrayList<DaySchedule>();

        // Get array of schedules to iterate through
        JsonObject hours = hoursArray.get(0).getAsJsonObject();

        // Make sure "open" and "is_open_now" elements exist
        if(!(hours.has("open") &&
                hours.has("is_open_now"))) {
            throw new InvalidJsonException();
        }

        // Iterate through "open" array
        JsonArray openArray = hours.get("open").getAsJsonArray();
        for(int i = 0; i < openArray.size(); i++) {
            JsonObject open = openArray.get(i).getAsJsonObject();

            // Make sure all elements are present
            if(!(open.has("is_overnight") &&
                    open.has("start") &&
                    open.has("end") &&
                    open.has("day"))) {
                throw new InvalidJsonException();
            }

            boolean isOvernight =  open.get("is_overnight").getAsBoolean();
            String start = open.get("start").getAsString();
            String end = open.get("end").getAsString();
            int yelpDay = open.get("day").getAsInt();

            int day = getCalendarDayFromYelpDay(yelpDay);

            DaySchedule ds = new DaySchedule(isOvernight, start, end, day);

            addDayScheduleToArrayList(ds, daySchedules);
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
        if(cDay == 8) { // Sunday
            cDay = 1;
        }

        return cDay;
    }

    /**
     * Adds schedule to daySchedules in order of ascending schedule.day
     * @param schedule The DaySchedule to be added
     * @param daySchedules The ArrayList of DaySchedules to add schedule to
     */
    private static void addDayScheduleToArrayList(DaySchedule schedule, ArrayList<DaySchedule> daySchedules) {
        // Iterate insertion index until day order is maintained
        // In the event of a tie, the element will be inserted at the end of the block with the same day value
        int index = 0;
        while(index < daySchedules.size() &&
                schedule.getDay() >= daySchedules.get(index).getDay()) {
            index++;
        }

        // Append to end if end was reached, else insert at index
        if(index == daySchedules.size()) {
            daySchedules.add(schedule);
        } else {
            daySchedules.add(index, schedule);
        }
    }
}
