package com.cc.link;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DeepLinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link);
        TextView tv = findViewById(R.id.tv);
        //获得网页传递过来的
        Intent intent = getIntent();
        if (intent != null) {
            Uri uri = intent.getData();
            String scheme = uri.getScheme();//cc
            String host = uri.getHost();//share
            if ("share".equals(host)) {
                // 跳转到对应详情页面
                Toast.makeText(this, "host:"+host+"scheme:"+scheme+"值："+uri.getPathSegments(), Toast.LENGTH_SHORT).show();
                tv.setText("host:"+host+"\nscheme:"+scheme+"\n值："+uri.getPathSegments());
            }
        }
    }
}
