package edu.ucsb.munchease;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Test;

import edu.ucsb.munchease.data.DaySchedule;
import edu.ucsb.munchease.data.InvalidJsonException;
import edu.ucsb.munchease.data.Restaurant;
import edu.ucsb.munchease.data.RestaurantParser;
import edu.ucsb.munchease.data.RestaurantSchedule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RestaurantParserUnitTests {

    // Use this string as json to parse in all tests
    private String json;

    // SCHEDULE PARSER TESTS

    // Test 1 - ensure InvalidJsonException is thrown on incomplete response
    @Test(expected= InvalidJsonException.class)
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
            assertEquals("1730", ds.getStartTime());
            assertEquals("2200", ds.getEndTime());
            assertEquals(ds.getDay(), i + 1); // Days should be in order from 1 - 7
        }
    }

    // Ensure schedule order is maintained with multiple same-day slots
    @Test
    public void testScheduleParser_1() throws InvalidJsonException {
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


    @Test
    public void testRestaurantParser_0() throws InvalidJsonException {
        json = SampleJsonRestaurants.completeExample;
        Restaurant r = RestaurantParser.parseRestaurantFromYelpResponse(json);

        assertEquals("gary-danko-san-francisco", r.getAlias());
        assertEquals("Gary Danko", r.getName());
        assertEquals("https://www.yelp.com/biz/gary-danko-san-francisco?adjust_creative=wpr6gw4FnptTrk1CeT8POg&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=wpr6gw4FnptTrk1CeT8POg",
                r.getUrl());
        assertEquals("4.5", r.getRating());
        assertEquals(5296, r.getReviewCount());
        assertEquals("$$$$", r.getPrice());
        assertEquals("800 N Point St\nSan Francisco, CA 94109", r.getAddress());
        assertEquals(37.80587, r.getLatitude(), 0.0001);
        assertEquals(-122.42058, r.getLongitude(), 0.0001);

        //assertNotNull(r.getSchedule());
    }









}
