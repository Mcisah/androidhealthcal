package com.developers.sugarsinitiative.musk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText serCrt;
    EditText Age;
    EditText Weight;
    double value;
    String gender;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);
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
                        value = 0.85;
                        gender = "Female";
                        break;
                }
            }
        });

        calculate = (Button) findViewById(R.id.viewDosage);
        calculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                serCrt = (EditText) findViewById(R.id.serumCreatinine);
                String crt = serCrt.getText().toString();



                Age = (EditText) findViewById(R.id.age);
                String age = Age.getText().toString();


                Weight = (EditText) findViewById(R.id.weight);
                String weights = Weight.getText().toString();


                if (((serCrt.getText().toString().trim().equals(""))) ||
                        ((Age.getText().toString().trim().equals(""))) ||
                        ((Weight.getText().toString().trim().equals(""))) ||
                        (radioGroup.getCheckedRadioButtonId() == -1)) {
                  Toast.makeText(getApplicationContext(),"input all values", Toast.LENGTH_LONG).show();
                } else {
                    double finalScrt = Double.valueOf(serCrt.getText().toString());
                    double finalAge = Double.valueOf(Age.getText().toString());
                    double finalWeight = Double.valueOf(Weight.getText().toString());
                    double sweet = (((140 - finalAge) * finalWeight) / (72 * finalScrt) * value);

                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("i", sweet);
                    intent.putExtra("crt", crt);
                    intent.putExtra("Genders", gender);
                    intent.putExtra("Age", age);
                    intent.putExtra("Weights", weights);
                    startActivity(intent);
                }
            }
        });


    }


}
