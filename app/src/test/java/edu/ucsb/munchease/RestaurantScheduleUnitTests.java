package edu.ucsb.munchease;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.google.gson.*;
import java.util.Calendar;

public class RestaurantScheduleUnitTests {
    private RestaurantSchedule schedule;
    private Calendar c;
    private int today;
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
                }
            }
        }

        // Assert that either it is closed and no open schedule was found, or
        // it is open and an open schedule was found
        assertTrue(isOpen == found);
    }

    // Actual tests
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
}
