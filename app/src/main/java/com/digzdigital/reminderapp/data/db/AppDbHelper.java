package com.digzdigital.reminderapp.data.db;

import android.content.Context;

import com.digzdigital.reminderapp.data.db.model.Course;
import com.digzdigital.reminderapp.data.db.model.ReminderItem;
import com.digzdigital.reminderapp.data.db.model.RowObject;
import com.digzdigital.reminderapp.eventbus.EventType;
import com.digzdigital.reminderapp.eventbus.FirebaseEvent;
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

    public AppDbHelper(Context context) {
        databaseReference = FirebaseDatabase.getInstance().getReference();

    }


    @Override
    public boolean createCourse(Course course, String userId) {
        if ((Course.find(Course.class, "time = ? and day = ?", course.getTime().toString(), course.getDay().toString())).size() > 0)
            return false;
        course.save();
        return true;

    }

    @Override
    public ArrayList<Course> queryForCourses() {
        return new ArrayList<>(Course.findWithQuery(Course.class, "Select * from Course ORDER BY day"));
        // return new ArrayList<>(Course.listAll(Course.class));
    }

    @Override
    public ArrayList<Course> getAllCourses() {
        return courses;
    }

    @Override
    public boolean deleteCourse(Course course) {
        Course course1 = Course.findById(Course.class, course.getId());
        course1.delete();
        return false;
    }

    @Override
    public boolean updateCourse(Course course, String userId) {
        Course course1 = Course.findById(Course.class, course.getId());
        course1.setCourseTitle(course.getCourseTitle());
        course1.setVenue(course.getVenue());
        course1.setCourseCode(course.getCourseCode());
        course1.setDuration(course.getDuration());
        course1.setDay(course.getDay());
        course1.setTime(course.getTime());
        course1.save();
        return false;
    }

    @Override
    public void queryForReminders() {
        databaseReference.child("reminders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reminders = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ReminderItem reminderItem = new ReminderItem();
                    reminderItem.setId(snapshot.getKey());
                    reminderItem.setTitle((String) snapshot.child("title").getValue());
                    Date date = dateConverter((String) snapshot.child("date").getValue());
                    reminderItem.setDateString(cleanDate((String) snapshot.child("date").getValue()));
                    reminderItem.setTimeString(cleanTime((String) snapshot.child("date").getValue()));
                    reminderItem.setDate(date);
                    reminderItem.setMessage((String) snapshot.child("message").getValue());
                    reminderItem.setVenue((String) snapshot.child("venue").getValue());
                    reminderItem.setSender((String) snapshot.child("sender").getValue());
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
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

    private String cleanTime(String dateString) {
        String dateString1 = dateString.replace("T", " ");
        String dateString2 = dateString1.replace("-", "/");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString2);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String hour = (String) android.text.format.DateFormat.format("hh", convertedDate);//Thursday
        String minute = (String) android.text.format.DateFormat.format("mm", convertedDate); //Jun
        return hour + ":" + minute;
    }

    @Override
    public ArrayList<ReminderItem> getOnlineReminders() {
        return reminders;
    }


    @Override
    public void queryForRowObjects() {
        queryForCourses();

    }

    private ArrayList<RowObject> createRowObjects() {
        ArrayList<Course> results = queryForCourses();

        RowObjectsCreator creator = new RowObjectsCreator(results);

        return creator.getRows();
    }


    @Override
    public ArrayList<RowObject> getRowObjects() {
        return createRowObjects();
    }


    private void postEvent(EventType eventType) {
        EventBus.getDefault().post(new FirebaseEvent(eventType));
    }
}
