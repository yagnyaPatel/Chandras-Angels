package edu.ucsb.munchease;

import org.junit.Test;
import org.junit.Assert.*;

import com.google.gson.*;

import static org.junit.Assert.*;

public class RestaurantParserUnitTests {

    // Use this string as json to parse in all tests
    private String json;

    // SCHEDULE PARSER TESTS

    // Test 1 - ensure InvalidJsonException is thrown on incomplete response
    @Test(expected=InvalidJsonException.class)
    public void testScheduleParserException() throws InvalidJsonException {
        json = SampleJsonRestaurantSchedules.incomplete;
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        JsonArray hours = obj.get("hours").getAsJsonArray();

        // This should throw exception because input string is incomplete
        RestaurantSchedule schedule = RestaurantParser.parseScheduleFromYelpResponse(hours);
    }

    // Ensure all fields are what they should be and proper day order is maintained
    @Test
    public void testScheduleParser_0() throws InvalidJsonException {
        // Parse object from json
        json = SampleJsonRestaurantSchedules.consistent;
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        JsonArray hours = obj.get("hours").getAsJsonArray();
        RestaurantSchedule schedule = RestaurantParser.parseScheduleFromYelpResponse(hours);

        // Iterate through, make sure fields match what they should match
        for(int i = 0; i < schedule.getScheduleSize(); i++) {
            DaySchedule ds = schedule.getDayScheduleAtIndex(i);
            assertFalse(ds.getIsOvernight());
            assertTrue(ds.getStartTime().equals("1730"));
            assertTrue(ds.getEndTime().equals("2200"));
            assertEquals(i + 1, ds.getDay()); // Days should be in order from 1 - 7
        }
    }

    // Ensure schedule order is maintained with multiple same-day slots
    @Test
    public void testShcheduleParser_1() throws InvalidJsonException {
        // Parse object from json
        json = SampleJsonRestaurantSchedules.multipleSlotsInDay;
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        JsonArray hours = obj.get("hours").getAsJsonArray();
        RestaurantSchedule schedule = RestaurantParser.parseScheduleFromYelpResponse(hours);

        // Iterate through, make sure days are tied or in order
        // In tie, ensure start times are in order
        int previousDay = 0;
        String previousOpeningTime = "0000";
        for(int i = 0; i < schedule.getScheduleSize(); i++) {
            DaySchedule ds = schedule.getDayScheduleAtIndex(i);

            assertTrue(previousDay <= ds.getDay());

            if(previousDay == ds.getDay()) {
                assertTrue(previousOpeningTime.compareTo(ds.getStartTime()) < 0);
            }

            previousDay = ds.getDay();
            previousOpeningTime = ds.getStartTime();
        }
    }

    // RESTAURANT PARSER TESTS
    @Test(expected=InvalidJsonException.class)
    public void testRestaurantParserException() throws InvalidJsonException {
        json = SampleJsonRestaurants.incompleteExample;
        Restaurant r = RestaurantParser.parseRestaurantFromYelpResponse(json);
    }
}
