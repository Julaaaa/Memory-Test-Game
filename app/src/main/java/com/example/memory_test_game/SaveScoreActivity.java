package com.example.memory_test_game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class SaveScoreActivity extends AppCompatActivity{

    private TextView textPoints;
    private TextView textTopScore;
    private TextView titleTopScore;
    private EditText enterYourNick;
    private String data;
    private int topScore = 0;
    String[] retArray;
    private int points;
    private boolean foundFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_score);

        Bundle bundle = getIntent().getExtras();
        textPoints = (TextView) findViewById(R.id.points);
        textTopScore = (TextView) findViewById(R.id.topScore);
        titleTopScore = (TextView) findViewById(R.id.titleTopScore);
        enterYourNick = (EditText)findViewById(R.id.entertYourNick);

        //Getting the points value from the previous activity
        points = bundle.getInt("points");

        //Reading the previous high score from the scoreboard.txt file
        String ret = readFromFile(SaveScoreActivity.this);
        //Displaying the scored amount of points
        textPoints.setText(String.valueOf(points));

        if (foundFile) {
            //Displaying the top score in TextView if the file was found
            retArray = ret.split(",");
            textTopScore.setText(retArray[0]+ ": " + retArray[1]);
            //Saving the top score for further calculations
            topScore = Integer.parseInt(retArray[1]);

        }

        Button cancel = (Button) findViewById(R.id.buttonCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStart = new Intent(SaveScoreActivity.this, MainActivity.class);
                startActivity(intentStart);
            }
        });

        Button saveScore = (Button)findViewById(R.id.buttonSave);
        saveScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Saving the new high score to the file
                data = String.valueOf(enterYourNick.getText()).concat(",").concat(String.valueOf(topScore));
                writeToFile(data,SaveScoreActivity.this);

                Intent intentSave = new Intent(SaveScoreActivity.this, ScoreboardActivity.class);
                startActivity(intentSave);
            }
        });

        //Checking if a new high score was achieved
        if(topScore < points){
            topScore = points;
            saveScore.setClickable(true);
            saveScore.setEnabled(true);
            titleTopScore.setText("New high score! Congratulations!\nPrevious high score:");
        }
        else {
            titleTopScore.setText("Try better next time!\nCurrent top score:");
        }

    }

    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("scoreboard.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("scoreboard.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();

                foundFile = true;
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
            foundFile = false;
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
            foundFile = false;
        }

        return ret;
    }
}


