package com.csci3130_group11.csci3130_group11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout ll;
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
        setContentView(R.layout.activity_main);
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

        humidityMax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && !s.toString().equals("")) {
                    Double d = Double.parseDouble(s.toString());
                    if (d > 100) {
                        Toast.makeText(MainActivity.this, "No more than 100", Toast.LENGTH_SHORT).show();
                        humidityMax.setText("100");
                    }
                }
            }
        });
        lightMax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && !s.toString().equals("")) {
                    Double d = Double.parseDouble(s.toString());
                    if (d > 100) {
                        Toast.makeText(MainActivity.this, "No more than 100", Toast.LENGTH_SHORT).show();
                        lightMax.setText("100");
                    }
                }
            }
        });
        humidityMax.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (!tempMin.getText().toString().trim().isEmpty() && !tempMax.getText().toString().trim().isEmpty())
                        checkTemperature();
                    if (!lightMin.getText().toString().trim().isEmpty() && !lightMax.getText().toString().trim().isEmpty())
                        checkLight();
                }
            }
        });

        humidityMin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (!tempMin.getText().toString().trim().isEmpty() && !tempMax.getText().toString().trim().isEmpty())
                        checkTemperature();
                    if (!lightMin.getText().toString().trim().isEmpty() && !lightMax.getText().toString().trim().isEmpty())
                        checkLight();
                }
            }
        });

        tempMin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (!humidityMin.getText().toString().trim().isEmpty() && !humidityMax.getText().toString().trim().isEmpty())
                        checkHumidity();
                    if (!lightMin.getText().toString().trim().isEmpty() && !lightMax.getText().toString().trim().isEmpty())
                        checkLight();
                }
            }
        });
        tempMax.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (!humidityMin.getText().toString().trim().isEmpty() && !humidityMax.getText().toString().trim().isEmpty())
                        checkHumidity();
                    if (!lightMin.getText().toString().trim().isEmpty() && !lightMax.getText().toString().trim().isEmpty())
                        checkLight();
                }
            }
        });
        lightMax.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (!humidityMin.getText().toString().trim().isEmpty() && !humidityMax.getText().toString().trim().isEmpty())
                        checkHumidity();
                    if (!tempMin.getText().toString().trim().isEmpty() && !tempMax.getText().toString().trim().isEmpty())
                        checkTemperature();
                }
            }
        });
        lightMin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (!humidityMin.getText().toString().trim().isEmpty() && !humidityMax.getText().toString().trim().isEmpty())
                        checkHumidity();
                    if (!tempMin.getText().toString().trim().isEmpty() && !tempMax.getText().toString().trim().isEmpty())
                        checkTemperature();
                }
            }
        });
    }

    private void checkHumidity() {
        double humidityMinValue = Double.valueOf(humidityMin.getText().toString().trim());
        double humidityMaxValue = Double.valueOf(humidityMax.getText().toString().trim());
        if (humidityMinValue > humidityMaxValue) {
            Toast.makeText(MainActivity.this, " Humidity: Min should be less than Max!", Toast.LENGTH_SHORT).show();
            humidityMin.setText("0");
            humidityMax.setText("0");
        }
    }

    private void checkTemperature() {
        double tempMinValue = Double.valueOf(tempMin.getText().toString().trim());
        double tempMaxValue = Double.valueOf(tempMax.getText().toString().trim());
        if (tempMinValue > tempMaxValue) {
            Toast.makeText(MainActivity.this, "Temperature: Min should be less than Max!", Toast.LENGTH_SHORT).show();
            tempMin.setText("0");
            tempMax.setText("0");
        }
    }

    private void checkLight() {
        double lightMinValue = Double.valueOf(lightMin.getText().toString().trim());
        double lightMaxValue = Double.valueOf(lightMax.getText().toString().trim());
        if (lightMinValue > lightMaxValue) {
            Toast.makeText(MainActivity.this, "Light: Min should be less than Max!", Toast.LENGTH_SHORT).show();
            lightMin.setText("0");
            lightMax.setText("0");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear:
                humidityMax.setText("0");
                humidityMin.setText("0");
                tempMax.setText("0");
                tempMin.setText("0");
                lightMax.setText("0");
                lightMin.setText("0");
                break;
            case R.id.increase:
                if (humidityMax.isFocused()) {
                    if (humidityMax.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double humidityMaxValue = Double.parseDouble(humidityMax.getText().toString());
                    if (Util.increase(humidityMaxValue) > 100) {
                        Toast.makeText(MainActivity.this, "Humidity cannot be more than 100% !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    humidityMax.setText(Util.increase(humidityMaxValue) + "");
                } else if (humidityMin.isFocused()) {
                    if (humidityMin.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double humidityMinValue = Double.parseDouble(humidityMin.getText().toString());
                    if (Util.increase(humidityMinValue) > 100) {
                        Toast.makeText(MainActivity.this, "Humidity cannot be more than 100% !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double humidityMaxValue = Double.parseDouble(humidityMax.getText().toString());
                    if (Util.increase(humidityMinValue) > humidityMaxValue) {
                        Toast.makeText(MainActivity.this, "Humidity: Min should be less than Max!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    humidityMin.setText(Util.increase(humidityMinValue) + "");
                } else if (tempMax.isFocused()) {
                    if (tempMax.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double tempMaxValue = Double.parseDouble(tempMax.getText().toString());
                    tempMax.setText(Util.increase(tempMaxValue) + "");
                } else if (tempMin.isFocused()) {
                    if (tempMin.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double tempMinValue = Double.parseDouble(tempMin.getText().toString());
                    double tempMaxValue = Double.parseDouble(tempMax.getText().toString());
                    if (Util.increase(tempMinValue) > tempMaxValue) {
                        Toast.makeText(MainActivity.this, "Temperature: Min should be less than Max!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    tempMin.setText(Util.increase(tempMinValue) + "");
                } else if (lightMax.isFocused()) {
                    if (lightMax.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double lightMaxValue = Double.parseDouble(lightMax.getText().toString());
                    if (Util.increase(lightMaxValue) > 100) {
                        Toast.makeText(MainActivity.this, "Light cannot be more than 100% !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    lightMax.setText(Util.increase(lightMaxValue) + "");
                } else if (lightMin.isFocused()) {
                    if (lightMin.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double lightMinValue = Double.parseDouble(lightMin.getText().toString());
                    if (Util.increase(lightMinValue) > 100) {
                        Toast.makeText(MainActivity.this, "Light cannot be more than 100% !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double lightMaxValue = Double.parseDouble(lightMax.getText().toString());
                    if (Util.increase(lightMinValue) > lightMaxValue) {
                        Toast.makeText(MainActivity.this, "Light: Min should be less than Max!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    lightMin.setText(Util.increase(lightMinValue) + "");
                }
                break;
            case R.id.decrease:
                if (humidityMax.isFocused()) {
                    if (humidityMax.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double humidityMaxValue = Double.parseDouble(humidityMax.getText().toString());
                    if (Util.decrease(humidityMaxValue) < 0) {
                        Toast.makeText(MainActivity.this, "Humidity cannot be less than 0 !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double humidityMinValue = Double.parseDouble(humidityMin.getText().toString().trim());
                    if (Util.decrease(humidityMaxValue) < humidityMinValue) {
                        Toast.makeText(MainActivity.this, "Humidity: Max should be more than Min!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    humidityMax.setText(Util.decrease(humidityMaxValue) + "");

                } else if (humidityMin.isFocused()) {
                    if (humidityMin.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double humidityMinValue = Double.parseDouble(humidityMin.getText().toString());
                    if (Util.decrease(humidityMinValue) < 0) {
                        Toast.makeText(MainActivity.this, "Humidity cannot be less than 0 !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    humidityMin.setText(Util.decrease(humidityMinValue) + "");
                } else if (tempMax.isFocused()) {
                    if (tempMax.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double tempMaxValue = Double.parseDouble(tempMax.getText().toString().trim());
                    double tempMinValue = Double.parseDouble(tempMin.getText().toString().trim());
                    if (Util.decrease(tempMaxValue) < tempMinValue) {
                        Toast.makeText(MainActivity.this, "Temperature: Max should be more than Min!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    tempMax.setText(Util.decrease(tempMaxValue) + "");
                } else if (tempMin.isFocused()) {
                    if (tempMin.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double tempMinValue = Double.parseDouble(tempMin.getText().toString());
                    tempMin.setText(Util.decrease(tempMinValue) + "");
                } else if (lightMax.isFocused()) {
                    if (lightMax.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double lightMaxValue = Double.parseDouble(lightMax.getText().toString().trim());
                    if (Util.decrease(lightMaxValue) < 0) {
                        Toast.makeText(MainActivity.this, "Light cannot be less than 0 !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double lightMinValue = Double.parseDouble(lightMin.getText().toString().trim());
                    if (Util.decrease(lightMaxValue) < lightMinValue) {
                        Toast.makeText(MainActivity.this, "Light: Max should be more than Min!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    lightMax.setText(Util.decrease(lightMaxValue) + "");
                } else if (lightMin.isFocused()) {
                    if (lightMin.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please input!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    double lightMinValue = Double.parseDouble(lightMin.getText().toString());
                    if (Util.decrease(lightMinValue) < 0) {
                        Toast.makeText(MainActivity.this, "Light cannot be less than 0 !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    lightMin.setText(Util.decrease(lightMinValue) + "");
                }
                break;
        }
    }
}
