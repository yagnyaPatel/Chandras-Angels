package edu.ucsb.munchease.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.SearchResponse;

import edu.ucsb.munchease.R;

import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.TextView;

public class Searchable extends AppCompatActivity {
    String query = "";
    TextView textView;
    RequestQueue queue;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        textView = findViewById(R.id.search_text);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            query = intent.getStringExtra(SearchManager.QUERY);

            // Pass data back to PartyHomeActivity
            Intent returnIntent = new Intent();
            returnIntent.putExtra("query", query);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }

        queue = Volley.newRequestQueue(this);
    }

    @Override
      public boolean onCreateOptionsMenu(Menu menu) {
           MenuInflater inflater = getMenuInflater();
           inflater.inflate(R.menu.options_menu, menu);

           // Associate searchable configuration with the SearchView
           SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
           SearchView searchView =
                   (SearchView) menu.findItem(R.id.search).getActionView();
           searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

           return true;
    }
}
/*

 */
