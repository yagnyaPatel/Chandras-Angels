package edu.ucsb.munchease.activity;

public class Searcheable {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
            YelpFusionApi yelpFusionApi = apiFactory.createAPI(apiKey);
            Map<String, String> params = new HashMap<>();
            // general params
            params.put("term", query);
        }
    }
    Call<SearchResponse> req = yelpFusionApi.getBusinessSearch(params);
    Response<SearchResponse> response = call.execute();
    Callback<SearchResponse> callback = new Callback<SearchResponse>() {
        @Override
        public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
            SearchResponse searchResponse = response.body();
        }
        @Override
        public void onFailure(Call<SearchResponse> call, Throwable t) {
            // HTTP error happened, do something to handle it.
        }
    };
    call.enqueue(callback);
}
