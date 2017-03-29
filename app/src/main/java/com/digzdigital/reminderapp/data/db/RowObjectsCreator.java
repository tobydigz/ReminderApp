package com.digzdigital.reminderapp.data.db;

import com.digzdigital.reminderapp.data.db.model.Course;
import com.digzdigital.reminderapp.data.db.model.RowObject;

import java.util.ArrayList;

import io.realm.RealmResults;


class RowObjectsCreator {

    private RealmResults<Course> results;
    private ArrayList<RowObject> rowObjects;

    RowObjectsCreator(RealmResults<Course> results) {
        this.results = results;
    }

    ArrayList<RowObject> getRows() {
        rowObjects = new ArrayList<>();
        startCreation();


        return rowObjects;
    }

    private void do7pm(RealmResults<Course> otherCourses) {
        String header = determineHeader(12);
        RealmResults<Course> courses = results.where().equalTo("time", 12).findAll();

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
    }

    private void do6pm(RealmResults<Course> otherCourses) {
        String header = determineHeader(11);
        RealmResults<Course> courses = results.where().equalTo("time", 11).findAll();

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do7pm(courses.where().greaterThan("duration", 1).findAll());
    }

    private void do5pm(RealmResults<Course> otherCourses) {
        String header = determineHeader(10);
        RealmResults<Course> courses = results.where().equalTo("time", 10).findAll();

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do6pm(courses.where().greaterThan("duration", 1).findAll());
    }

    private void do4pm(RealmResults<Course> otherCourses) {
        String header = determineHeader(9);
        RealmResults<Course> courses = results.where().equalTo("time", 9).findAll();

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do5pm(courses.where().greaterThan("duration", 1).findAll());
    }

    private void do3pm(RealmResults<Course> otherCourses) {
        String header = determineHeader(8);
        RealmResults<Course> courses = results.where().equalTo("time", 8).findAll();

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do4pm(courses.where().greaterThan("duration", 1).findAll());
    }

    private void do2pm(RealmResults<Course> otherCourses) {
        String header = determineHeader(7);
        RealmResults<Course> courses = results.where().equalTo("time", 7).findAll();

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do3pm(courses.where().greaterThan("duration", 1).findAll());
    }

    private void do1pm(RealmResults<Course> otherCourses) {
        String header = determineHeader(6);
        RealmResults<Course> courses = results.where().equalTo("time", 6).findAll();

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do2pm(courses.where().greaterThan("duration", 1).findAll());
    }

    private void do12pm(RealmResults<Course> otherCourses) {
        String header = determineHeader(5);
        RealmResults<Course> courses = results.where().equalTo("time", 5).findAll();

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do1pm(courses.where().greaterThan("duration", 1).findAll());
    }

    private void do11am(RealmResults<Course> otherCourses) {
        String header = determineHeader(4);
        RealmResults<Course> courses = results.where().equalTo("time", 4).findAll();

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do12pm(courses.where().greaterThan("duration", 1).findAll());
    }

    private void do10am(RealmResults<Course> otherCourses) {
        String header = determineHeader(3);
        RealmResults<Course> courses = results.where().equalTo("time", 3).findAll();

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do11am(courses.where().greaterThan("duration", 1).findAll());
    }

    private void do9am(RealmResults<Course> otherCourses) {
        String header = determineHeader(2);
        RealmResults<Course> courses = results.where().equalTo("time", 2).findAll();

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        finalCourses.addAll(otherCourses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do10am(courses.where().greaterThan("duration", 1).findAll());
    }

    private void startCreation() {
        String header = determineHeader(1);
        RealmResults<Course> courses = results.where().equalTo("time", 1).findAll();

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course>finalCourses = new ArrayList<>();
        finalCourses.addAll(courses);
        rowObject.setCourses(finalCourses);

        rowObjects.add(rowObject);
        do9am(courses.where().greaterThan("duration", 1).findAll());
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
