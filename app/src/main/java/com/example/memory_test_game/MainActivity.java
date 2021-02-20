package com.example.memory_test_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = (Button) findViewById(R.id.buttonSettings);
        Button scoreboard = (Button) findViewById(R.id.buttonScoreboard);
        Button rules = (Button) findViewById(R.id.buttonRules);

         start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStart = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intentStart);
            }
        });
        scoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentScoreboard = new Intent(MainActivity.this, ScoreboardActivity.class);
                startActivity(intentScoreboard);
            }
        });
        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRules = new Intent(MainActivity.this, RulesActivity.class);
                startActivity(intentRules);
            }
        });
    }
}