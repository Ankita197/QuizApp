package com.ankita.questionanswerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.ankita.questionanswerapp.adapter.ItemAnswerKeyAdapter;
import com.ankita.questionanswerapp.adapter.ItemOptionAdapter;
import com.ankita.questionanswerapp.model.Option;
import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;

import java.util.ArrayList;

public class ShowResultActivity extends AppCompatActivity {

    private TextView tvCorrectAnswer, tvWrongAnswer,tvSkipAnswer, tvShowSecond, tvShowMinute,tvTotalWriteAnswer;
    private String minute, second;
    private int wrongPercentage,writePercentage,skipPercentage;
    private RecyclerView rvItemAnswer;
    private ItemAnswerKeyAdapter itemOptionAdapter;
    private ArrayList<Option> listAnswerKey=new ArrayList<>();
    private ArrayList<String> QuestionList=new ArrayList<>();
    private ArrayList<String> AnswerList=new ArrayList<>();
    private DecoView dynamicArcView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);
        init();
        minute = getIntent().getStringExtra("minute");
        second = getIntent().getStringExtra("second");
        if(minute!=null) {
            setMinute();
        }
        if(second!=null) {
            setSecond();
        }
        setQuestionList();
        setAnswerList();
        setAnswerKeyList();

        itemOptionAdapter=new ItemAnswerKeyAdapter(this,listAnswerKey);
        rvItemAnswer.setAdapter(itemOptionAdapter);
        if(getIntent().getStringExtra("correctAnswer")!=null){
            tvCorrectAnswer.setText(getIntent().getStringExtra("correctAnswer"));
            tvTotalWriteAnswer.setText(getIntent().getStringExtra("correctAnswer"));
            writePercentage=(int)(Integer.parseInt(getIntent().getStringExtra("correctAnswer"))*100/5);
        }
        if(getIntent().getStringExtra("wrongAnswer")!=null){
            tvWrongAnswer.setText(getIntent().getStringExtra("wrongAnswer"));
            wrongPercentage=(int)(Integer.parseInt(getIntent().getStringExtra("wrongAnswer"))*100/5);

        }
        if(getIntent().getStringExtra("skipAnswer")!=null){
            tvSkipAnswer.setText(getIntent().getStringExtra("skipAnswer"));
            skipPercentage=(int)(Integer.parseInt(getIntent().getStringExtra("skipAnswer"))*100/5);

        }

        configSereiceItem();
    }

    private void configSereiceItem() {
        // Create background track
        dynamicArcView.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 100)
                .setInitialVisibility(false)
                .build());

//Create data series track
        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(255, 64, 196, 0))
                .setRange(0, 100, 0)
                .build();
        SeriesItem seriesItem2 = new SeriesItem.Builder(Color.parseColor("#F84911"))
                .setRange(0, 100, 0)
                .build();
        SeriesItem seriesItem3 = new SeriesItem.Builder(Color.parseColor("#FB7448"))
                .setRange(0, 100, 0)
                .build();


        int series1Index = dynamicArcView.addSeries(seriesItem1);
        int series2Index = dynamicArcView.addSeries(seriesItem2);

        dynamicArcView.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                .setDelay(1000)
                .setDuration(2000)
                .build());


        dynamicArcView.addEvent(new DecoEvent.Builder(100).setIndex(series1Index).setDelay(4000).build());
        dynamicArcView.addEvent(new DecoEvent.Builder(100-writePercentage).setIndex(series2Index).setDelay(8000).build());
        if(!tvSkipAnswer.getText().toString().equals("0")){
            int series3Index = dynamicArcView.addSeries(seriesItem3);
            dynamicArcView.addEvent(new DecoEvent.Builder(100-writePercentage-wrongPercentage).setIndex(series3Index).setDelay(12000).build());

        }
    }

    private void setAnswerKeyList() {
        for(int i=0;i<QuestionList.size();i++){
            listAnswerKey.add(new Option(QuestionList.get(i),AnswerList.get(i)));
        }
    }

    private void setQuestionList() {
        QuestionList.add("Q1 A train running at the speed of 60 km/hr crosses a pole in 9 seconds. What is the length of the train?");
        QuestionList.add("Q2 A train 125 m long passes a man, running at 5 km/hr in the same direction in which the train is going, in 10 seconds. The speed of the train is:");
        QuestionList.add("Q3 The length of the bridge, which a train 130 metres long and travelling at 45 km/hr can cross in 30 seconds, is:");
        QuestionList.add("Q4 Two trains running in opposite directions cross a man standing on the platform in 27 seconds and 17 seconds respectively and they cross each other in 23 seconds. The ratio of their speeds is:");
        QuestionList.add("Q5 A train passes a station platform in 36 seconds and a man standing on the platform in 20 seconds. If the speed of the train is 54 km/hr, what is the length of the platform?");
    }
    private void setAnswerList() {
        AnswerList.add("180");
        AnswerList.add("40");
        AnswerList.add("45");
        AnswerList.add("120");
        AnswerList.add("1:3");
    }


    private void setSecond() {
        if (second.length() == 1) {
            tvShowSecond.setText("0" + second);
        } else {
            tvShowSecond.setText(second);
        }
    }

    private void setMinute() {
        if (minute.length() == 1) {
            tvShowMinute.setText("0" + minute);
        } else {
            tvShowMinute.setText(minute);
        }
    }

    private void init() {
        tvCorrectAnswer = findViewById(R.id.tvCorrectAnswer);
        tvTotalWriteAnswer = findViewById(R.id.tvTotalWriteAnswer);
        tvWrongAnswer = findViewById(R.id.tvWrongAnswer);
        tvSkipAnswer = findViewById(R.id.tvSkipAnswer);
        tvShowMinute = findViewById(R.id.tvShowMinute);
        tvShowSecond = findViewById(R.id.tvShowSecond);
        rvItemAnswer = findViewById(R.id.rvItemAnswer);
        dynamicArcView=findViewById(R.id.dynamicArcView);

    }
}
