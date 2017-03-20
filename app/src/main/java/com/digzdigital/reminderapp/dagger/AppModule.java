package com.digzdigital.reminderapp.dagger;

import com.digzdigital.reminderapp.ReminderApplication;
import com.digzdigital.reminderapp.data.db.AppDbHelper;
import com.digzdigital.reminderapp.data.db.DbHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Digz on 16/03/2017.
 */
@Module
public class AppModule {

    public AppModule(){

    }

    @Provides @Singleton
    public DbHelper providesDbHelper(){
        return new AppDbHelper();
    }
}
