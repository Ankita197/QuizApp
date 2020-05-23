package com.ankita.questionanswerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ankita.questionanswerapp.adapter.ItemOptionAdapter;
import com.ankita.questionanswerapp.adapter.OnItemClick;
import com.ankita.questionanswerapp.model.Option;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private TextView tvTime, tvQuestionNumber, tvQuestionDescription;
    private int min = 0, second = 0, i = 0, answer = 0, correctAnswer = 0,asciChar=64;
    private char asciDigit;
    public static HashMap<Integer, Integer> mapQuestionAnswer = new HashMap<>();
    private boolean setSubmit = false;
    private ArrayList<Option> optionArrayList;
    private Button btnPrevious, btnNext;
    private String miniut,Second;
    private RecyclerView rvItemOption;
    private ItemOptionAdapter itemOptionAdapter;
    private BaseActivity baseActivity=new BaseActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        baseActivity.setFinalAnswer();
        baseActivity.addQuestionInList();
        baseActivity.addOptions();
        setTimer();
        tvQuestionDescription.setText(baseActivity.getQuestion(0));
        setAdapter();
        initMap();
        btnNext.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        itemOptionAdapter.setClick(new OnItemClick() {
            @Override
            public void OnClick(ItemOptionAdapter.ViewHolder holder, int position) {
                answer = position;
                mapQuestionAnswer.put(i, answer);
                itemOptionAdapter.selectedItem();
                //TODO
                btnNext.setEnabled(true);
            }
        });
    }

    private void initMap() {
        for (int i = 0; i < baseActivity.getFinalQuestionListSize(); i++) {
            mapQuestionAnswer.put(i, -1);
        }
    }




    private void setAdapter() {
        if (baseActivity.getoptionListSize() > 0) {
            setAnswerList(baseActivity.getOptions(0));
            itemOptionAdapter.setList(optionArrayList);
            rvItemOption.setAdapter(itemOptionAdapter);
        } else {
            Toast.makeText(this, "list is empty", Toast.LENGTH_LONG).show();
        }

    }

    private void setAnswerList(ArrayList<String> options) {
        optionArrayList.clear();
        for (int i = 0; i < options.size(); i++) {
            asciDigit= (char) ((char) asciChar+i+1);
            optionArrayList.add(new Option("Option " + asciDigit, options.get(i)));
        }

    }




    //set simple timer
    private void setTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                second += 1;
                if (second == 60) {
                    second = 0;
                    min += 1;
                }
                miniut=String.valueOf(min);
                Second=String.valueOf(second);
                setTimeInLabel();
                setTimer();
            }
        }, 1000);
    }

    private void setTimeInLabel() {
        String timeLabel="Time:  ";
        if(miniut.length()==1&&Second.length()==1) {
           timeLabel= timeLabel+"0"+miniut+" : "+"0"+Second;
        }
        else if(miniut.length()==1&&Second.length()==2){
            timeLabel= timeLabel+"0"+miniut+" : "+Second;
        }
        else if(miniut.length()==2&&Second.length()==1){
            timeLabel= timeLabel+miniut+" : "+"0"+Second;
        }
        else {
            timeLabel= timeLabel+miniut+" : "+Second;
        }
        tvTime.setText(timeLabel);
    }


    private void init() {
        tvTime = findViewById(R.id.tvTime);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvQuestionDescription = findViewById(R.id.tvQuestionDescription);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        rvItemOption = findViewById(R.id.rvItemOption);
        itemOptionAdapter = new ItemOptionAdapter(this);
        optionArrayList = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                if (i == 0) {
                    btnPrevious.setVisibility(View.VISIBLE);
                }
                if (i < baseActivity.getFinalQuestionListSize() - 1) {
                    Log.d("___@___", "i is" + i);
                    mapQuestionAnswer.put(i, mapQuestionAnswer.get(i));
                    getNextQuestionData();

                }
                if (setSubmit) {
                    showResult();
                }
                if (i ==baseActivity.getFinalQuestionListSize() - 1) {
                    btnNext.setText("Submit");
                    mapQuestionAnswer.put(i, mapQuestionAnswer.get(i));
                    setSubmit = true;
                }


                break;
            case R.id.btnPrevious:
                if (i == 1) {
                    btnPrevious.setVisibility(View.GONE);
                }
                if (btnNext.getText().toString().equals("Submit")) {
                    btnNext.setText("Next");
                    setSubmit = false;
                }
                Log.d("___@___", "i is" + i);
                getPreviousQuestion();
                break;
        }
    }

    private void showResult() {
        for (Map.Entry m : mapQuestionAnswer.entrySet()) {
            Log.d("activity is", m.getKey() + " " + m.getValue());
        }
        for (int i = 0; i < baseActivity.getFinalQuestionListSize(); i++) {
            if (mapQuestionAnswer.get(i) == baseActivity.getFinalAnswer(i)) {
                correctAnswer += 1;
            }
        }
        Intent intent = new Intent(MainActivity.this, ShowResultActivity.class);
        intent.putExtra("minute", String.valueOf(min));
        intent.putExtra("second", String.valueOf(second));
        intent.putExtra("correctAnswer", String.valueOf(correctAnswer));
        intent.putExtra("wrongAnswer", String.valueOf(baseActivity.getFinalQuestionListSize() - correctAnswer));
        startActivity(intent);
        finish();

    }

    private void getPreviousQuestion() {
        mapQuestionAnswer.put(i, mapQuestionAnswer.get(i));
        i -= 1;
        tvQuestionNumber.setText(String.valueOf(i + 1));
        tvQuestionDescription.setText(baseActivity.getQuestion(i));
        setAnswerList(baseActivity.getOptions(i));
        itemOptionAdapter.setSelectedPos(mapQuestionAnswer.get(i));
        printLogMessage(mapQuestionAnswer.get(i) + "in previous");
        itemOptionAdapter.notifyDataSetChanged();
    }

    private void getNextQuestionData() {
        printLogMessage(mapQuestionAnswer.get(i) + "in next before pos");
        i += 1;
        tvQuestionNumber.setText(String.valueOf(i + 1));
        tvQuestionDescription.setText(baseActivity.getQuestion(i));
        setAnswerList(baseActivity.getOptions(i));
        itemOptionAdapter.setSelectedPos(mapQuestionAnswer.get(i));
        Log.d("___@___", mapQuestionAnswer.get(i) + "in next after pos");
        itemOptionAdapter.notifyDataSetChanged();


    }

    private void printLogMessage(String message) {
        Log.d("___@___", message);

    }


}
