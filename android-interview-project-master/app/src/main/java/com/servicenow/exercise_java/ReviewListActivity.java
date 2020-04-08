package com.servicenow.exercise_java;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.servicenow.coffee.Review;
import com.servicenow.coffee.CoffeeShopReviews;
import com.servicenow.exercise.R;

public class ReviewListActivity extends ListActivity {

    public static final Review[] coffeeShopReviews = CoffeeShopReviews.INSTANCE.getList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ReviewAdapter());
    }

    class ReviewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return coffeeShopReviews.length;
        }

        @Override
        public Object getItem(int position) {
            return coffeeShopReviews[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if (row == null) {
                row = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
            }

            ImageView reviewImage = row.findViewById(R.id.image);
            TextView shopName = row.findViewById(R.id.text1);
            TextView reviewText = row.findViewById(R.id.text2);

            Review review = coffeeShopReviews[position];
            shopName.setText(review.getName());
            reviewText.setText(review.getReview());
            reviewImage.setImageResource(Review.getIconResourceFromName(review.getName()));

            return row;
        }
    }
}
