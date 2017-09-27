package com.google.firebase.codelab.friendlychat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class QRCode_Result extends AppCompatActivity {
    Intent dataIntent;
    String data;

    String s;
    String [] array;
    TextView productName;
    TextView authorName;
    TextView productPage;
    TextView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode__result);
        productName = (TextView) findViewById(R.id.productName);
        authorName = (TextView) findViewById(R.id.authorName);
        productPage = (TextView) findViewById(R.id.productPage);
        content = (TextView) findViewById(R.id.content);
        dataIntent = getIntent();
        data = (String) dataIntent.getSerializableExtra("data");
        s = data;
        array = s.split("/");
        content.setText(array[0]);
        productName.setText(array[1]);
        authorName.setText(array[2]);
        productPage.setText(array[3]);


    }
}
