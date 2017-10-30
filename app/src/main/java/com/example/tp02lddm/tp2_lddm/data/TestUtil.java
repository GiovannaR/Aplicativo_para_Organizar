package com.example.tp02lddm.tp2_lddm.data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;



public class TestUtil {

    public static void insertFakeData(SQLiteDatabase db){
        if(db == null){
            return;
        }
        //create a list of fake guests
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(Tp2Contract.SubjectEntry.COLUMN_SUBJECT_NAME, "Banco de Dados");
        //cv.put(Tp2Contract.SubjectEntry.COLUMN_PARTY_SIZE, 12);
        list.add(cv);

        cv = new ContentValues();
        cv.put(Tp2Contract.SubjectEntry.COLUMN_SUBJECT_NAME, "Cálculo 1");
        //cv.put(Tp2Contract.SubjectEntry.COLUMN_PARTY_SIZE, 12);
        list.add(cv);

        cv = new ContentValues();
        cv.put(Tp2Contract.SubjectEntry.COLUMN_SUBJECT_NAME, "Cálculo 2");
        //cv.put(Tp2Contract.SubjectEntry.COLUMN_PARTY_SIZE, 12);
        list.add(cv);

        cv = new ContentValues();
        cv.put(Tp2Contract.SubjectEntry.COLUMN_SUBJECT_NAME, "MD");
        //cv.put(Tp2Contract.SubjectEntry.COLUMN_PARTY_SIZE, 12);
        list.add(cv);



        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete (Tp2Contract.SubjectEntry.TABLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(Tp2Contract.SubjectEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }

    }
}