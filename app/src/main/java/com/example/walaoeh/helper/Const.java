package com.example.walaoeh.helper;

/**
 * Created by taingmeng on 5/11/14.
 */
public class Const {
    public static final int PLAYER_NONE = 0;

    public static final String STAGE_KEY = "com.example.walaoeh.const.stage_key";
    public static final String LEVEL_KEY = "com.example.walaoeh.const.stage_level";

    public static final String SELECT_STAGE_KEY = "com.example.walaoeh.const.selected_stage_key";

    public static final int STAGE_ELEMENTARY = 0;
    public static final int STAGE_SECONDARY = 1;
    public static final int STAGE_HIGHSCHOOL = 2;
    public static final int STAGE_COLLEGE = 3;
    public static final int STAGE_WORK = 4;

    public static final String[][] QUESTIONS = {
            {"1:1+1=2", "0:0>2", "1:6+8=14", "1:4+8=12", "1:21=7*3","1:1+2=3","1:1-1=0","0:1+1=11","0:1*1=2","0:1+3-4=6"},
            {"0:-20 is more than 15", "0:2^2=2", "0:5+x=2 so x=3", "0:1+2+3+4+5=10", "1:2^2^2=8","1:199-99=100","1:1-1+1-1=0","1:4*5=20","0:sin(0)=1","1:cos(0)=1"},
            {"1:111^2=112211", "1:3*4*5=60", "0:1.0-0.1=0.0", "0:1 apple for $3, 3 apples for $10, so I should buy 3 apples.",
                "2000 is a leap year","1:sqrt(2) = 1.414...","0:Ca is Carbon", "1:Lightning is faster than thunder","1:True && False == False", "0:Love is more important than study"},
            {"1:1+1=2", "0:0>2", "1:6+8=14", "1:4+8=12", "1:21=7*3","1:1+2=3","1:1-1=0","0:1+1=11","0:1*1=2","0:1+3-4=6"},
            {"1:1+1=2", "0:0>2", "1:6+8=14", "1:4+8=12", "1:21=7*3","1:1+2=3","1:1-1=0","0:1+1=11","0:1*1=2","0:1+3-4=6"}
    };

    public static final String[] STAGE_NAME = {
            "Stage 1: Elementary",
            "Stage 2: Secondary",
            "Stage 3: High School",
            "Stage 4: College",
            "Stage 5: Work"
    };

    public static final int LOGIC_TYPE_AND = 0;
    public static final int LOGIC_TYPE_OR = 1;

    public static final String STOP_TIMER_STATE = "com.example.walaoeh.const.timer_state";
}
