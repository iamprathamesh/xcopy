package com.solutions.canopy.crosscopy;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetData extends AsyncTask<String, String, String> {

    String data;
    protected String doInBackground(String... params) {

        MainActivity mainActivity = new MainActivity();
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
            String finalJSON = buffer.toString();
            JSONObject parentobject = new JSONObject(finalJSON);
            JSONArray parentarray = parentobject.getJSONArray("copied");
            JSONObject finalobject = parentarray.getJSONObject(0);
            data = finalobject.getString("copy");
            return data;

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
        return null;
    }

    protected void onProgressUpdate(String... progress) {
        super.onProgressUpdate();
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //getData(result);
    }

}