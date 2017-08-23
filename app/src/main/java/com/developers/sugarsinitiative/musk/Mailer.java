package com.developers.sugarsinitiative.musk;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer extends AppCompatActivity {
    String title;
    String message;
    Session session = null;
    String finalsubject, finalemail, finalmessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailer);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Bundle extras = getIntent().getExtras();
        title = extras.getString("title");
        message = extras.getString("message");
        final String title_2 = title;
        final String message_2 = message;

        final EditText title_box = (EditText) findViewById(R.id.hcp_subject);
        title_box.setText(title_2);

        final EditText message_box = (EditText) findViewById(R.id.hcp_message);
        message_box.setText(message_2);

        final EditText email_box = (EditText) findViewById(R.id.hcp_email);

        final Button sendMail = (Button) findViewById(R.id.sendMail);
        sendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((title_box.getText().toString().trim().equals(""))) ||
                        ((message_box.getText().toString().trim().equals(""))) ||
                        ((email_box.getText().toString().trim().equals("")))) {
                    Toast.makeText(getApplicationContext(),"input all values", Toast.LENGTH_LONG).show();
                } else {
                    finalsubject = title_box.getText().toString();
                    finalemail = email_box.getText().toString();
                    finalmessage = message_box.getText().toString();

                    Properties props = new Properties();
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.socketFactory.port", "465");
                    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.port", "465");

                    session = Session.getDefaultInstance(props, new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication(){
                            return new PasswordAuthentication("example@gmail.com", "password");
                        }
                    });

                    Toast.makeText(getApplicationContext(),"Sending Email...", Toast.LENGTH_LONG).show();

                    RetreiveFeedTask task = new RetreiveFeedTask();
                    task.execute();

                    Toast.makeText(getApplicationContext(),"Email Sent", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params){

            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("example@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(finalemail));
                message.setSubject(finalsubject);
                message.setContent(finalmessage, "text/html; charset=utf-8");
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result){
            Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_LONG).show();
        }
    }
}
