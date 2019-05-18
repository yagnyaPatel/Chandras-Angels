package edu.ucsb.munchease;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.google.gson.*;
import java.util.Calendar;

public class RestaurantScheduleUnitTests {
    private RestaurantSchedule schedule; // Local schedule object to use in each test
    private Calendar c;
    private int today; // Integer representation of today's date Sunday:1 - Saturday:7
    private String currentTime; // "HHmm"

    // Update today and currentTime values before running each test
    @Before
    public void updateTime() {
        c = Calendar.getInstance();
        today = c.get(Calendar.DAY_OF_WEEK);

        int currentHour = c.get(Calendar.HOUR_OF_DAY);
        int currentMinute = c.get(Calendar.MINUTE);
        currentTime = String.format("%02d%02d", currentHour, currentMinute);
    }

    // updateIsOpen, getNextClosingTime, getNextOpeningTime

    // TODO compensate for is_overnight
    /**
     * Parameterized function that all updateIsOpen tests will call with different inputs
     * @param json - the JSON string to parse to run the tests
     * @throws InvalidJsonException
     */
    public void testUpdateIsOpen(String json) throws InvalidJsonException {
        json = SampleJsonRestaurantSchedules.consistent;
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        JsonArray hours = obj.get("hours").getAsJsonArray();
        schedule = RestaurantParser.parseScheduleFromYelpResponse(hours);

        boolean isOpen = schedule.updateIsOpen();

        // Attempt to find a schedule object that would confirm it is currently open
        boolean found = false;
        for(int i = 0; i < schedule.getScheduleSize(); i++) {
            DaySchedule ds = schedule.getDayScheduleAtIndex(i);
            if(ds.getDay() == today) {
                if(currentTime.compareTo(ds.getStartTime()) >= 0 &&
                        currentTime.compareTo(ds.getEndTime()) < 0) {
                    //Current time is within the DaySchedule window
                    found = true;
                    break;
                }
            }
        }

        // Assert that either it is closed and no open schedule was found, or
        // it is open and an open schedule was found
        assertTrue(isOpen == found);
    }

    public void testGetNextOpeningTime(String json) throws InvalidJsonException {
        json = SampleJsonRestaurantSchedules.consistent;
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        JsonArray hours = obj.get("hours").getAsJsonArray();
        schedule = RestaurantParser.parseScheduleFromYelpResponse(hours);

        String nextOpeningTime = schedule.getNextOpeningTime();

        if(schedule.getIsOpen()) {
            assertNull(nextOpeningTime);
        } else {
            //assertNull()
        }
        // TODO stub
        fail();
    }

    public void testGetNextClosingTime(String json) throws InvalidJsonException {
        json = SampleJsonRestaurantSchedules.consistent;
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        JsonArray hours = obj.get("hours").getAsJsonArray();
        schedule = RestaurantParser.parseScheduleFromYelpResponse(hours);

        String nextClosingTime = schedule.getNextClosingTime();

        if(schedule.getIsOpen()) {
            // Find current open index
            int index = -1;
            for(int i = 0; i < schedule.getScheduleSize(); i++) {
                DaySchedule ds = schedule.getDayScheduleAtIndex(i);
                if(ds.getDay() == today) {
                    if(currentTime.compareTo(ds.getStartTime()) >= 0 &&
                            currentTime.compareTo(ds.getEndTime()) < 0) {
                        //Current time is within the DaySchedule window
                        index = i;
                        break;
                    }
                }
            }

            // Assert that closing time at index is the same as the function call
            assertEquals(schedule.getDayScheduleAtIndex(index).getEndTime(),
                    nextClosingTime);
        } else {
            assertNull(nextClosingTime);
        }
    }

    // Actual tests
    // TODO test overnight samples for all tests too
    @Test
    public void testUpdateIsOpen_0() throws InvalidJsonException {
        testUpdateIsOpen(SampleJsonRestaurantSchedules.consistent);
    }

    @Test
    public void testUpdateIsOpen_1() throws InvalidJsonException {
        testUpdateIsOpen(SampleJsonRestaurantSchedules.ascending);
    }

    @Test
    public void testUpdateIsOpen_2() throws InvalidJsonException {
        testUpdateIsOpen(SampleJsonRestaurantSchedules.multipleSlotsInDay);
    }

    @Test
    public void testGetNextOpeningTime_0() throws InvalidJsonException {
        testGetNextOpeningTime(SampleJsonRestaurantSchedules.consistent);
    }

    public void testGetNextOpeningTime_1() throws InvalidJsonException {
        testGetNextOpeningTime(SampleJsonRestaurantSchedules.ascending);
    }

    public void testGetNextOpeningTime_2() throws InvalidJsonException {
        testGetNextOpeningTime(SampleJsonRestaurantSchedules.multipleSlotsInDay);
    }

    public void testGetNextClosingTime_0() throws InvalidJsonException {
        testGetNextClosingTime(SampleJsonRestaurantSchedules.consistent);
    }

    public void testGetNextClosingTime_1() throws InvalidJsonException {
        testGetNextClosingTime(SampleJsonRestaurantSchedules.ascending);
    }

    public void testGetNextClosingTime_2() throws InvalidJsonException {
        testGetNextClosingTime(SampleJsonRestaurantSchedules.multipleSlotsInDay);
    }
}
