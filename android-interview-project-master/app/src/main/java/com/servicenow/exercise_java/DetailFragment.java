package com.servicenow.exercise_java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.servicenow.coffee.Review;
import com.servicenow.exercise.R;

import static com.servicenow.exercise_java.ReviewListFragment.coffeeShopReviews;

public class DetailFragment extends Fragment {
    public DetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail, container, false);

        ImageView image = (ImageView) view.findViewById(R.id.detail_image);
        image.setImageResource(Review.getIconResourceFromName(this.getArguments().getString("shop")));
        TextView review = (TextView) view.findViewById(R.id.detail_review_text);
        review.setText(this.getArguments().getString("review"));
        TextView rating = (TextView) view.findViewById(R.id.detail_rating_text);
        rating.setText(this.getArguments().getString("rating"));
        TextView loc = (TextView) view.findViewById(R.id.detail_location_text);
        loc.setText(this.getArguments().getString("location"));
        return view;
    }
}
