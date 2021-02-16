package com.example.memory_test_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SaveScoreActivity extends AppCompatActivity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_score);



     // save score
        Button saveScore = (Button)findViewById(R.id.buttonSave);
        saveScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentStart = new Intent(SaveScoreActivity.this, ScoreboardActivity.class);
                    startActivity(intentStart);
                }
            });

    //back to the main menu
        Button cancel = (Button) findViewById(R.id.buttonCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStart = new Intent(SaveScoreActivity.this, MainActivity.class);
                startActivity(intentStart);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
