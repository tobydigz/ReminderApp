package com.digzdigital.reminderapp.data.db;


import com.digzdigital.reminderapp.data.db.model.Course;
import com.digzdigital.reminderapp.data.db.model.ReminderItem;
import com.digzdigital.reminderapp.data.db.model.RowObject;

import java.util.ArrayList;

public interface DbHelper {

    void createCourse(Course course, String userId);
    void queryForCourses(String userId);
    ArrayList<Course> getAllCourses();
    boolean deleteCourse(String key, String userId);
    boolean updateCourse(Course course, String userId);
    void queryForReminders();
    ArrayList<ReminderItem> getOnlineReminders();
    void queryForRowObjects(String userId);
    ArrayList<RowObject> getRowObjects();
}
