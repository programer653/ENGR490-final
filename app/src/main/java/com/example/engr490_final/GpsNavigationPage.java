package com.example.engr490_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;

public class GpsNavigationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_navigation_page);

        ConstraintLayout gpsXML = (ConstraintLayout) findViewById(R.id.gpsXML);

        Object gpsXMLv = new Object();
        Intent intent = new Intent(this, AppSettingsPage.class);
        //Intent.putExtra("gpsXMl", gpsXML);

    }
}