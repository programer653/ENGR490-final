package com.example.engr490_final;

import static android.widget.Toast.LENGTH_LONG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class AppSettingsPage extends AppCompatActivity {

    ConstraintLayout settingsXML ;
    int selectedColor = R.color.blue;
    int Colortext = R.color.black;
    int Colorbutton = R.color.blue2;
    float fontSize = 10;

    Switch switch1;
    Switch switch2 ;
    Switch switch3 ;

    Button Backbutton ;
    Button buttonSubmit;
    EditText FonteditText ;

    TextView textview2;
     SharedPreferences sp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings_page);

        settingsXML = (ConstraintLayout) findViewById(R.id.settingsXML);
        switch1  = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        switch3 = findViewById(R.id.switch3);
        Backbutton = findViewById(R.id.Backbutton);
        buttonSubmit  = findViewById(R.id.buttonSubmit);
        FonteditText = findViewById(R.id.FontEditText);
        textview2 = findViewById(R.id.textView2);

        // Initialize SharedPreference
        sp = getSharedPreferences("ColorPref", Context.MODE_PRIVATE);

        applySelectedColor(selectedColor);

        Backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenMainPage();
            }
        });

    }



    public void changeColorButton(View view)
    {

        String fontSizeText = FonteditText.getText().toString().trim();
        fontSize = Float.parseFloat(fontSizeText);


        if (switch1.isChecked())
        {
           selectedColor = R.color.blue;
           Colortext = R.color.black;
           Colorbutton = R.color.blue2;




        } else if (switch2.isChecked())
        {
            selectedColor = R.color.orange;
            Colortext = R.color.black;
            Colorbutton = R.color.orange2;



        } else if (switch3.isChecked()) {

            selectedColor = R.color.white;
            Colortext = R.color.orange;
            Colorbutton = R.color.white2;
        }

        applySelectedColor(selectedColor);
        Backbutton.setTextSize(fontSize);
        Backbutton.setTextColor(getColor(Colortext));
        Backbutton.setBackgroundColor(getColor(Colorbutton));

        buttonSubmit.setTextSize(fontSize);
        buttonSubmit.setTextColor(getColor(Colortext));
        buttonSubmit.setBackgroundColor(getColor(Colorbutton));


        switch1.setTextSize(fontSize);
        switch1.setTextColor(getColor(Colortext));

        switch2.setTextSize(fontSize);
        switch2.setTextColor(getColor(Colortext));

        switch3.setTextSize(fontSize);
        switch3.setTextColor(getColor(Colortext));

        textview2.setTextSize(fontSize);
        textview2.setTextColor(getColor(Colortext));

        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("selectedColor", selectedColor);
        editor.putInt("ColorText", Colortext);
        editor.putInt("Colorbutton", Colorbutton);
        editor.putFloat("fontSize", fontSize);
        editor.commit();
        Toast.makeText(AppSettingsPage.this, "Information Saved.", Toast.LENGTH_LONG).show();



    }
    private void applySelectedColor( int colorResId) {
        // Apply the selected color to the background
        settingsXML.setBackgroundColor(getColor(colorResId));
    }

    public void OpenMainPage()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}