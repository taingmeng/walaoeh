package com.example.walaoeh;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Game extends Activity {
    private Button btn_true, btn_false, btn_help;
    private RelativeLayout layout_left, layout_right;
    private ImageView logic_sign;
    private TextView tvTimer, tvScore;

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

    }



}
