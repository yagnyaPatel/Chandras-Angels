package edu.ucsb.munchease.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.ucsb.munchease.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //------------------------------------------------------------------
        //BUTTON CONFIGURATION
        //------------------------------------------------------------------

        //Make "CREATE" button (button_createParty) take you to the Share Party Activity (SharePartyActivity)
        Button button_createParty = findViewById(R.id.button_createParty);
        button_createParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSharePartyActivity = new Intent(getApplicationContext(), SharePartyActivity.class);
                startActivity(goToSharePartyActivity);
            }
        });

        Button button_joinParty = findViewById(R.id.button_joinParty);
        button_joinParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToJoinPartyActivity = new Intent(getApplicationContext(), JoinPartyActivity.class);
                startActivity(goToJoinPartyActivity);
            }
        });


    }
}
