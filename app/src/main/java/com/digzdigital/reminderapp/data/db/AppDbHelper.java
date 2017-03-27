package com.digzdigital.reminderapp.data.db;

import com.digzdigital.reminderapp.data.db.model.Course;
import com.digzdigital.reminderapp.data.db.model.ReminderItem;
import com.digzdigital.reminderapp.data.db.model.RowObject;
import com.digzdigital.reminderapp.eventbus.EventType;
import com.digzdigital.reminderapp.eventbus.FirebaseEvent;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AppDbHelper implements DbHelper {

    private ArrayList<Course> courses;
    private ArrayList<ReminderItem> reminders;
    private DatabaseReference databaseReference;
    private ArrayList<RowObject> rowObjects;

    public AppDbHelper() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void createCourse(Course course, String userId) {

        databaseReference.child(userId).child(course.getCourseCode()).setValue(course);
    }

    @Override
    public void queryForCourses(String userId) {
        databaseReference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                courses = null;
                courses = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Course course = new Course();
                    course.setCourseCode((String) snapshot.child("courseCode").getValue());
                    course.setCourseTitle((String) snapshot.child("courseTitle").getValue());
                    course.setVenue((String) snapshot.child("venue").getValue());
                    // course.setDay((Integer) snapshot.child("day").getValue());
                    course.setStartTime((Date) snapshot.child("startTime").getValue());
                    course.setDuration((Long) snapshot.child("duration").getValue());
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
    public boolean deleteCourse(String key, String userId) {
        databaseReference.child(userId).child(key).removeValue();
        return false;
    }

    @Override
    public boolean updateCourse(Course course, String userId) {
        databaseReference.child(userId).child(course.getCourseCode()).setValue(course);
        return false;
    }

    @Override
    public void queryForReminders() {
        databaseReference.child("reminders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reminders = null;
                reminders = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ReminderItem reminderItem = new ReminderItem();
                    reminderItem.setId(snapshot.getKey());
                    reminderItem.setTitle((String)snapshot.child("title").getValue());
                    Date date = dateConverter((String) snapshot.child("date").getValue());
                    reminderItem.setDate(date);
                    reminderItem.setMessage((String)snapshot.child("message").getValue());
                    reminderItem.setVenue((String)snapshot.child("venue").getValue());
                    reminderItem.setSender((String)snapshot.child("sender").getValue());
                    reminders.add(reminderItem);
                }
                postEvent(EventType.REMINDER);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private Date dateConverter(String dateString) {
        String dateString1 = dateString.replace("T", " ");
        String dateString2 = dateString1.replace("-", "/");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString2);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convertedDate;
    }


    private String cleanDate(String dateString) {
        String dateString1 = dateString.replace("T", " ");
        String dateString2 = dateString1.replace("-", "/");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString2);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String dayOfTheWeek = (String) android.text.format.DateFormat.format("EEEE", convertedDate);//Thursday
        String stringMonth = (String) android.text.format.DateFormat.format("MMM", convertedDate); //Jun
//        String intMonth = (String) android.text.format.DateFormat.format("MM", date); //06
        String year = (String) android.text.format.DateFormat.format("yyyy", convertedDate); //2013
        String day = (String) android.text.format.DateFormat.format("dd", convertedDate); //20
        return dayOfTheWeek + " " + day + " " + stringMonth + " " + year;
    }
    @Override
    public ArrayList<ReminderItem> getOnlineReminders() {
        return null;
    }


    @Override
    public void queryForRowObjects(String userId){
        if (courses==null)queryForCourses(userId);

    }
    private void createRowObjects(){
        rowObjects = new ArrayList<>();



        postEvent(EventType.TIMETABLE);

    }
    @Override
    public ArrayList<RowObject> getRowObjects() {


        return rowObjects;
    }


    private void postEvent(EventType eventType) {
        EventBus.getDefault().post(new FirebaseEvent(eventType));
    }
}
