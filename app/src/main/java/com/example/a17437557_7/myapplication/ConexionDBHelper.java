package com.example.a17437557_7.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionDBHelper extends SQLiteOpenHelper {

    String sql = "CREATE TABLE USUARIO (ID INTEGER PRIMARY KEY, NOMBRE TEXT, APELLIDO TEXT)";
    public ConexionDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE USUARIO");
        db.execSQL(sql);
    }
}
