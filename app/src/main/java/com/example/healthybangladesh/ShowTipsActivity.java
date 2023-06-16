package com.example.healthybangladesh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.healthybangladesh.programlogic.Tip;
import com.example.healthybangladesh.programlogic.TipListAsyncResponse;
import com.example.healthybangladesh.programlogic.TipRepository;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ShowTipsActivity extends AppCompatActivity {
    private TextView textViewShowTips;
    private TextView textViewOutOf;

    private Button buttonNext;
    private Button buttonPrev;

    private int currentTipIndex;
    private List<Tip> tipList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tips);

        // changing app title bar background color
        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),
                R.color.title_bar_background_color));

        textViewShowTips = findViewById(R.id.textViewShowTips);
        textViewOutOf = findViewById(R.id.textViewOutOf);
        buttonNext = findViewById(R.id.buttonNext);
        buttonPrev = findViewById(R.id.buttonPrev);

        // getting the value from the MainActivity
        String categoryValue = getIntent().getStringExtra("Category");

        // data fetching starts
        tipList = new TipRepository(categoryValue).getTipList(new TipListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Tip> tipListAM) {
                textViewShowTips.setText(tipListAM.get(currentTipIndex).getTip());
                textViewOutOf.setText(tipListAM.get(currentTipIndex).getCategory() + ": " + (currentTipIndex + 1) + "/" + tipListAM.size());
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentTipIndex == (tipList.size() - 1)) {
                    Toast.makeText(getApplicationContext(), "The End", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    currentTipIndex = (currentTipIndex + 1);
                    textViewShowTips.setText(tipList.get(currentTipIndex).getTip());

                    textViewOutOf.setText(tipList.get(currentTipIndex).getCategory() + ": " + (currentTipIndex + 1) + "/" + tipList.size());
                }
            }
        });

        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentTipIndex == 0) {
                    Toast.makeText(getApplicationContext(), "The End", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    currentTipIndex = (currentTipIndex - 1);
                    textViewShowTips.setText(tipList.get(currentTipIndex).getTip());

                    textViewOutOf.setText(tipList.get(currentTipIndex).getCategory() + ": " + (currentTipIndex + 1) + "/" + tipList.size());
                }
            }
        });
    }
}