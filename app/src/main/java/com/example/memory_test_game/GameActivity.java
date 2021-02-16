package com.example.memory_test_game;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import org.w3c.dom.Text;

import java.time.Clock;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    //User input
    private boolean randomize = true;
    private int count = 3;
    private int displayTime = 1000 * 5;
    //Constants
    private int max_count = 9;
    private int rows = 5;
    private int columns = 5;
    private int gameTime = 1000 * 60;

    //Initializing stuff
    private int[][] gameBoard = new int[rows][columns];
    private Button[][] buttons = new Button[rows][columns];
    private int[] numbers = new int[count];
    private Random random = new Random();
    private int clicks = 0;
    private int round = 0;
    private int points = 0;
    private TextView textPoints;
    private ProgressBar progressBar;
    private int endTime;
    private int leftTime;
    private int allTime;
    private Handler handler = new Handler();
    private Runnable gameRunnable = new Runnable() {
        @Override
        public void run() {
            playerLoses();
        }
    };
    private Runnable displayRunnable = new Runnable() {
        @Override
        public void run() {
            hideNumbers();
            //Initializing the timer resposible for updating the bar
            CountDownTimer timer = new CountDownTimer(gameTime,50) {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onTick(long millisUntilFinished) {
                    updateProgressBar();
                }

                @Override
                public void onFinish() {
                    cancel();
                }
            }.start();
            //Setting the new time for the progress bar
            allTime = gameTime;
            endTime = allTime + (int)System.currentTimeMillis();
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Assigning view IDs to fields
        //Point display
        textPoints = findViewById(R.id.textPoints);
        // Quit the game button
        Button exitFromGame = (Button) findViewById(R.id.backFromTheGame);
        exitFromGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStart = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intentStart);
            }
        });
        //Time progress bar
        progressBar = findViewById(R.id.progressBar);

        CountDownTimer timer = new CountDownTimer(displayTime,50) {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTick(long millisUntilFinished) {
                updateProgressBar();
            }

            @Override
            public void onFinish() {
                cancel();
            }
        }.start();

        //Initializing the board
        initGameLogic();

    }

    @Override
    public void onClick(View v) {

        if(((Button) v).getText() == String.valueOf(numbers[clicks])) {
            clicks++;
            //Checking the win condition
            if(clicks == count){
                playerWins();
            }
        }
        //Loosing condition
        else {
            playerLoses();
        }
    }

    private void initGameLogic(){
        System.out.println("d");

        //Randomizing numbers used for the game
        for(int i=0; i< numbers.length; i++){
            if (randomize) {
                numbers[i] = random.nextInt(max_count)+1;
            }
            else {
                numbers[i] = random.nextInt(count)+1;
            }
            boolean repeat = true;
            while(repeat){
                repeat = false;
                int check = numbers[i];

                for(int j=0; j<i; j++){
                    if(numbers[j]==check){
                        repeat = true;
                    }
                }
                if(repeat) {
                    numbers[i] = random.nextInt(count)+1;
                }
            }
        }
//        Debug: printing out the randomized numbers
//        System.out.println(Arrays.toString(numbers));

        //Assigning numbers to fields on the board
        for (int i=0; i < count; i++) {

            int rowId = random.nextInt(rows);
            int columnId = random.nextInt(columns);

            while (gameBoard[rowId][columnId] != 0) {
                rowId = random.nextInt(rows);
                columnId = random.nextInt(columns);
            }
            gameBoard[rowId][columnId] = numbers[i];

        }
//        Debug: printing out the gameBoard
//        for(int[] row : gameBoard) {
//            for(int number : row) {
//                System.out.print(number + " ");
//            }
//            System.out.println();
//        }

        //Displaying the text on the buttons
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
                buttons[i][j].setTextSize(60f);
                buttons[i][j].setClickable(false);
                if (gameBoard[i][j] != 0) {
                    buttons[i][j].setText(String.valueOf(gameBoard[i][j]));
                }
                else{
                    buttons[i][j].setText(null);
                }
            }
        }

        //Sorting numbers - this way we can check if the player clicks them in the right order
        Arrays.sort(numbers);

        //Setting the timers for hiding the numbers and losing the game
        handler.postDelayed(displayRunnable,displayTime);

        //Setting the time for the progress bar
        allTime = displayTime;
        endTime = allTime + (int)System.currentTimeMillis();
    }

    private void playerWins(){
        //Shortening time - increase in difficulty
        gameTime = gameTime - 5 * 1000;
        //Calcilationg the runds to add after winning
        numberOfRounds();
        //Calculationg the point to add after winning
        countPoints();
        //Reseting the game
        resetGame();
        //Starting new round
        initGameLogic();
    }

    private void playerLoses(){
        System.out.println("dsffrfcre");
        handler.removeCallbacks(gameRunnable);
        Intent intentSave = new Intent(GameActivity.this, SaveScoreActivity.class);
        startActivity(intentSave);

    }

    private void hideNumbers(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                buttons[i][j].setTextSize(0f);
                buttons[i][j].setClickable(true);
            }
        }
        handler.postDelayed(gameRunnable,gameTime);
    }

    private void resetGame(){
        //Stopping the old runnable so a new one can be started
        handler.removeCallbacks(gameRunnable);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                gameBoard[i][j] = 0;
            }
        }
        clicks = 0;
    }


    //counting the number of rounds
    private void numberOfRounds(){
        round++;
        System.out.println("Round: " + round);
    }

    //counting the points scored
    private void countPoints(){
        if (randomize = true) {
            points = points + (int) ((5000/displayTime)*(leftTime / 100) * (round * 1.08) * (Math.pow(count, 1.3))*1.05);
        }
        else {
            points = points + (int) ((5000/displayTime)*(leftTime / 100) * (round * 1.08) * (Math.pow(count, 1.3)));
        }

        //Debug: Print out the number of points
        System.out.println("Points scored: " + points);

        //Display the number of points in the field
        textPoints.setText("Points: " + points);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateProgressBar(){
        leftTime = endTime - (int)System.currentTimeMillis();
        progressBar.setProgress((leftTime*100)/allTime,true);
    }


}



