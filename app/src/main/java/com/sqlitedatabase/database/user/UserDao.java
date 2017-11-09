package com.sqlitedatabase.database.user;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sqlitedatabase.database.DbContentProvider;
import com.sqlitedatabase.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by asmaasamir1 on 11/9/17.
 */

public class UserDao extends DbContentProvider
        implements IUserSchema, IUserDao {

    private Cursor cursor;
    private ContentValues initialValues;

    public UserDao(SQLiteDatabase db) {
        super(db);
    }

    public User fetchUserByID(int id) {
        final String selectionArgs[] = {String.valueOf(id)};
        final String selection = COLUMN_ID + " = ?";
        User user = new User();
        cursor = super.query(USER_TABLE, USER_COLUMNS, selection,
                selectionArgs, COLUMN_ID);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                user = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return user;
    }

    public List<User> fetchAllUsers() {
        List<User> userList = new ArrayList<User>();
        cursor = super.query(USER_TABLE, USER_COLUMNS, null,
                null, COLUMN_ID);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                User user = cursorToEntity(cursor);
                userList.add(user);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return userList;
    }

    public boolean addUser(User user) {
        // set values
        setContentValue(user);
        try {
            return super.insert(USER_TABLE, getContentValue()) > 0;
        } catch (SQLiteConstraintException ex) {
            Log.w("Database", ex.getMessage());
            return false;
        }
    }

    protected User cursorToEntity(Cursor cursor) {

        User user = new User();

        int idIndex;
        int userNameIndex;
        int emailIndex;
        int dateIndex;

        if (cursor != null) {
            if (cursor.getColumnIndex(COLUMN_ID) != -1) {
                idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
                user.id = cursor.getInt(idIndex);
            }
            if (cursor.getColumnIndex(COLUMN_USER_NAME) != -1) {
                userNameIndex = cursor.getColumnIndexOrThrow(
                        COLUMN_USER_NAME);
                user.username = cursor.getString(userNameIndex);
            }
            if (cursor.getColumnIndex(COLUMN_EMAIL) != -1) {
                emailIndex = cursor.getColumnIndexOrThrow(
                        COLUMN_EMAIL);
                user.email = cursor.getString(emailIndex);
            }
            if (cursor.getColumnIndex(COLUMN_DATE) != -1) {
                dateIndex = cursor.getColumnIndexOrThrow(COLUMN_DATE);
                user.createdDate = new Date(cursor.getLong(dateIndex));
            }

        }
        return user;
    }

    private void setContentValue(User user) {
        initialValues = new ContentValues();
        initialValues.put(COLUMN_ID, user.id);
        initialValues.put(COLUMN_USER_NAME, user.username);
        initialValues.put(COLUMN_EMAIL, user.email);
        initialValues.put(COLUMN_DATE, user.createdDate.getTime());
    }

    private ContentValues getContentValue() {
        return initialValues;
    }

}