package com.google.firebase.codelab.friendlychat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import android.support.v7.app.AppCompatActivity;




public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button registerUser;
    EditText etId, etPass;
    Button logbtn;
    Person person;
    CheckBox autoLogin;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    static int result_code;
    String loginInfo[];
    static String strJson = "";
    String url;
    String macAddress;
    Intent urlIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etId = (EditText) findViewById(R.id.userid);
        etPass = (EditText) findViewById(R.id.pass);
        logbtn = (Button) findViewById(R.id.loginbtn);
        registerUser = (Button) findViewById(R.id.registerUser);
        autoLogin = (CheckBox) findViewById(R.id.autoLog);
        pref = getSharedPreferences("login", 0);
        editor = pref.edit();
        result_code = 0;
        loginInfo = new String[3];
        macAddress = getMacAddress();
        System.out.println("kimtaejoong"+macAddress);
        //login_confirm = new HashMap<String,String>();


        logbtn.setOnClickListener(this);
        registerUser.setOnClickListener(this);
        if (pref.getBoolean("Auto_Login_enabled", false)) {
            etId.setText(pref.getString("ID", ""));
            etPass.setText(pref.getString("PW", ""));
            autoLogin.setChecked(true);
        }
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    String ID = etId.getText().toString();
                    String PW = etPass.getText().toString();

                    editor.putString("ID", ID);
                    editor.putString("PW", PW);
                    editor.putBoolean("Auto_Login_enabled", true);
                    editor.commit();
                } else {

                    editor.clear();
                    editor.commit();
                }
            }
        });
    }
    private String getMacAddress() {
        try {
            String interfaceName = "wlan0";
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (!intf.getName().equalsIgnoreCase(interfaceName)){
                    continue;
                }

                byte[] mac = intf.getHardwareAddress();
                if (mac==null){
                    return "";
                }

                StringBuilder buf = new StringBuilder();
                for (byte aMac : mac) {
                    buf.append(String.format("%02X:", aMac));
                }
                if (buf.length()>0) {
                    buf.deleteCharAt(buf.length() - 1);
                }
                return buf.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } // for now eat exceptions

        return "";
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

            String link = url;
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
            case R.id.loginbtn:
                if (!validate())
                    Toast.makeText(getBaseContext(), "Enter some data!", Toast.LENGTH_LONG).show();
                else {
                    try {
                        LoginActivity.HttpAsyncTask1 httpTask = new LoginActivity.HttpAsyncTask1(LoginActivity.this);
                        httpTask.execute("http://ec2-13-124-194-75.ap-northeast-2.compute.amazonaws.com:8080/ex0/login",
                                etId.getText().toString(),etPass.getText().toString());
                    } catch (Exception e) {}
                }
                break;
            case R.id.registerUser:
                url = "http://ec2-13-124-194-75.ap-northeast-2.compute.amazonaws.com:8080/regist ?user_Code="+macAddress;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                break;
        }
    }

    private class HttpAsyncTask1 extends AsyncTask<String, Void, String> {

        private LoginActivity loginAct;

        HttpAsyncTask1(LoginActivity loginActivity) {
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
                        Toast.makeText(getApplicationContext(),"로그인 되셨습니다.",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("userID",etId.getText().toString());
                        startActivity(intent);
                    }
                    if(result_code == 200) {
                        Toast.makeText(getApplicationContext(),"ID/PW를 확인해주세요.",Toast.LENGTH_LONG).show();
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
        if (etId.getText().toString().trim().equals(""))
            return false;
        else if (etPass.getText().toString().trim().equals(""))
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