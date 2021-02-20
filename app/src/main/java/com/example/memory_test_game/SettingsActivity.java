package com.example.memory_test_game;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String FILE_NAME = "example.txt";

    private Spinner spinnerNumber;
    private Spinner spinnerTime;
    private CheckBox randomNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        View start = findViewById(R.id.backFromTheSettings);
        start.setOnClickListener(this);

        randomNumber = (CheckBox) findViewById(R.id.randomNumber);
        spinnerTime = findViewById(R.id.spinnerTime);
        spinnerNumber = findViewById(R.id.spinnerNumber);

        //filling the spinners
        spinnerNumber = (Spinner) findViewById(R.id.spinnerNumber);
        Integer[] numbersList = new Integer[]{2, 3, 4, 5, 6, 7, 8, 9};
        ArrayAdapter<Integer> adapterNumbers = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, numbersList);
        spinnerNumber.setAdapter(adapterNumbers);

        spinnerTime = (Spinner) findViewById(R.id.spinnerTime);
        Integer[] time = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> adapterTime = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, time);
        spinnerTime.setAdapter(adapterTime);


        //Deactivating the "Random Numbers" button if the player chooses to play with 9 numbers
        spinnerNumber = (Spinner) findViewById(R.id.spinnerNumber);
        spinnerNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v, int position, long id) {

                if (Integer.parseInt(spinnerNumber.getSelectedItem().toString()) == 9) {
                    randomNumber.setTextColor(Color.GRAY);
                    randomNumber.setClickable(false);
                    randomNumber.setChecked(false);
                } else {
                    randomNumber.setClickable(true);
                    randomNumber.setTextColor(Color.parseColor("#055173"));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Starting the game
        Button startTheGame = (Button) findViewById(R.id.buttonStart);
        startTheGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSettings = new Intent(SettingsActivity.this, GameActivity.class);

                //Sending necessary values to the "Game" activity using a bundle
                int displayTime = Integer.parseInt(spinnerTime.getSelectedItem().toString());

                int count = Integer.parseInt(spinnerNumber.getSelectedItem().toString());

                boolean randomize = randomNumber.isChecked();

                //Create the bundle
                Bundle bundle = new Bundle();

                //Add data to bundle
                bundle.putInt("displayTime", displayTime);
                bundle.putInt("count", count);
                bundle.putBoolean("randomize", randomize);

                //Add the bundle to the intent
                intentSettings.putExtras(bundle);

                //Fire the second activity
                startActivity(intentSettings);
            }
        });


        Button backToMenu = (Button) findViewById(R.id.backFromTheSettings);
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