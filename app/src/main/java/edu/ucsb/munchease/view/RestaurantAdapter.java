package edu.ucsb.munchease.view;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

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

        private FirebaseFirestore db;
        private DocumentReference docRef;

        //Basic information
        public TextView textView_restaurantName;

        //Voting components
        public ImageButton button_upvote, button_downvote;
        public TextView textView_votes;

        //Rating components
        public ImageView imageView_stars;
        public TextView textView_numberOfReviews;
        public TextView textView_price;

        public RestaurantViewHolder(View v) {
            super(v);

            setUpFirebase();

            textView_restaurantName = v.findViewById(R.id.textView_restaurantName);

            textView_votes = v.findViewById(R.id.textView_votes);

            imageView_stars = v.findViewById(R.id.imageView_stars);
            textView_numberOfReviews = v.findViewById(R.id.textView_numberOfReviews);
            textView_price = v.findViewById(R.id.textView_price);

            button_upvote = v.findViewById(R.id.imageButton_upvote);
            button_downvote = v.findViewById(R.id.imageButton_downvote);

            button_upvote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("---ADAPTER---", "Upvote button clicked");
                    //docRef.update("members", FieldValue.increment(1)); //TODO Fix this line
                }
            });

            button_downvote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("---ADAPTER---", "Downvote button clicked");
                    docRef.update("members", FieldValue.increment(-1));
                }
            });
        }

        /**
         * Initializes the Firebase references
         */
        private void setUpFirebase() {
            db = FirebaseFirestore.getInstance();
            docRef = db.collection("parties").document("123456");
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_restaurant_list_item, parent, false);
        return new RestaurantViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);

        holder.textView_restaurantName.setText(restaurant.getName());
        holder.textView_numberOfReviews.setText(restaurant.getNumberOfReviews() + " reviews");
        holder.textView_price.setText(restaurant.getPrice());
        holder.textView_votes.setText(restaurant.getVotes() + "");
        holder.imageView_stars.setImageResource(ratingImageHelper(restaurant.getRating()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    private int ratingImageHelper(String rating) {
        switch (rating) {
            case "0":
                return R.drawable.stars_regular_0;
            case "1":
                return R.drawable.stars_regular_1;
            case "1.5":
                return R.drawable.stars_regular_1_half;
            case "2":
                return R.drawable.stars_regular_2;
            case "2.5":
                return R.drawable.stars_regular_2_half;
            case "3":
                return R.drawable.stars_regular_3;
            case "3.5":
                return R.drawable.stars_regular_3_half;
            case "4":
                return R.drawable.stars_regular_4;
            case "4.5":
                return R.drawable.stars_regular_4_half;
            case "5":
                return R.drawable.stars_regular_5;
            default:
                return R.drawable.stars_regular_0;
        }
    }
}
