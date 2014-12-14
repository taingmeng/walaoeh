package com.example.walaoeh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.easyandroidanimations.library.Animation;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.FadeInAnimation;
import com.easyandroidanimations.library.FadeOutAnimation;
import com.example.walaoeh.helper.Const;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Activity {

    private int runningIndex = 0;
    private Timer timer;
    private TimerTask task;

    private int minutes = 0;

    private int MAXIMUM_TIME = 5*1000;

    private TextView dialogTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = (Button) findViewById(R.id.activity_mainPlayButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Main.this, Timeline.class);
                startActivity(intent);
            }
        });

        dialogTextView = (TextView) findViewById(R.id.dialogTextView);
        initializeTimer();

    }

    private void initializeTimer() {

        timer = new Timer();
        task = new TimerTask(){
            @Override
            public void run() {
                minutes += 1000;

                if (minutes >= MAXIMUM_TIME) {
                    minutes = 0;
                    runningIndex = (runningIndex + 1) % Const.MAIN_MESSAGES.length;

                    dialogTextView.post(new Runnable() {
                        @Override
                        public void run() {

                            new FadeOutAnimation(dialogTextView).setDuration(500).setListener(new AnimationListener() {

                                @Override
                                public void onAnimationEnd(Animation animation) {

                                    dialogTextView.setText("");

                                    new FadeInAnimation(dialogTextView).setDuration(500).setListener(new AnimationListener() {
                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            dialogTextView.setText(Const.MAIN_MESSAGES[runningIndex]);
                                        }
                                    }).animate();
                                }

                            }).animate();


                        }
                    });
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
