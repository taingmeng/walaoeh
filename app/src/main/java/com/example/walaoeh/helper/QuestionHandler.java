package com.example.walaoeh.helper;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Sivly on 12/13/2014.
 */
public class QuestionHandler {

    private final static int MAX_CHOOSE_QUESTIONS = 2;

    private ArrayList<Integer> answeredList;
    private Random random;
    private int stage;

    public QuestionHandler() {
        initialize(Const.STAGE_ELEMENTARY);
    }

    public QuestionHandler(int stage){
        initialize(stage);
    }

    private void initialize() {
        answeredList = new ArrayList<Integer>();
        random = new Random();
    }

    private void initialize(int stage) {
        initialize();
        this.stage = stage;
    }

    public void updateStage(int stage){
        answeredList.clear();
        this.stage = stage;
    }

    public ArrayList<String> getQuestion() {

        ArrayList<String> questions = new ArrayList<String>();
        int chooseQuestions = 0;

        while(chooseQuestions <= MAX_CHOOSE_QUESTIONS) {
            int questionNumber = random.nextInt(Const.QUESTIONS[stage].length);

            boolean getQuestion = true;

            for(Integer number : answeredList)
                if(questionNumber == number) {
                    getQuestion = false;
                    break;
                }

            if(getQuestion) {
                chooseQuestions++;
                answeredList.add(questionNumber);
                questions.add(Const.QUESTIONS[stage][questionNumber]);
            }
        }

        return questions;
    }
}
