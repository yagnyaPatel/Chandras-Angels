package com.example.munchease;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_CreateParty = findViewById(R.id.button_createParty); //Get the CREATE button and assign it to the button_createParty object
        button_CreateParty.setOnClickListener(new View.OnClickListener() {  //Set the action that occurs when the CREATE button is clicked
            @Override
            public void onClick(View v) {
                Intent goToShareParty = new Intent(getApplicationContext(), SharePartyActivity.class);  //Create a new intent to go to the Share Party activity
                startActivity(goToShareParty);  //Start the new activity
            }
        });
    }
}
