package com.example.engr490_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;

public class AppSettingsPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings_page);


        //constraintLayout = findViewById(R.id.constraint_layout);
        Button Backbutton = findViewById(R.id.Backbutton);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        Switch switch1 = findViewById(R.id.switch1);
        Switch switch2 = findViewById(R.id.switch2);
        Switch switch3 = findViewById(R.id.switch3);
        EditText FonteditText = findViewById(R.id.FontEditText);
        //final ConstraintLayout relativeLayout;
        ConstraintLayout Settings = findViewById(R.id.settings);


        Boolean switchstate = switch1.isChecked();
        switch1.setChecked(true);

        Boolean switchstate2 = switch2.isChecked();
        switch2.setChecked(false);

        Boolean switchstate3 = switch3.isChecked();
        switch3.setChecked(false);


        Backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenMainPage();
            }
        });


        ConstraintLayout main = (ConstraintLayout) findViewById(R.id.settings);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switch1.isChecked())
                {
                    main.setBackgroundResource(R.color.blue);


                } else if (switch2.isChecked())
                {
                    //setActivityBackgroundColor(R.color.black);
                    main.setBackgroundColor(Color.BLACK);

                } else if (switch3.isChecked()) {

                    main.setBackgroundResource(R.color.orange);

                }



            }
        });


    }

    public void OpenMainPage()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void setActivityBackgroundColor(int color)
    {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
}