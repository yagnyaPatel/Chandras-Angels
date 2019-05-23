package edu.ucsb.munchease.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.util.HashMap;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;
import edu.ucsb.munchease.R;
import android.widget.TextView;

public class Searchable extends AppCompatActivity {
    String query = "";
    final TextView textView = (TextView) findViewById(R.id.text);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
        }
    }

    String api_key = "_OQqAnq91MYWUPjoqbXMTIcDSpcoIcXqhbKDASfG1CQf1OXmyL7Zjf1DHPwwncAk4sOuc1YQY79xcynpQ93ewSUMfynihNmTR1ckaAWNNeNhfIVLhQ-Q04YRy_XAXHYx";
    RequestQueue queue = Volley.newRequestQueue(this);

    String url = "https://api.yelp.com/v3/businesses/search";
    /**
    public class Parameters {
        HashMap<String, String> mParams = new HashMap<String, String>();

        public Parameters() {
            mParams.put("Authorization", "Bearer " + "_OQqAnq91MYWUPjoqbXMTIcDSpcoIcXqhbKDASfG1CQf1OXmyL7Zjf1DHPwwncAk4sOuc1YQY79xcynpQ93ewSUMfynihNmTR1ckaAWNNeNhfIVLhQ");
            mParams.put("cache-control", "no-cache");
        }
    }
     */

    // TODO dummy
    public void search() {
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: " + response.substring(0, 500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        }) {
            @Override
            protected HashMap<String, String> getParams() {
                HashMap<String, String> mParams = new HashMap<String, String>();
                mParams.put("Authorization", "Bearer " + "_OQqAnq91MYWUPjoqbXMTIcDSpcoIcXqhbKDASfG1CQf1OXmyL7Zjf1DHPwwncAk4sOuc1YQY79xcynpQ93ewSUMfynihNmTR1ckaAWNNeNhfIVLhQ");
                mParams.put("cache-control", "no-cache");
                return mParams;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
/*

 */