package com.digzdigital.reminderapp.data.db;

import com.digzdigital.reminderapp.data.db.model.Course;
import com.digzdigital.reminderapp.data.db.model.RowObject;

import java.util.ArrayList;
import java.util.List;


class RowObjectsCreator {

    private ArrayList<Course> results;
    private ArrayList<RowObject> rowObjects;

    RowObjectsCreator(ArrayList<Course> results) {
        this.results = results;
    }

    ArrayList<RowObject> getRows() {
        rowObjects = new ArrayList<>();
        startCreation();


        return rowObjects;
    }

    private void do7pm(List<Course> otherCourses) {
        String header = determineHeader(12);
        ArrayList<Course> courses = new ArrayList<>(Course.find(Course.class, "time = ?", "12"));

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
    }

    private void do6pm(List<Course> otherCourses) {
        String header = determineHeader(11);
        ArrayList<Course> courses = new ArrayList<>(Course.find(Course.class, "time = ?", "11"));

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do7pm(Course.find(Course.class, "duration = ?", "2"));
    }

    private void do5pm(List<Course> otherCourses) {
        String header = determineHeader(10);
        ArrayList<Course> courses = new ArrayList<>(Course.find(Course.class, "time = ?", "10"));

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do6pm(Course.find(Course.class, "duration = ?", "2"));
    }

    private void do4pm(List<Course> otherCourses) {
        String header = determineHeader(9);
        ArrayList<Course> courses = new ArrayList<>(Course.find(Course.class, "time = ?", "9"));

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do5pm(Course.find(Course.class, "duration = ?", "2"));
    }

    private void do3pm(List<Course> otherCourses) {
        String header = determineHeader(8);
        ArrayList<Course> courses = new ArrayList<>(Course.find(Course.class, "time = ?", "8"));

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do4pm(Course.find(Course.class, "duration = ?", "2"));
    }

    private void do2pm(List<Course> otherCourses) {
        String header = determineHeader(7);
        ArrayList<Course> courses = new ArrayList<>(Course.find(Course.class, "time = ?", "7"));

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do3pm(Course.find(Course.class, "duration = ?", "2"));
    }

    private void do1pm(List<Course> otherCourses) {
        String header = determineHeader(6);
        ArrayList<Course> courses = new ArrayList<>(Course.find(Course.class, "time = ?", "6"));

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do2pm(Course.find(Course.class, "duration = ?", "2"));
    }

    private void do12pm(List<Course> otherCourses) {
        String header = determineHeader(5);
        ArrayList<Course> courses = new ArrayList<>(Course.find(Course.class, "time = ?", "5"));

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do1pm(Course.find(Course.class, "duration = ?", "2"));
    }

    private void do11am(List<Course> otherCourses) {
        String header = determineHeader(4);
        ArrayList<Course> courses = new ArrayList<>(Course.find(Course.class, "time = ?", "4"));

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do12pm(Course.find(Course.class, "duration = ?", "2"));
    }

    private void do10am(List<Course> otherCourses) {
        String header = determineHeader(3);
        ArrayList<Course> courses = new ArrayList<>(Course.find(Course.class, "time = ?", "3"));

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do11am(Course.find(Course.class, "duration = ?", "2"));
    }

    private void do9am(List<Course> otherCourses) {
        String header = determineHeader(2);
        ArrayList<Course> courses = new ArrayList<>(Course.find(Course.class, "time = ?", "2"));

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do10am(Course.find(Course.class, "duration = ?", "2"));
    }

    private void startCreation() {
        String header = determineHeader(1);
        // ArrayList<Course> courses = results.where().equalTo("time", 1).findAll();
        ArrayList<Course> courses = new ArrayList<>(Course.find(Course.class, "time = ?", "1"));

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course>finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        // do9am(courses.where().greaterThan("duration", 1).findAll());
        do9am(Course.find(Course.class, "duration = ?", "2"));
    }

    private String determineHeader(int i) {
        String header = "";
        switch (i) {
            case 1:
                header = "8:00 - 9:00";
                break;
            case 2:
                header = "9:00 - 10:00";
                break;
            case 3:
                header = "10:00 - 11:00";
                break;
            case 4:
                header = "11:00 - 12:00";
                break;
            case 5:
                header = "12:00 - 13:00";
                break;
            case 6:
                header = "13:00 - 14:00";
                break;
            case 7:
                header = "14:00 - 15:00";
                break;
            case 8:
                header = "15:00 - 16:00";
                break;
            case 9:
                header = "16:00 - 17:00";
                break;
            case 10:
                header = "17:00 - 18:00";
                break;
            case 11:
                header = "18:00 - 19:00";
                break;
            case 12:
                header = "19:00 - 20:00";
                break;
        }
        return header;
    }

}
