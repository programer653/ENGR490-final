package com.example.engr490_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class GpsNavigationPage extends AppCompatActivity {
    Button Backbutton ;

    private boolean isSpeaking = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_navigation_page);


        Backbutton = findViewById(R.id.Backbutton);

        Object gpsXMLv = new Object();
        Intent intent = new Intent(this, AppSettingsPage.class);
        //Intent.putExtra("gpsXMl", gpsXML);

        Backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenMainPage();
            }
        });

    }
    public void OpenMainPage()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}