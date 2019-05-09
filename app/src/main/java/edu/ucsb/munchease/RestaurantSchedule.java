package edu.ucsb.munchease;

import java.util.ArrayList;
import java.util.Calendar;
import com.google.gson.*;

public class RestaurantSchedule {
    private boolean isOpen;
    private ArrayList<DaySchedule> daySchedules;

    // Construct from JSON hours[] array from yelp return
    // Throw exception if bad array
    public RestaurantSchedule(JsonArray hours)
            throws IllegalArgumentException {
        daySchedules = new ArrayList<DaySchedule>();

        // TODO: Parse JSON into arguments
        // TODO: Convert yelp day to calendar format before inserting
    }

    public boolean getIsOpen() { return isOpen; }

    // Call this if it is currently closed
    // format: "HH:HH,d" where HH:HH is the 24 hour format and d is the integer value of the day
    // Uses Calendar day format: 1 = Sunday, 7 = Saturday
    // Returns null if it is currently open
    public String getNextOpeningTime() {
        if(isOpen)
            return null;

        // Get index of the first DaySchedule that has NOT occurred
        int index = getIndexOfLatestDaySchedule() + 1;
        if(index == daySchedules.size())
            index = 0;

        return daySchedules.get(index).getStartTime();
    }

    // Call this if it is currently open
    // format: "HH:HH,d" where HH:HH is the 24 hour format and d is the integer value of the day
    // Uses Calendar day format: 1 = Sunday, 7 = Saturday
    // Returns null if it is currently closed
    public String getNextClosingTime() {
        if(!isOpen)
            return null;

        int index =
    }

    /* TODO may not need this
    public DaySchedule getScheduleAtDay(int calendarDay) {
        for(DaySchedule ds : daySchedules) {
            if(ds.getDay() == calendarDay) {
                return ds;
            }
        }
        return null;
    }

    private DaySchedule getTodaySchedule() {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);


    }*/

    // Helper function - Use the java.util.Calendar day to get the day value stored in the Yelp returns


    // Get yelp date integer format from
    /* TODO may not need this
    private static int getYelpDayFromDay(int calendarDay) {
        // Adjust date format
        int yelpDay = calendarDay - 2;
        if(yelpDay == -1) // Sunday
            yelpDay = 6;

        return yelpDay;
    } */

    private static int getCalendarDayFromYelpDay(int yelpDay) {
        int cDay = yelpDay + 2;
        if(cDay == 8) // Sunday
            cDay = 1;

        return cDay;
    }

    // Returns the index of daySchedules of the current or most recent window
    private int getIndexOfLatestDaySchedule() {
        Calendar c = Calendar.getInstance();
        int today = c.get(Calendar.DAY_OF_WEEK);

        int currentHour = c.get(Calendar.HOUR_OF_DAY);
        int currentMinute = c.get(Calendar.MINUTE);

        // Format as "HHmm" string
        String currentTime = String.format("%02d%02d", currentHour, currentMinute);

        // Iterate with index because it is needed
        int index = 0;
        while(index < daySchedules.size()) {
            // Shorthand
            DaySchedule current = daySchedules.get(index);

            // Increment if it is earlier in the week than today
            if(current.getDay() < today) {
                index++;
                continue;
            }

            // Stop if index has passed current day
            // Decrement index
            if(current.getDay() > today) {
                index--;
                break;
            }

            // The schedule being looked at is today
            // If the shift has already started, increment by one
            // If the shift has not started yet, return the previous index
        }

        // If the end was reached, then the schedule at the last index was the most recent shift
        if(index == daySchedules.size())
            index--;

        return index;

        // TODO

    }
}
