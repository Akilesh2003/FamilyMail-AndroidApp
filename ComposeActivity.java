package com.example.familymail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ComposeActivity extends AppCompatActivity {
    private EditText mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        mEditTextTo = findViewById(R.id.edit_text_to);
        mEditTextSubject = findViewById(R.id.edit_text_subject);
        mEditTextMessage = findViewById(R.id.edit_text_message);
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        if(name.contains("Ayya")) {
            mEditTextTo.setText(R.string.ayya_email);
        }

        if(name.contains("Prabhu")) {
            mEditTextTo.setText(R.string.prabhu_email);
        }

        if(name.contains("Akilesh")) {
            mEditTextTo.setText(R.string.akilesh_email);
        }
        Button buttonSend = findViewById(R.id.button_send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendMail();
            }
        });
    }

    private void sendMail() {
        //String recipientList = mEditTextTo.getText().toString();
        //String[] recipients = recipientList.split(",");

        String subject = mEditTextSubject.getText().toString();


        String message = mEditTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        //intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, mEditTextTo.getText().toString());
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        /*
        try {
            startActivity(Intent.createChooser(intent, "Choose an email client"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ComposeActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }

         */
    }
}