package com.example.engr490_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button AppSettingsButton = findViewById(R.id.AppSettingButton);
        Button VibrationButton = findViewById(R.id.Vibrationbutton);
        Button GpsNavigationButton = findViewById(R.id.GpsNavigationButton);

        //Setup button to open Settings page
        AppSettingsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openAppSettingsPage();
            }
        });

        //Setup button to open Vibration Settings
        VibrationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                OpenVibrationPage();
            }
        });

        //Setup to open GPS Navigation
        GpsNavigationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                OpenGpsPage();
            }
        });


    }

    public void openAppSettingsPage()
    {
        Intent intent = new Intent(this, AppSettingsPage.class);
        startActivity(intent);
    }

    public void OpenVibrationPage()
    {
        Intent intent = new Intent(this, VibrationSettingsPage.class);
        startActivity(intent);
    }

    public void OpenGpsPage()
    {
        Intent intent = new Intent(this, GpsNavigationPage.class);
        startActivity(intent);
    }












}