package com.example.outlab9;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView t;
    OkHttpClient client = new OkHttpClient();

    public MainActivity() throws IOException {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("message", "cas");
        t = findViewById(R.id.text);

    }
    private void news() throws IOException {

        Request request = new Request.Builder()
                .url("https://bing-news-search1.p.rapidapi.com/news/trendingtopics?textFormat=Raw&safeSearch=Off")
                .get()
                .addHeader("x-bingapis-sdk", "true")
                .addHeader("x-rapidapi-key", "861d20dc81msh6ddeccde9e1257fp17fc15jsnb7a938b0e057")
                .addHeader("x-rapidapi-host", "bing-news-search1.p.rapidapi.com")
                .build();
        try {
            Response response = client.newCall(request).execute();//client.newCall(request).execute();
            if(response.isSuccessful()){
                t.setText(response.body().toString());
            }
        }catch (Exception e){
            Log.d("stackerror","bruhh thers a stackexception");
            e.printStackTrace();
        }
    }

    public void dothis(View view) {
        try {
            news();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}