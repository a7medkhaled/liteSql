package com.example.litesql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText name_editText_id, surname_editText_id, marks_editText_id , id_editText_id;
    Button add_button_id, getAll_button_id , update_button_id;


    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create the instance of the database helper class
        mydb = new DatabaseHelper(this);
        name_editText_id = (EditText) findViewById(R.id.in1);
        surname_editText_id = (EditText) findViewById(R.id.in2);
        marks_editText_id = (EditText) findViewById(R.id.in3);
        id_editText_id = (EditText) findViewById(R.id.in4);
        add_button_id = (Button) findViewById(R.id.b1);
        getAll_button_id = (Button) findViewById(R.id.b2);
        update_button_id = (Button) findViewById(R.id.b3);
        mainActivity_AddData_void();
        viewAll();
        updateData_void();



    }

    public void mainActivity_AddData_void() {
        add_button_id.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 boolean isInserted = mydb.InsertData(name_editText_id.getText().toString(), surname_editText_id.getText().toString(), marks_editText_id.getText().toString());

                                                 if (isInserted == true) {
                                                     Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

                                                 } else {
                                                     Toast.makeText(MainActivity.this, "Data NOT Inserted", Toast.LENGTH_LONG).show();

                                                 }
                                             }

                                         }


        );
    }

    public void updateData_void() {
        update_button_id.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {

                                                 boolean isupdated = mydb.UpdateData(id_editText_id.getText().toString() , name_editText_id.getText().toString(), surname_editText_id.getText().toString(), marks_editText_id.getText().toString());
                                             if(isupdated == true )
                                             {
                                                 Toast.makeText(MainActivity.this, "Data updated", Toast.LENGTH_LONG).show();
                                             }
                                             else
                                             {
                                                 Toast.makeText(MainActivity.this, "Data NOT updated", Toast.LENGTH_LONG).show();
                                             }
                                             }

                                         }


        );
    }

    public void viewAll()
    {
      getAll_button_id.setOnClickListener(new View.OnClickListener()


    {
        @Override
        public void onClick (View view){


        Cursor res = mydb.GetAllData_database_cursor();


        if (res.getCount() == 0) {

            showMessage("Error" , "no data yet");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("id :" + res.getString(0) + "\n");
            buffer.append("name :" + res.getString(1) + "\n");
            buffer.append("Surname :" + res.getString(2) + "\n");
            buffer.append("Marks :" + res.getString(3) + "\n\n");


        }
        showMessage("Data" , buffer.toString());

    }
    }
    );
    }


    public void showMessage(String title , String message )

    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();



    }





}




