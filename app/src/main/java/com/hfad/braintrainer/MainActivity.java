package com.hfad.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView timerTextView ;
    TextView resultTextView;
    int score = 0;
    int numberOfQuestions = 0;
    Button button1 ;
    Button button2 ;
    Button button3 ;
    TextView sumTextView;
    Button button4 ;
    TextView pointsTextView ;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30000+100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished/1000));

            }

            @Override
            public void onFinish() {

                timerTextView.setText("0s");
                playAgainButton.setVisibility(View.VISIBLE);

                //resultTextView.setText("Your Score L " + score + "/" + numberOfQuestions );
                resultTextView.setText(String.format("Your Score: %d/%d", score, numberOfQuestions));

            }
        }.start();


    }

    public void generateQuestion(){

        answers.clear();
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(a + " + " + b);

        locationOfCorrectAnswer = rand.nextInt(4);

        int incorrectAnswer;

        for(int i=0;i<4;++i){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else{
                incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer == a+b){
                    incorrectAnswer = rand.nextInt(41);
                }

                answers.add(incorrectAnswer);

            }
        }

        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));



    }

    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){

            score++;

            resultTextView.setText("CORRECT!");
            //Log.i("Correct Answer !", "Correct");
        }
        else{
            resultTextView.setText("WRONG!");
        }
        numberOfQuestions++;
        pointsTextView.setText(score + "/" + numberOfQuestions);
        generateQuestion();

        //Log.i("Tag", (String)view.getTag());
    }

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        playAgain(gameRelativeLayout);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        startButton = (Button)findViewById(R.id.startButton);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);

        sumTextView = (TextView)findViewById(R.id.sumTextView);


       // playAgain(sumTextView);

        //generateQuestion();

    }
}
