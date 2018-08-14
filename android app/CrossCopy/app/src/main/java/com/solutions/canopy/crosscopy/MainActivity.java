package com.solutions.canopy.crosscopy;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

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

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    public String string;
    public String copieddata;
    public String copiedText;
    public String stop;
    //public String copiedData = "Copied Data: \n\n";
    //public String sharedData = "Shared Data: \n\n";
    public TextView textview;
    ClipboardManager clipboard;
    Integer integer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-2078176618715324~8355025680");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        final EditText editText = (EditText) findViewById(R.id.editText);
        Button shareButton = (Button) findViewById(R.id.shareButton);
        Button getButton = (Button) findViewById(R.id.getbButton);
        textview = (TextView) findViewById(R.id.textView);
        textview.setText("");


        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editText.getText().toString();
                /*String pasteData = "";
                // If it does contain data, decide if you can handle the data.
                if (!(clipboard.hasPrimaryClip())) {

                } else if (!(clipboard.getPrimaryClipDescription().hasMimeType(MIMETYPE_TEXT_PLAIN))) {

                    // since the clipboard has data but it is not plain text

                } else {

                    //since the clipboard contains plain text.
                    ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);

                    // Gets the clipboard as text.
                    pasteData = item.getText().toString();
                    //ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                    ///string = "http://xcopy.atwebpages.com/xpaste.php?title=" + pasteData;
                    ///new GetData().execute(string);
                    ///GetData objGetData = new GetData();
                    //String data = objGetData.setString();
                    ///data = objGetData.getData();


                }*/
                string = "http://xcopy.atwebpages.com/xpaste.php?title=" + title;
                new AsyncT().execute(string);

            }

        });


        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editText.getText().toString();
                ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                copiedText = item.getText().toString();
                //copiedText = clipboard.getText().toString();
                //copiedText = copiedText + stop;
                string = "http://xcopy.atwebpages.com/xcopy.php?title=" + title + "&copy=" + copiedText.replace(" ","%20");

                new Async1().execute(string);
                //Context context = getApplicationContext();
                //Toast.makeText(context, "Copied Sucessfully!", Toast.LENGTH_SHORT).show();
            }
        });


    }


    class AsyncT extends AsyncTask<String, String, String> {

        String data;

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
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                copieddata = buffer.toString();
                JSONObject parentobject = new JSONObject(copieddata);
                integer = parentobject.getInt("success");
                if (integer == 1) {
                    JSONArray parentarray = parentobject.getJSONArray("copied");
                    JSONObject finalobject = parentarray.getJSONObject(0);
                    data = finalobject.getString("copy");
                    return data;
                } else {
                    return "null";
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String st = "null";
            return st;
        }

        protected void onProgressUpdate(String... progress) {
            super.onProgressUpdate();
        }

        protected void onPostExecute(String result) {
            copieddata = result;
            ClipData clip = ClipData.newPlainText(copieddata, copieddata);
            clipboard.setPrimaryClip(clip);
            //textview.setText(copiedData + copieddata);
            Context context = getApplicationContext();
            if (result.contains("null")) {
                Toast.makeText(context, "Title does not exist", Toast.LENGTH_SHORT).show();
            } else if (integer == 1) {
                Toast.makeText(context, "Got It!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Oops! An error occurred", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(result);
        }

    }


    class Async1 extends AsyncTask<String, String, String> {

        Integer integer;
        protected String doInBackground(String... params) {

            String s;
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
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                stop = buffer.toString();
                JSONObject parentobject = new JSONObject(stop);
                integer = parentobject.getInt("success");
                s = integer.toString();
                return s;

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
            String s1 = "null";
            return s1;
        }

        protected void onProgressUpdate(String... progress) {
            super.onProgressUpdate();
        }

        protected void onPostExecute(String result) {
            Context context = getApplicationContext();
            //textview.setText(sharedData + copiedText);
            if (result.contains("null")) {
                Toast.makeText(context, "Something went Wrong!", Toast.LENGTH_SHORT).show();
            } else {
                Integer id = Integer.parseInt(result);
                if (id == 0) {
                    Toast.makeText(context, "Oops! Title Already Used", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Shared Sucessfully!", Toast.LENGTH_SHORT).show();
                }
            }
            super.onPostExecute(result);
        }
    }
}

