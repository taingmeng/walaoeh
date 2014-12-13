package com.example.walaoeh;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.walaoeh.helper.Const;
import com.example.walaoeh.helper.Pref;


public class Timeline extends Activity {

    private int stage;
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        Pref.init(this);

        stage = Pref.getPlayerStage();
        level = Pref.getPlayerLevel();

        TableLayout timelineLayout = (TableLayout) findViewById(R.id.activity_timeline_layout);
        for(int stageIndex=0; stageIndex< Const.QUESTIONS[stage].length; stageIndex++){

            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.activity_timeline_stage, row);

            TextView stageTextView = (TextView) row.findViewById(R.id.stageTextView);
            Button stageButton = (Button) row.findViewById(R.id.stageButton);
            View stageLine = row.findViewById(R.id.stageLine);

            stageTextView.setText(Const.STAGE_NAME[stageIndex]);

            if(stageIndex < stage) {
                stageButton.setBackgroundResource(R.drawable.round_button_complete);
                stageButton.setText("100%");
            } else if(stageIndex == stage) {
                stageButton.setBackgroundResource(R.drawable.round_button_progress);
                double percentage = ((double)level/((double)Const.QUESTIONS[stage].length/2))*100;
                stageButton.setText(String.valueOf(level)+"%");
            } else {
                stageButton.setBackgroundResource(R.drawable.round_button_not_arrived);
                stageButton.setText("??");
            }

            if(stageIndex == Const.QUESTIONS[stage].length -1) {
                stageLine.setVisibility(View.GONE);
            } else if(stageIndex < stage) {
                stageLine.setBackgroundColor(getResources().getColor(R.color.stage_completed));
            } else {
                stageLine.setBackgroundColor(getResources().getColor(R.color.stage_not_arrived));
            }

            timelineLayout.addView(row, stageIndex);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timeline, menu);
        return true;
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
