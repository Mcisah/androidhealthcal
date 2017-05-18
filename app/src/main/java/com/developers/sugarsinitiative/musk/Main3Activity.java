package com.developers.sugarsinitiative.musk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    double value;
    String gender;
    Spinner raceSpinner;
    Object race;
    String raceName;
    double raceValue;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        raceSpinner = (Spinner) findViewById(R.id.raceSpinner);
        raceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                race = adapterView.getItemAtPosition(position);
                raceName = "unknown";
                switch (position) {
                    case 0:
                        raceName = "Nigerian";
                        raceValue = 1;
                        break;
                    case 1:
                        raceName = "African American";
                        raceValue = 1.210;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        radioGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                value = 0;
                gender = "unknown";
                switch (checkedId) {
                    case R.id.maleRadio:
                        value = 1;
                        gender = "Male";
                        break;

                    case R.id.femaleRadio:
                        value = 0.742;
                        gender = "Female";
                        break;
                }
            }
        });
    }

    public void calculateValue1(View button) {
        EditText serCrt = (EditText) findViewById(R.id.serumCreatinine);
        String crt = serCrt.getText().toString();

        EditText Age = (EditText) findViewById(R.id.age);
        String age = Age.getText().toString();

        EditText Weight = (EditText) findViewById(R.id.weight);
        String weights = Weight.getText().toString();


        if (((serCrt.getText().toString().trim().equals("")))
                || ((Age.getText().toString().trim().equals("")))
                || ((Weight.getText().toString().trim().equals("")))
                || (radioGroup.getCheckedRadioButtonId() == -1)
                ) {
            Toast.makeText(getApplicationContext(), "input all values", Toast.LENGTH_LONG).show();
        } else {

            double finalserCrt = Double.valueOf(serCrt.getText().toString());
            double finalAge = Double.valueOf(Age.getText().toString());
            double sweet = (175 * Math.pow(finalserCrt, -1.154)) * (Math.pow(finalAge, -0.203)) * value * raceValue;


            Intent intent = new Intent(this, Main4Activity.class);
            intent.putExtra("i", sweet);
            intent.putExtra("crt", crt);
            intent.putExtra("Genders", gender);
            intent.putExtra("Age", age);
            intent.putExtra("Weights", weights);
            intent.putExtra("RaceName", raceName);
            startActivity(intent);


        }
    }
}
