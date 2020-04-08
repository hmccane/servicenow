/**
 * ReviewListFragment - Fragment with a simple RecyclerView that holds the review list
 * Usage:
 * ReviewlistFragment rlFragment = new ReviewListFragment();
 *
 * @author Henry Song
 */
package com.servicenow.exercise_java;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import androidx.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.servicenow.coffee.Review;
import com.servicenow.coffee.CoffeeShopReviews;
import com.servicenow.exercise.R;


import com.servicenow.coffee.Review;

public class ReviewListFragment extends Fragment {
    static final Review[] coffeeShopReviews = CoffeeShopReviews.INSTANCE.getList();

    public ReviewListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new ReviewListAdapter(coffeeShopReviews));
        return rv;
    }

    /**
     * A Simple Adapter for the RecyclerView
     */
    public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListViewHolder> {
        private Review[] dataSource;
        public ReviewListAdapter(Review[] data){
            dataSource = data;
        }

        @Override
        public ReviewListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
            ReviewListViewHolder viewHolder = new ReviewListViewHolder(row);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ReviewListViewHolder holder, int position) {
            holder.shop = dataSource[position].getName();
            holder.location = dataSource[position].getLocation();
            holder.rating = Integer.toString(dataSource[position].getRating());
            holder.review = dataSource[position].getReview();

            holder.shopName.setText(holder.shop);
            holder.reviewText.setText(holder.review);
            holder.reviewImage.setImageResource(Review.getIconResourceFromName(holder.shop));

            holder.row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("shop", holder.shop);
                    bundle.putString("rating", holder.rating);
                    bundle.putString("location", holder.location);
                    bundle.putString("review", holder.review);

                    FragmentManager manager = getFragmentManager();
                    Fragment detailFragment = FragmentUtil.getFragmentByTagName(manager, "Detail Fragment");
                    if (detailFragment == null) {
                        detailFragment = new DetailFragment();
                    }
                    detailFragment.setArguments(bundle);
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.activity_frame, detailFragment,  "Detail Fragment");
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataSource.length;
        }
    }

    /**
     * A Simple ViewHolder for the RecyclerView 
     */
    public static class ReviewListViewHolder extends RecyclerView.ViewHolder{
        public TextView reviewText;
        public TextView shopName;
        public ImageView reviewImage;
        public View row;

        public String review;
        public String shop;
        public String rating;
        public String location;

        public ReviewListViewHolder(View itemView) {
            super(itemView);
            row = itemView;
            shopName = (TextView) itemView.findViewById(R.id.text1);
            reviewText = (TextView) itemView.findViewById(R.id.text2);
            reviewImage = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}