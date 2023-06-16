package com.example.healthybangladesh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonYoga;
    private Button buttonLifestyle;
    private Button buttonFood;
    private Button buttonFinance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // changing app title bar background color
        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),
                R.color.title_bar_background_color));

        buttonYoga = findViewById(R.id.buttonYoga);
        buttonLifestyle = findViewById(R.id.buttonLifestyle);
        buttonFood = findViewById(R.id.buttonFood);
        buttonFinance = findViewById(R.id.buttonFinance);

        buttonYoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToShowTipsActivity("Yoga");
            }
        });

        buttonLifestyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToShowTipsActivity("Lifestyle");
            }
        });

        buttonFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToShowTipsActivity("Food");
            }
        });

        buttonFinance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToShowTipsActivity("Finance");
            }
        });
    }

    void goToShowTipsActivity(String category) {
        // going from MainActivity to ShowTipsActivity
        Intent intent = new Intent(MainActivity.this, ShowTipsActivity.class);

        // sending category info to the ShowTipsActivity
        intent.putExtra("Category", category);
        startActivity(intent);
    }
}