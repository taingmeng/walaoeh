package com.example.walaoeh;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.walaoeh.helper.Const;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game extends Activity {
    public static final String TAG = "Game Activity";
    private Button btn_true, btn_false;
    private RelativeLayout layout_left, layout_right;
    private ImageView logic_sign;
    private TextView tvTimer, tvScore, tvQuestionLeft, tvQuestionRight, tvMessage, tvHelp;

    private int logicType;
    private CountDownTimer cdTimer;
    private CountDownTimer openingTimer;


    private List<String> questionList;

    private int playerStage;

    private boolean question_left_boolean;
    private boolean question_right_boolean;

    private boolean layout_left_boolean;
    private boolean layout_right_boolean;

    private int score;
    private static final int TOTAL_NUMBER_OF_QUESTIONS =5;
    private int numberOfQuestions=0;

    private Animation messageAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        btn_true = (Button)findViewById(R.id.btn_true);
        btn_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        btn_false = (Button)findViewById(R.id.btn_false);
        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        tvHelp = (TextView)findViewById(R.id.tv_help);
        tvHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        layout_left = (RelativeLayout)findViewById(R.id.layout_left);
        layout_right = (RelativeLayout)findViewById(R.id.layout_right);
        logic_sign = (ImageView)findViewById(R.id.logic_sign);
        tvTimer = (TextView)findViewById(R.id.tv_timer);
        tvQuestionLeft = (TextView)findViewById(R.id.tv_question_left);
        tvQuestionRight = (TextView)findViewById(R.id.tv_question_right);
        tvMessage = (TextView)findViewById(R.id.tv_message);
        tvScore = (TextView)findViewById(R.id.score);
        tvScore.setText(numberOfQuestions+" / "+TOTAL_NUMBER_OF_QUESTIONS);

        layout_left.setVisibility(View.INVISIBLE);
        layout_right.setVisibility(View.INVISIBLE);
        logic_sign.setVisibility(View.INVISIBLE);
        tvMessage.setVisibility(View.INVISIBLE);

        cdTimer = new CountDownTimer(11000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(millisUntilFinished/1000-1+"");
            }

            @Override
            public void onFinish() {
                tvTimer.setText("0");
                loadQuestion();
            }
        };

        playerStage = 0;

        questionList = new ArrayList<String>();
        for (String s: Const.QUESTIONS[playerStage]){
            questionList.add(s);
        }
        loadQuestion();



    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    private void loadQuestion(){
        setLogic();
        setLayoutBoolean();
        int leftQuestion = new Random().nextInt(questionList.size());
        int rightQuestion = new Random().nextInt(questionList.size());
        while(rightQuestion == leftQuestion){
            rightQuestion = new Random().nextInt(questionList.size());
        }
        tvQuestionLeft.setText(questionList.get(leftQuestion).substring(2));
        tvQuestionRight.setText(questionList.get(rightQuestion).substring(2));

        question_left_boolean = questionList.get(leftQuestion).charAt(0) == 1;
        question_right_boolean = questionList.get(rightQuestion).charAt(0) == 1;
        cdTimer.start();

    }
    private void setLogic(){
        logicType = new Random().nextBoolean()?0:1;
        switch (logicType){
            case Const.LOGIC_TYPE_AND:
                logic_sign.setImageResource(R.drawable.logic_and);
                break;
            case Const.LOGIC_TYPE_OR:
                logic_sign.setImageResource(R.drawable.logic_or);
                break;
        }

        logic_sign.setVisibility(View.VISIBLE);
    }
    private void setLayoutBoolean(){

        layout_left_boolean = new Random().nextBoolean();
        layout_right_boolean =  new Random().nextBoolean();

        if(layout_left_boolean){
            layout_left.setBackgroundColor(getResources().getColor(R.color.color_true));
        }else{
            layout_left.setBackgroundColor(getResources().getColor(R.color.color_false));
        }

        if(layout_right_boolean){
            layout_right.setBackgroundColor(getResources().getColor(R.color.color_true));
        }else{
            layout_right.setBackgroundColor(getResources().getColor(R.color.color_false));
        }

        layout_left.setVisibility(View.VISIBLE);
        layout_right.setVisibility(View.VISIBLE);

    }
    private void checkAnswer(boolean playerAnswer){
        switch (logicType){
            case Const.LOGIC_TYPE_AND:
                if(((question_left_boolean | layout_left_boolean) && (question_right_boolean | layout_right_boolean)) == playerAnswer){
                    tvMessage.setText("Correct");
                    Log.d(TAG, "Correct");
                }
                else{
                    tvMessage.setText("Wrong");
                    Log.d(TAG, "Wrong");
                }

                break;
            case Const.LOGIC_TYPE_OR:
                if(((question_left_boolean | layout_left_boolean) || (question_right_boolean | layout_right_boolean)) == playerAnswer){
                    tvMessage.setText("Correct");
                    Log.d(TAG, "Correct");
                }
                else{
                    tvMessage.setText("Wrong");
                    Log.d(TAG, "Wrong");
                }
                break;

        }
        tvMessage.setVisibility(View.INVISIBLE);
        Animation messageAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.point);
        messageAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                loadQuestion();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        tvMessage.setAnimation(messageAnimation);
        tvScore.setText(numberOfQuestions+" / "+TOTAL_NUMBER_OF_QUESTIONS);


    }
}
