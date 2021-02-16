package com.example.memory_test_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreboardActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);


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

}
