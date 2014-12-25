package com.example.walaoeh;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.walaoeh.helper.Const;
import com.example.walaoeh.helper.Pref;
import com.example.walaoeh.helper.QuestionHandler;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Game extends Activity {
    public static final String TAG = "Game Activity";
    public static final long QUESTION_TIME=10*1000;
    private Button btn_true, btn_false;
    private RelativeLayout layout_left, layout_right;
    private ImageView logic_sign, helpImage;
    private TextView tvTimer, tvScore, tvQuestionLeft, tvQuestionRight, tvMessage, tvStage;
    private ImageButton helpButton;
    private Random random;

    private int logicType;
    private CountDownTimer cdTimer;
    private Timer questionTimer;
    private TimerTask questionTimerTask;
    private long remainingTime;

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
    private boolean isPlaying;
    private boolean exiting;
    private int isFirstTime;

    private AlertDialog pauseAlert;


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

        questionHandler = new QuestionHandler();

        isPlaying = true;

        playerStage = getIntent().getIntExtra(Const.SELECT_STAGE_KEY, Pref.getPlayerStage());

        random = new Random();

        initTimer();
        resetVariables();

        helpButton = (ImageButton) findViewById(R.id.help_button);
        helpImage = (ImageView) findViewById(R.id.helpImage);
        createHelpSession();

        helpImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideHelpSession();
            }
        });
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHelpSession();
            }
        });
    }


    private void resetVariables(){
        questionHandler.updateStage(playerStage);
        numberOfQuestions = 0;
        tvStage.setText(Const.STAGE_NAME[playerStage]);
        tvScore.setText(numberOfQuestions+" / "+TOTAL_NUMBER_OF_QUESTIONS);
        isPlaying = true;

        loadQuestion();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if((pauseAlert!= null && pauseAlert.isShowing()) || !isPlaying){
            isPlaying = true;
        }
        else{
            stopTimer=false;
        }
        isFirstTime = Pref.getPlayerFirstTime();
        if(((isFirstTime & (1 << playerStage)) == 0) && playerStage <= Const.STAGE_HIGHSCHOOL) {
            showHelpSession();
            stopTimer = true;
            isFirstTime = isFirstTime | (1 << playerStage);
            Pref.saveFirstTime(isFirstTime);
        }

    }
    @Override
    protected void onPause() {
        stopTimer = true;
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stoptimertask();
    }

    private void hideHelpSession() {
        stopTimer = false;
        helpImage.setVisibility(View.INVISIBLE);
    }

    private void showHelpSession() {
        helpImage.setVisibility(View.VISIBLE);
    }


    private void loadQuestion(){
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
                layout_left.setBackgroundResource(R.drawable.white_panel_background);
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
                layout_left.setBackgroundResource(R.drawable.white_panel_background);
                layout_left_boolean = true;

                tvQuestionRight.setTextColor(getResources().getColor(R.color.black));
                layout_right.setBackgroundResource(R.drawable.white_panel_background);
                layout_right_boolean = true;
                break;
            default:
                tvQuestionLeft.setTextColor(getResources().getColor(R.color.white));
                tvQuestionRight.setTextColor(getResources().getColor(R.color.white));
                break;
        }
        stopTimer = false;
        remainingTime = QUESTION_TIME;

    }
    private void setLogic(){
        logicType = random.nextBoolean()?0:1;
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

        layout_left_boolean = random.nextBoolean();
        layout_right_boolean =  random.nextBoolean();

        if(layout_left_boolean){
            layout_left.setBackgroundResource(R.drawable.green_panel_background);
        }else{
            layout_left.setBackgroundResource(R.drawable.red_panel_background);
        }

        if(layout_right_boolean){
            layout_right.setBackgroundResource(R.drawable.green_panel_background);
        }else{
            layout_right.setBackgroundResource(R.drawable.red_panel_background);
        }

        layout_left.setVisibility(View.VISIBLE);
        layout_right.setVisibility(View.VISIBLE);

    }

    private void createHelpSession() {

        helpImage.setBackground(null);

        switch (playerStage){
            case Const.STAGE_ELEMENTARY:
                helpImage.setBackgroundResource(R.drawable.help1);
                break;
            case Const.STAGE_SECONDARY:
                helpImage.setBackgroundResource(R.drawable.help2);
                break;
            case Const.STAGE_HIGHSCHOOL:
                helpImage.setBackgroundResource(R.drawable.help3);
                break;
            default:
                helpImage.setBackgroundResource(R.drawable.help);
                break;
        }

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

        numberOfQuestions++;
        switch (numberOfQuestions){
            case 1:
                tvMessage.setText("Correct");
                break;
            case 2:
                tvMessage.setText("Awesome");
                break;
            case 3:
                tvMessage.setText("Excellent");
                break;
            case 4:
                tvMessage.setText("Almost There");
                break;
            case 5:
                tvMessage.setText("Congratulation");
                break;
        }

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
        stopTimer = true;
        isPlaying = false;

        //questionHandler.updateStage(playerStage);

        LayoutInflater mylayout = LayoutInflater.from(Game.this);
        View dialogView = mylayout.inflate(R.layout.activity_end_stage, null);

        TextView stage = (TextView) dialogView.findViewById(R.id.stage);
        stage.setText(Const.STAGE_NAME[playerStage]);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false)
                .setPositiveButton("Next Level", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dialog.dismiss();
            }
        })
            .setView(dialogView)
            .show();

        if(playerStage == Pref.getPlayerStage()) {
            playerStage++;
            Pref.savePlayerStage(playerStage);
        }

    }
    private void endGame(){
        stopTimer = true;
        isPlaying = false;

        LayoutInflater mylayout = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = mylayout.inflate(R.layout.activity_end,null);

        TextView stage = (TextView) dialogView.findViewById(R.id.stage);
        stage.setText(Const.STAGE_NAME[playerStage]);

        AlertDialog.Builder builder = (new AlertDialog.Builder(this));
        builder.setCancelable(false)
                .setView(dialogView)
        .setPositiveButton("Replay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                resetVariables();
                dialog.dismiss();

            }
        })
        .setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();
                dialog.dismiss();
            }
        })
        .show();

    }

    private void initTimer(){
        remainingTime = QUESTION_TIME;

        questionTimer = new Timer();
        questionTimerTask = new TimerTask() {
            @Override
            public void run() {
                if(!stopTimer) {
                    tvTimer.post(new Runnable() {
                        @Override
                        public void run() {
                            tvTimer.setText(remainingTime / 1000 + "");
                            if (remainingTime == 0) {
                                endGame();
                            }
                            remainingTime -= 1000;
                        }
                    });



                }
            }
        };
        questionTimer.scheduleAtFixedRate(questionTimerTask, 0, 1000);


//        stopTimer = false;
//        cdTimer = new CountDownTimer(11000,1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                tvTimer.setText(millisUntilFinished/1000-1+"");
//                if(stopTimer){
//                    this.cancel();
//                }
//            }
//            @Override
//            public void onFinish() {
//                endGame();
//            }
//        };
    }
    public void stoptimertask() {
        if (questionTimer != null) {
            questionTimer.cancel();
            questionTimer = null;
        }
    }

    @Override
    public void onBackPressed() {
        showPauseAlert();

    }
    private void showPauseAlert(){
        stopTimer = true;
        if(pauseAlert == null){
            LayoutInflater mylayout = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View dialogView = mylayout.inflate(R.layout.dialog_pause,null);

            AlertDialog.Builder builder = (new AlertDialog.Builder(this));
            builder.setView(dialogView)
                    .setPositiveButton("Resume", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            stopTimer=false;
                            dialog.dismiss();

                        }
                    })
                    .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            exiting = true;
                            dialog.dismiss();
                            finish();
                        }
                    });
            pauseAlert = builder.create();


        }
        if(!pauseAlert.isShowing()){
            pauseAlert.show();
        }


    }
}
