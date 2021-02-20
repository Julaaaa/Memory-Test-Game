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

    Spinner spinnerNumber;
    Spinner spinnerTime;
    CheckBox randomNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        randomNumber = (CheckBox) findViewById(R.id.randomNumber);
        spinnerTime = findViewById(R.id.spinnerTime);
        spinnerNumber = findViewById(R.id.spinnerNumber);

        //    View r = findViewById(R.id.);
        View start = findViewById(R.id.backFromTheSettings);
        start.setOnClickListener(this);

        // setting for spinnerNumber
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
                    randomNumber.setTextColor(Color.BLUE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // settings buttom -- go to the game
        Button startTheGame = (Button) findViewById(R.id.buttonStart);
        startTheGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSettings = new Intent(SettingsActivity.this, GameActivity.class);

                int displayTime = Integer.parseInt(spinnerTime.getSelectedItem().toString());

                int count = Integer.parseInt(spinnerNumber.getSelectedItem().toString());

                boolean randomize = randomNumber.isChecked();

                System.out.println(randomize);
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
        // setting button - go to the Main Activvity
        Button backToMenu = (Button) findViewById(R.id.backFromTheSettings);
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSettings = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intentSettings);
            }
        });

        //fill both of the spinners
        spinnerNumber = (Spinner) findViewById(R.id.spinnerNumber);
        Integer[] numbersList = new Integer[]{2, 3, 4, 5, 6, 7, 8, 9};
        ArrayAdapter<Integer> adapterNumbers = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, numbersList);
        spinnerNumber.setAdapter(adapterNumbers);

        spinnerTime = (Spinner) findViewById(R.id.spinnerTime);
        Integer[] time = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> adapterTime = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, time);
        spinnerTime.setAdapter(adapterTime);
    }

    @Override
    public void onClick(View v) {

    }
}
//    public void save(View v) {
//        String text = giveNumber.getText().toString();
//        FileOutputStream fos = null;
//
//        try{
//            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
//            fos.write(text.getBytes());
//
//            giveNumber.getText().clear();
//            Toast.makeText(this,"Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if( fos != null){
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }
//
//    public void load() {
//        FileInputStream fis = null;
//
//        try{
//            fis = openFileInput(FILE_NAME);
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader br = new BufferedReader(isr);
//            StringBuilder sb =new StringBuilder();
//            String text;
//
//            while((text = br.readLine()) != null){
//                sb.append(text).append("\n");
//            }
//
//            giveNumber.setText(sb.toString());
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }


