package com.sbad.storyapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sbad on 25/04/16.
 */
public class DbHelper extends SQLiteOpenHelper{


        private static final String DB_NAME = "storydb";
        private static final int DB_VERSION = 6;

        public static final String TABLE_NAME = "Users";
        public static final String COL_NAME1 = "userid";
        public static final String COL_NAME2 = "is_following";
        public static final String COL_NAME3 = "about";
        public static final String COL_NAME4 = "username";

        private static final String STRING_CREATE = "CREATE TABLE "+TABLE_NAME+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COL_NAME1+" TEXT UNIQUE, "+COL_NAME2+" TEXT, "+COL_NAME3+" TEXT, "+COL_NAME4+" TEXT );";

        public DbHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d("table","table");
            db.execSQL(STRING_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
        }
        public void insertData(String id,String value,String about,String username){
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(COL_NAME1,id);
            contentValues.put(COL_NAME2,value);
            contentValues.put(COL_NAME3,about);
            contentValues.put(COL_NAME4,username);
            sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        }

    public void UpdateData(String id,String value){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_NAME2,value);
        sqLiteDatabase.update(TABLE_NAME,contentValues,COL_NAME1+"="+"'"+id+"'",null);
    }
        public User readData(String id){
            User user=new User();
            SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
            Cursor cursor=sqLiteDatabase.query(TABLE_NAME,null,COL_NAME1+"="+"'"+id+"'",null,null,null,null);
            if(cursor.moveToFirst()){
                    user.setIs_following(cursor.getString(cursor.getColumnIndex(COL_NAME2)));

                    user.setUserId(cursor.getString(cursor.getColumnIndex(COL_NAME1)));
                    user.setAbout(cursor.getString(cursor.getColumnIndex(COL_NAME3)));

                    user.setUsername(cursor.getString(cursor.getColumnIndex(COL_NAME4)));

            }
            Log.d("hhhhhhh", "readData: "+cursor.getString(cursor.getColumnIndex(COL_NAME3)));
            return user;
        }

    public String checkFollowing(String id){
        String columns[]={COL_NAME2};
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,columns,COL_NAME1+"="+"'"+id+"'",null,null,null,null);
        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_NAME2));
        }
        return "false";
    }

    public String getUsername(String id){
        String columns[]={COL_NAME4};
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,columns,COL_NAME1+"="+"'"+id+"'",null,null,null,null);
        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_NAME4));
        }
        return "false";
    }

    }

