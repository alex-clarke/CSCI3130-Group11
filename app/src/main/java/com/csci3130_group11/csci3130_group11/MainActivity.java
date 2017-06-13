package com.csci3130_group11.csci3130_group11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /*
    Varibales for buttons
     */
    Button currButton;
    Button histoButton;
    Button settButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Creation of objects in main page
         */

        Util.createDataObjects();

        /*
        Assignes variables to UI's buttons
         */
        currButton = (Button) findViewById(R.id.data_button);
        histoButton =(Button) findViewById(R.id.visualization_button);
        settButton = (Button) findViewById(R.id.setting_button);

        /*
        On click method is called as soon as the View (Buttons) is clicked
         */
        currButton.setOnClickListener(this);
        histoButton.setOnClickListener(this);
        settButton.setOnClickListener(this);

    }

    /**
     * Identifes the click ID and sends user to the selected page.
     * @param v
     */
    public void onClick(View v){

        int i = v.getId();
        Intent nextPage;

        if (i==R.id.data_button){
            nextPage = new Intent(this, DisplayCurrentData.class);
            startActivity(nextPage);
        }
        else if (i== R.id.visualization_button){
            nextPage = new Intent(this, DisplayHistoricalData.class);
            startActivity(nextPage);
        }
        else if (i== R.id.setting_button){
            nextPage = new Intent(this, Settings.class);
            startActivity(nextPage);
        }



    }

}
