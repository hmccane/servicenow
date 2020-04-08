/**
 * DetailFragment - Fragment holds the detailed review for each shop
 * Information about a shop is delivered via bundle in argument.
 *
 * @author Henry Song
 */
package com.servicenow.exercise_java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.Fragment;

import com.servicenow.coffee.Review;
import com.servicenow.exercise.R;

public class DetailFragment extends Fragment {
    public DetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail, container, false);

        // load information to each view vi bundled argument.
        // load shop name
        TextView shop = (TextView) view.findViewById(R.id.detail_name);
        shop.setText(this.getArguments().getString("shop"));
        // load Image via shop name
        ImageView image = (ImageView) view.findViewById(R.id.detail_image);
        image.setImageResource(Review.getIconResourceFromName(this.getArguments().getString("shop")));
        // load review text
        TextView review = (TextView) view.findViewById(R.id.detail_review_text);
        review.setText(this.getArguments().getString("review"));
        // load rating
        TextView rating = (TextView) view.findViewById(R.id.detail_rating_text);
        rating.setText(this.getArguments().getString("rating"));
        // load location
        TextView loc = (TextView) view.findViewById(R.id.detail_location_text);
        loc.setText(this.getArguments().getString("location"));
        return view;
    }
}
