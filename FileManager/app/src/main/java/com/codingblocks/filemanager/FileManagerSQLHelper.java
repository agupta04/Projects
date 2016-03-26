package com.codingblocks.filemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FileManagerSQLHelper extends SQLiteOpenHelper{
    final static String DATABASE_NAME="file_manager";
    final static String FAV_TABLE_NAME="favourites";
    final static String FAV_TABLE_FILE_PATH="file_path";
    final static String FAV_TABLE_DATE_ADDED="date_added";
    final static String _ID="_id";

    public FileManagerSQLHelper(Context context,int version)
    {
     super(context,DATABASE_NAME,null,version);
    }
    //SQLiteOpenHelper is an abstract class, so u hv to define onCreate n onUpgrade both
    public void onCreate(SQLiteDatabase db)
    {
        String query="CREATE TABLE "+FAV_TABLE_NAME+" ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                FAV_TABLE_FILE_PATH+" TEXT, "+FAV_TABLE_DATE_ADDED+" TEXT);";
        db.execSQL(query);

    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {

    }


}
