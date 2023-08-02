package com.example.database_demo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

public class MydataBase extends SQLiteOpenHelper
{

    public MydataBase(@Nullable Context context) {
        super(context, "Company",null,1);
        Log.d("TTT","MyDataBase: DataBase Created..");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table Emp(ID integer primary key autoincrement,NAME text,EMAIL text)";
        db.execSQL(query);
        Log.d("TTT","MyDataBase: Table Created...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addData(String name, String email) {
        //String hoy to   '" "'
        //int    hoy to    ' '
        //double hoy to    ' '
        String query="insert into Emp(NAME,EMAIL) values('"+name+"','"+email+"')";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);
    }

    public Cursor showData()
    {
        String query="select * from Emp";
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }
    public void updateData(Integer id, String name, String email) {
        String query="update Emp set NAME='"+name+"',EMAIL='"+email+"' where ID="+id+"";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);
    }
    public void deleteData(Integer id) {
        String query="delete from Emp where ID="+id+"";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);
    }
}