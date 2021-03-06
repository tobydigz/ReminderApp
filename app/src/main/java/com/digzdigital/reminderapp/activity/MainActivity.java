package com.digzdigital.reminderapp.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.digzdigital.reminderapp.R;
import com.digzdigital.reminderapp.ReminderApplication;
import com.digzdigital.reminderapp.data.db.DbHelper;
import com.digzdigital.reminderapp.data.db.model.Course;
import com.digzdigital.reminderapp.fragment.addCourse.AddCourseFragment;
import com.digzdigital.reminderapp.fragment.manageCourse.ManageCoursesFragment;
import com.digzdigital.reminderapp.fragment.reminder.ReminderFragment;
import com.digzdigital.reminderapp.fragment.timetable.TimetableFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    public DbHelper dbHelper;
    private TextView emailText;
    private FragmentManager fragmentManager;
    private FirebaseAuth auth;
    private Fragment timetableFragment, coursesFragment;
    private FirebaseUser user;
    private FirebaseAuth.AuthStateListener listener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            user = firebaseAuth.getCurrentUser();
            if (user != null) {
                //Nigga signed in
                try {
                    emailText.setText(user.getEmail());
                } catch (Exception ignore) {

                }

                FirebaseMessaging.getInstance().subscribeToTopic("reminders");
                switchFragment(getReminderFragment(), null);
                if (getIntent().getExtras() != null) {
                }

            } else {
                //Nigga signed out
                switchActivity(LoginActivity.class);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((ReminderApplication) getApplication()).getAppComponent().inject(this);
        // dbHelper.createRealm(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        emailText = (TextView) findViewById(R.id.emailTextView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        fragmentManager = getFragmentManager();
        auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(listener);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_timetable) {
            switchFragment(getTimetableFragment(), null);
        } else if (id == R.id.nav_course) {
            switchFragment(getCourseFragment(), null);

        } else if (id == R.id.nav_reminder) {
            switchFragment(getReminderFragment(), null);

        } else if (id == R.id.nav_signout) {
            auth.signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Fragment getTimetableFragment() {
        return new TimetableFragment();
    }

    private Fragment getCourseFragment() {
        return ManageCoursesFragment.newInstance(user.getUid(), "");
    }

    private Fragment getAddCourseFragment() {
        return AddCourseFragment.newInstance(user.getUid(), "");
    }

    private Fragment getReminderFragment() {
        return new ReminderFragment();
    }

    public void switchFragment(Fragment fragment, @Nullable String tag) {
        fragmentManager.beginTransaction()
                .replace(R.id.content_main, fragment)
                .addToBackStack(tag)
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .commit();
    }

    private void switchFragmentNoStack(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.content_main, fragment)
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .commit();
    }

    private void switchActivity(Class classFile) {
        Intent intent = new Intent(this, classFile);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public DbHelper getDbHelper() {
        return dbHelper;
    }

    public void onCancelClicked() {
        switchFragmentNoStack(getCourseFragment());

    }

    public void onSaveClicked(Course course) {
        if (dbHelper.createCourse(course, user.getUid()))
            switchFragmentNoStack(getCourseFragment());
        else showError();

    }

    private void showError() {
        Toast.makeText(this, "Course not saved", Toast.LENGTH_LONG).show();
    }

    public void onUpdateClicked(Course course) {
        dbHelper.updateCourse(course, user.getUid());
        switchFragmentNoStack(getCourseFragment());

    }

    public void addNewCourse() {
        switchFragmentNoStack(getAddCourseFragment());
    }

    public void deleteCourse(Course course) {
        dbHelper.deleteCourse(course);
        switchFragmentNoStack(getCourseFragment());
    }
}
