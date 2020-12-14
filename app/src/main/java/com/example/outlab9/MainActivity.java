package com.example.outlab9;

import android.os.Bundle;
import android.util.Log;

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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<String> list = new ArrayList<>();
        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
        final TitleAdapter adapter = new TitleAdapter(list);
        recyclerView.setAdapter(adapter);
        final Request request = new Request.Builder()
                .url("https://bing-news-search1.p.rapidapi.com/news?safeSearch=Off&textFormat=Raw")
                .addHeader("x-bingapis-sdk", "true")
                .addHeader("x-rapidapi-key", "861d20dc81msh6ddeccde9e1257fp17fc15jsnb7a938b0e057")
                .addHeader("x-rapidapi-host", "bing-news-search1.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String s = response.body().string();
                    Log.d("jsonData", s);
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject json = new JSONObject(s);
                                JSONArray values = json.getJSONArray("value");
                                for (int i = 0; i < values.length(); i++) {
                                    JSONObject newsClip = values.getJSONObject(i);
                                    String headline = newsClip.getString("name");
                                    Log.d("holaNames", headline);
                                    list.add(headline);
                                }
                            } catch (JSONException e) {
                                Log.d("missionf", "failed successfully");
                                e.printStackTrace();
                            }
                            Log.d("holaNames", String.valueOf(list.size()));
                            adapter.setData(list);
                            adapter.notifyDataSetChanged();
                            Log.d("holaNames", "adapter changed");
                        }
                    });
                }
            }
        });
   /* runOnUiThread(new Runnable() {
        @Override
        public void run() {
            list.add("SECOND title");
            try {
                Response response=client.newCall(request).execute();
                if(response.isSuccessful()){
                    String s=response.body().string();
                        JSONObject json = new JSONObject(s);
                        JSONArray values=json.getJSONArray("value");
                        t.setText(values.length());

                        for(int i=0;i<values.length();i++){
                            JSONObject newsClip=values.getJSONObject(i);
                            String headline=newsClip.getString("name");
                            Log.d("holaNames",headline);
                            list.add(headline);
                        }

                    TitleAdapter adapter = new TitleAdapter(list);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                Log.d("missioinf","mission failed successfully");
            }

        }
    });
        Log.d("checkList", String.valueOf(list.size()));
*/
    }
}