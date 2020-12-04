package com.example.aocdaytwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //This is basically an object with the string stored in it
    //I created this to move the 1000 line declaration out of the main activity
    //have a look at RawData.java to see what i mean.

    RawData rawDataObject = new RawData();


    String rawData = rawDataObject.getRawData();
    String[] splitString = rawData.split("\n");
    int totalValidPasswords = 0;
    int secondCheckTotalValidPasswords = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parse(splitString);
        TextView outputTextView = (TextView) findViewById(R.id.output_text_view);
        outputTextView.setText("VALID PASSWORDS FOUND BY FIRST RULES: " + totalValidPasswords+"\nVALID PASSWORDS FOUND BY SECOND RULES: "+secondCheckTotalValidPasswords);
    }

    private void parse(String[] list) {
        String text;
        String searchChar;
        int minBound;
        int maxBound;


        for (int i = 0; i < list.length; i++) {

            String tempString;
            int colonAT = list[i].indexOf(":");
            text = list[i].substring(colonAT + 1, list[i].length());
            char tempChar = list[i].charAt(colonAT - 1);
            searchChar = Character.toString(tempChar);
            int dashAT = list[i].indexOf("-");
            tempString = list[i].substring(0, dashAT);
            minBound = Integer.parseInt(tempString);
            tempString = list[i].substring(dashAT + 1, colonAT - 2);
            maxBound = Integer.parseInt(tempString);
            checkPassword(text, searchChar, minBound, maxBound);
            checkPasswordTwoElectricBoogaloo(text, searchChar, minBound, maxBound);
        }
    }

    private void checkPassword(String text, String searchChar, int mindBound, int maxBound) {
        int totalFound = 0;
        String[] passwordText = text.split("");

        for (int i = 0; i < passwordText.length; i++) {
            if (passwordText[i].equals(searchChar)) {
                totalFound++;
            }
        }
        if ((totalFound >= mindBound) && (totalFound <= maxBound)) {
            totalValidPasswords++;
        }

    }

    private void checkPasswordTwoElectricBoogaloo (String text, String searchChar, int minBound, int maxBound){
        String[] passwordText = text.split("");
        if ((passwordText[minBound].equals(searchChar))&&(!passwordText[maxBound].equals(searchChar))){
            secondCheckTotalValidPasswords++;
        }
        if((!passwordText[minBound].equals(searchChar))&&(passwordText[maxBound].equals(searchChar))){
            secondCheckTotalValidPasswords++;
        }
    }
}