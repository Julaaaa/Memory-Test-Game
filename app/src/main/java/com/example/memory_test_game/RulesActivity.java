package com.example.memory_test_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class RulesActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        View rules = findViewById(R.id.backFromTheRules);
        rules.setOnClickListener(this);
    }
    @Override
    public void onClick(View rules) {
        //  if(r.getId() == R.id.){
        //define a new Intent for the second Activity
        Intent intent = new Intent(this, MainActivity.class);
        //start the second Activity
        this.startActivity(intent);
    }
}
