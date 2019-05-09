package edu.ucsb.munchease;

public class DaySchedule {
    private int day; // same format as java.util.Calendar enum: 1 = Sunday, 7 = Saturday
    private String start;
    private String end;
    private boolean isOvernight;

    public DaySchedule(int day, String start, String end, boolean isOvernight) {
        this.day = day;
        this.start = start;
        this.end = end;
        this.isOvernight = isOvernight;
    }

    public int getDay() { return day; }
    public String getStartTime() { return start; }
    public String getEndTime() { return end; }
    public boolean getIsOvernight() { return isOvernight; }
}
