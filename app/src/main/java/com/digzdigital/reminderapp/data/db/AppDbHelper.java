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

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class AppDbHelper implements DbHelper {

    private ArrayList<Course> courses;
    private ArrayList<ReminderItem> reminders;
    private DatabaseReference databaseReference;
    private ArrayList<RowObject> rowObjects;
    private Realm realm;

    public AppDbHelper(Context context) {
        databaseReference = FirebaseDatabase.getInstance().getReference();

    }


    @Override
    public void createRealm(Context context){
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }
    @Override
    public void createCourse(Course course, String userId) {

        realm.beginTransaction();
        realm.copyToRealm(course);
        realm.commitTransaction();

    }

    @Override
    public RealmResults<Course> queryForCourses() {
        RealmQuery<Course> query = realm.where(Course.class);
        return query.findAll();
    }

    @Override
    public ArrayList<Course> getAllCourses() {
        return courses;
    }

    @Override
    public boolean deleteCourse(Course course) {
        realm.beginTransaction();
        Course realmCourse = realm.where(Course.class).equalTo("id", course.getId()).findAll().first();
        realmCourse.deleteFromRealm();
        realm.commitTransaction();
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
                    reminderItem.setTitle((String) snapshot.child("title").getValue());
                    Date date = dateConverter((String) snapshot.child("date").getValue());
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
    public void queryForRowObjects() {
        queryForCourses();

    }

    private ArrayList<RowObject> createRowObjects() {
        RealmQuery<Course> query = realm.where(Course.class);
        RealmResults<Course> results = query.findAll();

        RowObjectsCreator creator = new RowObjectsCreator(results);
        rowObjects = creator.getRows();

        return rowObjects;
    }


    @Override
    public ArrayList<RowObject> getRowObjects() {


        return createRowObjects();
    }


    private void postEvent(EventType eventType) {
        EventBus.getDefault().post(new FirebaseEvent(eventType));
    }
}
