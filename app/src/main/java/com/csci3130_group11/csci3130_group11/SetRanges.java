package com.csci3130_group11.csci3130_group11;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetRanges extends AppCompatActivity implements View.OnClickListener {


   /*
        Creates variables
   */
    private EditText humidityMax;
    private EditText humidityMin;
    private EditText tempMax;
    private EditText tempMin;
    private EditText lightMax;
    private EditText lightMin;

    private TextView humidity;
    private TextView temperature;
    private TextView light;

    private Button clear;
    private Button increase;
    private Button decrease;
    private Button reset;
    private Button saveChanges;

    /*
    Creates data objects
     */
    Temperature t;
    Light l;
    Humidity h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ranges);
       /*
        gets intent from parent activity. This is not required
         */

        Intent intent = getIntent();

        /*
        Assigns objects
         */
        Util.assignObjects(t, h, l);

        /*
        assignes EditTexts to variavles
         */
        humidityMax = (EditText) findViewById(R.id.humidity_max);
        humidityMin = (EditText) findViewById(R.id.humidity_min);
        tempMax = (EditText) findViewById(R.id.temp_max);
        tempMin = (EditText) findViewById(R.id.temp_min);
        lightMax = (EditText) findViewById(R.id.light_max);
        lightMin = (EditText) findViewById(R.id.light_min);

        temperature = (TextView) findViewById(R.id.text_temp);
        humidity = (TextView) findViewById(R.id.text_humidity);
        light = (TextView) findViewById((R.id.text_light));

        /*
        Assignes Buttons to variables
         */
        clear = (Button) findViewById(R.id.clear);
        increase = (Button) findViewById(R.id.increase);
        decrease = (Button) findViewById(R.id.decrease);
        saveChanges = (Button) findViewById(R.id.setChanges);
        reset = (Button) findViewById(R.id.reset);

        /**
         * sets initial values.
         */
        setInitialValues();

        /*
        After clikcing on the buttoms they will use the onClikc Method
         */
        clear.setOnClickListener(this);
        increase.setOnClickListener(this);
        decrease.setOnClickListener(this);
        reset.setOnClickListener(this);
        saveChanges.setOnClickListener(this);


        /**
         * Checks for input as user enters it.
         */
        humidityMax.addTextChangedListener(generalTextWatcher);
        humidityMin.addTextChangedListener(generalTextWatcher);
        tempMax.addTextChangedListener(generalTextWatcher);
        tempMin.addTextChangedListener(generalTextWatcher);
        lightMax.addTextChangedListener(generalTextWatcher);
        lightMin.addTextChangedListener(generalTextWatcher);

    }

    /**
     * TextWatcher to check for EditText being modified.
     * Each EditText has its own Spannable. TextWatcher's events has this Spannable as s parameter.
     * I check their hashCode (unique Id of each object). myEditText1.getText() returns that Spannable.
     * So if the myEditText1.getText().hashCode() equals with s.hashCode() it means that s belongs
     * to myEditText1.
     *
     * Solution Found: https://stackoverflow.com/questions/4283062/textwatcher-for-more-than-one-edittext
     */
    private TextWatcher generalTextWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            //NO NEED FOR THIS

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            //NO NEED FOR THIS

        }

        @Override
        /**
         * If the string modified equals to one of the sets (max or min data) it will check for
         * user input. Empty strings are not allowed since it will make the app crash
         */
        public void afterTextChanged(Editable s) {
            if (humidityMax.getText().hashCode() == s.hashCode()||humidityMin.getText().hashCode() == s.hashCode())
            {
                isRangeEmpty(humidityMax, h.getDeviceRangeUpper());
                isRangeEmpty(humidityMin, h.getDeviceLowerRange());
                checkRange(humidityMax, humidityMin, h,true);

            }
            else if (tempMax.getText().hashCode() == s.hashCode()||tempMin.getText().hashCode() == s.hashCode())
            {
                isRangeEmpty(tempMax, t.getDeviceRangeUpper());
                isRangeEmpty(tempMin,t.getDeviceLowerRange());
                checkRange(tempMax, tempMin,t ,true);
            }
            else if (lightMax.getText().hashCode() == s.hashCode()||lightMin.getText().hashCode() == s.hashCode())
            {
                isRangeEmpty(lightMax, l.getDeviceRangeUpper());
                isRangeEmpty(lightMin,l.getDeviceLowerRange());
                checkRange(lightMax, lightMin,l ,true);
            }
        }
    };

    /**
     * checks if the user's input meets the app requirements. max>min, min>=lower limit and
     * max<= upper limit
     * @param max, EditText containing the max value
     * @param min, EditText containing the min value
     * @param type, it can be light, temperature or humidity
     * @param warning, if true it will execute a toast warning if requirements are not met
     * @return true if requirements met
     */
    public boolean checkRange( EditText max, EditText min, measurement type,boolean warning){
        double maxValue = Double.parseDouble(max.getText().toString());
        double minValue = Double.parseDouble(min.getText().toString());
        double upRange = type.getDeviceRangeUpper();
        double lowRange = type.getDeviceRangeLower();
        String msg="";
        /*
        modifies the warning String as rules are not met.
         */
        if (minValue>=maxValue){msg = "The MIN value must be lower than the MAX value \n";}
        if (minValue<lowRange){msg = msg +"The MIN value must be above the limit: "+ lowRange+ "\n";}
        if (maxValue>upRange){msg = msg+ "The MAX value must be below the limit: "+ upRange+ "\n";}
        if (minValue<maxValue&&minValue>=lowRange&&maxValue<=upRange){
            max.setTextColor(Color.GREEN);
            min.setTextColor(Color.GREEN);
            return true;
        }
        else {if(warning){warningMessage(msg);}
            max.setTextColor(Color.RED);
            min.setTextColor(Color.RED);
            return false;}
    }

    /**
     * checks all sets and does not return warning message
     * @return true if all set met conditions
     */
    public boolean checkAllRanges(){
        return (checkRange( humidityMax, humidityMin, h ,false)&&
                checkRange( tempMax, tempMin, t ,false)&&
                checkRange(lightMax, lightMin, l ,false));
    }

    /**
     * prevents an empty string in the EditTexts
     * @param range EditText containing the string
     * @param num, it will be use for replacement
     */
    public void isRangeEmpty( EditText range, double num){
        if (range.getText().toString().trim().isEmpty()){
            range.setText(num+"");
        }
    }

    /**
     * Displayes a warning message
     * @param msg, String to be displayed
     */
    public void warningMessage ( String msg){
        Toast toast = Toast.makeText(SetRanges.this, msg, Toast.LENGTH_LONG);
        toast.show();

    }

    /**
     * changes the selected EditText via the isFocused() method. When focused, the String is parsed into
     * a double and gets modifed by addition or substraction. New Value is then passed to the focused EditText
     * as a String
     * Addition rule -> value + (+/-) 1 = new value
     * @param addition, true when addition, false when substraction
     */
    public void changeSelectedRange(boolean addition){

        double value;


        /**
         * checks if user is requesting an addition ot a substraction
         */
        int addOrSub = 1;
        if (!addition){addOrSub= -1 ;}

        /*
        selects the data from the selected editText.
         */
        if (humidityMax.isFocused()){
            value = Double.parseDouble(humidityMax.getText().toString()) + addOrSub;
            humidityMax.setText(value+"");
        }
        else if (humidityMin.isFocused()) {
            value = Double.parseDouble(humidityMin.getText().toString()) + addOrSub;
            humidityMin.setText(value+"");
        }
        else if (tempMax.isFocused()) {
            value = Double.parseDouble(tempMax.getText().toString()) + addOrSub;
            tempMax.setText(value+"");
        }
        else if (tempMin.isFocused()) {
            value = Double.parseDouble(tempMin.getText().toString()) + addOrSub;
            tempMin.setText(value+"");
        }
        else if(lightMax.isFocused()) {
            value = Double.parseDouble(lightMax.getText().toString()) + addOrSub;
            lightMax.setText(value+"");
        }
        else if(lightMin.isFocused()) {
            value = Double.parseDouble(lightMin.getText().toString()) + addOrSub;
            lightMin.setText(value+"");
        }
    }

    /**
     * sets initial values to whatever user last selected (WE NEED DATA BASE CONNECTIVITY HERE)
     */
    public void setInitialValues(){

        double hMax = h.getUserInputedRangeUpper();
        double hMin = h.getUserInputedRangeLower();
        double tMax = t.getUserInputedRangeUpper();
        double tMin = t.getUserInputedRangeLower();;
        double lMax = l.getUserInputedRangeUpper();
        double lMin = l.getUserInputedRangeLower();;

        humidityMax.setText(hMax+"");
        humidityMin.setText(hMin+"");
        tempMax.setText(tMax+"");
        tempMin.setText(tMin+"");
        lightMax.setText(lMax+"");
        lightMin.setText(lMin+"");

    }

    /**
     * sets all EditTexts to defaulted values (device's limits)
     */
    public void defaultedValues(){
        double hMax = h.getDeviceRangeUpper();
        double hMin = h.getDeviceRangeLower();
        double tMax = t.getDeviceRangeUpper();
        double tMin = t.getDeviceRangeLower();
        double lMax = l.getDeviceRangeUpper();
        double lMin = l.getDeviceRangeLower();

        humidityMax.setText(hMax+"");
        humidityMin.setText(hMin+"");
        tempMax.setText(tMax+"");
        tempMin.setText(tMin+"");
        lightMax.setText(lMax+"");
        lightMin.setText(lMin+"");
    }

    /**
     * sets object variables with inputted data
     */
    public void updateNewValues(){

        double hMax = Double.parseDouble(humidityMax.getText().toString());
        double hMin = Double.parseDouble(humidityMin.getText().toString());
        double tMax = Double.parseDouble(tempMax.getText().toString());
        double tMin = Double.parseDouble(tempMin.getText().toString());
        double lMax = Double.parseDouble(lightMax.getText().toString());
        double lMin = Double.parseDouble(lightMin.getText().toString());

        h.setUserInputedRangeLower(hMin);
        l.setUserInputedRangeLower(lMin);
        t.setUserInputedRangeLower(tMin);
        h.setUserInputedRangeUpper(hMax);
        l.setUserInputedRangeUpper(lMax);
        t.setUserInputedRangeUpper(tMax);

    }

    /**
     * On clikc method for button activation.
     * @param v
     */
    @Override
    public void onClick(View v) {

        int i = v.getId();

        if (i == clear.getId()) {
            defaultedValues();
        } else if (i == increase.getId()) {
            changeSelectedRange(true);
        } else if (i == decrease.getId()) {
            changeSelectedRange(false);
        }
        else if( i == reset.getId()){
            setInitialValues();
        }
        else if(i==saveChanges.getId()){
            if (checkAllRanges()){
                // here we save the new information and update the data base.
                updateNewValues();
            }
            else{
                warningMessage("Please modify the RED fields as instructed");
            }

        }
    }
}
