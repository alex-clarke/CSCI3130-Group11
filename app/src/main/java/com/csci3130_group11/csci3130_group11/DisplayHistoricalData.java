package com.csci3130_group11.csci3130_group11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class DisplayHistoricalData extends AppCompatActivity implements View.OnClickListener {

    private LineChart chart;
    private Button btn_day;
    private Button btn_month;
    private Button btn_year;
    private XAxis xAxis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_historical_data);


        btn_day = (Button) findViewById(R.id.btn_day);
        btn_month = (Button) findViewById(R.id.btn_month);
        btn_year = (Button) findViewById(R.id.btn_year);
        btn_day.setOnClickListener(this);
        btn_month.setOnClickListener(this);
        btn_year.setOnClickListener(this);


        chart = (LineChart) findViewById(R.id.linechart);
        chart.setVisibleXRangeMaximum(31);
        XAxis xAxis = chart.getXAxis();
        xAxis .setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setLabelsToSkip(1);
        xAxis.setSpaceBetweenLabels(1);




        setData(7);

    }

    private void setData(int count) {
        chart.clear();
        Date date = new Date();
        int month = date.getMonth()+1;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("current");

        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            xValues.add(i + "" );
        }

        // yAxis Data
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 1; i <= count; i++) {
            float val = (float) (Math.random() * 165) - 40;
            Entry entry = new Entry(val, i);
            yValues.add(entry);
        }

        //yAxis
        LineDataSet lineDataSet = new LineDataSet(yValues, "Temperature");
        lineDataSet.setValueTextSize(10.0f);
//        lineDataSet.setCircleColor(Color.BLACK);
//        lineDataSet.setColor(Color.BLACK);

        ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet);

//        lineDataSet.setValueTextSize(10.0f);


        LineData lineData = new LineData(xValues, lineDataSets);

        chart.notifyDataSetChanged();
        chart.setData(lineData);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_day:
                setData(7);
                break;
            case R.id.btn_month:
                setData(30);
                break;
            case R.id.btn_year:
                setData(12);
                break;
        }
    }
}

