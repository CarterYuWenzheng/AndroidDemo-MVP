package com.carter.javaAndroid.core.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.carter.javaAndroid.core.db.bean.User;
import com.carter.javaAndroid.core.db.dao.HistoryDao;
import com.carter.javaAndroid.core.db.dao.UserDao;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    private static final String DB_NAME = "JavaAndroid.db";
    private static volatile MyDatabase instance;

    static synchronized MyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    public static MyDatabase create(Context context) {
        return Room.databaseBuilder(
                context,
                MyDatabase.class,
                DB_NAME
        ).allowMainThreadQueries().build();
    }

    public abstract UserDao getUserDao();

    public abstract HistoryDao getHistoryDao();

}
