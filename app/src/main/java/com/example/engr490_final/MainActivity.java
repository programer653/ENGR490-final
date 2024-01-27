package com.example.engr490_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button AppSettingsButton;
    Button VibrationButton;
    Button GpsNavigationButton;

    ConstraintLayout mainXML;
    SharedPreferences sp;

    int selectedColor = R.color.blue;
    int Colortext = R.color.black;
    int Colorbutton = R.color.blue2;
    float fontSize = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppSettingsButton = findViewById(R.id.AppSettingButton);
        VibrationButton = findViewById(R.id.Vibrationbutton);
        GpsNavigationButton  = findViewById(R.id.GpsNavigationButton);
        mainXML = findViewById(R.id.mainXML);

        sp = getApplicationContext().getSharedPreferences("ColorPref", Context.MODE_PRIVATE);
        selectedColor= sp.getInt("selectedColor", R.color.blue);
        Colortext = sp.getInt("ColorText", R.color.black);
        Colorbutton = sp.getInt("Colorbutton", R.color.blue2);
        fontSize = sp.getFloat("fontSize", 10);

        applySelectedColor(selectedColor, Colortext, Colorbutton, fontSize);
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
        Intent intent = new Intent(this, GpsNavigationPage2.class);
        startActivity(intent);
    }

    private void applySelectedColor(int selectedColor, int Colortext, int Colorbutton, float fontSize) {
        // Apply the selected color to the background
        mainXML.setBackgroundColor(getColor(selectedColor));

        AppSettingsButton.setBackgroundColor(getColor(Colorbutton));
        AppSettingsButton.setTextSize(fontSize);
        AppSettingsButton.setTextColor(getColor(Colortext));

        GpsNavigationButton.setBackgroundColor(getColor(Colorbutton));
        GpsNavigationButton.setTextSize(fontSize);
        GpsNavigationButton.setTextColor(getColor(Colortext));

        VibrationButton.setBackgroundColor(getColor(Colorbutton));
        VibrationButton.setTextSize(fontSize);
        VibrationButton.setTextColor(getColor(Colortext));




        // chnage the coor button and the font text and the size
    }












}