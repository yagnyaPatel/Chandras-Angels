package edu.ucsb.munchease.view;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.ucsb.munchease.R;
import edu.ucsb.munchease.data.Restaurant;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private ArrayList<Restaurant> restaurants;

    // Provide a suitable constructor (depends on the kind of dataset)
    public RestaurantAdapter(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        //Basic information
        public TextView textView_restaurantName;

        //Voting components
        public Button button_upvote, button_downvote;
        public TextView textView_votes;

        //Rating components
        public ImageView imageView_stars;
        public TextView textView_numberOfReviews;
        public TextView textView_price;

        public RestaurantViewHolder(View v) {
            super(v);

            textView_restaurantName = v.findViewById(R.id.textView_restaurantName);

            textView_votes = v.findViewById(R.id.textView_votes);

            imageView_stars = v.findViewById(R.id.imageView_stars);
            textView_numberOfReviews = v.findViewById(R.id.textView_numberOfReviews);
            textView_price = v.findViewById(R.id.textView_price);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_restaurant_list_item, parent, false);
        RestaurantViewHolder vh = new RestaurantViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.restaurantView.setText(mDataset[position]);
        holder.textView_restaurantName.setText(restaurants.get(position).getName());
        holder.textView_numberOfReviews.setText(restaurants.get(position).getNumberOfReviews() + " reviews");
        holder.textView_price.setText(restaurants.get(position).getPrice());
        holder.textView_votes.setText(restaurants.get(position).getVotes() + "");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}