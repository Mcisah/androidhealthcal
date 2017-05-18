package com.developers.sugarsinitiative.musk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {
    double V;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    public void calculateValue(View button) {
        EditText K = (EditText) findViewById(R.id.k);
        String kay = K.getText().toString();

        EditText T = (EditText) findViewById(R.id.T);
        String tee = T.getText().toString();

        EditText Weight = (EditText) findViewById(R.id.weight);
        String weights = Weight.getText().toString();


        if (((K.getText().toString().trim().equals("")))
                || ((T.getText().toString().trim().equals("")))
                || ((Weight.getText().toString().trim().equals("")))) {
            Toast.makeText(getApplicationContext(), "input all values", Toast.LENGTH_LONG).show();
        } else

        {

            double finalkay = Double.valueOf(K.getText().toString());
            double finaltee = Double.valueOf(T.getText().toString());
            double finalWeight = Double.valueOf(Weight.getText().toString());

            V = (finalWeight * 60) / 100;

            double dialade = (finalkay * finaltee) / V;


            Intent intent = new Intent(Main5Activity.this, Main6Activity.class);
            intent.putExtra("i", dialade);
            intent.putExtra("k", kay);
            intent.putExtra("t", tee);
            intent.putExtra("Weights", weights);

            startActivity(intent);

        }


    }
}
