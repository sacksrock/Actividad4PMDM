package com.example.milib.AsyncTasks;

import android.os.AsyncTask;

/**
 * Created by daniel.iglesia on 15/01/2018.
 */

public class HTTPAsyncTask extends AsyncTask<String,Void,Void>{

    public HTTPAsyncTask(){

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
