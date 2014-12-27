package com.example.walaoeh.mainfragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.easyandroidanimations.library.Animation;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.FadeInAnimation;
import com.easyandroidanimations.library.FadeOutAnimation;
import com.example.walaoeh.R;
import com.example.walaoeh.Timeline;
import com.example.walaoeh.helper.Const;

import java.util.Timer;
import java.util.TimerTask;

public class MainFragment extends Fragment {

    private int runningIndex = 0;
    private Timer timer;
    private TimerTask task;

    private int minutes = 0;

    private int MAXIMUM_TIME = 5 * 1000;

    private Button playButton;
    private TextView dialogTextView;
    private ImageButton menuSlideIcon;

    private SlidePanelButtonPressed slideListener;

    public interface SlidePanelButtonPressed {
      public void slidePanel();
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        playButton = (Button) view.findViewById(R.id.activity_mainPlayButton);
        dialogTextView = (TextView) view.findViewById(R.id.dialogTextView);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Timeline.class);
                startActivity(intent);
            }
        });

        menuSlideIcon = (ImageButton) view.findViewById(R.id.menuSlideIcon);
        menuSlideIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideListener.slidePanel();
            }
        });

        initializeTimer();

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            slideListener = (SlidePanelButtonPressed) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    private void initializeTimer() {

        timer = new Timer();
        task = new TimerTask() {
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

}