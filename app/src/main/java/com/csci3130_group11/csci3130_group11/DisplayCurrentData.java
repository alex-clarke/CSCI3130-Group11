package com.csci3130_group11.csci3130_group11;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Scanner;

public class DisplayCurrentData extends AppCompatActivity implements View.OnClickListener{

    /*
    Objects representing the data already created.
    */
    Light l ;
    Humidity h ;
    Temperature t ;

    /*
     Firebase objects
     */
    private DatabaseReference firebaseReference;
    private FirebaseDatabase firebaseDBInstance;
    /*
     Scanner for parsing strings from firebase
     */
    Scanner scr;

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

    /**
     * Constructor to create Objects for background process
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_current_data);

        /*
        Firebase connectivity
         */
        firebaseDBInstance = FirebaseDatabase.getInstance();
        firebaseReference = firebaseDBInstance.getReference();

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
        /*
        Sets the saved ranges
         */
        Util.retrieveSavedRanges(getApplicationContext());


        /*
        Data gets displayed
         */

        // Read from the database
        firebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                //Get the data, as a string, from firebase
                String tempStr = dataSnapshot.child("current").child("temp").getValue(String.class);
                //Scanner to parse the string to double
                scr = new Scanner(tempStr);
                scr.useDelimiter("[^\\p{Alnum},\\.-]");
                double tempCurrent = scr.nextDouble();
                //Set current value of the object to be the value from firebase
                t.setCurrent(tempCurrent);

                String humStr = dataSnapshot.child("current").child("hum").getValue(String.class);
                scr = new Scanner(humStr);
                scr.useDelimiter("[^\\p{Alnum},\\.-]");
                double humCurrent = scr.nextDouble();
                h.setCurrent(humCurrent);

                String lightStr = dataSnapshot.child("current").child("light").getValue(String.class);
                scr = new Scanner(lightStr);
                scr.useDelimiter("[^\\p{Alnum},\\.-]");
                double lightCurrent = scr.nextDouble();
                l.setCurrent(lightCurrent);

                displayData(tCurrent, tLow, tHigh, t);
                displayData(hCurrent, hLow, hHigh, h);
                displayData(lCurrent, lLow, lHigh, l);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //TODO Some kind of message display here
            }
        });
    }

    /**
     * Displays the data and verifies that data is within the requested parameters.
     * If not error message is displayes along with a toast.
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

    /**
     *Set the text for the TextViews.
     */
    public void setData(TextView current, TextView low, TextView high, Measurement data){
        current.setText(Double.toString(data.getCurrent()));
        low.setText(data.getUserInputedRangeLower()+ "");
        high.setText(data.getUserInputedRangeUpper()+ "");
    }


    /**
     * Toast for error
     */
    public void toastWarning(){
        Context context = getApplicationContext();
        CharSequence text = "WARNING: SOMETHING IS OUT OF RANGE";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     *After pressing the bottom set Ranges it creates a new activity
     */
    public void onClick(View v){
        Intent intent = new Intent(DisplayCurrentData.this, SetRanges.class);
        startActivity(intent);
    }

    /**
     * Refresh previous activity with updated data
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }



}
