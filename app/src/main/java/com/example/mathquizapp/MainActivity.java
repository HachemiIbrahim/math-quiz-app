package com.example.mathquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startbutton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainbutton;
    TextView sumTextView;
    TextView resultTextView;
    TextView scoreTextView;
    TextView timerTextView;
    int location_of_the_correct_answer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int score=0;
    int nbrquestion=0;


    public void start(View view){
        startbutton.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    public void playAgain(View view){
        score=0;
        nbrquestion=0;
        timerTextView.setText("60s");
        scoreTextView.setText("0/0");
        resultTextView.setText("");
        playAgainbutton.setVisibility(View.INVISIBLE);
        generateQuestion();
        new CountDownTimer(60100, 1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");

            }

            @Override
            public void onFinish() {
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                sumTextView.setVisibility(View.INVISIBLE);
                scoreTextView.setVisibility(View.INVISIBLE);
                timerTextView.setVisibility(View.INVISIBLE);
                playAgainbutton.setVisibility(View.VISIBLE);
                resultTextView.setText("you have scored \n "+Integer.toString(score)+"/"+Integer.toString(nbrquestion));
                timerTextView.setText("0s");
            }
        }.start();
    }

    public void generateQuestion(){
        Random random = new Random();
        int a = random.nextInt(22);
        int b = random.nextInt(22);
        int c = random.nextInt(4);
        location_of_the_correct_answer=random.nextInt(4);
        int incorrect_answer;
        answers.clear();
        if(c==0){
            sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));
            for (int i = 0; i < 4; i++) {
                if (i == location_of_the_correct_answer) {
                    answers.add(a + b);
                } else {
                    incorrect_answer = random.nextInt(41);
                    while (incorrect_answer == a + b) {
                        incorrect_answer = random.nextInt(41);
                    }
                    answers.add(incorrect_answer);
                }
            }
            button0.setText(Integer.toString(answers.get(0)));
            button1.setText(Integer.toString(answers.get(1)));
            button2.setText(Integer.toString(answers.get(2)));
            button3.setText(Integer.toString(answers.get(3)));
        }
        if(c==1){
            sumTextView.setText(Integer.toString(a) + "-" + Integer.toString(b));
            for (int i = 0; i < 4; i++) {
                if (i == location_of_the_correct_answer) {
                    answers.add(a - b);
                } else {
                    incorrect_answer = random.nextInt(21);
                    while (incorrect_answer == a - b) {
                        incorrect_answer = random.nextInt(21);
                    }
                    answers.add(incorrect_answer);
                }
            }
            button0.setText(Integer.toString(answers.get(0)));
            button1.setText(Integer.toString(answers.get(1)));
            button2.setText(Integer.toString(answers.get(2)));
            button3.setText(Integer.toString(answers.get(3)));
        }
        if(c==2){
            sumTextView.setText(Integer.toString(a) + "*" + Integer.toString(b));
            for (int i = 0; i < 4; i++) {
                if (i == location_of_the_correct_answer) {
                    answers.add(a * b);
                } else {
                    incorrect_answer = random.nextInt(200);
                    while (incorrect_answer == a * b) {
                        incorrect_answer = random.nextInt(200);
                    }
                    answers.add(incorrect_answer);
                }
            }
            button0.setText(Integer.toString(answers.get(0)));
            button1.setText(Integer.toString(answers.get(1)));
            button2.setText(Integer.toString(answers.get(2)));
            button3.setText(Integer.toString(answers.get(3)));
        }
    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(location_of_the_correct_answer))){
            resultTextView.setText("Correct");
            score++;
        }
        else{
            resultTextView.setText("Wrong");
        }
        nbrquestion++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(nbrquestion));
        generateQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        startbutton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.questionTextView);
        resultTextView = (TextView) findViewById(R.id.answerTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);

        playAgainbutton = (Button)findViewById(R.id.playAgainButton);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
    }
}