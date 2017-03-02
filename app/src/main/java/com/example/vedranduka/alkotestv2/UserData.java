package com.example.vedranduka.alkotestv2;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * Created by Vedran Duka on 2.12.2016..
 */

public class UserData implements Serializable{
    public int userMass;
    public enum genderEnum {
        Male(0.68), Female(0.55);

        private double numVal;

        genderEnum(double numVal) {
            this.numVal = numVal;
        }

        public double getNumVal() {
            return numVal;
        }
    }
    public genderEnum gender;
    public double timeDrinking;
    public double timeResting;

    public UserData() {
        userMass = 60;
        gender = genderEnum.Male;
        timeDrinking = 2;
        timeResting = 0.5;

    }

    public UserData(int userMass, genderEnum gender,
                     double timeDrinking, double timeResting) {
        this.userMass = userMass;
        this.gender = gender;
        this.timeDrinking = timeDrinking;
        this.timeResting = timeResting;

    }

    @Override
    public String toString() {
        return String.format("{%d}", userMass);
    }
}
