package com.example.edward.homework5;

import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by edward on 10/23/17.
 */

public class GetEntriesAsync extends AsyncTask<String , Void , ArrayList<Entry>> {
    ArrayList<Entry> entries;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(ArrayList<Entry> entries) {
        super.onPostExecute(entries);
        Log.d("demo" , entries.get(0).summary);
        Log.d("demo" , entries.get(0).largeImage);
        Log.d("demo" , entries.get(0).smallImage);
    }

    @Override
    protected ArrayList<Entry> doInBackground(String... strings) {
        entries = new ArrayList<>();
        HttpURLConnection connection = null;
        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                entries = FeedParser.FeedSaxParser.parseFeed(connection.getInputStream());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


        return entries;

    }
}