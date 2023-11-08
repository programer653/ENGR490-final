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
    }

    Button AppSettingsButton = findViewById(R.id.AppSettingButton);
    Button CaneBatteryButton = findViewById(R.id.CaneBatteryButton);
    Button FindCaneButton = findViewById(R.id.FindCaneButton);
    Button VibrationButton = findViewById(R.id.Vibrationbutton);
    Button GpsNavigationButton = findViewById(R.id.GpsNavigationButton);









    Intent intent = new Intent(this, AppSettingsPage.class);
    //this.startActivity ( intent );


}