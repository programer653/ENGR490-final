package com.example.engr490_final;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Locale;

public class UserAgreementPage extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private static final String PREF_NAME = "MyPrefs";
    private static final String AGREEMENT_KEY = "agreed";

    //ConstraintLayout activityxml;

    int selectedColor = R.color.blue;
    int Colortext = R.color.black;
    int Colorbutton = R.color.blue2;
    float fontSize = 20;

    SharedPreferences sp;
    CheckBox cb;
    Button buttonAccept, ListenButton;

    TextView textView3, textView4, textView5, textView6, textView7, textView8;

    private TextToSpeech textToSpeech;
    private boolean isSpeaking = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_agreement_page);

        // hook for checkbox
        cb = findViewById(R.id.checkBox);
        buttonAccept = findViewById(R.id.ButtonAccept);
        ListenButton = findViewById(R.id.ListenButton);
        sp = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        boolean agreed = sp.getBoolean(AGREEMENT_KEY, false);
        String para123 = "@string/para123";

        sp = getApplicationContext().getSharedPreferences("ColorPref", Context.MODE_PRIVATE);
        selectedColor= sp.getInt("selectedColor", R.color.blue);
        Colortext = sp.getInt("ColorText", R.color.black);
        Colorbutton = sp.getInt("Colorbutton", R.color.blue2);
        fontSize = sp.getFloat("fontSize", 10);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);

        applySelectedColor(selectedColor, Colortext, Colorbutton, fontSize);


        if (agreed) {
            // If already agreed, go directly to MainActivity
            openMainActivityPage();
            finish();
        }
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isSpeaking) {
                    //speakText(AppSettingsButton.getText().toString());
                    handleButtonClick(buttonAccept.getText().toString(), buttonAccept);

                } else {
                    if (cb.isChecked()) {

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean(AGREEMENT_KEY, true);
                        editor.apply();
                        //switch from termsconditionsactivity to mainactivity


                        openMainActivityPage();
                    } else {
                        cb.setError("Please check this checkbox to approve terms ");
                        Toast.makeText(UserAgreementPage.this, "Please check the below box for approval", Toast.LENGTH_SHORT).show();
                        //CHANGES
                        showToastWithSpeech("Please check the below box for approval");
                    }

                }
            }
        });

        //CHANGES
        ListenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isSpeaking) {
                    handleButtonClick(para123, ListenButton);

                }
            }
        });
    }


    public void openMainActivityPage()
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
        }, 2000); // Adjust the delay as needed
    }

    //CHANGES
    private void showToastWithSpeech(String message) {
        // Convert text to speech
        textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);

        // Show the toast message
        Context context = getApplicationContext();
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
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

    private void applySelectedColor(int selectedColor, int Colortext, int Colorbutton, float fontSize) {
        // Apply the selected color to the background
        //activityxml.setBackgroundColor(getColor(selectedColor));

        textView3.setTextSize(fontSize);
        textView3.setTextColor(getColor(Colortext));

        textView4.setTextSize(fontSize);
        textView4.setTextColor(getColor(Colortext));

        textView5.setTextSize(fontSize);
        textView5.setTextColor(getColor(Colortext));

        textView6.setTextSize(fontSize);
        textView6.setTextColor(getColor(Colortext));

        textView7.setTextSize(fontSize);
        textView7.setTextColor(getColor(Colortext));

        textView8.setTextSize(fontSize);
        textView8.setTextColor(getColor(Colortext));

        cb.setTextSize(fontSize);
        cb.setTextColor(getColor(Colortext));

        ListenButton.setBackgroundColor(getColor(Colorbutton));
        ListenButton.setTextSize(fontSize);
        ListenButton.setTextColor(getColor(Colortext));

        buttonAccept.setBackgroundColor(getColor(Colorbutton));
        buttonAccept.setTextSize(fontSize);
        buttonAccept.setTextColor(getColor(Colortext));

    }
}