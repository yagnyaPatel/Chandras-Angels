package edu.ucsb.munchease.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.widget.TextView;

import edu.ucsb.munchease.R;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private TextView textView_test;

    private RecyclerView recyclerView_searchSuggestions;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initializeComponents();
        setUpSearchbar();
    }

    private void initializeComponents() {
        searchView = findViewById(R.id.searchView);
        textView_test = findViewById(R.id.textView_test);

        recyclerView_searchSuggestions = findViewById(R.id.recyclerView_searchSuggestions);
    }

    private void setUpSearchbar() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                textView_test.setText("Searched For: " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                textView_test.setText("Current Text: " + newText);
                return false;
            }
        });
    }
}
