package com.phoenix.EventReminder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    Button cancel;
    Button save;
    EditText remind;
    TextView time;
    TextView date;

    String Time="";
    String Date="";
    String Description="";

    DatabaseHelper myDB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);

        cancel = findViewById(R.id.close_btn);
        save = findViewById(R.id.save_btn);
        remind = findViewById(R.id.edit_text);
        time = findViewById(R.id.timepick);
        date = findViewById(R.id.datepick);


        cancel.setOnClickListener(this);
        save.setOnClickListener(this);


        //Set Time before Selection

        Calendar c = Calendar.getInstance();
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        String status = "AM";

        if(hourOfDay > 11)
        {
            status = "PM";
        }
        int hour_of_12_hour_format;
        if(hourOfDay > 11){
            hour_of_12_hour_format = hourOfDay - 12;
        }
        else {
            hour_of_12_hour_format = hourOfDay;
        }

        String hor = String.format("%02d", hour_of_12_hour_format);
        String min = String.format("%02d", minute);

        time.setText(hor+ ":" +min+ " "+status);


        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH,day);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        date.setText(currentDateString);




    }



    public void set_time(View v) {

        DialogFragment timepicker = new TimePickerFragment();
        timepicker.show(getSupportFragmentManager(), "time picker");



    }

    public void set_date(View v) {

        DialogFragment datepicker = new DatePickerFragment();
        datepicker.show(getSupportFragmentManager(), "date picker");

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String status = "AM";

        if(hourOfDay > 11)
        {
            status = "PM";
        }
        int hour_of_12_hour_format;
        if(hourOfDay > 11){
            hour_of_12_hour_format = hourOfDay - 12;
        }
        else {
            hour_of_12_hour_format = hourOfDay;
        }

        String hor = String.format("%02d", hour_of_12_hour_format);
        String min = String.format("%02d", minute);




        Time = (hor+":"+min+" "+status);
        time.setText(Time);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        Date = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        date.setText(Date);
    }



    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.close_btn:
                Intent c = new Intent(this, MainActivity.class);
                startActivity(c);
                break;

            case R.id.save_btn:
                Save();
                break;

        }
    }

    public void Save(){

                String itemEntered = remind.getText().toString();
                Description = itemEntered;

                if(Description.trim().length() != 0) {
                    //Check is Values are null
                    if(Time != null||Date != null||Description != null ) {
                        DatabaseHelper myDB=new DatabaseHelper(this);
                        Boolean Result = myDB.insertData(Time, Date, Description);
                        Log.d("MainActivity","Save:Data Sent");
                        if (Result == true) {

                            Toast.makeText(this, "Event Set", Toast.LENGTH_SHORT).show();
                            Intent s = new Intent(this, MainActivity.class);
                            startActivity(s);

                        } else {
                            Toast.makeText(this, "Error...", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(this, "Enter a Note", Toast.LENGTH_SHORT).show();
                }

    }

}
