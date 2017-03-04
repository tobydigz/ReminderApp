package com.digzdigital.reminderapp.data.db;

import com.digzdigital.reminderapp.data.db.model.Course;
import com.digzdigital.reminderapp.data.db.model.ReminderItem;
import com.digzdigital.reminderapp.data.db.model.RowObject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import io.realm.RealmResults;


public interface DbHelper {

    // ArrayList<ReminderItem> getAllLocalReminders();
    // void putLocalReminder(ReminderItem reminderItem);
    // ReminderItem getLocalReminderItem(long key);
    // void deleteLocalReminder(long key);
    void createCourse(Course course);
    ArrayList<Course> getAllCourses();
    boolean deleteCourse(long key);
    boolean updateCourse(Course course);
    void queryForReminders();
    ArrayList<ReminderItem> getOnlineReminders();
    ArrayList<RowObject> getRowObjects();
}
