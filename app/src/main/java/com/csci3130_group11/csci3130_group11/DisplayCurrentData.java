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

    /*Firebase Connectivity*/
    private static DatabaseReference firebaseReference;
    private static FirebaseDatabase firebaseDBInstance;

    /*Scanner for parsing database*/
    private static Scanner scr;

    /*SharedPreferences to retrieve saved app data*/
    SharedPreferences prefs;


    /*Flag for multithreading (Listerner method from firebase run on a different thread which is not started from the app
    but from the firebase, hence everytime the data changes it will update,
    since our data is stored on objects we need to integrate data retrieval
     and app saved ranges for proper data displayed, as well as, integration with our toast notification
     without it being fired everytime data is updated
     even when the activity is not active).
     */
    private static boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_current_data);

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

        /*FireBase Connectivity*/
        firebaseDBInstance = FirebaseDatabase.getInstance();
        firebaseReference = firebaseDBInstance.getReference();

        flag = true;

        /**
         * On change method for fatabase. This allows real time data retrieval as soon as data is changed.
         * Hence, user will always see the updated data.
         */
        firebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*Allows retrieval from sharedPreferences as well as saves current data*/
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor savedData = preferences.edit();
                /*Retrieves save ranges and data*/
                Util.retrieveSavedData(getApplicationContext());
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
                savedData.putString("TEMPERATURE_CURRENT", Double.toString(tempCurrent)).commit();

                String humStr = dataSnapshot.child("current").child("hum").getValue(String.class);
                scr = new Scanner(humStr);
                scr.useDelimiter("[^\\p{Alnum},\\.-]");
                double humCurrent = scr.nextDouble();
                h.setCurrent(humCurrent);
                savedData.putString("HUMIDITY_CURRENT", Double.toString(humCurrent)).commit();

                String lightStr = dataSnapshot.child("current").child("light").getValue(String.class);
                scr = new Scanner(lightStr);
                scr.useDelimiter("[^\\p{Alnum},\\.-]");
                double lightCurrent = scr.nextDouble();
                l.setCurrent(lightCurrent);
                savedData.putString("LIGHT_CURRENT", Double.toString(lightCurrent)).commit();
                /*
                Displays the data and activates notifications if user requested them for warning.
                */
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
     * Displays the data and verifies that data is within the requested parameters.~
     * If not error message is displayed.
     * Notification generator was integrated here since this method checks for out of ranges
     * data.
     */
    public void displayData(TextView current, TextView low, TextView high, Measurement data){


        if(data.checkMeasurement(data.getCurrent())){
            current.setTextColor(Color.GREEN);
            low.setTextColor(Color.GREEN);
            high.setTextColor(Color.GREEN);
            setData(current, low, high, data);
        }
        else{
            /*Allows retrieval from sharedPreferences*/
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            /*Notification generator running in the background*/
            if(prefs.getBoolean("WARNING_MSG", false)) {
                /**
                 * See notes in broadcastUpdate class for proper explanation.
                 * Generates notification only when data is out of range.
                 */
                prefs.edit().putBoolean("WARNING_MSG", false).commit();
                /**
                 * Generates notification.
                 */
                NotificationGenerator.NotificationMessage("GREENHOUSE UPDATE",
                        NotificationGenerator.UpdateInformation(getApplicationContext()), getApplicationContext(), DisplayCurrentData.class);
            }

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
     * Toast for error. Only will activate if user is in the activity.
     */
    public void toastWarning(){
        if(flag==true) {
            flag=false;
            Context context = getApplicationContext();
            CharSequence text = "WARNING: SOMETHING IS OUT OF RANGE";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    /**
     *After pressing the bottom set Ranges it creates a new activity
     */
    public void onClick(View v){
        int i = v.getId();
        if (i==R.id.change_range_button) {
            Intent intent = new Intent(this, SetRanges.class);
            startActivity(intent);
        }
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
