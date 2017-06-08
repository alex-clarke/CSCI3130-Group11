package com.csci3130_group11.csci3130_group11;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class  SetRanges extends AppCompatActivity implements View.OnClickListener {

    private EditText humidityMax;
    private EditText humidityMin;
    private EditText tempMax;
    private EditText tempMin;
    private EditText lightMax;
    private EditText lightMin;
    private Button clear;
    private Button increase;
    private Button decrease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_ranges);
        humidityMax = (EditText) findViewById(R.id.humidity_max);
        humidityMin = (EditText) findViewById(R.id.humidity_min);
        tempMax = (EditText) findViewById(R.id.temp_max);
        tempMin = (EditText) findViewById(R.id.temp_min);
        lightMax = (EditText) findViewById(R.id.light_max);
        lightMin = (EditText) findViewById(R.id.light_min);

        clear = (Button) findViewById(R.id.clear);
        increase = (Button) findViewById(R.id.increase);
        decrease = (Button) findViewById(R.id.decrease);
        clear.setOnClickListener(this);
        increase.setOnClickListener(this);
        decrease.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear:
                humidityMax.setText("");
                humidityMin.setText("");
                tempMax.setText("");
                tempMin.setText("");
                lightMax.setText("");
                lightMin.setText("");
                break;
            case R.id.increase:
                if (humidityMax.isFocused()) {
                    if (humidityMax.getText().toString().trim().isEmpty()) {
                        Toast.makeText(SetRanges.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double humidityMaxValue = Double.parseDouble(humidityMax.getText().toString());
                    if(Util.increase(humidityMaxValue)>100) {
                        Toast.makeText(SetRanges.this, "Humidity cannot be more than 100% !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    humidityMax.setText(Util.increase(humidityMaxValue) + "");
                } else if (humidityMin.isFocused()) {
                    if (humidityMin.getText().toString().trim().isEmpty()) {
                        Toast.makeText(SetRanges.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double humidityMinValue = Double.parseDouble(humidityMin.getText().toString());
                    if(Util.increase(humidityMinValue)>100) {
                        Toast.makeText(SetRanges.this, "Humidity cannot be more than 100% !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    humidityMin.setText(Util.increase(humidityMinValue) + "");
                } else if (tempMax.isFocused()) {
                    if (tempMax.getText().toString().trim().isEmpty()) {
                        Toast.makeText(SetRanges.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double tempMaxValue = Double.parseDouble(tempMax.getText().toString());
                    tempMax.setText(Util.increase(tempMaxValue) + "");
                } else if (tempMin.isFocused()) {
                    if (tempMin.getText().toString().trim().isEmpty()) {
                        Toast.makeText(SetRanges.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double tempMinValue = Double.parseDouble(tempMin.getText().toString());
                    tempMin.setText(Util.increase(tempMinValue) + "");
                } else if (lightMax.isFocused()) {
                    if (lightMax.getText().toString().trim().isEmpty()) {
                        Toast.makeText(SetRanges.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double lightMaxValue = Double.parseDouble(lightMax.getText().toString());
                    if(Util.increase(lightMaxValue)>100) {
                        Toast.makeText(SetRanges.this, "Light cannot be more than 100% !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    lightMax.setText(Util.increase(lightMaxValue) + "");
                } else if (lightMin.isFocused()) {
                    if (lightMin.getText().toString().trim().isEmpty()) {
                        Toast.makeText(SetRanges.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double lightMinValue = Double.parseDouble(lightMin.getText().toString());
                    if(Util.increase(lightMinValue)>100) {
                        Toast.makeText(SetRanges.this, "Light cannot be more than 100% !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    lightMin.setText(Util.increase(lightMinValue) + "");
                }
                break;
            case R.id.decrease:
                if (humidityMax.isFocused()) {
                    if (humidityMax.getText().toString().trim().isEmpty()) {
                        Toast.makeText(SetRanges.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double humidityMaxValue = Double.parseDouble(humidityMax.getText().toString());
                    if (Util.decrease(humidityMaxValue) < 0) {
                        Toast.makeText(SetRanges.this, "Humidity cannot be less than 0 !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    humidityMax.setText(Util.decrease(humidityMaxValue) + "");

                } else if (humidityMin.isFocused()) {
                    if (humidityMin.getText().toString().trim().isEmpty()) {
                        Toast.makeText(SetRanges.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double humidityMinValue = Double.parseDouble(humidityMin.getText().toString());
                    if (Util.decrease(humidityMinValue) < 0) {
                        Toast.makeText(SetRanges.this, "Humidity cannot be less than 0 !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    humidityMin.setText(Util.decrease(humidityMinValue) + "");
                } else if (tempMax.isFocused()) {
                    if (tempMax.getText().toString().trim().isEmpty()) {
                        Toast.makeText(SetRanges.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double tempMaxValue = Double.parseDouble(tempMax.getText().toString());
                    tempMax.setText(Util.decrease(tempMaxValue) + "");
                } else if (tempMin.isFocused()) {
                    if (tempMin.getText().toString().trim().isEmpty()) {
                        Toast.makeText(SetRanges.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double tempMinValue = Double.parseDouble(tempMin.getText().toString());
                    tempMin.setText(Util.decrease(tempMinValue) + "");
                } else if (lightMax.isFocused()) {
                    if (lightMax.getText().toString().trim().isEmpty()) {
                        Toast.makeText(SetRanges.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double lightMaxValue = Double.parseDouble(lightMax.getText().toString());
                    if (Util.decrease(lightMaxValue)  < 0) {
                        Toast.makeText(SetRanges.this, "Light cannot be less than 0 !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    lightMax.setText(Util.decrease(lightMaxValue) + "");
                } else if (lightMin.isFocused()) {
                    if (lightMin.getText().toString().trim().isEmpty()) {
                        Toast.makeText(SetRanges.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double lightMinValue = Double.parseDouble(lightMin.getText().toString());
                    if (Util.decrease(lightMinValue) < 0) {
                        Toast.makeText(SetRanges.this, "Light cannot be less than 0 !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    lightMin.setText(Util.decrease(lightMinValue) + "");
                }
                break;
        }
    }
}
