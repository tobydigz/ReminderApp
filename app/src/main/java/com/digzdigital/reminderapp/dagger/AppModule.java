package com.digzdigital.reminderapp.dagger;

import android.content.Context;

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

    private final ReminderApplication app;
    public AppModule(ReminderApplication app){
this.app = app;
    }

    @Provides
    @Singleton
    public Context providesContext(){
        return app;
    }

    @Provides @Singleton
    public DbHelper providesDbHelper(Context context){
        return new AppDbHelper(context);
    }
}
