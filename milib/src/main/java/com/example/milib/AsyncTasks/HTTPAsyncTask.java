package com.example.milib.AsyncTasks;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by daniel.iglesia on 15/01/2018.
 */

public class HTTPAsyncTask extends AsyncTask<String, Integer, String>{

    public HTTPAsyncTask(){

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... urls) {
        int count;
        String pathfin=null;
    for (int i=0;i<urls.length;i++) {
        try {
            String root = Environment.getExternalStorageDirectory().toString();
            Log.v("HttpAsyncTask", "Downloading");
            URL url = new URL(urls[0]);

            URLConnection conection = url.openConnection();
            conection.connect();
            // getting file length
            int lenghtOfFile = conection.getContentLength();

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            // Output stream to write file
            pathfin = root + "/downloadedfile.jpg";
            OutputStream output = new FileOutputStream(pathfin);
            byte data[] = new byte[1024];

            int contador = 0;
            long total = 0;
            while ((count = input.read(data)) != -1) {
                total += count;
                contador = contador + 5;
                this.publishProgress(contador);
                // writing data to file
                output.write(data, 0, count);

            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
    }
        return pathfin;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.v("HttpAsyncTask","Downloading");
    }

    @Override
    protected void onPostExecute(String in) {
        super.onPostExecute(in);
    }
}
