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
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class AppSettingsPage extends AppCompatActivity implements TextToSpeech.OnInitListener {

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

    private TextToSpeech textToSpeech;
    private boolean isSpeaking = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings_page);

        textToSpeech = new TextToSpeech(this, this);
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

        selectedColor= sp.getInt("selectedColor", R.color.blue);
        Colortext = sp.getInt("ColorText", R.color.black);
        Colorbutton = sp.getInt("Colorbutton", R.color.blue2);
        fontSize = sp.getFloat("fontSize", 10);

        applySelectedColor(selectedColor, Colortext, Colorbutton, fontSize);

        Backbutton.setOnClickListener(new View.OnClickListener() {
            // add if font size not chnaged then error
            @Override
            public void onClick(View view) {
                if (!isSpeaking) {
                    //speakText(AppSettingsButton.getText().toString());
                    handleButtonClick(Backbutton.getText().toString(), Backbutton);

                } else {
                    OpenMainPage();
                }
            }
        });

    }



    public void changeColorButton(View view)
    {
        String fontSizeText = FonteditText.getText().toString().trim();
        fontSize = Float.parseFloat(fontSizeText);


        if (!isSpeaking) {
            //speakText(AppSettingsButton.getText().toString());
            handleButtonClick(buttonSubmit.getText().toString(), Backbutton);

        }
        else {
            if (switch1.isChecked()) {
                selectedColor = R.color.blue;
                Colortext = R.color.black;
                Colorbutton = R.color.blue2;


            } else if (switch2.isChecked()) {
                selectedColor = R.color.orange;
                Colortext = R.color.black;
                Colorbutton = R.color.orange2;


            } else if (switch3.isChecked()) {

                selectedColor = R.color.white;
                Colortext = R.color.orange;
                Colorbutton = R.color.white2;
            }

            if(fontSizeText.isEmpty())
            {
                Toast.makeText(AppSettingsPage.this, "PLease enter a font size", Toast.LENGTH_SHORT).show();
                buttonSubmit.setEnabled(false);
            }
            else {
                buttonSubmit.setEnabled(true);
                applySelectedColor(selectedColor, Colortext, Colorbutton, fontSize);
                SharedPreferences.Editor editor = sp.edit();

                editor.putInt("selectedColor", selectedColor);
                editor.putInt("ColorText", Colortext);
                editor.putInt("Colorbutton", Colorbutton);
                editor.putFloat("fontSize", fontSize);
                editor.commit();
                Toast.makeText(AppSettingsPage.this, "Information Saved.", Toast.LENGTH_LONG).show();
            }
        }



    }
    private void applySelectedColor(int selectedColor,  int Colortext, int Colorbutton, float fontSize) {
        // Apply the selected color to the background
        settingsXML.setBackgroundColor(getColor(selectedColor));
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
    }

    public void OpenMainPage()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void speakText(String text, Button button) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        isSpeaking = true;
        // Add a delay to ensure the speech is completed before navigating
        button.postDelayed(new Runnable() {
            @Override
            public void run() {
                isSpeaking = false;
            }
        }, 2000); // Adjust the delay as needed*/
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.getDefault());

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "Language not supported.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Initialization failed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    private void handleButtonClick(String buttonText, Button button) {
        speakText(buttonText, button);

    }


}