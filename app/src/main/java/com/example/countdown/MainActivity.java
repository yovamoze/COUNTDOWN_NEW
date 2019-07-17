package com.example.countdown;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 600000;
//hallo ihr super tollen leute!Lest es!
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        onClickButtons();
        updateCountDownTextView();
    }

    private void onClickButtons() {
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         if (mTimerRunning){
             pauseTimer();
         }else {
             startTimer();
         }
            }
        });
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              resetTimer();
            }
        });
    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000 ) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownTextView();

            }

            @Override
            public void onFinish() {
                  mTimerRunning = false;
                  mButtonStartPause.setText ("satrt");
                  mButtonStartPause.setVisibility(View.INVISIBLE);
                  mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();
       //Timer is running
        mTimerRunning = true;
        //Button gets another text to pause
        mButtonStartPause.setText("pause");
        //reset is invisible
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void updateCountDownTextView() {
        int minutes = (int) (mTimeLeftInMillis /100)/ 60;
        int seconds = (int) (mTimeLeftInMillis /100) % 60;
        String timeLeftFormat = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormat);
    }

    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning=false;
        mButtonReset.setVisibility(View.VISIBLE);
        mButtonStartPause.setText("start");

    }

    private void resetTimer(){
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownTextView();
        mButtonStartPause.setText("start");
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }


    private void findViews() {
        mButtonReset = findViewById(R.id.button_reset);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
    }
}
