package edu.ucsb.munchease.data;

public class DaySchedule {
    private boolean isOvernight;
    private String start;
    private String end;
    private int day; // same format as java.util.Calendar enum: 1 = Sunday, 7 = Saturday


    public DaySchedule(boolean isOvernight, String start, String end, int day) {
        this.isOvernight = isOvernight;
        this.start = start;
        this.end = end;
        this.day = day;
    }

    public int getDay() { return day; }
    public String getStartTime() { return start; }
    public String getEndTime() { return end; }
    public boolean getIsOvernight() { return isOvernight; }
}
