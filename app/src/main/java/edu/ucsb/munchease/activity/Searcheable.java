package edu.ucsb.munchease.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.api.Response;
import com.yelp.fusion.client.connection.*;
import com.yelp.fusion.client.models.*;

import java.util.HashMap;
import java.util.Map;

import edu.ucsb.munchease.R;
import retrofit2.Call;
import retrofit2.Callback;

public class Searcheable extends AppCompatActivity {
    String query = "";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
        }
    }
    //String inputString = ""; //Placeholder for the input from the query
    YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
    public Response<SearchResponse> getResponse(){
        YelpFusionApi yelpFusionApi = apiFactory.createAPI("_OQqAnq91MYWUPjoqbXMTIcDSpcoIcXqhbKDASfG1CQf1OXmyL7Zjf1DHPwwncAk4sOuc1YQY79xcynpQ93ewSUMfynihNmTR1ckaAWNNeNhfIVLhQ-Q04YRy_XAXHYx");
        Map<String, String> params = new HashMap<>();
        // general params
        params.put("term", query);
        Call<SearchResponse> req = yelpFusionApi.getBusinessSearch(params);
        Response<SearchResponse> response;
        response = call.execute();
        return response;
    };
    call.enqueue(callback);

}
/*

 */