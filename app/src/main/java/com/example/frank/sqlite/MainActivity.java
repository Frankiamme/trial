package com.example.frank.sqlite;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);
        /**
         * CRUD Operations
         */
//Inserting Contacts
        Log.d("insert: ", "Inserting..");
        db.addContact(new Contact("Ravi", "910000000000"));
        db.addContact(new Contact("srinivas", "91999999999"));
        db.addContact(new Contact("Tommy", "95222222222"));
        db.addContact(new Contact("Karthik", "953333333333"));

//Inserting departments
        Log.d("insert: ","Inserting....");
        db.addDepartment(new Department("IT", "Frank Ojwang"));
        db.addDepartment(new Department("Law", "Willis Ojwang"));
        db.addDepartment(new Department("Logistics", "Odhiammbo Ojwang"));
        db.addDepartment(new Department("Marketing", "Frank Oj"));
6

        //Reading all contacts
        Log.d("Reading: ", "Reading all contacts... ");
        List<Contact> contact = db.getAllContacts();

        for (Contact en : contact) {
            String log = "Id: " + en.getId() + " , Name " + en.getName() + " , Phone: " + en.getPhoneNumber();
            //Writing Contacts to log
            Log.d("Name: ", log);
        }

        //Reading all the departments
        Log.d("Reading: ", "Reading all the departments.... ");
        List<Department> department = db.getAllDepartments();

        for (Department en : department) {
            String log = "Id: " + en.get_deptId() + " , Name " + en.get_deptName() + " ,Head: " + en.get_deptHead();
            //writing departments to log
            Log.d("Name: ", log);
        }
    }
}

