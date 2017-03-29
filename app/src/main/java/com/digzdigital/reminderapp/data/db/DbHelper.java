package com.digzdigital.reminderapp.data.db;


import android.content.Context;

import com.digzdigital.reminderapp.data.db.model.Course;
import com.digzdigital.reminderapp.data.db.model.ReminderItem;
import com.digzdigital.reminderapp.data.db.model.RowObject;

import java.util.ArrayList;

import io.realm.RealmResults;

public interface DbHelper {

    void createRealm(Context context);
    void createCourse(Course course, String userId);
    RealmResults<Course> queryForCourses();
    ArrayList<Course> getAllCourses();
    boolean deleteCourse(Course course);
    boolean updateCourse(Course course, String userId);
    void queryForReminders();
    ArrayList<ReminderItem> getOnlineReminders();
    void queryForRowObjects();
    ArrayList<RowObject> getRowObjects();
}
