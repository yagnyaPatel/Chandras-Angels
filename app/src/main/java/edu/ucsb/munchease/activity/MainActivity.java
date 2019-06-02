package edu.ucsb.munchease.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import edu.ucsb.munchease.R;

// TODO Fix duplicate toasts bug

public class MainActivity extends AppCompatActivity {

    private ImageView imageView_logoMain;
    private Button button_createParty;
    private Button button_joinParty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView_logoMain = findViewById(R.id.imageView_logoMain);

        //------------------------------------------------------------------
        // *** BUTTON CONFIGURATION ***
        //------------------------------------------------------------------

        //Make "CREATE" button (button_createParty) take you to the Share Party Activity (SharePartyActivity)
        button_createParty = findViewById(R.id.button_createParty);
        button_createParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseAuth.getInstance().getCurrentUser() != null) {
                    // Only navigate to other pages if user is signed in
                    Intent goToSharePartyActivity = new Intent(getApplicationContext(), SharePartyActivity.class);
                    startActivity(goToSharePartyActivity);
                }
                else {
                    Toast.makeText(MainActivity.this, "Error: Not signed into database. Attempting sign in...",
                            Toast.LENGTH_SHORT).show();
                    attemptSignIn();
                }
            }
        });

        button_joinParty = findViewById(R.id.button_joinParty);
        button_joinParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseAuth.getInstance().getCurrentUser() != null)
                {
                    // Only navigate if user is signed in
                    Intent goToJoinPartyActivity = new Intent(getApplicationContext(), JoinPartyActivity.class);
                    startActivity(goToJoinPartyActivity);
                }
                else {
                    Toast.makeText(MainActivity.this, "Error: Not signed into database. Attempting sign in...",
                            Toast.LENGTH_SHORT).show();
                    attemptSignIn();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    // Sign in anonymously to Firebase so database can be accessed
    private void attemptSignIn() {
        FirebaseAuth.getInstance().signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Create toast with status
                        if(task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Successfully signed into database with id "
                                    + FirebaseAuth.getInstance().getCurrentUser().getUid(), Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Sign in failed",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }

}
