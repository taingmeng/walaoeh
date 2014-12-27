package com.example.walaoeh;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.view.Window;

import com.example.walaoeh.helper.Pref;
import com.example.walaoeh.mainfragments.MainFragment;
import com.example.walaoeh.mainfragments.SignoutFragment;

public class Main extends FragmentActivity
     implements MainFragment.SlidePanelButtonPressed{

    SlidingPaneLayout slidePanel;
    SignoutFragment signoutFragment;
    MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);

        slidePanel = (SlidingPaneLayout) findViewById(R.id.slidingPanel);
        FragmentManager fm = getSupportFragmentManager();
        if(savedInstanceState == null) {
            FragmentTransaction ft = fm.beginTransaction();
            mainFragment = new MainFragment();
            signoutFragment = SignoutFragment.newInstance(Pref.getPlayerStage());

            ft.add(R.id.leftPane, signoutFragment);
            ft.add(R.id.mainPane, mainFragment);

            ft.commit();

        } else {
            signoutFragment = (SignoutFragment) fm.findFragmentById(R.id.leftPane);
            mainFragment = (MainFragment) fm.findFragmentById(R.id.mainPane);
        }

        slidePanel.setPanelSlideListener(panelListener);
        slidePanel.setParallaxDistance(250);
    }

    SlidingPaneLayout.PanelSlideListener panelListener = new SlidingPaneLayout.PanelSlideListener() {
        @Override
        public void onPanelSlide(View panel, float slideOffset) {

        }

        @Override
        public void onPanelOpened(View panel) {

        }

        @Override
        public void onPanelClosed(View panel) {

        }
    };

    public void slidePanel() {
        if(slidePanel.isOpen()){
            slidePanel.closePane();
        }
        else{
            slidePanel.openPane();
        }
    }

}
