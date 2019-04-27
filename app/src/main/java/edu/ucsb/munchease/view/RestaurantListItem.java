package edu.ucsb.munchease.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.ucsb.munchease.R;

public class RestaurantListItem extends ConstraintLayout {

    //Basic information
    TextView textView_restaurantName;

    //Voting components
    Button button_upvote, button_downvote;
    TextView textView_votes;

    //Rating components
    ImageView imageView_stars;
    TextView textView_numberOfReviews;
    TextView textView_price;

    public RestaurantListItem(Context context) {
        super(context);
    }

    public RestaurantListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RestaurantListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.activity_restaurant_list_item,this);
    }

    public void setRestaurantName(CharSequence restaurantName) {
        textView_restaurantName.setText(restaurantName);
    }

    public void setVotes(CharSequence votes) {
        textView_votes.setText(votes);
    }

    public void setVotes(int votes) {
        textView_votes.setText(votes + "");
    }

    public void setNumberOfReviews(CharSequence numberOfReviews) {
        textView_numberOfReviews.setText(numberOfReviews);
    }

    public void setNumberOfReviews(int numberOfReviews) {
        textView_numberOfReviews.setText(numberOfReviews + "");
    }

    public void setPrice(CharSequence price) {
        textView_price.setText(price);
    }

    public void setPrice(int price) {
        switch (price) {
            case 1:
                textView_price.setText(R.string.price_1);
                break;
            case 2:
                textView_price.setText(R.string.price_2);
                break;
            case 3:
                textView_price.setText(R.string.price_3);
                break;
            case 4:
                textView_price.setText(R.string.price_4);
                break;
            default:
                textView_price.setText(R.string.price_unavailable);
        }
    }
}
