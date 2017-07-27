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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Calendar;

public class DisplayHistoricalData extends AppCompatActivity implements View.OnClickListener {

    private LineChart chart;
    private Button btn_day;
    private Button btn_month;
    private Button btn_year;
    private Button btn_light;
    private Button btn_humidity;
    private Button btn_temperature;
    private XAxis xAxis;
    private DatabaseReference firebaseReference;
    private FirebaseDatabase firebaseDBInstance;
    private boolean day = false;
    private boolean month = false;
    private boolean year = false;
    Light l ;
    Humidity h ;
    Temperature t ;
    Scanner scr;
    float[] lightArray = new float[365];
    float[] humidityArray = new float[365];
    float[] temperatureArray = new float[365];
    Calendar currentTime = Calendar.getInstance();
    int currentDay = currentTime.get(Calendar.DAY_OF_YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_historical_data);

        firebaseDBInstance = FirebaseDatabase.getInstance();
        firebaseReference = firebaseDBInstance.getReference();

        btn_day = (Button) findViewById(R.id.btn_day);
        btn_month = (Button) findViewById(R.id.btn_month);
        btn_year = (Button) findViewById(R.id.btn_year);
        btn_light = (Button) findViewById(R.id.btn_light);
        btn_humidity = (Button) findViewById(R.id.btn_humidity);
        btn_temperature = (Button) findViewById(R.id.btn_temperature);
        btn_day.setOnClickListener(this);
        btn_month.setOnClickListener(this);
        btn_year.setOnClickListener(this);
        btn_light.setOnClickListener(this);
        btn_humidity.setOnClickListener(this);
        btn_temperature.setOnClickListener(this);

        chart = (LineChart) findViewById(R.id.linechart);
        chart.setVisibleXRangeMaximum(31);
        XAxis xAxis = chart.getXAxis();
        xAxis .setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setSpaceBetweenLabels(1);

        l=Util.getLight();
        h=Util.getHumidity();
        t=Util.getTemperature();

        // Attach a listener to read the data at our posts reference
        firebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                //Get the data, as a string, from firebase~
                for (int i = 0; i < 365; i++) {
                    try {
                        String tempStr = dataSnapshot.child("history").child(Integer.toString(i)).child("9").child("58").child("0").child("temp").getValue(String.class);
                        String humStr = dataSnapshot.child("history").child(Integer.toString(i)).child("9").child("58").child("0").child("hum").getValue(String.class);
                        String lightStr = dataSnapshot.child("history").child(Integer.toString(i)).child("9").child("58").child("0").child("light").getValue(String.class);

                        //Scanner to parse the string to double
                        scr = new Scanner(tempStr);
                        scr.useDelimiter("[^\\p{Alnum},\\.-]");
                        double tempCurrent = scr.nextDouble();
                        //Set current value of the object to be the value from firebase
                        t.setCurrent(tempCurrent);
                        temperatureArray[i] = (float) t.getCurrent();

                        scr = new Scanner(humStr);
                        scr.useDelimiter("[^\\p{Alnum},\\.-]");
                        double humCurrent = scr.nextDouble();
                        h.setCurrent(humCurrent);
                        humidityArray[i] = (float) t.getCurrent();

                        scr = new Scanner(lightStr);
                        scr.useDelimiter("[^\\p{Alnum},\\.-]");
                        double lightCurrent = scr.nextDouble();
                        l.setCurrent(lightCurrent);
                        lightArray[i] = (float) t.getCurrent();
                    }
                    catch (Exception ex) {
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //TODO Some kind of message display here
            }
        });
    }

    /**method dayLight ~
     * This method sets the data for displaying the light readings for the past
     * seven days.
     * @param count
     */
    private void dayLight(int count) {
        chart.clear();

        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            xValues.add(i + "" );
        }

        // yAxis Data
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 1; i <= count; i++) {
            Entry entry = new Entry(lightArray[currentDay-i], i);
            yValues.add(entry);
        }

        //yAxis
        LineDataSet lineDataSet = new LineDataSet(yValues, "Light");
        lineDataSet.setValueTextSize(10.0f);
        ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet);
        LineData lineData = new LineData(xValues, lineDataSets);
        chart.notifyDataSetChanged();
        chart.setData(lineData);
    }

    /**method dayHumidity
     * This method sets the data for displaying the humidity readings for the past
     * seven days.
     * @param count
     */
    private void dayHumidity(int count) {
        chart.clear();

        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            xValues.add(i + "" );
        }

        // yAxis Data
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 1; i <= count; i++) {
            Entry entry = new Entry(humidityArray[currentDay-i], i);
            yValues.add(entry);
        }

        //yAxis
        LineDataSet lineDataSet = new LineDataSet(yValues, "Humidity");
        lineDataSet.setValueTextSize(10.0f);
        ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet);
        LineData lineData = new LineData(xValues, lineDataSets);
        chart.notifyDataSetChanged();
        chart.setData(lineData);
    }

    /**method dayTemperature
     * This method sets the data for displaying the temperature readings for the past
     * seven days.
     * @param count
     */
    private void dayTemperature(int count) {
        chart.clear();

        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            xValues.add(i + "" );
        }

        // yAxis Data
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 1; i <= count; i++) {
            Entry entry = new Entry(temperatureArray[currentDay-i], i);
            yValues.add(entry);
        }

        //yAxis
        LineDataSet lineDataSet = new LineDataSet(yValues, "Temperature");
        lineDataSet.setValueTextSize(10.0f);
        ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet);
        LineData lineData = new LineData(xValues, lineDataSets);
        chart.notifyDataSetChanged();
        chart.setData(lineData);
    }

    /**method monthLight
     * This method sets the data for displaying the light readings for the past
     * month.
     * @param count
     */
    private void monthLight(int count) {
        chart.clear();

        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            xValues.add(i + "" );
        }

        // yAxis Data
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 1; i <= count; i++) {
            Entry entry = new Entry(lightArray[currentDay-i], i);
            yValues.add(entry);
        }

        //yAxis
        LineDataSet lineDataSet = new LineDataSet(yValues, "Light");
        lineDataSet.setValueTextSize(10.0f);
        ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet);
        LineData lineData = new LineData(xValues, lineDataSets);
        chart.notifyDataSetChanged();
        chart.setData(lineData);
    }

    /**method monthHumidity
     * This method sets the data for displaying the humidity readings for the past
     * month.
     * @param count
     */
    private void monthHumidity(int count) {
        chart.clear();

        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            xValues.add(i + "" );
        }

        // yAxis Data
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 1; i <= count; i++) {
            Entry entry = new Entry(humidityArray[currentDay-i], i);
            yValues.add(entry);
        }

        //yAxis
        LineDataSet lineDataSet = new LineDataSet(yValues, "Humidity");
        lineDataSet.setValueTextSize(10.0f);
        ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet);
        LineData lineData = new LineData(xValues, lineDataSets);
        chart.notifyDataSetChanged();
        chart.setData(lineData);
    }

    /**method monthTemperature
     * This method sets the data for displaying the temperature readings for the past
     * month.
     * @param count
     */
    private void monthTemperature(int count) {
        chart.clear();

        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            xValues.add(i + "" );
        }

        // yAxis Data
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 1; i <= count; i++) {
            Entry entry = new Entry(temperatureArray[currentDay-i], i);
            yValues.add(entry);
        }

        //yAxis
        LineDataSet lineDataSet = new LineDataSet(yValues, "Temperature");
        lineDataSet.setValueTextSize(10.0f);
        ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet);
        LineData lineData = new LineData(xValues, lineDataSets);
        chart.notifyDataSetChanged();
        chart.setData(lineData);
    }

    /**method yearLight
     * This method sets the data for displaying the light readings for the past
     * year.
     * @param count
     */
    private void yearLight(int count) {
        chart.clear();

        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            xValues.add(i + "" );
        }

        // yAxis Data
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 1; i <= count; i++) {
            Entry entry = new Entry(lightArray[i*30], i);
            yValues.add(entry);
        }

        //yAxis
        LineDataSet lineDataSet = new LineDataSet(yValues, "Light");
        lineDataSet.setValueTextSize(10.0f);
        ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet);
        LineData lineData = new LineData(xValues, lineDataSets);
        chart.notifyDataSetChanged();
        chart.setData(lineData);
    }

    /**method yearHumidity
     * This method sets the data for displaying the humidity readings for the past
     * year.
     * @param count
     */
    private void yearHumidity(int count) {
        chart.clear();

        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            xValues.add(i + "" );
        }

        // yAxis Data
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 1; i <= count; i++) {
            Entry entry = new Entry(humidityArray[i*30], i);
            yValues.add(entry);
        }

        //yAxis
        LineDataSet lineDataSet = new LineDataSet(yValues, "Humidity");
        lineDataSet.setValueTextSize(10.0f);
        ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet);
        LineData lineData = new LineData(xValues, lineDataSets);
        chart.notifyDataSetChanged();
        chart.setData(lineData);
    }

    /**method yearTemperature
     * This method sets the data for displaying the temperature readings for the past
     * year.
     * @param count
     */
    private void yearTemperature(int count) {
        chart.clear();

        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            xValues.add(i + "" );
        }

        // yAxis Data
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 1; i <= count; i++) {
            Entry entry = new Entry(temperatureArray[i*30], i);
            yValues.add(entry);
        }

        //yAxis
        LineDataSet lineDataSet = new LineDataSet(yValues, "Temperature");
        lineDataSet.setValueTextSize(10.0f);
        ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet);
        LineData lineData = new LineData(xValues, lineDataSets);
        chart.notifyDataSetChanged();
        chart.setData(lineData);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_day:
                day = true;
                break;
            case R.id.btn_month:
                month = true;
                break;
            case R.id.btn_year:
                year = true;
                break;
        }
        if (day == true && v.getId() == R.id.btn_light) {
            dayLight(7);
            day = false;
        }
        else if (day == true && v.getId() == R.id.btn_humidity) {
            dayHumidity(7);
            day = false;
        }
        else if (day == true && v.getId() == R.id.btn_temperature) {
            dayTemperature(7);
            day = false;
        }
        else if (month == true && v.getId() == R.id.btn_light) {
            monthLight(30);
            month = false;
        }
        else if (month == true && v.getId() == R.id.btn_humidity) {
            monthHumidity(30);
            month = false;
        }
        else if (month == true && v.getId() == R.id.btn_temperature) {
            monthTemperature(30);
            month = false;
        }
        else if (year == true && v.getId() == R.id.btn_light) {
            yearLight(12);
            year = false;
        }
        else if (year == true && v.getId() == R.id.btn_humidity) {
            yearHumidity(12);
            year = false;
        }
        else if (year == true && v.getId() == R.id.btn_temperature) {
            yearTemperature(12);
            year = false;
        }
    }
}