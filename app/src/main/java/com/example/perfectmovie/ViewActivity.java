package com.example.perfectmovie;

import static android.view.View.GONE;
import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ViewActivity extends AppCompatActivity {
    private TextView textView;
    private ProgressBar spinner;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        textView = findViewById(R.id.textView);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        int movieID = getIntent().getIntExtra("selected_movie_id", 0);
        String movie_name = getIntent().getStringExtra("name");
        String movie_eng_name = getIntent().getStringExtra("engname");
        int movie_year = getIntent().getIntExtra("year", 2016);

        String apiUrl = "https://kinopoiskapiunofficial.tech/api/v1/staff?filmId=" + movieID;
        String apiUrl2 = "https://kinopoiskapiunofficial.tech/api/v2.2/films/"+ movieID +"/videos";

        new GetApiAsync(textView, apiUrl) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                textView.setText(" ");

                if (result != null) {
                    try {
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<Staff>>() {}.getType();
                        List<Staff> staffList = gson.fromJson(result, listType);
                        RecyclerView recyclerView = findViewById(R.id.recyclerView);
                        InfoAdapter infoAdapter = new InfoAdapter(staffList, ViewActivity.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ViewActivity.this));
                        recyclerView.setAdapter(infoAdapter);

                        new GetApiAsync(textView, apiUrl2) {
                            @Override
                            protected void onPostExecute(String result) {
                                super.onPostExecute(result);
                                textView.setText(movie_name + "\n" + movie_eng_name + "\n" + movie_year);

                                if (result != null) {
                                    try {
                                        JsonElement jsonElement = JsonParser.parseString(result);
                                        if (jsonElement.isJsonObject()) {
                                            JsonObject jsonObject = jsonElement.getAsJsonObject();
                                            JsonArray itemsArray = jsonObject.getAsJsonArray("items");

                                            for (JsonElement itemElement : itemsArray) {
                                                if (itemElement.isJsonObject()) {
                                                    JsonObject itemObject = itemElement.getAsJsonObject();

                                                    if (itemObject.has("site") && itemObject.get("site").getAsString().equals("YOUTUBE")) {
                                                        String youtubeUrl = itemObject.get("url").getAsString();
                                                        webView = findViewById(R.id.webview);
                                                        webView.getSettings().setJavaScriptEnabled(true);
                                                        webView.setWebViewClient(new WebViewClient());
                                                        webView.loadUrl(youtubeUrl);
                                                        spinner.setVisibility(GONE);
                                                    }
                                                }
                                            }
                                        }
                                    } catch (JsonSyntaxException e) {
                                        textView.setText("JSON Parsing Error: " + e.getMessage());
                                    }
                                } else {

                                }
                            }
                        }.execute();
                    } catch (JsonSyntaxException e) {
                        textView.setText("JSON Parsing Error: " + e.getMessage());
                    }
                } else {

                }
            }
        }.execute();
    }
}