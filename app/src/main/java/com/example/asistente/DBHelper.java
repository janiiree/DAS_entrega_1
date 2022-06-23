package com.example.asistente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE Users (" +
                        "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        "'email' VARCHAR(255), " +
                        "'name' VARCHAR(255), " +
                        "'password' VARCHAR(255))");

        sqLiteDatabase.execSQL(
                "CREATE TABLE Tasks (" +
                        "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        "'email' VARCHAR(255), " +
                        "'taskName' VARCHAR(255), " +
                        "'taskDescription' VARCHAR(255)," +
                        "'priority' INTEGER," +
                        "'finished' INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    // Tabla de Users
    public void insertUser(String email, String name, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("email", email);
        newValues.put("name", name);
        newValues.put("password", password);
        db.insert("Users", null, newValues);
        db.close();
    }

    public void updateUser(String email, String name, String password, String[] arguments) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues newValues = new ContentValues();
        if (email != null) {
            newValues.put("email", email);
        }
        if (name != null) {
            newValues.put("name", name);
        }
        if (password != null) {
            newValues.put("password", password);
        }
        db.update("Users", newValues, "email=?", arguments);
        db.close();
    }

    public void deleteUser(String[] arguments) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Users", "email=?", arguments);
        db.close();
    }

    public String selectLogin(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String[] query = new String[]{"name"};
        String[] arguments = new String[]{email, password};
        Cursor cu = db.query("Users", query, "email=? AND password=?", arguments, null, null, null);

        String qName = "";
        while (cu.moveToNext()) {
            qName = cu.getString(0);
        }
        cu.close();
        db.close();

        return qName;
    }

    public boolean checkUserExist(String email) {
        SQLiteDatabase db = getReadableDatabase();
        String[] query = new String[]{"email"};
        Cursor cu = db.query("Users", query, null, null, null, null, null);

        boolean result = false;
        while (cu.moveToNext()) {
            String e = cu.getString(0);
            if (e.equals(email)) {
                result = true;
            }
        }
        cu.close();
        db.close();

        return result;
    }

    // Tabla de tareas
    public long inserTask(String email, String taskName, String taskDescription, int priority, int finished) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("email", email);
        newValues.put("taskName", taskName);
        newValues.put("taskDescription", taskDescription);
        newValues.put("priority", priority);
        newValues.put("finished", finished);
        long id = db.insert("Tasks", null, newValues);
        db.close();
        return id;
    }

    public void updateTask(String email, String taskName, String taskDescription, int priority, int finished, String[] arguments) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues newValues = new ContentValues();
        if (email != null) {
            newValues.put("email", email);
        }
        if (taskName != null) {
            newValues.put("taskName", taskName);
        }
        if (taskDescription != null) {
            newValues.put("taskDescription", taskDescription);
        }
        if (priority == 0) {
            newValues.put("priority", priority);
        }
        if (finished == 0) {
            newValues.put("finished", finished);
        }
        db.update("Tasks", newValues, "id=?", arguments);
        db.close();
    }

    public void deleteTask(String[] arguments) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Tasks", "id=?", arguments);
        db.close();
    }

    public ArrayList<Task> selectTasks(String email) {
        SQLiteDatabase db = getReadableDatabase();
        String[] query = new String[]{"id, taskName, taskDescription, priority, finished"};
        String[] arguments = new String[]{email};
        Cursor cu = db.query("Tasks", query, "email=?", arguments, null, null, null);

        ArrayList<Task> result = new ArrayList<Task>();

        while (cu.moveToNext()) {
            Task task = new Task();

            task.setId(cu.getInt(0));
            task.setTaskName(cu.getString(1));
            task.setTaskDescription(cu.getString(2));
            task.setPriority(cu.getInt(3));
            task.setFinished(cu.getInt(4));

            result.add(task);
        }
        cu.close();
        db.close();

        return result;
    }
}