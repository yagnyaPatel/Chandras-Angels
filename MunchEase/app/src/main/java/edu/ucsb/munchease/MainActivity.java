package edu.ucsb.munchease;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!isInternetAvailable())
        {
            String noIntern = "No Internet Connection";
            Snackbar message = Snackbar.make((CoordinatorLayout) findViewById(R.id.myCoordinatorLayout), noIntern, 8000);
            message.show();
        }
        Button button_createParty = findViewById(R.id.button_createParty);
        button_createParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSharePartyActivity = new Intent(getApplicationContext(), SharePartyActivity.class);
                startActivity(goToSharePartyActivity);
            }
        });
    }
    public boolean isInternetAvailable(){
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

}
