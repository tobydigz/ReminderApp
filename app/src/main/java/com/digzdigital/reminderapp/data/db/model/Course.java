package com.digzdigital.reminderapp.data.db.model;


import java.util.Date;


public class Course{

    private String courseCode;
    private String courseTitle;
    private String venue;
    private int day;
    private Date startTime;
    private int duration;

    public Course() {
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getDay(){
        return day;
    }
    public String getDayText() {
        String dayOfWeek ="";
        switch (day){
            case 0:
                dayOfWeek = "Monday";
                break;
            case 1:
                dayOfWeek = "Tuesday";
                break;
            case 2:
                dayOfWeek = "Wednesday";
                break;
            case 3:
                dayOfWeek = "Thursday";
                break;
            case 4:
                dayOfWeek = "Friday";
                break;
            case 5:
                dayOfWeek = "Saturday";
                break;
            case 6:
                dayOfWeek = "Sunday";
                break;
            default:
                dayOfWeek ="lol";
                break;
        }
        return dayOfWeek;
    }

    public int getDayInt(){
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = (int) duration;
    }
}
