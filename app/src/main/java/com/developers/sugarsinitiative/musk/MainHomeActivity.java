package com.developers.sugarsinitiative.musk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;


public class MainHomeActivity extends AppCompatActivity {

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        b1 = (Button) findViewById(R.id.viewDosage);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainHomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }


    public void calculateValue1(View button) {
        Intent intent = new Intent(MainHomeActivity.this, Main3Activity.class);
        startActivity(intent);
    }


    public void calculateValue2(View button) {
        Intent intent = new Intent(MainHomeActivity.this, Main5Activity.class);
        startActivity(intent);
    }

}
