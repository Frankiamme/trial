package com.example.frank.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Frank on 10/19/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    //All static variables
    //Database version

    private static final int DATABASE_VERSION = 1;

    //Database name
    private static final String DATABASE_NAME = "contactsManager";

    //contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    //Departments table
    private static final String TABLE_DEPARTMENTS = "departments";

    //contacts table column names
    private static final String KEY_ID ="id";
    private static final String KEY_NAME ="name";
    private static final String KEY_PH_NO = "phone_number";

    //departments table column names
    private static final String DEP_ID = "id";
    private static final String DEP_NAME = "name";
    private static final String DEP_HEAD = "head";


    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    //creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS
                + "(" + KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_NAME + " TEXT, " + KEY_PH_NO + " TEXT)";
        String CREATE_DEPARTMENTS_TABLE = "CREATE TABLE " + TABLE_DEPARTMENTS
                + "(" + DEP_ID + " INTEGER PRIMARY KEY, " +
                DEP_NAME + " TEXT , " + DEP_HEAD + " TEXT )";
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_DEPARTMENTS_TABLE);
    }


    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENTS);

        //creating tables again
        onCreate(db);
    }

    //Adding new contact
    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());//contact name
        values.put(KEY_PH_NO, contact.getPhoneNumber());//Contact phone number

        //inserting a row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); //Closing Database Connection
    }
    //Adding new Department
    public void addDepartment(Department department){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DEP_NAME, department.get_deptName());//department name
        values.put(DEP_HEAD, department.get_deptHead());//department heads

        //inserting a new row
        db.insert(TABLE_DEPARTMENTS, null, values);
        db.close();//Closing the database connection
    }

    //Getting single contact
    public Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{ KEY_ID, KEY_NAME,
                        KEY_PH_NO}, KEY_ID + "=?", new String[] { String.valueOf(id)}, null, null, null
                , null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        //return contact
        return contact;

    }

    //Getting a single department
    public Department getDepartment(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_DEPARTMENTS, new String[]{ DEP_ID, DEP_NAME, DEP_HEAD
        }, DEP_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();

        Department department = new Department(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        //return department
        return department;
    }


    //getting all contacts
    public List<Contact> getAllContacts(){
        List<Contact> contactsList = new ArrayList<Contact>();
        //Select all query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                //Adding contact to list
                contactsList.add(contact);
            } while (cursor.moveToNext());
        }
        //return contact list
        return contactsList;
    }

    //getting all the departments
    public List<Department> getAllDepartments(){
        List<Department> departmentsList = new ArrayList<Department>();
        //select all query
        String selectQuery = "SELECT * FROM " + TABLE_DEPARTMENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and addding to a list
        if (cursor.moveToFirst()){
            do {
                Department department = new Department();
                department.set_deptId(Integer.parseInt(cursor.getString(0)));
                department.set_deptName(cursor.getString(1));
                department.set_deptHead(cursor.getString(2));

                //Adding a department list
                departmentsList.add(department);
            } while(cursor.moveToNext());
        }
        //return a department list
        return  departmentsList;
    }

    //getting contacts count
    public int getContactsCount(){
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();
    }

    //getting departments count
    public int getDepartmentsCount(){
        String countQuery = " SELECT * FROM " + TABLE_DEPARTMENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return the count
        return cursor.getCount();
    }

    //Updating single contact
    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        //updating row
        return  db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[]{ String.valueOf(contact.getId())});
    }

    //Updating a single department
    public int updateDepartment(Department department){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DEP_NAME, department.get_deptName());
        values.put(DEP_HEAD, department.get_deptHead());

        //updating the row
        return  db.update(TABLE_DEPARTMENTS, values, DEP_ID + " = ?",
                new String[]{ String.valueOf(department.get_deptId())});
    }

    //Deleting single count
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?", new String[]{ String.valueOf(contact.getId()) });
        db.close();
    }
    //Deleting a single department
    public void deleteDepartment(Department department){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEPARTMENTS, DEP_ID + " = ?", new String[]{ String.valueOf(department.get_deptId())});
    }
}
