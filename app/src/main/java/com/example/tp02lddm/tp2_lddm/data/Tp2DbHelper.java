package com.example.tp02lddm.tp2_lddm.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by giovannariqueti on 29/10/17.
 */

public class Tp2DbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "linkematerias.db";

    private static final int DATABASE_VERSION = 1;

    public Tp2DbHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        final String SQL_CREATE_SUBJECT_TABLE = "CREATE TABLE " + Tp2Contract.SubjectEntry.TABLE_NAME + " (" +
                Tp2Contract.SubjectEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Tp2Contract.SubjectEntry.COLUMN_SUBJECT_NAME + " TEXT NOT NULL UNIQUE " +
                "); ";

        final String SQL_CREATE_CONTAINER_TABLE = "CREATE TABLE " + Tp2Contract.ContainerEntry.TABLE_NAME + " (" +
                Tp2Contract.ContainerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Tp2Contract.ContainerEntry.COLUMN_CONTAINER_NAME + " TEXT NOT NULL, " +
                Tp2Contract.ContainerEntry.COLUMN_FK_SUBJECT_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + Tp2Contract.ContainerEntry.COLUMN_FK_SUBJECT_ID + ") REFERENCES " +
                Tp2Contract.SubjectEntry.TABLE_NAME + "(" + Tp2Contract.SubjectEntry._ID + ") ON DELETE CASCADE" +
                "); ";

        final String SQL_CREATE_LINK_TABLE = "CREATE TABLE " + Tp2Contract.LinkEntry.TABLE_NAME + " (" +
                Tp2Contract.LinkEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Tp2Contract.LinkEntry.COLUMN_LINK_NAME + " TEXT NOT NULL, " +
                Tp2Contract.LinkEntry.COLUMN_LINK_ADDRESS + " TEXT NOT NULL, " +
                Tp2Contract.LinkEntry.COLUMN_FK_CONTAINER_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + Tp2Contract.LinkEntry.COLUMN_FK_CONTAINER_ID + ") REFERENCES " +
                Tp2Contract.ContainerEntry.TABLE_NAME + "(" + Tp2Contract.ContainerEntry._ID + ") ON DELETE CASCADE" +
                ");";
        sqLiteDatabase.execSQL(SQL_CREATE_SUBJECT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_CONTAINER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_LINK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Tp2Contract.SubjectEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Tp2Contract.ContainerEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Tp2Contract.LinkEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys = ON;");
    }

}
