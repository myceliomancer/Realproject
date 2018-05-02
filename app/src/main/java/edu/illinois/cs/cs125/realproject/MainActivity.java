package edu.illinois.cs.cs125.realproject;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.*;


public class MainActivity extends AppCompatActivity {
    private Chronometer timer;
    private boolean isRunning;
    private long stopOffset;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = findViewById(R.id.timer);
        Button reset = (Button) findViewById(R.id.reset);
        Button start = (Button) findViewById(R.id.start);
        Button stop = (Button) findViewById(R.id.stop);
        final TextView textboi= (TextView) findViewById(R.id.textboi);
        Cube perma = new Cube();
        String[] scramB = perma.scramble(20);
        String finalScramz = Cube.toString(scramB);
        textboi.setText(finalScramz);


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cube current = new Cube();
                String[] scram = current.scramble(20);
                String finalScram = Cube.toString(scram);
                textboi.setText(finalScram);

                timer.setBase(SystemClock.elapsedRealtime());
                stopOffset = 0;
                timer.stop();
            }
        });

    }

    public void start(View v){
        if (!isRunning) {
            timer.setBase(SystemClock.elapsedRealtime() - stopOffset);
            timer.start();
            isRunning = true;
        }
    }

    public void stop(View v) {
        if (isRunning) {
            timer.stop();
            stopOffset = SystemClock.elapsedRealtime() - timer.getBase();
            isRunning = false;
        }
    }

    public void reset(View v) {
        Cube ronald = new Cube();
        String[] scram = ronald.scramble(20);
        String printMe = Cube.toString(scram); //This the string version of scramble string array.
    }




}
