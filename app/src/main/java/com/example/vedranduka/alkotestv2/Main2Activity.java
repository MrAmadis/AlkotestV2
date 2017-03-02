package com.example.vedranduka.alkotestv2;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends Activity {

    TextView userMass;
    TextView userGender;
    TextView userTimeSpentDrinking;
    TextView userTimeSpentAfter;
    Button btnAdd;
    TextView drinkName;
    TextView alcoholQuantity;
    TextView alcoholPercentage;
    ListView list;
    ArrayList<DrinkData> drinks;
    ArrayAdapter<DrinkData> adapter;
    Button btnNext;
    UserData ud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        linkData();
        ud = getData();
        fillUserData(ud);

        drinks = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, drinks);
        list.setAdapter(adapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double alcQ = Double.parseDouble(alcoholQuantity.getText().toString());
                double alcP = Double.parseDouble(alcoholPercentage.getText().toString());
                drinks.add(new DrinkData(drinkName.getText().toString(),alcQ, alcP));
                adapter.notifyDataSetChanged();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startThirdActivity(ud, drinks);
            }
        });

        addTestData();

    }

    private void addTestData() {
        //drinks.add(new DrinkData("Rakija",1,40));
        drinks.add(new DrinkData("Piva",1,5));
        adapter.notifyDataSetChanged();
    }

    private void fillUserData(UserData ud) {
        userMass.setText("Mass: " + ud.userMass + " kg");
        userGender.setText("Gender: " + ud.gender);
        userTimeSpentDrinking.setText("Time Spent Drinking: " + ud.timeDrinking + " hours");
        userTimeSpentAfter.setText("Time Spent After Drinking: " + ud.timeResting + " hours");

    }

    private void linkData() {
        userMass = (TextView) findViewById(R.id.textViewMass);
        userGender = (TextView) findViewById(R.id.textViewGender);
        userTimeSpentDrinking = (TextView) findViewById(R.id.textViewTimeSpentDrinking);
        userTimeSpentAfter = (TextView) findViewById(R.id.textViewTimeSpentAfter);
        drinkName = (TextView) findViewById(R.id.drinkNameTextBox);
        alcoholQuantity = (TextView) findViewById(R.id.alcoholQuantityTextBox);
        alcoholPercentage = (TextView) findViewById(R.id.alcoholPercentageTextBox);
        list = (ListView) findViewById(R.id.list);
        btnAdd = (Button) findViewById(R.id.addButton);
        btnNext = (Button) findViewById(R.id.btnNext);
    }

    @Nullable
    private UserData getData() {
        Bundle extras = getIntent().getExtras();
        UserData d;
        if(extras != null)
            d = (UserData)extras.getSerializable("UserData");
        else
            d = null;
        return d;
    }

    private void startThirdActivity(UserData ud, ArrayList<DrinkData> dd) {
        Intent intent = new Intent(this, Main3Activity.class);
        Bundle b = new Bundle();
        b.putSerializable("UserData", ud);
        intent.putExtras(b);
        intent.putExtra("DrinkData", dd);
        startActivity(intent);
    }
}
