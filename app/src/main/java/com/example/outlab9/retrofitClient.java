package com.example.outlab9;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class retrofitClient {
    private void news() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://bing-news-search1.p.rapidapi.com/news/trendingtopics?textFormat=Raw&safeSearch=Off")
                .get()
                .addHeader("x-bingapis-sdk", "true")
                .addHeader("x-rapidapi-key", "861d20dc81msh6ddeccde9e1257fp17fc15jsnb7a938b0e057")
                .addHeader("x-rapidapi-host", "bing-news-search1.p.rapidapi.com")
                .build();
       // Response response = client.newCall(request).execute();
        // t.setText(response.toString());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }
}
