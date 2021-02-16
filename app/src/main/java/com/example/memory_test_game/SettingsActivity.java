package com.example.memory_test_game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //    View r = findViewById(R.id.);
        View start = findViewById(R.id.backFromTheSettings);
        start.setOnClickListener(this);


        // settings buttom -- go to the game
        Button startTheGame = (Button)findViewById(R.id.buttonStart);
        startTheGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intentSettings = new Intent(SettingsActivity.this, GameActivity.class);
                 startActivity(intentSettings);
            }
        });
        // setting button - go to the Main Activvity
        Button backToMenu = (Button)findViewById(R.id.backFromTheSettings);
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSettings = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intentSettings);
            }
        });


    }

    @Override
    public void onClick(View v) {

    }
}

