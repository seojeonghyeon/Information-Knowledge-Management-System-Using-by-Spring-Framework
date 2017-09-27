package com.google.firebase.codelab.friendlychat;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class My_Knowledge extends AppCompatActivity  {
    LinearLayout scv;
    String myknow_userId;

    static String result [];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my__knowledge);
        scv = (LinearLayout) findViewById(R.id.lv13);
        // get reference to the views

        Intent intent = getIntent();
        myknow_userId = (String) intent.getSerializableExtra("userID");

        result = new String[100];

        try{
            JSONArray json = null;
            json = new JSONArray(myknow_userId);
        }
        catch (JSONException e){ System.out.println("kimtaeexcep");}
    }

}
