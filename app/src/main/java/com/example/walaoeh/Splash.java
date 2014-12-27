package com.example.walaoeh;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ProgressBar;


public class Splash extends FragmentActivity implements FacebookLoginFragment.OnLoginListener {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1500;
    FacebookLoginFragment loginFragment;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progress = (ProgressBar) findViewById(R.id.progress_bar);

        if(savedInstanceState == null) {
            loginFragment = new FacebookLoginFragment();
            getSupportFragmentManager()
                    .beginTransaction().add(R.id.authFragment, loginFragment).commit();
        } else {
            loginFragment = (FacebookLoginFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.authFragment);
        }

    }

    public void isLogin(){
        getSupportFragmentManager().beginTransaction().hide(loginFragment).commit();

        progress.setVisibility(View.VISIBLE);

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
