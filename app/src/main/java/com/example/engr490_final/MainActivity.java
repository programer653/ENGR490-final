package com.example.engr490_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;


// add textto speech
//when you are clicking on one button, premiere fois ca va enoncer le button puis deuxieme fois ca va cliquer dessus - done
// chnage naviagtion page to constraint layout
// enregistre switch states
//put app settings page as first page then keep main activity
public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    Button AppSettingsButton;
    Button VibrationButton;
    Button GpsNavigationButton;

    ConstraintLayout mainXML;
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
        setContentView(R.layout.activity_main);

        textToSpeech = new TextToSpeech(this, this);

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
                if (!isSpeaking) {
                    //speakText(AppSettingsButton.getText().toString());
                    handleButtonClick(AppSettingsButton.getText().toString(), AppSettingsButton);

                } else {
                    // Navigate to another activity
                    openAppSettingsPage();

                }

            }
        });

        //Setup button to open Vibration Settings
        VibrationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!isSpeaking) {
                    //speakText(AppSettingsButton.getText().toString());
                    handleButtonClick(VibrationButton.getText().toString(), VibrationButton);

                } else {
                    // Navigate to another activity
                    OpenVibrationPage();

                }
            }
        });

        //Setup to open GPS Navigation
        GpsNavigationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!isSpeaking) {
                    //speakText(AppSettingsButton.getText().toString());
                    handleButtonClick(GpsNavigationButton.getText().toString(), GpsNavigationButton);

                } else {
                    // Navigate to another activity
                    OpenGpsPage();

                }
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