package com.example.engr490_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class VibrationSettingsPage extends AppCompatActivity implements TextToSpeech.OnInitListener{

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

    private TextToSpeech textToSpeech;
    private boolean isSpeaking = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibration_settings_page);

        textToSpeech = new TextToSpeech(this, this);
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
                if (!isSpeaking) {
                    //speakText(AppSettingsButton.getText().toString());
                    handleButtonClick(Backbutton.getText().toString(), Backbutton);

                } else {
                    OpenMainPage();
                }
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
