package com.example.engr490_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class VibrationSettingsPage extends AppCompatActivity {

    Button Backbutton ;
    Switch VibrationOnSwitch;
    SeekBar VibrationseekBar;

    TextView VibrationLevelTextView;

    ConstraintLayout vibrationXML;
    SharedPreferences sp;

    int selectedColor = R.color.blue;
    int Colortext = R.color.black;
    int Colorbutton = R.color.blue2;
    float fontSize = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibration_settings_page);
        vibrationXML = findViewById(R.id.vibrationXML);

        Backbutton = findViewById(R.id.Backbutton);
        VibrationOnSwitch = findViewById(R.id.VibrationOnSwitch);
        VibrationseekBar = findViewById(R.id.VibrationseekBar);
        VibrationLevelTextView = findViewById(R.id.VibrationLevelTextView);

        sp = getApplicationContext().getSharedPreferences("ColorPref", Context.MODE_PRIVATE);
        selectedColor= sp.getInt("selectedColor", R.color.blue);
        Colortext = sp.getInt("ColorText", R.color.black);
        Colorbutton = sp.getInt("Colorbutton", R.color.blue2);
        fontSize = sp.getFloat("fontSize", 10);

        applySelectedColor(selectedColor, Colortext, Colorbutton, fontSize);
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

    private void applySelectedColor(int selectedColor, int Colortext, int Colorbutton, float fontSize) {
        // Apply the selected color to the background
        vibrationXML.setBackgroundColor(getColor(selectedColor));

        Backbutton.setBackgroundColor(getColor(Colorbutton));
        Backbutton.setTextSize(fontSize);
        Backbutton.setTextColor(getColor(Colortext));

        VibrationOnSwitch.setTextSize(fontSize);
        VibrationOnSwitch.setTextColor(getColor(Colortext));

        //VibrationLevelTextView.setBackgroundColor(getColor(Colorbutton));
        VibrationLevelTextView.setTextSize(fontSize);
        VibrationLevelTextView.setTextColor(getColor(Colortext));




        // chnage the coor button and the font text and the size
    }
}
