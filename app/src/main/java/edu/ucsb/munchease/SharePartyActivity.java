package edu.ucsb.munchease;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SharePartyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_party);

        Button button_next = findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPartyHomeActivity = new Intent(getApplicationContext(), PartyHomeActivity.class);
                startActivity(goToPartyHomeActivity);
            }
        });
    }
}
