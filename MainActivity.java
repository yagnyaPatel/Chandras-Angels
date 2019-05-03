package edu.ucsb.munchease;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {
    SearchView sView;
    ListView sList;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_createParty = findViewById(R.id.button_createParty);
        button_createParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSharePartyActivity = new Intent(getApplicationContext(), SharePartyActivity.class);
                startActivity(goToSharePartyActivity);
            }
        });
        sView = (SearchView)findViewById(R.id.searchView);
        sList = (ListView)findViewById(R.id.ListView);

        list.add("Filler1");
        list.add("Filler2");
        list.add("Filler3");
        list.add("Filler4");
        adapter = new ArrayAdapter<>(this.android.R.layout.simple_list_item_1,list);
        myList.setAdapter(adapter);

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
           @Override
           public boolean onQueryTextSubmit(String s){
               return false;
           }

           @Override
            public boolean onQueryTextChange(String s){
               adapter.getFilter().filter(s);
               return false;
           }
        });
    }

}
