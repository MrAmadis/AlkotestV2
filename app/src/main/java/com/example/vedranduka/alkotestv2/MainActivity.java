package com.example.vedranduka.alkotestv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.vedranduka.alkotestv2.R.id.radioMale;

public class MainActivity extends AppCompatActivity {

    TextView viewUserMass;
    RadioGroup radioSex;
    TextView viewTimeDrinking;
    TextView viewTimeResting;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkData();
        setTestData();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp;

                temp = viewUserMass.getText().toString();
                int userMass = Integer.parseInt(temp);

                int selectedId = radioSex.getCheckedRadioButtonId();
                RadioButton radioSexButton = (RadioButton) findViewById(selectedId);
                UserData.genderEnum g;
                if(radioSexButton.getText().equals("Male"))
                    g = UserData.genderEnum.Male;
                else
                    g = UserData.genderEnum.Female;

                temp = viewTimeDrinking.getText().toString();
                double timeDrinking = Double.parseDouble(temp);

                temp = viewTimeResting.getText().toString();
                double timeResting = Double.parseDouble(temp);

                UserData d = new UserData(userMass, g, timeDrinking, timeResting);
                //Toast.makeText(MainActivity.this, d.toString(), Toast.LENGTH_SHORT).show();
                reset();
                startSecondActivity(d);
            }
        });

    }

    private void startSecondActivity(UserData d) {
        Intent intent = new Intent(this, Main2Activity.class);
        Bundle b = new Bundle();
        b.putSerializable("UserData", d);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void linkData() {
        viewUserMass = (TextView)findViewById(R.id.drinkNameTextBox);
        radioSex = (RadioGroup) findViewById(R.id.radioSex);
        viewTimeDrinking = (TextView)findViewById(R.id.timeDrinkingTimeBox);
        viewTimeResting = (TextView)findViewById(R.id.timeRestingTimeBox);
        submitButton = (Button) findViewById(R.id.submitButton);
    }

    private void reset() {
        viewUserMass.setText("");
        radioSex.clearCheck();
        viewTimeDrinking.setText("");
        viewTimeResting.setText("");
    }

    private void setTestData() {
        viewUserMass.setText("100");
        radioSex.check(radioMale);
        viewTimeDrinking.setText("1");
        viewTimeResting.setText("0");
    }
}
