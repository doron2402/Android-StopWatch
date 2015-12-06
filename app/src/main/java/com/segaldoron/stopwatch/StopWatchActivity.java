package com.segaldoron.stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;

public class StopWatchActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.seconds = savedInstanceState.getInt("seconds");
            this.running = savedInstanceState.getBoolean("running");
            this.wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        setContentView(R.layout.activity_stop_watch);
        runTimer();
    }


    public void onClickStart(View view) {
        running = true;

    }

    public void onClickStop(View view) {
        running = false;

    }

    public void onClickReset(View view) {
        seconds = 0;
        running = false;
    }


    private void runTimer() {
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);

                if (running) {
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.running = false;
        this.wasRunning = true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (this.wasRunning){
            this.running = true;
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("Puase");
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("Resume");
        if (wasRunning) {
            running = true;
        }
    }

}
