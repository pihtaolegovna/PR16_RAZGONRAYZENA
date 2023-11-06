package com.example.perfectmovie;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.widget.TextView;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class GetApiAsync extends AsyncTask<String, Void, String> {
    private TextView textView;
    private String apiUrl;

    public GetApiAsync(TextView textView, String apiUrl) {
        this.textView = textView;
        this.apiUrl = apiUrl;
    }

    public GetApiAsync(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-API-KEY", "471a96f4-725c-44d8-bcd8-6c48ae066585");
            connection.setRequestProperty("Content-Type", "application/json");

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                String jsonResponse = response.toString();
                return jsonResponse;
            } else {
                return String.valueOf(responseCode);
            }
        } catch (Exception e) {
            return "error";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null) {
            // Update the TextView in the activity
            textView.setText(result);
        } else {
            textView.setText("error");
        }
    }
}
