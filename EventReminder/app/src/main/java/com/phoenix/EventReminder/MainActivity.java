package com.phoenix.EventReminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private Button addbtn;
    private Button viewbtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addbtn = findViewById(R.id.addevent);
        viewbtn = findViewById(R.id.viewevent);

        addbtn.setOnClickListener(this);
        viewbtn.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {


        switch(v.getId()) {
            case R.id.addevent:
                //Goto Next Activity
                Intent i = new Intent(this,MainActivity2.class);
                startActivity(i);
                break;

            case R.id.viewevent:
                Intent d = new Intent(this, ViewEvent.class);
                startActivity(d);

                break;

        }
    }




}


