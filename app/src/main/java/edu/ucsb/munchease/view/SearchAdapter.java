package edu.ucsb.munchease.view;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import edu.ucsb.munchease.R;
import edu.ucsb.munchease.data.Restaurant;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.RestaurantViewHolder> {

    private ArrayList<Restaurant> restaurants;
    private static String partyID;

    // Provide a suitable constructor (depends on the kind of dataset)
    public SearchAdapter(ArrayList<Restaurant> restaurants, String partyID) {
        this.restaurants = restaurants;
        this.partyID = partyID;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        private Restaurant restaurant;

        private FirebaseFirestore db;
        private DocumentReference docRef;
        private CollectionReference restaurantsRef;

        //Basic information
        private TextView textView_restaurantName;
        final public static int RESTAURANT_NAME_MAX_LENGTH = 24;

        //Rating components
        private ImageView imageView_stars;
        private TextView textView_numberOfReviews;
        private TextView textView_price;

        //Add button
        private Button button_add;

        public RestaurantViewHolder(View v) {
            super(v);

            textView_restaurantName = v.findViewById(R.id.textView_restaurantName);

            imageView_stars = v.findViewById(R.id.imageView_stars);
            textView_numberOfReviews = v.findViewById(R.id.textView_numberOfReviews);
            textView_price = v.findViewById(R.id.textView_price);

            button_add = v.findViewById(R.id.button_add);

            setUpFirebase();
            setUpAddButton();
        }

        /**
         * Initializes the Firebase references
         */
        private void setUpFirebase() {
            db = FirebaseFirestore.getInstance();
            docRef = db.collection("parties").document(SearchAdapter.partyID);
            restaurantsRef = docRef.collection("restaurants");
        }

        private void setUpAddButton() {
            button_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addRestaurantToParty(restaurant);
                }
            });
        }

        /**
         * Adds a restaurant to the DATABASE party and pushes it back to the database.  This means that it does NOT deal with the local party at all!!!
         * @param r The restaurant to be added to the party
         */
        private void addRestaurantToParty(final Restaurant r) {
            DocumentReference newRestaurantRef = restaurantsRef.document(r.getName());

            newRestaurantRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("---RETRIEVE---", "Restaurant already exists: " + document.getData());
                        } else {
                            Log.d("---RETRIEVE---", "No such document - creating now");
                            restaurantsRef.document(r.getName()).set(r);
                        }
                    } else {
                        Log.d("---RETRIEVE---", "get failed with ", task.getException());
                    }
                }
            });
        }

        private void setRestaurant(Restaurant r) {
            restaurant = r;
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_restaurant_list_suggestion, parent, false);
        return new RestaurantViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);

        holder.setRestaurant(restaurant);

        if(restaurant.getName().length() > RestaurantViewHolder.RESTAURANT_NAME_MAX_LENGTH) {
            String shortenedText = restaurant.getName().substring(0, RestaurantViewHolder.RESTAURANT_NAME_MAX_LENGTH - 5) + "...";
            holder.textView_restaurantName.setText(shortenedText);
        } else {
            holder.textView_restaurantName.setText(restaurant.getName());
        }

        holder.textView_numberOfReviews.setText(restaurant.getReviewCount() + " reviews");
        holder.textView_price.setText(restaurant.getPrice());
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
            case "1.0":
                return R.drawable.stars_regular_1;
            case "1.5":
                return R.drawable.stars_regular_1_half;
            case "2.0":
                return R.drawable.stars_regular_2;
            case "2.5":
                return R.drawable.stars_regular_2_half;
            case "3.0":
                return R.drawable.stars_regular_3;
            case "3.5":
                return R.drawable.stars_regular_3_half;
            case "4.0":
                return R.drawable.stars_regular_4;
            case "4.5":
                return R.drawable.stars_regular_4_half;
            case "5.0":
                return R.drawable.stars_regular_5;
            default:
                return R.drawable.stars_regular_0;
        }
    }
}
