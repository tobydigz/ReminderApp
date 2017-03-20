package com.digzdigital.reminderapp.data.db;

import com.digzdigital.reminderapp.data.db.model.Course;
import com.digzdigital.reminderapp.data.db.model.ReminderItem;
import com.digzdigital.reminderapp.data.db.model.RowObject;
import com.digzdigital.reminderapp.data.db.model.User;
import com.digzdigital.reminderapp.eventbus.EventType;
import com.digzdigital.reminderapp.eventbus.FirebaseEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;


public class AppDbHelper implements DbHelper {

    private ArrayList<Course> courses;
    private User userInfo;
    private ArrayList<User> users;
    private ArrayList<ReminderItem> reminders;
    private DatabaseReference databaseReference;

    public AppDbHelper() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void createCourse(Course course) {

        databaseReference.child(course.getId()).setValue(course);
    }

    @Override
    public void queryForCourses() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                courses = null;
                courses = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Course course = snapshot.getValue(Course.class);
                    courses.add(course);
                }
                postEvent(EventType.COURSES);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public ArrayList<Course> getAllCourses() {
        return courses;
    }

    @Override
    public boolean deleteCourse(String key) {
        databaseReference.child(key).removeValue();
        return false;
    }

    @Override
    public boolean updateCourse(Course course) {
        databaseReference.setValue(course);
        return false;
    }

    @Override
    public void queryForReminders() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reminders = null;
                reminders = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ReminderItem reminderItem = snapshot.getValue(ReminderItem.class);
                    reminders.add(reminderItem);
                }
                postEvent(EventType.REMINDER);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public ArrayList<ReminderItem> getOnlineReminders() {
        return null;
    }


    @Override
    public ArrayList<RowObject> getRowObjects() {
        return null;
    }

    @Override
    public void updateUser(User user) {
        databaseReference.setValue(user);
    }

    @Override
    public void queryForUserInfo(String id) {
        databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userInfo = dataSnapshot.getValue(User.class);
                postEvent(EventType.USER);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public User getUserInfo() {
        return userInfo;
    }

    @Override
    public void queryForUsers() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users = null;
                users = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    users.add(user);
                }
                postEvent(EventType.USERS);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public ArrayList<User> getUsers() {
        return users;
    }

    private void postEvent(EventType eventType) {
        EventBus.getDefault().post(new FirebaseEvent(eventType));
    }
}
