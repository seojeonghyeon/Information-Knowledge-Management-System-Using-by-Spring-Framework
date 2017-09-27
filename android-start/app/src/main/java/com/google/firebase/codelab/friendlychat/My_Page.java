package com.google.firebase.codelab.friendlychat;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class My_Page extends AppCompatActivity {

    static final String[] LIST_MENU = { "내 지식자산", "목표설정", "자산등록", "정보수정"} ;
    Intent intent;
    TextView userID;
    String url;
    String ididid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_page);
        userID = (TextView) findViewById(R.id.my_page_id);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, LIST_MENU) ;
        Intent intent2 =getIntent();
        userID.setText((String)intent2.getSerializableExtra("id")+"님 환영합니다.");
        ListView listview = (ListView) findViewById(R.id.mypage_ListView) ;
        listview.setAdapter(adapter) ;
        Intent intent5 =getIntent();
        ididid = (String)intent5.getSerializableExtra("userID");
        userID.setText(ididid+" 님 환영합니다.");

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                // get TextView's Text.
                String strText = (String) parent.getItemAtPosition(position) ;
                if(strText.equals("내 지식자산")) {

                    try {
                        // HttpAsyncTask1 httpTask = new My_Page.HttpAsyncTask1(My_Page.this);
                        // httpTask.execute("http://ec2-13-124-194-75.ap-northeast-2.compute.amazonaws.com:8080/ex0/jsonPost",
                        //         "kim");
                        url = "http://ec2-13-124-194-75.ap-northeast-2.compute.amazonaws.com:8080/ex0/jsonPost?user_ID="+ididid;
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    } catch (Exception e) {
                    }
                }
                if(strText.equals("목표설정")) {
                    intent = new Intent(My_Page.this, My_Goal.class);
                    startActivity(intent);
                }
                if(strText.equals("자산등록")) {
                    intent = new Intent(My_Page.this, Nfc.class);
                    startActivity(intent);
                }
                if(strText.equals("정보수정")) {
                    intent = new Intent(My_Page.this, Modify_Information.class);
                    startActivity(intent);
                }

            }
        }) ;
    }
}
