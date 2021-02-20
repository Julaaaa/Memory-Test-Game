package com.example.memory_test_game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ScoreboardActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView nickText;
    private TextView scoreText;
    String[] retArray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        String ret = readFromFile(ScoreboardActivity.this);

        nickText = (TextView)findViewById(R.id.nickText);
        scoreText = (TextView)findViewById(R.id.scoreText);
        retArray = ret.split(",");

        nickText.setText(retArray[0]);
        scoreText.setText(retArray[1]);

        View board = findViewById(R.id.backFromScoreboard);
        board.setOnClickListener(this);
    }

    @Override
    public void onClick(View board) {
        //  if(r.getId() == R.id.){
        //define a new Intent for the second Activity
        Intent intent = new Intent(this, MainActivity.class);
        //start the second Activity
        this.startActivity(intent);
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
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
