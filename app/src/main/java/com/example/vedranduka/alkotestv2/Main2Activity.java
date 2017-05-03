package com.example.vedranduka.alkotestv2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    Button btnClear;
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

        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                DrinkData d = (DrinkData) list.getItemAtPosition(position);
                popUp(d);
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUp(null);
                }

        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.clear();
                drinks.clear();
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

    public void popUp(DrinkData d) {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.pop_up_layout, null);
        drinkName = (EditText) alertLayout.findViewById(R.id.pop_drinkNameTextBox);
        alcoholQuantity = (EditText) alertLayout.findViewById(R.id.pop_alcoholQuantityTextBox);
        alcoholPercentage = (EditText) alertLayout.findViewById(R.id.pop_alcoholPercentageTextBox);

        if(d != null)
        {
            drinkName.setText(d.Name);
            alcoholQuantity.setText(Double.toString(d.amountDrinking));
            alcoholPercentage.setText(Double.toString(d.alcoholPercentage));
        }



        AlertDialog.Builder alert = new AlertDialog.Builder(Main2Activity.this);
        alert.setTitle("Drink input");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String dN = drinkName.getText().toString();
                String aQ = alcoholQuantity.getText().toString();
                String aP = alcoholPercentage.getText().toString();
                double alcQ = Double.parseDouble(alcoholQuantity.getText().toString());
                double alcP = Double.parseDouble(alcoholPercentage.getText().toString());
                drinks.add(new DrinkData(drinkName.getText().toString(),alcQ, alcP));
                adapter.notifyDataSetChanged();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
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
        list = (ListView) findViewById(R.id.list);
        btnAdd = (Button) findViewById(R.id.addButton);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnClear = (Button) findViewById(R.id.btnClear);
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
