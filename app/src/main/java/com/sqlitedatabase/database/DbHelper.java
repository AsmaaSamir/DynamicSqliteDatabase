package com.sqlitedatabase.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sqlitedatabase.AndroidApplication;
import com.sqlitedatabase.database.user.IUserSchema;
import com.sqlitedatabase.database.user.UserDao;

/**
 * Created by asmaasamir1 on 11/9/17.
 */

public class DbHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 1;
    // Database Name
    public static final String DATABASE_NAME = "database.db";
    private static final String TAG = DbHelper.class.getSimpleName().toString();

    public DbHelper() {
        super(AndroidApplication.get().getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        db.execSQL(IUserSchema.USER_TABLE_CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        // Drop table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + IUserSchema.USER_TABLE);
        onCreate(db);
    }


    public void deleteDatabase(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS " + IUserSchema.USER_TABLE);
        onCreate(db);

    }

}
