package edu.ucsb.munchease;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //------------------------------------------------------------------
        //DATABASE TESTING
        //------------------------------------------------------------------

        /*
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("123456");
        myRef.setValue(1);
        */

        //------------------------------------------------------------------
        //BUTTON CONFIGURATION
        //------------------------------------------------------------------

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
