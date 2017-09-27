package com.google.firebase.codelab.friendlychat;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    static String strJson;
    static int result_code;
    String doorPass[];
    String macAddress;
    static String userID;
    TextView tvView;
    static final String[] LIST_MENU = { "My Page", "QR Code", "게시판"} ;
    String url;
    Intent intent3;
    Button tknbtn;
    Person person;
    MyFirebaseInstanceIdService myFirebaseInstanceIdService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, LIST_MENU) ;
        ListView listview = (ListView) findViewById(R.id.main_ListView) ;
        listview.setAdapter(adapter) ;

        FirebaseMessaging.getInstance().subscribeToTopic("news");
        FirebaseInstanceId.getInstance().getToken();

        person = new Person();
        tknbtn = (Button)findViewById(R.id.tkbtn);
        doorPass = new String[1];
        result_code = 0;
        strJson = "";

        macAddress = getMacAddress();
        Intent idIntent = getIntent();
        userID = (String) idIntent.getSerializableExtra("userID");
        tvView = (TextView) findViewById(R.id.main_textView);
        tvView.setText(userID+"님 환영합니다.");
        tknbtn.setOnClickListener(this);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                // get TextView's Text.
                String strText = (String) parent.getItemAtPosition(position) ;
                if(strText.equals("My Page")) {
                    intent3 = new Intent(MainActivity.this, My_Page.class);
                    intent3.putExtra("userID",userID);
                    startActivity(intent3);
                }
                if(strText.equals("QR Code")) {
                    intent3 = new Intent(MainActivity.this, QRCode.class);
                    startActivity(intent3);
                }
                if(strText.equals("게시판")) {
                    url = "http://ec2-13-124-194-75.ap-northeast-2.compute.amazonaws.com:8080/board/listAllapp?user_ID="+userID;
                    intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent3);
                }
            }
        }) ;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
        }

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


            String link = url;
            String data = URLEncoder.encode("Token", "UTF-8") + "=" + URLEncoder.encode(user_ID, "UTF-8");


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
            case R.id.tkbtn:

                    try {
                        MainActivity.HttpAsyncTask1 httpTask = new MainActivity.HttpAsyncTask1(MainActivity.this);
                        httpTask.execute("http://ec2-13-124-194-75.ap-northeast-2.compute.amazonaws.com:8080/RegistToken",
                                FirebaseInstanceId.getInstance().getToken()+"/"+userID);
                    } catch (Exception e) {}

                break;
        }
    }
    private class HttpAsyncTask1 extends AsyncTask<String, Void, String> {

        private MainActivity loginAct;

        HttpAsyncTask1(MainActivity loginActivity) {
            this.loginAct = loginActivity;
        }

        @Override
        protected String doInBackground(String... urls) {

            person = new Person();
            person.setID(urls[1]);



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
                        Toast.makeText(getApplicationContext(),"성공적으로 토큰이 등록되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                    if(result_code == 200) {
                        Toast.makeText(getApplicationContext(),"토큰 등록에 실패했습니다.",Toast.LENGTH_LONG).show();
                    }


                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
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
