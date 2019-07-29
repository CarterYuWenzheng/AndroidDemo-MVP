package com.carter.javaAndroid.di.module;

import com.carter.javaAndroid.Application.MyApplication;
import com.carter.javaAndroid.core.data.DataManager;
import com.carter.javaAndroid.core.db.DbHelper;
import com.carter.javaAndroid.core.db.DbHelperImpl;
import com.carter.javaAndroid.core.http.HttpImpl;
import com.carter.javaAndroid.core.http.IHttp;
import com.carter.javaAndroid.core.preference.IPreference;
import com.carter.javaAndroid.core.preference.PreferenceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final MyApplication application;

    public AppModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    MyApplication provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    IHttp provideHttpHelper(HttpImpl httpHelperImpl) {
        return httpHelperImpl;
    }

    @Provides
    @Singleton
    DbHelper providerHttpHelper(DbHelperImpl dbHelperImpl) {
        return dbHelperImpl;
    }

    @Provides
    @Singleton
    IPreference providePreferenceHelper(PreferenceImpl preference) {
        return preference;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(IHttp httpHelper, IPreference preferenceHelper, DbHelper dbHelper) {
        return new DataManager(httpHelper, preferenceHelper, dbHelper);
    }
}
