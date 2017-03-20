package com.digzdigital.reminderapp.dagger;

import com.digzdigital.reminderapp.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Digz on 16/03/2017.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
}
