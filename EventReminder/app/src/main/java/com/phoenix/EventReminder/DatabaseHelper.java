package com.phoenix.EventReminder;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Events.db";
    public static final String TABLE_NAME = "Event_Table";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "TIME";
    public static final String COL_3 = "DATE";
    public static final String COL_4 = "DESCRIPTION";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +"(" +COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TIME TEXT, DATE TEXT, DESCRIPTION TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }

        public boolean insertData(String Time, String Date, String Description){


            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2,Time);
            contentValues.put(COL_3,Date);
            contentValues.put(COL_4,Description);


            Log.d("DatabaseHelper","insertData:Data Received");
            long result = db.insert(TABLE_NAME,null, contentValues);
            db.close();

            if(result==-1) {
                return false;
            }
            else{
                return true;
            }

        }

        public boolean updateData(String id, String Time, String Date, String Description, Boolean Repeat){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2,Time);
            contentValues.put(COL_3,Date);
            contentValues.put(COL_4,Description);
            long result = db.update(TABLE_NAME,contentValues,"ID=?",new String[]{id});
            db.close();

            if(result>0) {
                return false;
            }
            else{
                return true;
            }

        }

        public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " +TABLE_NAME,null);
        return res;
        }

}
