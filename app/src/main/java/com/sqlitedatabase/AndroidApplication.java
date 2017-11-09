package com.sqlitedatabase;

import android.app.Application;

import com.sqlitedatabase.database.DbHelper;
import com.sqlitedatabase.database.DbManager;

/**
 * Created by asmaasamir1 on 11/9/17.
 */

public class AndroidApplication extends Application {
    private static AndroidApplication appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        DbManager.initializeInstance(new DbHelper());
    }

    public static AndroidApplication get() {

        return appInstance;
    }
}
