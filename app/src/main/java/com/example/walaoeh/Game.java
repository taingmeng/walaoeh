package com.example.walaoeh;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.walaoeh.helper.Const;

import java.util.Random;


public class Game extends Activity {
    private Button btn_true, btn_false, btn_help;
    private RelativeLayout layout_left, layout_right;
    private ImageView logic_sign;
    private TextView tvTimer, tvScore;

    private int logicType;
    private CountDownTimer cdTimer;

    private int quesitonNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        btn_true = (Button)findViewById(R.id.btn_true);
        btn_false = (Button)findViewById(R.id.btn_false);
        btn_help = (Button)findViewById(R.id.btn_help);

        layout_left = (RelativeLayout)findViewById(R.id.layout_left);
        layout_right = (RelativeLayout)findViewById(R.id.layout_right);

        logic_sign = (ImageView)findViewById(R.id.logic_sign);
        tvTimer = (TextView)findViewById(R.id.tv_timer);

        cdTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(millisUntilFinished/1000+"");
            }

            @Override
            public void onFinish() {
                loadQuesiton();
            }
        };


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadQuesiton();
    }
    private void loadQuesiton(){
        setLogic();

        cdTimer.start();

    }
    private void setLogic(){
        int logicType = new Random().nextInt(1);
        switch (logicType){
            case Const.LOGIC_TYPE_AND:
                logic_sign.setImageResource(R.drawable.logic_and);
                break;
            case Const.LOGIC_TYPE_OR:
                logic_sign.setImageResource(R.drawable.logic_or);
                break;

        }
    }
    private void checkAnswer(){

    }
}
