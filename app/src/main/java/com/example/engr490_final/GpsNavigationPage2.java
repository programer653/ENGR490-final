package com.example.engr490_final;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.Nullable;


import java.util.ArrayList;

import android.os.Bundle;

public class GpsNavigationPage2 extends AppCompatActivity {

    protected static final int RESULT_SPEECH = 1;
    private EditText editTextSource;
    private EditText editTextDestination;
    private Button button;
    private ImageButton btnSpeakdst, btnSpeaksrc;

    LinearLayout gpsxml;

    SharedPreferences sp;

    int selectedColor = R.color.blue;
    int Colortext = R.color.black;
    int Colorbutton = R.color.blue2;
    float fontSize = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_navigation_page2);

        gpsxml = findViewById(R.id.gpsXML);

        sp = getApplicationContext().getSharedPreferences("ColorPref", Context.MODE_PRIVATE);
        selectedColor= sp.getInt("selectedColor", R.color.blue);
        Colortext = sp.getInt("ColorText", R.color.black);
        Colorbutton = sp.getInt("Colorbutton", R.color.blue2);
        fontSize = sp.getFloat("fontSize", 10);

        applySelectedColor(selectedColor, Colortext, Colorbutton, fontSize);

        editTextSource = findViewById(R.id.source);
        editTextDestination = findViewById(R.id.destination);
        button = findViewById(R.id.btnSubmit);
        btnSpeakdst = findViewById(R.id.ttsdest);
        btnSpeaksrc = findViewById(R.id.ttssrc);

        button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String source = editTextSource.getText().toString();
            String destination = editTextDestination.getText().toString();
            if (source.equals("") || destination.equals("")) {
                Toast.makeText(getApplicationContext(), "Enter both source and destination", Toast.LENGTH_SHORT).show();
            } else {
                Uri uri = Uri.parse("https://www.google.com/maps/dir/" + source + "/" + destination);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.google.android.apps.maps");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
    });

        btnSpeakdst.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            editTextDestination.requestFocus(); // Set focus to destination EditText
            startSpeechRecognition();
        }
    });

        btnSpeaksrc.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            editTextSource.requestFocus(); // Set focus to source EditText
            startSpeechRecognition();
        }
    });
}

    private void startSpeechRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        try {
            startActivityForResult(intent, RESULT_SPEECH);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Your device doesn't support Speech to Text", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_SPEECH:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    // Check which EditText field has focus and fill the recognized speech into that field.
                    if (editTextDestination.hasFocus()) {
                        editTextDestination.setText(text.get(0));
                    } else if (editTextSource.hasFocus()) {
                        editTextSource.setText(text.get(0));
                    }
                }
                break;
        }
    }


    // add "your location" for source location

    private void applySelectedColor(int selectedColor, int Colortext, int Colorbutton, float fontSize) {
        // Apply the selected color to the background
        gpsxml.setBackgroundColor(getColor(selectedColor));

        //editTextSource.setTextSize(fontSize);
        //editTextSource.setTextColor(getColor(Colortext));

        //editTextDestination.setTextSize(fontSize);
        //editTextDestination.setTextColor(getColor(Colortext));

        //button.setBackgroundColor(getColor(Colorbutton));
        //button.setTextSize(fontSize);
        //button.setTextColor(getColor(Colortext));

        //btnSpeakdst.setBackgroundColor(getColor(Colorbutton));

        //btnSpeaksrc.setBackgroundColor(getColor(Colorbutton));



        // chnage the coor button and the font text and the size
    }
}

