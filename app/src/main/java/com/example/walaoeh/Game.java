package com.example.walaoeh;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.walaoeh.helper.Const;
import com.example.walaoeh.helper.Pref;
import com.example.walaoeh.helper.QuestionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game extends Activity {
    public static final String TAG = "Game Activity";
    private Button btn_true, btn_false;
    private RelativeLayout layout_left, layout_right;
    private ImageView logic_sign;
    private TextView tvTimer, tvScore, tvQuestionLeft, tvQuestionRight, tvMessage, tvHelp, tvStage;

    private int logicType;
    private CountDownTimer cdTimer;

    private int playerStage;

    private boolean question_left_boolean;
    private boolean question_right_boolean;

    private boolean layout_left_boolean;
    private boolean layout_right_boolean;

    private int score;
    private static final int TOTAL_NUMBER_OF_QUESTIONS =5;
    private int numberOfQuestions=0;

    private Animation messageAnimation;

    private QuestionHandler questionHandler;
    private boolean stopTimer;


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
        tvStage = (TextView)findViewById(R.id.tv_stage);

        tvScore = (TextView)findViewById(R.id.score);



        layout_left.setVisibility(View.INVISIBLE);
        layout_right.setVisibility(View.INVISIBLE);
        logic_sign.setVisibility(View.INVISIBLE);
        tvMessage.setVisibility(View.INVISIBLE);

        playerStage = Pref.getPlayerStage();

        questionHandler = new QuestionHandler();

        resetVariables();



    }
    private void resetVariables(){
        questionHandler.updateStage(playerStage);
        numberOfQuestions = 0;
        tvStage.setText(Const.STAGE_NAME[Pref.getPlayerStage()]);
        tvScore.setText(numberOfQuestions+" / "+TOTAL_NUMBER_OF_QUESTIONS);

        initTimer();
        loadQuestion();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    private void loadQuestion(){
        tvStage.setText(Const.STAGE_NAME[Pref.getPlayerStage()]);
        List<String> questions = questionHandler.getQuestion();
        setLogic();
        setLayoutBoolean();

        tvQuestionLeft.setText(questions.get(0).substring(2));
        tvQuestionRight.setText(questions.get(1).substring(2));

        question_left_boolean = String.valueOf(questions.get(0).charAt(0)).equals("1");
        question_right_boolean = String.valueOf(questions.get(1).charAt(0)).equals("1");

        switch (playerStage){
            case Const.STAGE_ELEMENTARY:
                tvQuestionLeft.setTextColor(getResources().getColor(R.color.black));
                layout_left.setBackgroundColor(getResources().getColor(R.color.white));
                layout_left_boolean = true;

                logic_sign.setVisibility(View.INVISIBLE);
                logicType = Const.LOGIC_TYPE_AND;

                layout_right.setVisibility(View.INVISIBLE);
                layout_right_boolean = true;
                question_right_boolean = true;
                break;
            case Const.STAGE_SECONDARY:
                tvQuestionLeft.setTextColor(getResources().getColor(R.color.white));

                logic_sign.setVisibility(View.INVISIBLE);
                logicType = Const.LOGIC_TYPE_AND;

                layout_right.setVisibility(View.INVISIBLE);
                layout_right_boolean = true;
                question_right_boolean = true;
                break;
            case Const.STAGE_HIGHSCHOOL:
                tvQuestionLeft.setTextColor(getResources().getColor(R.color.black));
                layout_left.setBackgroundColor(getResources().getColor(R.color.white));
                layout_left_boolean = true;

                tvQuestionRight.setTextColor(getResources().getColor(R.color.black));
                layout_right.setBackgroundColor(getResources().getColor(R.color.white));
                layout_right_boolean = true;
                break;
            default:
                tvQuestionLeft.setTextColor(getResources().getColor(R.color.white));
                tvQuestionRight.setTextColor(getResources().getColor(R.color.white));
                break;
        }

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
                if((!(question_left_boolean ^ layout_left_boolean) && !(question_right_boolean ^ layout_right_boolean)) != playerAnswer){
                    endGame();
                    return;
                }
                break;
            case Const.LOGIC_TYPE_OR:
                if((!(question_left_boolean ^ layout_left_boolean) || !(question_right_boolean ^ layout_right_boolean)) != playerAnswer){
                    endGame();
                    return;
                }
                break;

        }
        tvMessage.setText("Correct");
        numberOfQuestions++;
        Animation messageAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.point);
        messageAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(numberOfQuestions==TOTAL_NUMBER_OF_QUESTIONS){
                    winGame();

                }else {
                    loadQuestion();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        tvMessage.setAnimation(messageAnimation);
        tvScore.setText(numberOfQuestions + " / " + TOTAL_NUMBER_OF_QUESTIONS);
    }
    private void winGame(){
        stopTimer=true;
        playerStage++;
        Pref.savePlayerStage(playerStage);
        questionHandler.updateStage(playerStage);

        LayoutInflater mylayout = LayoutInflater.from(Game.this);
        View dialogView = mylayout.inflate(R.layout.activity_end_stage,null);

        TextView stage = (TextView) dialogView.findViewById(R.id.stage);
        stage.setText(Const.STAGE_NAME[Pref.getPlayerStage()]);


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setNegativeButton("Next Level",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    onBackPressed();
                    //resetVariables();


                }
            })
//            .setPositiveButton("Back",new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    onBackPressed();
//                }
//            })
            .setView(dialogView)
            .create();

        AlertDialog test = alert.show();

    }
    private void endGame(){
        stopTimer = true;
        LayoutInflater mylayout = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = mylayout.inflate(R.layout.activity_end,null);

        TextView stage = (TextView) dialogView.findViewById(R.id.stage);
        stage.setText(Const.STAGE_NAME[Pref.getPlayerStage()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
        .setPositiveButton("Replay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                resetVariables();

            }
        })
        .setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                onBackPressed();
            }
        });
        builder.create().show();

    }
    private void initTimer(){
        stopTimer = false;
        cdTimer = new CountDownTimer(11000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(millisUntilFinished/1000-1+"");
                if(stopTimer){
                    this.cancel();
                }
            }
            @Override
            public void onFinish() {
                endGame();
            }
        };
    }


}
