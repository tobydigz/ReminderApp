package com.digzdigital.reminderapp.data.db;

import com.digzdigital.reminderapp.data.db.model.Course;
import com.digzdigital.reminderapp.data.db.model.RowObject;

import java.util.ArrayList;


class RowObjectsCreator {

    private ArrayList<Course> results;
    private ArrayList<RowObject> rowObjects;
    private Course course = new Course();

    RowObjectsCreator(ArrayList<Course> results) {
        this.results = results;
        course.setCourseCode("");
    }

    ArrayList<RowObject> getRows() {
        rowObjects = new ArrayList<>();
        RowObject rowObject;
        rowObject = do8am();
        rowObjects.add(rowObject);
        rowObject = do9am();
        rowObjects.add(rowObject);
        rowObject = do10am();
        rowObjects.add(rowObject);
        rowObject = do11am();
        rowObjects.add(rowObject);
        rowObject = do12am();
        rowObjects.add(rowObject);
        rowObject = do1pm();
        rowObjects.add(rowObject);
        rowObject = do2pm();
        rowObjects.add(rowObject);
        rowObject = do3pm();
        rowObjects.add(rowObject);
        rowObject = do4pm();
        rowObjects.add(rowObject);
        rowObject = do5pm();
        rowObjects.add(rowObject);
        rowObject = do6pm();
        rowObjects.add(rowObject);
        rowObject = do7pm();
        rowObjects.add(rowObject);
        return rowObjects;
    }

    private RowObject do7pm() {
        String header = determineHeader(12);

        ArrayList<Course> monCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "1", "1") != null)
            monCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "1", "1"));
        else monCourses.add(course);

        ArrayList<Course> tueCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "12", "2") != null)
            tueCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "12", "2"));
        else tueCourses.add(course);

        ArrayList<Course> wedCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "12", "3") != null)
            wedCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "12", "3"));
        else wedCourses.add(course);

        ArrayList<Course> thuCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "12", "4") != null)
            thuCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "12", "4"));
        else thuCourses.add(course);

        ArrayList<Course> friCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "12", "5") != null)
            friCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "12", "5"));
        else friCourses.add(course);
        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(monCourses);
        finalCourses.addAll(tueCourses);
        finalCourses.addAll(wedCourses);
        finalCourses.addAll(thuCourses);
        finalCourses.addAll(friCourses);
        rowObject.setCourses(finalCourses);
        return rowObject;
    }

    private RowObject do6pm() {
        String header = determineHeader(11);

        ArrayList<Course> monCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "1", "1") != null)
            monCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "11", "1"));
        else monCourses.add(course);

        ArrayList<Course> tueCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "11", "2") != null)
            tueCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "11", "2"));
        else tueCourses.add(course);

        ArrayList<Course> wedCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "11", "3") != null)
            wedCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "11", "3"));
        else wedCourses.add(course);

        ArrayList<Course> thuCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "11", "4") != null)
            thuCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "11", "4"));
        else thuCourses.add(course);

        ArrayList<Course> friCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "11", "5") != null)
            friCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "11", "5"));
        else friCourses.add(course);

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(monCourses);
        finalCourses.addAll(tueCourses);
        finalCourses.addAll(wedCourses);
        finalCourses.addAll(thuCourses);
        finalCourses.addAll(friCourses);
        rowObject.setCourses(finalCourses);
        return rowObject;

    }

    private RowObject do5pm() {
        String header = determineHeader(10);

        ArrayList<Course> monCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "10", "1") != null)
            monCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "10", "1"));
        else monCourses.add(course);

        ArrayList<Course> tueCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "10", "2") != null)
            tueCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "10", "2"));
        else tueCourses.add(course);

        ArrayList<Course> wedCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "10", "3") != null)
            wedCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "10", "3"));
        else wedCourses.add(course);

        ArrayList<Course> thuCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "10", "4") != null)
            thuCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "10", "4"));
        else thuCourses.add(course);

        ArrayList<Course> friCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "10", "5") != null)
            friCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "10", "5"));
        else friCourses.add(course);
        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(monCourses);
        finalCourses.addAll(tueCourses);
        finalCourses.addAll(wedCourses);
        finalCourses.addAll(thuCourses);
        finalCourses.addAll(friCourses);
        rowObject.setCourses(finalCourses);
        return rowObject;

    }

    private RowObject do4pm() {
        String header = determineHeader(9);

        ArrayList<Course> monCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "9", "1") != null)
            monCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "9", "1"));
        else monCourses.add(course);

        ArrayList<Course> tueCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "9", "2") != null)
            tueCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "9", "2"));
        else tueCourses.add(course);

        ArrayList<Course> wedCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "9", "3") != null)
            wedCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "9", "3"));
        else wedCourses.add(course);

        ArrayList<Course> thuCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "9", "4") != null)
            thuCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "9", "4"));
        else thuCourses.add(course);

        ArrayList<Course> friCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "9", "5") != null)
            friCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "9", "5"));
        else friCourses.add(course);

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(monCourses);
        finalCourses.addAll(tueCourses);
        finalCourses.addAll(wedCourses);
        finalCourses.addAll(thuCourses);
        finalCourses.addAll(friCourses);
        rowObject.setCourses(finalCourses);
        return rowObject;

    }

    private RowObject do3pm() {
        String header = determineHeader(8);

        ArrayList<Course> monCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "8", "1") != null)
            monCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "8", "1"));
        else monCourses.add(course);

        ArrayList<Course> tueCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "8", "2") != null)
            tueCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "8", "2"));
        else tueCourses.add(course);

        ArrayList<Course> wedCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "8", "3") != null)
            wedCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "8", "3"));
        else wedCourses.add(course);

        ArrayList<Course> thuCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "8", "4") != null)
            thuCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "8", "4"));
        else thuCourses.add(course);

        ArrayList<Course> friCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "8", "5") != null)
            friCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "8", "5"));
        else friCourses.add(course);

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(monCourses);
        finalCourses.addAll(tueCourses);
        finalCourses.addAll(wedCourses);
        finalCourses.addAll(thuCourses);
        finalCourses.addAll(friCourses);
        rowObject.setCourses(finalCourses);
        return rowObject;

    }

    private RowObject do2pm() {
        String header = determineHeader(7);

        ArrayList<Course> monCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "7", "1") != null)
            monCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "7", "1"));
        else monCourses.add(course);

        ArrayList<Course> tueCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "7", "2") != null)
            tueCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "7", "2"));
        else tueCourses.add(course);

        ArrayList<Course> wedCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "7", "3") != null)
            wedCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "7", "3"));
        else wedCourses.add(course);

        ArrayList<Course> thuCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "7", "4") != null)
            thuCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "7", "4"));
        else thuCourses.add(course);

        ArrayList<Course> friCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "7", "5") != null)
            friCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "7", "5"));
        else friCourses.add(course);

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(monCourses);
        finalCourses.addAll(tueCourses);
        finalCourses.addAll(wedCourses);
        finalCourses.addAll(thuCourses);
        finalCourses.addAll(friCourses);
        rowObject.setCourses(finalCourses);
        return rowObject;

    }

    private RowObject do1pm() {
        String header = determineHeader(6);

        ArrayList<Course> monCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "1", "1") != null)
            monCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "1", "1"));
        else monCourses.add(course);

        ArrayList<Course> tueCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "6", "2") != null)
            tueCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "6", "2"));
        else tueCourses.add(course);

        ArrayList<Course> wedCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "6", "3") != null)
            wedCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "6", "3"));
        else wedCourses.add(course);

        ArrayList<Course> thuCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "6", "4") != null)
            thuCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "6", "4"));
        else thuCourses.add(course);

        ArrayList<Course> friCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "6", "5") != null)
            friCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "6", "5"));
        else friCourses.add(course);

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(monCourses);
        finalCourses.addAll(tueCourses);
        finalCourses.addAll(wedCourses);
        finalCourses.addAll(thuCourses);
        finalCourses.addAll(friCourses);
        rowObject.setCourses(finalCourses);
        return rowObject;

    }

    private RowObject do12am() {
        String header = determineHeader(5);

        ArrayList<Course> monCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "1", "1") != null)
            monCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "1", "1"));
        else monCourses.add(course);

        ArrayList<Course> tueCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "5", "2") != null)
            tueCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "5", "2"));
        else tueCourses.add(course);

        ArrayList<Course> wedCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "5", "3") != null)
            wedCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "5", "3"));
        else wedCourses.add(course);

        ArrayList<Course> thuCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "5", "4") != null)
            thuCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "5", "4"));
        else thuCourses.add(course);

        ArrayList<Course> friCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "5", "5") != null)
            friCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "5", "5"));
        else friCourses.add(course);

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(monCourses);
        finalCourses.addAll(tueCourses);
        finalCourses.addAll(wedCourses);
        finalCourses.addAll(thuCourses);
        finalCourses.addAll(friCourses);
        rowObject.setCourses(finalCourses);
        return rowObject;

    }

    private RowObject do11am() {
        String header = determineHeader(4);

        ArrayList<Course> monCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "4", "1") != null)
            monCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "4", "1"));
        else monCourses.add(course);

        ArrayList<Course> tueCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "4", "2") != null)
            tueCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "4", "2"));
        else tueCourses.add(course);

        ArrayList<Course> wedCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "4", "3") != null)
            wedCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "4", "3"));
        else wedCourses.add(course);

        ArrayList<Course> thuCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "4", "4") != null)
            thuCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "4", "4"));
        else thuCourses.add(course);

        ArrayList<Course> friCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "4", "5") != null)
            friCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "4", "5"));
        else friCourses.add(course);

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(monCourses);
        finalCourses.addAll(tueCourses);
        finalCourses.addAll(wedCourses);
        finalCourses.addAll(thuCourses);
        finalCourses.addAll(friCourses);
        rowObject.setCourses(finalCourses);
        return rowObject;

    }

    private RowObject do10am() {
        String header = determineHeader(3);

        ArrayList<Course> monCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "3", "1") != null)
            monCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "3", "1"));
        else monCourses.add(course);

        ArrayList<Course> tueCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "3", "2") != null)
            tueCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "3", "2"));
        else tueCourses.add(course);

        ArrayList<Course> wedCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "3", "3") != null)
            wedCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "3", "3"));
        else wedCourses.add(course);

        ArrayList<Course> thuCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "3", "4") != null)
            thuCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "3", "4"));
        else thuCourses.add(course);

        ArrayList<Course> friCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "3", "5") != null)
            friCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "3", "5"));
        else friCourses.add(course);

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(monCourses);
        finalCourses.addAll(tueCourses);
        finalCourses.addAll(wedCourses);
        finalCourses.addAll(thuCourses);
        finalCourses.addAll(friCourses);
        rowObject.setCourses(finalCourses);
        return rowObject;

    }

    private RowObject do9am() {
        String header = determineHeader(2);

        ArrayList<Course> monCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "1", "1") != null)
            monCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "1", "1"));
        else monCourses.add(course);

        ArrayList<Course> tueCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "2", "2") != null)
            tueCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "2", "2"));
        else tueCourses.add(course);

        ArrayList<Course> wedCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "2", "3") != null)
            wedCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "2", "3"));
        else wedCourses.add(course);

        ArrayList<Course> thuCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "2", "4") != null)
            thuCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "2", "4"));
        else thuCourses.add(course);

        ArrayList<Course> friCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "2", "5") != null)
            friCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "2", "5"));
        else friCourses.add(course);

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(monCourses);
        finalCourses.addAll(tueCourses);
        finalCourses.addAll(wedCourses);
        finalCourses.addAll(thuCourses);
        finalCourses.addAll(friCourses);
        rowObject.setCourses(finalCourses);
        return rowObject;

    }

    private RowObject do8am() {
        String header = determineHeader(1);

        ArrayList<Course> monCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "1", "1") != null)
            monCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "1", "1"));
        else monCourses.add(course);

        ArrayList<Course> tueCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "1", "2") != null)
            tueCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "1", "2"));
        else tueCourses.add(course);

        ArrayList<Course> wedCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "1", "3") != null)
            wedCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "1", "3"));
        else wedCourses.add(course);

        ArrayList<Course> thuCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "1", "4") != null)
            thuCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "1", "4"));
        else thuCourses.add(course);

        ArrayList<Course> friCourses = new ArrayList<>();
        if (Course.find(Course.class, "time = ? and day = ?", "1", "5") != null)
            friCourses.addAll(Course.find(Course.class, "time = ? and day = ?", "1", "5"));
        else friCourses.add(course);

        RowObject rowObject = new RowObject();
        rowObject.setRowHeader(header);

        ArrayList<Course> finalCourses = new ArrayList<>();
        finalCourses.addAll(monCourses);
        finalCourses.addAll(tueCourses);
        finalCourses.addAll(wedCourses);
        finalCourses.addAll(thuCourses);
        finalCourses.addAll(friCourses);
        rowObject.setCourses(finalCourses);
        return rowObject;

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
