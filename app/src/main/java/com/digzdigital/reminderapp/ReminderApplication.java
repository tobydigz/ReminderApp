package com.digzdigital.reminderapp;

import android.app.Application;

import com.digzdigital.reminderapp.dagger.AppComponent;
import com.digzdigital.reminderapp.dagger.AppModule;
import com.digzdigital.reminderapp.dagger.DaggerAppComponent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Digz on 16/03/2017.
 */

public class ReminderApplication extends Application {
    private static ReminderApplication instance = new ReminderApplication();
    private static AppComponent appComponent;

    public static ReminderApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        getAppComponent();
        FirebaseAuth.getInstance();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public AppComponent getAppComponent(){
        if (appComponent ==null){
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }
}
