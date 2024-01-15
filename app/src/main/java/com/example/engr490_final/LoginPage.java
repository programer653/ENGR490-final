package com.example.engr490_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button EnterButton = findViewById(R.id.EnterButton);
        EnterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openMainPage();
            }
        });

    }

    public void openMainPage()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}