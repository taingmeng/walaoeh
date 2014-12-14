package com.example.walaoeh;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyandroidanimations.library.Animation;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.FadeInAnimation;
import android.widget.ProgressBar;

import java.util.concurrent.CountDownLatch;


public class Splash extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1560;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //final TextView crisis = (TextView) findViewById(R.id.crisis);
        //final TextView teamName = (TextView) findViewById(R.id.teamName);

        //teamName.setVisibility(View.INVISIBLE);

        final ProgressBar progress = (ProgressBar) findViewById(R.id.progress_bar);
        CountDownTimer timer = new CountDownTimer(SPLASH_TIME_OUT,50) {
            @Override
            public void onTick(long l) {
                //current remaining time
                progress.setProgress((int)(SPLASH_TIME_OUT-l)/15);
            }

            @Override
            public void onFinish() {
                Intent i = new Intent(Splash.this, Main.class);
                startActivity(i);
                finish();

            }
        };
        timer.start();



/*
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
/*
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                //crisis.setPaintFlags(crisis.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                /*new FadeInAnimation(teamName).setDuration(500).setListener(new AnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        try{
                            Thread.sleep(1000);
                        }catch (InterruptedException e){

                        }

                    }
                }).animate();

 */
/*

            }
        }, SPLASH_TIME_OUT);

 */
    }


}
