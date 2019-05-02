package edu.ucsb.munchease.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.ucsb.munchease.R;

public class SharePartyActivity extends AppCompatActivity {

    Button button_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_party);

        //Make "NEXT" button (button_next) take you to the Party Home Activity (PartyHomeActivity)
        setUpButtonNext();
    }

    private void setUpButtonNext() {
        button_next = findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPartyHomeActivity = new Intent(getApplicationContext(), PartyHomeActivity.class);
                startActivity(goToPartyHomeActivity);
            }
        });
    }
}
