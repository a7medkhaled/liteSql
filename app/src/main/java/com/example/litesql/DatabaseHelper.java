package com.example.litesql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    //hold the name of the database
    public static  final  String DatabaseName_string = "Student.db";

    //hold the name of the Table
    public static  final  String Table1Name_string = "Student_table";


    //columns names
    public static  final  String Col1Name_string = "ID";
    public static  final  String Col2Name_string = "Name";
    public static  final  String Col3Name_string = "SurName";
    public static  final  String Col4Name_string = "Marks";




    public DatabaseHelper(@Nullable Context context) {

        super(context, DatabaseName_string, null, 1);

        //create an intsatnce from the database class
        SQLiteDatabase db =this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

       //create the data base
        sqLiteDatabase.execSQL("create table "+
                Table1Name_string +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT , SURNAME TEXT , MARKS INTEGER  )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table1Name_string);

    }

    public boolean InsertData(String name , String surname , String marks )
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues_Database_table1 = new ContentValues();
        contentValues_Database_table1.put(Col2Name_string , name);
        contentValues_Database_table1.put(Col3Name_string , surname);
        contentValues_Database_table1.put(Col4Name_string , marks);
        long result = db.insert(Table1Name_string , null , contentValues_Database_table1);

        if(result == -1)
            return  false;
        else
            return  true;



    }

    public boolean UpdateData(String id ,String name , String surname , String marks )
    {
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues contentValues_Database_table1 = new ContentValues();
        contentValues_Database_table1.put(Col1Name_string , id);
        contentValues_Database_table1.put(Col2Name_string , name);
        contentValues_Database_table1.put(Col3Name_string , surname);
        contentValues_Database_table1.put(Col4Name_string , marks);

        db.update(Table1Name_string , contentValues_Database_table1 , "ID = ?" , new String[]{new String(id)});
        return true;
        

    }



    public Cursor GetAllData_database_cursor()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table1Name_string , null);
        return  res;
    }
}
