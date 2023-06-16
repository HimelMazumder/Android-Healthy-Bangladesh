package com.example.healthybangladesh.programlogic;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class QueueSingleton extends Application {
    private static QueueSingleton instance;
    private RequestQueue requestQueue;


    public synchronized static QueueSingleton getInstance() {
        return instance;
    }

    RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }
}
