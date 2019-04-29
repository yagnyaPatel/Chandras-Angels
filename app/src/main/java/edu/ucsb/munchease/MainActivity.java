package edu.ucsb.munchease;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.auth.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

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

        // -----
        // FIREBASE AUTH STUFF
        // -----

        // Initialize FrebaseAuth
        mAuth = FirebaseAuth.getInstance();

        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            // Sign in success, display Toast
                            Toast.makeText(MainActivity.this, "Anonymous authentication successful",
                                    Toast.LENGTH_SHORT).show();
                        }

                        else {
                            Toast.makeText(MainActivity.this, "Anonymous authentication failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

        });

        //
    }
}
