package com.example.walaoeh.helper;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sivly on 12/13/2014.
 */
public class QuestionHandler {

    private final static int MAX_CHOOSE_QUESTIONS = 2;

    private ArrayList<Integer> questionList;
    private int correctCounter;
    private int stage;

    public QuestionHandler() {
        initialize(Const.STAGE_ELEMENTARY);
        createQuestions();
    }

    public QuestionHandler(int stage){
        initialize(stage);
        createQuestions();
    }

    private void createQuestions() {
        for(int i=0; i<Const.QUESTIONS[stage].length; i++)
            questionList.add(i);

        Collections.shuffle(questionList);

    }

    private void initialize() {
        questionList = new ArrayList<Integer>();
        correctCounter = 0;
    }

    private void initialize(int stage) {
        initialize();
        this.stage = stage;
    }

    public void updateStage(int stage){
        correctCounter=0;
        questionList.clear();
        this.stage = stage;
        createQuestions();
    }

    public ArrayList<String> getQuestion() {


        ArrayList<String> questions = new ArrayList<String>();
        int chooseQuestions = 0;

        while(chooseQuestions < MAX_CHOOSE_QUESTIONS) {
            questions.add(Const.QUESTIONS[stage][questionList.get(correctCounter++)]);
            chooseQuestions++;
        }

        return questions;
    }
}
