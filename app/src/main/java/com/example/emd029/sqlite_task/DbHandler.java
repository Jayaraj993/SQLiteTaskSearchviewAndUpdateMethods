package com.example.emd029.sqlite_task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.emd029.sqlite_task.ListNewPage.ListEditPage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by EMD029 on 6/1/2015.
 */
public class DbHandler extends SQLiteOpenHelper {
    static String col;
    ArrayList<String> namesList = new ArrayList<String>();
    //table,database names
    static final String DATABASE_NAME = "StudentDB";
    static final String TABLE_NAME = "Sqllite Task";
    static final int DATABASE_VERSION = 1;
    //column names
    static final String COLUMN_ID = "id";
    static final String COLUMN_NAME = "COLUMN_NAME";
    static final String COLUMN_SUBJECT = "subject";
    static final String COLUMN_ASSIGNMENTTASK = "assignmenttask";
    Context context;
    ListEditPage page=new ListEditPage();
    StudentNames studentNames;
    ArrayList<StudentNames> studentNamesList = new ArrayList<StudentNames>();

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //creating database
        sqLiteDatabase.execSQL("CREATE TABLE TABLE_NAME ( COLUMN_ID INTEGER PRIMARY KEY, COLUMN_NAME TEXT, COLUMN_SUBJECT TEXT," +
                " COLUMN_ASSIGNMENTTASK TEXT, COLUMN_DESCRIPTION TEXT, COLUMN_DATE TEXT, COLUMN_TIME TEXT)");
        // Toast.makeText(context,"table created",Toast.LENGTH_LONG).show();
        Log.d("DB", "Students Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldversion, int newversion) {
        //upgrade database
        sqLiteDatabase.execSQL("Drop Table If Exists" + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addName(StudentNames names) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        if (db.isOpen()) {
            ContentValues insertvalues = new ContentValues();
            //insertvalues.put("COLUMN_ID", names.getId());
            insertvalues.put("COLUMN_NAME", names.getName());
            insertvalues.put("COLUMN_SUBJECT", names.getSubject());
            insertvalues.put("COLUMN_ASSIGNMENTTASK", names.getAssignmentTask());
            insertvalues.put("COLUMN_DATE", dateFormat.format(date).substring(0, 10));
            insertvalues.put("COLUMN_TIME", dateFormat.format(date).substring(11,16));
            db.insert("TABLE_NAME", null, insertvalues);
            db.close();
            //  Toast.makeText(context, insertvalues.toString(),Toast.LENGTH_LONG).show();
        }
    }
    public void update(StudentNames names){
        SQLiteDatabase db=getWritableDatabase();
//        Toast.makeText(context, names.getName()+names.getDate()+names.getTime()+names.getDescription()+names.getAssignmentTask()
//                , Toast.LENGTH_SHORT).show();
        ContentValues values=new ContentValues();
        values.put("COLUMN_DESCRIPTION", names.getDescription() );
        values.put("COLUMN_DATE",names.getDate() );
        values.put("COLUMN_TIME", names.getTime());
        values.put("COLUMN_ASSIGNMENTTASK", names.getAssignmentTask());
        String where= "COLUMN_NAME = ?";
        String[] wherwArgs={ names.getName() };
        db.update("TABLE_NAME", values, where, wherwArgs);
        Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<StudentNames> getall() {
        //crate a arraylist
//       ArrayList<String> namesList=new ArrayList<String >();
        //getting data of students who are completed the assignment
        String selectQuery = "SELECT COLUMN_NAME, COLUMN_DATE, COLUMN_TIME, COLUMN_DESCRIPTION FROM TABLE_NAME where COLUMN_ASSIGNMENTTASK='Completed'";
        //select all query
        SQLiteDatabase db = getWritableDatabase();
        // long count = DatabaseUtils.queryNumEntries(db,"");
        Cursor cursor = db.rawQuery(selectQuery, null);

        //add alldata in a arraylist
        if (cursor.moveToFirst()) {
            do {
                studentNames = new StudentNames();
                studentNames.setName(cursor.getString(cursor.getColumnIndex("COLUMN_NAME")));
                studentNames.setDate(cursor.getString(cursor.getColumnIndex("COLUMN_DATE")));
                studentNames.setTime(cursor.getString(cursor.getColumnIndex("COLUMN_TIME")));
                studentNames.setDescription(cursor.getString(cursor.getColumnIndex("COLUMN_DESCRIPTION")));
                //adding
                //Toast.makeText(context, cursor.getString(cursor.getColumnIndex("COLUMN_DESCRIPTION")), Toast.LENGTH_SHORT).show();
                studentNamesList.add(studentNames);
            } while (cursor.moveToNext());

        }

        // Toast.makeText(context,namesList.toString(),Toast.LENGTH_LONG).show();
        return studentNamesList;
    }


    public ArrayList<StudentNames> getalldatas() {
        //crate a arraylist
//       ArrayList<String> namesList=new ArrayList<String >();
        //getting data of students who are Notcompleted the assignment
        String selectQuery = "SELECT COLUMN_NAME, COLUMN_DATE, COLUMN_TIME, COLUMN_DESCRIPTION FROM TABLE_NAME where COLUMN_ASSIGNMENTTASK='NotCompleted'";
        //select all query
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        //add alldata in a arraylist
        if (cursor.moveToFirst()) {
            do {
                studentNames=new StudentNames();

                studentNames.setName(cursor.getString(cursor.getColumnIndex("COLUMN_NAME")));
                studentNames.setDate(cursor.getString(cursor.getColumnIndex("COLUMN_DATE")));
                studentNames.setTime(cursor.getString(cursor.getColumnIndex("COLUMN_TIME")));
                studentNames.setDescription(cursor.getString(cursor.getColumnIndex("COLUMN_DESCRIPTION")));
                studentNamesList.add(studentNames);
            } while (cursor.moveToNext());

        }
        // Toast.makeText(context, cursor.toString(), Toast.LENGTH_SHORT).show();
        // Toast.makeText(context,namesList.toString(),Toast.LENGTH_LONG).show();
        return studentNamesList;
    }

    public void deletealldata() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from " + "TABLE_NAME");
    }

    public ArrayList<StudentNames> searchByInputTxt(String inputText) {
        SQLiteDatabase db = getWritableDatabase();
        //String query ="SELECT COLUMN_NAME FROM TABLE_NAME where COLUMN_NAME='"+inputText+"'";

        //String query = "Select COLUMN_NAME from TABLE_NAME Where(COLUMN_NAME like " + "'%" + inputText + "%'" + ")";
        String query = "SELECT COLUMN_NAME, COLUMN_DATE, COLUMN_TIME FROM TABLE_NAME where(COLUMN_NAME like " + "'%" + inputText + "%'" + ")";
        Cursor cursor = db.rawQuery(query, null);
        //Toast.makeText(context, query, Toast.LENGTH_SHORT).show();
        if (cursor.moveToFirst()) {
            do {
                studentNames = new StudentNames();
                studentNames.setName(cursor.getString(cursor.getColumnIndex("COLUMN_NAME")));
                studentNames.setDate(cursor.getString(cursor.getColumnIndex("COLUMN_DATE")));
                studentNames.setTime(cursor.getString(cursor.getColumnIndex("COLUMN_TIME")));
                //adding
                studentNamesList.add(studentNames);
            } while (cursor.moveToNext());

        }

        // Toast.makeText(context,namesList.toString(),Toast.LENGTH_LONG).show();
        return studentNamesList;
    }

    public boolean checkDBExists()
    {
        String selectQuery = "SELECT * FROM TABLE_NAME";
        //select all query
        SQLiteDatabase db = getWritableDatabase();
        // long count = DatabaseUtils.queryNumEntries(db,"");
        Cursor cursor = db.rawQuery(selectQuery, null);

        //add alldata in a arraylist
        if (cursor.getCount() > 0 ) {
            return true;
        }
        return false;
    }

}



