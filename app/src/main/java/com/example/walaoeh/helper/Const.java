package com.example.walaoeh.helper;

/**
 * Created by taingmeng on 5/11/14.
 */
public class Const {
    public static final int PLAYER_NONE = 0;

    public static final String STAGE_KEY = "com.example.walaoeh.const.stage_key";
    public static final String LEVEL_KEY = "com.example.walaoeh.const.stage_level";


    public static final String SELECT_STAGE_KEY = "com.example.walaoeh.const.selected_stage_key";

    public static final String FIRST_TIME_KEY = "com.example.walaoeh.const.first_time_key";

    public static final int STAGE_ELEMENTARY = 0;
    public static final int STAGE_SECONDARY = 1;
    public static final int STAGE_HIGHSCHOOL = 2;
    public static final int STAGE_COLLEGE = 3;
    public static final int STAGE_WORK = 4;

    public static final String[][] QUESTIONS = {
        {"1:1+1=2","0:0>2","1:0*8<0+1","1:6+8=14","1:21=7*3","1:1+2=3","1:1-1=0","0:1/0=0","0:1+1=11","0:1*1=2","0:1+3-4=6","0:8+21-4*2=12","0:15+9=26","1:We are the world","0:We don’t need to wash hands before eating" },
        {
            "0:-20 is more than 15",
            "0:2^2=2",
            "1:3*4*5=60",
            "0:1.0-0.1=0.0",
            "0:u=0 and 3-y=0 then u=3",
            "0:5+x=2 so x=3",
            "0:1+2+3+4+5=10",
            "0:2^2^2=8",
            "1:199-99=100",
            "0:Pi = 3.1451…",
            "1:sqrt(2) = 1.414...",
            "0:sqrt(-1) = infinity",
            "1:1-1+1-1=0",
            "1:4*5=20",
            "0:sin(0)=1",
            "1:cos(0)=1",
            "1:arctan(infinity) = 90 degrees",
            "0:arccot(infinity) = 90 degrees"
        },

        {
            "1:111^2=12321",
            "1:n! > 2^n",
            "0:1 apple for $3, 3 apples for $10, so I should buy 3 apples.",
            "1:2000 is a leap year",
            "0:Ca is Carbon",
            "1:One Mole is 6.022*10^23",
            "0:Speed of light is 3*10^8 km/s",
            "1:Earth’s escape velocity is 11.2 km/s",
            "0:Static electric field produces electromagnetic field",
            "1:Electromagnetic field produces an electric field",
            "0:Capacitance is opposition to the passage of an electric current through a conductor",
            "1:Lightning is faster than thunder",
            "0:The sum from 1 to n is n(n-1)/2",
            "0:Number of ways to choose 3 out n items is n(n-1)/2",
            "1:The sum of infinite geometric series with ratio r less than 1 is 1/(1-r)",
            "1:Derivate of f(x) = 1/x with respect to x is \n-1/x^2",
            "1:True && False == False",
            "0:Love is more important than study"
        },

        {
            "1:Love is more important than study.",
            "1:Pluto is in Solar system.",
            "0:Dota 2 can be played on phone.",
            "1:Love is in Maslow's hierarchy of needs.",
            "0:Computer Engineering is about playing games",
            "0:Static electric field produces electromagnetic field",
            "1:Electromagnetic field produces an electric field",
            "0:Capacitance is opposition to the passage of an electric current through a conductor",
            "1:The ultimate meaning of life is 42",
            "0:Sampling signals can always be reconstructed",
            "1:The best time complexity of a sorting algorithm is O(n*logn)",
            "0:There’s no need to sleep at night",
            "0:Cryptography is about hacking people’s privacy."
        },

        {
            "0:Bill Gates is the founder of Apple",
            "0:Taylor Swift is the author of Romeo and Juliet",
            "0:No money, go gambling.",
            "0:Customers are always right",
            "1:DBS's Game Jam is awesome",
            "1:This game is awesome! :D (thank you)",
            "0:Get married first and start your career later",
            "0:You can always start up a business without a business plan",
            "1:You should frequently check and reply to emails",
            "1:Do not respect a boss. Respect a leader",
            "0:You can always send your marketing sms to any number. (Hint: DNC)"
        }

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

    public static final String[] MAIN_MESSAGES = {
        "Hi there! I am TOM.\nMy life is full of crisis\n (T_T)",
        "HELP ME !!!\n\\(^o^)/",
        "Walao eh!\n My life is in crisis!",
        "Life is full of choices.\n Wrong ones lead to crisis.",
        "Bad decision\n makes good come-back.",
        "As we grow up,\n choices become\n more complicated.",
        "The harder the crisis,\n the wiser you are.",
        "Depending \non environment,\n a choice could be right or wrong.",
        "Keep calm \nand rock on.\n",
        "Life is short,\n so live it."
    };
}
