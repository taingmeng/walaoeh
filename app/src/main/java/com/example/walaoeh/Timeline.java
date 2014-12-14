package com.example.walaoeh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.walaoeh.helper.Const;
import com.example.walaoeh.helper.Pref;

import java.util.Random;


public class Timeline extends Activity {

    private int stage;
    private int level;

    TableLayout timelineLayout;
    ImageView tomImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        Pref.init(this);

        stage = Pref.getPlayerStage();
        level = Pref.getPlayerLevel();

        createTableLayout();
        tomImage = (ImageView)findViewById(R.id.tomImage);
    }

    private void createTableLayout() {

        timelineLayout = (TableLayout) findViewById(R.id.activity_timeline_layout);
        int numRows = (stage < 9) ? 3 : (int)Math.ceil((float)stage/3);
        int maxDoors = (stage < 9) ? 9 : stage;

        int createdDoors = 0;

        for(int stageIndex=0; stageIndex< numRows; stageIndex++){

            TableRow row= new TableRow(this);

            for(int col=0; col<3; col++) {

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View door = inflater.inflate(R.layout.activity_timeline_stage, null);

                TextView stageTextView = (TextView) door.findViewById(R.id.stageTextView);
                ImageButton doorImageButton = (ImageButton) door.findViewById(R.id.doorImageButton);
                doorImageButton.setTag(createdDoors);
                doorImageButton.setOnClickListener(listener);

                if (createdDoors < stage) {
                    doorImageButton.setBackgroundResource(R.drawable.greendoor_pressed);
                    stageTextView.setText("Stage " + String.valueOf(createdDoors+1));
                } else if (createdDoors == stage) {
                    doorImageButton.setBackgroundResource(R.drawable.bluedoor_pressed);
                    stageTextView.setText("Stage " + String.valueOf(createdDoors+1));
                } else {
                    doorImageButton.setBackgroundResource(R.drawable.doorgrey);
                    doorImageButton.setClickable(false);
                    stageTextView.setText("??");
                }

                row.addView(door);
                createdDoors++;
                if(createdDoors > maxDoors)
                    break;
            }

            timelineLayout.addView(row, stageIndex);
        }
    }

    private OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(((Integer) v.getTag())>=Const.STAGE_NAME.length){
                Toast.makeText(Timeline.this, "This stage is not available yet. Stay tuned.", Toast.LENGTH_SHORT);
                return;
            }
            Intent intent = new Intent(Timeline.this, Game.class);
            intent.putExtra(Const.SELECT_STAGE_KEY, (Integer) v.getTag());
            startActivity(intent);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timeline, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        int stageLevel = Pref.getPlayerStage();
        if(this.stage != stageLevel) {
            this.stage = stageLevel;
            timelineLayout.removeAllViews();
            createTableLayout();
        }
        //To randomly pick Tom image on header when activity is resumed
        int tomInt = new Random().nextInt(3);
        switch (tomInt){
            case 0:
                tomImage.setImageResource(R.drawable.sad1);
                break;
            case 1:
                tomImage.setImageResource(R.drawable.happy);
                break;
            case 2:
                tomImage.setImageResource(R.drawable.neutral);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
