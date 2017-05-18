package com.developers.sugarsinitiative.musk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Main6Activity extends AppCompatActivity {
    String Kay;
    String Tee;
    String Weights;
    double dialade;
    String Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        Bundle extras = getIntent().getExtras();

         Kay= extras.getString("k");
        Tee = extras.getString("t");
        Weights = extras.getString("Weights");
        dialade = extras.getDouble("i");

        TextView KAY = (TextView) findViewById(R.id.k);
        KAY.setText(Kay);

        TextView TEE = (TextView) findViewById(R.id.T);
        TEE.setText(Tee);

        TextView WEIGHT = (TextView) findViewById(R.id.weight);
        WEIGHT.setText(Weights);


        TextView Dialade = (TextView) findViewById(R.id.dialade);
        Dialade.setText(String.valueOf(dialade));


    }

    public void MailMessage(View button) {

        Message = " The Dialysis adequacy is " + dialade;
//        Intent i = new Intent(Intent.ACTION_SEND);
//        i.setType("Message/rfc822");
//        i.putExtra(Intent.EXTRA_EMAIL, new String[]{});
//        i.putExtra(Intent.EXTRA_SUBJECT, "Patient Result");
//        i.putExtra(Intent.EXTRA_TEXT, Message);
//        try {
//            startActivity(Intent.createChooser(i, "send mail..."));
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(this, "none", Toast.LENGTH_SHORT).show();
//        }

        Intent intent = new Intent(Main6Activity.this, Mailer3.class);
        intent.putExtra("title", "Patient's Result");
        intent.putExtra("message", Message);
        startActivity(intent);
    }

}
