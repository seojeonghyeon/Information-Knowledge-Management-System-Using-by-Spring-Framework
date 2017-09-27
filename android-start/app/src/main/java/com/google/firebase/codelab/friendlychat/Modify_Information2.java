package com.google.firebase.codelab.friendlychat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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



public class Modify_Information2 extends AppCompatActivity {

    EditText userID;
    EditText userPass;
    EditText userPhoneNumber;
    EditText userHomeNumber;
    EditText userHomeAddress;
    EditText userRePass;
    EditText userName;
    EditText userEmail;
    Button logbtn;
    Person person;
    static int result_code;
    //String loginInfo[];
    String macAddress;
    Intent macintent;
    static String strJson = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_information2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        macintent = getIntent();
        macAddress = (String)macintent.getSerializableExtra("macAddress");
        userID = (EditText) findViewById(R.id.mod_userID);
        userPass = (EditText) findViewById(R.id.mod_userPass);
        userRePass = (EditText) findViewById(R.id.mod_userRePass);
        userName = (EditText) findViewById(R.id.mod_userName);
        userEmail = (EditText) findViewById(R.id.mod_userEmail);
        userHomeAddress = (EditText) findViewById(R.id.mod_userHomeAdd);
        userHomeNumber = (EditText) findViewById(R.id.mod_userHomeNumber);
        userPhoneNumber = (EditText) findViewById(R.id.mod_userPhoneNumber);
        logbtn = (Button) findViewById(R.id.mod_loginbtn);
        result_code = 0;
        //loginInfo = new String[3];
        //logbtn.setOnClickListener(this);

    }

    public static String POST(String url, Person person) {
        InputStream is = null;
        String result = "";
        try {
            URL urlCon = new URL(url);
            HttpURLConnection httpCon = (HttpURLConnection) urlCon.openConnection();

            httpCon.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            httpCon.setDoInput(true);
            httpCon.setDoOutput(true);
            httpCon.setUseCaches(false);
            httpCon.setRequestMethod("POST");
            httpCon.setAllowUserInteraction(true);

            // Http Header Setting

            String user_id = (String) person.getID();
            String user_pass = (String) person.getPass();
            String user_repass = (String) person.getRePass();
            String user_name = (String) person.getName();
            String user_homeadd = (String) person.gethomeAdd();
            String user_homenum = (String) person.gethomeNum();
            String user_email = (String) person.getEmail();
            String user_phonenum = (String) person.getphoneNum();

            String data = URLEncoder.encode("userID", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8");
            data += "&" + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(user_pass, "UTF-8");
            data += "&" + URLEncoder.encode("repass", "UTF-8") + "=" + URLEncoder.encode(user_repass, "UTF-8");
            data += "&" + URLEncoder.encode("userName", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8");
            data += "&" + URLEncoder.encode("userEmail", "UTF-8") + "=" + URLEncoder.encode(user_email, "UTF-8");
            data += "&" + URLEncoder.encode("homeAddress", "UTF-8") + "=" + URLEncoder.encode(user_homeadd, "UTF-8");
            data += "&" + URLEncoder.encode("homeNumber", "UTF-8") + "=" + URLEncoder.encode(user_homenum, "UTF-8");
            data += "&" + URLEncoder.encode("phoneNumber", "UTF-8") + "=" + URLEncoder.encode(user_phonenum, "UTF-8");

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
            case R.id.mod_loginbtn:
                if (!validate())
                    Toast.makeText(getBaseContext(), "Enter some data!", Toast.LENGTH_LONG).show();
                else {
                    try {
                        Modify_Information2.HttpAsyncTask1 httpTask = new Modify_Information2.HttpAsyncTask1(Modify_Information2.this);
                        httpTask.execute("http://ec2-52-79-217-50.ap-northeast-2.compute.amazonaws.com:8080/AssetsManage/newMember/",
                                userID.getText().toString(),userPass.getText().toString(),userRePass.getText().toString(),
                                userName.getText().toString(),userEmail.getText().toString(),userHomeAddress.getText().toString(),
                                userHomeNumber.getText().toString(),userPhoneNumber.getText().toString());
                    } catch (Exception e) {
                    }
                }
                break;
        }
    }

    private class HttpAsyncTask1 extends AsyncTask<String, Void, String> {

        private Modify_Information2 userRegister;

        HttpAsyncTask1(Modify_Information2 userRegister) {
            this.userRegister = userRegister;
        }

        @Override
        protected String doInBackground(String... urls) {

            person = new Person();
            person.setID(urls[1]);
            person.setPass(urls[2]);
            person.setRePass(urls[3]);
            person.setName(urls[4]);
            person.setEmail(urls[5]);
            person.sethomeAdd(urls[6]);
            person.sethomeNum(urls[7]);
            person.setphoneNum(urls[8]);

            return POST(urls[0], person);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            strJson = result;

            userRegister.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    result_code = Integer.parseInt(strJson);
                    if(result_code == 100) {
                        Toast.makeText(getApplicationContext(),"회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Modify_Information2.this, MainActivity.class);
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
        if (userID.getText().toString().trim().equals(""))
            return false;
        else if (userPass.getText().toString().trim().equals(""))
            return false;
        else if (userRePass.getText().toString().trim().equals(""))
            return false;
        else if (userName.getText().toString().trim().equals(""))
            return false;
        else if (userEmail.getText().toString().trim().equals(""))
            return false;
        else if (userHomeAddress.getText().toString().trim().equals(""))
            return false;
        else if (userHomeNumber.getText().toString().trim().equals(""))
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
