package com.google.firebase.codelab.friendlychat;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class My_Goal extends AppCompatActivity implements LoadJSONTask.Listener, AdapterView.OnItemClickListener {

    private ListView mListView;

    public static final String URL = "http://ec2-13-124-194-75.ap-northeast-2.compute.amazonaws.com:8080/jsonService?user_ID=kim";

    private List<HashMap<String, String>> mAndroidMapList = new ArrayList<>();

    private static final String KEY_VER = "ver";
    private static final String KEY_NAME = "name";
    private static final String KEY_API = "api";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_goal);

        mListView = (ListView) findViewById(R.id.mygoal_listview);
        mListView.setOnItemClickListener(this);
        new LoadJSONTask(this).execute(URL);
    }

    @Override
    public void onLoaded(List<AndroidVersion> androidList) {

        for (AndroidVersion android : androidList) {

            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_VER, android.getVer());
            map.put(KEY_NAME, android.getName());
            map.put(KEY_API, android.getApi());

            mAndroidMapList.add(map);
        }

        loadListView();
    }

    @Override
    public void onError() {

        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        final EditText edittext = new EditText(this);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("목표 설정");
        builder.setView(edittext);
        builder.setPositiveButton("입력",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),edittext.getText().toString() ,Toast.LENGTH_LONG).show();
                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),edittext.getText().toString() ,Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }

    private void loadListView() {

        ListAdapter adapter = new SimpleAdapter(My_Goal.this, mAndroidMapList, R.layout.list_item,
                new String[] { KEY_VER, KEY_NAME, KEY_API },
                new int[] { R.id.version,R.id.name, R.id.api });

        mListView.setAdapter(adapter);

    }
}