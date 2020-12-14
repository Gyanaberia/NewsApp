package com.example.outlab9;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    TextView t;
    OkHttpClient client = new OkHttpClient();
    TitleAdapter adapter;
    RecyclerView recyclerView;
//    public MainActivity() throws IOException { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("message", "cas");
        t=findViewById(R.id.text);
    }

    private List<String> news() throws IOException {
        final List<String> list = new ArrayList<>();
        final Request request = new Request.Builder()
                .url("https://bing-news-search1.p.rapidapi.com/news?safeSearch=Off&textFormat=Raw")
                .get()
                .header("x-bingapis-sdk", "true")
                .header("x-rapidapi-key", "861d20dc81msh6ddeccde9e1257fp17fc15jsnb7a938b0e057")
                .header("x-rapidapi-host", "bing-news-search1.p.rapidapi.com")
                .build();
/*
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
               // t.setText("abject Failure");
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(response.isSuccessful()){
                            Log.d("jsonData", Objects.requireNonNull(response.body()).toString());
                            try {
                                JSONObject json = new JSONObject(Objects.requireNonNull(response.body()).toString());
                                JSONArray values=json.getJSONArray("value");
                                for(int i=0;i<values.length();i++){
                                    JSONObject newsClip=values.getJSONObject(i);
                                    String headline=newsClip.getString("name");
                                    Log.d("holaNames",headline);
                                    list.add(headline);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } //    t.setText("Unsuccessful");


                    }
                });
            }
        });
        */
    /*runOnUiThread(new Runnable() {
        @Override
        public void run() {
            Response response= null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                Log.d("eceptioIO","buddy failed");
                e.printStackTrace();
            }
            assert response != null;
            if(response.isSuccessful()){
                Log.d("jsonData", Objects.requireNonNull(response.body()).toString());
                try {
                    JSONObject json = new JSONObject(Objects.requireNonNull(response.body()).toString());
                    JSONArray values=json.getJSONArray("value");
                    for(int i=0;i<values.length();i++){
                        JSONObject newsClip=values.getJSONObject(i);
                        String headline=newsClip.getString("name");
                        Log.d("holaNames",headline);
                        list.add(headline);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("nono","failed successfully");
                }
            }
        }
    });*/
        Log.d("checkList", String.valueOf(list.size()));
        t.setText(String.valueOf(list.size()));
        return list;
    }

    public void doTHis(View view) {
        final List<String> list = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        list.add("first title");
        final Request request = new Request.Builder()
                .url("https://bing-news-search1.p.rapidapi.com/news?safeSearch=Off&textFormat=Raw")
                .get()
                .header("x-bingapis-sdk", "true")
                .header("x-rapidapi-key", "861d20dc81msh6ddeccde9e1257fp17fc15jsnb7a938b0e057")
                .header("x-rapidapi-host", "bing-news-search1.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
                t.setText("abject Failure");
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ///DO CHANGES HERE.My code doesn't work
                        ResponseBody rb=response.body();//GET RESPONSE BODY
                            String s= rb != null ? rb.toString() : null;//CONVERT INTO STRING
                            Log.d("jsonData", s);
                            try {
                                //PARSE STRING INTO JSONOBJECT TO GET JSOONArray "value"
                                JSONObject json = new JSONObject(s);
                                JSONArray values=json.getJSONArray("value");
                                t.setText(values.length());
                                ///ADD ALL strings corresponding to key "name" in list
                                for(int i=0;i<values.length();i++){
                                    JSONObject newsClip=values.getJSONObject(i);
                                    String headline=newsClip.getString("name");
                                    Log.d("holaNames",headline);
                                    list.add(headline);///TO ADD (STRING) HEADLINES IN LIST
                                }
                            } catch (JSONException e) {
                                Log.d("missionf","failed successfully");
                                e.printStackTrace();
                            }
                            adapter = new TitleAdapter(list);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    }
                });
            }
        });
        Log.d("checkList", String.valueOf(list.size()));
       // t.setText(String.valueOf(list.size()));
    }
}