package com.ankita.questionanswerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {
    private ArrayList<String> listOfQuestions=new ArrayList<>();
    private ArrayList<ArrayList<String>> optionsList=new ArrayList<>();
    private ArrayList<Integer> finalAnswer=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setFinalAnswer();
        addQuestionInList();
        addOptions();
    }
    public void setFinalAnswer() {
        finalAnswer.add(0);
        finalAnswer.add(1);
        finalAnswer.add(2);
        finalAnswer.add(1);
        finalAnswer.add(1);
    }
    public String getQuestion(int index){
        return  listOfQuestions.get(index);

    }
    public ArrayList<String> getOptions(int index){
        return  optionsList.get(index);

    }
    public int getFinalAnswer(int index){
        return  finalAnswer.get(index);

    }
    public int getoptionListSize(){
        return  optionsList.size();

    }
    public int getTotalSize(){
        return  finalAnswer.size();

    }
    public int getFinalQuestionListSize(){
        return  listOfQuestions.size();

    }

    public void addOptions() {
        ArrayList<String> qu1Option = new ArrayList<>();
        qu1Option.add("40");
        qu1Option.add("44");
        ArrayList<String> qu2Option = new ArrayList<>();
        qu2Option.add("40");
        qu2Option.add("44");
        qu2Option.add("50");
        ArrayList<String> qu3Option = new ArrayList<>();
        qu3Option.add("40");
        qu3Option.add("44");
        qu3Option.add("50");
        qu3Option.add("180");
        ArrayList<String> qu4Option = new ArrayList<>();
        qu4Option.add("40");
        qu4Option.add("44");
        qu4Option.add("50");
        qu4Option.add("180");
        ArrayList<String> qu5Option = new ArrayList<>();
        qu5Option.add("40");
        qu5Option.add("44");
        qu5Option.add("50");
        qu5Option.add("180");
        optionsList.add(qu1Option);
        optionsList.add(qu2Option);
        optionsList.add(qu3Option);
        optionsList.add(qu4Option);
        optionsList.add(qu5Option);

    }
    public void addQuestionInList() {
        listOfQuestions.add("Q1 A train running at the speed of 60 km/hr crosses a pole in 9 seconds. What is the length of the train?");
        listOfQuestions.add("Q2 A train 125 m long passes a man, running at 5 km/hr in the same direction in which the train is going, in 10 seconds. The speed of the train is:");
        listOfQuestions.add("Q3 The length of the bridge, which a train 130 metres long and travelling at 45 km/hr can cross in 30 seconds, is:");
        listOfQuestions.add("Q4 Two trains running in opposite directions cross a man standing on the platform in 27 seconds and 17 seconds respectively and they cross each other in 23 seconds. The ratio of their speeds is:");
        listOfQuestions.add("Q5 A train passes a station platform in 36 seconds and a man standing on the platform in 20 seconds. If the speed of the train is 54 km/hr, what is the length of the platform?");
    }

    public ArrayList<String> getListOfQuestions() {
        return listOfQuestions;
    }

    public void setListOfQuestions(ArrayList<String> listOfQuestions) {
        this.listOfQuestions = listOfQuestions;
    }

    public ArrayList<ArrayList<String>> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(ArrayList<ArrayList<String>> optionsList) {
        this.optionsList = optionsList;
    }

    public ArrayList<Integer> getFinalAnswer() {
        return finalAnswer;
    }

    public void setFinalAnswer(ArrayList<Integer> finalAnswer) {
        this.finalAnswer = finalAnswer;
    }
}
