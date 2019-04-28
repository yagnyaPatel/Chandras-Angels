package edu.ucsb.munchease.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.StyleableRes;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.ucsb.munchease.R;
import edu.ucsb.munchease.data.Restaurant;

public class RestaurantListItem extends ConstraintLayout {

    @StyleableRes
    int index0 = 0;
    @StyleableRes
    int index1 = 1;
    @StyleableRes
    int index2 = 2;
    @StyleableRes
    int index3 = 3;
    @StyleableRes
    int index4 = 4;
    @StyleableRes
    int index5 = 5;
    @StyleableRes
    int index6 = 6;

    Restaurant restaurant;

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

        int[] sets = {R.attr.id, R.attr.link, R.attr.restaurantName, R.attr.rating, R.attr.numberOfReviews, R.attr.price, R.attr.votes};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
        CharSequence id = typedArray.getText(index0);
        CharSequence link = typedArray.getText(index1);
        CharSequence restaurantName = typedArray.getText(index2)
        CharSequence rating = typedArray.getText(index3);
        CharSequence numberOfReviews = typedArray.getText(index4);
        CharSequence price = typedArray.getText(index5);
        CharSequence votes = typedArray.getText(index6);
        typedArray.recycle();

        initComponents();

        setRestaurantName(restaurantName);
        setNumberOfReviews(numberOfReviews);
        setPrice(price);
        setVotes(votes);
    }

    private void initComponents() {
        textView_restaurantName = findViewById(R.id.textView_restaurantName);
        imageView_stars = findViewById(R.id.imageView_stars);
        textView_numberOfReviews = findViewById(R.id.textView_numberOfReviews);
        textView_price = findViewById(R.id.textView_price);
        textView_votes = findViewById(R.id.textView_votes);
    }

    public void setID

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
