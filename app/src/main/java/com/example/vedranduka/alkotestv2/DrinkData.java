package com.example.vedranduka.alkotestv2;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Vedran Duka on 16.1.2017..
 */

public class DrinkData implements Serializable {
    public String Name;
    public double amountDrinking;
    public double alcoholPercentage;

    public DrinkData() {
        Name = "Sexy drink";
        amountDrinking = 1;
        alcoholPercentage = 17.4;
    }

    public DrinkData(String Name, double amountDrinking, double alcoholPercentage) {
        this.Name = Name;
        this.amountDrinking = amountDrinking;
        this.alcoholPercentage = alcoholPercentage;

    }

    @Override
    public String toString() {
        String sAmountDrinking = String.valueOf(amountDrinking);
        String sAlcoholPercentage = String.valueOf(alcoholPercentage);

        return String.format("%s, %.2f liters, %.2f‰", Name.toString(), amountDrinking, alcoholPercentage);
        //
    }
}
