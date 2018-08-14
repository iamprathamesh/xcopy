package com.solutions.canopy.crosscopy;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Async extends AsyncTask<String, String, String> {

    Integer integer;
    protected String doInBackground(String... params) {


        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();


            String line = "";
            while ((line = reader.readLine())!=null) {
                buffer.append(line);
            }
            String copieddata = buffer.toString();
            JSONObject parentobject = new JSONObject(copieddata);
            integer = parentobject.getInt("success");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
            try {
             if(reader != null) {
                 reader.close();
             }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String s = "Copied Sucessfully";
        return s;
    }

    protected void onProgressUpdate(String... progress) {
        super.onProgressUpdate();
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}