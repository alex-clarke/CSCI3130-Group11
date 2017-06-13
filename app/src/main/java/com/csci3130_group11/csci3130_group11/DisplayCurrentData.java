package com.csci3130_group11.csci3130_group11;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayCurrentData extends AppCompatActivity implements View.OnClickListener{

    /*
        Objects representing the data already created.
         */
    Light l ;
    Humidity h ;
    Temperature t ;

    /*
    Textviews for data display
     */
    TextView tCurrent;
    TextView tLow;
    TextView tHigh;
    TextView hCurrent;
    TextView hLow;
    TextView hHigh;
    TextView lCurrent;
    TextView lLow;
    TextView lHigh;

    /*
    Button to go to setRanges activity
     */
    Button setRangesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_current_data);

        /*
        Obtained the intent that started this activity
         */
        Intent intent = getIntent();

        /*
        Objects representing the data are assigned here.
         */
       // Util.assignObjects(t, h, l);
        /*
        Objects 
         */
        l=Util.getLight();
        h=Util.getHumidity();
        t=Util.getTemperature();
        /*
        TextViews get assigned
         */
        tCurrent = (TextView) findViewById(R.id.CURRENT_T);
        tLow = (TextView) findViewById(R.id.MIN_T_RANGE);
        tHigh = (TextView) findViewById(R.id.MAX_T_RANGE);
        hCurrent = (TextView) findViewById(R.id.CURRENT_H);
        hLow = (TextView) findViewById(R.id.MIN_H_RANGE);
        hHigh = (TextView) findViewById(R.id.MAX_H_RANGE);
        lCurrent = (TextView) findViewById(R.id.CURRENT_L);
        lLow = (TextView) findViewById(R.id.MIN_L_RANGE);
        lHigh = (TextView) findViewById(R.id.MAX_L_RANGE);
        /*
        Button gets assigned and linked to onClick method
         */
        setRangesButton = (Button) findViewById(R.id.change_range_button);
        setRangesButton.setOnClickListener(this);
        toastWarning();
        /*
        Data gets displayed
         */
        displayData(tCurrent, tLow, tHigh, t);
        displayData(hCurrent, hLow, hHigh, h);
        displayData(lCurrent, lLow, lHigh, l);



    }

    /*
        Displays the data and verifies that data is within the requested parameters. If not error
        message is displayes along with a toast.
     */
    public void displayData(TextView current, TextView low, TextView high, Measurement data){

        if(data.checkMeasurement(data.getCurrent())){
            current.setTextColor(Color.GREEN);
            low.setTextColor(Color.GREEN);
            high.setTextColor(Color.GREEN);
            setData(current, low, high, data);
        }
        else{
            current.setTextColor(Color.RED);
            low.setTextColor(Color.RED);
            high.setTextColor(Color.RED);
            setData(current, low, high, data);
            toastWarning();

        }
    }

    /*
    Set the text for the TextViews.
     */
    public void setData(TextView current, TextView low, TextView high, Measurement data){
        current.setText(data.getCurrent() + "");
        low.setText(data.getUserInputedRangeLower()+ "");
        high.setText(data.getUserInputedRangeUpper()+ "");
    }

    /*
    Toast for error
     */
    public void toastWarning(){

        Context context = getApplicationContext();
        CharSequence text = "WARNING: SOMETHING IS OUT OF RANGE";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /*
    After pressing the bottom set Ranges it creates a new activity
     */
    public void onClick(View v){
        int i = v.getId();
        if (i==R.id.change_range_button) {
            Intent intent = new Intent(this, SetRanges.class);
            startActivity(intent);
        }
    }

}
