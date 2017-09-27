package com.google.firebase.codelab.friendlychat;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Modify_Information extends AppCompatActivity implements View.OnClickListener{
    Button btn;
    Person person;
    EditText modi_ID;
    EditText modi_Pass;
    static String strJson = "";
    int result_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_information);
        person = new Person();
        btn = (Button) findViewById(R.id.modify_btn);
        modi_ID = (EditText) findViewById(R.id.modify_userID);
        modi_Pass = (EditText) findViewById(R.id.modify_userPass);
        result_code = 0;
        btn.setOnClickListener(this);
    }
    public static String POST(String url, Person person) {
        InputStream is = null;
        String result = "";
        try {
            URL urlCon = new URL(url);
            HttpURLConnection httpCon = (HttpURLConnection) urlCon.openConnection();

            String json = "";

            httpCon.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            httpCon.setDoInput(true);
            httpCon.setDoOutput(true);
            httpCon.setUseCaches(false);
            httpCon.setRequestMethod("POST");
            httpCon.setAllowUserInteraction(true);

            // Http Header Setting

            String user_ID = (String) person.getID();
            String user_Pass = (String) person.getPass();

            String data = URLEncoder.encode("user_ID", "UTF-8") + "=" + URLEncoder.encode(user_ID, "UTF-8");
            data += "&" + URLEncoder.encode("user_Password", "UTF-8") + "=" + URLEncoder.encode(user_Pass, "UTF-8");


            PrintWriter pw = new PrintWriter(new OutputStreamWriter(httpCon.getOutputStream(), "UTF-8"));
            pw.write(data);
            pw.flush();
            // receive response as inputStream
            try {
                is = httpCon.getInputStream();
                // convert inputstream to string
                if (is != null)
                    result = convertInputStreamToString(is);
                else
                    result = "Did not work!";
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                httpCon.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.modify_btn:
                if (!validate())
                    Toast.makeText(getBaseContext(), "Enter some data!", Toast.LENGTH_LONG).show();
                else {
                    try {
                        Modify_Information.HttpAsyncTask1 httpTask = new Modify_Information.HttpAsyncTask1(Modify_Information.this);
                        httpTask.execute("http://ec2-52-79-217-50.ap-northeast-2.compute.amazonaws.com:8080/AssetsManage/login/",
                                modi_ID.getText().toString(),modi_Pass.getText().toString());
                    } catch (Exception e) {
                    }
                }
                break;
        }

    }

    private class HttpAsyncTask1 extends AsyncTask<String, Void, String> {

        private Modify_Information loginAct;

        HttpAsyncTask1(Modify_Information loginActivity) {
            this.loginAct = loginActivity;
        }

        @Override
        protected String doInBackground(String... urls) {

            person = new Person();
            person.setID(urls[1]);
            person.setPass(urls[2]);


            return POST(urls[0], person);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            strJson = result;

            loginAct.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    result_code = Integer.parseInt(strJson);
                    if(result_code == 100) {
                        Toast.makeText(getApplicationContext(),"회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Modify_Information.this, Modify_Information2.class);
                        startActivity(intent);
                    }
                    else if(result_code == 200) {
                        Toast.makeText(getApplicationContext(),"이미 등록된 ID입니다.",Toast.LENGTH_LONG).show();
                    }
                    else {

                    }

                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private boolean validate() {
        if (modi_ID.getText().toString().trim().equals(""))
            return false;
        else if (modi_Pass.getText().toString().trim().equals(""))
            return false;
        else
            return true;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();

        return result;

    }

}
