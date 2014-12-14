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
    private static int SPLASH_TIME_OUT = 1500;

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
                progress.setProgress((int)((SPLASH_TIME_OUT-l)/15)+30);
            }

            @Override
            public void onFinish() {
                Intent i = new Intent(Splash.this, Main.class);
                startActivity(i);
                finish();


            }
        };
        timer.start();


    }


}
