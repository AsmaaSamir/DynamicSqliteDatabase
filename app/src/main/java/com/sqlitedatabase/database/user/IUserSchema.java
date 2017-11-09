package com.sqlitedatabase.database.user;

/**
 * Created by asmaasamir1 on 11/9/17.
 */

public interface IUserSchema {
    String USER_TABLE = "users";
    String COLUMN_ID = "_id";
    String COLUMN_USER_NAME = "user_name";
    String COLUMN_EMAIL = "email";
    String COLUMN_DATE = "created_date";
    String USER_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
            + USER_TABLE
            + " ("
            + COLUMN_ID
            + " INTEGER PRIMARY KEY, "
            + COLUMN_USER_NAME
            + " TEXT NOT NULL, "
            + COLUMN_EMAIL
            + " TEXT,"
            + COLUMN_DATE
            + "BIGINT "
            + ")";

    String[] USER_COLUMNS = new String[]{COLUMN_ID,
            COLUMN_USER_NAME, COLUMN_EMAIL, COLUMN_DATE};
}