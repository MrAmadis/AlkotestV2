package com.example.vedranduka.alkotestv2;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    UserData ud;
    ArrayList<DrinkData> dd;
    TextView tvAlcohol;
    TextView timeDrinkingTimeBox;
    Button btnCheckAlcoholAfterTime;
    TextView textViewAlcoholAfterHours;
    double finalAlcoholPercentage;
    double alcoholPercentageTimeDrinking;
    double bloodAlcoholPercentage;
    double sumConsumedAlcohol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        
        linkData();
        getData();
        calculateAlcoholInBlood();
        createButton();
    }

    private void createButton() {
        btnCheckAlcoholAfterTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double finalAlcoholPercentageAfterTime = finalAlcoholPercentage - (Double.parseDouble(timeDrinkingTimeBox.getText().toString()) * 0.015);
                if(finalAlcoholPercentageAfterTime > 0)
                    textViewAlcoholAfterHours.setText(String.format( "%.2f", finalAlcoholPercentageAfterTime));
                else
                    textViewAlcoholAfterHours.setText("0.00");

            }
        });
    }

    private void calculateAlcoholInBlood() {
        sumConsumedAlcohol = 0;
        for (DrinkData item : dd){
            sumConsumedAlcohol += item.amountDrinking * item.alcoholPercentage * 0.789 * 10;
        }
        //double consumedAlcohol = item.amountDrinking * item.alcoholPercentage * 0.789 * 10;

        bloodAlcoholPercentage = (sumConsumedAlcohol / (ud.userMass * ud.gender.getNumVal())) / 10;

        alcoholPercentageTimeDrinking = bloodAlcoholPercentage - (ud.timeDrinking * 0.015);
        finalAlcoholPercentage = alcoholPercentageTimeDrinking - ud.timeResting * 0.015;
        tvAlcohol.setText(String.format( "%.2f", finalAlcoholPercentage));
    }

    private void linkData() {
        tvAlcohol = (TextView) findViewById(R.id.tvAlcohol);
        timeDrinkingTimeBox = (TextView) findViewById(R.id.timeDrinkingTimeBox);
        btnCheckAlcoholAfterTime = (Button) findViewById(R.id.btnCheckAlcoholAfterTime);
        textViewAlcoholAfterHours = (TextView) findViewById(R.id.textViewAlcoholAfterHours);
    }

    @Nullable
    private void getData() {
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            ud = (UserData) extras.getSerializable("UserData");
            dd = (ArrayList<DrinkData>) extras.get("DrinkData");
        }
        else {
            ud = null;
            dd = null;
        }
    }
}
