package com.example.engr490_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VibrationSettingsPage extends AppCompatActivity {

    Button Backbutton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibration_settings_page);

        Backbutton = findViewById(R.id.Backbutton);
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
