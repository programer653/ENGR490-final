package com.example.engr490_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SharedPreferencesHelper extends AppCompatActivity {


    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferencesHelper(Context context)
    {
        this.context = context;
        //opening the file and returning the sharedpreference object
        this.sharedPreferences = context.getSharedPreferences(this.context.getString(R.string.Settings_SharedPreferences), Context.MODE_PRIVATE);

    }
}