package com.example.healthybangladesh.programlogic;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;

public class TipRepository {
    private ArrayList<Tip> tipList;
    private String apiUrl = "https://json.extendsclass.com/bin/8a1679ffe14e";
    private String category;

    public TipRepository(String category) {
        this.category = category;
    }

    public ArrayList<Tip> getTipList(final TipListAsyncResponse tipListAsyncResponse) {
        tipList = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, apiUrl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONArray jsonArray;
                        // questionBank = new ArrayList<>();

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                jsonArray = response.getJSONArray(i);

                                String t = jsonArray.getString(0);
                                String c = jsonArray.getString(1);

                                if (category.equals(c)) {
                                    Tip tip = new Tip(t, c);
                                    tipList.add(tip);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // checking if questionBank is null or not
                        // means whether data is fetched or not
                        if (tipList != null) {
                            tipListAsyncResponse.processFinished(tipList);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Repository", "onErrorResponse: failed to fetch!");
                    }
                });
        QueueSingleton.getInstance().addToRequestQueue(jsonArrayRequest);

        return tipList;
    }
}
